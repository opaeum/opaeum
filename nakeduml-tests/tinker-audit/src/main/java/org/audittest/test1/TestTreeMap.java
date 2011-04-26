package org.audittest.test1;

import java.util.NavigableSet;
import java.util.TreeMap;

import org.util.DbListener;
import org.util.GraphDb;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class TestTreeMap {

	public static void main(String[] args) {
		TestTreeMap testTreeMap  = new TestTreeMap();
		testTreeMap.testVerticesCount();
	}
	
	private void getPreviousAuditVertex() {
		TreeMap<Integer, String> auditTransactions = new TreeMap<Integer, String>();
		auditTransactions.put(5, "5");
		auditTransactions.put(2, "2");
		auditTransactions.put(4, "4");
		auditTransactions.put(3, "3");
		auditTransactions.put(1, "1");
		auditTransactions.put(15, "15");
		
		NavigableSet<Integer> descendingKeySet = auditTransactions.navigableKeySet();
		descendingKeySet.remove(descendingKeySet.last());
		System.out.println(auditTransactions.get(descendingKeySet.last()));
	}
	
	private void testVerticesCount() {
		OrientGraph db = new OrientGraph("local:/tmp/orientdbtest");
		db.clear();
		db.addVertex(null);
		System.out.println(db.getRawGraph().countVertexes());
		db.shutdown();

		db = new OrientGraph("local:/tmp/orientdbtest");
		db.clear();
		db.addVertex(null);
		System.out.println(db.getRawGraph().countVertexes());
		db.shutdown();
		
		db = new OrientGraph("local:/tmp/orientdbtest");
		db.clear();
		db.addVertex(null);
		System.out.println(db.getRawGraph().countVertexes());
		db.shutdown();		
	}
}
