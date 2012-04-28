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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_nhV7IGCfEeG6xvYqJACneg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="dish_washer_model")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="DishWasherModel")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class DishWasherModel implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishWasher",targetEntity=DishWasherComponent.class)
	private Set<DishWasherComponent> component = new HashSet<DishWasherComponent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="dishwashers_inc_id",name="idx_dish_washer_model_dishwashers_inc_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dishwashers_inc_id",nullable=true)
	private DishwashersInc dishwashersInc;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<DishWasherModel> mockedAllInstances;
	@Column(name="name")
	private String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="dishWasherModel",targetEntity=Order.class)
	private Set<Order> order = new HashSet<Order>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Column(name="part_number")
	private String partNumber;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 9167031835761131135l;
	private String uid;
	@Type(type="structuredbusiness.VendorResolver")
	@Column(name="vendor",nullable=true)
	private Vendor vendor;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DishWasherModel(DishwashersInc owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for DishWasherModel
	 */
	public DishWasherModel() {
	}

	public void addAllToComponent(Set<DishWasherComponent> component) {
		for ( DishWasherComponent o : component ) {
			addToComponent(o);
		}
	}
	
	public void addAllToOrder(Set<Order> order) {
		for ( Order o : order ) {
			addToOrder(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToComponent(DishWasherComponent component) {
		if ( component!=null ) {
			component.z_internalRemoveFromDishWasher(component.getDishWasher());
			component.z_internalAddToDishWasher(this);
			z_internalAddToComponent(component);
		}
	}
	
	public void addToOrder(Order order) {
		if ( order!=null ) {
			order.z_internalRemoveFromDishWasherModel(order.getDishWasherModel());
			order.z_internalAddToDishWasherModel(this);
			z_internalAddToOrder(order);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDishwashersInc().z_internalAddToDishWasher((DishWasherModel)this);
	}
	
	static public Set<? extends DishWasherModel> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.DishWasherModel.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("partNumber").length()>0 ) {
			setPartNumber(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("partNumber")));
		}
		if ( xml.getAttribute("vendor").length()>0 ) {
			setVendor(Vendor.valueOf(xml.getAttribute("vendor")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("component") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6689744676322243651")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						DishWasherComponent curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToComponent(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearComponent() {
		removeAllFromComponent(getComponent());
	}
	
	public void clearOrder() {
		removeAllFromOrder(getOrder());
	}
	
	public void copyShallowState(DishWasherModel from, DishWasherModel to) {
		to.setName(from.getName());
		to.setPartNumber(from.getPartNumber());
		to.setVendor(from.getVendor());
	}
	
	public void copyState(DishWasherModel from, DishWasherModel to) {
		for ( DishWasherComponent child : from.getComponent() ) {
			to.addToComponent(child.makeCopy());
		}
		to.setName(from.getName());
		to.setPartNumber(from.getPartNumber());
		to.setVendor(from.getVendor());
	}
	
	public DishWasherComponent createComponent() {
		DishWasherComponent newInstance= new DishWasherComponent();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof DishWasherModel ) {
			return other==this || ((DishWasherModel)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6689744676322243651l,opposite="dishWasher",uuid="914890@_4_SYoHJ6EeG5aYCQXxe9BQ")
	@NumlMetaInfo(uuid="914890@_4_SYoHJ6EeG5aYCQXxe9BQ")
	public Set<DishWasherComponent> getComponent() {
		Set<DishWasherComponent> result = this.component;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8669346731255087885l,opposite="dishWasher",uuid="914890@_z0NB0XHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_z0NB0XHgEeGus4aKic9sIg")
	public DishwashersInc getDishwashersInc() {
		DishwashersInc result = this.dishwashersInc;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8027636478953627954l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="914890@_ht9n8HphEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_ht9n8HphEeGlh5y8zQdYBA")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,lookupMethod="getSourcePopulationForOrder",opaeumId=4329998626631571742l,opposite="dishWasherModel",uuid="914890@_jKqncX47EeGarqqEaoJFHg")
	@NumlMetaInfo(uuid="914890@_jKqncX47EeGarqqEaoJFHg")
	public Set<Order> getOrder() {
		Set<Order> result = this.order;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getDishwashersInc();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6341938639454514848l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="914890@_Kid8QHpiEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_Kid8QHpiEeGlh5y8zQdYBA")
	public String getPartNumber() {
		String result = this.partNumber;
		
		return result;
	}
	
	public List<Order> getSourcePopulationForOrder() {
		return new ArrayList<Order>(Stdlib.collectionAsSet(this.getDishwashersInc().getOrder()));
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1278716437618707842l,opposite="dishWasherModel",uuid="914890@_7RIUYHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_7RIUYHsKEeGBGZr9IpIa3A")
	public Vendor getVendor() {
		Vendor result = this.vendor;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToDishwashersInc((DishwashersInc)owner);
		createComponents();
	}
	
	public DishWasherModel makeCopy() {
		DishWasherModel result = new DishWasherModel();
		copyState((DishWasherModel)this,result);
		return result;
	}
	
	public DishWasherModel makeShallowCopy() {
		DishWasherModel result = new DishWasherModel();
		copyShallowState((DishWasherModel)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getDishwashersInc()!=null ) {
			getDishwashersInc().z_internalRemoveFromDishWasher(this);
		}
		for ( DishWasherComponent child : new ArrayList<DishWasherComponent>(getComponent()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<DishWasherModel> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("component") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6689744676322243651")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((DishWasherComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromComponent(Set<DishWasherComponent> component) {
		Set<DishWasherComponent> tmp = new HashSet<DishWasherComponent>(component);
		for ( DishWasherComponent o : tmp ) {
			removeFromComponent(o);
		}
	}
	
	public void removeAllFromOrder(Set<Order> order) {
		Set<Order> tmp = new HashSet<Order>(order);
		for ( Order o : tmp ) {
			removeFromOrder(o);
		}
	}
	
	public void removeFromComponent(DishWasherComponent component) {
		if ( component!=null ) {
			component.z_internalRemoveFromDishWasher(this);
			z_internalRemoveFromComponent(component);
		}
	}
	
	public void removeFromOrder(Order order) {
		if ( order!=null ) {
			order.z_internalRemoveFromDishWasherModel(this);
			z_internalRemoveFromOrder(order);
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
	
	public void setComponent(Set<DishWasherComponent> component) {
		propertyChangeSupport.firePropertyChange("component",getComponent(),component);
		this.clearComponent();
		this.addAllToComponent(component);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishwashersInc(DishwashersInc dishwashersInc) {
		propertyChangeSupport.firePropertyChange("dishwashersInc",getDishwashersInc(),dishwashersInc);
		if ( this.getDishwashersInc()!=null ) {
			this.getDishwashersInc().z_internalRemoveFromDishWasher(this);
		}
		if ( dishwashersInc!=null ) {
			dishwashersInc.z_internalAddToDishWasher(this);
			this.z_internalAddToDishwashersInc(dishwashersInc);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
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
	
	public void setOrder(Set<Order> order) {
		propertyChangeSupport.firePropertyChange("order",getOrder(),order);
		this.clearOrder();
		this.addAllToOrder(order);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPartNumber(String partNumber) {
		propertyChangeSupport.firePropertyChange("partNumber",getPartNumber(),partNumber);
		this.z_internalAddToPartNumber(partNumber);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setVendor(Vendor vendor) {
		propertyChangeSupport.firePropertyChange("vendor",getVendor(),vendor);
		this.z_internalAddToVendor(vendor);
	}
	
	public String toXmlReferenceString() {
		return "<DishWasherModel uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<DishWasherModel ");
		sb.append("classUuid=\"914890@_nhV7IGCfEeG6xvYqJACneg\" ");
		sb.append("className=\"structuredbusiness.DishWasherModel\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getPartNumber()!=null ) {
			sb.append("partNumber=\""+ StructuredbusinessFormatter.getInstance().formatString(getPartNumber())+"\" ");
		}
		if ( getVendor()!=null ) {
			sb.append("vendor=\""+ getVendor().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n<component propertyId=\"6689744676322243651\">");
		for ( DishWasherComponent component : getComponent() ) {
			sb.append("\n" + component.toXmlString());
		}
		sb.append("\n</component>");
		sb.append("\n</DishWasherModel>");
		return sb.toString();
	}
	
	public void z_internalAddToComponent(DishWasherComponent val) {
		this.component.add(val);
	}
	
	public void z_internalAddToDishwashersInc(DishwashersInc val) {
		this.dishwashersInc=val;
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
	}
	
	public void z_internalAddToOrder(Order val) {
		this.order.add(val);
	}
	
	public void z_internalAddToPartNumber(String val) {
		this.partNumber=val;
	}
	
	public void z_internalAddToVendor(Vendor val) {
		this.vendor=val;
	}
	
	public void z_internalRemoveFromComponent(DishWasherComponent val) {
		this.component.remove(val);
	}
	
	public void z_internalRemoveFromDishwashersInc(DishwashersInc val) {
		if ( getDishwashersInc()!=null && val!=null && val.equals(getDishwashersInc()) ) {
			this.dishwashersInc=null;
			this.dishwashersInc=null;
		}
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromOrder(Order val) {
		this.order.remove(val);
	}
	
	public void z_internalRemoveFromPartNumber(String val) {
		if ( getPartNumber()!=null && val!=null && val.equals(getPartNumber()) ) {
			this.partNumber=null;
			this.partNumber=null;
		}
	}
	
	public void z_internalRemoveFromVendor(Vendor val) {
		if ( getVendor()!=null && val!=null && val.equals(getVendor()) ) {
			this.vendor=null;
			this.vendor=null;
		}
	}

}