package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PhysicalAddressInstanceSimulation_1Xf5Yn49EeGY3962KIdJNA extends EntityInstanceSimulation {



	public PhysicalAddress createNewObject(CompositionNode parent) {
		PhysicalAddress result = new PhysicalAddress();
		int unitNumberCount = 0;
		while ( unitNumberCount<1 ) {
			unitNumberCount++;
			result.setUnitNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::PhysicalAddressInstanceSimulation","unitNumber"));
		}
		int complexNameCount = 0;
		while ( complexNameCount<1 ) {
			complexNameCount++;
			result.setComplexName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::PhysicalAddressInstanceSimulation","complexName"));
		}
		int streetNameCount = 0;
		while ( streetNameCount<1 ) {
			streetNameCount++;
			result.setStreetName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::PhysicalAddressInstanceSimulation","streetName"));
		}
		int streetNumberCount = 0;
		while ( streetNumberCount<1 ) {
			streetNumberCount++;
			result.setStreetNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::PhysicalAddressInstanceSimulation","streetNumber"));
		}
		int Property1Count = 0;
		while ( Property1Count<1 ) {
			Property1Count++;
			result.setProperty1((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201204041206::PhysicalAddressInstanceSimulation","Property1"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PhysicalAddress instance = (PhysicalAddress)in;
	}

}