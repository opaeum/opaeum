package org.opaeum.test.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.hibernate.util.ModelFormatter;
import org.opaeum.test.hibernate.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="model.uml@_2V87UHdnEeK1avyEuWoMVw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="hand")
@Entity(name="Hand")
public class Hand implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="hand",targetEntity=Finger.class)
	protected Set<Finger> finger = new HashSet<Finger>();
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="hand",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Hand> mockedAllInstances;
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
	static final private long serialVersionUID = 8911816607800989409l;
	private String uid;

	/** Default constructor for Hand
	 */
	public Hand() {
	}

	public void addAllToFinger(Set<Finger> finger) {
		for ( Finger o : finger ) {
			addToFinger(o);
		}
	}
	
	public void addToFinger(Finger finger) {
		if ( finger!=null ) {
			if ( finger.getHand()!=null ) {
				finger.getHand().removeFromFinger(finger);
			}
			finger.z_internalAddToHand(this);
		}
		z_internalAddToFinger(finger);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Hand> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.hibernate.Hand.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("finger") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6367708381970660001")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Finger curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.hibernate.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToFinger(curVal);
						curVal.z_internalAddToHand(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearFinger() {
		Set<Finger> tmp = new HashSet<Finger>(getFinger());
		for ( Finger o : tmp ) {
			removeFromFinger(o);
		}
	}
	
	public void copyShallowState(Hand from, Hand to) {
		to.setName(from.getName());
	}
	
	public void copyState(Hand from, Hand to) {
		for ( Finger child : from.getFinger() ) {
			to.addToFinger(child.makeCopy());
		}
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public Finger createFinger() {
		Finger newInstance= new Finger();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Hand ) {
			return other==this || ((Hand)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6367708381970660001l,opposite="hand",uuid="model.uml@_7XEr0HdnEeK1avyEuWoMVw")
	@NumlMetaInfo(uuid="model.uml@_7XEr0HdnEeK1avyEuWoMVw")
	public Set<Finger> getFinger() {
		Set result = this.finger;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4505917115397142369l,uuid="model.uml@_-uZ0AHdnEeK1avyEuWoMVw")
	@NumlMetaInfo(uuid="model.uml@_-uZ0AHdnEeK1avyEuWoMVw")
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
		return null;
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
	}
	
	public Hand makeCopy() {
		Hand result = new Hand();
		copyState((Hand)this,result);
		return result;
	}
	
	public Hand makeShallowCopy() {
		Hand result = new Hand();
		copyShallowState((Hand)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( Finger child : new ArrayList<Finger>(getFinger()) ) {
			child.markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("finger") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6367708381970660001")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Finger)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromFinger(Set<? extends Finger> finger) {
		Set<Finger> tmp = new HashSet<Finger>(finger);
		for ( Finger o : tmp ) {
			removeFromFinger(o);
		}
	}
	
	public void removeFromFinger(Finger finger) {
		if ( finger!=null ) {
			finger.z_internalRemoveFromHand(this);
			z_internalRemoveFromFinger(finger);
			finger.markDeleted();
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
	
	public void setFinger(Set<Finger> finger) {
		this.clearFinger();
		this.addAllToFinger(finger);
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
		return "<Hand uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Hand ");
		sb.append("classUuid=\"model.uml@_2V87UHdnEeK1avyEuWoMVw\" ");
		sb.append("className=\"org.opaeum.test.hibernate.Hand\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<finger propertyId=\"6367708381970660001\">");
		for ( Finger finger : getFinger() ) {
			sb.append("\n" + finger.toXmlString());
		}
		sb.append("\n</finger>");
		sb.append("\n</Hand>");
		return sb.toString();
	}
	
	public void z_internalAddToFinger(Finger finger) {
		if ( this.finger.contains(finger) ) {
			return;
		}
		this.finger.add(finger);
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(this.name) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalRemoveFromFinger(Finger finger) {
		this.finger.remove(finger);
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}

}