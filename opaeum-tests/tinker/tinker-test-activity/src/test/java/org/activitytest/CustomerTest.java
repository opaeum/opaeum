package org.activitytest;

import org.junit.Test;

public class CustomerTest implements CustomerTestContract {



	@Test
	public void testhomeAddressInitialValue() {
		Root parent = new Root();
		Customer object = new Customer(parent);
	}
	
	@Test
	public void testworkAddressInitialValue() {
		Root parent = new Root();
		Customer object = new Customer(parent);
	}

}