package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PersonPhoneNumberActualInstance3_6z4DPX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonPhoneNumber> {
	static final public PersonPhoneNumberActualInstance3_6z4DPX_JEeK5usaVqVCtXw INSTANCE = new PersonPhoneNumberActualInstance3_6z4DPX_JEeK5usaVqVCtXw();
	static final public PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE3 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE3;
		result.init(parent);
		result.setPhoneNumber("08234368403");
		result.setType(PersonPhoneNumberType.HOME);
		result.setType(PersonPhoneNumberType.WORK);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}