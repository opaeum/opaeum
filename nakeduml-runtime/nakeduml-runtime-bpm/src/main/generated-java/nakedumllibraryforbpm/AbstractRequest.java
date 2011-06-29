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
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import nakedumllibraryforbpm.util.Stdlib;

import org.drools.runtime.StatefulKnowledgeSession;
import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Proxy;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;

@Entity(name="AbstractRequest")
@Table(name="null_abstract_request")
@AccessType("field")
@Filter(name="noDeletedObjects")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.abstract_request",nakedUmlId=27)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Proxy(lazy=false)
abstract public class AbstractRequest implements CompositionNode, HibernateEntity, Serializable, IPersistentObject, IProcessObject {
	static final private long serialVersionUID = 27;
	@Index(name="idx_abstract_request_parent_task_id",columnNames="parent_task_id")
	@JoinColumn(name="parent_task_id",nullable=true)
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	private TaskRequest parentTask;
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="request",targetEntity=ParticipationInRequest.class)
	private Set<ParticipationInRequest> participationsInRequest = new HashSet<ParticipationInRequest>();
	private String uid;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	static private Set<AbstractRequest> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Enumerated
	private AbstractRequestState endNodeInRegion1;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="executed_on")
	private Date executedOn;

	/** Default constructor for 
	 */
	public AbstractRequest() {
	}

	public void addAllToParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		for ( ParticipationInRequest o : participationsInRequest ) {
			addToParticipationsInRequest(o);
		}
	}
	
	@NumlMetaInfo(persistentName="abstract_request.add_request_participant",nakedUmlId=91)
	public void addRequestParticipant(Participant newParticipant, RequestParticipationKind kind) {
		ParticipationInRequest participation = null;
		ParticipationInRequest resultOnCreatePartipation = null;
		resultOnCreatePartipation=new ParticipationInRequest();
		participation=resultOnCreatePartipation;
		AbstractRequest tgtAddParticipation=this;
		tgtAddParticipation.addToParticipationsInRequest(participation);
		ParticipationInRequest tgtSetNewParticipant=participation;
		tgtSetNewParticipant.setParticipant(newParticipant);
		ParticipationInRequest tgtSetKind=participation;
		tgtSetKind.setKind(kind);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToParticipationsInRequest(ParticipationInRequest participationsInRequest) {
		participationsInRequest.setRequest(this);
	}
	
	static public Set<? extends AbstractRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from AbstractRequest").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void clearParticipationsInRequest() {
		removeAllFromParticipationsInRequest(getParticipationsInRequest());
	}
	
	@NumlMetaInfo(persistentName="abstract_request.complete",nakedUmlId=84)
	public void complete() {
	}
	
	public ParticipationInRequest createParticipationsInRequest() {
		ParticipationInRequest newInstance= new ParticipationInRequest();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof AbstractRequest ) {
			return other==this || ((AbstractRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void execute() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		WorkflowProcessInstance processInstance;
		setExecutedOn(new Date());
		params.put("processObject", this);
		processInstance = (WorkflowProcessInstance)org.nakeduml.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("naked_uml_library_for_bpm_abstract_request",params);
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
		AbstractRequestState nextStep = (AbstractRequestState)step;
		NodeImpl newNode = getNodeForStep(nextStep);
		for ( NodeInstanceImpl curNodeInstance : getNodeInstancesRecursively() ) {
			if ( curNodeInstance.getNode().getNodeContainer()==newNode.getNodeContainer() ) {
				transition(curNodeInstance,newNode);
				return;
			}
		}
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public Date getDeletedOn() {
		return deletedOn;
	}
	
	public Date getExecutedOn() {
		return executedOn;
	}
	
	public Long getId() {
		return id;
	}
	
	@NumlMetaInfo(persistentName="abstract_request.get_initiator",nakedUmlId=96)
	public Participant getInitiator() {
		Participant result = null;
		result= any2().getParticipant();
		return result;
	}
	
	public IProcessStep getInnermostNonParallelStep() {
		if ( this.endNodeInRegion1!=null ) {
			return this.endNodeInRegion1;
		} else {
			NodeInstanceImpl nodeInstance = (NodeInstanceImpl)getProcessInstance().getNodeInstances().iterator().next();
			if ( getProcessInstance().getNodeInstances().size()>1 ) {
				return null;
			}
			while ( nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1 ) {
				nodeInstance=(NodeInstanceImpl)((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next();
			}
			return AbstractRequestState.resolveByQualifiedName(((NodeImpl)nodeInstance.getNode()).getName());
		}
	}
	
	public String getName() {
		return "AbstractRequest["+getId()+"]";
	}
	
	public Collection<NodeInstanceImpl> getNodeInstancesRecursively() {
		return (Collection<NodeInstanceImpl>)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true);
	}
	
	public int getObjectVersion() {
		return objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@NumlMetaInfo(persistentName="abstract_request.parent_task_id",nakedUmlId=77)
	public TaskRequest getParentTask() {
		return parentTask;
	}
	
	@NumlMetaInfo(persistentName="abstract_request.participations_in_request_id",nakedUmlId=101)
	public Set<ParticipationInRequest> getParticipationsInRequest() {
		return participationsInRequest;
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
	
	@NumlMetaInfo(persistentName="abstract_request.responsibility_object",nakedUmlId=76)
	public RequestObject getResponsibilityObject() {
		return null;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
	}
	
	public boolean isStepActive(IProcessStep step) {
		if ( step==this.endNodeInRegion1 ) {
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getParentTask()!=null ) {
			getParentTask().getSubRequests().remove((AbstractRequest)this);
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationsInRequest()) ) {
			child.markDeleted();
		}
	}
	
	static public void mockAllInstances(Set<AbstractRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public boolean processSignal(AbstractSignal signal) {
		return false;
	}
	
	public NodeImpl recursivelyFindNode(AbstractRequestState step, Collection<NodeImpl> collection) {
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
	
	public void removeAllFromParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		Set<ParticipationInRequest> tmp = new HashSet<ParticipationInRequest>(participationsInRequest);
		for ( ParticipationInRequest o : tmp ) {
			removeFromParticipationsInRequest(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipationsInRequest(ParticipationInRequest participationsInRequest) {
		participationsInRequest.setRequest(null);
	}
	
	@NumlMetaInfo(persistentName="abstract_request.remove_request_participant",nakedUmlId=86)
	public void removeRequestParticipant(Participant participant, RequestParticipationKind kind) {
		AbstractRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInRequest((Set)(select1(participant, kind)));
	}
	
	@NumlMetaInfo(persistentName="abstract_request.resume",nakedUmlId=102)
	public void resume() {
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setExecutedOn(Date executedOn) {
		this.executedOn=executedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setParentTask(TaskRequest parentTask) {
		if ( this.parentTask!=null ) {
			this.parentTask.getSubRequests().remove((AbstractRequest)this);
		}
		if ( parentTask!=null ) {
			parentTask.getSubRequests().add((AbstractRequest)this);
			this.parentTask=parentTask;
			setDeletedOn(Stdlib.FUTURE);
		} else {
			setDeletedOn(new Date());
		}
	}
	
	public void setParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		for ( ParticipationInRequest o : new HashSet<ParticipationInRequest>(this.participationsInRequest) ) {
			o.setRequest(null);
		}
		for ( ParticipationInRequest o : participationsInRequest ) {
			o.setRequest((AbstractRequest)this);
		}
	}
	
	public void setProcessInstance(WorkflowProcessInstance processInstance) {
		this.processInstance=processInstance;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId=processInstanceId;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	@NumlMetaInfo(persistentName="abstract_request.start",nakedUmlId=100)
	public void start() {
	}
	
	@NumlMetaInfo(persistentName="abstract_request.suspend",nakedUmlId=85)
	public void suspend() {
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
		return sb.toString();
	}
	
	public void transition(NodeInstanceImpl curNodeInstance, NodeImpl newNode) {
		NodeInstanceContainer container = (NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer();
		NodeInstanceImpl newNodeInstance = (NodeInstanceImpl)container.getNodeInstance(newNode);
		container.removeNodeInstance(curNodeInstance);
		curNodeInstance.trigger(newNodeInstance, "asdf");
	}
	
	public void z_internalAddToParentTask(TaskRequest parentTask) {
		this.parentTask=parentTask;
	}
	
	public void z_internalRemoveFromParentTask(TaskRequest parentTask) {
		if ( getParentTask()!=null && getParentTask().equals(parentTask) ) {
			this.parentTask=null;
		}
	}
	
	/** Implements ->any( p : ParticipationInRequest | p.kind = RequestParticipationKind::initiator )
	 */
	private ParticipationInRequest any2() {
		ParticipationInRequest result = null;
		for ( ParticipationInRequest p : this.getParticipationsInRequest() ) {
			if ( (p.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				return p;
			}
		}
		return result;
	}
	
	private NodeImpl getNodeForStep(AbstractRequestState step) {
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