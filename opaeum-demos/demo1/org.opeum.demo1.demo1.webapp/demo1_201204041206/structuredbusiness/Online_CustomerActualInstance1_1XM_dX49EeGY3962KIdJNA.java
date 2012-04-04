package structuredbusiness;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class Online_CustomerActualInstance1_1XM_dX49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public Online_CustomerActualInstance1_1XM_dX49EeGY3962KIdJNA INSTANCE = new Online_CustomerActualInstance1_1XM_dX49EeGY3962KIdJNA();
	static final private Online_Customer ONLINE_CUSTOMERACTUALINSTANCE1 = new Online_Customer();


	public Online_Customer createNewObject(CompositionNode parent) {
		Online_Customer result = ONLINE_CUSTOMERACTUALINSTANCE1;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Online_Customer instance = (Online_Customer)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::Online CustomerActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::Online CustomerActualInstance1","participation").getNextReference());
		}
		int organizationCount = 0;
		while ( organizationCount<1 ) {
			organizationCount++;
			instance.setOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::Online CustomerActualInstance1","organization").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::Online CustomerActualInstance1","representedPerson").getNextReference());
		}
	}

}