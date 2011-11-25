package org.opaeum.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_neCVAI6UEeCne5ArYLDbiA")public enum TaskParticipationKind implements IEnum, Serializable {
	OWNER("252060@_plvBUI6UEeCne5ArYLDbiA"),
	POTENTIALOWNER("252060@_qJU2kI6UEeCne5ArYLDbiA");
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

}