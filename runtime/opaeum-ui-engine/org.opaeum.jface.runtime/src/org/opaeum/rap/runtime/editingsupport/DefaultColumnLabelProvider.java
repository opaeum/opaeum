/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.editingsupport;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.opaeum.rap.runtime.internal.editors.BindingUtil;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.runtime.strategy.ToStringConverter;
import org.opaeum.uim.component.UimField;

public final class DefaultColumnLabelProvider extends ColumnLabelProvider{
	public final UimField uimField;
	String prefix = "";
	String suffix = "";
	private BindingUtil util;
	private JavaTypedElement typedElement;
	public DefaultColumnLabelProvider(UimField uimField,String prefix,String suffix,BindingUtil util){
		this.uimField = uimField;
		this.prefix = prefix;
		this.suffix = suffix;
		this.util = util;
		typedElement = util.getTypedElement(uimField.getBinding().getLastPropertyUuid());
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
			if(typedElement.getStrategyFactory().hasStrategy(ToStringConverter.class)){
				result = typedElement.getStrategyFactory().getStrategy(ToStringConverter.class).toString(value);
			}else{
				result = GenericConverter.getInstance().toString(value);
			}
		}else{
			result = "";
		}
		return prefix + result + suffix;
	}
}