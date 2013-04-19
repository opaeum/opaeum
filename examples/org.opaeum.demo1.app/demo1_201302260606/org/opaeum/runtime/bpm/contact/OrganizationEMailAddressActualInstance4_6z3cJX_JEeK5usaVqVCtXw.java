package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationEMailAddressActualInstance4_6z3cJX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationEMailAddress> {
	static final public OrganizationEMailAddressActualInstance4_6z3cJX_JEeK5usaVqVCtXw INSTANCE = new OrganizationEMailAddressActualInstance4_6z3cJX_JEeK5usaVqVCtXw();
	static final public OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE4 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) throws Exception {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE4;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co4");
		result.setType(OrganizationEMailAddressType.INFO);
		result.setType(OrganizationEMailAddressType.ADMIN);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}