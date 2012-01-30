package org.activitytest.test;

import java.util.HashSet;
import java.util.Set;

import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.SendEmailActivity;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.runtime.domain.activity.AbstractNode;
import org.nakeduml.runtime.domain.activity.NodeStatus;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestControlFlowActivity extends BaseLocalDbTest {

	@Test
	public void testActivityPersistence() {
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		SendEmailActivity sendEmailActivityProcess = new SendEmailActivity(customer);
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(18, countVertices());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Set<AbstractNode> completedNodes = sendEmailActivityProcess.getCompletedNodes();
		Assert.assertEquals(1, completedNodes.size());
		Set<String> nodeNames = extractNames(completedNodes);
		Assert.assertTrue(nodeNames.contains("InitialNode1"));
		Set<AbstractNode> activeNodes = sendEmailActivityProcess.getActiveNodes();
		Assert.assertEquals(0, activeNodes.size());
		AbstractNode sendEmail = sendEmailActivityProcess.getNodeForName("SendEmail");
		Assert.assertNotNull(sendEmail);
		Assert.assertEquals(NodeStatus.INACTIVE, sendEmail.getNodeStatus());
		Assert.assertEquals(0, sendEmail.getNodeStat().getExecuteCount());
		
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);

		sendEmail = sendEmailActivityProcess.getNodeForName("SendEmail");
		Assert.assertEquals(NodeStatus.COMPLETE, sendEmail.getNodeStatus());
		Assert.assertEquals(1, sendEmail.getOutControlTokens().size());
		Assert.assertEquals(1, sendEmail.getNodeStat().getExecuteCount());
		
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);
		
		//They have been deleted
		Assert.assertEquals(0, sendEmail.getOutControlTokens().size());
		Assert.assertEquals(2, sendEmail.getNodeStat().getExecuteCount());
		AbstractNode sendSms = sendEmailActivityProcess.getNodeForName("SendSms");
		Assert.assertEquals(1, sendSms.getNodeStat().getExecuteCount());
		completedNodes =  sendEmailActivityProcess.getCompletedNodes();
		nodeNames = extractNames(completedNodes);
		Assert.assertTrue(nodeNames.contains("InitialNode1"));
		Assert.assertTrue(nodeNames.contains("SendEmail"));
		Assert.assertTrue(nodeNames.contains("SendSms"));
		Assert.assertTrue(nodeNames.contains("OpaqueAction1"));
		Assert.assertTrue(nodeNames.contains("OpaqueAction2"));
		Assert.assertTrue(nodeNames.contains("OpaqueAction3"));
		Assert.assertTrue(nodeNames.contains("OpaqueAction4"));
		Assert.assertTrue(nodeNames.contains("OpaqueAction6"));
//		Assert.assertTrue(nodeNames.contains("FlowFinalNode1"));
		Assert.assertTrue(nodeNames.contains("DecisionNode1"));
		Assert.assertTrue(nodeNames.contains("MergeNode1"));
		Assert.assertTrue(nodeNames.contains("ForkNode2"));
		Assert.assertTrue(nodeNames.contains("JoinNode1"));
		Assert.assertTrue(nodeNames.contains("ForkNode1"));
		
		AbstractNode opaqueAction1 = sendEmailActivityProcess.getNodeForName("OpaqueAction1");
		Assert.assertEquals(1, opaqueAction1.getNodeStat().getExecuteCount());
		AbstractNode opaqueAction2 = sendEmailActivityProcess.getNodeForName("OpaqueAction2");
		Assert.assertEquals(1, opaqueAction2.getNodeStat().getExecuteCount());
		AbstractNode opaqueAction3 = sendEmailActivityProcess.getNodeForName("OpaqueAction3");
		Assert.assertEquals(1, opaqueAction3.getNodeStat().getExecuteCount());
		AbstractNode opaqueAction4 = sendEmailActivityProcess.getNodeForName("OpaqueAction4");
		Assert.assertEquals(1, opaqueAction4.getNodeStat().getExecuteCount());
		AbstractNode opaqueAction6 = sendEmailActivityProcess.getNodeForName("OpaqueAction6");
		Assert.assertEquals(2, opaqueAction6.getNodeStat().getExecuteCount());

		AbstractNode flowFinalNode1 = sendEmailActivityProcess.getNodeForName("FlowFinalNode1");
		Assert.assertNotNull(flowFinalNode1);

		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}

	private Set<String> extractNames(Set<AbstractNode> completedNodes) {
		Set<String> nodeNames = new HashSet<String>();
		for (AbstractNode abstractNode : completedNodes) {
			nodeNames.add(abstractNode.getClass().getSimpleName());
		}
		return nodeNames;
	}

}
