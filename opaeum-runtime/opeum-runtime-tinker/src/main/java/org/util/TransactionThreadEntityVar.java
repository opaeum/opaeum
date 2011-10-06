package org.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opeum.runtime.domain.TinkerCompositionNode;
import org.opeum.runtime.domain.TinkerNode;


public class TransactionThreadEntityVar {
	
	private TransactionThreadEntityVar() {
	}

	private static ThreadLocal<Map<String, TinkerCompositionNode>> transactionVar = new ThreadLocal<Map<String, TinkerCompositionNode>>() {

		Map<String, TinkerCompositionNode> newVertexMap = new HashMap<String, TinkerCompositionNode>();
		
		public Map<String, TinkerCompositionNode> get() {
			return newVertexMap;
		}

		public void set(Map<String, TinkerCompositionNode> newValue) {
			newVertexMap = newValue;
		}
		
		@Override
	    protected Map<String, TinkerCompositionNode> initialValue() {
	        return new HashMap<String, TinkerCompositionNode>();
	    }

	};

	public static boolean hasNoAuditEntry(String clazzAndId) {
		Map<String, TinkerCompositionNode> newVertexMap = transactionVar.get();
		TinkerCompositionNode newVertex = newVertexMap.get(clazzAndId);
		return newVertex==null;
	}
	
	public static void clear() {
		transactionVar.get().clear();
	}

	public static void setNewEntity(TinkerCompositionNode node) {
		transactionVar.get().put(((TinkerNode)node).getVertex().getId().toString(), node);
	}
	
	public static List<TinkerCompositionNode> get() {
		return new ArrayList<TinkerCompositionNode>(transactionVar.get().values());
	}	

}
