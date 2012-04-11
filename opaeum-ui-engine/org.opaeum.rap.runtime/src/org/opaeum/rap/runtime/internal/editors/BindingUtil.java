/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.editors;

import org.opaeum.runtime.environment.JavaMetaInfoMap;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.event.IEventHandler;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.UimBinding;

public class BindingUtil{
	public BindingUtil(JavaMetaInfoMap metaInfoMap){
		this.javaMetaInfo = metaInfoMap;
	}
	JavaMetaInfoMap javaMetaInfo;
	public IEventHandler getEventHandler(String umlElementUid){
		return javaMetaInfo.getEventHandler(umlElementUid);
	}
	public JavaTypedElement getTypedElement(String lastPropertyUuid){
		return javaMetaInfo.getTypedElement(lastPropertyUuid);
	}
	public String getExpression(UimBinding b){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(b.getUmlElementUid());
		StringBuilder sb = new StringBuilder(typedElement.getName());
		appendExpression(sb, b.getNext());
		return sb.toString();
	}
	public Object invoke(Object target,UimBinding b){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(b.getUmlElementUid());
		Object value = typedElement.invokeGetter(target);
		if(b.getNext() != null){
			return invoke(value, b.getNext());
		}else{
			return value;
		}
	}
	public Object resolveTarget(Object target,UimBinding b){
		if(b.getNext() == null){
			return target;
		}else{
			JavaTypedElement typedElement = javaMetaInfo.getTypedElement(b.getUmlElementUid());
			Object value = typedElement.invokeGetter(target);
			return resolveTarget(value, b.getNext());
		}
	}
	private Object resolveTarget(Object target,PropertyRef next){
		if(next.getNext() == null){
			return target;
		}else{
			JavaTypedElement typedElement = javaMetaInfo.getTypedElement(next.getUmlElementUid());
			Object value = typedElement.invokeGetter(target);
			return resolveTarget(value, next.getNext());
		}
	}
	private Object invoke(Object target,PropertyRef next){
		JavaTypedElement typedElement = javaMetaInfo.getTypedElement(next.getUmlElementUid());
		Object value = typedElement.invokeGetter(target);
		if(next.getNext() != null){
			return invoke(value, next.getNext());
		}else{
			return value;
		}
	}
	private void appendExpression(StringBuilder sb,PropertyRef next){
		if(next != null){
			sb.append('.');
			sb.append(javaMetaInfo.getTypedElement(next.getUmlElementUid()).getName());
			appendExpression(sb, next.getNext());
		}
	}
}