package structuredbusiness;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.opaeum.annotation.BusinessRole;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.organization.Person_iBusinessRole_1;
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
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_u7lZEGQWEeGbL9nlXe9lTQ")
@BusinessRole
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="manager")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Manager")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Manager implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessRole, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Length(groups={},max=15,message="Phone number must consist of between  9 and 15 characters",min=8,payload={})
	@Digits(fraction=0,groups={},integer=15,message="",payload={})
	@Column(name="contact_number")
	private String contactNumber;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="dishwashers_inc_id",name="idx_manager_dishwashers_inc_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dishwashers_inc_id",nullable=true)
	private ApplianceDoctor dishwashersInc;
	@Column(name="hourly_rate")
	private Double hourlyRate;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Manager> mockedAllInstances;
	@Column(name="name")
	private String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_u7lZEGQWEeGbL9nlXe9lTQ'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participation_participant_participation_id",nullable=true)
	private Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private AbstractPersistence persistence;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_i_business_role_1_represented_person_id",nullable=true)
	private Person_iBusinessRole_1 person_iBusinessRole_1_representedPerson;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 3586662115628447123l;
	private String uid;
	@Column(name="years_in_position")
	private String yearsInPosition;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Manager(ApplianceDoctor owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Manager
	 */
	public Manager() {
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
		getDishwashersInc().z_internalAddToManager((Manager)this);
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
	
	static public Set<? extends Manager> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(structuredbusiness.Manager.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("yearsInPosition").length()>0 ) {
			setYearsInPosition(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("yearsInPosition")));
		}
		if ( xml.getAttribute("contactNumber").length()>0 ) {
			setContactNumber(StructuredbusinessFormatter.getInstance().parsePhoneNumber(xml.getAttribute("contactNumber")));
		}
		if ( xml.getAttribute("hourlyRate").length()>0 ) {
			setHourlyRate(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("hourlyRate")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("person_iBusinessRole_1_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6594897030343926470")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Person_iBusinessRole_1 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setPerson_iBusinessRole_1_representedPerson(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
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
	
	public void copyShallowState(Manager from, Manager to) {
		to.setName(from.getName());
		to.setYearsInPosition(from.getYearsInPosition());
		to.setContactNumber(from.getContactNumber());
		to.setHourlyRate(from.getHourlyRate());
	}
	
	public void copyState(Manager from, Manager to) {
		to.setName(from.getName());
		to.setYearsInPosition(from.getYearsInPosition());
		to.setContactNumber(from.getContactNumber());
		to.setHourlyRate(from.getHourlyRate());
	}
	
	public void createComponents() {
	}
	
	public ParticipationParticipant createParticipationParticipant_participation() {
		ParticipationParticipant newInstance= new ParticipationParticipant();
		newInstance.init(this);
		return newInstance;
	}
	
	public Person_iBusinessRole_1 createPerson_iBusinessRole_1_representedPerson() {
		Person_iBusinessRole_1 newInstance= new Person_iBusinessRole_1();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Manager ) {
			return other==this || ((Manager)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2919533358671176131l,uuid="914890@_M_kSoIPrEeGccYWaoIFIyQ")
	@NumlMetaInfo(uuid="914890@_M_kSoIPrEeGccYWaoIFIyQ")
	public String getContactNumber() {
		String result = this.contactNumber;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=325738725014330933l,opposite="manager",uuid="914890@_0XIvwXHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0XIvwXHgEeGus4aKic9sIg")
	public ApplianceDoctor getDishwashersInc() {
		ApplianceDoctor result = this.dishwashersInc;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4619353406591108579l,uuid="914890@_ROTj8IPrEeGccYWaoIFIyQ")
	@NumlMetaInfo(uuid="914890@_ROTj8IPrEeGccYWaoIFIyQ")
	public Double getHourlyRate() {
		Double result = this.hourlyRate;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1221154963085114366l,uuid="914890@_hU8D8HphEeGlh5y8zQdYBA")
	@NumlMetaInfo(uuid="914890@_hU8D8HphEeGlh5y8zQdYBA")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
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
		return getDishwashersInc();
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6594897030343926470l,opposite="businessRole",uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_tH0fAIoVEeCLqpffVZYAlw@252060@_3lcZgFYuEeGj5_I7bIwNoA")
	public Person_iBusinessRole_1 getPerson_iBusinessRole_1_representedPerson() {
		Person_iBusinessRole_1 result = this.person_iBusinessRole_1_representedPerson;
		
		return result;
	}
	
	public Person_iBusinessRole_1 getPerson_iBusinessRole_1_representedPersonFor(PersonNode match) {
		if ( person_iBusinessRole_1_representedPerson.getRepresentedPerson().equals(match) ) {
			return person_iBusinessRole_1_representedPerson;
		} else {
			return null;
		}
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8923586012099856841l,opposite="businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
	public PersonNode getRepresentedPerson() {
		PersonNode result = null;
		if ( this.person_iBusinessRole_1_representedPerson!=null ) {
			result = this.person_iBusinessRole_1_representedPerson.getRepresentedPerson();
		}
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1631645898744506437l,uuid="914890@_JMxWcIPrEeGccYWaoIFIyQ")
	@NumlMetaInfo(uuid="914890@_JMxWcIPrEeGccYWaoIFIyQ")
	public String getYearsInPosition() {
		String result = this.yearsInPosition;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToDishwashersInc((ApplianceDoctor)owner);
		createComponents();
	}
	
	public Manager makeCopy() {
		Manager result = new Manager();
		copyState((Manager)this,result);
		return result;
	}
	
	public Manager makeShallowCopy() {
		Manager result = new Manager();
		copyShallowState((Manager)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getDishwashersInc()!=null ) {
			getDishwashersInc().z_internalRemoveFromManager(this);
		}
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromBusinessRole(this);
		}
		if ( getPerson_iBusinessRole_1_representedPerson()!=null ) {
			getPerson_iBusinessRole_1_representedPerson().markDeleted();
		}
		for ( ParticipationParticipant child : new ArrayList<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Manager> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("person_iBusinessRole_1_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6594897030343926470")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Person_iBusinessRole_1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void setContactNumber(String contactNumber) {
		propertyChangeSupport.firePropertyChange("contactNumber",getContactNumber(),contactNumber);
		this.z_internalAddToContactNumber(contactNumber);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishwashersInc(ApplianceDoctor dishwashersInc) {
		propertyChangeSupport.firePropertyChange("dishwashersInc",getDishwashersInc(),dishwashersInc);
		if ( this.getDishwashersInc()!=null ) {
			this.getDishwashersInc().z_internalRemoveFromManager(this);
		}
		if ( dishwashersInc!=null ) {
			dishwashersInc.z_internalAddToManager(this);
			this.z_internalAddToDishwashersInc(dishwashersInc);
		}
	}
	
	public void setHourlyRate(Double hourlyRate) {
		propertyChangeSupport.firePropertyChange("hourlyRate",getHourlyRate(),hourlyRate);
		this.z_internalAddToHourlyRate(hourlyRate);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name",getName(),name);
		this.z_internalAddToName(name);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
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
	
	public void setPerson_iBusinessRole_1_representedPerson(Person_iBusinessRole_1 person_iBusinessRole_1_representedPerson) {
		Person_iBusinessRole_1 oldValue = this.getPerson_iBusinessRole_1_representedPerson();
		propertyChangeSupport.firePropertyChange("person_iBusinessRole_1_representedPerson",getPerson_iBusinessRole_1_representedPerson(),person_iBusinessRole_1_representedPerson);
		if ( oldValue==null ) {
			if ( person_iBusinessRole_1_representedPerson!=null ) {
				Manager oldOther = (Manager)person_iBusinessRole_1_representedPerson.getBusinessRole();
				person_iBusinessRole_1_representedPerson.z_internalRemoveFromBusinessRole(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(person_iBusinessRole_1_representedPerson);
				}
				person_iBusinessRole_1_representedPerson.z_internalAddToBusinessRole((Manager)this);
			}
			this.z_internalAddToPerson_iBusinessRole_1_representedPerson(person_iBusinessRole_1_representedPerson);
		} else {
			if ( !oldValue.equals(person_iBusinessRole_1_representedPerson) ) {
				oldValue.z_internalRemoveFromBusinessRole(this);
				z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(oldValue);
				if ( person_iBusinessRole_1_representedPerson!=null ) {
					Manager oldOther = (Manager)person_iBusinessRole_1_representedPerson.getBusinessRole();
					person_iBusinessRole_1_representedPerson.z_internalRemoveFromBusinessRole(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(person_iBusinessRole_1_representedPerson);
					}
					person_iBusinessRole_1_representedPerson.z_internalAddToBusinessRole((Manager)this);
				}
				this.z_internalAddToPerson_iBusinessRole_1_representedPerson(person_iBusinessRole_1_representedPerson);
			}
		}
	}
	
	public void setRepresentedPerson(IPersonNode p) {
		setRepresentedPerson((PersonNode)p);
	}
	
	public void setRepresentedPerson(PersonNode representedPerson) {
		propertyChangeSupport.firePropertyChange("representedPerson",getRepresentedPerson(),representedPerson);
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromBusinessRole(this);
		}
		if ( representedPerson!=null ) {
			this.z_internalAddToRepresentedPerson(representedPerson);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setYearsInPosition(String yearsInPosition) {
		propertyChangeSupport.firePropertyChange("yearsInPosition",getYearsInPosition(),yearsInPosition);
		this.z_internalAddToYearsInPosition(yearsInPosition);
	}
	
	public String toXmlReferenceString() {
		return "<Manager uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Manager ");
		sb.append("classUuid=\"914890@_u7lZEGQWEeGbL9nlXe9lTQ\" ");
		sb.append("className=\"structuredbusiness.Manager\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getYearsInPosition()!=null ) {
			sb.append("yearsInPosition=\""+ StructuredbusinessFormatter.getInstance().formatString(getYearsInPosition())+"\" ");
		}
		if ( getContactNumber()!=null ) {
			sb.append("contactNumber=\""+ StructuredbusinessFormatter.getInstance().formatPhoneNumber(getContactNumber())+"\" ");
		}
		if ( getHourlyRate()!=null ) {
			sb.append("hourlyRate=\""+ StructuredbusinessFormatter.getInstance().formatReal(getHourlyRate())+"\" ");
		}
		sb.append(">");
		if ( getPerson_iBusinessRole_1_representedPerson()==null ) {
			sb.append("\n<person_iBusinessRole_1_representedPerson/>");
		} else {
			sb.append("\n<person_iBusinessRole_1_representedPerson propertyId=\"6594897030343926470\">");
			sb.append("\n" + getPerson_iBusinessRole_1_representedPerson().toXmlString());
			sb.append("\n</person_iBusinessRole_1_representedPerson>");
		}
		sb.append("\n<participationParticipant_participation propertyId=\"5579540379306504838\">");
		for ( ParticipationParticipant participationParticipant_participation : getParticipationParticipant_participation() ) {
			sb.append("\n" + participationParticipant_participation.toXmlString());
		}
		sb.append("\n</participationParticipant_participation>");
		sb.append("\n</Manager>");
		return sb.toString();
	}
	
	public void z_internalAddToContactNumber(String val) {
		this.contactNumber=val;
	}
	
	public void z_internalAddToDishwashersInc(ApplianceDoctor val) {
		this.dishwashersInc=val;
	}
	
	public void z_internalAddToHourlyRate(Double val) {
		this.hourlyRate=val;
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
	}
	
	public void z_internalAddToParticipation(Participation participation) {
		ParticipationParticipant newOne = new ParticipationParticipant(this,participation);
		this.z_internalAddToParticipationParticipant_participation(newOne);
		newOne.getParticipation().z_internalAddToParticipationParticipant_participant(newOne);
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant val) {
		this.participationParticipant_participation.add(val);
	}
	
	public void z_internalAddToPerson_iBusinessRole_1_representedPerson(Person_iBusinessRole_1 val) {
		this.person_iBusinessRole_1_representedPerson=val;
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode representedPerson) {
		Person_iBusinessRole_1 newOne = new Person_iBusinessRole_1(this,representedPerson);
		this.z_internalAddToPerson_iBusinessRole_1_representedPerson(newOne);
		newOne.getRepresentedPerson().z_internalAddToPerson_iBusinessRole_1_businessRole(newOne);
	}
	
	public void z_internalAddToYearsInPosition(String val) {
		this.yearsInPosition=val;
	}
	
	public void z_internalRemoveFromContactNumber(String val) {
		if ( getContactNumber()!=null && val!=null && val.equals(getContactNumber()) ) {
			this.contactNumber=null;
			this.contactNumber=null;
		}
	}
	
	public void z_internalRemoveFromDishwashersInc(ApplianceDoctor val) {
		if ( getDishwashersInc()!=null && val!=null && val.equals(getDishwashersInc()) ) {
			this.dishwashersInc=null;
			this.dishwashersInc=null;
		}
	}
	
	public void z_internalRemoveFromHourlyRate(Double val) {
		if ( getHourlyRate()!=null && val!=null && val.equals(getHourlyRate()) ) {
			this.hourlyRate=null;
			this.hourlyRate=null;
		}
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
			this.name=null;
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
	
	public void z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(Person_iBusinessRole_1 val) {
		if ( getPerson_iBusinessRole_1_representedPerson()!=null && val!=null && val.equals(getPerson_iBusinessRole_1_representedPerson()) ) {
			this.person_iBusinessRole_1_representedPerson=null;
			this.person_iBusinessRole_1_representedPerson=null;
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode representedPerson) {
		if ( this.person_iBusinessRole_1_representedPerson!=null ) {
			this.person_iBusinessRole_1_representedPerson.clear();
		}
	}
	
	public void z_internalRemoveFromYearsInPosition(String val) {
		if ( getYearsInPosition()!=null && val!=null && val.equals(getYearsInPosition()) ) {
			this.yearsInPosition=null;
			this.yearsInPosition=null;
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