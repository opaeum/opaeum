package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ManagerActualInstance2_kpPsAZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public ManagerActualInstance2_kpPsAZT9EeGaNaT6nrkuVQ INSTANCE = new ManagerActualInstance2_kpPsAZT9EeGaNaT6nrkuVQ();
	static final private Manager MANAGERACTUALINSTANCE2 = new Manager();


	public Manager createNewObject(CompositionNode parent) throws ParseException {
		Manager result = MANAGERACTUALINSTANCE2;
		result.init(parent);
		result.setName("name2");
		result.setYearsInPosition("yearsInPosition2");
		result.setContactNumber("08234368402");
		result.setHourlyRate(0.5);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Manager instance = (Manager)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance2","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance2","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance2","representedPerson").getNextReference());
		}
	}

}