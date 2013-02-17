package org.opaeum.demo1.structuredbusiness.branch.branch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.bpm.request.TaskParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.bpm.requestobject.IStandaloneSingleScreenTask;
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
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_ylMisBYQEeKIFJAOfPz88A")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="standalone_single_screen_task1",schema="structuredbusiness",uniqueConstraints=
	@UniqueConstraint(columnNames={"request_id","deleted_on"}))
@Entity(name="StandaloneSingleScreenTask1")
public class StandaloneSingleScreenTask1 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IStandaloneSingleScreenTask, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="context_object_id",name="idx_standalone_single_screen_task1_context_object_id")
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
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="standalone_single_screen_task1",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends StandaloneSingleScreenTask1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="request_id",name="idx_standalone_single_screen_task1_request_id")
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
	static final private long serialVersionUID = 4759130792859575725l;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="task_request_id",nullable=true)
	protected TaskRequest taskRequest;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public StandaloneSingleScreenTask1(Branch owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for StandaloneSingleScreenTask1
	 */
	public StandaloneSingleScreenTask1() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getContextObject().z_internalAddToStandaloneSingleScreenTask1((StandaloneSingleScreenTask1)this);
	}
	
	static public Set<? extends StandaloneSingleScreenTask1> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.branch.branch.StandaloneSingleScreenTask1.class));
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
		}
	}
	
	public void complete() {
		StandaloneSingleScreenTask1Listener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onStandaloneSingleScreenTask1Complete(getReturnInfo(),this);
		}
	}
	
	public boolean consumeOnActivatedOccurrence(@ParameterMetaInfo(name="activatedBy",opaeumId=3572050351186706778l,uuid="252060@_YslssK0OEeCK48ywUpk_rg") IParticipant activatedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnClaimedOccurrence(@ParameterMetaInfo(name="claimedBy",opaeumId=7951140015384934948l,uuid="252060@_roekYK0NEeCK48ywUpk_rg") IParticipant claimedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnCompletedOccurrence(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnDelegatedOccurrence(@ParameterMetaInfo(name="delegatedBy",opaeumId=2937187766882494462l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") IParticipant delegatedBy, @ParameterMetaInfo(name="delegatedTo",opaeumId=9102602523982706512l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") IParticipant delegatedTo) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnForwardedOccurrence(@ParameterMetaInfo(name="forwardedBy",opaeumId=5405405098671388300l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") IParticipant forwardedBy, @ParameterMetaInfo(name="forwardedTo",opaeumId=3907720224420364138l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") IParticipant forwardedTo) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnResumedOccurrence(@ParameterMetaInfo(name="resumedBy",opaeumId=4926573096485933090l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") IParticipant resumedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnRevokedOccurrence(@ParameterMetaInfo(name="revokedBy",opaeumId=273103052661183192l,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") IParticipant revokedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnSkippedOccurrence(@ParameterMetaInfo(name="skippedBy",opaeumId=6103866618185670366l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") IParticipant skippedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStartedOccurrence(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnStoppedOccurrence(@ParameterMetaInfo(name="stoppedBy",opaeumId=6681754435738713602l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") IParticipant stoppedBy) {
		boolean result = false;
		
		return result;
	}
	
	public boolean consumeOnSuspendedOccurrence(@ParameterMetaInfo(name="suspendedBy",opaeumId=7500393394354827722l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") IParticipant suspendedBy) {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(StandaloneSingleScreenTask1 from, StandaloneSingleScreenTask1 to) {
	}
	
	public void copyState(StandaloneSingleScreenTask1 from, StandaloneSingleScreenTask1 to) {
		if ( from.getRequest()!=null ) {
			to.setRequest(from.getRequest().makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof StandaloneSingleScreenTask1 ) {
			return other==this || ((StandaloneSingleScreenTask1)other).getUid().equals(this.getUid());
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
	
	public void generateOnClaimedEvent(@ParameterMetaInfo(name="claimedBy",opaeumId=7951140015384934948l,uuid="252060@_roekYK0NEeCK48ywUpk_rg") IParticipant claimedBy) {
	}
	
	public void generateOnCompletedEvent(@ParameterMetaInfo(name="completedBy",opaeumId=2231883736722554878l,uuid="252060@_cHSR8K0OEeCK48ywUpk_rg") IParticipant completedBy) {
	}
	
	public void generateOnDelegatedEvent(@ParameterMetaInfo(name="delegatedBy",opaeumId=2937187766882494462l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") IParticipant delegatedBy, @ParameterMetaInfo(name="delegatedTo",opaeumId=9102602523982706512l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") IParticipant delegatedTo) {
	}
	
	public void generateOnForwardedEvent(@ParameterMetaInfo(name="forwardedBy",opaeumId=5405405098671388300l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") IParticipant forwardedBy, @ParameterMetaInfo(name="forwardedTo",opaeumId=3907720224420364138l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") IParticipant forwardedTo) {
	}
	
	public void generateOnResumedEvent(@ParameterMetaInfo(name="resumedBy",opaeumId=4926573096485933090l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") IParticipant resumedBy) {
	}
	
	public void generateOnRevokedEvent(@ParameterMetaInfo(name="revokedBy",opaeumId=273103052661183192l,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") IParticipant revokedBy) {
	}
	
	public void generateOnSkippedEvent(@ParameterMetaInfo(name="skippedBy",opaeumId=6103866618185670366l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") IParticipant skippedBy) {
	}
	
	public void generateOnStartedEvent(@ParameterMetaInfo(name="startedBy",opaeumId=2842620541558802106l,uuid="252060@_mYTrQK0NEeCK48ywUpk_rg") IParticipant startedBy) {
	}
	
	public void generateOnStoppedEvent(@ParameterMetaInfo(name="stoppedBy",opaeumId=6681754435738713602l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") IParticipant stoppedBy) {
	}
	
	public void generateOnSuspendedEvent(@ParameterMetaInfo(name="suspendedBy",opaeumId=7500393394354827722l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") IParticipant suspendedBy) {
	}
	
	public StandaloneSingleScreenTask1Listener getCallingBehaviorExecution() {
		StandaloneSingleScreenTask1Listener result = null;
		if ( getReturnInfo()!=null  ) {
			result=(StandaloneSingleScreenTask1Listener)getReturnInfo().getBehaviorExecution();
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7209825201188215075l,opposite="standaloneSingleScreenTask1",uuid="914890@_ylMisBYQEeKIFJAOfPz88A")
	@NumlMetaInfo(uuid="914890@_ylMisBYQEeKIFJAOfPz88A@contextObject")
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
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "StandaloneSingleScreenTask1["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6849052820632333237l,opposite="requestObject",uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvYY53EeCfQedkc0TCdA")
	public AbstractRequest getRequest() {
		AbstractRequest result = this.request;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1547652806773264111l,uuid="252060@_RczxwOtsEeGT0cXodbXPYg")
	@NumlMetaInfo(uuid="252060@_RczxwOtsEeGT0cXodbXPYg")
	public TaskRequest getTaskRequest() {
		TaskRequest result = this.taskRequest;
		
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
		this.setTaskRequest( ((TaskRequest) this.getRequest()) );
		createComponents();
	}
	
	public StandaloneSingleScreenTask1 makeCopy() {
		StandaloneSingleScreenTask1 result = new StandaloneSingleScreenTask1();
		copyState((StandaloneSingleScreenTask1)this,result);
		return result;
	}
	
	public StandaloneSingleScreenTask1 makeShallowCopy() {
		StandaloneSingleScreenTask1 result = new StandaloneSingleScreenTask1();
		copyShallowState((StandaloneSingleScreenTask1)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getContextObject()!=null ) {
			getContextObject().z_internalRemoveFromStandaloneSingleScreenTask1(this);
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
	
	@NumlMetaInfo(uuid="252060@_qTa18K0NEeCK48ywUpk_rg")
	public void onClaimed(@ParameterMetaInfo(name="claimedBy",opaeumId=7951140015384934948l,uuid="252060@_roekYK0NEeCK48ywUpk_rg") IParticipant claimedBy) {
		generateOnClaimedEvent(claimedBy);
	}
	
	public void onCompleted(IParticipant participant) {
		getCallingBehaviorExecution().onStandaloneSingleScreenTask1Complete(getReturnInfo(), this);
	}
	
	@NumlMetaInfo(uuid="252060@_EE4B0K0OEeCK48ywUpk_rg")
	public void onDelegated(@ParameterMetaInfo(name="delegatedBy",opaeumId=2937187766882494462l,uuid="252060@_Fe698K0OEeCK48ywUpk_rg") IParticipant delegatedBy, @ParameterMetaInfo(name="delegatedTo",opaeumId=9102602523982706512l,uuid="252060@_GAGYYK0OEeCK48ywUpk_rg") IParticipant delegatedTo) {
		generateOnDelegatedEvent(delegatedBy,delegatedTo);
	}
	
	@NumlMetaInfo(uuid="252060@_NdLN8K0OEeCK48ywUpk_rg")
	public void onForwarded(@ParameterMetaInfo(name="forwardedBy",opaeumId=5405405098671388300l,uuid="252060@_Qcqd4K0OEeCK48ywUpk_rg") IParticipant forwardedBy, @ParameterMetaInfo(name="forwardedTo",opaeumId=3907720224420364138l,uuid="252060@_Qv0DYK0OEeCK48ywUpk_rg") IParticipant forwardedTo) {
		generateOnForwardedEvent(forwardedBy,forwardedTo);
	}
	
	@NumlMetaInfo(uuid="252060@_8ba9IK0NEeCK48ywUpk_rg")
	public void onResumed(@ParameterMetaInfo(name="resumedBy",opaeumId=4926573096485933090l,uuid="252060@_9Y_YoK0NEeCK48ywUpk_rg") IParticipant resumedBy) {
		generateOnResumedEvent(resumedBy);
	}
	
	@NumlMetaInfo(uuid="252060@__imwgK0NEeCK48ywUpk_rg")
	public void onRevoked(@ParameterMetaInfo(name="revokedBy",opaeumId=273103052661183192l,uuid="252060@_AhkTIK0OEeCK48ywUpk_rg") IParticipant revokedBy) {
		generateOnRevokedEvent(revokedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_fdkRQK0OEeCK48ywUpk_rg")
	public void onSkipped(@ParameterMetaInfo(name="skippedBy",opaeumId=6103866618185670366l,uuid="252060@_hELpwK0OEeCK48ywUpk_rg") IParticipant skippedBy) {
		generateOnSkippedEvent(skippedBy);
	}
	
	public void onStarted(IParticipant participant) {
	}
	
	@NumlMetaInfo(uuid="252060@_zwcxEK0NEeCK48ywUpk_rg")
	public void onStopped(@ParameterMetaInfo(name="stoppedBy",opaeumId=6681754435738713602l,uuid="252060@_1Xu38K0NEeCK48ywUpk_rg") IParticipant stoppedBy) {
		generateOnStoppedEvent(stoppedBy);
	}
	
	@NumlMetaInfo(uuid="252060@_ug7_QK0NEeCK48ywUpk_rg")
	public void onSuspended(@ParameterMetaInfo(name="suspendedBy",opaeumId=7500393394354827722l,uuid="252060@_wGUosK0NEeCK48ywUpk_rg") IParticipant suspendedBy) {
		generateOnSuspendedEvent(suspendedBy);
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("taskRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1547652806773264111")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setTaskRequest((TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
		StandaloneSingleScreenTask1Listener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onStandaloneSingleScreenTask1UnhandledException(getReturnInfo(),exception, this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setContextObject(Branch contextObject) {
		propertyChangeSupport.firePropertyChange("contextObject",getContextObject(),contextObject);
		if ( this.getContextObject()!=null ) {
			this.getContextObject().z_internalRemoveFromStandaloneSingleScreenTask1(this);
		}
		if ( contextObject!=null ) {
			contextObject.z_internalAddToStandaloneSingleScreenTask1(this);
			this.z_internalAddToContextObject(contextObject);
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
	
	public void setRequest(AbstractRequest request) {
		AbstractRequest oldValue = this.getRequest();
		propertyChangeSupport.firePropertyChange("request",getRequest(),request);
		if ( oldValue==null ) {
			if ( request!=null ) {
				StandaloneSingleScreenTask1 oldOther = (StandaloneSingleScreenTask1)request.getRequestObject();
				request.z_internalRemoveFromRequestObject(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromRequest(request);
				}
				request.z_internalAddToRequestObject((StandaloneSingleScreenTask1)this);
			}
			this.z_internalAddToRequest(request);
		} else {
			if ( !oldValue.equals(request) ) {
				oldValue.z_internalRemoveFromRequestObject(this);
				z_internalRemoveFromRequest(oldValue);
				if ( request!=null ) {
					StandaloneSingleScreenTask1 oldOther = (StandaloneSingleScreenTask1)request.getRequestObject();
					request.z_internalRemoveFromRequestObject(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromRequest(request);
					}
					request.z_internalAddToRequestObject((StandaloneSingleScreenTask1)this);
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
	
	public void setTaskRequest(TaskRequest taskRequest) {
		propertyChangeSupport.firePropertyChange("taskRequest",getTaskRequest(),taskRequest);
		this.z_internalAddToTaskRequest(taskRequest);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<StandaloneSingleScreenTask1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<StandaloneSingleScreenTask1 ");
		sb.append("classUuid=\"914890@_ylMisBYQEeKIFJAOfPz88A\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.branch.branch.StandaloneSingleScreenTask1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getTaskRequest()==null ) {
			sb.append("\n<taskRequest/>");
		} else {
			sb.append("\n<taskRequest propertyId=\"1547652806773264111\">");
			sb.append("\n" + getTaskRequest().toXmlReferenceString());
			sb.append("\n</taskRequest>");
		}
		if ( getRequest()==null ) {
			sb.append("\n<request/>");
		} else {
			sb.append("\n<request propertyId=\"6849052820632333237\">");
			sb.append("\n" + getRequest().toXmlString());
			sb.append("\n</request>");
		}
		sb.append("\n</StandaloneSingleScreenTask1>");
		return sb.toString();
	}
	
	public void z_internalAddToContextObject(Branch contextObject) {
		this.contextObject=contextObject;
	}
	
	public void z_internalAddToRequest(AbstractRequest request) {
		this.request=request;
	}
	
	public void z_internalAddToTaskRequest(TaskRequest taskRequest) {
		this.taskRequest=taskRequest;
	}
	
	public void z_internalRemoveFromContextObject(Branch contextObject) {
		if ( getContextObject()!=null && contextObject!=null && contextObject.equals(getContextObject()) ) {
			this.contextObject=null;
			this.contextObject=null;
		}
	}
	
	public void z_internalRemoveFromRequest(AbstractRequest request) {
		if ( getRequest()!=null && request!=null && request.equals(getRequest()) ) {
			this.request=null;
			this.request=null;
		}
	}
	
	public void z_internalRemoveFromTaskRequest(TaskRequest taskRequest) {
		if ( getTaskRequest()!=null && taskRequest!=null && taskRequest.equals(getTaskRequest()) ) {
			this.taskRequest=null;
			this.taskRequest=null;
		}
	}

}