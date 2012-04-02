package structuredbusiness;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_x_4WgHJ6EeG5aYCQXxe9BQ")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="dish_washer_component")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="DishWasherComponent")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class DishWasherComponent implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="dish_washer_id",name="idx_dish_washer_component_dish_washer_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dish_washer_id",nullable=true)
	private DishWasherModel dishWasher;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<DishWasherComponent> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Column(name="part_number")
	private String partNumber;
	@Transient
	private AbstractPersistence persistence;
	@Column(name="price")
	private Double price;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6177098332065339845l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DishWasherComponent(DishWasherModel owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for DishWasherComponent
	 */
	public DishWasherComponent() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDishWasher().z_internalAddToComponent((DishWasherComponent)this);
	}
	
	static public Set<? extends DishWasherComponent> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.DishWasherComponent.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("partNumber").length()>0 ) {
			setPartNumber(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("partNumber")));
		}
		if ( xml.getAttribute("price").length()>0 ) {
			setPrice(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("price")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(DishWasherComponent from, DishWasherComponent to) {
		to.setPartNumber(from.getPartNumber());
		to.setPrice(from.getPrice());
	}
	
	public void copyState(DishWasherComponent from, DishWasherComponent to) {
		to.setPartNumber(from.getPartNumber());
		to.setPrice(from.getPrice());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof DishWasherComponent ) {
			return other==this || ((DishWasherComponent)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4751173535218028061l,opposite="component",uuid="914890@_4_Vb8HJ6EeG5aYCQXxe9BQ")
	@NumlMetaInfo(uuid="914890@_4_Vb8HJ6EeG5aYCQXxe9BQ")
	public DishWasherModel getDishWasher() {
		DishWasherModel result = this.dishWasher;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "DishWasherComponent["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getDishWasher();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4779184346657477803l,uuid="914890@_Fn4Q0HJ7EeG5aYCQXxe9BQ")
	@NumlMetaInfo(uuid="914890@_Fn4Q0HJ7EeG5aYCQXxe9BQ")
	public String getPartNumber() {
		String result = this.partNumber;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1800320774890339505l,uuid="914890@_HxpZsHJ7EeG5aYCQXxe9BQ")
	@NumlMetaInfo(uuid="914890@_HxpZsHJ7EeG5aYCQXxe9BQ")
	public Double getPrice() {
		Double result = this.price;
		
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
		this.z_internalAddToDishWasher((DishWasherModel)owner);
		createComponents();
	}
	
	public DishWasherComponent makeCopy() {
		DishWasherComponent result = new DishWasherComponent();
		copyState((DishWasherComponent)this,result);
		return result;
	}
	
	public DishWasherComponent makeShallowCopy() {
		DishWasherComponent result = new DishWasherComponent();
		copyShallowState((DishWasherComponent)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getDishWasher()!=null ) {
			getDishWasher().z_internalRemoveFromComponent(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<DishWasherComponent> newMocks) {
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishWasher(DishWasherModel dishWasher) {
		propertyChangeSupport.firePropertyChange("dishWasher",getDishWasher(),dishWasher);
		if ( this.getDishWasher()!=null ) {
			this.getDishWasher().z_internalRemoveFromComponent(this);
		}
		if ( dishWasher!=null ) {
			dishWasher.z_internalAddToComponent(this);
			this.z_internalAddToDishWasher(dishWasher);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
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
	
	public void setPartNumber(String partNumber) {
		propertyChangeSupport.firePropertyChange("partNumber",getPartNumber(),partNumber);
		this.z_internalAddToPartNumber(partNumber);
	}
	
	public void setPrice(Double price) {
		propertyChangeSupport.firePropertyChange("price",getPrice(),price);
		this.z_internalAddToPrice(price);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<DishWasherComponent uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<DishWasherComponent ");
		sb.append("classUuid=\"914890@_x_4WgHJ6EeG5aYCQXxe9BQ\" ");
		sb.append("className=\"structuredbusiness.DishWasherComponent\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getPartNumber()!=null ) {
			sb.append("partNumber=\""+ StructuredbusinessFormatter.getInstance().formatString(getPartNumber())+"\" ");
		}
		if ( getPrice()!=null ) {
			sb.append("price=\""+ StructuredbusinessFormatter.getInstance().formatReal(getPrice())+"\" ");
		}
		sb.append(">");
		sb.append("\n</DishWasherComponent>");
		return sb.toString();
	}
	
	public void z_internalAddToDishWasher(DishWasherModel val) {
		this.dishWasher=val;
	}
	
	public void z_internalAddToPartNumber(String val) {
		this.partNumber=val;
	}
	
	public void z_internalAddToPrice(Double val) {
		this.price=val;
	}
	
	public void z_internalRemoveFromDishWasher(DishWasherModel val) {
		if ( getDishWasher()!=null && val!=null && val.equals(getDishWasher()) ) {
			this.dishWasher=null;
			this.dishWasher=null;
		}
	}
	
	public void z_internalRemoveFromPartNumber(String val) {
		if ( getPartNumber()!=null && val!=null && val.equals(getPartNumber()) ) {
			this.partNumber=null;
			this.partNumber=null;
		}
	}
	
	public void z_internalRemoveFromPrice(Double val) {
		if ( getPrice()!=null && val!=null && val.equals(getPrice()) ) {
			this.price=null;
			this.price=null;
		}
	}

}