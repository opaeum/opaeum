package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationEMailAddressActualInstance5_6z3ckX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationEMailAddress> {
	static final public OrganizationEMailAddressActualInstance5_6z3ckX_JEeK5usaVqVCtXw INSTANCE = new OrganizationEMailAddressActualInstance5_6z3ckX_JEeK5usaVqVCtXw();
	static final public OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE5 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) throws Exception {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE5;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co5");
		result.setType(OrganizationEMailAddressType.INFO);
		result.setType(OrganizationEMailAddressType.INFO);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}