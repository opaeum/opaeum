package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToCompleted extends TransitionInstance {


	/** Constructor for ActiveToCompleted
	 * 
	 * @param regionActivation 
	 */
	public ActiveToCompleted(RegionActivation regionActivation) {
	super("252060@__sZ_0KCjEeCmJqvPP4zbUw",regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Active)getSource()).setActiveToCompleted(this);
	}

	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}
	
	public boolean onActiveCompleted() {
		boolean result = false;
		result=true;
		StateMachineToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}

}