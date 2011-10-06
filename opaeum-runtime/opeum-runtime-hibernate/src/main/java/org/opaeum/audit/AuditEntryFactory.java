package org.opeum.audit;

import org.opeum.runtime.domain.IPersistentObject;


public interface AuditEntryFactory<TYPE extends AuditEntry> {
	AuditEntry createAuditEntry(IPersistentObject o, int version);
	StringBuilder getInsertClause();
	void appendToValuesClause(StringBuilder sb, TYPE t);
}
