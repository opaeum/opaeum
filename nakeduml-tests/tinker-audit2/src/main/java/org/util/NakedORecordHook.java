package org.util;

import com.orientechnologies.orient.core.db.graph.OGraphEdge;
import com.orientechnologies.orient.core.db.graph.OGraphVertex;
import com.orientechnologies.orient.core.hook.ORecordHook;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class NakedORecordHook implements ORecordHook {

	@Override
	public boolean onTrigger(TYPE iType, ORecord<?> iRecord) {
		ODocument document = (ODocument) iRecord;
		String className = document.getClassName();
		switch (iType) {
		case BEFORE_CREATE:
			System.out.println(iType);
			break;
		case BEFORE_READ:
			System.out.println(iType);
			break;
		case BEFORE_UPDATE:
			System.out.println(iType);
			break;
		case BEFORE_DELETE:
			System.out.println(iType);
			break;
		case AFTER_CREATE:
			System.out.println(iType);
			if (className.equals(OGraphVertex.class.getSimpleName())) {
				OrientVertex originalVertex = new OrientVertex((OrientGraph)GraphDb.getDB(), (ODocument)iRecord);
				OrientVertex auditVertex = createAuditVertexWithEdgeToOriginal(originalVertex);
				
				
			} else if (className.equals(OGraphEdge.class.getSimpleName())) {
				System.out.println();
			}
			break;
		case AFTER_READ:
			System.out.println(iType);
			break;
		case AFTER_UPDATE:
			System.out.println(iType);
			break;
		case AFTER_DELETE:
			System.out.println(iType);
			break;
		case ANY:
			System.out.println(iType);
			break;
		default:
			break;
		}
		return false;
	}
	
	private OrientVertex createAuditVertexWithEdgeToOriginal(OrientVertex originalVertex) {
		OrientVertex auditVertex = new OrientVertex((OrientGraph)GraphDb.getDB(), (ODocument)originalVertex.getRawElement().copy());
		GraphDb.getDB().addEdge(null, originalVertex, auditVertex, "original");
		return auditVertex;
	}

	private int countIter(Iterable<Edge> iter) {
		int count = 0;
		for (@SuppressWarnings("unused") Edge element : iter) {
			count++;
		}
		return count;
	}

}
