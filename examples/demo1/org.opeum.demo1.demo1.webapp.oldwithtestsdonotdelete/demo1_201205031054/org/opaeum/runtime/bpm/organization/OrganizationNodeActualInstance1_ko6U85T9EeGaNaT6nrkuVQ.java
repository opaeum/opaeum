package org.opaeum.runtime.bpm.organization;

import java.text.ParseException;

import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddress;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationNodeActualInstance1_ko6U85T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final public OrganizationNodeActualInstance1_ko6U85T9EeGaNaT6nrkuVQ INSTANCE = new OrganizationNodeActualInstance1_ko6U85T9EeGaNaT6nrkuVQ();
	static final private OrganizationNode ORGANIZATIONNODEACTUALINSTANCE1 = new OrganizationNode();


	public OrganizationNode createNewObject(CompositionNode parent) throws ParseException {
		OrganizationNode result = ORGANIZATIONNODEACTUALINSTANCE1;
		result.init(parent);
		result.setName("name1");
		int phoneNumberCount = 0;
		while ( phoneNumberCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","phoneNumber") ) {
			phoneNumberCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","phoneNumber").createNewInstance(result);
		}
		int eMailAddressCount = 0;
		while ( eMailAddressCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","eMailAddress") ) {
			eMailAddressCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","eMailAddress").createNewInstance(result);
		}
		int businessCalendarCount = 0;
		while ( businessCalendarCount<1 ) {
			businessCalendarCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","businessCalendar").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		OrganizationNode instance = (OrganizationNode)in;
		int businessComponentCount = 0;
		while ( businessComponentCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","businessComponent") ) {
			businessComponentCount++;
			instance.addToBusinessComponent((IBusinessComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","businessComponent").getNextReference());
		}
		int businessActorCount = 0;
		while ( businessActorCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","businessActor") ) {
			businessActorCount++;
			instance.addToBusinessActor((IBusinessActor)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::BusinessNetworkActualInstance1::organization::null::OrganizationNodeActualInstance1","businessActor").getNextReference());
		}
	}

}