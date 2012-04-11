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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.rap.runtime.internal.editors.DefaultLabelProvider;
import org.opaeum.rap.runtime.widgets.ChooseDialog;
import org.opaeum.runtime.domain.IPersistentObject;

public class PopupSearchCellEditor extends DialogCellEditor{
	public PopupSearchCellEditor(Composite parent){
		super(parent);
	}
	protected Object[] getChoices(){
		return new Object[0];
	}
	protected ILabelProvider getLabelProvider(){
		return new DefaultLabelProvider();
	}
	protected ILabelProvider getAdvancedLabeProvider(){
		return new DefaultLabelProvider(){
			@Override
			public String getText(Object value){
				return value.getClass().getSimpleName() + ": " + super.getText(value);
			}
		};
	}
	@Override
	protected void updateContents(Object value){
		super.updateContents(value == null ? null : ((IPersistentObject) value).getName());
	}
	@Override
	protected Object openDialogBox(Control cellEditorWindow){
		ChooseDialog dialog = new ChooseDialog(cellEditorWindow.getShell(), getChoices());
		dialog.setLabelProvider(getLabelProvider());
		dialog.setAdvancedLabelProvider(getAdvancedLabeProvider());
		List<Object> selectedObjects = new ArrayList<Object>();
		selectedObjects.add(getValue());
		dialog.setInitialElementSelections(selectedObjects);
		if(dialog.open() == Window.OK){
			Object[] selection = dialog.getResult();
			if(selection != null && selection.length > 0){
				return selection[0];
			}
		}
		return null;
	}
}