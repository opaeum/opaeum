package org.opaeum.topcased.propertysections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.topcased.modeler.editor.MixedEditDomain;
import org.topcased.modeler.utils.LabelHelper;

public class ParametersTableComposite extends Composite{
	private boolean isRefreshing = false;
	private Element owner;
	private MixedEditDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer parametersTableViewer;
	private Table parametersTable;
	private String[] columnNames = new String[]{
			"Name","Type","Direction"
	};
	private Button addButton;
	private Button removeButton;
	private EStructuralFeature feature;
	public ParametersTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature  feature){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
		this.feature=feature;
	}
	public void setOwner(Element owner){
		this.owner=owner;
		refresh();
	}
	public void setMixedEditDomain(MixedEditDomain mixedEditDomain){
		this.mixedEditDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		parametersTable = widgetFactory.createTable(parent, SWT.BORDER);
		addButton = widgetFactory.createButton(parent, "Add", SWT.NONE);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 2;
		gridData.heightHint=60;
		parametersTable.setLayoutData(gridData);
		TableColumn directionColumn = new TableColumn(parametersTable, SWT.LEFT);
		directionColumn.setText(columnNames[0]);
		directionColumn.setWidth(75);
		TableColumn nameColumn = new TableColumn(parametersTable, SWT.LEFT);
		nameColumn.setText(columnNames[1]);
		nameColumn.setWidth(150);
		TableColumn typeColumn = new TableColumn(parametersTable, SWT.LEFT);
		typeColumn.setText(columnNames[2]);
		typeColumn.setWidth(150);
		parametersTable.setHeaderVisible(true);
		parametersTable.setLinesVisible(true);
		parametersTableViewer = new TableViewer(parametersTable);
		parametersTableViewer.setContentProvider(new ParameterContentProvider());
		parametersTableViewer.setLabelProvider(new ParameterLabelProvider());
		parametersTableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(!isRefreshing){
					if(parametersTable.getSelection().length > 0){
						updateSelectedParameter((Parameter) parametersTable.getSelection()[0].getData());
					}else{
						updateSelectedParameter(null);
					}
				}
			}
		});
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				AddCommand addCommand = (AddCommand) AddCommand.create(mixedEditDomain.getEMFEditingDomain(), owner, feature, getNewChild());
				mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(addCommand);
				refresh();
				parametersTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(getOwnedParameters().size() - 1)));
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object object = parametersTable.getSelection()[0].getData();
				mixedEditDomain.getEMFEditingDomain().getCommandStack().execute(RemoveCommand.create(mixedEditDomain.getEMFEditingDomain(), object));
				refresh();
				if(getOwnedParameters().size() > 0){
					parametersTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(0)));
				}else{
					parametersTableViewer.setSelection(null);
				}
			}
		});
	}
	protected void refresh(){
		isRefreshing = true;
		parametersTableViewer.setInput(getOwnedParameters());
		isRefreshing = false;
	}
	private EList<Parameter> getOwnedParameters(){
		return (EList<Parameter>) owner.eGet(feature);
	}
	protected Object getNewChild(){
		Parameter newParameter = UMLFactory.eINSTANCE.createParameter();
		LabelHelper.INSTANCE.initName(mixedEditDomain, owner, newParameter);
		return newParameter;
	}
	public void updateSelectedParameter(Parameter newParameter){
	}
	private class ParameterContentProvider implements IStructuredContentProvider{
		public Object[] getElements(Object inputElement){
			if(inputElement instanceof EList){
				return ((EList<?>) inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose(){
		}
		public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		}
	}
	class ParameterLabelProvider extends LabelProvider implements ITableLabelProvider{
		public Image getColumnImage(Object element,int columnIndex){
			return null;
		}
		public String getColumnText(Object element,int columnIndex){
			switch(columnIndex){
			case 0:
				return ((Parameter) element).getName();
			case 1:
				if(((Parameter) element).getType() != null){
					return ((Parameter) element).getType().getName();
				}
				return "";
			case 2:
				if(((Parameter) element).getDirection() != null){
					return ((Parameter) element).getDirection().getName();
				}
			default:
				return "";
			}
		}
	}
}
