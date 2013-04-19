package structuredbusiness;

import java.text.ParseException;
import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentSaleInstanceSimulation_kprw0JT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public ApplianceComponentSale createNewObject(CompositionNode parent) throws ParseException {
		ApplianceComponentSale result = new ApplianceComponentSale();
		result.init(parent);
		int dateOfSaleCount = 0;
		while ( dateOfSaleCount<1 ) {
			dateOfSaleCount++;
			result.setDateOfSale((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ApplianceComponentSaleInstanceSimulation","dateOfSale"));
		}
		int costToCustomerCount = 0;
		while ( costToCustomerCount<1 ) {
			costToCustomerCount++;
			result.setCostToCustomer((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ApplianceComponentSaleInstanceSimulation","costToCustomer"));
		}
		int costPriceOfComponentCount = 0;
		while ( costPriceOfComponentCount<1 ) {
			costPriceOfComponentCount++;
			result.setCostPriceOfComponent((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ApplianceComponentSaleInstanceSimulation","costPriceOfComponent"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceComponentSale instance = (ApplianceComponentSale)in;
		int applianceComponentCount = 0;
		while ( applianceComponentCount<1 ) {
			applianceComponentCount++;
			instance.setApplianceComponent((ApplianceComponent)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ApplianceComponentSaleInstanceSimulation","applianceComponent").getNextReference());
		}
	}

}