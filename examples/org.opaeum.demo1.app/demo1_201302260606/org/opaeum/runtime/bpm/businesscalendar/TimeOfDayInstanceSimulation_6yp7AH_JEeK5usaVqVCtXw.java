package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class TimeOfDayInstanceSimulation_6yp7AH_JEeK5usaVqVCtXw extends StructInstanceSimulation<TimeOfDay> {
	static final public TimeOfDayInstanceSimulation_6yp7AH_JEeK5usaVqVCtXw INSTANCE = new TimeOfDayInstanceSimulation_6yp7AH_JEeK5usaVqVCtXw();


	public TimeOfDay createNewObject() throws Exception {
		TimeOfDay result = new TimeOfDay();
		int hoursCount = 0;
		while ( hoursCount<1 ) {
			hoursCount++;
			result.setHours((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::TimeOfDayInstanceSimulation","hours"));
		}
		int minutesCount = 0;
		while ( minutesCount<1 ) {
			minutesCount++;
			result.setMinutes((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::TimeOfDayInstanceSimulation","minutes"));
		}
		return result;
	}
	
	public void populateReferences(Object in) throws Exception {
		TimeOfDay instance = (TimeOfDay)in;
	}

}