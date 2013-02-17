package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToReserved<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_vNq_4KDMEeCv9IRqC7lfYw";

	/** Constructor for ActiveToReserved
	 * 
	 * @param regionActivation 
	 */
	public ActiveToReserved(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Active.ID,Reserved.ID);
		((Active)getSource()).setActiveToReserved(this);
	}

	public boolean consumeDelegateOccurrence(@ParameterMetaInfo(name="delegate",opaeumId=8205705053048523991l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ") IBusinessRole delegate) {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}

}