package structuredbusiness;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ManagerActualInstance3_dESLg3seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public ManagerActualInstance3_dESLg3seEeGYpMNvYdsq3Q INSTANCE = new ManagerActualInstance3_dESLg3seEeGYpMNvYdsq3Q();
	static final private Manager MANAGERACTUALINSTANCE3 = new Manager();


	public Manager createNewObject(CompositionNode parent) {
		Manager result = MANAGERACTUALINSTANCE3;
		result.init(parent);
		result.setName("name3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Manager instance = (Manager)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::DishwashersIncActualInstance1::manager::null::ManagerActualInstance3","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::manager::null::ManagerActualInstance3","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::manager::null::ManagerActualInstance3","representedPerson").getNextReference());
		}
	}

}