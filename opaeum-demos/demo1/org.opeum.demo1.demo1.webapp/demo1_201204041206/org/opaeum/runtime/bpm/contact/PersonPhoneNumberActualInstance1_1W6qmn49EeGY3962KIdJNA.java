package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance1_1W6qmn49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public PersonPhoneNumberActualInstance1_1W6qmn49EeGY3962KIdJNA INSTANCE = new PersonPhoneNumberActualInstance1_1W6qmn49EeGY3962KIdJNA();
	static final private PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE1 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE1;
		result.init(parent);
		result.setPhoneNumber("08234368401");
		result.setType(PersonPhoneNumberType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}