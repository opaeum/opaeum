package org.opaeum.runtime.jface.builder;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.Validator;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.AbstractListViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
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
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.runtime.jface.actions.OpenEditorAction;
import org.opaeum.runtime.jface.binding.BindingUtil;
import org.opaeum.runtime.jface.binding.GenericFromStringConverter;
import org.opaeum.runtime.jface.binding.GenericToStringConverter;
import org.opaeum.runtime.jface.binding.GenericValidator;
import org.opaeum.runtime.jface.binding.OpaeumBeanObservableValue;
import org.opaeum.runtime.jface.binding.SingleObjectSelectionProperty;
import org.opaeum.runtime.jface.editingsupport.GenericConverter;
import org.opaeum.runtime.jface.entityeditor.EntityEditorInputJface;
import org.opaeum.runtime.jface.entityeditor.SecurityUtil;
import org.opaeum.runtime.jface.ui.OpaeumValidationRealm;
import org.opaeum.runtime.jface.widgets.CSingleObjectChooser;
import org.opaeum.runtime.jface.wizards.OperationInvocationWizard;
import org.opaeum.runtime.rwt.IOpaeumApplication;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.runtime.strategy.FromStringConverter;
import org.opaeum.runtime.strategy.ToStringConverter;
import org.opaeum.uim.action.ActionKind;
import org.opaeum.uim.action.BuiltInActionButton;
import org.opaeum.uim.action.BuiltInLink;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uim.swt.LinkComposite;
import org.opaeum.uim.swt.UimFieldComposite;
import org.opaeum.uim.swt.UimSwtUtil;

