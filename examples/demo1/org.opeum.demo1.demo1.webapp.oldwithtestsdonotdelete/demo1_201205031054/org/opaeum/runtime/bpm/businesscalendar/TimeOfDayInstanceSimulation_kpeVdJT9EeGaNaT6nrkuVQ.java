package org.opaeum.runtime.bpm.businesscalendar;

import java.text.ParseException;

import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class TimeOfDayInstanceSimulation_kpeVdJT9EeGaNaT6nrkuVQ extends StructInstanceSimulation {



	public TimeOfDay createNewObject() throws ParseException {
		TimeOfDay result = new TimeOfDay();
		int hoursCount = 0;
		while ( hoursCount<1 ) {
			hoursCount++;
			result.setHours((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::TimeOfDayInstanceSimulation","hours"));
		}
		int minutesCount = 0;
		while ( minutesCount<1 ) {
			minutesCount++;
			result.setMinutes((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::TimeOfDayInstanceSimulation","minutes"));
		}
		return result;
	}
	
	public void populateReferences(Object in) throws ParseException {
		TimeOfDay instance = (TimeOfDay)in;
	}

}