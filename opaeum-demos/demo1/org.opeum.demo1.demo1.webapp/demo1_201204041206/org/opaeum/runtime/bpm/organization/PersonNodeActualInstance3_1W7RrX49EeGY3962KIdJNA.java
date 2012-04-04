package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance1_1W7R0X49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance2_1W7R2H49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance1_1W7RtH49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance2_1W7Ru349EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance3_1W7Rwn49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance4_1W7RyX49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance3_1W7RrX49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public PersonNodeActualInstance3_1W7RrX49EeGY3962KIdJNA INSTANCE = new PersonNodeActualInstance3_1W7RrX49EeGY3962KIdJNA();
	static final private PersonNode PERSONNODEACTUALINSTANCE3 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) {
		PersonNode result = PERSONNODEACTUALINSTANCE3;
		result.init(parent);
		result.setFirstName("firstName3");
		result.setSurname("surname3");
		PersonPhoneNumberActualInstance1_1W7RtH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance2_1W7Ru349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance3_1W7Rwn49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance4_1W7RyX49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance1_1W7R0X49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance2_1W7R2H49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		LeaveActualInstance4_1W7R4H49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		LeaveActualInstance3_1W7R5X49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken3");
		result.setUsername("username3");
		result.setRefreshToken("refreshToken3");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessRole") ) {
			businessRoleCount++;
			instance.addToBusinessRole((IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessRole").getNextReference());
		}
		int postalAddressCount = 0;
		while ( postalAddressCount<1 ) {
			postalAddressCount++;
			instance.setPostalAddress((PostalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","postalAddress").getNextReference());
		}
		int physicalAddressCount = 0;
		while ( physicalAddressCount<1 ) {
			physicalAddressCount++;
			instance.setPhysicalAddress((PhysicalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","physicalAddress").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessActor").getNextReference());
		}
	}

}