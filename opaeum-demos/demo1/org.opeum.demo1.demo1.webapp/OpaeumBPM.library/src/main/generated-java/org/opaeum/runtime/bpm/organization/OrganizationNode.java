package org.opaeum.runtime.bpm.organization;

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
import javax.persistence.MapKey;
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
import org.opaeum.annotation.Property;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddress;
import org.opaeum.runtime.bpm.contact.OrganizationEMailAddressType;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber;
import org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberType;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_pZdQEEtmEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_node")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="OrganizationNode")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class OrganizationNode implements IOrganizationNode, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization")
	private BusinessCalendar businessCalendar;
	@Index(columnNames="business_network_id",name="idx_organization_node_business_network_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_id",nullable=true)
	private BusinessNetwork businessNetwork;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization",targetEntity=OrganizationEMailAddress.class)
	@MapKey(name="z_keyOfEMailAddressOnOrganizationNode")
	private Map<String, OrganizationEMailAddress> eMailAddress = new HashMap<String,OrganizationEMailAddress>();
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<OrganizationNode> mockedAllInstances;
	@Column(name="name")
	private String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization",targetEntity=OrganizationFullfillsActorRole.class)
	private Set<OrganizationFullfillsActorRole> organizationFullfillsActorRole_businessActor = new HashSet<OrganizationFullfillsActorRole>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="representedOrganization",targetEntity=Organization_iBusinessComponent_1.class)
	private Set<Organization_iBusinessComponent_1> organization_iBusinessComponent_1_businessComponent = new HashSet<Organization_iBusinessComponent_1>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization",targetEntity=OrganizationPhoneNumber.class)
	@MapKey(name="z_keyOfPhoneNumberOnOrganizationNode")
	private Map<String, OrganizationPhoneNumber> phoneNumber = new HashMap<String,OrganizationPhoneNumber>();
	static final private long serialVersionUID = 9636702410571466l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public OrganizationNode(BusinessNetwork owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for OrganizationNode
	 */
	public OrganizationNode() {
	}

	public void addAllToBusinessActor(Set<IBusinessActor> businessActor) {
		for ( IBusinessActor o : businessActor ) {
			addToBusinessActor(o);
		}
	}
	
	public void addAllToBusinessComponent(Set<IBusinessComponent> businessComponent) {
		for ( IBusinessComponent o : businessComponent ) {
			addToBusinessComponent(o);
		}
	}
	
	public void addAllToOrganizationFullfillsActorRole_businessActor(Set<OrganizationFullfillsActorRole> organizationFullfillsActorRole_businessActor) {
		for ( OrganizationFullfillsActorRole o : organizationFullfillsActorRole_businessActor ) {
			addToOrganizationFullfillsActorRole_businessActor(o);
		}
	}
	
	public void addAllToOrganization_iBusinessComponent_1_businessComponent(Set<Organization_iBusinessComponent_1> organization_iBusinessComponent_1_businessComponent) {
		for ( Organization_iBusinessComponent_1 o : organization_iBusinessComponent_1_businessComponent ) {
			addToOrganization_iBusinessComponent_1_businessComponent(o);
		}
	}
	
	public void addToBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null ) {
			businessActor.z_internalRemoveFromOrganization(businessActor.getOrganization());
			z_internalAddToBusinessActor(businessActor);
		}
	}
	
	public void addToBusinessComponent(IBusinessComponent businessComponent) {
		if ( businessComponent!=null ) {
			businessComponent.z_internalRemoveFromRepresentedOrganization(businessComponent.getRepresentedOrganization());
			z_internalAddToBusinessComponent(businessComponent);
		}
	}
	
	public void addToEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromOrganization(eMailAddress.getOrganization());
			eMailAddress.z_internalAddToOrganization(this);
			z_internalAddToEMailAddress(type,eMailAddress);
		}
	}
	
	public void addToOrganizationFullfillsActorRole_businessActor(OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor) {
		if ( organizationFullfillsActorRole_businessActor!=null ) {
			organizationFullfillsActorRole_businessActor.z_internalRemoveFromOrganization(organizationFullfillsActorRole_businessActor.getOrganization());
			organizationFullfillsActorRole_businessActor.z_internalAddToOrganization(this);
			z_internalAddToOrganizationFullfillsActorRole_businessActor(organizationFullfillsActorRole_businessActor);
		}
	}
	
	public void addToOrganization_iBusinessComponent_1_businessComponent(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_businessComponent) {
		if ( organization_iBusinessComponent_1_businessComponent!=null ) {
			organization_iBusinessComponent_1_businessComponent.z_internalRemoveFromRepresentedOrganization(organization_iBusinessComponent_1_businessComponent.getRepresentedOrganization());
			organization_iBusinessComponent_1_businessComponent.z_internalAddToRepresentedOrganization(this);
			z_internalAddToOrganization_iBusinessComponent_1_businessComponent(organization_iBusinessComponent_1_businessComponent);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessNetwork().z_internalAddToOrganization((OrganizationNode)this);
	}
	
	public void addToPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromOrganization(phoneNumber.getOrganization());
			phoneNumber.z_internalAddToOrganization(this);
			z_internalAddToPhoneNumber(type,phoneNumber);
		}
	}
	
	static public Set<? extends OrganizationNode> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.OrganizationNode.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("phoneNumber") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1861213202254517122")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationPhoneNumber curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToPhoneNumber(curVal.getType(),curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("eMailAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6276678134555712740")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationEMailAddress curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToEMailAddress(curVal.getType(),curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCalendar") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2759918346397932051")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessCalendar curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setBusinessCalendar(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5544220265950373323")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationFullfillsActorRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToOrganizationFullfillsActorRole_businessActor(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearBusinessActor() {
		removeAllFromBusinessActor(getBusinessActor());
	}
	
	public void clearBusinessComponent() {
		removeAllFromBusinessComponent(getBusinessComponent());
	}
	
	public void clearEMailAddress() {
		Set<OrganizationEMailAddress> tmp = new HashSet<OrganizationEMailAddress>(getEMailAddress());
		for ( OrganizationEMailAddress o : tmp ) {
			removeFromEMailAddress(o.getType(),o);
		}
		eMailAddress.clear();
	}
	
	public void clearOrganizationFullfillsActorRole_businessActor() {
		removeAllFromOrganizationFullfillsActorRole_businessActor(getOrganizationFullfillsActorRole_businessActor());
	}
	
	public void clearOrganization_iBusinessComponent_1_businessComponent() {
		removeAllFromOrganization_iBusinessComponent_1_businessComponent(getOrganization_iBusinessComponent_1_businessComponent());
	}
	
	public void clearPhoneNumber() {
		Set<OrganizationPhoneNumber> tmp = new HashSet<OrganizationPhoneNumber>(getPhoneNumber());
		for ( OrganizationPhoneNumber o : tmp ) {
			removeFromPhoneNumber(o.getType(),o);
		}
		phoneNumber.clear();
	}
	
	public void copyShallowState(OrganizationNode from, OrganizationNode to) {
		to.setName(from.getName());
		if ( from.getBusinessCalendar()!=null ) {
			to.setBusinessCalendar(from.getBusinessCalendar().makeShallowCopy());
		}
	}
	
	public void copyState(OrganizationNode from, OrganizationNode to) {
		to.setName(from.getName());
		for ( OrganizationPhoneNumber child : from.getPhoneNumber() ) {
			to.addToPhoneNumber(child.getType(),child.makeCopy());
		}
		for ( OrganizationEMailAddress child : from.getEMailAddress() ) {
			to.addToEMailAddress(child.getType(),child.makeCopy());
		}
		if ( from.getBusinessCalendar()!=null ) {
			to.setBusinessCalendar(from.getBusinessCalendar().makeCopy());
		}
	}
	
	public BusinessCalendar createBusinessCalendar() {
		BusinessCalendar newInstance= new BusinessCalendar();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public OrganizationEMailAddress createEMailAddress(OrganizationEMailAddressType type) {
		OrganizationEMailAddress newInstance= new OrganizationEMailAddress();
		newInstance.setType(type);
		newInstance.init(this);
		return newInstance;
	}
	
	public OrganizationFullfillsActorRole createOrganizationFullfillsActorRole_businessActor() {
		OrganizationFullfillsActorRole newInstance= new OrganizationFullfillsActorRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public Organization_iBusinessComponent_1 createOrganization_iBusinessComponent_1_businessComponent() {
		Organization_iBusinessComponent_1 newInstance= new Organization_iBusinessComponent_1();
		newInstance.init(this);
		return newInstance;
	}
	
	public OrganizationPhoneNumber createPhoneNumber(OrganizationPhoneNumberType type) {
		OrganizationPhoneNumber newInstance= new OrganizationPhoneNumber();
		newInstance.setType(type);
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OrganizationNode ) {
			return other==this || ((OrganizationNode)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<IBusinessActor> getBusinessActor() {
		Set<IBusinessActor> result = new HashSet<IBusinessActor>();
		for ( OrganizationFullfillsActorRole cur : this.getOrganizationFullfillsActorRole_businessActor() ) {
			result.add(cur.getBusinessActor());
		}
		return result;
	}
	
	@Property(isComposite=true,opposite="organization")
	@NumlMetaInfo(uuid="252060@_8YsOoFZFEeGj5_I7bIwNoA")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this.businessCalendar;
		
		return result;
	}
	
	public Set<IBusinessComponent> getBusinessComponent() {
		Set<IBusinessComponent> result = new HashSet<IBusinessComponent>();
		for ( Organization_iBusinessComponent_1 cur : this.getOrganization_iBusinessComponent_1_businessComponent() ) {
			result.add(cur.getBusinessComponent());
		}
		return result;
	}
	
	@Property(isComposite=false,opposite="organization")
	@NumlMetaInfo(uuid="252060@_4uxKkUvREeGmqIr8YsFD4g")
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = this.businessNetwork;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public OrganizationEMailAddress getEMailAddress(OrganizationEMailAddressType type) {
		OrganizationEMailAddress result = null;
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		result=this.eMailAddress.get(key.toString());
		return result;
	}
	
	@Property(isComposite=true,opposite="organization")
	@NumlMetaInfo(uuid="252060@_JF99wEtqEeGd4cpyhpib9Q")
	public Set<OrganizationEMailAddress> getEMailAddress() {
		Set<OrganizationEMailAddress> result = new HashSet<OrganizationEMailAddress>(this.eMailAddress.values());
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@Property(isComposite=false)
	@NumlMetaInfo(uuid="252060@_OorfwEtnEeGd4cpyhpib9Q")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@Property(isComposite=true,opposite="organization")
	@NumlMetaInfo(uuid="252060@_WjvQ0UtyEeGElKTCe2jfDw252060@_WjvQ0EtyEeGElKTCe2jfDw")
	public Set<OrganizationFullfillsActorRole> getOrganizationFullfillsActorRole_businessActor() {
		Set<OrganizationFullfillsActorRole> result = this.organizationFullfillsActorRole_businessActor;
		
		return result;
	}
	
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_businessActorFor(IBusinessActor match) {
		for ( OrganizationFullfillsActorRole var : getOrganizationFullfillsActorRole_businessActor() ) {
			if ( var.getBusinessActor().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@Property(isComposite=true,opposite="representedOrganization")
	@NumlMetaInfo(uuid="252060@_vf2LYFYuEeGj5_I7bIwNoA252060@_vf4noFYuEeGj5_I7bIwNoA")
	public Set<Organization_iBusinessComponent_1> getOrganization_iBusinessComponent_1_businessComponent() {
		Set<Organization_iBusinessComponent_1> result = this.organization_iBusinessComponent_1_businessComponent;
		
		return result;
	}
	
	public Organization_iBusinessComponent_1 getOrganization_iBusinessComponent_1_businessComponentFor(IBusinessComponent match) {
		for ( Organization_iBusinessComponent_1 var : getOrganization_iBusinessComponent_1_businessComponent() ) {
			if ( var.getBusinessComponent().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessNetwork();
	}
	
	public OrganizationPhoneNumber getPhoneNumber(OrganizationPhoneNumberType type) {
		OrganizationPhoneNumber result = null;
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		result=this.phoneNumber.get(key.toString());
		return result;
	}
	
	@Property(isComposite=true,opposite="organization")
	@NumlMetaInfo(uuid="252060@_HF7DgEtoEeGd4cpyhpib9Q")
	public Set<OrganizationPhoneNumber> getPhoneNumber() {
		Set<OrganizationPhoneNumber> result = new HashSet<OrganizationPhoneNumber>(this.phoneNumber.values());
		
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
		createComponents();
	}
	
	public OrganizationNode makeCopy() {
		OrganizationNode result = new OrganizationNode();
		copyState((OrganizationNode)this,result);
		return result;
	}
	
	public OrganizationNode makeShallowCopy() {
		OrganizationNode result = new OrganizationNode();
		copyShallowState((OrganizationNode)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromOrganization(this);
		}
		for ( OrganizationPhoneNumber child : new ArrayList<OrganizationPhoneNumber>(getPhoneNumber()) ) {
			child.markDeleted();
		}
		for ( OrganizationEMailAddress child : new ArrayList<OrganizationEMailAddress>(getEMailAddress()) ) {
			child.markDeleted();
		}
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().markDeleted();
		}
		for ( OrganizationFullfillsActorRole child : new ArrayList<OrganizationFullfillsActorRole>(getOrganizationFullfillsActorRole_businessActor()) ) {
			child.markDeleted();
		}
		for ( Organization_iBusinessComponent_1 child : new ArrayList<Organization_iBusinessComponent_1>(getOrganization_iBusinessComponent_1_businessComponent()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<OrganizationNode> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("phoneNumber") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1861213202254517122")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationPhoneNumber)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("eMailAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6276678134555712740")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationEMailAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCalendar") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2759918346397932051")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((BusinessCalendar)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5544220265950373323")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_businessComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6254493747225779734")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToOrganization_iBusinessComponent_1_businessComponent((Organization_iBusinessComponent_1)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromBusinessActor(Set<IBusinessActor> businessActor) {
		Set<IBusinessActor> tmp = new HashSet<IBusinessActor>(businessActor);
		for ( IBusinessActor o : tmp ) {
			removeFromBusinessActor(o);
		}
	}
	
	public void removeAllFromBusinessComponent(Set<IBusinessComponent> businessComponent) {
		Set<IBusinessComponent> tmp = new HashSet<IBusinessComponent>(businessComponent);
		for ( IBusinessComponent o : tmp ) {
			removeFromBusinessComponent(o);
		}
	}
	
	public void removeAllFromOrganizationFullfillsActorRole_businessActor(Set<OrganizationFullfillsActorRole> organizationFullfillsActorRole_businessActor) {
		Set<OrganizationFullfillsActorRole> tmp = new HashSet<OrganizationFullfillsActorRole>(organizationFullfillsActorRole_businessActor);
		for ( OrganizationFullfillsActorRole o : tmp ) {
			removeFromOrganizationFullfillsActorRole_businessActor(o);
		}
	}
	
	public void removeAllFromOrganization_iBusinessComponent_1_businessComponent(Set<Organization_iBusinessComponent_1> organization_iBusinessComponent_1_businessComponent) {
		Set<Organization_iBusinessComponent_1> tmp = new HashSet<Organization_iBusinessComponent_1>(organization_iBusinessComponent_1_businessComponent);
		for ( Organization_iBusinessComponent_1 o : tmp ) {
			removeFromOrganization_iBusinessComponent_1_businessComponent(o);
		}
	}
	
	public void removeFromBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null ) {
			z_internalRemoveFromBusinessActor(businessActor);
		}
	}
	
	public void removeFromBusinessComponent(IBusinessComponent businessComponent) {
		if ( businessComponent!=null ) {
			z_internalRemoveFromBusinessComponent(businessComponent);
		}
	}
	
	public void removeFromEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromOrganization(this);
			z_internalRemoveFromEMailAddress(type,eMailAddress);
		}
	}
	
	public void removeFromOrganizationFullfillsActorRole_businessActor(OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor) {
		if ( organizationFullfillsActorRole_businessActor!=null ) {
			organizationFullfillsActorRole_businessActor.z_internalRemoveFromOrganization(this);
			z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(organizationFullfillsActorRole_businessActor);
		}
	}
	
	public void removeFromOrganization_iBusinessComponent_1_businessComponent(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_businessComponent) {
		if ( organization_iBusinessComponent_1_businessComponent!=null ) {
			organization_iBusinessComponent_1_businessComponent.z_internalRemoveFromRepresentedOrganization(this);
			z_internalRemoveFromOrganization_iBusinessComponent_1_businessComponent(organization_iBusinessComponent_1_businessComponent);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromOrganization(this);
			z_internalRemoveFromPhoneNumber(type,phoneNumber);
		}
	}
	
	public void setBusinessActor(Set<IBusinessActor> businessActor) {
		this.clearBusinessActor();
		this.addAllToBusinessActor(businessActor);
	}
	
	public void setBusinessCalendar(BusinessCalendar businessCalendar) {
		BusinessCalendar oldValue = this.getBusinessCalendar();
		if ( oldValue==null ) {
			if ( businessCalendar!=null ) {
				OrganizationNode oldOther = (OrganizationNode)businessCalendar.getOrganization();
				businessCalendar.z_internalRemoveFromOrganization(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessCalendar(businessCalendar);
				}
				businessCalendar.z_internalAddToOrganization((OrganizationNode)this);
			}
			this.z_internalAddToBusinessCalendar(businessCalendar);
		} else {
			if ( !oldValue.equals(businessCalendar) ) {
				oldValue.z_internalRemoveFromOrganization(this);
				z_internalRemoveFromBusinessCalendar(oldValue);
				if ( businessCalendar!=null ) {
					OrganizationNode oldOther = (OrganizationNode)businessCalendar.getOrganization();
					businessCalendar.z_internalRemoveFromOrganization(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessCalendar(businessCalendar);
					}
					businessCalendar.z_internalAddToOrganization((OrganizationNode)this);
				}
				this.z_internalAddToBusinessCalendar(businessCalendar);
			}
		}
	}
	
	public void setBusinessComponent(Set<IBusinessComponent> businessComponent) {
		this.clearBusinessComponent();
		this.addAllToBusinessComponent(businessComponent);
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromOrganization(this);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToOrganization(this);
			this.z_internalAddToBusinessNetwork(businessNetwork);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
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
	
	public void setName(String name) {
		this.z_internalAddToName(name);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganizationFullfillsActorRole_businessActor(Set<OrganizationFullfillsActorRole> organizationFullfillsActorRole_businessActor) {
		this.clearOrganizationFullfillsActorRole_businessActor();
		this.addAllToOrganizationFullfillsActorRole_businessActor(organizationFullfillsActorRole_businessActor);
	}
	
	public void setOrganization_iBusinessComponent_1_businessComponent(Set<Organization_iBusinessComponent_1> organization_iBusinessComponent_1_businessComponent) {
		this.clearOrganization_iBusinessComponent_1_businessComponent();
		this.addAllToOrganization_iBusinessComponent_1_businessComponent(organization_iBusinessComponent_1_businessComponent);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<OrganizationNode uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<OrganizationNode ");
		sb.append("classUuid=\"252060@_pZdQEEtmEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.OrganizationNode\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<phoneNumber propertyId=\"1861213202254517122\">");
		for ( OrganizationPhoneNumber phoneNumber : getPhoneNumber() ) {
			sb.append("\n" + phoneNumber.toXmlString());
		}
		sb.append("\n</phoneNumber>");
		sb.append("\n<eMailAddress propertyId=\"6276678134555712740\">");
		for ( OrganizationEMailAddress eMailAddress : getEMailAddress() ) {
			sb.append("\n" + eMailAddress.toXmlString());
		}
		sb.append("\n</eMailAddress>");
		if ( getBusinessCalendar()==null ) {
			sb.append("\n<businessCalendar/>");
		} else {
			sb.append("\n<businessCalendar propertyId=\"2759918346397932051\">");
			sb.append("\n" + getBusinessCalendar().toXmlString());
			sb.append("\n</businessCalendar>");
		}
		sb.append("\n<organizationFullfillsActorRole_businessActor propertyId=\"5544220265950373323\">");
		for ( OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor : getOrganizationFullfillsActorRole_businessActor() ) {
			sb.append("\n" + organizationFullfillsActorRole_businessActor.toXmlString());
		}
		sb.append("\n</organizationFullfillsActorRole_businessActor>");
		sb.append("\n<organization_iBusinessComponent_1_businessComponent propertyId=\"6254493747225779734\">");
		for ( Organization_iBusinessComponent_1 organization_iBusinessComponent_1_businessComponent : getOrganization_iBusinessComponent_1_businessComponent() ) {
			sb.append("\n" + organization_iBusinessComponent_1_businessComponent.toXmlReferenceString());
		}
		sb.append("\n</organization_iBusinessComponent_1_businessComponent>");
		sb.append("\n</OrganizationNode>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor businessActor) {
		OrganizationFullfillsActorRole newOne = new OrganizationFullfillsActorRole(this,businessActor);
		this.z_internalAddToOrganizationFullfillsActorRole_businessActor(newOne);
		newOne.getBusinessActor().z_internalAddToOrganizationFullfillsActorRole_organization(newOne);
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val) {
		this.businessCalendar=val;
	}
	
	public void z_internalAddToBusinessComponent(IBusinessComponent businessComponent) {
		Organization_iBusinessComponent_1 newOne = new Organization_iBusinessComponent_1(this,businessComponent);
		this.z_internalAddToOrganization_iBusinessComponent_1_businessComponent(newOne);
		newOne.getBusinessComponent().z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(newOne);
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork val) {
		this.businessNetwork=val;
	}
	
	public void z_internalAddToEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress val) {
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		val.z_internalAddToType(type);
		this.eMailAddress.put(key.toString(),val);
		val.setZ_keyOfEMailAddressOnOrganizationNode(key.toString());
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
	}
	
	public void z_internalAddToOrganizationFullfillsActorRole_businessActor(OrganizationFullfillsActorRole val) {
		this.organizationFullfillsActorRole_businessActor.add(val);
	}
	
	public void z_internalAddToOrganization_iBusinessComponent_1_businessComponent(Organization_iBusinessComponent_1 val) {
		this.organization_iBusinessComponent_1_businessComponent.add(val);
	}
	
	public void z_internalAddToPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber val) {
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		val.z_internalAddToType(type);
		this.phoneNumber.put(key.toString(),val);
		val.setZ_keyOfPhoneNumberOnOrganizationNode(key.toString());
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor businessActor) {
		for ( OrganizationFullfillsActorRole cur : new HashSet<OrganizationFullfillsActorRole>(this.organizationFullfillsActorRole_businessActor) ) {
			if ( cur.getBusinessActor().equals(businessActor) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val) {
		if ( getBusinessCalendar()!=null && val!=null && val.equals(getBusinessCalendar()) ) {
			this.businessCalendar=null;
			this.businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromBusinessComponent(IBusinessComponent businessComponent) {
		for ( Organization_iBusinessComponent_1 cur : new HashSet<Organization_iBusinessComponent_1>(this.organization_iBusinessComponent_1_businessComponent) ) {
			if ( cur.getBusinessComponent().equals(businessComponent) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork val) {
		if ( getBusinessNetwork()!=null && val!=null && val.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress val) {
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		this.eMailAddress.remove(key.toString());
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(OrganizationFullfillsActorRole val) {
		this.organizationFullfillsActorRole_businessActor.remove(val);
	}
	
	public void z_internalRemoveFromOrganization_iBusinessComponent_1_businessComponent(Organization_iBusinessComponent_1 val) {
		this.organization_iBusinessComponent_1_businessComponent.remove(val);
	}
	
	public void z_internalRemoveFromPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber val) {
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		this.phoneNumber.remove(key.toString());
	}

}