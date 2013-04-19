package structuredbusiness;

import java.text.ParseException;
import java.util.Date;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ActivityInstanceSimulation_kphYw5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {



	public Activity createNewObject(CompositionNode parent) throws ParseException {
		Activity result = new Activity();
		result.init(parent);
		int durationInHoursCount = 0;
		while ( durationInHoursCount<1 ) {
			durationInHoursCount++;
			result.setDurationInHours((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ActivityInstanceSimulation","durationInHours"));
		}
		int costToCustomerCount = 0;
		while ( costToCustomerCount<1 ) {
			costToCustomerCount++;
			result.setCostToCustomer((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ActivityInstanceSimulation","costToCustomer"));
		}
		int costToCompanyCount = 0;
		while ( costToCompanyCount<1 ) {
			costToCompanyCount++;
			result.setCostToCompany((Double)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ActivityInstanceSimulation","costToCompany"));
		}
		int dateOfWorkCount = 0;
		while ( dateOfWorkCount<1 ) {
			dateOfWorkCount++;
			result.setDateOfWork((Date)SimulationMetaData.getInstance().getNextValueForProperty("demo1_201205031054::ActivityInstanceSimulation","dateOfWork"));
		}
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		Activity instance = (Activity)in;
		int technicianCount = 0;
		while ( technicianCount<1 ) {
			technicianCount++;
			instance.setTechnician((Technician)SimulationMetaData.getInstance().getEntityValueProvider("demo1_201205031054::ActivityInstanceSimulation","technician").getNextReference());
		}
	}

}