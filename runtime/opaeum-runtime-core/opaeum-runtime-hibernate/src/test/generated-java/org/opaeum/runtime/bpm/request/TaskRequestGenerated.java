package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.opaeum.hibernate.domain.Duration;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.IParticipant;
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
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IObservation;
import org.opaeum.runtime.domain.IObserver;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TaskDelegation;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.DurationStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_zFmsEIoVEeCLqpffVZYAlw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@MappedSuperclass
@DiscriminatorValue(	"task_request")
public class TaskRequestGenerated extends AbstractRequest implements IStateMachineExecution, IPersistentObject, IObserver, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	private String History;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="completion_date")
	protected Date completionDate;
	@Transient
	protected Object currentException;
	@Embedded
	@Column(name="delay_before_progress")
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="delay_before_progress_quantity"),name="quantity"),
		@AttributeOverride(column=
			@Column(name="delay_before_progress_from_date"),name="fromDate"),
		@AttributeOverride(column=
			@Column(name="delay_before_progress_to_date"),name="toDate"),
		@AttributeOverride(column=
			@Column(name="delay_before_progress_time_unit"),name="timeUnit")})
	protected Duration delayBeforeProgress;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="delegation",nullable=true)
	protected TaskDelegation delegation;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	private Map<String, IExecutionElement> executionElements;
	static private Set<? extends TaskRequest> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="taskRequest",targetEntity=ParticipationInTask.class)
	protected Set<ParticipationInTask> participationInTask = new HashSet<ParticipationInTask>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="return_info"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="return_info_type"),name="classIdentifier")})
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 8501108023512204129l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="parentTask",targetEntity=AbstractRequest.class)
	protected Set<AbstractRequest> subRequests = new HashSet<AbstractRequest>();

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public TaskRequestGenerated(IRequestObject owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for TaskRequest
	 */
	public TaskRequestGenerated() {
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
	public void addTaskRequestParticipant(@ParameterMetaInfo(name="newParticipant",opaeumId=5015842494571072006l,uuid="252060@_v52VoY6SEeCrtavWRHwoHg") IParticipant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=3546049321002453618l,uuid="252060@_v52Voo6SEeCrtavWRHwoHg") TaskParticipationKind kind) {
		generateAddTaskRequestParticipantEvent(newParticipant,kind);
	}
	
	/** Call (TaskRequest)this method when you want to attach (TaskRequest)this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getRequestObject().z_internalAddToRequest((TaskRequest)this);
	}
	
	public void addToParticipationInTask(ParticipationInTask participationInTask) {
		if ( participationInTask!=null ) {
			if ( participationInTask.getTaskRequest()!=null ) {
				participationInTask.getTaskRequest().removeFromParticipationInTask(participationInTask);
			}
			participationInTask.z_internalAddToTaskRequest((TaskRequest)this);
		}
		z_internalAddToParticipationInTask(participationInTask);
	}
	
	public void addToSubRequests(AbstractRequest subRequests) {
		if ( subRequests!=null ) {
			if ( subRequests.getParentTask()!=null ) {
				subRequests.getParentTask().removeFromSubRequests(subRequests);
			}
			subRequests.z_internalAddToParentTask((TaskRequest)this);
		}
		z_internalAddToSubRequests(subRequests);
	}
	
	static public Set<? extends TaskRequest> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.TaskRequest.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("delegation").length()>0 ) {
			setDelegation(TaskDelegation.valueOf(xml.getAttribute("delegation")));
		}
		if ( xml.getAttribute("delayBeforeProgress").length()>0 ) {
			setDelayBeforeProgress(OpaeumLibraryForBPMFormatter.getInstance().parseDuration(xml.getAttribute("delayBeforeProgress")));
		}
		if ( xml.getAttribute("completionDate").length()>0 ) {
			setCompletionDate(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("completionDate")));
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToParticipationInRequest(curVal);
						curVal.z_internalAddToRequest((TaskRequest)this);
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToParticipationInTask(curVal);
						curVal.z_internalAddToTaskRequest((TaskRequest)this);
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
	
	public void clearParticipationInTask() {
		Set<ParticipationInTask> tmp = new HashSet<ParticipationInTask>(getParticipationInTask());
		for ( ParticipationInTask o : tmp ) {
			removeFromParticipationInTask(o);
		}
	}
	
	public void clearSubRequests() {
		Set<AbstractRequest> tmp = new HashSet<AbstractRequest>(getSubRequests());
		for ( AbstractRequest o : tmp ) {
			removeFromSubRequests(o);
		}
	}
	
	public void complete() {
		TaskRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onAbstractRequestComplete(getReturnInfo(),(TaskRequest)this);
		}
	}
	
	public boolean consumeActivateOccurrence() {
		boolean result = false;
		result=super.consumeActivateOccurrence();
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Created ) {
				Created state = (Created)token.getCurrentExecutionElement();
				if ( result==false &&  state.getCreatedToActive().consumeActivateOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeAddTaskRequestParticipantOccurrence(@ParameterMetaInfo(name="newParticipant",opaeumId=5015842494571072006l,uuid="252060@_v52VoY6SEeCrtavWRHwoHg") IParticipant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=3546049321002453618l,uuid="252060@_v52Voo6SEeCrtavWRHwoHg") TaskParticipationKind kind) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeClaimOccurrence() {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Ready ) {
				Ready state = (Ready)token.getCurrentExecutionElement();
				if ( result==false &&  state.getReadyToReserved().consumeClaimOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeCompleteOccurrence() {
		boolean result = false;
		result=super.consumeCompleteOccurrence();
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof InProgress ) {
				InProgress state = (InProgress)token.getCurrentExecutionElement();
				if ( result==false &&  state.getTransition5().consumeCompleteOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeDelegateOccurrence(@ParameterMetaInfo(name="delegate",opaeumId=8205705053048523991l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ") IBusinessRole delegate) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Active ) {
				Active state = (Active)token.getCurrentExecutionElement();
				if ( result==false &&  state.getActiveToReserved().consumeDelegateOccurrence(delegate) ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeForwardOccurrence(@ParameterMetaInfo(name="toPerson",opaeumId=3350895467208403091l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ") IBusinessRole toPerson) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Active ) {
				Active state = (Active)token.getCurrentExecutionElement();
				if ( result==false &&  state.getActiveToReady().consumeForwardOccurrence(toPerson) ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeRemoveTaskRequestParticipantOccurrence(@ParameterMetaInfo(name="participant",opaeumId=7590474051790031114l,uuid="252060@_wuzAoY6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=6120680878221412726l,uuid="252060@_wuzAoo6SEeCrtavWRHwoHg") TaskParticipationKind kind) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeResumeOccurrence() {
		boolean result = false;
		result=super.consumeResumeOccurrence();
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Suspended ) {
				Suspended state = (Suspended)token.getCurrentExecutionElement();
				if ( result==false &&  state.getSuspendedToActive().consumeResumeOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeRevokeOccurrence() {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Reserved ) {
				Reserved state = (Reserved)token.getCurrentExecutionElement();
				if ( result==false &&  state.getReservedToReady().consumeRevokeOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeSetPotentialOwnersOccurrence(@ParameterMetaInfo(name="po",opaeumId=8413390915277987936l,uuid="252060@_etZooOj8EeGqMbZBSj5Rng") Collection<IBusinessRole> po) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeSkipOccurrence() {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Active ) {
				Active state = (Active)token.getCurrentExecutionElement();
				if ( result==false &&  state.getAtiveToObsolete().consumeSkipOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeStartOccurrence() {
		boolean result = false;
		result=super.consumeStartOccurrence();
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Reserved ) {
				Reserved state = (Reserved)token.getCurrentExecutionElement();
				if ( result==false &&  state.getReservedToInProgress().consumeStartOccurrence() ) {
					result=true;
					break;
				}
			}
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Active ) {
				Active state = (Active)token.getCurrentExecutionElement();
				if ( result==false &&  state.getActiveToInProgress().consumeStartOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeStopOccurrence() {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof InProgress ) {
				InProgress state = (InProgress)token.getCurrentExecutionElement();
				if ( result==false &&  state.getInProgressToReserved().consumeStopOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeSuspendOccurrence() {
		boolean result = false;
		result=super.consumeSuspendOccurrence();
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Ready ) {
				Ready state = (Ready)token.getCurrentExecutionElement();
				if ( result==false &&  state.getReadyToSuspended().consumeSuspendOccurrence() ) {
					result=true;
					break;
				}
			}
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof InProgress ) {
				InProgress state = (InProgress)token.getCurrentExecutionElement();
				if ( result==false &&  state.getInProgressToSuspended().consumeSuspendOccurrence() ) {
					result=true;
					break;
				}
			}
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Reserved ) {
				Reserved state = (Reserved)token.getCurrentExecutionElement();
				if ( result==false &&  state.getReservedToSuspended().consumeSuspendOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public void copyShallowState(TaskRequest from, TaskRequest to) {
		to.setDelegation(from.getDelegation());
		to.setDelayBeforeProgress(from.getDelayBeforeProgress());
		to.setCompletionDate(from.getCompletionDate());
	}
	
	public void copyState(TaskRequest from, TaskRequest to) {
		for ( ParticipationInRequest child : from.getParticipationInRequest() ) {
			to.addToParticipationInRequest(child.makeCopy());
		}
		for ( ParticipationInTask child : from.getParticipationInTask() ) {
			to.addToParticipationInTask(child.makeCopy());
		}
		to.setDelegation(from.getDelegation());
		to.setDelayBeforeProgress(from.getDelayBeforeProgress());
		to.setCompletionDate(from.getCompletionDate());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ParticipationInTask createParticipationInTask() {
		ParticipationInTask newInstance= new ParticipationInTask();
		newInstance.init((TaskRequest)this);
		return newInstance;
	}
	
	public IToken createToken(IToken smToken) {
		TaskRequestToken result = new TaskRequestToken((TaskRequestToken)smToken);
		tokens.add(result);
		result.setBehaviorExecution((TaskRequest)this);
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_0lAQAIoaEeCPduia_-NbFw")
	public void delegate(@ParameterMetaInfo(name="delegate",opaeumId=8205705053048523991l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ") IBusinessRole delegate) {
		generateDelegateEvent(delegate);
	}
	
	public boolean equals(Object other) {
		if ( other instanceof TaskRequest ) {
			return other==(TaskRequest)this || ((TaskRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void evaluatePostconditions() {
		super.evaluatePostconditions();
	}
	
	public void evaluatePreconditions() {
		super.evaluatePreconditions();
	}
	
	public void execute() {
		super.execute();
		((TaskRequestRegion)getExecutionElements().get(TaskRequestRegion.ID)).initiate(null);
	}
	
	@NumlMetaInfo(uuid="252060@__6uyIIoaEeCPduia_-NbFw")
	public void forward(@ParameterMetaInfo(name="toPerson",opaeumId=3350895467208403091l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ") IBusinessRole toPerson) {
		generateForwardEvent(toPerson);
	}
	
	public void generateActivateEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new ActivateHandler3717858776997870408(true)));
	}
	
	public void generateAddTaskRequestParticipantEvent(@ParameterMetaInfo(name="newParticipant",opaeumId=5015842494571072006l,uuid="252060@_v52VoY6SEeCrtavWRHwoHg") IParticipant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=3546049321002453618l,uuid="252060@_v52Voo6SEeCrtavWRHwoHg") TaskParticipationKind kind) {
	}
	
	public void generateClaimEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new ClaimHandler7608483210400824401(true)));
	}
	
	public void generateCompleteEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new CompleteHandler20843194362239925(true)));
	}
	
	public void generateDelegateEvent(@ParameterMetaInfo(name="delegate",opaeumId=8205705053048523991l,uuid="252060@_TsfTcJTyEeChgI0v02SJHQ") IBusinessRole delegate) {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new DelegateHandler6837298467385087869(delegate,true)));
	}
	
	public void generateForwardEvent(@ParameterMetaInfo(name="toPerson",opaeumId=3350895467208403091l,uuid="252060@_kN7FcJTyEeChgI0v02SJHQ") IBusinessRole toPerson) {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new ForwardHandler7251280809563715157(toPerson,true)));
	}
	
	public void generateRemoveTaskRequestParticipantEvent(@ParameterMetaInfo(name="participant",opaeumId=7590474051790031114l,uuid="252060@_wuzAoY6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=6120680878221412726l,uuid="252060@_wuzAoo6SEeCrtavWRHwoHg") TaskParticipationKind kind) {
	}
	
	public void generateResumeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new ResumeHandler286517672851676135(true)));
	}
	
	public void generateRevokeEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new RevokeHandler7253144136017044923(true)));
	}
	
	public void generateSetPotentialOwnersEvent(@ParameterMetaInfo(name="po",opaeumId=8413390915277987936l,uuid="252060@_etZooOj8EeGqMbZBSj5Rng") Collection<IBusinessRole> po) {
	}
	
	public void generateSkipEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new SkipHandler2519565167906952379(true)));
	}
	
	public void generateStartEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new StartHandler2809139800710243133(true)));
	}
	
	public void generateStopEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new StopHandler8236205458182045891(true)));
	}
	
	public void generateSuspendEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent((TaskRequest)this, new SuspendHandler3547071820132893959(true)));
	}
	
	public TaskRequestListener getCallingBehaviorExecution() {
		TaskRequestListener result = null;
		if ( getReturnInfo()!=null  ) {
			result=(TaskRequestListener)getReturnInfo().getBehaviorExecution();
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2073509684791310533l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@__jbNsPaVEeGzc-zRlu1Zfg")
	@NumlMetaInfo(uuid="252060@__jbNsPaVEeGzc-zRlu1Zfg@OBS")
	public Date getCompletionDate() {
		Date result = this.completionDate;
		
		return result;
	}
	
	public Object getCurrentException() {
		return this.currentException;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5251963287403689722l,strategyFactory=DurationStrategyFactory.class,uuid="252060@_IvOywPaSEeGIlLxE5lYjPg")
	@NumlMetaInfo(uuid="252060@_IvOywPaSEeGIlLxE5lYjPg@OBS")
	public Duration getDelayBeforeProgress() {
		Duration result = this.delayBeforeProgress;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4580607342736823288l,opposite="taskRequest",uuid="252060@_84NrDrRZEeCilvbXE8KmHA")
	@NumlMetaInfo(uuid="252060@_84NrDrRZEeCilvbXE8KmHA")
	public TaskDelegation getDelegation() {
		TaskDelegation result = this.delegation;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Map<String, IExecutionElement> getExecutionElements() {
		Map result = executionElements;
		if ( executionElements==null ) {
			Set<RegionActivation> regions = new HashSet<RegionActivation>();
			result=executionElements=new HashMap<String,org.opaeum.runtime.domain.IExecutionElement>();
			regions.add(new TaskRequestRegion<TaskRequest>((TaskRequest)this));
			for ( RegionActivation ra : regions ) {
				ra.linkTransitions();
			}
			registerObservations(executionElements);
			if ( getOwningObject() instanceof IObserver ) {
				((IObserver)getOwningObject()).registerObservations(executionElements);
			}
		}
		return result;
	}
	
	public String getHistory() {
		return this.History;
	}
	
	public IProcessStep getInnermostNonParallelStep() {
		IProcessStep result = null;
		for ( IToken token : getTokens() ) {
			if ( token.getParentToken()==null ) {
				return (IProcessStep)token.getInnermostNonParallelToken().getCurrentExecutionElement();
			}
		}
		return result;
	}
	
	public String getName() {
		return "TaskRequest["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8552480891737957111l,uuid="252060@_wy5fAKDREeCi16HgBnUGFw")
	@NumlMetaInfo(uuid="252060@_wy5fAKDREeCi16HgBnUGFw")
	public IParticipant getOwner() {
		IParticipant result = any5();
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getRequestObject();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7631795069536317681l,opposite="taskRequest",uuid="252060@_BB8NEI6VEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid="252060@_BB8NEI6VEeCne5ArYLDbiA")
	public Set<ParticipationInTask> getParticipationInTask() {
		Set result = this.participationInTask;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3802915038871319282l,uuid="252060@_sMysAKDQEeCEB8xJMe8jaA")
	@NumlMetaInfo(uuid="252060@_sMysAKDQEeCEB8xJMe8jaA")
	public Set<IParticipant> getPotentialOwners() {
		Set result = new HashSet<IParticipant>(Stdlib.collectionAsSet(collect2()));
		
		return result;
	}
	
	public IToken<?> getReturnInfo() {
		IToken result = null;
		if ( this.returnInfo==null ) {
			this.returnInfo=new ReturnInfo();
		}
		result=this.returnInfo.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4881745325393372278l,opposite="parentTask",uuid="252060@_tog08I29EeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_tog08I29EeCrtavWRHwoHg")
	public Set<AbstractRequest> getSubRequests() {
		Set result = this.subRequests;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2168494812173334099l,uuid="252060@_sLVasPWwEeG3AqMTRXQUEA")
	@NumlMetaInfo(uuid="252060@_sLVasPWwEeG3AqMTRXQUEA")
	public ITaskObject getTaskObject() {
		ITaskObject result = ((ITaskObject) this.getRequestObject());
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToRequestObject((IRequestObject)owner);
	}
	
	public boolean isStepActive(Class<? extends IExecutionElement> clss) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( clss.isInstance(token.getCurrentExecutionElement()) ) {
				return true;
			}
		}
		return result;
	}
	
	public TaskRequest makeCopy() {
		TaskRequest result = new TaskRequest();
		copyState((TaskRequest)this,result);
		return result;
	}
	
	public TaskRequest makeShallowCopy() {
		TaskRequest result = new TaskRequest();
		copyShallowState((TaskRequest)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		removeAllFromSubRequests(getSubRequests());
		if ( getRequestObject()!=null ) {
			getRequestObject().z_internalRemoveFromRequest((TaskRequest)this);
		}
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests((TaskRequest)this);
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationInRequest()) ) {
			child.markDeleted();
		}
		for ( ParticipationInTask child : new ArrayList<ParticipationInTask>(getParticipationInTask()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
		mockedAllInstances=newMocks;
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("parentTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5501213897228443240")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TaskRequest parentTask = (TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( parentTask!=null ) {
							z_internalAddToParentTask(parentTask);
							parentTask.z_internalAddToSubRequests((TaskRequest)this);
						}
					}
				}
			}
		}
	}
	
	public void propagateException(Object exception) {
		TaskRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onAbstractRequestUnhandledException(getReturnInfo(),exception, (TaskRequest)this);
		}
	}
	
	public void registerObservations(Map<String, IExecutionElement> elements) {
		if ( elements.containsKey(org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Active.ID) ) {
			IObservation observation = new org.opaeum.runtime.domain.IObservation(){	
					public void onEntry(IExecutionElement<?> element) {
						if ( getDelayBeforeProgress()==null ) {
							setDelayBeforeProgress(new Duration(BusinessTimeUnit.BUSINESSDAY));
						}
						getDelayBeforeProgress().fromEventOccurred(true);
					}
					
					public void onExit(IExecutionElement<?> element) {
					}
				};
			elements.get(org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Active.ID).registerObservation(observation);
		}
		if ( elements.containsKey(org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress.ID) ) {
			IObservation observation = new org.opaeum.runtime.domain.IObservation(){	
					public void onEntry(IExecutionElement<?> element) {
						getDelayBeforeProgress().toEventOccurred(BusinessCalendar.getInstance(),BusinessTimeUnit.BUSINESSDAY,true);
					}
					
					public void onExit(IExecutionElement<?> element) {
					}
				};
			elements.get(org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.active.region1.InProgress.ID).registerObservation(observation);
		}
		if ( elements.containsKey(org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Completed.ID) ) {
			IObservation observation = new org.opaeum.runtime.domain.IObservation(){	
					public void onEntry(IExecutionElement<?> element) {
						setCompletionDate(new Date());
					}
					
					public void onExit(IExecutionElement<?> element) {
					}
				};
			elements.get(org.opaeum.runtime.bpm.request.taskrequest.taskrequestregion.Completed.ID).registerObservation(observation);
		}
	}
	
	public void removeAllFromParticipationInTask(Set<? extends ParticipationInTask> participationInTask) {
		Set<ParticipationInTask> tmp = new HashSet<ParticipationInTask>(participationInTask);
		for ( ParticipationInTask o : tmp ) {
			removeFromParticipationInTask(o);
		}
	}
	
	public void removeAllFromSubRequests(Set<? extends AbstractRequest> subRequests) {
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
			participationInTask.z_internalRemoveFromTaskRequest((TaskRequest)this);
			z_internalRemoveFromParticipationInTask(participationInTask);
			participationInTask.markDeleted();
		}
	}
	
	public void removeFromSubRequests(AbstractRequest subRequests) {
		if ( subRequests!=null ) {
			subRequests.z_internalRemoveFromParentTask((TaskRequest)this);
			z_internalRemoveFromSubRequests(subRequests);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_wuzAoI6SEeCrtavWRHwoHg")
	public void removeTaskRequestParticipant(@ParameterMetaInfo(name="participant",opaeumId=7590474051790031114l,uuid="252060@_wuzAoY6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=6120680878221412726l,uuid="252060@_wuzAoo6SEeCrtavWRHwoHg") TaskParticipationKind kind) {
		generateRemoveTaskRequestParticipantEvent(participant,kind);
	}
	
	public void removeToken(IToken smToken) {
		tokens.remove((TaskRequestToken)smToken);
	}
	
	@NumlMetaInfo(uuid="252060@_LlMOIIobEeCPduia_-NbFw")
	public void revoke() {
		generateRevokeEvent();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCompletionDate(Date completionDate) {
		if ( completionDate == null ) {
			this.z_internalRemoveFromCompletionDate(getCompletionDate());
		} else {
			this.z_internalAddToCompletionDate(completionDate);
		}
	}
	
	public void setCurrentException(Object currentException) {
		this.currentException=currentException;
	}
	
	public void setDelayBeforeProgress(Duration delayBeforeProgress) {
		if ( delayBeforeProgress == null ) {
			this.z_internalRemoveFromDelayBeforeProgress(getDelayBeforeProgress());
		} else {
			this.z_internalAddToDelayBeforeProgress(delayBeforeProgress);
		}
	}
	
	public void setDelegation(TaskDelegation delegation) {
		if ( delegation == null ) {
			this.z_internalRemoveFromDelegation(getDelegation());
		} else {
			this.z_internalAddToDelegation(delegation);
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setHistory(String History) {
		this.History=History;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipationInTask(Set<ParticipationInTask> participationInTask) {
		this.clearParticipationInTask();
		this.addAllToParticipationInTask(participationInTask);
	}
	
	@NumlMetaInfo(uuid="252060@_cQs8sOj8EeGqMbZBSj5Rng")
	public void setPotentialOwners(@ParameterMetaInfo(name="po",opaeumId=8413390915277987936l,uuid="252060@_etZooOj8EeGqMbZBSj5Rng") Collection<IBusinessRole> po) {
		generateSetPotentialOwnersEvent(po);
	}
	
	public void setReturnInfo(IToken token) {
		if ( this.returnInfo==null ) {
			this.returnInfo=new ReturnInfo();
		}
		this.returnInfo.setValue(token);
	}
	
	public void setSubRequests(Set<AbstractRequest> subRequests) {
		this.clearSubRequests();
		this.addAllToSubRequests(subRequests);
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
		if ( getDelayBeforeProgress()!=null ) {
			sb.append("delayBeforeProgress=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDuration(getDelayBeforeProgress())+"\" ");
		}
		if ( getCompletionDate()!=null ) {
			sb.append("completionDate=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getCompletionDate())+"\" ");
		}
		sb.append(">");
		sb.append("\n<participationInRequest propertyId=\"3022263813028286216\">");
		for ( ParticipationInRequest participationInRequest : getParticipationInRequest() ) {
			sb.append("\n" + participationInRequest.toXmlString());
		}
		sb.append("\n</participationInRequest>");
		sb.append("\n<participationInTask propertyId=\"7631795069536317681\">");
		for ( ParticipationInTask participationInTask : getParticipationInTask() ) {
			sb.append("\n" + participationInTask.toXmlString());
		}
		sb.append("\n</participationInTask>");
		if ( getParentTask()==null ) {
			sb.append("\n<parentTask/>");
		} else {
			sb.append("\n<parentTask propertyId=\"5501213897228443240\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</parentTask>");
		}
		sb.append("\n</TaskRequest>");
		return sb.toString();
	}
	
	public void z_internalAddToCompletionDate(Date completionDate) {
		if ( completionDate.equals(this.completionDate) ) {
			return;
		}
		this.completionDate=completionDate;
	}
	
	public void z_internalAddToDelayBeforeProgress(Duration delayBeforeProgress) {
		if ( delayBeforeProgress.equals(this.delayBeforeProgress) ) {
			return;
		}
		this.delayBeforeProgress=delayBeforeProgress;
	}
	
	public void z_internalAddToDelegation(TaskDelegation delegation) {
		if ( delegation.equals(this.delegation) ) {
			return;
		}
		this.delegation=delegation;
	}
	
	public void z_internalAddToParticipationInTask(ParticipationInTask participationInTask) {
		if ( this.participationInTask.contains(participationInTask) ) {
			return;
		}
		this.participationInTask.add(participationInTask);
	}
	
	public void z_internalAddToSubRequests(AbstractRequest subRequests) {
		if ( this.subRequests.contains(subRequests) ) {
			return;
		}
		this.subRequests.add(subRequests);
	}
	
	public void z_internalRemoveFromCompletionDate(Date completionDate) {
		if ( getCompletionDate()!=null && completionDate!=null && completionDate.equals(getCompletionDate()) ) {
			this.completionDate=null;
			this.completionDate=null;
		}
	}
	
	public void z_internalRemoveFromDelayBeforeProgress(Duration delayBeforeProgress) {
		if ( getDelayBeforeProgress()!=null && delayBeforeProgress!=null && delayBeforeProgress.equals(getDelayBeforeProgress()) ) {
			this.delayBeforeProgress=null;
			this.delayBeforeProgress=null;
		}
	}
	
	public void z_internalRemoveFromDelegation(TaskDelegation delegation) {
		if ( getDelegation()!=null && delegation!=null && delegation.equals(getDelegation()) ) {
			this.delegation=null;
			this.delegation=null;
		}
	}
	
	public void z_internalRemoveFromParticipationInTask(ParticipationInTask participationInTask) {
		this.participationInTask.remove(participationInTask);
	}
	
	public void z_internalRemoveFromSubRequests(AbstractRequest subRequests) {
		this.subRequests.remove(subRequests);
	}
	
	/** Implements self.participationInTask->select(p : ParticipationInTask | p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::owner))->collect(temp1 : ParticipationInTask | temp1.participant)->any(temp2 : IParticipant | true)
	 */
	private IParticipant any5() {
		IParticipant result = null;
		for ( IParticipant temp2 : collect4() ) {
			if ( true ) {
				return temp2;
			}
		}
		return result;
	}
	
	/** Implements self.participationInTask->select(p : ParticipationInTask | p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::potentialOwner))->collect(p : ParticipationInTask | p.participant)
	 */
	private Collection<IParticipant> collect2() {
		Collection<IParticipant> result = new ArrayList<IParticipant>();
		for ( ParticipationInTask p : select1() ) {
			IParticipant bodyExpResult = p.getParticipant();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationInTask->select(p : ParticipationInTask | p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::owner))->collect(temp1 : ParticipationInTask | temp1.participant)
	 */
	private Collection<IParticipant> collect4() {
		Collection<IParticipant> result = new ArrayList<IParticipant>();
		for ( ParticipationInTask temp1 : select3() ) {
			IParticipant bodyExpResult = temp1.getParticipant();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationInTask->select(p : ParticipationInTask | p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::potentialOwner))
	 */
	private Set<ParticipationInTask> select1() {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask p : this.getParticipationInTask() ) {
			if ( (p.getKind().equals( TaskParticipationKind.POTENTIALOWNER)) ) {
				result.add( p );
			}
		}
		return result;
	}
	
	/** Implements self.participationInTask->select(p : ParticipationInTask | p.kind.=(OpaeumLibraryForBPM::request::TaskParticipationKind::owner))
	 */
	private Set<ParticipationInTask> select3() {
		Set<ParticipationInTask> result = new HashSet<ParticipationInTask>();
		for ( ParticipationInTask p : this.getParticipationInTask() ) {
			if ( (p.getKind().equals( TaskParticipationKind.OWNER)) ) {
				result.add( p );
			}
		}
		return result;
	}

}