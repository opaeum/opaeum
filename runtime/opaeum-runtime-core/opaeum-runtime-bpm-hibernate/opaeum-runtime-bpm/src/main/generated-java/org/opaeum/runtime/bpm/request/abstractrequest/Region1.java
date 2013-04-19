package org.opaeum.runtime.bpm.request.abstractrequest;

import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.AbstractRequestToken;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1 extends RegionActivation<AbstractRequest, AbstractRequestToken<AbstractRequest>> {
	static public String ID = "252060@_FgGnMI6lEeCFsPOcAnk69Q";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(AbstractRequest owner) {
	super("252060@_FgGnMI6lEeCFsPOcAnk69Q",owner);
	}

	public void linkTransitions() {
		super.linkTransitions();
	}

}