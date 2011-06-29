package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.request_participation_kind",nakedUmlId=24)public enum RequestParticipationKind implements IEnum, Serializable {
	BUSINESSOWNER("request_participation_kind.businessowner",213),
	POTENTIALBUSINESSOWNER("request_participation_kind.potentialbusinessowner",212),
	INITIATOR("request_participation_kind.initiator",214),
	STAKEHOLDER("request_participation_kind.stakeholder",211),
	POTENTIALSTAKEHOLDER("request_participation_kind.potentialstakeholder",210);
	private String persistentName;
	private int nakedUmlId;
	private String uid;
	/** Constructor for RequestParticipationKind
	 * 
	 * @param persistentName 
	 * @param nakedUmlId 
	 */
	private RequestParticipationKind(String persistentName, int nakedUmlId) {
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