package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class BusinessCalendarActualInstance3_6z3coX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<BusinessCalendar> {
	static final public BusinessCalendar BUSINESSCALENDARACTUALINSTANCE3 = new BusinessCalendar();
	static final public BusinessCalendarActualInstance3_6z3coX_JEeK5usaVqVCtXw INSTANCE = new BusinessCalendarActualInstance3_6z3coX_JEeK5usaVqVCtXw();


	public BusinessCalendar createNewObject(CompositionNode parent) throws Exception {
		BusinessCalendar result = BUSINESSCALENDARACTUALINSTANCE3;
		result.init(parent);
		WorkDayActualInstance7_6z3cpH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		WorkDayActualInstance8_6z3crX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		WorkDayActualInstance9_6z3ctn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance7_6z3cwH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance8_6z3cyH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		RecurringHolidayActualInstance9_6z4DAX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance7_6z4DCn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance8_6z4DEH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnceOffHolidayActualInstance9_6z4DFn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setBusinessDaysPerMonth(3);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		BusinessCalendar instance = (BusinessCalendar)in;
	}

}