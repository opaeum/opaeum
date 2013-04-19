package org.opaeum.runtime.bpm.organization;

import java.util.Date;
import java.util.Locale;

import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance1_6z4DTH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonEMailAddressActualInstance2_6z4DU3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance1_6z4DL3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance2_6z4DNn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance3_6z4DPX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberActualInstance4_6z4DRH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PhysicalAddressActualInstance1_6z4Dfn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.PostalAddressActualInstance1_6z4Dc3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.LocaleStrategyFactory;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonNodeActualInstance1_6z4DIn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PersonNode> {
	static final public PersonNodeActualInstance1_6z4DIn_JEeK5usaVqVCtXw INSTANCE = new PersonNodeActualInstance1_6z4DIn_JEeK5usaVqVCtXw();
	static final public PersonNode PERSONNODEACTUALINSTANCE1 = new PersonNode();


	public PersonNode createNewObject(CompositionNode parent) throws Exception {
		PersonNode result = PERSONNODEACTUALINSTANCE1;
		result.init(parent);
		result.setPreferredNotificationType(NotificationType.EMAIL);
		result.setPreferredEMailAddressType(PersonEMailAddressType.WORK);
		result.setPreferredPhoneNumberType(PersonPhoneNumberType.HOME);
		result.setFirstName("firstName1");
		result.setSurname("surname1");
		PersonPhoneNumberActualInstance1_6z4DL3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance2_6z4DNn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance3_6z4DPX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonPhoneNumberActualInstance4_6z4DRH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance1_6z4DTH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonEMailAddressActualInstance2_6z4DU3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance1_6z4DW3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance2_6z4DYX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		LeaveActualInstance3_6z4DZ3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setAuthenticationToken("authenticationToken1");
		result.setUsername("ampieb@gmail.com");
		PostalAddressActualInstance1_6z4Dc3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PhysicalAddressActualInstance1_6z4Dfn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.setRefreshToken("refreshToken1");
		result.setTokenExpiryDateTime((Date)new DateTimeStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("2012-3-27 1:24:30"));
		result.setPreferredLocale((Locale)new LocaleStrategyFactory().getStrategy(org.opaeum.runtime.strategy.FromStringConverter.class).fromString("en_ZA"));
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PersonNode instance = (PersonNode)in;
		int businessRoleCount = 0;
		while ( businessRoleCount<3 ) {
			IBusinessRole newVal = (IBusinessRole)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::person::PersonNodeContainedActualInstance::PersonNodeActualInstance1","businessRole").getNextReference();
			businessRoleCount++;
			if ( newVal != null && newVal.getRepresentedPerson() ==null ) {
				instance.addToBusinessRole(newVal);
			}
		}
		instance.addToBusinessRole(ocltests.BusinessRole1InstanceSimulation_60OoUn_JEeK5usaVqVCtXw.INSTANCE.getNextReference());
		instance.addToBusinessRole(org.opaeum.demo1.structuredbusiness.branch.ManagerActualInstance1_6z4qnn_JEeK5usaVqVCtXw.MANAGERACTUALINSTANCE1);
		instance.addToBusinessRole(org.opaeum.demo1.structuredbusiness.branch.CustomerAssistantInstanceSimulation_6zmWMH_JEeK5usaVqVCtXw.INSTANCE.getNextReference());
		instance.addToBusinessRole(org.opaeum.demo1.structuredbusiness.branch.TechnicianInstanceSimulation_6znkVH_JEeK5usaVqVCtXw.INSTANCE.getNextReference());
		int businessActorCount = 0;
		while ( businessActorCount<3 ) {
			IBusinessActor newVal = (IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::person::PersonNodeContainedActualInstance::PersonNodeActualInstance1","businessActor").getNextReference();
			businessActorCount++;
			if ( newVal != null && newVal.getRepresentedPerson() ==null ) {
				instance.addToBusinessActor(newVal);
			}
		}
	}

}