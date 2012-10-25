package org.opaeum.runtime.bpm.businesscalendar;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessCalendarInstanceSimulation_kpvbMJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public BusinessCalendar createNewObject(CompositionNode parent) throws ParseException {
		BusinessCalendar result = new BusinessCalendar();
		result.init(parent);
		int workDayCount = 0;
		while ( workDayCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessCalendarInstanceSimulation","workDay") ) {
			workDayCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessCalendarInstanceSimulation","workDay").createNewInstance(result);
		}
		int recurringHolidayCount = 0;
		while ( recurringHolidayCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessCalendarInstanceSimulation","recurringHoliday") ) {
			recurringHolidayCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessCalendarInstanceSimulation","recurringHoliday").createNewInstance(result);
		}
		int onceOffHolidayCount = 0;
		while ( onceOffHolidayCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessCalendarInstanceSimulation","onceOffHoliday") ) {
			onceOffHolidayCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessCalendarInstanceSimulation","onceOffHoliday").createNewInstance(result);
		}
		int businessHoursPerWeekCount = 0;
		while ( businessHoursPerWeekCount<1 ) {
			businessHoursPerWeekCount++;
			result.setBusinessHoursPerWeek((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::BusinessCalendarInstanceSimulation","businessHoursPerWeek"));
		}
		int businessDaysPerMonthCount = 0;
		while ( businessDaysPerMonthCount<1 ) {
			businessDaysPerMonthCount++;
			result.setBusinessDaysPerMonth((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::BusinessCalendarInstanceSimulation","businessDaysPerMonth"));
		}
		int businessHoursPerDayCount = 0;
		while ( businessHoursPerDayCount<1 ) {
			businessHoursPerDayCount++;
			result.setBusinessHoursPerDay((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::BusinessCalendarInstanceSimulation","businessHoursPerDay"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		BusinessCalendar instance = (BusinessCalendar)in;
	}

}