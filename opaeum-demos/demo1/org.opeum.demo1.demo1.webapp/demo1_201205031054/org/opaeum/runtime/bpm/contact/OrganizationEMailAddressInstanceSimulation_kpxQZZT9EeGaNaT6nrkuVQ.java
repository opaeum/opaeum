package org.opaeum.runtime.bpm.contact;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationEMailAddressInstanceSimulation_kpxQZZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public OrganizationEMailAddress createNewObject(CompositionNode parent) throws ParseException {
		OrganizationEMailAddress result = new OrganizationEMailAddress();
		result.init(parent);
		int emailAddressCount = 0;
		while ( emailAddressCount<1 ) {
			emailAddressCount++;
			result.setEmailAddress((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::OrganizationEMailAddressInstanceSimulation","emailAddress"));
		}
		int typeCount = 0;
		while ( typeCount<1 ) {
			typeCount++;
			result.setType((OrganizationEMailAddressType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::OrganizationEMailAddressInstanceSimulation","type"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}