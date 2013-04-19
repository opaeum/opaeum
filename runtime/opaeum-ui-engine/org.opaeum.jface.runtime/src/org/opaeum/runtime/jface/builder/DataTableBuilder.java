package org.opaeum.runtime.jface.builder;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.nebula.widgets.pagination.table.SortTableColumnSelectionListener;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.rap.metamodels.uim.UimActivator;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.runtime.jface.actions.OpenEditorAction;
import org.opaeum.runtime.jface.binding.BindingUtil;
import org.opaeum.runtime.jface.editingsupport.ActivatingEditingSupport;
import org.opaeum.runtime.jface.editingsupport.BuiltInLinkEditingSupport;
import org.opaeum.runtime.jface.editingsupport.CheckboxEditingSupport;
import org.opaeum.runtime.jface.editingsupport.DateTimeEditingSupport;
import org.opaeum.runtime.jface.editingsupport.DefaultColumnLabelProvider;
import org.opaeum.runtime.jface.editingsupport.DropdownEditingSupport;
import org.opaeum.runtime.jface.editingsupport.PopupSearchEditingSupport;
import org.opaeum.runtime.jface.editingsupport.TextEditingSupport;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.jface.entityeditor.SecurityUtil;
import org.opaeum.runtime.jface.widgets.SortablePageableCheckboxTableViewer;
import org.opaeum.runtime.jface.widgets.TableAndActionBarComposite;
import org.opaeum.runtime.jface.wizards.InvocationWizard;
import org.opaeum.runtime.rwt.Activator;
import org.opaeum.runtime.rwt.DialogUtil;
import org.opaeum.runtime.rwt.IOpaeumApplication;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.AbstractActionButton;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.BuiltInLinkKind;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.UimSwtUtil;

@SuppressWarnings("serial")
public final class DataTableBuilder{
	private OpaeumRapSession session;
	private IOpaeumApplication application;
	private Object objectBeingUpdated;
	private BindingUtil bindingUtil;
	private EntityEditorInputJface input;
	private SecurityUtil securityUtil;
	/**
	 * Constructor to be called from Editors, assumes the selectedObject is the objectBeingUpdated.
	 * 
	 * @param input
	 */
	public DataTableBuilder(EntityEditorInputJface input){
		this(input.getPersistentObject(), input.getPersistentObject(), input.getOpaeumSession());
		this.input = input;
	}
	/**
	 * Constructor to be called from Editors, assumes there is a selectedObject to evaluate security rules agains, which could be different
	 * from the objectBeingUpdated, which could be an operation handler. Typically called from invocation wizards etc.
	 * 
	 */
	public DataTableBuilder(IPersistentObject selectedObject,Object handler,OpaeumRapSession session){
		this.session = session;
		this.application = session.getApplication();
		this.objectBeingUpdated = handler;
		this.bindingUtil = new BindingUtil(application.getEnvironment().getMetaInfoMap(), application.getValidator());
		this.securityUtil = new SecurityUtil(selectedObject, session);
	}
	void buildDataTable(final Composite body,UimComponent comp){
		final UimDataTable uimTable = (UimDataTable) comp;
		Class<?> rowClass = bindingUtil.resolveLastTypedElement(objectBeingUpdated, uimTable.getBinding()).getBaseType();
		if(securityUtil.calculateVisibility(uimTable)){
			TableAndActionBarComposite table = new TableAndActionBarComposite(body, SWT.BORDER);
			final SortablePageableCheckboxTableViewer tableViewer = table.getViewer();
			tableViewer.setContentProvider(new ArrayContentProvider());
			tableViewer.init(bindingUtil.invoke(objectBeingUpdated, uimTable.getBinding()));
			List<UimComponent> children = uimTable.getChildren();
			ComponentTreeBuilder.setLayoutData(table, uimTable);
			table.getTable().setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
			final TableViewerColumn firstColumn = new TableViewerColumn(tableViewer, SWT.NONE, 0);
			final TableColumn column = firstColumn.getColumn();
			column.setText("");
			column.setWidth(25);
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
							addFieldColumn(rowClass,tableViewer, viewerColumn, (UimField) child);
						}else if(child instanceof BuiltInLink){
							BuiltInLink builtInLink = (BuiltInLink) child;
							addBuiltInLink(tableViewer, viewerColumn, builtInLink);
						}else if(child instanceof BuiltInActionButton){
							addBuiltInAction(tableViewer, viewerColumn, (BuiltInActionButton) child, uimTable);
						}else if(child instanceof InvocationButton){
							addInvocationButton(body, tableViewer, viewerColumn, (InvocationButton) child);
						}
						i++;
					}
				}
			}
			List<AbstractActionButton> actionsOnMultipleSelection = uimTable.getActionsOnMultipleSelection();
			for(AbstractActionButton uimAction:actionsOnMultipleSelection){
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
								JavaTypedElement typedElement = bindingUtil.resolveLastTypedElement(objectBeingUpdated, uimTable.getBinding());
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
									}else{
										bindingUtil.invokeAdder(objectBeingUpdated, ni, uimTable.getBinding());
									}
									tableViewer.addNew(ni);
									tableViewer.getTable().getParent().getParent().layout();
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
				}else if(uimAction instanceof InvocationButton){
					final InvocationButton ob = (InvocationButton) uimAction;
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
							InvocationWizard wizard = new InvocationWizard(sel, eventHandler, ob.getPopup(), input);
							WizardDialog dialog = new WizardDialog(body.getShell(), wizard);
							DialogUtil.open(dialog, null);
						}
						public void widgetDefaultSelected(SelectionEvent e){
						}
					});
				}
			}
			tableViewer.refreshPage();
		}
	}
	private void addInvocationButton(final Composite body,final CheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,
			final InvocationButton btn){
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
				InvocationWizard wizard = new InvocationWizard((IPersistentObject) element, eventHandler, btn.getPopup(), input);
				WizardDialog dialog = new WizardDialog(body.getShell(), wizard);
				DialogUtil.open(dialog, null);
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
	private void addFieldColumn(Class<?> rowClass,final CheckboxTableViewer tableViewer,final TableViewerColumn viewerColumn,
			final UimField uimField){
		boolean editable = securityUtil.calculateEditability(uimField)
				&& !bindingUtil.resolveLastTypedElement(rowClass, uimField.getBinding()).isReadOnly();
		final BindingUtil bindingUtil = this.bindingUtil;
		final EntityEditorInputJface input = this.input;
		switch(uimField.getControlKind() == null ? ControlKind.TEXT : uimField.getControlKind()){
		case TEXT:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(rowClass, uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new TextEditingSupport(rowClass,tableViewer, input, bindingUtil, uimField));
			}
			break;
		case LINK:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(rowClass,uimField, "<span style=\"cursor:pointer\">", "</span>", bindingUtil));
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
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(rowClass,uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new DateTimeEditingSupport(rowClass,tableViewer, uimField, bindingUtil, input));
			}
			break;
		case DROPDOWN:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(rowClass,uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new DropdownEditingSupport(rowClass,tableViewer, uimField, bindingUtil, input));
			}
			break;
		case POPUP_SEARCH:
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(rowClass,uimField, "", "", bindingUtil));
			if(editable){
				viewerColumn.setEditingSupport(new PopupSearchEditingSupport(rowClass,tableViewer, input, bindingUtil, uimField));
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
			viewerColumn.setLabelProvider(new DefaultColumnLabelProvider(rowClass, uimField, "", "", bindingUtil));
		}
		viewerColumn.getColumn().addSelectionListener(new SortTableColumnSelectionListener(bindingUtil.getExpression(rowClass, uimField.getBinding())));
	}
}