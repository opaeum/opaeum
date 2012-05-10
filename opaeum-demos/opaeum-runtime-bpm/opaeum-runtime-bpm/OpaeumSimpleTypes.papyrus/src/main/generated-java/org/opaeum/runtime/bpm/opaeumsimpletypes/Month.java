package org.opaeum.runtime.bpm.opaeumsimpletypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="9f50af7e_a244_4962_b89f_c0b2dcce18c8@_VSZhgNcCEeCJ0dmaHEVVnw")public enum Month implements IEnum, Serializable {
	JANUARY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_W2iNwNcCEeCJ0dmaHEVVnw",640653935744425155l),
	FEBRUARY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_X8o6MNcCEeCJ0dmaHEVVnw",972801950013056379l),
	MARCH("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_Z7lTINcCEeCJ0dmaHEVVnw",5185348543107263063l),
	APRIL("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_am1PkNcCEeCJ0dmaHEVVnw",6921870273021791857l),
	MAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_bUr0ENcCEeCJ0dmaHEVVnw",4615887491455175497l),
	JUNE("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_b9WWkNcCEeCJ0dmaHEVVnw",3472788856023550217l),
	JULY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_cu7kgNcCEeCJ0dmaHEVVnw",6688112956924166609l),
	AUGUST("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_dwKYANcCEeCJ0dmaHEVVnw",4398678130798243985l),
	SEPTEMBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_eYqicNcCEeCJ0dmaHEVVnw",6336416570622065821l),
	OCTOBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_fPv7cNcCEeCJ0dmaHEVVnw",5875481493733805759l),
	NOVEMBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_gSa5YNcCEeCJ0dmaHEVVnw",7294355607504504191l),
	DECEMBER("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_hMQrcNcCEeCJ0dmaHEVVnw",440334831924994583l);
	private long opaeumId;
	private String uuid;
	/** Constructor for Month
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private Month(String uuid, long opaeumId) {
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
	
	static public Set<Month> getValues() {
		return new HashSet<Month>(java.util.Arrays.asList(values()));
	}

}