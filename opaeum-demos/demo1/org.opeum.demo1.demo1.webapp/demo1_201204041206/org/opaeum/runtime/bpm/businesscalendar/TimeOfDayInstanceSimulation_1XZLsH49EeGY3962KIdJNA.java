package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class TimeOfDayInstanceSimulation_1XZLsH49EeGY3962KIdJNA extends StructInstanceSimulation {



	public TimeOfDay createNewObject() {
		TimeOfDay result = new TimeOfDay();
		int hoursCount = 0;
		while ( hoursCount<1 ) {
			hoursCount++;
			result.setHours((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::TimeOfDayInstanceSimulation","hours"));
		}
		int minutesCount = 0;
		while ( minutesCount<1 ) {
			minutesCount++;
			result.setMinutes((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::TimeOfDayInstanceSimulation","minutes"));
		}
		return result;
	}
	
	public void populateReferences(Object in) {
		TimeOfDay instance = (TimeOfDay)in;
	}

}