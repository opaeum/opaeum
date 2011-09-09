package org.nakeduml.audit;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "AuditEntry")
@javax.persistence.Table(name = "audit_entry")
@org.hibernate.annotations.Table(appliesTo = "audit_entry", indexes = { @Index(name = "audit_entry_idx", columnNames = { "original_type", "original_id",
		"object_version" }) })
public class AuditEntry implements Serializable, Comparable<AuditEntry> {

	private static final long serialVersionUID = -5996095627052884699L;
	@EmbeddedId
	AuditEntryId id;
	@Basic
	@Column(name = "original_id")
	Long originalId;
	@Basic
	@Column(name = "original_type")
	String originalType;
	@Basic
	@Column(name = "object_version")
	int objectVersion;
	@OneToMany(mappedBy = "auditEntry", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@MapKey(name = "propertyName")
	Map<String, PropertyChange<?>> changes = new HashMap<String, PropertyChange<?>>();
	@ManyToOne()
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "previous_version_id", referencedColumnName = "id")
	private AuditEntry previousVersion;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="audit_date_time")
	private Date auditDateTime=new Date(System.currentTimeMillis());
	@Transient
	private Map<String, IPersistentObject> manyToOnes = new HashMap<String, IPersistentObject>();
	@Transient
	private AuditEntryId previousVersionId;
	@Transient
	private Class<? extends IPersistentObject> originalClass;
	@Transient
	private IPersistentObject original;

	public AuditEntry() {
		super();
	}

	public AuditEntry(IPersistentObject entity, int version) {
		this.originalClass = (Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entity);
		this.originalType = originalClass.getName();
		this.originalId = entity.getId();
		this.objectVersion = version;
		this.id = new AuditEntryId(entity, version);
		this.previousVersionId = id.previousVersion();
	}

	public void addManyToOne(String name, IPersistentObject value) {
		manyToOnes.put(name, value);
	}

	public Set<Entry<String, IPersistentObject>> getManyToOnes() {
		return manyToOnes.entrySet();
	}

	public void putPropertyChange(String name, Object oldValue, Object value) {
		PropertyChange<?> pc = null;

		if (isFloatingPoint(value) || isFloatingPoint(oldValue)) {
			pc = new FloatingPointPropertyChange(name, (Number) oldValue,(Number) value );
		} else if (isInteger(value) || isInteger(oldValue)) {
			pc = new IntegerPropertyChange(name, (Number) oldValue,(Number) value);
		} else if (isDate(value) || isDate(oldValue)) {
			pc = new DateTimePropertyChange(name, (Date) oldValue,(Date) value);
		} else if (isString(value) || isString(oldValue)) {
			pc = new StringPropertyChange(name,(String) oldValue, (String) value);
		} else if (isBoolean(value)||isBoolean(oldValue)) {
			pc = new BooleanPropertyChange(name, (Boolean) oldValue,(Boolean) value);
		} else if (isEntity(value) || isEntity(oldValue)) {
			Class<?> cls = value==null?oldValue.getClass() : value.getClass();
			if (IntrospectionUtil.getOriginalClass(cls).isAnnotationPresent(AuditMe.class)) {
				pc = new AuditEntryPropertyChange(name, (IPersistentObject) oldValue,(IPersistentObject) value);
			} else {
				pc = new EntityPropertyChange(name, (IPersistentObject) oldValue,(IPersistentObject) value);
			}
		} else if (value instanceof AuditEntry) {
			//Many to one to ensure a snapshot is available 
			pc = new AuditEntryPropertyChange(name, (AuditEntry) value);
		} else if (value == null && oldValue==null) {
			pc = new NullPropertyChange(name);
		}
		if (pc != null) {
			pc.setAuditEntry(this);
			changes.put(name, pc);
		}
	}

	private boolean isEntity(Object value) {
		return value instanceof IPersistentObject;
	}

	private boolean isBoolean(Object value) {
		return value instanceof Boolean;
	}

	private boolean isString(Object value) {
		return value instanceof String;
	}

	private boolean isDate(Object value) {
		return value instanceof Date;
	}

	private boolean isInteger(Object value) {
		return value instanceof Integer || value instanceof Long;
	}

	private boolean isFloatingPoint(Object value) {
		return value instanceof Double || value instanceof Float;
	}

	public Map<String, PropertyChange<?>> getChanges() {
		return changes;
	}

	public void setChanges(Map<String, PropertyChange<?>> changes) {
		this.changes = changes;
	}

	public void setPreviousVersion(AuditEntry previousVersion) {
		this.previousVersion = previousVersion;
	}

	public AuditEntryId getPreviousVersionId() {
		if (previousVersionId == null) {
			if (previousVersion == null) {
				previousVersionId = id.previousVersion();
			} else {
				previousVersionId = previousVersion.getId();
			}
		}
		return previousVersionId;
	}

	public AuditEntry getPreviousVersion() {
		return previousVersion;
	}

	public Long getOriginalId() {
		return originalId;
	}

	public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}

	public String getOriginalType() {
		return originalType;
	}

	public void setOriginalType(String originalType) {
		this.originalType = originalType;
	}

	public int getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(int objectVersion) {
		this.objectVersion = objectVersion;
	}

	public Class<?> getOriginalClass() {
		if (this.originalClass == null) {
			this.originalClass = IntrospectionUtil.getClass(originalType);
		}
		return originalClass;
	}

	public AuditEntryId getId() {
		return id;
	}

	public String toString() {
		return id.toString();
	}

	public void updateVersion(int version) {
		id.updateVersion(version);
		this.objectVersion = version;
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(AuditEntry arg0) {
		int compareTo = originalType.compareTo(arg0.getOriginalType());
		if (compareTo == 0) {
			compareTo = originalId.compareTo(arg0.getOriginalId());
			if (compareTo == 0) {
				compareTo = objectVersion = arg0.getObjectVersion();
			}
		}
		return compareTo;
	}

	public Date getAuditDateTime() {
		return auditDateTime;
	}

	public void setAuditDateTime(Date auditDate) {
		this.auditDateTime = auditDate;
	}

	public void setOriginal(IPersistentObject object) {
		this.original=object;
		
	}

	public IPersistentObject getOriginal() {
		return original;
	}

}
