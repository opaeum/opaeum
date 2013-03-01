package org.opaeum.demo1.structuredbusiness.branch;

import org.opaeum.demo1.structuredbusiness.jobs.Job;
import org.opaeum.runtime.bpm.costing.RatePerTimeUnit;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class CustomerAssistantInstanceSimulation_6zmWMH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<CustomerAssistant> {
	static final public CustomerAssistantInstanceSimulation_6zmWMH_JEeK5usaVqVCtXw INSTANCE = new CustomerAssistantInstanceSimulation_6zmWMH_JEeK5usaVqVCtXw();


	public CustomerAssistant createNewObject(CompositionNode parent) throws Exception {
		CustomerAssistant result = new CustomerAssistant();
		result.init(parent);
		int preferredNotificationTypeCount = 0;
		while ( preferredNotificationTypeCount<1 ) {
			preferredNotificationTypeCount++;
			result.setPreferredNotificationType((NotificationType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CustomerAssistantInstanceSimulation","preferredNotificationType"));
		}
		int preferredEMailAddressTypeCount = 0;
		while ( preferredEMailAddressTypeCount<1 ) {
			preferredEMailAddressTypeCount++;
			result.setPreferredEMailAddressType((PersonEMailAddressType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CustomerAssistantInstanceSimulation","preferredEMailAddressType"));
		}
		int preferredPhoneNumberTypeCount = 0;
		while ( preferredPhoneNumberTypeCount<1 ) {
			preferredPhoneNumberTypeCount++;
			result.setPreferredPhoneNumberType((PersonPhoneNumberType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CustomerAssistantInstanceSimulation","preferredPhoneNumberType"));
		}
		int ratePerTimeUnitCount = 0;
		while ( ratePerTimeUnitCount<3 ) {
			ratePerTimeUnitCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::CustomerAssistantInstanceSimulation","ratePerTimeUnit").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		CustomerAssistant instance = (CustomerAssistant)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::CustomerAssistantInstanceSimulation","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::CustomerAssistantInstanceSimulation","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			PersonNode newVal = (PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::CustomerAssistantInstanceSimulation","representedPerson").getNextReference();
			representedPersonCount++;
			if ( instance.getRepresentedPerson() == null ) {
				instance.setRepresentedPerson(newVal);
			}
		}
		int jobCount = 0;
		while ( jobCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::CustomerAssistantInstanceSimulation","job") ) {
			Job newVal = (Job)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::CustomerAssistantInstanceSimulation","job").getNextReference();
			jobCount++;
			if ( newVal != null && newVal.getCustomerAssistant() ==null ) {
				instance.addToJob(newVal);
			}
		}
	}

}