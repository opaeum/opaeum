package org.opaeum.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_EnGlsNb-EeCJ0dmaHEVVnw")
public enum WorkDayKind implements IEnum, Serializable {
	WEEKDAY("252060@_GJQU8Nb-EeCJ0dmaHEVVnw",1582201863518161267l),
	SATURDAY("252060@_H3nUMNb-EeCJ0dmaHEVVnw",6901241406743663175l),
	SUNDAY("252060@_IionINb-EeCJ0dmaHEVVnw",4486357431481835835l);
	private long opaeumId;
	private String uuid;
	/** Constructor for WorkDayKind
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private WorkDayKind(String uuid, long opaeumId) {
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
	
	static public Set<WorkDayKind> getValues() {
		return new HashSet<WorkDayKind>(java.util.Arrays.asList(values()));
	}

}