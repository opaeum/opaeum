package org.activitytest.test;

import java.util.HashSet;
import java.util.Set;

import org.activitytest.Address;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.activitytest.customer.ActivityTestReturnParametersInOut;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Assert;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TestActivityReturnParameters extends BaseLocalDbTest {

	@Test(expected=IllegalStateException.class)
	public void testInOutParameterMustHaveVertex() {
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
		customer.testActivityWithReturnParameterInOutOperation(new Address(),"");
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testInOutParameter() {
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
		customer.testActivityWithReturnParameterInOutOperation(address, "testThis");
		db.stopTransaction(Conclusion.SUCCESS);
		
		ActivityTestReturnParametersInOut activity = customer.getActivityTestReturnParametersInOut().get(0);
		Assert.assertEquals(1, activity.getNodeForName("testParameterOutInOut").getNodeStat().getExecuteCount());
		Assert.assertEquals(1, activity.getNodeForName("testParameterOutInOut").getInTokens().size());
		Assert.assertEquals(address, activity.getTestParameterOutInOut().getReturnParameterValues().get(0));
		Assert.assertEquals("testThis", address.getName());
	}	
	
	/**
	 * Out parameters goes in without a vertex and comes out with a vertex
	 */
	@Test(expected=IllegalStateException.class)
	public void testOutParameterMayNotHaveAVertex() {
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
		customer.testActivityReturnParametersOutOperation(address);
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	/**
	 * Out parameters goes in without a vertex and comes out with a vertex
	 */
	@Test
	public void testOutParameterHasVertex() {
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
		Address outAddress = new Address();
		customer.testActivityReturnParametersOutOperation(outAddress);
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertNotNull(outAddress.getVertex());
		Assert.assertEquals(address, outAddress);
		Assert.assertEquals("address1", outAddress.getName());
	}
	
	@Test
	public void testParameters() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		Address addressInOut1 = new Address(customer);
		addressInOut1.setName("addressInOut1");
		addressInOut1.setAddressType(AddressType.HOME);

		Address addressIn = new Address(customer);
		addressIn.setName("addressIn");
		addressIn.setAddressType(AddressType.HOME);

		Address addressInoutExpectNew = new Address(customer);
		addressIn.setName("addressInoutExpectNew");
		addressIn.setAddressType(AddressType.HOME);
		Vertex v = addressInoutExpectNew.getVertex();

		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(22, countVertices());
		
		db.startTransaction();
		Address addressOut = new Address();
		Address address = customer.testActivityWithReturnParameterInOutOperation2(addressInOut1, "addressString1", addressIn, "addressString2", addressInoutExpectNew, addressOut);
		customer.addToAddress(addressInoutExpectNew);
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals("addressString1", addressInOut1.getName());
		Assert.assertEquals("addressString2", addressIn.getName());
		Assert.assertEquals("addressString2", addressOut.getName());
		Assert.assertEquals("addressString2", address.getName());
		Assert.assertNotSame(v, addressInoutExpectNew.getVertex());
	}	
	
	@Test
	public void testSimpleParameters() {
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
		StringBuilder paramInOutString = new StringBuilder("paramInOutString");
		StringBuilder paramOutString = new StringBuilder();
		String result = customer.testActivityWithSimpleParameters("paramInString", paramInOutString, paramOutString);
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals("paramInString", paramInOutString.toString());
		Assert.assertEquals("paramInString", paramOutString.toString());
		Assert.assertEquals("paramInString", result);
	}
	
	@Test
	public void testParametersMultiplicityMany() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		Root root = new Root(true);
		root.setName("THEROOT");
		Customer customer = new Customer(root);
		customer.setName("customer1");
		Address a = new Address(customer);
		a.setName("address1");

		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(20, countVertices());
		
		db.startTransaction();
		Address additionalAddress = new Address(true);
		additionalAddress.setName("add");
		Set<Address> outAddress = new HashSet<Address>();
		customer.testActivityParametersInAndOutMultiplicityMany(customer, additionalAddress, outAddress);
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals(2, outAddress.size());
		boolean foundAddress1 = false;
		boolean foundAddressAdd = false;
		for (Address address : outAddress) {
			if (address.getName().equals("add")) {
				foundAddressAdd = true;
			}
			if (address.getName().equals("address1")) {
				foundAddress1 = true;
			}
		}
		Assert.assertTrue(foundAddress1);
		Assert.assertTrue(foundAddressAdd);
	}		
	
}
