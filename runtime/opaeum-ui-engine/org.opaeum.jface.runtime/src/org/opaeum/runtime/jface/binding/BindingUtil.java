/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.runtime.jface.binding;

import javax.validation.Validator;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;

public class BindingUtil{
	private Validator validator;
	public BindingUtil(JavaMetaInfoMap metaInfoMap,Validator validator){
		this.javaMetaInfo = metaInfoMap;
		this.validator = validator;
	}
	JavaMetaInfoMap javaMetaInfo;
	public IEventHandler getEventHandler(String umlElementUid){
		return javaMetaInfo.getEventHandler(umlElementUid);
	}
	public JavaTypedElement resolveLastTypedElement(Class<?> source,UimBinding b){
		JavaTypedElement te = javaMetaInfo.getTypedElement(IntrospectionUtil.getOriginalClass(source), b.getUmlElementUid());
		PropertyRef pr = b.getNext();
		if(pr != null){
			te = resolveTypedElement(te.getBaseType(), pr);
		}
		return te;
	}
	private JavaTypedElement resolveTypedElement(Class<?> targetType,PropertyRef pr){
		JavaTypedElement type = javaMetaInfo.getTypedElement(targetType, pr.getUmlElementUid());
		if(pr.getNext() != null){
			type = resolveTypedElement(type.getBaseType(), pr.getNext());
		}
		return type;
	}

	public String getExpression(Class<?> class1, UimBinding b){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(class1, b.getUmlElementUid());
		StringBuilder sb = new StringBuilder(typedElement.getName());
		appendExpression(sb, typedElement.getBaseType(), b.getNext());
		return sb.toString();
	}
	public Object invoke(Object target,UimBinding b){
		// TODO put the consecutive typeElements in a class of its own?
		if(target == null){
			return null;
		}else{
			JavaTypedElement typedElement = javaMetaInfo.getTypedElement(IntrospectionUtil.getOriginalClass(target), b.getUmlElementUid());
			Object value = typedElement.invokeGetter(target);
			if(b.getNext() != null){
				return invoke(value, b.getNext());
			}else{
				return value;
			}
		}
	}
	public Object resolveTarget(Object target,UimBinding b){
		if(target == null){
			return null;
		}else{
			if(b.getNext() == null){
				return target;
			}else{
				JavaTypedElement typedElement = javaMetaInfo.getTypedElement(IntrospectionUtil.getOriginalClass(target), b.getUmlElementUid());
				Object value = typedElement.invokeGetter(target);
				return resolveTarget(value, b.getNext());
			}
		}
	}
	private Object resolveTarget(Object target,PropertyRef current){
		if(target == null){
			return null;
		}else{
			if(current.getNext() == null){
				return target;
			}else{
				JavaTypedElement typedElement = javaMetaInfo
						.getTypedElement(IntrospectionUtil.getOriginalClass(target), current.getUmlElementUid());
				Object value = typedElement.invokeGetter(target);
				return resolveTarget(value, current.getNext());
			}
		}
	}
	private Object invoke(Object target,PropertyRef next){
		if(target == null){
			return null;
		}else{
			JavaTypedElement typedElement = javaMetaInfo.getTypedElement(IntrospectionUtil.getOriginalClass(target), next.getUmlElementUid());
			Object value = typedElement.invokeGetter(target);
			if(next.getNext() != null){
				return invoke(value, next.getNext());
			}else{
				return value;
			}
		}
	}
	private void appendExpression(StringBuilder sb,Class<?> fromClass, PropertyRef next){
		if(next != null){
			sb.append('.');
			JavaTypedElement te = javaMetaInfo.getTypedElement(fromClass,next.getUmlElementUid());
			sb.append(te.getName());
			appendExpression(sb, te.getBaseType(), next.getNext());
		}
	}
	public void invokeSetter(Object element,FieldBinding binding,Object value){
		Object target = resolveTarget(element, binding);
		if(target != null){
			JavaTypedElement typedElement = javaMetaInfo.getTypedElement(IntrospectionUtil.getOriginalClass(target),binding.getLastPropertyUuid());
			typedElement.invokeSetter(target, value);
		}
	}
	public Validator getValidator(){
		return validator;
	}
	public void invokeAdder(Object objectBeingUpdated,IPersistentObject ni,UimBinding binding){
		Object target = resolveTarget(objectBeingUpdated, binding);
		if(target != null){
			JavaTypedElement typedElement = javaMetaInfo.getTypedElement(IntrospectionUtil.getOriginalClass(target),binding.getLastPropertyUuid());
			typedElement.invokeAdder(target, ni);
		}
	}
	public JavaTypedElement resolveLastTypedElement(Object object,UimBinding binding){
		if(object==null){
			return null;
		}else{
			return resolveLastTypedElement(IntrospectionUtil.getOriginalClass(object), binding);
		}
	}
	public Object newInstance(String umlElementUid){
		Class<?> class1 = javaMetaInfo.getClass(umlElementUid);
		return IntrospectionUtil.newInstance(class1);
	}
}