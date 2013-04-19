package org.opaeum.runtime.bpm.organization;

import java.util.Date;
import java.util.Locale;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance3_6z4DuX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance4_6z4DwH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance5_6z4DnH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance6_6z4Do3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance7_6z4Dqn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance8_6z4DsX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PhysicalAddressActualInstance2_6z4qGX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PostalAddressActualInstance2_6z4D4H_JEeK5usaVqVCtXw;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.LocaleStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance2_6z4Dj3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonNode> {
	static final public PersonNodeActualInstance2_6z4Dj3_JEeK5usaVqVCtXw INSTANCE = new PersonNodeActualInstance2_6z4Dj3_JEeK5usaVqVCtXw();
	static final public PersonNode PERSONNODEACTUALINSTANCE2 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) throws Exception {
		PersonNode result = PERSONNODEACTUALINSTANCE2;
		result.init(parent);
		result.setPreferredNotificationType(NotificationType.EMAIL);
		result.setPreferredEMailAddressType(PersonEMailAddressType.WORK);
		result.setPreferredPhoneNumberType(PersonPhoneNumberType.HOME);
		result.setFirstName("firstName2");
		result.setSurname("surname2");
		PersonPhoneNumberActualInstance5_6z4DnH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance6_6z4Do3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance7_6z4Dqn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance8_6z4DsX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance3_6z4DuX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance4_6z4DwH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance4_6z4DyH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance5_6z4Dzn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance6_6z4D1H_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken2");
		result.setUsername("username2");
		PostalAddressActualInstance2_6z4D4H_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PhysicalAddressActualInstance2_6z4qGX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setRefreshToken("refreshToken2");
		result.setTokenExpiryDateTime((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2011-4-0 2:48:30"));
		result.setPreferredLocale((Locale)new LocaleStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("en_US"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<3 ) {
			IBusinessRole newVal = (IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::person::PersonNodeContainedActualInstance::PersonNodeActualInstance2","businessRole").getNextReference();
			businessRoleCount++;
			if ( newVal != null && newVal.getRepresentedPerson() ==null ) {
				instance.addToBusinessRole(newVal);
			}
		}
		int businessActorCount = 0;
		while ( businessActorCount<3 ) {
			IBusinessActor newVal = (IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::person::PersonNodeContainedActualInstance::PersonNodeActualInstance2","businessActor").getNextReference();
			businessActorCount++;
			if ( newVal != null && newVal.getRepresentedPerson() ==null ) {
				instance.addToBusinessActor(newVal);
			}
		}
	}

}