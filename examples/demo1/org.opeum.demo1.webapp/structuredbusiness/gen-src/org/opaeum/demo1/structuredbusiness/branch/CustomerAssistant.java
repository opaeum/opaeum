package org.opaeum.demo1.structuredbusiness.branch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.BusinessRole;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.Online_Customer;
import org.opaeum.demo1.structuredbusiness.job.Job;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.costing.RatePerTimeUnit;
import org.opaeum.runtime.bpm.costing.TimedResourceRatePerTimeUnit;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.PersonInBusinessRole;
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
import org.opaeum.runtime.costing.IRatePerTimeUnit;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.FailedConstraint;
import org.opaeum.runtime.domain.FailedConstraintsException;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_bX-ooJJPEeGW4L5IejZxpA")
@BusinessRole
@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="customer_assistant",schema="structuredbusiness")
@Entity(name="CustomerAssistant")
public class CustomerAssistant implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessRole, Serializable {
	@Index(columnNames="branch_id",name="idx_customer_assistant_branch_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="branch_id",nullable=true)
	protected Branch branch;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="customer_assistant",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,mappedBy="customerAssistant",targetEntity=Job.class)
	protected Set<Job> job = new HashSet<Job>();
	static private Set<? extends CustomerAssistant> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_bX-ooJJPEeGW4L5IejZxpA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participant",nullable=true)
	protected Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private InternalHibernatePersistence persistence;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_in_business_role_represented_person_id",nullable=true)
	protected PersonInBusinessRole personInBusinessRole_representedPerson;
	@Type(type="org.opaeum.runtime.contact.PersonEMailAddressTypeResolver")
	@Column(name="preferred_e_mail_address_type",nullable=true)
	protected PersonEMailAddressType preferredEMailAddressType;
	@Type(type="org.opaeum.runtime.event.NotificationTypeResolver")
	@Column(name="preferred_notification_type",nullable=true)
	protected NotificationType preferredNotificationType;
	@Type(type="org.opaeum.runtime.contact.PersonPhoneNumberTypeResolver")
	@Column(name="preferred_phone_number_type",nullable=true)
	protected PersonPhoneNumberType preferredPhoneNumberType;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8190330448809072926l;
	@Where(clause="timed_resource_type='914890@_bX-ooJJPEeGW4L5IejZxpA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@IndexColumn(name="idx_in_t_r_r_p_t_u_r_p_t_u_on_i_t_r")
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=TimedResourceRatePerTimeUnit.class)
	@JoinColumn(name="timed_resource",nullable=true)
	protected List<TimedResourceRatePerTimeUnit> timedResourceRatePerTimeUnit_ratePerTimeUnit = new ArrayList<TimedResourceRatePerTimeUnit>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public CustomerAssistant(Branch owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for CustomerAssistant
	 */
	public CustomerAssistant() {
	}

	public void addAllToJob(Set<Job> job) {
		for ( Job o : job ) {
			addToJob(o);
		}
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
	
	public void addAllToRatePerTimeUnit(List<RatePerTimeUnit> ratePerTimeUnit) {
		for ( RatePerTimeUnit o : ratePerTimeUnit ) {
			addToRatePerTimeUnit(o);
		}
	}
	
	public void addAllToTimedResourceRatePerTimeUnit_ratePerTimeUnit(List<TimedResourceRatePerTimeUnit> timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		for ( TimedResourceRatePerTimeUnit o : timedResourceRatePerTimeUnit_ratePerTimeUnit ) {
			addToTimedResourceRatePerTimeUnit_ratePerTimeUnit(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToJob(Job job) {
		if ( job!=null ) {
			job.z_internalRemoveFromCustomerAssistant(job.getCustomerAssistant());
			job.z_internalAddToCustomerAssistant(this);
			z_internalAddToJob(job);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBranch().z_internalAddToCustomerAssistant((CustomerAssistant)this);
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
	
	public void addToRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		if ( ratePerTimeUnit!=null ) {
			ratePerTimeUnit.z_internalRemoveFromTimedResource(ratePerTimeUnit.getTimedResource());
			z_internalAddToRatePerTimeUnit(ratePerTimeUnit);
		}
	}
	
	public void addToTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		if ( timedResourceRatePerTimeUnit_ratePerTimeUnit!=null ) {
			timedResourceRatePerTimeUnit_ratePerTimeUnit.z_internalRemoveFromTimedResource(timedResourceRatePerTimeUnit_ratePerTimeUnit.getTimedResource());
			timedResourceRatePerTimeUnit_ratePerTimeUnit.z_internalAddToTimedResource(this);
			z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(timedResourceRatePerTimeUnit_ratePerTimeUnit);
		}
	}
	
	static public Set<? extends CustomerAssistant> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.branch.CustomerAssistant.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personInBusinessRole_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6594897030343926470")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PersonInBusinessRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setPersonInBusinessRole_representedPerson(curVal);
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
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToParticipationParticipant_participation(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearJob() {
		removeAllFromJob(getJob());
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void clearParticipationParticipant_participation() {
		removeAllFromParticipationParticipant_participation(getParticipationParticipant_participation());
	}
	
	public void clearRatePerTimeUnit() {
		removeAllFromRatePerTimeUnit(getRatePerTimeUnit());
	}
	
	public void clearTimedResourceRatePerTimeUnit_ratePerTimeUnit() {
		removeAllFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(getTimedResourceRatePerTimeUnit_ratePerTimeUnit());
	}
	
	public boolean consumeFollowLeadOccurrence(@ParameterMetaInfo(name="customer",opaeumId=5501124320077697795l,uuid="914890@_Dzy24JKiEeGiJMBDeZRymA") Online_Customer customer, @ParameterMetaInfo(name="timeOfLead",opaeumId=3684593961093410895l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_D1Kv4JKiEeGiJMBDeZRymA") Date timeOfLead) {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(CustomerAssistant from, CustomerAssistant to) {
		to.setPreferredNotificationType(from.getPreferredNotificationType());
		to.setPreferredEMailAddressType(from.getPreferredEMailAddressType());
		to.setPreferredPhoneNumberType(from.getPreferredPhoneNumberType());
	}
	
	public void copyState(CustomerAssistant from, CustomerAssistant to) {
		to.setPreferredNotificationType(from.getPreferredNotificationType());
		to.setPreferredEMailAddressType(from.getPreferredEMailAddressType());
		to.setPreferredPhoneNumberType(from.getPreferredPhoneNumberType());
		for ( RatePerTimeUnit child : from.getRatePerTimeUnit() ) {
			to.addToRatePerTimeUnit(child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public ParticipationParticipant createParticipationParticipant_participation() {
		ParticipationParticipant newInstance= new ParticipationParticipant();
		newInstance.init(this);
		return newInstance;
	}
	
	public PersonInBusinessRole createPersonInBusinessRole_representedPerson() {
		PersonInBusinessRole newInstance= new PersonInBusinessRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public RatePerTimeUnit createRatePerTimeUnit() {
		RatePerTimeUnit newInstance= new RatePerTimeUnit();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof CustomerAssistant ) {
			return other==this || ((CustomerAssistant)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="914890@_A1hu8JKiEeGiJMBDeZRymA")
	public void followLead(@ParameterMetaInfo(name="customer",opaeumId=5501124320077697795l,uuid="914890@_Dzy24JKiEeGiJMBDeZRymA") Online_Customer customer, @ParameterMetaInfo(name="timeOfLead",opaeumId=3684593961093410895l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_D1Kv4JKiEeGiJMBDeZRymA") Date timeOfLead) throws FailedConstraintsException {
		Set<FailedConstraint> failedConstraints = new HashSet<FailedConstraint>();
		Date now = new Date();
		if ( !(now.after(timeOfLead) == true) ) {
			String message = org.opeum.demo1.util.Demo1Environment.INSTANCE.getMessage("structuredbusiness::branch::CustomerAssistant::followLead::newConstraint" );
			failedConstraints.add(new FailedConstraint("timeOfLead" ,message);
		}
		if ( failedConstraints.size()>0 ) {
			throw new FailedConstraintsException(true,failedConstraints);
		}
		generateFollowLeadEvent(customer,timeOfLead);
	}
	
	public void generateFollowLeadEvent(@ParameterMetaInfo(name="customer",opaeumId=5501124320077697795l,uuid="914890@_Dzy24JKiEeGiJMBDeZRymA") Online_Customer customer, @ParameterMetaInfo(name="timeOfLead",opaeumId=3684593961093410895l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_D1Kv4JKiEeGiJMBDeZRymA") Date timeOfLead) {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7513322466736053232l,opposite="customerAssistant",uuid="914890@_pNF-UZJPEeGW4L5IejZxpA")
	@NumlMetaInfo(uuid="914890@_pNF-UZJPEeGW4L5IejZxpA")
	public Branch getBranch() {
		Branch result = this.branch;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4499477814458115732l,uuid="252060@_6BkycPl5EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_6BkycPl5EeGYn9aDKdPmKA")
	public BusinessCalendar getBusinessCalendarToUse() {
		BusinessCalendar result = BusinessCalendar.getInstance();
		
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
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>(collect11());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5635486542671558270l,uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>(collect2());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3791221050524753443l,opposite="customerAssistant",uuid="914890@_YMhu8ZLAEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_YMhu8ZLAEeGnpuq6_ber_Q")
	public Set<Job> getJob() {
		Set<Job> result = this.job;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5447021495172291044l,uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests() {
		Collection<AbstractRequest> result = new ArrayList<AbstractRequest>(collect9());
		
		return result;
	}
	
	public String getName() {
		return "CustomerAssistant["+getId()+"]";
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
		return getBranch();
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
	public PersonInBusinessRole getPersonInBusinessRole_representedPerson() {
		PersonInBusinessRole result = this.personInBusinessRole_representedPerson;
		
		return result;
	}
	
	public PersonInBusinessRole getPersonInBusinessRole_representedPersonFor(PersonNode match) {
		if ( personInBusinessRole_representedPerson.getRepresentedPerson().equals(match) ) {
			return personInBusinessRole_representedPerson;
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
	
	@NumlMetaInfo(uuid="252060@_V3n1EPjyEeGEN6Fz86uBYA")
	public IRatePerTimeUnit getRateEffectiveOn(@ParameterMetaInfo(name="date",opaeumId=818066613415140745l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_nQHK4PjyEeGEN6Fz86uBYA") Date date) {
		IRatePerTimeUnit result = getAtIndex13(select12(date));
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=534666560944430555l,opposite="timedResource",uuid="252060@_dIQKFPjyEeGEN6Fz86uBYA")
	public List<RatePerTimeUnit> getRatePerTimeUnit() {
		ArrayList<IRatePerTimeUnit> result = new ArrayList<IRatePerTimeUnit>new ArrayList<RatePerTimeUnit>());
		for ( TimedResourceRatePerTimeUnit cur : this.getTimedResourceRatePerTimeUnit_ratePerTimeUnit() ) {
			result.add(cur.getRatePerTimeUnit());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8923586012099856841l,opposite="businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
	public PersonNode getRepresentedPerson() {
		PersonNode result = null;
		if ( this.personInBusinessRole_representedPerson!=null ) {
			result = this.personInBusinessRole_representedPerson.getRepresentedPerson();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=726238315624324931l,opposite="timedResource",uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_aCyDYPjxEeGEN6Fz86uBYA@252060@_dIQKEPjyEeGEN6Fz86uBYA")
	public List<TimedResourceRatePerTimeUnit> getTimedResourceRatePerTimeUnit_ratePerTimeUnit() {
		List<TimedResourceRatePerTimeUnit> result = this.timedResourceRatePerTimeUnit_ratePerTimeUnit;
		
		return result;
	}
	
	public TimedResourceRatePerTimeUnit getTimedResourceRatePerTimeUnit_ratePerTimeUnitFor(RatePerTimeUnit match) {
		for ( TimedResourceRatePerTimeUnit var : getTimedResourceRatePerTimeUnit_ratePerTimeUnit() ) {
			if ( var.getRatePerTimeUnit().equals(match) ) {
				return var;
			}
		}
		return null;
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
		this.z_internalAddToBranch((Branch)owner);
		this.setPreferredEMailAddressType( PersonEMailAddressType.WORK );
		this.setPreferredPhoneNumberType( PersonPhoneNumberType.CELL );
		this.setPreferredNotificationType( NotificationType.EMAIL );
		createComponents();
	}
	
	public CustomerAssistant makeCopy() {
		CustomerAssistant result = new CustomerAssistant();
		copyState((CustomerAssistant)this,result);
		return result;
	}
	
	public CustomerAssistant makeShallowCopy() {
		CustomerAssistant result = new CustomerAssistant();
		copyShallowState((CustomerAssistant)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromBusinessRole(this);
		}
		if ( getBranch()!=null ) {
			getBranch().z_internalRemoveFromCustomerAssistant(this);
		}
		for ( RatePerTimeUnit child : new ArrayList<RatePerTimeUnit>(getRatePerTimeUnit()) ) {
			child.markDeleted();
		}
		if ( getPersonInBusinessRole_representedPerson()!=null ) {
			getPersonInBusinessRole_representedPerson().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("personInBusinessRole_representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6594897030343926470")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PersonInBusinessRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("timedResourceRatePerTimeUnit_ratePerTimeUnit") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("726238315624324931")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						addToTimedResourceRatePerTimeUnit_ratePerTimeUnit((TimedResourceRatePerTimeUnit)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromJob(Set<Job> job) {
		Set<Job> tmp = new HashSet<Job>(job);
		for ( Job o : tmp ) {
			removeFromJob(o);
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
	
	public void removeAllFromRatePerTimeUnit(List<RatePerTimeUnit> ratePerTimeUnit) {
		List<RatePerTimeUnit> tmp = new ArrayList<RatePerTimeUnit>(ratePerTimeUnit);
		for ( RatePerTimeUnit o : tmp ) {
			removeFromRatePerTimeUnit(o);
		}
	}
	
	public void removeAllFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(List<TimedResourceRatePerTimeUnit> timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		List<TimedResourceRatePerTimeUnit> tmp = new ArrayList<TimedResourceRatePerTimeUnit>(timedResourceRatePerTimeUnit_ratePerTimeUnit);
		for ( TimedResourceRatePerTimeUnit o : tmp ) {
			removeFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(o);
		}
	}
	
	public void removeFromJob(Job job) {
		if ( job!=null ) {
			job.z_internalRemoveFromCustomerAssistant(this);
			z_internalRemoveFromJob(job);
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
			participationParticipant_participation.markDeleted();
		}
	}
	
	public void removeFromRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		if ( ratePerTimeUnit!=null ) {
			z_internalRemoveFromRatePerTimeUnit(ratePerTimeUnit);
			ratePerTimeUnit.markDeleted();
		}
	}
	
	public void removeFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		if ( timedResourceRatePerTimeUnit_ratePerTimeUnit!=null ) {
			timedResourceRatePerTimeUnit_ratePerTimeUnit.z_internalRemoveFromTimedResource(this);
			z_internalRemoveFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(timedResourceRatePerTimeUnit_ratePerTimeUnit);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setBranch(Branch branch) {
		propertyChangeSupport.firePropertyChange("branch",getBranch(),branch);
		if ( this.getBranch()!=null ) {
			this.getBranch().z_internalRemoveFromCustomerAssistant(this);
		}
		if ( branch!=null ) {
			branch.z_internalAddToCustomerAssistant(this);
			this.z_internalAddToBranch(branch);
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
	
	public void setJob(Set<Job> job) {
		propertyChangeSupport.firePropertyChange("job",getJob(),job);
		this.clearJob();
		this.addAllToJob(job);
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
	
	public void setPersonInBusinessRole_representedPerson(PersonInBusinessRole personInBusinessRole_representedPerson) {
		PersonInBusinessRole oldValue = this.getPersonInBusinessRole_representedPerson();
		propertyChangeSupport.firePropertyChange("personInBusinessRole_representedPerson",getPersonInBusinessRole_representedPerson(),personInBusinessRole_representedPerson);
		if ( oldValue==null ) {
			if ( personInBusinessRole_representedPerson!=null ) {
				CustomerAssistant oldOther = (CustomerAssistant)personInBusinessRole_representedPerson.getBusinessRole();
				personInBusinessRole_representedPerson.z_internalRemoveFromBusinessRole(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromPersonInBusinessRole_representedPerson(personInBusinessRole_representedPerson);
				}
				personInBusinessRole_representedPerson.z_internalAddToBusinessRole((CustomerAssistant)this);
			}
			this.z_internalAddToPersonInBusinessRole_representedPerson(personInBusinessRole_representedPerson);
		} else {
			if ( !oldValue.equals(personInBusinessRole_representedPerson) ) {
				oldValue.z_internalRemoveFromBusinessRole(this);
				z_internalRemoveFromPersonInBusinessRole_representedPerson(oldValue);
				if ( personInBusinessRole_representedPerson!=null ) {
					CustomerAssistant oldOther = (CustomerAssistant)personInBusinessRole_representedPerson.getBusinessRole();
					personInBusinessRole_representedPerson.z_internalRemoveFromBusinessRole(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromPersonInBusinessRole_representedPerson(personInBusinessRole_representedPerson);
					}
					personInBusinessRole_representedPerson.z_internalAddToBusinessRole((CustomerAssistant)this);
				}
				this.z_internalAddToPersonInBusinessRole_representedPerson(personInBusinessRole_representedPerson);
			}
		}
	}
	
	public void setPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		propertyChangeSupport.firePropertyChange("preferredEMailAddressType",getPreferredEMailAddressType(),preferredEMailAddressType);
		this.z_internalAddToPreferredEMailAddressType(preferredEMailAddressType);
	}
	
	public void setPreferredNotificationType(NotificationType preferredNotificationType) {
		propertyChangeSupport.firePropertyChange("preferredNotificationType",getPreferredNotificationType(),preferredNotificationType);
		this.z_internalAddToPreferredNotificationType(preferredNotificationType);
	}
	
	public void setPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		propertyChangeSupport.firePropertyChange("preferredPhoneNumberType",getPreferredPhoneNumberType(),preferredPhoneNumberType);
		this.z_internalAddToPreferredPhoneNumberType(preferredPhoneNumberType);
	}
	
	public void setRatePerTimeUnit(List<RatePerTimeUnit> ratePerTimeUnit) {
		propertyChangeSupport.firePropertyChange("ratePerTimeUnit",getRatePerTimeUnit(),ratePerTimeUnit);
		this.clearRatePerTimeUnit();
		this.addAllToRatePerTimeUnit(ratePerTimeUnit);
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
	
	public void setTimedResourceRatePerTimeUnit_ratePerTimeUnit(List<TimedResourceRatePerTimeUnit> timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		propertyChangeSupport.firePropertyChange("timedResourceRatePerTimeUnit_ratePerTimeUnit",getTimedResourceRatePerTimeUnit_ratePerTimeUnit(),timedResourceRatePerTimeUnit_ratePerTimeUnit);
		this.clearTimedResourceRatePerTimeUnit_ratePerTimeUnit();
		this.addAllToTimedResourceRatePerTimeUnit_ratePerTimeUnit(timedResourceRatePerTimeUnit_ratePerTimeUnit);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<CustomerAssistant uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<CustomerAssistant ");
		sb.append("classUuid=\"914890@_bX-ooJJPEeGW4L5IejZxpA\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.branch.CustomerAssistant\" ");
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
		if ( getPersonInBusinessRole_representedPerson()==null ) {
			sb.append("\n<personInBusinessRole_representedPerson/>");
		} else {
			sb.append("\n<personInBusinessRole_representedPerson propertyId=\"6594897030343926470\">");
			sb.append("\n" + getPersonInBusinessRole_representedPerson().toXmlString());
			sb.append("\n</personInBusinessRole_representedPerson>");
		}
		sb.append("\n<participationParticipant_participation propertyId=\"5579540379306504838\">");
		for ( ParticipationParticipant participationParticipant_participation : getParticipationParticipant_participation() ) {
			sb.append("\n" + participationParticipant_participation.toXmlString());
		}
		sb.append("\n</participationParticipant_participation>");
		sb.append("\n<timedResourceRatePerTimeUnit_ratePerTimeUnit propertyId=\"726238315624324931\">");
		for ( TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit : getTimedResourceRatePerTimeUnit_ratePerTimeUnit() ) {
			sb.append("\n" + timedResourceRatePerTimeUnit_ratePerTimeUnit.toXmlReferenceString());
		}
		sb.append("\n</timedResourceRatePerTimeUnit_ratePerTimeUnit>");
		sb.append("\n</CustomerAssistant>");
		return sb.toString();
	}
	
	public void z_internalAddToBranch(Branch branch) {
		this.branch=branch;
	}
	
	public void z_internalAddToJob(Job job) {
		this.job.add(job);
	}
	
	public void z_internalAddToParticipation(Participation participation) {
		ParticipationParticipant newOne = new ParticipationParticipant(this,participation);
		this.z_internalAddToParticipationParticipant_participation(newOne);
		newOne.getParticipation().z_internalAddToParticipationParticipant_participant(newOne);
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		this.participationParticipant_participation.add(participationParticipant_participation);
	}
	
	public void z_internalAddToPersonInBusinessRole_representedPerson(PersonInBusinessRole personInBusinessRole_representedPerson) {
		this.personInBusinessRole_representedPerson=personInBusinessRole_representedPerson;
	}
	
	public void z_internalAddToPreferredEMailAddressType(PersonEMailAddressType preferredEMailAddressType) {
		this.preferredEMailAddressType=preferredEMailAddressType;
	}
	
	public void z_internalAddToPreferredNotificationType(NotificationType preferredNotificationType) {
		this.preferredNotificationType=preferredNotificationType;
	}
	
	public void z_internalAddToPreferredPhoneNumberType(PersonPhoneNumberType preferredPhoneNumberType) {
		this.preferredPhoneNumberType=preferredPhoneNumberType;
	}
	
	public void z_internalAddToRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		TimedResourceRatePerTimeUnit newOne = new TimedResourceRatePerTimeUnit(this,ratePerTimeUnit);
		this.z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(newOne);
		newOne.getRatePerTimeUnit().z_internalAddToTimedResourceRatePerTimeUnit_timedResource(newOne);
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode representedPerson) {
		PersonInBusinessRole newOne = new PersonInBusinessRole(this,representedPerson);
		this.z_internalAddToPersonInBusinessRole_representedPerson(newOne);
		newOne.getRepresentedPerson().z_internalAddToPersonInBusinessRole_businessRole(newOne);
	}
	
	public void z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		this.timedResourceRatePerTimeUnit_ratePerTimeUnit.add(timedResourceRatePerTimeUnit_ratePerTimeUnit);
	}
	
	public void z_internalRemoveFromBranch(Branch branch) {
		if ( getBranch()!=null && branch!=null && branch.equals(getBranch()) ) {
			this.branch=null;
			this.branch=null;
		}
	}
	
	public void z_internalRemoveFromJob(Job job) {
		this.job.remove(job);
	}
	
	public void z_internalRemoveFromParticipation(Participation participation) {
		for ( ParticipationParticipant cur : new HashSet<ParticipationParticipant>(this.participationParticipant_participation) ) {
			if ( cur.getParticipation().equals(participation) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		this.participationParticipant_participation.remove(participationParticipant_participation);
	}
	
	public void z_internalRemoveFromPersonInBusinessRole_representedPerson(PersonInBusinessRole personInBusinessRole_representedPerson) {
		if ( getPersonInBusinessRole_representedPerson()!=null && personInBusinessRole_representedPerson!=null && personInBusinessRole_representedPerson.equals(getPersonInBusinessRole_representedPerson()) ) {
			this.personInBusinessRole_representedPerson=null;
			this.personInBusinessRole_representedPerson=null;
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
	
	public void z_internalRemoveFromRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		for ( TimedResourceRatePerTimeUnit cur : new ArrayList<TimedResourceRatePerTimeUnit>(this.timedResourceRatePerTimeUnit_ratePerTimeUnit) ) {
			if ( cur.getRatePerTimeUnit().equals(ratePerTimeUnit) ) {
				cur.clear();
				break;
			}
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode representedPerson) {
		if ( this.personInBusinessRole_representedPerson!=null ) {
			this.personInBusinessRole_representedPerson.clear();
		}
	}
	
	public void z_internalRemoveFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		this.timedResourceRatePerTimeUnit_ratePerTimeUnit.remove(timedResourceRatePerTimeUnit_ratePerTimeUnit);
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
	
	private IRatePerTimeUnit getAtIndex13(List<IRatePerTimeUnit> source) {
		IRatePerTimeUnit result = null;
		if ( source.size()>0 ) {
			result=source.get(0);
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
	
	/** Implements self.ratePerTimeUnit->select(temp1 : IRatePerTimeUnit | date.after(temp1.effectiveFrom))
	 * 
	 * @param date 
	 */
	private List<IRatePerTimeUnit> select12(@ParameterMetaInfo(name="date",opaeumId=818066613415140745l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_nQHK4PjyEeGEN6Fz86uBYA") Date date) {
		List<IRatePerTimeUnit> result = new ArrayList<IRatePerTimeUnit>();
		for ( IRatePerTimeUnit temp1 : this.getRatePerTimeUnit() ) {
			if ( date.after(temp1.getEffectiveFrom()) ) {
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