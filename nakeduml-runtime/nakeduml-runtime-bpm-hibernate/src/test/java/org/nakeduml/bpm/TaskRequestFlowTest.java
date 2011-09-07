package org.nakeduml.bpm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.runtime.bpm.BusinessRole;
import org.nakeduml.runtime.bpm.Participant;
import org.nakeduml.runtime.bpm.ParticipationInTask;
import org.nakeduml.runtime.bpm.TaskParticipationKind;
import org.nakeduml.runtime.bpm.TaskRequest;
import org.nakeduml.runtime.domain.OutgoingEvent;
import org.nakeduml.runtime.environment.MockEnvironment;

public class TaskRequestFlowTest{
	@Test
	public void testit(){
		MockEnvironment.getInstance();
		Mockery mockery = new Mockery();
		TaskRequest tr = new TaskRequest();
		tr.execute();
		Collection<NodeInstanceImpl> nodeInstancesRecursively = tr.getNodeInstancesRecursively();
		Assert.assertTrue(tr.getCreated());
		ParticipationInTask p1 = new ParticipationInTask(tr);
		p1.setKind(TaskParticipationKind.POTENTIALOWNER);
		p1.z_internalAddToParticipant(mockery.mock(Participant.class, "p1"));
		ParticipationInTask p2 = new ParticipationInTask(tr);
		p2.setKind(TaskParticipationKind.POTENTIALOWNER);
		p2.z_internalAddToParticipant(mockery.mock(Participant.class, "p2"));
		tr.activate();
		deliverEvents(tr.getOutgoingEvents());
		for(NodeInstanceImpl nodeInstanceImpl:nodeInstancesRecursively){
			System.out.println(nodeInstanceImpl.getNodeName());
		}
		Assert.assertTrue(tr.getActive());
		Assert.assertTrue(tr.getActive_Ready());
		tr.suspend();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getSuspended());
		Assert.assertTrue(tr.getSuspended_ReadyButSuspended());
		tr.resume();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getActive());
		Assert.assertTrue(tr.getActive_Ready());
		tr.claim();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getActive());
		Assert.assertTrue(tr.getActive_Reserved());
		final BusinessRole br = mockery.mock(BusinessRole.class, "br1");
		mockery.checking(new Expectations(){
			{
				super.allowing(br);
			}
		});
		tr.forward(br);
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getActive_Ready());
		tr.start();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getActive_InProgress());
		tr.suspend();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getSuspended());
		Assert.assertTrue(tr.getSuspended_InProgressButSuspended());
		tr.resume();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getActive_InProgress());
		tr.complete();
		deliverEvents(tr.getOutgoingEvents());
		Assert.assertTrue(tr.getCompleted());
	}
	private void deliverEvents(Set<OutgoingEvent> outgoingEvents){
		Set<OutgoingEvent> outgoingEvents2 = new HashSet<OutgoingEvent>(outgoingEvents);
		outgoingEvents.clear();
		for(OutgoingEvent entry:outgoingEvents2){
			entry.getHandler().handleOn(entry.getTarget());
		}
		// TODO Auto-generated method stub
	}
}
