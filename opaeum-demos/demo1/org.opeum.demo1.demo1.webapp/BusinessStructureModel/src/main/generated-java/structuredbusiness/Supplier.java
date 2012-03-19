package structuredbusiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import org.hibernate.annotations.Where;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.OrganizationFullfillsActorRole;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.PersonFullfillsActorRole;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.bpm.request.ParticipationInRequest;
import org.opaeum.runtime.bpm.request.ParticipationInTask;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_-N6PwGK6EeGNuoaMwaBk1w")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="supplier")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Supplier")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Supplier implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessActor, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Supplier> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_fullfills_actor_role_organization_id",nullable=true)
	private OrganizationFullfillsActorRole organizationFullfillsActorRole_organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_-N6PwGK6EeGNuoaMwaBk1w'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,targetEntity=Participation.class)
	@JoinColumn(name="participation_id",nullable=true)
	private Set<Participation> participation = new HashSet<Participation>();
	@Transient
	private AbstractPersistence persistence;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_fullfills_actor_role_represented_person_id",nullable=true)
	private PersonFullfillsActorRole personFullfillsActorRole_representedPerson;
	@Index(columnNames="root_id",name="idx_supplier_root_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="root_id",nullable=true)
	private Structuredbusiness root;
	static final private long serialVersionUID = 6592718997733823438l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Supplier(Structuredbusiness owningObject) {
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
		getRoot().z_internalAddToSupplier((Supplier)this);
	}
	
	public void addToParticipation(Participation participation) {
		if ( participation!=null ) {
			participation.z_internalRemoveFromParticipant(participation.getParticipant());
			participation.z_internalAddToParticipant(this);
			z_internalAddToParticipation(participation);
		}
	}
	
	static public Set<? extends Supplier> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.Supplier.class));
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
		
		}
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public OrganizationFullfillsActorRole createOrganizationFullfillsActorRole_organization() {
		OrganizationFullfillsActorRole newInstance= new OrganizationFullfillsActorRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public PersonFullfillsActorRole createPersonFullfillsActorRole_representedPerson() {
		PersonFullfillsActorRole newInstance= new PersonFullfillsActorRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Supplier ) {
			return other==this || ((Supplier)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		if ( this.getRoot()!=null ) {
			result=this.getRoot();
		}
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
	
	@NumlMetaInfo(uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	public Collection<AbstractRequest> getInitiatedRequests() {
		Collection<AbstractRequest> result = collect11();
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests() {
		Collection<AbstractRequest> result = collect2();
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests() {
		Collection<AbstractRequest> result = collect7();
		
		return result;
	}
	
	public String getName() {
		return "Supplier["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public OrganizationNode getOrganization() {
		OrganizationNode result = null;
		if ( this.organizationFullfillsActorRole_organization!=null ) {
			result = this.organizationFullfillsActorRole_organization.getOrganization();
		}
		return result;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=4147448129438915430,opposite="businessActor",uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw252060@_WjvQ0EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw252060@_WjvQ0EtyEeGElKTCe2jfDw")
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
	
	@NumlMetaInfo(uuid="252060@_NYHP0I6mEeCFsPOcAnk69Q")
	public Collection<TaskRequest> getOwnedTaskRequests() {
		Collection<TaskRequest> result = collect3();
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getRoot();
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=4480510548106225415,opposite="participant",uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	public Set<Participation> getParticipation() {
		Set<Participation> result = this.participation;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_TfLFAJBkEeCWM9wKKqKWag")
	public Collection<ParticipationInRequest> getParticipationsInRequests() {
		Collection<ParticipationInRequest> result = collect9();
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	public Collection<ParticipationInTask> getParticipationsInTasks() {
		Collection<ParticipationInTask> result = collect5();
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=9023075862366939329,opposite="businessActor",uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw252060@_X4-lcEtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw252060@_X4-lcEtyEeGElKTCe2jfDw")
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
	
	public PersonNode getRepresentedPerson() {
		PersonNode result = null;
		if ( this.personFullfillsActorRole_representedPerson!=null ) {
			result = this.personFullfillsActorRole_representedPerson.getRepresentedPerson();
		}
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=7737100568581358598,opposite="supplier",uuid="914890@_-N6PwGK6EeGNuoaMwaBk1w914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	public Structuredbusiness getRoot() {
		Structuredbusiness result = this.root;
		
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
		this.z_internalAddToRoot((Structuredbusiness)owner);
	}
	
	public void markDeleted() {
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromBusinessActor(this);
		}
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromBusinessActor(this);
		}
		if ( getRoot()!=null ) {
			getRoot().z_internalRemoveFromSupplier(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Supplier> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9023075862366939329")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setPersonFullfillsActorRole_representedPerson((PersonFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_organization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4147448129438915430")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setOrganizationFullfillsActorRole_organization((OrganizationFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromParticipation(Set<Participation> participation) {
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
			participation.z_internalRemoveFromParticipant(this);
			z_internalRemoveFromParticipation(participation);
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
			this.getOrganization().z_internalRemoveFromBusinessActor(this);
		}
		if ( organization!=null ) {
			organization.z_internalAddToBusinessActor(this);
			this.z_internalAddToOrganization(organization);
		}
	}
	
	public void setOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization) {
		OrganizationFullfillsActorRole oldValue = this.getOrganizationFullfillsActorRole_organization();
		if ( oldValue==null ) {
			if ( organizationFullfillsActorRole_organization!=null ) {
				Supplier oldOther = (Supplier)organizationFullfillsActorRole_organization.getBusinessActor();
				organizationFullfillsActorRole_organization.z_internalRemoveFromBusinessActor(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganizationFullfillsActorRole_organization(organizationFullfillsActorRole_organization);
				}
				organizationFullfillsActorRole_organization.z_internalAddToBusinessActor((Supplier)this);
			}
			this.z_internalAddToOrganizationFullfillsActorRole_organization(organizationFullfillsActorRole_organization);
		} else {
			if ( !oldValue.equals(organizationFullfillsActorRole_organization) ) {
				oldValue.z_internalRemoveFromBusinessActor(this);
				z_internalRemoveFromOrganizationFullfillsActorRole_organization(oldValue);
				if ( organizationFullfillsActorRole_organization!=null ) {
					Supplier oldOther = (Supplier)organizationFullfillsActorRole_organization.getBusinessActor();
					organizationFullfillsActorRole_organization.z_internalRemoveFromBusinessActor(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganizationFullfillsActorRole_organization(organizationFullfillsActorRole_organization);
					}
					organizationFullfillsActorRole_organization.z_internalAddToBusinessActor((Supplier)this);
				}
				this.z_internalAddToOrganizationFullfillsActorRole_organization(organizationFullfillsActorRole_organization);
			}
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setParticipation(Set<Participation> participation) {
		this.clearParticipation();
		this.addAllToParticipation(participation);
	}
	
	public void setPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson) {
		PersonFullfillsActorRole oldValue = this.getPersonFullfillsActorRole_representedPerson();
		if ( oldValue==null ) {
			if ( personFullfillsActorRole_representedPerson!=null ) {
				Supplier oldOther = (Supplier)personFullfillsActorRole_representedPerson.getBusinessActor();
				personFullfillsActorRole_representedPerson.z_internalRemoveFromBusinessActor(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPersonFullfillsActorRole_representedPerson(personFullfillsActorRole_representedPerson);
				}
				personFullfillsActorRole_representedPerson.z_internalAddToBusinessActor((Supplier)this);
			}
			this.z_internalAddToPersonFullfillsActorRole_representedPerson(personFullfillsActorRole_representedPerson);
		} else {
			if ( !oldValue.equals(personFullfillsActorRole_representedPerson) ) {
				oldValue.z_internalRemoveFromBusinessActor(this);
				z_internalRemoveFromPersonFullfillsActorRole_representedPerson(oldValue);
				if ( personFullfillsActorRole_representedPerson!=null ) {
					Supplier oldOther = (Supplier)personFullfillsActorRole_representedPerson.getBusinessActor();
					personFullfillsActorRole_representedPerson.z_internalRemoveFromBusinessActor(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPersonFullfillsActorRole_representedPerson(personFullfillsActorRole_representedPerson);
					}
					personFullfillsActorRole_representedPerson.z_internalAddToBusinessActor((Supplier)this);
				}
				this.z_internalAddToPersonFullfillsActorRole_representedPerson(personFullfillsActorRole_representedPerson);
			}
		}
	}
	
	public void setRepresentedPerson(IPersonNode p) {
		setRepresentedPerson((PersonNode)p);
	}
	
	public void setRepresentedPerson(PersonNode representedPerson) {
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromBusinessActor(this);
		}
		if ( representedPerson!=null ) {
			representedPerson.z_internalAddToBusinessActor(this);
			this.z_internalAddToRepresentedPerson(representedPerson);
		}
	}
	
	public void setRoot(Structuredbusiness root) {
		if ( this.getRoot()!=null ) {
			this.getRoot().z_internalRemoveFromSupplier(this);
		}
		if ( root!=null ) {
			root.z_internalAddToSupplier(this);
			this.z_internalAddToRoot(root);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
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
		sb.append("classUuid=\"914890@_-N6PwGK6EeGNuoaMwaBk1w\" ");
		sb.append("className=\"structuredbusiness.Supplier\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getPersonFullfillsActorRole_representedPerson()==null ) {
			sb.append("\n<personFullfillsActorRole_representedPerson/>");
		} else {
			sb.append("\n<personFullfillsActorRole_representedPerson propertyId=\"9023075862366939329\">");
			sb.append("\n" + getPersonFullfillsActorRole_representedPerson().toXmlReferenceString());
			sb.append("\n</personFullfillsActorRole_representedPerson>");
		}
		if ( getOrganizationFullfillsActorRole_organization()==null ) {
			sb.append("\n<organizationFullfillsActorRole_organization/>");
		} else {
			sb.append("\n<organizationFullfillsActorRole_organization propertyId=\"4147448129438915430\">");
			sb.append("\n" + getOrganizationFullfillsActorRole_organization().toXmlReferenceString());
			sb.append("\n</organizationFullfillsActorRole_organization>");
		}
		sb.append("\n</Supplier>");
		return sb.toString();
	}
	
	public void z_internalAddToOrganization(OrganizationNode organization) {
		OrganizationFullfillsActorRole newOne = new OrganizationFullfillsActorRole(this,organization);
		this.z_internalAddToOrganizationFullfillsActorRole_organization(newOne);
		newOne.getOrganization().z_internalAddToOrganizationFullfillsActorRole_businessActor(newOne);
	}
	
	public void z_internalAddToOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val) {
		this.organizationFullfillsActorRole_organization=val;
	}
	
	public void z_internalAddToParticipation(Participation val) {
		this.participation.add(val);
	}
	
	public void z_internalAddToPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val) {
		this.personFullfillsActorRole_representedPerson=val;
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode representedPerson) {
		PersonFullfillsActorRole newOne = new PersonFullfillsActorRole(this,representedPerson);
		this.z_internalAddToPersonFullfillsActorRole_representedPerson(newOne);
		newOne.getRepresentedPerson().z_internalAddToPersonFullfillsActorRole_businessActor(newOne);
	}
	
	public void z_internalAddToRoot(Structuredbusiness val) {
		this.root=val;
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode organization) {
		if ( this.organizationFullfillsActorRole_organization!=null ) {
			this.organizationFullfillsActorRole_organization.clear();
		}
	}
	
	public void z_internalRemoveFromOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val) {
		if ( getOrganizationFullfillsActorRole_organization()!=null && val!=null && val.equals(getOrganizationFullfillsActorRole_organization()) ) {
			this.organizationFullfillsActorRole_organization=null;
			this.organizationFullfillsActorRole_organization=null;
		}
	}
	
	public void z_internalRemoveFromParticipation(Participation val) {
		this.participation.remove(val);
	}
	
	public void z_internalRemoveFromPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val) {
		if ( getPersonFullfillsActorRole_representedPerson()!=null && val!=null && val.equals(getPersonFullfillsActorRole_representedPerson()) ) {
			this.personFullfillsActorRole_representedPerson=null;
			this.personFullfillsActorRole_representedPerson=null;
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode representedPerson) {
		if ( this.personFullfillsActorRole_representedPerson!=null ) {
			this.personFullfillsActorRole_representedPerson.clear();
		}
	}
	
	public void z_internalRemoveFromRoot(Structuredbusiness val) {
		if ( getRoot()!=null && val!=null && val.equals(getRoot()) ) {
			this.root=null;
			this.root=null;
		}
	}
	
	/** Implements ->collect( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.request )
	 */
	private Collection<AbstractRequest> collect11() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : select10() ) {
			AbstractRequest bodyExpResult = i_ParticipationInRequest.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.request )
	 */
	private Collection<AbstractRequest> collect2() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : select1() ) {
			AbstractRequest bodyExpResult = i_ParticipationInRequest.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInTask : ParticipationInTask | i_ParticipationInTask.taskRequest )
	 */
	private Collection<TaskRequest> collect3() {
		Collection<TaskRequest> result = new ArrayList<TaskRequest>();
		for ( ParticipationInTask i_ParticipationInTask : this.getParticipationsInTasks() ) {
			TaskRequest bodyExpResult = i_ParticipationInTask.getTaskRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_Participation : Participation | i_Participation.oclAsType(ParticipationInTask) )
	 */
	private Collection<ParticipationInTask> collect5() {
		Collection<ParticipationInTask> result = new ArrayList<ParticipationInTask>();
		for ( Participation i_Participation : select4() ) {
			ParticipationInTask bodyExpResult = ((ParticipationInTask) i_Participation);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.request )
	 */
	private Collection<AbstractRequest> collect7() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : select6() ) {
			AbstractRequest bodyExpResult = i_ParticipationInRequest.getRequest();
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( i_Participation : Participation | i_Participation.oclAsType(ParticipationInRequest) )
	 */
	private Collection<ParticipationInRequest> collect9() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( Participation i_Participation : select8() ) {
			ParticipationInRequest bodyExpResult = ((ParticipationInRequest) i_Participation);
			if ( bodyExpResult != null ) result.add( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->select( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.kind = RequestParticipationKind::stakeholder )
	 */
	private Collection<ParticipationInRequest> select1() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : this.getParticipationsInRequests() ) {
			if ( (i_ParticipationInRequest.getKind().equals( RequestParticipationKind.STAKEHOLDER)) ) {
				result.add( i_ParticipationInRequest );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.kind = RequestParticipationKind::initiator )
	 */
	private Collection<ParticipationInRequest> select10() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : this.getParticipationsInRequests() ) {
			if ( (i_ParticipationInRequest.getKind().equals( RequestParticipationKind.INITIATOR)) ) {
				result.add( i_ParticipationInRequest );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_Participation : Participation | i_Participation.oclIsKindOf(ParticipationInTask) )
	 */
	private Set<Participation> select4() {
		Set<Participation> result = new HashSet<Participation>();
		for ( Participation i_Participation : this.getParticipation() ) {
			if ( (i_Participation instanceof ParticipationInTask) ) {
				result.add( i_Participation );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_ParticipationInRequest : ParticipationInRequest | i_ParticipationInRequest.kind = RequestParticipationKind::businessOwner )
	 */
	private Collection<ParticipationInRequest> select6() {
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>();
		for ( ParticipationInRequest i_ParticipationInRequest : this.getParticipationsInRequests() ) {
			if ( (i_ParticipationInRequest.getKind().equals( RequestParticipationKind.BUSINESSOWNER)) ) {
				result.add( i_ParticipationInRequest );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_Participation : Participation | i_Participation.oclIsKindOf(ParticipationInRequest) )
	 */
	private Set<Participation> select8() {
		Set<Participation> result = new HashSet<Participation>();
		for ( Participation i_Participation : this.getParticipation() ) {
			if ( (i_Participation instanceof ParticipationInRequest) ) {
				result.add( i_Participation );
			}
		}
		return result;
	}

}