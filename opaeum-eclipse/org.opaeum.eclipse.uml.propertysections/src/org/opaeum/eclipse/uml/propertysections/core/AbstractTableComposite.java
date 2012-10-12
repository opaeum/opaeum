package org.opaeum.eclipse.uml.propertysections.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.UmlElementImageProvider;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;
import org.opaeum.name.NameConverter;

public abstract class AbstractTableComposite<T extends EObject> extends Composite{
	private boolean isRefreshing = false;
	protected abstract void createColumns();
	public T owner;
	private EditingDomain editingDomain;
	protected TabbedPropertySheetWidgetFactory widgetFactory;
	protected TableViewer tableViewer;
	private Button addButton;
	private Button removeButton;
	private Button moveUpButton;
	private Button moveDownButton;
	protected EStructuralFeature feature;
	private T selectedObject;
	protected List<EditingDomainEditingSupport> viewerColumns = new ArrayList<EditingDomainEditingSupport>();
	protected RecursiveAdapter adaptor = new RecursiveAdapter(){
		@SuppressWarnings("unchecked")
		@Override
		public void notifyChanged(Notification msg){
			if(table.isDisposed()){
				// Should not, but does happen
				((T) msg.getNotifier()).eAdapters().remove(adaptor);
			}else{
				super.notifyChanged(msg);
				if(msg.getNotifier() instanceof EObject && isInterestingFeature(msg.getFeature())){
					boolean inScope = false;
					EObject notifier = (EObject) msg.getNotifier();
					while(notifier != null){
						if(getObjectList().contains(notifier) || notifier == owner){
							inScope = true;
							notifier = null;
						}else{
							notifier = notifier.eContainer();
						}
						if(inScope){
							tableViewer.refresh(msg.getNotifier());
						}
					}
				}
			}
		}
	};
	private Table table;
	public AbstractTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory,EStructuralFeature feature){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
		this.feature = feature;
	}
	public void setOwner(T owner){
		removeAdaptor();
		this.owner = owner;
		addAdaptor();
		refresh();
	}
	private void addAdaptor(){
		if(this.owner != null){
			EList<T> op = getObjectList();
			for(T parameter:op){
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
	public T getSelectedObject(){
		return selectedObject;
	}
	@SuppressWarnings("unchecked")
	protected void createContents(Composite parent){
		tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER){
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
		table = tableViewer.getTable();
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
		table.setLayoutData(gridData);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer.setContentProvider(new ArrayContentProvider());
		// tableViewer.setLabelProvider(new ParameterLabelProvider());
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(!isRefreshing){
					if(table.getSelection().length > 0){
						selectedObject = (T) table.getSelection()[0].getData();
					}else{
						selectedObject = null;
					}
				}
			}
		});
		TableViewerColumn col = createTableViewerColumn("", 20, 0);
		col.setLabelProvider(new UmlElementImageProvider());
		createColumns();
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Command addCommand = AddCommand.create(editingDomain, owner, feature, getNewChild());
				editingDomain.getCommandStack().execute(addCommand);
				refresh();
				T newObject = getObjectList().get(getObjectList().size() - 1);
				newObject.eAdapters().add(adaptor);
				tableViewer.setSelection(new StructuredSelection(newObject));
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object object = table.getSelection()[0].getData();
				editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, object));
				refresh();
				if(getObjectList().size() > 0){
					tableViewer.setSelection(new StructuredSelection(getObjectList().get(0)));
				}else{
					tableViewer.setSelection(null);
				}
			}
		});
		moveUpButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				T object = (T) table.getSelection()[0].getData();
				if(getObjectList().size() > table.getSelectionIndex() - 1){
					editingDomain.getCommandStack().execute(MoveCommand.create(editingDomain, owner, feature, object, table.getSelectionIndex() - 1));
					refresh();
					tableViewer.setSelection(new StructuredSelection(getObjectList().get(table.getSelectionIndex())));
				}
			}
		});
		moveDownButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				T object = (T) table.getSelection()[0].getData();
				if(getObjectList().size() > table.getSelectionIndex() + 1){
					Command cmd = MoveCommand.create(editingDomain, owner, feature, object, table.getSelectionIndex() + 1);
					editingDomain.getCommandStack().execute(cmd);
					refresh();
					tableViewer.setSelection(new StructuredSelection(getObjectList().get(table.getSelectionIndex())));
				}
			}
		});
	}
	private TableViewerColumn createTableViewerColumn(String name,int width,int index){
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE, index);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(name);
		column.setWidth(width);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	protected TableViewerColumn createTableViewerColumn(EditingDomainEditingSupport sdf){
		this.viewerColumns.add(sdf);
		TableViewerColumn result = createTableViewerColumn(sdf.getName(), sdf.getWidth(), viewerColumns.size());
		result.setEditingSupport(sdf);
		result.setLabelProvider(sdf.getLabelProvider());
		return result;
	}
	protected void refresh(){
		isRefreshing = true;
		if(!table.isDisposed()){
			EList<T> ownedParameters = getObjectList();
			tableViewer.setInput(ownedParameters.toArray());
			for(EditingDomainEditingSupport vc:viewerColumns){
				vc.setEditingDomain(editingDomain);
			}
		}
		isRefreshing = false;
	}
	@Override
	public void dispose(){
		super.dispose();
		removeAdaptor();
	}
	protected boolean isInterestingFeature(Object feature){
		return feature instanceof EStructuralFeature;
	}
	@SuppressWarnings("unchecked")
	protected Object getNewChild(){
		T newObject = (T) UMLFactory.eINSTANCE.create((EClass) this.feature.getEType());
		EStructuralFeature nf = newObject.eClass().getEStructuralFeature("name");
		if(nf != null){
			newObject.eSet(nf, NameConverter.decapitalize(newObject.eClass().getName()) + getObjectList().size());
		}
		return newObject;
	}
	@SuppressWarnings("unchecked")
	protected EList<T> getObjectList(){
		return (EList<T>) owner.eGet(feature);
	}
	public void addSelectionChangedListener(ISelectionChangedListener listener){
		tableViewer.addSelectionChangedListener(listener);
	}
}