package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class AddressInstanceSimulation_dEauF3seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {



	public Address createNewObject(CompositionNode parent) {
		Address result = new Address();
		int unitNumberCount = 0;
		while ( unitNumberCount<1 ) {
			unitNumberCount++;
			result.setUnitNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::AddressInstanceSimulation","unitNumber"));
		}
		int complexNameCount = 0;
		while ( complexNameCount<1 ) {
			complexNameCount++;
			result.setComplexName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::AddressInstanceSimulation","complexName"));
		}
		int streetNameCount = 0;
		while ( streetNameCount<1 ) {
			streetNameCount++;
			result.setStreetName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::AddressInstanceSimulation","streetName"));
		}
		int streetNumberCount = 0;
		while ( streetNumberCount<1 ) {
			streetNumberCount++;
			result.setStreetNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::AddressInstanceSimulation","streetNumber"));
		}
		int Property1Count = 0;
		while ( Property1Count<1 ) {
			Property1Count++;
			result.setProperty1((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::AddressInstanceSimulation","Property1"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Address instance = (Address)in;
	}

}