package net.sf.nakeduml.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.actions.ActionType;
import net.sf.nakeduml.metamodel.actions.INakedAcceptCallAction;
import net.sf.nakeduml.metamodel.actions.INakedReplyAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.activities.internal.NakedActionImpl;
import net.sf.nakeduml.metamodel.core.INakedElement;

public class NakedReplyActionImpl extends NakedActionImpl implements INakedReplyAction{
	private INakedInputPin returnInfo;
	private List<INakedInputPin> replyValues;
	public List<INakedInputPin> getReplyValues(){
		return replyValues;
	}
	public void setReplyValues(List<INakedInputPin> replyValues){
		this.replyValues = replyValues;
	}
	public void setReturnInfo(INakedInputPin returnInfo){
		this.returnInfo = returnInfo;
	}
	@Override
	public ActionType getActionType(){
		return ActionType.REPLY_ACTION;
	}
	@Override
	public Collection<INakedInputPin> getInput(){
		List<INakedInputPin> result = new ArrayList<INakedInputPin>(getReplyValues());
		if(returnInfo != null){
			result.add(getReturnInfo());
		}
		return result;
	}
	@Override
	public Collection<INakedOutputPin> getOutput(){
		return Collections.emptySet();
	}
	@Override
	public INakedInputPin getReturnInfo(){
		return this.returnInfo;
	}
	@Override
	public INakedAcceptCallAction getCause(){
		return (INakedAcceptCallAction) this.returnInfo.getFeedingNode().getOwnerElement();
	}
}
