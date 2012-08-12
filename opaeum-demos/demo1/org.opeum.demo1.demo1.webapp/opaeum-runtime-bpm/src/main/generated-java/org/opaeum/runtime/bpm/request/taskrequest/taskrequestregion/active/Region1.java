package org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active;

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

public class Region1 extends RegionActivation {


	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(Active owner) {
	super("252060@_XwDj4aCVEeCmJqvPP4zbUw",owner);
		vertices.add(new Ready(this));
		vertices.add(new Reserved(this));
		vertices.add(new InProgress(this));
		vertices.add(new History(this));
		vertices.add(new FinalActiveState(this));
		vertices.add(new NumberOfPotentialOwners_(this));
	}

	public void linkTransitions() {
		transitions.add(new HistoryToNumberOfPotentialOwners(this));
		transitions.add(new NumberOfPotentialOwnersToReady(this));
		transitions.add(new NumberOfPotentialOwnersToReserved(this));
		transitions.add(new InProgressToReserved(this));
		transitions.add(new ReservedToReady(this));
		transitions.add(new ReadyToReserved(this));
		transitions.add(new ReservedToInProgress(this));
		transitions.add(new Transition5(this));
		super.linkTransitions();
	}

}