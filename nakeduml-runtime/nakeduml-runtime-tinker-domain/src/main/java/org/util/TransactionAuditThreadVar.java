package org.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tinkerpop.blueprints.pgm.Vertex;


public class TransactionAuditThreadVar {
	
	private TransactionAuditThreadVar() {
	}

	private static ThreadLocal<Set<Vertex>> transactionAuditVertexVar = new ThreadLocal<Set<Vertex>>() {

		Set<Vertex> auditVertexSet = new HashSet<Vertex>();
		
		public Set<Vertex> get() {
			return auditVertexSet;
		}

		public void set(Set<Vertex> newValue) {
			auditVertexSet = newValue;
		}
		
		@Override
	    protected Set<Vertex> initialValue() {
	        return new HashSet<Vertex>();
	    }

	};

	public static void clear() {
		transactionAuditVertexVar.get().clear();
	}
	
	public static void addVertex(Vertex audit) {
		transactionAuditVertexVar.get().add(audit);
	}

	public static Set<Vertex> get() {
		return transactionAuditVertexVar.get();
	}

}
