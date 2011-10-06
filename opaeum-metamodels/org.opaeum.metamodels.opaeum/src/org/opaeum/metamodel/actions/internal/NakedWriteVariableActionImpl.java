package org.opaeum.metamodel.actions.internal;

import java.util.Collections;
import java.util.Set;

import org.opaeum.metamodel.actions.INakedWriteVariableAction;
import org.opaeum.metamodel.activities.INakedInputPin;

public abstract class NakedWriteVariableActionImpl extends NakedVariableActionImpl implements INakedWriteVariableAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5075905704631284087L;
	INakedInputPin value;
	private INakedInputPin insertAt;
	public Set<INakedInputPin> getInput(){
		if(getValue() == null){
			return Collections.emptySet();
		}else{
			return Collections.singleton(getValue());
		}
	}
	public INakedInputPin getValue(){
		return this.value;
	}
	public void setValue(INakedInputPin value){
		if(this.value != value){
			removeOwnedElement(this.value, true);
			this.value = value;
		}
	}
	public INakedInputPin getInsertAt(){
		return insertAt;
	}
	public void setInsertAt(INakedInputPin insertAt){
		this.insertAt = insertAt;
	}
}
