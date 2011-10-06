package org.opaeum.metamodel.actions.internal;

import org.opaeum.metamodel.actions.INakedAddVariableValueAction;

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
