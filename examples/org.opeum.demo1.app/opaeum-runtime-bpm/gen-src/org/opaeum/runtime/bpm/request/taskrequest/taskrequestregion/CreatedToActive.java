package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class CreatedToActive<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_feodwIobEeCPduia_-NbFw";

	/** Constructor for CreatedToActive
	 * 
	 * @param regionActivation 
	 */
	public CreatedToActive(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Created.ID,Active.ID);
		((Created)getSource()).setCreatedToActive(this);
	}

	public boolean consumeActivateOccurrence() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}

}