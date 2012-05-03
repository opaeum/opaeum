package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentActualInstance1_kpRhLZT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceComponent APPLIANCECOMPONENTACTUALINSTANCE1 = new ApplianceComponent();
	static final public ApplianceComponentActualInstance1_kpRhLZT9EeGaNaT6nrkuVQ INSTANCE = new ApplianceComponentActualInstance1_kpRhLZT9EeGaNaT6nrkuVQ();


	public ApplianceComponent createNewObject(CompositionNode parent) throws ParseException {
		ApplianceComponent result = APPLIANCECOMPONENTACTUALINSTANCE1;
		result.init(parent);
		result.setPartNumber("partNumber1");
		result.setPrice(1.0);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceComponent instance = (ApplianceComponent)in;
	}

}