package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrderInstanceSimulation_1XZywH49EeGY3962KIdJNA extends EntityInstanceSimulation {



	public Order createNewObject(CompositionNode parent) {
		Order result = new Order();
		result.init(parent);
		int descriptionCount = 0;
		while ( descriptionCount<1 ) {
			descriptionCount++;
			result.setDescription((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::OrderInstanceSimulation","description"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Order instance = (Order)in;
		int dishWasherModelCount = 0;
		while ( dishWasherModelCount<1 ) {
			dishWasherModelCount++;
			instance.setDishWasherModel((DishWasherModel)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::OrderInstanceSimulation","dishWasherModel").getNextReference());
		}
	}

}