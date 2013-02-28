package org.opaeum.demo1.structuredbusiness.jobs;

import org.opaeum.demo1.structuredbusiness.branch.CustomerAssistant;
import org.opaeum.demo1.structuredbusiness.branch.Technician;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class JobInstanceSimulation_6zoycH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<Job> {
	static final public JobInstanceSimulation_6zoycH_JEeK5usaVqVCtXw INSTANCE = new JobInstanceSimulation_6zoycH_JEeK5usaVqVCtXw();


	public Job createNewObject(CompositionNode parent) throws Exception {
		Job result = new Job();
		result.init(parent);
		int applianceComponentSaleCount = 0;
		while ( applianceComponentSaleCount<3 ) {
			applianceComponentSaleCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::JobInstanceSimulation","applianceComponentSale").createNewInstance(result);
		}
		int activityCount = 0;
		while ( activityCount<3 ) {
			activityCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::JobInstanceSimulation","activity").createNewInstance(result);
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		Job instance = (Job)in;
		int customerAssistantCount = 0;
		while ( customerAssistantCount<1 ) {
			CustomerAssistant newVal = (CustomerAssistant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::JobInstanceSimulation","customerAssistant").getNextReference();
			customerAssistantCount++;
			if ( instance.getCustomerAssistant() == null ) {
				instance.setCustomerAssistant(newVal);
			}
		}
		int foremanCount = 0;
		while ( foremanCount<1 ) {
			Technician newVal = (Technician)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::JobInstanceSimulation","foreman").getNextReference();
			foremanCount++;
			if ( instance.getForeman() == null ) {
				instance.setForeman(newVal);
			}
		}
	}

}