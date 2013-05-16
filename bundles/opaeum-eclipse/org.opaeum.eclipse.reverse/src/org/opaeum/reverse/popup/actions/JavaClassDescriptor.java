package org.opaeum.reverse.popup.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ITypeBinding;

public class JavaClassDescriptor{
	private ITypeBinding typeBinding;
	private Map<String,PropertyDescriptor> propertyDescriptors=new HashMap<String,PropertyDescriptor>();
	public JavaClassDescriptor(ITypeBinding getter, JavaDescriptorFactory factory){
		this.typeBinding= getter;
		for(PropertyDescriptor pd:PropertyDescriptor.getPropertyDescriptors(typeBinding,factory)){
			propertyDescriptors.put(pd.getName(), pd);
		}
	}
	public Map<String,PropertyDescriptor> getPropertyDescriptors(){
		return propertyDescriptors;
	}
}
