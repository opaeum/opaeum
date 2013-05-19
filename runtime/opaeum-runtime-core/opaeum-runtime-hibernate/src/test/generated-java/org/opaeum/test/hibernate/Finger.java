package org.opaeum.test.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.hibernate.util.ModelFormatter;
import org.opaeum.test.hibernate.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="model.uml@_5bDEAHdnEeK1avyEuWoMVw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="finger")
@Entity(name="Finger")
public class Finger implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="hand_id",name="idx_finger_hand_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="hand_id",nullable=true)
	protected Hand hand;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="finger",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Finger> mockedAllInstances;
	@Column(name="name")
	@Basic
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 3402930467607875873l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Finger(Hand owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Finger
	 */
	public Finger() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHand().z_internalAddToFinger((Finger)this);
	}
	
	static public Set<? extends Finger> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.hibernate.Finger.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(ModelFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Finger from, Finger to) {
		to.setName(from.getName());
	}
	
	public void copyState(Finger from, Finger to) {
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Finger ) {
			return other==this || ((Finger)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7816469590199664793l,opposite="finger",uuid="model.uml@_7XGhAXdnEeK1avyEuWoMVw")
	@NumlMetaInfo(uuid="model.uml@_7XGhAXdnEeK1avyEuWoMVw")
	public Hand getHand() {
		Hand result = this.hand;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7766697773952373135l,uuid="model.uml@_AbC8AHdoEeK1avyEuWoMVw")
	@NumlMetaInfo(uuid="model.uml@_AbC8AHdoEeK1avyEuWoMVw")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getHand();
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
		this.z_internalAddToHand((Hand)owner);
	}
	
	public Finger makeCopy() {
		Finger result = new Finger();
		copyState((Finger)this,result);
		return result;
	}
	
	public Finger makeShallowCopy() {
		Finger result = new Finger();
		copyShallowState((Finger)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getHand()!=null ) {
			getHand().z_internalRemoveFromFinger(this);
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
	
	public void setHand(Hand hand) {
		if ( this.getHand()!=null ) {
			this.getHand().z_internalRemoveFromFinger(this);
		}
		if ( hand == null ) {
			this.z_internalRemoveFromHand(this.getHand());
		} else {
			this.z_internalAddToHand(hand);
		}
		if ( hand!=null ) {
			hand.z_internalAddToFinger(this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setName(String name) {
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Finger uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Finger ");
		sb.append("classUuid=\"model.uml@_5bDEAHdnEeK1avyEuWoMVw\" ");
		sb.append("className=\"org.opaeum.test.hibernate.Finger\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Finger>");
		return sb.toString();
	}
	
	public void z_internalAddToHand(Hand hand) {
		if ( hand.equals(this.hand) ) {
			return;
		}
		this.hand=hand;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(this.name) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalRemoveFromHand(Hand hand) {
		if ( getHand()!=null && hand!=null && hand.equals(getHand()) ) {
			this.hand=null;
			this.hand=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}

}