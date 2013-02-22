package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class AtiveToObsolete<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_tEnkhKDTEeCi16HgBnUGFw";

	/** Constructor for AtiveToObsolete
	 * 
	 * @param regionActivation 
	 */
	public AtiveToObsolete(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,Active.ID,Obsolete.ID);
		((Active)getSource()).setAtiveToObsolete(this);
	}

	public boolean consumeSkipOccurrence() {
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