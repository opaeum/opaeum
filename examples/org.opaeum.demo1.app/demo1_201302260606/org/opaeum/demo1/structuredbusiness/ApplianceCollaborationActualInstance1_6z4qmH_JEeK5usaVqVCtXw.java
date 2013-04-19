package org.opaeum.demo1.structuredbusiness;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class ApplianceCollaborationActualInstance1_6z4qmH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<ApplianceCollaboration> {
	static final public ApplianceCollaboration APPLIANCECOLLABORATIONACTUALINSTANCE1 = new ApplianceCollaboration();
	static final public ApplianceCollaborationActualInstance1_6z4qmH_JEeK5usaVqVCtXw INSTANCE = new ApplianceCollaborationActualInstance1_6z4qmH_JEeK5usaVqVCtXw();


	public ApplianceCollaboration createNewObject(CompositionNode parent) throws Exception {
		ApplianceCollaboration result = APPLIANCECOLLABORATIONACTUALINSTANCE1;
		result.init(parent);
		ApplianceDoctorActualInstance1_6z4qm3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnlineCustomerActualInstance1_6z5Rcn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnlineCustomerActualInstance2_6z5RfX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OnlineCustomerActualInstance3_6z5RiH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		SupplierActualInstance1_6z5RlH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		SupplierActualInstance2_6z5Rn3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		SupplierActualInstance3_6z5Rqn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		ApplianceCollaboration instance = (ApplianceCollaboration)in;
	}

}