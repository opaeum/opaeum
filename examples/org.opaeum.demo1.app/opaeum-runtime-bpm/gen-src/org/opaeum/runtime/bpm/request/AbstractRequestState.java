package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;


public enum AbstractRequestState implements IProcessStep, IEnum {
	INITIALNODE(null,"252060@_JKNVEPNREeGhGoYzbfvy4A",3957967350396842218l,"Initial node",new TriggerMethod[]{}),
	ACTIVE(null,"252060@_KHZWEPNREeGhGoYzbfvy4A",2621009303440568440l,"Active",new TriggerMethod[]{}),
	SUSPENDED(null,"252060@_O2wBIPNREeGhGoYzbfvy4A",463934878768429112l,"Suspended",new TriggerMethod[]{}),
	CREATED(null,"252060@_TfNaAPNREeGhGoYzbfvy4A",7850806052753459670l,"Created",new TriggerMethod[]{}),
	COMPLETE(null,"252060@_U2MbEPNREeGhGoYzbfvy4A",3509965088053427528l,"Complete",new TriggerMethod[]{});
	private String humanName;
	private long id;
	private IProcessStep parentState;
	private TriggerMethod[] triggerMethods;
	private String uuid;
	/** Constructor for AbstractRequestState
	 * 
	 * @param parentState 
	 * @param uuid 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private AbstractRequestState(IProcessStep parentState, String uuid, long id, String humanName, TriggerMethod[] triggerMethods) {
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
	
	static public AbstractRequestState resolveById(long id) {
		for ( AbstractRequestState s : values() ) {
			if ( s.getId()==id ) {
				return s;
			}
		}
		return null;
	}

}