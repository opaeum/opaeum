package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance2_6z4DNn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonPhoneNumber> {
	static final public PersonPhoneNumberActualInstance2_6z4DNn_JEeK5usaVqVCtXw INSTANCE = new PersonPhoneNumberActualInstance2_6z4DNn_JEeK5usaVqVCtXw();
	static final public PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE2 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE2;
		result.init(parent);
		result.setPhoneNumber("08234368402");
		result.setType(PersonPhoneNumberType.HOME);
		result.setType(PersonPhoneNumberType.CELL);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}