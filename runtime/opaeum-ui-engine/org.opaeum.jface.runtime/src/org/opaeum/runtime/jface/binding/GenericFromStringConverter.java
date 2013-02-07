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

import java.text.ParseException;

import org.eclipse.core.databinding.conversion.IConverter;
import org.opaeum.runtime.strategy.FromStringConverter;

public class GenericFromStringConverter implements IConverter{
	Class<?> toType;
	FromStringConverter delegate;
	public GenericFromStringConverter(Class<?> toType,FromStringConverter delegate){
		super();
		this.toType = toType;
		this.delegate = delegate;
	}
	public Object getFromType(){
		return String.class;
	}
	public Object getToType(){
		return toType;
	}
	public Object convert(Object fromObject){
		try{
			return delegate.fromString((String) fromObject);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
	}
}
