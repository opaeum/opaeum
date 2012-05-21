package org.activitytest.test;

import java.util.Arrays;
import java.util.HashSet;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.Activity1;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestTestActivity1 extends BaseLocalDbTest {

	@Test
	public void testActivity1() {
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
//		Root + Customer + homeAddress + workAddress + Activity nodes + 1 Control token
		Assert.assertEquals(21, countVertices());
		db.startTransaction();
		customer.testActivity1Operation(new HashSet<String>(Arrays.asList("halo1","halo2")));
		db.stopTransaction(Conclusion.SUCCESS);

		//21 + 6 nodes + 1 activity + 1 (3 vertices) object tokens
		Assert.assertEquals(31, countVertices());

		//Test activity did not finished and is saved
		Assert.assertEquals(1, customer.getActivity1().size());
		
		Activity1 testActivity1 = customer.getActivity1().get(0);
		
		Assert.assertEquals(0, testActivity1.getNodeForName("inputPin1ManyString").getInTokens().size());
		Assert.assertEquals(0, testActivity1.getNodeForName("outputPin1").getOutTokens().size());
		//ObjectFLow2 has weight of 2, so token can not proceed
		Assert.assertEquals(1, testActivity1.getNodeForName("outputPin1").getOutTokens("ObjectFlow2").size());
		
	}
}
