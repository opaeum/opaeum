package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInRequestInstanceSimulation_60V9En_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ParticipationInRequest> {
	static final public ParticipationInRequestInstanceSimulation_60V9En_JEeK5usaVqVCtXw INSTANCE = new ParticipationInRequestInstanceSimulation_60V9En_JEeK5usaVqVCtXw();


	public ParticipationInRequest createNewObject(CompositionNode parent) throws Exception {
		ParticipationInRequest result = new ParticipationInRequest();
		result.init(parent);
		int kindCount = 0;
		while ( kindCount<1 ) {
			kindCount++;
			result.setKind((RequestParticipationKind)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::ParticipationInRequestInstanceSimulation","kind"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ParticipationInRequest instance = (ParticipationInRequest)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			IParticipant newVal = (IParticipant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::ParticipationInRequestInstanceSimulation","participant").getNextReference();
			participantCount++;
			if ( instance.getParticipant() == null ) {
				instance.setParticipant(newVal);
			}
		}
	}

}