package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherModelActualInstance4_dESLNnseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DishWasherModel DISHWASHERMODELACTUALINSTANCE4 = new DishWasherModel();
	static final public DishWasherModelActualInstance4_dESLNnseEeGYpMNvYdsq3Q INSTANCE = new DishWasherModelActualInstance4_dESLNnseEeGYpMNvYdsq3Q();


	public DishWasherModel createNewObject(CompositionNode parent) {
		DishWasherModel result = DISHWASHERMODELACTUALINSTANCE4;
		result.init(parent);
		DishWasherComponentActualInstance4_dESLOXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		DishWasherComponentActualInstance3_dESLQHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		result.setName("name4");
		result.setPartNumber("partNumber4");
		result.setVendor(Vendor.WHIRLPOOL);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DishWasherModel instance = (DishWasherModel)in;
	}

}