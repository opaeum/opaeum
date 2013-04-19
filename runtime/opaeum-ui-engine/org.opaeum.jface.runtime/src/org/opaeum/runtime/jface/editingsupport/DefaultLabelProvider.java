/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   EclipseSource - initial API and implementation
 ******************************************************************************/
package org.opaeum.runtime.jface.editingsupport;

import org.eclipse.jface.viewers.LabelProvider;
import org.opaeum.runtime.domain.IPersistentObject;

public class DefaultLabelProvider extends LabelProvider{
	@Override
	public String getText(Object value){
		String result = "";
		if(value instanceof IPersistentObject){
			result = ((IPersistentObject) value).getName();
		}else if(value instanceof Enum){
			result = ((Enum) value).name();
		}else if(value != null){
			result = value.toString();
		}
		return result;
	}
}