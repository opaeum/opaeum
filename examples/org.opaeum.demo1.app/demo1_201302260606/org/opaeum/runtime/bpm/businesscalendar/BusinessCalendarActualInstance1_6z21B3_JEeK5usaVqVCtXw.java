package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class BusinessCalendarActualInstance1_6z21B3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<BusinessCalendar> {
	static final public BusinessCalendar BUSINESSCALENDARACTUALINSTANCE1 = new BusinessCalendar();
	static final public BusinessCalendarActualInstance1_6z21B3_JEeK5usaVqVCtXw INSTANCE = new BusinessCalendarActualInstance1_6z21B3_JEeK5usaVqVCtXw();


	public BusinessCalendar createNewObject(CompositionNode parent) throws Exception {
		BusinessCalendar result = BUSINESSCALENDARACTUALINSTANCE1;
		result.init(parent);
		WorkDayActualInstance1_6z21Cn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		WorkDayActualInstance2_6z21E3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		WorkDayActualInstance3_6z21HH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance1_6z21Jn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance2_6z21Ln_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance3_6z21Nn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance1_6z3b83_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance2_6z3b_X_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance3_6z3b_3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setBusinessDaysPerMonth(1);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		BusinessCalendar instance = (BusinessCalendar)in;
	}

}