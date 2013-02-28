package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PostalAddressActualInstance2_6z4D4H_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PostalAddress> {
	static final public PostalAddressActualInstance2_6z4D4H_JEeK5usaVqVCtXw INSTANCE = new PostalAddressActualInstance2_6z4D4H_JEeK5usaVqVCtXw();
	static final public PostalAddress POSTALADDRESSACTUALINSTANCE2 = new PostalAddress();


	public PostalAddress createNewObject(CompositionNode parent) throws Exception {
		PostalAddress result = POSTALADDRESSACTUALINSTANCE2;
		result.init(parent);
		result.setUnitNumber("unitNumber2");
		result.setComplexName("complexName2");
		result.setStreetName("streetName2");
		result.setStreetNumber("streetNumber2");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PostalAddress instance = (PostalAddress)in;
	}

}