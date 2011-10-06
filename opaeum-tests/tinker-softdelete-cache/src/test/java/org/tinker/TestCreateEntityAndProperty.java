package org.tinker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;

public class TestCreateEntityAndProperty extends BaseLocalDbTest {

	@Test
	public void testSetGetProperty() {
		God god = new God();
		god.setName("THEGOD");
		assertEquals("THEGOD", god.getName());
		assertEquals(1, countVertices());
	}
	
}
