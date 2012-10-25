package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InactiveToCreated extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_fSHrwIobEeCPduia_-NbFw";

	/** Constructor for InactiveToCreated
	 * 
	 * @param regionActivation 
	 */
	public InactiveToCreated(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_PGwF0IoaEeCPduia_-NbFw","252060@_Qtk5sIoaEeCPduia_-NbFw");
		((Inactive)getSource()).setInactiveToCreated(this);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onInactiveCompleted() {
		boolean result = false;
		result=true;
		TaskRequestToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}

}