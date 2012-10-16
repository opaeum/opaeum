package org.opaeum.runtime.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="OpaeumBPMCommon.library.uml@_7zLcgNb-EeCJ0dmaHEVVnw")public enum BusinessTimeUnit implements IEnum, Serializable {
	BUSINESSMINUTE("OpaeumBPMCommon.library.uml@_9aqXsNb-EeCJ0dmaHEVVnw",8352317045811511744l),
	ACTUALMINUTE("OpaeumBPMCommon.library.uml@_AADsoNb_EeCJ0dmaHEVVnw",5085120101231742644l),
	BUSINESSHOUR("OpaeumBPMCommon.library.uml@_BSwqoNb_EeCJ0dmaHEVVnw",2096741548211662020l),
	ACTUALHOUR("OpaeumBPMCommon.library.uml@_B9jUENb_EeCJ0dmaHEVVnw",8799233759197904602l),
	BUSINESSDAY("OpaeumBPMCommon.library.uml@_Cl_0INb_EeCJ0dmaHEVVnw",2729757306656991890l),
	CALENDARDAY("OpaeumBPMCommon.library.uml@_DolSgNb_EeCJ0dmaHEVVnw",8769337351836134558l),
	CALENDARWEEK("OpaeumBPMCommon.library.uml@_EYj98Nb_EeCJ0dmaHEVVnw",6721263799379286430l),
	BUSINESSWEEK("OpaeumBPMCommon.library.uml@_MWLUwNb_EeCJ0dmaHEVVnw",2883229145281295940l),
	CALENDARMONTH("OpaeumBPMCommon.library.uml@_NEFjoNb_EeCJ0dmaHEVVnw",3744136449941699484l),
	BUSINESSMONTH("OpaeumBPMCommon.library.uml@_N78YsNb_EeCJ0dmaHEVVnw",8849849135354011066l);
	private long opaeumId;
	private String uuid;
	/** Constructor for BusinessTimeUnit
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private BusinessTimeUnit(String uuid, long opaeumId) {
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
	
	static public Set<BusinessTimeUnit> getValues() {
		return new HashSet<BusinessTimeUnit>(java.util.Arrays.asList(values()));
	}

}