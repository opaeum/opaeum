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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;

public abstract class ActivatingEditingSupport extends EditingSupport{
	private static final long serialVersionUID = 1L;
	public ActivatingEditingSupport(ColumnViewer viewer){
		super(viewer);
	}
	public abstract void onClick(Object element);
	@Override
	protected CellEditor getCellEditor(final Object element){
		return new ActivatingCellEditor(){
			@Override
			public void activate(){
				onClick(element);
			}
		};
	}
	@Override
	protected boolean canEdit(Object element){
		return true;
	}
	@Override
	protected Object getValue(Object element){
		return null;
	}
	@Override
	protected void setValue(Object element,Object value){
	}
}
