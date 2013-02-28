package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class PersonEMailAddressActualInstance2_6z4DU3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonEMailAddress> {
	static final public PersonEMailAddressActualInstance2_6z4DU3_JEeK5usaVqVCtXw INSTANCE = new PersonEMailAddressActualInstance2_6z4DU3_JEeK5usaVqVCtXw();
	static final public PersonEMailAddress PERSONEMAILADDRESSACTUALINSTANCE2 = new PersonEMailAddress();


	public PersonEMailAddress createNewObject(CompositionNode parent) throws Exception {
		PersonEMailAddress result = PERSONEMAILADDRESSACTUALINSTANCE2;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co2");
		result.setType(PersonEMailAddressType.WORK);
		result.setType(PersonEMailAddressType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}