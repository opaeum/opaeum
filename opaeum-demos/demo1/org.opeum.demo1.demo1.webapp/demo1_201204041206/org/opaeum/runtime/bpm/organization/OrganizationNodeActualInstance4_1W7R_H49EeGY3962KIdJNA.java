package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance1_1W7SDH49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance2_1W7SE349EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance1_1W7R_X49EeGY3962KIdJNA;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance2_1W7SBH49EeGY3962KIdJNA;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationNodeActualInstance4_1W7R_H49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public OrganizationNodeActualInstance4_1W7R_H49EeGY3962KIdJNA INSTANCE = new OrganizationNodeActualInstance4_1W7R_H49EeGY3962KIdJNA();
	static final private OrganizationNode ORGANIZATIONNODEACTUALINSTANCE4 = new OrganizationNode();


	public OrganizationNode createNewObject(CompositionNode parent) {
		OrganizationNode result = ORGANIZATIONNODEACTUALINSTANCE4;
		result.init(parent);
		result.setName("name4");
		OrganizationPhoneNumberActualInstance1_1W7R_X49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		OrganizationPhoneNumberActualInstance2_1W7SBH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance1_1W7SDH49EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance2_1W7SE349EeGY3962KIdJNA.INSTANCE.generateInstance(result);
		int businessCalendarCount = 0;
		while ( businessCalendarCount<1 ) {
			businessCalendarCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance4","businessCalendar").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OrganizationNode instance = (OrganizationNode)in;
		int businessComponentCount = 0;
		while ( businessComponentCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance4","businessComponent") ) {
			businessComponentCount++;
			instance.addToBusinessComponent((IBusinessComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance4","businessComponent").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201204041206::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance4","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance4","businessActor").getNextReference());
		}
	}

}