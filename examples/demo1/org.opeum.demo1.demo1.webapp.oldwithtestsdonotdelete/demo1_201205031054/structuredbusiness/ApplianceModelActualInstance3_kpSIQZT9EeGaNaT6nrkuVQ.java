package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceModelActualInstance3_kpSIQZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceModel APPLIANCEMODELACTUALINSTANCE3 = new ApplianceModel();
	static final public ApplianceModelActualInstance3_kpSIQZT9EeGaNaT6nrkuVQ INSTANCE = new ApplianceModelActualInstance3_kpSIQZT9EeGaNaT6nrkuVQ();


	public ApplianceModel createNewObject(CompositionNode parent) throws ParseException {
		ApplianceModel result = APPLIANCEMODELACTUALINSTANCE3;
		result.init(parent);
		ApplianceComponentActualInstance7_kpSIRJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceComponentActualInstance8_kpSISpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceComponentActualInstance9_kpSIUJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setName("name3");
		result.setPartNumber("partNumber3");
		result.setVendor(Vendor.WHIRLPOOL);
		result.setApplianceType(ApplianceType.DISHWASHER);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceModel instance = (ApplianceModel)in;
	}

}