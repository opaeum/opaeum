package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendarActualInstance2_6z3cLn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance3_6z3cHn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance4_6z3cJX_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance3_6z3cD3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance4_6z3cFn_JEeK5usaVqVCtXw;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationNodeActualInstance2_6z3cCn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationNode> {
	static final public OrganizationNodeActualInstance2_6z3cCn_JEeK5usaVqVCtXw INSTANCE = new OrganizationNodeActualInstance2_6z3cCn_JEeK5usaVqVCtXw();
	static final public OrganizationNode ORGANIZATIONNODEACTUALINSTANCE2 = new OrganizationNode();


	public OrganizationNode createNewObject(CompositionNode parent) throws Exception {
		OrganizationNode result = ORGANIZATIONNODEACTUALINSTANCE2;
		result.init(parent);
		result.setName("name2");
		OrganizationPhoneNumberActualInstance3_6z3cD3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationPhoneNumberActualInstance4_6z3cFn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance3_6z3cHn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance4_6z3cJX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		BusinessCalendarActualInstance2_6z3cLn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationNode instance = (OrganizationNode)in;
		int businessComponentCount = 0;
		while ( businessComponentCount<3 ) {
			IBusinessComponent newVal = (IBusinessComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance2","businessComponent").getNextReference();
			businessComponentCount++;
			if ( newVal != null && newVal.getRepresentedOrganization() ==null ) {
				instance.addToBusinessComponent(newVal);
			}
		}
		int businessActorCount = 0;
		while ( businessActorCount<3 ) {
			IBusinessActor newVal = (IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance2","businessActor").getNextReference();
			businessActorCount++;
			if ( newVal != null && newVal.getOrganization() ==null ) {
				instance.addToBusinessActor(newVal);
			}
		}
	}

}