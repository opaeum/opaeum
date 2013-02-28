package org.opaeum.runtime.bpm.organization;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;

public class LeaveActualInstance3_6z4DZ3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Leave> {
	static final public LeaveActualInstance3_6z4DZ3_JEeK5usaVqVCtXw INSTANCE = new LeaveActualInstance3_6z4DZ3_JEeK5usaVqVCtXw();
	static final public Leave LEAVEACTUALINSTANCE3 = new Leave();


	public Leave createNewObject(CompositionNode parent) throws Exception {
		Leave result = LEAVEACTUALINSTANCE3;
		result.init(parent);
		result.setFromDate((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2010-5-1 3:12:30"));
		result.setToDate((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2010-5-1 3:12:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Leave instance = (Leave)in;
	}

}