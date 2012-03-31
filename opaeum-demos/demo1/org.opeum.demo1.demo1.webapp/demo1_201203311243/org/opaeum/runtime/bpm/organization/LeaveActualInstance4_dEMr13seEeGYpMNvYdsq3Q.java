package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class LeaveActualInstance4_dEMr13seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public LeaveActualInstance4_dEMr13seEeGYpMNvYdsq3Q INSTANCE = new LeaveActualInstance4_dEMr13seEeGYpMNvYdsq3Q();
	static final private Leave LEAVEACTUALINSTANCE4 = new Leave();


	public Leave createNewObject(CompositionNode parent) {
		Leave result = LEAVEACTUALINSTANCE4;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Leave instance = (Leave)in;
	}

}