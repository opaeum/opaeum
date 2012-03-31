package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationPhoneNumberActualInstance1_dENTMHseEeGYpMNvYdsq3Q extends EntityInstanceSimulation {
	static final public OrganizationPhoneNumberActualInstance1_dENTMHseEeGYpMNvYdsq3Q INSTANCE = new OrganizationPhoneNumberActualInstance1_dENTMHseEeGYpMNvYdsq3Q();
	static final private OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE1 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE1;
		result.init(parent);
		result.setHponeNumber("08234368401");
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}