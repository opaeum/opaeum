package org.opaeum.runtime.bpm.request;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInRequestInstanceSimulation_kpaEA5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public ParticipationInRequest createNewObject(CompositionNode parent) throws ParseException {
		ParticipationInRequest result = new ParticipationInRequest();
		result.init(parent);
		int kindCount = 0;
		while ( kindCount<1 ) {
			kindCount++;
			result.setKind((RequestParticipationKind)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ParticipationInRequestInstanceSimulation","kind"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ParticipationInRequest instance = (ParticipationInRequest)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			participantCount++;
			instance.setParticipant((Participant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ParticipationInRequestInstanceSimulation","participant").getNextReference());
		}
	}

}