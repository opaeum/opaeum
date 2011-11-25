package org.opaeum.runtime.bpm;

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
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.abstractrequest.ActivateHandler202;
import org.opaeum.runtime.bpm.abstractrequest.CompleteHandler197;
import org.opaeum.runtime.bpm.abstractrequest.ResumeHandler205;
import org.opaeum.runtime.bpm.abstractrequest.StartHandler213;
import org.opaeum.runtime.bpm.abstractrequest.SuspendHandler201;
import org.opaeum.runtime.bpm.taskrequest.ClaimHandler262;
import org.opaeum.runtime.bpm.taskrequest.DelegateHandler252;
import org.opaeum.runtime.bpm.taskrequest.ForwardHandler249;
import org.opaeum.runtime.bpm.taskrequest.RevokeHandler254;
import org.opaeum.runtime.bpm.taskrequest.SkipHandler265;
import org.opaeum.runtime.bpm.taskrequest.StopHandler266;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TaskDelegation;
import org.opaeum.runtime.domain.TransitionListener;
import org.opaeum.runtime.domain.UmlNodeInstance;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="TaskRequest")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",name="task_request")
@NumlMetaInfo(uuid="252060@_zFmsEIoVEeCLqpffVZYAlw")
@AccessType("field")
@DiscriminatorValue("task_request")
public class TaskRequest extends AbstractRequest implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject, IProcessObject {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;
	@Column(name="calling_process_instance_id")
	private Long callingProcessInstanceId;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="delegation",nullable=true)
	private TaskDelegation _delegation;
	protected Object currentException;
	@Type(type="org.opaeum.runtime.bpm.TaskRequestStateResolver")
	private TaskRequestState endNodeInTaskInstanceRegion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Index(name="idx_task_request_task_object_id",columnNames="task_object_id")
	@Any(metaDef="TaskObject",metaColumn=@Column(name="task_object_id_type"))
	@JoinColumn(name="task_object_id",nullable=true)
	private TaskObject _taskObject;
	private String callingNodeInstanceUniqueId;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Transient
	transient private WorkflowProcessInstance callingProcessInstance;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	private boolean processDirty;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="_taskRequest",targetEntity=ParticipationInTask.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<ParticipationInTask> _participationsInTask = new HashSet<ParticipationInTask>();
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Type(type="TaskRequestStateResolver")
	private TaskRequestState History;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="_parentTask",targetEntity=AbstractRequest.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<AbstractRequest> _subRequests = new HashSet<AbstractRequest>();
	static final private long serialVersionUID = 40;
	static private Set<TaskRequest> mockedAllInstances;

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

	public void addAllToParticipationsInTask(Set<ParticipationInTask> _participationsInTask) {
		for ( ParticipationInTask o : _participationsInTask ) {
			addToParticipationsInTask(o);
		}
	}
	
	public void addAllToSubRequests(Set<AbstractRequest> _subRequests) {
		for ( AbstractRequest o : _subRequests ) {
			addToSubRequests(o);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_v52VoI6SEeCrtavWRHwoHg")
	public void addTaskRequestParticipant(Participant _newParticipant, TaskParticipationKind _kind) {
		ParticipationInTask _participation = null;
		ParticipationInTask _resultOnCreatePartipation = null;
		_resultOnCreatePartipation=new ParticipationInTask();
		_participation=_resultOnCreatePartipation;
		TaskRequest tgtAddParticipation=this;
		tgtAddParticipation.addToParticipationsInTask(_participation);
		ParticipationInTask tgtSetNewParticipant=_participation;
		tgtSetNewParticipant.setParticipant(_newParticipant);
		ParticipationInTask tgtSetKind=_participation;
		tgtSetKind.setKind(_kind);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getTaskObject().z_internalAddToTaskRequest((TaskRequest)this);
	}
	
	public void addToParticipationsInTask(ParticipationInTask _participationsInTask) {
		if ( _participationsInTask!=null ) {
			_participationsInTask.z_internalRemoveFromTaskRequest(_participationsInTask.getTaskRequest());
			_participationsInTask.z_internalAddToTaskRequest(this);
			z_internalAddToParticipationsInTask(_participationsInTask);
		}
	}
	
	public void addToSubRequests(AbstractRequest _subRequests) {
		if ( _subRequests!=null ) {
			_subRequests.z_internalRemoveFromParentTask(_subRequests.getParentTask());
			_subRequests.z_internalAddToParentTask(this);
			z_internalAddToSubRequests(_subRequests);
		}
	}
	
	static public Set<? extends TaskRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from TaskRequest").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_delegation").length()>0 ) {
			setDelegation(TaskDelegation.valueOf(xml.getAttribute("_delegation")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("206")) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_participationsInTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("251")) ) {
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
	
	@NumlMetaInfo(uuid="252060@_Nk_isIobEeCPduia_-NbFw")
	public void claim() {
		generateClaimEvent();
	}
	
	public void clearParticipationsInTask() {
		removeAllFromParticipationsInTask(getParticipationsInTask());
	}
	
	public void clearSubRequests() {
		removeAllFromSubRequests(getSubRequests());
	}
	
	public void completed() {
		AbstractRequestListener callbackListener = getCallingProcessObject();
		getProcessInstance().setState(WorkflowProcessInstance.STATE_COMPLETED);
		if ( callbackListener!=null ) {
			callbackListener.onAbstractRequestComplete(getCallingNodeInstanceUniqueId(),this);
		}
	}
	
	public boolean consumeActivateOccurrence() {
		boolean consumed = false;
		consumed=super.consumeActivateOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.CREATED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeAddTaskRequestParticipantOccurrence(Participant _newParticipant, TaskParticipationKind _kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeClaimOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_READY.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeCompleteOccurrence() {
		boolean consumed = false;
		consumed=super.consumeCompleteOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_FINALSTATE1.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeDelegateOccurrence(BusinessRole _delegate) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeForwardOccurrence(BusinessRole _toPerson) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_READY.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeRemoveTaskRequestParticipantOccurrence(Participant _participant, TaskParticipationKind _kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeResumeOccurrence() {
		boolean consumed = false;
		consumed=super.consumeResumeOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.SUSPENDED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeRevokeOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_READY.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeSkipOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.OBSOLETE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeStartOccurrence() {
		boolean consumed = false;
		consumed=super.consumeStartOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_INPROGRESS.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_INPROGRESS.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeStopOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_RESERVED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeSuspendOccurrence() {
		boolean consumed = false;
		consumed=super.consumeSuspendOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.SUSPENDED_RESERVEDBUTSUSPENDED.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_READY.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.SUSPENDED_READYBUTSUSPENDED.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.SUSPENDED_INPROGRESSBUTSUSPENDED.getId(), listener);
			}
		}
		return consumed;
	}
	
	@NumlMetaInfo(uuid="252060@_0lAQAIoaEeCPduia_-NbFw")
	public void delegate(BusinessRole _delegate) {
		BusinessRole _currentUser = null;
		if ( (!(this.getOwner() == null)) ) {
			this.removeTaskRequestParticipant(_delegate,TaskParticipationKind.OWNER);
			this.addTaskRequestParticipant(_delegate,TaskParticipationKind.OWNER);
			TaskObject tgtOnDelegated=this.getTaskObject();
			tgtOnDelegated.onDelegated(_currentUser,_delegate);
		
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
		processInstance = (WorkflowProcessInstance)org.opaeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("opaeum_bpm_task_request",params);
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
	
	@NumlMetaInfo(uuid="252060@_e0G_YK0gEeCwWeEjtrrMeQ")
	public void forward() {
	}
	
	@NumlMetaInfo(uuid="252060@__6uyIIoaEeCPduia_-NbFw")
	public void forward(BusinessRole _toPerson) {
		generateForwardEvent(_toPerson);
	}
	
	public void generateActivateEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ActivateHandler202(true)));
	}
	
	public void generateAddTaskRequestParticipantEvent(Participant _newParticipant, TaskParticipationKind _kind) {
	}
	
	public void generateClaimEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ClaimHandler262(true)));
	}
	
	public void generateCompleteEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new CompleteHandler197(true)));
	}
	
	public void generateDelegateEvent(BusinessRole _delegate) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new DelegateHandler252(_delegate,true)));
	}
	
	public void generateForwardEvent(BusinessRole _toPerson) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ForwardHandler249(_toPerson,true)));
	}
	
	public void generateRemoveTaskRequestParticipantEvent(Participant _participant, TaskParticipationKind _kind) {
	}
	
	public void generateResumeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ResumeHandler205(true)));
	}
	
	public void generateRevokeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new RevokeHandler254(true)));
	}
	
	public void generateSkipEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SkipHandler265(true)));
	}
	
	public void generateStartEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StartHandler213(true)));
	}
	
	public void generateStopEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StopHandler266(true)));
	}
	
	public void generateSuspendEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SuspendHandler201(true)));
	}
	
	public boolean getActive() {
		return isStepActive(TaskRequestState.ACTIVE);
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public boolean getActive_FinalState1() {
		return isStepActive(TaskRequestState.ACTIVE_FINALSTATE1);
	}
	
	public boolean getActive_InProgress() {
		return isStepActive(TaskRequestState.ACTIVE_INPROGRESS);
	}
	
	public boolean getActive_Ready() {
		return isStepActive(TaskRequestState.ACTIVE_READY);
	}
	
	public boolean getActive_Reserved() {
		return isStepActive(TaskRequestState.ACTIVE_RESERVED);
	}
	
	public String getCallingNodeInstanceUniqueId() {
		return this.callingNodeInstanceUniqueId;
	}
	
	public WorkflowProcess getCallingProcessDefinition() {
		return (WorkflowProcess) getCallingProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getCallingProcessInstance() {
		if ( this.callingProcessInstance==null ) {
			this.callingProcessInstance=(WorkflowProcessInstance)org.opaeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getCallingProcessInstanceId());
			if ( this.callingProcessInstance!=null ) {
				((WorkflowProcessImpl)this.callingProcessInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.callingProcessInstance;
	}
	
	public Long getCallingProcessInstanceId() {
		return this.callingProcessInstanceId;
	}
	
	public AbstractRequestListener getCallingProcessObject() {
		if ( getCallingProcessInstance()!=null  ) {
			AbstractRequestListener processObject = (AbstractRequestListener)getCallingProcessInstance().getVariable("processObject");
			return processObject;
		}
		return null;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public boolean getCompleted() {
		return isStepActive(TaskRequestState.COMPLETED);
	}
	
	public boolean getCreated() {
		return isStepActive(TaskRequestState.CREATED);
	}
	
	@NumlMetaInfo(uuid="252060@_84NrDrRZEeCilvbXE8KmHA")
	public TaskDelegation getDelegation() {
		TaskDelegation result = this._delegation;
		
		return result;
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
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	@NumlMetaInfo(uuid="252060@_wy5fAKDREeCi16HgBnUGFw")
	public Participant getOwner() {
		Participant result = any1().getParticipant();
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getTaskObject();
	}
	
	@NumlMetaInfo(uuid="252060@_BB8NEI6VEeCne5ArYLDbiA")
	public Set<ParticipationInTask> getParticipationsInTask() {
		Set<ParticipationInTask> result = this._participationsInTask;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_sMysAKDQEeCEB8xJMe8jaA")
	public Set<Participant> getPotentialOwners() {
		Set<Participant> result = Stdlib.collectionAsSet(collect3());
		
		return result;
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null ) {
			this.processInstance=(WorkflowProcessInstance)org.opaeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getProcessInstanceId());
			if ( this.processInstance!=null ) {
				((WorkflowProcessImpl)this.processInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.processInstance;
	}
	
	public Long getProcessInstanceId() {
		return this.processInstanceId;
	}
	
	@NumlMetaInfo(uuid="252060@_tog08I29EeCrtavWRHwoHg")
	public Set<AbstractRequest> getSubRequests() {
		Set<AbstractRequest> result = this._subRequests;
		
		return result;
	}
	
	public boolean getSuspended() {
		return isStepActive(TaskRequestState.SUSPENDED);
	}
	
	public boolean getSuspended_InProgressButSuspended() {
		return isStepActive(TaskRequestState.SUSPENDED_INPROGRESSBUTSUSPENDED);
	}
	
	public boolean getSuspended_ReadyButSuspended() {
		return isStepActive(TaskRequestState.SUSPENDED_READYBUTSUSPENDED);
	}
	
	public boolean getSuspended_ReservedButSuspended() {
		return isStepActive(TaskRequestState.SUSPENDED_RESERVEDBUTSUSPENDED);
	}
	
	@NumlMetaInfo(uuid="252060@_I3guVI3pEeCfQedkc0TCdA")
	public TaskObject getTaskObject() {
		TaskObject result = this._taskObject;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(ProcessContext context) {
		super.init(context);
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToTaskObject((TaskObject)owner);
	}
	
	public boolean isComplete() {
		boolean result = endNodeInTaskInstanceRegion!=null ||currentException!=null;
		
		return result;
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
	}
	
	static public void mockAllInstances(Set<TaskRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public boolean onActiveCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.COMPLETED.getId(), listener);
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
		setHistory(TaskRequestState.ACTIVE_INPROGRESS);
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
		setHistory(TaskRequestState.ACTIVE_READY);
	}
	
	public void onEntryOfReserved(ProcessContext context) {
		setHistory(TaskRequestState.ACTIVE_RESERVED);
	}
	
	public boolean onHistoryCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_HISTORY.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_NUMBEROFPOTENTIALOWNERS_.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onInactiveCompleted() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.INACTIVE.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.CREATED.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean onNumberOfPotentialOwners_Completed() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.TaskRequestState.ACTIVE_NUMBEROFPOTENTIALOWNERS_.getId()))!=null ) {
				if ( (this.getPotentialOwners().size() == 1) ) {
					TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
							public void onTransition() {
							}
						};
					processDirty=consumed=true;
					waitingNode.transitionToNode(TaskRequestState.ACTIVE_RESERVED.getId(), listener);
				} else {
					if ( (this.getPotentialOwners().size() > 1) ) {
						TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
								public void onTransition() {
								}
							};
						processDirty=consumed=true;
						waitingNode.transitionToNode(TaskRequestState.ACTIVE_READY.getId(), listener);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("206")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_parentTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("186")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_participationsInTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("251")) ) {
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
	
	public void propagateException(Object exception) {
		AbstractRequestListener callbackListener = getCallingProcessObject();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onAbstractRequestUnhandledException(getCallingNodeInstanceUniqueId(),exception, this);
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
	
	public void removeAllFromParticipationsInTask(Set<ParticipationInTask> _participationsInTask) {
		Set<ParticipationInTask> tmp = new HashSet<ParticipationInTask>(_participationsInTask);
		for ( ParticipationInTask o : tmp ) {
			removeFromParticipationsInTask(o);
		}
	}
	
	public void removeAllFromSubRequests(Set<AbstractRequest> _subRequests) {
		Set<AbstractRequest> tmp = new HashSet<AbstractRequest>(_subRequests);
		for ( AbstractRequest o : tmp ) {
			removeFromSubRequests(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipationsInTask(ParticipationInTask _participationsInTask) {
		if ( _participationsInTask!=null ) {
			_participationsInTask.z_internalRemoveFromTaskRequest(this);
			z_internalRemoveFromParticipationsInTask(_participationsInTask);
		}
	}
	
	public void removeFromSubRequests(AbstractRequest _subRequests) {
		if ( _subRequests!=null ) {
			_subRequests.z_internalRemoveFromParentTask(this);
			z_internalRemoveFromSubRequests(_subRequests);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_wuzAoI6SEeCrtavWRHwoHg")
	public void removeTaskRequestParticipant(Participant _participant, TaskParticipationKind _kind) {
		TaskRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInTask((select4(_participant, _kind)));
	}
	
	@NumlMetaInfo(uuid="252060@_LlMOIIobEeCPduia_-NbFw")
	public void revoke() {
		generateRevokeEvent();
	}
	
	public void setCallingNodeInstanceUniqueId(String callingNodeInstanceUniqueId) {
		this.callingNodeInstanceUniqueId=callingNodeInstanceUniqueId;
	}
	
	public void setCallingProcessInstance(WorkflowProcessInstance callingProcessInstance) {
		this.callingProcessInstance=callingProcessInstance;
	}
	
	public void setCallingProcessInstanceId(Long callingProcessInstanceId) {
		this.callingProcessInstanceId=callingProcessInstanceId;
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDelegation(TaskDelegation _delegation) {
		this.z_internalAddToDelegation(_delegation);
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
	
	public void setParticipationsInTask(Set<ParticipationInTask> _participationsInTask) {
		this.clearParticipationsInTask();
		this.addAllToParticipationsInTask(_participationsInTask);
	}
	
	public void setProcessInstance(WorkflowProcessInstance processInstance) {
		this.processInstance=processInstance;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId=processInstanceId;
	}
	
	public void setReturnInfo(ProcessContext context) {
		this.callingProcessInstanceId=context.getProcessInstance().getId();
		this.callingNodeInstanceUniqueId=((NodeInstanceImpl)context.getNodeInstance()).getUniqueId();
	}
	
	public void setSubRequests(Set<AbstractRequest> _subRequests) {
		this.clearSubRequests();
		this.addAllToSubRequests(_subRequests);
	}
	
	public void setTaskObject(TaskObject _taskObject) {
		TaskObject oldValue = this.getTaskObject();
		if ( oldValue==null ) {
			this.z_internalAddToTaskObject(_taskObject);
			if ( _taskObject!=null ) {
				_taskObject.z_internalAddToTaskRequest((TaskRequest)this);
			}
		} else {
			if ( !oldValue.equals(_taskObject) ) {
				this.z_internalAddToTaskObject(_taskObject);
				oldValue.z_internalRemoveFromTaskRequest(this);
				if ( _taskObject!=null ) {
					_taskObject.z_internalAddToTaskRequest((TaskRequest)this);
				}
			}
		}
	}
	
	@NumlMetaInfo(uuid="252060@_1gF8AKDTEeCi16HgBnUGFw")
	public void skip() {
		generateSkipEvent();
	}
	
	@NumlMetaInfo(uuid="252060@_GRVH0IobEeCPduia_-NbFw")
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
		sb.append("className=\"org.opaeum.runtime.bpm.TaskRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDelegation()!=null ) {
			sb.append("_delegation=\""+ getDelegation().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n<_participationsInRequest propertyId=\"206\">");
		for ( ParticipationInRequest _participationsInRequest : getParticipationsInRequest() ) {
			sb.append("\n" + _participationsInRequest.toXmlString());
		}
		sb.append("\n</_participationsInRequest>");
		if ( getParentTask()==null ) {
			sb.append("\n<_parentTask/>");
		} else {
			sb.append("\n<_parentTask propertyId=\"186\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</_parentTask>");
		}
		sb.append("\n<_participationsInTask propertyId=\"251\">");
		for ( ParticipationInTask _participationsInTask : getParticipationsInTask() ) {
			sb.append("\n" + _participationsInTask.toXmlString());
		}
		sb.append("\n</_participationsInTask>");
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
		this._delegation=val;
	}
	
	public void z_internalAddToParticipationsInTask(ParticipationInTask val) {
		this._participationsInTask.add(val);
	}
	
	public void z_internalAddToSubRequests(AbstractRequest val) {
		this._subRequests.add(val);
	}
	
	public void z_internalAddToTaskObject(TaskObject val) {
		this._taskObject=val;
	}
	
	public void z_internalRemoveFromDelegation(TaskDelegation val) {
		if ( getDelegation()!=null && val!=null && val.equals(getDelegation()) ) {
			this._delegation=null;
		}
	}
	
	public void z_internalRemoveFromParticipationsInTask(ParticipationInTask val) {
		this._participationsInTask.remove(val);
	}
	
	public void z_internalRemoveFromSubRequests(AbstractRequest val) {
		this._subRequests.remove(val);
	}
	
	public void z_internalRemoveFromTaskObject(TaskObject val) {
		if ( getTaskObject()!=null && val!=null && val.equals(getTaskObject()) ) {
			this._taskObject=null;
		}
	}
	
	/** Implements ->any( p : ParticipationInTask | p.kind = TaskParticipationKind::owner )
	 */
	private ParticipationInTask any1() {
		ParticipationInTask result = null;
		for ( ParticipationInTask _p : this.getParticipationsInTask() ) {
			if ( (_p.getKind().equals( TaskParticipationKind.OWNER)) ) {
				return _p;
			}
		}
		return result;
	}
	
	/** Implements ->collect( p : ParticipationInTask | p.participant )
	 */
	private Collection<Participant> collect3() {
		Collection<Participant> result = new ArrayList<Participant>();
		for ( ParticipationInTask _p : select2() ) {
			Participant bodyExpResult = _p.getParticipant();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	private NodeImpl getNodeForStep(TaskRequestState step) {
		return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()));
	}
	
	/** Implements ->select( p : ParticipationInTask | p.kind = TaskParticipationKind::potentialOwner )
	 */
	private Set<ParticipationInTask> select2() {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask _p : this.getParticipationsInTask() ) {
			if ( (_p.getKind().equals( TaskParticipationKind.POTENTIALOWNER)) ) {
				result.add( _p );
			}
		}
		return result;
	}
	
	/** Implements ->select( p : ParticipationInTask | (p.participant = participant) and p.kind = kind )
	 * 
	 * @param _participant 
	 * @param _kind 
	 */
	private Set<ParticipationInTask> select4(Participant _participant, TaskParticipationKind _kind) {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask _p : this.getParticipationsInTask() ) {
			if ( (_p.getParticipant().equals(_participant) && (_p.getKind().equals( _kind))) ) {
				result.add( _p );
			}
		}
		return result;
	}

}