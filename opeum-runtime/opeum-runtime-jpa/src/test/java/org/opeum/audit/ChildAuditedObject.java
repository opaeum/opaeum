package org.opeum.audit;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.opeum.runtime.domain.IPersistentObject;

@Entity
@AuditMe
public class ChildAuditedObject implements IPersistentObject {
	@Id
	@GeneratedValue
	Long id;
	@Temporal(TemporalType.TIMESTAMP)
	Date updatedOn;
	@Version
	int objectVersion;
	@ManyToOne
	ParentAuditedObject parent;
	@ManyToOne
	UnauditedObject unauditedObject;
	@Basic
	String someProperty;
	public UnauditedObject getUnauditedObject() {
		return unauditedObject;
	}

	public void setUnauditedObject(UnauditedObject unauditedObject) {
		this.unauditedObject = unauditedObject;
	}

	public String getSomeProperty() {
		return someProperty;
	}

	public void setSomeProperty(String someProperty) {
		this.someProperty = someProperty;
	}

	public ParentAuditedObject getParent() {
		return parent;
	}

	@PreUpdate
	@PrePersist
	public void onUpdate() {
		updatedOn = new Date();
	}

	public void setParent(ParentAuditedObject parent) {
		this.parent = parent;
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

	@Override
	public String getUid(){
		// TODO Auto-generated method stub
		return null;
	}
}
