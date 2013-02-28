package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OrganizationPhoneNumberActualInstance6_6z3ciX_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationPhoneNumber> {
	static final public OrganizationPhoneNumberActualInstance6_6z3ciX_JEeK5usaVqVCtXw INSTANCE = new OrganizationPhoneNumberActualInstance6_6z3ciX_JEeK5usaVqVCtXw();
	static final public OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE6 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE6;
		result.init(parent);
		result.setHponeNumber("08234368406");
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.setType(OrganizationPhoneNumberType.FAX);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}