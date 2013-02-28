package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PersonPhoneNumberActualInstance8_6z4DsX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonPhoneNumber> {
	static final public PersonPhoneNumberActualInstance8_6z4DsX_JEeK5usaVqVCtXw INSTANCE = new PersonPhoneNumberActualInstance8_6z4DsX_JEeK5usaVqVCtXw();
	static final public PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE8 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE8;
		result.init(parent);
		result.setPhoneNumber("08234368408");
		result.setType(PersonPhoneNumberType.HOME);
		result.setType(PersonPhoneNumberType.FAX);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}