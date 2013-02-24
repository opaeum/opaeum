package ocltests;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import ocltests.util.OcltestsFormatter;
import ocltests.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyConstraint;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IConstrained;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="ocltests.uml@_ECviIOEgEeGhxPe2keryuw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="boat")
@Entity(name="Boat")
public class Boat implements IPersistentObject, IEventGenerator, IConstrained, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="boat",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Boat> mockedAllInstances;
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
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="boat",targetEntity=Sail.class)
	@MapKey(name="z_keyOfSailOnBoat")
	protected Map<String, Sail> sail = new HashMap<String,Sail>();
	static final private long serialVersionUID = 3955155691036485044l;
	private String uid;

	/** Default constructor for Boat
	 */
	public Boat() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToSail(SailPosition sailPosition, Sail sail) {
		if ( sail!=null ) {
			sail.z_internalRemoveFromBoat(sail.getBoat());
			sail.z_internalAddToBoat(this);
			z_internalAddToSail(sailPosition,sail);
		}
	}
	
	static public Set<? extends Boat> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(ocltests.Boat.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(OcltestsFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("sail") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("814077939394093143")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Sail curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=ocltests.util.OcltestsJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToSail(curVal.getSailPosition(),curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(uuid="ocltests.uml@_1HN10OFSEeGyXP1sbXN4GQ")
	public Integer calculateTimeForDistance(@ParameterMetaInfo(name="distance",opaeumId=1577379025219961793l,uuid="ocltests.uml@_Pwtc8OFTEeGyXP1sbXN4GQ") Integer distance) {
		Integer result = (Math.round(((distance.intValue() / this.getMaximumSpeed()) + 5)));
		
		return result;
	}
	
	public void clearSail() {
		Set<Sail> tmp = new HashSet<Sail>(getSail());
		for ( Sail o : tmp ) {
			removeFromSail(o.getSailPosition(),o);
		}
		sail.clear();
	}
	
	public void copyShallowState(Boat from, Boat to) {
		to.setName(from.getName());
	}
	
	public void copyState(Boat from, Boat to) {
		to.setName(from.getName());
		for ( Sail child : from.getSail() ) {
			to.addToSail(child.getSailPosition(),child.makeCopy());
		}
	}
	
	public void createComponents() {
		if ( getSail().isEmpty() ) {
			Sail newsail;
			newsail= new Sail();
			addToSail(ocltests.SailPosition.FRONT,newsail);
			newsail= new Sail();
			addToSail(ocltests.SailPosition.REAR,newsail);
		}
	}
	
	public Sail createSail(SailPosition sailPosition) {
		Sail newInstance= new Sail();
		newInstance.setSailPosition(sailPosition);
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Boat ) {
			return other==this || ((Boat)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Set<String> getFailedInvariants() {
		Set<String> failedInvariants = new HashSet<String>();
		if ( !isHasName() ) {
			failedInvariants.add("ocltests.Boat.hasName");
		}
		if ( !isMaximumSpeed() ) {
			failedInvariants.add("ocltests.Boat.maximumSpeed");
		}
		if ( !isHasSailForEachSailPosition() ) {
			failedInvariants.add("ocltests.Boat.hasSailForEachSailPosition");
		}
		if ( !isAllInstancesHaveSails() ) {
			failedInvariants.add("ocltests.Boat.allInstancesHaveSails");
		}
		return failedInvariants;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints=
		@PropertyConstraint(message="Maximum speed",method="isMaximumSpeed"),isComposite=false,opaeumId=3791110638885068466l,uuid="ocltests.uml@_hbIYcOEgEeGhxPe2keryuw")
	@NumlMetaInfo(uuid="ocltests.uml@_hbIYcOEgEeGhxPe2keryuw")
	public Integer getMaximumSpeed() {
		Integer result = (sum6() + this.getSail(SailPosition.FRONT).getSize());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3760641809463748788l,uuid="ocltests.uml@_VfaNUOEgEeGhxPe2keryuw")
	@NumlMetaInfo(uuid="ocltests.uml@_VfaNUOEgEeGhxPe2keryuw")
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
	
	public Sail getSail(SailPosition sailPosition) {
		Sail result = null;
		String key = sailPosition.getUid();
		result=this.sail.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=814077939394093143l,opposite="boat",uuid="ocltests.uml@_VNzyEOFDEeGyXP1sbXN4GQ")
	@NumlMetaInfo(uuid="ocltests.uml@_VNzyEOFDEeGyXP1sbXN4GQ")
	public Set<Sail> getSail() {
		Set result = new HashSet<Sail>(this.sail.values());
		
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
		this.setName( "MyNamessssss" );
	}
	
	public boolean isAllInstancesHaveSails() {
		boolean result = false;
		result = forAll4();
		return result;
	}
	
	public boolean isHasName() {
		boolean result = false;
		result = (collectionLiteral1().size() > 0);
		return result;
	}
	
	public boolean isHasSailForEachSailPosition() {
		boolean result = false;
		result = forAll3();
		return result;
	}
	
	public boolean isMaximumSpeed() {
		boolean result = false;
		result = (this.getMaximumSpeed() > (this.getSail().size() * 10));
		return result;
	}
	
	public Boat makeCopy() {
		Boat result = new Boat();
		copyState((Boat)this,result);
		return result;
	}
	
	public Boat makeShallowCopy() {
		Boat result = new Boat();
		copyShallowState((Boat)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( Sail child : new ArrayList<Sail>(getSail()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("sail") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("814077939394093143")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Sail)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromSail(SailPosition sailPosition, Sail sail) {
		if ( sail!=null ) {
			sail.z_internalRemoveFromBoat(this);
			z_internalRemoveFromSail(sailPosition,sail);
		}
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
	
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name",getName(),name);
		this.z_internalAddToName(name);
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
		return "<Boat uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Boat ");
		sb.append("classUuid=\"ocltests.uml@_ECviIOEgEeGhxPe2keryuw\" ");
		sb.append("className=\"ocltests.Boat\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ OcltestsFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<sail propertyId=\"814077939394093143\">");
		for ( Sail sail : getSail() ) {
			sb.append("\n" + sail.toXmlString());
		}
		sb.append("\n</sail>");
		sb.append("\n</Boat>");
		return sb.toString();
	}
	
	public void z_internalAddToName(String name) {
		this.name=name;
	}
	
	public void z_internalAddToSail(SailPosition sailPosition, Sail sail) {
		String key = sailPosition.getUid();
		sail.z_internalAddToSailPosition(sailPosition);
		this.sail.put(key.toString(),sail);
		sail.setZ_keyOfSailOnBoat(key.toString());
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromSail(SailPosition sailPosition, Sail sail) {
		String key = sailPosition.getUid();
		this.sail.remove(key.toString());
	}
	
	/** Implements Set {self.sail}->collect(s : Sail | s.size.+(5))
	 */
	private Collection<Integer> collect5() {
		Collection<Integer> result = new ArrayList<Integer>();
		for ( Sail s : this.getSail() ) {
			Integer bodyExpResult = (s.getSize() + 5);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements Set {self.name}
	 */
	private Set<String> collectionLiteral1() {
		Set<String> myList = new HashSet<String>();
		if ( this.getName() != null ) {
			myList.add( this.getName() );
		}
		return myList;
	}
	
	/** Implements Set {self.sail[t]}
	 * 
	 * @param t 
	 */
	private Set<Sail> collectionLiteral2(SailPosition t) {
		Set<Sail> myList = new HashSet<Sail>();
		if ( this.getSail(t) != null ) {
			myList.add( this.getSail(t) );
		}
		return myList;
	}
	
	/** Implements ocltests::SailPosition.allInstances()->forAll(t : SailPosition | Set {self.sail[t]}->size().>(0))
	 */
	private boolean forAll3() {
		for ( SailPosition t : SailPosition.getValues() ) {
			if ( !(collectionLiteral2(t).size() > 0) ) {
				return false;
			}
		}
		return true;
	}
	
	/** Implements ocltests::Boat.allInstances()->forAll(temp1 : Boat | Set {temp1.sail}->size().>(0))
	 */
	private boolean forAll4() {
		for ( Boat temp1 : Boat.allInstances(persistence) ) {
			if ( !(temp1.getSail().size() > 0) ) {
				return false;
			}
		}
		return true;
	}
	
	/** Implements Set {self.sail}->collect(s : Sail | s.size.+(5))->sum() on Set {self.sail}->collect(s : Sail | s.size.+(5))
	 */
	private Integer sum6() {
		Integer result = 0;
		for ( Integer elem : collect5() ) {
			result = result + elem;
		}
		return result;
	}

}