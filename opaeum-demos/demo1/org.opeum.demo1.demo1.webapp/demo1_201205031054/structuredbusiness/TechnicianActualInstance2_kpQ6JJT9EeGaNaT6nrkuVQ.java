package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class TechnicianActualInstance2_kpQ6JJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public TechnicianActualInstance2_kpQ6JJT9EeGaNaT6nrkuVQ INSTANCE = new TechnicianActualInstance2_kpQ6JJT9EeGaNaT6nrkuVQ();
	static final private Technician TECHNICIANACTUALINSTANCE2 = new Technician();


	public Technician createNewObject(CompositionNode parent) throws ParseException {
		Technician result = TECHNICIANACTUALINSTANCE2;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Technician instance = (Technician)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2::technician::null::TechnicianActualInstance2","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2::technician::null::TechnicianActualInstance2","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceDoctorActualInstance1::branch::null::BranchActualInstance2::technician::null::TechnicianActualInstance2","representedPerson").getNextReference());
		}
	}

}