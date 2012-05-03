package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceModelActualInstance2_kpRhR5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceModel APPLIANCEMODELACTUALINSTANCE2 = new ApplianceModel();
	static final public ApplianceModelActualInstance2_kpRhR5T9EeGaNaT6nrkuVQ INSTANCE = new ApplianceModelActualInstance2_kpRhR5T9EeGaNaT6nrkuVQ();


	public ApplianceModel createNewObject(CompositionNode parent) throws ParseException {
		ApplianceModel result = APPLIANCEMODELACTUALINSTANCE2;
		result.init(parent);
		ApplianceComponentActualInstance4_kpRhSpT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceComponentActualInstance5_kpRhUJT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		ApplianceComponentActualInstance6_kpSIM5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		result.setName("name2");
		result.setPartNumber("partNumber2");
		result.setVendor(Vendor.WHIRLPOOL);
		result.setApplianceType(ApplianceType.DISHWASHER);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceModel instance = (ApplianceModel)in;
	}

}