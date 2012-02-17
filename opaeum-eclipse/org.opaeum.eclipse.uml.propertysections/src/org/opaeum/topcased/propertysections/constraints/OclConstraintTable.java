package org.opaeum.topcased.propertysections.constraints;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;

public class OclConstraintTable extends Composite{
	private static final int TABLE_HEIGHT = 60;
	private TabbedPropertySheetWidgetFactory theFactory;
	private TableViewer constraintTableViewer;
	private Element context = null;
	private EditingDomain currentEditDomain = null;
	private EStructuralFeature feature;
	private Constraint selectedConstraint;
	public OclConstraintTable(TabbedPropertySheetWidgetFactory factory,Composite parent,EStructuralFeature feature){
		super(parent, SWT.NONE);
		this.feature = feature;
		theFactory = factory;
		setBackground(parent.getBackground());
		setLayout(new GridLayout(2, false));
		createGroupConstraints(this);
	}
	private TabbedPropertySheetWidgetFactory getWidgetFactory(){
		return theFactory;
	}
	private void createGroupConstraints(Composite composite){
		// add the table for listing all the constraints
		constraintTableViewer = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.FILL);
		constraintTableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(event.getSelection() instanceof IStructuredSelection){
					IStructuredSelection selec = (IStructuredSelection) event.getSelection();
					setSelectedConstraint((Constraint) selec.getFirstElement());
				}
			}
		});
		// create the columns
		applyTableInfo(constraintTableViewer, null /* OpaeumPlugin.getDefault().getImageRegistry().getDescriptor("Actor").createImage()*/, new int[]{
				200,200
		}, "Name", "Expression");
		GridData layoutDataConstraintsList = new GridData(SWT.FILL, SWT.BEGINNING, true, false, 1, 2);
		layoutDataConstraintsList.heightHint = TABLE_HEIGHT;
		constraintTableViewer.getTable().setLayoutData(layoutDataConstraintsList);
		constraintTableViewer.setContentProvider(new ConstraintContentProvider());
		constraintTableViewer.setLabelProvider(new ConstraintLabelProvider());
		Button plus = getWidgetFactory().createButton(composite, "Add", SWT.PUSH);
		plus.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseUp(MouseEvent e){
				createConstraints();
			}
		});
		plus.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false, 1, 1));
		Button minus = getWidgetFactory().createButton(composite, "Delete", SWT.PUSH);
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
				constraintTableViewer.setInput(context.eGet(feature));
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
			constraintTableViewer.setInput(context.eGet(feature));
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
	void applyTableInfo(TableViewer t,Image colImage,int[] sizes,String...columnNames){
		Table ta = t.getTable();
		int i = 0;
		for(String s:columnNames){
			TableColumn column = new TableColumn(ta, SWT.LEFT);
			if(i == 0){
				column.setImage(colImage);
			}
			column.setText(s);
			if(sizes != null && i < sizes.length){
				column.setWidth(sizes[i]);
			}else{
				column.setWidth(300);
			}
			i++;
		}
		ta.setHeaderVisible(true);
		ta.setLinesVisible(true);
	}
	private List<?> getAllElements(){
		return context.eContents();
	}
	public static int getMinHeight(){
		return 150;
	}
	public void setContext(Element theContext){
		context = theContext;
		if(theContext != null){
			constraintTableViewer.setInput(getConstraints());
			if(getConstraints().size() == 1){
				setSelectedConstraint(getConstraints().iterator().next());
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
		if(theConstraint != null){
			this.selectedConstraint=theConstraint;
			int indexForSelection = getIndex(theConstraint, constraintTableViewer);
			constraintTableViewer.getTable().setSelection(indexForSelection);
			String constraintName = theConstraint.getName();
			if(constraintName == null){
				constraintName = "";
			}
		}else{
		}
	}
	private int getIndex(Object object,TableViewer list){
		int i = 0;
		for(TableItem item:list.getTable().getItems()){
			if(item.getData().equals(object)){
				return i;
			}
			i++;
		}
		return 0;
	}
	public void setEditDomain(EditingDomain domain){
		currentEditDomain = domain;
	}
	public void refreshConstraint(Constraint a){
		constraintTableViewer.refresh(a);
		
	}
}
