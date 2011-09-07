package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.request_participation_kind",uuid="OpiumBPM.library.uml@_ysdO4I6MEeCrtavWRHwoHg")public enum RequestParticipationKind implements IEnum, Serializable {
	BUSINESSOWNER("54e7f473_33ab_4fb8_997b_a0ba18d293f1"),
	POTENTIALBUSINESSOWNER("fc6a1409_fed7_4f97_9842_356c14adebc5"),
	STAKEHOLDER("e21f6bac_36f7_4903_a388_4aa7125524f7"),
	POTENTIALSTAKEHOLDER("176b1bbe_8ceb_46b4_89b4_e8203880ed18"),
	INITIATOR("c2a1841a_e952_4604_8059_ae46adb98259");
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}