package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessNetworkFacilatatesCollaborationActualInstance1_dESySnseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final private BusinessNetworkFacilatatesCollaboration BUSINESSNETWORKFACILATATESCOLLABORATIONACTUALINSTANCE1 = new BusinessNetworkFacilatatesCollaboration();
	static final public BusinessNetworkFacilatatesCollaborationActualInstance1_dESySnseEeGYpMNvYdsq3Q INSTANCE = new BusinessNetworkFacilatatesCollaborationActualInstance1_dESySnseEeGYpMNvYdsq3Q();


	public BusinessNetworkFacilatatesCollaboration createNewObject(CompositionNode parent) {
		BusinessNetworkFacilatatesCollaboration result = BUSINESSNETWORKFACILATATESCOLLABORATIONACTUALINSTANCE1;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		BusinessNetworkFacilatatesCollaboration instance = (BusinessNetworkFacilatatesCollaboration)in;
	}

}