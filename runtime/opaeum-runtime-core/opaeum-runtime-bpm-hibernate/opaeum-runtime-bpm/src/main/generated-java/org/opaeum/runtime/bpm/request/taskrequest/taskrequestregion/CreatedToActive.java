package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class CreatedToActive extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_feodwIobEeCPduia_-NbFw";

	/** Constructor for CreatedToActive
	 * 
	 * @param regionActivation 
	 */
	public CreatedToActive(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_Qtk5sIoaEeCPduia_-NbFw","252060@_XwDj4KCVEeCmJqvPP4zbUw");
		((Created)getSource()).setCreatedToActive(this);
	}

	public boolean consumeActivateOccurrence() {
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