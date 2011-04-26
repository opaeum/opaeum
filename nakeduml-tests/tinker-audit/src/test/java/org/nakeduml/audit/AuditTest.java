package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.audittest.test1.God;
import org.audittest.test1.GodAudit;
import org.audittest.test1.Universe;
import org.junit.Test;
import org.tinker.BaseMemoryDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTest extends BaseMemoryDbTest {

	@Test
	public void testAuditGod() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		assertTrue(god.getAudits().size()==1);
		assertTrue(god.getAudits().iterator().next() instanceof GodAudit);
		assertEquals("THEGOD",god.getAudits().iterator().next().getName());
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());
		db.startTransaction();
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInOneTransaction() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Universe universe3 = new Universe(god);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(10, countEdges());
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInTwoTransaction() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Universe universe3 = new Universe(god);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(11, countEdges());
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInThreeTransaction() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Universe universe3 = new Universe(god);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(11, countEdges());
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Universe universe4 = new Universe(god);
		universe4.setName("universe4");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(18, countEdges());
		db.startTransaction();
		universe4.setName("universe4");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(25, countEdges());
		god.getUniverse();
	}
	
}
