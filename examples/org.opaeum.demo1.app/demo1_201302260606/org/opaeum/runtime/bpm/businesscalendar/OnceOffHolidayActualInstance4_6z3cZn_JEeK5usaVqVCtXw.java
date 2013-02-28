package org.opaeum.runtime.bpm.businesscalendar;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OnceOffHolidayActualInstance4_6z3cZn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OnceOffHoliday> {
	static final public OnceOffHolidayActualInstance4_6z3cZn_JEeK5usaVqVCtXw INSTANCE = new OnceOffHolidayActualInstance4_6z3cZn_JEeK5usaVqVCtXw();
	static final public OnceOffHoliday ONCEOFFHOLIDAYACTUALINSTANCE4 = new OnceOffHoliday();


	public OnceOffHoliday createNewObject(CompositionNode parent) throws Exception {
		OnceOffHoliday result = ONCEOFFHOLIDAYACTUALINSTANCE4;
		result.init(parent);
		result.setName("name4");
		result.setDate((Date)new DateStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2009-6-2"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}