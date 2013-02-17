package org.opaeum.demo1.structuredbusiness.branch.branch;

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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.ProductAnnouncement;
import org.opaeum.demo1.structuredbusiness.ProductAnnouncementHandler;
import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.Region1;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.SubmitHandler60362420042796755;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Draft;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Initial;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Submitted;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.ProcessRequest;
import org.opaeum.runtime.bpm.request.TaskParticipationKind;
import org.opaeum.runtime.bpm.requestobject.IBusinessStateMachine;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.FailedConstraintsException;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IObserver;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessObjectBase;
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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="prepare_quote",schema="structuredbusiness",uniqueConstraints={
	@UniqueConstraint(columnNames={"process_request_id","deleted_on"}),
	@UniqueConstraint(columnNames={"request_id","deleted_on"})})
@Entity(name="PrepareQuote")
public class PrepareQuote implements IStateMachineExecution, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessStateMachine, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="context_object_id",name="idx_prepare_quote_context_object_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(insertable=false,name="context_object_id",nullable=true,updatable=false)
	protected Branch contextObject;
	@Transient
	protected Object currentException;
	@Column(name="customer_approved")
	@Basic
	protected Boolean customerApproved;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="executed_on")
	private Date executedOn;
	@Transient
	private Map<String, IExecutionElement> executionElements;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="prepare_quote",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Type(type="org.opaeum.runtime.bpm.request.TaskParticipationKindResolver")
	@Column(name="ll",nullable=true)
	protected TaskParticipationKind ll;
	@Column(name="lll")
	@Basic
	protected Integer lll;
	static private Set<? extends PrepareQuote> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Column(name="parameter1")
	@Basic
	protected Integer parameter1;
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="process_request_id",name="idx_prepare_quote_process_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="process_request_id",nullable=true)
	protected ProcessRequest processRequest;
	@Column(name="property1ll")
	@Basic
	protected Boolean property1ll;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="request_id",name="idx_prepare_quote_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="return_info"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="return_info_type"),name="classIdentifier")})
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 6201964425220440083l;
	@OneToMany(mappedBy="behaviorExecution")
	protected Set<PrepareQuoteToken> tokens = new HashSet<PrepareQuoteToken>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PrepareQuote(Branch owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PrepareQuote
	 */
	public PrepareQuote() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getContextObject().z_internalAddToPrepareQuote((PrepareQuote)this);
	}
	
	static public Set<? extends PrepareQuote> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	@NumlMetaInfo(uuid="914890@_Y9DC0BYbEeKsDbmQL25eBw")
	public void approve() {
		generateApproveEvent();
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("ll").length()>0 ) {
			setLl(TaskParticipationKind.valueOf(xml.getAttribute("ll")));
		}
		if ( xml.getAttribute("lll").length()>0 ) {
			setLll(StructuredbusinessFormatter.getInstance().parseInteger(xml.getAttribute("lll")));
		}
		if ( xml.getAttribute("property1ll").length()>0 ) {
			setProperty1ll(StructuredbusinessFormatter.getInstance().parseBoolean(xml.getAttribute("property1ll")));
		}
		if ( xml.getAttribute("customerApproved").length()>0 ) {
			setCustomerApproved(StructuredbusinessFormatter.getInstance().parseBoolean(xml.getAttribute("customerApproved")));
		}
		if ( xml.getAttribute("parameter1").length()>0 ) {
			setParameter1(StructuredbusinessFormatter.getInstance().parseInteger(xml.getAttribute("parameter1")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("request") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6849052820632333237")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						AbstractRequest curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("processRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7762966921979730371")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ProcessRequest curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setProcessRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public Date calculateNextOccurrenceOfTransition1ChangeEvent() {
		Date result = new Date(System.currentTimeMillis()+1000*60*60*4);
		
		return result;
	}
	
	public void complete() {
		PrepareQuoteListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onPrepareQuoteComplete(getReturnInfo(),this);
		}
	}
	
	public boolean consumeApproveOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnActivatedOccurrence(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=4095523919410592856l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") IBusinessRole completedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnFailureOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnResumedOccurrence(@ParameterMetaInfo(name="resumedBy",opaeumId=787801760248251908l,uuid="252060@_xd0650uBEeGElKTCe2jfDw") IBusinessRole resumedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=8825299842246312l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") IBusinessRole startedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnSuspendedOccurrence(@ParameterMetaInfo(name="suspendedBy",opaeumId=3377686963449892476l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") IBusinessRole suspendedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeProductAnnouncementEvent(ProductAnnouncement signal) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Initial ) {
				Initial state = (Initial)token.getCurrentExecutionElement();
				if ( result==false &&  state.getTransition0().consumeProductAnnouncementEvent(signal) ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean consumeSubmitOccurrence() {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Draft ) {
				Draft state = (Draft)token.getCurrentExecutionElement();
				if ( result==false &&  state.getOnSubmit().consumeSubmitOccurrence() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public void copyShallowState(PrepareQuote from, PrepareQuote to) {
		to.setLl(from.getLl());
		to.setLll(from.getLll());
		to.setProperty1ll(from.getProperty1ll());
		to.setCustomerApproved(from.getCustomerApproved());
		to.setParameter1(from.getParameter1());
	}
	
	public void copyState(PrepareQuote from, PrepareQuote to) {
		to.setLl(from.getLl());
		to.setLll(from.getLll());
		to.setProperty1ll(from.getProperty1ll());
		to.setCustomerApproved(from.getCustomerApproved());
		if ( from.getRequest()!=null ) {
			to.setRequest(from.getRequest().makeCopy());
		}
		if ( from.getProcessRequest()!=null ) {
			to.setProcessRequest(from.getProcessRequest().makeCopy());
		}
		to.setParameter1(from.getParameter1());
	}
	
	public void createComponents() {
	}
	
	public ProcessRequest createProcessRequest() {
		ProcessRequest newInstance= new ProcessRequest();
		newInstance.init(this);
		return newInstance;
	}
	
	public IToken createToken(IToken smToken) {
		PrepareQuoteToken result = new PrepareQuoteToken((PrepareQuoteToken)smToken);
		tokens.add(result);
		result.setBehaviorExecution(this);
		return result;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PrepareQuote ) {
			return other==this || ((PrepareQuote)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void evaluatePostconditions() {
	}
	
	public void evaluatePreconditions() {
	}
	
	public boolean evaluateTransition1ChangeEvent() {
		return this.getCustomerApproved();
	}
	
	public void execute() throws FailedConstraintsException {
		evaluatePreconditions();
		setExecutedOn(new Date());
		((Region1)getExecutionElements().get(Region1.ID)).initiate(null);
	}
	
	public void generateApproveEvent() {
	}
	
	public void generateOnActivatedEvent(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
	}
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=4095523919410592856l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") IBusinessRole completedBy) {
	}
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
	}
	
	public void generateOnFailureEvent() {
	}
	
	public void generateOnResumedEvent(@ParameterMetaInfo(name="resumedBy",opaeumId=787801760248251908l,uuid="252060@_xd0650uBEeGElKTCe2jfDw") IBusinessRole resumedBy) {
	}
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=8825299842246312l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") IBusinessRole startedBy) {
	}
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
	}
	
	public void generateOnSuspendedEvent(@ParameterMetaInfo(name="suspendedBy",opaeumId=3377686963449892476l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") IBusinessRole suspendedBy) {
	}
	
	public void generateProductAnnouncementEvent(ProductAnnouncement signal) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ProductAnnouncementHandler(signal,true)));
	}
	
	public void generateSubmitEvent() {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new SubmitHandler60362420042796755(true)));
	}
	
	public PrepareQuoteListener getCallingBehaviorExecution() {
		PrepareQuoteListener result = null;
		if ( getReturnInfo()!=null  ) {
			result=(PrepareQuoteListener)getReturnInfo().getBehaviorExecution();
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=305380707991727546l,opposite="prepareQuote",uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
	@NumlMetaInfo(uuid="914890@_HHdlgBYUEeKsDbmQL25eBw@contextObject")
	public Branch getContextObject() {
		Branch result = this.contextObject;
		
		return result;
	}
	
	public Object getCurrentException() {
		return this.currentException;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=134878655361561375l,uuid="914890@_wk6wkHgGEeKNG8mFSp3Ijg")
	@NumlMetaInfo(uuid="914890@_wk6wkHgGEeKNG8mFSp3Ijg")
	public Boolean getCustomerApproved() {
		Boolean result = this.customerApproved;
		
		return result;
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
			regions.add(new Region1<PrepareQuote>(this));
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
	
	public IProcessStep getInnermostNonParallelStep() {
		IProcessStep result = null;
		for ( IToken token : getTokens() ) {
			if ( token.getParentToken()==null ) {
				return (IProcessStep)token.getInnermostNonParallelToken().getCurrentExecutionElement();
			}
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4591559402472459001l,uuid="914890@_Nv-xoBYUEeKsDbmQL25eBw")
	@NumlMetaInfo(uuid="914890@_Nv-xoBYUEeKsDbmQL25eBw")
	public TaskParticipationKind getLl() {
		TaskParticipationKind result = this.ll;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5517235915039157961l,uuid="914890@_zEyr8BYUEeKsDbmQL25eBw")
	@NumlMetaInfo(uuid="914890@_zEyr8BYUEeKsDbmQL25eBw")
	public Integer getLll() {
		Integer result = this.lll;
		
		return result;
	}
	
	public String getName() {
		return "PrepareQuote["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getContextObject();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=934438638546746096l,uuid="914890@__oyQoBYXEeKsDbmQL25eBw")
	@NumlMetaInfo(uuid="914890@__oyQoBYXEeKsDbmQL25eBw@TEPB")
	public Integer getParameter1() {
		Integer result = this.parameter1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7762966921979730371l,opposite="processObject",uuid="252060@_JY15wY3pEeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_JY15wY3pEeCfQedkc0TCdA")
	public ProcessRequest getProcessRequest() {
		ProcessRequest result = this.processRequest;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4071540829756570175l,uuid="914890@_JUKJwBYYEeKsDbmQL25eBw")
	@NumlMetaInfo(uuid="914890@_JUKJwBYYEeKsDbmQL25eBw")
	public Boolean getProperty1ll() {
		Boolean result = this.property1ll;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6849052820632333237l,opposite="requestObject",uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	public AbstractRequest getRequest() {
		AbstractRequest result = getProcessRequest();
		
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
		this.z_internalAddToContextObject((Branch)owner);
		createComponents();
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
	
	public PrepareQuote makeCopy() {
		PrepareQuote result = new PrepareQuote();
		copyState((PrepareQuote)this,result);
		return result;
	}
	
	public PrepareQuote makeShallowCopy() {
		PrepareQuote result = new PrepareQuote();
		copyShallowState((PrepareQuote)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getContextObject()!=null ) {
			getContextObject().z_internalRemoveFromPrepareQuote(this);
		}
		if ( getRequest()!=null ) {
			getRequest().markDeleted();
		}
		if ( getProcessRequest()!=null ) {
			getProcessRequest().markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
		mockedAllInstances=newMocks;
	}
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
		generateOnActivatedEvent(activatedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_yDSYYEuBEeGElKTCe2jfDw")
	public void onCompleted(@ParameterMetaInfo(name="completedBy",opaeumId=4095523919410592856l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") IBusinessRole completedBy) {
		generateOnCompletedEvent(completedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
		generateOnCompletedEvent(completedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_CDJHUEuCEeGElKTCe2jfDw")
	public void onFailure() {
		generateOnFailureEvent();
	}
	
	public boolean onOccurrenceOfSubmittedTimeEvent(IToken callingToken, Date triggerDate) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Draft ) {
				Draft state = (Draft)token.getCurrentExecutionElement();
				if ( result==false &&  state.getAfter3BusinessDays().onOccurrenceOfSubmittedTimeEvent(triggerDate) ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean onOccurrenceOfTransition1ChangeEvent(IToken callingToken) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof Submitted ) {
				Submitted state = (Submitted)token.getCurrentExecutionElement();
				if ( result==false &&  state.getTransition1().onOccurrenceOfTransition1ChangeEvent() ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_xd064EuBEeGElKTCe2jfDw")
	public void onResumed(@ParameterMetaInfo(name="resumedBy",opaeumId=787801760248251908l,uuid="252060@_xd0650uBEeGElKTCe2jfDw") IBusinessRole resumedBy) {
		generateOnResumedEvent(resumedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_v7N4oEuBEeGElKTCe2jfDw")
	public void onStarted(@ParameterMetaInfo(name="startedBy",opaeumId=8825299842246312l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") IBusinessRole startedBy) {
		generateOnStartedEvent(startedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
		generateOnStartedEvent(startedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_xCSOYEuBEeGElKTCe2jfDw")
	public void onSuspended(@ParameterMetaInfo(name="suspendedBy",opaeumId=3377686963449892476l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") IBusinessRole suspendedBy) {
		generateOnSuspendedEvent(suspendedBy);
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("request") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6849052820632333237")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((AbstractRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("processRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7762966921979730371")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ProcessRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void propagateException(Object exception) {
		PrepareQuoteListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onPrepareQuoteUnhandledException(getReturnInfo(),exception, this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void removeToken(IToken smToken) {
		tokens.remove((PrepareQuoteToken)smToken);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setContextObject(Branch contextObject) {
		propertyChangeSupport.firePropertyChange("contextObject",getContextObject(),contextObject);
		if ( this.getContextObject()!=null ) {
			this.getContextObject().z_internalRemoveFromPrepareQuote(this);
		}
		if ( contextObject!=null ) {
			contextObject.z_internalAddToPrepareQuote(this);
			this.z_internalAddToContextObject(contextObject);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setCurrentException(Object currentException) {
		this.currentException=currentException;
	}
	
	public void setCustomerApproved(Boolean customerApproved) {
		propertyChangeSupport.firePropertyChange("customerApproved",getCustomerApproved(),customerApproved);
		this.z_internalAddToCustomerApproved(customerApproved);
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
	
	public void setLl(TaskParticipationKind ll) {
		propertyChangeSupport.firePropertyChange("ll",getLl(),ll);
		this.z_internalAddToLl(ll);
	}
	
	public void setLll(Integer lll) {
		propertyChangeSupport.firePropertyChange("lll",getLll(),lll);
		this.z_internalAddToLll(lll);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParameter1(Integer parameter1) {
		propertyChangeSupport.firePropertyChange("parameter1",getParameter1(),parameter1);
		this.z_internalAddToParameter1(parameter1);
	}
	
	public void setProcessRequest(ProcessRequest processRequest) {
		ProcessRequest oldValue = this.getProcessRequest();
		propertyChangeSupport.firePropertyChange("processRequest",getProcessRequest(),processRequest);
		if ( oldValue==null ) {
			if ( processRequest!=null ) {
				PrepareQuote oldOther = (PrepareQuote)processRequest.getProcessObject();
				processRequest.z_internalRemoveFromProcessObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromProcessRequest(processRequest);
				}
				processRequest.z_internalAddToProcessObject((PrepareQuote)this);
			}
			this.z_internalAddToProcessRequest(processRequest);
		} else {
			if ( !oldValue.equals(processRequest) ) {
				oldValue.z_internalRemoveFromProcessObject(this);
				z_internalRemoveFromProcessRequest(oldValue);
				if ( processRequest!=null ) {
					PrepareQuote oldOther = (PrepareQuote)processRequest.getProcessObject();
					processRequest.z_internalRemoveFromProcessObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromProcessRequest(processRequest);
					}
					processRequest.z_internalAddToProcessObject((PrepareQuote)this);
				}
				this.z_internalAddToProcessRequest(processRequest);
			}
		}
	}
	
	public void setProperty1ll(Boolean property1ll) {
		propertyChangeSupport.firePropertyChange("property1ll",getProperty1ll(),property1ll);
		this.z_internalAddToProperty1ll(property1ll);
	}
	
	public void setRequest(AbstractRequest request) {
		AbstractRequest oldValue = this.getRequest();
		propertyChangeSupport.firePropertyChange("request",getRequest(),request);
		if ( oldValue==null ) {
			if ( request!=null ) {
				PrepareQuote oldOther = (PrepareQuote)request.getRequestObject();
				request.z_internalRemoveFromRequestObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromRequest(request);
				}
				request.z_internalAddToRequestObject((PrepareQuote)this);
			}
			this.z_internalAddToRequest(request);
		} else {
			if ( !oldValue.equals(request) ) {
				oldValue.z_internalRemoveFromRequestObject(this);
				z_internalRemoveFromRequest(oldValue);
				if ( request!=null ) {
					PrepareQuote oldOther = (PrepareQuote)request.getRequestObject();
					request.z_internalRemoveFromRequestObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromRequest(request);
					}
					request.z_internalAddToRequestObject((PrepareQuote)this);
				}
				this.z_internalAddToRequest(request);
			}
		}
	}
	
	public void setReturnInfo(IToken token) {
		if ( this.returnInfo==null ) {
			this.returnInfo=new ReturnInfo();
		}
		this.returnInfo.setValue(token);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	@NumlMetaInfo(uuid="914890@_SvOOoBYbEeKsDbmQL25eBw")
	public void submit() {
		generateSubmitEvent();
	}
	
	public String toXmlReferenceString() {
		return "<PrepareQuote uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PrepareQuote ");
		sb.append("classUuid=\"914890@_HHdlgBYUEeKsDbmQL25eBw\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getLl()!=null ) {
			sb.append("ll=\""+ getLl().name() + "\" ");
		}
		if ( getLll()!=null ) {
			sb.append("lll=\""+ StructuredbusinessFormatter.getInstance().formatInteger(getLll())+"\" ");
		}
		if ( getProperty1ll()!=null ) {
			sb.append("property1ll=\""+ StructuredbusinessFormatter.getInstance().formatBoolean(getProperty1ll())+"\" ");
		}
		if ( getCustomerApproved()!=null ) {
			sb.append("customerApproved=\""+ StructuredbusinessFormatter.getInstance().formatBoolean(getCustomerApproved())+"\" ");
		}
		if ( getParameter1()!=null ) {
			sb.append("parameter1=\""+ StructuredbusinessFormatter.getInstance().formatInteger(getParameter1())+"\" ");
		}
		sb.append(">");
		if ( getRequest()==null ) {
			sb.append("\n<request/>");
		} else {
			sb.append("\n<request propertyId=\"6849052820632333237\">");
			sb.append("\n" + getRequest().toXmlString());
			sb.append("\n</request>");
		}
		if ( getProcessRequest()==null ) {
			sb.append("\n<processRequest/>");
		} else {
			sb.append("\n<processRequest propertyId=\"7762966921979730371\">");
			sb.append("\n" + getProcessRequest().toXmlString());
			sb.append("\n</processRequest>");
		}
		sb.append("\n</PrepareQuote>");
		return sb.toString();
	}
	
	public void z_internalAddToContextObject(Branch contextObject) {
		this.contextObject=contextObject;
	}
	
	public void z_internalAddToCustomerApproved(Boolean customerApproved) {
		this.customerApproved=customerApproved;
	}
	
	public void z_internalAddToLl(TaskParticipationKind ll) {
		this.ll=ll;
	}
	
	public void z_internalAddToLll(Integer lll) {
		this.lll=lll;
	}
	
	public void z_internalAddToParameter1(Integer parameter1) {
		this.parameter1=parameter1;
	}
	
	public void z_internalAddToProcessRequest(ProcessRequest processRequest) {
		AbstractRequest request = processRequest;
		this.processRequest=processRequest;
		this.request=request;
	}
	
	public void z_internalAddToProperty1ll(Boolean property1ll) {
		this.property1ll=property1ll;
	}
	
	public void z_internalAddToRequest(AbstractRequest request) {
		ProcessRequest processRequest = (ProcessRequest)request;
		this.request=request;
		this.processRequest=processRequest;
	}
	
	public void z_internalRemoveFromContextObject(Branch contextObject) {
		if ( getContextObject()!=null && contextObject!=null && contextObject.equals(getContextObject()) ) {
			this.contextObject=null;
			this.contextObject=null;
		}
	}
	
	public void z_internalRemoveFromCustomerApproved(Boolean customerApproved) {
		if ( getCustomerApproved()!=null && customerApproved!=null && customerApproved.equals(getCustomerApproved()) ) {
			this.customerApproved=null;
			this.customerApproved=null;
		}
	}
	
	public void z_internalRemoveFromLl(TaskParticipationKind ll) {
		if ( getLl()!=null && ll!=null && ll.equals(getLl()) ) {
			this.ll=null;
			this.ll=null;
		}
	}
	
	public void z_internalRemoveFromLll(Integer lll) {
		if ( getLll()!=null && lll!=null && lll.equals(getLll()) ) {
			this.lll=null;
			this.lll=null;
		}
	}
	
	public void z_internalRemoveFromParameter1(Integer parameter1) {
		if ( getParameter1()!=null && parameter1!=null && parameter1.equals(getParameter1()) ) {
			this.parameter1=null;
			this.parameter1=null;
		}
	}
	
	public void z_internalRemoveFromProcessRequest(ProcessRequest processRequest) {
		AbstractRequest request = processRequest;
		if ( getProcessRequest()!=null && processRequest!=null && processRequest.equals(getProcessRequest()) ) {
			this.processRequest=null;
			this.processRequest=null;
		}
		if ( getRequest()!=null && request!=null && request.equals(getRequest()) ) {
			this.request=null;
			this.request=null;
		}
	}
	
	public void z_internalRemoveFromProperty1ll(Boolean property1ll) {
		if ( getProperty1ll()!=null && property1ll!=null && property1ll.equals(getProperty1ll()) ) {
			this.property1ll=null;
			this.property1ll=null;
		}
	}
	
	public void z_internalRemoveFromRequest(AbstractRequest request) {
		ProcessRequest processRequest = (ProcessRequest)request;
		if ( getRequest()!=null && request!=null && request.equals(getRequest()) ) {
			this.request=null;
			this.request=null;
		}
		if ( getProcessRequest()!=null && processRequest!=null && processRequest.equals(getProcessRequest()) ) {
			this.processRequest=null;
			this.processRequest=null;
		}
	}

}