package org.opaeum.runtime.bpm.opaeumsimpletypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="9f50af7e_a244_4962_b89f_c0b2dcce18c8@_VSZhgNcCEeCJ0dmaHEVVnw")public enum Month implements IEnum, Serializable {
	JANUARY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_W2iNwNcCEeCJ0dmaHEVVnw"),
	FEBRUARY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_X8o6MNcCEeCJ0dmaHEVVnw"),
	MARCH("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_Z7lTINcCEeCJ0dmaHEVVnw"),
	APRIL("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_am1PkNcCEeCJ0dmaHEVVnw"),
	MAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_bUr0ENcCEeCJ0dmaHEVVnw"),
	JUNE("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_b9WWkNcCEeCJ0dmaHEVVnw"),
	JULY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_cu7kgNcCEeCJ0dmaHEVVnw"),
	AUGUST("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_dwKYANcCEeCJ0dmaHEVVnw"),
	SEPTEMBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_eYqicNcCEeCJ0dmaHEVVnw"),
	OCTOBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_fPv7cNcCEeCJ0dmaHEVVnw"),
	NOVEMBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_gSa5YNcCEeCJ0dmaHEVVnw"),
	DECEMBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_hMQrcNcCEeCJ0dmaHEVVnw");
	private String uuid;
	/** Constructor for Month
	 * 
	 * @param uuid 
	 */
	private Month(String uuid) {
		this.uuid=uuid;
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<Month> getValues() {
		return new HashSet<Month>(java.util.Arrays.asList(values()));
	}

}