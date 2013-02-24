package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1;

import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.Region1;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReservedToInProgress<SME extends TaskRequest> extends TransitionInstance<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_f0sNUIobEeCPduia_-NbFw";

	/** Constructor for ReservedToInProgress
	 * 
	 * @param regionActivation 
	 */
	public ReservedToInProgress(Region1 regionActivation) {
	super(ID,regionActivation,Reserved.ID,InProgress.ID);
		((Reserved)getSource()).setReservedToInProgress(this);
	}

	public boolean consumeStartOccurrence() {
		boolean result = false;
		result=true;
		TaskRequestToken<SME> token= getMainSource().exit();
		super.onEntry(token);
		IRequestObject tgtCallOperationAction1=((ITaskObject) getStateMachineExecution().getRequestObject());
		tgtCallOperationAction1.onStarted((IParticipant)org.opaeum.demo1.util.Demo1Environment.INSTANCE.getCurrentRole());
		getMainTarget().enter(token,target);
		super.onExit(token);
		return result;
	}
	
	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}

}