@SuppressWarnings("serial")
public class ComponentTreeBuilder{
	BindingUtil bindingUtil;
	IPersistentObject selectedObject;
	Object objectBeingUpdated;
	private Validator validator;
	IOpaeumApplication application;
	private OpaeumRapSession session;
	private EntityEditorInputJface input;
	DataTableBuilder dataTableBuilder;
	private SecurityUtil securityUtil;
	// Constructor for operation invocation wizards where there is no editor input
	public ComponentTreeBuilder(IPersistentObject selectedObject,Object handler,OpaeumRapSession session){
		init(selectedObject, handler, session);
		new OpaeumValidationRealm();
		this.dataTableBuilder = new DataTableBuilder(selectedObject, handler, session);
	}
	private void init(IPersistentObject selectedObject,Object objectBeingUpdated,OpaeumRapSession session){
		this.session = session;
		this.application = session.getApplication();
		this.validator = application.getValidator();
		this.objectBeingUpdated = objectBeingUpdated;
		this.selectedObject = selectedObject;
		this.bindingUtil = new BindingUtil(application.getEnvironment().getMetaInfoMap(), application.getValidator());
		this.securityUtil = new SecurityUtil(selectedObject, session);
	}
	// Constructor for class editors
	public ComponentTreeBuilder(EntityEditorInputJface input){
		this(input.getPersistentObject(), input.getPersistentObject(), input.getOpaeumSession());
		this.input = input;
		this.dataTableBuilder = new DataTableBuilder(input);
	}
	public void addComponent(final Composite body,UimComponent comp,DataBindingContext bc){
		if(comp instanceof GridPanel){
			GridPanelComposite gpc = new GridPanelComposite(body, SWT.NONE);
			Integer numberOfColumns = ((GridPanel) comp).getNumberOfColumns();
			gpc.getContentPane().setLayout(new GridLayout(numberOfColumns, false));
			List<UimComponent> children = ((GridPanel) comp).getChildren();
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
			dataTableBuilder.buildDataTable(body, comp);
		}else if(comp instanceof BuiltInActionButton){
			BuiltInActionButton btn = (BuiltInActionButton) comp;
			Button button = new Button(body, SWT.PUSH);
			button.setText(btn.getName());
			setLayoutData(button, btn);
			ActionKind kind = btn.getKind()==null?ActionKind.UPDATE:btn.getKind();
			switch(kind){
			case UPDATE:
				button.addSelectionListener(new SelectionListener(){
					public void widgetSelected(SelectionEvent e){
						input.getPersistence().flush();
						input.setDirty(false);
					}
					public void widgetDefaultSelected(SelectionEvent e){
					}
				});
				break;
			case DELETE:
				button.addSelectionListener(new SelectionListener(){
					public void widgetSelected(SelectionEvent e){
						((CompositionNode) input.getPersistentObject()).markDeleted();
						input.getPersistence().flush();
						input.setDirty(false);
					}
					public void widgetDefaultSelected(SelectionEvent e){
					}
				});
				break;
			case EXECUTE:
				break;
			}
		}else if(comp instanceof InvocationButton){
			final InvocationButton ob = (InvocationButton) comp;
			Button button = new Button(body, SWT.PUSH);
			button.setText(ob.getName());
			setLayoutData(button, ob);
			button.addSelectionListener(new SelectionListener(){
				public void widgetSelected(SelectionEvent e){
					if(ob.eContainer() instanceof UimDataTable){
						// TODO handle multiple selection etc.
					}else{
						IEventHandler eventHandler = bindingUtil.getEventHandler(ob.getUmlElementUid());
						OperationInvocationWizard wizard = new OperationInvocationWizard((IPersistentObject) objectBeingUpdated, eventHandler, ob
								.getPopup(), input);
						WizardDialog dialog = new WizardDialog(body.getShell(), wizard);
						dialog.open();
					}
				}
				public void widgetDefaultSelected(SelectionEvent e){
				}
			});
		}else if(comp instanceof BuiltInLink){
			BuiltInLink link = (BuiltInLink) comp;
			switch(link.getKind()){
			case AUDIT_TRAIL:
				break;
			case BUSINESS_INTELLIGENCE:
				Link hl = new Link(body, SWT.NONE);
				hl.setText(link.getName());
				hl.addSelectionListener(new SelectionListener(){
					@Override
					public void widgetSelected(SelectionEvent e){
						OpenEditorAction.openCubeEditor(objectBeingUpdated, false, input.getOpaeumSession());
					}
					@Override
					public void widgetDefaultSelected(SelectionEvent e){
						// TODO Auto-generated method stub
					}
				});
				break;
			}
		}
	}
	private void addUimFieldComposite(Composite body,UimComponent comp,DataBindingContext bc){
		UimFieldComposite uimFieldComposite = new UimFieldComposite(body, SWT.NONE);
		UimField uimField = (UimField) comp;
		if(uimField.getControlKind()==null){
			System.out.println(comp.getName());
			System.out.println();
		}
		ControlKind controlKind = uimField.getControlKind()==null?ControlKind.TEXT:uimField.getControlKind();
		UimSwtUtil.populateControl(uimFieldComposite, controlKind, uimField.getOrientation());
		setLayoutData(uimFieldComposite, uimField);
		uimFieldComposite.setMinimumLabelWidth(uimField.getMinimumLabelWidth());
		uimFieldComposite.getLabel().setText(uimField.getName());
		UimSwtUtil.setOrientation(uimField.getOrientation(), uimFieldComposite, uimField.getMinimumLabelWidth());
		uimFieldComposite.layout();
		final JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		IObservableValue observeValue = BeansObservables.observeValue( objectBeingUpdated, bindingUtil.getExpression(uimField.getBinding()));
		targetToModel.setAfterConvertValidator(new GenericValidator(IntrospectionUtil.getOriginalClass(objectBeingUpdated), typedElement,
				this.validator));
		IObservableValue observeControl = null;
		switch(controlKind){
		case TEXT:
		case TEXT_AREA:
			observeControl = populateTextStrategies(uimFieldComposite, typedElement, targetToModel, modelToTarget);
			break;
		case CHECK_BOX:
			observeValue = new OpaeumBeanObservableValue(objectBeingUpdated, uimField, bindingUtil);
			observeControl = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case NUMBER_SCROLLER:
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
			CLabel label = (CLabel) uimFieldComposite.getControl();
			Object value = bindingUtil.invoke(objectBeingUpdated, uimField.getBinding());
			if(value instanceof IPersistentObject){
				label.setText(((IPersistentObject) value).getName());
			}else if(value instanceof Enum){
				label.setText(((Enum<?>) value).name());
			}else if(value != null){
				if(typedElement.getStrategyFactory().hasStrategy(ToStringConverter.class)){
					label.setText(typedElement.getStrategyFactory().getStrategy(ToStringConverter.class).toString(value));
				}else{
					label.setText(GenericConverter.getInstance().toString(value));
				}
			}else{
				label.setText("");
			}
			break;
		case LINK:
			LinkComposite link = (LinkComposite) uimFieldComposite.getControl();
			final IPersistentObject linked = (IPersistentObject) bindingUtil.invoke(objectBeingUpdated, uimField.getBinding());
			Link firstLink = (Link) link.getChildren()[0];
			firstLink.setText(linked.getName());
			firstLink.addMouseListener(new MouseListener(){
				public void mouseUp(MouseEvent e){
					OpenEditorAction.openEntityEditor(linked, true, session);
				}
				public void mouseDown(MouseEvent e){
				}
				public void mouseDoubleClick(MouseEvent e){
				}
			});
			break;
		case POPUP_SEARCH:
			observeControl = observePopupSearch(uimFieldComposite, uimField, typedElement);
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
		uimFieldComposite.setEnabled(securityUtil.calculateEditability(uimField));
		uimFieldComposite.setVisible(securityUtil.calculateVisibility(uimField));
	}
	private IObservableValue observeListbox(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement){
		AbstractListViewer cb = new ListViewer((org.eclipse.swt.widgets.List) uimFieldComposite.getControl());
		return observeListViewer(uimFieldComposite, uimField, typedElement, cb);
	}
	private IObservableValue observeListViewer(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement,
			AbstractListViewer cb){
		IObservableValue observeText = ViewersObservables.observeSingleSelection(cb);
		cb.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element){
				if(element instanceof Enum){
					return ((Enum<?>) element).name();
				}else if(element instanceof IPersistentObject){
					return ((IPersistentObject) element).getName();
				}else{
					return element.toString();
				}
			}
		});
		cb.setContentProvider(new ArrayContentProvider());
		if(typedElement.getBaseType().isEnum()){
			cb.setInput(typedElement.getBaseType().getEnumConstants());
		}else if(typedElement.getBaseType().isAnnotationPresent(Entity.class)){
			UimLookup lookup = (UimLookup) uimField.getControl();
			if(lookup.getLookupSource() == null){
				if(typedElement.isReadOnly()){
					cb.setInput(bindingUtil.invoke(objectBeingUpdated, uimField.getBinding()));
				}else{
					cb.setInput(typedElement.invokeLookupMethod(selectedObject));
				}
			}else{
				cb.setInput(bindingUtil.invoke(objectBeingUpdated, lookup.getLookupSource()));
			}
		}
		return observeText;
	}
	private ISWTObservableValue observePopupSearch(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement){
		ISWTObservableValue observeText;
		// TODO mave to UimSwtUtil - requires some dependency management
		CSingleObjectChooser cb = new CSingleObjectChooser(uimFieldComposite, SWT.None);
		cb.setLayoutData(uimFieldComposite.getLayoutData());
		uimFieldComposite.getControl().dispose();
		uimFieldComposite.setControl(cb);
		uimFieldComposite.layout();
		observeText = new SingleObjectSelectionProperty().observe(cb);
		if(typedElement.getBaseType().isEnum()){
		}else if(typedElement.getBaseType().isAnnotationPresent(Entity.class)){
			Collection<?> invoke;
			cb.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element){
					return ((IPersistentObject) element).getName();
				}
			});
			UimLookup lookup = (UimLookup) uimField.getControl();
			if(lookup.getLookupSource() == null){
				if(typedElement.isReadOnly()){
					invoke = (Collection<?>) bindingUtil.invoke(objectBeingUpdated, uimField.getBinding());
				}else{
					invoke = (Collection<?>) typedElement.invokeLookupMethod(selectedObject);
				}
			}else{
				invoke = (Collection<?>) bindingUtil.invoke(objectBeingUpdated, lookup.getLookupSource());
			}
			if(invoke != null){
				cb.setChoices(invoke.toArray());
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
	public static void setLayoutData(Control uimFieldComposite,Outlayable uimField){
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = Boolean.TRUE.equals(uimField.getFillHorizontally());
		gd.horizontalAlignment = Boolean.TRUE.equals(uimField.getFillHorizontally()) ? GridData.FILL : GridData.CENTER;
		gd.grabExcessVerticalSpace = Boolean.TRUE.equals(uimField.getFillVertically());
		gd.verticalAlignment = Boolean.TRUE.equals(uimField.getFillVertically()) ? GridData.FILL : GridData.CENTER;
		uimFieldComposite.setLayoutData(gd);
	}
}
