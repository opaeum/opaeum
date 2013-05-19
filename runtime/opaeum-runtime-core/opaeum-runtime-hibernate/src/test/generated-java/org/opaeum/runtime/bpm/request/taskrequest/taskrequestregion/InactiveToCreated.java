package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class InactiveToCreated<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_fSHrwIobEeCPduia_-NbFw";

	/** Constructor for InactiveToCreated
	 * 
	 * @param regionActivation 
	 */
	public InactiveToCreated(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Inactive.ID,Created.ID);
		((Inactive)getSource()).setInactiveToCreated(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onInactiveCompleted() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}