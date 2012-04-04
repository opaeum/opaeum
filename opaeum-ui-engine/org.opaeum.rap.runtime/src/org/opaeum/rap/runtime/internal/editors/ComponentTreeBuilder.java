package org.opaeum.rap.runtime.internal.editors;

import java.util.Arrays;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.validation.Validator;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.AbstractListViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TableColumn;
import org.opaeum.rap.runtime.IOpaeumApplication;
import org.opaeum.rap.runtime.OpaeumRapSession;
import org.opaeum.rap.runtime.internal.actions.OpenEditorAction;
import org.opaeum.rap.runtime.internal.binding.GenericFromStringConverter;
import org.opaeum.rap.runtime.internal.binding.GenericToStringConverter;
import org.opaeum.rap.runtime.internal.binding.GenericValidator;
import org.opaeum.rap.runtime.internal.wizards.OperationInvocationWizard;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.runtime.strategy.FromStringConverter;
import org.opaeum.runtime.strategy.ToStringConverter;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uim.swt.LinkComposite;
import org.opaeum.uim.swt.NumberScroller;
import org.opaeum.uim.swt.TableAndActionBarComposite;
import org.opaeum.uim.swt.UimFieldComposite;
import org.opaeum.uim.swt.UimSwtUtil;

public class ComponentTreeBuilder{
	IPersistentObject selectedObject;
	Object objectBeingUpdated;
	JavaMetaInfoMap javaMetaInfo;
	private Validator validator;
	IOpaeumApplication application;
	private OpaeumRapSession session;
	private EntityEditorInput input;
	// Constructor for operation invocations
	public ComponentTreeBuilder(IPersistentObject selectedObject,Object handler,OpaeumRapSession session){
		this.session = session;
		this.application = session.getApplication();
		this.validator = application.getValidator();
		this.objectBeingUpdated = handler;
		this.selectedObject = selectedObject;
		this.javaMetaInfo = application.getEnvironment().getMetaInfoMap();
	}
	// Constructor for class editors
	public ComponentTreeBuilder(IPersistentObject selectedObject,EntityEditorInput input){
		this(selectedObject, selectedObject, input.getOpaeumSession());
		this.input = input;
	}
	public void addComponent(final Composite body,UimComponent comp,DataBindingContext bc){
		if(comp instanceof GridPanel){
			GridPanelComposite gpc = new GridPanelComposite(body, SWT.NONE);
			Integer numberOfColumns = ((GridPanel) comp).getNumberOfColumns();
			gpc.getContentPane().setLayout(new GridLayout(numberOfColumns, false));
			EList<UimComponent> children = ((GridPanel) comp).getChildren();
			for(UimComponent child:children){
				addComponent(gpc.getContentPane(), child, bc);
			}
			gpc.layout();
		}else if(comp instanceof UimField){
			if(comp.eContainer() instanceof UimDataTable){
			}else{
				addUimFieldComposite(body, comp, bc);
			}
		}else if(comp instanceof UimDataTable){
			TableAndActionBarComposite table = new TableAndActionBarComposite(body, SWT.BORDER);
			TableViewer tableViewer = new TableViewer(table.getTable());
			tableViewer.setContentProvider(new ArrayContentProvider());
			UimDataTable uimTable = (UimDataTable) comp;
			EList<UimComponent> children = uimTable.getChildren();
			setLayoutData(table, uimTable);
			int i = 0;
			for(UimComponent child:children){
				if(child instanceof UimField){
					final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE, i++);
					final TableColumn column = viewerColumn.getColumn();
					column.setText(child.getName());
					final UimField uimField = (UimField) child;
					column.setWidth(uimField.getPreferredWidth() == null ? 130 : uimField.getPreferredWidth());
					column.setResizable(true);
					column.setMoveable(true);
					viewerColumn.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public String getText(Object element){
							Object value = invoke(element, uimField.getBinding());
							if(value instanceof IPersistentObject){
								return ((IPersistentObject) value).getName();
							}else if(value instanceof Enum){
								return ((Enum) value).name();
							}else if(value != null){
								return value.toString();
							}else{
								return "";
							}
						}
					});
				}
			}
			EList<UimAction> actionsOnMultipleSelection = uimTable.getActionsOnMultipleSelection();
			for(UimAction uimAction:actionsOnMultipleSelection){
				addComponent(table.getActionBar(), uimAction, bc);
			}
			tableViewer.setInput(invoke(objectBeingUpdated, uimTable.getBinding()));
		}else if(comp instanceof BuiltInActionButton){
			BuiltInActionButton btn = (BuiltInActionButton) comp;
			Button button = new Button(body, SWT.PUSH);
			button.setText(btn.getName());
			setLayoutData(button, btn);
			switch(btn.getKind()){
			case UPDATE:
				break;
			case DELETE:
				break;
			case EXECUTE_OPERATION:
				break;
			}
		}else if(comp instanceof OperationButton){
			final OperationButton ob = (OperationButton) comp;
			Button button = new Button(body, SWT.PUSH);
			button.setText(ob.getName());
			setLayoutData(button, ob);
			button.addSelectionListener(new SelectionListener(){
				public void widgetSelected(SelectionEvent e){
					if(ob.eContainer() instanceof UimDataTable){
						// TODO handle multiple selection etc.
					}else{
						IEventHandler eventHandler = javaMetaInfo.getEventHandler(ob.getUmlElementUid());
						OperationInvocationWizard wizard = new OperationInvocationWizard((IPersistentObject) objectBeingUpdated, eventHandler, ob
								.getPopup(), input);
						WizardDialog dialog = new WizardDialog(body.getShell(), wizard);
						dialog.open();
					}
				}
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
		}
	}
	private void addUimFieldComposite(Composite body,UimComponent comp,DataBindingContext bc){
		UimFieldComposite uimFieldComposite = new UimFieldComposite(body, SWT.NONE);
		UimField uimField = (UimField) comp;
		UimSwtUtil.populateControl(uimFieldComposite, uimField.getControlKind(), uimField.getOrientation());
		setLayoutData(uimFieldComposite, uimField);
		uimFieldComposite.setMinimumLabelWidth(uimField.getMinimumLabelWidth());
		uimFieldComposite.getLabel().setText(uimField.getName());
		UimSwtUtil.setOrientation(uimField.getOrientation(), uimFieldComposite, uimField.getMinimumLabelWidth());
		uimFieldComposite.layout();
		final JavaTypedElement typedElement = javaMetaInfo.getTypedElement(uimField.getBinding().getLastPropertyUuid());
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		IObservableValue observeValue = BeansObservables.observeValue(objectBeingUpdated, getExpression(uimField.getBinding()));
		targetToModel.setAfterConvertValidator(new GenericValidator(IntrospectionUtil.getOriginalClass(objectBeingUpdated), typedElement,
				this.validator));
		IObservableValue observeControl = null;
		switch(uimField.getControlKind()){
		case TEXT:
		case NUMBER_SCROLLER:
		case TEXT_AREA:
			observeControl = populateTextStrategies(uimFieldComposite, typedElement, targetToModel, modelToTarget);
			break;
		case CHECK_BOX:
			break;
		case DATE_POPUP:
		case DATE_SCROLLER:
		case DATE_TIME_POPUP:
			observeControl = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case LIST_BOX:
			observeControl = observeListbox(uimFieldComposite, uimField, typedElement);
			break;
		case DROPDOWN:
			observeControl = observeDropdown(uimFieldComposite, uimField, typedElement);
			break;
		case LABEL:
			observeControl = populateTextStrategies(uimFieldComposite, typedElement, targetToModel, modelToTarget);
			break;
		case LINK:
			LinkComposite link = (LinkComposite) uimFieldComposite.getControl();
			final IPersistentObject linked = (IPersistentObject) invoke(objectBeingUpdated, uimField.getBinding());
			Link firstLink = (Link) link.getChildren()[0];
			firstLink.setText(linked.getName());
			firstLink.addMouseListener(new MouseListener(){
				public void mouseUp(MouseEvent e){
					OpenEditorAction.openEditor(linked, true, session);
				}
				public void mouseDown(MouseEvent e){
				}
				public void mouseDoubleClick(MouseEvent e){
				}
			});
			break;
		case POPUP_SEARCH:
			observeControl = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case RADIO_BUTTON:
			observeControl = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case SELECTION_TABLE:
			break;
		case TOGGLE_BUTTON:
			observeControl = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case TREE_VIEW:
			observeControl = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		}
		if(observeControl != null){
			if(input != null){
				observeValue.addValueChangeListener(input);
			}
			Binding bindValue = bc.bindValue(observeControl, observeValue, targetToModel, modelToTarget);
			ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
		}
	}
	private ISWTObservableValue observeListbox(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement){
		AbstractListViewer cb = new ListViewer((List) uimFieldComposite.getControl());
		return observeListViewer(uimFieldComposite, uimField, typedElement, cb);
	}
	private ISWTObservableValue observeListViewer(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement,
			AbstractListViewer cb){
		ISWTObservableValue observeText;
		observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
		if(typedElement.getBaseType().isEnum()){
		}else if(typedElement.getBaseType().isAnnotationPresent(Entity.class)){
			cb.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element){
					return ((IPersistentObject) element).getName();
				}
			});
			cb.setContentProvider(new ArrayContentProvider());
			UimLookup lookup = (UimLookup) uimField.getControl();
			if(lookup.getLookupSource() == null){
				if(typedElement.isReadOnly()){
					cb.setInput(invoke(objectBeingUpdated, uimField.getBinding()));
				}else{
					cb.setInput(typedElement.invokeLookupMethod(selectedObject));
				}
			}else{
				cb.setInput(invoke(objectBeingUpdated, lookup.getLookupSource()));
			}
		}
		return observeText;
	}
	private IObservableValue observeDropdown(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement){
		ComboViewer cb = new ComboViewer((CCombo) uimFieldComposite.getControl());
		return observeListViewer(uimFieldComposite, uimField, typedElement, cb);
	}
	private ISWTObservableValue populateTextStrategies(UimFieldComposite uimFieldComposite,JavaTypedElement typedElement,
			UpdateValueStrategy targetToModel,UpdateValueStrategy modelToTarget){
		Control control = uimFieldComposite.getControl();
		if(control instanceof NumberScroller){
			control = ((NumberScroller) control).getChildren()[0];// TODO provide getter
		}
		ISWTObservableValue observeText = SWTObservables.observeText(control, SWT.Modify);
		if(typedElement.getStrategyFactory().hasStrategy(FromStringConverter.class)){
			targetToModel.setConverter(new GenericFromStringConverter(typedElement.getBaseType(), typedElement.getStrategyFactory().getStrategy(
					FromStringConverter.class)));
		}
		if(typedElement.getStrategyFactory().hasStrategy(ToStringConverter.class)){
			modelToTarget.setConverter(new GenericToStringConverter(typedElement.getBaseType(), typedElement.getStrategyFactory().getStrategy(
					ToStringConverter.class)));
		}
		return observeText;
	}
	private String getExpression(UimBinding b){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(b.getUmlElementUid());
		StringBuilder sb = new StringBuilder(typedElement.getName());
		appendExpression(sb, b.getNext());
		return sb.toString();
	}
	private Object invoke(Object target,UimBinding b){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(b.getUmlElementUid());
		Object value = typedElement.invokeGetter(target);
		if(b.getNext() != null){
			return invoke(value, b.getNext());
		}else{
			return value;
		}
	}
	private Object invoke(Object target,PropertyRef next){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(next.getUmlElementUid());
		Object value = typedElement.invokeGetter(target);
		if(next.getNext() != null){
			return invoke(value, next.getNext());
		}else{
			return value;
		}
	}
	private void appendExpression(StringBuilder sb,PropertyRef next){
		if(next != null){
			sb.append('.');
			sb.append(javaMetaInfo.getTypedElement(next.getUmlElementUid()).getName());
			appendExpression(sb, next.getNext());
		}
	}
	private void setLayoutData(Control uimFieldComposite,Outlayable uimField){
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = Boolean.TRUE.equals(uimField.getFillHorizontally());
		gd.horizontalAlignment = Boolean.TRUE.equals(uimField.getFillHorizontally()) ? GridData.FILL : GridData.CENTER;
		gd.grabExcessVerticalSpace = Boolean.TRUE.equals(uimField.getFillVertically());
		gd.verticalAlignment = Boolean.TRUE.equals(uimField.getFillVertically()) ? GridData.FILL : GridData.CENTER;
		uimFieldComposite.setLayoutData(gd);
	}
}
