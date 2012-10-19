package structuredbusiness.branch;

import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;

public enum BusinessStateMachine1State implements IProcessStep {
	DRAFT(null,"914890@_Jx4kYBYUEeKsDbmQL25eBw",2638769176053329929l,"Draft",new TriggerMethod[]{}),
	STATE1(null,"914890@_KIj_ABYUEeKsDbmQL25eBw",8036718979494311659l,"State1",new TriggerMethod[]{}),
	APPROVED(null,"914890@_Kd2SgBYUEeKsDbmQL25eBw",6554627263207717015l,"Approved",new TriggerMethod[]{}),
	INITIAL(null,"914890@_MwyhUBYbEeKsDbmQL25eBw",3401036042186692275l,"Initial",new TriggerMethod[]{});
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