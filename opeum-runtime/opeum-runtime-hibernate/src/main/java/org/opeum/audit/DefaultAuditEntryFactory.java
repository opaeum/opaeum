package org.opeum.audit;

import org.opeum.runtime.domain.IPersistentObject;

public class DefaultAuditEntryFactory implements AuditEntryFactory<AuditEntry> {

	@Override
	public AuditEntry createAuditEntry(IPersistentObject o, int version) {
		return new AuditEntry(o, version);
	}

	@Override
	public StringBuilder getInsertClause() {
		return new StringBuilder();
	}

	@Override
	public void appendToValuesClause(StringBuilder sb, AuditEntry t) {
		
	}

}
