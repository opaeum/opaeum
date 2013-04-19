package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationEMailAddressActualInstance2_6z20_n_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationEMailAddress> {
	static final public OrganizationEMailAddressActualInstance2_6z20_n_JEeK5usaVqVCtXw INSTANCE = new OrganizationEMailAddressActualInstance2_6z20_n_JEeK5usaVqVCtXw();
	static final public OrganizationEMailAddress ORGANIZATIONEMAILADDRESSACTUALINSTANCE2 = new OrganizationEMailAddress();


	public OrganizationEMailAddress createNewObject(CompositionNode parent) throws Exception {
		OrganizationEMailAddress result = ORGANIZATIONEMAILADDRESSACTUALINSTANCE2;
		result.init(parent);
		result.setEmailAddress("john.doe@gmail.co2");
		result.setType(OrganizationEMailAddressType.INFO);
		result.setType(OrganizationEMailAddressType.ADMIN);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationEMailAddress instance = (OrganizationEMailAddress)in;
	}

}