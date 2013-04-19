package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion;

import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.statemachines.TransitionInstance;

public class CreatedToActive extends TransitionInstance {


	/** Constructor for CreatedToActive
	 * 
	 * @param regionActivation 
	 */
	public CreatedToActive(RegionActivation regionActivation) {
	super("252060@_feodwIobEeCPduia_-NbFw",regionActivation,"252060@_Qtk5sIoaEeCPduia_-NbFw","252060@_Qtk5sIoaEeCPduia_-NbFw");
		((Created)getSource()).setCreatedToActive(this);
	}

	public boolean consumeActivateOccurrence() {
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