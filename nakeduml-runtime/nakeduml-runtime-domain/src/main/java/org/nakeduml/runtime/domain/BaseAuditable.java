package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

//TODO this class needs to be in generated source folder
@MappedSuperclass
public abstract class BaseAuditable implements Serializable{

	private static final long serialVersionUID = 3751023772087546585L;
//	@Generated(GenerationTime.INSERT)
	private Date createdOn;
//	@Generated(GenerationTime.ALWAYS)
	private Date updatedOn;
	public BaseAuditable() {
		super();
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", nullable = false)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON", nullable = false)
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@PrePersist
	public void defaultCreate() {
		setCreatedOn(new Timestamp(System.currentTimeMillis()));
		setUpdatedOn(new Timestamp(System.currentTimeMillis()));
	}

	@PreUpdate
	public void defaultUpdate() {
		setUpdatedOn(new Timestamp(System.currentTimeMillis()));
	}

}
