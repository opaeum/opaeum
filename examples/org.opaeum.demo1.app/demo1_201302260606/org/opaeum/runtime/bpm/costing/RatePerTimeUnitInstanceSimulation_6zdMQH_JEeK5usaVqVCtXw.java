package org.opaeum.runtime.bpm.costing;

import java.util.Date;

import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class RatePerTimeUnitInstanceSimulation_6zdMQH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<RatePerTimeUnit> {
	static final public RatePerTimeUnitInstanceSimulation_6zdMQH_JEeK5usaVqVCtXw INSTANCE = new RatePerTimeUnitInstanceSimulation_6zdMQH_JEeK5usaVqVCtXw();


	public RatePerTimeUnit createNewObject(CompositionNode parent) throws Exception {
		RatePerTimeUnit result = new RatePerTimeUnit();
		result.init(parent);
		int additionalCostToCompanyCount = 0;
		while ( additionalCostToCompanyCount<1 ) {
			additionalCostToCompanyCount++;
			result.setAdditionalCostToCompany((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::RatePerTimeUnitInstanceSimulation","additionalCostToCompany"));
		}
		int ratePaidByCustomerCount = 0;
		while ( ratePaidByCustomerCount<1 ) {
			ratePaidByCustomerCount++;
			result.setRatePaidByCustomer((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::RatePerTimeUnitInstanceSimulation","ratePaidByCustomer"));
		}
		int effectiveFromCount = 0;
		while ( effectiveFromCount<1 ) {
			effectiveFromCount++;
			result.setEffectiveFrom((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::RatePerTimeUnitInstanceSimulation","effectiveFrom"));
		}
		int ratePaidByCompanyCount = 0;
		while ( ratePaidByCompanyCount<1 ) {
			ratePaidByCompanyCount++;
			result.setRatePaidByCompany((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::RatePerTimeUnitInstanceSimulation","ratePaidByCompany"));
		}
		int timeUnitCount = 0;
		while ( timeUnitCount<1 ) {
			timeUnitCount++;
			result.setTimeUnit((BusinessTimeUnit)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::RatePerTimeUnitInstanceSimulation","timeUnit"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		RatePerTimeUnit instance = (RatePerTimeUnit)in;
	}

}