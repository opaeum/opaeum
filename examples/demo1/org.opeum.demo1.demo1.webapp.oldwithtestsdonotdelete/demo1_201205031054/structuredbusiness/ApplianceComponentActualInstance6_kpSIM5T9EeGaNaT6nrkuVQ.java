package structuredbusiness;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceComponentActualInstance6_kpSIM5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private ApplianceComponent APPLIANCECOMPONENTACTUALINSTANCE6 = new ApplianceComponent();
	static final public ApplianceComponentActualInstance6_kpSIM5T9EeGaNaT6nrkuVQ INSTANCE = new ApplianceComponentActualInstance6_kpSIM5T9EeGaNaT6nrkuVQ();


	public ApplianceComponent createNewObject(CompositionNode parent) throws ParseException {
		ApplianceComponent result = APPLIANCECOMPONENTACTUALINSTANCE6;
		result.init(parent);
		result.setPartNumber("partNumber6");
		result.setPrice(0.16666666666666666);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		ApplianceComponent instance = (ApplianceComponent)in;
	}

}