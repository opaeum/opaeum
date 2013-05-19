package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
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
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Proxy;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
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
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_pZdQEEtmEeGd4cpyhpib9Q")
@Proxy(lazy=false)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_node",schema="bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="OrganizationNode")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class OrganizationNode implements IOrganizationNode, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization")
	protected BusinessCalendar businessCalendar;
	@Index(columnNames="business_network_id",name="idx_organization_node_business_network_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_id",nullable=true)
	protected BusinessNetwork businessNetwork;
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
	protected Map<String, OrganizationEMailAddress> eMailAddress = new HashMap<String,OrganizationEMailAddress>();
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="organization_node",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends OrganizationNode> mockedAllInstances;
	@Column(name="name")
	@Basic
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="representedOrganization",targetEntity=OrganizationAsBusinessComponent.class)
	protected Set<OrganizationAsBusinessComponent> organizationAsBusinessComponent_businessComponent = new HashSet<OrganizationAsBusinessComponent>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization",targetEntity=OrganizationFullfillsActorRole.class)
	protected Set<OrganizationFullfillsActorRole> organizationFullfillsActorRole_businessActor = new HashSet<OrganizationFullfillsActorRole>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="organization",targetEntity=OrganizationPhoneNumber.class)
	@MapKey(name="z_keyOfPhoneNumberOnOrganizationNode")
	protected Map<String, OrganizationPhoneNumber> phoneNumber = new HashMap<String,OrganizationPhoneNumber>();
	static final private long serialVersionUID = 9636702410571466l;
	private String uid;
	@Column(name="key_in_organiz_on_bus_net")
	private String z_keyOfOrganizationOnBusinessNetwork;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public OrganizationNode(BusinessNetwork owningObject, String name) {
		setName(name);
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
	
	public void addAllToEMailAddress(Set<OrganizationEMailAddress> eMailAddress) {
		for ( OrganizationEMailAddress o : eMailAddress ) {
			addToEMailAddress(o.getType(),o);
		}
	}
	
	public void addAllToPhoneNumber(Set<OrganizationPhoneNumber> phoneNumber) {
		for ( OrganizationPhoneNumber o : phoneNumber ) {
			addToPhoneNumber(o.getType(),o);
		}
	}
	
	public void addToBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null && !this.getBusinessActor().contains(businessActor) ) {
			OrganizationFullfillsActorRole newLink = new OrganizationFullfillsActorRole((OrganizationNode)this,(IBusinessActor)businessActor);
			if ( businessActor.getOrganization()!=null ) {
				businessActor.getOrganization().removeFromBusinessActor(businessActor);
			}
			this.z_internalAddToOrganizationFullfillsActorRole_businessActor(newLink);
			businessActor.z_internalAddToOrganizationFullfillsActorRole_organization(newLink);
		}
	}
	
	public void addToBusinessComponent(IBusinessComponent businessComponent) {
		if ( businessComponent!=null && !this.getBusinessComponent().contains(businessComponent) ) {
			OrganizationAsBusinessComponent newLink = new OrganizationAsBusinessComponent((OrganizationNode)this,(IBusinessComponent)businessComponent);
			if ( businessComponent.getRepresentedOrganization()!=null ) {
				businessComponent.getRepresentedOrganization().removeFromBusinessComponent(businessComponent);
			}
			this.z_internalAddToOrganizationAsBusinessComponent_businessComponent(newLink);
			businessComponent.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(newLink);
		}
	}
	
	public void addToEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			if ( eMailAddress.getOrganization()!=null ) {
				eMailAddress.getOrganization().removeFromEMailAddress(eMailAddress.getType(),eMailAddress);
			}
			eMailAddress.z_internalAddToOrganization(this);
		}
		z_internalAddToEMailAddress(type,eMailAddress);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		if ( getName()==null ) {
			throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'businessNetwork'");
		}
		getBusinessNetwork().z_internalAddToOrganization(this.getName(),(OrganizationNode)this);
	}
	
	public void addToPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			if ( phoneNumber.getOrganization()!=null ) {
				phoneNumber.getOrganization().removeFromPhoneNumber(phoneNumber.getType(),phoneNumber);
			}
			phoneNumber.z_internalAddToOrganization(this);
		}
		z_internalAddToPhoneNumber(type,phoneNumber);
	}
	
	static public Set<? extends OrganizationNode> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.OrganizationNode.class));
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToPhoneNumber(curVal.getType(),curVal);
						curVal.z_internalAddToOrganization(this);
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToEMailAddress(curVal.getType(),curVal);
						curVal.z_internalAddToOrganization(this);
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToBusinessCalendar(curVal);
						curVal.z_internalAddToOrganization(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("72028596279487881")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationFullfillsActorRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToOrganizationFullfillsActorRole_businessActor(curVal);
						curVal.z_internalAddToOrganization(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearBusinessActor() {
		Set<IBusinessActor> tmp = new HashSet<IBusinessActor>(getBusinessActor());
		for ( IBusinessActor o : tmp ) {
			removeFromBusinessActor(o);
		}
	}
	
	public void clearBusinessComponent() {
		Set<IBusinessComponent> tmp = new HashSet<IBusinessComponent>(getBusinessComponent());
		for ( IBusinessComponent o : tmp ) {
			removeFromBusinessComponent(o);
		}
	}
	
	public void clearEMailAddress() {
		Set<OrganizationEMailAddress> tmp = new HashSet<OrganizationEMailAddress>(getEMailAddress());
		for ( OrganizationEMailAddress o : tmp ) {
			removeFromEMailAddress(o.getType(),o);
		}
	}
	
	public void clearPhoneNumber() {
		Set<OrganizationPhoneNumber> tmp = new HashSet<OrganizationPhoneNumber>(getPhoneNumber());
		for ( OrganizationPhoneNumber o : tmp ) {
			removeFromPhoneNumber(o.getType(),o);
		}
	}
	
	public void copyShallowState(OrganizationNode from, OrganizationNode to) {
		to.setName(from.getName());
		if ( from.getBusinessCalendar()!=null ) {
			to.z_internalAddToBusinessCalendar(from.getBusinessCalendar().makeShallowCopy());
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
			to.z_internalAddToBusinessCalendar(from.getBusinessCalendar().makeCopy());
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5524780793534395552l,opposite="organization",uuid="252060@_WjvQ0UtyEeGElKTCe2jfDw")
	public Set<IBusinessActor> getBusinessActor() {
		Set result = new HashSet<IBusinessActor>();
		for ( OrganizationFullfillsActorRole cur : this.getOrganizationFullfillsActorRole_businessActor() ) {
			result.add(cur.getBusinessActor());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2759918346397932051l,opposite="organization",uuid="252060@_8YsOoFZFEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_8YsOoFZFEeGj5_I7bIwNoA")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this.businessCalendar;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5399857156390828985l,opposite="representedOrganization",uuid="252060@_vf2LYFYuEeGj5_I7bIwNoA")
	public Set<IBusinessComponent> getBusinessComponent() {
		Set result = new HashSet<IBusinessComponent>();
		for ( OrganizationAsBusinessComponent cur : this.getOrganizationAsBusinessComponent_businessComponent() ) {
			result.add(cur.getBusinessComponent());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4954312663461917749l,opposite="organization",uuid="252060@_4uxKkUvREeGmqIr8YsFD4g")
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
		String key = type.getUid();
		result=this.eMailAddress.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6276678134555712740l,opposite="organization",uuid="252060@_JF99wEtqEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_JF99wEtqEeGd4cpyhpib9Q")
	public Set<OrganizationEMailAddress> getEMailAddress() {
		Set result = new HashSet<OrganizationEMailAddress>(this.eMailAddress.values());
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=286422379505074318l,uuid="252060@_OorfwEtnEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_OorfwEtnEeGd4cpyhpib9Q")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2861752063599373180l,opposite="representedOrganization",uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pZdQEEtmEeGd4cpyhpib9Q@252060@_vf4noFYuEeGj5_I7bIwNoA")
	public Set<OrganizationAsBusinessComponent> getOrganizationAsBusinessComponent_businessComponent() {
		Set result = this.organizationAsBusinessComponent_businessComponent;
		
		return result;
	}
	
	public OrganizationAsBusinessComponent getOrganizationAsBusinessComponent_businessComponentFor(IBusinessComponent match) {
		for ( OrganizationAsBusinessComponent var : getOrganizationAsBusinessComponent_businessComponent() ) {
			if ( var.getBusinessComponent().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=72028596279487881l,opposite="organization",uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_pZdQEEtmEeGd4cpyhpib9Q@252060@_WjvQ0EtyEeGElKTCe2jfDw")
	public Set<OrganizationFullfillsActorRole> getOrganizationFullfillsActorRole_businessActor() {
		Set result = this.organizationFullfillsActorRole_businessActor;
		
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
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessNetwork();
	}
	
	public OrganizationPhoneNumber getPhoneNumber(OrganizationPhoneNumberType type) {
		OrganizationPhoneNumber result = null;
		String key = type.getUid();
		result=this.phoneNumber.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1861213202254517122l,opposite="organization",uuid="252060@_HF7DgEtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_HF7DgEtoEeGd4cpyhpib9Q")
	public Set<OrganizationPhoneNumber> getPhoneNumber() {
		Set result = new HashSet<OrganizationPhoneNumber>(this.phoneNumber.values());
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfOrganizationOnBusinessNetwork() {
		return this.z_keyOfOrganizationOnBusinessNetwork;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToBusinessNetwork((BusinessNetwork)owner);
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( OrganizationAsBusinessComponent link : new HashSet<OrganizationAsBusinessComponent>(getOrganizationAsBusinessComponent_businessComponent()) ) {
			link.getBusinessComponent().z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(link);
		}
		for ( OrganizationFullfillsActorRole link : new HashSet<OrganizationFullfillsActorRole>(getOrganizationFullfillsActorRole_businessActor()) ) {
			link.getBusinessActor().z_internalRemoveFromOrganizationFullfillsActorRole_organization(link);
		}
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromOrganization(this.getName(),this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("72028596279487881")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationAsBusinessComponent_businessComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2861752063599373180")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationAsBusinessComponent organizationAsBusinessComponent_businessComponent = (OrganizationAsBusinessComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( organizationAsBusinessComponent_businessComponent!=null ) {
							z_internalAddToOrganizationAsBusinessComponent_businessComponent(organizationAsBusinessComponent_businessComponent);
							organizationAsBusinessComponent_businessComponent.z_internalAddToRepresentedOrganization(this);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromBusinessActor(Set<? extends IBusinessActor> businessActor) {
		Set<IBusinessActor> tmp = new HashSet<IBusinessActor>(businessActor);
		for ( IBusinessActor o : tmp ) {
			removeFromBusinessActor(o);
		}
	}
	
	public void removeAllFromBusinessComponent(Set<? extends IBusinessComponent> businessComponent) {
		Set<IBusinessComponent> tmp = new HashSet<IBusinessComponent>(businessComponent);
		for ( IBusinessComponent o : tmp ) {
			removeFromBusinessComponent(o);
		}
	}
	
	public void removeAllFromEMailAddress(Set<? extends OrganizationEMailAddress> eMailAddress) {
		Set<OrganizationEMailAddress> tmp = new HashSet<OrganizationEMailAddress>(eMailAddress);
		for ( OrganizationEMailAddress o : tmp ) {
			removeFromEMailAddress(o.getType(),o);
		}
	}
	
	public void removeAllFromPhoneNumber(Set<? extends OrganizationPhoneNumber> phoneNumber) {
		Set<OrganizationPhoneNumber> tmp = new HashSet<OrganizationPhoneNumber>(phoneNumber);
		for ( OrganizationPhoneNumber o : tmp ) {
			removeFromPhoneNumber(o.getType(),o);
		}
	}
	
	public void removeFromBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null ) {
			OrganizationFullfillsActorRole oldLink = getOrganizationFullfillsActorRole_businessActorFor(businessActor);
			if ( oldLink!=null ) {
				businessActor.z_internalRemoveFromOrganizationFullfillsActorRole_organization(oldLink);
				oldLink.clear();
				z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(oldLink);
			}
		}
	}
	
	public void removeFromBusinessComponent(IBusinessComponent businessComponent) {
		if ( businessComponent!=null ) {
			OrganizationAsBusinessComponent oldLink = getOrganizationAsBusinessComponent_businessComponentFor(businessComponent);
			if ( oldLink!=null ) {
				businessComponent.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(oldLink);
				oldLink.clear();
				z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(oldLink);
			}
		}
	}
	
	public void removeFromEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromOrganization(this);
			z_internalRemoveFromEMailAddress(eMailAddress.getType(),eMailAddress);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromOrganization(this);
			z_internalRemoveFromPhoneNumber(phoneNumber.getType(),phoneNumber);
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
			this.getBusinessNetwork().z_internalRemoveFromOrganization(this.getName(),this);
		}
		if ( businessNetwork == null ) {
			this.z_internalRemoveFromBusinessNetwork(this.getBusinessNetwork());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'businessNetwork'");
			}
			this.z_internalAddToBusinessNetwork(businessNetwork);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToOrganization(this.getName(),this);
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
	
	public void setEMailAddress(Set<OrganizationEMailAddress> eMailAddress) {
		this.clearEMailAddress();
		this.addAllToEMailAddress(eMailAddress);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setName(String name) {
		if ( getBusinessNetwork()!=null && getName()!=null ) {
			getBusinessNetwork().z_internalRemoveFromOrganization(this.getName(),this);
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
		if ( getBusinessNetwork()!=null && getName()!=null ) {
			getBusinessNetwork().z_internalAddToOrganization(this.getName(),this);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPhoneNumber(Set<OrganizationPhoneNumber> phoneNumber) {
		this.clearPhoneNumber();
		this.addAllToPhoneNumber(phoneNumber);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setZ_keyOfOrganizationOnBusinessNetwork(String z_keyOfOrganizationOnBusinessNetwork) {
		this.z_keyOfOrganizationOnBusinessNetwork=z_keyOfOrganizationOnBusinessNetwork;
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
		sb.append("\n<organizationFullfillsActorRole_businessActor propertyId=\"72028596279487881\">");
		for ( OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor : getOrganizationFullfillsActorRole_businessActor() ) {
			sb.append("\n" + organizationFullfillsActorRole_businessActor.toXmlString());
		}
		sb.append("\n</organizationFullfillsActorRole_businessActor>");
		sb.append("\n<organizationAsBusinessComponent_businessComponent propertyId=\"2861752063599373180\">");
		for ( OrganizationAsBusinessComponent organizationAsBusinessComponent_businessComponent : getOrganizationAsBusinessComponent_businessComponent() ) {
			sb.append("\n" + organizationAsBusinessComponent_businessComponent.toXmlReferenceString());
		}
		sb.append("\n</organizationAsBusinessComponent_businessComponent>");
		sb.append("\n</OrganizationNode>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar businessCalendar) {
		if ( businessCalendar.equals(this.businessCalendar) ) {
			return;
		}
		this.businessCalendar=businessCalendar;
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( businessNetwork.equals(this.businessNetwork) ) {
			return;
		}
		this.businessNetwork=businessNetwork;
	}
	
	public void z_internalAddToEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress eMailAddress) {
		String key = type.getUid();
		if ( this.eMailAddress.containsValue(eMailAddress) ) {
			return;
		}
		eMailAddress.z_internalAddToType(type);
		this.eMailAddress.put(key.toString(),eMailAddress);
		eMailAddress.setZ_keyOfEMailAddressOnOrganizationNode(key.toString());
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(this.name) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalAddToOrganizationAsBusinessComponent_businessComponent(OrganizationAsBusinessComponent organizationAsBusinessComponent_businessComponent) {
		if ( getOrganizationAsBusinessComponent_businessComponent().contains(organizationAsBusinessComponent_businessComponent) ) {
			return;
		}
		this.organizationAsBusinessComponent_businessComponent.add(organizationAsBusinessComponent_businessComponent);
	}
	
	public void z_internalAddToOrganizationFullfillsActorRole_businessActor(OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor) {
		if ( getOrganizationFullfillsActorRole_businessActor().contains(organizationFullfillsActorRole_businessActor) ) {
			return;
		}
		this.organizationFullfillsActorRole_businessActor.add(organizationFullfillsActorRole_businessActor);
	}
	
	public void z_internalAddToPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber phoneNumber) {
		String key = type.getUid();
		if ( this.phoneNumber.containsValue(phoneNumber) ) {
			return;
		}
		phoneNumber.z_internalAddToType(type);
		this.phoneNumber.put(key.toString(),phoneNumber);
		phoneNumber.setZ_keyOfPhoneNumberOnOrganizationNode(key.toString());
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar businessCalendar) {
		if ( getBusinessCalendar()!=null && businessCalendar!=null && businessCalendar.equals(getBusinessCalendar()) ) {
			this.businessCalendar=null;
			this.businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( getBusinessNetwork()!=null && businessNetwork!=null && businessNetwork.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromEMailAddress(OrganizationEMailAddressType type, OrganizationEMailAddress eMailAddress) {
		String key = type.getUid();
		this.eMailAddress.remove(key.toString());
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(OrganizationAsBusinessComponent organizationAsBusinessComponent_businessComponent) {
		this.organizationAsBusinessComponent_businessComponent.remove(organizationAsBusinessComponent_businessComponent);
	}
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(OrganizationFullfillsActorRole organizationFullfillsActorRole_businessActor) {
		this.organizationFullfillsActorRole_businessActor.remove(organizationFullfillsActorRole_businessActor);
	}
	
	public void z_internalRemoveFromPhoneNumber(OrganizationPhoneNumberType type, OrganizationPhoneNumber phoneNumber) {
		String key = type.getUid();
		this.phoneNumber.remove(key.toString());
	}

}