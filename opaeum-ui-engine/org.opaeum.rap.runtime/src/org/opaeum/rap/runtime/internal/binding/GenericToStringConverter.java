/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.rap.runtime.internal.binding;

import org.eclipse.core.databinding.conversion.IConverter;
import org.opaeum.runtime.strategy.ToStringConverter;

public class GenericToStringConverter implements IConverter{
	public GenericToStringConverter(Class<?> fromType,ToStringConverter delegate){
		super();
		this.fromType = fromType;
		this.delegate = delegate;
	}
	private Class<?> fromType;
	private ToStringConverter delegate;
	public Object getFromType(){
		return this.fromType;
	}
	public Object getToType(){
		return String.class;
	}
	public Object convert(Object fromObject){
		return delegate.toString(fromObject);
	}
}