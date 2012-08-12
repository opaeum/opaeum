package org.opaeum.runtime.bpm.request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.runtime.bpm.organization.Participant;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="252060@_3YyGkIoXEeCPduia_-NbFw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="participation_participant",uniqueConstraints=
	@UniqueConstraint(columnNames={"participation_id","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="ParticipationParticipant")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class ParticipationParticipant implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<ParticipationParticipant> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="participant"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="participant_type"),name="classIdentifier")})
	private InterfaceValue participant = new InterfaceValue();
	@Index(columnNames="participation_id",name="idx_participation_participant_participation_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="participation_id",nullable=true)
	private Participation participation;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6189386099840902425l;
	private String uid;

	/** Constructor for ParticipationParticipant
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public ParticipationParticipant(Participant end2, Participation end1) {
		this.z_internalAddToParticipation(end1);
		this.z_internalAddToParticipant(end2);
	}
	
	/** Constructor for ParticipationParticipant
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public ParticipationParticipant(Participation end1, Participant end2) {
		this.z_internalAddToParticipation(end1);
		this.z_internalAddToParticipant(end2);
	}
	
	/** Default constructor for ParticipationParticipant
	 */
	public ParticipationParticipant() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends ParticipationParticipant> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.ParticipationParticipant.class));
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
		
		}
	}
	
	public void clear() {
		getParticipant().z_internalRemoveFromParticipationParticipant_participation(this);
		this.z_internalRemoveFromParticipant(getParticipant());
		getParticipation().z_internalRemoveFromParticipationParticipant_participant(this);
		this.z_internalRemoveFromParticipation(getParticipation());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ParticipationParticipant ) {
			return other==this || ((ParticipationParticipant)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "ParticipationParticipant["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getParticipation();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1067114618754283926l,opposite="participationParticipant_participation",uuid="252060@_3YyGlIoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_3YyGkIoXEeCPduia_-NbFw@252060@_3YyGlIoXEeCPduia_-NbFw")
	public Participant getParticipant() {
		Participant result = (Participant)this.participant.getValue(persistence);
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3746223061979168312l,opposite="participationParticipant_participant",uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_3YyGkIoXEeCPduia_-NbFw@252060@_3YyGkYoXEeCPduia_-NbFw")
	public Participation getParticipation() {
		Participation result = this.participation;
		
		return result;
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
		this.z_internalAddToParticipation((Participation)owner);
	}
	
	public void markDeleted() {
		if ( getParticipation()!=null ) {
			getParticipation().z_internalRemoveFromParticipationParticipant_participant(this);
		}
		if ( getParticipant()!=null ) {
			getParticipant().z_internalRemoveFromParticipationParticipant_participation(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<ParticipationParticipant> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3746223061979168312")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParticipation((Participation)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1067114618754283926")) ) {
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
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
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
		propertyChangeSupport.firePropertyChange("participant",getParticipant(),participant);
		if ( this.getParticipant()!=null ) {
			this.getParticipant().z_internalRemoveFromParticipationParticipant_participation(this);
		}
		if ( participant!=null ) {
			participant.z_internalAddToParticipationParticipant_participation(this);
			this.z_internalAddToParticipant(participant);
		}
	}
	
	public void setParticipation(Participation participation) {
		Participation oldValue = this.getParticipation();
		propertyChangeSupport.firePropertyChange("participation",getParticipation(),participation);
		if ( oldValue==null ) {
			if ( participation!=null ) {
				ParticipationParticipant oldOther = (ParticipationParticipant)participation.getParticipationParticipant_participant();
				participation.z_internalRemoveFromParticipationParticipant_participant(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromParticipation(participation);
				}
				participation.z_internalAddToParticipationParticipant_participant((ParticipationParticipant)this);
			}
			this.z_internalAddToParticipation(participation);
		} else {
			if ( !oldValue.equals(participation) ) {
				oldValue.z_internalRemoveFromParticipationParticipant_participant(this);
				z_internalRemoveFromParticipation(oldValue);
				if ( participation!=null ) {
					ParticipationParticipant oldOther = (ParticipationParticipant)participation.getParticipationParticipant_participant();
					participation.z_internalRemoveFromParticipationParticipant_participant(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromParticipation(participation);
					}
					participation.z_internalAddToParticipationParticipant_participant((ParticipationParticipant)this);
				}
				this.z_internalAddToParticipation(participation);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<ParticipationParticipant uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ParticipationParticipant ");
		sb.append("classUuid=\"252060@_3YyGkIoXEeCPduia_-NbFw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.ParticipationParticipant\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getParticipation()==null ) {
			sb.append("\n<participation/>");
		} else {
			sb.append("\n<participation propertyId=\"3746223061979168312\">");
			sb.append("\n" + getParticipation().toXmlReferenceString());
			sb.append("\n</participation>");
		}
		if ( getParticipant()==null ) {
			sb.append("\n<participant/>");
		} else {
			sb.append("\n<participant propertyId=\"1067114618754283926\">");
			sb.append("\n" + getParticipant().toXmlReferenceString());
			sb.append("\n</participant>");
		}
		sb.append("\n</ParticipationParticipant>");
		return sb.toString();
	}
	
	public void z_internalAddToParticipant(Participant val) {
		this.participant.setValue(val);
	}
	
	public void z_internalAddToParticipation(Participation val) {
		this.participation=val;
	}
	
	public void z_internalRemoveFromParticipant(Participant val) {
		if ( getParticipant()!=null && val!=null && val.equals(getParticipant()) ) {
			this.participant.setValue(null);
		}
	}
	
	public void z_internalRemoveFromParticipation(Participation val) {
		if ( getParticipation()!=null && val!=null && val.equals(getParticipation()) ) {
			this.participation=null;
			this.participation=null;
		}
	}

}