package org.opaeum.reverse.popup.actions;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ITypeBinding;

public class JavaDescriptorFactory{
	private Map<ITypeBinding,JavaClassDescriptor> classDescriptors = new HashMap<ITypeBinding,JavaClassDescriptor>();
	public JavaClassDescriptor getClassDescriptor(ITypeBinding b){
		JavaClassDescriptor result = classDescriptors.get(b);
		if(result == null){
			classDescriptors.put(b, result = new JavaClassDescriptor(b,this));
		}
		return result;
	}
}
