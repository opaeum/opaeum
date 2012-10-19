package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentActualInstance3_kpRhOZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceComponent APPLIANCECOMPONENTACTUALINSTANCE3 = new ApplianceComponent();
	static final public ApplianceComponentActualInstance3_kpRhOZT9EeGaNaT6nrkuVQ INSTANCE = new ApplianceComponentActualInstance3_kpRhOZT9EeGaNaT6nrkuVQ();


	public ApplianceComponent createNewObject(CompositionNode parent) throws ParseException {
		ApplianceComponent result = APPLIANCECOMPONENTACTUALINSTANCE3;
		result.init(parent);
		result.setPartNumber("partNumber3");
		result.setPrice(0.3333333333333333);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceComponent instance = (ApplianceComponent)in;
	}

}