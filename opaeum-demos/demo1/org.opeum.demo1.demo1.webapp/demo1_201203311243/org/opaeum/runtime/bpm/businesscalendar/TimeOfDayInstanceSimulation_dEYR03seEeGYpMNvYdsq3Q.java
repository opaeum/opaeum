package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class TimeOfDayInstanceSimulation_dEYR03seEeGYpMNvYdsq3Q extends StructInstanceSimulation {



	public TimeOfDay createNewObject() {
		TimeOfDay result = new TimeOfDay();
		int hoursCount = 0;
		while ( hoursCount<1 ) {
			hoursCount++;
			result.setHours((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::TimeOfDayInstanceSimulation","hours"));
		}
		int minutesCount = 0;
		while ( minutesCount<1 ) {
			minutesCount++;
			result.setMinutes((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::TimeOfDayInstanceSimulation","minutes"));
		}
		return result;
	}
	
	public void populateReferences(Object in) {
		TimeOfDay instance = (TimeOfDay)in;
	}

}