package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class Transition5 extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_gC3MMIobEeCPduia_-NbFw";

	/** Constructor for Transition5
	 * 
	 * @param regionActivation 
	 */
	public Transition5(Region1 regionActivation) {
	super(ID,regionActivation,"252060@_RMUEIIoaEeCPduia_-NbFw","252060@_YxQFsKCWEeCmJqvPP4zbUw");
		((InProgress)getSource()).setTransition5(this);
	}

	public boolean consumeCompleteOccurrence() {
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