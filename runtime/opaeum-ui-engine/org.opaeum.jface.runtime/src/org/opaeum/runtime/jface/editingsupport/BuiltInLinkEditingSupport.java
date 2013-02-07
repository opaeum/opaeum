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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.action.BuiltInLinkKind;

public class BuiltInLinkEditingSupport extends EditingSupport{
	private final CheckboxTableViewer tableViewer;
	private final BuiltInLinkKind kind;
	private OpaeumRapSession session;
	public BuiltInLinkEditingSupport(ColumnViewer viewer,CheckboxTableViewer tableViewer,BuiltInLinkKind kind, OpaeumRapSession session){
		super(viewer);
		this.tableViewer = tableViewer;
		this.kind = kind;
		this.session=session;
	}
	@Override
	protected void setValue(Object element,Object value){
	}
	@Override
	protected Object getValue(Object element){
		return "";
	}
	@Override
	protected CellEditor getCellEditor(final Object element){
		return new BuiltInLinkCellEditor(tableViewer.getTable(), kind, element,session);
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
}