package org.opaeum.demo1.structuredbusiness.jobs;

import java.util.Date;

import org.opaeum.demo1.structuredbusiness.appliance.ApplianceComponent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentSaleInstanceSimulation_6zoyc3_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ApplianceComponentSale> {
	static final public ApplianceComponentSaleInstanceSimulation_6zoyc3_JEeK5usaVqVCtXw INSTANCE = new ApplianceComponentSaleInstanceSimulation_6zoyc3_JEeK5usaVqVCtXw();


	public ApplianceComponentSale createNewObject(CompositionNode parent) throws Exception {
		ApplianceComponentSale result = new ApplianceComponentSale();
		result.init(parent);
		int dateOfSaleCount = 0;
		while ( dateOfSaleCount<1 ) {
			dateOfSaleCount++;
			result.setDateOfSale((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::ApplianceComponentSaleInstanceSimulation","dateOfSale"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ApplianceComponentSale instance = (ApplianceComponentSale)in;
		int applianceComponentCount = 0;
		while ( applianceComponentCount<1 ) {
			ApplianceComponent newVal = (ApplianceComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201302260606::ApplianceComponentSaleInstanceSimulation","applianceComponent").getNextReference();
			applianceComponentCount++;
			if ( instance.getApplianceComponent() == null ) {
				instance.setApplianceComponent(newVal);
			}
		}
	}

}