package org.nakeduml.runtime.domain;


public interface TinkerAuditNode extends TinkerNode {
	Long getTransactionNo();
	TinkerAuditNode getNextAuditEntry();
	TinkerAuditableNode getOriginal();
	String getOriginalUid();
}
