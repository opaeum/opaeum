package org.opaeum.rap.runtime.editingsupport;

import java.text.ParseException;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.opaeum.rap.runtime.internal.editors.BindingUtil;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInput;
import org.opaeum.runtime.strategy.FromStringConverter;
import org.opaeum.runtime.strategy.ToStringConverter;
import org.opaeum.uim.component.UimField;

@SuppressWarnings("serial")
public class TextEditingSupport extends AbstractEditingSupport{
	private TextCellEditor result;
	public TextEditingSupport(CheckboxTableViewer tableViewer,EntityEditorInput input,BindingUtil bindingUtil,UimField uimField){
		super(tableViewer, input, bindingUtil, uimField);
	}
	@Override
	protected Object getValue(Object element){
		Object value = super.getValue(element);
		if(typedElement.getStrategyFactory().hasStrategy(ToStringConverter.class)){
			value = typedElement.getStrategyFactory().getStrategy(ToStringConverter.class).toString(value);
		}else if(!(value instanceof String)){
			value = GenericConverter.getInstance().toString(value);
		}
		return value;
	}
	@Override
	protected void setValue(Object element,Object value){
		try{
			if(typedElement.getStrategyFactory().hasStrategy(FromStringConverter.class)){
				value = typedElement.getStrategyFactory().getStrategy(FromStringConverter.class).fromString((String) value);
			}else if(!typedElement.getBaseType().isInstance(value)){
				value = GenericConverter.getInstance().fromString(typedElement.getBaseType(), (String) value);
			}
			super.setValue(element, value);
		}catch(ParseException e){
		}
	}
	@Override
	protected CellEditor getCellEditor(final Object element){
		if(this.result == null){
			this.result = new TextCellEditor((Composite) tableViewer.getControl());
			result.setValidator(new ICellEditorValidator(){
				public String isValid(Object value){
					try{
						if(typedElement.getStrategyFactory().hasStrategy(FromStringConverter.class)){
							value = typedElement.getStrategyFactory().getStrategy(FromStringConverter.class).fromString((String) value);
						}else if(!typedElement.getBaseType().isInstance(value)){
							value = GenericConverter.getInstance().fromString(typedElement.getBaseType(), (String) value);
						}
						StringBuilder sb = new StringBuilder();
						Set<? extends ConstraintViolation<?>> v = bindingUtil.getValidator().validateValue(typedElement.getDeclaringClass(),
								typedElement.getName(), value);
						for(ConstraintViolation<?> cv:v){
							sb.append(cv.getMessage());
							sb.append(',');
						}
						return sb.length() == 0 ? null : sb.toString();
					}catch(ParseException e){
						return e.getMessage();
					}
				}
			});
		}
		return result;
	}
}