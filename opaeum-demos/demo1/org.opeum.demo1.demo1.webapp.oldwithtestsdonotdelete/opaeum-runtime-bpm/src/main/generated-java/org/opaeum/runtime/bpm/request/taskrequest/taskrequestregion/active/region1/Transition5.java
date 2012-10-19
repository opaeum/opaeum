package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class Transition5 extends TransitionInstance {


	/** Constructor for Transition5
	 * 
	 * @param regionActivation 
	 */
	public Transition5(RegionActivation regionActivation) {
	super("252060@_gC3MMIobEeCPduia_-NbFw",regionActivation,"252060@_RMUEIIoaEeCPduia_-NbFw","252060@_RMUEIIoaEeCPduia_-NbFw");
		((InProgress)getSource()).setTransition5(this);
	}

	public boolean consumeCompleteOccurrence() {
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