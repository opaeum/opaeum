package structuredbusiness;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class SupplierActualInstance1_1XM_cH49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public SupplierActualInstance1_1XM_cH49EeGY3962KIdJNA INSTANCE = new SupplierActualInstance1_1XM_cH49EeGY3962KIdJNA();
	static final private Supplier SUPPLIERACTUALINSTANCE1 = new Supplier();


	public Supplier createNewObject(CompositionNode parent) {
		Supplier result = SUPPLIERACTUALINSTANCE1;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Supplier instance = (Supplier)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::SupplierActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::SupplierActualInstance1","participation").getNextReference());
		}
		int organizationCount = 0;
		while ( organizationCount<1 ) {
			organizationCount++;
			instance.setOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::SupplierActualInstance1","organization").getNextReference());
		}
		int representedPersonCount = 0;
		while ( representedPersonCount<1 ) {
			representedPersonCount++;
			instance.setRepresentedPerson((PersonNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::SupplierActualInstance1","representedPerson").getNextReference());
		}
	}

}