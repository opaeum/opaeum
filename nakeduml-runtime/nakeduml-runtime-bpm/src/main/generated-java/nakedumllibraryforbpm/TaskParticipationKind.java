package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.task_participation_kind",nakedUmlId=25)public enum TaskParticipationKind implements IEnum, Serializable {
	POTENTIALOWNER("task_participation_kind.potentialowner",216),
	OWNER("task_participation_kind.owner",215);
	private String persistentName;
	private int nakedUmlId;
	private String uid;
	/** Constructor for TaskParticipationKind
	 * 
	 * @param persistentName 
	 * @param nakedUmlId 
	 */
	private TaskParticipationKind(String persistentName, int nakedUmlId) {
		this.persistentName=persistentName;
		this.nakedUmlId=nakedUmlId;
	}

	public int getNakedUmlId() {
		return this.nakedUmlId;
	}
	
	public String getPersistentName() {
		return this.persistentName;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}