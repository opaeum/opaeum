package org.tinker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestCreateEntityAndProperty extends BaseLocalDbTest {

	@Test
	public void testSetGetProperty() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		assertEquals("THEGOD", god.getName());
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, countVertices());
	}
	
}
