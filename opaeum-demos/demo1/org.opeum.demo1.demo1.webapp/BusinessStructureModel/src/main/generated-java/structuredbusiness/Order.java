package structuredbusiness;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import org.opaeum.annotation.ParameterMetaInfo;
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

@NumlMetaInfo(uuid="914890@_gTMrEH47EeGarqqEaoJFHg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="`order`")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Order")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Order implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="__order_date")
	private Date __orderDate;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dish_washer_model_id",nullable=true)
	private DishWasherModel dishWasherModel;
	@Index(columnNames="dishwashers_inc_id",name="idx_order_dishwashers_inc_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dishwashers_inc_id",nullable=true)
	private DishwashersInc dishwashersInc;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="due_date")
	private Date dueDate;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Order> mockedAllInstances;
	@Column(name="my_special_description")
	private String mySpecialDescription;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 2247787225748188966l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Order(DishwashersInc owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Order
	 */
	public Order() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDishwashersInc().z_internalAddToOrder((Order)this);
	}
	
	static public Set<? extends Order> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.Order.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("__orderDate").length()>0 ) {
			set__orderDate(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("__orderDate")));
		}
		if ( xml.getAttribute("mySpecialDescription").length()>0 ) {
			setMySpecialDescription(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("mySpecialDescription")));
		}
		if ( xml.getAttribute("dueDate").length()>0 ) {
			setDueDate(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("dueDate")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public boolean consumeDispatchOccurrence(@ParameterMetaInfo(name="accountant",opaeumId=244889823387463052l,uuid="914890@_5a6WYH47EeGarqqEaoJFHg") Accountant accountant, @ParameterMetaInfo(name="date",opaeumId=39625519448773874l,uuid="914890@_5cxXkH47EeGarqqEaoJFHg") Date date, @ParameterMetaInfo(name="complete",opaeumId=7181427565989028018l,uuid="914890@_5eWr8H47EeGarqqEaoJFHg") Boolean complete) {
		boolean consumed = false;
		return consumed;
	}
	
	public void copyShallowState(Order from, Order to) {
		to.set__orderDate(from.get__orderDate());
		to.setMySpecialDescription(from.getMySpecialDescription());
		to.setDueDate(from.getDueDate());
	}
	
	public void copyState(Order from, Order to) {
		to.set__orderDate(from.get__orderDate());
		to.setMySpecialDescription(from.getMySpecialDescription());
		to.setDueDate(from.getDueDate());
	}
	
	public void createComponents() {
	}
	
	@NumlMetaInfo(uuid="914890@_2Wwo4H47EeGarqqEaoJFHg")
	public void dispatch(@ParameterMetaInfo(name="accountant",opaeumId=244889823387463052l,uuid="914890@_5a6WYH47EeGarqqEaoJFHg") Accountant accountant, @ParameterMetaInfo(name="date",opaeumId=39625519448773874l,uuid="914890@_5cxXkH47EeGarqqEaoJFHg") Date date, @ParameterMetaInfo(name="complete",opaeumId=7181427565989028018l,uuid="914890@_5eWr8H47EeGarqqEaoJFHg") Boolean complete) {
		generateDispatchEvent(accountant,date,complete);
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Order ) {
			return other==this || ((Order)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateDispatchEvent(@ParameterMetaInfo(name="accountant",opaeumId=244889823387463052l,uuid="914890@_5a6WYH47EeGarqqEaoJFHg") Accountant accountant, @ParameterMetaInfo(name="date",opaeumId=39625519448773874l,uuid="914890@_5cxXkH47EeGarqqEaoJFHg") Date date, @ParameterMetaInfo(name="complete",opaeumId=7181427565989028018l,uuid="914890@_5eWr8H47EeGarqqEaoJFHg") Boolean complete) {
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,lookupMethod="getSourcePopulationForDishWasherModel",opaeumId=9062381283096013522l,opposite="order",uuid="914890@_jKoLMH47EeGarqqEaoJFHg")
	@NumlMetaInfo(uuid="914890@_jKoLMH47EeGarqqEaoJFHg")
	public DishWasherModel getDishWasherModel() {
		DishWasherModel result = this.dishWasherModel;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6549117260321655658l,opposite="order",uuid="914890@_nyl00X47EeGarqqEaoJFHg")
	@NumlMetaInfo(uuid="914890@_nyl00X47EeGarqqEaoJFHg")
	public DishwashersInc getDishwashersInc() {
		DishwashersInc result = this.dishwashersInc;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5688197628287701802l,uuid="914890@_weIooH47EeGarqqEaoJFHg")
	@NumlMetaInfo(uuid="914890@_weIooH47EeGarqqEaoJFHg")
	public Date getDueDate() {
		Date result = this.dueDate;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4763224184902676354l,uuid="914890@_uIu3gH47EeGarqqEaoJFHg")
	@NumlMetaInfo(uuid="914890@_uIu3gH47EeGarqqEaoJFHg")
	public String getMySpecialDescription() {
		String result = this.mySpecialDescription;
		
		return result;
	}
	
	public String getName() {
		return "Order["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getDishwashersInc();
	}
	
	public List<DishWasherModel> getSourcePopulationForDishWasherModel() {
		return new ArrayList<DishWasherModel>(Stdlib.collectionAsSet(this.getDishwashersInc().getDishWasher()));
	}
	
	public List<Accountant> getSourcePopulationForDispatchAccountant() {
		return new ArrayList<Accountant>(Stdlib.collectionAsSet(this.getDishwashersInc().getAccountant()));
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1580127939218001944l,uuid="914890@_sQarAH47EeGarqqEaoJFHg")
	@NumlMetaInfo(uuid="914890@_sQarAH47EeGarqqEaoJFHg")
	public Date get__orderDate() {
		Date result = this.__orderDate;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToDishwashersInc((DishwashersInc)owner);
		createComponents();
	}
	
	public Order makeCopy() {
		Order result = new Order();
		copyState((Order)this,result);
		return result;
	}
	
	public Order makeShallowCopy() {
		Order result = new Order();
		copyShallowState((Order)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getDishwashersInc()!=null ) {
			getDishwashersInc().z_internalRemoveFromOrder(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Order> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("dishWasherModel") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9062381283096013522")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setDishWasherModel((DishWasherModel)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setDishWasherModel(DishWasherModel dishWasherModel) {
		propertyChangeSupport.firePropertyChange("dishWasherModel",getDishWasherModel(),dishWasherModel);
		this.z_internalAddToDishWasherModel(dishWasherModel);
	}
	
	public void setDishwashersInc(DishwashersInc dishwashersInc) {
		propertyChangeSupport.firePropertyChange("dishwashersInc",getDishwashersInc(),dishwashersInc);
		if ( this.getDishwashersInc()!=null ) {
			this.getDishwashersInc().z_internalRemoveFromOrder(this);
		}
		if ( dishwashersInc!=null ) {
			dishwashersInc.z_internalAddToOrder(this);
			this.z_internalAddToDishwashersInc(dishwashersInc);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setDueDate(Date dueDate) {
		propertyChangeSupport.firePropertyChange("dueDate",getDueDate(),dueDate);
		this.z_internalAddToDueDate(dueDate);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMySpecialDescription(String mySpecialDescription) {
		propertyChangeSupport.firePropertyChange("mySpecialDescription",getMySpecialDescription(),mySpecialDescription);
		this.z_internalAddToMySpecialDescription(mySpecialDescription);
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
	
	public void set__orderDate(Date __orderDate) {
		propertyChangeSupport.firePropertyChange("__orderDate",get__orderDate(),__orderDate);
		this.z_internalAddTo__orderDate(__orderDate);
	}
	
	public String toXmlReferenceString() {
		return "<Order uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Order ");
		sb.append("classUuid=\"914890@_gTMrEH47EeGarqqEaoJFHg\" ");
		sb.append("className=\"structuredbusiness.Order\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( get__orderDate()!=null ) {
			sb.append("__orderDate=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(get__orderDate())+"\" ");
		}
		if ( getMySpecialDescription()!=null ) {
			sb.append("mySpecialDescription=\""+ StructuredbusinessFormatter.getInstance().formatString(getMySpecialDescription())+"\" ");
		}
		if ( getDueDate()!=null ) {
			sb.append("dueDate=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(getDueDate())+"\" ");
		}
		sb.append(">");
		if ( getDishWasherModel()==null ) {
			sb.append("\n<dishWasherModel/>");
		} else {
			sb.append("\n<dishWasherModel propertyId=\"9062381283096013522\">");
			sb.append("\n" + getDishWasherModel().toXmlReferenceString());
			sb.append("\n</dishWasherModel>");
		}
		sb.append("\n</Order>");
		return sb.toString();
	}
	
	public void z_internalAddToDishWasherModel(DishWasherModel val) {
		this.dishWasherModel=val;
	}
	
	public void z_internalAddToDishwashersInc(DishwashersInc val) {
		this.dishwashersInc=val;
	}
	
	public void z_internalAddToDueDate(Date val) {
		this.dueDate=val;
	}
	
	public void z_internalAddToMySpecialDescription(String val) {
		this.mySpecialDescription=val;
	}
	
	public void z_internalAddTo__orderDate(Date val) {
		this.__orderDate=val;
	}
	
	public void z_internalRemoveFromDishWasherModel(DishWasherModel val) {
		if ( getDishWasherModel()!=null && val!=null && val.equals(getDishWasherModel()) ) {
			this.dishWasherModel=null;
			this.dishWasherModel=null;
		}
	}
	
	public void z_internalRemoveFromDishwashersInc(DishwashersInc val) {
		if ( getDishwashersInc()!=null && val!=null && val.equals(getDishwashersInc()) ) {
			this.dishwashersInc=null;
			this.dishwashersInc=null;
		}
	}
	
	public void z_internalRemoveFromDueDate(Date val) {
		if ( getDueDate()!=null && val!=null && val.equals(getDueDate()) ) {
			this.dueDate=null;
			this.dueDate=null;
		}
	}
	
	public void z_internalRemoveFromMySpecialDescription(String val) {
		if ( getMySpecialDescription()!=null && val!=null && val.equals(getMySpecialDescription()) ) {
			this.mySpecialDescription=null;
			this.mySpecialDescription=null;
		}
	}
	
	public void z_internalRemoveFrom__orderDate(Date val) {
		if ( get__orderDate()!=null && val!=null && val.equals(get__orderDate()) ) {
			this.__orderDate=null;
			this.__orderDate=null;
		}
	}

}