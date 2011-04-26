package org.util;

import java.util.HashMap;
import java.util.Map;


public class TransactionThreadVar {
	
	private TransactionThreadVar() {
	}

	private static ThreadLocal<Map<String, Boolean>> transactionVar = new ThreadLocal<Map<String, Boolean>>() {

		Map<String, Boolean> newVertexMap = new HashMap<String, Boolean>();
		
		public Map<String, Boolean> get() {
			return newVertexMap;
		}

		public void set(Map<String, Boolean> newValue) {
			newVertexMap = newValue;
		}
		
		@Override
	    protected Map<String, Boolean> initialValue() {
	        return new HashMap<String, Boolean>();
	    }

	};

	public static Boolean hasNoAuditEntry(String clazzAndId) {
		Map<String, Boolean> newVertexMap = transactionVar.get();
		Boolean newVertex = newVertexMap.get(clazzAndId);
		return newVertex==null?true:newVertex;
	}
	
	public static void clear() {
		transactionVar.get().clear();
		GraphDb.incrementTransactionCount();
	}

	public static void setNewVertexFalse(String clazzAndId) {
		transactionVar.get().put(clazzAndId, false);
	}

}
