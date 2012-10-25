package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToCompleted extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@__sZ_0KCjEeCmJqvPP4zbUw";

	/** Constructor for ActiveToCompleted
	 * 
	 * @param regionActivation 
	 */
	public ActiveToCompleted(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_dAgD4IoaEeCPduia_-NbFw");
		((Active)getSource()).setActiveToCompleted(this);
	}

	public TaskRequest getBehaviorExecution() {
		TaskRequest result = (TaskRequest)super.getBehaviorExecution();
		
		return result;
	}
	
	public boolean onActiveCompleted() {
		boolean result = false;
		result=true;
		TaskRequestToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}

}