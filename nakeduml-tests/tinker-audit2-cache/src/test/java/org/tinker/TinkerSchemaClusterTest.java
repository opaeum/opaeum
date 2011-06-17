package org.tinker;

import org.junit.Test;

public class TinkerSchemaClusterTest {

	@Test
	public void testSchemaAndCluster() {
//		OrientGraph db = new OrientGraph("local:/tmp/testSchemaAndClusterXX11");
//		db.setTransactionMode(Mode.MANUAL);
//		OGraphDatabase rawGraph = ((OrientGraph)db).getRawGraph();
//		OSchema schema = rawGraph.getMetadata().getSchema();
//		if ( !schema.existsClass("Employee") ) {
//			schema.createClass("Employee", schema.getClass("OGraphVertex"), rawGraph.getStorage().addCluster("EmployeeCluster", OStorage.CLUSTER_TYPE.PHYSICAL));
//		}
//		db.startTransaction();
//		ODocument oDocument = rawGraph.createVertex("Employee");
//		Vertex vertex = new OrientVertex(db, oDocument);
//		vertex.setProperty("test", "testValue");
//		db.stopTransaction(Conclusion.SUCCESS);
//		Assert.assertTrue(rawGraph.countClass("Employee")>0);
	}
}
