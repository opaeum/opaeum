package org.opaeum.runtime.bpm.businesscalendar;

import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class DurationInstanceSimulation_dEb8NXseEeGYpMNvYdsq3Q extends StructInstanceSimulation {



	public Duration createNewObject() {
		Duration result = new Duration();
		int timeUnitCount = 0;
		while ( timeUnitCount<1 ) {
			timeUnitCount++;
			result.setTimeUnit((BusinessTimeUnit)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::DurationInstanceSimulation","timeUnit"));
		}
		int quantityCount = 0;
		while ( quantityCount<1 ) {
			quantityCount++;
			result.setQuantity((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::DurationInstanceSimulation","quantity"));
		}
		return result;
	}
	
	public void populateReferences(Object in) {
		Duration instance = (Duration)in;
	}

}