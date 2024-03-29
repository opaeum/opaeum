package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
import org.opaeum.runtime.bpm.request.processrequest.ProcessRequestRegion;
import org.opaeum.runtime.bpm.requestobject.IProcessObject;
import org.opaeum.runtime.bpm.requestobject.IRequestObject;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IExecutionElement;
import org.opaeum.runtime.domain.IObserver;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IProcessStep;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.InterfaceValueOwner;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.statemachines.IStateMachineExecution;
import org.opaeum.runtime.statemachines.RegionActivation;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_ciiWAI2-EeCrtavWRHwoHg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="process_request",schema="bpm")
@Entity(name="ProcessRequest")
@DiscriminatorValue(	"process_request")
public class ProcessRequest extends AbstractRequest implements IStateMachineExecution, InterfaceValueOwner, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("processObject",IProcessObject.class);
	INTERFACE_FIELDS.put("requestObject",IRequestObject.class);
	}
	
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Transient
	protected Object currentException;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	private Map<String, IExecutionElement> executionElements;
	static private Set<? extends ProcessRequest> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="process_object"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="process_object_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue processObject;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="return_info"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="return_info_type"),name="classIdentifier")})
	private ReturnInfo returnInfo = new ReturnInfo();
	static final private long serialVersionUID = 8869913001046429952l;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ProcessRequest(IProcessObject owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for ProcessRequest
	 */
	public ProcessRequest() {
	}

	@NumlMetaInfo(uuid="252060@_4zDaYK0wEeCTTvcJZSDicw")
	public void abort() {
		generateAbortEvent();
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getProcessObject().z_internalAddToProcessRequest((ProcessRequest)this);
	}
	
	static public Set<? extends ProcessRequest> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.ProcessRequest.class));
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
						this.z_internalAddToParticipationInRequest(curVal);
						curVal.z_internalAddToRequest(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void complete() {
		ProcessRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener!=null ) {
			callbackListener.onAbstractRequestComplete(getReturnInfo(),this);
		}
	}
	
	public boolean consumeAbortOccurrence() {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(ProcessRequest from, ProcessRequest to) {
	}
	
	public void copyState(ProcessRequest from, ProcessRequest to) {
		for ( ParticipationInRequest child : from.getParticipationInRequest() ) {
			to.addToParticipationInRequest(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public IToken createToken(IToken smToken) {
		ProcessRequestToken result = new ProcessRequestToken((ProcessRequestToken)smToken);
		tokens.add(result);
		result.setBehaviorExecution(this);
		return result;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ProcessRequest ) {
			return other==this || ((ProcessRequest)other).getUid().equals(this.getUid());
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
		((ProcessRequestRegion)getExecutionElements().get(ProcessRequestRegion.ID)).initiate(null);
	}
	
	public void generateAbortEvent() {
	}
	
	public ProcessRequestListener getCallingBehaviorExecution() {
		ProcessRequestListener result = null;
		if ( getReturnInfo()!=null  ) {
			result=(ProcessRequestListener)getReturnInfo().getBehaviorExecution();
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
	
	public Map<String, IExecutionElement> getExecutionElements() {
		Map result = executionElements;
		if ( executionElements==null ) {
			Set<RegionActivation> regions = new HashSet<RegionActivation>();
			result=executionElements=new HashMap<String,org.opaeum.runtime.domain.IExecutionElement>();
			regions.add(new ProcessRequestRegion<ProcessRequest>(this));
			for ( RegionActivation ra : regions ) {
				ra.linkTransitions();
			}
			if ( getOwningObject() instanceof IObserver ) {
				((IObserver)getOwningObject()).registerObservations(executionElements);
			}
		}
		return result;
	}
	
	public Class getFieldType(String fieldName) {
		Class result = INTERFACE_FIELDS.get(fieldName);
		
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
		return "ProcessRequest["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getProcessObject();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5083858478754845985l,opposite="processRequest",uuid="252060@_JY15xI3pEeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_JY15xI3pEeCfQedkc0TCdA")
	public IProcessObject getProcessObject() {
		IProcessObject result = null;
		if ( this.processObject==null ) {
			this.processObject=new UiidBasedInterfaceValue();
		}
		result=(IProcessObject)this.processObject.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8918582809852333993l,opposite="request",uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	@NumlMetaInfo(uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	public IRequestObject getRequestObject() {
		IRequestObject result = getProcessObject();
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
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToProcessObject((IProcessObject)owner);
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
	
	public ProcessRequest makeCopy() {
		ProcessRequest result = new ProcessRequest();
		copyState((ProcessRequest)this,result);
		return result;
	}
	
	public ProcessRequest makeShallowCopy() {
		ProcessRequest result = new ProcessRequest();
		copyShallowState((ProcessRequest)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests(this);
		}
		if ( getRequestObject()!=null ) {
			getRequestObject().z_internalRemoveFromRequest(this);
		}
		if ( getProcessObject()!=null ) {
			getProcessObject().z_internalRemoveFromProcessRequest(this);
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
						TaskRequest parentTask = (TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( parentTask!=null ) {
							z_internalAddToParentTask(parentTask);
							parentTask.z_internalAddToSubRequests(this);
						}
					}
				}
			}
		}
	}
	
	public void propagateException(Object exception) {
		ProcessRequestListener callbackListener = getCallingBehaviorExecution();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onAbstractRequestUnhandledException(getReturnInfo(),exception, this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeToken(IToken smToken) {
		tokens.remove((ProcessRequestToken)smToken);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCurrentException(Object currentException) {
		this.currentException=currentException;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setProcessObject(IProcessObject processObject) {
		IProcessObject oldValue = this.getProcessObject();
		if ( oldValue==null ) {
			if ( processObject!=null ) {
				ProcessRequest oldOther = (ProcessRequest)processObject.getProcessRequest();
				processObject.z_internalRemoveFromProcessRequest(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromProcessObject(processObject);
				}
				processObject.z_internalAddToProcessRequest((ProcessRequest)this);
			}
			this.z_internalAddToProcessObject(processObject);
		} else {
			if ( !oldValue.equals(processObject) ) {
				oldValue.z_internalRemoveFromProcessRequest(this);
				z_internalRemoveFromProcessObject(oldValue);
				if ( processObject!=null ) {
					ProcessRequest oldOther = (ProcessRequest)processObject.getProcessRequest();
					processObject.z_internalRemoveFromProcessRequest(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromProcessObject(processObject);
					}
					processObject.z_internalAddToProcessRequest((ProcessRequest)this);
				}
				this.z_internalAddToProcessObject(processObject);
			}
		}
	}
	
	public void setRequestObject(IRequestObject requestObject) {
		IRequestObject oldValue = this.getRequestObject();
		if ( oldValue==null ) {
			if ( requestObject!=null ) {
				ProcessRequest oldOther = (ProcessRequest)requestObject.getRequest();
				requestObject.z_internalRemoveFromRequest(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromRequestObject(requestObject);
				}
				requestObject.z_internalAddToRequest((ProcessRequest)this);
			}
			this.z_internalAddToRequestObject(requestObject);
		} else {
			if ( !oldValue.equals(requestObject) ) {
				oldValue.z_internalRemoveFromRequest(this);
				z_internalRemoveFromRequestObject(oldValue);
				if ( requestObject!=null ) {
					ProcessRequest oldOther = (ProcessRequest)requestObject.getRequest();
					requestObject.z_internalRemoveFromRequest(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromRequestObject(requestObject);
					}
					requestObject.z_internalAddToRequest((ProcessRequest)this);
				}
				this.z_internalAddToRequestObject(requestObject);
			}
		}
	}
	
	public void setReturnInfo(IToken token) {
		if ( this.returnInfo==null ) {
			this.returnInfo=new ReturnInfo();
		}
		this.returnInfo.setValue(token);
	}
	
	public String toXmlReferenceString() {
		return "<ProcessRequest uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ProcessRequest ");
		sb.append("classUuid=\"252060@_ciiWAI2-EeCrtavWRHwoHg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.ProcessRequest\" ");
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
		sb.append("\n</ProcessRequest>");
		return sb.toString();
	}
	
	public void z_internalAddToProcessObject(IProcessObject processObject) {
		IRequestObject requestObject = processObject;
		if ( processObject.equals(getProcessObject()) ) {
			return;
		}
		if ( this.processObject==null ) {
			this.processObject=new UiidBasedInterfaceValue();
		}
		this.processObject.setValue(processObject);
		if ( requestObject.equals(getRequestObject()) ) {
			return;
		}
		if ( this.requestObject==null ) {
			this.requestObject=new UiidBasedInterfaceValue();
		}
		this.requestObject.setValue(requestObject);
	}
	
	public void z_internalAddToRequestObject(IRequestObject requestObject) {
		IProcessObject processObject = (IProcessObject)requestObject;
		if ( requestObject.equals(getRequestObject()) ) {
			return;
		}
		if ( this.requestObject==null ) {
			this.requestObject=new UiidBasedInterfaceValue();
		}
		this.requestObject.setValue(requestObject);
		if ( processObject.equals(getProcessObject()) ) {
			return;
		}
		if ( this.processObject==null ) {
			this.processObject=new UiidBasedInterfaceValue();
		}
		this.processObject.setValue(processObject);
	}
	
	public void z_internalRemoveFromProcessObject(IProcessObject processObject) {
		IRequestObject requestObject = processObject;
		if ( getProcessObject()!=null && processObject!=null && processObject.equals(getProcessObject()) ) {
			this.processObject.setValue(null);
		}
		if ( getRequestObject()!=null && requestObject!=null && requestObject.equals(getRequestObject()) ) {
			this.requestObject.setValue(null);
		}
	}
	
	public void z_internalRemoveFromRequestObject(IRequestObject requestObject) {
		IProcessObject processObject = (IProcessObject)requestObject;
		if ( getRequestObject()!=null && requestObject!=null && requestObject.equals(getRequestObject()) ) {
			this.requestObject.setValue(null);
		}
		if ( getProcessObject()!=null && processObject!=null && processObject.equals(getProcessObject()) ) {
			this.processObject.setValue(null);
		}
	}

}