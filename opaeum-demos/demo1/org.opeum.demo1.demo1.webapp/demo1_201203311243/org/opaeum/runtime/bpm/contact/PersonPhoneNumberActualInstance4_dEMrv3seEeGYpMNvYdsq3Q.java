package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance4_dEMrv3seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public PersonPhoneNumberActualInstance4_dEMrv3seEeGYpMNvYdsq3Q INSTANCE = new PersonPhoneNumberActualInstance4_dEMrv3seEeGYpMNvYdsq3Q();
	static final private PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE4 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE4;
		result.init(parent);
		result.setPhoneNumber("08234368404");
		result.setType(PersonPhoneNumberType.FAX);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}