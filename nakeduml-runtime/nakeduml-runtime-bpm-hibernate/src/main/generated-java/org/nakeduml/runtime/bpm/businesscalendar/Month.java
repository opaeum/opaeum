package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.IEnum;

@NumlMetaInfo(qualifiedPersistentName="businesscalendar.month",uuid="656318ab_0082_43e5_ba65_e18c4abe6dff")public enum Month implements IEnum, Serializable {
	JANUARY("294e1424_610e_4022_b7e2_e34ef77d0377"),
	FEBRUARY("1fea8aa6_5338_4465_ad2e_313a25f37f1d"),
	MARCH("8828539d_ae7f_43cb_9f10_f679137815e5"),
	APRIL("f238d5b7_2657_45f1_b52f_078d9249f6e0"),
	MAY("7780f5e1_08c0_4ea1_aa96_ec544db11ba1"),
	JUNE("a8f10247_21d4_4c7a_9668_70da9cdc1da3"),
	JULY("622545cc_8141_4217_b045_4b8a3684f639"),
	AUGUST("83b78d0c_1019_4cb0_b4b7_5cbfd33cf813"),
	SEPTEMBER("ffd70091_826f_4ca5_bde8_f790853b8a45"),
	OCTOBER("21c5220c_c2e7_40c2_9c3c_56175357dcff"),
	NOVEMBER("9d7c25d2_4de6_47c7_9c31_01c7d05de243"),
	DECEMBER("942665c6_01a2_40cd_80a0_8ca3492847c5");
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}

}