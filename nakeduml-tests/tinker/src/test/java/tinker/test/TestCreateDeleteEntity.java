package tinker.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tinker.God;

public class TestCreateDeleteEntity extends BaseTest {

	@Test
	public void testSetGetProperty() {
		God god = new God();
		god.setName("THEGOD");
		assertEquals("THEGOD", god.getName());
	}
	
}
