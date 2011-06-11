package org.tinker;

import org.junit.Test;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TestRoot {

	@Test
	public void testRoot() {
		OrientGraph orientGraph = new OrientGraph("local:/tmp/tinker-testroot");
		orientGraph.setTransactionMode(Mode.MANUAL);
		orientGraph.startTransaction();
		Vertex v = orientGraph.addVertex(null);
		v.setProperty("transactionCount", 1);
		orientGraph.getRawGraph().setRoot("root", ((OrientVertex)v).getRawElement());
		orientGraph.stopTransaction(Conclusion.SUCCESS);		
	}
}
