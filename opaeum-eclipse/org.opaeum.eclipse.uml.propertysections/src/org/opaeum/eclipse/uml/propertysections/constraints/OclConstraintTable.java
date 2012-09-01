package org.opaeum.eclipse.uml.propertysections.constraints;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.uml.editingsupport.ConstraintSpecificationEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.UmlElementImageProvider;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;

public class OclConstraintTable extends Composite{
	private static final int TABLE_HEIGHT = 60;
	private TabbedPropertySheetWidgetFactory theFactory;
	private TableViewer constraintTableViewer;
	private Element context = null;
	private EditingDomain currentEditDomain = null;
	private EStructuralFeature feature;
	private Constraint selectedConstraint;
	private TabbedPropertySheetWidgetFactory toolkit;
	private Set<EditingDomainEditingSupport> editingSupport = new HashSet<EditingDomainEditingSupport>();
	private RecursiveAdapter adapter = new RecursiveAdapter(){
		public void notifyChanged(Notification msg){
			super.notifyChanged(msg);
			if(msg.getNotifier() instanceof EObject && msg.getFeature() instanceof EStructuralFeature){
				if(constraintTableViewer.getTable().isDisposed()){
					adapter.unsubscribe();
				}else{
					Constraint c = EmfElementFinder.findNearestElementOfType(Constraint.class, (EObject) msg.getNotifier());
					Control focusControl = Display.getCurrent().getFocusControl();
					if(msg.getFeature().equals(UMLPackage.eINSTANCE.getOpaqueExpression_Body()) && focusControl instanceof StyledText
							&& focusControl.getParent().getParent() == constraintTableViewer.getTable()){
						// nothing-this control caused it
					}else{
						constraintTableViewer.refresh(c);
					}
				}
			}
		};
	};
	public OclConstraintTable(TabbedPropertySheetWidgetFactory factory,Composite parent,EStructuralFeature feature){
		super(parent, SWT.NONE);
		this.feature = feature;
		theFactory = factory;
		setBackground(parent.getBackground());
		setLayout(new GridLayout(2, false));
		createGroupConstraints(this);
		this.toolkit = factory;
	}
	private TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return theFactory;
	}
	private void createGroupConstraints(Composite composite){
		// add the table for listing all the constraints
		constraintTableViewer = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.FILL);
		constraintTableViewer.setContentProvider(new ArrayContentProvider());
		constraintTableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(event.getSelection() instanceof IStructuredSelection){
					IStructuredSelection selec = (IStructuredSelection) event.getSelection();
					setSelectedConstraint((Constraint) selec.getFirstElement());
				}
			}
		});
		// create the columns
		applyTableInfo();
		GridData layoutDataConstraintsList = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 2);
		layoutDataConstraintsList.heightHint = TABLE_HEIGHT;
		constraintTableViewer.getTable().setLayoutData(layoutDataConstraintsList);
		Button plus = getWidgetFactory().createButton(composite, "Add", SWT.PUSH);
		plus.setImage(ImageManager.IMG_ADD);
		plus.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e){
				createConstraints();
			}
		});
		plus.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1));
		Button minus = getWidgetFactory().createButton(composite, "Delete", SWT.PUSH);
		minus.setImage(ImageManager.IMG_DELETE);
		minus.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e){
				deleteConstraints();
			}
		});
		minus.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1));
	}
	private EStructuralFeature getFeature(EObject e,int id){
		return e.eClass().getEStructuralFeature(id);
	}
	private void deleteConstraints(){
		if(context != null && currentEditDomain != null){
			if(selectedConstraint != null){
				currentEditDomain.getCommandStack().execute(DeleteCommand.create(currentEditDomain, selectedConstraint));
				setSelectedConstraint(null);
				constraintTableViewer.setInput(getConstraints());
				constraintTableViewer.refresh();
				if(constraintTableViewer.getTable().getItems().length > 0){
					setSelectedConstraint((Constraint) constraintTableViewer.getTable().getItems()[0].getData());
				}
			}
		}
	}
	private void createConstraints(){
		if(context != null && currentEditDomain != null){
			Constraint newConstraint = UMLFactory.eINSTANCE.createConstraint();
			newConstraint.setName("newConstraint");
			OpaqueExpression oclExpression = createExpression(newConstraint);
			newConstraint.setSpecification(oclExpression);
			currentEditDomain.getCommandStack().execute(AddCommand.create(currentEditDomain, context, this.feature, newConstraint));
			adapter.subscribeTo(newConstraint, 4);
			constraintTableViewer.setInput(getConstraints());
			constraintTableViewer.refresh();
			setSelectedConstraint(newConstraint);
		}
	}
	private OpaqueExpression createExpression(Constraint newConstraint){
		OpaqueExpression oclExpression = UMLFactory.eINSTANCE.createOpaqueExpression();
		if(selectedConstraint != null){
			oclExpression.setName(selectedConstraint.getName() + "_body");
		}else{
			oclExpression.setName("newConstraint_body");
		}
		oclExpression.getLanguages().add("OCL");
		String body = "";
		oclExpression.getBodies().add(body);
		return oclExpression;
	}
	void applyTableInfo(){
		Table ta = constraintTableViewer.getTable();
		ta.setHeaderVisible(true);
		ta.setLinesVisible(true);
		TableViewerColumn icon = createTableViewerColumn("", 20, 0);
		icon.setLabelProvider(new UmlElementImageProvider());
		TableViewerColumn name = createTableViewerColumn("Name", 150, 1);
		NamedElementNameEditingSupport nameEditingSupport = new NamedElementNameEditingSupport(constraintTableViewer);
		name.setEditingSupport(nameEditingSupport);
		name.setLabelProvider(nameEditingSupport.getLabelProvider());
		editingSupport.add(nameEditingSupport);
		TableViewerColumn spec = createTableViewerColumn("Condition", 200, 2);
		ConstraintSpecificationEditingSupport specEditingSupport = new ConstraintSpecificationEditingSupport(constraintTableViewer, toolkit);
		spec.setEditingSupport(specEditingSupport);
		editingSupport.add(specEditingSupport);
		spec.setLabelProvider(specEditingSupport.getLabelProvider());
	}
	private TableViewerColumn createTableViewerColumn(String title,int bound,final int colNumber){
		final TableViewerColumn viewerColumn = new TableViewerColumn(constraintTableViewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	public static int getMinHeight(){
		return 150;
	}
	@Override
	public void dispose(){
		super.dispose();
		adapter.unsubscribe();
	}
	public void setContext(Element theContext){
		if(this.context != null){
			adapter.unsubscribe();
		}
		context = theContext;
		if(theContext != null){
			EList<Constraint> constraints = getConstraints();
			for(Constraint constraint:constraints){
				adapter.subscribeTo(constraint, 3);
			}
			constraintTableViewer.setInput(constraints);
			if(constraints.size() == 1){
				setSelectedConstraint(constraints.iterator().next());
			}
		}
	}
	private EList<Constraint> getConstraints(){
		return((EList<Constraint>) context.eGet(feature));
	}
	public void setConstraints(Collection<Constraint> constraints){
		constraintTableViewer.setInput(constraints);
		constraintTableViewer.update(constraints, null);
	}
	public void setSelectedConstraint(Constraint theConstraint){
		if(theConstraint != null && !theConstraint.equals(((IStructuredSelection) constraintTableViewer.getSelection()).getFirstElement())){
			this.selectedConstraint = theConstraint;
			constraintTableViewer.setSelection(new StructuredSelection(theConstraint));
		}
	}
	public void setEditDomain(EditingDomain domain){
		for(EditingDomainEditingSupport es:this.editingSupport){
			es.setEditingDomain(domain);
		}
		currentEditDomain = domain;
	}
	public void refreshConstraint(Constraint a){
		constraintTableViewer.refresh(a);
	}
}
