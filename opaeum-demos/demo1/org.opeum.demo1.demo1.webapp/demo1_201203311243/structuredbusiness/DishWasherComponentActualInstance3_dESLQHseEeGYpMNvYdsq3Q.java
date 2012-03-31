package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherComponentActualInstance3_dESLQHseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DishWasherComponent DISHWASHERCOMPONENTACTUALINSTANCE3 = new DishWasherComponent();
	static final public DishWasherComponentActualInstance3_dESLQHseEeGYpMNvYdsq3Q INSTANCE = new DishWasherComponentActualInstance3_dESLQHseEeGYpMNvYdsq3Q();


	public DishWasherComponent createNewObject(CompositionNode parent) {
		DishWasherComponent result = DISHWASHERCOMPONENTACTUALINSTANCE3;
		result.init(parent);
		result.setPartNumber("partNumber3");
		result.setPrice(0.3333333333333333);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DishWasherComponent instance = (DishWasherComponent)in;
	}

}