package structuredbusiness;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishwashersIncActualInstance1_dESLMHseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DishwashersInc DISHWASHERSINCACTUALINSTANCE1 = new DishwashersInc();
	static final public DishwashersIncActualInstance1_dESLMHseEeGYpMNvYdsq3Q INSTANCE = new DishwashersIncActualInstance1_dESLMHseEeGYpMNvYdsq3Q();


	public DishwashersInc createNewObject(CompositionNode parent) {
		DishwashersInc result = DISHWASHERSINCACTUALINSTANCE1;
		result.init(parent);
		DishWasherModelActualInstance4_dESLNnseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		DishWasherModelActualInstance3_dESLTnseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		IdBookActualInstance4_dESLZ3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		IdBookActualInstance3_dESLcXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		ManagerActualInstance4_dESLfHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		ManagerActualInstance3_dESLg3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		AccountantActualInstance4_dESLi3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		AccountantActualInstance3_dESLknseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		DocumentVerifierActualInstance4_dESLmnseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		DocumentVerifierActualInstance3_dESLoXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		result.setName("name1");
		result.setSupportNumber("08234368401");
		result.setSupportEMailAddress("john.doe@gmail.co1");
		result.setVatNumber(1);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DishwashersInc instance = (DishwashersInc)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::DishwashersIncActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1","participation").getNextReference());
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			representedOrganizationCount++;
			instance.setRepresentedOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1","representedOrganization").getNextReference());
		}
	}

}