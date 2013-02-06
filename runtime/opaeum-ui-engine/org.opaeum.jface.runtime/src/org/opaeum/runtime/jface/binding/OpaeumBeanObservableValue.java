package org.opaeum.runtime.jface.binding;

import java.beans.PropertyDescriptor;

import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.uim.component.UimField;

public class OpaeumBeanObservableValue extends AbstractObservableValue implements IBeanObservable{
	private final Object object;
	private final JavaTypedElement propertyDescriptor;
	private BindingUtil bindingUtil;
	private UimField uimField;
	public OpaeumBeanObservableValue(Object object,UimField field,BindingUtil util){
		super(Realm.getDefault());
		this.uimField = field;
		this.object = object;
		this.bindingUtil = util;
		this.propertyDescriptor = util.getTypedElement(uimField.getBinding().getLastPropertyUuid());
	}
	public void doSetValue(final Object value){
		bindingUtil.invokeSetter(object, uimField.getBinding(), value);
		ValueDiff diff = new ValueDiff(){
			@Override
			public Object getOldValue(){
				return doGetValue();
			}
			@Override
			public Object getNewValue(){
				return value;
			}
		};
		fireValueChange(diff);
	}
	public Object doGetValue(){
		return bindingUtil.invoke(object, uimField.getBinding());
	}
	public Object getValueType(){
		return propertyDescriptor.getType();
	}
	public Object getObserved(){
		return object;
	}
	public PropertyDescriptor getPropertyDescriptor(){
		return propertyDescriptor.getPropertyDescriptor();
	}
}
