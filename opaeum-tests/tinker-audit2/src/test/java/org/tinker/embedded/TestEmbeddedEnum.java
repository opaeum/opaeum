package org.tinker.embedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;
import org.tinker.REASON;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestEmbeddedEnum extends BaseLocalDbTest {

	@Test
	public void testSingleEnumAssociation() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		god.setREASON(REASON.GOOD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertNotNull(god.getREASON());
		assertEquals(REASON.GOOD, god.getREASON());
		db.startTransaction();
		god.setREASON(REASON.BAD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertNotNull(god.getREASON());
		assertEquals(REASON.BAD, god.getREASON());
	}
	
	@Test
	public void testSingleEnumEmbedded() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		god.setSingleReason(REASON.GOOD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertNotNull(god.getSingleReason());
		assertEquals(REASON.GOOD, god.getSingleReason());
		db.startTransaction();
		god.setSingleReason(REASON.BAD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertNotNull(god.getSingleReason());
		assertEquals(REASON.BAD, god.getSingleReason());
	}
	
	@Test
	public void testMultiEnumAssociation() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		
		Set<REASON> reasons = new HashSet<REASON>();
		reasons.add(REASON.GOOD);
		reasons.add(REASON.BAD);
		
		god.setReasons(reasons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getReasons().size());
		
		db.startTransaction();
		reasons.remove(REASON.GOOD);
		god.setReasons(reasons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1, god.getReasons().size());
		assertEquals(REASON.BAD, god.getReasons().iterator().next());
		
		db.startTransaction();
		god.addToReasons(REASON.GOOD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getReasons().size());
		
		db.startTransaction();
		god.removeFromReasons(REASON.BAD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1, god.getReasons().size());
		assertEquals(REASON.GOOD, god.getReasons().iterator().next());
		
		db.startTransaction();
		Set<REASON> reasons2 = new HashSet<REASON>();
		reasons2.add(REASON.GOOD);
		reasons2.add(REASON.BAD);
		god.setReasons(reasons2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getReasons().size());
	}
	
	@Test
	public void testMultiEnumEmbedded() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		
		Set<REASON> reasons = new HashSet<REASON>();
		reasons.add(REASON.GOOD);
		reasons.add(REASON.BAD);
		
		god.setMultiEmbeddedReason(reasons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getMultiEmbeddedReason().size());
		
		db.startTransaction();
		reasons.remove(REASON.GOOD);
		god.setMultiEmbeddedReason(reasons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1, god.getMultiEmbeddedReason().size());
		assertEquals(REASON.BAD, god.getMultiEmbeddedReason().iterator().next());
		
		db.startTransaction();
		god.addToMultiEmbeddedReason(REASON.GOOD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getMultiEmbeddedReason().size());
		
		db.startTransaction();
		god.removeFromMultiEmbeddedReason(REASON.BAD);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1, god.getMultiEmbeddedReason().size());
		assertEquals(REASON.GOOD, god.getMultiEmbeddedReason().iterator().next());
		
		db.startTransaction();
		Set<REASON> reasons2 = new HashSet<REASON>();
		reasons2.add(REASON.GOOD);
		reasons2.add(REASON.BAD);
		god.setMultiEmbeddedReason(reasons2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getMultiEmbeddedReason().size());
	}	
}
