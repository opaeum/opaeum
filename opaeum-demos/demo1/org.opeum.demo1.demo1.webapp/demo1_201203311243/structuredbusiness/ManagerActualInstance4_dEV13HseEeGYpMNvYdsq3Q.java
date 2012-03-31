package structuredbusiness;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ManagerActualInstance4_dEV13HseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public ManagerActualInstance4_dEV13HseEeGYpMNvYdsq3Q INSTANCE = new ManagerActualInstance4_dEV13HseEeGYpMNvYdsq3Q();
	static final private Manager MANAGERACTUALINSTANCE4 = new Manager();


	public Manager createNewObject(CompositionNode parent) {
		Manager result = MANAGERACTUALINSTANCE4;
		result.init(parent);
		result.setName("name4");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Manager instance = (Manager)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::DishwashersIncActualInstance1::manager::null::ManagerActualInstance4","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::manager::null::ManagerActualInstance4","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::manager::null::ManagerActualInstance4","representedPerson").getNextReference());
		}
	}

}