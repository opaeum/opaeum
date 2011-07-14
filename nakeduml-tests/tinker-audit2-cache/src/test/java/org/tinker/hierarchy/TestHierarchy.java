package org.tinker.hierarchy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestHierarchy extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		RealRootFolder realRootFolder = new RealRootFolder(god);
		realRootFolder.setName("realRootFolder");
		Folder folder1 = new Folder(realRootFolder);
		folder1.setName("folder1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(8, countEdges());
		db.startTransaction();
		Folder folder2 = new Folder(realRootFolder);
		folder2.setName("folder2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(12, countEdges());
		db.startTransaction();
		Folder folder11 = new Folder(folder1);
		folder11.setName("folder11");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(16, countEdges());
		assertTrue(folder11.getParent().getParent() instanceof RealRootFolder);
		db.startTransaction();
		Folder folder111 = new Folder(folder11);
		folder111.setName("folder111");
		Folder folder1111 = new Folder(folder111);
		folder1111.setName("folder1111");
		db.stopTransaction(Conclusion.SUCCESS);
		Hierarchy hierarchy = folder1111;
		int countLevels = 0;
		while (!hierarchy.isRoot()) {
			countLevels++;
			hierarchy = hierarchy.getParent();
		}
		assertEquals(17, countVertices());
		assertEquals(23, countEdges());
		assertEquals(4, countLevels);
		assertEquals("THEGOD", ((RealRootFolder)hierarchy).getGod().getName());
	}
	
}
