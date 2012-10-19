package org.opaeum.runtime.bpm.businesscalendar;

import java.text.ParseException;

import org.opaeum.runtime.bpm.opaeumsimpletypes.Month;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class RecurringHolidayInstanceSimulation_kplqMZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public RecurringHoliday createNewObject(CompositionNode parent) throws ParseException {
		RecurringHoliday result = new RecurringHoliday();
		result.init(parent);
		int dayCount = 0;
		while ( dayCount<1 ) {
			dayCount++;
			result.setDay((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::RecurringHolidayInstanceSimulation","day"));
		}
		int monthCount = 0;
		while ( monthCount<1 ) {
			monthCount++;
			result.setMonth((Month)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::RecurringHolidayInstanceSimulation","month"));
		}
		int nameCount = 0;
		while ( nameCount<1 ) {
			nameCount++;
			result.setName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::RecurringHolidayInstanceSimulation","name"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}