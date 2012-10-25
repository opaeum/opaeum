package org.activitytest.test;

import org.activitytest.Customer;
import org.activitytest.Root;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestActivity1 extends BaseLocalDbTest {

	@Test
	public void testActivity1() {
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		db.stopTransaction(Conclusion.SUCCESS);
//		Root + Customer + homeAddress + workAddress + Activity nodes + 1 Control token
		Assert.assertEquals(21, countVertices());
		db.startTransaction();
		customer.testActivity1("halo");
		db.stopTransaction(Conclusion.SUCCESS);
	}
}
