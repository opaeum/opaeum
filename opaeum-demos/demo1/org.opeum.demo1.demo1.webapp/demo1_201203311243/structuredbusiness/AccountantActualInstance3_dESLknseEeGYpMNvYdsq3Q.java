package structuredbusiness;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class AccountantActualInstance3_dESLknseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private Accountant ACCOUNTANTACTUALINSTANCE3 = new Accountant();
	static final public AccountantActualInstance3_dESLknseEeGYpMNvYdsq3Q INSTANCE = new AccountantActualInstance3_dESLknseEeGYpMNvYdsq3Q();


	public Accountant createNewObject(CompositionNode parent) {
		Accountant result = ACCOUNTANTACTUALINSTANCE3;
		result.init(parent);
		result.set__name("__name3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Accountant instance = (Accountant)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::DishwashersIncActualInstance1::accountant::null::AccountantActualInstance3","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::accountant::null::AccountantActualInstance3","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::accountant::null::AccountantActualInstance3","representedPerson").getNextReference());
		}
	}

}