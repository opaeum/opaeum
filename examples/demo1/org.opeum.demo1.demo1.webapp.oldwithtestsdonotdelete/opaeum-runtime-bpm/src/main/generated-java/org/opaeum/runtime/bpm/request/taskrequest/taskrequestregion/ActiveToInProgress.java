package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToInProgress extends TransitionInstance {


	/** Constructor for ActiveToInProgress
	 * 
	 * @param regionActivation 
	 */
	public ActiveToInProgress(RegionActivation regionActivation) {
	super("252060@_JhMXAKDNEeCv9IRqC7lfYw",regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Active)getSource()).setActiveToInProgress(this);
	}

	public boolean consumeStartOccurrence() {
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