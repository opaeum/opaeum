package org.opaeum.demo1.structuredbusiness.branch;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class TechnicianInstanceSimulation_6znkVH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Technician> {
	static final public TechnicianInstanceSimulation_6znkVH_JEeK5usaVqVCtXw INSTANCE = new TechnicianInstanceSimulation_6znkVH_JEeK5usaVqVCtXw();


	public Technician createNewObject(CompositionNode parent) throws Exception {
		Technician result = new Technician();
		result.init(parent);
		int preferredNotificationTypeCount = 0;
		while ( preferredNotificationTypeCount<1 ) {
			preferredNotificationTypeCount++;
			result.setPreferredNotificationType((NotificationType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::TechnicianInstanceSimulation","preferredNotificationType"));
		}
		int preferredEMailAddressTypeCount = 0;
		while ( preferredEMailAddressTypeCount<1 ) {
			preferredEMailAddressTypeCount++;
			result.setPreferredEMailAddressType((PersonEMailAddressType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::TechnicianInstanceSimulation","preferredEMailAddressType"));
		}
		int preferredPhoneNumberTypeCount = 0;
		while ( preferredPhoneNumberTypeCount<1 ) {
			preferredPhoneNumberTypeCount++;
			result.setPreferredPhoneNumberType((PersonPhoneNumberType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::TechnicianInstanceSimulation","preferredPhoneNumberType"));
		}
		int ratePerTimeUnitCount = 0;
		while ( ratePerTimeUnitCount<3 ) {
			ratePerTimeUnitCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::TechnicianInstanceSimulation","ratePerTimeUnit").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Technician instance = (Technician)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::TechnicianInstanceSimulation","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::TechnicianInstanceSimulation","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			PersonNode newVal = (PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::TechnicianInstanceSimulation","representedPerson").getNextReference();
			representedPersonCount++;
			if ( instance.getRepresentedPerson() == null ) {
				instance.setRepresentedPerson(newVal);
			}
		}
	}

}