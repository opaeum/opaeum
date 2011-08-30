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
import javax.persistence.Enumerated;
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
import org.nakeduml.runtime.domain.ISignal;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IActiveObject;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IProcessObject;
import org.nakeduml.runtime.domain.IProcessStep;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Proxy(lazy=false)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="AbstractRequest")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="null_abstract_request")
@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.abstract_request",uuid="b6f61fb7_a6d0_4c24_b3e8_224cfbfcf089")
@AccessType("field")
abstract public class AbstractRequest implements CompositionNode, HibernateEntity, Serializable, IPersistentObject, IActiveObject, IProcessObject {
	static final private long serialVersionUID = 17;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="request",targetEntity=ParticipationInRequest.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<ParticipationInRequest> participationsInRequest = new HashSet<ParticipationInRequest>();
	@Index(name="idx_abstract_request_parent_task_id",columnNames="parent_task_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="parent_task_id",nullable=true)
	private TaskRequest parentTask;
	@Transient
	transient private WorkflowProcessInstance processInstance;
	@Column(name="process_instance_id")
	private Long processInstanceId;
	@Enumerated
	@Type(type="AbstractRequestStateResolver")
	private AbstractRequestState endNodeInRegion1;
	@Column(name="executed_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date executedOn;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	static private Set<AbstractRequest> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;

	/** Default constructor for AbstractRequest
	 */
	public AbstractRequest() {
	}

	@NumlMetaInfo(qualifiedPersistentName="abstract_request.activate",uuid="79817349_d260_44a0_af0f_0e752e34bb9e")
	public void activate() {
	}
	
	public void addAllToParticipationsInRequest(Set<ParticipationInRequest> participationsInRequest) {
		for ( ParticipationInRequest o : participationsInRequest ) {
			addToParticipationsInRequest(o);
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.add_request_participant",uuid="7fbf1d45_44f9_45d0_91e0_e55fe40720a6")
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
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participationsInRequest") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationInRequest curVal = (ParticipationInRequest)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.addToParticipationsInRequest(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearParticipationsInRequest() {
		removeAllFromParticipationsInRequest(getParticipationsInRequest());
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.complete",uuid="7c5cfc65_b4da_4818_9c2d_79f6da935243")
	public void complete() {
	}
	
	public ParticipationInRequest createParticipationsInRequest() {
		ParticipationInRequest newInstance= new ParticipationInRequest();
		newInstance.init(this);
		return newInstance;
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
	
	public Set<IProcessStep> getActiveLeafSteps() {
		Set results = new HashSet<IProcessStep>();
		return results;
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
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.get_initiator",uuid="45148b3f_79a0_46ba_b505_b40d26663c1c")
	public Participant getInitiator() {
		Participant result = null;
		result= any1().getParticipant();
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
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.parent_task_id",uuid="3a9699a4_258f_47a4_bf31_d99a1d6d6d9b")
	public TaskRequest getParentTask() {
		return parentTask;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.participations_in_request_id",uuid="3186e9cf_f4ac_4a35_b066_a6ff5dedff6c")
	public Set<ParticipationInRequest> getParticipationsInRequest() {
		return participationsInRequest;
	}
	
	public WorkflowProcess getProcessDefinition() {
		return (WorkflowProcess) getProcessInstance().getProcess();
	}
	
	public WorkflowProcessInstance getProcessInstance() {
		if ( this.processInstance==null || true ) {
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
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.responsibility_object",uuid="b2ced4df_9d38_4881_92f4_9aa6663daa71")
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
	
	public void init(CompositionNode owner) {
	}
	
	public void init(ProcessContext context) {
		this.setProcessInstanceId(context.getProcessInstance().getId());
		((WorkflowProcessImpl)context.getProcessInstance().getProcess()).setAutoComplete(true);
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getParentTask()!=null ) {
			getParentTask().z_internalRemoveFromSubRequests((AbstractRequest)this);
		}
		for ( ParticipationInRequest child : new ArrayList<ParticipationInRequest>(getParticipationsInRequest()) ) {
			child.markDeleted();
		}
	}
	
	static public void mockAllInstances(Set<AbstractRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participationsInRequest") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationInRequest)map.get(((Element)xml).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("parentTask") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParentTask((TaskRequest)map.get(((Element)xml).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public boolean processSignal(ISignal signal) {
		return false;
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
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.remove_request_participant",uuid="048f13d7_c73d_43ff_a6de_f05423518ae1")
	public void removeRequestParticipant(Participant participant, RequestParticipationKind kind) {
		AbstractRequest tgtRemoveParticipation=this;
		tgtRemoveParticipation.removeAllFromParticipationsInRequest((select2(participant, kind)));
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.resume",uuid="64d76ca2_374d_447c_9c76_e861db0dd383")
	public void resume() {
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
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.start",uuid="36828715_ab4f_4725_8518_66a481352aa3")
	public void start() {
	}
	
	@NumlMetaInfo(qualifiedPersistentName="abstract_request.suspend",uuid="b6837a3a_3b9e_4a67_ab1e_057218eec221")
	public void suspend() {
	}
	
	public String toXmlReferenceString() {
		return "<abstractRequest uid=\"+getUid() + \">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<abstractRequest");
		sb.append(" className=\"org.nakeduml.runtime.bpm.AbstractRequest\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		sb.append(">\n");
		if ( getParentTask()==null ) {
			sb.append("<parentTask/>");
		} else {
			sb.append(getParentTask().toXmlReferenceString());
			sb.append("</parentTask>");
			sb.append("\n");
		}
		sb.append("</AbstractRequest>");
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
	private ParticipationInRequest any1() {
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
	private Set<ParticipationInRequest> select2(Participant participant, RequestParticipationKind kind) {
		Set<ParticipationInRequest> result = new HashSet<ParticipationInRequest>();
		for ( ParticipationInRequest p : this.getParticipationsInRequest() ) {
			if ( (p.getParticipant().equals(participant) && (p.getKind().equals( kind))) ) {
				result.add( p );
			}
		}
		return result;
	}

}