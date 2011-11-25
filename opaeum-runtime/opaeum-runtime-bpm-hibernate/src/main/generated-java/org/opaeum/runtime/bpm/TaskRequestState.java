package org.opaeum.runtime.bpm;

import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;

public enum TaskRequestState implements IProcessStep {
	COMPLETED(null,"252060@_dAgD4IoaEeCPduia_-NbFw",511l,"Completed",new TriggerMethod[]{}),
	INACTIVE(null,"252060@_PGwF0IoaEeCPduia_-NbFw",505l,"Inactive",new TriggerMethod[]{}),
	OBSOLETE(null,"252060@_renQAKDTEeCi16HgBnUGFw",503l,"Obsolete",new TriggerMethod[]{}),
	SUSPENDED(null,"252060@_eXmqMKCWEeCmJqvPP4zbUw",506l,"Suspended",new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")}),
	SUSPENDED_READYBUTSUSPENDED(SUSPENDED,"252060@_RC_JAIoaEeCPduia_-NbFw",508l,"Ready but suspended",new TriggerMethod[]{}),
	SUSPENDED_RESERVEDBUTSUSPENDED(SUSPENDED,"252060@_sFnzcIobEeCPduia_-NbFw",509l,"Reserved but suspended",new TriggerMethod[]{}),
	SUSPENDED_INPROGRESSBUTSUSPENDED(SUSPENDED,"252060@_tnUPsIobEeCPduia_-NbFw",510l,"In progress but suspended",new TriggerMethod[]{}),
	ACTIVE(null,"252060@_XwDj4KCVEeCmJqvPP4zbUw",495l,"Active",new TriggerMethod[]{new TriggerMethod(false,"Forwarded","Forwarded"),new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"On skip","OnSkip"),new TriggerMethod(false,"Delegated","Delegated")}),
	ACTIVE_INPROGRESS(ACTIVE,"252060@_RMUEIIoaEeCPduia_-NbFw",500l,"In progress",new TriggerMethod[]{new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Stopped","Stopped"),new TriggerMethod(false,"On complete","OnComplete")}),
	ACTIVE_NUMBEROFPOTENTIALOWNERS_(ACTIVE,"252060@_t3M00KDPEeCv9IRqC7lfYw",502l,"NumberOfPotentialOwners ",new TriggerMethod[]{}),
	ACTIVE_FINALSTATE1(ACTIVE,"252060@_YxQFsKCWEeCmJqvPP4zbUw",498l,"Final state1",new TriggerMethod[]{}),
	ACTIVE_READY(ACTIVE,"252060@_Q0VB8IoaEeCPduia_-NbFw",501l,"Ready",new TriggerMethod[]{new TriggerMethod(false,"Claimed","Claimed"),new TriggerMethod(false,"Suspended","Suspended")}),
	ACTIVE_HISTORY(ACTIVE,"252060@_UBMQAKCWEeCmJqvPP4zbUw",497l,"History",new TriggerMethod[]{}),
	ACTIVE_RESERVED(ACTIVE,"252060@_Q6NAcIoaEeCPduia_-NbFw",499l,"Reserved",new TriggerMethod[]{new TriggerMethod(false,"Revoked","Revoked"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Started","Started")}),
	CREATED(null,"252060@_Qtk5sIoaEeCPduia_-NbFw",504l,"Created",new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")});
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