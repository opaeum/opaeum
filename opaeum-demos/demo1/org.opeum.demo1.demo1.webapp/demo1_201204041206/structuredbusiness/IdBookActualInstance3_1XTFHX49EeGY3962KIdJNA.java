package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.DocumentType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class IdBookActualInstance3_1XTFHX49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final private IdBook IDBOOKACTUALINSTANCE3 = new IdBook();
	static final public IdBookActualInstance3_1XTFHX49EeGY3962KIdJNA INSTANCE = new IdBookActualInstance3_1XTFHX49EeGY3962KIdJNA();


	public IdBook createNewObject(CompositionNode parent) {
		IdBook result = IDBOOKACTUALINSTANCE3;
		result.init(parent);
		result.setDocumentType(DocumentType.SPREADSHEET);
		result.setIdNumber("idNumber3");
		result.setFullNames("fullNames3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		IdBook instance = (IdBook)in;
	}

}