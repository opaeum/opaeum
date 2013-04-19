package org.opaeum.runtime.bpm.request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.abstractrequest.Region1;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.FailedConstraintsException;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IObserver;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_6MA8UI2-EeCrtavWRHwoHg")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@MappedSuperclass
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
abstract public class AbstractRequestGenerated implements IStateMachineExecution, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Transient
	protected Object currentException;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="executed_on")
	private Date executedOn;
	@Transient
	private Map<String, IExecutionElement> executionElements;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="abstract_request",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends AbstractRequest> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Index(columnNames="parent_task_id",name="idx_abstract_request_parent_task_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="parent_task_id",nullable=true)
	protected TaskRequest parentTask;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="request",targetEntity=ParticipationInRequest.class)
	protected Set<ParticipationInRequest> participationInRequest = new HashSet<ParticipationInRequest>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport((AbstractRequest)this);
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="request_object"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="request_object_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue requestObject;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="return_info"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="return_info_type"),name="classIdentifier")})
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 8866332427474042484l;
	@OneToMany(mappedBy="behaviorExecution")
	protected Set<AbstractRequestToken> tokens = new HashSet<AbstractRequestToken>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public AbstractRequestGenerated(IRequestObject owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for AbstractRequest
	 */
	public AbstractRequestGenerated() {
	}

	@NumlMetaInfo(uuid="252060@_3USwcKDGEeCv9IRqC7lfYw")
	public void activate() {
		generateActivateEvent();
	}
	
	public void addAllToParticipationInRequest(Set<ParticipationInRequest> participationInRequest) {
		for ( ParticipationInRequest o : participationInRequest ) {
			addToParticipationInRequest(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	@NumlMetaInfo(uuid="252060@_Qo338I6QEeCrtavWRHwoHg")
	public void addRequestParticipant(@ParameterMetaInfo(name="newParticipant",opaeumId=4174829400602250316l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg") IParticipant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=9084738623180185780l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg") RequestParticipationKind kind) {
		ParticipationInRequest participation = null;
		ParticipationInRequest result = null;
		generateAddRequestParticipantEvent(newParticipant,kind);
		result=new ParticipationInRequest();
		participation=result;
		AbstractRequest tgtAddParticipation=(AbstractRequest)this;
		tgtAddParticipation.addToParticipationInRequest(participation);
		Participation tgtSetNewParticipant=participation;
		tgtSetNewParticipant.setParticipant(newParticipant);
		ParticipationInRequest tgtSetKind=participation;
		tgtSetKind.setKind(kind);
	}
	
	/** Call (AbstractRequest)this method when you want to attach (AbstractRequest)this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getRequestObject().z_internalAddToRequest((AbstractRequest)this);
	}
	
	public void addToParticipationInRequest(ParticipationInRequest participationInRequest) {
		if ( participationInRequest!=null ) {
			participationInRequest.z_internalRemoveFromRequest(participationInRequest.getRequest());
			participationInRequest.z_internalAddToRequest((AbstractRequest)this);
			z_internalAddToParticipationInRequest(participationInRequest);
		}
	}
	
	static public Set<? extends AbstractRequest> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.AbstractRequest.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
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
						this.addToParticipationInRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearParticipationInRequest() {
		Set<ParticipationInRequest> tmp = new HashSet<ParticipationInRequest>(getParticipationInRequest());
		for ( ParticipationInRequest o : tmp ) {
			removeFromParticipationInRequest(o);
		}
	}
	
	public void complete() {
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onAbstractRequestComplete(getReturnInfo(),(AbstractRequest)this);
		}
	}
	
	public boolean consumeActivateOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeAddRequestParticipantOccurrence(@ParameterMetaInfo(name="newParticipant",opaeumId=4174829400602250316l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg") IParticipant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=9084738623180185780l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg") RequestParticipationKind kind) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeCompleteOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeRemoveRequestParticipantOccurrence(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeResumeOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeStartOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeSuspendOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(AbstractRequest from, AbstractRequest to) {
	}
	
	public void copyState(AbstractRequest from, AbstractRequest to) {
		for ( ParticipationInRequest child : from.getParticipationInRequest() ) {
			to.addToParticipationInRequest(child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public ParticipationInRequest createParticipationInRequest() {
		ParticipationInRequest newInstance= new ParticipationInRequest();
		newInstance.init((AbstractRequest)this);
		return newInstance;
	}
	
	public IToken createToken(IToken smToken) {
		AbstractRequestToken result = new AbstractRequestToken((AbstractRequestToken)smToken);
		tokens.add(result);
		result.setBehaviorExecution((AbstractRequest)this);
		return result;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof AbstractRequest ) {
			return other==(AbstractRequest)this || ((AbstractRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void evaluatePostconditions() {
	}
	
	public void evaluatePreconditions() {
	}
	
	public void execute() throws FailedConstraintsException {
		evaluatePreconditions();
		setExecutedOn(new Date());
		((Region1)getExecutionElements().get(Region1.ID)).initiate(null);
	}
	
	public void generateActivateEvent() {
	}
	
	public void generateAddRequestParticipantEvent(@ParameterMetaInfo(name="newParticipant",opaeumId=4174829400602250316l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg") IParticipant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=9084738623180185780l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg") RequestParticipationKind kind) {
	}
	
	public void generateCompleteEvent() {
	}
	
	public void generateRemoveRequestParticipantEvent(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
	}
	
	public void generateResumeEvent() {
	}
	
	public void generateStartEvent() {
	}
	
	public void generateSuspendEvent() {
	}
	
	public AbstractRequestListener getCallingBehaviorExecution() {
		AbstractRequestListener result = null;
		if ( getReturnInfo()!=null  ) {
			result=(AbstractRequestListener)getReturnInfo().getBehaviorExecution();
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Object getCurrentException() {
		return this.currentException;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Date getExecutedOn() {
		return this.executedOn;
	}
	
	public Map<String, IExecutionElement> getExecutionElements() {
		Map result = executionElements;
		if ( executionElements==null ) {
			Set<RegionActivation> regions = new HashSet<RegionActivation>();
			result=executionElements=new HashMap<String,org.opaeum.runtime.domain.IExecutionElement>();
			regions.add(new Region1<AbstractRequest>((AbstractRequest)this));
			for ( RegionActivation ra : regions ) {
				ra.linkTransitions();
			}
			if ( getOwningObject() instanceof IObserver ) {
				((IObserver)getOwningObject()).registerObservations(executionElements);
			}
		}
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7529780277726304705l,uuid="252060@_lw17UO6QEeGtc9wBJ1VReA")
	@NumlMetaInfo(uuid="252060@_lw17UO6QEeGtc9wBJ1VReA")
	public IParticipant getInitiator() {
		IParticipant result = any6().getParticipant();
		
		return result;
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
		return "AbstractRequest["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getRequestObject();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,lookupMethod="getParentTaskSourcePopulation",opaeumId=5501213897228443240l,opposite="subRequests",uuid="252060@_towFgY29EeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_towFgY29EeCrtavWRHwoHg")
	public TaskRequest getParentTask() {
		TaskRequest result = this.parentTask;
		
		return result;
	}
	
	public Collection<? extends TaskRequest> getParentTaskSourcePopulation() {
		Collection result = Stdlib.collectionAsSet(collect10());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3022263813028286216l,opposite="request",uuid="252060@_XLHkUI6NEeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_XLHkUI6NEeCrtavWRHwoHg")
	public Set<ParticipationInRequest> getParticipationInRequest() {
		Set result = this.participationInRequest;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8918582809852333993l,opposite="request",uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	public IRequestObject getRequestObject() {
		IRequestObject result = null;
		if ( this.requestObject==null ) {
			this.requestObject=new UiidBasedInterfaceValue();
		}
		result=(IRequestObject)this.requestObject.getValue(persistence);
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7005855959528463153l,uuid="252060@_ROT18O6REeGtc9wBJ1VReA")
	@NumlMetaInfo(uuid="252060@_ROT18O6REeGtc9wBJ1VReA")
	public Set<IParticipant> getStakeholders() {
		Set result = new HashSet<IParticipant>(collect2());
		
		return result;
	}
	
	public Set<IToken> getTokens() {
		Set result = new HashSet<IToken>(tokens);
		
		return result;
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
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
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
	
	abstract public AbstractRequest makeCopy();
	
	abstract public AbstractRequest makeShallowCopy();
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests((AbstractRequest)this);
		}
		if ( getRequestObject()!=null ) {
			getRequestObject().z_internalRemoveFromRequest((AbstractRequest)this);
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationInRequest()) ) {
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
		}
	}
	
	public void propagateException(Object exception) {
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onAbstractRequestUnhandledException(getReturnInfo(),exception, (AbstractRequest)this);
		}
	}
	
	public void removeAllFromParticipationInRequest(Set<ParticipationInRequest> participationInRequest) {
		Set<ParticipationInRequest> tmp = new HashSet<ParticipationInRequest>(participationInRequest);
		for ( ParticipationInRequest o : tmp ) {
			removeFromParticipationInRequest(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipationInRequest(ParticipationInRequest participationInRequest) {
		if ( participationInRequest!=null ) {
			participationInRequest.z_internalRemoveFromRequest((AbstractRequest)this);
			z_internalRemoveFromParticipationInRequest(participationInRequest);
			participationInRequest.markDeleted();
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	@NumlMetaInfo(uuid="252060@_Nl5kQI6SEeCrtavWRHwoHg")
	public void removeRequestParticipant(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
		generateRemoveRequestParticipantEvent(participant,kind);
		AbstractRequest tgtRemoveParticipation=(AbstractRequest)this;
		tgtRemoveParticipation.removeAllFromParticipationInRequest((select7(participant, kind)));
	}
	
	public void removeToken(IToken smToken) {
		tokens.remove((AbstractRequestToken)smToken);
	}
	
	@NumlMetaInfo(uuid="252060@_qwWfEIoaEeCPduia_-NbFw")
	public void resume() {
		generateResumeEvent();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCurrentException(Object currentException) {
		this.currentException=currentException;
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
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParentTask(TaskRequest parentTask) {
		propertyChangeSupport.firePropertyChange("parentTask",getParentTask(),parentTask);
		if ( this.getParentTask()!=null ) {
			this.getParentTask().z_internalRemoveFromSubRequests((AbstractRequest)this);
		}
		if ( parentTask == null ) {
			this.z_internalRemoveFromParentTask(this.getParentTask());
		} else {
			this.z_internalAddToParentTask(parentTask);
		}
		if ( parentTask!=null ) {
			parentTask.z_internalAddToSubRequests((AbstractRequest)this);
		}
	}
	
	public void setParticipationInRequest(Set<ParticipationInRequest> participationInRequest) {
		propertyChangeSupport.firePropertyChange("participationInRequest",getParticipationInRequest(),participationInRequest);
		this.clearParticipationInRequest();
		this.addAllToParticipationInRequest(participationInRequest);
	}
	
	public void setRequestObject(IRequestObject requestObject) {
		IRequestObject oldValue = this.getRequestObject();
		propertyChangeSupport.firePropertyChange("requestObject",getRequestObject(),requestObject);
		if ( oldValue==null ) {
			if ( requestObject!=null ) {
				AbstractRequest oldOther = (AbstractRequest)requestObject.getRequest();
				requestObject.z_internalRemoveFromRequest(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromRequestObject(requestObject);
				}
				requestObject.z_internalAddToRequest((AbstractRequest)this);
			}
			this.z_internalAddToRequestObject(requestObject);
		} else {
			if ( !oldValue.equals(requestObject) ) {
				oldValue.z_internalRemoveFromRequest((AbstractRequest)this);
				z_internalRemoveFromRequestObject(oldValue);
				if ( requestObject!=null ) {
					AbstractRequest oldOther = (AbstractRequest)requestObject.getRequest();
					requestObject.z_internalRemoveFromRequest(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromRequestObject(requestObject);
					}
					requestObject.z_internalAddToRequest((AbstractRequest)this);
				}
				this.z_internalAddToRequestObject(requestObject);
			}
		}
	}
	
	public void setReturnInfo(IToken token) {
		if ( this.returnInfo==null ) {
			this.returnInfo=new ReturnInfo();
		}
		this.returnInfo.setValueInternal(token);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	@NumlMetaInfo(uuid="252060@_-PsMoIoaEeCPduia_-NbFw")
	public void start() {
		generateStartEvent();
	}
	
	@NumlMetaInfo(uuid="252060@_ov5DMIoaEeCPduia_-NbFw")
	public void suspend() {
		generateSuspendEvent();
	}
	
	public String toXmlReferenceString() {
		return "<AbstractRequest uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<AbstractRequest ");
		sb.append("classUuid=\"252060@_6MA8UI2-EeCrtavWRHwoHg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.AbstractRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
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
		sb.append("\n</AbstractRequest>");
		return sb.toString();
	}
	
	public void z_internalAddToParentTask(TaskRequest parentTask) {
		if ( parentTask.equals(getParentTask()) ) {
			return;
		}
		this.parentTask=parentTask;
	}
	
	public void z_internalAddToParticipationInRequest(ParticipationInRequest participationInRequest) {
		if ( getParticipationInRequest().contains(participationInRequest) ) {
			return;
		}
		this.participationInRequest.add(participationInRequest);
	}
	
	public void z_internalAddToRequestObject(IRequestObject requestObject) {
		if ( requestObject.equals(getRequestObject()) ) {
			return;
		}
		if ( this.requestObject==null ) {
			this.requestObject=new UiidBasedInterfaceValue();
		}
		this.requestObject.setValueInternal(requestObject);
	}
	
	public void z_internalRemoveFromParentTask(TaskRequest parentTask) {
		if ( getParentTask()!=null && parentTask!=null && parentTask.equals(getParentTask()) ) {
			this.parentTask=null;
			this.parentTask=null;
		}
	}
	
	public void z_internalRemoveFromParticipationInRequest(ParticipationInRequest participationInRequest) {
		this.participationInRequest.remove(participationInRequest);
	}
	
	public void z_internalRemoveFromRequestObject(IRequestObject requestObject) {
		if ( getRequestObject()!=null && requestObject!=null && requestObject.equals(getRequestObject()) ) {
			this.requestObject.setValueInternal(null);
		}
	}
	
	/** Implements self.participationInRequest->any(p : ParticipationInRequest | p.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))
	 */
	private ParticipationInRequest any4() {
		ParticipationInRequest result = null;
		for ( ParticipationInRequest p : this.getParticipationInRequest() ) {
			if ( (p.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				return p;
			}
		}
		return result;
	}
	
	/** Implements self.participationInRequest->any(p : ParticipationInRequest | p.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))
	 */
	private ParticipationInRequest any6() {
		ParticipationInRequest result = null;
		for ( ParticipationInRequest p : this.getParticipationInRequest() ) {
			if ( (p.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				return p;
			}
		}
		return result;
	}
	
	/** Implements Set {self.requestObject.request}->select(c : AbstractRequest | c.oclIsKindOf(OpaeumLibraryForBPM::request::TaskRequest))->collect(c : AbstractRequest | c.oclAsType(OpaeumLibraryForBPM::request::TaskRequest))
	 */
	private Collection<TaskRequest> collect10() {
		Collection<TaskRequest> result = new ArrayList<TaskRequest>();
		for ( AbstractRequest c : select9() ) {
			TaskRequest bodyExpResult = ((TaskRequest) c);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationInRequest->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::stakeholder))->collect(temp2 : ParticipationInRequest | temp2.participant)
	 */
	private Collection<IParticipant> collect2() {
		Collection<IParticipant> result = new ArrayList<IParticipant>();
		for ( ParticipationInRequest temp2 : select1() ) {
			IParticipant bodyExpResult = temp2.getParticipant();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements Set {self.participationInRequest->any(p : ParticipationInRequest | p.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))}->collect(temp1 : ParticipationInRequest | temp1.participant)
	 */
	private Collection<IParticipant> collect5() {
		Collection<IParticipant> result = new ArrayList<IParticipant>();
		for ( ParticipationInRequest temp1 : collectionLiteral3() ) {
			IParticipant bodyExpResult = temp1.getParticipant();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements Set {self.participationInRequest->any(p : ParticipationInRequest | p.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))}
	 */
	private Set<ParticipationInRequest> collectionLiteral3() {
		Set<ParticipationInRequest> myList = new HashSet<ParticipationInRequest>();
		if ( any4() != null ) {
			myList.add( any4() );
		}
		return myList;
	}
	
	/** Implements Set {self.requestObject.request}
	 */
	private Set<AbstractRequest> collectionLiteral8() {
		Set<AbstractRequest> myList = new HashSet<AbstractRequest>();
		if ( this.getRequestObject().getRequest() != null ) {
			myList.add( this.getRequestObject().getRequest() );
		}
		return myList;
	}
	
	/** Implements self.participationInRequest->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::stakeholder))
	 */
	private Set<ParticipationInRequest> select1() {
		Set<ParticipationInRequest> result = new HashSet<ParticipationInRequest>();
		for ( ParticipationInRequest temp1 : this.getParticipationInRequest() ) {
			if ( (temp1.getKind().equals( RequestParticipationKind.STAKEHOLDER)) ) {
				result.add( temp1 );
			}
		}
		return result;
	}
	
	/** Implements self.participationInRequest->select(p : ParticipationInRequest | p.participant.=(participant).and(p.kind.=(kind)))
	 * 
	 * @param participant 
	 * @param kind 
	 */
	private Set<ParticipationInRequest> select7(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") IParticipant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
		Set<ParticipationInRequest> result = new HashSet<ParticipationInRequest>();
		for ( ParticipationInRequest p : this.getParticipationInRequest() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}
	
	/** Implements Set {self.requestObject.request}->select(c : AbstractRequest | c.oclIsKindOf(OpaeumLibraryForBPM::request::TaskRequest))
	 */
	private Set<AbstractRequest> select9() {
		Set<AbstractRequest> result = new HashSet<AbstractRequest>();
		for ( AbstractRequest c : collectionLiteral8() ) {
			if ( (c instanceof TaskRequest) ) {
				result.add( c );
			}
		}
		return result;
	}

}