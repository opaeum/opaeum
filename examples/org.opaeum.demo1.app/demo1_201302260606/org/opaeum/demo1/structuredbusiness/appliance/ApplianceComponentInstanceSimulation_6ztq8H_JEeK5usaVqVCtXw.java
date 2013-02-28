package org.opaeum.demo1.structuredbusiness.appliance;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentInstanceSimulation_6ztq8H_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ApplianceComponent> {
	static final public ApplianceComponentInstanceSimulation_6ztq8H_JEeK5usaVqVCtXw INSTANCE = new ApplianceComponentInstanceSimulation_6ztq8H_JEeK5usaVqVCtXw();


	public ApplianceComponent createNewObject(CompositionNode parent) throws Exception {
		ApplianceComponent result = new ApplianceComponent();
		result.init(parent);
		int partNumberCount = 0;
		while ( partNumberCount<1 ) {
			partNumberCount++;
			result.setPartNumber((Integer)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::ApplianceComponentInstanceSimulation","partNumber"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ApplianceComponent instance = (ApplianceComponent)in;
	}

}