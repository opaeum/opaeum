package org.opaeum.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_EnGlsNb-EeCJ0dmaHEVVnw")public enum WorkDayKind implements IEnum, Serializable {
	WEEKDAY("252060@_GJQU8Nb-EeCJ0dmaHEVVnw"),
	SATURDAY("252060@_H3nUMNb-EeCJ0dmaHEVVnw"),
	SUNDAY("252060@_IionINb-EeCJ0dmaHEVVnw");
	private String uuid;
	/** Constructor for WorkDayKind
	 * 
	 * @param uuid 
	 */
	private WorkDayKind(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<WorkDayKind> getValues() {
		return new HashSet<WorkDayKind>(java.util.Arrays.asList(values()));
	}

}