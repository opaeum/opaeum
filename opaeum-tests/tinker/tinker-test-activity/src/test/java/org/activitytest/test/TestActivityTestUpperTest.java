package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.root.ActivityTestUpper;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestActivityTestUpperTest  extends BaseLocalDbTest {

	@Test
	public void testUpper() {
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
		//Add another 99 home addresses
		for (int i = 0; i < 3; i++) {
			homeAddress = new Address(customer);
			homeAddress.setAddressType(AddressType.HOME);
			homeAddress.setName("homeAddress" + i);
		}
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(23, countVertices());
		Assert.assertEquals(4, customer.getAddress().size());
		
		db.startTransaction();
		ActivityTestUpper testActivityTestUpper = new ActivityTestUpper(root);
		testActivityTestUpper.setAddress(customer.getAddress());
		testActivityTestUpper.execute();
		db.stopTransaction(Conclusion.SUCCESS);

		//23 + activity and its 5 nodes + 2 object tokens stuck on input pin as its upper multiplicity is only 2
		Assert.assertEquals(31, countVertices());
		Assert.assertEquals(4, customer.getAddress().size());
		Assert.assertEquals(2, testActivityTestUpper.getNodeForName("addressInputPin").getInTokens().size());
		Assert.assertEquals(1, testActivityTestUpper.getNodeForName("OpaqueAction1").getNodeStat().getExecuteCount());
		Assert.assertEquals(1, testActivityTestUpper.getNodeForName("FlowFinalNode1").getNodeStat().getExecuteCount());
	}
	
}
