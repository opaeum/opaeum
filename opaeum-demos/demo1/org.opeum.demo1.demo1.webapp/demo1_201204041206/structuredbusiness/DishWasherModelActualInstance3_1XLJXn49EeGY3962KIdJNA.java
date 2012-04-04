package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherModelActualInstance3_1XLJXn49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final private DishWasherModel DISHWASHERMODELACTUALINSTANCE3 = new DishWasherModel();
	static final public DishWasherModelActualInstance3_1XLJXn49EeGY3962KIdJNA INSTANCE = new DishWasherModelActualInstance3_1XLJXn49EeGY3962KIdJNA();


	public DishWasherModel createNewObject(CompositionNode parent) {
		DishWasherModel result = DISHWASHERMODELACTUALINSTANCE3;
		result.init(parent);
		DishWasherComponentActualInstance4_1XLJYX49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		DishWasherComponentActualInstance3_1XLJaH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		result.setName("name3");
		result.setPartNumber("partNumber3");
		result.setVendor(Vendor.WHIRLPOOL);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DishWasherModel instance = (DishWasherModel)in;
	}

}