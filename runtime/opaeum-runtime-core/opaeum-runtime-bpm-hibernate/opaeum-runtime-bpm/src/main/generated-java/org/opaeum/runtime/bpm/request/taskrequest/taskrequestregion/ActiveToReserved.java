package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ActiveToReserved extends TransitionInstance<TaskRequest, TaskRequestToken> {
	static public String ID = "252060@_vNq_4KDMEeCv9IRqC7lfYw";

	/** Constructor for ActiveToReserved
	 * 
	 * @param regionActivation 
	 */
	public ActiveToReserved(TaskRequestRegion regionActivation) {
	super(ID,regionActivation,"252060@_XwDj4KCVEeCmJqvPP4zbUw","252060@_Q6NAcIoaEeCPduia_-NbFw");
		((Active)getSource()).setActiveToReserved(this);
	}

	public boolean consumeDelegateOccurrence(@ParameterMetaInfo(name="delegate",opaeumId=8205705053048523991l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ") IBusinessRole delegate) {
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