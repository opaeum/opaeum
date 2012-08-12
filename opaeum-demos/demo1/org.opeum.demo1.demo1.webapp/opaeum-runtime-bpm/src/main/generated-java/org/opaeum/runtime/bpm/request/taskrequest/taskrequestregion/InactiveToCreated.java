package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InactiveToCreated extends TransitionInstance {


	/** Constructor for InactiveToCreated
	 * 
	 * @param regionActivation 
	 */
	public InactiveToCreated(RegionActivation regionActivation) {
	super("252060@_fSHrwIobEeCPduia_-NbFw",regionActivation,"252060@_PGwF0IoaEeCPduia_-NbFw","252060@_PGwF0IoaEeCPduia_-NbFw");
		((Inactive)getSource()).setInactiveToCreated(this);
	}

	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onInactiveCompleted() {
		boolean result = false;
		result=true;
		StateMachineToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}

}