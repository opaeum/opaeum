package org.nakeduml.runtime.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Any;

public class TaskRequest extends AbstractRequest{
	@Any(metaDef="UserRoleMetaDef",metaColumn=@Column(name="fulfiller_type"))
	private AbstractUserRole fulfiller;
	@Any(metaDef="OrganizationUnitMetaDef",metaColumn=@Column(name="organization_unit_type"))
	private OrganizationUnit organizationUnit;
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptedOn;
	@Any(metaDef="TaskMetaDef",metaColumn=@Column(name="task_type"))
	AbstractTask task;
	@OneToMany(mappedBy="parentTask")
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
