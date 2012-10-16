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

import javax.persistence.Entity;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.opaeum.rap.runtime.internal.editors.BindingUtil;
import org.opaeum.rap.runtime.internal.editors.DefaultLabelProvider;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInput;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaTypedElement;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.control.UimLookup;

public class DropdownEditingSupport extends EditingSupport{
	private final CheckboxTableViewer tableViewer;
	private final UimField uimField;
	private BindingUtil bindingUtil;
	private EntityEditorInput input;
	public DropdownEditingSupport(CheckboxTableViewer tableViewer,UimField uimField, BindingUtil bindingUtil, EntityEditorInput input){
		super(tableViewer);
		this.tableViewer = tableViewer;
		this.uimField = uimField;
		this.bindingUtil=bindingUtil;
		this.input=input;
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
	protected CellEditor getCellEditor(final Object element){
		JavaTypedElement typedElement = bindingUtil.getTypedElement(uimField.getBinding().getLastPropertyUuid());
		ComboBoxViewerCellEditor result = new ComboBoxViewerCellEditor(tableViewer.getTable());
		result.setLabelProvider(new DefaultLabelProvider());
		result.setContentProvider(new ArrayContentProvider());
		if(typedElement.getBaseType().isEnum()){
			result.setInput(typedElement.getBaseType().getEnumConstants());
		}else if(typedElement.getBaseType().isAnnotationPresent(Entity.class)){
			UimLookup lookup = (UimLookup) uimField.getControl();
			if(lookup.getLookupSource() == null){
				if(typedElement.isReadOnly()){
					result.setInput(bindingUtil.invoke(element, uimField.getBinding()));
				}else{
					result.setInput(typedElement.invokeLookupMethod((IPersistentObject) element));
				}
			}else{
				result.setInput(bindingUtil.invoke(element, lookup.getLookupSource()));
			}
		}
		return result;
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
}