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

@DiscriminatorValue("participation_in_request")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Table(name="null_participation_in_request")
@AccessType("field")
@NumlMetaInfo(persistentName="naked_uml_library_for_bpm.participation_in_request",nakedUmlId=3)
@Entity(name="ParticipationInRequest")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
public class ParticipationInRequest extends Participation implements CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 3;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private RequestParticipationKind kind;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@Index(name="idx_participation_in_request_request_id",columnNames="request_id")
	@JoinColumn(name="request_id",nullable=true)
	protected AbstractRequest request;
	static private Set<ParticipationInRequest> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParticipationInRequest(AbstractRequest owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public ParticipationInRequest() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getRequest().getParticipationsInRequest().add((ParticipationInRequest)this);
	}
	
	static public Set<? extends ParticipationInRequest> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from ParticipationInRequest").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void copyShallowState(ParticipationInRequest from, ParticipationInRequest to) {
		to.setKind(from.getKind());
	}
	
	public void copyState(ParticipationInRequest from, ParticipationInRequest to) {
		to.setKind(from.getKind());
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ParticipationInRequest ) {
			return other==this || ((ParticipationInRequest)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return deletedOn;
	}
	
	@NumlMetaInfo(persistentName="participation_in_request.kind",nakedUmlId=82)
	public RequestParticipationKind getKind() {
		return kind;
	}
	
	public String getName() {
		return "ParticipationInRequest["+getId()+"]";
	}
	
	public CompositionNode getOwningObject() {
		return getRequest();
	}
	
	@NumlMetaInfo(persistentName="participation_in_request.request_id",nakedUmlId=74)
	public AbstractRequest getRequest() {
		return request;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((AbstractRequest)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipant()!=null ) {
			getParticipant().getParticipation().remove((ParticipationInRequest)this);
		}
		if ( getRequest()!=null ) {
			getRequest().getParticipationsInRequest().remove((ParticipationInRequest)this);
		}
	}
	
	static public void mockAllInstances(Set<ParticipationInRequest> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setKind(RequestParticipationKind kind) {
		this.kind=kind;
	}
	
	public void setRequest(AbstractRequest request) {
		if ( this.request!=null ) {
			this.request.getParticipationsInRequest().remove((ParticipationInRequest)this);
		}
		if ( request!=null ) {
			request.getParticipationsInRequest().add((ParticipationInRequest)this);
			this.request=request;
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
		sb.append("kind=");
		sb.append(getKind());
		sb.append(";");
		if ( getRequest()==null ) {
			sb.append("request=null");
		} else {
			sb.append("request.id=");
			sb.append(getRequest().getId());
			sb.append(";");
		}
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
		if ( getKind()==null ) {
			sb.append("<kind/>");
		} else {
			sb.append("<kind>");
			sb.append(getKind());
			sb.append("</kind>");
			sb.append("\n");
		}
		if ( getRequest()==null ) {
			sb.append("<request/>");
		} else {
			sb.append("<request>");
			sb.append(getRequest());
			sb.append("</request>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToRequest(AbstractRequest request) {
		this.request=request;
	}
	
	public void z_internalRemoveFromRequest(AbstractRequest request) {
		if ( getRequest()!=null && getRequest().equals(request) ) {
			this.request=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(AbstractRequest newOwner) {
		this.request=newOwner;
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ParticipationInRequest makeCopy() {
		ParticipationInRequest result = new ParticipationInRequest();
		copyState((ParticipationInRequest)this,result);
		return result;
	}
	
	public ParticipationInRequest makeShallowCopy() {
		ParticipationInRequest result = new ParticipationInRequest();
		copyShallowState((ParticipationInRequest)this,result);
		result.setId(this.getId());
		return result;
	}

}