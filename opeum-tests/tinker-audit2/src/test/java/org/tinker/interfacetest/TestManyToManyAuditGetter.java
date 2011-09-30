package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestManyToManyAuditGetter extends BaseLocalDbTest {

	@Test
	public void testGetter() {
		db.startTransaction();
		God god = new God();
		ManyA manyA1 = new ManyA1(god);
		manyA1.setName("manyA1");
		ManyA manyA11 = new ManyA1(god);
		manyA11.setName("manyA11");
		ManyA manyA111 = new ManyA1(god);
		manyA111.setName("manyA111");
		ManyB manyB1 = new ManyB1(god);
		manyB1.setName("manyB1");
		ManyB manyB11 = new ManyB1(god);
		manyB11.setName("manyB11");
		ManyB manyB111 = new ManyB1(god);
		manyB111.setName("manyB111");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(19, countEdges());
		assertEquals(1, god.getAudits().size());
		assertEquals(1, manyA1.getAudits().size());
		assertEquals(1, manyB1.getAudits().size());
		assertEquals(6, god.getAudits().get(0).getMany().size());

		db.startTransaction();
		manyA1.addToManyB(manyB1);
		manyA1.setName("manyA1_1");
		manyB1.setName("manyB1_1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1, god.getAudits().size());
		assertEquals(2, manyA1.getAudits().size());
		assertEquals(2, manyB1.getAudits().size());
		db.startTransaction();
		assertEquals(0, ((ManyA1) manyA1).getAudits().get(0).getManyB().size());
		assertEquals(1, ((ManyA1) manyA1).getAudits().get(1).getManyB().size());
		assertEquals(0, ((ManyB1) manyB1).getAudits().get(0).getManyA().size());
		assertEquals(1, ((ManyB1) manyB1).getAudits().get(1).getManyA().size());
		assertEquals("manyA1", ((ManyA1) manyA1).getAudits().get(0).getName());
		assertEquals("manyB1", ((ManyB1) manyB1).getAudits().get(0).getName());
		assertEquals("manyA1_1", ((ManyA1) manyA1).getAudits().get(1).getName());
		assertEquals("manyB1_1", ((ManyB1) manyB1).getAudits().get(1).getName());
		assertEquals("manyB1_1", ((ManyA1) manyA1).getAudits().get(1).getManyB().iterator().next().getName());
		assertEquals("manyA1_1", ((ManyB1) manyB1).getAudits().get(1).getManyA().iterator().next().getName());

		manyA1.addToManyB(manyB11);
		manyA1.setName("manyA1_11");
		manyB11.setName("manyB11_1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1, god.getAudits().size());
		assertEquals(3, manyA1.getAudits().size());
		assertEquals(2, manyB1.getAudits().size());
		assertEquals(2, manyB11.getAudits().size());

		db.startTransaction();
		assertEquals(0, ((ManyA1) manyA1).getAudits().get(0).getManyB().size());
		assertEquals(1, ((ManyA1) manyA1).getAudits().get(1).getManyB().size());
		assertEquals(2, ((ManyA1) manyA1).getAudits().get(2).getManyB().size());
		assertEquals(0, ((ManyB1) manyB1).getAudits().get(0).getManyA().size());
		assertEquals(1, ((ManyB1) manyB1).getAudits().get(1).getManyA().size());
		assertEquals(0, ((ManyB1) manyB11).getAudits().get(0).getManyA().size());
		assertEquals(1, ((ManyB1) manyB11).getAudits().get(1).getManyA().size());

		assertEquals("manyA1", ((ManyA1) manyA1).getAudits().get(0).getName());
		assertEquals("manyB1", ((ManyB1) manyB1).getAudits().get(0).getName());
		assertEquals("manyB11", ((ManyB1) manyB11).getAudits().get(0).getName());
		assertEquals("manyA1_1", ((ManyA1) manyA1).getAudits().get(1).getName());
		assertEquals("manyB1_1", ((ManyB1) manyB1).getAudits().get(1).getName());
		assertEquals("manyB11_1", ((ManyB1) manyB11).getAudits().get(1).getName());

		assertEquals(2, ((ManyA1) manyA1).getAudits().get(2).getManyB().size());
		ManyA1Audit manyAuditA1 = ((ManyA1) manyA1).getAudits().get(2);

		boolean foundManyB1_1 = false;
		boolean foundManyB11_1 = false;
		for (ManyBAudit manyBAudit : manyAuditA1.getManyB()) {
			if (manyBAudit.getName().equals("manyB1_1")) {
				foundManyB1_1 = true;
			} else if (manyBAudit.getName().equals("manyB11_1")) {
				foundManyB11_1 = true;
			}
		}
		assertTrue(foundManyB1_1 && foundManyB11_1);
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction();
		manyA11.addToManyB(manyB1);
		manyA11.addToManyB(manyB11);
		manyA11.setName("manyA11_1");
		manyB1.setName("manyB1_11");
		manyB11.setName("manyB11_11");
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction();
		assertEquals(2, ((ManyB1) manyB1).getAudits().get(2).getManyA().size());
		boolean foundManyA11_1 = false;
		boolean foundManyA1_11 = false;
		for (ManyAAudit manyAAudit : ((ManyB1) manyB1).getAudits().get(2).getManyA()) {
			if (manyAAudit.getName().equals("manyA11_1")) {
				foundManyA11_1 = true;
			} else if (manyAAudit.getName().equals("manyA1_11")) {
				foundManyA1_11 = true;
			}
		}
		assertTrue(foundManyA11_1 && foundManyA1_11);
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction();
		manyA111.addToManyB(manyB1);
		manyA111.addToManyB(manyB11);
		manyA111.setName("manyA111_1");
		manyB1.setName("manyB1_111");
		manyB11.setName("manyB11_111");
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction(); 
		assertEquals(4, ((ManyB1) manyB11).getAudits().size());
		foundManyA1_11 = false;
		foundManyA11_1 = false;
		boolean foundManyA111_1 = false;
		for (ManyAAudit manyAAudit : ((ManyB1) manyB11).getAudits().get(3).getManyA()) {
			if (manyAAudit.getName().equals("manyA1_11")) {
				foundManyA1_11 = true;
			} else if (manyAAudit.getName().equals("manyA11_1")) {
				foundManyA11_1 = true;
			} else if (manyAAudit.getName().equals("manyA111_1")) {
				foundManyA111_1 = true;
			}
		}
		assertTrue(foundManyA1_11 && foundManyA11_1 && foundManyA111_1);

		assertEquals(0, ((ManyA1) manyA1).getAudits().get(0).getManyB().size());
		assertEquals(1, ((ManyA1) manyA1).getAudits().get(1).getManyB().size());
		assertEquals(2, ((ManyA1) manyA1).getAudits().get(2).getManyB().size());
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction();
		manyB11.removeFromManyA(manyA1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(5, manyB11.getAudits().size());
		db.startTransaction();
		assertEquals(2, ((ManyB1) manyB11).getAudits().get(4).getManyA().size());
		assertEquals(3, ((ManyB1) manyB11).getAudits().get(3).getManyA().size());
		assertEquals(2, ((ManyB1) manyB11).getAudits().get(2).getManyA().size());
		assertEquals(1, ((ManyB1) manyB11).getAudits().get(1).getManyA().size());
		assertEquals(0, ((ManyB1) manyB11).getAudits().get(0).getManyA().size());
		db.stopTransaction(Conclusion.SUCCESS);
	}
}
