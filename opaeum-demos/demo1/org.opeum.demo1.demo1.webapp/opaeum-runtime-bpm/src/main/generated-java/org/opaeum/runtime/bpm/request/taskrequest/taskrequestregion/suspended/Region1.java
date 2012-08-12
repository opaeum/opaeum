package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended;

import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Suspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.InProgressButSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReadyButSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReservedButSuspended;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1 extends RegionActivation {


	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(Suspended owner) {
	super("252060@_eXmqMaCWEeCmJqvPP4zbUw",owner);
		vertices.add(new ReadyButSuspended(this));
		vertices.add(new ReservedButSuspended(this));
		vertices.add(new InProgressButSuspended(this));
	}

	public void linkTransitions() {
		super.linkTransitions();
	}

}