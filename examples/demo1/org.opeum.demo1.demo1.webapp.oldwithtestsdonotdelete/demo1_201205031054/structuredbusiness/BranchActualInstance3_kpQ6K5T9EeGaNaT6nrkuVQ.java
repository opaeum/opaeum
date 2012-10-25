package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BranchActualInstance3_kpQ6K5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private Branch BRANCHACTUALINSTANCE3 = new Branch();
	static final public BranchActualInstance3_kpQ6K5T9EeGaNaT6nrkuVQ INSTANCE = new BranchActualInstance3_kpQ6K5T9EeGaNaT6nrkuVQ();


	public Branch createNewObject(CompositionNode parent) throws ParseException {
		Branch result = BRANCHACTUALINSTANCE3;
		result.init(parent);
		CustomerAssistantActualInstance7_kpQ6MJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		CustomerAssistantActualInstance8_kpQ6NZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		CustomerAssistantActualInstance9_kpQ6OpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setCity(City.JOHANNESBURG);
		TechnicianActualInstance3_kpRhIpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		int jobCount = 0;
		while ( jobCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3","job") ) {
			jobCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3","job").createNewInstance(result);
		}
		result.setName("name3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Branch instance = (Branch)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3","participation").getNextReference());
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			representedOrganizationCount++;
			instance.setRepresentedOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3","representedOrganization").getNextReference());
		}
	}

}