package org.opaeum.metamodel.actions.internal;

import java.util.Collection;

import org.eclipse.uml2.uml.INakedAcceptCallAction;
import org.eclipse.uml2.uml.INakedCallEvent;
import org.eclipse.uml2.uml.INakedElement;
import org.eclipse.uml2.uml.INakedObjectNode;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedReplyAction;

public class NakedAcceptCallActionImpl extends NakedAcceptEventActionImpl implements INakedAcceptCallAction{
	private static final long serialVersionUID = 1472278565796238451L;
	@Override
	public Collection<INakedElement> getOwnedElements(){
		return super.getOwnedElements();
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		Collection<INakedOutputPin> result = super.getOutput();
		if(getReturnInfo() != null){
			result.add(getReturnInfo());
		}
		return result;
	}
	INakedOutputPin returnInfo;
	@Override
	public INakedOutputPin getReturnInfo(){
		return returnInfo;
	}
	public void setReturnInfo(INakedOutputPin r){
		if(this.returnInfo != r){
			replacePin(this.returnInfo, r);
			this.returnInfo = r;
		}
	}
	@Override
	public INakedOperation getOperation(){
		if(super.getTriggers().isEmpty()){
			return null;
		}else{
			final INakedCallEvent event = (INakedCallEvent) super.getTriggers().iterator().next().getEvent();
			return (INakedOperation) event.getOperation();
		}
	}
	@Override
	public INakedReplyAction getReplyAction(){
		INakedOutputPin returnInfo2 = getReturnInfo();
		if(returnInfo2 == null){
			return null;
		}else{
			INakedObjectNode feedingNode = returnInfo2.getFedNode();
			return (INakedReplyAction) feedingNode.getOwnerElement();
		}
	}
}
