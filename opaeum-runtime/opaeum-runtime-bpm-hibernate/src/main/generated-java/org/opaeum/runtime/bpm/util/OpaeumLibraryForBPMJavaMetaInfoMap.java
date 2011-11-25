package org.opaeum.runtime.bpm.util;

import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.JavaMetaInfoMap;

public class OpaeumLibraryForBPMJavaMetaInfoMap extends JavaMetaInfoMap {
	static final public OpaeumLibraryForBPMJavaMetaInfoMap INSTANCE = new OpaeumLibraryForBPMJavaMetaInfoMap();

	/** Constructor for OpaeumLibraryForBPMJavaMetaInfoMap
	 */
	public OpaeumLibraryForBPMJavaMetaInfoMap() {
		putClass(org.opaeum.runtime.bpm.Participation.class,"252060@_jRjnII6MEeCrtavWRHwoHg");
		putClass(org.opaeum.runtime.bpm.OperationProcessObject.class,"252060@_5DVD4I3oEeCfQedkc0TCdA");
		putClass(org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar.class,"252060@_x9fmQNb9EeCJ0dmaHEVVnw");
		putMethod(org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar.class,"252060@_dXLYsASTEeGb9qsDxKJdSA",641);
		putMethod(org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar.class,"252060@_NTccANcEEeCJ0dmaHEVVnw",299);
		putMethod(org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar.class,"252060@_mOhZgNcEEeCJ0dmaHEVVnw",290);
		putClass(org.opaeum.runtime.bpm.RequestParticipationKind.class,"252060@_ysdO4I6MEeCrtavWRHwoHg");
		putClass(org.opaeum.runtime.domain.TaskDelegation.class,"252060@_5lxp4LRZEeCilvbXE8KmHA");
		putClass(java.lang.Integer.class,"252060@_FvE9kNcCEeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.businesscalendar.Month.class,"252060@_VSZhgNcCEeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.businesscalendar.CronExpression.class,"252060@_hqpgYASQEeGb9qsDxKJdSA");
		putClass(org.opaeum.runtime.bpm.businesscalendar.BusinessTimeUnit.class,"252060@_7zLcgNb-EeCJ0dmaHEVVnw");
		putClass(java.util.Date.class,"252060@_qJQboNcCEeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.RequestObject.class,"252060@_Wd2QoI53EeCfQedkc0TCdA");
		putClass(org.opaeum.runtime.bpm.businesscalendar.WorkDay.class,"252060@_Jn9QcNb-EeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.ParticipationInRequest.class,"252060@_GVhgMI6NEeCrtavWRHwoHg");
		putClass(org.opaeum.runtime.bpm.BusinessRole.class,"252060@_tH0fAIoVEeCLqpffVZYAlw");
		putClass(java.lang.Integer.class,"252060@_bb59wNb_EeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.businesscalendar.Duration.class,"252060@_mkwNcASREeGb9qsDxKJdSA");
		putClass(org.opaeum.runtime.bpm.ProcessRequest.class,"252060@_ciiWAI2-EeCrtavWRHwoHg");
		putMethod(org.opaeum.runtime.bpm.ProcessRequest.class,"252060@_4zDaYK0wEeCTTvcJZSDicw",243);
		putClass(org.opaeum.runtime.bpm.BusinessComponent.class,"252060@_uVek8IoVEeCLqpffVZYAlw");
		putClass(org.opaeum.runtime.bpm.TaskParticipationKind.class,"252060@_neCVAI6UEeCne5ArYLDbiA");
		putClass(org.opaeum.runtime.bpm.businesscalendar.OnceOffHoliday.class,"252060@_5rW3kNcCEeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.Participant.class,"252060@_YgstsI29EeCrtavWRHwoHg");
		putClass(org.opaeum.runtime.bpm.ParticipationInTask.class,"252060@_vZOC4I6UEeCne5ArYLDbiA");
		putClass(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_zFmsEIoVEeCLqpffVZYAlw");
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@__6uyIIoaEeCPduia_-NbFw",249);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_1gF8AKDTEeCi16HgBnUGFw",265);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_GRVH0IobEeCPduia_-NbFw",266);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_wuzAoI6SEeCrtavWRHwoHg",246);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_0lAQAIoaEeCPduia_-NbFw",252);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_LlMOIIobEeCPduia_-NbFw",254);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_v52VoI6SEeCrtavWRHwoHg",259);
		putMethod(org.opaeum.runtime.bpm.TaskRequest.class,"252060@_Nk_isIobEeCPduia_-NbFw",262);
		putClass(org.opaeum.runtime.bpm.businesscalendar.RecurringHoliday.class,"252060@_TFKVQNb_EeCJ0dmaHEVVnw");
		putClass(java.lang.Integer.class,"252060@_cIJYsNb_EeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.businesscalendar.TimeOfDay.class,"252060@_UjTHMNb_EeCJ0dmaHEVVnw");
		putClass(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_6MA8UI2-EeCrtavWRHwoHg");
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_3USwcKDGEeCv9IRqC7lfYw",202);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_Nl5kQI6SEeCrtavWRHwoHg",198);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_qwWfEIoaEeCPduia_-NbFw",205);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_VJyB4I6REeCrtavWRHwoHg",195);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_-PsMoIoaEeCPduia_-NbFw",213);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_Qo338I6QEeCrtavWRHwoHg",210);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_4RNcAIoaEeCPduia_-NbFw",197);
		putMethod(org.opaeum.runtime.bpm.AbstractRequest.class,"252060@_ov5DMIoaEeCPduia_-NbFw",201);
		putClass(org.opaeum.runtime.bpm.TaskObject.class,"252060@_2tdYsI3oEeCfQedkc0TCdA");
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_8ba9IK0NEeCK48ywUpk_rg",237);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_iBCwEK0NEeCK48ywUpk_rg",235);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_EE4B0K0OEeCK48ywUpk_rg",225);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_a_82cK0OEeCK48ywUpk_rg",231);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_NdLN8K0OEeCK48ywUpk_rg",228);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@__imwgK0NEeCK48ywUpk_rg",217);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_ug7_QK0NEeCK48ywUpk_rg",223);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_qTa18K0NEeCK48ywUpk_rg",221);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_XbPZkK0OEeCK48ywUpk_rg",239);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_fdkRQK0OEeCK48ywUpk_rg",233);
		putMethod(org.opaeum.runtime.bpm.TaskObject.class,"252060@_zwcxEK0NEeCK48ywUpk_rg",219);
		putClass(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.class,"252060@_EnGlsNb-EeCJ0dmaHEVVnw");
	}


}