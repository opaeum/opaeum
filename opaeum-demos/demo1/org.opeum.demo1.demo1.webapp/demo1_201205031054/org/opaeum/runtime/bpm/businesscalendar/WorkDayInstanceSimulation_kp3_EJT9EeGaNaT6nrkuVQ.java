package org.opaeum.runtime.bpm.businesscalendar;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class WorkDayInstanceSimulation_kp3_EJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public WorkDay createNewObject(CompositionNode parent) throws ParseException {
		WorkDay result = new WorkDay();
		result.init(parent);
		int startTimeCount = 0;
		while ( startTimeCount<1 ) {
			startTimeCount++;
			result.setStartTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201205031054::WorkDayInstanceSimulation","startTime").createNewInstance());
		}
		int endTimeCount = 0;
		while ( endTimeCount<1 ) {
			endTimeCount++;
			result.setEndTime((TimeOfDay)SimulationMetaData.getInstance().getStructValueProvider("demo1_201205031054::WorkDayInstanceSimulation","endTime").createNewInstance());
		}
		int kindCount = 0;
		while ( kindCount<1 ) {
			kindCount++;
			result.setKind((WorkDayKind)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::WorkDayInstanceSimulation","kind"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		WorkDay instance = (WorkDay)in;
	}

}