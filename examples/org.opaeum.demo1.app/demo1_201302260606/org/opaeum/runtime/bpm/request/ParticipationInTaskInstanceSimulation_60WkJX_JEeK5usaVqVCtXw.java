package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInTaskInstanceSimulation_60WkJX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ParticipationInTask> {
	static final public ParticipationInTaskInstanceSimulation_60WkJX_JEeK5usaVqVCtXw INSTANCE = new ParticipationInTaskInstanceSimulation_60WkJX_JEeK5usaVqVCtXw();


	public ParticipationInTask createNewObject(CompositionNode parent) throws Exception {
		ParticipationInTask result = new ParticipationInTask();
		result.init(parent);
		int kindCount = 0;
		while ( kindCount<1 ) {
			kindCount++;
			result.setKind((TaskParticipationKind)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::ParticipationInTaskInstanceSimulation","kind"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ParticipationInTask instance = (ParticipationInTask)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			IParticipant newVal = (IParticipant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::ParticipationInTaskInstanceSimulation","participant").getNextReference();
			participantCount++;
			if ( instance.getParticipant() == null ) {
				instance.setParticipant(newVal);
			}
		}
	}

}