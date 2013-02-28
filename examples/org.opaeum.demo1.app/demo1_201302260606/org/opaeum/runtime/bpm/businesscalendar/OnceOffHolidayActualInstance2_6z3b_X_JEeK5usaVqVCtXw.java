package org.opaeum.runtime.bpm.businesscalendar;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OnceOffHolidayActualInstance2_6z3b_X_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OnceOffHoliday> {
	static final public OnceOffHolidayActualInstance2_6z3b_X_JEeK5usaVqVCtXw INSTANCE = new OnceOffHolidayActualInstance2_6z3b_X_JEeK5usaVqVCtXw();
	static final public OnceOffHoliday ONCEOFFHOLIDAYACTUALINSTANCE2 = new OnceOffHoliday();


	public OnceOffHoliday createNewObject(CompositionNode parent) throws Exception {
		OnceOffHoliday result = ONCEOFFHOLIDAYACTUALINSTANCE2;
		result.init(parent);
		result.setName("name2");
		result.setDate((Date)new DateStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2011-4-0"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}