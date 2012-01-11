package org.nakeuml.tinker.concretetest;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;
import org.tinker.concretetest.God;
import org.tinker.concretetest.GodAudit;
import org.tinker.embeddedtest.REASON;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestEmbedded extends BaseLocalDbTest {

	@Test
	public void testSingleEnum() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		god.setReason(REASON.GOOD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, countVertices());
		assertEquals(2, countEdges());
		God godTest = new God(god.getVertex());
		Assert.assertEquals(1, godTest.getAudits().size());
		GodAudit auditGod = godTest.getAudits().get(0);
		Assert.assertNotNull(auditGod);
		Assert.assertEquals("THEGOD", auditGod.getName());
		Assert.assertEquals(REASON.GOOD, auditGod.getReason());
		God original = auditGod.getOriginal();
		Assert.assertNotNull(original);
		Assert.assertEquals("THEGOD", original.getName());
	}
	
	@Test
	public void testEmbeddedIntegers() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		god.addToEmbeddedInteger(0);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
	}	
	
}
