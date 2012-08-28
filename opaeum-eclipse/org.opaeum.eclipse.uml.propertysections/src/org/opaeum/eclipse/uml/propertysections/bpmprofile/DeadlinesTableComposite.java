package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
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
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.commands.ApplyStereotypeCommand;
import org.opaeum.eclipse.uml.propertysections.ocl.OclBodyComposite;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;

public class DeadlinesTableComposite extends Composite{
	private Stereotype deadlinesStereotype;
	private boolean isRefreshing = false;
	protected NamedElement element;
	private EditingDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer deadlinesTableViewer;
	private Table deadlinesTable;
	private String[] columnNames = new String[]{"Name","Deadline Type","Relative","When"};
	private Button addRelativeButton;
	private Button addAbsoluteButton;
	private Button removeButton;
	private Stereotype stereotype;
	DeadlinesTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
	}
	public void setOwningElement(NamedElement element,Stereotype s,Stereotype taskStereotype){
		this.element = element;
		this.deadlinesStereotype = s;
		this.stereotype = taskStereotype;
		refresh();
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
		TableColumn nameColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		nameColumn.setText(columnNames[0]);
		nameColumn.setWidth(100);
		TableColumn typeColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		typeColumn.setText(columnNames[1]);
		typeColumn.setWidth(100);
		TableColumn relativeColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		relativeColumn.setText(columnNames[2]);
		relativeColumn.setWidth(60);
		TableColumn exprColumn = new TableColumn(deadlinesTable, SWT.LEFT);
		exprColumn.setText(columnNames[3]);
		exprColumn.setWidth(100);
		deadlinesTable.setHeaderVisible(true);
		deadlinesTable.setLinesVisible(true);
		deadlinesTableViewer = new TableViewer(deadlinesTable);
		deadlinesTableViewer.setContentProvider(new ArrayContentProvider());
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
	}
	protected void refresh(){
		isRefreshing = true;
		deadlinesTableViewer.setInput(getDeadlines());
		deadlinesTableViewer.getTable().selectAll();
		isRefreshing = false;
	}
	@SuppressWarnings("unchecked")
	private List<TimeEvent> getDeadlines(){
		if(stereotype ==null){
			System.out.println();
		}
		return (List<TimeEvent>) element.getValue(stereotype, "deadlines");
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
				return "" + timeEvent.isRelative();
			case 3:
				OpaqueExpression expr = (OpaqueExpression) timeEvent.getWhen().getExpr();
				return expr.getBodies().get(0);
			default:
				return "";
			}
		}
	}
}
