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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.opaeum.runtime.jface.actions.OpenEditorAction;
import org.opaeum.runtime.rwt.OpaeumRapSession;
import org.opaeum.uim.action.BuiltInLinkKind;

public class BuiltInLinkCellEditor extends CellEditor{
	private final BuiltInLinkKind kind;
	private final Object element;
	private OpaeumRapSession session;
	BuiltInLinkCellEditor(Composite parent,BuiltInLinkKind kind,Object element, OpaeumRapSession session){
		super(parent);
		this.session=session;
		this.kind = kind;
		this.element = element;
	}
	@Override
	public void activate(){
		switch(kind){
		case EDIT:
		case VIEW:
			OpenEditorAction.openEntityEditor(element, true, session);
			break;
		}
	}
	@Override
	protected Control createControl(Composite parent){
		return null;
	}
	@Override
	protected Object doGetValue(){
		return null;
	}
	@Override
	protected void doSetFocus(){
	}
	@Override
	protected void doSetValue(Object value){
	}
}