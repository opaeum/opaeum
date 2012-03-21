package org.activitytest.test;

import junit.framework.Assert;

import org.activitytest.Address;
import org.activitytest.AddressSignal;
import org.activitytest.AddressType;
import org.activitytest.Customer;
import org.activitytest.Root;
import org.junit.Test;
import org.nakeduml.runtime.domain.activity.SignalEvent;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestSignalPersistence extends BaseLocalDbTest {

	@Test
	public void testSignalPersistence() {
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
		customer.getHomeAddress().setName("homeAddress1");
		db.stopTransaction(Conclusion.SUCCESS);
		
		//Root + Customer + homeAddress + workAddress + Classifier Behavior nodes + 1 Control token
		Assert.assertEquals(21, countVertices());

		db.startTransaction();
		AddressSignal addressSignal = new AddressSignal(true);
		addressSignal.setAddress(homeAddress);
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals(22, countVertices());
		
		AddressSignal addressSignal2 = new AddressSignal(addressSignal.getVertex());
		Assert.assertTrue(addressSignal2.getAddress().getName().equals("homeAddress1"));
		
		db.startTransaction();
		SignalEvent signalEvent = new SignalEvent(addressSignal2, "aaaa");
		db.stopTransaction(Conclusion.SUCCESS);
		
		Assert.assertEquals(23, countVertices());
		
		SignalEvent signalEvent2 = new SignalEvent(signalEvent.getVertex());
		ISignal signal = signalEvent2.getSignal();
		Assert.assertTrue(signal instanceof AddressSignal);
		AddressSignal a = (AddressSignal)signal;
		Assert.assertTrue(a.getAddress().getName().equals("homeAddress1"));
	}

}
