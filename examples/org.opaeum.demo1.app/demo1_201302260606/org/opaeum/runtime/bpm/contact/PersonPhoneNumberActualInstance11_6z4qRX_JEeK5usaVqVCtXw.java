package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance11_6z4qRX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonPhoneNumber> {
	static final public PersonPhoneNumberActualInstance11_6z4qRX_JEeK5usaVqVCtXw INSTANCE = new PersonPhoneNumberActualInstance11_6z4qRX_JEeK5usaVqVCtXw();
	static final public PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE11 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE11;
		result.init(parent);
		result.setPhoneNumber("082343684011");
		result.setType(PersonPhoneNumberType.HOME);
		result.setType(PersonPhoneNumberType.WORK);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}