package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.metamodel.actions.INakedOclAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedOclActionImpl extends NakedOpaqueActionImpl implements INakedOclAction{

	private INakedOutputPin returnPin;
	private IOclContext bodyExpression;
	public IOclContext getBodyExpression() {
		return bodyExpression;
	}

	public void setBodyExpression(IOclContext bodyExpression) {
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
		this.returnPin = returnPin;
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
