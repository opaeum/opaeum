package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToReserved extends TransitionInstance {


	/** Constructor for ActiveToReserved
	 * 
	 * @param regionActivation 
	 */
	public ActiveToReserved(RegionActivation regionActivation) {
	super("252060@_vNq_4KDMEeCv9IRqC7lfYw",regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Active)getSource()).setActiveToReserved(this);
	}

	public boolean consumeDelegateOccurrence(@ParameterMetaInfo(name="delegate",opaeumId=8205705053048523991l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ") IBusinessRole delegate) {
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