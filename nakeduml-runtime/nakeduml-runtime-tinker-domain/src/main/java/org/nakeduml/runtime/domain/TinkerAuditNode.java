package org.nakeduml.runtime.domain;


public interface TinkerAuditNode extends TinkerNode {
	Long getTransactionNo();
//	List<? extends TinkerAuditNode> getNextAuditEntries();
	TinkerAuditNode getNextAuditEntry();
	TinkerAuditableNode getOriginal();
}
