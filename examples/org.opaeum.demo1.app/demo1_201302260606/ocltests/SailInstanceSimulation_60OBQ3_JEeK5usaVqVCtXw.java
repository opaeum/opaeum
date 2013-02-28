package ocltests;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class SailInstanceSimulation_60OBQ3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Sail> {
	static final public SailInstanceSimulation_60OBQ3_JEeK5usaVqVCtXw INSTANCE = new SailInstanceSimulation_60OBQ3_JEeK5usaVqVCtXw();


	public Sail createNewObject(CompositionNode parent) throws Exception {
		Sail result = new Sail();
		result.init(parent);
		int sizeCount = 0;
		while ( sizeCount<1 ) {
			sizeCount++;
			result.setSize((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::SailInstanceSimulation","size"));
		}
		int sailPositionCount = 0;
		while ( sailPositionCount<1 ) {
			sailPositionCount++;
			result.setSailPosition((SailPosition)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::SailInstanceSimulation","sailPosition"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Sail instance = (Sail)in;
	}

}