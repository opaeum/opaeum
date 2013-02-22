package org.opaeum.runtime.bpm.request;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_jRjnII6MEeCrtavWRHwoHg")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="participation",schema="bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Participation")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Participation implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="participation",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Participation> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="participation")
	protected ParticipationParticipant participationParticipant_participant;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 3694398622584451528l;
	private String uid;

	/** Default constructor for Participation
	 */
	public Participation() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Participation> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.request.Participation.class));
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
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Participation["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1801402104881341029l,opposite="participation",uuid="252060@_3YyGlIoXEeCPduia_-NbFw")
	public IParticipant getParticipant() {
		IParticipant result = null;
		if ( this.participationParticipant_participant!=null ) {
			result = this.participationParticipant_participant.getParticipant();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7938655185881125361l,opposite="participation",uuid="252060@_3YyGkIoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_jRjnII6MEeCrtavWRHwoHg@252060@_3YyGkIoXEeCPduia_-NbFw")
	public ParticipationParticipant getParticipationParticipant_participant() {
		ParticipationParticipant result = this.participationParticipant_participant;
		
		return result;
	}
	
	public ParticipationParticipant getParticipationParticipant_participantFor(IParticipant match) {
		if ( participationParticipant_participant.getParticipant().equals(match) ) {
			return participationParticipant_participant;
		} else {
			return null;
		}
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
			getParticipant().z_internalRemoveFromParticipation(this);
		}
		if ( getParticipationParticipant_participant()!=null ) {
			getParticipationParticipant_participant().z_internalRemoveFromParticipation(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationParticipant_participant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7938655185881125361")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setParticipationParticipant_participant((ParticipationParticipant)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
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
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipant(IParticipant participant) {
		propertyChangeSupport.firePropertyChange("participant",getParticipant(),participant);
		if ( this.getParticipant()!=null ) {
			this.getParticipant().z_internalRemoveFromParticipation(this);
		}
		if ( participant!=null ) {
			this.z_internalAddToParticipant(participant);
		}
	}
	
	public void setParticipationParticipant_participant(ParticipationParticipant participationParticipant_participant) {
		ParticipationParticipant oldValue = this.getParticipationParticipant_participant();
		propertyChangeSupport.firePropertyChange("participationParticipant_participant",getParticipationParticipant_participant(),participationParticipant_participant);
		if ( oldValue==null ) {
			if ( participationParticipant_participant!=null ) {
				Participation oldOther = (Participation)participationParticipant_participant.getParticipation();
				participationParticipant_participant.z_internalRemoveFromParticipation(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromParticipationParticipant_participant(participationParticipant_participant);
				}
				participationParticipant_participant.z_internalAddToParticipation((Participation)this);
			}
			this.z_internalAddToParticipationParticipant_participant(participationParticipant_participant);
		} else {
			if ( !oldValue.equals(participationParticipant_participant) ) {
				oldValue.z_internalRemoveFromParticipation(this);
				z_internalRemoveFromParticipationParticipant_participant(oldValue);
				if ( participationParticipant_participant!=null ) {
					Participation oldOther = (Participation)participationParticipant_participant.getParticipation();
					participationParticipant_participant.z_internalRemoveFromParticipation(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromParticipationParticipant_participant(participationParticipant_participant);
					}
					participationParticipant_participant.z_internalAddToParticipation((Participation)this);
				}
				this.z_internalAddToParticipationParticipant_participant(participationParticipant_participant);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Participation uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Participation ");
		sb.append("classUuid=\"252060@_jRjnII6MEeCrtavWRHwoHg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.request.Participation\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getParticipationParticipant_participant()==null ) {
			sb.append("\n<participationParticipant_participant/>");
		} else {
			sb.append("\n<participationParticipant_participant propertyId=\"7938655185881125361\">");
			sb.append("\n" + getParticipationParticipant_participant().toXmlReferenceString());
			sb.append("\n</participationParticipant_participant>");
		}
		sb.append("\n</Participation>");
		return sb.toString();
	}
	
	public void z_internalAddToParticipant(IParticipant participant) {
		ParticipationParticipant newOne = new ParticipationParticipant(this,participant);
		this.z_internalAddToParticipationParticipant_participant(newOne);
		newOne.getParticipant().z_internalAddToParticipationParticipant_participation(newOne);
	}
	
	public void z_internalAddToParticipationParticipant_participant(ParticipationParticipant participationParticipant_participant) {
		this.participationParticipant_participant=participationParticipant_participant;
	}
	
	public void z_internalRemoveFromParticipant(IParticipant participant) {
		if ( this.participationParticipant_participant!=null ) {
			this.participationParticipant_participant.clear();
		}
	}
	
	public void z_internalRemoveFromParticipationParticipant_participant(ParticipationParticipant participationParticipant_participant) {
		if ( getParticipationParticipant_participant()!=null && participationParticipant_participant!=null && participationParticipant_participant.equals(getParticipationParticipant_participant()) ) {
			this.participationParticipant_participant=null;
			this.participationParticipant_participant=null;
		}
	}

}