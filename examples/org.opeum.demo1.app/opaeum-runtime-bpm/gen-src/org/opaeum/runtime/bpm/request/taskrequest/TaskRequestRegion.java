package org.opaeum.runtime.bpm.request.taskrequest;

import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.abstractrequest.Region1;
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

public class TaskRequestRegion<SME extends TaskRequest> extends Region1<SME> {
	static public String ID = "252060@_zFmsEYoVEeCLqpffVZYAlw";

	/** Constructor for TaskRequestRegion
	 * 
	 * @param owner 
	 */
	public TaskRequestRegion(SME owner) {
	super(owner);
		getBehaviorExecution().getExecutionElements().put(ID,this);
		vertices.add(new Inactive<SME>(this));
		vertices.add(new Created<SME>(this));
		vertices.add(new Completed<SME>(this));
		vertices.add(new Active<SME>(this));
		vertices.add(new Suspended<SME>(this));
		vertices.add(new Obsolete<SME>(this));
	}

	public void linkTransitions() {
		transitions.add(new InactiveToCreated<SME>(this));
		transitions.add(new CreatedToActive<SME>(this));
		transitions.add(new ReadyToSuspended<SME>(this));
		transitions.add(new ReservedToSuspended<SME>(this));
		transitions.add(new InProgressToSuspended<SME>(this));
		transitions.add(new SuspendedToActive<SME>(this));
		transitions.add(new ActiveToCompleted<SME>(this));
		transitions.add(new ActiveToReserved<SME>(this));
		transitions.add(new ActiveToReady<SME>(this));
		transitions.add(new ActiveToInProgress<SME>(this));
		transitions.add(new AtiveToObsolete<SME>(this));
		super.linkTransitions();
	}

}