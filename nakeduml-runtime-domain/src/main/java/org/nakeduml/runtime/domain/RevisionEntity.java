package org.nakeduml.runtime.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "RevisionEntity")
@Table(name = "revision_entity")
public class RevisionEntity implements Serializable {

	private static final long serialVersionUID = -7784910360128343664L;
	@Id
	@GeneratedValue
	private Long id;
	private Date createdOn;

	public RevisionEntity() {
		super();
		setCreatedOn(new Timestamp(System.currentTimeMillis()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", nullable = false)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
