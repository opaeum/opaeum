package org.nakeduml.runtime.bpm;

import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TriggerMethod;

public enum TaskRequestState implements IProcessStep {
	CREATED(null,"69363e0d_de8a_4640_b088_f4a354c5523a",214l,"Created",new TriggerMethod[]{new TriggerMethod(false,"On activate","OnActivate")}),
	SUSPENDED(null,"6386fe81_a224_4e33_b553_d1b447ac6da2",209l,"Suspended",new TriggerMethod[]{new TriggerMethod(false,"Resumed","Resumed")}),
	READYBUTSUSPENDED(SUSPENDED,"0773f0ec_1a5a_44d7_918f_da1a02688529",213l,"Ready but suspended",new TriggerMethod[]{}),
	RESERVEDBUTSUSPENDED(SUSPENDED,"5c273710_bdeb_4aaf_aed6_1b9bb57f54ce",211l,"Reserved but suspended",new TriggerMethod[]{}),
	INPROGRESSBUTSUSPENDED(SUSPENDED,"320f8e0e_ef4e_4100_8474_d09cae9ec1a7",212l,"In progress but suspended",new TriggerMethod[]{}),
	OBSOLETE(null,"16796ec2_3b08_4442_b56d_86c12ae957ee",217l,"Obsolete",new TriggerMethod[]{}),
	INACTIVE(null,"18f2cf04_e89b_4428_b376_4d5b449be1a6",216l,"Inactive",new TriggerMethod[]{}),
	ACTIVE(null,"9e92c9fb_bb1e_4bb9_a522_d9dc02c90ac1",201l,"Active",new TriggerMethod[]{new TriggerMethod(false,"Delegated","Delegated"),new TriggerMethod(false,"Forwarded","Forwarded"),new TriggerMethod(false,"Started","Started"),new TriggerMethod(false,"On skip","OnSkip")}),
	INPROGRESS(ACTIVE,"3f7193d9_cc4e_4ead_b8d6_882b3d5aba90",203l,"In progress",new TriggerMethod[]{new TriggerMethod(false,"On complete","OnComplete"),new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Stopped","Stopped")}),
	RESERVED(ACTIVE,"02da48d2_5afb_45f5_826f_4df8e54de0e9",205l,"Reserved",new TriggerMethod[]{new TriggerMethod(false,"Suspended","Suspended"),new TriggerMethod(false,"Revoked","Revoked"),new TriggerMethod(false,"Started","Started")}),
	HISTORY(ACTIVE,"d897a9fc_ca20_46c8_87e2_2ff90c4f0aef",208l,"History",new TriggerMethod[]{}),
	READY(ACTIVE,"7f5b4c74_f141_4cb6_b69d_cd8db569db87",204l,"Ready",new TriggerMethod[]{new TriggerMethod(false,"Claimed","Claimed"),new TriggerMethod(false,"Suspended","Suspended")}),
	NUMBEROFPOTENTIALOWNERS_(ACTIVE,"ff4d1190_32d0_4f72_bfea_c0fd84285293",206l,"NumberOfPotentialOwners ",new TriggerMethod[]{}),
	FINALSTATE1(ACTIVE,"6f8fa6bc_10e0_4ed9_80ad_b6fdde51c343",207l,"Final state1",new TriggerMethod[]{}),
	COMPLETED(null,"52b357df_e4d0_49a6_9b0b_8b81a13332c1",215l,"Completed",new TriggerMethod[]{});
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