package org.opaeum.metamodel.actions.internal;

import java.util.Collection;

import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;

public class NakedGenericActionImpl extends NakedActionImpl{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4911926111906947171L;
	private Collection<INakedInputPin> input;
	private Collection<INakedOutputPin> output;
	@Override
	public Collection<INakedInputPin> getInput(){
		return this.input;
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		return this.output;
	}
	public void setInput(Collection<INakedInputPin> input){
		this.input = input;
	}
	public void setOutput(Collection<INakedOutputPin> output){
		this.output = output;
	}
}
