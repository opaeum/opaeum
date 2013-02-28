package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class AddressInstanceSimulation_60XyQH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Address> {
	static final public AddressInstanceSimulation_60XyQH_JEeK5usaVqVCtXw INSTANCE = new AddressInstanceSimulation_60XyQH_JEeK5usaVqVCtXw();


	public Address createNewObject(CompositionNode parent) throws Exception {
		Address result = new Address();
		int unitNumberCount = 0;
		while ( unitNumberCount<1 ) {
			unitNumberCount++;
			result.setUnitNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::AddressInstanceSimulation","unitNumber"));
		}
		int complexNameCount = 0;
		while ( complexNameCount<1 ) {
			complexNameCount++;
			result.setComplexName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::AddressInstanceSimulation","complexName"));
		}
		int streetNameCount = 0;
		while ( streetNameCount<1 ) {
			streetNameCount++;
			result.setStreetName((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::AddressInstanceSimulation","streetName"));
		}
		int streetNumberCount = 0;
		while ( streetNumberCount<1 ) {
			streetNumberCount++;
			result.setStreetNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::AddressInstanceSimulation","streetNumber"));
		}
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Address instance = (Address)in;
	}

}