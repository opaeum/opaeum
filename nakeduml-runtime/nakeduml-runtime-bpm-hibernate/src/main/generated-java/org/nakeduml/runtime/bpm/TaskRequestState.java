package org.nakeduml.runtime.bpm;

import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TriggerMethod;

public enum TaskRequestState implements IProcessStep {
	INACTIVE(null,"18f2cf04_e89b_4428_b376_4d5b449be1a6",216l,"Inactive",new TriggerMethod[]{}),
	OBSOLETE(null,"OpiumBPM.library.uml@_renQAKDTEeCi16HgBnUGFw",816l,"Obsolete",new TriggerMethod[]{}),
	COMPLETED(null,"OpiumBPM.library.uml@_dAgD4IoaEeCPduia_-NbFw",823l,"Completed",new TriggerMethod[]{}),
	SUSPENDED(null,"OpiumBPM.library.uml@_eXmqMKCWEeCmJqvPP4zbUw",812l,"Suspended",new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")}),
	READYBUTSUSPENDED(SUSPENDED,"OpiumBPM.library.uml@_RC_JAIoaEeCPduia_-NbFw",815l,"Ready but suspended",new TriggerMethod[]{}),
	INPROGRESSBUTSUSPENDED(SUSPENDED,"OpiumBPM.library.uml@_tnUPsIobEeCPduia_-NbFw",813l,"In progress but suspended",new TriggerMethod[]{}),
	RESERVEDBUTSUSPENDED(SUSPENDED,"OpiumBPM.library.uml@_sFnzcIobEeCPduia_-NbFw",814l,"Reserved but suspended",new TriggerMethod[]{}),
	CREATED(null,"OpiumBPM.library.uml@_Qtk5sIoaEeCPduia_-NbFw",822l,"Created",new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")}),
	ACTIVE(null,"OpiumBPM.library.uml@_XwDj4KCVEeCmJqvPP4zbUw",817l,"Active",new TriggerMethod[]{new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"Delegated","Delegated"),new TriggerMethod(false,"On skip","OnSkip"),new TriggerMethod(false,"Forwarded","Forwarded")}),
	INPROGRESS(ACTIVE,"OpiumBPM.library.uml@_RMUEIIoaEeCPduia_-NbFw",821l,"In progress",new TriggerMethod[]{new TriggerMethod(false,"On complete","OnComplete"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Stopped","Stopped")}),
	NUMBEROFPOTENTIALOWNERS_(ACTIVE,"ff4d1190_32d0_4f72_bfea_c0fd84285293",206l,"NumberOfPotentialOwners ",new TriggerMethod[]{}),
	FINALSTATE1(ACTIVE,"OpiumBPM.library.uml@_YxQFsKCWEeCmJqvPP4zbUw",818l,"Final state1",new TriggerMethod[]{}),
	RESERVED(ACTIVE,"OpiumBPM.library.uml@_Q6NAcIoaEeCPduia_-NbFw",819l,"Reserved",new TriggerMethod[]{new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"Revoked","Revoked")}),
	HISTORY(ACTIVE,"d897a9fc_ca20_46c8_87e2_2ff90c4f0aef",208l,"History",new TriggerMethod[]{}),
	READY(ACTIVE,"OpiumBPM.library.uml@_Q0VB8IoaEeCPduia_-NbFw",820l,"Ready",new TriggerMethod[]{new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Claimed","Claimed")});
	private IProcessStep parentState;
	private String uuid;
	private long id;
	private String humanName;
	private TriggerMethod[] triggerMethods;
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