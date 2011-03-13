package org.nakeduml.reverse.popup.actions;

import java.beans.Introspector;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

public class PropertyDescriptor {
	String name;
	IMethodBinding getter;
	boolean isReadOnly = true;

	public PropertyDescriptor(IMethodBinding getter) {
		this.getter = getter;
		this.name = Introspector.decapitalize(getter.getName().substring(3));
	}

	public String getName() {
		return name;
	}

	public ITypeBinding getType() {
		return getter.getReturnType();
	}

	public static Collection<PropertyDescriptor> getPropertyDescriptors(ITypeBinding type) {
		IMethodBinding[] methods = type.getDeclaredMethods();
		Collection<PropertyDescriptor> results = new ArrayList<PropertyDescriptor>();
		for (IMethodBinding getter : methods) {
			if (Modifier.isPublic(getter.getModifiers()) && isGetter(getter)) {
				PropertyDescriptor pd = new PropertyDescriptor(getter);
				results.add(pd);
				for (IMethodBinding setter : methods) {
					if (isSetterFor(setter, pd.getter)) {
						pd.isReadOnly = false;
					}
				}
			}
		}
		return results;
	}

	protected static boolean isGetter(IMethodBinding method) {
		return method.getName().startsWith("get") && !method.getReturnType().getName().equals("void")
				&& method.getParameterTypes().length == 0;
	}

	protected static boolean isSetterFor(IMethodBinding setter, IMethodBinding getter) {
		boolean nameOK = setter.getName().startsWith("set") && setter.getName().substring(3).equals(getter.getName().substring(3));
		boolean typesMatch = setter.getParameterTypes().length == 1 && setter.getParameterTypes()[0].equals(getter.getReturnType());
		return nameOK && setter.getReturnType().getName().equals("void") && typesMatch;
	}
}
