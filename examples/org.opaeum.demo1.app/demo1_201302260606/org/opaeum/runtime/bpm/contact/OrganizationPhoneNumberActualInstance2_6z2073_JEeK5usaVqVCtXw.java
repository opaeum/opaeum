package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OrganizationPhoneNumberActualInstance2_6z2073_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationPhoneNumber> {
	static final public OrganizationPhoneNumberActualInstance2_6z2073_JEeK5usaVqVCtXw INSTANCE = new OrganizationPhoneNumberActualInstance2_6z2073_JEeK5usaVqVCtXw();
	static final public OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE2 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE2;
		result.init(parent);
		result.setHponeNumber("08234368402");
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.setType(OrganizationPhoneNumberType.FAX);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}