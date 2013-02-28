package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PhysicalAddressActualInstance2_6z4qGX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PhysicalAddress> {
	static final public PhysicalAddressActualInstance2_6z4qGX_JEeK5usaVqVCtXw INSTANCE = new PhysicalAddressActualInstance2_6z4qGX_JEeK5usaVqVCtXw();
	static final public PhysicalAddress PHYSICALADDRESSACTUALINSTANCE2 = new PhysicalAddress();


	public PhysicalAddress createNewObject(CompositionNode parent) throws Exception {
		PhysicalAddress result = PHYSICALADDRESSACTUALINSTANCE2;
		result.init(parent);
		result.setUnitNumber("unitNumber2");
		result.setComplexName("complexName2");
		result.setStreetName("streetName2");
		result.setStreetNumber("streetNumber2");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PhysicalAddress instance = (PhysicalAddress)in;
	}

}