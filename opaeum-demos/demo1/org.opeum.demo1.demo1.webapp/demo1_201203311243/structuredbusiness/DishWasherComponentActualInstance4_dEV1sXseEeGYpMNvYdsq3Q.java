package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherComponentActualInstance4_dEV1sXseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DishWasherComponent DISHWASHERCOMPONENTACTUALINSTANCE4 = new DishWasherComponent();
	static final public DishWasherComponentActualInstance4_dEV1sXseEeGYpMNvYdsq3Q INSTANCE = new DishWasherComponentActualInstance4_dEV1sXseEeGYpMNvYdsq3Q();


	public DishWasherComponent createNewObject(CompositionNode parent) {
		DishWasherComponent result = DISHWASHERCOMPONENTACTUALINSTANCE4;
		result.init(parent);
		result.setPartNumber("partNumber4");
		result.setPrice(0.25);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DishWasherComponent instance = (DishWasherComponent)in;
	}

}