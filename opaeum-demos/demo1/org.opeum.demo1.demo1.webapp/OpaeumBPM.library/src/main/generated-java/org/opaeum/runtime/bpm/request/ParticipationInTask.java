package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_vZOC4I6UEeCne5ArYLDbiA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="participation_in_task")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="ParticipationInTask")
@DiscriminatorValue(	"participation_in_task")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class ParticipationInTask extends Participation implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Type(type="org.opaeum.runtime.bpm.request.TaskParticipationKindResolver")
	@Column(name="kind",nullable=true)
	private TaskParticipationKind kind;
	static private Set<ParticipationInTask> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	static final private long serialVersionUID = 8655129122508963499l;
	@Index(columnNames="task_request_id",name="idx_participation_in_task_task_request_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="task_request_id",nullable=true)
	private TaskRequest taskRequest;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParticipationInTask(TaskRequest owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for ParticipationInTask
	 */
	public ParticipationInTask() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getTaskRequest().z_internalAddToParticipationInTask((ParticipationInTask)this);
	}
	
	static public Set<? extends ParticipationInTask> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.request.ParticipationInTask.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("kind").length()>0 ) {
			setKind(TaskParticipationKind.valueOf(xml.getAttribute("kind")));
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=7015815221919899759l,opposite="participation",uuid="252060@_2tlBVI6UEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid="252060@_2tlBVI6UEeCne5ArYLDbiA")
	public TaskParticipationKind getKind() {
		TaskParticipationKind result = this.kind;
		
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=1010997141532452205l,opposite="participationInTask",uuid="252060@_BCPvEY6VEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid="252060@_BCPvEY6VEeCne5ArYLDbiA")
	public TaskRequest getTaskRequest() {
		TaskRequest result = this.taskRequest;
		
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
			getParticipant().z_internalRemoveFromParticipation(this);
		}
		if ( getTaskRequest()!=null ) {
			getTaskRequest().z_internalRemoveFromParticipationInTask(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<ParticipationInTask> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1801402104881341029")) ) {
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
	
	public void setKind(TaskParticipationKind kind) {
		this.z_internalAddToKind(kind);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setTaskRequest(TaskRequest taskRequest) {
		if ( this.getTaskRequest()!=null ) {
			this.getTaskRequest().z_internalRemoveFromParticipationInTask(this);
		}
		if ( taskRequest!=null ) {
			taskRequest.z_internalAddToParticipationInTask(this);
			this.z_internalAddToTaskRequest(taskRequest);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public String toXmlReferenceString() {
		return "<ParticipationInTask uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ParticipationInTask ");
		sb.append("classUuid=\"252060@_vZOC4I6UEeCne5ArYLDbiA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.ParticipationInTask\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getKind()!=null ) {
			sb.append("kind=\""+ getKind().name() + "\" ");
		}
		sb.append(">");
		if ( getParticipant()==null ) {
			sb.append("\n<participant/>");
		} else {
			sb.append("\n<participant propertyId=\"1801402104881341029\">");
			sb.append("\n" + getParticipant().toXmlReferenceString());
			sb.append("\n</participant>");
		}
		sb.append("\n</ParticipationInTask>");
		return sb.toString();
	}
	
	public void z_internalAddToKind(TaskParticipationKind val) {
		this.kind=val;
	}
	
	public void z_internalAddToTaskRequest(TaskRequest val) {
		this.taskRequest=val;
	}
	
	public void z_internalRemoveFromKind(TaskParticipationKind val) {
		if ( getKind()!=null && val!=null && val.equals(getKind()) ) {
			this.kind=null;
			this.kind=null;
		}
	}
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val) {
		if ( getTaskRequest()!=null && val!=null && val.equals(getTaskRequest()) ) {
			this.taskRequest=null;
			this.taskRequest=null;
		}
	}

}