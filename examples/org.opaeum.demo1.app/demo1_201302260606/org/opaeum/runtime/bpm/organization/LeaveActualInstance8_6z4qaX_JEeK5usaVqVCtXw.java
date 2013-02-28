package org.opaeum.runtime.bpm.organization;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;

public class LeaveActualInstance8_6z4qaX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Leave> {
	static final public LeaveActualInstance8_6z4qaX_JEeK5usaVqVCtXw INSTANCE = new LeaveActualInstance8_6z4qaX_JEeK5usaVqVCtXw();
	static final public Leave LEAVEACTUALINSTANCE8 = new Leave();


	public Leave createNewObject(CompositionNode parent) throws Exception {
		Leave result = LEAVEACTUALINSTANCE8;
		result.init(parent);
		result.setFromDate((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2005-10-6 8:12:30"));
		result.setToDate((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2005-10-6 8:12:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Leave instance = (Leave)in;
	}

}