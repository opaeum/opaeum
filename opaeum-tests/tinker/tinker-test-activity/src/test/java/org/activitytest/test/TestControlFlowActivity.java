package org.activitytest.test;

import java.util.HashSet;
import java.util.Set;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.SendEmailActivity;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.runtime.domain.activity.ActivityNode;
import org.nakeduml.runtime.domain.activity.NodeStatus;
import org.nakeduml.runtime.domain.activity.Token;
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
		Address homeAddress = new Address(customer);
		homeAddress.setAddressType(AddressType.HOME);
		homeAddress.setName("homeAddress1");
		Address workAddress = new Address(customer);
		workAddress.setAddressType(AddressType.WORK);
		workAddress.setName("workAddress1");
		SendEmailActivity sendEmailActivityProcess = customer.getClassifierBehavior();
		db.stopTransaction(Conclusion.SUCCESS);
//		Root + Customer + homeAddress + workAddress + Activity nodes + 1 Control token
		Assert.assertEquals(21, countVertices());
		ActivityNode<? extends Token> initialNode1 = sendEmailActivityProcess.getNodeForName("InitialNode1");
		Assert.assertEquals(0, initialNode1.getInTokens().size());
		Assert.assertEquals(1, initialNode1.getOutTokens().size());
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);
		Set<ActivityNode<? extends Token>> completedNodes = sendEmailActivityProcess.getCompletedNodes();
		Assert.assertEquals(1, completedNodes.size());
		Set<String> nodeNames = extractNames(completedNodes);
		Assert.assertTrue(nodeNames.contains("InitialNode1"));
		Set<ActivityNode<? extends Token>> activeNodes = sendEmailActivityProcess.getActiveNodes();
		Assert.assertEquals(0, activeNodes.size());
		ActivityNode<? extends Token> sendEmail = sendEmailActivityProcess.getNodeForName("SendEmail");
		Assert.assertNotNull(sendEmail);
		Assert.assertEquals(NodeStatus.INACTIVE, sendEmail.getNodeStatus());
		Assert.assertEquals(0, sendEmail.getNodeStat().getExecuteCount());
		Assert.assertEquals(22, countVertices());

		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals(21, countVertices());
		sendEmail = sendEmailActivityProcess.getNodeForName("SendEmail");
		Assert.assertEquals(NodeStatus.COMPLETE, sendEmail.getNodeStatus());
		Assert.assertEquals(1, sendEmail.getOutTokens().size());
		Assert.assertEquals(1, sendEmail.getNodeStat().getExecuteCount());
		
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(25, countVertices());

		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);

		Assert.assertEquals(20, countVertices());

		initialNode1 = sendEmailActivityProcess.getNodeForName("InitialNode1");
		Assert.assertEquals(0, initialNode1.getInTokens().size());
		Assert.assertEquals(0, initialNode1.getOutTokens().size());
		
		//They have been deleted
		Assert.assertEquals(0, sendEmail.getOutTokens().size());
		Assert.assertEquals(2, sendEmail.getNodeStat().getExecuteCount());
		ActivityNode<? extends Token> sendSms = sendEmailActivityProcess.getNodeForName("SendSms");
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
		Assert.assertTrue(nodeNames.contains("DecisionNode1"));
		Assert.assertTrue(nodeNames.contains("MergeNode1"));
		Assert.assertTrue(nodeNames.contains("ForkNode2"));
		Assert.assertTrue(nodeNames.contains("JoinNode1"));
		Assert.assertTrue(nodeNames.contains("ForkNode1"));
		Assert.assertTrue(nodeNames.contains("FlowFinalNode1"));
		
		sendEmail = sendEmailActivityProcess.getNodeForName("SendEmail");
		Assert.assertEquals(2, sendEmail.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, sendEmail.getOutTokens().size()); 
		Assert.assertEquals(0, sendEmail.getInTokens().size()); 

		sendSms = sendEmailActivityProcess.getNodeForName("SendSms");
		Assert.assertEquals(1, sendSms.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, sendSms.getOutTokens().size()); 
		Assert.assertEquals(0, sendSms.getInTokens().size()); 

		ActivityNode<? extends Token> forkNode1 = sendEmailActivityProcess.getNodeForName("ForkNode1");
		Assert.assertEquals(1, forkNode1.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, forkNode1.getOutTokens().size()); 
		Assert.assertEquals(0, forkNode1.getInTokens().size()); 

		ActivityNode<? extends Token> opaqueAction1 = sendEmailActivityProcess.getNodeForName("OpaqueAction1");
		Assert.assertEquals(1, opaqueAction1.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, opaqueAction1.getOutTokens().size()); 
		Assert.assertEquals(0, opaqueAction1.getInTokens().size()); 

		ActivityNode<? extends Token> opaqueAction2 = sendEmailActivityProcess.getNodeForName("OpaqueAction2");
		Assert.assertEquals(1, opaqueAction2.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, opaqueAction2.getOutTokens().size()); 
		Assert.assertEquals(0, opaqueAction2.getInTokens().size()); 

		ActivityNode<? extends Token> joinNode1 = sendEmailActivityProcess.getNodeForName("JoinNode1");
		Assert.assertEquals(1, joinNode1.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, joinNode1.getOutTokens().size()); 
		Assert.assertEquals(0, joinNode1.getInTokens().size()); 

		ActivityNode<? extends Token> forkNode2 = sendEmailActivityProcess.getNodeForName("ForkNode2");
		Assert.assertEquals(1, forkNode2.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, forkNode2.getOutTokens().size()); 
		Assert.assertEquals(0, forkNode2.getInTokens().size()); 

		ActivityNode<? extends Token> opaqueAction3 = sendEmailActivityProcess.getNodeForName("OpaqueAction3");
		Assert.assertEquals(1, opaqueAction3.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, opaqueAction3.getOutTokens().size()); 
		Assert.assertEquals(0, opaqueAction3.getInTokens().size()); 
		
		ActivityNode<? extends Token> opaqueAction4 = sendEmailActivityProcess.getNodeForName("OpaqueAction4");
		Assert.assertEquals(1, opaqueAction4.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, opaqueAction4.getOutTokens().size()); 
		Assert.assertEquals(0, opaqueAction4.getInTokens().size()); 

		ActivityNode<? extends Token> mergeNode1 = sendEmailActivityProcess.getNodeForName("MergeNode1");
		Assert.assertEquals(2, mergeNode1.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, mergeNode1.getOutTokens().size()); 
		Assert.assertEquals(0, mergeNode1.getInTokens().size()); 

		ActivityNode<? extends Token> decisionNode1 = sendEmailActivityProcess.getNodeForName("DecisionNode1");
		Assert.assertEquals(2, decisionNode1.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, decisionNode1.getOutTokens().size()); 
		Assert.assertEquals(0, decisionNode1.getInTokens().size()); 
		
		ActivityNode<? extends Token> opaqueAction6 = sendEmailActivityProcess.getNodeForName("OpaqueAction6");
		Assert.assertEquals(2, opaqueAction6.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, opaqueAction6.getOutTokens().size()); 
		Assert.assertEquals(0, opaqueAction6.getInTokens().size()); 

		ActivityNode<? extends Token> opaqueAction5 = sendEmailActivityProcess.getNodeForName("OpaqueAction5");
		Assert.assertEquals(0, opaqueAction5.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, opaqueAction5.getOutTokens().size()); 
		Assert.assertEquals(0, opaqueAction5.getInTokens().size()); 

		ActivityNode<? extends Token> flowFinalNode1 = sendEmailActivityProcess.getNodeForName("FlowFinalNode1");
		Assert.assertNotNull(flowFinalNode1);
		Assert.assertEquals(2, flowFinalNode1.getNodeStat().getExecuteCount());
		Assert.assertEquals(0, flowFinalNode1.getOutTokens().size()); 
		Assert.assertEquals(0, flowFinalNode1.getInTokens().size()); 

		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}

	private Set<String> extractNames(Set<ActivityNode<? extends Token>> completedNodes) {
		Set<String> nodeNames = new HashSet<String>();
		for (ActivityNode<? extends Token> abstractNode : completedNodes) {
			nodeNames.add(abstractNode.getClass().getSimpleName());
		}
		return nodeNames;
	}

}
