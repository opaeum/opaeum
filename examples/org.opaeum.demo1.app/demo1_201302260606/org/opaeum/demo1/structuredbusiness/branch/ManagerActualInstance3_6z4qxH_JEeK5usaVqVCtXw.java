package org.opaeum.demo1.structuredbusiness.branch;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ManagerActualInstance3_6z4qxH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Manager> {
	static final public ManagerActualInstance3_6z4qxH_JEeK5usaVqVCtXw INSTANCE = new ManagerActualInstance3_6z4qxH_JEeK5usaVqVCtXw();
	static final public Manager MANAGERACTUALINSTANCE3 = new Manager();


	public Manager createNewObject(CompositionNode parent) throws Exception {
		Manager result = MANAGERACTUALINSTANCE3;
		result.init(parent);
		result.setPreferredNotificationType(NotificationType.EMAIL);
		result.setPreferredEMailAddressType(PersonEMailAddressType.WORK);
		result.setPreferredPhoneNumberType(PersonPhoneNumberType.HOME);
		result.setName("name3");
		result.setYearsInPosition(3);
		result.setContactNumber("08234368403");
		int ratePerTimeUnitCount = 0;
		while ( ratePerTimeUnitCount<3 ) {
			ratePerTimeUnitCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1::manager::ManagerContainedActualInstance::ManagerActualInstance3","ratePerTimeUnit").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Manager instance = (Manager)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1::manager::ManagerContainedActualInstance::ManagerActualInstance3","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1::manager::ManagerContainedActualInstance::ManagerActualInstance3","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			PersonNode newVal = (PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1::manager::ManagerContainedActualInstance::ManagerActualInstance3","representedPerson").getNextReference();
			representedPersonCount++;
			if ( instance.getRepresentedPerson() == null ) {
				instance.setRepresentedPerson(newVal);
			}
		}
	}

}