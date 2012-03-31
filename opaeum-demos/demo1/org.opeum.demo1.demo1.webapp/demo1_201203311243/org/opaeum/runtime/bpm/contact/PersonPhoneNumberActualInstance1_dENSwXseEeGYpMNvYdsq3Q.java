package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberActualInstance1_dENSwXseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public PersonPhoneNumberActualInstance1_dENSwXseEeGYpMNvYdsq3Q INSTANCE = new PersonPhoneNumberActualInstance1_dENSwXseEeGYpMNvYdsq3Q();
	static final private PersonPhoneNumber PERSONPHONENUMBERACTUALINSTANCE1 = new PersonPhoneNumber();


	public PersonPhoneNumber createNewObject(CompositionNode parent) {
		PersonPhoneNumber result = PERSONPHONENUMBERACTUALINSTANCE1;
		result.init(parent);
		result.setPhoneNumber("08234368401");
		result.setType(PersonPhoneNumberType.HOME);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}