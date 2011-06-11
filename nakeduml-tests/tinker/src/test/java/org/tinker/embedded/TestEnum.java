package org.tinker.embedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;
import org.tinker.REASON;

public class TestEnum extends BaseLocalDbTest {

	@Test
	public void testSingleEnumAssociation() {
		God god = new God();
		god.setName("THEGOD");
		god.setREASON(REASON.GOOD);
		assertNotNull(god.getREASON());
		assertEquals(REASON.GOOD, god.getREASON());
		god.setREASON(REASON.BAD);
		assertNotNull(god.getREASON());
		assertEquals(REASON.BAD, god.getREASON());
	}
	
	@Test
	public void testSingleEnumEmbedded() {
		God god = new God();
		god.setName("THEGOD");
		god.setSingleReason(REASON.GOOD);
		assertNotNull(god.getSingleReason());
		assertEquals(REASON.GOOD, god.getSingleReason());
		god.setSingleReason(REASON.BAD);
		assertNotNull(god.getSingleReason());
		assertEquals(REASON.BAD, god.getSingleReason());
	}
	
	@Test
	public void testMultiEnumAssociation() {
		God god = new God();
		god.setName("THEGOD");
		
		Set<REASON> reasons = new HashSet<REASON>();
		reasons.add(REASON.GOOD);
		reasons.add(REASON.BAD);
		
		god.setReasons(reasons);
		assertEquals(2, god.getReasons().size());
		
		reasons.remove(REASON.GOOD);
		god.setReasons(reasons);
		assertEquals(1, god.getReasons().size());
		assertEquals(REASON.BAD, god.getReasons().iterator().next());
		
		god.addToReasons(REASON.GOOD);
		assertEquals(2, god.getReasons().size());
		
		god.removeFromReasons(REASON.BAD);
		assertEquals(1, god.getReasons().size());
		assertEquals(REASON.GOOD, god.getReasons().iterator().next());
		
		Set<REASON> reasons2 = new HashSet<REASON>();
		reasons2.add(REASON.GOOD);
		reasons2.add(REASON.BAD);
		god.setReasons(reasons2);
		assertEquals(2, god.getReasons().size());
	}
	
	@Test
	public void testMultiEnumEmbedded() {
		God god = new God();
		god.setName("THEGOD");
		
		Set<REASON> reasons = new HashSet<REASON>();
		reasons.add(REASON.GOOD);
		reasons.add(REASON.BAD);
		
		god.setMultiEmbeddedReason(reasons);
		assertEquals(2, god.getMultiEmbeddedReason().size());
		
		reasons.remove(REASON.GOOD);
		god.setMultiEmbeddedReason(reasons);
		assertEquals(1, god.getMultiEmbeddedReason().size());
		assertEquals(REASON.BAD, god.getMultiEmbeddedReason().iterator().next());
		
		god.addToMultiEmbeddedReason(REASON.GOOD);
		assertEquals(2, god.getMultiEmbeddedReason().size());
		
		god.removeFromMultiEmbeddedReason(REASON.BAD);
		assertEquals(1, god.getMultiEmbeddedReason().size());
		assertEquals(REASON.GOOD, god.getMultiEmbeddedReason().iterator().next());
		
		Set<REASON> reasons2 = new HashSet<REASON>();
		reasons2.add(REASON.GOOD);
		reasons2.add(REASON.BAD);
		god.setMultiEmbeddedReason(reasons2);
		assertEquals(2, god.getMultiEmbeddedReason().size());
	}		
	
}
