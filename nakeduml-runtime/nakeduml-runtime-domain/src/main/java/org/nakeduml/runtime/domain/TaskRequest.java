package org.nakeduml.runtime.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Any;
@Entity
@Table(name="numl_task_request")
public class TaskRequest extends AbstractRequest{
	public static final String ORGANIZATION_UNIT_META_DEF = "OrganizationUnitMetaDef";
	public static final String TASK_META_DEF = "TaskMetaDef";
	@Any(metaDef=USER_ROLE_META_DEF,metaColumn=@Column(name="fulfiller_type"))
	@JoinColumn(name="fulfiller_id")
	private AbstractUserRole fulfiller;
	@Any(metaDef=ORGANIZATION_UNIT_META_DEF,metaColumn=@Column(name="organization_unit_type"))
	@JoinColumn(name="organization_unit_id")
	private OrganizationUnit organizationUnit;
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptedOn;
	@Any(metaDef=TASK_META_DEF,metaColumn=@Column(name="task_type"))
	@JoinColumn(name="task_id")
	AbstractTask task;
	@OneToMany(mappedBy="parentTaskRequest",cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<AbstractRequest> childRequests = new HashSet<AbstractRequest>();
	
	public AbstractUserRole getFulfiller(){
		return fulfiller;
	}
	public void setFulfiller(AbstractUserRole fulfiller){
		this.fulfiller = fulfiller;
	}
	public OrganizationUnit getOrganizationUnit(){
		return organizationUnit;
	}
	public void setOrganizationUnit(OrganizationUnit organizationUnit){
		this.organizationUnit = organizationUnit;
	}
	public Date getAcceptedOn(){
		return acceptedOn;
	}
	public void setAcceptedOn(Date acceptedOn){
		this.acceptedOn = acceptedOn;
	}
	public AbstractTask getTask(){
		return task;
	}
	public void setTask(AbstractTask task){
		this.task = task;
	}
	public Set<AbstractRequest> getChildRequests(){
		return childRequests;
	}
	public void setChildRequests(Set<AbstractRequest> childRequests){
		this.childRequests = childRequests;
	}
}
