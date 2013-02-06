package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToReady extends TransitionInstance {


	/** Constructor for ActiveToReady
	 * 
	 * @param regionActivation 
	 */
	public ActiveToReady(RegionActivation regionActivation) {
	super("252060@_5xIXlKDMEeCv9IRqC7lfYw",regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Active)getSource()).setActiveToReady(this);
	}

	public boolean consumeForwardOccurrence(@ParameterMetaInfo(name="toPerson",opaeumId=3350895467208403091l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ") IBusinessRole toPerson) {
		boolean result = false;
		result=true;
		StateMachineToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}

}