package org.opaeum.runtime.bpm.businesscalendar;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OnceOffHolidayInstanceSimulation_kpu0IJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public OnceOffHoliday createNewObject(CompositionNode parent) throws ParseException {
		OnceOffHoliday result = new OnceOffHoliday();
		result.init(parent);
		int nameCount = 0;
		while ( nameCount<1 ) {
			nameCount++;
			result.setName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::OnceOffHolidayInstanceSimulation","name"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}