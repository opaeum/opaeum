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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.contact.PersonEMailAddress;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumber;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumberType;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
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
import org.opaeum.runtime.organization.IPerson;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_k23OoEtmEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person",schema="opaeum_bpm",uniqueConstraints={
	@UniqueConstraint(columnNames={"postal_address_id","deleted_on"}),
	@UniqueConstraint(columnNames={"physical_address_id","deleted_on"})})
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Person")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Person implements IPerson, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Column(name="authentication_token")
	private String authenticationToken;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Index(columnNames="collaboration_id",name="idx_person_collaboration_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="collaboration_id",nullable=true)
	private BusinessNetwork collaboration;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person",targetEntity=PersonEMailAddress.class)
	private Set<PersonEMailAddress> eMailAddress = new HashSet<PersonEMailAddress>();
	@Column(name="first_name")
	private String firstName;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	private Long id;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person",targetEntity=Leave.class)
	private Set<Leave> leave = new HashSet<Leave>();
	static private Set<Person> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="representedPerson",targetEntity=PersonFullfillsActorRole.class)
	private Set<PersonFullfillsActorRole> personFullfillsActorRole_businessActor = new HashSet<PersonFullfillsActorRole>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="representedPerson",targetEntity=Person_iBusinessRole_1.class)
	private Set<Person_iBusinessRole_1> person_iBusinessRole_1_businessRole = new HashSet<Person_iBusinessRole_1>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person",targetEntity=PersonPhoneNumber.class)
	private Map<String, PersonPhoneNumber> phoneNumber = new HashMap<String,PersonPhoneNumber>();
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="physical_address_id",nullable=true)
	private PhysicalAddress physicalAddress;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="postal_address_id",nullable=true)
	private PostalAddress postalAddress;
	static final private long serialVersionUID = 3517707551286497542l;
	@Column(name="surname")
	private String surname;
	private String uid;
	@Column(name="username")
	private String username;
	@Column(name="key_in_person_on_bus_net")
	private String z_keyOfPersonOnBusinessNetwork;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param username 
	 */
	public Person(BusinessNetwork owningObject, String username) {
		init(owningObject);
		addToOwningObject();
		setUsername(username);
	}
	
	/** Default constructor for Person
	 */
	public Person() {
	}

	public void addAllToBusinessActor(Set<IBusinessActor> businessActor) {
		for ( IBusinessActor o : businessActor ) {
			addToBusinessActor(o);
		}
	}
	
	public void addAllToBusinessRole(Set<IBusinessRole> businessRole) {
		for ( IBusinessRole o : businessRole ) {
			addToBusinessRole(o);
		}
	}
	
	public void addAllToEMailAddress(Set<PersonEMailAddress> eMailAddress) {
		for ( PersonEMailAddress o : eMailAddress ) {
			addToEMailAddress(o);
		}
	}
	
	public void addAllToLeave(Set<Leave> leave) {
		for ( Leave o : leave ) {
			addToLeave(o);
		}
	}
	
	public void addAllToPersonFullfillsActorRole_businessActor(Set<PersonFullfillsActorRole> personFullfillsActorRole_businessActor) {
		for ( PersonFullfillsActorRole o : personFullfillsActorRole_businessActor ) {
			addToPersonFullfillsActorRole_businessActor(o);
		}
	}
	
	public void addAllToPerson_iBusinessRole_1_businessRole(Set<Person_iBusinessRole_1> person_iBusinessRole_1_businessRole) {
		for ( Person_iBusinessRole_1 o : person_iBusinessRole_1_businessRole ) {
			addToPerson_iBusinessRole_1_businessRole(o);
		}
	}
	
	public void addToBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null ) {
			businessActor.z_internalRemoveFromRepresentedPerson(businessActor.getRepresentedPerson());
			businessActor.z_internalAddToRepresentedPerson(this);
			z_internalAddToBusinessActor(businessActor);
		}
	}
	
	public void addToBusinessRole(IBusinessRole businessRole) {
		if ( businessRole!=null ) {
			businessRole.z_internalRemoveFromRepresentedPerson(businessRole.getRepresentedPerson());
			businessRole.z_internalAddToRepresentedPerson(this);
			z_internalAddToBusinessRole(businessRole);
		}
	}
	
	public void addToEMailAddress(PersonEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromPerson(eMailAddress.getPerson());
			eMailAddress.z_internalAddToPerson(this);
			z_internalAddToEMailAddress(eMailAddress);
		}
	}
	
	public void addToLeave(Leave leave) {
		if ( leave!=null ) {
			leave.z_internalRemoveFromPerson(leave.getPerson());
			leave.z_internalAddToPerson(this);
			z_internalAddToLeave(leave);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getCollaboration().z_internalAddToPerson(this.getUsername(),(Person)this);
	}
	
	public void addToPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole personFullfillsActorRole_businessActor) {
		if ( personFullfillsActorRole_businessActor!=null ) {
			personFullfillsActorRole_businessActor.z_internalRemoveFromRepresentedPerson(personFullfillsActorRole_businessActor.getRepresentedPerson());
			personFullfillsActorRole_businessActor.z_internalAddToRepresentedPerson(this);
			z_internalAddToPersonFullfillsActorRole_businessActor(personFullfillsActorRole_businessActor);
		}
	}
	
	public void addToPerson_iBusinessRole_1_businessRole(Person_iBusinessRole_1 person_iBusinessRole_1_businessRole) {
		if ( person_iBusinessRole_1_businessRole!=null ) {
			person_iBusinessRole_1_businessRole.z_internalRemoveFromRepresentedPerson(person_iBusinessRole_1_businessRole.getRepresentedPerson());
			person_iBusinessRole_1_businessRole.z_internalAddToRepresentedPerson(this);
			z_internalAddToPerson_iBusinessRole_1_businessRole(person_iBusinessRole_1_businessRole);
		}
	}
	
	public void addToPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromPerson(phoneNumber.getPerson());
			phoneNumber.z_internalAddToPerson(this);
			z_internalAddToPhoneNumber(type,phoneNumber);
		}
	}
	
	static public Set<? extends Person> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.Person.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("firstName").length()>0 ) {
			setFirstName(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("firstName")));
		}
		if ( xml.getAttribute("surname").length()>0 ) {
			setSurname(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("surname")));
		}
		if ( xml.getAttribute("authenticationToken").length()>0 ) {
			setAuthenticationToken(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("authenticationToken")));
		}
		if ( xml.getAttribute("username").length()>0 ) {
			setUsername(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("username")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("phoneNumber") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("213312905486829476")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonPhoneNumber curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToPhoneNumber(curVal.getType(),curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("eMailAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("399677207222426596")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonEMailAddress curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToEMailAddress(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("leave") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8728994280524309614")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Leave curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToLeave(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("455318727460481644")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonFullfillsActorRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToPersonFullfillsActorRole_businessActor(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearBusinessActor() {
		removeAllFromBusinessActor(getBusinessActor());
	}
	
	public void clearBusinessRole() {
		removeAllFromBusinessRole(getBusinessRole());
	}
	
	public void clearEMailAddress() {
		removeAllFromEMailAddress(getEMailAddress());
	}
	
	public void clearLeave() {
		removeAllFromLeave(getLeave());
	}
	
	public void clearPersonFullfillsActorRole_businessActor() {
		removeAllFromPersonFullfillsActorRole_businessActor(getPersonFullfillsActorRole_businessActor());
	}
	
	public void clearPerson_iBusinessRole_1_businessRole() {
		removeAllFromPerson_iBusinessRole_1_businessRole(getPerson_iBusinessRole_1_businessRole());
	}
	
	public void clearPhoneNumber() {
		Set<PersonPhoneNumber> tmp = new HashSet<PersonPhoneNumber>(getPhoneNumber());
		for ( PersonPhoneNumber o : tmp ) {
			removeFromPhoneNumber(o.getType(),o);
		}
		phoneNumber.clear();
	}
	
	public void copyShallowState(Person from, Person to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setAuthenticationToken(from.getAuthenticationToken());
		to.setUsername(from.getUsername());
	}
	
	public void copyState(Person from, Person to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		for ( PersonPhoneNumber child : from.getPhoneNumber() ) {
			to.addToPhoneNumber(child.getType(),child.makeCopy());
		}
		for ( PersonEMailAddress child : from.getEMailAddress() ) {
			to.addToEMailAddress(child.makeCopy());
		}
		for ( Leave child : from.getLeave() ) {
			to.addToLeave(child.makeCopy());
		}
		to.setAuthenticationToken(from.getAuthenticationToken());
		to.setUsername(from.getUsername());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Person ) {
			return other==this || ((Person)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_Bih5IEt4EeGElKTCe2jfDw")
	public String getAuthenticationToken() {
		String result = this.authenticationToken;
		
		return result;
	}
	
	public Set<IBusinessActor> getBusinessActor() {
		Set<IBusinessActor> result = new HashSet<IBusinessActor>();
		for ( PersonFullfillsActorRole cur : this.getPersonFullfillsActorRole_businessActor() ) {
			result.add(cur.getBusinessActor());
		}
		return result;
	}
	
	public Set<IBusinessRole> getBusinessRole() {
		Set<IBusinessRole> result = new HashSet<IBusinessRole>();
		for ( Person_iBusinessRole_1 cur : this.getPerson_iBusinessRole_1_businessRole() ) {
			result.add(cur.getBusinessRole());
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@NumlMetaInfo(uuid="252060@_3lspsUvREeGmqIr8YsFD4g")
	public BusinessNetwork getCollaboration() {
		BusinessNetwork result = this.collaboration;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(uuid="252060@_fNec4EtpEeGd4cpyhpib9Q")
	public Set<PersonEMailAddress> getEMailAddress() {
		Set<PersonEMailAddress> result = this.eMailAddress;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_wwPQYEtmEeGd4cpyhpib9Q")
	public String getFirstName() {
		String result = this.firstName;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_oRHdEEtoEeGd4cpyhpib9Q")
	public String getFullName() {
		String result = (this.getFirstName().concat((" "))).concat(this.getSurname());
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_UvAxkEt3EeGElKTCe2jfDw")
	public Set<Leave> getLeave() {
		Set<Leave> result = this.leave;
		
		return result;
	}
	
	public String getName() {
		return "Person["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getCollaboration();
	}
	
	@NumlMetaInfo(uuid="252060@_X4_MgEtyEeGElKTCe2jfDw252060@_X4-lcEtyEeGElKTCe2jfDw")
	public Set<PersonFullfillsActorRole> getPersonFullfillsActorRole_businessActor() {
		Set<PersonFullfillsActorRole> result = this.personFullfillsActorRole_businessActor;
		
		return result;
	}
	
	public PersonFullfillsActorRole getPersonFullfillsActorRole_businessActorFor(IBusinessActor match) {
		for ( PersonFullfillsActorRole var : getPersonFullfillsActorRole_businessActor() ) {
			if ( var.getBusinessActor().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@NumlMetaInfo(uuid="252060@_3lakUFYuEeGj5_I7bIwNoA252060@_3lcZgFYuEeGj5_I7bIwNoA")
	public Set<Person_iBusinessRole_1> getPerson_iBusinessRole_1_businessRole() {
		Set<Person_iBusinessRole_1> result = this.person_iBusinessRole_1_businessRole;
		
		return result;
	}
	
	public Person_iBusinessRole_1 getPerson_iBusinessRole_1_businessRoleFor(IBusinessRole match) {
		for ( Person_iBusinessRole_1 var : getPerson_iBusinessRole_1_businessRole() ) {
			if ( var.getBusinessRole().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	public PersonPhoneNumber getPhoneNumber(PersonPhoneNumberType type) {
		PersonPhoneNumber result = null;
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		result=this.phoneNumber.get(key.toString());
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_GjivMEtoEeGd4cpyhpib9Q")
	public Set<PersonPhoneNumber> getPhoneNumber() {
		Set<PersonPhoneNumber> result = new HashSet<PersonPhoneNumber>(this.phoneNumber.values());
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_U_gx0F-mEeGSPaWW9iQb9Q")
	public PhysicalAddress getPhysicalAddress() {
		PhysicalAddress result = this.physicalAddress;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_Ueg9kF-mEeGSPaWW9iQb9Q")
	public PostalAddress getPostalAddress() {
		PostalAddress result = this.postalAddress;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_xcB_YEtmEeGd4cpyhpib9Q")
	public String getSurname() {
		String result = this.surname;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@NumlMetaInfo(uuid="252060@_DNbUsEt4EeGElKTCe2jfDw")
	public String getUsername() {
		String result = this.username;
		
		return result;
	}
	
	public String getZ_keyOfPersonOnBusinessNetwork() {
		return this.z_keyOfPersonOnBusinessNetwork;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToCollaboration((BusinessNetwork)owner);
		createComponents();
	}
	
	public Person makeCopy() {
		Person result = new Person();
		copyState((Person)this,result);
		return result;
	}
	
	public Person makeShallowCopy() {
		Person result = new Person();
		copyShallowState((Person)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getCollaboration()!=null ) {
			getCollaboration().z_internalRemoveFromPerson(this.getUsername(),this);
		}
		for ( PersonPhoneNumber child : new ArrayList<PersonPhoneNumber>(getPhoneNumber()) ) {
			child.markDeleted();
		}
		for ( PersonEMailAddress child : new ArrayList<PersonEMailAddress>(getEMailAddress()) ) {
			child.markDeleted();
		}
		for ( Leave child : new ArrayList<Leave>(getLeave()) ) {
			child.markDeleted();
		}
		for ( IBusinessRole child : new ArrayList<IBusinessRole>(getBusinessRole()) ) {
			child.markDeleted();
		}
		for ( Person_iBusinessRole_1 child : new ArrayList<Person_iBusinessRole_1>(getPerson_iBusinessRole_1_businessRole()) ) {
			child.markDeleted();
		}
		for ( PersonFullfillsActorRole child : new ArrayList<PersonFullfillsActorRole>(getPersonFullfillsActorRole_businessActor()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Person> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("phoneNumber") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("213312905486829476")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PersonPhoneNumber)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("eMailAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("399677207222426596")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PersonEMailAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("leave") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8728994280524309614")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Leave)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("postalAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3364558357702710040")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setPostalAddress((PostalAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("physicalAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3105719662914651808")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setPhysicalAddress((PhysicalAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("person_iBusinessRole_1_businessRole") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5291344624570808175")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToPerson_iBusinessRole_1_businessRole((Person_iBusinessRole_1)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("455318727460481644")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PersonFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void removeAllFromBusinessRole(Set<IBusinessRole> businessRole) {
		Set<IBusinessRole> tmp = new HashSet<IBusinessRole>(businessRole);
		for ( IBusinessRole o : tmp ) {
			removeFromBusinessRole(o);
		}
	}
	
	public void removeAllFromEMailAddress(Set<PersonEMailAddress> eMailAddress) {
		Set<PersonEMailAddress> tmp = new HashSet<PersonEMailAddress>(eMailAddress);
		for ( PersonEMailAddress o : tmp ) {
			removeFromEMailAddress(o);
		}
	}
	
	public void removeAllFromLeave(Set<Leave> leave) {
		Set<Leave> tmp = new HashSet<Leave>(leave);
		for ( Leave o : tmp ) {
			removeFromLeave(o);
		}
	}
	
	public void removeAllFromPersonFullfillsActorRole_businessActor(Set<PersonFullfillsActorRole> personFullfillsActorRole_businessActor) {
		Set<PersonFullfillsActorRole> tmp = new HashSet<PersonFullfillsActorRole>(personFullfillsActorRole_businessActor);
		for ( PersonFullfillsActorRole o : tmp ) {
			removeFromPersonFullfillsActorRole_businessActor(o);
		}
	}
	
	public void removeAllFromPerson_iBusinessRole_1_businessRole(Set<Person_iBusinessRole_1> person_iBusinessRole_1_businessRole) {
		Set<Person_iBusinessRole_1> tmp = new HashSet<Person_iBusinessRole_1>(person_iBusinessRole_1_businessRole);
		for ( Person_iBusinessRole_1 o : tmp ) {
			removeFromPerson_iBusinessRole_1_businessRole(o);
		}
	}
	
	public void removeFromBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null ) {
			z_internalRemoveFromBusinessActor(businessActor);
		}
	}
	
	public void removeFromBusinessRole(IBusinessRole businessRole) {
		if ( businessRole!=null ) {
			z_internalRemoveFromBusinessRole(businessRole);
		}
	}
	
	public void removeFromEMailAddress(PersonEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromPerson(this);
			z_internalRemoveFromEMailAddress(eMailAddress);
		}
	}
	
	public void removeFromLeave(Leave leave) {
		if ( leave!=null ) {
			leave.z_internalRemoveFromPerson(this);
			z_internalRemoveFromLeave(leave);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole personFullfillsActorRole_businessActor) {
		if ( personFullfillsActorRole_businessActor!=null ) {
			personFullfillsActorRole_businessActor.z_internalRemoveFromRepresentedPerson(this);
			z_internalRemoveFromPersonFullfillsActorRole_businessActor(personFullfillsActorRole_businessActor);
		}
	}
	
	public void removeFromPerson_iBusinessRole_1_businessRole(Person_iBusinessRole_1 person_iBusinessRole_1_businessRole) {
		if ( person_iBusinessRole_1_businessRole!=null ) {
			person_iBusinessRole_1_businessRole.z_internalRemoveFromRepresentedPerson(this);
			z_internalRemoveFromPerson_iBusinessRole_1_businessRole(person_iBusinessRole_1_businessRole);
		}
	}
	
	public void removeFromPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromPerson(this);
			z_internalRemoveFromPhoneNumber(type,phoneNumber);
		}
	}
	
	public void setAuthenticationToken(String authenticationToken) {
		this.z_internalAddToAuthenticationToken(authenticationToken);
	}
	
	public void setBusinessActor(Set<IBusinessActor> businessActor) {
		this.clearBusinessActor();
		this.addAllToBusinessActor(businessActor);
	}
	
	public void setBusinessRole(Set<IBusinessRole> businessRole) {
		this.clearBusinessRole();
		this.addAllToBusinessRole(businessRole);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCollaboration(BusinessNetwork collaboration) {
		if ( this.getCollaboration()!=null ) {
			this.getCollaboration().z_internalRemoveFromPerson(this.getUsername(),this);
		}
		if ( collaboration!=null ) {
			collaboration.z_internalAddToPerson(this.getUsername(),this);
			this.z_internalAddToCollaboration(collaboration);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setEMailAddress(Set<PersonEMailAddress> eMailAddress) {
		this.clearEMailAddress();
		this.addAllToEMailAddress(eMailAddress);
	}
	
	public void setFirstName(String firstName) {
		this.z_internalAddToFirstName(firstName);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setLeave(Set<Leave> leave) {
		this.clearLeave();
		this.addAllToLeave(leave);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPersonFullfillsActorRole_businessActor(Set<PersonFullfillsActorRole> personFullfillsActorRole_businessActor) {
		this.clearPersonFullfillsActorRole_businessActor();
		this.addAllToPersonFullfillsActorRole_businessActor(personFullfillsActorRole_businessActor);
	}
	
	public void setPerson_iBusinessRole_1_businessRole(Set<Person_iBusinessRole_1> person_iBusinessRole_1_businessRole) {
		this.clearPerson_iBusinessRole_1_businessRole();
		this.addAllToPerson_iBusinessRole_1_businessRole(person_iBusinessRole_1_businessRole);
	}
	
	public void setPhysicalAddress(PhysicalAddress physicalAddress) {
		this.z_internalAddToPhysicalAddress(physicalAddress);
	}
	
	public void setPostalAddress(PostalAddress postalAddress) {
		this.z_internalAddToPostalAddress(postalAddress);
	}
	
	public void setSurname(String surname) {
		this.z_internalAddToSurname(surname);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setUsername(String username) {
		if ( getCollaboration()!=null && getUsername()!=null ) {
			getCollaboration().z_internalRemoveFromPerson(this.getUsername(),this);
		}
		this.z_internalAddToUsername(username);
		if ( getCollaboration()!=null && getUsername()!=null ) {
			getCollaboration().z_internalAddToPerson(this.getUsername(),this);
		}
	}
	
	public void setZ_keyOfPersonOnBusinessNetwork(String z_keyOfPersonOnBusinessNetwork) {
		this.z_keyOfPersonOnBusinessNetwork=z_keyOfPersonOnBusinessNetwork;
	}
	
	public String toXmlReferenceString() {
		return "<Person uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Person ");
		sb.append("classUuid=\"252060@_k23OoEtmEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.Person\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getFirstName()!=null ) {
			sb.append("firstName=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getFirstName())+"\" ");
		}
		if ( getSurname()!=null ) {
			sb.append("surname=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getSurname())+"\" ");
		}
		if ( getAuthenticationToken()!=null ) {
			sb.append("authenticationToken=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getAuthenticationToken())+"\" ");
		}
		if ( getUsername()!=null ) {
			sb.append("username=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getUsername())+"\" ");
		}
		sb.append(">");
		sb.append("\n<phoneNumber propertyId=\"213312905486829476\">");
		for ( PersonPhoneNumber phoneNumber : getPhoneNumber() ) {
			sb.append("\n" + phoneNumber.toXmlString());
		}
		sb.append("\n</phoneNumber>");
		sb.append("\n<eMailAddress propertyId=\"399677207222426596\">");
		for ( PersonEMailAddress eMailAddress : getEMailAddress() ) {
			sb.append("\n" + eMailAddress.toXmlString());
		}
		sb.append("\n</eMailAddress>");
		sb.append("\n<leave propertyId=\"8728994280524309614\">");
		for ( Leave leave : getLeave() ) {
			sb.append("\n" + leave.toXmlString());
		}
		sb.append("\n</leave>");
		if ( getPostalAddress()==null ) {
			sb.append("\n<postalAddress/>");
		} else {
			sb.append("\n<postalAddress propertyId=\"3364558357702710040\">");
			sb.append("\n" + getPostalAddress().toXmlReferenceString());
			sb.append("\n</postalAddress>");
		}
		if ( getPhysicalAddress()==null ) {
			sb.append("\n<physicalAddress/>");
		} else {
			sb.append("\n<physicalAddress propertyId=\"3105719662914651808\">");
			sb.append("\n" + getPhysicalAddress().toXmlReferenceString());
			sb.append("\n</physicalAddress>");
		}
		sb.append("\n<person_iBusinessRole_1_businessRole propertyId=\"5291344624570808175\">");
		for ( Person_iBusinessRole_1 person_iBusinessRole_1_businessRole : getPerson_iBusinessRole_1_businessRole() ) {
			sb.append("\n" + person_iBusinessRole_1_businessRole.toXmlReferenceString());
		}
		sb.append("\n</person_iBusinessRole_1_businessRole>");
		sb.append("\n<personFullfillsActorRole_businessActor propertyId=\"455318727460481644\">");
		for ( PersonFullfillsActorRole personFullfillsActorRole_businessActor : getPersonFullfillsActorRole_businessActor() ) {
			sb.append("\n" + personFullfillsActorRole_businessActor.toXmlString());
		}
		sb.append("\n</personFullfillsActorRole_businessActor>");
		sb.append("\n</Person>");
		return sb.toString();
	}
	
	public void z_internalAddToAuthenticationToken(String val) {
		this.authenticationToken=val;
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor businessActor) {
		PersonFullfillsActorRole newOne = new PersonFullfillsActorRole(this,businessActor);
		this.z_internalAddToPersonFullfillsActorRole_businessActor(newOne);
		newOne.getBusinessActor().z_internalAddToPersonFullfillsActorRole_representedPerson(newOne);
	}
	
	public void z_internalAddToBusinessRole(IBusinessRole businessRole) {
		Person_iBusinessRole_1 newOne = new Person_iBusinessRole_1(this,businessRole);
		this.z_internalAddToPerson_iBusinessRole_1_businessRole(newOne);
		newOne.getBusinessRole().z_internalAddToPerson_iBusinessRole_1_representedPerson(newOne);
	}
	
	public void z_internalAddToCollaboration(BusinessNetwork val) {
		this.collaboration=val;
	}
	
	public void z_internalAddToEMailAddress(PersonEMailAddress val) {
		this.eMailAddress.add(val);
	}
	
	public void z_internalAddToFirstName(String val) {
		this.firstName=val;
	}
	
	public void z_internalAddToLeave(Leave val) {
		this.leave.add(val);
	}
	
	public void z_internalAddToPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole val) {
		this.personFullfillsActorRole_businessActor.add(val);
	}
	
	public void z_internalAddToPerson_iBusinessRole_1_businessRole(Person_iBusinessRole_1 val) {
		this.person_iBusinessRole_1_businessRole.add(val);
	}
	
	public void z_internalAddToPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber val) {
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		val.z_internalAddToType(type);
		this.phoneNumber.put(key.toString(),val);
		val.setZ_keyOfPhoneNumberOnPerson(key.toString());
	}
	
	public void z_internalAddToPhysicalAddress(PhysicalAddress val) {
		this.physicalAddress=val;
	}
	
	public void z_internalAddToPostalAddress(PostalAddress val) {
		this.postalAddress=val;
	}
	
	public void z_internalAddToSurname(String val) {
		this.surname=val;
	}
	
	public void z_internalAddToUsername(String val) {
		this.username=val;
	}
	
	public void z_internalRemoveFromAuthenticationToken(String val) {
		if ( getAuthenticationToken()!=null && val!=null && val.equals(getAuthenticationToken()) ) {
			this.authenticationToken=null;
			this.authenticationToken=null;
		}
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor businessActor) {
		for ( PersonFullfillsActorRole cur : new HashSet<PersonFullfillsActorRole>(this.personFullfillsActorRole_businessActor) ) {
			if ( cur.getBusinessActor().equals(businessActor) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromBusinessRole(IBusinessRole businessRole) {
		for ( Person_iBusinessRole_1 cur : new HashSet<Person_iBusinessRole_1>(this.person_iBusinessRole_1_businessRole) ) {
			if ( cur.getBusinessRole().equals(businessRole) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromCollaboration(BusinessNetwork val) {
		if ( getCollaboration()!=null && val!=null && val.equals(getCollaboration()) ) {
			this.collaboration=null;
			this.collaboration=null;
		}
	}
	
	public void z_internalRemoveFromEMailAddress(PersonEMailAddress val) {
		this.eMailAddress.remove(val);
	}
	
	public void z_internalRemoveFromFirstName(String val) {
		if ( getFirstName()!=null && val!=null && val.equals(getFirstName()) ) {
			this.firstName=null;
			this.firstName=null;
		}
	}
	
	public void z_internalRemoveFromLeave(Leave val) {
		this.leave.remove(val);
	}
	
	public void z_internalRemoveFromPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole val) {
		this.personFullfillsActorRole_businessActor.remove(val);
	}
	
	public void z_internalRemoveFromPerson_iBusinessRole_1_businessRole(Person_iBusinessRole_1 val) {
		this.person_iBusinessRole_1_businessRole.remove(val);
	}
	
	public void z_internalRemoveFromPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber val) {
		StringBuilder key = new StringBuilder();
		key.append(type.getUid());
		this.phoneNumber.remove(key.toString());
	}
	
	public void z_internalRemoveFromPhysicalAddress(PhysicalAddress val) {
		if ( getPhysicalAddress()!=null && val!=null && val.equals(getPhysicalAddress()) ) {
			this.physicalAddress=null;
			this.physicalAddress=null;
		}
	}
	
	public void z_internalRemoveFromPostalAddress(PostalAddress val) {
		if ( getPostalAddress()!=null && val!=null && val.equals(getPostalAddress()) ) {
			this.postalAddress=null;
			this.postalAddress=null;
		}
	}
	
	public void z_internalRemoveFromSurname(String val) {
		if ( getSurname()!=null && val!=null && val.equals(getSurname()) ) {
			this.surname=null;
			this.surname=null;
		}
	}
	
	public void z_internalRemoveFromUsername(String val) {
		if ( getUsername()!=null && val!=null && val.equals(getUsername()) ) {
			this.username=null;
			this.username=null;
		}
	}

}