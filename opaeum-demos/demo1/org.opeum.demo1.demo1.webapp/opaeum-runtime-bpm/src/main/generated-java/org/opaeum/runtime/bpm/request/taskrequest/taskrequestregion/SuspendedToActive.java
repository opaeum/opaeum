package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class SuspendedToActive extends TransitionInstance {


	/** Constructor for SuspendedToActive
	 * 
	 * @param regionActivation 
	 */
	public SuspendedToActive(RegionActivation regionActivation) {
	super("252060@_vnrq4KCWEeCmJqvPP4zbUw",regionActivation,"252060@_eXmqMKCWEeCmJqvPP4zbUw","252060@_eXmqMKCWEeCmJqvPP4zbUw");
		((Suspended)getSource()).setSuspendedToActive(this);
	}

	public boolean consumeResumeOccurrence() {
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