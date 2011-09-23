package org.nakeduml.audit;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.nakeduml.runtime.domain.IPersistentObject;

@Entity
@AuditMe(factory=CustomAuditEntryFactory.class)
@Table(name="parent_auditable_object")
public class ParentAuditedObject implements IPersistentObject {
	@Id
	@GeneratedValue
	Long id;
	@Temporal(TemporalType.TIMESTAMP)
	Date updatedOn;
	@Version
	int objectVersion;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	Set<ChildAuditedObject> children = new HashSet<ChildAuditedObject>();
	@Basic
	String stringProperty;
	@Basic
	Double doubleProperty;

	@Basic
	Long integerProperty;
	@Temporal(TemporalType.TIMESTAMP)
	Date dateTimeProperty;
	@Basic
	Boolean booleanProperty;

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		updatedOn = new Date();
	}

	public double getDoubleProperty() {
		return doubleProperty;
	}

	public void setDoubleProperty(double doubleProperty) {
		this.doubleProperty = doubleProperty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public int getObjectVersion() {
		return objectVersion;
	}

	public void setObjectVersion(int objectVersion) {
		this.objectVersion = objectVersion;
	}

	@Override
	public String getName() {
		return "Ja";
	}

	public Set<ChildAuditedObject> getChildren() {
		return children;
	}

	public void setChildren(Set<ChildAuditedObject> children) {
		this.children = children;
	}

	public String getStringProperty() {
		return stringProperty;
	}

	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}

	public long getIntegerProperty() {
		return integerProperty;
	}

	public void setIntegerProperty(long integerProperty) {
		this.integerProperty = integerProperty;
	}

	public Date getDateTimeProperty() {
		return dateTimeProperty;
	}

	public void setDateTimeProperty(Date dateTimeProperty) {
		this.dateTimeProperty = dateTimeProperty;
	}

	public boolean isBooleanProperty() {
		return booleanProperty;
	}

	public void setBooleanProperty(boolean booleanProperty) {
		this.booleanProperty = booleanProperty;
	}

	@Override
	public String getUid(){
		// TODO Auto-generated method stub
		return null;
	}

}
