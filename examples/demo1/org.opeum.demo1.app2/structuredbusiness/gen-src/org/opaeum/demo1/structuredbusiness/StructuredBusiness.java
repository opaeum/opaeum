package org.opaeum.demo1.structuredbusiness;

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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_kqiUQHjtEeKMgr_8va3Mhg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="structured_business",schema="structuredbusiness")
@Entity(name="StructuredBusiness")
public class StructuredBusiness implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="appliance_doctor_id",nullable=true)
	protected ApplianceDoctor applianceDoctor;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="structured_business",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends StructuredBusiness> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@ManyToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="online_customer_id",nullable=true)
	protected OnlineCustomer onlineCustomer;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6564891247115943824l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@ManyToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=Supplier.class)
	@JoinTable(inverseJoinColumns=
		@JoinColumn(name="supplier_id"),joinColumns=
		@JoinColumn(name="structured_business_id"),name="structured_business_supplier",schema="structuredbusiness")
	protected Set<Supplier> supplier = new HashSet<Supplier>();
	private String uid;

	/** Default constructor for StructuredBusiness
	 */
	public StructuredBusiness() {
	}

	public void addAllToSupplier(Set<Supplier> supplier) {
		for ( Supplier o : supplier ) {
			addToSupplier(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToSupplier(Supplier supplier) {
		z_internalAddToSupplier(supplier);
	}
	
	static public Set<? extends StructuredBusiness> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.StructuredBusiness.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("370752505461177290")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Supplier curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToSupplier(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("onlineCustomer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1202466605611950532")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OnlineCustomer curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setOnlineCustomer(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceDoctor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9020492966392633516")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ApplianceDoctor curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setApplianceDoctor(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearSupplier() {
		Set<Supplier> tmp = new HashSet<Supplier>(getSupplier());
		for ( Supplier o : tmp ) {
			removeFromSupplier(o);
		}
	}
	
	public ApplianceDoctor createApplianceDoctor() {
		ApplianceDoctor newInstance= new ApplianceDoctor();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		if ( getOnlineCustomer()==null ) {
			setOnlineCustomer(new OnlineCustomer());
		}
		if ( getApplianceDoctor()==null ) {
			setApplianceDoctor(new ApplianceDoctor());
		}
	}
	
	public OnlineCustomer createOnlineCustomer() {
		OnlineCustomer newInstance= new OnlineCustomer();
		newInstance.init(this);
		return newInstance;
	}
	
	public Supplier createSupplier() {
		Supplier newInstance= new Supplier();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof StructuredBusiness ) {
			return other==this || ((StructuredBusiness)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=9020492966392633516l,uuid="914890@_nVaJoHjuEeKMgr_8va3Mhg")
	@NumlMetaInfo(uuid="914890@_nVaJoHjuEeKMgr_8va3Mhg")
	public ApplianceDoctor getApplianceDoctor() {
		ApplianceDoctor result = this.applianceDoctor;
		
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
		return "StructuredBusiness["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1202466605611950532l,uuid="914890@_d_b4wHjuEeKMgr_8va3Mhg")
	@NumlMetaInfo(uuid="914890@_d_b4wHjuEeKMgr_8va3Mhg")
	public OnlineCustomer getOnlineCustomer() {
		OnlineCustomer result = this.onlineCustomer;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=370752505461177290l,uuid="914890@_bWrGwHjuEeKMgr_8va3Mhg")
	@NumlMetaInfo(uuid="914890@_bWrGwHjuEeKMgr_8va3Mhg")
	public Set<Supplier> getSupplier() {
		Set result = this.supplier;
		
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
		getOnlineCustomer().init(this);
		getApplianceDoctor().init(this);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( Supplier child : new ArrayList<Supplier>(getSupplier()) ) {
			child.markDeleted();
		}
		if ( getOnlineCustomer()!=null ) {
			getOnlineCustomer().markDeleted();
		}
		if ( getApplianceDoctor()!=null ) {
			getApplianceDoctor().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("370752505461177290")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Supplier)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("onlineCustomer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1202466605611950532")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OnlineCustomer)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceDoctor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9020492966392633516")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceDoctor)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromSupplier(Set<Supplier> supplier) {
		Set<Supplier> tmp = new HashSet<Supplier>(supplier);
		for ( Supplier o : tmp ) {
			removeFromSupplier(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromSupplier(Supplier supplier) {
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setApplianceDoctor(ApplianceDoctor applianceDoctor) {
		propertyChangeSupport.firePropertyChange("applianceDoctor",getApplianceDoctor(),applianceDoctor);
		this.z_internalAddToApplianceDoctor(applianceDoctor);
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
	
	public void setOnlineCustomer(OnlineCustomer onlineCustomer) {
		propertyChangeSupport.firePropertyChange("onlineCustomer",getOnlineCustomer(),onlineCustomer);
		this.z_internalAddToOnlineCustomer(onlineCustomer);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSupplier(Set<Supplier> supplier) {
		propertyChangeSupport.firePropertyChange("supplier",getSupplier(),supplier);
		this.clearSupplier();
		this.addAllToSupplier(supplier);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<StructuredBusiness uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<StructuredBusiness ");
		sb.append("classUuid=\"914890@_kqiUQHjtEeKMgr_8va3Mhg\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.StructuredBusiness\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n<supplier propertyId=\"370752505461177290\">");
		for ( Supplier supplier : getSupplier() ) {
			sb.append("\n" + supplier.toXmlString());
		}
		sb.append("\n</supplier>");
		if ( getOnlineCustomer()==null ) {
			sb.append("\n<onlineCustomer/>");
		} else {
			sb.append("\n<onlineCustomer propertyId=\"1202466605611950532\">");
			sb.append("\n" + getOnlineCustomer().toXmlString());
			sb.append("\n</onlineCustomer>");
		}
		if ( getApplianceDoctor()==null ) {
			sb.append("\n<applianceDoctor/>");
		} else {
			sb.append("\n<applianceDoctor propertyId=\"9020492966392633516\">");
			sb.append("\n" + getApplianceDoctor().toXmlString());
			sb.append("\n</applianceDoctor>");
		}
		sb.append("\n</StructuredBusiness>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceDoctor(ApplianceDoctor applianceDoctor) {
		this.applianceDoctor=applianceDoctor;
	}
	
	public void z_internalAddToOnlineCustomer(OnlineCustomer onlineCustomer) {
		this.onlineCustomer=onlineCustomer;
	}
	
	public void z_internalAddToSupplier(Supplier supplier) {
		this.supplier.add(supplier);
	}
	
	public void z_internalRemoveFromApplianceDoctor(ApplianceDoctor applianceDoctor) {
		if ( getApplianceDoctor()!=null && applianceDoctor!=null && applianceDoctor.equals(getApplianceDoctor()) ) {
			this.applianceDoctor=null;
			this.applianceDoctor=null;
		}
	}
	
	public void z_internalRemoveFromOnlineCustomer(OnlineCustomer onlineCustomer) {
		if ( getOnlineCustomer()!=null && onlineCustomer!=null && onlineCustomer.equals(getOnlineCustomer()) ) {
			this.onlineCustomer=null;
			this.onlineCustomer=null;
		}
	}
	
	public void z_internalRemoveFromSupplier(Supplier supplier) {
		this.supplier.remove(supplier);
	}

}