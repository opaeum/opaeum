package org.opaeum.demo1.structuredbusiness;

import java.util.Date;

import org.opaeum.demo1.structuredbusiness.appliance.ApplianceModelActualInstance1_6z5RP3_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.appliance.ApplianceModelActualInstance2_6z5RTX_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.appliance.ApplianceModelActualInstance3_6z5RW3_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.branch.BranchActualInstance1_6z4q4H_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.branch.BranchActualInstance2_6z4q8H_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.branch.BranchActualInstance3_6z5RLn_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.branch.ManagerActualInstance1_6z4qnn_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.branch.ManagerActualInstance2_6z4qsX_JEeK5usaVqVCtXw;
import org.opaeum.demo1.structuredbusiness.branch.ManagerActualInstance3_6z4qxH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.TextStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceDoctorActualInstance1_6z4qm3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ApplianceDoctor> {
	static final public ApplianceDoctor APPLIANCEDOCTORACTUALINSTANCE1 = new ApplianceDoctor();
	static final public ApplianceDoctorActualInstance1_6z4qm3_JEeK5usaVqVCtXw INSTANCE = new ApplianceDoctorActualInstance1_6z4qm3_JEeK5usaVqVCtXw();


	public ApplianceDoctor createNewObject(CompositionNode parent) throws Exception {
		ApplianceDoctor result = APPLIANCEDOCTORACTUALINSTANCE1;
		result.init(parent);
		ManagerActualInstance1_6z4qnn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		ManagerActualInstance2_6z4qsX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		ManagerActualInstance3_6z4qxH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setSupportNumber("08234368401");
		result.setSupportEMailAddress("john.doe@gmail.co1");
		result.setInitiationDatell((Date)new DateStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27"));
		result.setVatNumber("vatNumber1");
		BranchActualInstance1_6z4q4H_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		BranchActualInstance2_6z4q8H_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		BranchActualInstance3_6z5RLn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		ApplianceModelActualInstance1_6z5RP3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		ApplianceModelActualInstance2_6z5RTX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		ApplianceModelActualInstance3_6z5RW3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setName((String)new TextStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("This is an extremely LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG clob"));
		result.setProperty1((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27 1:24:30"));
		result.setAttribute1((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27 1:24:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ApplianceDoctor instance = (ApplianceDoctor)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1","participation") ) {
			Participation newVal = (Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1","participation").getNextReference();
			participationCount++;
			if ( newVal != null && newVal.getParticipant() ==null ) {
				instance.addToParticipation(newVal);
			}
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			OrganizationNode newVal = (OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::businessCollaboration::ApplianceCollaborationContainedActualInstance::ApplianceCollaborationActualInstance1::applianceDoctor::ApplianceDoctorContainedActualInstance::ApplianceDoctorActualInstance1","representedOrganization").getNextReference();
			representedOrganizationCount++;
			if ( instance.getRepresentedOrganization() == null ) {
				instance.setRepresentedOrganization(newVal);
			}
		}
	}

}