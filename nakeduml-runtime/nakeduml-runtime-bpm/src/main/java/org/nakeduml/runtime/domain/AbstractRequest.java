package org.nakeduml.runtime.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Any;
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="numl_abstract_request")
@Entity
public abstract class AbstractRequest{
	public static final String USER_ROLE_META_DEF="UserRoleMetaDef";
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	Long id;
	@Any(metaDef = USER_ROLE_META_DEF,metaColumn = @Column(name = "user_role_type"))
	@JoinColumn(name="requestor_id")
	private IUserInRole requestor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date completedOn;
	@ManyToOne()
	@JoinColumn(name="parent_task_instance")
	private TaskRequest parentTaskInstance;
	public AbstractRequest(){
		super();
	}
	public IUserInRole getRequestor(){
		return requestor;
	}
	public void setRequestor(IUserInRole requestor){
		this.requestor = requestor;
	}
	public Date getCreatedOn(){
		return createdOn;
	}
	public void setCreatedOn(Date createdOn){
		this.createdOn = createdOn;
	}
	public Date getCompletedOn(){
		return completedOn;
	}
	public void setCompletedOn(Date completedOn){
		this.completedOn = completedOn;
	}
}