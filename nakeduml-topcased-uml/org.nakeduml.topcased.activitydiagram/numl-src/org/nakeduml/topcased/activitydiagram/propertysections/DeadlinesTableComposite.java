package org.nakeduml.topcased.activitydiagram.propertysections;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.nakeduml.topcased.commands.AddAnnotationContentCommand;
import org.nakeduml.topcased.propertysections.ocl.OclBodyComposite;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.utils.LabelHelper;

public class DeadlinesTableComposite extends Composite{
	private Stereotype deadlinesStereotype;
	private boolean isRefreshing = false;
	protected Action action;
	private MixedEditDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer deadlinesTableViewer;
	private Table deadlinesTable;
	private String[] columnNames = new String[]{
			"Name","Deadline Type","Relative", "When"
	};
	private Button addRelativeButton;
	private Button addAbsoluteButton;
	private Button removeButton;
	private Stereotype taskStereotype;
	DeadlinesTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
	}
	public void setAction(Action action, Stereotype s, Stereotype taskStereotype){
		this.action = action;
		this.deadlinesStereotype=s;
		this.taskStereotype=taskStereotype;
		refresh();
	}
	public void setMixedEditDomain(MixedEditDomain mixedEditDomain){
		this.mixedEditDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		deadlinesTable = widgetFactory.createTable(parent, SWT.BORDER);
		addRelativeButton = widgetFactory.createButton(parent, "Add Relative", SWT.NONE);
		addRelativeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		addAbsoluteButton = widgetFactory.createButton(parent, "Add Absolute", SWT.NONE);
		addAbsoluteButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 3;
		deadlinesTable.setLayoutData(gridData);
		TableColumn nameColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		nameColumn.setText(columnNames[0]);
		nameColumn.setWidth(100);
		TableColumn typeColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		typeColumn.setText(columnNames[1]);
		typeColumn.setWidth(100);
		TableColumn relativeColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		relativeColumn .setText(columnNames[2]);
		relativeColumn.setWidth(60);
		TableColumn exprColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		exprColumn.setText(columnNames[3]);
		exprColumn.setWidth(100);
		deadlinesTable.setHeaderVisible(true);
		deadlinesTable.setLinesVisible(true);
		deadlinesTableViewer = new TableViewer(deadlinesTable);
		deadlinesTableViewer.setContentProvider(new ParameterContentProvider());
		deadlinesTableViewer.setLabelProvider(new DeadlineLabelProvider());
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
				EObject sa = action.getStereotypeApplication(taskStereotype);
				Command removeFromSteretoype = RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), sa, sa.eClass().getEStructuralFeature("deadlines"),object);
				mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(removeFromSteretoype);
				mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), object));
				refresh();
				if(getDeadlines().size() > 0){
					deadlinesTableViewer.setSelection(new StructuredSelection(getDeadlines().get(0)));
				}else{
					deadlinesTableViewer.setSelection(null);
				}
			}
		});
	}
	protected void refresh(){
		isRefreshing = true;
		deadlinesTableViewer.setInput(getDeadlines());
		deadlinesTableViewer.getTable().selectAll();
		isRefreshing = false;
	}
	private List<TimeEvent> getDeadlines(){
		List<TimeEvent> result = new ArrayList<TimeEvent>();
		for(EObject eObject:StereotypesHelper.getNumlAnnotation(action).getContents()){
			if(eObject instanceof TimeEvent){
				result.add((TimeEvent) eObject);
			}
		}
		return result;
	}
	protected TimeEvent getNewChild(boolean b){
		TimeEvent newDeadline = UMLFactory.eINSTANCE.createTimeEvent();
		TimeExpression createWhen = newDeadline.createWhen("when", null);
		OpaqueExpression oa = (OpaqueExpression) createWhen.createExpr("expr", null, UMLPackage.eINSTANCE.getOpaqueExpression());
		oa.getBodies().add(OclBodyComposite.DEFAULT_TEXT);
		oa.getLanguages().add("ocl");
		newDeadline.setIsRelative(b);
		StereotypesHelper.getNumlAnnotation(newDeadline).getDetails().put(StereotypeNames.DEADLINE, "");
		LabelHelper.INSTANCE.initName(mixedEditDomain, action, newDeadline);
		return newDeadline;
	}
	public void updateSelectedDeadlines(TimeEvent newDeadline){
	}
	private void addDeadline(TimeEvent newChild){
		Command addCommand = AddCommand.create(mixedEditDomain.getEMFEditingDomain(), StereotypesHelper.getNumlAnnotation(action), EcorePackage.eINSTANCE.getEAnnotation_Contents(), newChild);
		EObject sa = action.getStereotypeApplication(taskStereotype);
		Command addToSteretoype = AddCommand.create(mixedEditDomain.getEMFEditingDomain(), sa, sa.eClass().getEStructuralFeature("deadlines"),newChild);
		mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(addCommand);
		mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(addToSteretoype);
		refresh();
		deadlinesTableViewer.setSelection(new StructuredSelection(getDeadlines().get(getDeadlines().size() - 1)));
	}
	class ParameterContentProvider implements IStructuredContentProvider{
		public Object[] getElements(Object inputElement){
			if(inputElement instanceof List){
				return ((List<?>) inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose(){
		}
		public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		}
	}
	class DeadlineLabelProvider extends LabelProvider implements ITableLabelProvider{
		public Image getColumnImage(Object element,int columnIndex){
			return null;
		}
		public String getColumnText(Object element,int columnIndex){
			TimeEvent timeEvent = (TimeEvent) element;
			switch(columnIndex){
			case 0:
				return timeEvent.getName();
			case 1:
				Object value = timeEvent.getValue(deadlinesStereotype, "kind");
				if(value instanceof EnumerationLiteral){
					return ((EnumerationLiteral) value).getName();
				}
				return "";
			case 2:
				return ""+timeEvent.isRelative();
			case 3:
				OpaqueExpression expr = (OpaqueExpression) timeEvent.getWhen().getExpr();
				return expr.getBodies().get(0);
			default:
				return "";
			}
		}
	}
}
