package org.opaeum.audit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.opaeum.runtime.domain.IPersistentObject;

@Entity
@Table(name="custom_audit_entry")
public class CustomAuditEntry extends AuditEntry {
	private static final long serialVersionUID = 123;
	@Column(name="custom_value")
	private String customValue;
	public CustomAuditEntry() {
		super();
	}
	public CustomAuditEntry(IPersistentObject entity, int version) {
		super(entity, version);
	}
	public String getCustomValue() {
		return customValue;
	}
	public void setCustomValue(String customValue) {
		this.customValue = customValue;
	}
}
