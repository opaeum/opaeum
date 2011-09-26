package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(uuid="252060@_VSZhgNcCEeCJ0dmaHEVVnw")public enum Month implements IEnum, Serializable {
	JANUARY("252060@_W2iNwNcCEeCJ0dmaHEVVnw"),
	FEBRUARY("252060@_X8o6MNcCEeCJ0dmaHEVVnw"),
	MARCH("252060@_Z7lTINcCEeCJ0dmaHEVVnw"),
	APRIL("252060@_am1PkNcCEeCJ0dmaHEVVnw"),
	MAY("252060@_bUr0ENcCEeCJ0dmaHEVVnw"),
	JUNE("252060@_b9WWkNcCEeCJ0dmaHEVVnw"),
	JULY("252060@_cu7kgNcCEeCJ0dmaHEVVnw"),
	AUGUST("252060@_dwKYANcCEeCJ0dmaHEVVnw"),
	SEPTEMBER("252060@_eYqicNcCEeCJ0dmaHEVVnw"),
	OCTOBER("252060@_fPv7cNcCEeCJ0dmaHEVVnw"),
	NOVEMBER("252060@_gSa5YNcCEeCJ0dmaHEVVnw"),
	DECEMBER("252060@_hMQrcNcCEeCJ0dmaHEVVnw");
	private String uuid;
	/** Constructor for Month
	 * 
	 * @param uuid 
	 */
	private Month(String uuid) {
		this.uuid=uuid;
	}

	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<Month> getValues() {
		return new HashSet<Month>(java.util.Arrays.asList(values()));
	}

}