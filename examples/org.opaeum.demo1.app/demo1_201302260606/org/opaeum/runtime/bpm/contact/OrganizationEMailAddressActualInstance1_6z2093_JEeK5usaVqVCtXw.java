package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OrganizationEMailAddressActualInstance1_6z2093_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationEMailAddress> {
	static final public OrganizationEMailAddressActualInstance1_6z2093_JEeK5usaVqVCtXw INSTANCE = new OrganizationEMailAddressActualInstance1_6z2093_JEeK5usaVqVCtXw();
	static final public OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE1 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) throws Exception {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE1;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co1");
		result.setType(OrganizationEMailAddressType.INFO);
		result.setType(OrganizationEMailAddressType.INFO);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}