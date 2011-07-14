package org.tinker.jetty.simulation;

import org.junit.Test;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;

public class JettySimulationTest {


//	@Test
//	public void testJetty() {
//		NakedGraph db = startup();
//		
//		db.startTransaction();
//		God god = (God) db.getCompositeRoots().get(0);
//		god.setName("THEGOD");
//		db.stopTransaction(Conclusion.SUCCESS);
//		
//		Object id = god.getVertex().getId();
//		
//		GraphDb.remove();
//		db.shutdown();
//		db = startup();
//		
//		db.startTransaction();
//		god = new God( db.getVertex(id) );
//		god.setName("THEGODAGAIN");
//		db.stopTransaction(Conclusion.SUCCESS);
//	}
//
//	private NakedGraph startup() {
//		OrientGraph orientGraph = new OrientGraph("local:/tmp/tinkerjettytest");
//		orientGraph.setTransactionMode(Mode.MANUAL);
//		NakedGraph nakedGraph = new NakedOrientGraph(orientGraph, false);
//		nakedGraph.startTransaction();
//		nakedGraph.addRoot();
//		nakedGraph.stopTransaction(Conclusion.SUCCESS);
//		nakedGraph.clearAutoIndices();
//		nakedGraph.registerListeners();	
//		GraphDb.setDb(nakedGraph);
//		return nakedGraph;
//	}
}
