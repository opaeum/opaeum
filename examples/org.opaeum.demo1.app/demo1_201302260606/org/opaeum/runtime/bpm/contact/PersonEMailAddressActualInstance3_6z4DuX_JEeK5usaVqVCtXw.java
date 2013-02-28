package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PersonEMailAddressActualInstance3_6z4DuX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonEMailAddress> {
	static final public PersonEMailAddressActualInstance3_6z4DuX_JEeK5usaVqVCtXw INSTANCE = new PersonEMailAddressActualInstance3_6z4DuX_JEeK5usaVqVCtXw();
	static final public PersonEMailAddress PERSONEMAILADDRESSACTUALINSTANCE3 = new PersonEMailAddress();


	public PersonEMailAddress createNewObject(CompositionNode parent) throws Exception {
		PersonEMailAddress result = PERSONEMAILADDRESSACTUALINSTANCE3;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co3");
		result.setType(PersonEMailAddressType.WORK);
		result.setType(PersonEMailAddressType.WORK);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}