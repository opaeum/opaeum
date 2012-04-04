package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance2_1W6qoX49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public PersonPhoneNumberActualInstance2_1W6qoX49EeGY3962KIdJNA INSTANCE = new PersonPhoneNumberActualInstance2_1W6qoX49EeGY3962KIdJNA();
	static final private PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE2 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE2;
		result.init(parent);
		result.setPhoneNumber("08234368402");
		result.setType(PersonPhoneNumberType.CELL);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}