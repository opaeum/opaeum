package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToReady<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_5xIXlKDMEeCv9IRqC7lfYw";

	/** Constructor for ActiveToReady
	 * 
	 * @param regionActivation 
	 */
	public ActiveToReady(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Active.ID,Ready.ID);
		((Active)getSource()).setActiveToReady(this);
	}

	public boolean consumeForwardOccurrence(@ParameterMetaInfo(name="toPerson",opaeumId=3350895467208403091l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ") IBusinessRole toPerson) {
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