package org.opaeum.rap.runtime.internal.editors;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.nebula.widgets.pagination.table.SortTableColumnSelectionListener;
import org.eclipse.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.editingsupport.ActivatingEditingSupport;
import org.opaeum.rap.runtime.editingsupport.BuiltInLinkEditingSupport;
import org.opaeum.rap.runtime.editingsupport.CheckboxEditingSupport;
import org.opaeum.rap.runtime.editingsupport.DateTimeEditingSupport;
import org.opaeum.rap.runtime.editingsupport.DefaultColumnLabelProvider;
import org.opaeum.rap.runtime.editingsupport.DropdownEditingSupport;
import org.opaeum.rap.runtime.editingsupport.PopupSearchEditingSupport;
import org.opaeum.rap.runtime.editingsupport.TextEditingSupport;
import org.opaeum.rap.runtime.internal.Activator;
import org.opaeum.rap.runtime.internal.actions.OpenEditorAction;
import org.opaeum.rap.runtime.internal.wizards.OperationInvocationWizard;
import org.opaeum.rap.runtime.widgets.SortablePageableCheckboxTableViewer;
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
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.UimActivator;
import org.opaeum.uim.swt.UimSwtUtil;

@SuppressWarnings("serial")
public final class DataTableBuilder{
	private OpaeumRapSession session;
	private IOpaeumApplication application;
	private Object objectBeingUpdated;
	private IPersistentObject selectedObject;
	private BindingUtil bindingUtil;
	private EntityEditorInput input;
	private SecurityUtil securityUtil;
	public DataTableBuilder(EntityEditorInput input){
		this.input = input;
		this.session = input.getOpaeumSession();
		this.application = session.getApplication();
		this.objectBeingUpdated = input.getPersistentObject();
		this.selectedObject = input.getPersistentObject();
		this.bindingUtil = new BindingUtil(application.getEnvironment().getMetaInfoMap(), application.getValidator());
		this.securityUtil = new SecurityUtil(selectedObject, session);
	}
	public DataTableBuilder(IPersistentObject selectedObject,Object handler,OpaeumRapSession session){
		this.session = session;
		this.application = session.getApplication();
		this.objectBeingUpdated = handler;
		this.selectedObject = selectedObject;
		this.bindingUtil = new BindingUtil(application.getEnvironment().getMetaInfoMap(), application.getValidator());
		this.securityUtil = new SecurityUtil(selectedObject, session);
	}
	void buildDataTable(final Composite body,UimComponent comp){
		final UimDataTable uimTable = (UimDataTable) comp;
		if(securityUtil.calculateVisibility(uimTable)){
			org.opaeum.rap.runtime.widgets.TableAndActionBarComposite table = new org.opaeum.rap.runtime.widgets.TableAndActionBarComposite(body,
					SWT.BORDER);
			final SortablePageableCheckboxTableViewer tableViewer = table.getViewer();
			tableViewer.setContentProvider(new ArrayContentProvider());
			tableViewer.init(bindingUtil.invoke(objectBeingUpdated, uimTable.getBinding()));
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
					if(securityUtil.calculateVisibility(child)){
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
								tableViewer.delete(array);
								if(input != null){
									input.setDirty(true);
								}
							}
							public void widgetDefaultSelected(SelectionEvent e){
							}
						});
						break;
					case ADD:
						button.addSelectionListener(new SelectionListener(){
							public void widgetSelected(SelectionEvent e){
								JavaTypedElement typedElement = bindingUtil.getTypedElement(uimTable.getBinding().getLastPropertyUuid());
								if(!Modifier.isAbstract(typedElement.getBaseType().getModifiers())){
									IPersistentObject ni;
									try{
										ni = (IPersistentObject) typedElement.getBaseType().newInstance();
									}catch(InstantiationException e1){
										throw new RuntimeException(e1);
									}catch(IllegalAccessException e1){
										throw new RuntimeException(e1);
									}
									if(objectBeingUpdated instanceof CompositionNode && ni instanceof CompositionNode){
										CompositionNode cn = (CompositionNode) ni;
										cn.init((CompositionNode) objectBeingUpdated);
										cn.addToOwningObject();
										tableViewer.addNew(cn);
									}else{
										bindingUtil.invokeAdder(objectBeingUpdated, ni, uimTable.getBinding());
									}
									if(input != null){
										input.setDirty(true);
									}
								}else{
									// TODO find implementations and select from a popup of sorts
								}
							}
							public void widgetDefaultSelected(SelectionEvent e){
							}
						});
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
			tableViewer.refreshPage();
		}
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
	private void addBuiltInAction(final SortablePageableCheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,
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
					tableViewer.delete(element);
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
		boolean editable = securityUtil.calculateEditability(uimField);
		final BindingUtil bindingUtil = this.bindingUtil;
		final EntityEditorInput input = this.input;
		switch(uimField.getControlKind()){
		case TEXT:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new TextEditingSupport(tableViewer, input, bindingUtil, uimField));
			}
			break;
		case LINK:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "<span style=\"cursor:pointer\">", "</span>", bindingUtil));
			viewerColumn.setEditingSupport(new ActivatingEditingSupport(tableViewer){
				@Override
				public void onClick(Object element){
					Object invoke = bindingUtil.invoke(element, uimField.getBinding());
					if(invoke != null){
						OpenEditorAction.openEntityEditor(invoke, true, session);
					}
				}
			});
			break;
		case DATE_POPUP:
		case DATE_SCROLLER:
		case DATE_TIME_POPUP:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new DateTimeEditingSupport(tableViewer, uimField, bindingUtil, input));
			}
			break;
		case DROPDOWN:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new DropdownEditingSupport(tableViewer, uimField, bindingUtil, input));
			}
			break;
		case POPUP_SEARCH:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new PopupSearchEditingSupport(tableViewer, input, bindingUtil, uimField));
			}
			break;
		case CHECK_BOX:
			viewerColumn.setLabelProvider(new ColumnLabelProvider(){
				public String getText(Object element){
					return null;
				}
				@Override
				public Image getImage(Object element){
					Boolean boolean1 = (Boolean) bindingUtil.invoke(element, uimField.getBinding());
					if(Boolean.TRUE.equals(boolean1)){
						return Activator.getDefault().getImage("checked.gif");
					}
					return Activator.getDefault().getImage("unchecked.gif");
				}
			});
			if(editable){
				viewerColumn.setEditingSupport(new CheckboxEditingSupport(tableViewer, uimField, input, bindingUtil));
			}
			break;
		default:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(uimField, "", "", bindingUtil));
		}
		viewerColumn.getColumn().addSelectionListener(new SortTableColumnSelectionListener(bindingUtil.getExpression(uimField.getBinding())));
	}
}