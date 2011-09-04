package org.nakeduml.runtime.bpm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Proxy;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.event.IEventHandler;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Proxy(lazy=false)
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="Participation")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="participation")
@NumlMetaInfo(qualifiedPersistentName="opium_library_for_bpm.participation",uuid="d643e291_b3f7_4edf_b57f_b4488fc8e6d5")
@AccessType("field")
public class Participation implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 16;
	@Index(name="idx_participation_participant",columnNames="participant")
	@Any(metaDef="Participant",metaColumn=@Column(name="participant_type"))
	@JoinColumn(name="participant",nullable=true)
	private Participant participant;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	static private Set<Participation> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;
	@Transient
	private Map<Object, String> cancelledEvents = new HashMap<Object,String>();
	@Transient
	private Map<Object, IEventHandler> outgoingEvents = new HashMap<Object,IEventHandler>();

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
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from Participation").list());
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
	
	public Map<Object, String> getCancelledEvents() {
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
	
	public Map<Object, IEventHandler> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="participation.participant",uuid="a66d140c_d460_445d_b76c_b9461aacf45b")
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
			getParticipant().z_internalRemoveFromParticipation((Participation)this);
		}
	}
	
	static public void mockAllInstances(Set<Participation> newMocks) {
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
	
	public void setCancelledEvents(Map<Object, String> cancelledEvents) {
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
	
	public void setOutgoingEvents(Map<Object, IEventHandler> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipant(Participant participant) {
		if ( this.getParticipant()!=null ) {
			this.getParticipant().z_internalRemoveFromParticipation((Participation)this);
		}
		if ( participant!=null ) {
			participant.z_internalAddToParticipation((Participation)this);
			this.z_internalAddToParticipant(participant);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<participation uid=\""+getUid() + "\">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<participation");
		sb.append(" className=\"org.nakeduml.runtime.bpm.Participation\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		sb.append(">\n");
		if ( getParticipant()==null ) {
			sb.append("<participant/>");
		} else {
			sb.append("<participant>");
			sb.append(getParticipant().toXmlReferenceString());
			sb.append("</participant>");
			sb.append("\n");
		}
		sb.append("</participation>");
		return sb.toString();
	}
	
	public void z_internalAddToParticipant(Participant val) {
		this.participant=val;
	}
	
	public void z_internalRemoveFromParticipant(Participant val) {
		if ( getParticipant()!=null && val!=null && val.equals(getParticipant()) ) {
			this.participant=null;
		}
	}

}