package bpmmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
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
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bpmmodel.util.Stdlib;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_WOOI4I_cEeKWQLWOG6WHmA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_collaboration1")
@Entity(name="BusinessCollaboration1")
public class BusinessCollaboration1 implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessCollaboration, Serializable {
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_facilatates_collaboration_business_network_id",nullable=true)
	protected BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCollaboration1")
	protected Customer customer;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_collaboration1",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends BusinessCollaboration1> mockedAllInstances;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCollaboration1")
	protected MyBusiness myBusiness;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 431479303239275879l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCollaboration1",targetEntity=Supplier.class)
	protected Set<Supplier> supplier = new HashSet<Supplier>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessCollaboration1(BusinessNetwork owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for BusinessCollaboration1
	 */
	public BusinessCollaboration1() {
	}

	public void addAllToSupplier(Set<Supplier> supplier) {
		for ( Supplier o : supplier ) {
			addToSupplier(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessNetwork().z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(getBusinessNetworkFacilatatesCollaboration_businessNetwork());
	}
	
	public void addToSupplier(Supplier supplier) {
		if ( supplier!=null ) {
			if ( supplier.getBusinessCollaboration1()!=null ) {
				supplier.getBusinessCollaboration1().removeFromSupplier(supplier);
			}
			supplier.z_internalAddToBusinessCollaboration1(this);
		}
		z_internalAddToSupplier(supplier);
	}
	
	static public Set<? extends BusinessCollaboration1> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.BusinessCollaboration1.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("myBusiness") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7612831505171661123")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						MyBusiness curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToMyBusiness(curVal);
						curVal.z_internalAddToBusinessCollaboration1(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4122167787844205637")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Customer curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToCustomer(curVal);
						curVal.z_internalAddToBusinessCollaboration1(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7213187247678944025")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Supplier curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToSupplier(curVal);
						curVal.z_internalAddToBusinessCollaboration1(this);
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
	
	public void createComponents() {
		if ( getMyBusiness()==null ) {
			setMyBusiness(new MyBusiness());
		}
		if ( getCustomer()==null ) {
			setCustomer(new Customer());
		}
	}
	
	public Customer createCustomer() {
		Customer newInstance= new Customer();
		newInstance.init(this);
		return newInstance;
	}
	
	public MyBusiness createMyBusiness() {
		MyBusiness newInstance= new MyBusiness();
		newInstance.init(this);
		return newInstance;
	}
	
	public Supplier createSupplier() {
		Supplier newInstance= new Supplier();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessCollaboration1 ) {
			return other==this || ((BusinessCollaboration1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=675923819432853497l,opposite="businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public Set<? extends IBusiness> getBusiness() {
		Set result = new HashSet<IBusiness>();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2554691001457309645l,opposite="businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public Set<? extends IBusinessActor> getBusinessActor() {
		Set result = new HashSet<IBusinessActor>();
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4122167787844205637l,opposite="businessCollaboration1",uuid="bpm.uml@_fb8csI_cEeKWQLWOG6WHmA")
	@NumlMetaInfo(uuid="bpm.uml@_fb8csI_cEeKWQLWOG6WHmA")
	public Customer getCustomer() {
		Customer result = this.customer;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7612831505171661123l,opposite="businessCollaboration1",uuid="bpm.uml@_dNVQEI_cEeKWQLWOG6WHmA")
	@NumlMetaInfo(uuid="bpm.uml@_dNVQEI_cEeKWQLWOG6WHmA")
	public MyBusiness getMyBusiness() {
		MyBusiness result = this.myBusiness;
		
		return result;
	}
	
	public String getName() {
		return "BusinessCollaboration1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessNetwork();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7213187247678944025l,opposite="businessCollaboration1",uuid="bpm.uml@_iMQoMI_cEeKWQLWOG6WHmA")
	@NumlMetaInfo(uuid="bpm.uml@_iMQoMI_cEeKWQLWOG6WHmA")
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
		this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(new BusinessNetworkFacilatatesCollaboration((IBusinessCollaboration)this,(BusinessNetwork)owner));
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessNetwork()!=null ) {
			BusinessNetworkFacilatatesCollaboration link = getBusinessNetworkFacilatatesCollaboration_businessNetwork();
			link.getBusinessNetwork().z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(link);
			link.markDeleted();
		}
		if ( getMyBusiness()!=null ) {
			getMyBusiness().markDeleted();
		}
		if ( getCustomer()!=null ) {
			getCustomer().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("myBusiness") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7612831505171661123")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((MyBusiness)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customer") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4122167787844205637")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Customer)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("supplier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7213187247678944025")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Supplier)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromSupplier(Set<? extends Supplier> supplier) {
		Set<Supplier> tmp = new HashSet<Supplier>(supplier);
		for ( Supplier o : tmp ) {
			removeFromSupplier(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromSupplier(Supplier supplier) {
		if ( supplier!=null ) {
			supplier.z_internalRemoveFromBusinessCollaboration1(this);
			z_internalRemoveFromSupplier(supplier);
			supplier.markDeleted();
		}
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessCollaboration(getBusinessNetworkFacilatatesCollaboration_businessNetwork());
		}
		if ( businessNetwork == null ) {
			this.z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(this.getBusinessNetworkFacilatatesCollaboration_businessNetwork());
		} else {
			BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork = new BusinessNetworkFacilatatesCollaboration((IBusinessCollaboration)this,(BusinessNetwork)businessNetwork);
			this.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(businessNetworkFacilatatesCollaboration_businessNetwork);
			businessNetwork.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessCollaboration(businessNetworkFacilatatesCollaboration_businessNetwork);
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCustomer(Customer customer) {
		Customer oldValue = this.getCustomer();
		if ( oldValue==null ) {
			if ( customer!=null ) {
				BusinessCollaboration1 oldOther = (BusinessCollaboration1)customer.getBusinessCollaboration1();
				customer.z_internalRemoveFromBusinessCollaboration1(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromCustomer(customer);
				}
				customer.z_internalAddToBusinessCollaboration1((BusinessCollaboration1)this);
			}
			this.z_internalAddToCustomer(customer);
		} else {
			if ( !oldValue.equals(customer) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration1(this);
				z_internalRemoveFromCustomer(oldValue);
				if ( customer!=null ) {
					BusinessCollaboration1 oldOther = (BusinessCollaboration1)customer.getBusinessCollaboration1();
					customer.z_internalRemoveFromBusinessCollaboration1(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromCustomer(customer);
					}
					customer.z_internalAddToBusinessCollaboration1((BusinessCollaboration1)this);
				}
				this.z_internalAddToCustomer(customer);
			}
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMyBusiness(MyBusiness myBusiness) {
		MyBusiness oldValue = this.getMyBusiness();
		if ( oldValue==null ) {
			if ( myBusiness!=null ) {
				BusinessCollaboration1 oldOther = (BusinessCollaboration1)myBusiness.getBusinessCollaboration1();
				myBusiness.z_internalRemoveFromBusinessCollaboration1(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromMyBusiness(myBusiness);
				}
				myBusiness.z_internalAddToBusinessCollaboration1((BusinessCollaboration1)this);
			}
			this.z_internalAddToMyBusiness(myBusiness);
		} else {
			if ( !oldValue.equals(myBusiness) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration1(this);
				z_internalRemoveFromMyBusiness(oldValue);
				if ( myBusiness!=null ) {
					BusinessCollaboration1 oldOther = (BusinessCollaboration1)myBusiness.getBusinessCollaboration1();
					myBusiness.z_internalRemoveFromBusinessCollaboration1(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromMyBusiness(myBusiness);
					}
					myBusiness.z_internalAddToBusinessCollaboration1((BusinessCollaboration1)this);
				}
				this.z_internalAddToMyBusiness(myBusiness);
			}
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSupplier(Set<Supplier> supplier) {
		this.clearSupplier();
		this.addAllToSupplier(supplier);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<BusinessCollaboration1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessCollaboration1 ");
		sb.append("classUuid=\"bpm.uml@_WOOI4I_cEeKWQLWOG6WHmA\" ");
		sb.append("className=\"bpmmodel.BusinessCollaboration1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getMyBusiness()==null ) {
			sb.append("\n<myBusiness/>");
		} else {
			sb.append("\n<myBusiness propertyId=\"7612831505171661123\">");
			sb.append("\n" + getMyBusiness().toXmlString());
			sb.append("\n</myBusiness>");
		}
		if ( getCustomer()==null ) {
			sb.append("\n<customer/>");
		} else {
			sb.append("\n<customer propertyId=\"4122167787844205637\">");
			sb.append("\n" + getCustomer().toXmlString());
			sb.append("\n</customer>");
		}
		sb.append("\n<supplier propertyId=\"7213187247678944025\">");
		for ( Supplier supplier : getSupplier() ) {
			sb.append("\n" + supplier.toXmlString());
		}
		sb.append("\n</supplier>");
		sb.append("\n</BusinessCollaboration1>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork) {
		if ( businessNetworkFacilatatesCollaboration_businessNetwork.equals(getBusinessNetworkFacilatatesCollaboration_businessNetwork()) ) {
			return;
		}
		this.businessNetworkFacilatatesCollaboration_businessNetwork=businessNetworkFacilatatesCollaboration_businessNetwork;
	}
	
	public void z_internalAddToCustomer(Customer customer) {
		if ( customer.equals(this.customer) ) {
			return;
		}
		this.customer=customer;
	}
	
	public void z_internalAddToMyBusiness(MyBusiness myBusiness) {
		if ( myBusiness.equals(this.myBusiness) ) {
			return;
		}
		this.myBusiness=myBusiness;
	}
	
	public void z_internalAddToSupplier(Supplier supplier) {
		if ( this.supplier.contains(supplier) ) {
			return;
		}
		this.supplier.add(supplier);
	}
	
	public void z_internalRemoveFromBusinessNetworkFacilatatesCollaboration_businessNetwork(BusinessNetworkFacilatatesCollaboration businessNetworkFacilatatesCollaboration_businessNetwork) {
		if ( getBusinessNetworkFacilatatesCollaboration_businessNetwork()!=null && businessNetworkFacilatatesCollaboration_businessNetwork!=null && businessNetworkFacilatatesCollaboration_businessNetwork.equals(getBusinessNetworkFacilatatesCollaboration_businessNetwork()) ) {
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
			this.businessNetworkFacilatatesCollaboration_businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromCustomer(Customer customer) {
		if ( getCustomer()!=null && customer!=null && customer.equals(getCustomer()) ) {
			this.customer=null;
			this.customer=null;
		}
	}
	
	public void z_internalRemoveFromMyBusiness(MyBusiness myBusiness) {
		if ( getMyBusiness()!=null && myBusiness!=null && myBusiness.equals(getMyBusiness()) ) {
			this.myBusiness=null;
			this.myBusiness=null;
		}
	}
	
	public void z_internalRemoveFromSupplier(Supplier supplier) {
		this.supplier.remove(supplier);
	}

}