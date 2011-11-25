package org.opaeum.metamodel.actions.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.opaeum.metamodel.actions.INakedReadVariableAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;

public class NakedReadVariableActionImpl extends NakedVariableActionImpl implements INakedReadVariableAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2385774601503864044L;
	INakedOutputPin result;
	public Set<INakedInputPin> getInput(){
		return Collections.emptySet();
	}
	public Collection<INakedOutputPin> getOutput(){
		return Arrays.asList(this.getResult());
	}
	public INakedOutputPin getResult(){
		return this.result;
	}
	public void setResult(INakedOutputPin result){
		if(this.result != result){
			replacePin(this.result,result);
			this.result = result;
		}
	}
}
