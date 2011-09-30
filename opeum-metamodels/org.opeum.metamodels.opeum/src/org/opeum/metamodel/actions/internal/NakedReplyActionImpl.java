package org.opeum.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.opeum.metamodel.actions.INakedAcceptCallAction;
import org.opeum.metamodel.actions.INakedReplyAction;
import org.opeum.metamodel.activities.INakedInputPin;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.activities.internal.NakedActionImpl;

public class NakedReplyActionImpl extends NakedActionImpl implements INakedReplyAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8009311922922903715L;
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
