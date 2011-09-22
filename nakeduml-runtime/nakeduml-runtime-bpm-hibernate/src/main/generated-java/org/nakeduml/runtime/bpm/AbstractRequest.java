package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.ProcessContext;
import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.WorkflowProcess;
import org.jbpm.workflow.core.impl.NodeImpl;
import org.jbpm.workflow.core.impl.WorkflowProcessImpl;
import org.jbpm.workflow.instance.NodeInstanceContainer;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CancelledEvent;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.domain.OutgoingEvent;
import org.nakeduml.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Proxy(lazy=false)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="AbstractRequest")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="abstract_request")
@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.abstract_request",uuid="252060@_6MA8UI2-EeCrtavWRHwoHg")
@AccessType("field")
abstract public class AbstractRequest implements IEventGenerator, HibernateEntity, CompositionNode, Serializable, IPersistentObject, IProcessObject {
	private String uid;
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="request",targetEntity=ParticipationInRequest.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<ParticipationInRequest> participationsInRequest = new HashSet<ParticipationInRequest>();
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	transient private WorkflowProcessInstance processInstance;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Transient
	private boolean processDirty;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Type(type="AbstractRequestStateResolver")
	private AbstractRequestState endNodeInRegion1;
	static final private long serialVersionUID = 851;
	@Index(name="idx_abstract_request_parent_task_id",columnNames="parent_task_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="parent_task_id",nullable=true)
	private TaskRequest parentTask;
	static private Set<AbstractRequest> mockedAllInstances;

	/** Default constructor for AbstractRequest
	 */
	public AbstractRequest() {
	}

	@NumlMetaInfo(qualifiedPersistentName="abstract_request.activate",uuid="252060@_3USwcKDGEeCv9IRqC7lfYw")
	public void activate() {
		generateActivateEvent();
	}
	
	public void addAllToParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		for ( ParticipationInRequest o : participationsInRequest ) {
			addToParticipationsInRequest(o);
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.add_request_participant",uuid="252060@_Qo338I6QEeCrtavWRHwoHg")
	public void addRequestParticipant(Participant newParticipant, RequestParticipationKind kind) {
		ParticipationInRequest participation = null;
		ParticipationInRequest resultOnCreatePartipation = null;
		resultOnCreatePartipation=new ParticipationInRequest();
		participation=resultOnCreatePartipation;
		AbstractRequest tgtAddParticipation=this;
		tgtAddParticipation.addToParticipationsInRequest(participation);
		ParticipationInRequest tgtSetNewParticipant=participation;
		tgtSetNewParticipant.setParticipant(newParticipant);
		ParticipationInRequest tgtSetKind=participation;
		tgtSetKind.setKind(kind);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToParticipationsInRequest(ParticipationInRequest participationsInRequest) {
		if ( participationsInRequest!=null ) {
			participationsInRequest.z_internalRemoveFromRequest(participationsInRequest.getRequest());
			participationsInRequest.z_internalAddToRequest(this);
			z_internalAddToParticipationsInRequest(participationsInRequest);
		}
	}
	
	static public Set<? extends AbstractRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from AbstractRequest").list());
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("914")) ) {
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
	
	public void clearParticipationsInRequest() {
		removeAllFromParticipationsInRequest(getParticipationsInRequest());
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.complete",uuid="252060@_4RNcAIoaEeCPduia_-NbFw")
	public void complete() {
		generateCompleteEvent();
	}
	
	public boolean consumeActivateOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeAddRequestParticipantOccurrence(Participant newParticipant, RequestParticipationKind kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeCompleteOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeRemoveRequestParticipantOccurrence(Participant participant, RequestParticipationKind kind) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeResumeOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeStartOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeSuspendOccurrence() {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof AbstractRequest ) {
			return other==this || ((AbstractRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void execute() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		WorkflowProcessInstance processInstance;
		setExecutedOn(new Date());
		params.put("processObject", this);
		processInstance = (WorkflowProcessInstance)org.nakeduml.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).startProcess("opium_library_for_bpm_abstract_request",params);
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
		AbstractRequestState nextStep = (AbstractRequestState)step;
		NodeImpl newNode = getNodeForStep(nextStep);
		for ( NodeInstanceImpl curNodeInstance : getNodeInstancesRecursively() ) {
			if ( curNodeInstance.getNode().getNodeContainer()==newNode.getNodeContainer() ) {
				transition(curNodeInstance,newNode);
				return;
			}
		}
	}
	
	public void generateActivateEvent() {
	}
	
	public void generateAddRequestParticipantEvent(Participant newParticipant, RequestParticipationKind kind) {
	}
	
	public void generateCompleteEvent() {
	}
	
	public void generateRemoveRequestParticipantEvent(Participant participant, RequestParticipationKind kind) {
	}
	
	public void generateResumeEvent() {
	}
	
	public void generateStartEvent() {
	}
	
	public void generateSuspendEvent() {
	}
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
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
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.get_initiator",uuid="252060@_VJyB4I6REeCrtavWRHwoHg")
	public Participant getInitiator() {
		Participant result = null;
		result= any2().getParticipant();
		return result;
	}
	
	public IProcessStep getInnermostNonParallelStep() {
		if ( this.endNodeInRegion1!=null ) {
			return this.endNodeInRegion1;
		} else {
			NodeInstanceImpl nodeInstance = (NodeInstanceImpl)getProcessInstance().getNodeInstances().iterator().next();
			if ( getProcessInstance().getNodeInstances().size()>1 ) {
				return null;
			}
			while ( nodeInstance instanceof NodeInstanceContainer && ((NodeInstanceContainer)nodeInstance).getNodeInstances().size()==1 ) {
				nodeInstance=(NodeInstanceImpl)((NodeInstanceContainer)nodeInstance).getNodeInstances().iterator().next();
			}
			return AbstractRequestState.resolveById(nodeInstance.getNodeId());
		}
	}
	
	public String getName() {
		return "AbstractRequest["+getId()+"]";
	}
	
	public Collection<NodeInstanceImpl> getNodeInstancesRecursively() {
		return (Collection<NodeInstanceImpl>)(Collection)((NodeInstanceContainer)getProcessInstance()).getNodeInstances(true);
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
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.parent_task_id",uuid="252060@_towFgY29EeCrtavWRHwoHg")
	public TaskRequest getParentTask() {
		return parentTask;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.participations_in_request_id",uuid="252060@_XLHkUI6NEeCrtavWRHwoHg")
	public Set<ParticipationInRequest> getParticipationsInRequest() {
		return participationsInRequest;
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null ) {
			this.processInstance=(WorkflowProcessInstance)org.nakeduml.runtime.environment.Environment.getInstance().getComponent(StatefulKnowledgeSession.class).getProcessInstance(getProcessInstanceId());
			if ( this.processInstance!=null ) {
				((WorkflowProcessImpl)this.processInstance.getProcess()).setAutoComplete(true);
			}
		}
		return this.processInstance;
	}
	
	public Long getProcessInstanceId() {
		return this.processInstanceId;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.responsibility_object",uuid="252060@_lEGvZI53EeCfQedkc0TCdA")
	public RequestObject getResponsibilityObject() {
		return null;
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
	
	public void init(ProcessContext context) {
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
	}
	
	public void init(CompositionNode owner) {
	}
	
	public boolean isProcessDirty() {
		return this.processDirty;
	}
	
	public boolean isStepActive(IProcessStep step) {
		if ( step==this.endNodeInRegion1 ) {
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
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests((AbstractRequest)this);
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationsInRequest()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationsInRequest") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("914")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("parentTask") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("929")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public NodeImpl recursivelyFindNode(AbstractRequestState step, Collection<NodeImpl> collection) {
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
	
	public void removeAllFromParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		Set<ParticipationInRequest> tmp = new HashSet<ParticipationInRequest>(participationsInRequest);
		for ( ParticipationInRequest o : tmp ) {
			removeFromParticipationsInRequest(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipationsInRequest(ParticipationInRequest participationsInRequest) {
		if ( participationsInRequest!=null ) {
			participationsInRequest.z_internalRemoveFromRequest(this);
			z_internalRemoveFromParticipationsInRequest(participationsInRequest);
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.remove_request_participant",uuid="252060@_Nl5kQI6SEeCrtavWRHwoHg")
	public void removeRequestParticipant(Participant participant, RequestParticipationKind kind) {
		AbstractRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInRequest((select1(participant, kind)));
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.resume",uuid="252060@_qwWfEIoaEeCPduia_-NbFw")
	public void resume() {
		generateResumeEvent();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
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
		if ( this.getParentTask()!=null ) {
			this.getParentTask().z_internalRemoveFromSubRequests((AbstractRequest)this);
		}
		if ( parentTask!=null ) {
			parentTask.z_internalAddToSubRequests((AbstractRequest)this);
			this.z_internalAddToParentTask(parentTask);
		}
	}
	
	public void setParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		this.clearParticipationsInRequest();
		this.addAllToParticipationsInRequest(participationsInRequest);
	}
	
	public void setProcessInstance(WorkflowProcessInstance processInstance) {
		this.processInstance=processInstance;
	}
	
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId=processInstanceId;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.start",uuid="252060@_-PsMoIoaEeCPduia_-NbFw")
	public void start() {
		generateStartEvent();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.suspend",uuid="252060@_ov5DMIoaEeCPduia_-NbFw")
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
		sb.append("className=\"org.nakeduml.runtime.bpm.AbstractRequest\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n<participationsInRequest propertyId=\"914\">");
		for ( ParticipationInRequest participationsInRequest : getParticipationsInRequest() ) {
			sb.append("\n" + participationsInRequest.toXmlString());
		}
		sb.append("\n</participationsInRequest>");
		if ( getParentTask()==null ) {
			sb.append("\n<parentTask/>");
		} else {
			sb.append("\n<parentTask propertyId=\"929\">");
			sb.append("\n" + getParentTask().toXmlReferenceString());
			sb.append("\n</parentTask>");
		}
		sb.append("\n</AbstractRequest>");
		return sb.toString();
	}
	
	public void transition(NodeInstanceImpl curNodeInstance, NodeImpl newNode) {
		NodeInstanceContainer container = (NodeInstanceContainer)curNodeInstance.getNodeInstanceContainer();
		NodeInstanceImpl newNodeInstance = (NodeInstanceImpl)container.getNodeInstance(newNode);
		container.removeNodeInstance(curNodeInstance);
		curNodeInstance.trigger(newNodeInstance, "asdf");
	}
	
	public void z_internalAddToParentTask(TaskRequest val) {
		this.parentTask=val;
	}
	
	public void z_internalAddToParticipationsInRequest(ParticipationInRequest val) {
		this.participationsInRequest.add(val);
	}
	
	public void z_internalRemoveFromParentTask(TaskRequest val) {
		if ( getParentTask()!=null && val!=null && val.equals(getParentTask()) ) {
			this.parentTask=null;
		}
	}
	
	public void z_internalRemoveFromParticipationsInRequest(ParticipationInRequest val) {
		this.participationsInRequest.remove(val);
	}
	
	/** Implements ->any( p : ParticipationInRequest | p.kind = RequestParticipationKind::initiator )
	 */
	private ParticipationInRequest any2() {
		ParticipationInRequest result = null;
		for ( ParticipationInRequest p : this.getParticipationsInRequest() ) {
			if ( (p.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				return p;
			}
		}
		return result;
	}
	
	private NodeImpl getNodeForStep(AbstractRequestState step) {
		return recursivelyFindNode(step, (Collection)Arrays.asList(getProcessDefinition().getNodes()));
	}
	
	/** Implements ->select( p : ParticipationInRequest | (p.participant = participant) and p.kind = kind )
	 * 
	 * @param participant 
	 * @param kind 
	 */
	private Set<ParticipationInRequest> select1(Participant participant, RequestParticipationKind kind) {
		Set<ParticipationInRequest> result = new HashSet<ParticipationInRequest>();
		for ( ParticipationInRequest p : this.getParticipationsInRequest() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}

}