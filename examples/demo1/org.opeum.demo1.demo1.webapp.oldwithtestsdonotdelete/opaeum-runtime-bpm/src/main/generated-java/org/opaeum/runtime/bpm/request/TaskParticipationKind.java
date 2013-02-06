package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_neCVAI6UEeCne5ArYLDbiA")public enum TaskParticipationKind implements IEnum, Serializable {
	OWNER("252060@_plvBUI6UEeCne5ArYLDbiA",4935160483967374189l),
	POTENTIALOWNER("252060@_qJU2kI6UEeCne5ArYLDbiA",3500106936288394851l);
	private long opaeumId;
	private String uuid;
	/** Constructor for TaskParticipationKind
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private TaskParticipationKind(String uuid, long opaeumId) {
		this.uuid=uuid;
		this.opaeumId=opaeumId;
	}

	public long getOpaeumId() {
		return this.opaeumId;
	}
	
	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<TaskParticipationKind> getValues() {
		return new HashSet<TaskParticipationKind>(java.util.Arrays.asList(values()));
	}

}