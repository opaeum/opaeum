package org.opaeum.runtime.bpm.contact;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationPhoneNumberInstanceSimulation_kpwpU5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public OrganizationPhoneNumber createNewObject(CompositionNode parent) throws ParseException {
		OrganizationPhoneNumber result = new OrganizationPhoneNumber();
		result.init(parent);
		int hponeNumberCount = 0;
		while ( hponeNumberCount<1 ) {
			hponeNumberCount++;
			result.setHponeNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::OrganizationPhoneNumberInstanceSimulation","hponeNumber"));
		}
		int typeCount = 0;
		while ( typeCount<1 ) {
			typeCount++;
			result.setType((OrganizationPhoneNumberType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::OrganizationPhoneNumberInstanceSimulation","type"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}