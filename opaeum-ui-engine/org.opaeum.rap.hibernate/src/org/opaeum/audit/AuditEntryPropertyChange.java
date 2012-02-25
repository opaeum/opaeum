package org.opaeum.audit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.event.spi.EventSource;
import org.opaeum.runtime.domain.IPersistentObject;

@Entity
@DiscriminatorValue("A")
public class AuditEntryPropertyChange extends PropertyChange<AuditEntry> {
	{
		super.propertyChangeType = 'A';
	}

	private static final long serialVersionUID = 4977434834049997925L;

	public AuditEntryPropertyChange() {
		super();
	}

	public AuditEntryPropertyChange(String name, IPersistentObject oldValue, IPersistentObject value) {
		super(name, toAuditEntry(oldValue), toAuditEntry(value));
	}

	private static AuditEntry toAuditEntry(IPersistentObject oldValue) {
		if (oldValue == null) {
			return null;
		} else {
			return new AuditEntry(oldValue, oldValue.getObjectVersion());
		}
	}

	// Used where to update manyToOnes with the current AuditEntry
	public AuditEntryPropertyChange(String name, AuditEntry value2) {
		super(name, null, value2);
		setLatestValue(value2);
	}

	@Override
	public AuditEntry getOldValue() {
		if (oldValue == null && value != null && value.getId().getObjectVersion() > 0) {
//			//
//			AuditEntry previousVersion = getAuditEntry().getPreviousVersion();
//			if (previousVersion != null) {
//				PropertyChange<?> previousValue = previousVersion.getChanges().get(getPropertyName());
//				if (previousValue instanceof AuditEntryPropertyChange) {
//					oldValue = (AuditEntry) previousValue.getValue();
//				}
//			}
//
		}
		return super.getOldValue();
	}

	@Override
	protected String toString(AuditEntry t) {
		return t.getId().toString();
	}

	@Override
	protected AuditEntry resolveFromString(EventSource em, String stringValue) {
		return (AuditEntry) em.load(AuditEntry.class, new AuditEntryId(stringValue));
	}

	public void setLatestValue(AuditEntry latest) {
		super.stringValue = latest.getId().getId();
		this.value = latest;
	}

}
