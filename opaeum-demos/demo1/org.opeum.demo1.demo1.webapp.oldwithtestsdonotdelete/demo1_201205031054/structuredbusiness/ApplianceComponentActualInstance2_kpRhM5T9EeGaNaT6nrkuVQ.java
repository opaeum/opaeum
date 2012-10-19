package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentActualInstance2_kpRhM5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceComponent APPLIANCECOMPONENTACTUALINSTANCE2 = new ApplianceComponent();
	static final public ApplianceComponentActualInstance2_kpRhM5T9EeGaNaT6nrkuVQ INSTANCE = new ApplianceComponentActualInstance2_kpRhM5T9EeGaNaT6nrkuVQ();


	public ApplianceComponent createNewObject(CompositionNode parent) throws ParseException {
		ApplianceComponent result = APPLIANCECOMPONENTACTUALINSTANCE2;
		result.init(parent);
		result.setPartNumber("partNumber2");
		result.setPrice(0.5);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceComponent instance = (ApplianceComponent)in;
	}

}