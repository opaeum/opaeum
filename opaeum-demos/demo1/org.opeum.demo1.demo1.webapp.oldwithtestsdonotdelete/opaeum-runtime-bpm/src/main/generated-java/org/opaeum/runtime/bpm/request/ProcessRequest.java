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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.hibernate.domain.ReturnInfo;
import org.opaeum.runtime.bpm.request.processrequest.ProcessRequestRegion;
import org.opaeum.runtime.bpm.requestobject.IProcessObject;
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
@NumlMetaInfo(uuid="252060@_ciiWAI2-EeCrtavWRHwoHg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="process_request")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="ProcessRequest")
@DiscriminatorValue(	"process_request")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class ProcessRequest extends AbstractRequest implements IStateMachineExecution, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
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
	static private Set<ProcessRequest> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="process_object"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="process_object_type"),name="classIdentifier")})
	private InterfaceValue processObject = new InterfaceValue();
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
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
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
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
	
	public void completed() {
		AbstractRequestListener callbackListener = getCallingBehaviorExecution();
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
		for ( ParticipationInRequest child : from.getParticipationInRequest() ) {
			to.addToParticipationInRequest(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ProcessRequestToken createToken(StateMachineToken smToken) {
		ProcessRequestToken result = new ProcessRequestToken((ProcessRequestToken)smToken);
		tokens.add(result);
		return result;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ProcessRequest ) {
			return other==this || ((ProcessRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void execute() {
		setExecutedOn(new Date());
	}
	
	public void generateAbortEvent() {
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
			new ProcessRequestRegion(this).linkTransitions();
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
		IProcessObject result = (IProcessObject)this.processObject.getValue(persistence);
		
		return result;
	}
	
	public IRequestObject getRequestObject() {
		IRequestObject result = null;
		result=super.getRequestObject();
		if ( this.getProcessObject()!=null ) {
			result=this.getProcessObject();
		}
		return result;
	}
	
	public IToken getReturnInfo() {
		IToken result = this.returnInfo.getValue(persistence);
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToProcessObject((IProcessObject)owner);
		createComponents();
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
		if ( getProcessObject()!=null ) {
			getProcessObject().z_internalRemoveFromProcessRequest(this);
		}
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
	
	static public void mockAllInstances(Set<ProcessRequest> newMocks) {
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
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void removeToken(StateMachineToken smToken) {
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
	
	public void setExecutedOn(Date executedOn) {
		this.executedOn=executedOn;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setProcessObject(IProcessObject processObject) {
		IProcessObject oldValue = this.getProcessObject();
		propertyChangeSupport.firePropertyChange("processObject",getProcessObject(),processObject);
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
	
	public void setReturnInfo(IToken token) {
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
		sb.append("\n<participationInRequest propertyId=\"3022263813028286216\">");
		for ( ParticipationInRequest participationInRequest : getParticipationInRequest() ) {
			sb.append("\n" + participationInRequest.toXmlString());
		}
		sb.append("\n</participationInRequest>");
		sb.append("\n</ProcessRequest>");
		return sb.toString();
	}
	
	public void z_internalAddToProcessObject(IProcessObject val) {
		this.processObject.setValue(val);
	}
	
	public void z_internalRemoveFromProcessObject(IProcessObject val) {
		if ( getProcessObject()!=null && val!=null && val.equals(getProcessObject()) ) {
			this.processObject.setValue(null);
		}
	}

}