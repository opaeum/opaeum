package nakedumllibraryforbpm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import nakedumllibraryforbpm.util.Stdlib;

import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.annotation.PersistentName;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Entity(name="TaskRequest")
@AccessType("field")
@Table(name="null_task_request")
@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.task_request",nakedUmlId=4)
@DiscriminatorValue("task_request")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Filter(name="noDeletedObjects")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
public class TaskRequest extends AbstractRequest implements CompositionNode, HibernateEntity, Serializable, IPersistentObject, IProcessObject {
	static final private long serialVersionUID = 4;
	@JoinColumn(name="task_object",nullable=true)
	@Any(metaDef="TaskObject",metaColumn=@Column(name="task_object_type"))
	private TaskObject taskObject;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="parentTask",targetEntity=AbstractRequest.class)
	private Set<AbstractRequest> subRequests = new HashSet<AbstractRequest>();
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="taskRequest",targetEntity=ParticipationInTask.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<ParticipationInTask> participationsInTask = new HashSet<ParticipationInTask>();
	static private Set<TaskRequest> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Enumerated
	private TaskRequestState endNodeInTaskInstanceRegion;
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;

	/** Default constructor for 
	 */
	public TaskRequest() {
	}

	public void addAllToParticipationsInTask(Set<ParticipationInTask> participationsInTask) {
		for ( ParticipationInTask o : participationsInTask ) {
			addToParticipationsInTask(o);
		}
	}
	
	public void addAllToSubRequests(Set<AbstractRequest> subRequests) {
		for ( AbstractRequest o : subRequests ) {
			addToSubRequests(o);
		}
	}
	
	@NumlMetaInfo(persistentName="task_request.add_task_request_participant",nakedUmlId=50)
	public void addTaskRequestParticipant(Participant newParticipant, TaskParticipationKind kind) {
		ParticipationInTask participation = null;
		ParticipationInTask resultOnCreatePartipation = null;
		resultOnCreatePartipation=new ParticipationInTask();
		participation=resultOnCreatePartipation;
		TaskRequest tgtAddParticipation=this;
		tgtAddParticipation.addToParticipationsInTask(participation);
		ParticipationInTask tgtSetNewParticipant=participation;
		tgtSetNewParticipant.setParticipant(newParticipant);
		ParticipationInTask tgtSetKind=participation;
		tgtSetKind.setKind(kind);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToParticipationsInTask(ParticipationInTask participationsInTask) {
		participationsInTask.setTaskRequest(this);
	}
	
	public void addToSubRequests(AbstractRequest subRequests) {
		subRequests.setParentTask(this);
	}
	
	static public Set<? extends TaskRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from TaskRequest").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	@NumlMetaInfo(persistentName="task_request.claim",nakedUmlId=43)
	public void claim() {
	}
	
	public void clearParticipationsInTask() {
		removeAllFromParticipationsInTask(getParticipationsInTask());
	}
	
	public void clearSubRequests() {
		removeAllFromSubRequests(getSubRequests());
	}
	
	public ParticipationInTask createParticipationsInTask() {
		ParticipationInTask newInstance= new ParticipationInTask();
		newInstance.init(this);
		return newInstance;
	}
	
	@NumlMetaInfo(persistentName="task_request.delegate",nakedUmlId=48)
	public void delegate(BusinessRole delegate) {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof TaskRequest ) {
			return other==this || ((TaskRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void execute() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		WorkflowProcessInstance processInstance;
		setExecutedOn(new Date());
		params.put("processObject", this);
		processInstance = (WorkflowProcessInstance)org.nakeduml.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("naked_uml_library_for_bpm_task_request",params);
		((WorkflowProcessImpl)processInstance.getProcess()).setAutoComplete(true);
		this.processInstance=processInstance;
		this.setProcessInstanceId(processInstance.getId());
	}
	
	public NodeInstanceImpl findNodeInstanceByUniqueId(String uniqueId) {
		for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
			if ( nodeInstance.getUniqueId().equals(uniqueId) ) {
				return nodeInstance;
			}
		}
		return null;
	}
	
	public NodeInstanceImpl findWaitingNodeByNodeId(long step) {
		for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
			if ( ((NodeImpl)nodeInstance.getNode()).getId()==step ) {
				return nodeInstance;
			}
		}
		return null;
	}
	
	public void forceToStep(IProcessStep step) {
		TaskRequestState nextStep = (TaskRequestState)step;
		NodeImpl newNode = getNodeForStep(nextStep);
		for ( NodeInstanceImpl curNodeInstance : getNodeInstancesRecursively() ) {
			if ( curNodeInstance.getNode().getNodeContainer()==newNode.getNodeContainer() ) {
				transition(curNodeInstance,newNode);
				return;
			}
		}
	}
	
	@NumlMetaInfo(persistentName="task_request.forward",nakedUmlId=41)
	public void forward(BusinessRole toPerson) {
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public boolean getClosed() {
		return isStepActive(TaskRequestState.CLOSED);
	}
	
	public boolean getCompleted() {
		return isStepActive(TaskRequestState.COMPLETED);
	}
	
	public boolean getCreated() {
		return isStepActive(TaskRequestState.CREATED);
	}
	
	public Date getDeletedOn() {
		return deletedOn;
	}
	
	public Date getExecutedOn() {
		return executedOn;
	}
	
	public boolean getInProgress() {
		return isStepActive(TaskRequestState.INPROGRESS);
	}
	
	public boolean getInProgressButSuspended() {
		return isStepActive(TaskRequestState.INPROGRESSBUTSUSPENDED);
	}
	
	public IProcessStep getInnermostNonParallelStep() {
		if ( this.endNodeInTaskInstanceRegion!=null ) {
			return this.endNodeInTaskInstanceRegion;
		} else {
			NodeInstanceImpl nodeInstance = (NodeInstanceImpl)getProcessInstance().getNodeInstances().iterator().next();
			if ( getProcessInstance().getNodeInstances().size()>1 ) {
				return null;
			}
			while ( nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1 ) {
				nodeInstance=(NodeInstanceImpl)((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next();
			}
			return TaskRequestState.resolveByQualifiedName(((NodeImpl)nodeInstance.getNode()).getName());
		}
	}
	
	public String getName() {
		return "TaskRequest["+getId()+"]";
	}
	
	public Collection<NodeInstanceImpl> getNodeInstancesRecursively() {
		return (Collection<NodeInstanceImpl>)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true);
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@NumlMetaInfo(persistentName="task_request.participations_in_task_id",nakedUmlId=58)
	public Set<ParticipationInTask> getParticipationsInTask() {
		return participationsInTask;
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null || true ) {
			this.processInstance=(WorkflowProcessInstance)org.nakeduml.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getProcessInstanceId());
			if ( this.processInstance!=null ) {
				((WorkflowProcessImpl)this.processInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.processInstance;
	}
	
	public Long getProcessInstanceId() {
		return processInstanceId;
	}
	
	public boolean getReady() {
		return isStepActive(TaskRequestState.READY);
	}
	
	public boolean getReadyButSuspended() {
		return isStepActive(TaskRequestState.READYBUTSUSPENDED);
	}
	
	public boolean getReserved() {
		return isStepActive(TaskRequestState.RESERVED);
	}
	
	public boolean getReservedButSuspended() {
		return isStepActive(TaskRequestState.RESERVEDBUTSUSPENDED);
	}
	
	@NumlMetaInfo(persistentName="task_request.sub_requests_id",nakedUmlId=40)
	public Set<AbstractRequest> getSubRequests() {
		return subRequests;
	}
	
	@NumlMetaInfo(persistentName="task_request.task_object",nakedUmlId=81)
	public TaskObject getTaskObject() {
		return taskObject;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
	}
	
	public boolean isStepActive(IProcessStep step) {
		if ( step==this.endNodeInTaskInstanceRegion ) {
			return true;
		}
		if ( getProcessInstance()==null ) {
			return false;
		} else {
			for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
				if ( step.getQualifiedName().equals(nodeInstance.getNode().getName()) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParentTask()!=null ) {
			getParentTask().getSubRequests().remove((TaskRequest)this);
		}
		if ( getTaskObject()!=null ) {
			getTaskObject().setTaskRequest(null);
		}
		for ( ParticipationInTask child : new ArrayList<ParticipationInTask>(getParticipationsInTask()) ) {
			child.markDeleted();
		}
	}
	
	static public void mockAllInstances(Set<TaskRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	@PersistentName("completed_occurred")
	public boolean onCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.COMPLETED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("closed", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("created_occurred")
	public boolean onCreated() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.CREATED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_ready", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("in_progress_occurred")
	public boolean onInProgress() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_reserved", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("in_progress_but_suspended_occurred")
	public boolean onInProgressButSuspended() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.INPROGRESSBUTSUSPENDED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_in_progress", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("ready_occurred")
	public boolean onReady() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.READY.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_ready", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("ready_but_suspended_occurred")
	public boolean onReadyButSuspended() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.READYBUTSUSPENDED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_ready", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("reserved_occurred")
	public boolean onReserved() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_ready", listener);
			}
		}
		return consumed;
	}
	
	@PersistentName("reserved_but_suspended_occurred")
	public boolean onReservedButSuspended() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.RESERVEDBUTSUSPENDED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.takeTransition("join_for_reserved", listener);
			}
		}
		return consumed;
	}
	
	public boolean processSignal(AbstractSignal signal) {
		return false;
	}
	
	public NodeImpl recursivelyFindNode(TaskRequestState step, Collection<NodeImpl> collection) {
		for ( NodeImpl curNode : collection ) {
			if ( curNode.getId()==step.getId() ) {
				return curNode;
			} else {
				if ( curNode instanceof NodeContainer ) {
					NodeImpl childNode = recursivelyFindNode(step, (Collection)Arrays.asList(((NodeContainer)curNode).getNodes()));
					if ( childNode!=null ) {
						return childNode;
					}
				}
			}
		}
		return null;
	}
	
	public void removeAllFromParticipationsInTask(Set<ParticipationInTask> participationsInTask) {
		Set<ParticipationInTask> tmp = new HashSet<ParticipationInTask>(participationsInTask);
		for ( ParticipationInTask o : tmp ) {
			removeFromParticipationsInTask(o);
		}
	}
	
	public void removeAllFromSubRequests(Set<AbstractRequest> subRequests) {
		Set<AbstractRequest> tmp = new HashSet<AbstractRequest>(subRequests);
		for ( AbstractRequest o : tmp ) {
			removeFromSubRequests(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipationsInTask(ParticipationInTask participationsInTask) {
		participationsInTask.setTaskRequest(null);
	}
	
	public void removeFromSubRequests(AbstractRequest subRequests) {
		subRequests.setParentTask(null);
	}
	
	@NumlMetaInfo(persistentName="task_request.remove_task_request_participant",nakedUmlId=44)
	public void removeTaskRequestParticipant(Participant participant, RequestParticipationKind kind) {
		TaskRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInRequest((Set)(select1(participant, kind)));
	}
	
	@NumlMetaInfo(persistentName="task_request.revoke",nakedUmlId=57)
	public void revoke() {
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setExecutedOn(Date executedOn) {
		this.executedOn=executedOn;
	}
	
	public void setParticipationsInTask(Set<ParticipationInTask> participationsInTask) {
		for ( ParticipationInTask o : new HashSet<ParticipationInTask>(this.participationsInTask) ) {
			o.setTaskRequest(null);
		}
		for ( ParticipationInTask o : participationsInTask ) {
			o.setTaskRequest((TaskRequest)this);
		}
	}
	
	public void setProcessInstance(WorkflowProcessInstance processInstance) {
		this.processInstance=processInstance;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId=processInstanceId;
	}
	
	public void setSubRequests(Set<AbstractRequest> subRequests) {
		for ( AbstractRequest o : new HashSet<AbstractRequest>(this.subRequests) ) {
			o.setParentTask(null);
		}
		for ( AbstractRequest o : subRequests ) {
			o.setParentTask((TaskRequest)this);
		}
	}
	
	public void setTaskObject(TaskObject taskObject) {
		TaskObject oldValue = this.taskObject;
		if ( oldValue==null ) {
			this.taskObject=taskObject;
			if ( taskObject!=null ) {
				taskObject.z_internalAddToTaskRequest((TaskRequest)this);
			}
		} else {
			if ( !oldValue.equals(taskObject) ) {
				this.taskObject=taskObject;
				oldValue.z_internalRemoveFromTaskRequest(this);
				if ( taskObject!=null ) {
					taskObject.z_internalAddToTaskRequest((TaskRequest)this);
				}
			}
		}
	}
	
	@NumlMetaInfo(persistentName="task_request.stop",nakedUmlId=47)
	public void stop() {
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if ( getParentTask()==null ) {
			sb.append("parentTask=null");
		} else {
			sb.append("parentTask.id=");
			sb.append(getParentTask().getId());
			sb.append(";");
		}
		sb.append("taskObject=");
		sb.append(getTaskObject());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getParentTask()==null ) {
			sb.append("<parentTask/>");
		} else {
			sb.append("<parentTask>");
			sb.append(getParentTask());
			sb.append("</parentTask>");
			sb.append("\n");
		}
		for ( ParticipationInRequest participationsInRequest : getParticipationsInRequest() ) {
			sb.append("<participationsInRequest>");
			sb.append(participationsInRequest.toXmlString());
			sb.append("</participationsInRequest>");
			sb.append("\n");
		}
		if ( getTaskObject()==null ) {
			sb.append("<taskObject/>");
		} else {
			sb.append("<taskObject>");
			sb.append(getTaskObject().getClass().getSimpleName());
			sb.append("[");
			sb.append(getTaskObject().hashCode());
			sb.append("]");
			sb.append("</taskObject>");
			sb.append("\n");
		}
		for ( AbstractRequest subRequests : getSubRequests() ) {
			sb.append("<subRequests>");
			sb.append(subRequests.toXmlString());
			sb.append("</subRequests>");
			sb.append("\n");
		}
		for ( ParticipationInTask participationsInTask : getParticipationsInTask() ) {
			sb.append("<participationsInTask>");
			sb.append(participationsInTask.toXmlString());
			sb.append("</participationsInTask>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void transition(NodeInstanceImpl curNodeInstance, NodeImpl newNode) {
		NodeInstanceContainer container = (NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer();
		NodeInstanceImpl newNodeInstance = (NodeInstanceImpl)container.getNodeInstance(newNode);
		container.removeNodeInstance(curNodeInstance);
		curNodeInstance.trigger(newNodeInstance, "asdf");
	}
	
	public void z_internalAddToTaskObject(TaskObject taskObject) {
		this.taskObject=taskObject;
	}
	
	public void z_internalRemoveFromTaskObject(TaskObject taskObject) {
		if ( getTaskObject()!=null && getTaskObject().equals(taskObject) ) {
			this.taskObject=null;
		}
	}
	
	private NodeImpl getNodeForStep(TaskRequestState step) {
		return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()));
	}
	
	/** Implements ->select( p : ParticipationInRequest | (p.participant = participant) and p.kind = kind )
	 * 
	 * @param participant 
	 * @param kind 
	 */
	private Set<ParticipationInRequest> select1(Participant participant, RequestParticipationKind kind) {
		Set<ParticipationInRequest> result = new HashSet<ParticipationInRequest>();
		for ( ParticipationInRequest p : this.getParticipationsInRequest() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}

}