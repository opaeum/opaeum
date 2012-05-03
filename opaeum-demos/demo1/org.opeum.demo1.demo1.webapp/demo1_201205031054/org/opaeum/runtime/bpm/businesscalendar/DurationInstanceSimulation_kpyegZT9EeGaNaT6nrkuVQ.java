package org.opaeum.runtime.bpm.businesscalendar;

import java.text.ParseException;
import java.util.Date;

import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.simulation.SimulationMetaData;
import org.opaeum.simulation.StructInstanceSimulation;

public class DurationInstanceSimulation_kpyegZT9EeGaNaT6nrkuVQ extends StructInstanceSimulation {



	public Duration createNewObject() throws ParseException {
		Duration result = new Duration();
		int timeUnitCount = 0;
		while ( timeUnitCount<1 ) {
			timeUnitCount++;
			result.setTimeUnit((BusinessTimeUnit)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::DurationInstanceSimulation","timeUnit"));
		}
		int quantityCount = 0;
		while ( quantityCount<1 ) {
			quantityCount++;
			result.setQuantity((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::DurationInstanceSimulation","quantity"));
		}
		int fromDateCount = 0;
		while ( fromDateCount<1 ) {
			fromDateCount++;
			result.setFromDate((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::DurationInstanceSimulation","fromDate"));
		}
		return result;
	}
	
	public void populateReferences(Object in) throws ParseException {
		Duration instance = (Duration)in;
	}

}