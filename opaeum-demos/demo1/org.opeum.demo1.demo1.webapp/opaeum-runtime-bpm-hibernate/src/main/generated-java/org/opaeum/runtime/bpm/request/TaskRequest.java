package org.opaeum.runtime.bpm.request;

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
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.request.abstractrequest.ActivateHandler3717858776997870408;
import org.opaeum.runtime.bpm.request.abstractrequest.CompleteHandler20843194362239925;
import org.opaeum.runtime.bpm.request.abstractrequest.ResumeHandler286517672851676135;
import org.opaeum.runtime.bpm.request.abstractrequest.StartHandler2809139800710243133;
import org.opaeum.runtime.bpm.request.abstractrequest.SuspendHandler3547071820132893959;
import org.opaeum.runtime.bpm.request.taskrequest.ClaimHandler7608483210400824401;
import org.opaeum.runtime.bpm.request.taskrequest.DelegateHandler6837298467385087869;
import org.opaeum.runtime.bpm.request.taskrequest.ForwardHandler7251280809563715157;
import org.opaeum.runtime.bpm.request.taskrequest.RevokeHandler7253144136017044923;
import org.opaeum.runtime.bpm.request.taskrequest.SkipHandler2519565167906952379;
import org.opaeum.runtime.bpm.request.taskrequest.StopHandler8236205458182045891;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TransitionListener;
import org.opaeum.runtime.domain.UmlNodeInstance;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_zFmsEIoVEeCLqpffVZYAlw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="task_request",schema="opaeum_bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="TaskRequest")
@DiscriminatorValue(	"task_request")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class TaskRequest extends AbstractRequest implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Type(type="TaskRequestStateResolver")
	private TaskRequestState History;
	private String callingNodeInstanceUniqueId;
	@Transient
	transient private WorkflowProcessInstance callingProcessInstance;
	@Column(name="calling_process_instance_id")
	private Long callingProcessInstanceId;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Transient
	protected Object currentException;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="delegation",nullable=true)
	private TaskDelegation delegation;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Enumerated(	javax.persistence.EnumType.STRING)
	private TaskRequestState endNodeInTaskRequestRegion;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="executed_on")
	private Date executedOn;
	static private Set<TaskRequest> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="taskRequest",targetEntity=ParticipationInTask.class)
	private Set<ParticipationInTask> participationInTask = new HashSet<ParticipationInTask>();
	@Transient
	private boolean processDirty;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	static final private long serialVersionUID = 8501108023512204129l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="parentTask",targetEntity=AbstractRequest.class)
	private Set<AbstractRequest> subRequests = new HashSet<AbstractRequest>();
	@Index(columnNames="task_object",name="idx_task_request_task_object")
	@Any(metaColumn=
		@Column(name="task_object_type"),metaDef="ITaskObject")
	@JoinColumn(name="task_object",nullable=true)
	private ITaskObject taskObject;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public TaskRequest(ITaskObject owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for TaskRequest
	 */
	public TaskRequest() {
	}

	public void addAllToParticipationInTask(Set<ParticipationInTask> participationInTask) {
		for ( ParticipationInTask o : participationInTask ) {
			addToParticipationInTask(o);
		}
	}
	
	public void addAllToSubRequests(Set<AbstractRequest> subRequests) {
		for ( AbstractRequest o : subRequests ) {
			addToSubRequests(o);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_v52VoI6SEeCrtavWRHwoHg")
	public void addTaskRequestParticipant(Participant newParticipant, TaskParticipationKind kind) {
		ParticipationInTask participation = null;
		ParticipationInTask resultOnCreatePartipation = null;
		resultOnCreatePartipation=new ParticipationInTask();
		participation=resultOnCreatePartipation;
		TaskRequest tgtAddParticipation=this;
		tgtAddParticipation.addToParticipationInTask(participation);
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
	
	public void addToParticipationInTask(ParticipationInTask participationInTask) {
		if ( participationInTask!=null ) {
			participationInTask.z_internalRemoveFromTaskRequest(participationInTask.getTaskRequest());
			participationInTask.z_internalAddToTaskRequest(this);
			z_internalAddToParticipationInTask(participationInTask);
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
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.request.TaskRequest.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3022263813028286216")) ) {
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
						this.addToParticipationInRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationInTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7631795069536317681")) ) {
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
						this.addToParticipationInTask(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void cancel() {
		getProcessInstance().setState(WorkflowProcessInstance.STATE_COMPLETED);
	}
	
	@NumlMetaInfo(uuid="252060@_Nk_isIobEeCPduia_-NbFw")
	public void claim() {
		generateClaimEvent();
	}
	
	public void clearParticipationInTask() {
		removeAllFromParticipationInTask(getParticipationInTask());
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.CREATED.getId()))!=null ) {
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
	
	public boolean consumeAddTaskRequestParticipantOccurrence(Participant newParticipant, TaskParticipationKind kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeClaimOccurrence() {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_READY.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_INPROGRESS.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_FINALACTIVESTATE.getId(), listener);
			}
		}
		return consumed;
	}
	
	public boolean consumeDelegateOccurrence(IBusinessRole delegate) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE.getId()))!=null ) {
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
	
	public boolean consumeForwardOccurrence(IBusinessRole toPerson) {
		boolean consumed = false;
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE.getId()))!=null ) {
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
	
	public boolean consumeRemoveTaskRequestParticipantOccurrence(Participant participant, TaskParticipationKind kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeResumeOccurrence() {
		boolean consumed = false;
		consumed=super.consumeResumeOccurrence();
		if ( getProcessInstance()!=null ) {
			UmlNodeInstance waitingNode;
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.SUSPENDED.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_RESERVED.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.ACTIVE_INPROGRESS.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_INPROGRESS.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_RESERVED.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.SUSPENDED_RESERVEDBUTSUSPENDED.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_READY.getId()))!=null ) {
				TransitionListener listener = new org.opaeum.runtime.domain.TransitionListener(){	
						public void onTransition() {
						}
					};
				processDirty=consumed=true;
				waitingNode.transitionToNode(TaskRequestState.SUSPENDED_READYBUTSUSPENDED.getId(), listener);
			}
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_INPROGRESS.getId()))!=null ) {
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
	public void delegate(IBusinessRole delegate) {
		Participant currentRole = null;
		if ( (!(this.getOwner() == null)) ) {
			this.removeTaskRequestParticipant(delegate,TaskParticipationKind.OWNER);
			this.addTaskRequestParticipant(delegate,TaskParticipationKind.OWNER);
			ITaskObject tgtOnDelegated=this.getTaskObject();
			tgtOnDelegated.onDelegated(currentRole,delegate);
		
		} else {
			if ( (this.getOwner() == null) ) {
				this.addTaskRequestParticipant(delegate,TaskParticipationKind.OWNER);
				ITaskObject tgtOnDelegated=this.getTaskObject();
				tgtOnDelegated.onDelegated(currentRole,delegate);
			
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
		processInstance = (WorkflowProcessInstance)org.opaeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("request_task_request",params);
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
	
	@NumlMetaInfo(uuid="252060@__6uyIIoaEeCPduia_-NbFw")
	public void forward(IBusinessRole toPerson) {
		generateForwardEvent(toPerson);
	}
	
	@NumlMetaInfo(uuid="252060@_e0G_YK0gEeCwWeEjtrrMeQ")
	public void forward() {
	}
	
	public void generateActivateEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ActivateHandler3717858776997870408(true)));
	}
	
	public void generateAddTaskRequestParticipantEvent(Participant newParticipant, TaskParticipationKind kind) {
	}
	
	public void generateClaimEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ClaimHandler7608483210400824401(true)));
	}
	
	public void generateCompleteEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new CompleteHandler20843194362239925(true)));
	}
	
	public void generateDelegateEvent(IBusinessRole delegate) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new DelegateHandler6837298467385087869(delegate,true)));
	}
	
	public void generateForwardEvent(IBusinessRole toPerson) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ForwardHandler7251280809563715157(toPerson,true)));
	}
	
	public void generateRemoveTaskRequestParticipantEvent(Participant participant, TaskParticipationKind kind) {
	}
	
	public void generateResumeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ResumeHandler286517672851676135(true)));
	}
	
	public void generateRevokeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new RevokeHandler7253144136017044923(true)));
	}
	
	public void generateSkipEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SkipHandler2519565167906952379(true)));
	}
	
	public void generateStartEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StartHandler2809139800710243133(true)));
	}
	
	public void generateStopEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StopHandler8236205458182045891(true)));
	}
	
	public void generateSuspendEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SuspendHandler3547071820132893959(true)));
	}
	
	public boolean getActive() {
		return isStepActive(TaskRequestState.ACTIVE);
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public boolean getActive_FinalActiveState() {
		return isStepActive(TaskRequestState.ACTIVE_FINALACTIVESTATE);
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
	
	public Object getCurrentException() {
		return this.currentException;
	}
	
	@NumlMetaInfo(uuid="252060@_84NrDrRZEeCilvbXE8KmHA")
	public TaskDelegation getDelegation() {
		TaskDelegation result = this.delegation;
		
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
		if ( this.endNodeInTaskRequestRegion!=null ) {
			return this.endNodeInTaskRequestRegion;
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
	public Set<ParticipationInTask> getParticipationInTask() {
		Set<ParticipationInTask> result = this.participationInTask;
		
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
	
	public IRequestObject getRequestObject() {
		IRequestObject result = null;
		result=super.getRequestObject();
		if ( this.getTaskObject()!=null ) {
			result=this.getTaskObject();
		}
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_tog08I29EeCrtavWRHwoHg")
	public Set<AbstractRequest> getSubRequests() {
		Set<AbstractRequest> result = this.subRequests;
		
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
	public ITaskObject getTaskObject() {
		ITaskObject result = this.taskObject;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToTaskObject((ITaskObject)owner);
	}
	
	public void init(ProcessContext context) {
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
	}
	
	public boolean isComplete() {
		boolean result = endNodeInTaskRequestRegion!=null ||currentException!=null;
		
		return result;
	}
	
	public boolean isNumberOfPotentialOwners_NumberOfPotentialOwnersToReady() {
		return (this.getPotentialOwners().size() > 1);
	}
	
	public boolean isNumberOfPotentialOwners_NumberOfPotentialOwnersToReserved() {
		return (this.getPotentialOwners().size() == 1);
	}
	
	public boolean isProcessDirty() {
		return this.processDirty;
	}
	
	public boolean isStepActive(IProcessStep step) {
		if ( step==this.endNodeInTaskRequestRegion ) {
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
			getParentTask().z_internalRemoveFromSubRequests(this);
		}
		if ( getTaskObject()!=null ) {
			getTaskObject().z_internalRemoveFromTaskRequest(this);
		}
		for ( ParticipationInTask child : new ArrayList<ParticipationInTask>(getParticipationInTask()) ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE.getId()))!=null ) {
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
	
	public void onEntryOfFinalActiveState(ProcessContext context) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_HISTORY.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.INACTIVE.getId()))!=null ) {
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
			if ( consumed==false && (waitingNode=(UmlNodeInstance)findWaitingNodeByNodeId(org.opaeum.runtime.bpm.request.TaskRequestState.ACTIVE_NUMBEROFPOTENTIALOWNERS_.getId()))!=null ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3022263813028286216")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("parentTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5501213897228443240")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationInTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7631795069536317681")) ) {
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
	
	public void removeAllFromParticipationInTask(Set<ParticipationInTask> participationInTask) {
		Set<ParticipationInTask> tmp = new HashSet<ParticipationInTask>(participationInTask);
		for ( ParticipationInTask o : tmp ) {
			removeFromParticipationInTask(o);
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
	
	public void removeFromParticipationInTask(ParticipationInTask participationInTask) {
		if ( participationInTask!=null ) {
			participationInTask.z_internalRemoveFromTaskRequest(this);
			z_internalRemoveFromParticipationInTask(participationInTask);
		}
	}
	
	public void removeFromSubRequests(AbstractRequest subRequests) {
		if ( subRequests!=null ) {
			subRequests.z_internalRemoveFromParentTask(this);
			z_internalRemoveFromSubRequests(subRequests);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_wuzAoI6SEeCrtavWRHwoHg")
	public void removeTaskRequestParticipant(Participant participant, TaskParticipationKind kind) {
		TaskRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationInTask((select4(participant, kind)));
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
	
	public void setCurrentException(Object currentException) {
		this.currentException=currentException;
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
	
	public void setParticipationInTask(Set<ParticipationInTask> participationInTask) {
		this.clearParticipationInTask();
		this.addAllToParticipationInTask(participationInTask);
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
	
	public void setSubRequests(Set<AbstractRequest> subRequests) {
		this.clearSubRequests();
		this.addAllToSubRequests(subRequests);
	}
	
	public void setTaskObject(ITaskObject taskObject) {
		ITaskObject oldValue = this.getTaskObject();
		if ( oldValue==null ) {
			if ( taskObject!=null ) {
				TaskRequest oldOther = (TaskRequest)taskObject.getTaskRequest();
				taskObject.z_internalRemoveFromTaskRequest(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromTaskObject(taskObject);
				}
				taskObject.z_internalAddToTaskRequest((TaskRequest)this);
			}
			this.z_internalAddToTaskObject(taskObject);
		} else {
			if ( !oldValue.equals(taskObject) ) {
				oldValue.z_internalRemoveFromTaskRequest(this);
				z_internalRemoveFromTaskObject(oldValue);
				if ( taskObject!=null ) {
					TaskRequest oldOther = (TaskRequest)taskObject.getTaskRequest();
					taskObject.z_internalRemoveFromTaskRequest(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromTaskObject(taskObject);
					}
					taskObject.z_internalAddToTaskRequest((TaskRequest)this);
				}
				this.z_internalAddToTaskObject(taskObject);
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
		sb.append("className=\"org.opaeum.runtime.bpm.request.TaskRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDelegation()!=null ) {
			sb.append("delegation=\""+ getDelegation().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n<participationInRequest propertyId=\"3022263813028286216\">");
		for ( ParticipationInRequest participationInRequest : getParticipationInRequest() ) {
			sb.append("\n" + participationInRequest.toXmlString());
		}
		sb.append("\n</participationInRequest>");
		if ( getParentTask()==null ) {
			sb.append("\n<parentTask/>");
		} else {
			sb.append("\n<parentTask propertyId=\"5501213897228443240\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</parentTask>");
		}
		sb.append("\n<participationInTask propertyId=\"7631795069536317681\">");
		for ( ParticipationInTask participationInTask : getParticipationInTask() ) {
			sb.append("\n" + participationInTask.toXmlString());
		}
		sb.append("\n</participationInTask>");
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
	
	public void z_internalAddToParticipationInTask(ParticipationInTask val) {
		this.participationInTask.add(val);
	}
	
	public void z_internalAddToSubRequests(AbstractRequest val) {
		this.subRequests.add(val);
	}
	
	public void z_internalAddToTaskObject(ITaskObject val) {
		this.taskObject=val;
	}
	
	public void z_internalRemoveFromDelegation(TaskDelegation val) {
		if ( getDelegation()!=null && val!=null && val.equals(getDelegation()) ) {
			this.delegation=null;
		}
	}
	
	public void z_internalRemoveFromParticipationInTask(ParticipationInTask val) {
		this.participationInTask.remove(val);
	}
	
	public void z_internalRemoveFromSubRequests(AbstractRequest val) {
		this.subRequests.remove(val);
	}
	
	public void z_internalRemoveFromTaskObject(ITaskObject val) {
		if ( getTaskObject()!=null && val!=null && val.equals(getTaskObject()) ) {
			this.taskObject=null;
		}
	}
	
	/** Implements ->any( p : ParticipationInTask | p.kind = TaskParticipationKind::owner )
	 */
	private ParticipationInTask any1() {
		ParticipationInTask result = null;
		for ( ParticipationInTask p : this.getParticipationInTask() ) {
			if ( (p.getKind().equals( TaskParticipationKind.OWNER)) ) {
				return p;
			}
		}
		return result;
	}
	
	/** Implements ->collect( p : ParticipationInTask | p.participant )
	 */
	private Collection<Participant> collect3() {
		Collection<Participant> result = new ArrayList<Participant>();
		for ( ParticipationInTask p : select2() ) {
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
	private Set<ParticipationInTask> select2() {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask p : this.getParticipationInTask() ) {
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
		for ( ParticipationInTask p : this.getParticipationInTask() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}

}