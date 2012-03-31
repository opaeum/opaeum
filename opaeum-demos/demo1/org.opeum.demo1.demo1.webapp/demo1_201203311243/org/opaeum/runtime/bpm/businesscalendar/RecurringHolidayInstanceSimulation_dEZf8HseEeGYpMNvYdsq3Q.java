package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class RecurringHolidayInstanceSimulation_dEZf8HseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {



	public RecurringHoliday createNewObject(CompositionNode parent) {
		RecurringHoliday result = new RecurringHoliday();
		result.init(parent);
		int dayCount = 0;
		while ( dayCount<1 ) {
			dayCount++;
			result.setDay((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::RecurringHolidayInstanceSimulation","day"));
		}
		int monthCount = 0;
		while ( monthCount<1 ) {
			monthCount++;
			result.setMonth((Month)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::RecurringHolidayInstanceSimulation","month"));
		}
		int nameCount = 0;
		while ( nameCount<1 ) {
			nameCount++;
			result.setName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::RecurringHolidayInstanceSimulation","name"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		RecurringHoliday instance = (RecurringHoliday)in;
	}

}