package org.opaeum.runtime.bpm.contact;

import java.text.ParseException;

import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PersonPhoneNumberInstanceSimulation_kpXnwJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public PersonPhoneNumber createNewObject(CompositionNode parent) throws ParseException {
		PersonPhoneNumber result = new PersonPhoneNumber();
		result.init(parent);
		int phoneNumberCount = 0;
		while ( phoneNumberCount<1 ) {
			phoneNumberCount++;
			result.setPhoneNumber((String)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PersonPhoneNumberInstanceSimulation","phoneNumber"));
		}
		int typeCount = 0;
		while ( typeCount<1 ) {
			typeCount++;
			result.setType((PersonPhoneNumberType)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::PersonPhoneNumberInstanceSimulation","type"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		PersonPhoneNumber instance = (PersonPhoneNumber)in;
	}

}