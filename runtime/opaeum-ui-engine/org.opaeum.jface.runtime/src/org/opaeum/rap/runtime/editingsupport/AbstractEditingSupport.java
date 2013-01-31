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

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.opaeum.rap.runtime.internal.editors.BindingUtil;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInputJface;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.uim.component.UimField;

public abstract class AbstractEditingSupport extends EditingSupport{
	protected final ColumnViewer tableViewer;
	protected final EntityEditorInputJface input;
	protected final BindingUtil bindingUtil;
	protected final UimField uimField;
	protected JavaTypedElement typedElement;
	public AbstractEditingSupport(ColumnViewer tableViewer,EntityEditorInputJface input,BindingUtil bindingUtil,UimField uimField){
		super(tableViewer);
		this.tableViewer = tableViewer;
		this.input = input;
		this.bindingUtil = bindingUtil;
		this.uimField = uimField;
		this.typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
	}
	@Override
	protected void setValue(Object element,Object value){
		Object target = bindingUtil.resolveTarget(element, uimField.getBinding());
		if(target != null){
			JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
			typedElement.invokeSetter(target, value);
			tableViewer.refresh(element);
			if(input != null){
				input.setDirty(true);
			}
		}
	}
	@Override
	protected Object getValue(Object element){
		return bindingUtil.invoke(element, uimField.getBinding());
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	public ICellEditorValidator getDefaultValidator(){
		return new ICellEditorValidator(){
			public String isValid(Object value){
				StringBuilder sb = new StringBuilder();
				Set<? extends ConstraintViolation<?>> v = bindingUtil.getValidator().validateValue(typedElement.getDeclaringClass(),
						typedElement.getName(), value);
				for(ConstraintViolation<?> cv:v){
					sb.append(cv.getMessage());
					sb.append(',');
				}
				return sb.length() == 0 ? null : sb.toString();
			}
		};
	}
}