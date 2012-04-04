package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance3_1W6qqH49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public PersonPhoneNumberActualInstance3_1W6qqH49EeGY3962KIdJNA INSTANCE = new PersonPhoneNumberActualInstance3_1W6qqH49EeGY3962KIdJNA();
	static final private PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE3 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE3;
		result.init(parent);
		result.setPhoneNumber("08234368403");
		result.setType(PersonPhoneNumberType.WORK);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}