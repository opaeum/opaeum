package org.opaeum.demo1.structuredbusiness.appliancedoctor;

import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;


public enum BusinessStateMachine1State implements IProcessStep, IEnum {
	STATE0(null,"914890@_eLtBsH2lEeK5F45wEGRv4A",7018546131653328789l,"State0",new TriggerMethod[]{}),
	STATE1(null,"914890@_ejX60H2lEeK5F45wEGRv4A",8578110679676129975l,"State1",new TriggerMethod[]{});
	private String humanName;
	private long id;
	private IProcessStep parentState;
	private TriggerMethod[] triggerMethods;
	private String uuid;
	/** Constructor for BusinessStateMachine1State
	 * 
	 * @param parentState 
	 * @param uuid 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private BusinessStateMachine1State(IProcessStep parentState, String uuid, long id, String humanName, TriggerMethod[] triggerMethods) {
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
	
	static public BusinessStateMachine1State resolveById(long id) {
		for ( BusinessStateMachine1State s : values() ) {
			if ( s.getId()==id ) {
				return s;
			}
		}
		return null;
	}

}