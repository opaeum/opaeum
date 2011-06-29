package nakedumllibraryforbpm;

import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TriggerMethod;

public enum TaskRequestState implements IProcessStep {
	CLOSED(null,"closed",112l,"Closed",new TriggerMethod[]{}),
	RESERVEDBUTSUSPENDED(null,"reserved_but_suspended",107l,"Reserved but suspended",new TriggerMethod[]{}),
	CREATED(null,"created",104l,"Created",new TriggerMethod[]{}),
	RESERVED(null,"reserved",105l,"Reserved",new TriggerMethod[]{}),
	INPROGRESSBUTSUSPENDED(null,"in_progress_but_suspended",113l,"In progress but suspended",new TriggerMethod[]{}),
	READYBUTSUSPENDED(null,"ready_but_suspended",108l,"Ready but suspended",new TriggerMethod[]{}),
	READY(null,"ready",111l,"Ready",new TriggerMethod[]{}),
	INPROGRESS(null,"in_progress",110l,"In progress",new TriggerMethod[]{}),
	COMPLETED(null,"completed",106l,"Completed",new TriggerMethod[]{});
	private IProcessStep parentState;
	private String persistentName;
	private long id;
	private String humanName;
	private TriggerMethod[] triggerMethods;
	/** Constructor for TaskRequestState
	 * 
	 * @param parentState 
	 * @param persistentName 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private TaskRequestState(IProcessStep parentState, String persistentName, long id, String humanName, TriggerMethod[] triggerMethods) {
		this.parentState=parentState;
		this.persistentName=persistentName;
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
	
	public int getNakedUmlId() {
		return (int)getId();
	}
	
	public IProcessStep getParentState() {
		return this.parentState;
	}
	
	public String getPersistentName() {
		return this.persistentName;
	}
	
	public String getQualifiedName() {
		return persistentName;
	}
	
	public TriggerMethod[] getTriggerMethods() {
		return this.triggerMethods;
	}
	
	static public TaskRequestState resolveByQualifiedName(String qualifiedName) {
		for ( TaskRequestState s : values() ) {
			if ( s.getQualifiedName().equals(qualifiedName) ) {
				return s;
			}
		}
		return null;
	}

}