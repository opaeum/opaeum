package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class AtiveToObsolete extends TransitionInstance {


	/** Constructor for AtiveToObsolete
	 * 
	 * @param regionActivation 
	 */
	public AtiveToObsolete(RegionActivation regionActivation) {
	super("252060@_tEnkhKDTEeCi16HgBnUGFw",regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Active)getSource()).setAtiveToObsolete(this);
	}

	public boolean consumeSkipOccurrence() {
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