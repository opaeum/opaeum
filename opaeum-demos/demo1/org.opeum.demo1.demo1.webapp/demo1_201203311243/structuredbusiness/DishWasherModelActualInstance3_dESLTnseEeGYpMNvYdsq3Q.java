package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherModelActualInstance3_dESLTnseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DishWasherModel DISHWASHERMODELACTUALINSTANCE3 = new DishWasherModel();
	static final public DishWasherModelActualInstance3_dESLTnseEeGYpMNvYdsq3Q INSTANCE = new DishWasherModelActualInstance3_dESLTnseEeGYpMNvYdsq3Q();


	public DishWasherModel createNewObject(CompositionNode parent) {
		DishWasherModel result = DISHWASHERMODELACTUALINSTANCE3;
		result.init(parent);
		DishWasherComponentActualInstance4_dESLUXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		DishWasherComponentActualInstance3_dESLWHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
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