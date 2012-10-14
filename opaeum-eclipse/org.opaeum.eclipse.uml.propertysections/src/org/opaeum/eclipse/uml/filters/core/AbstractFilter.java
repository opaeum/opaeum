package org.opaeum.eclipse.uml.filters.core;

import java.beans.PropertyDescriptor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.uml2.uml.Element;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.runtime.domain.IntrospectionUtil;

public abstract class AbstractFilter implements IFilter{
	public abstract boolean select(Element e);
	public boolean select(EObject e){
		return false;
	}
	@Override
	public boolean select(Object toTest){
		Element element = null;
		if(!(toTest instanceof Element)){
			EObject o = EmfElementFinder.adaptObject(toTest);
			if(o == null){
				return false;
			}else if(o.eClass().getName().equals("Diagram")){
				return false;
			}else if(o instanceof Element){
				toTest = o;
			}else{
				PropertyDescriptor eObjectProp = IntrospectionUtil.getProperty("EObject", toTest.getClass());
				if(eObjectProp != null){
					toTest = IntrospectionUtil.get(eObjectProp, toTest);
				}else{
					eObjectProp = IntrospectionUtil.getProperty("UMLElement", toTest.getClass());
					if(eObjectProp != null){
						toTest = IntrospectionUtil.get(eObjectProp, toTest);
					}
				}
				if(!(toTest instanceof Element)){
					return select(o);
				}
			}
		}
		if(toTest instanceof Element){
			element = (Element) toTest;
			return select(element);
		}else{
			return false;
		}
	}
}
