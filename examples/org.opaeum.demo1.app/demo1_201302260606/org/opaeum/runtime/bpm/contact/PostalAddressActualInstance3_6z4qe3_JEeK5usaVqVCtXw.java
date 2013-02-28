package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PostalAddressActualInstance3_6z4qe3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PostalAddress> {
	static final public PostalAddressActualInstance3_6z4qe3_JEeK5usaVqVCtXw INSTANCE = new PostalAddressActualInstance3_6z4qe3_JEeK5usaVqVCtXw();
	static final public PostalAddress POSTALADDRESSACTUALINSTANCE3 = new PostalAddress();


	public PostalAddress createNewObject(CompositionNode parent) throws Exception {
		PostalAddress result = POSTALADDRESSACTUALINSTANCE3;
		result.init(parent);
		result.setUnitNumber("unitNumber3");
		result.setComplexName("complexName3");
		result.setStreetName("streetName3");
		result.setStreetNumber("streetNumber3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PostalAddress instance = (PostalAddress)in;
	}

}