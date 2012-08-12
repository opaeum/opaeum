package org.opaeum.runtime.bpm.request.taskrequest;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Active;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ActiveToCompleted;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ActiveToInProgress;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ActiveToReady;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ActiveToReserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.AtiveToObsolete;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Completed;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Created;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.CreatedToActive;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.InProgressToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Inactive;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.InactiveToCreated;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Obsolete;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReadyToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.ReservedToSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Suspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.SuspendedToActive;
import org.opaeum.runtime.statemachines.RegionActivation;

public class TaskRequestRegion extends RegionActivation {


	/** Constructor for TaskRequestRegion
	 * 
	 * @param owner 
	 */
	public TaskRequestRegion(TaskRequest owner) {
	super("252060@_zFmsEYoVEeCLqpffVZYAlw",owner);
		vertices.add(new Inactive(this));
		vertices.add(new Created(this));
		vertices.add(new Completed(this));
		vertices.add(new Active(this));
		vertices.add(new Suspended(this));
		vertices.add(new Obsolete(this));
	}

	public void linkTransitions() {
		transitions.add(new InactiveToCreated(this));
		transitions.add(new CreatedToActive(this));
		transitions.add(new ReadyToSuspended(this));
		transitions.add(new ReservedToSuspended(this));
		transitions.add(new InProgressToSuspended(this));
		transitions.add(new SuspendedToActive(this));
		transitions.add(new ActiveToCompleted(this));
		transitions.add(new ActiveToReserved(this));
		transitions.add(new ActiveToReady(this));
		transitions.add(new ActiveToInProgress(this));
		transitions.add(new AtiveToObsolete(this));
		super.linkTransitions();
	}

}