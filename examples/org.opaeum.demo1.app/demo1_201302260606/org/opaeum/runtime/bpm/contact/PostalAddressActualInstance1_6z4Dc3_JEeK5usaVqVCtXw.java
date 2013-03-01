package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PostalAddressActualInstance1_6z4Dc3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PostalAddress> {
	static final public PostalAddressActualInstance1_6z4Dc3_JEeK5usaVqVCtXw INSTANCE = new PostalAddressActualInstance1_6z4Dc3_JEeK5usaVqVCtXw();
	static final public PostalAddress POSTALADDRESSACTUALINSTANCE1 = new PostalAddress();


	public PostalAddress createNewObject(CompositionNode parent) throws Exception {
		PostalAddress result = POSTALADDRESSACTUALINSTANCE1;
		result.init(parent);
		result.setUnitNumber("unitNumber1");
		result.setComplexName("complexName1");
		result.setStreetName("streetName1");
		result.setStreetNumber("streetNumber1");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PostalAddress instance = (PostalAddress)in;
	}

}