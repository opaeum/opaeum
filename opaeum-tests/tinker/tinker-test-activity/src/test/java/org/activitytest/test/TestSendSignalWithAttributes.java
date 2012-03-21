package org.activitytest.test;

import junit.framework.Assert;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.ReceiveSignalWithAttributesActivity;
import org.activitytest.customer.SendSignalWithAttributesActivity;
import org.junit.Test;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.nakeduml.runtime.domain.activity.FinalNode;
import org.nakeduml.runtime.domain.activity.NodeStatus;
import org.nakeduml.runtime.domain.activity.OpaqueAction;
import org.nakeduml.runtime.domain.activity.SendSignalAction;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestSendSignalWithAttributes extends BaseLocalDbTest  {
	
	@Test
	public void testSendSignalWithAttributes() {
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
		customer.getHomeAddress().setName("homeAddress1");
		db.stopTransaction(Conclusion.SUCCESS);
		
		//Root + Customer + homeAddress + workAddress + Classifier Behavior nodes + 1 Control token
		Assert.assertEquals(21, countVertices());
		
		db.startTransaction();
		customer.sendAddressAsSignalTest();
		db.stopTransaction(Conclusion.SUCCESS);

		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();

		//Comment out deletion of activity for these test to pass!!!!!!!!!!!!!!!!!!!!
		//+ 3 for TestSendSignalActivity Actions + 1 for Activity + 5 for TestReceiveSignalActivity Actions + 1 for Activity -1 as activity gets deleted
		Assert.assertEquals(31, countVertices());

		Assert.assertEquals(1, customer.getSendSignalWithAttributesActivity().size());
		Assert.assertEquals(1, customer.getReceiveSignalWithAttributesActivity().size());
		
		SendSignalWithAttributesActivity testSendSignalWithAttributesActivity = customer.getSendSignalWithAttributesActivity().get(0);
		ReceiveSignalWithAttributesActivity testReceiveSignalWithAttributesActivity = customer.getReceiveSignalWithAttributesActivity().get(0);

		SendSignalAction ssa = (SendSignalAction) testSendSignalWithAttributesActivity.getNodeForName("SendSignalAction1");
		Assert.assertNotNull(ssa);
		Assert.assertEquals(1, ssa.getNodeStat().getExecuteCount());
		Assert.assertEquals(NodeStatus.COMPLETE, ssa.getNodeStatus());
		Assert.assertEquals(0, ssa.getInTokens().size());
		Assert.assertEquals(0, ssa.getOutTokens().size());

		FinalNode fn = (FinalNode) testSendSignalWithAttributesActivity.getNodeForName("ActivityFinalNode1");
		Assert.assertNotNull(fn);
		Assert.assertEquals(1, fn.getNodeStat().getExecuteCount());
		Assert.assertEquals(NodeStatus.COMPLETE, fn.getNodeStatus());
		Assert.assertEquals(0, fn.getInTokens().size());
		Assert.assertEquals(0, fn.getOutTokens().size());

		OpaqueAction oa = (OpaqueAction) testReceiveSignalWithAttributesActivity.getNodeForName("OpaqueAction11");
		Assert.assertNotNull(oa);
		Assert.assertEquals(1, oa.getNodeStat().getExecuteCount());
		Assert.assertEquals(NodeStatus.COMPLETE, oa.getNodeStatus());
		Assert.assertEquals(0, oa.getInTokens().size());
		Assert.assertEquals(0, oa.getOutTokens().size());

		FinalNode fn1 = (FinalNode) testReceiveSignalWithAttributesActivity.getNodeForName("ActivityFinalNode1");
		Assert.assertNotNull(fn1);
		Assert.assertEquals(1, fn1.getNodeStat().getExecuteCount());
		Assert.assertEquals(NodeStatus.COMPLETE, fn1.getNodeStatus());
		Assert.assertEquals(0, fn1.getInTokens().size());
		Assert.assertEquals(0, fn1.getOutTokens().size());

		
	}

}
