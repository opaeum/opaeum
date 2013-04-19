package org.opaeum.metamodel.actions.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.uml2.uml.INakedAcceptCallAction;
import org.eclipse.uml2.uml.INakedInputPin;
import org.eclipse.uml2.uml.INakedOutputPin;
import org.eclipse.uml2.uml.INakedReplyAction;
import org.opaeum.metamodel.activities.internal.NakedActionImpl;

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
		replacePins(this.replyValues, replyValues);
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
