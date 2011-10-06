package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestInterfaceManyToMany extends BaseLocalDbTest {

	@Test
	public void tetSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ManyA manyA1 = new ManyA1(god);
		manyA1.setName("manyA1");
		ManyA manyA11 = new ManyA1(god);
		manyA11.setName("manyA11");
		ManyA manyA2 = new ManyA2(god);
		manyA2.setName("manyA2");
		ManyA manyA21 = new ManyA2(god);
		manyA21.setName("manyA21");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		
		db.startTransaction();
		ManyB manyB1 = new ManyB1(god);
		manyB1.setName("manyB1");
		ManyB manyB11 = new ManyB1(god);
		manyB11.setName("manyB11");
		ManyB manyB2 = new ManyB2(god);
		manyB2.setName("manyB2");
		ManyB manyB21 = new ManyB2(god);
		manyB21.setName("manyB21");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(19, countVertices());
		assertEquals(26, countEdges());
		db.startTransaction();
		Set<ManyB> manyBs = new HashSet<ManyB>();
		manyBs.add(manyB1);
		manyBs.add(manyB11);
		manyBs.add(manyB2);
		manyBs.add(manyB21);		
		manyA1.addAllToManyB(manyBs);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(24, countVertices());
		assertEquals(39, countEdges());

		db.startTransaction();
		manyA11.addAllToManyB(manyBs);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(29, countVertices());
		assertEquals(52, countEdges());
		
		db.startTransaction();
		manyA11.clearManyB();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(34, countVertices());
		assertEquals(57, countEdges());
		int countManyB1 = 0;
		int countManyB2 = 0;
		for (ManyB manyB : manyA1.getManyB()) {
			if (manyB instanceof ManyB1) {
				countManyB1++;	
			}
			if (manyB instanceof ManyB2) {
				countManyB2++;				
			}
		}
		assertEquals(2, countManyB1);
		assertEquals(2, countManyB2);
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ManyA manyA1 = new ManyA1(god);
		manyA1.setName("manyA1");
		ManyA manyA11 = new ManyA1(god);
		manyA11.setName("manyA11");
		ManyA manyA2 = new ManyA2(god);
		manyA2.setName("manyA2");
		ManyA manyA21 = new ManyA2(god);
		manyA21.setName("manyA21");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		ManyB manyB1 = new ManyB1(god);
		manyB1.setName("manyB1");
		ManyB manyB11 = new ManyB1(god);
		manyB11.setName("manyB11");
		ManyB manyB2 = new ManyB2(god);
		manyB2.setName("manyB2");
		ManyB manyB21 = new ManyB2(god);
		manyB21.setName("manyB21");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(19, countVertices());
		assertEquals(26, countEdges());
		db.startTransaction();
		Set<ManyA> manyAs = new HashSet<ManyA>();
		manyAs.add(manyA1);
		manyAs.add(manyA11);
		manyAs.add(manyA2);
		manyAs.add(manyA21);
		manyB1.addAllToManyA(manyAs);
		manyB2.addAllToManyA(manyAs);
		manyB11.addAllToManyA(manyAs);
		manyB21.addAllToManyA(manyAs);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(27, countVertices());
		assertEquals(66, countEdges());
		db.startTransaction();
		manyB1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(33, countVertices());
		assertEquals(77, countEdges());
	}
}
