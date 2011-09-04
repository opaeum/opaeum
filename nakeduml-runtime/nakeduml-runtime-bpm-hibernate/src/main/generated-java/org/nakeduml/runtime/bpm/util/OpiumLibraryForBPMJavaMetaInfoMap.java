package org.nakeduml.runtime.bpm.util;

import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.environment.JavaMetaInfoMap;

public class OpiumLibraryForBPMJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public OpiumLibraryForBPMJavaMetaInfoMap INSTANCE = new OpiumLibraryForBPMJavaMetaInfoMap();

	/** Constructor for OpiumLibraryForBPMJavaMetaInfoMap
	 */
	public OpiumLibraryForBPMJavaMetaInfoMap() {
		putClass(java.util.Date.class,"61f8c81c_6585_423a_a5d0_9693212aed2b");
		putClass(org.nakeduml.runtime.bpm.RequestParticipationKind.class,"5e4488e6_21cc_4c7e_b663_09a7fe0b0a86");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.WorkDay.class,"372722e6_244a_4e21_a5fd_3fd037cb2339");
		putClass(org.nakeduml.runtime.bpm.OperationProcessObject.class,"818a45f2_0e21_4904_83c1_087b2f2633c6");
		putClass(org.nakeduml.runtime.bpm.ParticipationInRequest.class,"1367f57a_8e08_4331_a755_0f313be95d3d");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit.class,"a3352ff1_7955_4296_bfb2_b8e8846219d1");
		putClass(org.nakeduml.runtime.bpm.Participation.class,"d643e291_b3f7_4edf_b57f_b4488fc8e6d5");
		putClass(org.nakeduml.runtime.bpm.BusinessRole.class,"0e0add46_a5da_4e51_a3db_9b16a244d0ab");
		putClass(java.lang.Integer.class,"514c45d0_8b6f_4911_9706_fe8f822ff273");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar.class,"65a77c10_1db1_40f2_9bc5_e3306b228731");
		putMethod(org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar.class,"1d614e13_4ac2_4a94_a626_51703da1345a",503);
		putMethod(org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar.class,"2451910c_292c_4f23_90d3_97453cd2cb92",495);
		putClass(org.nakeduml.runtime.bpm.businesscalendar.Month.class,"656318ab_0082_43e5_ba65_e18c4abe6dff");
		putClass(org.nakeduml.runtime.bpm.RequestObject.class,"8e1632c7_9456_4529_addf_3ffecb9e70ac");
		putClass(org.nakeduml.runtime.bpm.Participant.class,"02234900_5ce4_4179_8d64_4965b437cd7a");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.TimeOfDay.class,"9ddfdee2_11ec_44a6_8e51_fe542c94ead1");
		putClass(org.nakeduml.runtime.bpm.ParticipationInTask.class,"dabe8f9c_f994_4d74_808c_7f11adcaad0b");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.WorkDayKind.class,"9d527ba2_64ee_46a0_87f8_cd289798d929");
		putClass(java.lang.Integer.class,"8128900c_dae9_4cf6_9dd4_1e0619db4d7e");
		putClass(org.nakeduml.runtime.bpm.TaskRequest.class,"b63c167a_b5a4_43b7_a8e2_c995040b5d30");
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"f513a78c_c3ad_4bc5_82b7_d89618f1b3f5",123);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"5387e292_3648_4753_9f06_a27f41af44c3",114);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"11c32fb5_19b2_4378_9678_2a36674198da",126);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"92189f72_d271_4c59_96c4_1da3fd146d55",111);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"072ac484_0655_4c83_9888_279d6dc07b30",108);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"62beb10d_8ba3_4c65_9e7e_030478288a01",130);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"b48c2746_4c14_4fb9_b271_8148dc33ebca",117);
		putMethod(org.nakeduml.runtime.bpm.TaskRequest.class,"d536b80d_de3c_41ed_8765_aaf9adc955db",124);
		putClass(org.nakeduml.runtime.bpm.TaskParticipationKind.class,"cb04135a_35d0_4798_b64a_5b5f068e2258");
		putClass(org.nakeduml.runtime.bpm.BusinessComponent.class,"b40869e0_bd1f_44f2_ae37_b9c2ab8c9b26");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.RecurringHoliday.class,"51c97f43_9068_4645_8673_20cc7aa9bec3");
		putClass(org.nakeduml.runtime.bpm.businesscalendar.OnceOffHoliday.class,"354e353a_7b7c_4e54_8d8e_122d936f997a");
		putClass(org.nakeduml.runtime.bpm.TaskObject.class,"9ba47bb5_372d_40b7_b7ad_1a477f988162");
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"57a73970_27c0_43c2_bf84_217588287539",74);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"55da2d9a_27df_446d_8575_19bea4648d54",58);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"769aa6c0_3b27_474c_b415_a61b11d68581",67);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"d6e66578_e1a1_4736_beef_9bfb36f36ac8",60);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"1722026c_8dc5_4e7b_b099_8a49c5f7b467",65);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"d31ed2f8_1950_4756_b51f_399097440e88",56);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"e33e12bc_fff7_4b70_a535_47e2a0e4490f",71);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"5ca783d9_68c7_4b5a_9da9_38bc6f5e37f2",54);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"ec016cd5_a77f_494f_8cb2_0993a12a1378",76);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"6b36d586_5db5_4b08_8957_a7d21bb1634c",69);
		putMethod(org.nakeduml.runtime.bpm.TaskObject.class,"be928add_c441_4722_9ca4_55e62001185b",62);
		putClass(org.nakeduml.runtime.bpm.ProcessRequest.class,"4b56cfda_fe0e_45f9_b6c3_865c024f8283");
		putMethod(org.nakeduml.runtime.bpm.ProcessRequest.class,"66dd9320_2353_4cd3_b4df_c15a20be4a4d",82);
		putClass(java.lang.Integer.class,"6e71a6a1_57fb_4955_bfe0_9bd74c5fc848");
		putClass(org.nakeduml.runtime.bpm.AbstractRequest.class,"b6f61fb7_a6d0_4c24_b3e8_224cfbfcf089");
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"36828715_ab4f_4725_8518_66a481352aa3",100);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"7c5cfc65_b4da_4818_9c2d_79f6da935243",92);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"b6837a3a_3b9e_4a67_ab1e_057218eec221",102);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"7fbf1d45_44f9_45d0_91e0_e55fe40720a6",93);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"64d76ca2_374d_447c_9c76_e861db0dd383",101);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"048f13d7_c73d_43ff_a6de_f05423518ae1",87);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"45148b3f_79a0_46ba_b505_b40d26663c1c",103);
		putMethod(org.nakeduml.runtime.bpm.AbstractRequest.class,"79817349_d260_44a0_af0f_0e752e34bb9e",91);
		putClass(org.nakeduml.runtime.domain.TaskDelegation.class,"5aee6ba0_7517_4887_ba4f_f7766e191b03");
	}


}