package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PostalAddressInstanceSimulation_dEXqwXseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {



	public PostalAddress createNewObject(CompositionNode parent) {
		PostalAddress result = new PostalAddress();
		int unitNumberCount = 0;
		while ( unitNumberCount<1 ) {
			unitNumberCount++;
			result.setUnitNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::PostalAddressInstanceSimulation","unitNumber"));
		}
		int complexNameCount = 0;
		while ( complexNameCount<1 ) {
			complexNameCount++;
			result.setComplexName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::PostalAddressInstanceSimulation","complexName"));
		}
		int streetNameCount = 0;
		while ( streetNameCount<1 ) {
			streetNameCount++;
			result.setStreetName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::PostalAddressInstanceSimulation","streetName"));
		}
		int streetNumberCount = 0;
		while ( streetNumberCount<1 ) {
			streetNumberCount++;
			result.setStreetNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::PostalAddressInstanceSimulation","streetNumber"));
		}
		int Property1Count = 0;
		while ( Property1Count<1 ) {
			Property1Count++;
			result.setProperty1((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::PostalAddressInstanceSimulation","Property1"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PostalAddress instance = (PostalAddress)in;
	}

}