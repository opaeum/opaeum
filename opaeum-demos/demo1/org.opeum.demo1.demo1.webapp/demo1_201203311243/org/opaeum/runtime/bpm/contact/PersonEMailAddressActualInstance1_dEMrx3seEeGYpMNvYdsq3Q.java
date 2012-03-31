package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonEMailAddressActualInstance1_dEMrx3seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public PersonEMailAddressActualInstance1_dEMrx3seEeGYpMNvYdsq3Q INSTANCE = new PersonEMailAddressActualInstance1_dEMrx3seEeGYpMNvYdsq3Q();
	static final private PersonEMailAddress PERSONEMAILADDRESSACTUALINSTANCE1 = new PersonEMailAddress();


	public PersonEMailAddress createNewObject(CompositionNode parent) {
		PersonEMailAddress result = PERSONEMAILADDRESSACTUALINSTANCE1;
		result.init(parent);
		result.setEmailAddress("null");
		result.setEmailAddress("john.doe@gmail.co1");
		result.setType(PersonEMailAddressType.WORK);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}