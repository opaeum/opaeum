package org.tinker.hierarchy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

public class TestHierarchy extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		God god = new God();
		god.setName("THEGOD");
		RealRootFolder realRootFolder = new RealRootFolder(god);
		realRootFolder.setName("realRootFolder");
		Folder folder1 = new Folder(realRootFolder);
		folder1.setName("folder1");
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		Folder folder2 = new Folder(realRootFolder);
		folder2.setName("folder2");
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
		
		Folder folder11 = new Folder(folder1);
		folder11.setName("folder11");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		assertTrue(folder11.getParent().getParent() instanceof RealRootFolder);
		
		Folder folder111 = new Folder(folder11);
		folder111.setName("folder111");
		Folder folder1111 = new Folder(folder111);
		folder1111.setName("folder1111");

		Hierarchy hierarchy = folder1111;
		int countLevels = 0;
		while (!hierarchy.isRoot()) {
			countLevels++;
			hierarchy = hierarchy.getParent();
		}
		assertEquals(4, countLevels);
		assertEquals("THEGOD", ((RealRootFolder)hierarchy).getGod().getName());
	}
	
}
