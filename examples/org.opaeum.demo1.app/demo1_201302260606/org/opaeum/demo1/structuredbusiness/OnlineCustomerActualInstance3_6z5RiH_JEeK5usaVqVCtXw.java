package org.opaeum.demo1.structuredbusiness;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OnlineCustomerActualInstance3_6z5RiH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OnlineCustomer> {
	static final public OnlineCustomerActualInstance3_6z5RiH_JEeK5usaVqVCtXw INSTANCE = new OnlineCustomerActualInstance3_6z5RiH_JEeK5usaVqVCtXw();
	static final public OnlineCustomer ONLINECUSTOMERACTUALINSTANCE3 = new OnlineCustomer();


	public OnlineCustomer createNewObject(CompositionNode parent) throws Exception {
		OnlineCustomer result = ONLINECUSTOMERACTUALINSTANCE3;
		result.init(parent);
		result.setPreferredNotificationType(NotificationType.EMAIL);
		result.setPreferredEMailAddressType(PersonEMailAddressType.WORK);
		result.setPreferredPhoneNumberType(PersonPhoneNumberType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OnlineCustomer instance = (OnlineCustomer)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::onlineCustomer::OnlineCustomerContainedActualInstance::OnlineCustomerActualInstance3","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::onlineCustomer::OnlineCustomerContainedActualInstance::OnlineCustomerActualInstance3","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int organizationCount = 0;
		while ( organizationCount<1 ) {
			OrganizationNode newVal = (OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::onlineCustomer::OnlineCustomerContainedActualInstance::OnlineCustomerActualInstance3","organization").getNextReference();
			organizationCount++;
			if ( instance.getOrganization() == null ) {
				instance.setOrganization(newVal);
			}
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			PersonNode newVal = (PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::onlineCustomer::OnlineCustomerContainedActualInstance::OnlineCustomerActualInstance3","representedPerson").getNextReference();
			representedPersonCount++;
			if ( instance.getRepresentedPerson() == null ) {
				instance.setRepresentedPerson(newVal);
			}
		}
	}

}