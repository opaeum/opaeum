package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.ActivityTestControlAndObjectFlow;
import org.activitytest.customer.activitytestcontrolandobjectflow.InitialNode1;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.runtime.domain.activity.ControlToken;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.pipes.util.SingleIterator;

public class TestActivityTestControlAndObjectFlow extends BaseLocalDbTest {

	@Test
	public void testJoinAndMerge() {
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		Address homeAddress = new Address(customer);
		homeAddress.setAddressType(AddressType.HOME);
		homeAddress.setName("homeAddress1");
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(20, countVertices());
		db.startTransaction();
		ActivityTestControlAndObjectFlow activityTestControlAndObjectFlow = new ActivityTestControlAndObjectFlow(customer);
		activityTestControlAndObjectFlow.setParameter1(customer);
		activityTestControlAndObjectFlow.setParameter2(homeAddress);
		activityTestControlAndObjectFlow.execute();
		db.stopTransaction(Conclusion.SUCCESS);
		//20 + 1 for activity + 17 for nodes + 1 object token stuck at join node.
		Assert.assertEquals(39, countVertices());
		Assert.assertEquals(1, activityTestControlAndObjectFlow.getNodeForName("JoinNode1").getInTokens().size());
		
		db.startTransaction();
		//This is a bit dof, but so is the model
		InitialNode1 initialNode1 = activityTestControlAndObjectFlow.getInitialNode1();
		initialNode1.setStarts(new SingleIterator<ControlToken>(new ControlToken("InitialNode1")));
		initialNode1.next();
		db.stopTransaction(Conclusion.SUCCESS);
		//20 + 1 for activity + 17 for nodes.
		Assert.assertEquals(38, countVertices());
		Assert.assertEquals(0, activityTestControlAndObjectFlow.getNodeForName("JoinNode1").getInTokens().size());
	}
}
