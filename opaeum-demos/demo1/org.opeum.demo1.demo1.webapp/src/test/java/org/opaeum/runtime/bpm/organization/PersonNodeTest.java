package org.opaeum.runtime.bpm.organization;

import junit.framework.Assert;

import org.junit.Test;

public class PersonNodeTest implements PersonNodeTestContract {



	@Test
	public void testfullNameInitialValue() {
		BusinessNetwork parent = new BusinessNetwork();
		PersonNode p = new PersonNode(parent, "ampieb");
		p.setFirstName("Ampie");
		p.setSurname("Barnard");
		Assert.assertEquals("Ampie Barnard", p.getFullName());
	}

}