package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class LeaveActualInstance4_1W7R4H49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public LeaveActualInstance4_1W7R4H49EeGY3962KIdJNA INSTANCE = new LeaveActualInstance4_1W7R4H49EeGY3962KIdJNA();
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