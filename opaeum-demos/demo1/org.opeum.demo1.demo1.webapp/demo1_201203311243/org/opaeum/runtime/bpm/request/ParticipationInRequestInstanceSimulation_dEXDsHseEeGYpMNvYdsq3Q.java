package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInRequestInstanceSimulation_dEXDsHseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {



	public ParticipationInRequest createNewObject(CompositionNode parent) {
		ParticipationInRequest result = new ParticipationInRequest();
		result.init(parent);
		int kindCount = 0;
		while ( kindCount<1 ) {
			kindCount++;
			result.setKind((RequestParticipationKind)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::ParticipationInRequestInstanceSimulation","kind"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		ParticipationInRequest instance = (ParticipationInRequest)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			participantCount++;
			instance.setParticipant((Participant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::ParticipationInRequestInstanceSimulation","participant").getNextReference());
		}
	}

}