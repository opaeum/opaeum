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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.organization.BusinessNetwork;
import org.opaeum.runtime.bpm.organization.BusinessNetworkFacilatatesCollaboration;
import org.opaeum.runtime.bpm.organization.IBusiness;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
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

@AuditMe
@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="structuredbusiness")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Structuredbusiness")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Structuredbusiness implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessCollaboration, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="root",targetEntity=ApplianceDoctor.class)
	private Set<ApplianceDoctor> applianceDoctor = new HashSet<ApplianceDoctor>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="root",targetEntity=BusinessComponent1.class)
	private Set<BusinessComponent1> businessComponent1 = new HashSet<BusinessComponent1>();
	@Index(columnNames="business_network_id",name="idx_structuredbusiness_business_network_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_id",nullable=true)
	private BusinessNetwork businessNetwork;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_facilatates_collaboration_business_network_id",nullable=true)
	private BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Structuredbusiness> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="root",targetEntity=Online_Customer.class)
	private Set<Online_Customer> online_Customer = new HashSet<Online_Customer>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 7737100568581358598l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="root",targetEntity=Supplier.class)
	private Set<Supplier> supplier = new HashSet<Supplier>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Structuredbusiness(BusinessNetwork owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Structuredbusiness
	 */
	public Structuredbusiness() {
	}

	public void addAllToApplianceDoctor(Set<ApplianceDoctor> applianceDoctor) {
		for ( ApplianceDoctor o : applianceDoctor ) {
			addToApplianceDoctor(o);
		}
	}
	
	public void addAllToBusinessComponent1(Set<BusinessComponent1> businessComponent1) {
		for ( BusinessComponent1 o : businessComponent1 ) {
			addToBusinessComponent1(o);
		}
	}
	
	public void addAllToOnline_Customer(Set<Online_Customer> online_Customer) {
		for ( Online_Customer o : online_Customer ) {
			addToOnline_Customer(o);
		}
	}
	
	public void addAllToSupplier(Set<Supplier> supplier) {
		for ( Supplier o : supplier ) {
			addToSupplier(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToApplianceDoctor(ApplianceDoctor applianceDoctor) {
		if ( applianceDoctor!=null ) {
			applianceDoctor.z_internalRemoveFromRoot(applianceDoctor.getRoot());
			applianceDoctor.z_internalAddToRoot(this);
			z_internalAddToApplianceDoctor(applianceDoctor);
		}
	}
	
	public void addToBusinessComponent1(BusinessComponent1 businessComponent1) {
		if ( businessComponent1!=null ) {
			businessComponent1.z_internalRemoveFromRoot(businessComponent1.getRoot());
			businessComponent1.z_internalAddToRoot(this);
			z_internalAddToBusinessComponent1(businessComponent1);
		}
	}
	
	public void addToOnline_Customer(Online_Customer online_Customer) {
		if ( online_Customer!=null ) {
			online_Customer.z_internalRemoveFromRoot(online_Customer.getRoot());
			online_Customer.z_internalAddToRoot(this);
			z_internalAddToOnline_Customer(online_Customer);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessNetwork().z_internalAddToStructuredbusiness((Structuredbusiness)this);
	}
	
	public void addToSupplier(Supplier supplier) {
		if ( supplier!=null ) {
			supplier.z_internalRemoveFromRoot(supplier.getRoot());
			supplier.z_internalAddToRoot(this);
			z_internalAddToSupplier(supplier);
		}
	}
	
	static public Set<? extends Structuredbusiness> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.Structuredbusiness.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4152922492096726072")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Supplier curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToSupplier(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceDoctor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2329680291382308507")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ApplianceDoctor curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToApplianceDoctor(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("online_Customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6808203985768568000")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Online_Customer curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToOnline_Customer(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessComponent1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5983079574207307540")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessComponent1 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToBusinessComponent1(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearApplianceDoctor() {
		removeAllFromApplianceDoctor(getApplianceDoctor());
	}
	
	public void clearBusinessComponent1() {
		removeAllFromBusinessComponent1(getBusinessComponent1());
	}
	
	public void clearOnline_Customer() {
		removeAllFromOnline_Customer(getOnline_Customer());
	}
	
	public void clearSupplier() {
		removeAllFromSupplier(getSupplier());
	}
	
	public ApplianceDoctor createApplianceDoctor() {
		ApplianceDoctor newInstance= new ApplianceDoctor();
		newInstance.init(this);
		return newInstance;
	}
	
	public BusinessComponent1 createBusinessComponent1() {
		BusinessComponent1 newInstance= new BusinessComponent1();
		newInstance.init(this);
		return newInstance;
	}
	
	public BusinessNetworkFacilatatesCollaboration createBusinessNetworkFacilatatesCollaboration_businessNetwork() {
		BusinessNetworkFacilatatesCollaboration newInstance= new BusinessNetworkFacilatatesCollaboration();
		newInstance.init(this);
		return newInstance;
	}
	
	public Online_Customer createOnline_Customer() {
		Online_Customer newInstance= new Online_Customer();
		newInstance.init(this);
		return newInstance;
	}
	
	public Supplier createSupplier() {
		Supplier newInstance= new Supplier();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Structuredbusiness ) {
			return other==this || ((Structuredbusiness)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2329680291382308507l,opposite="root",uuid="914890@_CQTWAGOeEeGwMNo027LgxA")
	public Set<ApplianceDoctor> getApplianceDoctor() {
		Set<ApplianceDoctor> result = this.applianceDoctor;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=675923819432853497l,opposite="businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<IBusiness> getBusiness() {
		Set<IBusiness> result = new HashSet<IBusiness>();
		result.addAll(this.getApplianceDoctor());
		result.addAll(this.getBusinessComponent1());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2554691001457309645l,opposite="businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<IBusinessActor> getBusinessActor() {
		Set<IBusinessActor> result = new HashSet<IBusinessActor>();
		result.addAll(this.getSupplier());
		result.addAll(this.getOnline_Customer());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5983079574207307540l,opposite="root",uuid="914890@_ZXPEIKmhEeGU9ckjWaJA9g")
	public Set<BusinessComponent1> getBusinessComponent1() {
		Set<BusinessComponent1> result = this.businessComponent1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2395627898464121473l,opposite="structuredbusiness",uuid="252060@_NRu9QFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_NRu9QFYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = this.businessNetwork;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4246171799000216537l,opposite="businessCollaboration",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork() {
		BusinessNetworkFacilatatesCollaboration result = this.businessNetworkFacilatatesCollaboration_businessNetwork;
		
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
		return "Structuredbusiness["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6808203985768568000l,opposite="root",uuid="914890@_xQY8oGFKEeG2AvOqZt1NZQ")
	public Set<Online_Customer> getOnline_Customer() {
		Set<Online_Customer> result = this.online_Customer;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessNetwork();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4152922492096726072l,opposite="root",uuid="914890@_-N6PwGK6EeGNuoaMwaBk1w")
	public Set<Supplier> getSupplier() {
		Set<Supplier> result = this.supplier;
		
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
		this.z_internalAddToBusinessNetwork((BusinessNetwork)owner);
	}
	
	public void markDeleted() {
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromStructuredbusiness(this);
		}
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromStructuredbusiness(this);
		}
		for ( Supplier child : new ArrayList<Supplier>(getSupplier()) ) {
			child.markDeleted();
		}
		for ( ApplianceDoctor child : new ArrayList<ApplianceDoctor>(getApplianceDoctor()) ) {
			child.markDeleted();
		}
		for ( Online_Customer child : new ArrayList<Online_Customer>(getOnline_Customer()) ) {
			child.markDeleted();
		}
		for ( BusinessComponent1 child : new ArrayList<BusinessComponent1>(getBusinessComponent1()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Structuredbusiness> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4152922492096726072")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Supplier)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceDoctor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2329680291382308507")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceDoctor)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("online_Customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6808203985768568000")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Online_Customer)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessComponent1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5983079574207307540")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((BusinessComponent1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromApplianceDoctor(Set<ApplianceDoctor> applianceDoctor) {
		Set<ApplianceDoctor> tmp = new HashSet<ApplianceDoctor>(applianceDoctor);
		for ( ApplianceDoctor o : tmp ) {
			removeFromApplianceDoctor(o);
		}
	}
	
	public void removeAllFromBusinessComponent1(Set<BusinessComponent1> businessComponent1) {
		Set<BusinessComponent1> tmp = new HashSet<BusinessComponent1>(businessComponent1);
		for ( BusinessComponent1 o : tmp ) {
			removeFromBusinessComponent1(o);
		}
	}
	
	public void removeAllFromOnline_Customer(Set<Online_Customer> online_Customer) {
		Set<Online_Customer> tmp = new HashSet<Online_Customer>(online_Customer);
		for ( Online_Customer o : tmp ) {
			removeFromOnline_Customer(o);
		}
	}
	
	public void removeAllFromSupplier(Set<Supplier> supplier) {
		Set<Supplier> tmp = new HashSet<Supplier>(supplier);
		for ( Supplier o : tmp ) {
			removeFromSupplier(o);
		}
	}
	
	public void removeFromApplianceDoctor(ApplianceDoctor applianceDoctor) {
		if ( applianceDoctor!=null ) {
			applianceDoctor.z_internalRemoveFromRoot(this);
			z_internalRemoveFromApplianceDoctor(applianceDoctor);
		}
	}
	
	public void removeFromBusinessComponent1(BusinessComponent1 businessComponent1) {
		if ( businessComponent1!=null ) {
			businessComponent1.z_internalRemoveFromRoot(this);
			z_internalRemoveFromBusinessComponent1(businessComponent1);
		}
	}
	
	public void removeFromOnline_Customer(Online_Customer online_Customer) {
		if ( online_Customer!=null ) {
			online_Customer.z_internalRemoveFromRoot(this);
			z_internalRemoveFromOnline_Customer(online_Customer);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromSupplier(Supplier supplier) {
		if ( supplier!=null ) {
			supplier.z_internalRemoveFromRoot(this);
			z_internalRemoveFromSupplier(supplier);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setApplianceDoctor(Set<ApplianceDoctor> applianceDoctor) {
		propertyChangeSupport.firePropertyChange("applianceDoctor",getApplianceDoctor(),applianceDoctor);
		this.clearApplianceDoctor();
		this.addAllToApplianceDoctor(applianceDoctor);
	}
	
	public void setBusinessComponent1(Set<BusinessComponent1> businessComponent1) {
		propertyChangeSupport.firePropertyChange("businessComponent1",getBusinessComponent1(),businessComponent1);
		this.clearBusinessComponent1();
		this.addAllToBusinessComponent1(businessComponent1);
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		propertyChangeSupport.firePropertyChange("businessNetwork",getBusinessNetwork(),businessNetwork);
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromStructuredbusiness(this);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToStructuredbusiness(this);
			this.z_internalAddToBusinessNetwork(businessNetwork);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork) {
		BusinessNetworkFacilatatesCollaboration oldValue = this.getBusinessNetworkFacilatatesCollaboration_businessNetwork();
		propertyChangeSupport.firePropertyChange("businessNetworkFacilatatesCollaboration_businessNetwork",getBusinessNetworkFacilatatesCollaboration_businessNetwork(),businessNetworkFacilatatesCollaboration_businessNetwork);
		if ( oldValue==null ) {
			if ( businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
				Structuredbusiness oldOther = (Structuredbusiness)businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessCollaboration();
				businessNetworkFacilatatesCollaboration_businessNetwork.z_internalRemoveFromBusinessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
				}
				businessNetworkFacilatatesCollaboration_businessNetwork.z_internalAddToBusinessCollaboration((Structuredbusiness)this);
			}
			this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
		} else {
			if ( !oldValue.equals(businessNetworkFacilatatesCollaboration_businessNetwork) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration(this);
				z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(oldValue);
				if ( businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
					Structuredbusiness oldOther = (Structuredbusiness)businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessCollaboration();
					businessNetworkFacilatatesCollaboration_businessNetwork.z_internalRemoveFromBusinessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
					}
					businessNetworkFacilatatesCollaboration_businessNetwork.z_internalAddToBusinessCollaboration((Structuredbusiness)this);
				}
				this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
			}
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
	
	public void setOnline_Customer(Set<Online_Customer> online_Customer) {
		propertyChangeSupport.firePropertyChange("online_Customer",getOnline_Customer(),online_Customer);
		this.clearOnline_Customer();
		this.addAllToOnline_Customer(online_Customer);
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
		return "<Structuredbusiness uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Structuredbusiness ");
		sb.append("classUuid=\"914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration\" ");
		sb.append("className=\"structuredbusiness.Structuredbusiness\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n<supplier propertyId=\"4152922492096726072\">");
		for ( Supplier supplier : getSupplier() ) {
			sb.append("\n" + supplier.toXmlString());
		}
		sb.append("\n</supplier>");
		sb.append("\n<applianceDoctor propertyId=\"2329680291382308507\">");
		for ( ApplianceDoctor applianceDoctor : getApplianceDoctor() ) {
			sb.append("\n" + applianceDoctor.toXmlString());
		}
		sb.append("\n</applianceDoctor>");
		sb.append("\n<online_Customer propertyId=\"6808203985768568000\">");
		for ( Online_Customer online_Customer : getOnline_Customer() ) {
			sb.append("\n" + online_Customer.toXmlString());
		}
		sb.append("\n</online_Customer>");
		sb.append("\n<businessComponent1 propertyId=\"5983079574207307540\">");
		for ( BusinessComponent1 businessComponent1 : getBusinessComponent1() ) {
			sb.append("\n" + businessComponent1.toXmlString());
		}
		sb.append("\n</businessComponent1>");
		sb.append("\n</Structuredbusiness>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceDoctor(ApplianceDoctor val) {
		this.applianceDoctor.add(val);
	}
	
	public void z_internalAddToBusinessComponent1(BusinessComponent1 val) {
		this.businessComponent1.add(val);
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork val) {
		this.businessNetwork=val;
	}
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val) {
		this.businessNetworkFacilatatesCollaboration_businessNetwork=val;
	}
	
	public void z_internalAddToOnline_Customer(Online_Customer val) {
		this.online_Customer.add(val);
	}
	
	public void z_internalAddToSupplier(Supplier val) {
		this.supplier.add(val);
	}
	
	public void z_internalRemoveFromApplianceDoctor(ApplianceDoctor val) {
		this.applianceDoctor.remove(val);
	}
	
	public void z_internalRemoveFromBusinessComponent1(BusinessComponent1 val) {
		this.businessComponent1.remove(val);
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork val) {
		if ( getBusinessNetwork()!=null && val!=null && val.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration val) {
		if ( getBusinessNetworkFacilatatesCollaboration_businessNetwork()!=null && val!=null && val.equals(getBusinessNetworkFacilatatesCollaboration_businessNetwork()) ) {
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromOnline_Customer(Online_Customer val) {
		this.online_Customer.remove(val);
	}
	
	public void z_internalRemoveFromSupplier(Supplier val) {
		this.supplier.remove(val);
	}

}