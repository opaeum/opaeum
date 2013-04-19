package org.opaeum.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedRaiseExceptionAction;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;

public class NakedRaiseExceptionActionImpl extends NakedActionImpl implements INakedRaiseExceptionAction{
	private static final long serialVersionUID = 3691990455746398683L;
	private INakedInputPin exception;
	@Override
	public Collection<INakedInputPin> getInput(){
		ArrayList<INakedInputPin> input = new ArrayList<INakedInputPin>();
		if(exception != null){
			input.add(exception);
		}
		return input;
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		return new ArrayList<INakedOutputPin>();
	}
	@Override
	public void setException(INakedInputPin p){
		if(this.exception != p){
			replacePin(this.exception,p);
			this.exception = p;
		}
	}
	@Override
	public INakedInputPin getException(){
		return this.exception;
	}
}
