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

public class PersonNodeActualInstance1_ko5ts5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public PersonNodeActualInstance1_ko5ts5T9EeGaNaT6nrkuVQ INSTANCE = new PersonNodeActualInstance1_ko5ts5T9EeGaNaT6nrkuVQ();
	static final private PersonNode PERSONNODEACTUALINSTANCE1 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) throws ParseException {
		PersonNode result = PERSONNODEACTUALINSTANCE1;
		result.init(parent);
		result.setFirstName("firstName1");
		result.setSurname("surname1");
		int phoneNumberCount = 0;
		while ( phoneNumberCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","phoneNumber") ) {
			phoneNumberCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","phoneNumber").createNewInstance(result);
		}
		int eMailAddressCount = 0;
		while ( eMailAddressCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","eMailAddress") ) {
			eMailAddressCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","eMailAddress").createNewInstance(result);
		}
		int leaveCount = 0;
		while ( leaveCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","leave") ) {
			leaveCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","leave").createNewInstance(result);
		}
		result.setAuthenticationToken("authenticationToken1");
		result.setUsername("ampieb@gmail.com");
		result.setRefreshToken("refreshToken1");
		result.setTokenExpiryDateTime((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2011-6-4 1:24:30"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","businessRole") ) {
			businessRoleCount++;
			instance.addToBusinessRole((IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","businessRole").getNextReference());
		}
		int postalAddressCount = 0;
		while ( postalAddressCount<1 ) {
			postalAddressCount++;
			instance.setPostalAddress((PostalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","postalAddress").getNextReference());
		}
		int physicalAddressCount = 0;
		while ( physicalAddressCount<1 ) {
			physicalAddressCount++;
			instance.setPhysicalAddress((PhysicalAddress)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","physicalAddress").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::person::null::PersonNodeActualInstance1","businessActor").getNextReference());
		}
	}

}