package org.nakeduml.runtime.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Any;
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="numl_abstract_request")
public abstract class AbstractRequest{
	public static final String USER_ROLE_META_DEF="UserRoleMetaDef";
	@Any(metaDef = USER_ROLE_META_DEF,metaColumn = @Column(name = "user_role_type"))
	private AbstractUserRole requestor;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date completedOn;
	@ManyToOne()
	@JoinColumn(name="parent_task_request")
	private TaskRequest parentTaskRequest;
	public AbstractRequest(){
		super();
	}
	public AbstractUserRole getRequestor(){
		return requestor;
	}
	public void setRequestor(AbstractUserRole requestor){
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