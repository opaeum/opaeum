package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ManagerActualInstance3_kpPsDZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public ManagerActualInstance3_kpPsDZT9EeGaNaT6nrkuVQ INSTANCE = new ManagerActualInstance3_kpPsDZT9EeGaNaT6nrkuVQ();
	static final private Manager MANAGERACTUALINSTANCE3 = new Manager();


	public Manager createNewObject(CompositionNode parent) throws ParseException {
		Manager result = MANAGERACTUALINSTANCE3;
		result.init(parent);
		result.setName("name3");
		result.setYearsInPosition("yearsInPosition3");
		result.setContactNumber("08234368403");
		result.setHourlyRate(0.3333333333333333);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Manager instance = (Manager)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance3","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance3","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::manager::null::ManagerActualInstance3","representedPerson").getNextReference());
		}
	}

}