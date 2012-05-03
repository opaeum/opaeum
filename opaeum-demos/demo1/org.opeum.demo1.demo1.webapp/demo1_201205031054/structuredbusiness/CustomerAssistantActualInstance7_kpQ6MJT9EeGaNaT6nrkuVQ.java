package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class CustomerAssistantActualInstance7_kpQ6MJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private CustomerAssistant CUSTOMERASSISTANTACTUALINSTANCE7 = new CustomerAssistant();
	static final public CustomerAssistantActualInstance7_kpQ6MJT9EeGaNaT6nrkuVQ INSTANCE = new CustomerAssistantActualInstance7_kpQ6MJT9EeGaNaT6nrkuVQ();


	public CustomerAssistant createNewObject(CompositionNode parent) throws ParseException {
		CustomerAssistant result = CUSTOMERASSISTANTACTUALINSTANCE7;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		CustomerAssistant instance = (CustomerAssistant)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3::customerAssistant::null::CustomerAssistantActualInstance7","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3::customerAssistant::null::CustomerAssistantActualInstance7","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3::customerAssistant::null::CustomerAssistantActualInstance7","representedPerson").getNextReference());
		}
		int jobCount = 0;
		while ( jobCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3::customerAssistant::null::CustomerAssistantActualInstance7","job") ) {
			jobCount++;
			instance.addToJob((Job)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance3::customerAssistant::null::CustomerAssistantActualInstance7","job").getNextReference());
		}
	}

}