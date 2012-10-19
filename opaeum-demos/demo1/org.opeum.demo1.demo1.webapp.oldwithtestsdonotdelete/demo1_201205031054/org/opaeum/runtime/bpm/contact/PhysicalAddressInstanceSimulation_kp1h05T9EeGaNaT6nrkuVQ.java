package org.opaeum.runtime.bpm.contact;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PhysicalAddressInstanceSimulation_kp1h05T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public PhysicalAddress createNewObject(CompositionNode parent) throws ParseException {
		PhysicalAddress result = new PhysicalAddress();
		int unitNumberCount = 0;
		while ( unitNumberCount<1 ) {
			unitNumberCount++;
			result.setUnitNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PhysicalAddressInstanceSimulation","unitNumber"));
		}
		int complexNameCount = 0;
		while ( complexNameCount<1 ) {
			complexNameCount++;
			result.setComplexName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PhysicalAddressInstanceSimulation","complexName"));
		}
		int streetNameCount = 0;
		while ( streetNameCount<1 ) {
			streetNameCount++;
			result.setStreetName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PhysicalAddressInstanceSimulation","streetName"));
		}
		int streetNumberCount = 0;
		while ( streetNumberCount<1 ) {
			streetNumberCount++;
			result.setStreetNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PhysicalAddressInstanceSimulation","streetNumber"));
		}
		int Property1Count = 0;
		while ( Property1Count<1 ) {
			Property1Count++;
			result.setProperty1((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PhysicalAddressInstanceSimulation","Property1"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		PhysicalAddress instance = (PhysicalAddress)in;
	}

}