package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class JobInstanceSimulation_kps_8pT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public Job createNewObject(CompositionNode parent) throws ParseException {
		Job result = new Job();
		result.init(parent);
		int applianceComponentSaleCount = 0;
		while ( applianceComponentSaleCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::JobInstanceSimulation","applianceComponentSale") ) {
			applianceComponentSaleCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::JobInstanceSimulation","applianceComponentSale").createNewInstance(result);
		}
		int activityCount = 0;
		while ( activityCount<SimulationMetaData.getInstance().getNextPropertySize("demo1_201205031054::JobInstanceSimulation","activity") ) {
			activityCount++;
			SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::JobInstanceSimulation","activity").createNewInstance(result);
		}
		int totalCostCount = 0;
		while ( totalCostCount<1 ) {
			totalCostCount++;
			result.setTotalCost((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::JobInstanceSimulation","totalCost"));
		}
		int costToCompanyCount = 0;
		while ( costToCompanyCount<1 ) {
			costToCompanyCount++;
			result.setCostToCompany((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::JobInstanceSimulation","costToCompany"));
		}
		int timeInHoursCount = 0;
		while ( timeInHoursCount<1 ) {
			timeInHoursCount++;
			result.setTimeInHours((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::JobInstanceSimulation","timeInHours"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Job instance = (Job)in;
		int customerAssistantCount = 0;
		while ( customerAssistantCount<1 ) {
			customerAssistantCount++;
			instance.setCustomerAssistant((CustomerAssistant)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::JobInstanceSimulation","customerAssistant").getNextReference());
		}
		int foremanCount = 0;
		while ( foremanCount<1 ) {
			foremanCount++;
			instance.setForeman((Technician)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::JobInstanceSimulation","foreman").getNextReference());
		}
	}

}