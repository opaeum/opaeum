package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestAuditAttributeCache extends BaseLocalDbTest {

	@Test
	public void testMany() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ManyA1 manyA11 = new ManyA1(god);
		manyA11.setName("manyA11");
		ManyB1 manyB11 = new ManyB1(god);
		manyB11.setName("manyB11");
		ManyB1 manyB12 = new ManyB1(god);
		manyB12.setName("manyB12");
		manyA11.getManyB().add(manyB11);
		manyA11.getManyB().add(manyB12);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(14, countEdges());
		god.getMany().clear();
		boolean found = false;
		for (Many many : god.getMany()) {
			if (many instanceof ManyA) {
				((ManyA)many).getManyB().clear();
				found = ((ManyA)many).getManyB().size()==2;
			}
		}
		assertTrue(found);
		db.startTransaction();
		ManyA toBeRemovedA = null;
		for (Many many : god.getMany()) {
			if (many instanceof ManyA) {
				toBeRemovedA = (ManyA) many;	
			}
		}
		for (Many many : god.getMany()) {
			if (many instanceof ManyB) {
				ManyB manyB = (ManyB)many;
				manyB.getManyA().remove(toBeRemovedA);
				toBeRemovedA.getManyB().remove(many);
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(17, countEdges());
		assertEquals(3, god.getMany().size());
		for (Many many : god.getMany()) {
			if (many instanceof ManyA) {
				assertEquals(0, ((ManyA) many).getManyB().size());
			} else if (many instanceof ManyB) {
				assertEquals(0, ((ManyB) many).getManyA().size());
			}
		}
		
	}
}
