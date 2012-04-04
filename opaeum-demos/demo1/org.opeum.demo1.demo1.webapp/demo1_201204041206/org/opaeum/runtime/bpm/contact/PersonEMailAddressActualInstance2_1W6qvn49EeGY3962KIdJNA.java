package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonEMailAddressActualInstance2_1W6qvn49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public PersonEMailAddressActualInstance2_1W6qvn49EeGY3962KIdJNA INSTANCE = new PersonEMailAddressActualInstance2_1W6qvn49EeGY3962KIdJNA();
	static final private PersonEMailAddress PERSONEMAILADDRESSACTUALINSTANCE2 = new PersonEMailAddress();


	public PersonEMailAddress createNewObject(CompositionNode parent) {
		PersonEMailAddress result = PERSONEMAILADDRESSACTUALINSTANCE2;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co2");
		result.setType(PersonEMailAddressType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}