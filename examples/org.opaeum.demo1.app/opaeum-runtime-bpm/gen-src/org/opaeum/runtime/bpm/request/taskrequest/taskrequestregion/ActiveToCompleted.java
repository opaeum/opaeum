package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToCompleted<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@__sZ_0KCjEeCmJqvPP4zbUw";

	/** Constructor for ActiveToCompleted
	 * 
	 * @param regionActivation 
	 */
	public ActiveToCompleted(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Active.ID,Completed.ID);
		((Active)getSource()).setActiveToCompleted(this);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onActiveCompleted() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}

}