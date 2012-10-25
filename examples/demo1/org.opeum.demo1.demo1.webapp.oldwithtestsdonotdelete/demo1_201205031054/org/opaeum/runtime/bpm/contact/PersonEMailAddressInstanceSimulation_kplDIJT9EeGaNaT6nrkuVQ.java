package org.opaeum.runtime.bpm.contact;

import java.text.ParseException;

import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonEMailAddressInstanceSimulation_kplDIJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public PersonEMailAddress createNewObject(CompositionNode parent) throws ParseException {
		PersonEMailAddress result = new PersonEMailAddress();
		result.init(parent);
		int emailAddressCount = 0;
		while ( emailAddressCount<1 ) {
			emailAddressCount++;
			result.setEmailAddress((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PersonEMailAddressInstanceSimulation","emailAddress"));
		}
		int typeCount = 0;
		while ( typeCount<1 ) {
			typeCount++;
			result.setType((PersonEMailAddressType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PersonEMailAddressInstanceSimulation","type"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		PersonEMailAddress instance = (PersonEMailAddress)in;
	}

}