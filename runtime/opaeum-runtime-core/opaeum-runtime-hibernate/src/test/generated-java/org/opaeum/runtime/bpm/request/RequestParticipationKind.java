package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_ysdO4I6MEeCrtavWRHwoHg")
public enum RequestParticipationKind implements IEnum, Serializable {
	BUSINESSOWNER("252060@_2HekQI6MEeCrtavWRHwoHg",4714450189639896024l),
	STAKEHOLDER("252060@_4YwDUI6MEeCrtavWRHwoHg",3293883879134782196l),
	INITIATOR("252060@_DQXGEI6NEeCrtavWRHwoHg",8214635375160583394l);
	private long opaeumId;
	private String uuid;
	/** Constructor for RequestParticipationKind
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private RequestParticipationKind(String uuid, long opaeumId) {
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
	
	static public Set<RequestParticipationKind> getValues() {
		return new HashSet<RequestParticipationKind>(java.util.Arrays.asList(values()));
	}

}