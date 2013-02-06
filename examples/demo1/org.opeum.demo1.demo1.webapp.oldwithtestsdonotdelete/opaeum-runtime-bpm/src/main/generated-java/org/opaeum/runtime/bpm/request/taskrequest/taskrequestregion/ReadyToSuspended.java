package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class ReadyToSuspended extends TransitionInstance {


	/** Constructor for ReadyToSuspended
	 * 
	 * @param regionActivation 
	 */
	public ReadyToSuspended(RegionActivation regionActivation) {
	super("252060@_qzQzsIobEeCPduia_-NbFw",regionActivation,"252060@_Q0VB8IoaEeCPduia_-NbFw","252060@_Q0VB8IoaEeCPduia_-NbFw");
		((Ready)getSource()).setReadyToSuspended(this);
	}

	public boolean consumeSuspendOccurrence() {
		boolean result = false;
		result=true;
		StateMachineToken token= getMainSource().exit();
		getMainTarget().enter(token,target);
		return result;
	}
	
	public TaskRequest getStateMachineExecution() {
		TaskRequest result = (TaskRequest)super.getStateMachineExecution();
		
		return result;
	}

}