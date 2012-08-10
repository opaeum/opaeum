package structuredbusiness;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.organization.IBusinessActor;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessActor_1;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
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
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="i_business_collaboration_i_business_actor_1_business_collaboration_id",nullable=true)
	private IBusinessCollaboration_iBusinessActor_1 iBusinessCollaboration_iBusinessActor_1_businessCollaboration;
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
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participation_participant_participation_id",nullable=true)
	private Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private AbstractPersistence persistence;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_fullfills_actor_role_represented_person_id",nullable=true)
	private PersonFullfillsActorRole personFullfillsActorRole_representedPerson;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6592718997733823438l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Supplier(IBusinessCollaboration owningObject) {
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
	
	public void addAllToParticipationParticipant_participation(Set<ParticipationParticipant> participationParticipant_participation) {
		for ( ParticipationParticipant o : participationParticipant_participation ) {
			addToParticipationParticipant_participation(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToParticipation(Participation participation) {
		if ( participation!=null ) {
			participation.z_internalRemoveFromParticipant(participation.getParticipant());
			z_internalAddToParticipation(participation);
		}
	}
	
	public void addToParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		if ( participationParticipant_participation!=null ) {
			participationParticipant_participation.z_internalRemoveFromParticipant(participationParticipant_participation.getParticipant());
			participationParticipant_participation.z_internalAddToParticipant(this);
			z_internalAddToParticipationParticipant_participation(participationParticipant_participation);
		}
	}
	
	static public Set<? extends Supplier> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(structuredbusiness.Supplier.class));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToParticipationParticipant_participation(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void clearParticipationParticipant_participation() {
		removeAllFromParticipationParticipant_participation(getParticipationParticipant_participation());
	}
	
	public IBusinessCollaboration_iBusinessActor_1 createIBusinessCollaboration_iBusinessActor_1_businessCollaboration() {
		IBusinessCollaboration_iBusinessActor_1 newInstance= new IBusinessCollaboration_iBusinessActor_1();
		newInstance.init(this);
		return newInstance;
	}
	
	public OrganizationFullfillsActorRole createOrganizationFullfillsActorRole_organization() {
		OrganizationFullfillsActorRole newInstance= new OrganizationFullfillsActorRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public ParticipationParticipant createParticipationParticipant_participation() {
		ParticipationParticipant newInstance= new ParticipationParticipant();
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=124417441767574741l,opposite="businessActor",uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		if ( this.iBusinessCollaboration_iBusinessActor_1_businessCollaboration!=null ) {
			result = this.iBusinessCollaboration_iBusinessActor_1_businessCollaboration.getBusinessCollaboration();
		}
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6660669114099610761l,opposite="businessActor",uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_AN3QcEtxEeGElKTCe2jfDw@252060@_pP5QQFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration_iBusinessActor_1 getIBusinessCollaboration_iBusinessActor_1_businessCollaboration() {
		IBusinessCollaboration_iBusinessActor_1 result = this.iBusinessCollaboration_iBusinessActor_1_businessCollaboration;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6185666218388591493l,uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	@NumlMetaInfo(uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	public Collection<AbstractRequest> getInitiatedRequests() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>(collect11());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5635486542671558270l,uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>(collect2());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5447021495172291044l,uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>(collect9());
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1418802680892094436l,opposite="businessActor",uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
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
		Collection<TaskRequest> result = new ArrayList<TaskRequest>(collect5());
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessCollaboration();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4480510548106225415l,opposite="participant",uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
	public Set<Participation> getParticipation() {
		Set<Participation> result = new HashSet<Participation>();
		for ( ParticipationParticipant cur : this.getParticipationParticipant_participation() ) {
			result.add(cur.getParticipation());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5579540379306504838l,opposite="participant",uuid="252060@_3YyGkIoXEeCPduia_-NbFw")
	@NumlMetaInfo(uuid="252060@_YgstsI29EeCrtavWRHwoHg@252060@_3YyGkIoXEeCPduia_-NbFw")
	public Set<ParticipationParticipant> getParticipationParticipant_participation() {
		Set<ParticipationParticipant> result = this.participationParticipant_participation;
		
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
		Collection<ParticipationInRequest> result = new ArrayList<ParticipationInRequest>(collect7());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6858863738991536174l,uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	@NumlMetaInfo(uuid="252060@_DIGv8JBkEeCWM9wKKqKWag")
	public Collection<ParticipationInTask> getParticipationsInTasks() {
		Collection<ParticipationInTask> result = new ArrayList<ParticipationInTask>(collect4());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5948223451457234128l,opposite="businessActor",uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
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
	}
	
	public void markDeleted() {
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromBusinessActor(this);
		}
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromBusinessActor(this);
		}
		if ( getOrganizationFullfillsActorRole_organization()!=null ) {
			getOrganizationFullfillsActorRole_organization().markDeleted();
		}
		if ( getPersonFullfillsActorRole_representedPerson()!=null ) {
			getPersonFullfillsActorRole_representedPerson().markDeleted();
		}
		if ( getIBusinessCollaboration_iBusinessActor_1_businessCollaboration()!=null ) {
			getIBusinessCollaboration_iBusinessActor_1_businessCollaboration().markDeleted();
		}
		for ( ParticipationParticipant child : new ArrayList<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
			child.markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationFullfillsActorRole_organization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1418802680892094436")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setOrganizationFullfillsActorRole_organization((OrganizationFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personFullfillsActorRole_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5948223451457234128")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setPersonFullfillsActorRole_representedPerson((PersonFullfillsActorRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void removeAllFromParticipation(Set<Participation> participation) {
		Set<Participation> tmp = new HashSet<Participation>(participation);
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void removeAllFromParticipationParticipant_participation(Set<ParticipationParticipant> participationParticipant_participation) {
		Set<ParticipationParticipant> tmp = new HashSet<ParticipationParticipant>(participationParticipant_participation);
		for ( ParticipationParticipant o : tmp ) {
			removeFromParticipationParticipant_participation(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipation(Participation participation) {
		if ( participation!=null ) {
			z_internalRemoveFromParticipation(participation);
		}
	}
	
	public void removeFromParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		if ( participationParticipant_participation!=null ) {
			participationParticipant_participation.z_internalRemoveFromParticipant(this);
			z_internalRemoveFromParticipationParticipant_participation(participationParticipant_participation);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setIBusinessCollaboration_iBusinessActor_1_businessCollaboration(IBusinessCollaboration_iBusinessActor_1 iBusinessCollaboration_iBusinessActor_1_businessCollaboration) {
		IBusinessCollaboration_iBusinessActor_1 oldValue = this.getIBusinessCollaboration_iBusinessActor_1_businessCollaboration();
		propertyChangeSupport.firePropertyChange("iBusinessCollaboration_iBusinessActor_1_businessCollaboration",getIBusinessCollaboration_iBusinessActor_1_businessCollaboration(),iBusinessCollaboration_iBusinessActor_1_businessCollaboration);
		if ( oldValue==null ) {
			if ( iBusinessCollaboration_iBusinessActor_1_businessCollaboration!=null ) {
				Supplier oldOther = (Supplier)iBusinessCollaboration_iBusinessActor_1_businessCollaboration.getBusinessActor();
				iBusinessCollaboration_iBusinessActor_1_businessCollaboration.z_internalRemoveFromBusinessActor(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(iBusinessCollaboration_iBusinessActor_1_businessCollaboration);
				}
				iBusinessCollaboration_iBusinessActor_1_businessCollaboration.z_internalAddToBusinessActor((Supplier)this);
			}
			this.z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessCollaboration(iBusinessCollaboration_iBusinessActor_1_businessCollaboration);
		} else {
			if ( !oldValue.equals(iBusinessCollaboration_iBusinessActor_1_businessCollaboration) ) {
				oldValue.z_internalRemoveFromBusinessActor(this);
				z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(oldValue);
				if ( iBusinessCollaboration_iBusinessActor_1_businessCollaboration!=null ) {
					Supplier oldOther = (Supplier)iBusinessCollaboration_iBusinessActor_1_businessCollaboration.getBusinessActor();
					iBusinessCollaboration_iBusinessActor_1_businessCollaboration.z_internalRemoveFromBusinessActor(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(iBusinessCollaboration_iBusinessActor_1_businessCollaboration);
					}
					iBusinessCollaboration_iBusinessActor_1_businessCollaboration.z_internalAddToBusinessActor((Supplier)this);
				}
				this.z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessCollaboration(iBusinessCollaboration_iBusinessActor_1_businessCollaboration);
			}
		}
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
		propertyChangeSupport.firePropertyChange("organization",getOrganization(),organization);
		if ( this.getOrganization()!=null ) {
			this.getOrganization().z_internalRemoveFromBusinessActor(this);
		}
		if ( organization!=null ) {
			this.z_internalAddToOrganization(organization);
		}
	}
	
	public void setOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole organizationFullfillsActorRole_organization) {
		OrganizationFullfillsActorRole oldValue = this.getOrganizationFullfillsActorRole_organization();
		propertyChangeSupport.firePropertyChange("organizationFullfillsActorRole_organization",getOrganizationFullfillsActorRole_organization(),organizationFullfillsActorRole_organization);
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
		propertyChangeSupport.firePropertyChange("participation",getParticipation(),participation);
		this.clearParticipation();
		this.addAllToParticipation(participation);
	}
	
	public void setParticipationParticipant_participation(Set<ParticipationParticipant> participationParticipant_participation) {
		propertyChangeSupport.firePropertyChange("participationParticipant_participation",getParticipationParticipant_participation(),participationParticipant_participation);
		this.clearParticipationParticipant_participation();
		this.addAllToParticipationParticipant_participation(participationParticipant_participation);
	}
	
	public void setPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole personFullfillsActorRole_representedPerson) {
		PersonFullfillsActorRole oldValue = this.getPersonFullfillsActorRole_representedPerson();
		propertyChangeSupport.firePropertyChange("personFullfillsActorRole_representedPerson",getPersonFullfillsActorRole_representedPerson(),personFullfillsActorRole_representedPerson);
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
		propertyChangeSupport.firePropertyChange("representedPerson",getRepresentedPerson(),representedPerson);
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromBusinessActor(this);
		}
		if ( representedPerson!=null ) {
			this.z_internalAddToRepresentedPerson(representedPerson);
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
	
	public void z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessCollaboration(IBusinessCollaboration_iBusinessActor_1 val) {
		this.iBusinessCollaboration_iBusinessActor_1_businessCollaboration=val;
	}
	
	public void z_internalAddToOrganization(OrganizationNode organization) {
		OrganizationFullfillsActorRole newOne = new OrganizationFullfillsActorRole(this,organization);
		this.z_internalAddToOrganizationFullfillsActorRole_organization(newOne);
		newOne.getOrganization().z_internalAddToOrganizationFullfillsActorRole_businessActor(newOne);
	}
	
	public void z_internalAddToOrganizationFullfillsActorRole_organization(OrganizationFullfillsActorRole val) {
		this.organizationFullfillsActorRole_organization=val;
	}
	
	public void z_internalAddToParticipation(Participation participation) {
		ParticipationParticipant newOne = new ParticipationParticipant(this,participation);
		this.z_internalAddToParticipationParticipant_participation(newOne);
		newOne.getParticipation().z_internalAddToParticipationParticipant_participant(newOne);
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant val) {
		this.participationParticipant_participation.add(val);
	}
	
	public void z_internalAddToPersonFullfillsActorRole_representedPerson(PersonFullfillsActorRole val) {
		this.personFullfillsActorRole_representedPerson=val;
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode representedPerson) {
		PersonFullfillsActorRole newOne = new PersonFullfillsActorRole(this,representedPerson);
		this.z_internalAddToPersonFullfillsActorRole_representedPerson(newOne);
		newOne.getRepresentedPerson().z_internalAddToPersonFullfillsActorRole_businessActor(newOne);
	}
	
	public void z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(IBusinessCollaboration_iBusinessActor_1 val) {
		if ( getIBusinessCollaboration_iBusinessActor_1_businessCollaboration()!=null && val!=null && val.equals(getIBusinessCollaboration_iBusinessActor_1_businessCollaboration()) ) {
			this.iBusinessCollaboration_iBusinessActor_1_businessCollaboration=null;
			this.iBusinessCollaboration_iBusinessActor_1_businessCollaboration=null;
		}
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
	
	public void z_internalRemoveFromParticipation(Participation participation) {
		for ( ParticipationParticipant cur : new HashSet<ParticipationParticipant>(this.participationParticipant_participation) ) {
			if ( cur.getParticipation().equals(participation) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromParticipationParticipant_participation(ParticipationParticipant val) {
		this.participationParticipant_participation.remove(val);
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