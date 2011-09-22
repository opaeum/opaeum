package net.sf.nakeduml.metamodel.actions.internal;

import java.util.Collection;

import net.sf.nakeduml.metamodel.actions.INakedAcceptCallAction;
import net.sf.nakeduml.metamodel.actions.INakedReplyAction;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedCallEvent;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedEvent;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;

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
		return (INakedReplyAction) getReturnInfo().getFeedingNode().getOwnerElement();
	}
}
