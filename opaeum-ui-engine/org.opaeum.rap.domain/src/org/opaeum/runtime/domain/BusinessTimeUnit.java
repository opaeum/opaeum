package org.opaeum.runtime.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;

@NumlMetaInfo(uuid="OpaeumBPMCommon.library.uml@_7zLcgNb-EeCJ0dmaHEVVnw")public enum BusinessTimeUnit implements IEnum, Serializable {
	BUSINESSMINUTE("OpaeumBPMCommon.library.uml@_9aqXsNb-EeCJ0dmaHEVVnw"),
	ACTUALMINUTE("OpaeumBPMCommon.library.uml@_AADsoNb_EeCJ0dmaHEVVnw"),
	BUSINESSHOUR("OpaeumBPMCommon.library.uml@_BSwqoNb_EeCJ0dmaHEVVnw"),
	ACTUALHOUR("OpaeumBPMCommon.library.uml@_B9jUENb_EeCJ0dmaHEVVnw"),
	BUSINESSDAY("OpaeumBPMCommon.library.uml@_Cl_0INb_EeCJ0dmaHEVVnw"),
	CALENDARDAY("OpaeumBPMCommon.library.uml@_DolSgNb_EeCJ0dmaHEVVnw"),
	CALENDARWEEK("OpaeumBPMCommon.library.uml@_EYj98Nb_EeCJ0dmaHEVVnw"),
	BUSINESSWEEK("OpaeumBPMCommon.library.uml@_MWLUwNb_EeCJ0dmaHEVVnw"),
	CALENDARMONTH("OpaeumBPMCommon.library.uml@_NEFjoNb_EeCJ0dmaHEVVnw"),
	BUSINESSMONTH("OpaeumBPMCommon.library.uml@_N78YsNb_EeCJ0dmaHEVVnw");
	private String uuid;
	/** Constructor for BusinessTimeUnit
	 * 
	 * @param uuid 
	 */
	private BusinessTimeUnit(String uuid) {
		this.uuid=uuid;
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<BusinessTimeUnit> getValues() {
		return new HashSet<BusinessTimeUnit>(java.util.Arrays.asList(values()));
	}

}