package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInstanceSimulation_1Xggen49EeGY3962KIdJNA extends EntityInstanceSimulation {



	public Participation createNewObject(CompositionNode parent) {
		Participation result = new Participation();
		
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		Participation instance = (Participation)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			participantCount++;
			instance.setParticipant((Participant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201204041206::ParticipationInstanceSimulation","participant").getNextReference());
		}
	}

}