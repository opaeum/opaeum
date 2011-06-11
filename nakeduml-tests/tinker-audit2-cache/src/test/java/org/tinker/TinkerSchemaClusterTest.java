package org.tinker;

import org.junit.Assert;
import org.junit.Test;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.storage.OStorage;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TinkerSchemaClusterTest {

	@Test
	public void testSchemaAndCluster() {
		OrientGraph db = new OrientGraph("local:/tmp/testSchemaAndCluster");
		db.setTransactionMode(Mode.MANUAL);
		OGraphDatabase rawGraph = ((OrientGraph)db).getRawGraph();
		OSchema schema = rawGraph.getMetadata().getSchema();
		if ( !schema.existsClass("Employee") ) {
			schema.createClass("Employee", schema.getClass("OGraphVertex"), rawGraph.getStorage().addCluster("EmployeeCluster", OStorage.CLUSTER_TYPE.LOGICAL));
		}
		db.startTransaction();
		ODocument oDocument = rawGraph.createVertex("Employee");
		Vertex vertex = new OrientVertex(db, oDocument);
		vertex.setProperty("test", "testValue");
		db.stopTransaction(Conclusion.SUCCESS);
		Assert.assertEquals(1, rawGraph.countClass("Employee"));
	}
}
