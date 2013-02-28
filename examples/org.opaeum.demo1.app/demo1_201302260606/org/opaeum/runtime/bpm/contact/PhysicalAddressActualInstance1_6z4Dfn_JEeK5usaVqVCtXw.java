package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PhysicalAddressActualInstance1_6z4Dfn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PhysicalAddress> {
	static final public PhysicalAddressActualInstance1_6z4Dfn_JEeK5usaVqVCtXw INSTANCE = new PhysicalAddressActualInstance1_6z4Dfn_JEeK5usaVqVCtXw();
	static final public PhysicalAddress PHYSICALADDRESSACTUALINSTANCE1 = new PhysicalAddress();


	public PhysicalAddress createNewObject(CompositionNode parent) throws Exception {
		PhysicalAddress result = PHYSICALADDRESSACTUALINSTANCE1;
		result.init(parent);
		result.setUnitNumber("unitNumber1");
		result.setComplexName("complexName1");
		result.setStreetName("streetName1");
		result.setStreetNumber("streetNumber1");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PhysicalAddress instance = (PhysicalAddress)in;
	}

}