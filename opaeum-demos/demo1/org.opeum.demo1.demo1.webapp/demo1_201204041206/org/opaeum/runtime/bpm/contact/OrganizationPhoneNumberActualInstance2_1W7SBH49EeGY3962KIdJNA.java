package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationPhoneNumberActualInstance2_1W7SBH49EeGY3962KIdJNA extends EntityInstanceSimulation {
	static final public OrganizationPhoneNumberActualInstance2_1W7SBH49EeGY3962KIdJNA INSTANCE = new OrganizationPhoneNumberActualInstance2_1W7SBH49EeGY3962KIdJNA();
	static final private OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE2 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE2;
		result.init(parent);
		result.setHponeNumber("08234368402");
		result.setType(OrganizationPhoneNumberType.FAX);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}