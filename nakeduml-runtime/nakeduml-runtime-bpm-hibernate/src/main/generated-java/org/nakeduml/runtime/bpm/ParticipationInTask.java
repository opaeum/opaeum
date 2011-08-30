package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="ParticipationInTask")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="null_participation_in_task")
@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.participation_in_task",uuid="dabe8f9c_f994_4d74_808c_7f11adcaad0b")
@AccessType("field")
@DiscriminatorValue("participation_in_task")
public class ParticipationInTask extends Participation implements CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 20;
	@Index(name="idx_participation_in_task_task_request_id",columnNames="task_request_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="task_request_id",nullable=true)
	private TaskRequest taskRequest;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private TaskParticipationKind kind;
	static private Set<ParticipationInTask> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;

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
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from ParticipationInTask").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("kind")!=null ) {
			setKind(TaskParticipationKind.valueOf(xml.getAttribute("kind")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participant") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Participant curVal = (Participant)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.setParticipant(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
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
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="participation_in_task.kind",uuid="f505aebb_12ed_4321_a553_eb97f6def4a4")
	public TaskParticipationKind getKind() {
		return kind;
	}
	
	public String getName() {
		return "ParticipationInTask["+getId()+"]";
	}
	
	public CompositionNode getOwningObject() {
		return getTaskRequest();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="participation_in_task.task_request_id",uuid="665d54c5_c60f_49c6_ae71_d9eedda12b52")
	public TaskRequest getTaskRequest() {
		return taskRequest;
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
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("participant") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParticipant((Participant)map.get(((Element)xml).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setKind(TaskParticipationKind kind) {
		this.z_internalAddToKind(kind);
	}
	
	public void setTaskRequest(TaskRequest taskRequest) {
		if ( this.getTaskRequest()!=null ) {
			this.getTaskRequest().z_internalRemoveFromParticipationsInTask((ParticipationInTask)this);
		}
		if ( taskRequest!=null ) {
			taskRequest.z_internalAddToParticipationsInTask((ParticipationInTask)this);
			this.z_internalAddToTaskRequest(taskRequest);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			setDeletedOn(new Date());
		}
	}
	
	public String toXmlReferenceString() {
		return "<participationInTask uid=\"+getUid() + \">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<participationInTask");
		sb.append(" className=\"org.nakeduml.runtime.bpm.ParticipationInTask\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getKind()!=null ) {
			sb.append("kind=\""+ getKind() + "\" ");
		}
		sb.append(">\n");
		if ( getParticipant()==null ) {
			sb.append("<participant/>");
		} else {
			sb.append(getParticipant().toXmlReferenceString());
			sb.append("</participant>");
			sb.append("\n");
		}
		if ( getTaskRequest()==null ) {
			sb.append("<taskRequest/>");
		} else {
			sb.append(getTaskRequest().toXmlReferenceString());
			sb.append("</taskRequest>");
			sb.append("\n");
		}
		sb.append("</ParticipationInTask>");
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
		}
	}
	
	public void z_internalRemoveFromTaskRequest(TaskRequest val) {
		if ( getTaskRequest()!=null && val!=null && val.equals(getTaskRequest()) ) {
			this.taskRequest=null;
		}
	}

}