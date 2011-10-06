package org.opeum.runtime.bpm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessContext;
import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Type;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.opeum.annotation.NumlMetaInfo;
import org.opeum.runtime.bpm.util.Stdlib;
import org.opeum.runtime.domain.CancelledEvent;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.domain.IEventGenerator;
import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.domain.IProcessObject;
import org.opeum.runtime.domain.IProcessStep;
import org.opeum.runtime.domain.IntrospectionUtil;
import org.opeum.runtime.domain.OutgoingEvent;
import org.opeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="ProcessRequest")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opium_bpm",name="process_request")
@NumlMetaInfo(uuid="252060@_ciiWAI2-EeCrtavWRHwoHg")
@AccessType("field")
@DiscriminatorValue("process_request")
public class ProcessRequest extends AbstractRequest implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject, IProcessObject {
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="calling_process_instance_id")
	private Long callingProcessInstanceId;
	private Object currentException;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Type(type="ProcessRequestStateResolver")
	private ProcessRequestState endNodeInProcessRequestRegion;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	private String callingNodeInstanceUniqueId;
	@Transient
	transient private WorkflowProcessInstance callingProcessInstance;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	private boolean processDirty;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	static final private long serialVersionUID = 46;
	static private Set<ProcessRequest> mockedAllInstances;

	/** Default constructor for ProcessRequest
	 */
	public ProcessRequest() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ProcessRequest(OperationProcessObject owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	@NumlMetaInfo(uuid="252060@_4zDaYK0wEeCTTvcJZSDicw")
	public void abort() {
		generateAbortEvent();
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends ProcessRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from ProcessRequest").list());
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("86")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationInRequest curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToParticipationsInRequest(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void completed() {
		AbstractRequestListener callbackListener = getCallingProcessObject();
		if ( callbackListener!=null ) {
			callbackListener.onAbstractRequestComplete(getCallingNodeInstanceUniqueId(),this);
		}
	}
	
	public boolean consumeAbortOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ProcessRequest ) {
			return other==this || ((ProcessRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void execute() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		WorkflowProcessInstance processInstance;
		setExecutedOn(new Date());
		params.put("processObject", this);
		processInstance = (WorkflowProcessInstance)org.opeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("opium_bpm_process_request",params);
		((WorkflowProcessImpl)processInstance.getProcess()).setAutoComplete(true);
		this.processInstance=processInstance;
		this.setProcessInstanceId(processInstance.getId());
	}
	
	public NodeInstanceImpl findNodeInstanceByUniqueId(String uniqueId) {
		for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
			if ( nodeInstance.getUniqueId().equals(uniqueId) ) {
				return nodeInstance;
			}
		}
		return null;
	}
	
	public NodeInstanceImpl findWaitingNodeByNodeId(long step) {
		for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
			if ( ((NodeImpl)nodeInstance.getNode()).getId()==step ) {
				return nodeInstance;
			}
		}
		return null;
	}
	
	public void forceToStep(IProcessStep step) {
		ProcessRequestState nextStep = (ProcessRequestState)step;
		NodeImpl newNode = getNodeForStep(nextStep);
		for ( NodeInstanceImpl curNodeInstance : getNodeInstancesRecursively() ) {
			if ( curNodeInstance.getNode().getNodeContainer()==newNode.getNodeContainer() ) {
				transition(curNodeInstance,newNode);
				return;
			}
		}
	}
	
	public void generateAbortEvent() {
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
	}
	
	public String getCallingNodeInstanceUniqueId() {
		return this.callingNodeInstanceUniqueId;
	}
	
	public WorkflowProcess getCallingProcessDefinition() {
		return (WorkflowProcess) getCallingProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getCallingProcessInstance() {
		if ( this.callingProcessInstance==null ) {
			this.callingProcessInstance=(WorkflowProcessInstance)org.opeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getCallingProcessInstanceId());
			if ( this.callingProcessInstance!=null ) {
				((WorkflowProcessImpl)this.callingProcessInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.callingProcessInstance;
	}
	
	public Long getCallingProcessInstanceId() {
		return this.callingProcessInstanceId;
	}
	
	public AbstractRequestListener getCallingProcessObject() {
		if ( getCallingProcessInstance()!=null  ) {
			AbstractRequestListener processObject = (AbstractRequestListener)getCallingProcessInstance().getVariable("processObject");
			return processObject;
		}
		return null;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Date getExecutedOn() {
		return this.executedOn;
	}
	
	public IProcessStep getInnermostNonParallelStep() {
		if ( this.endNodeInProcessRequestRegion!=null ) {
			return this.endNodeInProcessRequestRegion;
		} else {
			NodeInstanceImpl nodeInstance = (NodeInstanceImpl)getProcessInstance().getNodeInstances().iterator().next();
			if ( getProcessInstance().getNodeInstances().size()>1 ) {
				return null;
			}
			while ( nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1 ) {
				nodeInstance=(NodeInstanceImpl)((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next();
			}
			return ProcessRequestState.resolveById(nodeInstance.getNodeId());
		}
	}
	
	public String getName() {
		return "ProcessRequest["+getId()+"]";
	}
	
	public Collection<NodeInstanceImpl> getNodeInstancesRecursively() {
		return (Collection<NodeInstanceImpl>)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true);
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getProcessObject();
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null ) {
			this.processInstance=(WorkflowProcessInstance)org.opeum.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getProcessInstanceId());
			if ( this.processInstance!=null ) {
				((WorkflowProcessImpl)this.processInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.processInstance;
	}
	
	public Long getProcessInstanceId() {
		return this.processInstanceId;
	}
	
	@NumlMetaInfo(uuid="252060@_JY15xI3pEeCfQedkc0TCdA")
	public OperationProcessObject getProcessObject() {
		OperationProcessObject result = null;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
	}
	
	public void init(ProcessContext context) {
		super.init(context);
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
	}
	
	public boolean isProcessDirty() {
		return this.processDirty;
	}
	
	public boolean isStepActive(IProcessStep step) {
		if ( step==this.endNodeInProcessRequestRegion ) {
			return true;
		}
		if ( getProcessInstance()==null ) {
			return false;
		} else {
			for ( NodeInstanceImpl nodeInstance : getNodeInstancesRecursively() ) {
				if ( step.getId()==nodeInstance.getNodeId() ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests((ProcessRequest)this);
		}
	}
	
	static public void mockAllInstances(Set<ProcessRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("parentTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("88")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("86")) ) {
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
		AbstractRequestListener callbackListener = getCallingProcessObject();
		if ( callbackListener==null ) {
		
		} else {
			callbackListener.onAbstractRequestUnhandledException(getCallingNodeInstanceUniqueId(),exception, this);
		}
	}
	
	public NodeImpl recursivelyFindNode(ProcessRequestState step, Collection<NodeImpl> collection) {
		for ( NodeImpl curNode : collection ) {
			if ( curNode.getId()==step.getId() ) {
				return curNode;
			} else {
				if ( curNode instanceof NodeContainer ) {
					NodeImpl childNode = recursivelyFindNode(step, (Collection)Arrays.asList(((NodeContainer)curNode).getNodes()));
					if ( childNode!=null ) {
						return childNode;
					}
				}
			}
		}
		return null;
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCallingNodeInstanceUniqueId(String callingNodeInstanceUniqueId) {
		this.callingNodeInstanceUniqueId=callingNodeInstanceUniqueId;
	}
	
	public void setCallingProcessInstance(WorkflowProcessInstance callingProcessInstance) {
		this.callingProcessInstance=callingProcessInstance;
	}
	
	public void setCallingProcessInstanceId(Long callingProcessInstanceId) {
		this.callingProcessInstanceId=callingProcessInstanceId;
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
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
	
	public void setProcessInstance(WorkflowProcessInstance processInstance) {
		this.processInstance=processInstance;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId=processInstanceId;
	}
	
	public void setReturnInfo(ProcessContext context) {
		this.callingProcessInstanceId=context.getProcessInstance().getId();
		this.callingNodeInstanceUniqueId=((NodeInstanceImpl)context.getNodeInstance()).getUniqueId();
	}
	
	public String toXmlReferenceString() {
		return "<ProcessRequest uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ProcessRequest ");
		sb.append("classUuid=\"252060@_ciiWAI2-EeCrtavWRHwoHg\" ");
		sb.append("className=\"org.opeum.runtime.bpm.ProcessRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getParentTask()==null ) {
			sb.append("\n<parentTask/>");
		} else {
			sb.append("\n<parentTask propertyId=\"88\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</parentTask>");
		}
		sb.append("\n<participationsInRequest propertyId=\"86\">");
		for ( ParticipationInRequest participationsInRequest : getParticipationsInRequest() ) {
			sb.append("\n" + participationsInRequest.toXmlString());
		}
		sb.append("\n</participationsInRequest>");
		sb.append("\n</ProcessRequest>");
		return sb.toString();
	}
	
	public void transition(NodeInstanceImpl curNodeInstance, NodeImpl newNode) {
		NodeInstanceContainer container = (NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer();
		NodeInstanceImpl newNodeInstance = (NodeInstanceImpl)container.getNodeInstance(newNode);
		container.removeNodeInstance(curNodeInstance);
		curNodeInstance.trigger(newNodeInstance, "asdf");
	}
	
	private NodeImpl getNodeForStep(ProcessRequestState step) {
		return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()));
	}

}