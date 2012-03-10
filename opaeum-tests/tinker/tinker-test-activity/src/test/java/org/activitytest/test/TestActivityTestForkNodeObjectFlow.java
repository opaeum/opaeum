package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.ActivityTestForkNodeObjectFlow;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestActivityTestForkNodeObjectFlow extends BaseLocalDbTest {

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
		ActivityTestForkNodeObjectFlow activityTestForkNodeObjectFlow = new ActivityTestForkNodeObjectFlow(customer);
		activityTestForkNodeObjectFlow.setCustomerParameter(customer);
		activityTestForkNodeObjectFlow.setAddressParameter(homeAddress);
		activityTestForkNodeObjectFlow.execute();
		db.stopTransaction(Conclusion.SUCCESS);
		//20 + 34 activity nodes + 1 for activity
		Assert.assertEquals(55, countVertices());
		
		//Only 2 object tokens sent in but 2 fork nodes duplicated it into 5
		Assert.assertEquals(5, activityTestForkNodeObjectFlow.getNodeForName("ActivityFinalNode1").getNodeStat().getExecuteCount());
		
	}
}
