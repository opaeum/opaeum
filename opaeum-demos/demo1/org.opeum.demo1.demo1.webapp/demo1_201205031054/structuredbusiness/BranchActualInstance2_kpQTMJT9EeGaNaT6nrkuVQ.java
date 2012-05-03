package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BranchActualInstance2_kpQTMJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private Branch BRANCHACTUALINSTANCE2 = new Branch();
	static final public BranchActualInstance2_kpQTMJT9EeGaNaT6nrkuVQ INSTANCE = new BranchActualInstance2_kpQTMJT9EeGaNaT6nrkuVQ();


	public Branch createNewObject(CompositionNode parent) throws ParseException {
		Branch result = BRANCHACTUALINSTANCE2;
		result.init(parent);
		CustomerAssistantActualInstance4_kpQ6EpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		CustomerAssistantActualInstance5_kpQ6F5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		CustomerAssistantActualInstance6_kpQ6HJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setCity(City.JOHANNESBURG);
		TechnicianActualInstance2_kpQ6JJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		int jobCount = 0;
		while ( jobCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2","job") ) {
			jobCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2","job").createNewInstance(result);
		}
		result.setName("name2");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Branch instance = (Branch)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2","participation").getNextReference());
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			representedOrganizationCount++;
			instance.setRepresentedOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2","representedOrganization").getNextReference());
		}
	}

}