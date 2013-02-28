package org.opaeum.runtime.bpm.organization;

import org.opaeum.demo1.structuredbusiness.ApplianceCollaborationActualInstance1_6z4qmH_JEeK5usaVqVCtXw;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class BusinessNetworkActualInstance1_6z204H_JEeK5usaVqVCtXw extends EntityInstanceSimulation<BusinessNetwork> {
	static final public BusinessNetwork BUSINESSNETWORKACTUALINSTANCE1 = new BusinessNetwork();
	static final public BusinessNetworkActualInstance1_6z204H_JEeK5usaVqVCtXw INSTANCE = new BusinessNetworkActualInstance1_6z204H_JEeK5usaVqVCtXw();


	public BusinessNetwork createNewObject(CompositionNode parent) throws Exception {
		BusinessNetwork result = BUSINESSNETWORKACTUALINSTANCE1;
		OrganizationNodeActualInstance1_6z2043_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance2_6z3cCn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance3_6z3cfX_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonNodeActualInstance1_6z4DIn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonNodeActualInstance2_6z4Dj3_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		PersonNodeActualInstance3_6z4qKn_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		ApplianceCollaborationActualInstance1_6z4qmH_JEeK5usaVqVCtXw.INSTANCE.generateInstance(result);
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		BusinessNetwork instance = (BusinessNetwork)in;
	}

}