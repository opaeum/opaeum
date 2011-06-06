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
@Table(name = "numl_task_request")
public class TaskRequest extends AbstractRequest{
	public static final String ORGANIZATION_UNIT_META_DEF = "OrganizationUnitMetaDef";
	public static final String TASK_META_DEF = "TaskMetaDef";
	@Any(metaDef = USER_ROLE_META_DEF,metaColumn = @Column(name = "fulfiller_type"))
	@JoinColumn(name = "fulfiller_id")
	private IUserInRole fulfiller;
	@Any(metaDef = ORGANIZATION_UNIT_META_DEF,metaColumn = @Column(name = "organization_unit_type"))
	@JoinColumn(name = "organization_unit_id")
	private IBusinessComponent organizationUnit;
	@Temporal(TemporalType.TIMESTAMP)
	private Date acceptedOn;
	@Any(metaDef = TASK_META_DEF,metaColumn = @Column(name = "task_type"))
	@JoinColumn(name = "task_id")
	ITaskInvocation taskInvocation;
	@OneToMany(mappedBy = "parentTaskInstance",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<AbstractRequest> childRequests = new HashSet<AbstractRequest>();
	public IUserInRole getFulfiller(){
		return fulfiller;
	}
	public void setFulfiller(IUserInRole fulfiller){
		this.fulfiller = fulfiller;
	}
	public IBusinessComponent getOrganizationUnit(){
		return organizationUnit;
	}
	public void setOrganizationUnit(IBusinessComponent organizationUnit){
		this.organizationUnit = organizationUnit;
	}
	public Date getAcceptedOn(){
		return acceptedOn;
	}
	public void setAcceptedOn(Date acceptedOn){
		this.acceptedOn = acceptedOn;
	}
	public Set<AbstractRequest> getChildRequests(){
		return childRequests;
	}
	public void setChildRequests(Set<AbstractRequest> childRequests){
		this.childRequests = childRequests;
	}
}
