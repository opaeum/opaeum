package org.opaeum.demo1.structuredbusiness.jobs;

import java.util.Date;

import org.opaeum.demo1.structuredbusiness.branch.Technician;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ActivityInstanceSimulation_6zpZhn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Activity> {
	static final public ActivityInstanceSimulation_6zpZhn_JEeK5usaVqVCtXw INSTANCE = new ActivityInstanceSimulation_6zpZhn_JEeK5usaVqVCtXw();


	public Activity createNewObject(CompositionNode parent) throws Exception {
		Activity result = new Activity();
		result.init(parent);
		int dateOfWorkCount = 0;
		while ( dateOfWorkCount<1 ) {
			dateOfWorkCount++;
			result.setDateOfWork((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::ActivityInstanceSimulation","dateOfWork"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Activity instance = (Activity)in;
		int technicianCount = 0;
		while ( technicianCount<1 ) {
			Technician newVal = (Technician)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::ActivityInstanceSimulation","technician").getNextReference();
			technicianCount++;
			if ( instance.getTechnician() == null ) {
				instance.setTechnician(newVal);
			}
		}
	}

}