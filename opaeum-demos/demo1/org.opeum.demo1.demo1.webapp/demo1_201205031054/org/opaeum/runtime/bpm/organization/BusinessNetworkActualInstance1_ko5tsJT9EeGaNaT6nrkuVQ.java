package org.opaeum.runtime.bpm.organization;

import java.text.ParseException;

import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.simulation.EntityInstanceSimulation;
import org.opaeum.simulation.SimulationMetaData;

public class BusinessNetworkActualInstance1_ko5tsJT9EeGaNaT6nrkuVQ extends EntityInstanceSimulation {
	static final private BusinessNetwork BUSINESSNETWORKACTUALINSTANCE1 = new BusinessNetwork();
	static final public BusinessNetworkActualInstance1_ko5tsJT9EeGaNaT6nrkuVQ INSTANCE = new BusinessNetworkActualInstance1_ko5tsJT9EeGaNaT6nrkuVQ();


	public BusinessNetwork createNewObject(CompositionNode parent) throws ParseException {
		BusinessNetwork result = BUSINESSNETWORKACTUALINSTANCE1;
		PersonNodeActualInstance1_ko5ts5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		PersonNodeActualInstance2_ko6Ux5T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		PersonNodeActualInstance3_ko6U3JT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance1_ko6U85T9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance2_ko672JT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		OrganizationNodeActualInstance3_ko674ZT9EeGaNaT6nrkuVQ.INSTANCE.generateInstance(result);
		return result;
	}
	
	public void populateReferences(CompositionNode in) throws ParseException {
		BusinessNetwork instance = (BusinessNetwork)in;
	}

}