package org.opaeum.runtime.bpm.contact;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class OrganizationPhoneNumberActualInstance5_6z3cgn_JEeK5usaVqVCtXw extends EntityInstanceSimulation<OrganizationPhoneNumber> {
	static final public OrganizationPhoneNumberActualInstance5_6z3cgn_JEeK5usaVqVCtXw INSTANCE = new OrganizationPhoneNumberActualInstance5_6z3cgn_JEeK5usaVqVCtXw();
	static final public OrganizationPhoneNumber ORGANIZATIONPHONENUMBERACTUALINSTANCE5 = new OrganizationPhoneNumber();


	public OrganizationPhoneNumber createNewObject(CompositionNode parent) throws Exception {
		OrganizationPhoneNumber result = ORGANIZATIONPHONENUMBERACTUALINSTANCE5;
		result.init(parent);
		result.setHponeNumber("08234368405");
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.setType(OrganizationPhoneNumberType.LANDLINE);
		result.addToOwningObject();
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws Exception {
		OrganizationPhoneNumber instance = (OrganizationPhoneNumber)in;
	}

}