package bpmmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.hibernate.annotations.Where;
import org.opaeum.annotation.BusinessActor;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.IParticipant;
import org.opaeum.runtime.bpm.organization.OrganizationFullfillsActorRole;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonFullfillsActorRole;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.bpm.request.ParticipationInRequest;
import org.opaeum.runtime.bpm.request.ParticipationInTask;
import org.opaeum.runtime.bpm.request.ParticipationParticipant;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
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
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bpmmodel.util.Stdlib;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_F-ntwI_cEeKWQLWOG6WHmA")
@BusinessActor
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="supplier")
@Entity(name="Supplier")
public class Supplier implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessActor, Serializable {
	@Index(columnNames="business_collaboration1_id",name="idx_supplier_business_collaboration1_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_collaboration1_id",nullable=true)
	protected BusinessCollaboration1 businessCollaboration1;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="supplier",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends Supplier> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_fullfills_actor_role_organization_id",nullable=true)
	protected OrganizationFullfillsActorRole organizationFullfillsActorRole_organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='bpm.uml@_F-ntwI_cEeKWQLWOG6WHmA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participant",nullable=true)
	protected Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private InternalHibernatePersistence persistence;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_fullfills_actor_role_represented_person_id",nullable=true)
	protected PersonFullfillsActorRole personFullfillsActorRole_representedPerson;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="preferred_e_mail_address_type",nullable=true)
	protected PersonEMailAddressType preferredEMailAddressType;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="preferred_notification_type",nullable=true)
	protected NotificationType preferredNotificationType;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="preferred_phone_number_type",nullable=true)
	protected PersonPhoneNumberType preferredPhoneNumberType;
	static final private long serialVersionUID = 2754817960601129143l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Supplier(BusinessCollaboration1 owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Supplier
	 */
	public Supplier() {
	}

	public void addAllToParticipation(Set<Participation> participation) {
		for ( Participation o : participation ) {
			addToParticipation(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessCollaboration1().z_internalAddToSupplier((Supplier)this);
	}
	
	public void addToParticipation(Participation participation) {
		if ( participation!=null && !this.getParticipation().contains(participation) ) {
			ParticipationParticipant newLink = new ParticipationParticipant((IParticipant)this,(Participation)participation);
			if ( participation.getParticipant()!=null ) {
				participation.getParticipant().removeFromParticipation(participation);
			}
			this.z_internalAddToParticipationParticipant_participation(newLink);
			participation.z_internalAddToParticipationParticipant_participant(newLink);
		}
	}
	
	static public Set<? extends Supplier> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.Supplier.class));
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
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationParticipant_participation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5579540379306504838")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ParticipationParticipant curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToParticipationParticipant_participation(curVal);
						curVal.z_internalAddToParticipant(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearParticipation() {
		Set<Participation> tmp = new HashSet<Participation>(getParticipation());
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Supplier ) {
			return other==this || ((Supplier)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=124417441767574741l,opposite="businessActor",uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8960782117207473359l,opposite="supplier",uuid="bpm.uml@_nJG8sY_cEeKWQLWOG6WHmA")
	@NumlMetaInfo(uuid="bpm.uml@_nJG8sY_cEeKWQLWOG6WHmA")
	public BusinessCollaboration1 getBusinessCollaboration1() {
		BusinessCollaboration1 result = this.businessCollaboration1;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6185666218388591493l,uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	public Collection<AbstractRequest> getInitiatedRequests() {
		Collection result = new ArrayList<AbstractRequest>(collect11());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5635486542671558270l,uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests() {
		Collection result = new ArrayList<AbstractRequest>(collect2());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5447021495172291044l,uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests() {
		Collection result = new ArrayList<AbstractRequest>(collect9());
		
		return result;
	}
	
	public String getName() {
		return "Supplier["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8203889236759279938l,opposite="businessActor",uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw")
	public OrganizationNode getOrganization() {
		OrganizationNode result = null;
		if ( this.organizationFullfillsActorRole_organization!=null ) {
			result = this.organizationFullfillsActorRole_organization.getOrganization();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1418802680892094436l,opposite="businessActor",uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_WjvQ0EtyEeGElKTCe2jfDw")
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_organization() {
		OrganizationFullfillsActorRole result = this.organizationFullfillsActorRole_organization;
		
		return result;
	}
	
	public OrganizationFullfillsActorRole getOrganizationFullfillsActorRole_organizationFor(OrganizationNode match) {
		if ( organizationFullfillsActorRole_organization.getOrganization().equals(match) ) {
			return organizationFullfillsActorRole_organization;
		} else {
			return null;
		}
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6404162095298970578l,uuid="252060@_NYHP0I6mEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_NYHP0I6mEeCFsPOcAnk69Q")
	public Collection<TaskRequest> getOwnedTaskRequests() {
		Collection result = new ArrayList<TaskRequest>(collect5());
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessCollaboration1();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4480510548106225415l,opposite="participant",uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	public Set<Participation> getParticipation() {
		Set result = new HashSet<Participation>();
		for ( ParticipationParticipant cur : this.getParticipationParticipant_participation() ) {
			result.add(cur.getParticipation());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5579540379306504838l,opposite="participant",uuid="252060@_3YyGkIoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_YgstsI29EeCrtavWRHwoHg@252060@_3YyGkIoXEeCPduia_-NbFw")
	public Set<ParticipationParticipant> getParticipationParticipant_participation() {
		Set result = this.participationParticipant_participation;
		
		return result;
	}
	
	public ParticipationParticipant getParticipationParticipant_participationFor(Participation match) {
		for ( ParticipationParticipant var : getParticipationParticipant_participation() ) {
			if ( var.getParticipation().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2234431193389771664l,uuid="252060@_TfLFAJBkEeCWM9wKKqKWag")
	@NumlMetaInfo(uuid="252060@_TfLFAJBkEeCWM9wKKqKWag")
	public Collection<ParticipationInRequest> getParticipationsInRequests() {
		Collection result = new ArrayList<ParticipationInRequest>(collect7());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6858863738991536174l,uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	@NumlMetaInfo(uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	public Collection<ParticipationInTask> getParticipationsInTasks() {
		Collection result = new ArrayList<ParticipationInTask>(collect4());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5948223451457234128l,opposite="businessActor",uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_X4-lcEtyEeGElKTCe2jfDw")
	public PersonFullfillsActorRole getPersonFullfillsActorRole_representedPerson() {
		PersonFullfillsActorRole result = this.personFullfillsActorRole_representedPerson;
		
		return result;
	}
	
	public PersonFullfillsActorRole getPersonFullfillsActorRole_representedPersonFor(PersonNode match) {
		if ( personFullfillsActorRole_representedPerson.getRepresentedPerson().equals(match) ) {
			return personFullfillsActorRole_representedPerson;
		} else {
			return null;
		}
	}
	
	@NumlMetaInfo(uuid="252060@_6Co6gO0lEeGN-aZ7URyUbw")
	public PersonNode getPersonNode() {
		PersonNode result = this.getRepresentedPerson();
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9151265436220545056l,opposite="businessActor",uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw")
	public PersonNode getRepresentedPerson() {
		PersonNode result = null;
		if ( this.personFullfillsActorRole_representedPerson!=null ) {
			result = this.personFullfillsActorRole_representedPerson.getRepresentedPerson();
		}
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
		this.z_internalAddToBusinessCollaboration1((BusinessCollaboration1)owner);
		this.setPreferredEMailAddressType( PersonEMailAddressType.WORK );
		this.setPreferredPhoneNumberType( PersonPhoneNumberType.CELL );
		this.setPreferredNotificationType( NotificationType.EMAIL );
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( ParticipationParticipant link : new HashSet<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
			link.getParticipation().z_internalRemoveFromParticipationParticipant_participant(link);
		}
		if ( getOrganization()!=null ) {
			OrganizationFullfillsActorRole link = getOrganizationFullfillsActorRole_organization();
			link.getOrganization().z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(link);
			link.markDeleted();
		}
		if ( getRepresentedPerson()!=null ) {
			PersonFullfillsActorRole link = getPersonFullfillsActorRole_representedPerson();
			link.getRepresentedPerson().z_internalRemoveFromPersonFullfillsActorRole_businessActor(link);
			link.markDeleted();
		}
		if ( getBusinessCollaboration1()!=null ) {
			getBusinessCollaboration1().z_internalRemoveFromSupplier(this);
		}
		for ( ParticipationParticipant child : new ArrayList<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_organization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1418802680892094436")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationFullfillsActorRole organizationFullfillsActorRole_organization = (OrganizationFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( organizationFullfillsActorRole_organization!=null ) {
							z_internalAddToOrganizationFullfillsActorRole_organization(organizationFullfillsActorRole_organization);
							organizationFullfillsActorRole_organization.z_internalAddToBusinessActor(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5948223451457234128")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonFullfillsActorRole personFullfillsActorRole_representedPerson = (PersonFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( personFullfillsActorRole_representedPerson!=null ) {
							z_internalAddToPersonFullfillsActorRole_representedPerson(personFullfillsActorRole_representedPerson);
							personFullfillsActorRole_representedPerson.z_internalAddToBusinessActor(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("participationParticipant_participation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5579540379306504838")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ParticipationParticipant)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromParticipation(Set<? extends Participation> participation) {
		Set<Participation> tmp = new HashSet<Participation>(participation);
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipation(Participation participation) {
		if ( participation!=null ) {
			ParticipationParticipant oldLink = getParticipationParticipant_participationFor(participation);
			if ( oldLink!=null ) {
				participation.z_internalRemoveFromParticipationParticipant_participant(oldLink);
				oldLink.clear();
				z_internalRemoveFromParticipationParticipant_participation(oldLink);
			}
		}
	}
	
	public void setBusinessCollaboration1(BusinessCollaboration1 businessCollaboration1) {
		if ( this.getBusinessCollaboration1()!=null ) {
			this.getBusinessCollaboration1().z_internalRemoveFromSupplier(this);
		}
		if ( businessCollaboration1 == null ) {
			this.z_internalRemoveFromBusinessCollaboration1(this.getBusinessCollaboration1());
		} else {
			this.z_internalAddToBusinessCollaboration1(businessCollaboration1);
		}
		if ( businessCollaboration1!=null ) {
			businessCollaboration1.z_internalAddToSupplier(this);
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
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganization(IOrganizationNode org) {
		setOrganization((OrganizationNode)org);
	}
	
	public void setOrganization(OrganizationNode organization) {
		if ( this.getOrganization()!=null ) {
			this.getOrganization().z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(getOrganizationFullfillsActorRole_organization());
		}
		if ( organization == null ) {
			this.z_internalRemoveFromOrganizationFullfillsActorRole_organization(this.getOrganizationFullfillsActorRole_organization());
		} else {
			OrganizationFullfillsActorRole organizationFullfillsActorRole_organization = new OrganizationFullfillsActorRole((IBusinessActor)this,(OrganizationNode)organization);
			this.z_internalAddToOrganizationFullfillsActorRole_organization(organizationFullfillsActorRole_organization);
			organization.z_internalAddToOrganizationFullfillsActorRole_businessActor(organizationFullfillsActorRole_organization);
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipation(Set<Participation> participation) {
		this.clearParticipation();
		this.addAllToParticipation(participation);
	}
	
	public void setPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		if ( preferredEMailAddressType == null ) {
			this.z_internalRemoveFromPreferredEMailAddressType(getPreferredEMailAddressType());
		} else {
			this.z_internalAddToPreferredEMailAddressType(preferredEMailAddressType);
		}
	}
	
	public void setPreferredNotificationType(NotificationType preferredNotificationType) {
		if ( preferredNotificationType == null ) {
			this.z_internalRemoveFromPreferredNotificationType(getPreferredNotificationType());
		} else {
			this.z_internalAddToPreferredNotificationType(preferredNotificationType);
		}
	}
	
	public void setPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		if ( preferredPhoneNumberType == null ) {
			this.z_internalRemoveFromPreferredPhoneNumberType(getPreferredPhoneNumberType());
		} else {
			this.z_internalAddToPreferredPhoneNumberType(preferredPhoneNumberType);
		}
	}
	
	public void setRepresentedPerson(IPersonNode p) {
		setRepresentedPerson((PersonNode)p);
	}
	
	public void setRepresentedPerson(PersonNode representedPerson) {
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromPersonFullfillsActorRole_businessActor(getPersonFullfillsActorRole_representedPerson());
		}
		if ( representedPerson == null ) {
			this.z_internalRemoveFromPersonFullfillsActorRole_representedPerson(this.getPersonFullfillsActorRole_representedPerson());
		} else {
			PersonFullfillsActorRole personFullfillsActorRole_representedPerson = new PersonFullfillsActorRole((IBusinessActor)this,(PersonNode)representedPerson);
			this.z_internalAddToPersonFullfillsActorRole_representedPerson(personFullfillsActorRole_representedPerson);
			representedPerson.z_internalAddToPersonFullfillsActorRole_businessActor(personFullfillsActorRole_representedPerson);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Supplier uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Supplier ");
		sb.append("classUuid=\"bpm.uml@_F-ntwI_cEeKWQLWOG6WHmA\" ");
		sb.append("className=\"bpmmodel.Supplier\" ");
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
		sb.append(">");
		if ( getOrganizationFullfillsActorRole_organization()==null ) {
			sb.append("\n<organizationFullfillsActorRole_organization/>");
		} else {
			sb.append("\n<organizationFullfillsActorRole_organization propertyId=\"1418802680892094436\">");
			sb.append("\n" + getOrganizationFullfillsActorRole_organization().toXmlReferenceString());
			sb.append("\n</organizationFullfillsActorRole_organization>");
		}
		if ( getPersonFullfillsActorRole_representedPerson()==null ) {
			sb.append("\n<personFullfillsActorRole_representedPerson/>");
		} else {
			sb.append("\n<personFullfillsActorRole_representedPerson propertyId=\"5948223451457234128\">");
			sb.append("\n" + getPersonFullfillsActorRole_representedPerson().toXmlReferenceString());
			sb.append("\n</personFullfillsActorRole_representedPerson>");
		}
		sb.append("\n<participationParticipant_participation propertyId=\"5579540379306504838\">");
		for ( ParticipationParticipant participationParticipant_participation : getParticipationParticipant_participation() ) {
			sb.append("\n" + participationParticipant_participation.toXmlString());
		}
		sb.append("\n</participationParticipant_participation>");
		sb.append("\n</Supplier>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCollaboration1(BusinessCollaboration1 businessCollaboration1) {
		if ( businessCollaboration1.equals(this.businessCollaboration1) ) {
			return;
		}
		this.businessCollaboration1=businessCollaboration1;
	}
	
	public void z_internalAddToOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization) {
		if ( organizationFullfillsActorRole_organization.equals(getOrganizationFullfillsActorRole_organization()) ) {
			return;
		}
		this.organizationFullfillsActorRole_organization=organizationFullfillsActorRole_organization;
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		if ( getParticipationParticipant_participation().contains(participationParticipant_participation) ) {
			return;
		}
		this.participationParticipant_participation.add(participationParticipant_participation);
	}
	
	public void z_internalAddToPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson) {
		if ( personFullfillsActorRole_representedPerson.equals(getPersonFullfillsActorRole_representedPerson()) ) {
			return;
		}
		this.personFullfillsActorRole_representedPerson=personFullfillsActorRole_representedPerson;
	}
	
	public void z_internalAddToPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		if ( preferredEMailAddressType.equals(this.preferredEMailAddressType) ) {
			return;
		}
		this.preferredEMailAddressType=preferredEMailAddressType;
	}
	
	public void z_internalAddToPreferredNotificationType(NotificationType preferredNotificationType) {
		if ( preferredNotificationType.equals(this.preferredNotificationType) ) {
			return;
		}
		this.preferredNotificationType=preferredNotificationType;
	}
	
	public void z_internalAddToPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		if ( preferredPhoneNumberType.equals(this.preferredPhoneNumberType) ) {
			return;
		}
		this.preferredPhoneNumberType=preferredPhoneNumberType;
	}
	
	public void z_internalRemoveFromBusinessCollaboration1(BusinessCollaboration1 businessCollaboration1) {
		if ( getBusinessCollaboration1()!=null && businessCollaboration1!=null && businessCollaboration1.equals(getBusinessCollaboration1()) ) {
			this.businessCollaboration1=null;
			this.businessCollaboration1=null;
		}
	}
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization) {
		if ( getOrganizationFullfillsActorRole_organization()!=null && organizationFullfillsActorRole_organization!=null && organizationFullfillsActorRole_organization.equals(getOrganizationFullfillsActorRole_organization()) ) {
			this.organizationFullfillsActorRole_organization=null;
			this.organizationFullfillsActorRole_organization=null;
		}
	}
	
	public void z_internalRemoveFromParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		this.participationParticipant_participation.remove(participationParticipant_participation);
	}
	
	public void z_internalRemoveFromPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson) {
		if ( getPersonFullfillsActorRole_representedPerson()!=null && personFullfillsActorRole_representedPerson!=null && personFullfillsActorRole_representedPerson.equals(getPersonFullfillsActorRole_representedPerson()) ) {
			this.personFullfillsActorRole_representedPerson=null;
			this.personFullfillsActorRole_representedPerson=null;
		}
	}
	
	public void z_internalRemoveFromPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		if ( getPreferredEMailAddressType()!=null && preferredEMailAddressType!=null && preferredEMailAddressType.equals(getPreferredEMailAddressType()) ) {
			this.preferredEMailAddressType=null;
			this.preferredEMailAddressType=null;
		}
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
	
	/** Implements self.participationsInRequests->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))->collect(temp2 : ParticipationInRequest | temp2.request)
	 */
	private Collection<AbstractRequest> collect11() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest temp2 : select10() ) {
			AbstractRequest bodyExpResult = temp2.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationsInRequests->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::stakeholder))->collect(temp2 : ParticipationInRequest | temp2.request)
	 */
	private Collection<AbstractRequest> collect2() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest temp2 : select1() ) {
			AbstractRequest bodyExpResult = temp2.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participation->select(temp1 : Participation | temp1.oclIsKindOf(OpaeumLibraryForBPM::request::ParticipationInTask))->collect(temp2 : Participation | temp2.oclAsType(OpaeumLibraryForBPM::request::ParticipationInTask))
	 */
	private Collection<ParticipationInTask> collect4() {
		Collection<ParticipationInTask> result = new ArrayList<ParticipationInTask>();
		for ( Participation temp2 : select3() ) {
			ParticipationInTask bodyExpResult = ((ParticipationInTask) temp2);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationsInTasks->collect(temp1 : ParticipationInTask | temp1.taskRequest)
	 */
	private Collection<TaskRequest> collect5() {
		Collection<TaskRequest> result = new ArrayList<TaskRequest>();
		for ( ParticipationInTask temp1 : this.getParticipationsInTasks() ) {
			TaskRequest bodyExpResult = temp1.getTaskRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participation->select(temp1 : Participation | temp1.oclIsKindOf(OpaeumLibraryForBPM::request::ParticipationInRequest))->collect(temp2 : Participation | temp2.oclAsType(OpaeumLibraryForBPM::request::ParticipationInRequest))
	 */
	private Collection<ParticipationInRequest> collect7() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( Participation temp2 : select6() ) {
			ParticipationInRequest bodyExpResult = ((ParticipationInRequest) temp2);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationsInRequests->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::businessOwner))->collect(temp2 : ParticipationInRequest | temp2.request)
	 */
	private Collection<AbstractRequest> collect9() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest temp2 : select8() ) {
			AbstractRequest bodyExpResult = temp2.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.participationsInRequests->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::stakeholder))
	 */
	private Collection<ParticipationInRequest> select1() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest temp1 : this.getParticipationsInRequests() ) {
			if ( (temp1.getKind().equals( RequestParticipationKind.STAKEHOLDER)) ) {
				result.add( temp1 );
			}
		}
		return result;
	}
	
	/** Implements self.participationsInRequests->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::initiator))
	 */
	private Collection<ParticipationInRequest> select10() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest temp1 : this.getParticipationsInRequests() ) {
			if ( (temp1.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				result.add( temp1 );
			}
		}
		return result;
	}
	
	/** Implements self.participation->select(temp1 : Participation | temp1.oclIsKindOf(OpaeumLibraryForBPM::request::ParticipationInTask))
	 */
	private Set<Participation> select3() {
		Set<Participation> result = new HashSet<Participation>();
		for ( Participation temp1 : this.getParticipation() ) {
			if ( (temp1 instanceof ParticipationInTask) ) {
				result.add( temp1 );
			}
		}
		return result;
	}
	
	/** Implements self.participation->select(temp1 : Participation | temp1.oclIsKindOf(OpaeumLibraryForBPM::request::ParticipationInRequest))
	 */
	private Set<Participation> select6() {
		Set<Participation> result = new HashSet<Participation>();
		for ( Participation temp1 : this.getParticipation() ) {
			if ( (temp1 instanceof ParticipationInRequest) ) {
				result.add( temp1 );
			}
		}
		return result;
	}
	
	/** Implements self.participationsInRequests->select(temp1 : ParticipationInRequest | temp1.kind.=(OpaeumLibraryForBPM::request::RequestParticipationKind::businessOwner))
	 */
	private Collection<ParticipationInRequest> select8() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest temp1 : this.getParticipationsInRequests() ) {
			if ( (temp1.getKind().equals( RequestParticipationKind.BUSINESSOWNER)) ) {
				result.add( temp1 );
			}
		}
		return result;
	}

}