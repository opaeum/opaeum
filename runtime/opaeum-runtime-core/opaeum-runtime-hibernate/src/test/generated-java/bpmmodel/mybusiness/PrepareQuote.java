package bpmmodel.mybusiness;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.requestobject.IResponsibilityObject;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.FailedConstraintsException;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.domain.TaskDelegation;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bpmmodel.MyBusiness;
import bpmmodel.Product;
import bpmmodel.util.BpmmodelFormatter;
import bpmmodel.util.Stdlib;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="prepare_quote")
@Entity(name="PrepareQuote")
public class PrepareQuote implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IResponsibilityObject, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="context_object_id",name="idx_prepare_quote_context_object_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(insertable=false,name="context_object_id",nullable=true,updatable=false)
	protected MyBusiness contextObject;
	@Transient
	protected Object currentException;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="executed_on")
	private Date executedOn;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="prepare_quote",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends PrepareQuote> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="product_id",nullable=true)
	protected Product product;
	@Column(name="quantity")
	@Basic
	protected Integer quantity;
	@Index(columnNames="request_id",name="idx_prepare_quote_request_id")
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="required_by_date")
	protected Date requiredByDate;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="return_info"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="return_info_type"),name="classIdentifier")})
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 1688387446020186287l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PrepareQuote(MyBusiness owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PrepareQuote
	 */
	public PrepareQuote() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getContextObject().z_internalAddToPrepareQuote((PrepareQuote)this);
	}
	
	static public Set<? extends PrepareQuote> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.mybusiness.PrepareQuote.class));
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
		}
	}
	
	public void complete() {
		PrepareQuoteListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onPrepareQuoteComplete(getReturnInfo(),this);
		}
	}
	
	public boolean consumeOnActivatedOccurrence(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
		boolean result = false;
		
		return result;
	}
	
	public void createComponents() {
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
	
	public void execute() throws FailedConstraintsException {
		IToken token = getReturnInfo();
		evaluatePreconditions();
		setExecutedOn(new Date());
		getRequest().execute();
		((TaskRequest)this.getRequest()).setDelegation(TaskDelegation.ANYBODY);
	}
	
	public void generateOnActivatedEvent(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
	}
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
	}
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=571692760198162545l,opposite="prepareQuote",uuid="bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT")
	@NumlMetaInfo(uuid="bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT@contextObject")
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
	
	public Date getExecutedOn() {
		return this.executedOn;
	}
	
	public Long getId() {
		return this.id;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1131942331364790025l,uuid="bpm.uml@_Kcyv8I_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_Kcyv8I_hEeK855GX2Z3x4Q@TEPB")
	public Product getProduct() {
		Product result = this.product;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3714362005109338164l,uuid="bpm.uml@_40ws0I_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_40ws0I_hEeK855GX2Z3x4Q@TEPB")
	public Integer getQuantity() {
		Integer result = this.quantity;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6849052820632333237l,opposite="requestObject",uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	public AbstractRequest getRequest() {
		AbstractRequest result = this.request;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3765310416962651107l,strategyFactory=DateStrategyFactory.class,uuid="bpm.uml@_6S-XQI_hEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_6S-XQI_hEeK855GX2Z3x4Q@TEPB")
	public Date getRequiredByDate() {
		Date result = this.requiredByDate;
		
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
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getContextObject()!=null ) {
			getContextObject().z_internalRemoveFromPrepareQuote(this);
		}
		if ( getContextObject()!=null ) {
			getContextObject().z_internalRemoveFromPrepareQuote(this);
		}
		if ( getRequest()!=null ) {
			getRequest().markDeleted();
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
	
	public void onCompleted(IParticipant participant) {
		getCallingBehaviorExecution().onPrepareQuoteComplete(getReturnInfo(), this);
	}
	
	public void onStarted(IParticipant participant) {
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("product") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1131942331364790025")) ) {
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setContextObject(MyBusiness contextObject) {
		if ( this.getContextObject()!=null ) {
			this.getContextObject().z_internalRemoveFromPrepareQuote(this);
		}
		if ( contextObject == null ) {
			this.z_internalRemoveFromContextObject(this.getContextObject());
		} else {
			this.z_internalAddToContextObject(contextObject);
		}
		if ( contextObject!=null ) {
			contextObject.z_internalAddToPrepareQuote(this);
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
	
	public void setRequiredByDate(Date requiredByDate) {
		if ( requiredByDate == null ) {
			this.z_internalRemoveFromRequiredByDate(getRequiredByDate());
		} else {
			this.z_internalAddToRequiredByDate(requiredByDate);
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
	
	public String toXmlReferenceString() {
		return "<PrepareQuote uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PrepareQuote ");
		sb.append("classUuid=\"bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT\" ");
		sb.append("className=\"bpmmodel.mybusiness.PrepareQuote\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getQuantity()!=null ) {
			sb.append("quantity=\""+ BpmmodelFormatter.getInstance().formatInteger(getQuantity())+"\" ");
		}
		if ( getRequiredByDate()!=null ) {
			sb.append("requiredByDate=\""+ BpmmodelFormatter.getInstance().formatDate(getRequiredByDate())+"\" ");
		}
		sb.append(">");
		if ( getProduct()==null ) {
			sb.append("\n<product/>");
		} else {
			sb.append("\n<product propertyId=\"1131942331364790025\">");
			sb.append("\n" + getProduct().toXmlReferenceString());
			sb.append("\n</product>");
		}
		if ( getRequest()==null ) {
			sb.append("\n<request/>");
		} else {
			sb.append("\n<request propertyId=\"6849052820632333237\">");
			sb.append("\n" + getRequest().toXmlString());
			sb.append("\n</request>");
		}
		sb.append("\n</PrepareQuote>");
		return sb.toString();
	}
	
	public void z_internalAddToContextObject(MyBusiness contextObject) {
		if ( contextObject.equals(this.contextObject) ) {
			return;
		}
		this.contextObject=contextObject;
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
		if ( request.equals(this.request) ) {
			return;
		}
		this.request=request;
	}
	
	public void z_internalAddToRequiredByDate(Date requiredByDate) {
		if ( requiredByDate.equals(this.requiredByDate) ) {
			return;
		}
		this.requiredByDate=requiredByDate;
	}
	
	public void z_internalRemoveFromContextObject(MyBusiness contextObject) {
		if ( getContextObject()!=null && contextObject!=null && contextObject.equals(getContextObject()) ) {
			this.contextObject=null;
			this.contextObject=null;
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
		if ( getRequest()!=null && request!=null && request.equals(getRequest()) ) {
			this.request=null;
			this.request=null;
		}
	}
	
	public void z_internalRemoveFromRequiredByDate(Date requiredByDate) {
		if ( getRequiredByDate()!=null && requiredByDate!=null && requiredByDate.equals(getRequiredByDate()) ) {
			this.requiredByDate=null;
			this.requiredByDate=null;
		}
	}
	
	private MyBusiness getSelf() {
		MyBusiness result = getContextObject();
		
		return result;
	}

}