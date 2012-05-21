package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.Signal2;
import org.activitytest.customer.ActivityLongLived1;
import org.activitytest.customer.ActivityTestCallOperationActionNoTriggerLongLived;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.runtime.domain.TinkerClassifierBehaviorExecutorService;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestCallOperationActionNoTriggerInvolvedLongLived extends BaseLocalDbTest {
	
	@Test
	public void testCallOperationActionNoTriggerInvolved() {
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		Address address1 = new Address(customer);
		address1.setName("address1");
		Address address2 = new Address(customer);
		address2.setName("address2");
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(21, countVertices());
		
		db.startTransaction();
		ActivityTestCallOperationActionNoTriggerLongLived a = new ActivityTestCallOperationActionNoTriggerLongLived(customer);
		a.execute(address1, address2);
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertFalse(a.isFinished());
		ActivityLongLived1 activityLongLived1 = customer.getActivityLongLived1().get(0);
		Assert.assertFalse(activityLongLived1.isFinished());
		Assert.assertTrue(activityLongLived1.getNodeForName("AcceptEventAction1111").isEnabled());
		Assert.assertEquals(0, activityLongLived1.getNodeForName("AcceptEventAction1111").getNodeStat().getExecuteCount());

		//Send a signal to ActivityLongLived1 to complete the call 
		db.startTransaction();
		customer.receiveSignal(new Signal2(true));
		db.stopTransaction(Conclusion.SUCCESS);
		
		TinkerClassifierBehaviorExecutorService.INSTANCE.take();
		
		Assert.assertTrue(activityLongLived1.isFinished());
		Assert.assertTrue(a.isFinished());
		
		Address resultAddress = a.getAddressParameterNodeInOut3().getReturnParameterValues().get(0);
		Assert.assertNotNull(resultAddress);
		Assert.assertEquals("lalalala", resultAddress.getName());
	}

}
