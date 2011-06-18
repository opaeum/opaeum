package org.tinker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestCompositeMarkDeleted extends BaseLocalDbTest {

	@Test
	public void testDeleteUniverses() {
		db.startTransaction();
		God god = new God();
		Universe universe1 = new Universe(god);
		Universe universe2 = new Universe(god);
		Universe universe3 = new Universe(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(10, countEdges());
		db.startTransaction();
		universe1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		assertEquals(2, god.getUniverse().size());
		assertEquals(2, god.getAudits().size());
		assertEquals(3, god.getAudits().get(0).getUniverse().size());
		assertEquals(2, god.getAudits().get(1).getUniverse().size());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
}
