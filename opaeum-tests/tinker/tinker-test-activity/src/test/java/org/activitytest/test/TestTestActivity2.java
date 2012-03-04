package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.TestActivity2;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestTestActivity2 extends BaseLocalDbTest {

	@Test
	public void testActivity2() {
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
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(21, countVertices());
		
		db.startTransaction();
		TestActivity2 testActivity2 = new TestActivity2(customer);
		db.stopTransaction(Conclusion.SUCCESS);
		
		//21 + 19
		Assert.assertEquals(40, countVertices());
		
		db.startTransaction();
		testActivity2.setHomeAddressParam(customer.getHomeAddress());
		testActivity2.setWorkAddressParam(customer.getWorkAddress());
		testActivity2.execute();
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals(40, countVertices());
		Assert.assertEquals(1, testActivity2.getNodeForName("OpaqueAction3").getNodeStat().getExecuteCount());
		Assert.assertEquals(1, testActivity2.getNodeForName("OpaqueAction4").getNodeStat().getExecuteCount());
		
	}
}
