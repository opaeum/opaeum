package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.task_participation_kind",uuid="OpiumBPM.library.uml@_neCVAI6UEeCne5ArYLDbiA")public enum TaskParticipationKind implements IEnum, Serializable {
	OWNER("f8fc5890_2913_489d_afd3_f46b0200b9d4"),
	POTENTIALOWNER("36c4e5e1_9499_44e7_9e82_e293ecc6f34f");
	private String uuid;
	/** Constructor for TaskParticipationKind
	 * 
	 * @param uuid 
	 */
	private TaskParticipationKind(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<TaskParticipationKind> getValues() {
		return new HashSet<TaskParticipationKind>(java.util.Arrays.asList(values()));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}