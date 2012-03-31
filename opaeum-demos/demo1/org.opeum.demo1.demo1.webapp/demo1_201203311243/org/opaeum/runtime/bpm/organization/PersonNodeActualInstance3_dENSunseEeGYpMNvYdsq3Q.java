package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance1_dENS3nseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance2_dENS5XseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance1_dENSwXseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance2_dENSyHseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance3_dENSz3seEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance4_dENS1nseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance3_dENSunseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public PersonNodeActualInstance3_dENSunseEeGYpMNvYdsq3Q INSTANCE = new PersonNodeActualInstance3_dENSunseEeGYpMNvYdsq3Q();
	static final private PersonNode PERSONNODEACTUALINSTANCE3 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) {
		PersonNode result = PERSONNODEACTUALINSTANCE3;
		result.init(parent);
		result.setFirstName("firstName3");
		result.setSurname("surname3");
		PersonPhoneNumberActualInstance1_dENSwXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance2_dENSyHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance3_dENSz3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance4_dENS1nseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance1_dENS3nseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance2_dENS5XseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		LeaveActualInstance4_dENS7XseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		LeaveActualInstance3_dENS8nseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken3");
		result.setUsername("ampieb@gmail.com");
		result.setRefreshToken("refreshToken3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessRole") ) {
			businessRoleCount++;
			instance.addToBusinessRole((IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessRole").getNextReference());
		}
		int postalAddressCount = 0;
		while ( postalAddressCount<1 ) {
			postalAddressCount++;
			instance.setPostalAddress((PostalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","postalAddress").getNextReference());
		}
		int physicalAddressCount = 0;
		while ( physicalAddressCount<1 ) {
			physicalAddressCount++;
			instance.setPhysicalAddress((PhysicalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","physicalAddress").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessActor").getNextReference());
		}
	}

}