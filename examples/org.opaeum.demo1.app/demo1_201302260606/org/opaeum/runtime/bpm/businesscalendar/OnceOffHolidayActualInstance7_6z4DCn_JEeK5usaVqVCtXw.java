package org.opaeum.runtime.bpm.businesscalendar;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OnceOffHolidayActualInstance7_6z4DCn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OnceOffHoliday> {
	static final public OnceOffHolidayActualInstance7_6z4DCn_JEeK5usaVqVCtXw INSTANCE = new OnceOffHolidayActualInstance7_6z4DCn_JEeK5usaVqVCtXw();
	static final public OnceOffHoliday ONCEOFFHOLIDAYACTUALINSTANCE7 = new OnceOffHoliday();


	public OnceOffHoliday createNewObject(CompositionNode parent) throws Exception {
		OnceOffHoliday result = ONCEOFFHOLIDAYACTUALINSTANCE7;
		result.init(parent);
		result.setName("name7");
		result.setDate((Date)new DateStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2006-9-5"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}