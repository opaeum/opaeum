package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(qualifiedPersistentName="businesscalendar.work_day_kind",uuid="OpiumBPM.library.uml@_EnGlsNb-EeCJ0dmaHEVVnw")public enum WorkDayKind implements IEnum, Serializable {
	WEEKDAY("11006c30_2384_4813_8f75_60455373b56c"),
	SATURDAY("475f4f71_f642_45f5_8fa3_1024c71527dc"),
	SUNDAY("92f90f5b_0beb_4474_80ae_02365f1838d5");
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}