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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.opaeum.rap.runtime.internal.editors.BindingUtil;
import org.opaeum.rap.runtime.internal.editors.EntityEditorInputJface;
import org.opaeum.uim.component.UimField;

public class CheckboxEditingSupport extends EditingSupport{
	private final UimField uimField;
	private final EntityEditorInputJface input;
	private final BindingUtil bindingUtil;
	private final CheckboxTableViewer tableViewer;
	public CheckboxEditingSupport(CheckboxTableViewer tableViewer,UimField uimField,EntityEditorInputJface input,BindingUtil bindingUtil){
		super(tableViewer);
		this.uimField = uimField;
		this.input = input;
		this.bindingUtil = bindingUtil;
		this.tableViewer = tableViewer;
	}
	@Override
	protected void setValue(Object element,Object value){
		bindingUtil.invokeSetter(element, uimField.getBinding(), value);
		tableViewer.refresh(element);
		if(input != null){
			input.setDirty(true);
		}
	}
	@Override
	protected Object getValue(Object element){
		return Boolean.TRUE.equals(bindingUtil.invoke(element, uimField.getBinding()));
	}
	@Override
	protected CellEditor getCellEditor(Object element){
		return new CheckboxCellEditor();
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
}