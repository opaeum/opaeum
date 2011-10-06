package org.opeum.runtime.bpm;

import org.opeum.runtime.domain.IProcessStep;
import org.opeum.runtime.domain.TriggerMethod;

public enum TaskRequestState implements IProcessStep {
	TASKINSTANCEREGION_INACTIVE(null,"252060@_PGwF0IoaEeCPduia_-NbFw",193l,"Inactive",new TriggerMethod[]{}),
	TASKINSTANCEREGION_CREATED(null,"252060@_Qtk5sIoaEeCPduia_-NbFw",199l,"Created",new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")}),
	TASKINSTANCEREGION_ACTIVE(null,"252060@_XwDj4KCVEeCmJqvPP4zbUw",201l,"Active",new TriggerMethod[]{new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"Delegated","Delegated"),new TriggerMethod(false,"Forwarded","Forwarded"),new TriggerMethod(false,"On skip","OnSkip")}),
	TASKINSTANCEREGION_ACTIVE_REGION1_READY(TASKINSTANCEREGION_ACTIVE,"252060@_Q0VB8IoaEeCPduia_-NbFw",203l,"Ready",new TriggerMethod[]{new TriggerMethod(false,"Claimed","Claimed"),new TriggerMethod(false,"Suspended","Suspended")}),
	TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS(TASKINSTANCEREGION_ACTIVE,"252060@_RMUEIIoaEeCPduia_-NbFw",205l,"In progress",new TriggerMethod[]{new TriggerMethod(false,"Stopped","Stopped"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"On complete","OnComplete")}),
	TASKINSTANCEREGION_ACTIVE_REGION1_NUMBEROFPOTENTIALOWNERS_(TASKINSTANCEREGION_ACTIVE,"252060@_t3M00KDPEeCv9IRqC7lfYw",207l,"NumberOfPotentialOwners ",new TriggerMethod[]{}),
	TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED(TASKINSTANCEREGION_ACTIVE,"252060@_Q6NAcIoaEeCPduia_-NbFw",208l,"Reserved",new TriggerMethod[]{new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Revoked","Revoked")}),
	TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1(TASKINSTANCEREGION_ACTIVE,"252060@_YxQFsKCWEeCmJqvPP4zbUw",204l,"Final state1",new TriggerMethod[]{}),
	TASKINSTANCEREGION_ACTIVE_REGION1_HISTORY(TASKINSTANCEREGION_ACTIVE,"252060@_UBMQAKCWEeCmJqvPP4zbUw",206l,"History",new TriggerMethod[]{}),
	TASKINSTANCEREGION_SUSPENDED(null,"252060@_eXmqMKCWEeCmJqvPP4zbUw",194l,"Suspended",new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")}),
	TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED(TASKINSTANCEREGION_SUSPENDED,"252060@_tnUPsIobEeCPduia_-NbFw",196l,"In progress but suspended",new TriggerMethod[]{}),
	TASKINSTANCEREGION_SUSPENDED_REGION1_RESERVEDBUTSUSPENDED(TASKINSTANCEREGION_SUSPENDED,"252060@_sFnzcIobEeCPduia_-NbFw",197l,"Reserved but suspended",new TriggerMethod[]{}),
	TASKINSTANCEREGION_SUSPENDED_REGION1_READYBUTSUSPENDED(TASKINSTANCEREGION_SUSPENDED,"252060@_RC_JAIoaEeCPduia_-NbFw",198l,"Ready but suspended",new TriggerMethod[]{}),
	TASKINSTANCEREGION_OBSOLETE(null,"252060@_renQAKDTEeCi16HgBnUGFw",200l,"Obsolete",new TriggerMethod[]{}),
	TASKINSTANCEREGION_COMPLETED(null,"252060@_dAgD4IoaEeCPduia_-NbFw",209l,"Completed",new TriggerMethod[]{});
	private long id;
	private TriggerMethod[] triggerMethods;
	private IProcessStep parentState;
	private String humanName;
	private String uuid;
	/** Constructor for TaskRequestState
	 * 
	 * @param parentState 
	 * @param uuid 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private TaskRequestState(IProcessStep parentState, String uuid, long id, String humanName, TriggerMethod[] triggerMethods) {
		this.parentState=parentState;
		this.uuid=uuid;
		this.id=id;
		this.humanName=humanName;
		this.triggerMethods=triggerMethods;
	}

	public String getHumanName() {
		return this.humanName;
	}
	
	public long getId() {
		return this.id;
	}
	
	public IProcessStep getParentState() {
		return this.parentState;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		return this.triggerMethods;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public TaskRequestState resolveById(long id) {
		for ( TaskRequestState s : values() ) {
			if ( s.getId()==id ) {
				return s;
			}
		}
		return null;
	}

}