package org.opaeum.runtime.bpm.organization;

import org.junit.Test;

public class PersonTest implements PersonTestContract {



	@Test
	public void testfullNameInitialValue() {
		BusinessNetwork parent = new BusinessNetwork();
		Person object = new Person(parent);
	}

}