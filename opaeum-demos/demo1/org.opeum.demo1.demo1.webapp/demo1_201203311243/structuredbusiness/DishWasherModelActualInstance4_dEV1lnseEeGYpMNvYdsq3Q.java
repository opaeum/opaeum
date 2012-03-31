package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishWasherModelActualInstance4_dEV1lnseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DishWasherModel DISHWASHERMODELACTUALINSTANCE4 = new DishWasherModel();
	static final public DishWasherModelActualInstance4_dEV1lnseEeGYpMNvYdsq3Q INSTANCE = new DishWasherModelActualInstance4_dEV1lnseEeGYpMNvYdsq3Q();


	public DishWasherModel createNewObject(CompositionNode parent) {
		DishWasherModel result = DISHWASHERMODELACTUALINSTANCE4;
		result.init(parent);
		DishWasherComponentActualInstance4_dEV1mXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		DishWasherComponentActualInstance3_dEV1oHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
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