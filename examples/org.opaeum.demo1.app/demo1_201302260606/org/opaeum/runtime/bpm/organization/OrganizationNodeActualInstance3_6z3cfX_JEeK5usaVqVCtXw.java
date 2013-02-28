package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendarActualInstance3_6z3coX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance5_6z3ckX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance6_6z3cmH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance5_6z3cgn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance6_6z3ciX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationNodeActualInstance3_6z3cfX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationNode> {
	static final public OrganizationNodeActualInstance3_6z3cfX_JEeK5usaVqVCtXw INSTANCE = new OrganizationNodeActualInstance3_6z3cfX_JEeK5usaVqVCtXw();
	static final public OrganizationNode ORGANIZATIONNODEACTUALINSTANCE3 = new OrganizationNode();


	public OrganizationNode createNewObject(CompositionNode parent) throws Exception {
		OrganizationNode result = ORGANIZATIONNODEACTUALINSTANCE3;
		result.init(parent);
		result.setName("name3");
		OrganizationPhoneNumberActualInstance5_6z3cgn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationPhoneNumberActualInstance6_6z3ciX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance5_6z3ckX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance6_6z3cmH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		BusinessCalendarActualInstance3_6z3coX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationNode instance = (OrganizationNode)in;
		int businessComponentCount = 0;
		while ( businessComponentCount<3 ) {
			IBusinessComponent newVal = (IBusinessComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance3","businessComponent").getNextReference();
			businessComponentCount++;
			if ( newVal != null && newVal.getRepresentedOrganization() ==null ) {
				instance.addToBusinessComponent(newVal);
			}
		}
		int businessActorCount = 0;
		while ( businessActorCount<3 ) {
			IBusinessActor newVal = (IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance3","businessActor").getNextReference();
			businessActorCount++;
			if ( newVal != null && newVal.getOrganization() ==null ) {
				instance.addToBusinessActor(newVal);
			}
		}
	}

}