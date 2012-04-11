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

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.uim.UimField;

public final class DefaultColumnLabelProvider extends ColumnLabelProvider{
	public final UimField uimField;
	String prefix = "";
	String suffix = "";
	private BindingUtil util;
	public DefaultColumnLabelProvider(UimField uimField,String prefix,String suffix,BindingUtil util){
		this.uimField = uimField;
		this.prefix = prefix;
		this.suffix = suffix;
		this.util = util;
	}
	@Override
	public String getText(Object element){
		Object value = util.invoke(element, uimField.getBinding());
		String result = "";
		if(value instanceof IPersistentObject){
			result = ((IPersistentObject) value).getName();
		}else if(value instanceof Enum){
			result = ((Enum) value).name();
		}else if(value != null){
			result = value.toString();
		}else{
			return "";
		}
		return prefix + result + suffix;
	}
}