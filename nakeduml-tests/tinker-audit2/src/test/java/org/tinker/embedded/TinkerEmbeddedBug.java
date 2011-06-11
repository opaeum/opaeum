package org.tinker.embedded;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.util.GraphDb;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TinkerEmbeddedBug extends BaseLocalDbTest {

	@Test
	public void testEmbeddeCollectionBug() {
		db.startTransaction();
		Vertex vertex1 = GraphDb.getDb().addVertex(null);
		Vertex vertex2 = GraphDb.getDb().addVertex(null);
		Set<Integer> numbers = new HashSet<Integer>();
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		vertex1.setProperty("numberSet", numbers);
		vertex2.setProperty("numberList", new ArrayList<Integer>(numbers));
		db.stopTransaction(Conclusion.SUCCESS);
		Set<Integer> numbers1Retrieved = (Set<Integer>) vertex1.getProperty("numberSet");  
		List<Integer> numbers2Retrieved = (List<Integer>) vertex2.getProperty("numberList");  
		assertEquals(3, numbers1Retrieved.size());
		assertEquals(3, numbers2Retrieved.size());
		db.startTransaction();
		numbers.add(4);
		vertex1.setProperty("numberSet", new HashSet<Integer>(numbers));
		db.stopTransaction(Conclusion.SUCCESS);
		
		
		vertex1 = db.getVertex(vertex1.getId());
		vertex2 = db.getVertex(vertex2.getId());

		numbers1Retrieved = (Set<Integer>) ((OrientVertex) vertex1).getRawElement().field("numberSet", Set.class);
		numbers2Retrieved = (List<Integer>) ((OrientVertex) vertex2).getRawElement().field("numberList", List.class);
		assertEquals(4, numbers1Retrieved.size());
		assertEquals(3, numbers2Retrieved.size());		
		
		
//		numbers1Retrieved = (Set<Integer>) vertex1.getProperty("numberList");  
//		numbers2Retrieved = (Set<Integer>) vertex2.getProperty("numberList");  
//		assertEquals(4, numbers1Retrieved.size());
//		assertEquals(3, numbers2Retrieved.size());
	}
	
	public enum TEST implements Serializable {
		ONE, TWO;
	}
	
	@Test
	public void testSetOfEnumsOrientBug() {
		db.startTransaction();
		Vertex vertex1 = db.addVertex(null);
		vertex1.setProperty("one", TEST.ONE);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals("ONE", vertex1.getProperty("one"));
		db.startTransaction();
		Set<TEST> testEnums = new HashSet<TEST>();
		testEnums.add(TEST.ONE);
		testEnums.add(TEST.TWO);
		vertex1.setProperty("testEnums", testEnums);
		db.stopTransaction(Conclusion.SUCCESS);
	}
}
