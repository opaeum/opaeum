package org.nakeduml.bpm;

import java.util.Collection;

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

public class TaskRequestFlowTest{
	@Test
	public void testit(){
		Mockery mockery = new Mockery();
		TaskRequest tr= new TaskRequest();
		tr.execute();
		System.out.println(tr.getInnermostNonParallelStep());
		Collection<NodeInstanceImpl> nodeInstancesRecursively = tr.getNodeInstancesRecursively();
		for(NodeInstanceImpl nodeInstanceImpl:nodeInstancesRecursively){
			System.out.println(nodeInstanceImpl.getNodeName());
		}
		Assert.assertTrue(tr.getCreated());
		ParticipationInTask p1 = new ParticipationInTask(tr);
		p1.setKind(TaskParticipationKind.POTENTIALOWNER);
		p1.z_internalAddToParticipant(mockery.mock(Participant.class, "p1"));
		ParticipationInTask p2 = new ParticipationInTask(tr);
		p2.setKind(TaskParticipationKind.POTENTIALOWNER);
		p2.z_internalAddToParticipant(mockery.mock(Participant.class, "p2"));
		tr.activate();
		Assert.assertTrue(tr.getActive());
		Assert.assertTrue(tr.getActive_Ready());
		tr.suspend();
		Assert.assertTrue(tr.getSuspended());
		Assert.assertTrue(tr.getSuspended_ReadyButSuspended());
		tr.resume();
		Assert.assertTrue(tr.getActive());
		Assert.assertTrue(tr.getActive_Ready());
		tr.claim();
		Assert.assertTrue(tr.getActive());
		Assert.assertTrue(tr.getActive_Reserved());
		final BusinessRole br = mockery.mock(BusinessRole.class,"br1");
		mockery.checking(new Expectations(){
			{
				super.allowing(br);
			}
		});
		tr.forward(br);
		Assert.assertTrue(tr.getActive_Ready());
		tr.start();
		Assert.assertTrue(tr.getActive_InProgress());
		tr.suspend();
		Assert.assertTrue(tr.getSuspended());
		Assert.assertTrue(tr.getSuspended_InProgressButSuspended());
		tr.resume();
		Assert.assertTrue(tr.getActive_InProgress());
		tr.complete();
		Assert.assertTrue(tr.getCompleted());
	}
}
