package org.opaeum.topcased.propertysections.property;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
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
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;

public class PropertiesTableComposite extends Composite{
	private boolean isRefreshing = false;
	private Element owner;
	private EditingDomain mixedEditDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer propertiesTableViewer;
	private Table propertiesTable;
	private String[] columnNames = new String[]{"Name","Type"};
	private Button addButton;
	private Button removeButton;
	private EStructuralFeature feature;
	public PropertiesTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
		this.feature = feature;
	}
	public void setOwner(Element owner){
		this.owner = owner;
		refresh();
	}
	public void setEditingDomain(EditingDomain mixedEditDomain){
		this.mixedEditDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		propertiesTable = widgetFactory.createTable(parent, SWT.BORDER);
		addButton = widgetFactory.createButton(parent, "Add", SWT.NONE);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 2;
		gridData.heightHint = 60;
		propertiesTable.setLayoutData(gridData);
		TableColumn nameColumn = new TableColumn(propertiesTable, SWT.LEFT);
		nameColumn.setText(columnNames[0]);
		nameColumn.setWidth(75);
		TableColumn typeColumn = new TableColumn(propertiesTable, SWT.LEFT);
		typeColumn.setText(columnNames[1]);
		typeColumn.setWidth(150);
		propertiesTable.setHeaderVisible(true);
		propertiesTable.setLinesVisible(true);
		propertiesTableViewer = new TableViewer(propertiesTable);
		propertiesTableViewer.setContentProvider(new ParameterContentProvider());
		propertiesTableViewer.setLabelProvider(new ParameterLabelProvider());
		propertiesTableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(!isRefreshing){
					if(propertiesTable.getSelection().length > 0){
						updateSelectedQualifier((Property) propertiesTable.getSelection()[0].getData());
					}else{
						updateSelectedQualifier(null);
					}
				}
			}
		});
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				AddCommand addCommand = (AddCommand) AddCommand.create(mixedEditDomain, owner, feature, getNewChild());
				mixedEditDomain.getCommandStack().execute(addCommand);
				refresh();
				propertiesTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(getOwnedParameters().size() - 1)));
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object object = propertiesTable.getSelection()[0].getData();
				mixedEditDomain.getCommandStack()
						.execute(RemoveCommand.create(mixedEditDomain, object));
				refresh();
				if(getOwnedParameters().size() > 0){
					propertiesTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(0)));
				}else{
					propertiesTableViewer.setSelection(null);
				}
			}
		});
	}
	protected void refresh(){
		isRefreshing = true;
		propertiesTableViewer.setInput(getOwnedParameters());
		isRefreshing = false;
	}
	@SuppressWarnings("unchecked")
	private EList<Property> getOwnedParameters(){
		return (EList<Property>) owner.eGet(feature);
	}
	protected Object getNewChild(){
		Property newParameter = UMLFactory.eINSTANCE.createProperty();
		newParameter.setName("newProperty");
		return newParameter;
	}
	public void updateSelectedQualifier(Property newParameter){
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
				return ((Property) element).getName();
			case 1:
				if(((Property) element).getType() != null){
					return ((Property) element).getType().getName();
				}
				return "";
			default:
				return "";
			}
		}
	}
}
