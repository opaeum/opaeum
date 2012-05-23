package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class SupplierActualInstance4_kpVykJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public SupplierActualInstance4_kpVykJT9EeGaNaT6nrkuVQ INSTANCE = new SupplierActualInstance4_kpVykJT9EeGaNaT6nrkuVQ();
	static final private Supplier SUPPLIERACTUALINSTANCE4 = new Supplier();


	public Supplier createNewObject(CompositionNode parent) throws ParseException {
		Supplier result = SUPPLIERACTUALINSTANCE4;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Supplier instance = (Supplier)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::SupplierActualInstance4","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::SupplierActualInstance4","participation").getNextReference());
		}
		int organizationCount = 0;
		while ( organizationCount<1 ) {
			organizationCount++;
			instance.setOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::SupplierActualInstance4","organization").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::SupplierActualInstance4","representedPerson").getNextReference());
		}
	}

}