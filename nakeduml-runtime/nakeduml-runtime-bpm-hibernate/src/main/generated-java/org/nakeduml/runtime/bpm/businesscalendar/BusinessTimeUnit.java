package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_7zLcgNb-EeCJ0dmaHEVVnw")public enum BusinessTimeUnit implements IEnum, Serializable {
	BUSINESSMINUTE("252060@_9aqXsNb-EeCJ0dmaHEVVnw"),
	ACTUALMINUTE("252060@_AADsoNb_EeCJ0dmaHEVVnw"),
	BUSINESSHOUR("252060@_BSwqoNb_EeCJ0dmaHEVVnw"),
	ACTUALHOUR("252060@_B9jUENb_EeCJ0dmaHEVVnw"),
	BUSINESSDAY("252060@_Cl_0INb_EeCJ0dmaHEVVnw"),
	CALENDARDAY("252060@_DolSgNb_EeCJ0dmaHEVVnw"),
	CALENDARWEEK("252060@_EYj98Nb_EeCJ0dmaHEVVnw"),
	BUSINESSWEEK("252060@_MWLUwNb_EeCJ0dmaHEVVnw"),
	CALENDARMONTH("252060@_NEFjoNb_EeCJ0dmaHEVVnw"),
	BUSINESSMONTH("252060@_N78YsNb_EeCJ0dmaHEVVnw");
	private String uuid;
	/** Constructor for BusinessTimeUnit
	 * 
	 * @param uuid 
	 */
	private BusinessTimeUnit(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<BusinessTimeUnit> getValues() {
		return new HashSet<BusinessTimeUnit>(java.util.Arrays.asList(values()));
	}

}