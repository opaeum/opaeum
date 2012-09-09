package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.NamedElementNameEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.ParameterDirectionEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.TypedElementTypeEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.UmlElementImageProvider;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;

public class ParametersTableComposite extends Composite{
	private boolean isRefreshing = false;
	private Element owner;
	private EditingDomain editingDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer parametersTableViewer;
	private Button addButton;
	private Button removeButton;
	private Button moveUpButton;
	private Button moveDownButton;
	private EStructuralFeature feature;
	private List<EditingDomainEditingSupport> viewerColumns = new ArrayList<EditingDomainEditingSupport>();
	RecursiveAdapter adaptor = new RecursiveAdapter(){
		@Override
		public void notifyChanged(Notification notification){
			super.notifyChanged(notification);
			if(notification.getNotifier() instanceof Parameter){
				switch(notification.getFeatureID(Parameter.class)){
				case UMLPackage.PARAMETER__NAME:
				case UMLPackage.PARAMETER__DIRECTION:
				case UMLPackage.PARAMETER__IS_EXCEPTION:
				case UMLPackage.PARAMETER__TYPE:
					if(!parametersTable.isDisposed()){
						//Should not, but does happen
						parametersTableViewer.refresh(notification.getNotifier());
					}else{
						((Parameter) notification.getNotifier()).eAdapters().remove(adaptor);
					}
				}
			}
		}
	};
	private Table parametersTable;
	public ParametersTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
		this.feature = feature;
	}
	public void setOwner(Element owner){
		removeAdaptor();
		this.owner = owner;
		addAdaptor();
		refresh();
	}
	private void addAdaptor(){
		if(this.owner != null){
			EList<Parameter> op = getOwnedParameters();
			for(Parameter parameter:op){
				adaptor.subscribeTo(parameter, 1);
			}
		}
	}
	private void removeAdaptor(){
		if(this.owner != null){
			adaptor.unsubscribe();
		}
	}
	public void setEditingDomain(EditingDomain mixedEditDomain){
		this.editingDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		parametersTableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER){
			protected void hookEditingSupport(Control control){
				super.hookEditingSupport(control);
				// Needed for backwards comp with AbstractTreeViewer and TableTreeViewer
				// who are not hooked this way others may already overwrite and provide
				// their
				// own impl
				if(getColumnViewerEditor() != null){
					control.addMouseListener(new MouseAdapter(){
						public void mouseDown(MouseEvent e){
						}
						public void mouseDoubleClick(MouseEvent e){
							ViewerCell cell = getCell(new Point(e.x, e.y));
							if(cell != null){
								triggerEditorActivationEvent(new ColumnViewerEditorActivationEvent(cell));
							}
						}
					});
				}
			}
		};
		parametersTable = parametersTableViewer.getTable();
		addButton = widgetFactory.createButton(parent, "Add", SWT.NONE);
		addButton.setImage(ImageManager.IMG_ADD);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		moveUpButton = widgetFactory.createButton(parent, "Move Up", SWT.NONE);
		moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		moveUpButton.setImage(ImageManager.IMG_UP);
		moveDownButton = widgetFactory.createButton(parent, "Move Down", SWT.NONE);
		moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		moveDownButton.setImage(ImageManager.IMG_DOWN);
		removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton.setImage(ImageManager.IMG_DELETE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 4;
		gridData.heightHint = 60;
		parametersTable.setLayoutData(gridData);
		parametersTable.setHeaderVisible(true);
		parametersTable.setLinesVisible(true);
		parametersTableViewer.setContentProvider(new ArrayContentProvider());
		// parametersTableViewer.setLabelProvider(new ParameterLabelProvider());
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
		createColumns();
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				AddCommand addCommand = (AddCommand) AddCommand.create(editingDomain, owner, feature, getNewChild());
				editingDomain.getCommandStack().execute(addCommand);
				refresh();
				Parameter newParam = getOwnedParameters().get(getOwnedParameters().size() - 1);
				newParam.eAdapters().add(adaptor);
				parametersTableViewer.setSelection(new StructuredSelection(newParam));
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object object = parametersTable.getSelection()[0].getData();
				editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, object));
				refresh();
				if(getOwnedParameters().size() > 0){
					parametersTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(0)));
				}else{
					parametersTableViewer.setSelection(null);
				}
			}
		});
		moveUpButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Parameter object = (Parameter) parametersTable.getSelection()[0].getData();
				if(getOwnedParameters().size() > parametersTable.getSelectionIndex() - 1){
					editingDomain.getCommandStack().execute(
							MoveCommand.create(editingDomain, owner, feature, object, parametersTable.getSelectionIndex() - 1));
					refresh();
					parametersTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(parametersTable.getSelectionIndex())));
				}
			}
		});
		moveDownButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Parameter object = (Parameter) parametersTable.getSelection()[0].getData();
				if(getOwnedParameters().size() > parametersTable.getSelectionIndex() + 1){
					Command cmd = MoveCommand.create(editingDomain, owner, feature, object, parametersTable.getSelectionIndex() + 1);
					editingDomain.getCommandStack().execute(cmd);
					refresh();
					parametersTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(parametersTable.getSelectionIndex())));
				}
			}
		});
	}
	// This will create the columns for the table
	private void createColumns(){
		TableViewerColumn col = createTableViewerColumn("", 20,0);
		col.setLabelProvider(new UmlElementImageProvider());
		TableViewerColumn name = createTableViewerColumn("Name", 200,1);
		NamedElementNameEditingSupport e = new NamedElementNameEditingSupport(parametersTableViewer);
		name.setLabelProvider(e.getLabelProvider());
		this.viewerColumns.add(e);
		name.setEditingSupport(e);
		TableViewerColumn direction = createTableViewerColumn("Direction", 100, 2);
		ParameterDirectionEditingSupport de = new ParameterDirectionEditingSupport(parametersTableViewer);
		direction.setLabelProvider(de.getLabelProvider());
		this.viewerColumns.add(de);
		direction.setEditingSupport(de);
		TableViewerColumn type = createTableViewerColumn("Type", 100, 3);
		TypedElementTypeEditingSupport te = new TypedElementTypeEditingSupport(parametersTableViewer, widgetFactory);
		type.setLabelProvider(te.getLabelProvider());
		this.viewerColumns.add(te);
		type.setEditingSupport(te);
	}
	private TableViewerColumn createTableViewerColumn(String title,int bound,final int colNumber){
		final TableViewerColumn viewerColumn = new TableViewerColumn(parametersTableViewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	protected void refresh(){
		isRefreshing = true;
		if(!parametersTable.isDisposed()){
			EList<Parameter> ownedParameters = getOwnedParameters();
			parametersTableViewer.setInput(ownedParameters.toArray());
			for(EditingDomainEditingSupport vc:viewerColumns){
				vc.setEditingDomain(editingDomain);
			}
		}
		isRefreshing = false;
	}
	private EList<Parameter> getOwnedParameters(){
		return (EList<Parameter>) owner.eGet(feature);
	}
	@Override
	public void dispose(){
		super.dispose();
		removeAdaptor();
	}
	protected Object getNewChild(){
		Parameter newParameter = UMLFactory.eINSTANCE.createParameter();
		newParameter.setName("param" + getOwnedParameters().size());
		return newParameter;
	}
	public void updateSelectedParameter(Parameter newParameter){
	}
}
