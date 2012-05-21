package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.ActivityTestCallOperationActionNoTriggerInvolved;
import org.activitytest.customer.ActivityTestInvokedFromCallOperationActionNoTriggerInvolved;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestCallOperationActionNoTriggerInvolved extends BaseLocalDbTest {
	
	@Test
	public void testCallOperationActionNoTriggerInvolved() {
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		Address address = new Address(customer);
		address.setName("address1");
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(20, countVertices());
		
		db.startTransaction();
		ActivityTestCallOperationActionNoTriggerInvolved actionNoTriggerInvolved = new ActivityTestCallOperationActionNoTriggerInvolved(customer);
		actionNoTriggerInvolved.execute();
		db.stopTransaction(Conclusion.SUCCESS);
		
		//20 + 1 + 4 (remember target value pin) + 1 +  4
		Assert.assertEquals(30, countVertices());
		ActivityTestCallOperationActionNoTriggerInvolved activityTestCallOperationActionNoTriggerInvolved = customer.getActivityTestCallOperationActionNoTriggerInvolved().get(0);
		Assert.assertEquals(1, activityTestCallOperationActionNoTriggerInvolved.getNodeForName("testCallOperationActionNoTriggerInvolved").getNodeStat().getExecuteCount());
		Assert.assertTrue(activityTestCallOperationActionNoTriggerInvolved.isFinished());
		
		ActivityTestInvokedFromCallOperationActionNoTriggerInvolved actionNoTriggerInvolved2 = customer.getActivityTestInvokedFromCallOperationActionNoTriggerInvolved().get(0); 
		Assert.assertEquals(1, actionNoTriggerInvolved2.getNodeForName("OpaqueAction1").getNodeStat().getExecuteCount());
		Assert.assertTrue(actionNoTriggerInvolved2.isFinished());
	}

}
