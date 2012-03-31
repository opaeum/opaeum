package org.opaeum.runtime.bpm.organization;

import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance1_dENTP3seEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressActualInstance2_dENTRnseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance1_dENTMHseEeGYpMNvYdsq3Q;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberActualInstance2_dENTN3seEeGYpMNvYdsq3Q;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationNodeActualInstance3_dENTK3seEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public OrganizationNodeActualInstance3_dENTK3seEeGYpMNvYdsq3Q INSTANCE = new OrganizationNodeActualInstance3_dENTK3seEeGYpMNvYdsq3Q();
	static final private OrganizationNode ORGANIZATIONNODEACTUALINSTANCE3 = new OrganizationNode();


	public OrganizationNode createNewObject(CompositionNode parent) {
		OrganizationNode result = ORGANIZATIONNODEACTUALINSTANCE3;
		result.init(parent);
		result.setName("name3");
		OrganizationPhoneNumberActualInstance1_dENTMHseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		OrganizationPhoneNumberActualInstance2_dENTN3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance1_dENTP3seEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		OrganizationEMailAddressActualInstance2_dENTRnseEeGYpMNvYdsq3Q.INSTANCE.generateInstance(result);
		int businessCalendarCount = 0;
		while ( businessCalendarCount<1 ) {
			businessCalendarCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance3","businessCalendar").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OrganizationNode instance = (OrganizationNode)in;
		int businessComponentCount = 0;
		while ( businessComponentCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance3","businessComponent") ) {
			businessComponentCount++;
			instance.addToBusinessComponent((IBusinessComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance3","businessComponent").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201203311243::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance3","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance3","businessActor").getNextReference());
		}
	}

}