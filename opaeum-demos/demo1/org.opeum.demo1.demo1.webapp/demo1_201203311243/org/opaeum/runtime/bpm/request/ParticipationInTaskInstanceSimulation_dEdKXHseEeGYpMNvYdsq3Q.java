package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInTaskInstanceSimulation_dEdKXHseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {



	public ParticipationInTask createNewObject(CompositionNode parent) {
		ParticipationInTask result = new ParticipationInTask();
		result.init(parent);
		int kindCount = 0;
		while ( kindCount<1 ) {
			kindCount++;
			result.setKind((TaskParticipationKind)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201203311243::ParticipationInTaskInstanceSimulation","kind"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		ParticipationInTask instance = (ParticipationInTask)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			participantCount++;
			instance.setParticipant((Participant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201203311243::ParticipationInTaskInstanceSimulation","participant").getNextReference());
		}
	}

}