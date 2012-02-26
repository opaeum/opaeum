package org.opaeum.runtime.bpm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="ParticipationInTask")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",name="participation_in_task")
@NumlMetaInfo(uuid="252060@_vZOC4I6UEeCne5ArYLDbiA")
@AccessType("field")
@DiscriminatorValue("participation_in_task")
public class ParticipationInTask extends Participation implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private TaskParticipationKind _kind;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 45;
	static private Set<ParticipationInTask> mockedAllInstances;
	@Index(name="idx_participation_in_task_task_request_id",columnNames="task_request_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="task_request_id",nullable=true)
	private TaskRequest _taskRequest;

	/** Default constructor for ParticipationInTask
	 */
	public ParticipationInTask() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParticipationInTask(TaskRequest owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getTaskRequest().z_internalAddToParticipationsInTask((ParticipationInTask)this);
	}
	
	static public Set<? extends ParticipationInTask> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from ParticipationInTask").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_kind").length()>0 ) {
			setKind(TaskParticipationKind.valueOf(xml.getAttribute("_kind")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(ParticipationInTask from, ParticipationInTask to) {
		to.setKind(from.getKind());
	}
	
	public void copyState(ParticipationInTask from, ParticipationInTask to) {
		to.setKind(from.getKind());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ParticipationInTask ) {
			return other==this || ((ParticipationInTask)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(uuid="252060@_2tlBVI6UEeCne5ArYLDbiA")
	public TaskParticipationKind getKind() {
		TaskParticipationKind result = this._kind;
		
		return result;
	}
	
	public String getName() {
		return "ParticipationInTask["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getTaskRequest();
	}
	
	@NumlMetaInfo(uuid="252060@_BCPvEY6VEeCne5ArYLDbiA")
	public TaskRequest getTaskRequest() {
		TaskRequest result = this._taskRequest;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToTaskRequest((TaskRequest)owner);
		createComponents();
	}
	
	public ParticipationInTask makeCopy() {
		ParticipationInTask result = new ParticipationInTask();
		copyState((ParticipationInTask)this,result);
		return result;
	}
	
	public ParticipationInTask makeShallowCopy() {
		ParticipationInTask result = new ParticipationInTask();
		copyShallowState((ParticipationInTask)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipant()!=null ) {
			getParticipant().z_internalRemoveFromParticipation((ParticipationInTask)this);
		}
		if ( getTaskRequest()!=null ) {
			getTaskRequest().z_internalRemoveFromParticipationsInTask((ParticipationInTask)this);
		}
	}
	
	static public void mockAllInstances(Set<ParticipationInTask> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_participant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("188")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParticipant((Participant)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setKind(TaskParticipationKind _kind) {
		this.z_internalAddToKind(_kind);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setTaskRequest(TaskRequest _taskRequest) {
		if ( this.getTaskRequest()!=null ) {
			this.getTaskRequest().z_internalRemoveFromParticipationsInTask((ParticipationInTask)this);
		}
		if ( _taskRequest!=null ) {
			_taskRequest.z_internalAddToParticipationsInTask((ParticipationInTask)this);
			this.z_internalAddToTaskRequest(_taskRequest);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			setDeletedOn(new Date());
		}
	}
	
	public String toXmlReferenceString() {
		return "<ParticipationInTask uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ParticipationInTask ");
		sb.append("classUuid=\"252060@_vZOC4I6UEeCne5ArYLDbiA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.ParticipationInTask\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getKind()!=null ) {
			sb.append("_kind=\""+ getKind().name() + "\" ");
		}
		sb.append(">");
		if ( getParticipant()==null ) {
			sb.append("\n<_participant/>");
		} else {
			sb.append("\n<_participant propertyId=\"188\">");
			sb.append("\n" + getParticipant().toXmlReferenceString());
			sb.append("\n</_participant>");
		}
		sb.append("\n</ParticipationInTask>");
		return sb.toString();
	}
	
	public void z_internalAddToKind(TaskParticipationKind val) {
		this._kind=val;
	}
	
	public void z_internalAddToTaskRequest(TaskRequest val) {
		this._taskRequest=val;
	}
	
	public void z_internalRemoveFromKind(TaskParticipationKind val) {
		if ( getKind()!=null && val!=null && val.equals(getKind()) ) {
			this._kind=null;
		}
	}
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val) {
		if ( getTaskRequest()!=null && val!=null && val.equals(getTaskRequest()) ) {
			this._taskRequest=null;
		}
	}

}