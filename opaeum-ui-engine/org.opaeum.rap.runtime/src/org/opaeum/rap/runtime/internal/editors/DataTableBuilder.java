package org.opaeum.rap.runtime.internal.editors;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.validation.Validator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.omg.CORBA.DATA_CONVERSION;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.actions.OpenEditorAction;
import org.opaeum.rap.runtime.internal.wizards.OperationInvocationWizard;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.TableAndActionBarComposite;
import org.opaeum.uim.swt.UimActivator;
import org.opaeum.uim.swt.UimSwtUtil;

public final class DataTableBuilder{
	private OpaeumRapSession session;
	private IOpaeumApplication application;
	private Validator validator;
	private Object objectBeingUpdated;
	private IPersistentObject selectedObject;
	private BindingUtil bindingUtil;
	private EntityEditorInput input;
	public DataTableBuilder(EntityEditorInput input){
		this.input = input;
		this.session = input.getOpaeumSession();
		this.application = session.getApplication();
		this.validator = application.getValidator();
		this.objectBeingUpdated = input.getPersistentObject();
		this.selectedObject = input.getPersistentObject();
		this.bindingUtil = new BindingUtil(application.getEnvironment().getMetaInfoMap());
	}
	public DataTableBuilder(IPersistentObject selectedObject,Object handler,OpaeumRapSession session){
		this.session = session;
		this.application = session.getApplication();
		this.validator = application.getValidator();
		this.objectBeingUpdated = handler;
		this.selectedObject = selectedObject;
		this.bindingUtil = new BindingUtil(application.getEnvironment().getMetaInfoMap());
	}
	void buildDataTable(final Composite body,UimComponent comp){
		TableAndActionBarComposite table = new TableAndActionBarComposite(body, SWT.BORDER);
		final CheckboxTableViewer tableViewer = new CheckboxTableViewer(table.getTable());
		tableViewer.setContentProvider(new ArrayContentProvider());
		final UimDataTable uimTable = (UimDataTable) comp;
		EList<UimComponent> children = uimTable.getChildren();
		ComponentTreeBuilder.setLayoutData(table, uimTable);
		table.getTable().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
		final TableViewerColumn firstColumn = new TableViewerColumn(tableViewer, SWT.NONE, 0);
		final TableColumn column = firstColumn.getColumn();
		column.setText("");
		column.setWidth(20);
		column.setResizable(false);
		column.setMoveable(false);
		firstColumn.setLabelProvider(new CellLabelProvider(){
			@Override
			public void update(ViewerCell cell){
				cell.setText("");
			}
		});
		int i = 1;
		for(final UimComponent child:children){
			if(child instanceof Outlayable){
				Outlayable outlayable = (Outlayable) child;
				final TableViewerColumn viewerColumn = prepareColumn(tableViewer, i, outlayable);
				if(child instanceof UimField){
					addFieldColumn(tableViewer, viewerColumn, (UimField) child);
				}else if(child instanceof BuiltInLink){
					BuiltInLink builtInLink = (BuiltInLink) child;
					addBuiltInLink(tableViewer, viewerColumn, builtInLink);
				}else if(child instanceof BuiltInActionButton){
					addBuiltInAction(tableViewer, viewerColumn, (BuiltInActionButton) child, uimTable);
				}else if(child instanceof OperationButton){
					addOperationButton(body, tableViewer, viewerColumn, (OperationButton) child);
				}
				i++;
			}
		}
		EList<UimAction> actionsOnMultipleSelection = uimTable.getActionsOnMultipleSelection();
		for(UimAction uimAction:actionsOnMultipleSelection){
			if(uimAction instanceof BuiltInActionButton){
				BuiltInActionButton btn = (BuiltInActionButton) uimAction;
				Button button = new Button(table.getActionBar(), SWT.PUSH);
				button.setText(btn.getName());
				ComponentTreeBuilder.setLayoutData(button, btn);
				switch(btn.getKind()){
				case DELETE:
					button.addSelectionListener(new SelectionListener(){
						public void widgetSelected(SelectionEvent e){
							Object[] array = tableViewer.getCheckedElements();
							for(Object object:array){
								((CompositionNode) object).markDeleted();
							}
							tableViewer.setInput(bindingUtil.invoke(objectBeingUpdated, uimTable.getBinding()));
							if(input != null){
								input.setDirty(true);
							}
						}
						public void widgetDefaultSelected(SelectionEvent e){
						}
					});
					break;
				case ADD:
					break;
				}
			}else if(uimAction instanceof OperationButton){
				final OperationButton ob = (OperationButton) uimAction;
				Button button = new Button(table.getActionBar(), SWT.PUSH);
				button.setText(ob.getName());
				ComponentTreeBuilder.setLayoutData(button, ob);
				button.addSelectionListener(new SelectionListener(){
					public void widgetSelected(SelectionEvent e){
						IEventHandler eventHandler = bindingUtil.getEventHandler(ob.getUmlElementUid());
						Object[] checkedElements = tableViewer.getCheckedElements();
						java.util.List<IPersistentObject> sel = new ArrayList<IPersistentObject>();
						for(Object object:checkedElements){
							sel.add((IPersistentObject) object);
						}
						OperationInvocationWizard wizard = new OperationInvocationWizard(sel, eventHandler, ob.getPopup(), input);
						WizardDialog dialog = new WizardDialog(body.getShell(), wizard);
						dialog.open();
					}
					public void widgetDefaultSelected(SelectionEvent e){
					}
				});
			}
		}
		tableViewer.setInput(bindingUtil.invoke(objectBeingUpdated, uimTable.getBinding()));
	}
	private void addOperationButton(final Composite body,final CheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,
			final OperationButton btn){
		viewerColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public Image getImage(Object element){
				try{
					return new Image(Display.getCurrent(), UimActivator.INSTANCE.getBundle().getResource("/icons/operation.ico").openStream());
				}catch(IOException e){
					return null;
				}
			}
			public String getText(Object element){
				return "<span style=\"cursor:pointer\">" + btn.getName() + "</span>";
			}
		});
		viewerColumn.setEditingSupport(new ActivatingEditingSupport(tableViewer){
			@Override
			public void onClick(Object element){
				IEventHandler eventHandler = bindingUtil.getEventHandler(btn.getUmlElementUid());
				OperationInvocationWizard wizard = new OperationInvocationWizard((IPersistentObject) element, eventHandler, btn.getPopup(), input);
				WizardDialog dialog = new WizardDialog(body.getShell(), wizard);
				dialog.open();
			}
		});
	}
	private void addBuiltInLink(final CheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,final BuiltInLink builtInLink){
		final BuiltInLinkKind kind = builtInLink.getKind();
		viewerColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public Image getImage(Object element){
				return UimSwtUtil.getImageFor(kind);
			}
			public String getText(Object element){
				return "";// "<span style=\"cursor:pointer\">" + builtInLink.getName() + "</span>";
			}
		});
		viewerColumn.setEditingSupport(new BuiltInLinkEditingSupport(tableViewer, tableViewer, kind, session));
	}
	private void addBuiltInAction(final CheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,
			final BuiltInActionButton builtInAction,final UimDataTable uimTable){
		final ActionKind kind = builtInAction.getKind();
		viewerColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public Image getImage(Object element){
				return UimSwtUtil.getImageFor(kind);
			}
			public String getText(Object element){
				return "";// "<span style=\"cursor:pointer\">" + builtInAction.getName() + "</span>";
			}
		});
		viewerColumn.setEditingSupport(new ActivatingEditingSupport(tableViewer){
			@Override
			public void onClick(Object element){
				switch(kind){
				case DELETE:
					((CompositionNode) element).markDeleted();
					tableViewer.setInput(bindingUtil.invoke(objectBeingUpdated, uimTable.getBinding()));
					if(input != null){
						input.setDirty(true);
					}
					break;
				}
			}
		});
	}
	private TableViewerColumn prepareColumn(final CheckboxTableViewer tableViewer,int i,Outlayable child){
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE, i);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(((UserInteractionElement) child).getName());
		int width = child.getPreferredWidth() == null ? 130 : child.getPreferredWidth();
		if(i == 0){
			width += 30;
		}
		column.setWidth(width);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}
	private void addFieldColumn(final CheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,final UimField uimField){
		switch(uimField.getControlKind()){
		case TEXT:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			viewerColumn.setEditingSupport(new EditingSupport(tableViewer){
				@Override
				protected void setValue(Object element,Object value){
					Object target = bindingUtil.resolveTarget(element, uimField.getBinding());
					if(target != null){
						JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
						typedElement.invokeSetter(target, value);
						tableViewer.refresh(element);
						if(input != null){
							input.setDirty(true);
						}
					}
				}
				@Override
				protected Object getValue(Object element){
					return bindingUtil.invoke(element, uimField.getBinding());
				}
				@Override
				protected CellEditor getCellEditor(final Object element){
					return new TextCellEditor(tableViewer.getTable()){
						@Override
						protected void editOccured(ModifyEvent e){
							super.editOccured(e);
						}
					};
				}
				@Override
				protected boolean canEdit(Object element){
					return true;
				}
			});
			break;
		case LINK:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "<span style=\"cursor:pointer\">", "</span>", bindingUtil));
			viewerColumn.setEditingSupport(new ActivatingEditingSupport(tableViewer){
				@Override
				public void onClick(Object element){
					Object invoke = bindingUtil.invoke(element, uimField.getBinding());
					if(invoke != null){
						OpenEditorAction.openEditor(invoke, true, session);
					}
				}
			});
			break;
		case DATE_POPUP:
		case DATE_SCROLLER:
		case DATE_TIME_POPUP:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			viewerColumn.setEditingSupport(new EditingSupport(tableViewer){

				@Override
				protected CellEditor getCellEditor(Object element){
					int style = SWT.None;
					switch (uimField.getControlKind()){
					case DATE_POPUP:
						style=SWT.DATE|SWT.CALENDAR|SWT.DROP_DOWN ;
						break;
					case DATE_SCROLLER:
						style=SWT.DATE|SWT.CALENDAR;
						break;
					case DATE_TIME_POPUP:
						style=SWT.DATE|SWT.TIME|SWT.CALENDAR|SWT.DROP_DOWN ;
						break;
					}
					return new DateCellEditor(tableViewer.getTable(), style);
				}

				@Override
				protected boolean canEdit(Object element){
					return true;
				}

				@Override
				protected Object getValue(Object element){
					return bindingUtil.invoke(element, uimField.getBinding());
				}

				@Override
				protected void setValue(Object element,Object value){
					Object target = bindingUtil.resolveTarget(element, uimField.getBinding());
					if(target != null){
						JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
						typedElement.invokeSetter(target, value);
						tableViewer.refresh(element);
						if(input != null){
							input.setDirty(true);
						}
					}
				}});
			break;
		case DROPDOWN:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			viewerColumn.setEditingSupport(new EditingSupport(tableViewer){
				@Override
				protected void setValue(Object element,Object value){
					Object target = bindingUtil.resolveTarget(element, uimField.getBinding());
					if(target != null){
						JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
						typedElement.invokeSetter(target, value);
						tableViewer.refresh(element);
						if(input != null){
							input.setDirty(true);
						}
					}
				}
				@Override
				protected Object getValue(Object element){
					return bindingUtil.invoke(element, uimField.getBinding());
				}
				@Override
				protected CellEditor getCellEditor(final Object element){
					JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
					ComboBoxViewerCellEditor result = new ComboBoxViewerCellEditor(tableViewer.getTable());
					result.setLabelProvider(new LabelProvider(){
						@Override
						public String getText(Object value){
							String result = "";
							if(value instanceof IPersistentObject){
								result = ((IPersistentObject) value).getName();
							}else if(value instanceof Enum){
								result = ((Enum) value).name();
							}else if(value != null){
								result = value.toString();
							}
							return result;
						}
					});
					result.setContentProvider(new ArrayContentProvider());
					if(typedElement.getBaseType().isEnum()){
						result.setInput(typedElement.getBaseType().getEnumConstants());
					}else if(typedElement.getBaseType().isAnnotationPresent(Entity.class)){
						UimLookup lookup = (UimLookup) uimField.getControl();
						if(lookup.getLookupSource() == null){
							if(typedElement.isReadOnly()){
								result.setInput(bindingUtil.invoke(element, uimField.getBinding()));
							}else{
								result.setInput(typedElement.invokeLookupMethod((IPersistentObject) element));
							}
						}else{
							result.setInput(bindingUtil.invoke(element, lookup.getLookupSource()));
						}
					}
					return result;
				}
				@Override
				protected boolean canEdit(Object element){
					return true;
				}
			});
			break;
		default:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
		}
	}
}