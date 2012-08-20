package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToReady extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_5xIXlKDMEeCv9IRqC7lfYw";

	/** Constructor for ActiveToReady
	 * 
	 * @param regionActivation 
	 */
	public ActiveToReady(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_Q0VB8IoaEeCPduia_-NbFw");
		((Active)getSource()).setActiveToReady(this);
	}

	public boolean consumeForwardOccurrence(@ParameterMetaInfo(name="toPerson",opaeumId=3350895467208403091l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ") IBusinessRole toPerson) {
		boolean result = false;
		result=true;
		TaskRequestToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}

}