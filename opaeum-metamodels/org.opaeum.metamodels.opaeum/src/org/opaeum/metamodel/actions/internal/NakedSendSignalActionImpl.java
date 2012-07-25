package org.opaeum.metamodel.actions.internal;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.uml2.uml.INakedInstanceSpecification;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedSendSignalAction;
import org.eclipse.uml2.uml.INakedSignal;
import org.eclipse.uml2.uml.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.TagNames;

public class NakedSendSignalActionImpl extends NakedInvocationActionImpl implements INakedSendSignalAction{
	private static final long serialVersionUID = 3809690763786259025L;
	private INakedSignal signal;
	private INakedValueSpecification fromExpression;
	private INakedValueSpecification bccExpression;
	private INakedValueSpecification ccExpression;
	public void setSignal(INakedSignal signal){
		this.signal = signal;
	}
	public INakedSignal getSignal(){
		return this.signal;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		this.fromExpression=getDestinationExpression(stereotype, TagNames.FROM_EXPRESSION);
		this.ccExpression=getDestinationExpression(stereotype, TagNames.CC_EXPRESSION);
		this.bccExpression=getDestinationExpression(stereotype, TagNames.BCC_EXPRESSION);
	}
	protected INakedValueSpecification getDestinationExpression(INakedInstanceSpecification stereotype,String fromExpression2){
		INakedValueSpecification fromExpression=null;
		if(stereotype.hasValueForFeature(fromExpression2)){
			fromExpression=(INakedValueSpecification) stereotype.getFirstValueFor(fromExpression2).getValue();
		}
		return fromExpression;
	}
	public Collection<INakedOutputPin> getOutput(){
		return new HashSet<INakedOutputPin>();
	}
	public INakedValueSpecification getFromExpression(){
		return fromExpression;
	}
	public INakedValueSpecification getBccExpression(){
		return bccExpression;
	}
	public INakedValueSpecification getCcExpression(){
		return ccExpression;
	}
}
