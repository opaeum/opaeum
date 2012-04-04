package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationEMailAddressActualInstance2_1W7SOX49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public OrganizationEMailAddressActualInstance2_1W7SOX49EeGY3962KIdJNA INSTANCE = new OrganizationEMailAddressActualInstance2_1W7SOX49EeGY3962KIdJNA();
	static final private OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE2 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE2;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co2");
		result.setType(OrganizationEMailAddressType.ADMIN);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}