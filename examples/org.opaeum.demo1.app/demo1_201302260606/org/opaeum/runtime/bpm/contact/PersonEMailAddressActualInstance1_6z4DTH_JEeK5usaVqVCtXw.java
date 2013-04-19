package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonEMailAddressActualInstance1_6z4DTH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonEMailAddress> {
	static final public PersonEMailAddressActualInstance1_6z4DTH_JEeK5usaVqVCtXw INSTANCE = new PersonEMailAddressActualInstance1_6z4DTH_JEeK5usaVqVCtXw();
	static final public PersonEMailAddress PERSONEMAILADDRESSACTUALINSTANCE1 = new PersonEMailAddress();


	public PersonEMailAddress createNewObject(CompositionNode parent) throws Exception {
		PersonEMailAddress result = PERSONEMAILADDRESSACTUALINSTANCE1;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co1");
		result.setType(PersonEMailAddressType.WORK);
		result.setType(PersonEMailAddressType.WORK);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}