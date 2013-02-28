package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.bpm.opaeumsimpletypes.Month;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class RecurringHolidayActualInstance4_6z3cTX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<RecurringHoliday> {
	static final public RecurringHolidayActualInstance4_6z3cTX_JEeK5usaVqVCtXw INSTANCE = new RecurringHolidayActualInstance4_6z3cTX_JEeK5usaVqVCtXw();
	static final public RecurringHoliday RECURRINGHOLIDAYACTUALINSTANCE4 = new RecurringHoliday();


	public RecurringHoliday createNewObject(CompositionNode parent) throws Exception {
		RecurringHoliday result = RECURRINGHOLIDAYACTUALINSTANCE4;
		result.init(parent);
		result.setDay(4);
		result.setMonth(Month.JANUARY);
		result.setName("name4");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}