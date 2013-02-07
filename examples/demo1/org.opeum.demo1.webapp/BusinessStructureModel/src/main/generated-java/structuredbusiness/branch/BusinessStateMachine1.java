package structuredbusiness.branch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
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

import structuredbusiness.Branch;
import structuredbusiness.branch.businessstatemachine1.Region1;
import structuredbusiness.branch.businessstatemachine1.region1.Draft;
import structuredbusiness.branch.businessstatemachine1.region1.Initial;
import structuredbusiness.branch.businessstatemachine1.region1.State1;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_state_machine1")
@Entity(name="BusinessStateMachine1")
public class BusinessStateMachine1 implements IStateMachineExecution, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessStateMachine, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="context_object_id",name="idx_business_state_machine1_context_object_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(insertable=false,name="context_object_id",nullable=true,updatable=false)
	protected Branch contextObject;
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
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Type(type="org.opaeum.runtime.bpm.request.TaskParticipationKindResolver")
	@Column(name="ll_id",nullable=true)
	protected TaskParticipationKind ll;
	@Column(name="lll")
	protected Integer lll;
	static private Set<BusinessStateMachine1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Column(name="parameter1")
	protected Integer parameter1;
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="process_request_id",name="idx_business_state_machine1_process_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="process_request_id",nullable=true)
	protected ProcessRequest processRequest;
	@Column(name="property1ll")
	protected Boolean property1ll;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="request_id",name="idx_business_state_machine1_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	static final private long serialVersionUID = 6201964425220440083l;
	@OneToMany(mappedBy="behaviorExecution")
	protected Set<BusinessStateMachine1Token> tokens = new HashSet<BusinessStateMachine1Token>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessStateMachine1(Branch owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for BusinessStateMachine1
	 */
	public BusinessStateMachine1() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getContextObject().z_internalAddToBusinessStateMachine1((BusinessStateMachine1)this);
	}
	
	static public Set<? extends BusinessStateMachine1> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(structuredbusiness.branch.BusinessStateMachine1.class));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setProcessRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
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
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=4095523919410592856l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") String completedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnFailureOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnResumedOccurrence(@ParameterMetaInfo(name="resumedBy",opaeumId=787801760248251908l,uuid="252060@_xd0650uBEeGElKTCe2jfDw") String resumedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=8825299842246312l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") String startedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnSuspendedOccurrence(@ParameterMetaInfo(name="suspendedBy",opaeumId=3377686963449892476l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") String suspendedBy) {
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
		
		return result;
	}
	
	public void copyShallowState(BusinessStateMachine1 from, BusinessStateMachine1 to) {
		to.setLl(from.getLl());
		to.setLll(from.getLll());
		to.setProperty1ll(from.getProperty1ll());
		to.setParameter1(from.getParameter1());
	}
	
	public void copyState(BusinessStateMachine1 from, BusinessStateMachine1 to) {
		to.setLl(from.getLl());
		to.setLll(from.getLll());
		to.setProperty1ll(from.getProperty1ll());
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
		BusinessStateMachine1Token result = new BusinessStateMachine1Token((BusinessStateMachine1Token)smToken);
		tokens.add(result);
		result.setBehaviorExecution(this);
		return result;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessStateMachine1 ) {
			return other==this || ((BusinessStateMachine1)other).getUid().equals(this.getUid());
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
	
	public void generateApproveEvent() {
	}
	
	public void generateOnActivatedEvent(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
	}
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
	}
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=4095523919410592856l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") String completedBy) {
	}
	
	public void generateOnFailureEvent() {
	}
	
	public void generateOnResumedEvent(@ParameterMetaInfo(name="resumedBy",opaeumId=787801760248251908l,uuid="252060@_xd0650uBEeGElKTCe2jfDw") String resumedBy) {
	}
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
	}
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=8825299842246312l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") String startedBy) {
	}
	
	public void generateOnSuspendedEvent(@ParameterMetaInfo(name="suspendedBy",opaeumId=3377686963449892476l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") String suspendedBy) {
	}
	
	public void generateProductAnnouncementEvent(ProductAnnouncement signal) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ProductAnnouncementHandler(signal,true)));
	}
	
	public void generateSubmitEvent() {
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=305380707991727546l,opposite="businessStateMachine1",uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
	@NumlMetaInfo(uuid="914890@_HHdlgBYUEeKsDbmQL25eBw@contextObject")
	public Branch getContextObject() {
		Branch result = this.contextObject;
		
		return result;
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
		Map<String, IExecutionElement> result = executionElements;
		if ( executionElements==null ) {
			Set<RegionActivation> regions = new HashSet<RegionActivation>();
			result=executionElements=new HashMap<String,org.opaeum.runtime.domain.IExecutionElement>();
			regions.add(new Region1<BusinessStateMachine1>(this));
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
		return "BusinessStateMachine1["+getId()+"]";
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
	
	public Set<IToken> getTokens() {
		Set<IToken> result = new HashSet<IToken>(tokens);
		
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
	
	public BusinessStateMachine1 makeCopy() {
		BusinessStateMachine1 result = new BusinessStateMachine1();
		copyState((BusinessStateMachine1)this,result);
		return result;
	}
	
	public BusinessStateMachine1 makeShallowCopy() {
		BusinessStateMachine1 result = new BusinessStateMachine1();
		copyShallowState((BusinessStateMachine1)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getContextObject()!=null ) {
			getContextObject().z_internalRemoveFromBusinessStateMachine1(this);
		}
		if ( getRequest()!=null ) {
			getRequest().markDeleted();
		}
		if ( getProcessRequest()!=null ) {
			getProcessRequest().markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<BusinessStateMachine1> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	@NumlMetaInfo(uuid="252060@_XbPZkK0OEeCK48ywUpk_rg")
	public void onActivated(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
		generateOnActivatedEvent(activatedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_a_82cK0OEeCK48ywUpk_rg")
	public void onCompleted(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
		generateOnCompletedEvent(completedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_yDSYYEuBEeGElKTCe2jfDw")
	public void onCompleted(@ParameterMetaInfo(name="completedBy",opaeumId=4095523919410592856l,uuid="252060@_yDSYZ0uBEeGElKTCe2jfDw") String completedBy) {
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
				if ( result==false &&  state.getSubmitted().onOccurrenceOfSubmittedTimeEvent(triggerDate) ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	public boolean onOccurrenceOfTransition1TimeEvent(IToken callingToken, Date triggerDate) {
		boolean result = false;
		for ( IToken token : getTokens() ) {
			if ( result==false && token.isActive() && token.getCurrentExecutionElement() instanceof State1 ) {
				State1 state = (State1)token.getCurrentExecutionElement();
				if ( result==false &&  state.getTransition1().onOccurrenceOfTransition1TimeEvent(triggerDate) ) {
					result=true;
					break;
				}
			}
		}
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_xd064EuBEeGElKTCe2jfDw")
	public void onResumed(@ParameterMetaInfo(name="resumedBy",opaeumId=787801760248251908l,uuid="252060@_xd0650uBEeGElKTCe2jfDw") String resumedBy) {
		generateOnResumedEvent(resumedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_iBCwEK0NEeCK48ywUpk_rg")
	public void onStarted(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
		generateOnStartedEvent(startedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_v7N4oEuBEeGElKTCe2jfDw")
	public void onStarted(@ParameterMetaInfo(name="startedBy",opaeumId=8825299842246312l,uuid="252060@_v7N4p0uBEeGElKTCe2jfDw") String startedBy) {
		generateOnStartedEvent(startedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_xCSOYEuBEeGElKTCe2jfDw")
	public void onSuspended(@ParameterMetaInfo(name="suspendedBy",opaeumId=3377686963449892476l,uuid="252060@_xCSOZ0uBEeGElKTCe2jfDw") String suspendedBy) {
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
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void removeToken(IToken smToken) {
		tokens.remove((BusinessStateMachine1Token)smToken);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setContextObject(Branch contextObject) {
		Branch oldValue = this.getContextObject();
		propertyChangeSupport.firePropertyChange("contextObject",getContextObject(),contextObject);
		if ( oldValue==null ) {
			if ( contextObject!=null ) {
				BusinessStateMachine1 oldOther = (BusinessStateMachine1)contextObject.getBusinessStateMachine1();
				contextObject.z_internalRemoveFromBusinessStateMachine1(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromContextObject(contextObject);
				}
				contextObject.z_internalAddToBusinessStateMachine1((BusinessStateMachine1)this);
			}
			this.z_internalAddToContextObject(contextObject);
		} else {
			if ( !oldValue.equals(contextObject) ) {
				oldValue.z_internalRemoveFromBusinessStateMachine1(this);
				z_internalRemoveFromContextObject(oldValue);
				if ( contextObject!=null ) {
					BusinessStateMachine1 oldOther = (BusinessStateMachine1)contextObject.getBusinessStateMachine1();
					contextObject.z_internalRemoveFromBusinessStateMachine1(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromContextObject(contextObject);
					}
					contextObject.z_internalAddToBusinessStateMachine1((BusinessStateMachine1)this);
				}
				this.z_internalAddToContextObject(contextObject);
			}
		}
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
				BusinessStateMachine1 oldOther = (BusinessStateMachine1)processRequest.getProcessObject();
				processRequest.z_internalRemoveFromProcessObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromProcessRequest(processRequest);
				}
				processRequest.z_internalAddToProcessObject((BusinessStateMachine1)this);
			}
			this.z_internalAddToProcessRequest(processRequest);
		} else {
			if ( !oldValue.equals(processRequest) ) {
				oldValue.z_internalRemoveFromProcessObject(this);
				z_internalRemoveFromProcessRequest(oldValue);
				if ( processRequest!=null ) {
					BusinessStateMachine1 oldOther = (BusinessStateMachine1)processRequest.getProcessObject();
					processRequest.z_internalRemoveFromProcessObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromProcessRequest(processRequest);
					}
					processRequest.z_internalAddToProcessObject((BusinessStateMachine1)this);
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
				BusinessStateMachine1 oldOther = (BusinessStateMachine1)request.getRequestObject();
				request.z_internalRemoveFromRequestObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromRequest(request);
				}
				request.z_internalAddToRequestObject((BusinessStateMachine1)this);
			}
			this.z_internalAddToRequest(request);
		} else {
			if ( !oldValue.equals(request) ) {
				oldValue.z_internalRemoveFromRequestObject(this);
				z_internalRemoveFromRequest(oldValue);
				if ( request!=null ) {
					BusinessStateMachine1 oldOther = (BusinessStateMachine1)request.getRequestObject();
					request.z_internalRemoveFromRequestObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromRequest(request);
					}
					request.z_internalAddToRequestObject((BusinessStateMachine1)this);
				}
				this.z_internalAddToRequest(request);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	@NumlMetaInfo(uuid="914890@_SvOOoBYbEeKsDbmQL25eBw")
	public void submit() {
		generateSubmitEvent();
	}
	
	public String toXmlReferenceString() {
		return "<BusinessStateMachine1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessStateMachine1 ");
		sb.append("classUuid=\"914890@_HHdlgBYUEeKsDbmQL25eBw\" ");
		sb.append("className=\"structuredbusiness.branch.BusinessStateMachine1\" ");
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
		sb.append("\n</BusinessStateMachine1>");
		return sb.toString();
	}
	
	public void z_internalAddToContextObject(Branch contextObject) {
		this.contextObject=contextObject;
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