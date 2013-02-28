package org.opaeum.runtime.bpm.businesscalendar;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OnceOffHolidayActualInstance3_6z3b_3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OnceOffHoliday> {
	static final public OnceOffHolidayActualInstance3_6z3b_3_JEeK5usaVqVCtXw INSTANCE = new OnceOffHolidayActualInstance3_6z3b_3_JEeK5usaVqVCtXw();
	static final public OnceOffHoliday ONCEOFFHOLIDAYACTUALINSTANCE3 = new OnceOffHoliday();


	public OnceOffHoliday createNewObject(CompositionNode parent) throws Exception {
		OnceOffHoliday result = ONCEOFFHOLIDAYACTUALINSTANCE3;
		result.init(parent);
		result.setName("name3");
		result.setDate((Date)new DateStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2010-5-1"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}