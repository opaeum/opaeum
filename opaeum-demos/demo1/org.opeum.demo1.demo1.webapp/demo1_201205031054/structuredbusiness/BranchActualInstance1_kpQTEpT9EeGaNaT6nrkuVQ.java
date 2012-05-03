package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BranchActualInstance1_kpQTEpT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private Branch BRANCHACTUALINSTANCE1 = new Branch();
	static final public BranchActualInstance1_kpQTEpT9EeGaNaT6nrkuVQ INSTANCE = new BranchActualInstance1_kpQTEpT9EeGaNaT6nrkuVQ();


	public Branch createNewObject(CompositionNode parent) throws ParseException {
		Branch result = BRANCHACTUALINSTANCE1;
		result.init(parent);
		CustomerAssistantActualInstance1_kpQTF5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		CustomerAssistantActualInstance2_kpQTHJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		CustomerAssistantActualInstance3_kpQTIZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setCity(City.JOHANNESBURG);
		TechnicianActualInstance1_kpQTKZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		int jobCount = 0;
		while ( jobCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance1","job") ) {
			jobCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance1","job").createNewInstance(result);
		}
		result.setName("name1");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Branch instance = (Branch)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance1","participation").getNextReference());
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			representedOrganizationCount++;
			instance.setRepresentedOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance1","representedOrganization").getNextReference());
		}
	}

}