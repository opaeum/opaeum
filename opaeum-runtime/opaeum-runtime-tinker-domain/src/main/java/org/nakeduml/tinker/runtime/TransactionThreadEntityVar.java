package org.nakeduml.tinker.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nakeduml.runtime.domain.TinkerCompositionNode;


public class TransactionThreadEntityVar {
	
	private TransactionThreadEntityVar() {
	}

	private static ThreadLocal<Map<String, TinkerCompositionNode>> transactionEntityVar = new ThreadLocal<Map<String, TinkerCompositionNode>>() {

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
		Map<String, TinkerCompositionNode> newVertexMap = transactionEntityVar.get();
		TinkerCompositionNode newVertex = newVertexMap.get(clazzAndId);
		return newVertex==null;
	}
	
	public static void clear() {
		transactionEntityVar.get().clear();
	}

	public static void setNewEntity(TinkerCompositionNode node) {
		transactionEntityVar.get().put(((TinkerCompositionNode)node).getVertex().getId().toString(), node);
	}
	
	public static List<TinkerCompositionNode> get() {
		return new ArrayList<TinkerCompositionNode>(transactionEntityVar.get().values());
	}
	
	public static TinkerCompositionNode remove(String key) {
		return transactionEntityVar.get().remove(key);
	}	
	

}
