package org.opaeum.eclipse.uml.propertysections.standardprofile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.opaeum.eclipse.ImageManager;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.editingsupport.EditingDomainEditingSupport;
import org.opaeum.eclipse.uml.editingsupport.UmlElementImageProvider;
import org.opaeum.eclipse.uml.propertysections.RecursiveAdapter;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

public class UserTextTableComposite extends Composite{
	private boolean isRefreshing = false;
	private EModelElement owner;
	private EditingDomain editingDomain;
	private TabbedPropertySheetWidgetFactory widgetFactory;
	private TableViewer userTextTableViewer;
	private Button addButton;
	private Button removeButton;
	private List<EditingDomainEditingSupport> viewerColumns = new ArrayList<EditingDomainEditingSupport>();
	static Map<String,Locale> locales = new HashMap<String,Locale>();
	static{
		for(Locale locale:OpaeumConfig.getAvailableLocales()){
			locales.put(locale.toString(), locale);
		}
	}
	RecursiveAdapter adaptor = new RecursiveAdapter(){
		@Override
		public void safeNotifyChanged(Notification notification){
			if(notification.getNotifier() instanceof EStringToStringMapEntryImpl){
				switch(notification.getFeatureID(EAnnotation.class)){
				case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
					if(!userTextTable.isDisposed()){
						// Should not, but does happen
						userTextTableViewer.refresh(notification.getNotifier());
					}else{
						((EAnnotation) notification.getNotifier()).eAdapters().remove(adaptor);
					}
				}
			}
		}
	};
	private Table userTextTable;
	public UserTextTableComposite(Composite parent,int style,TabbedPropertySheetWidgetFactory widgetFactory){
		super(parent, style);
		this.widgetFactory = widgetFactory;
		setLayout(new GridLayout(2, false));
		widgetFactory.adapt(this);
		createContents(this);
	}
	public void setOwner(Element owner){
		removeAdaptor();
		this.owner = owner;
		addAdaptor();
		refresh();
	}
	private void addAdaptor(){
		if(this.owner != null){
			adaptor.subscribeTo(owner, 1);
		}
	}
	private void removeAdaptor(){
		adaptor.unsubscribe();
	}
	public void setEditingDomain(EditingDomain mixedEditDomain){
		this.editingDomain = mixedEditDomain;
	}
	protected void createContents(Composite parent){
		userTextTableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER){
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
		userTextTable = userTextTableViewer.getTable();
		addButton = widgetFactory.createButton(parent, "Add", SWT.NONE);
		addButton.setImage(ImageManager.IMG_ADD);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton = widgetFactory.createButton(parent, "Delete", SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		removeButton.setImage(ImageManager.IMG_DELETE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.verticalSpan = 4;
		gridData.heightHint = 60;
		userTextTable.setLayoutData(gridData);
		userTextTable.setHeaderVisible(true);
		userTextTable.setLinesVisible(true);
		userTextTableViewer.setContentProvider(new ArrayContentProvider());
		// userTextTableViewer.setLabelProvider(new ParameterLabelProvider());
		userTextTableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent event){
				if(!isRefreshing){
					if(userTextTable.getSelection().length > 0){
						// updateSelectedParameter((Parameter) userTextTable.getSelection()[0].getData());
					}else{
						// updateSelectedParameter(null);
					}
				}
			}
		});
		createColumns();
		addButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				addButton.getMenu().setVisible(true);
			}
		});
		removeButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				Object object = userTextTable.getSelection()[0].getData();
				EAnnotation ann = StereotypesHelper.getInternationalization(owner);
				if(ann != null){
					editingDomain.getCommandStack().execute(RemoveCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Details(), object));
					refresh();
				}
				// if(getOwnedParameters().size() > 0){
				// userTextTableViewer.setSelection(new StructuredSelection(getOwnedParameters().get(0)));
				// }else{
				// userTextTableViewer.setSelection(null);
				// }
			}
		});
	}
	// This will create the columns for the table
	private void createColumns(){
		TableViewerColumn col = createTableViewerColumn("", 20, 0);
		col.setLabelProvider(new UmlElementImageProvider());
		TableViewerColumn language = createTableViewerColumn("Language", 150, 1);
		language.setLabelProvider(new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText(locales.get(((Map.Entry<String,String>) cell.getElement()).getKey()).getDisplayName());
			}
		});
		TableViewerColumn text = createTableViewerColumn("Text", 200, 2);
		EditingDomainEditingSupport textEs = new EditingDomainEditingSupport(userTextTableViewer, "User Text", 300){
			private final TableViewer viewer = userTextTableViewer;
			@Override
			protected CellEditor getCellEditor(Object element){
				return new TextCellEditor(viewer.getTable());
			}
			@Override
			protected boolean canEdit(Object element){
				return true;
			}
			@Override
			protected Object getValue(Object element){
				return ((Map.Entry<String,String>) element).getValue();
			}
			@Override
			protected void setValue(Object element,Object value){
				EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl) element;
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, entry, EcorePackage.eINSTANCE.getEStringToStringMapEntry_Value(), value));
			}
			@Override
			public CellLabelProvider getLabelProvider(){
				return new CellLabelProvider(){
					@Override
					public void update(ViewerCell cell){
						cell.setText(((Map.Entry<String,String>) cell.getElement()).getValue());
					}
				};
			}
		};
		text.setLabelProvider(textEs.getLabelProvider());
		text.setEditingSupport(textEs);
		this.viewerColumns.add(textEs);
	}
	private TableViewerColumn createTableViewerColumn(String title,int bound,final int colNumber){
		final TableViewerColumn viewerColumn = new TableViewerColumn(userTextTableViewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	protected void refresh(){
		addButton.setMenu(new Menu(UserTextTableComposite.this.getShell()));
		for(final Locale l:OpaeumEclipseContext.getContextFor((Element) owner).getConfig().getSupportedLocales()){
			MenuItem menuItem = new MenuItem(addButton.getMenu(), SWT.NONE);
			menuItem.setText(l.getDisplayName());
			menuItem.setData(l);
			menuItem.addSelectionListener(new SelectionListener(){
				@Override
				public void widgetSelected(SelectionEvent e){
					String name = owner.eClass().getName();
					if(owner instanceof NamedElement){
						name = ((NamedElement) owner).getName();
					}
					EAnnotation ann = StereotypesHelper.getInternationalization(owner);
					if(ann == null){
						ann = EcoreFactory.eINSTANCE.createEAnnotation();
						ann.setSource(StereotypeNames.INTERNATIONALIZATION_URI);
						ann.getDetails().put(l.toString(), NameConverter.separateWords(NameConverter.capitalize(name)));
						AddCommand addCommand = (AddCommand) AddCommand.create(editingDomain, owner, EcorePackage.eINSTANCE.getEModelElement_EAnnotations(), ann);
						editingDomain.getCommandStack().execute(addCommand);
						adaptor.subscribeTo(ann, 2);
					}else{
						EStringToStringMapEntryImpl entry = (EStringToStringMapEntryImpl) EcoreFactory.eINSTANCE.create(EcorePackage.eINSTANCE.getEStringToStringMapEntry());
						entry.setKey(l.toString());
						entry.setValue(NameConverter.separateWords(NameConverter.capitalize(name)));
						AddCommand addCommand = (AddCommand) AddCommand.create(editingDomain, ann, EcorePackage.eINSTANCE.getEAnnotation_Details(), entry);
						editingDomain.getCommandStack().execute(addCommand);
						adaptor.subscribeTo(entry, 1);
					}
					refresh();
				}
				@Override
				public void widgetDefaultSelected(SelectionEvent e){
					// TODO Auto-generated method stub
				}
			});
		}
		isRefreshing = true;
		if(!userTextTable.isDisposed()){
			EAnnotation ann = StereotypesHelper.getInternationalization(owner);
			for(EditingDomainEditingSupport vc:viewerColumns){
				vc.setEditingDomain(editingDomain);
			}
			MenuItem[] items = addButton.getMenu().getItems();
			boolean enabled = false;
			for(MenuItem menuItem:items){
				menuItem.setEnabled(ann == null || !ann.getDetails().containsKey(((Locale) menuItem.getData()).toString()));
				enabled = enabled || menuItem.isEnabled();
			}
			if(ann == null){
				userTextTableViewer.setInput(null);
				addButton.setEnabled(true);
			}else{
				addButton.setEnabled(enabled);
				userTextTableViewer.setInput(ann.getDetails().entrySet());
			}
		}
		isRefreshing = false;
	}
	@Override
	public void dispose(){
		super.dispose();
		removeAdaptor();
	}
}
