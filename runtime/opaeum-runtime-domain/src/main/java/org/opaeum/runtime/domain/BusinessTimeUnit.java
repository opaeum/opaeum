package org.opaeum.runtime.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;

@NumlMetaInfo(uuid="OpaeumBPMCommon.library.uml@_7zLcgNb-EeCJ0dmaHEVVnw")public enum BusinessTimeUnit implements IEnum, Serializable {
	BUSINESSMINUTE("OpaeumBPMCommon.library.uml@_9aqXsNb-EeCJ0dmaHEVVnw", "business minute"),
	ACTUALMINUTE("OpaeumBPMCommon.library.uml@_AADsoNb_EeCJ0dmaHEVVnw","actual minute"),
	BUSINESSHOUR("OpaeumBPMCommon.library.uml@_BSwqoNb_EeCJ0dmaHEVVnw", "business hour"),
	ACTUALHOUR("OpaeumBPMCommon.library.uml@_B9jUENb_EeCJ0dmaHEVVnw", "actual hour"),
	BUSINESSDAY("OpaeumBPMCommon.library.uml@_Cl_0INb_EeCJ0dmaHEVVnw","business day"),
	CALENDARDAY("OpaeumBPMCommon.library.uml@_DolSgNb_EeCJ0dmaHEVVnw", "calendar day"),
	CALENDARWEEK("OpaeumBPMCommon.library.uml@_EYj98Nb_EeCJ0dmaHEVVnw", "calendar week"),
	BUSINESSWEEK("OpaeumBPMCommon.library.uml@_MWLUwNb_EeCJ0dmaHEVVnw", "business week"),
	CALENDARMONTH("OpaeumBPMCommon.library.uml@_NEFjoNb_EeCJ0dmaHEVVnw", "calendar month"),
	BUSINESSMONTH("OpaeumBPMCommon.library.uml@_N78YsNb_EeCJ0dmaHEVVnw", "business month");
	private String uuid;
	private String name;
	/** Constructor for BusinessTimeUnit
	 * 
	 * @param uuid 
	 */
	private BusinessTimeUnit(String uuid, String name) {
		this.uuid=uuid;
		this.name=name;
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	public String toString(){
		return name;
	}
	static public Set<BusinessTimeUnit> getValues() {
		return new HashSet<BusinessTimeUnit>(java.util.Arrays.asList(values()));
	}

}