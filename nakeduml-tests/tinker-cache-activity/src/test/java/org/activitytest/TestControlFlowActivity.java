package org.activitytest;

import org.activitytest.customer.SendEmailActivity;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.nakeduml.tinker.activity.NodeStatus;

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
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		Assert.assertFalse(sendEmailActivityProcess.execute());
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());

	}

}
