package structuredbusiness;

import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DocumentVerifierActualInstance3_dESLoXseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private DocumentVerifier DOCUMENTVERIFIERACTUALINSTANCE3 = new DocumentVerifier();
	static final public DocumentVerifierActualInstance3_dESLoXseEeGYpMNvYdsq3Q INSTANCE = new DocumentVerifierActualInstance3_dESLoXseEeGYpMNvYdsq3Q();


	public DocumentVerifier createNewObject(CompositionNode parent) {
		DocumentVerifier result = DOCUMENTVERIFIERACTUALINSTANCE3;
		result.init(parent);
		result.setName("name3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DocumentVerifier instance = (DocumentVerifier)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::DishwashersIncActualInstance1::documentVerifier::null::DocumentVerifierActualInstance3","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::documentVerifier::null::DocumentVerifierActualInstance3","participation").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::DishwashersIncActualInstance1::documentVerifier::null::DocumentVerifierActualInstance3","representedPerson").getNextReference());
		}
	}

}