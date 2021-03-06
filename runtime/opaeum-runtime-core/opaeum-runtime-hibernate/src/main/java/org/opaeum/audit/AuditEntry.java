package org.opaeum.audit;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.event.EventSource;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "AuditEntry")
@javax.persistence.Table(name = "audit_entry")
@org.hibernate.annotations.Table(appliesTo = "audit_entry",indexes = {@Index(name = "audit_entry_idx",columnNames = {"original_type",
		"original_id","object_version"})})
public class AuditEntry implements Serializable,Comparable<AuditEntry>{
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
	@OneToMany(mappedBy = "auditEntry",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@MapKey(name = "propertyName")
	Map<String,PropertyChange<?>> changes = new HashMap<String,PropertyChange<?>>();
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "audit_date_time")
	private Date auditDateTime = new Date(System.currentTimeMillis());
	@Transient
	private Map<String,IPersistentObject> manyToOnes = new HashMap<String,IPersistentObject>();
	@Embedded()
	@AttributeOverrides(@AttributeOverride(name = "id",column = @Column(name = "previous_version_id")))
	private AuditEntryId previousVersionId;
	@Transient
	private Class<? extends IPersistentObject> originalClass;
	@Transient
	private IPersistentObject original;
	@Transient
	private boolean originalSearched;
	@Enumerated(EnumType.STRING)
	private AuditedAction action;
	@Transient
	protected EventSource session;
	public AuditEntry(){
		super();
	}
	private static Set<String> IGNORED_FIELDS = new java.util.HashSet<String>();
	static{
		IGNORED_FIELDS.add("createdOn");
		IGNORED_FIELDS.add("deletedOn");
		IGNORED_FIELDS.add("updatedOn");
		IGNORED_FIELDS.add("objectVersion");
		IGNORED_FIELDS.add("id");
	}
	public AuditEntry(IPersistentObject entity,int version){
		this.originalClass = (Class<? extends IPersistentObject>) IntrospectionUtil.getOriginalClass(entity);
		this.originalType = originalClass.getName();
		this.originalId = entity.getId();
		this.objectVersion = version;
		this.id = new AuditEntryId(entity, version);
		this.previousVersionId = id.previousVersion();
	}
	public final void setSession(EventSource session){
		this.session = session;
	}
	public void addManyToOne(String name,IPersistentObject value){
		manyToOnes.put(name, value);
	}
	public Set<Entry<String,IPersistentObject>> getManyToOnes(){
		return manyToOnes.entrySet();
	}
	public void putPropertyChange(String name,Object oldValue,Object value){
		if(!IGNORED_FIELDS.contains(name)){
			PropertyChange<?> pc = null;
			if(isFloatingPoint(value) || isFloatingPoint(oldValue)){
				pc = new FloatingPointPropertyChange(name, (Number) oldValue, (Number) value);
			}else if(isInteger(value) || isInteger(oldValue)){
				pc = new IntegerPropertyChange(name, (Number) oldValue, (Number) value);
			}else if(isDate(value) || isDate(oldValue)){
				pc = new DateTimePropertyChange(name, (Date) oldValue, (Date) value);
			}else if(isString(value) || isString(oldValue)){
				pc = new StringPropertyChange(name, (String) oldValue, (String) value);
			}else if(isBoolean(value) || isBoolean(oldValue)){
				pc = new BooleanPropertyChange(name, (Boolean) oldValue, (Boolean) value);
			}else if(isEntity(value) || isEntity(oldValue)){
				Class<?> cls = value == null ? oldValue.getClass() : value.getClass();
				IPersistentObject newEntityValue = (IPersistentObject) value;
				if(newEntityValue != null && newEntityValue.getId() == null){
					// error condition - hibernate will likely fail - how was
					// the
					// foreign key inserted. monitor this
				}else if(IntrospectionUtil.getOriginalClass(cls).isAnnotationPresent(AuditMe.class)){
					pc = new AuditEntryPropertyChange(name, (IPersistentObject) oldValue, newEntityValue);
				}else{
					pc = new EntityPropertyChange(name, (IPersistentObject) oldValue, newEntityValue);
				}
			}else if(value instanceof AuditEntry){
				// Many to one to ensure a snapshot is available
				pc = new AuditEntryPropertyChange(name, (AuditEntry) value);
			}else if(value == null && oldValue == null){
				pc = new NullPropertyChange(name);
			}
			if(pc != null){
				pc.setAuditEntry(this);
				changes.put(name, pc);
			}
		}
	}
	private boolean isEntity(Object value){
		return value instanceof IPersistentObject;
	}
	private boolean isBoolean(Object value){
		return value instanceof Boolean;
	}
	private boolean isString(Object value){
		return value instanceof String;
	}
	private boolean isDate(Object value){
		return value instanceof Date;
	}
	private boolean isInteger(Object value){
		return value instanceof Integer || value instanceof Long;
	}
	private boolean isFloatingPoint(Object value){
		return value instanceof Double || value instanceof Float;
	}
	public Map<String,PropertyChange<?>> getChanges(){
		return changes;
	}
	public void setChanges(Map<String,PropertyChange<?>> changes){
		this.changes = changes;
	}
	public AuditEntryId getPreviousVersionId(){
		return previousVersionId;
	}
	public Long getOriginalId(){
		return originalId;
	}
	public void setOriginalId(Long originalId){
		this.originalId = originalId;
	}
	public String getOriginalType(){
		return originalType;
	}
	public void setOriginalType(String originalType){
		this.originalType = originalType;
	}
	public int getObjectVersion(){
		return objectVersion;
	}
	public void setObjectVersion(int objectVersion){
		this.objectVersion = objectVersion;
	}
	public Class<?> getOriginalClass(){
		if(this.originalClass == null){
			this.originalClass = IntrospectionUtil.getClass(originalType);
		}
		return originalClass;
	}
	public AuditEntryId getId(){
		return id;
	}
	public String toString(){
		return id.toString();
	}
	public void updateVersion(int version){
		id.updateVersion(version);
		this.objectVersion = version;
	}
	@Override
	public int compareTo(AuditEntry arg0){
		int compareTo = originalType.compareTo(arg0.getOriginalType());
		if(compareTo == 0){
			compareTo = originalId.compareTo(arg0.getOriginalId());
			if(compareTo == 0){
				compareTo = objectVersion = arg0.getObjectVersion();
			}
		}
		return compareTo;
	}
	public Date getAuditDateTime(){
		return auditDateTime;
	}
	public void setAuditDateTime(Date auditDate){
		this.auditDateTime = auditDate;
	}
	public void setOriginal(IPersistentObject object){
		this.original = object;
	}
	public IPersistentObject getOriginal(){
		if(original == null && !originalSearched){
			original = (IPersistentObject) session.load(getOriginalClass(), originalId);
			originalSearched = true;
		}
		return original;
	}
	public AuditedAction getAction(){
		return action;
	}
	public void setAction(AuditedAction action){
		this.action = action;
	}
}
