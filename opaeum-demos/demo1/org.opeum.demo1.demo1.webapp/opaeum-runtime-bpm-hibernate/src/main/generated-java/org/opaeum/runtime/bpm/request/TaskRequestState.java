package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;

public enum TaskRequestState implements IProcessStep {
	INACTIVE(null,"252060@_PGwF0IoaEeCPduia_-NbFw",3168683303891366381l,"Inactive",new TriggerMethod[]{}),
	CREATED(null,"252060@_Qtk5sIoaEeCPduia_-NbFw",5036147100148054299l,"Created",new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")}),
	ACTIVE(null,"252060@_XwDj4KCVEeCmJqvPP4zbUw",8186560320517253471l,"Active",new TriggerMethod[]{new TriggerMethod(false,"Forwarded","Forwarded"),new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"On skip","OnSkip"),new TriggerMethod(false,"Delegated","Delegated")}),
	ACTIVE_READY(ACTIVE,"252060@_Q0VB8IoaEeCPduia_-NbFw",6900666214990186221l,"Ready",new TriggerMethod[]{new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Claimed","Claimed")}),
	ACTIVE_RESERVED(ACTIVE,"252060@_Q6NAcIoaEeCPduia_-NbFw",4930734197145594991l,"Reserved",new TriggerMethod[]{new TriggerMethod(false,"Revoked","Revoked"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Started","Started")}),
	ACTIVE_INPROGRESS(ACTIVE,"252060@_RMUEIIoaEeCPduia_-NbFw",6170327107602400395l,"In progress",new TriggerMethod[]{new TriggerMethod(false,"Stopped","Stopped"),new TriggerMethod(false,"On complete","OnComplete"),new TriggerMethod(false,"Suspended","Suspended")}),
	ACTIVE_HISTORY(ACTIVE,"252060@_UBMQAKCWEeCmJqvPP4zbUw",3831728107403670847l,"History",new TriggerMethod[]{}),
	ACTIVE_FINALACTIVESTATE(ACTIVE,"252060@_YxQFsKCWEeCmJqvPP4zbUw",5077142398307196385l,"Final active state",new TriggerMethod[]{}),
	ACTIVE_NUMBEROFPOTENTIALOWNERS_(ACTIVE,"252060@_t3M00KDPEeCv9IRqC7lfYw",4333852207725524968l,"NumberOfPotentialOwners ",new TriggerMethod[]{}),
	COMPLETED(null,"252060@_dAgD4IoaEeCPduia_-NbFw",3262741496760643789l,"Completed",new TriggerMethod[]{}),
	SUSPENDED(null,"252060@_eXmqMKCWEeCmJqvPP4zbUw",5887023903477310989l,"Suspended",new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")}),
	SUSPENDED_READYBUTSUSPENDED(SUSPENDED,"252060@_RC_JAIoaEeCPduia_-NbFw",3934459648279920547l,"Ready but suspended",new TriggerMethod[]{}),
	SUSPENDED_RESERVEDBUTSUSPENDED(SUSPENDED,"252060@_sFnzcIobEeCPduia_-NbFw",2581032990836164737l,"Reserved but suspended",new TriggerMethod[]{}),
	SUSPENDED_INPROGRESSBUTSUSPENDED(SUSPENDED,"252060@_tnUPsIobEeCPduia_-NbFw",9048647638684503117l,"In progress but suspended",new TriggerMethod[]{}),
	OBSOLETE(null,"252060@_renQAKDTEeCi16HgBnUGFw",3060939886108721567l,"Obsolete",new TriggerMethod[]{});
	private String humanName;
	private long id;
	private IProcessStep parentState;
	private TriggerMethod[] triggerMethods;
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