package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class BusinessCalendarActualInstance2_6z3cLn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<BusinessCalendar> {
	static final public BusinessCalendar BUSINESSCALENDARACTUALINSTANCE2 = new BusinessCalendar();
	static final public BusinessCalendarActualInstance2_6z3cLn_JEeK5usaVqVCtXw INSTANCE = new BusinessCalendarActualInstance2_6z3cLn_JEeK5usaVqVCtXw();


	public BusinessCalendar createNewObject(CompositionNode parent) throws Exception {
		BusinessCalendar result = BUSINESSCALENDARACTUALINSTANCE2;
		result.init(parent);
		WorkDayActualInstance4_6z3cMX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		WorkDayActualInstance5_6z3cOn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		WorkDayActualInstance6_6z3cQ3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance4_6z3cTX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance5_6z3cVX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance6_6z3cXX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance4_6z3cZn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance5_6z3cbH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance6_6z3ccn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setBusinessDaysPerMonth(2);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		BusinessCalendar instance = (BusinessCalendar)in;
	}

}