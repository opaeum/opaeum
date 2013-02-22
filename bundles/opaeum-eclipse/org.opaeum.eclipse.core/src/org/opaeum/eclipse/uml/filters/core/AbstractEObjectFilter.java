package org.opaeum.eclipse.uml.filters.core;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.UMLFactory;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.runtime.domain.IntrospectionUtil;

@SuppressWarnings("unchecked")
public abstract class AbstractEObjectFilter<T extends EObject> implements IFilter{
	private Class<? extends T> objectClass;
	public abstract boolean select(T e);
	private Class<? extends T> getObjectClass(){
		if(this.objectClass == null){
			this.objectClass = calcType(getClass());
		}
		return this.objectClass;
	}
	private Class<? extends T> calcType(Type sc){
		if(sc == Object.class){
			return (Class<? extends T>) EObject.class;
		}
		Class<? extends T> result = null;
		if(sc instanceof ParameterizedType){
			ParameterizedType t = (ParameterizedType) sc;
			Type[] actualTypeArguments = t.getActualTypeArguments();
			if(actualTypeArguments.length == 1){
				if(actualTypeArguments[0] instanceof Class){
					result = (Class<? extends T>) actualTypeArguments[0];
				}else if(actualTypeArguments[0] instanceof TypeVariable){
					TypeVariable tv=(TypeVariable) actualTypeArguments[0];
					Type[] bounds = tv.getBounds();
					tv.getGenericDeclaration().getTypeParameters();
				}
			}
		}
		if(result == null && sc instanceof Class){
			result = calcType(((Class<? extends T>) sc).getGenericSuperclass());
		}
		return result;
	}
	@Override
	public boolean select(Object toTest){
		if(!(getObjectClass().isInstance(toTest))){
			EObject o = EmfElementFinder.adaptObject(toTest);
			if(o == null){
				return false;
			}else if(o.eClass().getName().equals("Diagram")){
				return false;
			}else if(getObjectClass().isInstance(o)){
				toTest = o;
			}else{
				// TODO don't think this is used anymore
				PropertyDescriptor eObjectProp = IntrospectionUtil.getProperty("EObject", toTest.getClass());
				if(eObjectProp != null){
					toTest = IntrospectionUtil.get(eObjectProp, toTest);
				}else{
					eObjectProp = IntrospectionUtil.getProperty("UMLElement", toTest.getClass());
					if(eObjectProp != null){
						toTest = IntrospectionUtil.get(eObjectProp, toTest);
					}
				}
			}
		}
		if(getObjectClass().isInstance(toTest)){
			return select((T) toTest);
		}else{
			return false;
		}
	}
	public static void main(String[] args){
		System.out.println(new AbstractEObjectFilter<Element>(){
			@Override
			public boolean select(Element e){
				return true;
			}
		}.select((Object) UMLFactory.eINSTANCE.createClass()));
	}
}
