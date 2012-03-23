package org.opaeum.runtime.bpm.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InterfaceValue;
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

@NumlMetaInfo(uuid="252060@_jRjnII6MEeCrtavWRHwoHg")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="participation")
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
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Participation> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="participant"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="participant_type"),name="classIdentifier")})
	private InterfaceValue participant = new InterfaceValue();
	@Transient
	private AbstractPersistence persistence;
	static final private long serialVersionUID = 3694398622584451528l;
	private String uid;

	/** Default constructor for Participation
	 */
	public Participation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Participation> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.request.Participation.class));
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=1801402104881341029l,opposite="participation",uuid="252060@_3YyGlIoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_3YyGlIoXEeCPduia_-NbFw")
	public Participant getParticipant() {
		Participant result = (Participant)this.participant.getValue(persistence);
		
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
		if ( getParticipant()!=null ) {
			getParticipant().z_internalRemoveFromParticipation(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Participation> newMocks) {
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
	
	public void setParticipant(Participant participant) {
		if ( this.getParticipant()!=null ) {
			this.getParticipant().z_internalRemoveFromParticipation(this);
		}
		if ( participant!=null ) {
			participant.z_internalAddToParticipation(this);
			this.z_internalAddToParticipant(participant);
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
		if ( getParticipant()==null ) {
			sb.append("\n<participant/>");
		} else {
			sb.append("\n<participant propertyId=\"1801402104881341029\">");
			sb.append("\n" + getParticipant().toXmlReferenceString());
			sb.append("\n</participant>");
		}
		sb.append("\n</Participation>");
		return sb.toString();
	}
	
	public void z_internalAddToParticipant(Participant val) {
		this.participant.setValue(val);
	}
	
	public void z_internalRemoveFromParticipant(Participant val) {
		if ( getParticipant()!=null && val!=null && val.equals(getParticipant()) ) {
			this.participant.setValue(null);
		}
	}

}