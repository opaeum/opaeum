package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationPhoneNumberActualInstance4_6z3cFn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationPhoneNumber> {
	static final public OrganizationPhoneNumberActualInstance4_6z3cFn_JEeK5usaVqVCtXw INSTANCE = new OrganizationPhoneNumberActualInstance4_6z3cFn_JEeK5usaVqVCtXw();
	static final public OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE4 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE4;
		result.init(parent);
		result.setHponeNumber("08234368404");
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.setType(OrganizationPhoneNumberType.FAX);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}