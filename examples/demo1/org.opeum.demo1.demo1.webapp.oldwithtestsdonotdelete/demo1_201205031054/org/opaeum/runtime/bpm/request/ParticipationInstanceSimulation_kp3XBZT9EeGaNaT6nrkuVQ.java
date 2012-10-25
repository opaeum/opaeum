package org.opaeum.runtime.bpm.request;

import java.text.ParseException;

import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInstanceSimulation_kp3XBZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public Participation createNewObject(CompositionNode parent) throws ParseException {
		Participation result = new Participation();
		
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Participation instance = (Participation)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			participantCount++;
			instance.setParticipant((Participant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ParticipationInstanceSimulation","participant").getNextReference());
		}
	}

}