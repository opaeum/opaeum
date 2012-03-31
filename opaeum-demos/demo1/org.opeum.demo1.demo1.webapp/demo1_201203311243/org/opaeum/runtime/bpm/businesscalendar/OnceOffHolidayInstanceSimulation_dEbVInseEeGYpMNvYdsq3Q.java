package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OnceOffHolidayInstanceSimulation_dEbVInseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {



	public OnceOffHoliday createNewObject(CompositionNode parent) {
		OnceOffHoliday result = new OnceOffHoliday();
		result.init(parent);
		int nameCount = 0;
		while ( nameCount<1 ) {
			nameCount++;
			result.setName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::OnceOffHolidayInstanceSimulation","name"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OnceOffHoliday instance = (OnceOffHoliday)in;
	}

}