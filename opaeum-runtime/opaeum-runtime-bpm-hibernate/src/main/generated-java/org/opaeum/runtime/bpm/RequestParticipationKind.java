package org.opaeum.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_ysdO4I6MEeCrtavWRHwoHg")public enum RequestParticipationKind implements IEnum, Serializable {
	BUSINESSOWNER("252060@_2HekQI6MEeCrtavWRHwoHg"),
	POTENTIALBUSINESSOWNER("252060@_2-CY0I6MEeCrtavWRHwoHg"),
	STAKEHOLDER("252060@_4YwDUI6MEeCrtavWRHwoHg"),
	POTENTIALSTAKEHOLDER("252060@_8OBEsI6MEeCrtavWRHwoHg"),
	INITIATOR("252060@_DQXGEI6NEeCrtavWRHwoHg");
	private String uuid;
	/** Constructor for RequestParticipationKind
	 * 
	 * @param uuid 
	 */
	private RequestParticipationKind(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<RequestParticipationKind> getValues() {
		return new HashSet<RequestParticipationKind>(java.util.Arrays.asList(values()));
	}

}