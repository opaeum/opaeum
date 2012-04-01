package org.opaeum.rap.runtime.internal.editors;

import javax.persistence.Entity;
import javax.validation.Validator;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.opaeum.rap.runtime.internal.binding.GenericFromStringConverter;
import org.opaeum.rap.runtime.internal.binding.GenericToStringConverter;
import org.opaeum.rap.runtime.internal.binding.GenericValidator;
import org.opaeum.rap.runtime.internal.datamodel.PageUtil;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.environment.JavaTypedElement;
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
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.UimLookup;
import org.opaeum.uim.panel.GridPanel;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.swt.GridPanelComposite;
import org.opaeum.uim.swt.NumberScroller;
import org.opaeum.uim.swt.TableAndActionBarComposite;
import org.opaeum.uim.swt.UimFieldComposite;
import org.opaeum.uim.swt.UimSwtUtil;

public class ComponentTreeBuilder{
	IPersistentObject persistentObject;
	JavaMetaInfoMap javaMetaInfo;
	Class<? extends IPersistentObject> objectClass;
	private Validator validator;
	public ComponentTreeBuilder(IPersistentObject persistentObject,JavaMetaInfoMap javaMetaInfo,Validator validator){
		super();
		this.validator = validator;
		this.persistentObject = persistentObject;
		this.javaMetaInfo = javaMetaInfo;
		objectClass = IntrospectionUtil.getOriginalClass(persistentObject);
	}
	public void addComponent(Composite body,UimComponent comp){
		if(comp instanceof GridPanel){
			GridPanelComposite gpc = new GridPanelComposite(body, SWT.NONE);
			Integer numberOfColumns = ((GridPanel) comp).getNumberOfColumns();
			gpc.getContentPane().setLayout(new GridLayout(numberOfColumns, false));
			EList<UimComponent> children = ((GridPanel) comp).getChildren();
			for(UimComponent child:children){
				addComponent(gpc.getContentPane(), child);
			}
			gpc.layout();
		}else if(comp instanceof UimField){
			if(comp.eContainer() instanceof UimDataTable){
			}else{
				addUimFieldComposite(body, comp);
			}
		}else if(comp instanceof UimDataTable){
			TableAndActionBarComposite table = new TableAndActionBarComposite(body, SWT.BORDER);
			TableViewer tableViewer = new TableViewer(table.getTable());
			tableViewer.setContentProvider(new ArrayContentProvider());
			//TODO complex expressions
			UimDataTable uimTable = (UimDataTable) comp;
			JavaTypedElement tableRef = this.javaMetaInfo.getTypedElement(uimTable.getBinding().getUmlElementUid());
			EList<UimComponent> children = uimTable.getChildren();
			setLayoutData(table, uimTable);
			int i = 0;
			for(UimComponent child:children){
				if(child instanceof UimField){
					final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE, i++);
					final TableColumn column = viewerColumn.getColumn();
					column.setText(child.getName());
					UimField uimField = (UimField) child;
					column.setWidth(uimField.getPreferredWidth()==null?130:uimField.getPreferredWidth());
					column.setResizable(true);
					column.setMoveable(true);
					
					//TODO complex expressions
					 final JavaTypedElement te = this.javaMetaInfo.getTypedElement(uimField.getBinding().getUmlElementUid());
					viewerColumn.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public String getText(Object element){
							//TODO complex expressions
							Object value = te.invokeGetter(element);
							if(value instanceof IPersistentObject){
								return ((IPersistentObject) value).getName();
							}else if(value instanceof Enum){
								return ((Enum) value).name();
							}else{
								return value.toString();
							}
						}
					});
				}
			}
			EList<UimAction> actionsOnMultipleSelection = uimTable.getActionsOnMultipleSelection();
			for(UimAction uimAction:actionsOnMultipleSelection){
				addComponent(table.getActionBar(), uimAction);
			}
			tableViewer.setInput(tableRef.invokeGetter(persistentObject));
		}else if(comp instanceof BuiltInActionButton){
			BuiltInActionButton btn = (BuiltInActionButton) comp;
			Button button = new Button(body, SWT.PUSH);
			button.setText(btn.getName());
			setLayoutData(button, btn);
		}else if(comp instanceof OperationButton){
			OperationButton ob = (OperationButton) comp;
			Button button = new Button(body, SWT.PUSH);
			button.setText(ob.getName());
			setLayoutData(button, ob);
		}
	}
	private void addUimFieldComposite(Composite body,UimComponent comp){
		UimFieldComposite uimFieldComposite = new UimFieldComposite(body, SWT.NONE);
		UimField uimField = (UimField) comp;
		UimSwtUtil.populateControl(uimFieldComposite, uimField.getControlKind(), uimField.getOrientation());
		setLayoutData(uimFieldComposite, uimField);
		uimFieldComposite.setMinimumLabelWidth(uimField.getMinimumLabelWidth());
		uimFieldComposite.getLabel().setText(uimField.getName());
		UimSwtUtil.setOrientation(uimField.getOrientation(), uimFieldComposite, uimField.getMinimumLabelWidth());
		uimFieldComposite.layout();
		DataBindingContext bc = PageUtil.createBindingContext();
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(uimField.getBinding().getLastPropertyUuid());
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		UpdateValueStrategy modelToTarget = new UpdateValueStrategy();
		IObservableValue observeValue = BeansObservables.observeValue(persistentObject, getExpression(uimField.getBinding()));
		targetToModel.setAfterConvertValidator(new GenericValidator(IntrospectionUtil.getOriginalClass(persistentObject), typedElement,
				this.validator));
		ISWTObservableValue observeText = null;
		switch(uimField.getControlKind()){
		case TEXT:
		case NUMBER_SCROLLER:
		case TEXT_AREA:
			observeText = populateTextStrategies(uimFieldComposite, typedElement, targetToModel, modelToTarget);
			break;
		case CHECK_BOX:
			break;
		case DATE_POPUP:
		case DATE_SCROLLER:
		case DATE_TIME_POPUP:
			observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case LIST_BOX:
			observeText = observeListbox(uimFieldComposite, uimField, typedElement);
			break;
		case DROPDOWN:
			observeText = observeDropdown(uimFieldComposite, uimField, typedElement);
			break;
		case LABEL:
			observeText = populateTextStrategies(uimFieldComposite, typedElement, targetToModel, modelToTarget);
			break;
		case LINK:
			observeText = populateTextStrategies(uimFieldComposite, typedElement, targetToModel, modelToTarget);
			break;
		case POPUP_SEARCH:
			observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case RADIO_BUTTON:
			observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case SELECTION_TABLE:
			break;
		case TOGGLE_BUTTON:
			observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		case TREE_VIEW:
			observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
			break;
		}
		Binding bindValue = bc.bindValue(observeText, observeValue, targetToModel, modelToTarget);
		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
		// IObservableValue errorObservable = WidgetProperties.text().observe(uimFieldComposite.getLabel());
		// // This one listenes to all changes
		// bc.bindValue(errorObservable, new AggregateValidationStatus(bc.getBindings(), AggregateValidationStatus.MAX_SEVERITY), null, null);
	}
	private ISWTObservableValue observeListbox(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement){
		ISWTObservableValue observeText;
		observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
		if(typedElement.getBaseType().isEnum()){
		}else if(typedElement.getBaseType().isAnnotationPresent(Entity.class)){
			ListViewer cb = new ListViewer((List) uimFieldComposite.getControl());
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
					//TODO complex expressions
					cb.setInput(typedElement.invokeGetter(persistentObject));
				}else{
					cb.setInput(typedElement.invokeLookupMethod(persistentObject));
				}
			}else{
				// TODO lookup
			}
		}
		return observeText;
	}
	private ISWTObservableValue observeDropdown(UimFieldComposite uimFieldComposite,UimField uimField,JavaTypedElement typedElement){
		ISWTObservableValue observeText;
		observeText = SWTObservables.observeSelection(uimFieldComposite.getControl());
		ComboViewer cb = new ComboViewer((Combo) uimFieldComposite.getControl());
		if(typedElement.getBaseType().isEnum()){
			cb.setLabelProvider(new LabelProvider(){
				@Override
				public String getText(Object element){
					return ((Enum) element).name();
				}
			});
			cb.setContentProvider(new ArrayContentProvider());
			cb.setInput(typedElement.getBaseType().getEnumConstants());
		}else if(IPersistentObject.class.isAssignableFrom(typedElement.getBaseType())){
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
					//TODO complex expressions
					cb.setInput(typedElement.invokeGetter(persistentObject));
				}else{
					cb.setInput(typedElement.invokeLookupMethod(persistentObject));
				}
			}else{
				// TODO lookup
			}
		}
		return observeText;
	}
	private ISWTObservableValue populateTextStrategies(UimFieldComposite uimFieldComposite,JavaTypedElement typedElement,
			UpdateValueStrategy targetToModel,UpdateValueStrategy modelToTarget){
		Control control = uimFieldComposite.getControl();
		if(control instanceof NumberScroller){
			control = ((NumberScroller) control).getChildren()[0];// TODO provide getter
		}
		ISWTObservableValue observeText = SWTObservables.observeText((Text) control, SWT.Modify);
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
