package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PhysicalAddressActualInstance3_6z4qhn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PhysicalAddress> {
	static final public PhysicalAddressActualInstance3_6z4qhn_JEeK5usaVqVCtXw INSTANCE = new PhysicalAddressActualInstance3_6z4qhn_JEeK5usaVqVCtXw();
	static final public PhysicalAddress PHYSICALADDRESSACTUALINSTANCE3 = new PhysicalAddress();


	public PhysicalAddress createNewObject(CompositionNode parent) throws Exception {
		PhysicalAddress result = PHYSICALADDRESSACTUALINSTANCE3;
		result.init(parent);
		result.setUnitNumber("unitNumber3");
		result.setComplexName("complexName3");
		result.setStreetName("streetName3");
		result.setStreetNumber("streetNumber3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PhysicalAddress instance = (PhysicalAddress)in;
	}

}