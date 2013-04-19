package org.opaeum.runtime.bpm.organization;

import java.util.Date;
import java.util.Locale;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance5_6z4qVH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance6_6z4qW3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance10_6z4qPn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance11_6z4qRX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance12_6z4qTH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance9_6z4qN3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PhysicalAddressActualInstance3_6z4qhn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PostalAddressActualInstance3_6z4qe3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.LocaleStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance3_6z4qKn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonNode> {
	static final public PersonNodeActualInstance3_6z4qKn_JEeK5usaVqVCtXw INSTANCE = new PersonNodeActualInstance3_6z4qKn_JEeK5usaVqVCtXw();
	static final public PersonNode PERSONNODEACTUALINSTANCE3 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) throws Exception {
		PersonNode result = PERSONNODEACTUALINSTANCE3;
		result.init(parent);
		result.setPreferredNotificationType(NotificationType.EMAIL);
		result.setPreferredEMailAddressType(PersonEMailAddressType.WORK);
		result.setPreferredPhoneNumberType(PersonPhoneNumberType.HOME);
		result.setFirstName("firstName3");
		result.setSurname("surname3");
		PersonPhoneNumberActualInstance9_6z4qN3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance10_6z4qPn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance11_6z4qRX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance12_6z4qTH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance5_6z4qVH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance6_6z4qW3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance7_6z4qY3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance8_6z4qaX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance9_6z4qb3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken3");
		result.setUsername("username3");
		PostalAddressActualInstance3_6z4qe3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PhysicalAddressActualInstance3_6z4qhn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setRefreshToken("refreshToken3");
		result.setTokenExpiryDateTime((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2010-5-1 3:12:30"));
		result.setPreferredLocale((Locale)new LocaleStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("en_GB"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<3 ) {
			IBusinessRole newVal = (IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::person::PersonNodeContainedActualInstance::PersonNodeActualInstance3","businessRole").getNextReference();
			businessRoleCount++;
			if ( newVal != null && newVal.getRepresentedPerson() ==null ) {
				instance.addToBusinessRole(newVal);
			}
		}
		int businessActorCount = 0;
		while ( businessActorCount<3 ) {
			IBusinessActor newVal = (IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::person::PersonNodeContainedActualInstance::PersonNodeActualInstance3","businessActor").getNextReference();
			businessActorCount++;
			if ( newVal != null && newVal.getRepresentedPerson() ==null ) {
				instance.addToBusinessActor(newVal);
			}
		}
	}

}