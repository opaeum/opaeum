package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(qualifiedPersistentName="businesscalendar.business_time_unit",uuid="a3352ff1_7955_4296_bfb2_b8e8846219d1")public enum BusinessTimeUnit implements IEnum, Serializable {
	BUSINESSMINUTE("cee42889_08b4_4d04_a1f8_d362cd806456"),
	ACTUALMINUTE("63023ffa_2331_42a4_88cb_d7d442f23adf"),
	BUSINESSHOUR("4df758ce_8242_4281_96d3_f882256f305f"),
	ACTUALHOUR("c6aa50ab_bd7e_497c_a4ca_5507579007ab"),
	BUSINESSDAY("534075cf_4996_46bb_8027_c95300cf8409"),
	CALENDARDAY("498c10fd_c9fc_4a19_a021_8c59cf035c2a"),
	CALENDARWEEK("15a24930_f7bc_4fd0_ae96_8f80a47ed8e3"),
	BUSINESSWEEK("f01d4e41_3218_444b_8983_9ea4f1867310"),
	CALENDARMONTH("b4abd3cd_e155_4e67_ac28_62c9d6f9b7d0"),
	BUSINESSMONTH("9491c23c_a410_49a7_8a58_6d61b82c872c");
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}