package org.opaeum.runtime.bpm.request;

import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ParticipationInstanceSimulation_60V9EH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Participation> {
	static final public ParticipationInstanceSimulation_60V9EH_JEeK5usaVqVCtXw INSTANCE = new ParticipationInstanceSimulation_60V9EH_JEeK5usaVqVCtXw();


	public Participation createNewObject(CompositionNode parent) throws Exception {
		Participation result = new Participation();
		
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Participation instance = (Participation)in;
		int participantCount = 0;
		while ( participantCount<1 ) {
			IParticipant newVal = (IParticipant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::ParticipationInstanceSimulation","participant").getNextReference();
			participantCount++;
			if ( instance.getParticipant() == null ) {
				instance.setParticipant(newVal);
			}
		}
	}

}