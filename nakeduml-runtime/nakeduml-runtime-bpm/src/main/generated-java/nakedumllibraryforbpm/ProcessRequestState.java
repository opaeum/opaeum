package nakedumllibraryforbpm;

import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TriggerMethod;

public enum ProcessRequestState implements IProcessStep {
;
	private IProcessStep parentState;
	private String persistentName;
	private long id;
	private String humanName;
	private TriggerMethod[] triggerMethods;
	/** Constructor for ProcessRequestState
	 * 
	 * @param parentState 
	 * @param persistentName 
	 * @param id 
	 * @param humanName 
	 * @param triggerMethods 
	 */
	private ProcessRequestState(IProcessStep parentState, String persistentName, long id, String humanName, TriggerMethod[] triggerMethods) {
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
	
	static public ProcessRequestState resolveByQualifiedName(String qualifiedName) {
		for ( ProcessRequestState s : values() ) {
			if ( s.getQualifiedName().equals(qualifiedName) ) {
				return s;
			}
		}
		return null;
	}

}