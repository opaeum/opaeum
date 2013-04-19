package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class Online_CustomerActualInstance4_kpWZp5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public Online_CustomerActualInstance4_kpWZp5T9EeGaNaT6nrkuVQ INSTANCE = new Online_CustomerActualInstance4_kpWZp5T9EeGaNaT6nrkuVQ();
	static final private Online_Customer ONLINE_CUSTOMERACTUALINSTANCE4 = new Online_Customer();


	public Online_Customer createNewObject(CompositionNode parent) throws ParseException {
		Online_Customer result = ONLINE_CUSTOMERACTUALINSTANCE4;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Online_Customer instance = (Online_Customer)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::Online CustomerActualInstance4","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::Online CustomerActualInstance4","participation").getNextReference());
		}
		int organizationCount = 0;
		while ( organizationCount<1 ) {
			organizationCount++;
			instance.setOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::Online CustomerActualInstance4","organization").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::Online CustomerActualInstance4","representedPerson").getNextReference());
		}
	}

}