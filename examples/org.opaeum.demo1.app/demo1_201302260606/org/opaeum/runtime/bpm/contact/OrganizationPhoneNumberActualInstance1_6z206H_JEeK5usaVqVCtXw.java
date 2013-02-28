package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;

public class OrganizationPhoneNumberActualInstance1_6z206H_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationPhoneNumber> {
	static final public OrganizationPhoneNumberActualInstance1_6z206H_JEeK5usaVqVCtXw INSTANCE = new OrganizationPhoneNumberActualInstance1_6z206H_JEeK5usaVqVCtXw();
	static final public OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE1 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE1;
		result.init(parent);
		result.setHponeNumber("08234368401");
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}