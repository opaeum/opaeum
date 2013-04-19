package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ManagerActualInstance1_kpPr9ZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public ManagerActualInstance1_kpPr9ZT9EeGaNaT6nrkuVQ INSTANCE = new ManagerActualInstance1_kpPr9ZT9EeGaNaT6nrkuVQ();
	static final private Manager MANAGERACTUALINSTANCE1 = new Manager();


	public Manager createNewObject(CompositionNode parent) throws ParseException {
		Manager result = MANAGERACTUALINSTANCE1;
		result.init(parent);
		result.setName("name1");
		result.setYearsInPosition("yearsInPosition1");
		result.setContactNumber("08234368401");
		result.setHourlyRate(1.0);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Manager instance = (Manager)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance1","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance1","representedPerson").getNextReference());
		}
	}

}