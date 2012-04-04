package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherModelActualInstance4_1XLJRn49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final private DishWasherModel DISHWASHERMODELACTUALINSTANCE4 = new DishWasherModel();
	static final public DishWasherModelActualInstance4_1XLJRn49EeGY3962KIdJNA INSTANCE = new DishWasherModelActualInstance4_1XLJRn49EeGY3962KIdJNA();


	public DishWasherModel createNewObject(CompositionNode parent) {
		DishWasherModel result = DISHWASHERMODELACTUALINSTANCE4;
		result.init(parent);
		DishWasherComponentActualInstance4_1XLJSX49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		DishWasherComponentActualInstance3_1XLJUH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
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