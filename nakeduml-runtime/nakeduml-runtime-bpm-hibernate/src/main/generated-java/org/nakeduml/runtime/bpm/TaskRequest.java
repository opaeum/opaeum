package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.ISignal;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.domain.TransitionListener;
import org.nakeduml.runtime.domain.UmlNodeInstance;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="TaskRequest")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="null_task_request")
@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.task_request",uuid="b63c167a_b5a4_43b7_a8e2_c995040b5d30")
@AccessType("field")
@DiscriminatorValue("task_request")
public class TaskRequest extends AbstractRequest implements CompositionNode, HibernateEntity, Serializable, IPersistentObject, IActiveObject, IProcessObject {
	static final private long serialVersionUID = 25;
	@Index(name="idx_task_request_task_object",columnNames="task_object")
	@Any(metaDef="TaskObject",metaColumn=@Column(name="task_object_type"))
	@JoinColumn(name="task_object",nullable=true)
	private TaskObject taskObject;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="parentTask",targetEntity=AbstractRequest.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<AbstractRequest> subRequests = new HashSet<AbstractRequest>();
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="taskRequest",targetEntity=ParticipationInTask.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<ParticipationInTask> participationsInTask = new HashSet<ParticipationInTask>();
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="delegation",nullable=true)
	private TaskDelegation delegation;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Enumerated
	@Type(type="TaskRequestStateResolver")
	private TaskRequestState endNodeInTaskInstanceRegion;
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;
	@Type(type="TaskRequestStateResolver")
	private TaskRequestState History;
	static private Set<TaskRequest> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;

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

	public void activate() {
		super.activate();
		onActivate();
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.add_task_request_participant",uuid="072ac484_0655_4c83_9888_279d6dc07b30")
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
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("delegation")!=null ) {
			setDelegation(TaskDelegation.valueOf(xml.getAttribute("delegation")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participationsInRequest") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationInRequest curVal = (ParticipationInRequest)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.addToParticipationsInRequest(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("taskObject") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TaskObject curVal = (TaskObject)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.setTaskObject(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participationsInTask") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationInTask curVal = (ParticipationInTask)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.addToParticipationsInTask(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.claim",uuid="62beb10d_8ba3_4c65_9e7e_030478288a01")
	public void claim() {
		onClaim();
	}
	
	public void clearParticipationsInTask() {
		removeAllFromParticipationsInTask(getParticipationsInTask());
	}
	
	public void clearSubRequests() {
		removeAllFromSubRequests(getSubRequests());
	}
	
	public void complete() {
		super.complete();
		onComplete();
	}
	
	public ParticipationInTask createParticipationsInTask() {
		ParticipationInTask newInstance= new ParticipationInTask();
		newInstance.init(this);
		return newInstance;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.delegate",uuid="d536b80d_de3c_41ed_8765_aaf9adc955db")
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.forward",uuid="25ce92a7_7def_4370_ba4a_d57650b3f5c5")
	public void forward() {
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.forward",uuid="b48c2746_4c14_4fb9_b271_8148dc33ebca")
	public void forward(BusinessRole toPerson) {
		onForward(toPerson);
	}
	
	public boolean getActive() {
		return isStepActive(TaskRequestState.ACTIVE);
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public boolean getActive_FinalState1() {
		return isStepActive(TaskRequestState.FINALSTATE1);
	}
	
	public boolean getActive_InProgress() {
		return isStepActive(TaskRequestState.INPROGRESS);
	}
	
	public boolean getActive_Ready() {
		return isStepActive(TaskRequestState.READY);
	}
	
	public boolean getActive_Reserved() {
		return isStepActive(TaskRequestState.RESERVED);
	}
	
	public boolean getCompleted() {
		return isStepActive(TaskRequestState.COMPLETED);
	}
	
	public boolean getCreated() {
		return isStepActive(TaskRequestState.CREATED);
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.delegation",uuid="8d35484b_f740_488e_b828_91e6790bfa18")
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
		return isStepActive(TaskRequestState.OBSOLETE);
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.owner",uuid="0256facc_c131_4da6_ac58_64e094d7f40b")
	public Participant getOwner() {
		Participant owner = any3().getParticipant();
		return owner;
	}
	
	public CompositionNode getOwningObject() {
		return getTaskObject();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.participations_in_task_id",uuid="01407713_8b45_4372_9774_c4d1ce5edf79")
	public Set<ParticipationInTask> getParticipationsInTask() {
		return participationsInTask;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.potential_owners",uuid="5a5c95ab_a139_4518_b763_1ee95c58e254")
	public Set<Participant> getPotentialOwners() {
		Set<Participant> potentialOwners = Stdlib.collectionAsSet(collect2());
		return potentialOwners;
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null || true ) {
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.sub_requests_id",uuid="359aa7b9_776a_4ee6_a051_88d8efd090e2")
	public Set<AbstractRequest> getSubRequests() {
		return subRequests;
	}
	
	public boolean getSuspended() {
		return isStepActive(TaskRequestState.SUSPENDED);
	}
	
	public boolean getSuspended_InProgressButSuspended() {
		return isStepActive(TaskRequestState.INPROGRESSBUTSUSPENDED);
	}
	
	public boolean getSuspended_ReadyButSuspended() {
		return isStepActive(TaskRequestState.READYBUTSUSPENDED);
	}
	
	public boolean getSuspended_ReservedButSuspended() {
		return isStepActive(TaskRequestState.RESERVEDBUTSUSPENDED);
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.task_object",uuid="8ac3c83d_8aad_40a9_8842_dc6a071cf5c0")
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
		super.init(context);
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
	}
	
	public boolean isNumberOfPotentialOwners_Transition2() {
		return (this.getPotentialOwners().size() > 1);
	}
	
	public boolean isNumberOfPotentialOwners_Transition3() {
		return (this.getPotentialOwners().size() == 1);
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
	}
	
	static public void mockAllInstances(Set<TaskRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public boolean onActivate() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.CREATED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onActiveCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.COMPLETED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onClaim() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.READY.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onComplete() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.FINALSTATE1.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onDelegate(BusinessRole delegate) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.RESERVED.getId(), listener);
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
		setHistory(TaskRequestState.INPROGRESS);
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
		setHistory(TaskRequestState.READY);
	}
	
	public void onEntryOfReserved(ProcessContext context) {
		setHistory(TaskRequestState.RESERVED);
	}
	
	public boolean onForward(BusinessRole toPerson) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.READY.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onHistoryCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.HISTORY.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.NUMBEROFPOTENTIALOWNERS_.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onInactiveCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.INACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.CREATED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onNumberOfPotentialOwners_Completed() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.NUMBEROFPOTENTIALOWNERS_.getId()))!=null ) {
				if ( (this.getPotentialOwners().size() > 1) ) {
					TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
							public void onTransition() {
							}
						};
					consumed=true;
					waitingNode.transitionToNode(TaskRequestState.READY.getId(), listener);
				} else {
					if ( (this.getPotentialOwners().size() == 1) ) {
						TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
								public void onTransition() {
								}
							};
						consumed=true;
						waitingNode.transitionToNode(TaskRequestState.RESERVED.getId(), listener);
					} else {
					
					}
				}
			}
		}
		return consumed;
	}
	
	public boolean onResume() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.SUSPENDED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onRevoke() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.READY.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onSkip() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.OBSOLETE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onStart() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.INPROGRESS.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.INPROGRESS.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onStop() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onSuspend() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.RESERVEDBUTSUSPENDED.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.INPROGRESSBUTSUSPENDED.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(TaskRequestState.READY.getId()))!=null ) {
				TransitionListener listener = new org.nakeduml.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				consumed=true;
				waitingNode.transitionToNode(TaskRequestState.READYBUTSUSPENDED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participationsInRequest") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInRequest)map.get(((Element)xml).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("parentTask") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)xml).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participationsInTask") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInTask)map.get(((Element)xml).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public boolean processSignal(ISignal signal) {
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.remove_task_request_participant",uuid="92189f72_d271_4c59_96c4_1da3fd146d55")
	public void removeTaskRequestParticipant(Participant participant, TaskParticipationKind kind) {
		TaskRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInTask((select4(participant, kind)));
	}
	
	public void resume() {
		super.resume();
		onResume();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.revoke",uuid="f513a78c_c3ad_4bc5_82b7_d89618f1b3f5")
	public void revoke() {
		onRevoke();
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
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.skip",uuid="5387e292_3648_4753_9f06_a27f41af44c3")
	public void skip() {
		onSkip();
	}
	
	public void start() {
		super.start();
		onStart();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="task_request.stop",uuid="11c32fb5_19b2_4378_9678_2a36674198da")
	public void stop() {
		onStop();
	}
	
	public void suspend() {
		super.suspend();
		onSuspend();
	}
	
	public String toXmlReferenceString() {
		return "<taskRequest uid=\"+getUid() + \">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<taskRequest");
		sb.append(" className=\"org.nakeduml.runtime.bpm.TaskRequest\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getDelegation()!=null ) {
			sb.append("delegation=\""+ getDelegation() + "\" ");
		}
		sb.append(">\n");
		if ( getParentTask()==null ) {
			sb.append("<parentTask/>");
		} else {
			sb.append(getParentTask().toXmlReferenceString());
			sb.append("</parentTask>");
			sb.append("\n");
		}
		if ( getTaskObject()==null ) {
			sb.append("<taskObject/>");
		} else {
			sb.append(getTaskObject().toXmlReferenceString());
			sb.append("</taskObject>");
			sb.append("\n");
		}
		sb.append("</TaskRequest>");
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