package org.opeum.demo1.util;

import java.util.ArrayList;
import java.util.List;

import org.opaeum.runtime.jpa.AbstractPersistenceUnitInfo;

public class Demo1PersistenceUnitInfo extends AbstractPersistenceUnitInfo {


	/** Constructor for Demo1PersistenceUnitInfo
	 * 
	 * @param env 
	 */
	public Demo1PersistenceUnitInfo(Demo1Environment env) {
	super(env);
	}

	public List<String> getManagedClassNames() {
		List result = new ArrayList<String>();
		result.add("org.opaeum.hibernate.domain.EventOccurrence");
		result.add("org.opaeum.hibernate.domain.AbstractPersistentEnum");
		result.add("org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum");
		result.add("org.opeum.demo1.util");
		result.add("org.opaeum.demo1.structuredbusiness.OnlineCustomer");
		result.add("org.opaeum.demo1.structuredbusiness.Supplier");
		result.add("org.opaeum.demo1.structuredbusiness.ApplianceDoctor");
		result.add("org.opaeum.demo1.structuredbusiness.appliance.ApplianceComponent");
		result.add("org.opaeum.demo1.structuredbusiness.appliance.VendorEntity");
		result.add("org.opaeum.demo1.structuredbusiness.appliance.ApplianceTypeEntity");
		result.add("org.opaeum.demo1.structuredbusiness.appliance.ApplianceModel");
		result.add("org.opaeum.demo1.structuredbusiness.branch.Technician");
		result.add("org.opaeum.demo1.structuredbusiness.branch.Branch");
		result.add("org.opaeum.demo1.structuredbusiness.branch.branch.StandaloneSingleScreenTask1");
		result.add("org.opaeum.demo1.structuredbusiness.branch.branch.StandaloneSingleScreenTask1Token");
		result.add("org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote");
		result.add("org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken");
		result.add("org.opaeum.demo1.structuredbusiness.branch.CustomerAssistant");
		result.add("org.opaeum.demo1.structuredbusiness.branch.ProvinceEntity");
		result.add("org.opaeum.demo1.structuredbusiness.branch.CityEntity");
		result.add("org.opaeum.demo1.structuredbusiness.branch.IdBook");
		result.add("org.opaeum.demo1.structuredbusiness.branch.Manager");
		result.add("org.opaeum.demo1.structuredbusiness.jobs.Job");
		result.add("org.opaeum.demo1.structuredbusiness.jobs.ApplianceComponentSale");
		result.add("org.opaeum.demo1.structuredbusiness.jobs.Activity");
		result.add("org.opaeum.demo1.structuredbusiness.ApplianceCollaboration");
		result.add("org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar");
		result.add("org.opaeum.runtime.bpm.businesscalendar.WorkDayKindEntity");
		result.add("org.opaeum.runtime.bpm.businesscalendar.WorkDay");
		result.add("org.opaeum.runtime.bpm.businesscalendar.RecurringHoliday");
		result.add("org.opaeum.runtime.bpm.businesscalendar.TimeOfDay");
		result.add("org.opaeum.runtime.bpm.businesscalendar.OnceOffHoliday");
		result.add("org.opaeum.runtime.bpm.businesscalendar.CronExpression");
		result.add("org.opaeum.runtime.bpm.organization.PersonNode");
		result.add("org.opaeum.runtime.bpm.organization.OrganizationNode");
		result.add("org.opaeum.runtime.bpm.organization.OrganizationFullfillsActorRole");
		result.add("org.opaeum.runtime.bpm.organization.PersonFullfillsActorRole");
		result.add("org.opaeum.runtime.bpm.organization.Leave");
		result.add("org.opaeum.runtime.bpm.organization.BusinessNetwork");
		result.add("org.opaeum.runtime.bpm.organization.BusinessNetworkFacilatatesCollaboration");
		result.add("org.opaeum.runtime.bpm.organization.BusinessCollaboration_Business");
		result.add("org.opaeum.runtime.bpm.organization.BusinessCollaboration_BusinessActor");
		result.add("org.opaeum.runtime.bpm.organization.OrganizationAsBusinessComponent");
		result.add("org.opaeum.runtime.bpm.organization.PersonInBusinessRole");
		result.add("org.opaeum.runtime.bpm.request.TaskRequest");
		result.add("org.opaeum.runtime.bpm.request.TaskRequestToken");
		result.add("org.opaeum.runtime.bpm.request.ParticipationParticipant");
		result.add("org.opaeum.runtime.bpm.request.ProcessRequest");
		result.add("org.opaeum.runtime.bpm.request.ProcessRequestToken");
		result.add("org.opaeum.runtime.bpm.request.AbstractRequest");
		result.add("org.opaeum.runtime.bpm.request.AbstractRequestToken");
		result.add("org.opaeum.runtime.bpm.request.Participation");
		result.add("org.opaeum.runtime.bpm.request.RequestParticipationKindEntity");
		result.add("org.opaeum.runtime.bpm.request.ParticipationInRequest");
		result.add("org.opaeum.runtime.bpm.request.TaskParticipationKindEntity");
		result.add("org.opaeum.runtime.bpm.request.ParticipationInTask");
		result.add("org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberTypeEntity");
		result.add("org.opaeum.runtime.bpm.contact.PersonPhoneNumber");
		result.add("org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber");
		result.add("org.opaeum.runtime.bpm.contact.PersonEMailAddress");
		result.add("org.opaeum.runtime.bpm.contact.OrganizationEMailAddressTypeEntity");
		result.add("org.opaeum.runtime.bpm.contact.OrganizationEMailAddress");
		result.add("org.opaeum.runtime.bpm.contact.PostalAddress");
		result.add("org.opaeum.runtime.bpm.contact.PhysicalAddress");
		result.add("org.opaeum.runtime.bpm.contact.Address");
		result.add("org.opaeum.runtime.bpm.costing.RatePerTimeUnit");
		result.add("org.opaeum.runtime.bpm.costing.TimedResourceRatePerTimeUnit");
		result.add("org.opaeum.runtime.bpm.costing.PricePerUnit");
		result.add("org.opaeum.runtime.bpm.costing.QuantifiedResourcePricePerUnit");
		result.add("ocltests.Boat");
		result.add("ocltests.Sail");
		result.add("ocltests.SailPositionEntity");
		result.add("ocltests.BusinessRole1");
		return result;
	}
	
	public String getPersistenceUnitName() {
		String result = "demo1";
		
		return result;
	}

}