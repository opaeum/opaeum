package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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

import nakedumllibraryforbpm.util.Stdlib;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType("field")
@DiscriminatorValue("participation_in_task")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.participation_in_task",nakedUmlId=20)
@Filter(name="noDeletedObjects")
@Table(name="null_participation_in_task")
@Entity(name="ParticipationInTask")
public class ParticipationInTask extends Participation implements CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 20;
	@Index(name="idx_participation_in_task_task_request_id",columnNames="task_request_id")
	@JoinColumn(name="task_request_id",nullable=true)
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	protected TaskRequest taskRequest;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private TaskParticipationKind kind;
	static private Set<ParticipationInTask> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParticipationInTask(TaskRequest owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public ParticipationInTask() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getTaskRequest().getParticipationsInTask().add((ParticipationInTask)this);
	}
	
	static public Set<? extends ParticipationInTask> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from ParticipationInTask").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void copyShallowState(ParticipationInTask from, ParticipationInTask to) {
		to.setKind(from.getKind());
	}
	
	public void copyState(ParticipationInTask from, ParticipationInTask to) {
		to.setKind(from.getKind());
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ParticipationInTask ) {
			return other==this || ((ParticipationInTask)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return deletedOn;
	}
	
	@NumlMetaInfo(persistentName="participation_in_task.kind",nakedUmlId=39)
	public TaskParticipationKind getKind() {
		return kind;
	}
	
	public String getName() {
		return "ParticipationInTask["+getId()+"]";
	}
	
	public CompositionNode getOwningObject() {
		return getTaskRequest();
	}
	
	@NumlMetaInfo(persistentName="participation_in_task.task_request_id",nakedUmlId=73)
	public TaskRequest getTaskRequest() {
		return taskRequest;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((TaskRequest)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipant()!=null ) {
			getParticipant().getParticipation().remove((ParticipationInTask)this);
		}
		if ( getTaskRequest()!=null ) {
			getTaskRequest().getParticipationsInTask().remove((ParticipationInTask)this);
		}
	}
	
	static public void mockAllInstances(Set<ParticipationInTask> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setKind(TaskParticipationKind kind) {
		this.kind=kind;
	}
	
	public void setTaskRequest(TaskRequest taskRequest) {
		if ( this.taskRequest!=null ) {
			this.taskRequest.getParticipationsInTask().remove((ParticipationInTask)this);
		}
		if ( taskRequest!=null ) {
			taskRequest.getParticipationsInTask().add((ParticipationInTask)this);
			this.taskRequest=taskRequest;
			setDeletedOn(Stdlib.FUTURE);
		} else {
			setDeletedOn(new Date());
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("participant=");
		sb.append(getParticipant());
		sb.append(";");
		if ( getTaskRequest()==null ) {
			sb.append("taskRequest=null");
		} else {
			sb.append("taskRequest.id=");
			sb.append(getTaskRequest().getId());
			sb.append(";");
		}
		sb.append("kind=");
		sb.append(getKind());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getParticipant()==null ) {
			sb.append("<participant/>");
		} else {
			sb.append("<participant>");
			sb.append(getParticipant().getClass().getSimpleName());
			sb.append("[");
			sb.append(getParticipant().hashCode());
			sb.append("]");
			sb.append("</participant>");
			sb.append("\n");
		}
		if ( getTaskRequest()==null ) {
			sb.append("<taskRequest/>");
		} else {
			sb.append("<taskRequest>");
			sb.append(getTaskRequest());
			sb.append("</taskRequest>");
			sb.append("\n");
		}
		if ( getKind()==null ) {
			sb.append("<kind/>");
		} else {
			sb.append("<kind>");
			sb.append(getKind());
			sb.append("</kind>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToTaskRequest(TaskRequest taskRequest) {
		this.taskRequest=taskRequest;
	}
	
	public void z_internalRemoveFromTaskRequest(TaskRequest taskRequest) {
		if ( getTaskRequest()!=null && getTaskRequest().equals(taskRequest) ) {
			this.taskRequest=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(TaskRequest newOwner) {
		this.taskRequest=newOwner;
	}
	
	public void createComponents() {
		super.createComponents();
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

}