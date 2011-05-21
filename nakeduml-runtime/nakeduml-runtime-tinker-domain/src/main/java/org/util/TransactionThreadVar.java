package org.util;

import java.util.HashMap;
import java.util.Map;

import com.tinkerpop.blueprints.pgm.Vertex;


public class TransactionThreadVar {
	
	private TransactionThreadVar() {
	}

	private static ThreadLocal<Map<String, Vertex>> transactionVar = new ThreadLocal<Map<String, Vertex>>() {

		Map<String, Vertex> auditVertexMap = new HashMap<String, Vertex>();
		
		public Map<String, Vertex> get() {
			return auditVertexMap;
		}

		public void set(Map<String, Vertex> newValue) {
			auditVertexMap = newValue;
		}
		
		@Override
	    protected Map<String, Vertex> initialValue() {
	        return new HashMap<String, Vertex>();
	    }

	};

	public static Boolean hasNoAuditEntry(String clazzAndId) {
		Vertex newVertex = getAuditEntry(clazzAndId);
		return newVertex==null?true:false;
	}
	
	public static Vertex getAuditEntry(String clazzAndId) {
		Map<String, Vertex> auditVertexMap = transactionVar.get();
		Vertex auditVertex = auditVertexMap.get(clazzAndId);
		return auditVertex;
	}	
	
	public static void clear() {
		transactionVar.get().clear();
		GraphDb.incrementTransactionCount();
	}

	public static void putAuditVertexFalse(String clazzAndId, Vertex auditVertex) {
		transactionVar.get().put(clazzAndId, auditVertex);
	}

}
