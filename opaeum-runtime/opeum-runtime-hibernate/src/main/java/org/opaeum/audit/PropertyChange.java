package org.opeum.audit;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.event.EventSource;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("P")
@javax.persistence.Table(name = "property_change")
@DiscriminatorColumn(discriminatorType = DiscriminatorType.CHAR, name = "property_change_type")
public abstract class PropertyChange<T> implements Serializable, Comparable<PropertyChange<T>> {

	private static final long serialVersionUID = -8033131969731042935L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "property_change_type", insertable = false, updatable = false)
	protected char propertyChangeType;
	@ManyToOne
	@Index(name = "property_change_audit_entry", columnNames = { "audit_entry_id" })
	@JoinColumn(name = "audit_entry_id", referencedColumnName = "id")
	AuditEntry auditEntry;
	@Basic
	@Column(name = "property_name")
	String propertyName;
	@Column(name = "string_value")
	@Lob
	protected String stringValue;
	@Column(name = "old_string_value")
	@Lob
	protected String oldStringValue;
	@Transient
	protected T value;
	@Transient
	protected T oldValue;

	public PropertyChange() {
	}

	protected PropertyChange(String name, T oldValue, T newValue) {
		this.propertyName = name;
		if (oldValue != null) {
			this.oldStringValue = toString(oldValue);
			this.oldValue = oldValue;
		}
		if (newValue != null) {
			this.stringValue = toString(newValue);
			this.value = newValue;
		}
	}

	protected abstract String toString(T t);

	protected abstract T resolveFromString(EventSource eventSource, String stringValue);

	public Long getId() {
		return id;
	}

	public String getOldStringValue() {
		return oldStringValue;
	}

	public T getValue() {
		return value;
	}

	public T getOldValue() {
		return oldValue;
	}

	public char getPropertyChangeType() {
		return propertyChangeType;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void resolve(EventSource eventSource) {
		if (oldStringValue != null) {
			this.oldValue = resolveFromString(eventSource, oldStringValue);
		}
		if (stringValue != null) {
			this.value = resolveFromString(eventSource, stringValue);
		}
	}

	public AuditEntry getAuditEntry() {
		return auditEntry;
	}

	public void setAuditEntry(AuditEntry auditEntry) {
		this.auditEntry = auditEntry;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public int compareTo(PropertyChange<T> arg0) {
		int result = propertyName.compareTo(arg0.getPropertyName());

		if (result == 0) {
			return auditEntry.getObjectVersion() - arg0.auditEntry.getObjectVersion();
		}
		return result;
	}
	public String toString(){
		return propertyName + ":" + propertyChangeType + ":" +oldStringValue + ":" + stringValue;
	}

}
