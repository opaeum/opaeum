package org.opaeum.runtime.bpm.organization;

import java.text.ParseException;
import java.util.Date;

import org.opaeum.runtime.bpm.contact.PersonEMailAddress;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumber;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance3_ko6U3JT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public PersonNodeActualInstance3_ko6U3JT9EeGaNaT6nrkuVQ INSTANCE = new PersonNodeActualInstance3_ko6U3JT9EeGaNaT6nrkuVQ();
	static final private PersonNode PERSONNODEACTUALINSTANCE3 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) throws ParseException {
		PersonNode result = PERSONNODEACTUALINSTANCE3;
		result.init(parent);
		result.setFirstName("firstName3");
		result.setSurname("surname3");
		int phoneNumberCount = 0;
		while ( phoneNumberCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","phoneNumber") ) {
			phoneNumberCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","phoneNumber").createNewInstance(result);
		}
		int eMailAddressCount = 0;
		while ( eMailAddressCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","eMailAddress") ) {
			eMailAddressCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","eMailAddress").createNewInstance(result);
		}
		int leaveCount = 0;
		while ( leaveCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","leave") ) {
			leaveCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","leave").createNewInstance(result);
		}
		result.setAuthenticationToken("authenticationToken3");
		result.setUsername("username3");
		result.setRefreshToken("refreshToken3");
		result.setTokenExpiryDateTime((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2009-8-6 3:12:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessRole") ) {
			businessRoleCount++;
			instance.addToBusinessRole((IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessRole").getNextReference());
		}
		int postalAddressCount = 0;
		while ( postalAddressCount<1 ) {
			postalAddressCount++;
			instance.setPostalAddress((PostalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","postalAddress").getNextReference());
		}
		int physicalAddressCount = 0;
		while ( physicalAddressCount<1 ) {
			physicalAddressCount++;
			instance.setPhysicalAddress((PhysicalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","physicalAddress").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance3","businessActor").getNextReference());
		}
	}

}