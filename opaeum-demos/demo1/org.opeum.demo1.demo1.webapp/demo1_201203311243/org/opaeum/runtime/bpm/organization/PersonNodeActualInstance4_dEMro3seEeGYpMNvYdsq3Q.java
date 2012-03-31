package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance1_dEMrx3seEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance2_dEMrz3seEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance1_dEMrqnseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance2_dEMrsXseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance3_dEMruHseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance4_dEMrv3seEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance4_dEMro3seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public PersonNodeActualInstance4_dEMro3seEeGYpMNvYdsq3Q INSTANCE = new PersonNodeActualInstance4_dEMro3seEeGYpMNvYdsq3Q();
	static final private PersonNode PERSONNODEACTUALINSTANCE4 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) {
		PersonNode result = PERSONNODEACTUALINSTANCE4;
		result.init(parent);
		result.setFirstName("firstName4");
		result.setSurname("surname4");
		PersonPhoneNumberActualInstance1_dEMrqnseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance2_dEMrsXseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance3_dEMruHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance4_dEMrv3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance1_dEMrx3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance2_dEMrz3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		LeaveActualInstance4_dEMr13seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		LeaveActualInstance3_dEMr3HseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken4");
		result.setUsername("username4");
		result.setRefreshToken("refreshToken4");
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessRole") ) {
			businessRoleCount++;
			instance.addToBusinessRole((IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessRole").getNextReference());
		}
		int postalAddressCount = 0;
		while ( postalAddressCount<1 ) {
			postalAddressCount++;
			instance.setPostalAddress((PostalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","postalAddress").getNextReference());
		}
		int physicalAddressCount = 0;
		while ( physicalAddressCount<1 ) {
			physicalAddressCount++;
			instance.setPhysicalAddress((PhysicalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","physicalAddress").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance4","businessActor").getNextReference());
		}
	}

}