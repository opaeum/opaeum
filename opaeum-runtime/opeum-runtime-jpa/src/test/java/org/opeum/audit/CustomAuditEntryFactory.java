package org.opaeum.audit;

import org.opaeum.runtime.domain.IPersistentObject;

public class CustomAuditEntryFactory implements AuditEntryFactory<CustomAuditEntry> {
	private static final String INSERT_CLAUSE = "insert into custom_audit_entry(id, custom_value) values ";

	@Override
	public AuditEntry createAuditEntry(IPersistentObject o, int version) {
		CustomAuditEntry auditEntry2 = new CustomAuditEntry(o, version);
		auditEntry2.setCustomValue(((ParentAuditedObject) o).getStringProperty());
		return auditEntry2;
	}

	@Override
	public void appendToValuesClause(StringBuilder sb, CustomAuditEntry p) {
		if (sb.length() > INSERT_CLAUSE.length()) {
			sb.append(',');
		}
		sb.append("('");
		sb.append(p.getId().getId());
		sb.append("',");
		if (p.getCustomValue() != null) {
			sb.append("'");
			sb.append(p.getCustomValue());
			sb.append("'");
		} else {
			sb.append("null");
		}
		sb.append(")");
	}

	@Override
	public StringBuilder getInsertClause() {
		return new StringBuilder(INSERT_CLAUSE);
	}

}
