package org.opaeum.runtime.bpm.costing;

import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class PricePerUnitInstanceSimulation_60ZncH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<PricePerUnit> {
	static final public PricePerUnitInstanceSimulation_60ZncH_JEeK5usaVqVCtXw INSTANCE = new PricePerUnitInstanceSimulation_60ZncH_JEeK5usaVqVCtXw();


	public PricePerUnit createNewObject(CompositionNode parent) throws Exception {
		PricePerUnit result = new PricePerUnit();
		result.init(parent);
		int additionalCostToCompanyCount = 0;
		while ( additionalCostToCompanyCount<1 ) {
			additionalCostToCompanyCount++;
			result.setAdditionalCostToCompany((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::PricePerUnitInstanceSimulation","additionalCostToCompany"));
		}
		int pricePaidByCustomerCount = 0;
		while ( pricePaidByCustomerCount<1 ) {
			pricePaidByCustomerCount++;
			result.setPricePaidByCustomer((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::PricePerUnitInstanceSimulation","pricePaidByCustomer"));
		}
		int pricePaidByCompanyCount = 0;
		while ( pricePaidByCompanyCount<1 ) {
			pricePaidByCompanyCount++;
			result.setPricePaidByCompany((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::PricePerUnitInstanceSimulation","pricePaidByCompany"));
		}
		int effectiveFromCount = 0;
		while ( effectiveFromCount<1 ) {
			effectiveFromCount++;
			result.setEffectiveFrom((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201302260606::PricePerUnitInstanceSimulation","effectiveFrom"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		PricePerUnit instance = (PricePerUnit)in;
	}

}