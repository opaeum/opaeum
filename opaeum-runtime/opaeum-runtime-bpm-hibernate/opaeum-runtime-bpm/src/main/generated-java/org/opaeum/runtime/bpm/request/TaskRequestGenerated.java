package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.ReturnInfo;
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
import org.opaeum.runtime.bpm.request.taskrequest.TaskRequestRegion;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Active;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Created;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Suspended;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Ready;
import org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.Reserved;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.bpm.requestobject.ITaskObject;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TaskDelegation;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid = "252060@_zFmsEIoVEeCLqpffVZYAlw")
@Filter(name = "noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@AccessType("field")
@MappedSuperclass
@DiscriminatorValue("task_request")
public class TaskRequestGenerated extends AbstractRequest implements IStateMachineExecution,IPersistentObject,IEventGenerator,HibernateEntity,CompositionNode,
		Serializable{
	private String History;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Transient
	protected Object currentException;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name = "delegation",nullable = true)
	private TaskDelegation delegation;
	// Initialise to 1000 from 1970
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name = "executed_on")
	private Date executedOn;
	private Map<String,IExecutionElement> executionElements;
	static private Set<TaskRequest> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition = "deleted_on > current_timestamp",name = "noDeletedObjects")
	@OneToMany(cascade = javax.persistence.CascadeType.ALL,fetch = javax.persistence.FetchType.LAZY,mappedBy = "taskRequest",targetEntity = ParticipationInTask.class)
	private Set<ParticipationInTask> participationInTask = new HashSet<ParticipationInTask>();
	@Transient
	private AbstractPersistence persistence;
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 8501108023512204129l;
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition = "deleted_on > current_timestamp",name = "noDeletedObjects")
	@OneToMany(fetch = javax.persistence.FetchType.LAZY,mappedBy = "parentTask",targetEntity = AbstractRequest.class)
	private Set<AbstractRequest> subRequests = new HashSet<AbstractRequest>();
	@Embedded
	@AttributeOverrides({@AttributeOverride(column = @Column(name = "task_object"),name = "identifier"),
			@AttributeOverride(column = @Column(name = "task_object_type"),name = "classIdentifier")})
	private ITaskObject taskObject;
	/**
	 * This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject
	 */
	public TaskRequestGenerated(ITaskObject owningObject){
		init(owningObject);
		addToOwningObject();
	}
	/**
	 * Default constructor for TaskRequest
	 */
	public TaskRequestGenerated(){
	}
	public void addAllToParticipationInTask(Set<ParticipationInTask> participationInTask){
		for(ParticipationInTask o:participationInTask){
			addToParticipationInTask(o);
		}
	}
	public void addAllToSubRequests(Set<AbstractRequest> subRequests){
		for(AbstractRequest o:subRequests){
			addToSubRequests(o);
		}
	}
	@NumlMetaInfo(uuid = "252060@_v52VoI6SEeCrtavWRHwoHg")
	public void addTaskRequestParticipant(@ParameterMetaInfo(name = "newParticipant",opaeumId = 5015842494571072006l,uuid = "252060@_v52VoY6SEeCrtavWRHwoHg")
	Participant newParticipant,@ParameterMetaInfo(name = "kind",opaeumId = 3546049321002453618l,uuid = "252060@_v52Voo6SEeCrtavWRHwoHg")
	TaskParticipationKind kind){
		ParticipationInTask participation = null;
		ParticipationInTask result = null;
		generateAddTaskRequestParticipantEvent(newParticipant, kind);
		result = new ParticipationInTask();
		participation = result;
		TaskRequest tgtAddParticipation = (TaskRequest) this;
		tgtAddParticipation.addToParticipationInTask(participation);
		Participation tgtSetNewParticipant = participation;
		tgtSetNewParticipant.setParticipant(newParticipant);
		ParticipationInTask tgtSetKind = participation;
		tgtSetKind.setKind(kind);
	}
	/**
	 * Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject(){
		getTaskObject().z_internalAddToTaskRequest((TaskRequest) this);
	}
	public void addToParticipationInTask(ParticipationInTask participationInTask){
		if(participationInTask != null){
			participationInTask.z_internalRemoveFromTaskRequest(participationInTask.getTaskRequest());
			participationInTask.z_internalAddToTaskRequest((TaskRequest) this);
			z_internalAddToParticipationInTask(participationInTask);
		}
	}
	public void addToSubRequests(AbstractRequest subRequests){
		if(subRequests != null){
			subRequests.z_internalRemoveFromParentTask(subRequests.getParentTask());
			subRequests.z_internalAddToParentTask((TaskRequest) this);
			z_internalAddToSubRequests(subRequests);
		}
	}
	static public Set<? extends TaskRequest> allInstances(AbstractPersistence persistence){
		if(mockedAllInstances == null){
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.TaskRequest.class));
		}else{
			return mockedAllInstances;
		}
	}
	public void buildTreeFromXml(Element xml,Map<String,Object> map){
		setUid(xml.getAttribute("uid"));
		if(xml.getAttribute("delegation").length() > 0){
			setDelegation(TaskDelegation.valueOf(xml.getAttribute("delegation")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while(i < propertyNodes.getLength()){
			Node currentPropertyNode = propertyNodes.item(i++);
			if(currentPropertyNode instanceof Element
					&& (currentPropertyNode.getNodeName().equals("participationInTask") || ((Element) currentPropertyNode).getAttribute("propertyId")
							.equals("7631795069536317681"))){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						ParticipationInTask curVal;
						try{
							curVal = IntrospectionUtil.newInstance(((Element) currentPropertyValueNode).getAttribute("className"));
						}catch(Exception e){
							curVal = Environment.getInstance().getMetaInfoMap().newInstance(((Element) currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element) currentPropertyValueNode, map);
						this.addToParticipationInTask(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if(currentPropertyNode instanceof Element
					&& (currentPropertyNode.getNodeName().equals("participationInRequest") || ((Element) currentPropertyNode).getAttribute("propertyId")
							.equals("3022263813028286216"))){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						ParticipationInRequest curVal;
						try{
							curVal = IntrospectionUtil.newInstance(((Element) currentPropertyValueNode).getAttribute("className"));
						}catch(Exception e){
							curVal = Environment.getInstance().getMetaInfoMap().newInstance(((Element) currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element) currentPropertyValueNode, map);
						this.addToParticipationInRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	@NumlMetaInfo(uuid = "252060@_Nk_isIobEeCPduia_-NbFw")
	public void claim(){
		generateClaimEvent();
	}
	public void clearParticipationInTask(){
		removeAllFromParticipationInTask(getParticipationInTask());
	}
	public void clearSubRequests(){
		removeAllFromSubRequests(getSubRequests());
	}
	public void completed(){
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
		if(callbackListener != null){
			callbackListener.onAbstractRequestComplete(getReturnInfo(), this);
		}
	}
	public boolean consumeActivateOccurrence(){
		boolean result = false;
		result = super.consumeActivateOccurrence();
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Created){
				Created state = (Created) token.getCurrentExecutionElement();
				if(result == false && state.getCreatedToActive().consumeActivateOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeAddTaskRequestParticipantOccurrence(
			@ParameterMetaInfo(name = "newParticipant",opaeumId = 5015842494571072006l,uuid = "252060@_v52VoY6SEeCrtavWRHwoHg")
			Participant newParticipant,@ParameterMetaInfo(name = "kind",opaeumId = 3546049321002453618l,uuid = "252060@_v52Voo6SEeCrtavWRHwoHg")
			TaskParticipationKind kind){
		boolean result = false;
		return result;
	}
	public boolean consumeClaimOccurrence(){
		boolean result = false;
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Ready){
				Ready state = (Ready) token.getCurrentExecutionElement();
				if(result == false && state.getReadyToReserved().consumeClaimOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeCompleteOccurrence(){
		boolean result = false;
		result = super.consumeCompleteOccurrence();
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof InProgress){
				InProgress state = (InProgress) token.getCurrentExecutionElement();
				if(result == false && state.getTransition5().consumeCompleteOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeDelegateOccurrence(@ParameterMetaInfo(name = "delegate",opaeumId = 8205705053048523991l,uuid = "252060@_TsfTcJTyEeChgI0v02SJHQ")
	IBusinessRole delegate){
		boolean result = false;
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Active){
				Active state = (Active) token.getCurrentExecutionElement();
				if(result == false && state.getActiveToReserved().consumeDelegateOccurrence(delegate)){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeForwardOccurrence(@ParameterMetaInfo(name = "toPerson",opaeumId = 3350895467208403091l,uuid = "252060@_kN7FcJTyEeChgI0v02SJHQ")
	IBusinessRole toPerson){
		boolean result = false;
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Active){
				Active state = (Active) token.getCurrentExecutionElement();
				if(result == false && state.getActiveToReady().consumeForwardOccurrence(toPerson)){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeRemoveTaskRequestParticipantOccurrence(
			@ParameterMetaInfo(name = "participant",opaeumId = 7590474051790031114l,uuid = "252060@_wuzAoY6SEeCrtavWRHwoHg")
			Participant participant,@ParameterMetaInfo(name = "kind",opaeumId = 6120680878221412726l,uuid = "252060@_wuzAoo6SEeCrtavWRHwoHg")
			TaskParticipationKind kind){
		boolean result = false;
		return result;
	}
	public boolean consumeResumeOccurrence(){
		boolean result = false;
		result = super.consumeResumeOccurrence();
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Suspended){
				Suspended state = (Suspended) token.getCurrentExecutionElement();
				if(result == false && state.getSuspendedToActive().consumeResumeOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeRevokeOccurrence(){
		boolean result = false;
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Reserved){
				Reserved state = (Reserved) token.getCurrentExecutionElement();
				if(result == false && state.getReservedToReady().consumeRevokeOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeSkipOccurrence(){
		boolean result = false;
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Active){
				Active state = (Active) token.getCurrentExecutionElement();
				if(result == false && state.getAtiveToObsolete().consumeSkipOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeStartOccurrence(){
		boolean result = false;
		result = super.consumeStartOccurrence();
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Reserved){
				Reserved state = (Reserved) token.getCurrentExecutionElement();
				if(result == false && state.getReservedToInProgress().consumeStartOccurrence()){
					result = true;
					break;
				}
			}
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Active){
				Active state = (Active) token.getCurrentExecutionElement();
				if(result == false && state.getActiveToInProgress().consumeStartOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeStopOccurrence(){
		boolean result = false;
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof InProgress){
				InProgress state = (InProgress) token.getCurrentExecutionElement();
				if(result == false && state.getInProgressToReserved().consumeStopOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public boolean consumeSuspendOccurrence(){
		boolean result = false;
		result = super.consumeSuspendOccurrence();
		for(IToken token:getTokens()){
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof InProgress){
				InProgress state = (InProgress) token.getCurrentExecutionElement();
				if(result == false && state.getInProgressToSuspended().consumeSuspendOccurrence()){
					result = true;
					break;
				}
			}
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Reserved){
				Reserved state = (Reserved) token.getCurrentExecutionElement();
				if(result == false && state.getReservedToSuspended().consumeSuspendOccurrence()){
					result = true;
					break;
				}
			}
			if(result == false && token.isActive() && token.getCurrentExecutionElement() instanceof Ready){
				Ready state = (Ready) token.getCurrentExecutionElement();
				if(result == false && state.getReadyToSuspended().consumeSuspendOccurrence()){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	public void copyShallowState(TaskRequest from,TaskRequest to){
		to.setDelegation(from.getDelegation());
	}
	public void copyState(TaskRequest from,TaskRequest to){
		for(ParticipationInTask child:from.getParticipationInTask()){
			to.addToParticipationInTask(child.makeCopy());
		}
		to.setDelegation(from.getDelegation());
		for(ParticipationInRequest child:from.getParticipationInRequest()){
			to.addToParticipationInRequest(child.makeCopy());
		}
	}
	public void createComponents(){
		super.createComponents();
	}
	public ParticipationInTask createParticipationInTask(){
		ParticipationInTask newInstance = new ParticipationInTask();
		newInstance.init((TaskRequest) this);
		return newInstance;
	}
	public IToken createToken(IToken smToken){
		TaskRequestToken result = new TaskRequestToken((TaskRequestToken) smToken);
		tokens.add(result);
		result.setBehaviorExecution((TaskRequest) this);
		return result;
	}
	@NumlMetaInfo(uuid = "252060@_0lAQAIoaEeCPduia_-NbFw")
	public void delegate(@ParameterMetaInfo(name = "delegate",opaeumId = 8205705053048523991l,uuid = "252060@_TsfTcJTyEeChgI0v02SJHQ")
	IBusinessRole delegate){
		Participant currentRole = null;
		generateDelegateEvent(delegate);
		if((this.getOwner() == null)){
			this.addTaskRequestParticipant(delegate, TaskParticipationKind.OWNER);
			ITaskObject tgtOnDelegated = this.getTaskObject();
			tgtOnDelegated.onDelegated(currentRole, delegate);
		}else{
			if((!(this.getOwner() == null))){
				this.removeTaskRequestParticipant(delegate, TaskParticipationKind.OWNER);
				this.addTaskRequestParticipant(delegate, TaskParticipationKind.OWNER);
				ITaskObject tgtOnDelegated = this.getTaskObject();
				tgtOnDelegated.onDelegated(currentRole, delegate);
			}
		}
	}
	public boolean equals(Object other){
		if(other instanceof TaskRequest){
			return other == this || ((TaskRequest) other).getUid().equals(this.getUid());
		}
		return false;
	}
	public void execute(){
		setExecutedOn(new Date());
		((TaskRequestRegion) getExecutionElements().get(TaskRequestRegion.ID)).initiate(null);
	}
	@NumlMetaInfo(uuid = "252060@__6uyIIoaEeCPduia_-NbFw")
	public void forward(@ParameterMetaInfo(name = "toPerson",opaeumId = 3350895467208403091l,uuid = "252060@_kN7FcJTyEeChgI0v02SJHQ")
	IBusinessRole toPerson){
		generateForwardEvent(toPerson);
	}
	@NumlMetaInfo(uuid = "252060@_e0G_YK0gEeCwWeEjtrrMeQ")
	public void forward(){
	}
	public void generateActivateEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ActivateHandler3717858776997870408(true)));
	}
	public void generateAddTaskRequestParticipantEvent(
			@ParameterMetaInfo(name = "newParticipant",opaeumId = 5015842494571072006l,uuid = "252060@_v52VoY6SEeCrtavWRHwoHg")
			Participant newParticipant,@ParameterMetaInfo(name = "kind",opaeumId = 3546049321002453618l,uuid = "252060@_v52Voo6SEeCrtavWRHwoHg")
			TaskParticipationKind kind){
	}
	public void generateClaimEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ClaimHandler7608483210400824401(true)));
	}
	public void generateCompleteEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new CompleteHandler20843194362239925(true)));
	}
	public void generateDelegateEvent(@ParameterMetaInfo(name = "delegate",opaeumId = 8205705053048523991l,uuid = "252060@_TsfTcJTyEeChgI0v02SJHQ")
	IBusinessRole delegate){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new DelegateHandler6837298467385087869(delegate, true)));
	}
	public void generateForwardEvent(@ParameterMetaInfo(name = "toPerson",opaeumId = 3350895467208403091l,uuid = "252060@_kN7FcJTyEeChgI0v02SJHQ")
	IBusinessRole toPerson){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ForwardHandler7251280809563715157(toPerson, true)));
	}
	public void generateRemoveTaskRequestParticipantEvent(
			@ParameterMetaInfo(name = "participant",opaeumId = 7590474051790031114l,uuid = "252060@_wuzAoY6SEeCrtavWRHwoHg")
			Participant participant,@ParameterMetaInfo(name = "kind",opaeumId = 6120680878221412726l,uuid = "252060@_wuzAoo6SEeCrtavWRHwoHg")
			TaskParticipationKind kind){
	}
	public void generateResumeEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ResumeHandler286517672851676135(true)));
	}
	public void generateRevokeEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new RevokeHandler7253144136017044923(true)));
	}
	public void generateSkipEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SkipHandler2519565167906952379(true)));
	}
	public void generateStartEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StartHandler2809139800710243133(true)));
	}
	public void generateStopEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new StopHandler8236205458182045891(true)));
	}
	public void generateSuspendEvent(){
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SuspendHandler3547071820132893959(true)));
	}
	public AbstractRequestListener getCallingBehaviorExecution(){
		AbstractRequestListener result = null;
		if(getReturnInfo() != null){
			result = (AbstractRequestListener) getReturnInfo().getBehaviorExecution();
		}
		return result;
	}
	public Set<CancelledEvent> getCancelledEvents(){
		return this.cancelledEvents;
	}
	public Object getCurrentException(){
		return this.currentException;
	}
	@PropertyMetaInfo(constraints = {},isComposite = false,opaeumId = 4580607342736823288l,opposite = "taskRequest",uuid = "252060@_84NrDrRZEeCilvbXE8KmHA")
	@NumlMetaInfo(uuid = "252060@_84NrDrRZEeCilvbXE8KmHA")
	public TaskDelegation getDelegation(){
		TaskDelegation result = this.delegation;
		return result;
	}
	public Date getDeletedOn(){
		return this.deletedOn;
	}
	public Date getExecutedOn(){
		return this.executedOn;
	}
	public Map<String,IExecutionElement> getExecutionElements(){
		Map<String,IExecutionElement> result = executionElements;
		if(executionElements == null){
			result = executionElements = new HashMap<String,org.opaeum.runtime.domain.IExecutionElement>();
			new TaskRequestRegion((TaskRequest) this).linkTransitions();
		}
		return result;
	}
	public String getHistory(){
		return this.History;
	}
	public IProcessStep getInnermostNonParallelStep(){
		IProcessStep result = null;
		for(IToken token:getTokens()){
			if(token.getParentToken() == null){
				return (IProcessStep) token.getInnermostNonParallelToken().getCurrentExecutionElement();
			}
		}
		return result;
	}
	public String getName(){
		return "TaskRequest[" + getId() + "]";
	}
	public Set<OutgoingEvent> getOutgoingEvents(){
		return this.outgoingEvents;
	}
	@PropertyMetaInfo(constraints = {},isComposite = false,opaeumId = 8552480891737957111l,uuid = "252060@_wy5fAKDREeCi16HgBnUGFw")
	@NumlMetaInfo(uuid = "252060@_wy5fAKDREeCi16HgBnUGFw")
	public Participant getOwner(){
		Participant result = any3().getParticipant();
		return result;
	}
	public CompositionNode getOwningObject(){
		return getTaskObject();
	}
	@PropertyMetaInfo(constraints = {},isComposite = true,opaeumId = 7631795069536317681l,opposite = "taskRequest",uuid = "252060@_BB8NEI6VEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid = "252060@_BB8NEI6VEeCne5ArYLDbiA")
	public Set<ParticipationInTask> getParticipationInTask(){
		Set<ParticipationInTask> result = this.participationInTask;
		return result;
	}
	@PropertyMetaInfo(constraints = {},isComposite = false,opaeumId = 3802915038871319282l,uuid = "252060@_sMysAKDQEeCEB8xJMe8jaA")
	@NumlMetaInfo(uuid = "252060@_sMysAKDQEeCEB8xJMe8jaA")
	public Set<Participant> getPotentialOwners(){
		Set<Participant> result = new HashSet<Participant>(Stdlib.collectionAsSet(collect2()));
		return result;
	}
	@PropertyMetaInfo(constraints = {},isComposite = false,opaeumId = 8918582809852333993l,opposite = "request",uuid = "252060@_lEGvZI53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid = "252060@_lEGvZI53EeCfQedkc0TCdA")
	public IRequestObject getRequestObject(){
		IRequestObject result = null;
		if(this.getTaskObject() != null){
			result = this.getTaskObject();
		}
		return result;
	}
	public IToken getReturnInfo(){
		IToken result = this.returnInfo.getValue(persistence);
		return result;
	}
	@PropertyMetaInfo(constraints = {},isComposite = false,opaeumId = 4881745325393372278l,opposite = "parentTask",uuid = "252060@_tog08I29EeCrtavWRHwoHg")
	@NumlMetaInfo(uuid = "252060@_tog08I29EeCrtavWRHwoHg")
	public Set<AbstractRequest> getSubRequests(){
		Set<AbstractRequest> result = this.subRequests;
		return result;
	}
	@PropertyMetaInfo(constraints = {},isComposite = false,opaeumId = 5302390646449487153l,opposite = "taskRequest",uuid = "252060@_I3guVI3pEeCfQedkc0TCdA")
	@NumlMetaInfo(uuid = "252060@_I3guVI3pEeCfQedkc0TCdA")
	public ITaskObject getTaskObject(){
		ITaskObject result = this.taskObject;
		return result;
	}
	public int hashCode(){
		return getUid().hashCode();
	}
	public void init(CompositionNode owner){
		super.init(owner);
		this.z_internalAddToTaskObject((ITaskObject) owner);
		createComponents();
	}
	public boolean isStepActive(Class<? extends IExecutionElement> clss){
		boolean result = false;
		for(IToken token:getTokens()){
			if(clss.isInstance(token.getCurrentExecutionElement())){
				return true;
			}
		}
		return result;
	}
	public TaskRequest makeCopy(){
		TaskRequest result = new TaskRequest();
		copyState((TaskRequest) this, result);
		return result;
	}
	public TaskRequest makeShallowCopy(){
		TaskRequest result = new TaskRequest();
		copyShallowState((TaskRequest) this, result);
		result.setId(this.getId());
		return result;
	}
	public void markDeleted(){
		super.markDeleted();
		if(getTaskObject() != null){
			getTaskObject().z_internalRemoveFromTaskRequest((TaskRequest) this);
		}
		if(getParentTask() != null){
			getParentTask().z_internalRemoveFromSubRequests((TaskRequest) this);
		}
		for(ParticipationInTask child:new ArrayList<ParticipationInTask>(getParticipationInTask())){
			child.markDeleted();
		}
		for(ParticipationInRequest child:new ArrayList<ParticipationInRequest>(getParticipationInRequest())){
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	static public void mockAllInstances(Set<TaskRequest> newMocks){
		mockedAllInstances = newMocks;
	}
	public void populateReferencesFromXml(Element xml,Map<String,Object> map){
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while(i < propertyNodes.getLength()){
			Node currentPropertyNode = propertyNodes.item(i++);
			if(currentPropertyNode instanceof Element
					&& (currentPropertyNode.getNodeName().equals("participationInTask") || ((Element) currentPropertyNode).getAttribute("propertyId")
							.equals("7631795069536317681"))){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						((ParticipationInTask) map.get(((Element) currentPropertyValueNode).getAttribute("uid")))
								.populateReferencesFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element
					&& (currentPropertyNode.getNodeName().equals("participationInRequest") || ((Element) currentPropertyNode).getAttribute("propertyId")
							.equals("3022263813028286216"))){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						((ParticipationInRequest) map.get(((Element) currentPropertyValueNode).getAttribute("uid")))
								.populateReferencesFromXml((Element) currentPropertyValueNode, map);
					}
				}
			}
			if(currentPropertyNode instanceof Element
					&& (currentPropertyNode.getNodeName().equals("parentTask") || ((Element) currentPropertyNode).getAttribute("propertyId")
							.equals("5501213897228443240"))){
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while(j < propertyValueNodes.getLength()){
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if(currentPropertyValueNode instanceof Element){
						setParentTask((TaskRequest) map.get(((Element) currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	public void propagateException(Object exception){
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
		if(callbackListener == null){
		}else{
			callbackListener.onAbstractRequestUnhandledException(getReturnInfo(), exception, this);
		}
	}
	public void removeAllFromParticipationInTask(Set<ParticipationInTask> participationInTask){
		Set<ParticipationInTask> tmp = new HashSet<ParticipationInTask>(participationInTask);
		for(ParticipationInTask o:tmp){
			removeFromParticipationInTask(o);
		}
	}
	public void removeAllFromSubRequests(Set<AbstractRequest> subRequests){
		Set<AbstractRequest> tmp = new HashSet<AbstractRequest>(subRequests);
		for(AbstractRequest o:tmp){
			removeFromSubRequests(o);
		}
	}
	public void removeFromOwningObject(){
		this.markDeleted();
	}
	public void removeFromParticipationInTask(ParticipationInTask participationInTask){
		if(participationInTask != null){
			participationInTask.z_internalRemoveFromTaskRequest((TaskRequest) this);
			z_internalRemoveFromParticipationInTask(participationInTask);
		}
	}
	public void removeFromSubRequests(AbstractRequest subRequests){
		if(subRequests != null){
			subRequests.z_internalRemoveFromParentTask((TaskRequest) this);
			z_internalRemoveFromSubRequests(subRequests);
		}
	}
	@NumlMetaInfo(uuid = "252060@_wuzAoI6SEeCrtavWRHwoHg")
	public void removeTaskRequestParticipant(@ParameterMetaInfo(name = "participant",opaeumId = 7590474051790031114l,uuid = "252060@_wuzAoY6SEeCrtavWRHwoHg")
	Participant participant,@ParameterMetaInfo(name = "kind",opaeumId = 6120680878221412726l,uuid = "252060@_wuzAoo6SEeCrtavWRHwoHg")
	TaskParticipationKind kind){
		generateRemoveTaskRequestParticipantEvent(participant, kind);
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for(ParticipationInTask p:this.getParticipationInTask()){
			if((p.getParticipant().equals(participant) && (p.getKind().equals(kind)))){
				result.add(p);
			}
		}
		TaskRequest tgtRemoveParticipation = (TaskRequest) this;
		tgtRemoveParticipation.removeAllFromParticipationInTask(result);
	}
	public void removeToken(IToken smToken){
		tokens.remove((TaskRequestToken) smToken);
	}
	@NumlMetaInfo(uuid = "252060@_LlMOIIobEeCPduia_-NbFw")
	public void revoke(){
		generateRevokeEvent();
	}
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents){
		this.cancelledEvents = cancelledEvents;
	}
	public void setCurrentException(Object currentException){
		this.currentException = currentException;
	}
	public void setDelegation(TaskDelegation delegation){
		this.z_internalAddToDelegation(delegation);
	}
	public void setDeletedOn(Date deletedOn){
		this.deletedOn = deletedOn;
		super.setDeletedOn(deletedOn);
	}
	public void setExecutedOn(Date executedOn){
		this.executedOn = executedOn;
	}
	public void setHistory(String History){
		this.History = History;
	}
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents){
		this.outgoingEvents = outgoingEvents;
	}
	public void setParticipationInTask(Set<ParticipationInTask> participationInTask){
		this.clearParticipationInTask();
		this.addAllToParticipationInTask(participationInTask);
	}
	public void setReturnInfo(IToken token){
		this.returnInfo.setValue(token);
	}
	public void setSubRequests(Set<AbstractRequest> subRequests){
		this.clearSubRequests();
		this.addAllToSubRequests(subRequests);
	}
	public void setTaskObject(ITaskObject taskObject){
		ITaskObject oldValue = this.getTaskObject();
		if(oldValue == null){
			if(taskObject != null){
				TaskRequest oldOther = (TaskRequest) taskObject.getTaskRequest();
				taskObject.z_internalRemoveFromTaskRequest(oldOther);
				if(oldOther != null){
					oldOther.z_internalRemoveFromTaskObject(taskObject);
				}
				taskObject.z_internalAddToTaskRequest((TaskRequest) this);
			}
			this.z_internalAddToTaskObject(taskObject);
		}else{
			if(!oldValue.equals(taskObject)){
				oldValue.z_internalRemoveFromTaskRequest((TaskRequest) this);
				z_internalRemoveFromTaskObject(oldValue);
				if(taskObject != null){
					TaskRequest oldOther = (TaskRequest) taskObject.getTaskRequest();
					taskObject.z_internalRemoveFromTaskRequest(oldOther);
					if(oldOther != null){
						oldOther.z_internalRemoveFromTaskObject(taskObject);
					}
					taskObject.z_internalAddToTaskRequest((TaskRequest) this);
				}
				this.z_internalAddToTaskObject(taskObject);
			}
		}
	}
	@NumlMetaInfo(uuid = "252060@_1gF8AKDTEeCi16HgBnUGFw")
	public void skip(){
		generateSkipEvent();
	}
	@NumlMetaInfo(uuid = "252060@_GRVH0IobEeCPduia_-NbFw")
	public void stop(){
		generateStopEvent();
	}
	public String toXmlReferenceString(){
		return "<TaskRequest uid=\"" + getUid() + "\"/>";
	}
	public String toXmlString(){
		StringBuilder sb = new StringBuilder();
		sb.append("<TaskRequest ");
		sb.append("classUuid=\"252060@_zFmsEIoVEeCLqpffVZYAlw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.TaskRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if(getDelegation() != null){
			sb.append("delegation=\"" + getDelegation().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n<participationInTask propertyId=\"7631795069536317681\">");
		for(ParticipationInTask participationInTask:getParticipationInTask()){
			sb.append("\n" + participationInTask.toXmlString());
		}
		sb.append("\n</participationInTask>");
		sb.append("\n<participationInRequest propertyId=\"3022263813028286216\">");
		for(ParticipationInRequest participationInRequest:getParticipationInRequest()){
			sb.append("\n" + participationInRequest.toXmlString());
		}
		sb.append("\n</participationInRequest>");
		if(getParentTask() == null){
			sb.append("\n<parentTask/>");
		}else{
			sb.append("\n<parentTask propertyId=\"5501213897228443240\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</parentTask>");
		}
		sb.append("\n</TaskRequest>");
		return sb.toString();
	}
	public void z_internalAddToDelegation(TaskDelegation val){
		this.delegation = val;
	}
	public void z_internalAddToParticipationInTask(ParticipationInTask val){
		this.participationInTask.add(val);
	}
	public void z_internalAddToSubRequests(AbstractRequest val){
		this.subRequests.add(val);
	}
	public void z_internalAddToTaskObject(ITaskObject val){
		this.taskObject = val;
	}
	public void z_internalRemoveFromDelegation(TaskDelegation val){
		if(getDelegation() != null && val != null && val.equals(getDelegation())){
			this.delegation = null;
			this.delegation = null;
		}
	}
	public void z_internalRemoveFromParticipationInTask(ParticipationInTask val){
		this.participationInTask.remove(val);
	}
	public void z_internalRemoveFromSubRequests(AbstractRequest val){
		this.subRequests.remove(val);
	}
	public void z_internalRemoveFromTaskObject(ITaskObject val){
		if(getTaskObject() != null && val != null && val.equals(getTaskObject())){
			this.taskObject = null;
			this.taskObject = null;
		}
	}
	/**
	 * Implements self.participationInTask->any(p : ParticipationInTask |
	 * p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::owner))
	 */
	private ParticipationInTask any3(){
		ParticipationInTask result = null;
		for(ParticipationInTask p:this.getParticipationInTask()){
			if((p.getKind().equals(TaskParticipationKind.OWNER))){
				return p;
			}
		}
		return result;
	}
	/**
	 * Implements self.participationInTask->select(p : ParticipationInTask |
	 * p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::potentialOwner))->collect(p : ParticipationInTask | p.participant)
	 */
	private Collection<Participant> collect2(){
		Collection<Participant> result = new ArrayList<Participant>();
		for(ParticipationInTask p:select1()){
			Participant bodyExpResult = p.getParticipant();
			if(bodyExpResult != null)
				result.add(bodyExpResult);
		}
		return result;
	}
	/**
	 * Implements self.participationInTask->select(p : ParticipationInTask |
	 * p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::potentialOwner))
	 */
	private Set<ParticipationInTask> select1(){
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for(ParticipationInTask p:this.getParticipationInTask()){
			if((p.getKind().equals(TaskParticipationKind.POTENTIALOWNER))){
				result.add(p);
			}
		}
		return result;
	}
}