package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationEMailAddressActualInstance1_dENTGXseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public OrganizationEMailAddressActualInstance1_dENTGXseEeGYpMNvYdsq3Q INSTANCE = new OrganizationEMailAddressActualInstance1_dENTGXseEeGYpMNvYdsq3Q();
	static final private OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE1 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE1;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co1");
		result.setType(OrganizationEMailAddressType.INFO);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}