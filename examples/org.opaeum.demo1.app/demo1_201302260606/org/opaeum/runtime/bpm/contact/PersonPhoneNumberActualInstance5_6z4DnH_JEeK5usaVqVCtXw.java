package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PersonPhoneNumberActualInstance5_6z4DnH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonPhoneNumber> {
	static final public PersonPhoneNumberActualInstance5_6z4DnH_JEeK5usaVqVCtXw INSTANCE = new PersonPhoneNumberActualInstance5_6z4DnH_JEeK5usaVqVCtXw();
	static final public PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE5 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE5;
		result.init(parent);
		result.setPhoneNumber("08234368405");
		result.setType(PersonPhoneNumberType.HOME);
		result.setType(PersonPhoneNumberType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}