package org.tinker;

import org.apache.commons.lang.time.StopWatch;
import org.junit.After;
import org.junit.Before;
import org.tinker.derivedunion.HumanHand;

import com.orientechnologies.orient.core.intent.OIntentMassiveInsert;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class TestLargeSet {

	protected TransactionalGraph db;

	@Before
	public void before() {
		db = new OrientGraph("local:/tmp/orientdbtest2");
		db.clear();
		db.setTransactionMode(Mode.MANUAL);
	}	
	
	@After
	public void after() {
		db.shutdown();
	}	
	
//	@Test
	public void test() {
		StopWatch stopWatch = new StopWatch();
		((OrientGraph)db).getRawGraph().declareIntent( new OIntentMassiveInsert() );
		db.startTransaction();
		stopWatch.start();
		Vertex one = db.addVertex(null);
		one.setProperty("name", "THEONE");
		for (int i = 0; i < 100000; i++) {
			Vertex many = db.addVertex(null);
			many.setProperty("name", "ANOTHERMANY");
			Edge edge = db.addEdge(null, one, many, "oneToMany");
			edge.setProperty("outClass", "dribbleOut");
			edge.setProperty("inClass", "dribbleIn");
			if (i % 200 == 0) {
				System.out.println(i);
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
		((OrientGraph)db).getRawGraph().declareIntent( null );
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		db.startTransaction();
		int i = 0;
		for (Edge edge : one.getOutEdges()) {
			Class<?> c;
			try {
				c = Class.forName(HumanHand.class.getName());
				HumanHand humanHand = (HumanHand)c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
				if (i % 200 == 0) {
					System.out.println(edge.getProperty("outClass"));
					System.out.println(i);
				}			
				i++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
	}
	
}
