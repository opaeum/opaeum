package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Suspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.InProgressButSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReadyButSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReservedButSuspended;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1<SME extends TaskRequest> extends RegionActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_eXmqMaCWEeCmJqvPP4zbUw";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(Suspended<SME> owner) {
	super(ID,owner);
		vertices.add(new ReadyButSuspended<SME>(this));
		vertices.add(new ReservedButSuspended<SME>(this));
		vertices.add(new InProgressButSuspended<SME>(this));
	}

	public void linkTransitions() {
		super.linkTransitions();
	}

}