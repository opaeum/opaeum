package structuredbusiness;

import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class DishwashersIncActualInstance1_1XLJQH49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final private DishwashersInc DISHWASHERSINCACTUALINSTANCE1 = new DishwashersInc();
	static final public DishwashersIncActualInstance1_1XLJQH49EeGY3962KIdJNA INSTANCE = new DishwashersIncActualInstance1_1XLJQH49EeGY3962KIdJNA();


	public DishwashersInc createNewObject(CompositionNode parent) {
		DishwashersInc result = DISHWASHERSINCACTUALINSTANCE1;
		result.init(parent);
		DishWasherModelActualInstance4_1XLJRn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		DishWasherModelActualInstance3_1XLJXn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		IdBookActualInstance4_1XLJd349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		IdBookActualInstance3_1XLwVX49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		ManagerActualInstance4_1XLwYH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		ManagerActualInstance3_1XLwZ349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		AccountantActualInstance4_1XLwb349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		AccountantActualInstance3_1XLwdn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		result.setName("name1");
		result.setSupportNumber("08234368401");
		result.setSupportEMailAddress("john.doe@gmail.co1");
		result.setVatNumber(1);
		int orderCount = 0;
		while ( orderCount<1 ) {
			orderCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::DishwashersIncActualInstance1","order").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		DishwashersInc instance = (DishwashersInc)in;
		int participationCount = 0;
		while ( participationCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::DishwashersIncActualInstance1","participation") ) {
			participationCount++;
			instance.addToParticipation((Participation)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::DishwashersIncActualInstance1","participation").getNextReference());
		}
		int representedOrganizationCount = 0;
		while ( representedOrganizationCount<1 ) {
			representedOrganizationCount++;
			instance.setRepresentedOrganization((OrganizationNode)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::DishwashersIncActualInstance1","representedOrganization").getNextReference());
		}
	}

}