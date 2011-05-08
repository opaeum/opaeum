package org.nakeduml.runtime.domain;

import java.util.List;

public interface TinkerAuditNode extends TinkerNode {
	int getTransactionNo();
	List<? extends TinkerAuditNode> getNextAuditEntries();
}
