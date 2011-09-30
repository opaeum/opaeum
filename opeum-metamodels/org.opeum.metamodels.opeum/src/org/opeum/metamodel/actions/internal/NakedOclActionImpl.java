package org.opeum.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;

import nl.klasse.octopus.oclengine.IOclContext;

import org.opeum.metamodel.actions.INakedOclAction;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.INakedValueSpecification;
import org.opeum.metamodel.core.internal.NakedValueSpecificationImpl;

public class NakedOclActionImpl extends NakedOpaqueActionImpl implements INakedOclAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1463905559300356236L;
	private INakedOutputPin returnPin;
	private IOclContext bodyExpression;
	public IOclContext getBodyExpression(){
		return bodyExpression;
	}
	public void setBodyExpression(IOclContext bodyExpression){
		this.bodyExpression = bodyExpression;
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		Collection<INakedOutputPin> result = new ArrayList<INakedOutputPin>();
		if(getReturnPin() != null){
			result.add(getReturnPin());
		}
		return result;
	}
	@Override
	public INakedOutputPin getReturnPin(){
		return this.returnPin;
	}
	public void setReturnPin(INakedOutputPin returnPin){
		if(this.returnPin != returnPin){
			removeOwnedElement(this.returnPin, true);
			this.returnPin = returnPin;
		}
	}
	@Override
	public INakedValueSpecification getBody(){
		if(getBodyExpression() == null){
			return null;
		}else{
			return new NakedValueSpecificationImpl(getBodyExpression());
		}
	}
}
