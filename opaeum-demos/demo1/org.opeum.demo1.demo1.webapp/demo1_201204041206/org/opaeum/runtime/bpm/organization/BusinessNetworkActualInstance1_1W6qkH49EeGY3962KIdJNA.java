package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessNetworkActualInstance1_1W6qkH49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final private BusinessNetwork BUSINESSNETWORKACTUALINSTANCE1 = new BusinessNetwork();
	static final public BusinessNetworkActualInstance1_1W6qkH49EeGY3962KIdJNA INSTANCE = new BusinessNetworkActualInstance1_1W6qkH49EeGY3962KIdJNA();


	public BusinessNetwork createNewObject(CompositionNode parent) {
		BusinessNetwork result = BUSINESSNETWORKACTUALINSTANCE1;
		PersonNodeActualInstance4_1W6qk349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonNodeActualInstance3_1W7RrX49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance4_1W7R_H49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance3_1W7SHn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		BusinessNetwork instance = (BusinessNetwork)in;
	}

}