package org.opaeum.metamodel.actions.internal;

import org.eclipse.uml2.uml.INakedAddVariableValueAction;

public class NakedAddVariableValueActionImpl extends NakedWriteVariableActionImpl implements INakedAddVariableValueAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3441814396538983768L;
	private boolean replaceAll;
	@Override
	public boolean isReplaceAll(){
		return replaceAll;
	}
	@Override
	public void setReplaceAll(boolean b){
		this.replaceAll = b;
	}
}
