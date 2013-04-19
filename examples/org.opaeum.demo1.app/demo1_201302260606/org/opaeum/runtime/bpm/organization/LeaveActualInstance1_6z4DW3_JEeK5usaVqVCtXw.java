package org.opaeum.runtime.bpm.organization;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class LeaveActualInstance1_6z4DW3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Leave> {
	static final public LeaveActualInstance1_6z4DW3_JEeK5usaVqVCtXw INSTANCE = new LeaveActualInstance1_6z4DW3_JEeK5usaVqVCtXw();
	static final public Leave LEAVEACTUALINSTANCE1 = new Leave();


	public Leave createNewObject(CompositionNode parent) throws Exception {
		Leave result = LEAVEACTUALINSTANCE1;
		result.init(parent);
		result.setFromDate((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27 1:24:30"));
		result.setToDate((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27 1:24:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Leave instance = (Leave)in;
	}

}