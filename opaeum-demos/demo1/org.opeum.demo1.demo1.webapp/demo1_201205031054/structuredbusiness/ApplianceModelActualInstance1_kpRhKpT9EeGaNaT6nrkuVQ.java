package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceModelActualInstance1_kpRhKpT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceModel APPLIANCEMODELACTUALINSTANCE1 = new ApplianceModel();
	static final public ApplianceModelActualInstance1_kpRhKpT9EeGaNaT6nrkuVQ INSTANCE = new ApplianceModelActualInstance1_kpRhKpT9EeGaNaT6nrkuVQ();


	public ApplianceModel createNewObject(CompositionNode parent) throws ParseException {
		ApplianceModel result = APPLIANCEMODELACTUALINSTANCE1;
		result.init(parent);
		ApplianceComponentActualInstance1_kpRhLZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceComponentActualInstance2_kpRhM5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceComponentActualInstance3_kpRhOZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setName("name1");
		result.setPartNumber("partNumber1");
		result.setVendor(Vendor.WHIRLPOOL);
		result.setApplianceType(ApplianceType.DISHWASHER);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceModel instance = (ApplianceModel)in;
	}

}