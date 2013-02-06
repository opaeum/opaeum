package ocltests;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import ocltests.util.OcltestsFormatter;
import ocltests.util.Stdlib;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
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

@AuditMe
@NumlMetaInfo(uuid="ocltests.uml@_HYTIsOEgEeGhxPe2keryuw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="sail",uniqueConstraints=
	@UniqueConstraint(columnNames={"boat_id","sail_position","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QuerySailWithSailPositionForBoat",query="from Sail a where a.boat = :boat and a.sailPosition = :sailPosition"))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Sail")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Sail implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Index(columnNames="boat_id",name="idx_sail_boat_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="boat_id",nullable=true)
	private Boat boat;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Sail> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Type(type="ocltests.SailPositionResolver")
	@Column(name="sail_position",nullable=true)
	private SailPosition sailPosition;
	static final private long serialVersionUID = 8011964833092776758l;
	@Column(name="size")
	private Integer size;
	private String uid;
	private String z_keyOfSailOnBoat;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param sailPosition 
	 */
	public Sail(Boat owningObject, SailPosition sailPosition) {
		setSailPosition(sailPosition);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Sail
	 */
	public Sail() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBoat().z_internalAddToSail(this.getSailPosition(),(Sail)this);
	}
	
	static public Set<? extends Sail> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(ocltests.Sail.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("size").length()>0 ) {
			setSize(OcltestsFormatter.getInstance().parseInteger(xml.getAttribute("size")));
		}
		if ( xml.getAttribute("sailPosition").length()>0 ) {
			setSailPosition(SailPosition.valueOf(xml.getAttribute("sailPosition")));
		}
		if ( xml.getAttribute("sailPosition").length()>0 ) {
			setSailPosition(SailPosition.valueOf(xml.getAttribute("sailPosition")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Sail from, Sail to) {
		to.setSize(from.getSize());
		to.setSailPosition(from.getSailPosition());
		to.setSailPosition(from.getSailPosition());
	}
	
	public void copyState(Sail from, Sail to) {
		to.setSize(from.getSize());
		to.setSailPosition(from.getSailPosition());
		to.setSailPosition(from.getSailPosition());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Sail ) {
			return other==this || ((Sail)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6971942154820236185l,opposite="sail",uuid="ocltests.uml@_VN1nQeFDEeGyXP1sbXN4GQ")
	@NumlMetaInfo(uuid="ocltests.uml@_VN1nQeFDEeGyXP1sbXN4GQ")
	public Boat getBoat() {
		Boat result = this.boat;
		
		return result;
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
		return "Sail["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBoat();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9094639643155217465l,opposite="sail",uuid="ocltests.uml@_5ApIwOHyEeGQTZorD0E5LA")
	@NumlMetaInfo(uuid="ocltests.uml@_5ApIwOHyEeGQTZorD0E5LA")
	public SailPosition getSailPosition() {
		SailPosition result = this.sailPosition;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1943958053436575887l,uuid="ocltests.uml@_V0SRUOFFEeGyXP1sbXN4GQ")
	@NumlMetaInfo(uuid="ocltests.uml@_V0SRUOFFEeGyXP1sbXN4GQ")
	public Integer getSize() {
		Integer result = this.size;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfSailOnBoat() {
		return this.z_keyOfSailOnBoat;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToBoat((Boat)owner);
		createComponents();
	}
	
	public Sail makeCopy() {
		Sail result = new Sail();
		copyState((Sail)this,result);
		return result;
	}
	
	public Sail makeShallowCopy() {
		Sail result = new Sail();
		copyShallowState((Sail)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getBoat()!=null ) {
			getBoat().z_internalRemoveFromSail(this.getSailPosition(),this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Sail> newMocks) {
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
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setBoat(Boat boat) {
		propertyChangeSupport.firePropertyChange("boat",getBoat(),boat);
		if ( this.getBoat()!=null ) {
			this.getBoat().z_internalRemoveFromSail(this.getSailPosition(),this);
		}
		if ( boat!=null ) {
			boat.z_internalAddToSail(this.getSailPosition(),this);
			this.z_internalAddToBoat(boat);
		}
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
	
	public void setSailPosition(SailPosition sailPosition) {
		propertyChangeSupport.firePropertyChange("sailPosition",getSailPosition(),sailPosition);
		this.z_internalAddToSailPosition(sailPosition);
	}
	
	public void setSize(Integer size) {
		propertyChangeSupport.firePropertyChange("size",getSize(),size);
		this.z_internalAddToSize(size);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setZ_keyOfSailOnBoat(String z_keyOfSailOnBoat) {
		this.z_keyOfSailOnBoat=z_keyOfSailOnBoat;
	}
	
	public String toXmlReferenceString() {
		return "<Sail uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Sail ");
		sb.append("classUuid=\"ocltests.uml@_HYTIsOEgEeGhxPe2keryuw\" ");
		sb.append("className=\"ocltests.Sail\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getSize()!=null ) {
			sb.append("size=\""+ OcltestsFormatter.getInstance().formatInteger(getSize())+"\" ");
		}
		if ( getSailPosition()!=null ) {
			sb.append("sailPosition=\""+ getSailPosition().name() + "\" ");
		}
		if ( getSailPosition()!=null ) {
			sb.append("sailPosition=\""+ getSailPosition().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</Sail>");
		return sb.toString();
	}
	
	public void z_internalAddToBoat(Boat val) {
		this.boat=val;
	}
	
	public void z_internalAddToSailPosition(SailPosition val) {
		this.sailPosition=val;
	}
	
	public void z_internalAddToSize(Integer val) {
		this.size=val;
	}
	
	public void z_internalRemoveFromBoat(Boat val) {
		if ( getBoat()!=null && val!=null && val.equals(getBoat()) ) {
			this.boat=null;
			this.boat=null;
		}
	}
	
	public void z_internalRemoveFromSailPosition(SailPosition val) {
		if ( getSailPosition()!=null && val!=null && val.equals(getSailPosition()) ) {
			this.sailPosition=null;
			this.sailPosition=null;
		}
	}
	
	public void z_internalRemoveFromSize(Integer val) {
		if ( getSize()!=null && val!=null && val.equals(getSize()) ) {
			this.size=null;
			this.size=null;
		}
	}

}