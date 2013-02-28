package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class CronExpressionInstanceSimulation_60QdgH_JEeK5usaVqVCtXw extends StructInstanceSimulation<CronExpression> {
	static final public CronExpressionInstanceSimulation_60QdgH_JEeK5usaVqVCtXw INSTANCE = new CronExpressionInstanceSimulation_60QdgH_JEeK5usaVqVCtXw();


	public CronExpression createNewObject() throws Exception {
		CronExpression result = new CronExpression();
		int minutesCount = 0;
		while ( minutesCount<1 ) {
			minutesCount++;
			result.setMinutes((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CronExpressionInstanceSimulation","minutes"));
		}
		int hoursCount = 0;
		while ( hoursCount<1 ) {
			hoursCount++;
			result.setHours((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CronExpressionInstanceSimulation","hours"));
		}
		int dayOfMonthCount = 0;
		while ( dayOfMonthCount<1 ) {
			dayOfMonthCount++;
			result.setDayOfMonth((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CronExpressionInstanceSimulation","dayOfMonth"));
		}
		int monthCount = 0;
		while ( monthCount<1 ) {
			monthCount++;
			result.setMonth((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CronExpressionInstanceSimulation","month"));
		}
		int dayOfWeekCount = 0;
		while ( dayOfWeekCount<1 ) {
			dayOfWeekCount++;
			result.setDayOfWeek((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::CronExpressionInstanceSimulation","dayOfWeek"));
		}
		return result;
	}
	
	public void populateReferences(Object in) throws Exception {
		CronExpression instance = (CronExpression)in;
	}

}