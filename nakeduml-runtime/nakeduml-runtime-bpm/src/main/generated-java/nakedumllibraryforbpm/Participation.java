package nakedumllibraryforbpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

import nakedumllibraryforbpm.util.Stdlib;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Proxy;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType("field")
@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.participation",nakedUmlId=11)
@Proxy(lazy=false)
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Filter(name="noDeletedObjects")
@Entity(name="Participation")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="null_participation")
public class Participation implements CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 11;
	@JoinColumn(name="participant",nullable=true)
	@Index(name="idx_participation_participant",columnNames="participant")
	@Any(metaDef="Participant",metaColumn=@Column(name="participant_type"))
	private Participant participant;
	private String uid;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	static private Set<Participation> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;

	/** Default constructor for 
	 */
	public Participation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Participation> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from Participation").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void copyShallowState(Participation from, Participation to) {
	}
	
	public void copyState(Participation from, Participation to) {
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Participation ) {
			return other==this || ((Participation)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return deletedOn;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return "Participation["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@NumlMetaInfo(persistentName="participation.participant",nakedUmlId=71)
	public Participant getParticipant() {
		return participant;
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
		createComponents();
	}
	
	public Participation makeCopy() {
		Participation result = new Participation();
		copyState((Participation)this,result);
		return result;
	}
	
	public Participation makeShallowCopy() {
		Participation result = new Participation();
		copyShallowState((Participation)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getParticipant()!=null ) {
			getParticipant().getParticipation().remove((Participation)this);
		}
	}
	
	static public void mockAllInstances(Set<Participation> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setParticipant(Participant participant) {
		if ( this.participant!=null ) {
			this.participant.getParticipation().remove((Participation)this);
		}
		if ( participant!=null ) {
			participant.getParticipation().add((Participation)this);
			this.participant=participant;
		} else {
			this.participant=null;
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("participant=");
		sb.append(getParticipant());
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
		return sb.toString();
	}
	
	public void z_internalAddToParticipant(Participant participant) {
		this.participant=participant;
	}
	
	public void z_internalRemoveFromParticipant(Participant participant) {
		if ( getParticipant()!=null && getParticipant().equals(participant) ) {
			this.participant=null;
		}
	}

}