package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OrganizationEMailAddressActualInstance6_6z3cmH_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationEMailAddress> {
	static final public OrganizationEMailAddressActualInstance6_6z3cmH_JEeK5usaVqVCtXw INSTANCE = new OrganizationEMailAddressActualInstance6_6z3cmH_JEeK5usaVqVCtXw();
	static final public OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE6 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) throws Exception {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE6;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co6");
		result.setType(OrganizationEMailAddressType.INFO);
		result.setType(OrganizationEMailAddressType.ADMIN);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}