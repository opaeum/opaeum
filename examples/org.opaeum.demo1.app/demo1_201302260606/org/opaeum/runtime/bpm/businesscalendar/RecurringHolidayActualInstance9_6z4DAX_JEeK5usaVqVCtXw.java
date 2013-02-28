package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.bpm.opaeumsimpletypes.Month;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class RecurringHolidayActualInstance9_6z4DAX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<RecurringHoliday> {
	static final public RecurringHolidayActualInstance9_6z4DAX_JEeK5usaVqVCtXw INSTANCE = new RecurringHolidayActualInstance9_6z4DAX_JEeK5usaVqVCtXw();
	static final public RecurringHoliday RECURRINGHOLIDAYACTUALINSTANCE9 = new RecurringHoliday();


	public RecurringHoliday createNewObject(CompositionNode parent) throws Exception {
		RecurringHoliday result = RECURRINGHOLIDAYACTUALINSTANCE9;
		result.init(parent);
		result.setDay(9);
		result.setMonth(Month.JANUARY);
		result.setName("name9");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}