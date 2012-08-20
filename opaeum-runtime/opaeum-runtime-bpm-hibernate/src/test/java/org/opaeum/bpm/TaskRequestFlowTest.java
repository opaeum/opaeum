package org.opaeum.bpm;

import java.util.HashSet;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.request.ParticipationInTask;
import org.opaeum.runtime.bpm.request.TaskParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Active;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Completed;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Created;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Suspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.InProgressButSuspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.suspended.region1.ReadyButSuspended;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.MockEnvironment;

public class TaskRequestFlowTest{
	@Test
	public void testit(){
		MockEnvironment.getInstance();
		Mockery mockery = new Mockery();
		TaskRequest tr = new TaskRequest();
		final ITaskObject taskObject = mockery.mock(ITaskObject.class);
		final Participant participant1 = mockery.mock(Participant.class, "p1");
		final Participant participant2 = mockery.mock(Participant.class, "p2");
		mockery.checking(new Expectations(){
			{
				ignoring(participant1);
				ignoring(participant2);
				one(taskObject).on
			}
		});
		tr.setTaskObject(taskObject);
		tr.execute();
		Assert.assertTrue(tr.isStepActive(Created.class));
		ParticipationInTask p1 = new ParticipationInTask(tr);
		p1.setKind(TaskParticipationKind.POTENTIALOWNER);
		p1.z_internalAddToParticipant(participant1);
		ParticipationInTask p2 = new ParticipationInTask(tr);
		p2.setKind(TaskParticipationKind.POTENTIALOWNER);
		p2.z_internalAddToParticipant(participant2);
		tr.activate();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Active.class));
		Assert.assertTrue(tr.isStepActive(Ready.class));
		tr.suspend();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Suspended.class));
		Assert.assertTrue(tr.isStepActive(ReadyButSuspended.class));
		tr.resume();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Active.class));
		Assert.assertTrue(tr.isStepActive(Ready.class));
		tr.claim();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Active.class));
		Assert.assertTrue(tr.isStepActive(Reserved.class));
		final IBusinessRole br = mockery.mock(IBusinessRole.class, "br1");
		mockery.checking(new Expectations(){
			{
				super.allowing(br);
			}
		});
		tr.forward(br);
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Ready.class));
		tr.start();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(InProgress.class));
		tr.suspend();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Suspended.class));
		Assert.assertTrue(tr.isStepActive(InProgressButSuspended.class));
		tr.resume();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(InProgress.class));
		tr.complete();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.isStepActive(Completed.class));
	}
	private void deliverEvents(Set<OutgoingEvent> outgoingEvents){
		Set<OutgoingEvent> outgoingEvents2 = new HashSet<OutgoingEvent>(outgoingEvents);
		outgoingEvents.clear();
		for(OutgoingEvent entry:outgoingEvents2){
			entry.getHandler().handleOn(entry.getTarget());
		}
	}
}
