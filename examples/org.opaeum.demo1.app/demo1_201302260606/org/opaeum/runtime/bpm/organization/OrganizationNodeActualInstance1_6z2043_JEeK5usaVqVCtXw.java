package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendarActualInstance1_6z21B3_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance1_6z2093_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance2_6z20_n_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance1_6z206H_JEeK5usaVqVCtXw;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance2_6z2073_JEeK5usaVqVCtXw;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationNodeActualInstance1_6z2043_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationNode> {
	static final public OrganizationNodeActualInstance1_6z2043_JEeK5usaVqVCtXw INSTANCE = new OrganizationNodeActualInstance1_6z2043_JEeK5usaVqVCtXw();
	static final public OrganizationNode ORGANIZATIONNODEACTUALINSTANCE1 = new OrganizationNode();


	public OrganizationNode createNewObject(CompositionNode parent) throws Exception {
		OrganizationNode result = ORGANIZATIONNODEACTUALINSTANCE1;
		result.init(parent);
		result.setName("name1");
		OrganizationPhoneNumberActualInstance1_6z206H_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationPhoneNumberActualInstance2_6z2073_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance1_6z2093_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance2_6z20_n_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		BusinessCalendarActualInstance1_6z21B3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationNode instance = (OrganizationNode)in;
		int businessComponentCount = 0;
		while ( businessComponentCount<3 ) {
			IBusinessComponent newVal = (IBusinessComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance1","businessComponent").getNextReference();
			businessComponentCount++;
			if ( newVal != null && newVal.getRepresentedOrganization() ==null ) {
				instance.addToBusinessComponent(newVal);
			}
		}
		int businessActorCount = 0;
		while ( businessActorCount<3 ) {
			IBusinessActor newVal = (IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::BusinessNetworkActualInstance1::organization::OrganizationNodeContainedActualInstance::OrganizationNodeActualInstance1","businessActor").getNextReference();
			businessActorCount++;
			if ( newVal != null && newVal.getOrganization() ==null ) {
				instance.addToBusinessActor(newVal);
			}
		}
	}

}