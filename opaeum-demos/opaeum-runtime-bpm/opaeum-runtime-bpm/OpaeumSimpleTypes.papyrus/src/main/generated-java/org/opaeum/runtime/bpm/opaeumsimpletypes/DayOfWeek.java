package org.opaeum.runtime.bpm.opaeumsimpletypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.domain.IEnum;

@NumlMetaInfo(uuid="9f50af7e_a244_4962_b89f_c0b2dcce18c8@_jbPmkJTjEeG7rsbp1O_4ow")public enum DayOfWeek implements IEnum, Serializable {
	SUNDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_-13vsJTjEeG7rsbp1O_4ow",5497891836226107522l),
	MONDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@__lyJsJTjEeG7rsbp1O_4ow",5710826815533593808l),
	TUESDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_AN6gsJTkEeG7rsbp1O_4ow",7637501354073283762l),
	WEDNESDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_DPjswJTkEeG7rsbp1O_4ow",5854374740816063880l),
	THURSDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_EgWmQJTkEeG7rsbp1O_4ow",6034081447840876170l),
	FRIDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_F8BTAJTkEeG7rsbp1O_4ow",1574103007809572002l),
	SATURDAY("9f50af7e_a244_4962_b89f_c0b2dcce18c8@_G5irMJTkEeG7rsbp1O_4ow",3124615520862752976l);
	private long opaeumId;
	private String uuid;
	/** Constructor for DayOfWeek
	 * 
	 * @param uuid 
	 * @param opaeumId 
	 */
	private DayOfWeek(String uuid, long opaeumId) {
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
	
	static public Set<DayOfWeek> getValues() {
		return new HashSet<DayOfWeek>(java.util.Arrays.asList(values()));
	}

}