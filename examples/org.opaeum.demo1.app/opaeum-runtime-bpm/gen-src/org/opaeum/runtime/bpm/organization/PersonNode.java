package org.opaeum.runtime.bpm.organization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.contact.PersonEMailAddress;
import org.opaeum.runtime.bpm.contact.PersonPhoneNumber;
import org.opaeum.runtime.bpm.contact.PhysicalAddress;
import org.opaeum.runtime.bpm.contact.PostalAddress;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.LocaleStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_k23OoEtmEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_node",schema="bpm")
@Entity(name="PersonNode")
public class PersonNode implements IPersonNode, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Column(name="authentication_token")
	@Basic
	protected String authenticationToken;
	@Index(columnNames="business_network_id",name="idx_person_node_business_network_id")
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
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person",targetEntity=PersonEMailAddress.class)
	@MapKey(name="z_keyOfEMailAddressOnPersonNode")
	protected Map<String, PersonEMailAddress> eMailAddress = new HashMap<String,PersonEMailAddress>();
	@Column(name="first_name")
	@Basic
	protected String firstName;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="person_node",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person",targetEntity=Leave.class)
	protected Set<Leave> leave = new HashSet<Leave>();
	static private Set<? extends PersonNode> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="representedPerson",targetEntity=PersonFullfillsActorRole.class)
	protected Set<PersonFullfillsActorRole> personFullfillsActorRole_businessActor = new HashSet<PersonFullfillsActorRole>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="representedPerson",targetEntity=PersonInBusinessRole.class)
	protected Set<PersonInBusinessRole> personInBusinessRole_businessRole = new HashSet<PersonInBusinessRole>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person",targetEntity=PersonPhoneNumber.class)
	@MapKey(name="z_keyOfPhoneNumberOnPersonNode")
	protected Map<String, PersonPhoneNumber> phoneNumber = new HashMap<String,PersonPhoneNumber>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person")
	protected PhysicalAddress physicalAddress;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="person")
	protected PostalAddress postalAddress;
	@Type(type="org.opaeum.runtime.contact.PersonEMailAddressTypeResolver")
	@Column(name="preferred_e_mail_address_type",nullable=true)
	protected PersonEMailAddressType preferredEMailAddressType;
	@Column(name="preferred_locale")
	@Basic
	protected String preferredLocale;
	@Type(type="org.opaeum.runtime.event.NotificationTypeResolver")
	@Column(name="preferred_notification_type",nullable=true)
	protected NotificationType preferredNotificationType;
	@Type(type="org.opaeum.runtime.contact.PersonPhoneNumberTypeResolver")
	@Column(name="preferred_phone_number_type",nullable=true)
	protected PersonPhoneNumberType preferredPhoneNumberType;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Column(name="refresh_token")
	@Basic
	protected String refreshToken;
	static final private long serialVersionUID = 3517707551286497542l;
	@Column(name="surname")
	@Basic
	protected String surname;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="token_expiry_date_time")
	protected Date tokenExpiryDateTime;
	private String uid;
	@Column(name="username")
	@Basic
	protected String username;
	@Column(name="key_in_person_on_bus_net")
	private String z_keyOfPersonOnBusinessNetwork;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param username 
	 */
	public PersonNode(BusinessNetwork owningObject, String username) {
		setUsername(username);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PersonNode
	 */
	public PersonNode() {
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
	
	public void addAllToPersonInBusinessRole_businessRole(Set<PersonInBusinessRole> personInBusinessRole_businessRole) {
		for ( PersonInBusinessRole o : personInBusinessRole_businessRole ) {
			addToPersonInBusinessRole_businessRole(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToBusinessActor(IBusinessActor businessActor) {
		if ( businessActor!=null ) {
			businessActor.z_internalRemoveFromRepresentedPerson(businessActor.getRepresentedPerson());
			z_internalAddToBusinessActor(businessActor);
		}
	}
	
	public void addToBusinessRole(IBusinessRole businessRole) {
		if ( businessRole!=null ) {
			businessRole.z_internalRemoveFromRepresentedPerson(businessRole.getRepresentedPerson());
			z_internalAddToBusinessRole(businessRole);
		}
	}
	
	public void addToEMailAddress(PersonEMailAddressType type, PersonEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromPerson(eMailAddress.getPerson());
			eMailAddress.z_internalAddToPerson(this);
			z_internalAddToEMailAddress(type,eMailAddress);
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
		getBusinessNetwork().z_internalAddToPerson(this.getUsername(),(PersonNode)this);
	}
	
	public void addToPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole personFullfillsActorRole_businessActor) {
		if ( personFullfillsActorRole_businessActor!=null ) {
			personFullfillsActorRole_businessActor.z_internalRemoveFromRepresentedPerson(personFullfillsActorRole_businessActor.getRepresentedPerson());
			personFullfillsActorRole_businessActor.z_internalAddToRepresentedPerson(this);
			z_internalAddToPersonFullfillsActorRole_businessActor(personFullfillsActorRole_businessActor);
		}
	}
	
	public void addToPersonInBusinessRole_businessRole(PersonInBusinessRole personInBusinessRole_businessRole) {
		if ( personInBusinessRole_businessRole!=null ) {
			personInBusinessRole_businessRole.z_internalRemoveFromRepresentedPerson(personInBusinessRole_businessRole.getRepresentedPerson());
			personInBusinessRole_businessRole.z_internalAddToRepresentedPerson(this);
			z_internalAddToPersonInBusinessRole_businessRole(personInBusinessRole_businessRole);
		}
	}
	
	public void addToPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromPerson(phoneNumber.getPerson());
			phoneNumber.z_internalAddToPerson(this);
			z_internalAddToPhoneNumber(type,phoneNumber);
		}
	}
	
	static public Set<? extends PersonNode> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.PersonNode.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("preferredNotificationType").length()>0 ) {
			setPreferredNotificationType(NotificationType.valueOf(xml.getAttribute("preferredNotificationType")));
		}
		if ( xml.getAttribute("preferredEMailAddressType").length()>0 ) {
			setPreferredEMailAddressType(PersonEMailAddressType.valueOf(xml.getAttribute("preferredEMailAddressType")));
		}
		if ( xml.getAttribute("preferredPhoneNumberType").length()>0 ) {
			setPreferredPhoneNumberType(PersonPhoneNumberType.valueOf(xml.getAttribute("preferredPhoneNumberType")));
		}
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
		if ( xml.getAttribute("refreshToken").length()>0 ) {
			setRefreshToken(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("refreshToken")));
		}
		if ( xml.getAttribute("tokenExpiryDateTime").length()>0 ) {
			setTokenExpiryDateTime(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("tokenExpiryDateTime")));
		}
		if ( xml.getAttribute("preferredLocale").length()>0 ) {
			setPreferredLocale(OpaeumLibraryForBPMFormatter.getInstance().parseLocale(xml.getAttribute("preferredLocale")));
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToEMailAddress(curVal.getType(),curVal);
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToLeave(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("postalAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3364558357702710040")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PostalAddress curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setPostalAddress(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("physicalAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3105719662914651808")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PhysicalAddress curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setPhysicalAddress(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8013149829678573783")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonFullfillsActorRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
		Set<IBusinessActor> tmp = new HashSet<IBusinessActor>(getBusinessActor());
		for ( IBusinessActor o : tmp ) {
			removeFromBusinessActor(o);
		}
	}
	
	public void clearBusinessRole() {
		Set<IBusinessRole> tmp = new HashSet<IBusinessRole>(getBusinessRole());
		for ( IBusinessRole o : tmp ) {
			removeFromBusinessRole(o);
		}
	}
	
	public void clearEMailAddress() {
		Set<PersonEMailAddress> tmp = new HashSet<PersonEMailAddress>(getEMailAddress());
		for ( PersonEMailAddress o : tmp ) {
			removeFromEMailAddress(o.getType(),o);
		}
		eMailAddress.clear();
	}
	
	public void clearLeave() {
		Set<Leave> tmp = new HashSet<Leave>(getLeave());
		for ( Leave o : tmp ) {
			removeFromLeave(o);
		}
	}
	
	public void clearPersonFullfillsActorRole_businessActor() {
		Set<PersonFullfillsActorRole> tmp = new HashSet<PersonFullfillsActorRole>(getPersonFullfillsActorRole_businessActor());
		for ( PersonFullfillsActorRole o : tmp ) {
			removeFromPersonFullfillsActorRole_businessActor(o);
		}
	}
	
	public void clearPersonInBusinessRole_businessRole() {
		Set<PersonInBusinessRole> tmp = new HashSet<PersonInBusinessRole>(getPersonInBusinessRole_businessRole());
		for ( PersonInBusinessRole o : tmp ) {
			removeFromPersonInBusinessRole_businessRole(o);
		}
	}
	
	public void clearPhoneNumber() {
		Set<PersonPhoneNumber> tmp = new HashSet<PersonPhoneNumber>(getPhoneNumber());
		for ( PersonPhoneNumber o : tmp ) {
			removeFromPhoneNumber(o.getType(),o);
		}
		phoneNumber.clear();
	}
	
	public void copyShallowState(PersonNode from, PersonNode to) {
		to.setPreferredNotificationType(from.getPreferredNotificationType());
		to.setPreferredEMailAddressType(from.getPreferredEMailAddressType());
		to.setPreferredPhoneNumberType(from.getPreferredPhoneNumberType());
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setAuthenticationToken(from.getAuthenticationToken());
		to.setUsername(from.getUsername());
		if ( from.getPostalAddress()!=null ) {
			to.setPostalAddress(from.getPostalAddress().makeShallowCopy());
		}
		if ( from.getPhysicalAddress()!=null ) {
			to.setPhysicalAddress(from.getPhysicalAddress().makeShallowCopy());
		}
		to.setRefreshToken(from.getRefreshToken());
		to.setTokenExpiryDateTime(from.getTokenExpiryDateTime());
		to.setPreferredLocale(from.getPreferredLocale());
	}
	
	public void copyState(PersonNode from, PersonNode to) {
		to.setPreferredNotificationType(from.getPreferredNotificationType());
		to.setPreferredEMailAddressType(from.getPreferredEMailAddressType());
		to.setPreferredPhoneNumberType(from.getPreferredPhoneNumberType());
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		for ( PersonPhoneNumber child : from.getPhoneNumber() ) {
			to.addToPhoneNumber(child.getType(),child.makeCopy());
		}
		for ( PersonEMailAddress child : from.getEMailAddress() ) {
			to.addToEMailAddress(child.getType(),child.makeCopy());
		}
		for ( Leave child : from.getLeave() ) {
			to.addToLeave(child.makeCopy());
		}
		to.setAuthenticationToken(from.getAuthenticationToken());
		to.setUsername(from.getUsername());
		if ( from.getPostalAddress()!=null ) {
			to.setPostalAddress(from.getPostalAddress().makeCopy());
		}
		if ( from.getPhysicalAddress()!=null ) {
			to.setPhysicalAddress(from.getPhysicalAddress().makeCopy());
		}
		to.setRefreshToken(from.getRefreshToken());
		to.setTokenExpiryDateTime(from.getTokenExpiryDateTime());
		to.setPreferredLocale(from.getPreferredLocale());
	}
	
	public void createComponents() {
		if ( getPhoneNumber().isEmpty() ) {
			PersonPhoneNumber newphoneNumber;
			newphoneNumber= new PersonPhoneNumber();
			addToPhoneNumber(org.opaeum.runtime.contact.PersonPhoneNumberType.HOME,newphoneNumber);
			newphoneNumber= new PersonPhoneNumber();
			addToPhoneNumber(org.opaeum.runtime.contact.PersonPhoneNumberType.CELL,newphoneNumber);
			newphoneNumber= new PersonPhoneNumber();
			addToPhoneNumber(org.opaeum.runtime.contact.PersonPhoneNumberType.WORK,newphoneNumber);
			newphoneNumber= new PersonPhoneNumber();
			addToPhoneNumber(org.opaeum.runtime.contact.PersonPhoneNumberType.FAX,newphoneNumber);
		}
		if ( getPhysicalAddress()==null ) {
			setPhysicalAddress(new PhysicalAddress());
		}
		if ( getPostalAddress()==null ) {
			setPostalAddress(new PostalAddress());
		}
		if ( getEMailAddress().isEmpty() ) {
			PersonEMailAddress neweMailAddress;
			neweMailAddress= new PersonEMailAddress();
			addToEMailAddress(org.opaeum.runtime.contact.PersonEMailAddressType.WORK,neweMailAddress);
			neweMailAddress= new PersonEMailAddress();
			addToEMailAddress(org.opaeum.runtime.contact.PersonEMailAddressType.HOME,neweMailAddress);
		}
	}
	
	public PersonEMailAddress createEMailAddress(PersonEMailAddressType type) {
		PersonEMailAddress newInstance= new PersonEMailAddress();
		newInstance.setType(type);
		newInstance.init(this);
		return newInstance;
	}
	
	public Leave createLeave() {
		Leave newInstance= new Leave();
		newInstance.init(this);
		return newInstance;
	}
	
	public PersonFullfillsActorRole createPersonFullfillsActorRole_businessActor() {
		PersonFullfillsActorRole newInstance= new PersonFullfillsActorRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public PersonPhoneNumber createPhoneNumber(PersonPhoneNumberType type) {
		PersonPhoneNumber newInstance= new PersonPhoneNumber();
		newInstance.setType(type);
		newInstance.init(this);
		return newInstance;
	}
	
	public PhysicalAddress createPhysicalAddress() {
		PhysicalAddress newInstance= new PhysicalAddress();
		newInstance.init(this);
		return newInstance;
	}
	
	public PostalAddress createPostalAddress() {
		PostalAddress newInstance= new PostalAddress();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PersonNode ) {
			return other==this || ((PersonNode)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7236993358477623696l,uuid="252060@_Bih5IEt4EeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_Bih5IEt4EeGElKTCe2jfDw")
	public String getAuthenticationToken() {
		String result = this.authenticationToken;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,lookupMethod="getBusinessActorSourcePopulation",opaeumId=5667532828519993302l,opposite="representedPerson",uuid="252060@_X4_MgEtyEeGElKTCe2jfDw")
	public Set<IBusinessActor> getBusinessActor() {
		Set result = new HashSet<IBusinessActor>();
		for ( PersonFullfillsActorRole cur : this.getPersonFullfillsActorRole_businessActor() ) {
			result.add(cur.getBusinessActor());
		}
		return result;
	}
	
	public Collection<? extends IBusinessActor> getBusinessActorSourcePopulation() {
		Collection result = Stdlib.collectionAsSet(collect1());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4065462070317474495l,opposite="person",uuid="252060@_3lspsUvREeGmqIr8YsFD4g")
	@NumlMetaInfo(uuid="252060@_3lspsUvREeGmqIr8YsFD4g")
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = this.businessNetwork;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3322670798188881229l,opposite="representedPerson",uuid="252060@_3lakUFYuEeGj5_I7bIwNoA")
	public Set<IBusinessRole> getBusinessRole() {
		Set result = new HashSet<IBusinessRole>();
		for ( PersonInBusinessRole cur : this.getPersonInBusinessRole_businessRole() ) {
			result.add(cur.getBusinessRole());
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public PersonEMailAddress getEMailAddress(PersonEMailAddressType type) {
		PersonEMailAddress result = null;
		String key = type.getUid();
		result=this.eMailAddress.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=399677207222426596l,opposite="person",uuid="252060@_fNec4EtpEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_fNec4EtpEeGd4cpyhpib9Q")
	public Set<PersonEMailAddress> getEMailAddress() {
		Set result = new HashSet<PersonEMailAddress>(this.eMailAddress.values());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4316964747083058398l,uuid="252060@_wwPQYEtmEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_wwPQYEtmEeGd4cpyhpib9Q")
	public String getFirstName() {
		String result = this.firstName;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2959327275138211382l,uuid="252060@_oRHdEEtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_oRHdEEtoEeGd4cpyhpib9Q")
	public String getFullName() {
		String result = (this.getFirstName().concat((" "))).concat(this.getSurname());
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=8728994280524309614l,opposite="person",uuid="252060@_UvAxkEt3EeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_UvAxkEt3EeGElKTCe2jfDw")
	public Set<Leave> getLeave() {
		Set result = this.leave;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7064478324996090491l,uuid="252060@_EjZvcIRrEeGh1KS-l3XAqQ")
	@NumlMetaInfo(uuid="252060@_EjZvcIRrEeGh1KS-l3XAqQ")
	public String getName() {
		String result = this.getFullName();
		
		return result;
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=8013149829678573783l,opposite="representedPerson",uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_k23OoEtmEeGd4cpyhpib9Q@252060@_X4-lcEtyEeGElKTCe2jfDw")
	public Set<PersonFullfillsActorRole> getPersonFullfillsActorRole_businessActor() {
		Set result = this.personFullfillsActorRole_businessActor;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7482106327188733314l,opposite="representedPerson",uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_k23OoEtmEeGd4cpyhpib9Q@252060@_3lcZgFYuEeGj5_I7bIwNoA")
	public Set<PersonInBusinessRole> getPersonInBusinessRole_businessRole() {
		Set result = this.personInBusinessRole_businessRole;
		
		return result;
	}
	
	public PersonInBusinessRole getPersonInBusinessRole_businessRoleFor(IBusinessRole match) {
		for ( PersonInBusinessRole var : getPersonInBusinessRole_businessRole() ) {
			if ( var.getBusinessRole().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@NumlMetaInfo(uuid="252060@_6Co6gO0lEeGN-aZ7URyUbw")
	public PersonNode getPersonNode() {
		PersonNode result = this;
		
		return result;
	}
	
	public PersonPhoneNumber getPhoneNumber(PersonPhoneNumberType type) {
		PersonPhoneNumber result = null;
		String key = type.getUid();
		result=this.phoneNumber.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=213312905486829476l,opposite="person",uuid="252060@_GjivMEtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_GjivMEtoEeGd4cpyhpib9Q")
	public Set<PersonPhoneNumber> getPhoneNumber() {
		Set result = new HashSet<PersonPhoneNumber>(this.phoneNumber.values());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3105719662914651808l,opposite="person",uuid="252060@_U_gx0F-mEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_U_gx0F-mEeGSPaWW9iQb9Q")
	public PhysicalAddress getPhysicalAddress() {
		PhysicalAddress result = this.physicalAddress;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3364558357702710040l,opposite="person",uuid="252060@_Ueg9kF-mEeGSPaWW9iQb9Q")
	@NumlMetaInfo(uuid="252060@_Ueg9kF-mEeGSPaWW9iQb9Q")
	public PostalAddress getPostalAddress() {
		PostalAddress result = this.postalAddress;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9142199943122693186l,uuid="252060@_jUSd0O0mEeGN-aZ7URyUbw")
	@NumlMetaInfo(uuid="252060@_jUSd0O0mEeGN-aZ7URyUbw")
	public IPersonEMailAddress getPreferredEMailAddress() {
		IPersonEMailAddress result = this.getPersonNode().getEMailAddress(this.getPreferredEMailAddressType());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4785670204793099058l,uuid="252060@_5anGoO0kEeGN-aZ7URyUbw")
	@NumlMetaInfo(uuid="252060@_5anGoO0kEeGN-aZ7URyUbw")
	public PersonEMailAddressType getPreferredEMailAddressType() {
		PersonEMailAddressType result = this.preferredEMailAddressType;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9065023664849694243l,strategyFactory=LocaleStrategyFactory.class,uuid="252060@_HGMIcPjvEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_HGMIcPjvEeGEN6Fz86uBYA")
	public Locale getPreferredLocale() {
		Locale result = preferredLocale==null?null:Environment.getLocale(preferredLocale);
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4952128734263595888l,uuid="252060@_nqIHkO0kEeGN-aZ7URyUbw")
	@NumlMetaInfo(uuid="252060@_nqIHkO0kEeGN-aZ7URyUbw")
	public NotificationType getPreferredNotificationType() {
		NotificationType result = this.preferredNotificationType;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5794571916709549916l,uuid="252060@_v3024O0mEeGN-aZ7URyUbw")
	@NumlMetaInfo(uuid="252060@_v3024O0mEeGN-aZ7URyUbw")
	public IPersonPhoneNumber getPreferredPhoneNumber() {
		IPersonPhoneNumber result = this.getPersonNode().getPhoneNumber(this.getPreferredPhoneNumberType());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=30338930512177682l,uuid="252060@_6H5DYO0kEeGN-aZ7URyUbw")
	@NumlMetaInfo(uuid="252060@_6H5DYO0kEeGN-aZ7URyUbw")
	public PersonPhoneNumberType getPreferredPhoneNumberType() {
		PersonPhoneNumberType result = this.preferredPhoneNumberType;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6339644735992273166l,uuid="252060@_U9amkHaQEeGv4aLPxieKNg")
	@NumlMetaInfo(uuid="252060@_U9amkHaQEeGv4aLPxieKNg")
	public String getRefreshToken() {
		String result = this.refreshToken;
		
		return result;
	}
	
	public Collection<? extends PersonNode> getReturnSourcePopulation() {
		Collection result = Stdlib.collectionAsSet(this.getBusinessNetwork().getPerson());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4565578190639246320l,uuid="252060@_xcB_YEtmEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_xcB_YEtmEeGd4cpyhpib9Q")
	public String getSurname() {
		String result = this.surname;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3343613060723219152l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_WDO_IHaQEeGv4aLPxieKNg")
	@NumlMetaInfo(uuid="252060@_WDO_IHaQEeGv4aLPxieKNg")
	public Date getTokenExpiryDateTime() {
		Date result = this.tokenExpiryDateTime;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6611446670906621554l,uuid="252060@_DNbUsEt4EeGElKTCe2jfDw")
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
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
		this.z_internalAddToBusinessNetwork((BusinessNetwork)owner);
		this.setPreferredEMailAddressType( PersonEMailAddressType.WORK );
		this.setPreferredPhoneNumberType( PersonPhoneNumberType.CELL );
		this.setPreferredNotificationType( NotificationType.EMAIL );
	}
	
	public PersonNode makeCopy() {
		PersonNode result = new PersonNode();
		copyState((PersonNode)this,result);
		return result;
	}
	
	public PersonNode makeShallowCopy() {
		PersonNode result = new PersonNode();
		copyShallowState((PersonNode)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessNetwork()!=null ) {
			getBusinessNetwork().z_internalRemoveFromPerson(this.getUsername(),this);
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
		if ( getPostalAddress()!=null ) {
			getPostalAddress().markDeleted();
		}
		if ( getPhysicalAddress()!=null ) {
			getPhysicalAddress().markDeleted();
		}
		for ( PersonFullfillsActorRole child : new ArrayList<PersonFullfillsActorRole>(getPersonFullfillsActorRole_businessActor()) ) {
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
						((PostalAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("physicalAddress") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3105719662914651808")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PhysicalAddress)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8013149829678573783")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PersonFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personInBusinessRole_businessRole") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7482106327188733314")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToPersonInBusinessRole_businessRole((PersonInBusinessRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void removeAllFromPersonInBusinessRole_businessRole(Set<PersonInBusinessRole> personInBusinessRole_businessRole) {
		Set<PersonInBusinessRole> tmp = new HashSet<PersonInBusinessRole>(personInBusinessRole_businessRole);
		for ( PersonInBusinessRole o : tmp ) {
			removeFromPersonInBusinessRole_businessRole(o);
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
	
	public void removeFromEMailAddress(PersonEMailAddressType type, PersonEMailAddress eMailAddress) {
		if ( eMailAddress!=null ) {
			eMailAddress.z_internalRemoveFromPerson(this);
			z_internalRemoveFromEMailAddress(type,eMailAddress);
		}
	}
	
	public void removeFromLeave(Leave leave) {
		if ( leave!=null ) {
			leave.z_internalRemoveFromPerson(this);
			z_internalRemoveFromLeave(leave);
			leave.markDeleted();
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole personFullfillsActorRole_businessActor) {
		if ( personFullfillsActorRole_businessActor!=null ) {
			personFullfillsActorRole_businessActor.z_internalRemoveFromRepresentedPerson(this);
			z_internalRemoveFromPersonFullfillsActorRole_businessActor(personFullfillsActorRole_businessActor);
			personFullfillsActorRole_businessActor.markDeleted();
		}
	}
	
	public void removeFromPersonInBusinessRole_businessRole(PersonInBusinessRole personInBusinessRole_businessRole) {
		if ( personInBusinessRole_businessRole!=null ) {
			personInBusinessRole_businessRole.z_internalRemoveFromRepresentedPerson(this);
			z_internalRemoveFromPersonInBusinessRole_businessRole(personInBusinessRole_businessRole);
		}
	}
	
	public void removeFromPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber phoneNumber) {
		if ( phoneNumber!=null ) {
			phoneNumber.z_internalRemoveFromPerson(this);
			z_internalRemoveFromPhoneNumber(type,phoneNumber);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setAuthenticationToken(String authenticationToken) {
		propertyChangeSupport.firePropertyChange("authenticationToken",getAuthenticationToken(),authenticationToken);
		this.z_internalAddToAuthenticationToken(authenticationToken);
	}
	
	public void setBusinessActor(Set<IBusinessActor> businessActor) {
		propertyChangeSupport.firePropertyChange("businessActor",getBusinessActor(),businessActor);
		this.clearBusinessActor();
		this.addAllToBusinessActor(businessActor);
	}
	
	public void setBusinessNetwork(BusinessNetwork businessNetwork) {
		propertyChangeSupport.firePropertyChange("businessNetwork",getBusinessNetwork(),businessNetwork);
		if ( this.getBusinessNetwork()!=null ) {
			this.getBusinessNetwork().z_internalRemoveFromPerson(this.getUsername(),this);
		}
		if ( businessNetwork == null ) {
			this.z_internalRemoveFromBusinessNetwork(this.getBusinessNetwork());
		} else {
			this.z_internalAddToBusinessNetwork(businessNetwork);
		}
		if ( businessNetwork!=null ) {
			businessNetwork.z_internalAddToPerson(this.getUsername(),this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setBusinessRole(Set<IBusinessRole> businessRole) {
		propertyChangeSupport.firePropertyChange("businessRole",getBusinessRole(),businessRole);
		this.clearBusinessRole();
		this.addAllToBusinessRole(businessRole);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setFirstName(String firstName) {
		propertyChangeSupport.firePropertyChange("firstName",getFirstName(),firstName);
		this.z_internalAddToFirstName(firstName);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setLeave(Set<Leave> leave) {
		propertyChangeSupport.firePropertyChange("leave",getLeave(),leave);
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
		propertyChangeSupport.firePropertyChange("personFullfillsActorRole_businessActor",getPersonFullfillsActorRole_businessActor(),personFullfillsActorRole_businessActor);
		this.clearPersonFullfillsActorRole_businessActor();
		this.addAllToPersonFullfillsActorRole_businessActor(personFullfillsActorRole_businessActor);
	}
	
	public void setPersonInBusinessRole_businessRole(Set<PersonInBusinessRole> personInBusinessRole_businessRole) {
		propertyChangeSupport.firePropertyChange("personInBusinessRole_businessRole",getPersonInBusinessRole_businessRole(),personInBusinessRole_businessRole);
		this.clearPersonInBusinessRole_businessRole();
		this.addAllToPersonInBusinessRole_businessRole(personInBusinessRole_businessRole);
	}
	
	public void setPhysicalAddress(PhysicalAddress physicalAddress) {
		PhysicalAddress oldValue = this.getPhysicalAddress();
		propertyChangeSupport.firePropertyChange("physicalAddress",getPhysicalAddress(),physicalAddress);
		if ( oldValue==null ) {
			if ( physicalAddress!=null ) {
				PersonNode oldOther = (PersonNode)physicalAddress.getPerson();
				physicalAddress.z_internalRemoveFromPerson(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPhysicalAddress(physicalAddress);
				}
				physicalAddress.z_internalAddToPerson((PersonNode)this);
			}
			this.z_internalAddToPhysicalAddress(physicalAddress);
		} else {
			if ( !oldValue.equals(physicalAddress) ) {
				oldValue.z_internalRemoveFromPerson(this);
				z_internalRemoveFromPhysicalAddress(oldValue);
				if ( physicalAddress!=null ) {
					PersonNode oldOther = (PersonNode)physicalAddress.getPerson();
					physicalAddress.z_internalRemoveFromPerson(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPhysicalAddress(physicalAddress);
					}
					physicalAddress.z_internalAddToPerson((PersonNode)this);
				}
				this.z_internalAddToPhysicalAddress(physicalAddress);
			}
		}
	}
	
	public void setPostalAddress(PostalAddress postalAddress) {
		PostalAddress oldValue = this.getPostalAddress();
		propertyChangeSupport.firePropertyChange("postalAddress",getPostalAddress(),postalAddress);
		if ( oldValue==null ) {
			if ( postalAddress!=null ) {
				PersonNode oldOther = (PersonNode)postalAddress.getPerson();
				postalAddress.z_internalRemoveFromPerson(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPostalAddress(postalAddress);
				}
				postalAddress.z_internalAddToPerson((PersonNode)this);
			}
			this.z_internalAddToPostalAddress(postalAddress);
		} else {
			if ( !oldValue.equals(postalAddress) ) {
				oldValue.z_internalRemoveFromPerson(this);
				z_internalRemoveFromPostalAddress(oldValue);
				if ( postalAddress!=null ) {
					PersonNode oldOther = (PersonNode)postalAddress.getPerson();
					postalAddress.z_internalRemoveFromPerson(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPostalAddress(postalAddress);
					}
					postalAddress.z_internalAddToPerson((PersonNode)this);
				}
				this.z_internalAddToPostalAddress(postalAddress);
			}
		}
	}
	
	public void setPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		propertyChangeSupport.firePropertyChange("preferredEMailAddressType",getPreferredEMailAddressType(),preferredEMailAddressType);
		this.z_internalAddToPreferredEMailAddressType(preferredEMailAddressType);
	}
	
	public void setPreferredLocale(Locale preferredLocale) {
		propertyChangeSupport.firePropertyChange("preferredLocale",getPreferredLocale(),preferredLocale);
		this.z_internalAddToPreferredLocale(preferredLocale);
	}
	
	public void setPreferredNotificationType(NotificationType preferredNotificationType) {
		propertyChangeSupport.firePropertyChange("preferredNotificationType",getPreferredNotificationType(),preferredNotificationType);
		this.z_internalAddToPreferredNotificationType(preferredNotificationType);
	}
	
	public void setPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		propertyChangeSupport.firePropertyChange("preferredPhoneNumberType",getPreferredPhoneNumberType(),preferredPhoneNumberType);
		this.z_internalAddToPreferredPhoneNumberType(preferredPhoneNumberType);
	}
	
	public void setRefreshToken(String refreshToken) {
		propertyChangeSupport.firePropertyChange("refreshToken",getRefreshToken(),refreshToken);
		this.z_internalAddToRefreshToken(refreshToken);
	}
	
	public void setSurname(String surname) {
		propertyChangeSupport.firePropertyChange("surname",getSurname(),surname);
		this.z_internalAddToSurname(surname);
	}
	
	public void setTokenExpiryDateTime(Date tokenExpiryDateTime) {
		propertyChangeSupport.firePropertyChange("tokenExpiryDateTime",getTokenExpiryDateTime(),tokenExpiryDateTime);
		this.z_internalAddToTokenExpiryDateTime(tokenExpiryDateTime);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setUsername(String username) {
		propertyChangeSupport.firePropertyChange("username",getUsername(),username);
		if ( getBusinessNetwork()!=null && getUsername()!=null ) {
			getBusinessNetwork().z_internalRemoveFromPerson(this.getUsername(),this);
		}
		this.z_internalAddToUsername(username);
		if ( getBusinessNetwork()!=null && getUsername()!=null ) {
			getBusinessNetwork().z_internalAddToPerson(this.getUsername(),this);
		}
	}
	
	public void setZ_keyOfPersonOnBusinessNetwork(String z_keyOfPersonOnBusinessNetwork) {
		this.z_keyOfPersonOnBusinessNetwork=z_keyOfPersonOnBusinessNetwork;
	}
	
	public String toXmlReferenceString() {
		return "<PersonNode uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PersonNode ");
		sb.append("classUuid=\"252060@_k23OoEtmEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.PersonNode\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getPreferredNotificationType()!=null ) {
			sb.append("preferredNotificationType=\""+ getPreferredNotificationType().name() + "\" ");
		}
		if ( getPreferredEMailAddressType()!=null ) {
			sb.append("preferredEMailAddressType=\""+ getPreferredEMailAddressType().name() + "\" ");
		}
		if ( getPreferredPhoneNumberType()!=null ) {
			sb.append("preferredPhoneNumberType=\""+ getPreferredPhoneNumberType().name() + "\" ");
		}
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
		if ( getRefreshToken()!=null ) {
			sb.append("refreshToken=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getRefreshToken())+"\" ");
		}
		if ( getTokenExpiryDateTime()!=null ) {
			sb.append("tokenExpiryDateTime=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getTokenExpiryDateTime())+"\" ");
		}
		if ( getPreferredLocale()!=null ) {
			sb.append("preferredLocale=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatLocale(getPreferredLocale())+"\" ");
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
			sb.append("\n" + getPostalAddress().toXmlString());
			sb.append("\n</postalAddress>");
		}
		if ( getPhysicalAddress()==null ) {
			sb.append("\n<physicalAddress/>");
		} else {
			sb.append("\n<physicalAddress propertyId=\"3105719662914651808\">");
			sb.append("\n" + getPhysicalAddress().toXmlString());
			sb.append("\n</physicalAddress>");
		}
		sb.append("\n<personFullfillsActorRole_businessActor propertyId=\"8013149829678573783\">");
		for ( PersonFullfillsActorRole personFullfillsActorRole_businessActor : getPersonFullfillsActorRole_businessActor() ) {
			sb.append("\n" + personFullfillsActorRole_businessActor.toXmlString());
		}
		sb.append("\n</personFullfillsActorRole_businessActor>");
		sb.append("\n<personInBusinessRole_businessRole propertyId=\"7482106327188733314\">");
		for ( PersonInBusinessRole personInBusinessRole_businessRole : getPersonInBusinessRole_businessRole() ) {
			sb.append("\n" + personInBusinessRole_businessRole.toXmlReferenceString());
		}
		sb.append("\n</personInBusinessRole_businessRole>");
		sb.append("\n</PersonNode>");
		return sb.toString();
	}
	
	public void z_internalAddToAuthenticationToken(String authenticationToken) {
		if ( authenticationToken.equals(getAuthenticationToken()) ) {
			return;
		}
		this.authenticationToken=authenticationToken;
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor businessActor) {
		PersonFullfillsActorRole newOne;
		if ( getBusinessActor().contains(businessActor) ) {
			return;
		}
		newOne = new PersonFullfillsActorRole(this,businessActor);
		this.z_internalAddToPersonFullfillsActorRole_businessActor(newOne);
		newOne.getBusinessActor().z_internalAddToPersonFullfillsActorRole_representedPerson(newOne);
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( businessNetwork.equals(getBusinessNetwork()) ) {
			return;
		}
		this.businessNetwork=businessNetwork;
	}
	
	public void z_internalAddToBusinessRole(IBusinessRole businessRole) {
		PersonInBusinessRole newOne;
		if ( getBusinessRole().contains(businessRole) ) {
			return;
		}
		newOne = new PersonInBusinessRole(this,businessRole);
		this.z_internalAddToPersonInBusinessRole_businessRole(newOne);
		newOne.getBusinessRole().z_internalAddToPersonInBusinessRole_representedPerson(newOne);
	}
	
	public void z_internalAddToEMailAddress(PersonEMailAddressType type, PersonEMailAddress eMailAddress) {
		String key = type.getUid();
		if ( getEMailAddress().contains(eMailAddress) ) {
			return;
		}
		eMailAddress.z_internalAddToType(type);
		this.eMailAddress.put(key.toString(),eMailAddress);
		eMailAddress.setZ_keyOfEMailAddressOnPersonNode(key.toString());
	}
	
	public void z_internalAddToFirstName(String firstName) {
		if ( firstName.equals(getFirstName()) ) {
			return;
		}
		this.firstName=firstName;
	}
	
	public void z_internalAddToLeave(Leave leave) {
		if ( getLeave().contains(leave) ) {
			return;
		}
		this.leave.add(leave);
	}
	
	public void z_internalAddToPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole personFullfillsActorRole_businessActor) {
		if ( getPersonFullfillsActorRole_businessActor().contains(personFullfillsActorRole_businessActor) ) {
			return;
		}
		this.personFullfillsActorRole_businessActor.add(personFullfillsActorRole_businessActor);
	}
	
	public void z_internalAddToPersonInBusinessRole_businessRole(PersonInBusinessRole personInBusinessRole_businessRole) {
		if ( getPersonInBusinessRole_businessRole().contains(personInBusinessRole_businessRole) ) {
			return;
		}
		this.personInBusinessRole_businessRole.add(personInBusinessRole_businessRole);
	}
	
	public void z_internalAddToPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber phoneNumber) {
		String key = type.getUid();
		if ( getPhoneNumber().contains(phoneNumber) ) {
			return;
		}
		phoneNumber.z_internalAddToType(type);
		this.phoneNumber.put(key.toString(),phoneNumber);
		phoneNumber.setZ_keyOfPhoneNumberOnPersonNode(key.toString());
	}
	
	public void z_internalAddToPhysicalAddress(PhysicalAddress physicalAddress) {
		if ( physicalAddress.equals(getPhysicalAddress()) ) {
			return;
		}
		this.physicalAddress=physicalAddress;
	}
	
	public void z_internalAddToPostalAddress(PostalAddress postalAddress) {
		if ( postalAddress.equals(getPostalAddress()) ) {
			return;
		}
		this.postalAddress=postalAddress;
	}
	
	public void z_internalAddToPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		if ( preferredEMailAddressType.equals(getPreferredEMailAddressType()) ) {
			return;
		}
		this.preferredEMailAddressType=preferredEMailAddressType;
	}
	
	public void z_internalAddToPreferredLocale(Locale preferredLocale) {
		this.preferredLocale = preferredLocale==null?null:preferredLocale.toString();
	}
	
	public void z_internalAddToPreferredNotificationType(NotificationType preferredNotificationType) {
		if ( preferredNotificationType.equals(getPreferredNotificationType()) ) {
			return;
		}
		this.preferredNotificationType=preferredNotificationType;
	}
	
	public void z_internalAddToPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		if ( preferredPhoneNumberType.equals(getPreferredPhoneNumberType()) ) {
			return;
		}
		this.preferredPhoneNumberType=preferredPhoneNumberType;
	}
	
	public void z_internalAddToRefreshToken(String refreshToken) {
		if ( refreshToken.equals(getRefreshToken()) ) {
			return;
		}
		this.refreshToken=refreshToken;
	}
	
	public void z_internalAddToSurname(String surname) {
		if ( surname.equals(getSurname()) ) {
			return;
		}
		this.surname=surname;
	}
	
	public void z_internalAddToTokenExpiryDateTime(Date tokenExpiryDateTime) {
		if ( tokenExpiryDateTime.equals(getTokenExpiryDateTime()) ) {
			return;
		}
		this.tokenExpiryDateTime=tokenExpiryDateTime;
	}
	
	public void z_internalAddToUsername(String username) {
		if ( username.equals(getUsername()) ) {
			return;
		}
		this.username=username;
	}
	
	public void z_internalRemoveFromAuthenticationToken(String authenticationToken) {
		if ( getAuthenticationToken()!=null && authenticationToken!=null && authenticationToken.equals(getAuthenticationToken()) ) {
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
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( getBusinessNetwork()!=null && businessNetwork!=null && businessNetwork.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
	}
	
	public void z_internalRemoveFromBusinessRole(IBusinessRole businessRole) {
		for ( PersonInBusinessRole cur : new HashSet<PersonInBusinessRole>(this.personInBusinessRole_businessRole) ) {
			if ( cur.getBusinessRole().equals(businessRole) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromEMailAddress(PersonEMailAddressType type, PersonEMailAddress eMailAddress) {
		String key = type.getUid();
		this.eMailAddress.remove(key.toString());
	}
	
	public void z_internalRemoveFromFirstName(String firstName) {
		if ( getFirstName()!=null && firstName!=null && firstName.equals(getFirstName()) ) {
			this.firstName=null;
			this.firstName=null;
		}
	}
	
	public void z_internalRemoveFromLeave(Leave leave) {
		this.leave.remove(leave);
	}
	
	public void z_internalRemoveFromPersonFullfillsActorRole_businessActor(PersonFullfillsActorRole personFullfillsActorRole_businessActor) {
		this.personFullfillsActorRole_businessActor.remove(personFullfillsActorRole_businessActor);
	}
	
	public void z_internalRemoveFromPersonInBusinessRole_businessRole(PersonInBusinessRole personInBusinessRole_businessRole) {
		this.personInBusinessRole_businessRole.remove(personInBusinessRole_businessRole);
	}
	
	public void z_internalRemoveFromPhoneNumber(PersonPhoneNumberType type, PersonPhoneNumber phoneNumber) {
		String key = type.getUid();
		this.phoneNumber.remove(key.toString());
	}
	
	public void z_internalRemoveFromPhysicalAddress(PhysicalAddress physicalAddress) {
		if ( getPhysicalAddress()!=null && physicalAddress!=null && physicalAddress.equals(getPhysicalAddress()) ) {
			this.physicalAddress=null;
			this.physicalAddress=null;
		}
	}
	
	public void z_internalRemoveFromPostalAddress(PostalAddress postalAddress) {
		if ( getPostalAddress()!=null && postalAddress!=null && postalAddress.equals(getPostalAddress()) ) {
			this.postalAddress=null;
			this.postalAddress=null;
		}
	}
	
	public void z_internalRemoveFromPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		if ( getPreferredEMailAddressType()!=null && preferredEMailAddressType!=null && preferredEMailAddressType.equals(getPreferredEMailAddressType()) ) {
			this.preferredEMailAddressType=null;
			this.preferredEMailAddressType=null;
		}
	}
	
	public void z_internalRemoveFromPreferredLocale(Locale preferredLocale) {
		this.preferredLocale = null;
	}
	
	public void z_internalRemoveFromPreferredNotificationType(NotificationType preferredNotificationType) {
		if ( getPreferredNotificationType()!=null && preferredNotificationType!=null && preferredNotificationType.equals(getPreferredNotificationType()) ) {
			this.preferredNotificationType=null;
			this.preferredNotificationType=null;
		}
	}
	
	public void z_internalRemoveFromPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		if ( getPreferredPhoneNumberType()!=null && preferredPhoneNumberType!=null && preferredPhoneNumberType.equals(getPreferredPhoneNumberType()) ) {
			this.preferredPhoneNumberType=null;
			this.preferredPhoneNumberType=null;
		}
	}
	
	public void z_internalRemoveFromRefreshToken(String refreshToken) {
		if ( getRefreshToken()!=null && refreshToken!=null && refreshToken.equals(getRefreshToken()) ) {
			this.refreshToken=null;
			this.refreshToken=null;
		}
	}
	
	public void z_internalRemoveFromSurname(String surname) {
		if ( getSurname()!=null && surname!=null && surname.equals(getSurname()) ) {
			this.surname=null;
			this.surname=null;
		}
	}
	
	public void z_internalRemoveFromTokenExpiryDateTime(Date tokenExpiryDateTime) {
		if ( getTokenExpiryDateTime()!=null && tokenExpiryDateTime!=null && tokenExpiryDateTime.equals(getTokenExpiryDateTime()) ) {
			this.tokenExpiryDateTime=null;
			this.tokenExpiryDateTime=null;
		}
	}
	
	public void z_internalRemoveFromUsername(String username) {
		if ( getUsername()!=null && username!=null && username.equals(getUsername()) ) {
			this.username=null;
			this.username=null;
		}
	}
	
	/** Implements self.businessNetwork.businessCollaboration->collect(c : IBusinessCollaboration | c.businessActor)
	 */
	private Collection<IBusinessActor> collect1() {
		Collection<IBusinessActor> result = new ArrayList<IBusinessActor>();
		for ( IBusinessCollaboration c : this.getBusinessNetwork().getBusinessCollaboration() ) {
			Set<? extends IBusinessActor> bodyExpResult = c.getBusinessActor();
			result.addAll( bodyExpResult );
		}
		return result;
	}

}