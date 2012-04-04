package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance1_1W6qt349EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance2_1W6qvn49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance1_1W6qmn49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance2_1W6qoX49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance3_1W6qqH49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance4_1W6qr349EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance4_1W6qk349EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public PersonNodeActualInstance4_1W6qk349EeGY3962KIdJNA INSTANCE = new PersonNodeActualInstance4_1W6qk349EeGY3962KIdJNA();
	static final private PersonNode PERSONNODEACTUALINSTANCE4 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) {
		PersonNode result = PERSONNODEACTUALINSTANCE4;
		result.init(parent);
		result.setFirstName("firstName4");
		result.setSurname("surname4");
		PersonPhoneNumberActualInstance1_1W6qmn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance2_1W6qoX49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance3_1W6qqH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance4_1W6qr349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance1_1W6qt349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance2_1W6qvn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		LeaveActualInstance4_1W6qxn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		LeaveActualInstance3_1W6qy349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken4");
		result.setUsername("ampieb@gmail.com");
		result.setRefreshToken("refreshToken4");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessRole") ) {
			businessRoleCount++;
			instance.addToBusinessRole((IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessRole").getNextReference());
		}
		int postalAddressCount = 0;
		while ( postalAddressCount<1 ) {
			postalAddressCount++;
			instance.setPostalAddress((PostalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","postalAddress").getNextReference());
		}
		int physicalAddressCount = 0;
		while ( physicalAddressCount<1 ) {
			physicalAddressCount++;
			instance.setPhysicalAddress((PhysicalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","physicalAddress").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessActor").getNextReference());
		}
	}

}