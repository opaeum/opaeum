package bpmmodel.mybusiness;

import bpmmodel.MyBusiness;
import bpmmodel.Product;
import bpmmodel.mybusiness.preparequoteimpl.Call_Customer;
import bpmmodel.util.BpmmodelFormatter;
import bpmmodel.util.Stdlib;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.ProcessRequest;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.bpm.request.TaskParticipationKind;
import org.opaeum.runtime.bpm.requestobject.IBusinessProcess;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.ExceptionHolder;
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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_Gld5EI_hEeK855GX2Z3x4Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="prepare_quote_impl",uniqueConstraints={
	@UniqueConstraint(columnNames={"process_request_id","deleted_on"}),
	@UniqueConstraint(columnNames={"request_id","deleted_on"})})
@Entity(name="PrepareQuoteImpl")
@DiscriminatorValue(	"prepare_quote_impl")
public class PrepareQuoteImpl extends PrepareQuote implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessProcess, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@IndexColumn(name="idx_in_cal_cus_on_p_q_i")
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=Call_Customer.class)
	@JoinColumn(name="process_object_id",nullable=true)
	protected List<Call_Customer> call_Customer = new ArrayList<Call_Customer>();
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="context_object_id",name="idx_prepare_quote_impl_context_object_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(insertable=false,name="context_object_id",nullable=true,updatable=false)
	protected MyBusiness contextObject;
	@Transient
	protected Object currentException;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<? extends PrepareQuoteImpl> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="process_request_id",name="idx_prepare_quote_impl_process_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="process_request_id",nullable=true)
	protected ProcessRequest processRequest;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="product_id",nullable=true)
	protected Product product;
	@Column(name="quantity")
	@Basic
	protected Integer quantity;
	@Index(columnNames="request_id",name="idx_prepare_quote_impl_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="required_by_date")
	protected Date requiredByDate;
	static final private long serialVersionUID = 595192293096445278l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PrepareQuoteImpl(MyBusiness owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PrepareQuoteImpl
	 */
	public PrepareQuoteImpl() {
	}

	public void addAllToCall_Customer(List<Call_Customer> call_Customer) {
		for ( Call_Customer o : call_Customer ) {
			addToCall_Customer(o);
		}
	}
	
	public void addToCall_Customer(Call_Customer call_Customer) {
		if ( call_Customer!=null ) {
			if ( call_Customer.getProcessObject()!=null ) {
				call_Customer.getProcessObject().removeFromCall_Customer(call_Customer);
			}
			call_Customer.z_internalAddToProcessObject(this);
		}
		z_internalAddToCall_Customer(call_Customer);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getContextObject().z_internalAddToPrepareQuoteImpl((PrepareQuoteImpl)this);
	}
	
	static public Set<? extends PrepareQuoteImpl> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.mybusiness.PrepareQuoteImpl.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("quantity").length()>0 ) {
			setQuantity(BpmmodelFormatter.getInstance().parseInteger(xml.getAttribute("quantity")));
		}
		if ( xml.getAttribute("requiredByDate").length()>0 ) {
			setRequiredByDate(BpmmodelFormatter.getInstance().parseDate(xml.getAttribute("requiredByDate")));
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
						this.z_internalAddToRequest(curVal);
						curVal.z_internalAddToRequestObject(this);
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
						this.z_internalAddToProcessRequest(curVal);
						curVal.z_internalAddToProcessObject(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("call_Customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("968116775093938922")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Call_Customer curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToCall_Customer(curVal);
						curVal.z_internalAddToProcessObject(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearCall_Customer() {
		List<Call_Customer> tmp = new ArrayList<Call_Customer>(getCall_Customer());
		for ( Call_Customer o : tmp ) {
			removeFromCall_Customer(o);
		}
	}
	
	public void complete() {
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
	
	public void copyShallowState(PrepareQuoteImpl from, PrepareQuoteImpl to) {
		if ( from.getRequest()!=null ) {
			to.z_internalAddToRequest(from.getRequest().makeShallowCopy());
		}
		if ( from.getProcessRequest()!=null ) {
			to.z_internalAddToProcessRequest(from.getProcessRequest().makeShallowCopy());
		}
		to.setQuantity(from.getQuantity());
		to.setRequiredByDate(from.getRequiredByDate());
	}
	
	public void copyState(PrepareQuoteImpl from, PrepareQuoteImpl to) {
		if ( from.getRequest()!=null ) {
			to.z_internalAddToRequest(from.getRequest().makeCopy());
		}
		if ( from.getProcessRequest()!=null ) {
			to.z_internalAddToProcessRequest(from.getProcessRequest().makeCopy());
		}
		to.setQuantity(from.getQuantity());
		to.setRequiredByDate(from.getRequiredByDate());
	}
	
	public Call_Customer createCall_Customer() {
		Call_Customer newInstance= new Call_Customer();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public ProcessRequest createProcessRequest() {
		ProcessRequest newInstance= new ProcessRequest();
		newInstance.init(this);
		return newInstance;
	}
	
	@NumlMetaInfo(uuid="bpm.uml@_v6iqwJBYEeKoCK1-ZvrleA")
	public void doActivityFinalNode16128872851993182546() {
	}
	
	@NumlMetaInfo(uuid="bpm.uml@_LZvx4JBZEeKoCK1-ZvrleA")
	public void doCall_Customer8905861491530021382() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PrepareQuoteImpl ) {
			return other==this || ((PrepareQuoteImpl)other).getUid().equals(this.getUid());
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=968116775093938922l,opposite="processObject",uuid="bpm.uml@_LZvx4JBZEeKoCK1-ZvrleA@MT")
	public List<Call_Customer> getCall_Customer() {
		List result = this.call_Customer;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5456835094313513887l,opposite="prepareQuoteImpl",uuid="bpm.uml@_Gld5EI_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_Gld5EI_hEeK855GX2Z3x4Q@contextObject")
	public MyBusiness getContextObject() {
		MyBusiness result = this.contextObject;
		
		return result;
	}
	
	public Object getCurrentException() {
		return this.currentException;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public String getName() {
		return "PrepareQuoteImpl["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7582620149530352865l,uuid="bpm.uml@_KczXAI_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_KczXAI_hEeK855GX2Z3x4Q@TEPB")
	public Product getProduct() {
		Product result = this.product;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6503023890571024531l,uuid="bpm.uml@_40zJEI_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_40zJEI_hEeK855GX2Z3x4Q@TEPB")
	public Integer getQuantity() {
		Integer result = this.quantity;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6849052820632333237l,opposite="requestObject",uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	public AbstractRequest getRequest() {
		AbstractRequest result = getProcessRequest();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2782624712183248461l,strategyFactory=DateStrategyFactory.class,uuid="bpm.uml@_6TAMcI_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_6TAMcI_hEeK855GX2Z3x4Q@TEPB")
	public Date getRequiredByDate() {
		Date result = this.requiredByDate;
		
		return result;
	}
	
	public PrepareQuoteImpl getSelf() {
		PrepareQuoteImpl result = this;
		
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
		this.z_internalAddToContextObject((MyBusiness)owner);
	}
	
	public PrepareQuoteImpl makeCopy() {
		PrepareQuoteImpl result = new PrepareQuoteImpl();
		copyState((PrepareQuoteImpl)this,result);
		return result;
	}
	
	public PrepareQuoteImpl makeShallowCopy() {
		PrepareQuoteImpl result = new PrepareQuoteImpl();
		copyShallowState((PrepareQuoteImpl)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getContextObject()!=null ) {
			getContextObject().z_internalRemoveFromPrepareQuoteImpl(this);
		}
		if ( getRequest()!=null ) {
			getRequest().markDeleted();
		}
		if ( getProcessRequest()!=null ) {
			getProcessRequest().markDeleted();
		}
		for ( Call_Customer child : new ArrayList<Call_Customer>(getCall_Customer()) ) {
			child.markDeleted();
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
	
	public void onCallCustomerCompleted(IToken callingToken, Call_Customer completedWorkObject) {
		if ( callingToken.isActive() ) {
		
		}
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("product") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7582620149530352865")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Product product = (Product)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( product!=null ) {
							z_internalAddToProduct(product);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("call_Customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("968116775093938922")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Call_Customer)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromCall_Customer(List<? extends Call_Customer> call_Customer) {
		List<Call_Customer> tmp = new ArrayList<Call_Customer>(call_Customer);
		for ( Call_Customer o : tmp ) {
			removeFromCall_Customer(o);
		}
	}
	
	public void removeFromCall_Customer(Call_Customer call_Customer) {
		if ( call_Customer!=null ) {
			call_Customer.z_internalRemoveFromProcessObject(this);
			z_internalRemoveFromCall_Customer(call_Customer);
			call_Customer.markDeleted();
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCall_Customer(List<Call_Customer> call_Customer) {
		this.clearCall_Customer();
		this.addAllToCall_Customer(call_Customer);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setContextObject(MyBusiness contextObject) {
		if ( this.getContextObject()!=null ) {
			this.getContextObject().z_internalRemoveFromPrepareQuoteImpl(this);
		}
		if ( contextObject == null ) {
			this.z_internalRemoveFromContextObject(this.getContextObject());
		} else {
			this.z_internalAddToContextObject(contextObject);
		}
		if ( contextObject!=null ) {
			contextObject.z_internalAddToPrepareQuoteImpl(this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setCurrentException(Object currentException) {
		this.currentException=currentException;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setProcessRequest(ProcessRequest processRequest) {
		ProcessRequest oldValue = this.getProcessRequest();
		if ( oldValue==null ) {
			if ( processRequest!=null ) {
				PrepareQuoteImpl oldOther = (PrepareQuoteImpl)processRequest.getProcessObject();
				processRequest.z_internalRemoveFromProcessObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromProcessRequest(processRequest);
				}
				processRequest.z_internalAddToProcessObject((PrepareQuoteImpl)this);
			}
			this.z_internalAddToProcessRequest(processRequest);
		} else {
			if ( !oldValue.equals(processRequest) ) {
				oldValue.z_internalRemoveFromProcessObject(this);
				z_internalRemoveFromProcessRequest(oldValue);
				if ( processRequest!=null ) {
					PrepareQuoteImpl oldOther = (PrepareQuoteImpl)processRequest.getProcessObject();
					processRequest.z_internalRemoveFromProcessObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromProcessRequest(processRequest);
					}
					processRequest.z_internalAddToProcessObject((PrepareQuoteImpl)this);
				}
				this.z_internalAddToProcessRequest(processRequest);
			}
		}
	}
	
	public void setProduct(Product product) {
		if ( product == null ) {
			this.z_internalRemoveFromProduct(getProduct());
		} else {
			this.z_internalAddToProduct(product);
		}
	}
	
	public void setQuantity(Integer quantity) {
		if ( quantity == null ) {
			this.z_internalRemoveFromQuantity(getQuantity());
		} else {
			this.z_internalAddToQuantity(quantity);
		}
	}
	
	public void setRequest(AbstractRequest request) {
		AbstractRequest oldValue = this.getRequest();
		if ( oldValue==null ) {
			if ( request!=null ) {
				PrepareQuoteImpl oldOther = (PrepareQuoteImpl)request.getRequestObject();
				request.z_internalRemoveFromRequestObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromRequest(request);
				}
				request.z_internalAddToRequestObject((PrepareQuoteImpl)this);
			}
			this.z_internalAddToRequest(request);
		} else {
			if ( !oldValue.equals(request) ) {
				oldValue.z_internalRemoveFromRequestObject(this);
				z_internalRemoveFromRequest(oldValue);
				if ( request!=null ) {
					PrepareQuoteImpl oldOther = (PrepareQuoteImpl)request.getRequestObject();
					request.z_internalRemoveFromRequestObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromRequest(request);
					}
					request.z_internalAddToRequestObject((PrepareQuoteImpl)this);
				}
				this.z_internalAddToRequest(request);
			}
		}
	}
	
	public void setRequiredByDate(Date requiredByDate) {
		if ( requiredByDate == null ) {
			this.z_internalRemoveFromRequiredByDate(getRequiredByDate());
		} else {
			this.z_internalAddToRequiredByDate(requiredByDate);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<PrepareQuoteImpl uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PrepareQuoteImpl ");
		sb.append("classUuid=\"bpm.uml@_Gld5EI_hEeK855GX2Z3x4Q\" ");
		sb.append("className=\"bpmmodel.mybusiness.PrepareQuoteImpl\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getQuantity()!=null ) {
			sb.append("quantity=\""+ BpmmodelFormatter.getInstance().formatInteger(getQuantity())+"\" ");
		}
		if ( getRequiredByDate()!=null ) {
			sb.append("requiredByDate=\""+ BpmmodelFormatter.getInstance().formatDate(getRequiredByDate())+"\" ");
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
		if ( getProduct()==null ) {
			sb.append("\n<product/>");
		} else {
			sb.append("\n<product propertyId=\"7582620149530352865\">");
			sb.append("\n" + getProduct().toXmlReferenceString());
			sb.append("\n</product>");
		}
		sb.append("\n<call_Customer propertyId=\"968116775093938922\">");
		for ( Call_Customer call_Customer : getCall_Customer() ) {
			sb.append("\n" + call_Customer.toXmlString());
		}
		sb.append("\n</call_Customer>");
		sb.append("\n</PrepareQuoteImpl>");
		return sb.toString();
	}
	
	public void z_internalAddToCall_Customer(Call_Customer call_Customer) {
		if ( this.call_Customer.contains(call_Customer) ) {
			return;
		}
		this.call_Customer.add(call_Customer);
	}
	
	public void z_internalAddToContextObject(MyBusiness contextObject) {
		if ( contextObject.equals(this.contextObject) ) {
			return;
		}
		this.contextObject=contextObject;
	}
	
	public void z_internalAddToProcessRequest(ProcessRequest processRequest) {
		AbstractRequest request = processRequest;
		if ( processRequest.equals(this.processRequest) ) {
			return;
		}
		this.processRequest=processRequest;
		if ( request.equals(this.request) ) {
			return;
		}
		this.request=request;
	}
	
	public void z_internalAddToProduct(Product product) {
		if ( product.equals(this.product) ) {
			return;
		}
		this.product=product;
	}
	
	public void z_internalAddToQuantity(Integer quantity) {
		if ( quantity.equals(this.quantity) ) {
			return;
		}
		this.quantity=quantity;
	}
	
	public void z_internalAddToRequest(AbstractRequest request) {
		ProcessRequest processRequest = (ProcessRequest)request;
		if ( request.equals(this.request) ) {
			return;
		}
		this.request=request;
		if ( processRequest.equals(this.processRequest) ) {
			return;
		}
		this.processRequest=processRequest;
	}
	
	public void z_internalAddToRequiredByDate(Date requiredByDate) {
		if ( requiredByDate.equals(this.requiredByDate) ) {
			return;
		}
		this.requiredByDate=requiredByDate;
	}
	
	public void z_internalRemoveFromCall_Customer(Call_Customer call_Customer) {
		this.call_Customer.remove(call_Customer);
	}
	
	public void z_internalRemoveFromContextObject(MyBusiness contextObject) {
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
	
	public void z_internalRemoveFromProduct(Product product) {
		if ( getProduct()!=null && product!=null && product.equals(getProduct()) ) {
			this.product=null;
			this.product=null;
		}
	}
	
	public void z_internalRemoveFromQuantity(Integer quantity) {
		if ( getQuantity()!=null && quantity!=null && quantity.equals(getQuantity()) ) {
			this.quantity=null;
			this.quantity=null;
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
	
	public void z_internalRemoveFromRequiredByDate(Date requiredByDate) {
		if ( getRequiredByDate()!=null && requiredByDate!=null && requiredByDate.equals(getRequiredByDate()) ) {
			this.requiredByDate=null;
			this.requiredByDate=null;
		}
	}

	@Override
	public boolean isStepActive(Class<? extends IExecutionElement> step){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IProcessStep getInnermostNonParallelStep(){
		// TODO Auto-generated method stub
		return null;
	}

}