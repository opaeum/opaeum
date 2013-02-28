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

public class SupplierActualInstance1_6z5RlH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Supplier> {
	static final public SupplierActualInstance1_6z5RlH_JEeK5usaVqVCtXw INSTANCE = new SupplierActualInstance1_6z5RlH_JEeK5usaVqVCtXw();
	static final public Supplier SUPPLIERACTUALINSTANCE1 = new Supplier();


	public Supplier createNewObject(CompositionNode parent) throws Exception {
		Supplier result = SUPPLIERACTUALINSTANCE1;
		result.init(parent);
		result.setPreferredNotificationType(NotificationType.EMAIL);
		result.setPreferredEMailAddressType(PersonEMailAddressType.WORK);
		result.setPreferredPhoneNumberType(PersonPhoneNumberType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Supplier instance = (Supplier)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::supplier::SupplierContainedActualInstance::SupplierActualInstance1","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::supplier::SupplierContainedActualInstance::SupplierActualInstance1","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int organizationCount = 0;
		while ( organizationCount<1 ) {
			OrganizationNode newVal = (OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::supplier::SupplierContainedActualInstance::SupplierActualInstance1","organization").getNextReference();
			organizationCount++;
			if ( instance.getOrganization() == null ) {
				instance.setOrganization(newVal);
			}
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			PersonNode newVal = (PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::supplier::SupplierContainedActualInstance::SupplierActualInstance1","representedPerson").getNextReference();
			representedPersonCount++;
			if ( instance.getRepresentedPerson() == null ) {
				instance.setRepresentedPerson(newVal);
			}
		}
	}

}