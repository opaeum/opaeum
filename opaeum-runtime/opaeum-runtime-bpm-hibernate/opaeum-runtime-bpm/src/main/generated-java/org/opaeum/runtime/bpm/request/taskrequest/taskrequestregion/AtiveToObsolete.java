package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class AtiveToObsolete extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_tEnkhKDTEeCi16HgBnUGFw";

	/** Constructor for AtiveToObsolete
	 * 
	 * @param regionActivation 
	 */
	public AtiveToObsolete(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_renQAKDTEeCi16HgBnUGFw");
		((Active)getSource()).setAtiveToObsolete(this);
	}

	public boolean consumeSkipOccurrence() {
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