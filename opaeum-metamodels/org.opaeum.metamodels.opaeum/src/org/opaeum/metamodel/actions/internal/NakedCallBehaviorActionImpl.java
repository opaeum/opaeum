package org.opaeum.metamodel.actions.internal;

import org.opaeum.metamodel.actions.CallBehaviorMessageStructure;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.IParameterOwner;

public class NakedCallBehaviorActionImpl extends NakedCallActionImpl implements INakedCallBehaviorAction{
	private static final long serialVersionUID = -236758123440467617L;
	private INakedBehavior behavior;
	private CallBehaviorMessageStructure messageStructure;
	public INakedBehavior getBehavior(){
		return this.behavior;
	}
	public void setBehavior(INakedBehavior operation){
		this.behavior = operation;
	}
	public IParameterOwner getCalledElement(){
		return getBehavior();
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		if(getBehavior().getContext() == null){
			return messageStructure;
		}else{
			return getBehavior();
		}
	}
	@Override
	public void initMessageStructure(){
		if(getBehavior().getContext() == null){
			if(this.messageStructure == null){
				this.messageStructure = new CallBehaviorMessageStructure(this);
			}
		}
	}
	@Override
	public boolean isLongRunning(){
		return getBehavior().isProcess();
	}
}
