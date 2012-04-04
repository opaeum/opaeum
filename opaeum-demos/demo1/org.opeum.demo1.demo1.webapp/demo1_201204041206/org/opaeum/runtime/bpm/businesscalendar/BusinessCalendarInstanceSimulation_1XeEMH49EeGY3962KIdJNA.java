package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessCalendarInstanceSimulation_1XeEMH49EeGY3962KIdJNA extends EntityInstanceSimulation {



	public BusinessCalendar createNewObject(CompositionNode parent) {
		BusinessCalendar result = new BusinessCalendar();
		result.init(parent);
		int workDayCount = 0;
		while ( workDayCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessCalendarInstanceSimulation","workDay") ) {
			workDayCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessCalendarInstanceSimulation","workDay").createNewInstance(result);
		}
		int recurringHolidayCount = 0;
		while ( recurringHolidayCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessCalendarInstanceSimulation","recurringHoliday") ) {
			recurringHolidayCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessCalendarInstanceSimulation","recurringHoliday").createNewInstance(result);
		}
		int onceOffHolidayCount = 0;
		while ( onceOffHolidayCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessCalendarInstanceSimulation","onceOffHoliday") ) {
			onceOffHolidayCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessCalendarInstanceSimulation","onceOffHoliday").createNewInstance(result);
		}
		int businessHoursPerWeekCount = 0;
		while ( businessHoursPerWeekCount<1 ) {
			businessHoursPerWeekCount++;
			result.setBusinessHoursPerWeek((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::BusinessCalendarInstanceSimulation","businessHoursPerWeek"));
		}
		int businessDaysPerMonthCount = 0;
		while ( businessDaysPerMonthCount<1 ) {
			businessDaysPerMonthCount++;
			result.setBusinessDaysPerMonth((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::BusinessCalendarInstanceSimulation","businessDaysPerMonth"));
		}
		int businessHoursPerDayCount = 0;
		while ( businessHoursPerDayCount<1 ) {
			businessHoursPerDayCount++;
			result.setBusinessHoursPerDay((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::BusinessCalendarInstanceSimulation","businessHoursPerDay"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		BusinessCalendar instance = (BusinessCalendar)in;
	}

}