package org.opaeum.runtime.bpm.opaeumsimpletypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="9f50af7e_a244_4962_b89f_c0b2dcce18c8@_jbPmkJTjEeG7rsbp1O_4ow")public enum DayOfWeek implements IEnum, Serializable {
	SUNDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_-13vsJTjEeG7rsbp1O_4ow"),
	MONDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@__lyJsJTjEeG7rsbp1O_4ow"),
	TUESDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_AN6gsJTkEeG7rsbp1O_4ow"),
	WEDNESDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_DPjswJTkEeG7rsbp1O_4ow"),
	THURSDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_EgWmQJTkEeG7rsbp1O_4ow"),
	FRIDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_F8BTAJTkEeG7rsbp1O_4ow"),
	SATURDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_G5irMJTkEeG7rsbp1O_4ow");
	private String uuid;
	/** Constructor for DayOfWeek
	 * 
	 * @param uuid 
	 */
	private DayOfWeek(String uuid) {
		this.uuid=uuid;
	}

	public String getUid() {
		String result = getUuid();
		
		return result;
	}
	
	public String getUuid() {
		return this.uuid;
	}
	
	static public Set<DayOfWeek> getValues() {
		return new HashSet<DayOfWeek>(java.util.Arrays.asList(values()));
	}

}