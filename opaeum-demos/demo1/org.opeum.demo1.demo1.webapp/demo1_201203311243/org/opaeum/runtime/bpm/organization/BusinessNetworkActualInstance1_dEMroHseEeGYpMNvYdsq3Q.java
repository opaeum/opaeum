package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessNetworkActualInstance1_dEMroHseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private BusinessNetwork BUSINESSNETWORKACTUALINSTANCE1 = new BusinessNetwork();
	static final public BusinessNetworkActualInstance1_dEMroHseEeGYpMNvYdsq3Q INSTANCE = new BusinessNetworkActualInstance1_dEMroHseEeGYpMNvYdsq3Q();


	public BusinessNetwork createNewObject(CompositionNode parent) {
		BusinessNetwork result = BUSINESSNETWORKACTUALINSTANCE1;
		PersonNodeActualInstance4_dEMro3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonNodeActualInstance3_dENSunseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance4_dENTBXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance3_dENTK3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		BusinessNetwork instance = (BusinessNetwork)in;
	}

}