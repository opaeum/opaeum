package org.activitytest.test;

import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.ActivityTestAcceptCallAction;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestAcceptCallAction extends BaseLocalDbTest {

	@Test
	public void testAcceptCallAction() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(19, countVertices());
		db.startTransaction();
		ActivityTestAcceptCallAction testAcceptCallAction = new ActivityTestAcceptCallAction(customer);
		testAcceptCallAction.execute();
		db.stopTransaction(Conclusion.SUCCESS);
		//11 nodes + activity + 1 token + 19
		Assert.assertEquals(32, countVertices());
		db.startTransaction();
		//TODO fix return parameter
		String result = customer.testCallAcceptEvent("wtf","");
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals("wtf", result);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}
}
