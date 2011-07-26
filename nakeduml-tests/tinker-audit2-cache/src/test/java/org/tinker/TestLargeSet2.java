package org.tinker;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TestLargeSet2 extends BaseLocalDbTest {

	@Test
	public void test() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		God god = new God(true);
		god.setName("didthiswork");
		for (int i = 0; i < 5000; i++) {
			if (i % 100 == 0) {
//				db.stopTransaction(Conclusion.SUCCESS);
//				db.startTransaction();
				System.out.println(i);
//				stopWatch.stop();
//				System.out.println(stopWatch.toString());
//				stopWatch.reset();
//				stopWatch.start();
			}
			Universe universe1 = new Universe(god);
			universe1.setName("universe" + i);
			BlackHole blackHole1 = new BlackHole(universe1);
			blackHole1.setName("blackHole1");
			BlackHole blackHole2 = new BlackHole(universe1);
			blackHole2.setName("blackHole2");
			BlackHole blackHole3 = new BlackHole(universe1);
			blackHole3.setName("blackHole3");
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}
	
	@Test
	public void testPlain() {
		StopWatch totalStopWatch = new StopWatch();
		totalStopWatch.start();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
//		db.startTransaction();
		Vertex god = db.addVertex(null);
		god.setProperty("name", "didthiswork");
		Vertex auditGod = db.addVertex(null);
		auditGod.setProperty("name", "didthiswork");
		for (int i = 0; i < 5000; i++) {
			if (i % 100 == 0) {
//				db.stopTransaction(Conclusion.SUCCESS);
//				db.startTransaction();
				System.out.println(i);
				stopWatch.stop();
				System.out.println(stopWatch.toString());
				stopWatch.reset();
				stopWatch.start();				
			}
			
			Vertex universe = db.addVertex(null);
			universe.setProperty("name", "universe" + i);
			Edge godUniverseEdge = GraphDb.getDb().addEdge(null, god, universe, "A__god___universe_");
			godUniverseEdge.setProperty("outClass", "God");
			godUniverseEdge.setProperty("inClass", "Universe");

			Vertex auditUniverse = db.addVertex(null);
			auditUniverse.setProperty("name", "universe" + i);
			Edge auditUniverseEdge = GraphDb.getDb().addEdge(null, universe, auditUniverse, "original");
			auditUniverseEdge.setProperty("outClass", "Universe");
			auditUniverseEdge.setProperty("inClass", "Universe");
			
			Vertex blackHole1 = db.addVertex(null);
			blackHole1.setProperty("name", "blackHole1_" + i);
			Edge universeBlackHoleEdge1 = GraphDb.getDb().addEdge(null, universe, blackHole1, "A__universe_blackhole");
			universeBlackHoleEdge1.setProperty("outClass", "Universe");
			universeBlackHoleEdge1.setProperty("inClass", "BlackHole");

			Vertex auditBlackHole1 = db.addVertex(null);
			auditBlackHole1.setProperty("name", "blackHole1_" + i);
			Edge auditBlackHoleEdge1 = GraphDb.getDb().addEdge(null, blackHole1, auditBlackHole1, "original");
			auditBlackHoleEdge1.setProperty("outClass", "BlackHole");
			auditBlackHoleEdge1.setProperty("inClass", "BlackHole");
			
			Vertex blackHole2 = db.addVertex(null);
			blackHole2.setProperty("name", "blackHole2_" + i);
			Edge universeBlackHoleEdge2 = GraphDb.getDb().addEdge(null, universe, blackHole2, "A__universe_blackhole");
			universeBlackHoleEdge2.setProperty("outClass", "Universe");
			universeBlackHoleEdge2.setProperty("inClass", "BlackHole");

			Vertex auditBlackHole2 = db.addVertex(null);
			auditBlackHole2.setProperty("name", "blackHole2_" + i);
			Edge auditBlackHoleEdge2 = GraphDb.getDb().addEdge(null, blackHole2, auditBlackHole2, "original");
			auditBlackHoleEdge2.setProperty("outClass", "BlackHole");
			auditBlackHoleEdge2.setProperty("inClass", "BlackHole");
			
			Vertex blackHole3 = db.addVertex(null);
			blackHole3.setProperty("name", "blackHole3_" + i);
			Edge universeBlackHoleEdge3 = GraphDb.getDb().addEdge(null, universe, blackHole3, "A__universe_blackhole");
			universeBlackHoleEdge3.setProperty("outClass", "Universe");
			universeBlackHoleEdge3.setProperty("inClass", "BlackHole");

			Vertex auditBlackHole3 = db.addVertex(null);
			auditBlackHole3.setProperty("name", "blackHole3_" + i);
			Edge auditBlackHoleEdge3 = GraphDb.getDb().addEdge(null, blackHole3, auditBlackHole3, "original");
			auditBlackHoleEdge3.setProperty("outClass", "BlackHole");
			auditBlackHoleEdge3.setProperty("inClass", "BlackHole");
			
		}
//		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		totalStopWatch.stop();
		System.out.println("Total time taken = " + totalStopWatch.toString());
	}	
	
}
