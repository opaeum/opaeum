package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonEMailAddressActualInstance6_6z4qW3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonEMailAddress> {
	static final public PersonEMailAddressActualInstance6_6z4qW3_JEeK5usaVqVCtXw INSTANCE = new PersonEMailAddressActualInstance6_6z4qW3_JEeK5usaVqVCtXw();
	static final public PersonEMailAddress PERSONEMAILADDRESSACTUALINSTANCE6 = new PersonEMailAddress();


	public PersonEMailAddress createNewObject(CompositionNode parent) throws Exception {
		PersonEMailAddress result = PERSONEMAILADDRESSACTUALINSTANCE6;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co6");
		result.setType(PersonEMailAddressType.WORK);
		result.setType(PersonEMailAddressType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}