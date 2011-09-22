package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessContext;
import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Type;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.abstractrequest.ActivateHandler922;
import org.nakeduml.runtime.bpm.abstractrequest.CompleteHandler926;
import org.nakeduml.runtime.bpm.abstractrequest.ResumeHandler923;
import org.nakeduml.runtime.bpm.abstractrequest.StartHandler919;
import org.nakeduml.runtime.bpm.abstractrequest.SuspendHandler933;
import org.nakeduml.runtime.bpm.taskrequest.ClaimHandler999;
import org.nakeduml.runtime.bpm.taskrequest.DelegateHandler1001;
import org.nakeduml.runtime.bpm.taskrequest.ForwardHandler985;
import org.nakeduml.runtime.bpm.taskrequest.RevokeHandler984;
import org.nakeduml.runtime.bpm.taskrequest.SkipHandler998;
import org.nakeduml.runtime.bpm.taskrequest.StopHandler994;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CancelledEvent;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.domain.OutgoingEvent;
import org.nakeduml.runtime.domain.TaskDelegation;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;
import org.nakeduml.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="TaskRequest")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="task_request")
@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.task_request",uuid="252060@_zFmsEIoVEeCLqpffVZYAlw")
@AccessType("field")
@DiscriminatorValue("task_request")
public class TaskRequest extends AbstractRequest implements IEventGenerator, HibernateEntity, CompositionNode, Serializable, IPersistentObject, IProcessObject {
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Type(type="TaskRequestStateResolver")
	private TaskRequestState endNodeInTaskInstanceRegion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Index(name="idx_task_request_task_object",columnNames="task_object")
	@Any(metaDef="TaskObject",metaColumn=@Column(name="task_object_type"))
	@JoinColumn(name="task_object",nullable=true)
	private TaskObject taskObject;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="taskRequest",targetEntity=ParticipationInTask.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<ParticipationInTask> participationsInTask = new HashSet<ParticipationInTask>();
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="delegation",nullable=true)
	private TaskDelegation delegation;
	@Transient
	private boolean processDirty;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Type(type="TaskRequestStateResolver")
	private TaskRequestState History;
	static final private long serialVersionUID = 878;
	static private Set<TaskRequest> mockedAllInstances;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="parentTask",targetEntity=AbstractRequest.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<AbstractRequest> subRequests = new HashSet<AbstractRequest>();

	/** Default constructor for TaskRequest
	 */
	public TaskRequest() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public TaskRequest(TaskObject owningObject) {
		init(owningObject);
		addToOwningObject();
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.add_task_request_participant",uuid="252060@_v52VoI6SEeCrtavWRHwoHg")
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
		getTaskObject().z_internalAddToTaskRequest((TaskRequest)this);
	}
	
	public void addToParticipationsInTask(ParticipationInTask participationsInTask) {
		if ( participationsInTask!=null ) {
			participationsInTask.z_internalRemoveFromTaskRequest(participationsInTask.getTaskRequest());
			participationsInTask.z_internalAddToTaskRequest(this);
			z_internalAddToParticipationsInTask(participationsInTask);
		}
	}
	
	public void addToSubRequests(AbstractRequest subRequests) {
		if ( subRequests!=null ) {
			subRequests.z_internalRemoveFromParentTask(subRequests.getParentTask());
			subRequests.z_internalAddToParentTask(this);
			z_internalAddToSubRequests(subRequests);
		}
	}
	
	static public Set<? extends TaskRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from TaskRequest").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("delegation").length()>0 ) {
			setDelegation(TaskDelegation.valueOf(xml.getAttribute("delegation")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("914")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationInRequest curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToParticipationsInRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("993")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationInTask curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToParticipationsInTask(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.claim",uuid="252060@_Nk_isIobEeCPduia_-NbFw")
	public void claim() {
		generateClaimEvent();
	}
	
	public void clearParticipationsInTask() {
		removeAllFromParticipationsInTask(getParticipationsInTask());
	}
	
	public void clearSubRequests() {
		removeAllFromSubRequests(getSubRequests());
	}
	
	public boolean consumeActivateOccurrence() {
		boolean consumed = false;
		consumed=super.consumeActivateOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_CREATED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeAddTaskRequestParticipantOccurrence(Participant newParticipant, TaskParticipationKind kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeClaimOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeCompleteOccurrence() {
		boolean consumed = false;
		consumed=super.consumeCompleteOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeDelegateOccurrence(BusinessRole delegate) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeForwardOccurrence(BusinessRole toPerson) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeRemoveTaskRequestParticipantOccurrence(Participant participant, TaskParticipationKind kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeResumeOccurrence() {
		boolean consumed = false;
		consumed=super.consumeResumeOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_SUSPENDED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeRevokeOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeSkipOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_OBSOLETE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeStartOccurrence() {
		boolean consumed = false;
		consumed=super.consumeStartOccurrence();
		return consumed;
	}
	
	public boolean consumeStopOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeSuspendOccurrence() {
		boolean consumed = false;
		consumed=super.consumeSuspendOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED.getId(), listener);
			}
		}
		return consumed;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.delegate",uuid="252060@_0lAQAIoaEeCPduia_-NbFw")
	public void delegate(BusinessRole delegate) {
		BusinessRole currentUser = null;
		if ( (!(this.getOwner() == null)) ) {
			this.removeTaskRequestParticipant(delegate,TaskParticipationKind.OWNER);
			this.addTaskRequestParticipant(delegate,TaskParticipationKind.OWNER);
			TaskObject tgtOnDelegated=this.getTaskObject();
			tgtOnDelegated.onDelegated(currentUser,delegate);
		
		} else {
			if ( (this.getOwner() == null) ) {
				this.addTaskRequestParticipant(delegate,TaskParticipationKind.OWNER);
				TaskObject tgtOnDelegated=this.getTaskObject();
				tgtOnDelegated.onDelegated(currentUser,delegate);
			
			}
		}
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
		processInstance = (WorkflowProcessInstance)org.nakeduml.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("opium_library_for_bpm_task_request",params);
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.forward",uuid="252060@__6uyIIoaEeCPduia_-NbFw")
	public void forward(BusinessRole toPerson) {
		generateForwardEvent(toPerson);
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.forward",uuid="252060@_e0G_YK0gEeCwWeEjtrrMeQ")
	public void forward() {
	}
	
	public void generateActivateEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ActivateHandler922(true)));
	}
	
	public void generateAddTaskRequestParticipantEvent(Participant newParticipant, TaskParticipationKind kind) {
	}
	
	public void generateClaimEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ClaimHandler999(true)));
	}
	
	public void generateCompleteEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new CompleteHandler926(true)));
	}
	
	public void generateDelegateEvent(BusinessRole delegate) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new DelegateHandler1001(delegate,true)));
	}
	
	public void generateForwardEvent(BusinessRole toPerson) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ForwardHandler985(toPerson,true)));
	}
	
	public void generateRemoveTaskRequestParticipantEvent(Participant participant, TaskParticipationKind kind) {
	}
	
	public void generateResumeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ResumeHandler923(true)));
	}
	
	public void generateRevokeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new RevokeHandler984(true)));
	}
	
	public void generateSkipEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SkipHandler998(true)));
	}
	
	public void generateStartEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StartHandler919(true)));
	}
	
	public void generateStopEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StopHandler994(true)));
	}
	
	public void generateSuspendEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SuspendHandler933(true)));
	}
	
	public boolean getActive() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_ACTIVE);
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public boolean getActive_FinalState1() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_FINALSTATE1);
	}
	
	public boolean getActive_InProgress() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS);
	}
	
	public boolean getActive_Ready() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY);
	}
	
	public boolean getActive_Reserved() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED);
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public boolean getCompleted() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_COMPLETED);
	}
	
	public boolean getCreated() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_CREATED);
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.delegation",uuid="252060@_84NrDrRZEeCilvbXE8KmHA")
	public TaskDelegation getDelegation() {
		return delegation;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Date getExecutedOn() {
		return this.executedOn;
	}
	
	public TaskRequestState getHistory() {
		return this.History;
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
			return TaskRequestState.resolveById(nodeInstance.getNodeId());
		}
	}
	
	public String getName() {
		return "TaskRequest["+getId()+"]";
	}
	
	public Collection<NodeInstanceImpl> getNodeInstancesRecursively() {
		return (Collection<NodeInstanceImpl>)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true);
	}
	
	public boolean getObsolete() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_OBSOLETE);
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.owner",uuid="252060@_wy5fAKDREeCi16HgBnUGFw")
	public Participant getOwner() {
		Participant owner = any3().getParticipant();
		return owner;
	}
	
	public CompositionNode getOwningObject() {
		return getTaskObject();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.participations_in_task_id",uuid="252060@_BB8NEI6VEeCne5ArYLDbiA")
	public Set<ParticipationInTask> getParticipationsInTask() {
		return participationsInTask;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.potential_owners",uuid="252060@_sMysAKDQEeCEB8xJMe8jaA")
	public Set<Participant> getPotentialOwners() {
		Set<Participant> potentialOwners = Stdlib.collectionAsSet(collect2());
		return potentialOwners;
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null ) {
			this.processInstance=(WorkflowProcessInstance)org.nakeduml.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getProcessInstanceId());
			if ( this.processInstance!=null ) {
				((WorkflowProcessImpl)this.processInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.processInstance;
	}
	
	public Long getProcessInstanceId() {
		return this.processInstanceId;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.sub_requests_id",uuid="252060@_tog08I29EeCrtavWRHwoHg")
	public Set<AbstractRequest> getSubRequests() {
		return subRequests;
	}
	
	public boolean getSuspended() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_SUSPENDED);
	}
	
	public boolean getSuspended_InProgressButSuspended() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_INPROGRESSBUTSUSPENDED);
	}
	
	public boolean getSuspended_ReadyButSuspended() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_READYBUTSUSPENDED);
	}
	
	public boolean getSuspended_ReservedButSuspended() {
		return isStepActive(TaskRequestState.TASKINSTANCEREGION_SUSPENDED_REGION1_RESERVEDBUTSUSPENDED);
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.task_object",uuid="252060@_I3guVI3pEeCfQedkc0TCdA")
	public TaskObject getTaskObject() {
		return taskObject;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToTaskObject((TaskObject)owner);
	}
	
	public void init(ProcessContext context) {
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
	}
	
	public boolean isNumberOfPotentialOwners_Transition2() {
		return (this.getPotentialOwners().size() > 1);
	}
	
	public boolean isNumberOfPotentialOwners_Transition3() {
		return (this.getPotentialOwners().size() == 1);
	}
	
	public boolean isProcessDirty() {
		return this.processDirty;
	}
	
	public boolean isStepActive(IProcessStep step) {
		if ( step==this.endNodeInTaskInstanceRegion ) {
			return true;
		}
		if ( getProcessInstance()==null ) {
			return false;
		} else {
			for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
				if ( step.getId()==nodeInstance.getNodeId() ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests((TaskRequest)this);
		}
		if ( getTaskObject()!=null ) {
			getTaskObject().z_internalRemoveFromTaskRequest(this);
		}
		for ( ParticipationInTask child : new ArrayList<ParticipationInTask>(getParticipationsInTask()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<TaskRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public boolean onActiveCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_COMPLETED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public void onEntryOfCompleted(ProcessContext context) {
	}
	
	public void onEntryOfFinalState1(ProcessContext context) {
		setHistory(null);
		((NodeInstanceContainer)context.getNodeInstance().getNodeInstanceContainer()).removeNodeInstance((NodeInstanceImpl)context.getNodeInstance());
		((NodeInstanceContainer) context.getNodeInstance().getNodeInstanceContainer()).nodeInstanceCompleted((NodeInstanceImpl)context.getNodeInstance(), null);
	}
	
	public void onEntryOfHistory(ProcessContext context) {
		UmlNodeInstance umlState = (UmlNodeInstance)context.getNodeInstance();
		if ( getHistory()!=null ) {
			umlState.transitionToNode(getHistory().getId(),null);
		} else {
			onHistoryCompleted();
		}
	}
	
	public void onEntryOfInProgress(ProcessContext context) {
		setHistory(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_INPROGRESS);
	}
	
	public void onEntryOfInactive(ProcessContext context) {
		onInactiveCompleted();
	}
	
	public void onEntryOfNumberOfPotentialOwners_(ProcessContext context) {
		onNumberOfPotentialOwners_Completed();
	}
	
	public void onEntryOfObsolete(ProcessContext context) {
	}
	
	public void onEntryOfReady(ProcessContext context) {
		setHistory(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY);
	}
	
	public void onEntryOfReserved(ProcessContext context) {
		setHistory(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED);
	}
	
	public boolean onHistoryCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_HISTORY.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_NUMBEROFPOTENTIALOWNERS_.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onInactiveCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_INACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_CREATED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onNumberOfPotentialOwners_Completed() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_NUMBEROFPOTENTIALOWNERS_.getId()))!=null ) {
				if ( (this.getPotentialOwners().size() == 1) ) {
					TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
							public void onTransition() {
							}
						};
					processDirty=consumed=true;
					waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_RESERVED.getId(), listener);
				} else {
					if ( (this.getPotentialOwners().size() > 1) ) {
						TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
								public void onTransition() {
								}
							};
						processDirty=consumed=true;
						waitingNode.transitionToNode(TaskRequestState.TASKINSTANCEREGION_ACTIVE_REGION1_READY.getId(), listener);
					} else {
					
					}
				}
			}
		}
		return consumed;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("914")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("parentTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("929")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("993")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInTask)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
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
		if ( participationsInTask!=null ) {
			participationsInTask.z_internalRemoveFromTaskRequest(this);
			z_internalRemoveFromParticipationsInTask(participationsInTask);
		}
	}
	
	public void removeFromSubRequests(AbstractRequest subRequests) {
		if ( subRequests!=null ) {
			subRequests.z_internalRemoveFromParentTask(this);
			z_internalRemoveFromSubRequests(subRequests);
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.remove_task_request_participant",uuid="252060@_wuzAoI6SEeCrtavWRHwoHg")
	public void removeTaskRequestParticipant(Participant participant, TaskParticipationKind kind) {
		TaskRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInTask((select4(participant, kind)));
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.revoke",uuid="252060@_LlMOIIobEeCPduia_-NbFw")
	public void revoke() {
		generateRevokeEvent();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDelegation(TaskDelegation delegation) {
		this.z_internalAddToDelegation(delegation);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setExecutedOn(Date executedOn) {
		this.executedOn=executedOn;
	}
	
	public void setHistory(TaskRequestState History) {
		this.History=History;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipationsInTask(Set<ParticipationInTask> participationsInTask) {
		this.clearParticipationsInTask();
		this.addAllToParticipationsInTask(participationsInTask);
	}
	
	public void setProcessInstance(WorkflowProcessInstance processInstance) {
		this.processInstance=processInstance;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId=processInstanceId;
	}
	
	public void setSubRequests(Set<AbstractRequest> subRequests) {
		this.clearSubRequests();
		this.addAllToSubRequests(subRequests);
	}
	
	public void setTaskObject(TaskObject taskObject) {
		TaskObject oldValue = this.getTaskObject();
		if ( oldValue==null ) {
			this.z_internalAddToTaskObject(taskObject);
			if ( taskObject!=null ) {
				taskObject.z_internalAddToTaskRequest((TaskRequest)this);
			}
		} else {
			if ( !oldValue.equals(taskObject) ) {
				this.z_internalAddToTaskObject(taskObject);
				oldValue.z_internalRemoveFromTaskRequest(this);
				if ( taskObject!=null ) {
					taskObject.z_internalAddToTaskRequest((TaskRequest)this);
				}
			}
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.skip",uuid="252060@_1gF8AKDTEeCi16HgBnUGFw")
	public void skip() {
		generateSkipEvent();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.stop",uuid="252060@_GRVH0IobEeCPduia_-NbFw")
	public void stop() {
		generateStopEvent();
	}
	
	public String toXmlReferenceString() {
		return "<TaskRequest uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<TaskRequest ");
		sb.append("classUuid=\"252060@_zFmsEIoVEeCLqpffVZYAlw\" ");
		sb.append("className=\"org.nakeduml.runtime.bpm.TaskRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDelegation()!=null ) {
			sb.append("delegation=\""+ getDelegation().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n<participationsInRequest propertyId=\"914\">");
		for ( ParticipationInRequest participationsInRequest : getParticipationsInRequest() ) {
			sb.append("\n" + participationsInRequest.toXmlString());
		}
		sb.append("\n</participationsInRequest>");
		if ( getParentTask()==null ) {
			sb.append("\n<parentTask/>");
		} else {
			sb.append("\n<parentTask propertyId=\"929\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</parentTask>");
		}
		sb.append("\n<participationsInTask propertyId=\"993\">");
		for ( ParticipationInTask participationsInTask : getParticipationsInTask() ) {
			sb.append("\n" + participationsInTask.toXmlString());
		}
		sb.append("\n</participationsInTask>");
		sb.append("\n</TaskRequest>");
		return sb.toString();
	}
	
	public void transition(NodeInstanceImpl curNodeInstance, NodeImpl newNode) {
		NodeInstanceContainer container = (NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer();
		NodeInstanceImpl newNodeInstance = (NodeInstanceImpl)container.getNodeInstance(newNode);
		container.removeNodeInstance(curNodeInstance);
		curNodeInstance.trigger(newNodeInstance, "asdf");
	}
	
	public void z_internalAddToDelegation(TaskDelegation val) {
		this.delegation=val;
	}
	
	public void z_internalAddToParticipationsInTask(ParticipationInTask val) {
		this.participationsInTask.add(val);
	}
	
	public void z_internalAddToSubRequests(AbstractRequest val) {
		this.subRequests.add(val);
	}
	
	public void z_internalAddToTaskObject(TaskObject val) {
		this.taskObject=val;
	}
	
	public void z_internalRemoveFromDelegation(TaskDelegation val) {
		if ( getDelegation()!=null && val!=null && val.equals(getDelegation()) ) {
			this.delegation=null;
		}
	}
	
	public void z_internalRemoveFromParticipationsInTask(ParticipationInTask val) {
		this.participationsInTask.remove(val);
	}
	
	public void z_internalRemoveFromSubRequests(AbstractRequest val) {
		this.subRequests.remove(val);
	}
	
	public void z_internalRemoveFromTaskObject(TaskObject val) {
		if ( getTaskObject()!=null && val!=null && val.equals(getTaskObject()) ) {
			this.taskObject=null;
		}
	}
	
	/** Implements ->any( p : ParticipationInTask | p.kind = TaskParticipationKind::owner )
	 */
	private ParticipationInTask any3() {
		ParticipationInTask result = null;
		for ( ParticipationInTask p : this.getParticipationsInTask() ) {
			if ( (p.getKind().equals( TaskParticipationKind.OWNER)) ) {
				return p;
			}
		}
		return result;
	}
	
	/** Implements ->collect( p : ParticipationInTask | p.participant )
	 */
	private Collection<Participant> collect2() {
		Collection<Participant> result = new ArrayList<Participant>();
		for ( ParticipationInTask p : select1() ) {
			Participant bodyExpResult = p.getParticipant();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	private NodeImpl getNodeForStep(TaskRequestState step) {
		return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()));
	}
	
	/** Implements ->select( p : ParticipationInTask | p.kind = TaskParticipationKind::potentialOwner )
	 */
	private Set<ParticipationInTask> select1() {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask p : this.getParticipationsInTask() ) {
			if ( (p.getKind().equals( TaskParticipationKind.POTENTIALOWNER)) ) {
				result.add( p );
			}
		}
		return result;
	}
	
	/** Implements ->select( p : ParticipationInTask | (p.participant = participant) and p.kind = kind )
	 * 
	 * @param participant 
	 * @param kind 
	 */
	private Set<ParticipationInTask> select4(Participant participant, TaskParticipationKind kind) {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask p : this.getParticipationsInTask() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}

}