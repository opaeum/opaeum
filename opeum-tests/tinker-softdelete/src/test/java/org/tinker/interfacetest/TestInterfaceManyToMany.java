package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

public class TestInterfaceManyToMany extends BaseLocalDbTest {

	@Test
	public void tetSettingAndGetting() {
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
		Set<ManyA> manyAs = new HashSet<ManyA>();
		manyAs.add(manyA1);
		manyAs.add(manyA11);
		manyAs.add(manyA2);
		manyAs.add(manyA21);
		
		ManyB manyB1 = new ManyB1(god);
		manyB1.setName("manyB1");
		ManyB manyB11 = new ManyB1(god);
		manyB11.setName("manyB11");
		ManyB manyB2 = new ManyB2(god);
		manyB2.setName("manyB2");
		ManyB manyB21 = new ManyB2(god);
		manyB21.setName("manyB21");
		Set<ManyB> manyBs = new HashSet<ManyB>();
		manyBs.add(manyB1);
		manyBs.add(manyB11);
		manyBs.add(manyB2);
		manyBs.add(manyB21);		
		
		assertEquals(9, countVertices());
		assertEquals(8, countEdges());
		
		manyA1.addAllToManyB(manyBs);
		
		assertEquals(9, countVertices());
		assertEquals(12, countEdges());
		manyA11.addAllToManyB(manyBs);
		assertEquals(16, countEdges());
		
		manyA11.clearManyB();
		assertEquals(12, countEdges());
		
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
		Set<ManyA> manyAs = new HashSet<ManyA>();
		manyAs.add(manyA1);
		manyAs.add(manyA11);
		manyAs.add(manyA2);
		manyAs.add(manyA21);
		
		ManyB manyB1 = new ManyB1(god);
		manyB1.setName("manyB1");
		ManyB manyB11 = new ManyB1(god);
		manyB11.setName("manyB11");
		ManyB manyB2 = new ManyB2(god);
		manyB2.setName("manyB2");
		ManyB manyB21 = new ManyB2(god);
		manyB21.setName("manyB21");
		Set<ManyB> manyBs = new HashSet<ManyB>();
		manyBs.add(manyB1);
		manyBs.add(manyB11);
		manyBs.add(manyB2);
		manyBs.add(manyB21);	
		
		assertEquals(8, god.getMany().size());
		
		assertEquals(9, countVertices());
		assertEquals(8, countEdges());
		
		manyB1.addAllToManyA(manyAs);
		manyB2.addAllToManyA(manyAs);
		manyB11.addAllToManyA(manyAs);
		manyB21.addAllToManyA(manyAs);
		assertEquals(24, countEdges());
		
		assertEquals(4,manyA1.getManyB().size());
		manyB1.markDeleted();
		assertEquals(9, countVertices());
		assertEquals(24, countEdges());
		assertEquals(7, god.getMany().size());
		assertEquals(3,manyA1.getManyB().size());
		
	}
}
