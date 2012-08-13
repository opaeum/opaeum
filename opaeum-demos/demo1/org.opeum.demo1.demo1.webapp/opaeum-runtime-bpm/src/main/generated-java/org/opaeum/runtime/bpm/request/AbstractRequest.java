package org.opaeum.runtime.bpm.request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import javax.persistence.Entity;
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

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.request.abstractrequest.Region1;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.IStateMachineExecutionElement;
import org.opaeum.runtime.statemachines.StateMachineToken;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="252060@_6MA8UI2-EeCrtavWRHwoHg")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="abstract_request")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="AbstractRequest")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
abstract public class AbstractRequest implements IStateMachineExecution, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
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
	private Map<String, IStateMachineExecutionElement> executionElements;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<AbstractRequest> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Index(columnNames="parent_task_id",name="idx_abstract_request_parent_task_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="parent_task_id",nullable=true)
	private TaskRequest parentTask;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="request",targetEntity=ParticipationInRequest.class)
	private Set<ParticipationInRequest> participationInRequest = new HashSet<ParticipationInRequest>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="request_object"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="request_object_type"),name="classIdentifier")})
	private InterfaceValue requestObject = new InterfaceValue();
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 8866332427474042484l;
	@OneToMany(mappedBy="stateMachineExecution")
	protected Set<AbstractRequestToken> tokens;
	private String uid;

	/** Default constructor for AbstractRequest
	 */
	public AbstractRequest() {
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
	public void addRequestParticipant(@ParameterMetaInfo(name="newParticipant",opaeumId=4174829400602250316l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg") Participant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=9084738623180185780l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg") RequestParticipationKind kind) {
		ParticipationInRequest participation = null;
		ParticipationInRequest result = null;
		generateAddRequestParticipantEvent(newParticipant,kind);
		result=new ParticipationInRequest();
		participation=result;
		AbstractRequest tgtAddParticipation=this;
		tgtAddParticipation.addToParticipationInRequest(participation);
		Participation tgtSetNewParticipant=participation;
		tgtSetNewParticipant.setParticipant(newParticipant);
		ParticipationInRequest tgtSetKind=participation;
		tgtSetKind.setKind(kind);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToParticipationInRequest(ParticipationInRequest participationInRequest) {
		if ( participationInRequest!=null ) {
			participationInRequest.z_internalRemoveFromRequest(participationInRequest.getRequest());
			participationInRequest.z_internalAddToRequest(this);
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToParticipationInRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
		removeAllFromParticipationInRequest(getParticipationInRequest());
	}
	
	@NumlMetaInfo(uuid="252060@_4RNcAIoaEeCPduia_-NbFw")
	public void complete() {
		generateCompleteEvent();
	}
	
	public void completed() {
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onAbstractRequestComplete(getReturnInfo(),this);
		}
	}
	
	public boolean consumeActivateOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeAddRequestParticipantOccurrence(@ParameterMetaInfo(name="newParticipant",opaeumId=4174829400602250316l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg") Participant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=9084738623180185780l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg") RequestParticipationKind kind) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeCompleteOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeRemoveRequestParticipantOccurrence(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") Participant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
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
		for ( ParticipationInRequest child : from.getParticipationInRequest() ) {
			to.addToParticipationInRequest(child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public ParticipationInRequest createParticipationInRequest() {
		ParticipationInRequest newInstance= new ParticipationInRequest();
		newInstance.init(this);
		return newInstance;
	}
	
	public AbstractRequestToken createToken(StateMachineToken smToken) {
		AbstractRequestToken result = new AbstractRequestToken((AbstractRequestToken)smToken);
		tokens.add(result);
		return result;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof AbstractRequest ) {
			return other==this || ((AbstractRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void execute() {
		setExecutedOn(new Date());
	}
	
	public void generateActivateEvent() {
	}
	
	public void generateAddRequestParticipantEvent(@ParameterMetaInfo(name="newParticipant",opaeumId=4174829400602250316l,uuid="252060@_TKUhAI6QEeCrtavWRHwoHg") Participant newParticipant, @ParameterMetaInfo(name="kind",opaeumId=9084738623180185780l,uuid="252060@_TsHmgI6QEeCrtavWRHwoHg") RequestParticipationKind kind) {
	}
	
	public void generateCompleteEvent() {
	}
	
	public void generateRemoveRequestParticipantEvent(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") Participant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
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
	
	public Map<String, IStateMachineExecutionElement> getExecutionElements() {
		Map<String, IStateMachineExecutionElement> result = executionElements;
		if ( executionElements==null ) {
			result=executionElements=new HashMap<String,org.opaeum.runtime.statemachines.IStateMachineExecutionElement>();
			new Region1(this).linkTransitions();
		}
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_VJyB4I6REeCrtavWRHwoHg")
	public Participant getInitiator() {
		Participant result = any1().getParticipant();
		
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
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5501213897228443240l,opposite="subRequests",uuid="252060@_towFgY29EeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_towFgY29EeCrtavWRHwoHg")
	public TaskRequest getParentTask() {
		TaskRequest result = this.parentTask;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3022263813028286216l,opposite="request",uuid="252060@_XLHkUI6NEeCrtavWRHwoHg")
	@NumlMetaInfo(uuid="252060@_XLHkUI6NEeCrtavWRHwoHg")
	public Set<ParticipationInRequest> getParticipationInRequest() {
		Set<ParticipationInRequest> result = this.participationInRequest;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8918582809852333993l,opposite="request",uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	public IRequestObject getRequestObject() {
		IRequestObject result = (IRequestObject)this.requestObject.getValue(persistence);
		
		return result;
	}
	
	public IToken getReturnInfo() {
		IToken result = this.returnInfo.getValue(persistence);
		
		return result;
	}
	
	public Set<AbstractRequestToken> getTokens() {
		Set<AbstractRequestToken> result = tokens;
		
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
		createComponents();
	}
	
	abstract public AbstractRequest makeCopy();
	
	abstract public AbstractRequest makeShallowCopy();
	
	public void markDeleted() {
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests(this);
		}
		if ( getRequestObject()!=null ) {
			getRequestObject().z_internalRemoveFromRequest(this);
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationInRequest()) ) {
			child.markDeleted();
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationInRequest()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<AbstractRequest> newMocks) {
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
		}
	}
	
	public void propagateException(Object exception) {
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onAbstractRequestUnhandledException(getReturnInfo(),exception, this);
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
			participationInRequest.z_internalRemoveFromRequest(this);
			z_internalRemoveFromParticipationInRequest(participationInRequest);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	@NumlMetaInfo(uuid="252060@_Nl5kQI6SEeCrtavWRHwoHg")
	public void removeRequestParticipant(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") Participant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
		generateRemoveRequestParticipantEvent(participant,kind);
		AbstractRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationInRequest((select2(participant, kind)));
	}
	
	public void removeToken(StateMachineToken smToken) {
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
			this.getParentTask().z_internalRemoveFromSubRequests(this);
		}
		if ( parentTask!=null ) {
			parentTask.z_internalAddToSubRequests(this);
			this.z_internalAddToParentTask(parentTask);
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
				oldValue.z_internalRemoveFromRequest(this);
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
		this.returnInfo.setValue(token);
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
		sb.append("\n<participationInRequest propertyId=\"3022263813028286216\">");
		for ( ParticipationInRequest participationInRequest : getParticipationInRequest() ) {
			sb.append("\n" + participationInRequest.toXmlString());
		}
		sb.append("\n</participationInRequest>");
		sb.append("\n</AbstractRequest>");
		return sb.toString();
	}
	
	public void z_internalAddToParentTask(TaskRequest val) {
		this.parentTask=val;
	}
	
	public void z_internalAddToParticipationInRequest(ParticipationInRequest val) {
		this.participationInRequest.add(val);
	}
	
	public void z_internalAddToRequestObject(IRequestObject val) {
		this.requestObject.setValue(val);
	}
	
	public void z_internalRemoveFromParentTask(TaskRequest val) {
		if ( getParentTask()!=null && val!=null && val.equals(getParentTask()) ) {
			this.parentTask=null;
			this.parentTask=null;
		}
	}
	
	public void z_internalRemoveFromParticipationInRequest(ParticipationInRequest val) {
		this.participationInRequest.remove(val);
	}
	
	public void z_internalRemoveFromRequestObject(IRequestObject val) {
		if ( getRequestObject()!=null && val!=null && val.equals(getRequestObject()) ) {
			this.requestObject.setValue(null);
		}
	}
	
	/** Implements self.participationInRequest->any(p : ParticipationInRequest | p.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))
	 */
	private ParticipationInRequest any1() {
		ParticipationInRequest result = null;
		for ( ParticipationInRequest p : this.getParticipationInRequest() ) {
			if ( (p.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				return p;
			}
		}
		return result;
	}
	
	/** Implements self.participationInRequest->select(p : ParticipationInRequest | p.participant.=(participant).and(p.kind.=(kind)))
	 * 
	 * @param participant 
	 * @param kind 
	 */
	private Set<ParticipationInRequest> select2(@ParameterMetaInfo(name="participant",opaeumId=5904449775692844716l,uuid="252060@_P68JAI6SEeCrtavWRHwoHg") Participant participant, @ParameterMetaInfo(name="kind",opaeumId=5058842751504084224l,uuid="252060@_P8scgI6SEeCrtavWRHwoHg") RequestParticipationKind kind) {
		Set<ParticipationInRequest> result = new HashSet<ParticipationInRequest>();
		for ( ParticipationInRequest p : this.getParticipationInRequest() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}

}