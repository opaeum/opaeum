package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceDoctorActualInstance1_kpPr8JT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceDoctor APPLIANCEDOCTORACTUALINSTANCE1 = new ApplianceDoctor();
	static final public ApplianceDoctorActualInstance1_kpPr8JT9EeGaNaT6nrkuVQ INSTANCE = new ApplianceDoctorActualInstance1_kpPr8JT9EeGaNaT6nrkuVQ();


	public ApplianceDoctor createNewObject(CompositionNode parent) throws ParseException {
		ApplianceDoctor result = APPLIANCEDOCTORACTUALINSTANCE1;
		result.init(parent);
		ManagerActualInstance1_kpPr9ZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ManagerActualInstance2_kpPsAZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ManagerActualInstance3_kpPsDZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setName("name1");
		result.setSupportNumber("08234368401");
		result.setSupportEMailAddress("john.doe@gmail.co1");
		result.setVatNumber("vatNumber1");
		BranchActualInstance1_kpQTEpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		BranchActualInstance2_kpQTMJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		BranchActualInstance3_kpQ6K5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceModelActualInstance1_kpRhKpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceModelActualInstance2_kpRhR5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceModelActualInstance3_kpSIQZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceDoctor instance = (ApplianceDoctor)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1","participation").getNextReference());
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			representedOrganizationCount++;
			instance.setRepresentedOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1","representedOrganization").getNextReference());
		}
	}

}