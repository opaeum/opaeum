package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.TaskRequestToken;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Active;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.FinalActiveState;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.History;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.HistoryToNumberOfPotentialOwners;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgressToReserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.NumberOfPotentialOwnersToReady;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.NumberOfPotentialOwnersToReserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.NumberOfPotentialOwners_;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.ReadyToReserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.ReservedToInProgress;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.ReservedToReady;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Transition5;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1<SME extends TaskRequest> extends RegionActivation<SME, TaskRequestToken<SME>> {
	static public String ID = "252060@_XwDj4aCVEeCmJqvPP4zbUw";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(Active<SME> owner) {
	super(ID,owner);
		vertices.add(new Ready<SME>(this));
		vertices.add(new Reserved<SME>(this));
		vertices.add(new InProgress<SME>(this));
		vertices.add(new History<SME>(this));
		vertices.add(new FinalActiveState<SME>(this));
		vertices.add(new NumberOfPotentialOwners_<SME>(this));
	}

	public void linkTransitions() {
		transitions.add(new HistoryToNumberOfPotentialOwners<SME>(this));
		transitions.add(new NumberOfPotentialOwnersToReady<SME>(this));
		transitions.add(new NumberOfPotentialOwnersToReserved<SME>(this));
		transitions.add(new InProgressToReserved<SME>(this));
		transitions.add(new ReservedToReady<SME>(this));
		transitions.add(new ReadyToReserved<SME>(this));
		transitions.add(new ReservedToInProgress<SME>(this));
		transitions.add(new Transition5<SME>(this));
		super.linkTransitions();
	}

}