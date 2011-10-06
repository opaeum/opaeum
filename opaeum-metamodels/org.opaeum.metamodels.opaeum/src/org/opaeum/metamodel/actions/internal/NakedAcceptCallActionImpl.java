package org.opaeum.metamodel.actions.internal;

import java.util.Collection;

import org.opaeum.metamodel.actions.INakedAcceptCallAction;
import org.opaeum.metamodel.actions.INakedReplyAction;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.commonbehaviors.INakedCallEvent;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedOperation;

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
			removeOwnedElement(this.returnInfo, true);
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
