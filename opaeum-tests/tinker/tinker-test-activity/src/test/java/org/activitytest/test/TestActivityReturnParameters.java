package org.activitytest.test;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestActivityReturnParameters extends BaseLocalDbTest {

	@Test
	public void testAcceptCallActionInOut() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
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
		customer.testActivityWithReturnParameterInOutOperation(address);
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testAcceptCallActionOut() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		Address address = new Address(customer);
		address.setName("address1");
		address.setAddressType(AddressType.HOME);
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(20, countVertices());
		
		db.startTransaction();
		customer.testActivityreturnParametersOutOperation(address);
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
}
