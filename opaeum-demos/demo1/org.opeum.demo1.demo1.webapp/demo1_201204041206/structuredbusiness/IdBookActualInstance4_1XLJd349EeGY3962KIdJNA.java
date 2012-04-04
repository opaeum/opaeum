package structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.DocumentType;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class IdBookActualInstance4_1XLJd349EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final private IdBook IDBOOKACTUALINSTANCE4 = new IdBook();
	static final public IdBookActualInstance4_1XLJd349EeGY3962KIdJNA INSTANCE = new IdBookActualInstance4_1XLJd349EeGY3962KIdJNA();


	public IdBook createNewObject(CompositionNode parent) {
		IdBook result = IDBOOKACTUALINSTANCE4;
		result.init(parent);
		result.setDocumentType(DocumentType.SPREADSHEET);
		result.setIdNumber("idNumber4");
		result.setFullNames("fullNames4");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		IdBook instance = (IdBook)in;
	}

}