package org.opaeum.runtime.bpm;

import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;

public enum ProcessRequestState implements IProcessStep {
;
	private long id;
	private TriggerMethod[] triggerMethods;
	private IProcessStep parentState;
	private String humanName;
	private String uuid;
	/** Constructor for ProcessRequestState
	 * 
	 * @param parentState 
	 * @param uuid 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private ProcessRequestState(IProcessStep parentState, String uuid, long id, String humanName, TriggerMethod[] triggerMethods) {
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
	
	static public ProcessRequestState resolveById(long id) {
		for ( ProcessRequestState s : values() ) {
			if ( s.getId()==id ) {
				return s;
			}
		}
		return null;
	}

}