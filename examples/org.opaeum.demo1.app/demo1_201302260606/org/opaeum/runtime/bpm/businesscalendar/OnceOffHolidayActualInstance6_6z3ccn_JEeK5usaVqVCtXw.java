package org.opaeum.runtime.bpm.businesscalendar;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OnceOffHolidayActualInstance6_6z3ccn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OnceOffHoliday> {
	static final public OnceOffHolidayActualInstance6_6z3ccn_JEeK5usaVqVCtXw INSTANCE = new OnceOffHolidayActualInstance6_6z3ccn_JEeK5usaVqVCtXw();
	static final public OnceOffHoliday ONCEOFFHOLIDAYACTUALINSTANCE6 = new OnceOffHoliday();


	public OnceOffHoliday createNewObject(CompositionNode parent) throws Exception {
		OnceOffHoliday result = ONCEOFFHOLIDAYACTUALINSTANCE6;
		result.init(parent);
		result.setName("name6");
		result.setDate((Date)new DateStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2007-8-4"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}