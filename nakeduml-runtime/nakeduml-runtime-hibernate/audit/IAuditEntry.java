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
public interface IAuditEntry extends Serializable, Comparable<IAuditEntry> {

	public void addManyToOne(String name, IPersistentObject value);

	public Set<Entry<String, IPersistentObject>> getManyToOnes();

	public void putPropertyChange(String name, Object oldValue, Object value);
	public Map<String, PropertyChange<?>> getChanges();

	public void setChanges(Map<String, PropertyChange<?>> changes);
	public void setPreviousVersion(IAuditEntry previousVersion);
	public AuditEntryId getPreviousVersionId();
	public IAuditEntry getPreviousVersion() {
	}

	public Long getOriginalId() {

	public void setOriginalId(Long originalId) {

	public String getOriginalType() {

	public void setOriginalType(String originalType) {

	public int getObjectVersion() {

	public void setObjectVersion(int objectVersion) {

	public Class<?> getOriginalClass() {

	public AuditEntryId getId() {

	public String toString() {

	public void updateVersion(int version) {
	public int compareTo(IAuditEntry arg0) {
	public Date getAuditDateTime() {

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
