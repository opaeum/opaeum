package bpmmodel.mybusiness;

import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.TriggerMethod;


public enum PrepareQuoteImplState implements IProcessStep, IEnum {
	ACTIVITYFINALNODE1(null,"bpm.uml@_v6iqwJBYEeKoCK1-ZvrleA",6128872851993182546l,"Activity final node1",new TriggerMethod[]{}),
	CALL_CUSTOMER(null,"bpm.uml@_LZvx4JBZEeKoCK1-ZvrleA",8905861491530021382l,"Call customer",new TriggerMethod[]{});
	private String humanName;
	private long id;
	private IProcessStep parentState;
	private TriggerMethod[] triggerMethods;
	private String uuid;
	/** Constructor for PrepareQuoteImplState
	 * 
	 * @param parentState 
	 * @param uuid 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private PrepareQuoteImplState(IProcessStep parentState, String uuid, long id, String humanName, TriggerMethod[] triggerMethods) {
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
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public PrepareQuoteImplState resolveById(long id) {
		for ( PrepareQuoteImplState s : values() ) {
			if ( s.getId()==id ) {
				return s;
			}
		}
		return null;
	}

	@Override
	public long getOpaeumId(){
		// TODO Auto-generated method stub
		return 0;
	}

}