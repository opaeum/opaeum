package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.bpm.opaeumsimpletypes.Month;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class RecurringHolidayActualInstance8_6z3cyH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<RecurringHoliday> {
	static final public RecurringHolidayActualInstance8_6z3cyH_JEeK5usaVqVCtXw INSTANCE = new RecurringHolidayActualInstance8_6z3cyH_JEeK5usaVqVCtXw();
	static final public RecurringHoliday RECURRINGHOLIDAYACTUALINSTANCE8 = new RecurringHoliday();


	public RecurringHoliday createNewObject(CompositionNode parent) throws Exception {
		RecurringHoliday result = RECURRINGHOLIDAYACTUALINSTANCE8;
		result.init(parent);
		result.setDay(8);
		result.setMonth(Month.JANUARY);
		result.setName("name8");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}