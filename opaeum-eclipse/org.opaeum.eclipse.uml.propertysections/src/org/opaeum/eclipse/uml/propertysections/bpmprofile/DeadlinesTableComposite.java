package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TimeEventIsRelativeEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TimeEventWhenExprEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TimeEventWhenLabelProvider;
import org.opaeum.eclipse.uml.editingsupport.UmlElementImageProvider;
import org.opaeum.eclipse.uml.editingsupport.bpmprofile.DeadlineKindEditingSupport;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class DeadlinesTableComposite extends Composite{
	private Set<String> features = new HashSet<String>();
	{
		features.add("exp");
		features.add("body");
		features.add("isRelative");
		features.add("name");
		features.add(TagNames.TIME_UNIT);
		features.add(TagNames.DEADLINE_KIND);
	}
	private Stereotype deadlinesStereotype;
	private boolean isRefreshing = false;
	protected NamedElement element;
	private EditingDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer deadlinesTableViewer;
	private Table deadlinesTable;
	private Button addRelativeButton;
	private Button addAbsoluteButton;
	private Button removeButton;
	private Stereotype stereotype;
	private Collection<EditingDomainEditingSupport> columnViewers = new HashSet<EditingDomainEditingSupport>();
	RecursiveAdapter adapter = new RecursiveAdapter(){
		@Override
		public void safeNotifyChanged(Notification notification){
			if(notification.getFeature() instanceof EStructuralFeature && notification.getNotifier() instanceof EObject){
				EStructuralFeature f = (EStructuralFeature) notification.getFeature();
				if(features.contains(f.getName())){
					if(deadlinesTable.isDisposed()){
						// Should not, but does happen
						adapter.unsubscribe();
					}else{
						Control focusControl = Display.getCurrent().getFocusControl();
						if(notification.getFeature().equals(UMLPackage.eINSTANCE.getOpaqueExpression_Body())
								&& focusControl instanceof StyledText && focusControl.getParent().getParent()==deadlinesTable){
						}else{
							deadlinesTableViewer
									.refresh(EmfElementFinder.findNearestElementOfType(TimeEvent.class, (EObject) notification.getNotifier()));
						}
					}
				}
			}
		}
	};
	public DeadlinesTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
	}
	public void setOwningElement(NamedElement element,Stereotype s,Stereotype taskStereotype){
		removeAdaptor();
		this.element = element;
		this.deadlinesStereotype = s;
		this.stereotype = taskStereotype;
		addAdaptor();
		refresh();
	}
	private void addAdaptor(){
		if(this.element != null){
			List<TimeEvent> op = getDeadlines();
			for(TimeEvent te:op){
				adapter.subscribeTo(te, 3);
			}
		}
	}
	private void removeAdaptor(){
		adapter.unsubscribe();
	}
	public void setMixedEditDomain(EditingDomain mixedEditDomain){
		this.mixedEditDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		deadlinesTable = widgetFactory.createTable(parent, SWT.BORDER);
		addRelativeButton = widgetFactory.createButton(parent, "Add Relative", SWT.NONE);
		addRelativeButton.setImage(ImageManager.IMG_ADD);
		addRelativeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		addAbsoluteButton = widgetFactory.createButton(parent, "Add Absolute", SWT.NONE);
		addAbsoluteButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		addAbsoluteButton.setImage(ImageManager.IMG_ADD);
		removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton.setImage(ImageManager.IMG_DELETE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 3;
		deadlinesTable.setLayoutData(gridData);
		deadlinesTable.setHeaderVisible(true);
		deadlinesTable.setLinesVisible(true);
		deadlinesTableViewer = new TableViewer(deadlinesTable);
		deadlinesTableViewer.setContentProvider(new ArrayContentProvider());
		deadlinesTableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(!isRefreshing){
					if(deadlinesTable.getSelection().length > 0){
						updateSelectedDeadlines((TimeEvent) deadlinesTable.getSelection()[0].getData());
					}else{
						updateSelectedDeadlines(null);
					}
				}
			}
		});
		addAbsoluteButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				addDeadline(getNewChild(false));
			}
		});
		addRelativeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				addDeadline(getNewChild(true));
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object object = deadlinesTable.getSelection()[0].getData();
				EObject sa = element.getStereotypeApplication(stereotype);
				CompoundCommand cc = new CompoundCommand();
				cc.append(RemoveCommand.create(mixedEditDomain, sa, sa.eClass().getEStructuralFeature("deadlines"), object));
				EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
				if(ann != null){
					cc.append(RemoveCommand.create(mixedEditDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), object));
				}
				mixedEditDomain.getCommandStack().execute(cc);
				refresh();
				if(getDeadlines().size() > 0){
					deadlinesTableViewer.setSelection(new StructuredSelection(getDeadlines().get(0)));
				}else{
					deadlinesTableViewer.setSelection(null);
				}
			}
		});
		TableViewerColumn col = createTableViewerColumn("", 20, 0);
		col.setLabelProvider(new UmlElementImageProvider());
		columnViewers.clear();
		TableViewerColumn name = createTableViewerColumn("Name", 100, 1);
		NamedElementNameEditingSupport nees = new NamedElementNameEditingSupport(deadlinesTableViewer);
		name.setLabelProvider(nees.getLabelProvider());
		name.setEditingSupport(nees);
		columnViewers.add(nees);
		TableViewerColumn type = createTableViewerColumn("Deadline Type", 100, 2);
		DeadlineKindEditingSupport dkes = new DeadlineKindEditingSupport(deadlinesTableViewer);
		type.setLabelProvider(dkes.getLabelProvider());
		type.setEditingSupport(dkes);
		columnViewers.add(dkes);
		TableViewerColumn relative = createTableViewerColumn("Relative", 100, 3);
		TimeEventIsRelativeEditingSupport teires = new TimeEventIsRelativeEditingSupport(deadlinesTableViewer);
		relative.setEditingSupport(teires);
		relative.setLabelProvider(teires.getLabelProvider());
		columnViewers.add(teires);
		TableViewerColumn value = createTableViewerColumn("Value", 100, 4);
		TimeEventWhenExprEditingSupport tewees = new TimeEventWhenExprEditingSupport(deadlinesTableViewer, widgetFactory);
		value.setEditingSupport(tewees);
		columnViewers.add(tewees);
		value.setLabelProvider(new TimeEventWhenLabelProvider());
	}
	protected void refresh(){
		isRefreshing = true;
		deadlinesTableViewer.setInput(getDeadlines());
		deadlinesTableViewer.getTable().selectAll();
		for(EditingDomainEditingSupport vc:columnViewers){
			vc.setEditingDomain(mixedEditDomain);
		}
		isRefreshing = false;
	}
	@SuppressWarnings("unchecked")
	private List<TimeEvent> getDeadlines(){
		return (List<TimeEvent>) element.getValue(stereotype, TagNames.DEADLINES);
	}
	protected TimeEvent getNewChild(boolean b){
		TimeEvent newDeadline = UMLFactory.eINSTANCE.createTimeEvent();
		TimeExpression createWhen = newDeadline.createWhen("when", null);
		OpaqueExpression oa = (OpaqueExpression) createWhen.createExpr("expr", null, UMLPackage.eINSTANCE.getOpaqueExpression());
		oa.getBodies().add(OclBodyComposite.DEFAULT_TEXT);
		oa.getLanguages().add("ocl");
		newDeadline.setIsRelative(b);
		newDeadline.setName("NewDeadline");
		return newDeadline;
	}
	public void updateSelectedDeadlines(TimeEvent newDeadline){
	}
	private void addDeadline(TimeEvent newChild){
		EObject sa = element.getStereotypeApplication(stereotype);
		EAnnotation ann = StereotypesHelper.getNumlAnnotation(element);
		CompoundCommand cc = new CompoundCommand();
		if(ann == null){
			ann = EcoreFactory.eINSTANCE.createEAnnotation();
			ann.setSource(StereotypeNames.NUML_ANNOTATION);
			cc.append(AddCommand.create(mixedEditDomain, element, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann));
		}
		cc.append(AddCommand.create(mixedEditDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Contents(), newChild));
		cc.append(AddCommand.create(mixedEditDomain, sa, sa.eClass().getEStructuralFeature("deadlines"), newChild));
		cc.append(new ApplyStereotypeCommand(newChild, deadlinesStereotype));
		mixedEditDomain.getCommandStack().execute(cc);
		refresh();
		deadlinesTableViewer.setSelection(new StructuredSelection(getDeadlines().get(getDeadlines().size() - 1)));
	}
	private TableViewerColumn createTableViewerColumn(String title,int bound,final int colNumber){
		final TableViewerColumn viewerColumn = new TableViewerColumn(deadlinesTableViewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	@Override
	public void dispose(){
		super.dispose();
		this.adapter.unsubscribe();
	}
}
