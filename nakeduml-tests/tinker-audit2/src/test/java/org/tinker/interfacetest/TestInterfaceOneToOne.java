package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.tinker.BaseLocalDbTest;
import org.tinker.God;
import org.util.GraphDb;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TestInterfaceOneToOne extends BaseLocalDbTest {

	@Test
	public void test() {
		Vertex vertex1 = GraphDb.getDB().addVertex(null);
		Vertex vertex2 = GraphDb.getDB().addVertex(null);
		GraphDb.getDB().addEdge(null, vertex1, vertex2, "one");
		GraphDb.getDB().addEdge(null, vertex1, vertex2, "one");
		assertEquals(2, countVertices());
		assertEquals(2, countEdges());
	}
	
	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Creature creature = new Creature(god);
		creature.setName("creature");
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		Spook spook = new Spook(god);
		spook.setName("spook");
		Soul soul = new Soul(god);
		soul.setName("soul");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(16, countEdges());
		db.startTransaction();
		creature.setSpirit(spook);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(20, countEdges());
		db.startTransaction();
		soul.setBeing(human);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(16, countVertices());
		assertEquals(24, countEdges());
		db.startTransaction();
		human.setSpirit(spook);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(30, countEdges());
		assertEquals("spook", human.getSpirit().getName()); 
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Creature creature = new Creature(god);
		creature.setName("creature");
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		Spook spook = new Spook(god);
		spook.setName("spook");
		Soul soul = new Soul(god);
		soul.setName("soul");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(16, countEdges());
		db.startTransaction();
		creature.setSpirit(spook);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(20, countEdges());
		db.startTransaction();
		soul.setBeing(human);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(16, countVertices());
		assertEquals(24, countEdges());
		db.startTransaction();
		human.setSpirit(spook);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(30, countEdges());
		assertEquals("spook", human.getSpirit().getName()); 
		db.startTransaction();
		human.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(21, countVertices());
		assertEquals(31, countEdges());
	}	
}
