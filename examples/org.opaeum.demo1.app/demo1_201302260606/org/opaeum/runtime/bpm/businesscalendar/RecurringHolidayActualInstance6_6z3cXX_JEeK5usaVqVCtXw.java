package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.bpm.opaeumsimpletypes.Month;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class RecurringHolidayActualInstance6_6z3cXX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<RecurringHoliday> {
	static final public RecurringHolidayActualInstance6_6z3cXX_JEeK5usaVqVCtXw INSTANCE = new RecurringHolidayActualInstance6_6z3cXX_JEeK5usaVqVCtXw();
	static final public RecurringHoliday RECURRINGHOLIDAYACTUALINSTANCE6 = new RecurringHoliday();


	public RecurringHoliday createNewObject(CompositionNode parent) throws Exception {
		RecurringHoliday result = RECURRINGHOLIDAYACTUALINSTANCE6;
		result.init(parent);
		result.setDay(6);
		result.setMonth(Month.JANUARY);
		result.setName("name6");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}