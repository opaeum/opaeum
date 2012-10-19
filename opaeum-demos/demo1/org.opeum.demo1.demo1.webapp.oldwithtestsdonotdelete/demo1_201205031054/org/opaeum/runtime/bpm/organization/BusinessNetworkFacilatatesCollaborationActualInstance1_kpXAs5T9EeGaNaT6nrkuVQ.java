package org.opaeum.runtime.bpm.organization;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessNetworkFacilatatesCollaborationActualInstance1_kpXAs5T9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private BusinessNetworkFacilatatesCollaboration BUSINESSNETWORKFACILATATESCOLLABORATIONACTUALINSTANCE1 = new BusinessNetworkFacilatatesCollaboration();
	static final public BusinessNetworkFacilatatesCollaborationActualInstance1_kpXAs5T9EeGaNaT6nrkuVQ INSTANCE = new BusinessNetworkFacilatatesCollaborationActualInstance1_kpXAs5T9EeGaNaT6nrkuVQ();


	public BusinessNetworkFacilatatesCollaboration createNewObject(CompositionNode parent) throws ParseException {
		BusinessNetworkFacilatatesCollaboration result = BUSINESSNETWORKFACILATATESCOLLABORATIONACTUALINSTANCE1;
		result.init(parent);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		BusinessNetworkFacilatatesCollaboration instance = (BusinessNetworkFacilatatesCollaboration)in;
	}

}