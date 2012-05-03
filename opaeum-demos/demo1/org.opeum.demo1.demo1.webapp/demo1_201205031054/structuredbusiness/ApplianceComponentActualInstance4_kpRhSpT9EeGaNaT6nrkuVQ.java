package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentActualInstance4_kpRhSpT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceComponent APPLIANCECOMPONENTACTUALINSTANCE4 = new ApplianceComponent();
	static final public ApplianceComponentActualInstance4_kpRhSpT9EeGaNaT6nrkuVQ INSTANCE = new ApplianceComponentActualInstance4_kpRhSpT9EeGaNaT6nrkuVQ();


	public ApplianceComponent createNewObject(CompositionNode parent) throws ParseException {
		ApplianceComponent result = APPLIANCECOMPONENTACTUALINSTANCE4;
		result.init(parent);
		result.setPartNumber("partNumber4");
		result.setPrice(0.25);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceComponent instance = (ApplianceComponent)in;
	}

}