package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.bpm.opaeumsimpletypes.Month;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class RecurringHolidayActualInstance1_6z21Jn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<RecurringHoliday> {
	static final public RecurringHolidayActualInstance1_6z21Jn_JEeK5usaVqVCtXw INSTANCE = new RecurringHolidayActualInstance1_6z21Jn_JEeK5usaVqVCtXw();
	static final public RecurringHoliday RECURRINGHOLIDAYACTUALINSTANCE1 = new RecurringHoliday();


	public RecurringHoliday createNewObject(CompositionNode parent) throws Exception {
		RecurringHoliday result = RECURRINGHOLIDAYACTUALINSTANCE1;
		result.init(parent);
		result.setDay(1);
		result.setMonth(Month.JANUARY);
		result.setName("name1");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}