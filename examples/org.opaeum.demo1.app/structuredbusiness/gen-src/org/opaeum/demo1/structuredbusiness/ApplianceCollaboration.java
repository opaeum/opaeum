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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.BusinessCollaboration_Business;
import org.opaeum.runtime.bpm.organization.BusinessCollaboration_BusinessActor;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_J3MS8HkAEeKsL8ZaFiY2TQ")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="appliance_collaboration",schema="structuredbusiness")
@Entity(name="ApplianceCollaboration")
public class ApplianceCollaboration implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessCollaboration, Serializable {
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="applianceCollaboration")
	protected ApplianceDoctor applianceDoctor;
	@Where(clause="business_collaboration_type='914890@_J3MS8HkAEeKsL8ZaFiY2TQ'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=BusinessCollaboration_BusinessActor.class)
	@JoinColumn(name="business_collaboration",nullable=true)
	protected Set<BusinessCollaboration_BusinessActor> businessCollaboration_BusinessActor_businessActor = new HashSet<BusinessCollaboration_BusinessActor>();
	@Where(clause="business_collaboration_type='914890@_J3MS8HkAEeKsL8ZaFiY2TQ'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=BusinessCollaboration_Business.class)
	@JoinColumn(name="business_collaboration",nullable=true)
	protected Set<BusinessCollaboration_Business> businessCollaboration_Business_business = new HashSet<BusinessCollaboration_Business>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_facilatates_collaboration_business_network_id",nullable=true)
	protected BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="appliance_collaboration",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends ApplianceCollaboration> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="applianceCollaboration",targetEntity=OnlineCustomer.class)
	protected Set<OnlineCustomer> onlineCustomer = new HashSet<OnlineCustomer>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 4051364711987601726l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="applianceCollaboration",targetEntity=Supplier.class)
	protected Set<Supplier> supplier = new HashSet<Supplier>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ApplianceCollaboration(BusinessNetwork owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for ApplianceCollaboration
	 */
	public ApplianceCollaboration() {
	}

	public void addAllToBusinessCollaboration_BusinessActor_businessActor(Set<BusinessCollaboration_BusinessActor> businessCollaboration_BusinessActor_businessActor) {
		for ( BusinessCollaboration_BusinessActor o : businessCollaboration_BusinessActor_businessActor ) {
			addToBusinessCollaboration_BusinessActor_businessActor(o);
		}
	}
	
	public void addAllToBusinessCollaboration_Business_business(Set<BusinessCollaboration_Business> businessCollaboration_Business_business) {
		for ( BusinessCollaboration_Business o : businessCollaboration_Business_business ) {
			addToBusinessCollaboration_Business_business(o);
		}
	}
	
	public void addAllToOnlineCustomer(Set<OnlineCustomer> onlineCustomer) {
		for ( OnlineCustomer o : onlineCustomer ) {
			addToOnlineCustomer(o);
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
	
	public void addToBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor) {
		if ( businessCollaboration_BusinessActor_businessActor!=null ) {
			businessCollaboration_BusinessActor_businessActor.z_internalRemoveFromBusinessCollaboration(businessCollaboration_BusinessActor_businessActor.getBusinessCollaboration());
			businessCollaboration_BusinessActor_businessActor.z_internalAddToBusinessCollaboration(this);
			z_internalAddToBusinessCollaboration_BusinessActor_businessActor(businessCollaboration_BusinessActor_businessActor);
		}
	}
	
	public void addToBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business) {
		if ( businessCollaboration_Business_business!=null ) {
			businessCollaboration_Business_business.z_internalRemoveFromBusinessCollaboration(businessCollaboration_Business_business.getBusinessCollaboration());
			businessCollaboration_Business_business.z_internalAddToBusinessCollaboration(this);
			z_internalAddToBusinessCollaboration_Business_business(businessCollaboration_Business_business);
		}
	}
	
	public void addToOnlineCustomer(OnlineCustomer onlineCustomer) {
		if ( onlineCustomer!=null ) {
			onlineCustomer.z_internalRemoveFromApplianceCollaboration(onlineCustomer.getApplianceCollaboration());
			onlineCustomer.z_internalAddToApplianceCollaboration(this);
			z_internalAddToOnlineCustomer(onlineCustomer);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessNetwork().z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(getBusinessNetworkFacilatatesCollaboration_businessNetwork());
	}
	
	public void addToSupplier(Supplier supplier) {
		if ( supplier!=null ) {
			supplier.z_internalRemoveFromApplianceCollaboration(supplier.getApplianceCollaboration());
			supplier.z_internalAddToApplianceCollaboration(this);
			z_internalAddToSupplier(supplier);
		}
	}
	
	static public Set<? extends ApplianceCollaboration> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.ApplianceCollaboration.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceDoctor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("188468442459986718")) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("onlineCustomer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6747053811070959370")) ) {
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
						this.addToOnlineCustomer(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5617383462103121156")) ) {
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
		}
	}
	
	public void clearBusinessCollaboration_BusinessActor_businessActor() {
		Set<BusinessCollaboration_BusinessActor> tmp = new HashSet<BusinessCollaboration_BusinessActor>(getBusinessCollaboration_BusinessActor_businessActor());
		for ( BusinessCollaboration_BusinessActor o : tmp ) {
			removeFromBusinessCollaboration_BusinessActor_businessActor(o);
		}
	}
	
	public void clearBusinessCollaboration_Business_business() {
		Set<BusinessCollaboration_Business> tmp = new HashSet<BusinessCollaboration_Business>(getBusinessCollaboration_Business_business());
		for ( BusinessCollaboration_Business o : tmp ) {
			removeFromBusinessCollaboration_Business_business(o);
		}
	}
	
	public void clearOnlineCustomer() {
		Set<OnlineCustomer> tmp = new HashSet<OnlineCustomer>(getOnlineCustomer());
		for ( OnlineCustomer o : tmp ) {
			removeFromOnlineCustomer(o);
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
	
	public BusinessNetworkFacilatatesCollaboration createBusinessNetworkFacilatatesCollaboration_businessNetwork() {
		BusinessNetworkFacilatatesCollaboration newInstance= new BusinessNetworkFacilatatesCollaboration();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
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
		if ( other instanceof ApplianceCollaboration ) {
			return other==this || ((ApplianceCollaboration)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=188468442459986718l,opposite="applianceCollaboration",uuid="914890@_RcqPgHkAEeKsL8ZaFiY2TQ")
	@NumlMetaInfo(uuid="914890@_RcqPgHkAEeKsL8ZaFiY2TQ")
	public ApplianceDoctor getApplianceDoctor() {
		ApplianceDoctor result = this.applianceDoctor;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=675923819432853497l,opposite="businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<? extends IBusiness> getBusiness() {
		Set result = new HashSet<IBusiness>();
		for ( BusinessCollaboration_Business cur : this.getBusinessCollaboration_Business_business() ) {
			result.add(cur.getBusiness());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2554691001457309645l,opposite="businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<? extends IBusinessActor> getBusinessActor() {
		Set result = new HashSet<IBusinessActor>();
		for ( BusinessCollaboration_BusinessActor cur : this.getBusinessCollaboration_BusinessActor_businessActor() ) {
			result.add(cur.getBusinessActor());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6013815494391219680l,opposite="businessCollaboration",uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_pP5QQFYuEeGj5_I7bIwNoA")
	public Set<BusinessCollaboration_BusinessActor> getBusinessCollaboration_BusinessActor_businessActor() {
		Set result = this.businessCollaboration_BusinessActor_businessActor;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4027588851766867750l,opposite="businessCollaboration",uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_Rj0BAFYkEeGJUqEGX7bKSg")
	public Set<BusinessCollaboration_Business> getBusinessCollaboration_Business_business() {
		Set result = this.businessCollaboration_Business_business;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8276244440019438797l,opposite="businessCollaboration",uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = null;
		if ( this.businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
			result = this.businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessNetwork();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6711375140756202784l,opposite="businessCollaboration",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_zGMYgEvREeGmqIr8YsFD4g@252060@_YJGvcFYjEeGJUqEGX7bKSg")
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetwork() {
		BusinessNetworkFacilatatesCollaboration result = this.businessNetworkFacilatatesCollaboration_businessNetwork;
		
		return result;
	}
	
	public BusinessNetworkFacilatatesCollaboration getBusinessNetworkFacilatatesCollaboration_businessNetworkFor(BusinessNetwork match) {
		if ( businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessNetwork().equals(match) ) {
			return businessNetworkFacilatatesCollaboration_businessNetwork;
		} else {
			return null;
		}
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
		return "ApplianceCollaboration["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6747053811070959370l,opposite="applianceCollaboration",uuid="914890@_S_5j4HkAEeKsL8ZaFiY2TQ")
	@NumlMetaInfo(uuid="914890@_S_5j4HkAEeKsL8ZaFiY2TQ")
	public Set<OnlineCustomer> getOnlineCustomer() {
		Set result = this.onlineCustomer;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessNetwork();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5617383462103121156l,opposite="applianceCollaboration",uuid="914890@_U8lTAHkAEeKsL8ZaFiY2TQ")
	@NumlMetaInfo(uuid="914890@_U8lTAHkAEeKsL8ZaFiY2TQ")
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
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
		this.z_internalAddToBusinessNetwork((BusinessNetwork)owner);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromBusinessCollaboration(this);
		}
		if ( getApplianceDoctor()!=null ) {
			getApplianceDoctor().markDeleted();
		}
		for ( OnlineCustomer child : new ArrayList<OnlineCustomer>(getOnlineCustomer()) ) {
			child.markDeleted();
		}
		for ( Supplier child : new ArrayList<Supplier>(getSupplier()) ) {
			child.markDeleted();
		}
		if ( getBusinessNetworkFacilatatesCollaboration_businessNetwork()!=null ) {
			getBusinessNetworkFacilatatesCollaboration_businessNetwork().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceDoctor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("188468442459986718")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceDoctor)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("onlineCustomer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6747053811070959370")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OnlineCustomer)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5617383462103121156")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Supplier)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration_Business_business") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4027588851766867750")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToBusinessCollaboration_Business_business((BusinessCollaboration_Business)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration_BusinessActor_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6013815494391219680")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToBusinessCollaboration_BusinessActor_businessActor((BusinessCollaboration_BusinessActor)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromBusinessCollaboration_BusinessActor_businessActor(Set<BusinessCollaboration_BusinessActor> businessCollaboration_BusinessActor_businessActor) {
		Set<BusinessCollaboration_BusinessActor> tmp = new HashSet<BusinessCollaboration_BusinessActor>(businessCollaboration_BusinessActor_businessActor);
		for ( BusinessCollaboration_BusinessActor o : tmp ) {
			removeFromBusinessCollaboration_BusinessActor_businessActor(o);
		}
	}
	
	public void removeAllFromBusinessCollaboration_Business_business(Set<BusinessCollaboration_Business> businessCollaboration_Business_business) {
		Set<BusinessCollaboration_Business> tmp = new HashSet<BusinessCollaboration_Business>(businessCollaboration_Business_business);
		for ( BusinessCollaboration_Business o : tmp ) {
			removeFromBusinessCollaboration_Business_business(o);
		}
	}
	
	public void removeAllFromOnlineCustomer(Set<OnlineCustomer> onlineCustomer) {
		Set<OnlineCustomer> tmp = new HashSet<OnlineCustomer>(onlineCustomer);
		for ( OnlineCustomer o : tmp ) {
			removeFromOnlineCustomer(o);
		}
	}
	
	public void removeAllFromSupplier(Set<Supplier> supplier) {
		Set<Supplier> tmp = new HashSet<Supplier>(supplier);
		for ( Supplier o : tmp ) {
			removeFromSupplier(o);
		}
	}
	
	public void removeFromBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor) {
		if ( businessCollaboration_BusinessActor_businessActor!=null ) {
			businessCollaboration_BusinessActor_businessActor.z_internalRemoveFromBusinessCollaboration(this);
			z_internalRemoveFromBusinessCollaboration_BusinessActor_businessActor(businessCollaboration_BusinessActor_businessActor);
		}
	}
	
	public void removeFromBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business) {
		if ( businessCollaboration_Business_business!=null ) {
			businessCollaboration_Business_business.z_internalRemoveFromBusinessCollaboration(this);
			z_internalRemoveFromBusinessCollaboration_Business_business(businessCollaboration_Business_business);
		}
	}
	
	public void removeFromOnlineCustomer(OnlineCustomer onlineCustomer) {
		if ( onlineCustomer!=null ) {
			onlineCustomer.z_internalRemoveFromApplianceCollaboration(this);
			z_internalRemoveFromOnlineCustomer(onlineCustomer);
			onlineCustomer.markDeleted();
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromSupplier(Supplier supplier) {
		if ( supplier!=null ) {
			supplier.z_internalRemoveFromApplianceCollaboration(this);
			z_internalRemoveFromSupplier(supplier);
			supplier.markDeleted();
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setApplianceDoctor(ApplianceDoctor applianceDoctor) {
		ApplianceDoctor oldValue = this.getApplianceDoctor();
		propertyChangeSupport.firePropertyChange("applianceDoctor",getApplianceDoctor(),applianceDoctor);
		if ( oldValue==null ) {
			if ( applianceDoctor!=null ) {
				ApplianceCollaboration oldOther = (ApplianceCollaboration)applianceDoctor.getApplianceCollaboration();
				applianceDoctor.z_internalRemoveFromApplianceCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromApplianceDoctor(applianceDoctor);
				}
				applianceDoctor.z_internalAddToApplianceCollaboration((ApplianceCollaboration)this);
			}
			this.z_internalAddToApplianceDoctor(applianceDoctor);
		} else {
			if ( !oldValue.equals(applianceDoctor) ) {
				oldValue.z_internalRemoveFromApplianceCollaboration(this);
				z_internalRemoveFromApplianceDoctor(oldValue);
				if ( applianceDoctor!=null ) {
					ApplianceCollaboration oldOther = (ApplianceCollaboration)applianceDoctor.getApplianceCollaboration();
					applianceDoctor.z_internalRemoveFromApplianceCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromApplianceDoctor(applianceDoctor);
					}
					applianceDoctor.z_internalAddToApplianceCollaboration((ApplianceCollaboration)this);
				}
				this.z_internalAddToApplianceDoctor(applianceDoctor);
			}
		}
	}
	
	public void setBusinessCollaboration_BusinessActor_businessActor(Set<BusinessCollaboration_BusinessActor> businessCollaboration_BusinessActor_businessActor) {
		propertyChangeSupport.firePropertyChange("businessCollaboration_BusinessActor_businessActor",getBusinessCollaboration_BusinessActor_businessActor(),businessCollaboration_BusinessActor_businessActor);
		this.clearBusinessCollaboration_BusinessActor_businessActor();
		this.addAllToBusinessCollaboration_BusinessActor_businessActor(businessCollaboration_BusinessActor_businessActor);
	}
	
	public void setBusinessCollaboration_Business_business(Set<BusinessCollaboration_Business> businessCollaboration_Business_business) {
		propertyChangeSupport.firePropertyChange("businessCollaboration_Business_business",getBusinessCollaboration_Business_business(),businessCollaboration_Business_business);
		this.clearBusinessCollaboration_Business_business();
		this.addAllToBusinessCollaboration_Business_business(businessCollaboration_Business_business);
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		propertyChangeSupport.firePropertyChange("businessNetwork",getBusinessNetwork(),businessNetwork);
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromBusinessCollaboration(this);
		}
		if ( businessNetwork == null ) {
			this.z_internalRemoveFromBusinessNetwork(this.getBusinessNetwork());
		} else {
			this.z_internalAddToBusinessNetwork(businessNetwork);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToBusinessCollaboration(this);
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
				ApplianceCollaboration oldOther = (ApplianceCollaboration)businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessCollaboration();
				businessNetworkFacilatatesCollaboration_businessNetwork.z_internalRemoveFromBusinessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
				}
				businessNetworkFacilatatesCollaboration_businessNetwork.z_internalAddToBusinessCollaboration((ApplianceCollaboration)this);
			}
			this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
		} else {
			if ( !oldValue.equals(businessNetworkFacilatatesCollaboration_businessNetwork) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration(this);
				z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(oldValue);
				if ( businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
					ApplianceCollaboration oldOther = (ApplianceCollaboration)businessNetworkFacilatatesCollaboration_businessNetwork.getBusinessCollaboration();
					businessNetworkFacilatatesCollaboration_businessNetwork.z_internalRemoveFromBusinessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
					}
					businessNetworkFacilatatesCollaboration_businessNetwork.z_internalAddToBusinessCollaboration((ApplianceCollaboration)this);
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
	
	public void setOnlineCustomer(Set<OnlineCustomer> onlineCustomer) {
		propertyChangeSupport.firePropertyChange("onlineCustomer",getOnlineCustomer(),onlineCustomer);
		this.clearOnlineCustomer();
		this.addAllToOnlineCustomer(onlineCustomer);
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
		return "<ApplianceCollaboration uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ApplianceCollaboration ");
		sb.append("classUuid=\"914890@_J3MS8HkAEeKsL8ZaFiY2TQ\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.ApplianceCollaboration\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getApplianceDoctor()==null ) {
			sb.append("\n<applianceDoctor/>");
		} else {
			sb.append("\n<applianceDoctor propertyId=\"188468442459986718\">");
			sb.append("\n" + getApplianceDoctor().toXmlString());
			sb.append("\n</applianceDoctor>");
		}
		sb.append("\n<onlineCustomer propertyId=\"6747053811070959370\">");
		for ( OnlineCustomer onlineCustomer : getOnlineCustomer() ) {
			sb.append("\n" + onlineCustomer.toXmlString());
		}
		sb.append("\n</onlineCustomer>");
		sb.append("\n<supplier propertyId=\"5617383462103121156\">");
		for ( Supplier supplier : getSupplier() ) {
			sb.append("\n" + supplier.toXmlString());
		}
		sb.append("\n</supplier>");
		sb.append("\n<businessCollaboration_Business_business propertyId=\"4027588851766867750\">");
		for ( BusinessCollaboration_Business businessCollaboration_Business_business : getBusinessCollaboration_Business_business() ) {
			sb.append("\n" + businessCollaboration_Business_business.toXmlReferenceString());
		}
		sb.append("\n</businessCollaboration_Business_business>");
		sb.append("\n<businessCollaboration_BusinessActor_businessActor propertyId=\"6013815494391219680\">");
		for ( BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor : getBusinessCollaboration_BusinessActor_businessActor() ) {
			sb.append("\n" + businessCollaboration_BusinessActor_businessActor.toXmlReferenceString());
		}
		sb.append("\n</businessCollaboration_BusinessActor_businessActor>");
		sb.append("\n</ApplianceCollaboration>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceDoctor(ApplianceDoctor applianceDoctor) {
		if ( applianceDoctor.equals(getApplianceDoctor()) ) {
			return;
		}
		this.applianceDoctor=applianceDoctor;
	}
	
	public void z_internalAddToBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor) {
		if ( getBusinessCollaboration_BusinessActor_businessActor().contains(businessCollaboration_BusinessActor_businessActor) ) {
			return;
		}
		this.businessCollaboration_BusinessActor_businessActor.add(businessCollaboration_BusinessActor_businessActor);
	}
	
	public void z_internalAddToBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business) {
		if ( getBusinessCollaboration_Business_business().contains(businessCollaboration_Business_business) ) {
			return;
		}
		this.businessCollaboration_Business_business.add(businessCollaboration_Business_business);
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork) {
		BusinessNetworkFacilatatesCollaboration newOne;
		if ( businessNetwork!=null && businessNetwork.equals(getBusinessNetwork()) ) {
			return;
		}
		newOne = new BusinessNetworkFacilatatesCollaboration(this,businessNetwork);
		this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(newOne);
		newOne.getBusinessNetwork().z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(newOne);
	}
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork) {
		if ( businessNetworkFacilatatesCollaboration_businessNetwork.equals(getBusinessNetworkFacilatatesCollaboration_businessNetwork()) ) {
			return;
		}
		this.businessNetworkFacilatatesCollaboration_businessNetwork=businessNetworkFacilatatesCollaboration_businessNetwork;
	}
	
	public void z_internalAddToOnlineCustomer(OnlineCustomer onlineCustomer) {
		if ( getOnlineCustomer().contains(onlineCustomer) ) {
			return;
		}
		this.onlineCustomer.add(onlineCustomer);
	}
	
	public void z_internalAddToSupplier(Supplier supplier) {
		if ( getSupplier().contains(supplier) ) {
			return;
		}
		this.supplier.add(supplier);
	}
	
	public void z_internalRemoveFromApplianceDoctor(ApplianceDoctor applianceDoctor) {
		if ( getApplianceDoctor()!=null && applianceDoctor!=null && applianceDoctor.equals(getApplianceDoctor()) ) {
			this.applianceDoctor=null;
			this.applianceDoctor=null;
		}
	}
	
	public void z_internalRemoveFromBusinessCollaboration_BusinessActor_businessActor(BusinessCollaboration_BusinessActor businessCollaboration_BusinessActor_businessActor) {
		this.businessCollaboration_BusinessActor_businessActor.remove(businessCollaboration_BusinessActor_businessActor);
	}
	
	public void z_internalRemoveFromBusinessCollaboration_Business_business(BusinessCollaboration_Business businessCollaboration_Business_business) {
		this.businessCollaboration_Business_business.remove(businessCollaboration_Business_business);
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( this.businessNetworkFacilatatesCollaboration_businessNetwork!=null ) {
			this.businessNetworkFacilatatesCollaboration_businessNetwork.clear();
		}
	}
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork) {
		if ( getBusinessNetworkFacilatatesCollaboration_businessNetwork()!=null && businessNetworkFacilatatesCollaboration_businessNetwork!=null && businessNetworkFacilatatesCollaboration_businessNetwork.equals(getBusinessNetworkFacilatatesCollaboration_businessNetwork()) ) {
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromOnlineCustomer(OnlineCustomer onlineCustomer) {
		this.onlineCustomer.remove(onlineCustomer);
	}
	
	public void z_internalRemoveFromSupplier(Supplier supplier) {
		this.supplier.remove(supplier);
	}

}