package ocltests;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BoatInstanceSimulation_60MzIH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Boat> {
	static final public BoatInstanceSimulation_60MzIH_JEeK5usaVqVCtXw INSTANCE = new BoatInstanceSimulation_60MzIH_JEeK5usaVqVCtXw();


	public Boat createNewObject(CompositionNode parent) throws Exception {
		Boat result = new Boat();
		int nameCount = 0;
		while ( nameCount<1 ) {
			nameCount++;
			result.setName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::BoatInstanceSimulation","name"));
		}
		int sailCount = 0;
		while ( sailCount<3 ) {
			sailCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BoatInstanceSimulation","sail").createNewInstance(result);
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Boat instance = (Boat)in;
	}

}