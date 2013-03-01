package ocltests;

import org.opaeum.runtime.bpm.costing.RatePerTimeUnit;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessRole1InstanceSimulation_60OoUn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<BusinessRole1> {
	static final public BusinessRole1InstanceSimulation_60OoUn_JEeK5usaVqVCtXw INSTANCE = new BusinessRole1InstanceSimulation_60OoUn_JEeK5usaVqVCtXw();


	public BusinessRole1 createNewObject(CompositionNode parent) throws Exception {
		BusinessRole1 result = new BusinessRole1();
		int preferredNotificationTypeCount = 0;
		while ( preferredNotificationTypeCount<1 ) {
			preferredNotificationTypeCount++;
			result.setPreferredNotificationType((NotificationType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::BusinessRole1InstanceSimulation","preferredNotificationType"));
		}
		int preferredEMailAddressTypeCount = 0;
		while ( preferredEMailAddressTypeCount<1 ) {
			preferredEMailAddressTypeCount++;
			result.setPreferredEMailAddressType((PersonEMailAddressType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::BusinessRole1InstanceSimulation","preferredEMailAddressType"));
		}
		int preferredPhoneNumberTypeCount = 0;
		while ( preferredPhoneNumberTypeCount<1 ) {
			preferredPhoneNumberTypeCount++;
			result.setPreferredPhoneNumberType((PersonPhoneNumberType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::BusinessRole1InstanceSimulation","preferredPhoneNumberType"));
		}
		int ratePerTimeUnitCount = 0;
		while ( ratePerTimeUnitCount<3 ) {
			ratePerTimeUnitCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessRole1InstanceSimulation","ratePerTimeUnit").createNewInstance(result);
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		BusinessRole1 instance = (BusinessRole1)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::BusinessRole1InstanceSimulation","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessRole1InstanceSimulation","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			PersonNode newVal = (PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessRole1InstanceSimulation","representedPerson").getNextReference();
			representedPersonCount++;
			if ( instance.getRepresentedPerson() == null ) {
				instance.setRepresentedPerson(newVal);
			}
		}
	}

}