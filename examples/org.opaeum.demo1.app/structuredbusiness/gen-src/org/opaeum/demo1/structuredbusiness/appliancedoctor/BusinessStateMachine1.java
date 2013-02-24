package org.opaeum.demo1.structuredbusiness.appliancedoctor;

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

import javax.persistence.Column;
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
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.ApplianceDoctor;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.businessstatemachine1.Region1;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.ProcessRequest;
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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_cTMn8H2lEeK5F45wEGRv4A")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_state_machine1",schema="structuredbusiness",uniqueConstraints={
	@UniqueConstraint(columnNames={"process_request_id","deleted_on"}),
	@UniqueConstraint(columnNames={"request_id","deleted_on"})})
@Entity(name="BusinessStateMachine1")
public class BusinessStateMachine1 implements IStateMachineExecution, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessStateMachine, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="context_object_id",name="idx_business_state_machine1_context_object_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="context_object_id",nullable=true)
	protected ApplianceDoctor contextObject;
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
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_state_machine1",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends BusinessStateMachine1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="process_request_id",name="idx_business_state_machine1_process_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="process_request_id",nullable=true)
	protected ProcessRequest processRequest;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="request_id",name="idx_business_state_machine1_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	static final private long serialVersionUID = 6178617607197585829l;
	@OneToMany(mappedBy="behaviorExecution")
	protected Set<BusinessStateMachine1Token> tokens = new HashSet<BusinessStateMachine1Token>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessStateMachine1(ApplianceDoctor owningObject) {
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
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1.class));
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
	
	public void copyShallowState(BusinessStateMachine1 from, BusinessStateMachine1 to) {
	}
	
	public void copyState(BusinessStateMachine1 from, BusinessStateMachine1 to) {
		if ( from.getRequest()!=null ) {
			to.setRequest(from.getRequest().makeCopy());
		}
		if ( from.getProcessRequest()!=null ) {
			to.setProcessRequest(from.getProcessRequest().makeCopy());
		}
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
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2800483430576566031l,opposite="businessStateMachine1",uuid="914890@_cTMn8H2lEeK5F45wEGRv4A")
	@NumlMetaInfo(uuid="914890@_cTMn8H2lEeK5F45wEGRv4A@contextObject")
	public ApplianceDoctor getContextObject() {
		ApplianceDoctor result = this.contextObject;
		
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
		Map result = executionElements;
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7762966921979730371l,opposite="processObject",uuid="252060@_JY15wY3pEeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_JY15wY3pEeCfQedkc0TCdA")
	public ProcessRequest getProcessRequest() {
		ProcessRequest result = this.processRequest;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6849052820632333237l,opposite="requestObject",uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	public AbstractRequest getRequest() {
		AbstractRequest result = getProcessRequest();
		
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
		this.z_internalAddToContextObject((ApplianceDoctor)owner);
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
		setDeletedOn(new Date(System.currentTimeMillis()));
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
	
	public void setContextObject(ApplianceDoctor contextObject) {
		ApplianceDoctor oldValue = this.getContextObject();
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
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
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
	
	public String toXmlReferenceString() {
		return "<BusinessStateMachine1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessStateMachine1 ");
		sb.append("classUuid=\"914890@_cTMn8H2lEeK5F45wEGRv4A\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
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
	
	public void z_internalAddToContextObject(ApplianceDoctor contextObject) {
		this.contextObject=contextObject;
	}
	
	public void z_internalAddToProcessRequest(ProcessRequest processRequest) {
		AbstractRequest request = processRequest;
		this.processRequest=processRequest;
		this.request=request;
	}
	
	public void z_internalAddToRequest(AbstractRequest request) {
		ProcessRequest processRequest = (ProcessRequest)request;
		this.request=request;
		this.processRequest=processRequest;
	}
	
	public void z_internalRemoveFromContextObject(ApplianceDoctor contextObject) {
		if ( getContextObject()!=null && contextObject!=null && contextObject.equals(getContextObject()) ) {
			this.contextObject=null;
			this.contextObject=null;
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