package org.opaeum.demo1.structuredbusiness.branch.branch;

import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;


public enum PrepareQuoteState implements IProcessStep {
	DRAFT(null,"914890@_Jx4kYBYUEeKsDbmQL25eBw",2638769176053329929l,"Draft",new TriggerMethod[]{new TriggerMethod(false,"Transition2 call event","Transition2CallEvent")}),
	SUBMITTED(null,"914890@_KIj_ABYUEeKsDbmQL25eBw",8036718979494311659l,"Submitted",new TriggerMethod[]{}),
	APPROVED(null,"914890@_Kd2SgBYUEeKsDbmQL25eBw",6554627263207717015l,"Approved",new TriggerMethod[]{}),
	INITIAL(null,"914890@_MwyhUBYbEeKsDbmQL25eBw",3401036042186692275l,"Initial",new TriggerMethod[]{}),
	ESCALATED(null,"914890@_J_MA8HgGEeKNG8mFSp3Ijg",836851331648963919l,"Escalated",new TriggerMethod[]{});
	private String humanName;
	private long id;
	private IProcessStep parentState;
	private TriggerMethod[] triggerMethods;
	private String uuid;
	/** Constructor for PrepareQuoteState
	 * 
	 * @param parentState 
	 * @param uuid 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private PrepareQuoteState(IProcessStep parentState, String uuid, long id, String humanName, TriggerMethod[] triggerMethods) {
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
	
	public long getOpaeumId() {
		long result = getId();
		
		return result;
	}
	
	public IProcessStep getParentState() {
		return this.parentState;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		return this.triggerMethods;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public PrepareQuoteState resolveById(long id) {
		for ( PrepareQuoteState s : values() ) {
			if ( s.getId()==id ) {
				return s;
			}
		}
		return null;
	}

}