package org.tinker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tinker.God;

public class TestCreateEntityAndProperty extends BaseLocalDbTest {

	@Test
	public void testSetGetProperty() {
		God god = new God();
		god.setName("THEGOD");
		assertEquals("THEGOD", god.getName());
		assertEquals(2, countVertices());
	}
	
}