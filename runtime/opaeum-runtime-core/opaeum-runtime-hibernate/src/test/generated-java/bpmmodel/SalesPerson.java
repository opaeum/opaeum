package bpmmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.BusinessRole;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar;
import org.opaeum.runtime.bpm.costing.ITimedResource;
import org.opaeum.runtime.bpm.costing.RatePerTimeUnit;
import org.opaeum.runtime.bpm.costing.TimedResourceRatePerTimeUnit;
import org.opaeum.runtime.bpm.organization.IBusinessRole;
import org.opaeum.runtime.bpm.organization.IParticipant;
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
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.event.NotificationType;
import org.opaeum.runtime.organization.IPersonNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bpmmodel.util.Stdlib;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_qPapgI_gEeK855GX2Z3x4Q")
@BusinessRole
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="sales_person",uniqueConstraints=
	@UniqueConstraint(columnNames={"my_business_id","deleted_on"}))
@Entity(name="SalesPerson")
public class SalesPerson implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessRole, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="sales_person",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends SalesPerson> mockedAllInstances;
	@Index(columnNames="my_business_id",name="idx_sales_person_my_business_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="my_business_id",nullable=true)
	protected MyBusiness myBusiness;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='bpm.uml@_qPapgI_gEeK855GX2Z3x4Q'")
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
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="preferred_e_mail_address_type",nullable=true)
	protected PersonEMailAddressType preferredEMailAddressType;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="preferred_notification_type",nullable=true)
	protected NotificationType preferredNotificationType;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="preferred_phone_number_type",nullable=true)
	protected PersonPhoneNumberType preferredPhoneNumberType;
	static final private long serialVersionUID = 6741923809934540328l;
	@Where(clause="timed_resource_type='bpm.uml@_qPapgI_gEeK855GX2Z3x4Q'")
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
	public SalesPerson(MyBusiness owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for SalesPerson
	 */
	public SalesPerson() {
	}

	public void addAllToParticipation(Set<Participation> participation) {
		for ( Participation o : participation ) {
			addToParticipation(o);
		}
	}
	
	public void addAllToRatePerTimeUnit(List<RatePerTimeUnit> ratePerTimeUnit) {
		for ( RatePerTimeUnit o : ratePerTimeUnit ) {
			addToRatePerTimeUnit(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getMyBusiness().z_internalAddToSalesPerson((SalesPerson)this);
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
	
	public void addToRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		if ( ratePerTimeUnit!=null && !this.getRatePerTimeUnit().contains(ratePerTimeUnit) ) {
			TimedResourceRatePerTimeUnit newLink = new TimedResourceRatePerTimeUnit((ITimedResource)this,(RatePerTimeUnit)ratePerTimeUnit);
			if ( ratePerTimeUnit.getTimedResource()!=null ) {
				ratePerTimeUnit.getTimedResource().removeFromRatePerTimeUnit(ratePerTimeUnit);
			}
			this.z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(newLink);
			ratePerTimeUnit.z_internalAddToTimedResourceRatePerTimeUnit_timedResource(newLink);
		}
	}
	
	static public Set<? extends SalesPerson> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.SalesPerson.class));
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
						this.z_internalAddToPersonInBusinessRole_representedPerson(curVal);
						curVal.z_internalAddToBusinessRole(this);
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
						this.z_internalAddToParticipationParticipant_participation(curVal);
						curVal.z_internalAddToParticipant(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("timedResourceRatePerTimeUnit_ratePerTimeUnit") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("726238315624324931")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TimedResourceRatePerTimeUnit curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(curVal);
						curVal.z_internalAddToTimedResource(this);
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
	
	public void clearRatePerTimeUnit() {
		List<RatePerTimeUnit> tmp = new ArrayList<RatePerTimeUnit>(getRatePerTimeUnit());
		for ( RatePerTimeUnit o : tmp ) {
			removeFromRatePerTimeUnit(o);
		}
	}
	
	public void copyShallowState(SalesPerson from, SalesPerson to) {
		to.setPreferredNotificationType(from.getPreferredNotificationType());
		to.setPreferredEMailAddressType(from.getPreferredEMailAddressType());
		to.setPreferredPhoneNumberType(from.getPreferredPhoneNumberType());
	}
	
	public void copyState(SalesPerson from, SalesPerson to) {
		to.setPreferredNotificationType(from.getPreferredNotificationType());
		to.setPreferredEMailAddressType(from.getPreferredEMailAddressType());
		to.setPreferredPhoneNumberType(from.getPreferredPhoneNumberType());
		for ( RatePerTimeUnit child : from.getRatePerTimeUnit() ) {
			to.addToRatePerTimeUnit(child.makeCopy());
		}
	}
	
	public void createComponents() {
	}
	
	public RatePerTimeUnit createRatePerTimeUnit() {
		RatePerTimeUnit newInstance= new RatePerTimeUnit();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SalesPerson ) {
			return other==this || ((SalesPerson)other).getUid().equals(this.getUid());
		}
		return false;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2054055302346873506l,opposite="salesPerson",uuid="bpm.uml@_yTNg0Y_gEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_yTNg0Y_gEeK855GX2Z3x4Q")
	public MyBusiness getMyBusiness() {
		MyBusiness result = this.myBusiness;
		
		return result;
	}
	
	public String getName() {
		return "SalesPerson["+getId()+"]";
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
		Collection result = new ArrayList<TaskRequest>(collect5());
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getMyBusiness();
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
		List result = new ArrayList<RatePerTimeUnit>();
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=726238315624324931l,opposite="timedResource",uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_aCyDYPjxEeGEN6Fz86uBYA@252060@_dIQKEPjyEeGEN6Fz86uBYA")
	public List<TimedResourceRatePerTimeUnit> getTimedResourceRatePerTimeUnit_ratePerTimeUnit() {
		List result = this.timedResourceRatePerTimeUnit_ratePerTimeUnit;
		
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
		this.z_internalAddToMyBusiness((MyBusiness)owner);
		this.setPreferredEMailAddressType( PersonEMailAddressType.WORK );
		this.setPreferredPhoneNumberType( PersonPhoneNumberType.CELL );
		this.setPreferredNotificationType( NotificationType.EMAIL );
	}
	
	public SalesPerson makeCopy() {
		SalesPerson result = new SalesPerson();
		copyState((SalesPerson)this,result);
		return result;
	}
	
	public SalesPerson makeShallowCopy() {
		SalesPerson result = new SalesPerson();
		copyShallowState((SalesPerson)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( ParticipationParticipant link : new HashSet<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
			link.getParticipation().z_internalRemoveFromParticipationParticipant_participant(link);
		}
		if ( getRepresentedPerson()!=null ) {
			PersonInBusinessRole link = getPersonInBusinessRole_representedPerson();
			link.getRepresentedPerson().z_internalRemoveFromPersonInBusinessRole_businessRole(link);
			link.markDeleted();
		}
		if ( getMyBusiness()!=null ) {
			getMyBusiness().z_internalRemoveFromSalesPerson(this);
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
		for ( TimedResourceRatePerTimeUnit child : new ArrayList<TimedResourceRatePerTimeUnit>(getTimedResourceRatePerTimeUnit_ratePerTimeUnit()) ) {
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
						TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit = (TimedResourceRatePerTimeUnit)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( timedResourceRatePerTimeUnit_ratePerTimeUnit!=null ) {
							z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(timedResourceRatePerTimeUnit_ratePerTimeUnit);
							timedResourceRatePerTimeUnit_ratePerTimeUnit.z_internalAddToTimedResource(this);
						}
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
	
	public void removeAllFromRatePerTimeUnit(List<? extends RatePerTimeUnit> ratePerTimeUnit) {
		List<RatePerTimeUnit> tmp = new ArrayList<RatePerTimeUnit>(ratePerTimeUnit);
		for ( RatePerTimeUnit o : tmp ) {
			removeFromRatePerTimeUnit(o);
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
	
	public void removeFromRatePerTimeUnit(RatePerTimeUnit ratePerTimeUnit) {
		if ( ratePerTimeUnit!=null ) {
			TimedResourceRatePerTimeUnit oldLink = getTimedResourceRatePerTimeUnit_ratePerTimeUnitFor(ratePerTimeUnit);
			if ( oldLink!=null ) {
				ratePerTimeUnit.z_internalRemoveFromTimedResourceRatePerTimeUnit_timedResource(oldLink);
				oldLink.clear();
				z_internalRemoveFromTimedResourceRatePerTimeUnit_ratePerTimeUnit(oldLink);
			}
			ratePerTimeUnit.markDeleted();
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
	
	public void setMyBusiness(MyBusiness myBusiness) {
		MyBusiness oldValue = this.getMyBusiness();
		if ( oldValue==null ) {
			if ( myBusiness!=null ) {
				SalesPerson oldOther = (SalesPerson)myBusiness.getSalesPerson();
				myBusiness.z_internalRemoveFromSalesPerson(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromMyBusiness(myBusiness);
				}
				myBusiness.z_internalAddToSalesPerson((SalesPerson)this);
			}
			this.z_internalAddToMyBusiness(myBusiness);
		} else {
			if ( !oldValue.equals(myBusiness) ) {
				oldValue.z_internalRemoveFromSalesPerson(this);
				z_internalRemoveFromMyBusiness(oldValue);
				if ( myBusiness!=null ) {
					SalesPerson oldOther = (SalesPerson)myBusiness.getSalesPerson();
					myBusiness.z_internalRemoveFromSalesPerson(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromMyBusiness(myBusiness);
					}
					myBusiness.z_internalAddToSalesPerson((SalesPerson)this);
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
	
	public void setRatePerTimeUnit(List<RatePerTimeUnit> ratePerTimeUnit) {
		this.clearRatePerTimeUnit();
		this.addAllToRatePerTimeUnit(ratePerTimeUnit);
	}
	
	public void setRepresentedPerson(IPersonNode p) {
		setRepresentedPerson((PersonNode)p);
	}
	
	public void setRepresentedPerson(PersonNode representedPerson) {
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromPersonInBusinessRole_businessRole(getPersonInBusinessRole_representedPerson());
		}
		if ( representedPerson == null ) {
			this.z_internalRemoveFromPersonInBusinessRole_representedPerson(this.getPersonInBusinessRole_representedPerson());
		} else {
			PersonInBusinessRole personInBusinessRole_representedPerson = new PersonInBusinessRole((IBusinessRole)this,(PersonNode)representedPerson);
			this.z_internalAddToPersonInBusinessRole_representedPerson(personInBusinessRole_representedPerson);
			representedPerson.z_internalAddToPersonInBusinessRole_businessRole(personInBusinessRole_representedPerson);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<SalesPerson uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SalesPerson ");
		sb.append("classUuid=\"bpm.uml@_qPapgI_gEeK855GX2Z3x4Q\" ");
		sb.append("className=\"bpmmodel.SalesPerson\" ");
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
			sb.append("\n" + timedResourceRatePerTimeUnit_ratePerTimeUnit.toXmlString());
		}
		sb.append("\n</timedResourceRatePerTimeUnit_ratePerTimeUnit>");
		sb.append("\n</SalesPerson>");
		return sb.toString();
	}
	
	public void z_internalAddToMyBusiness(MyBusiness myBusiness) {
		if ( myBusiness.equals(this.myBusiness) ) {
			return;
		}
		this.myBusiness=myBusiness;
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		if ( getParticipationParticipant_participation().contains(participationParticipant_participation) ) {
			return;
		}
		this.participationParticipant_participation.add(participationParticipant_participation);
	}
	
	public void z_internalAddToPersonInBusinessRole_representedPerson(PersonInBusinessRole personInBusinessRole_representedPerson) {
		if ( personInBusinessRole_representedPerson.equals(getPersonInBusinessRole_representedPerson()) ) {
			return;
		}
		this.personInBusinessRole_representedPerson=personInBusinessRole_representedPerson;
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
	
	public void z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_ratePerTimeUnit) {
		if ( getTimedResourceRatePerTimeUnit_ratePerTimeUnit().contains(timedResourceRatePerTimeUnit_ratePerTimeUnit) ) {
			return;
		}
		this.timedResourceRatePerTimeUnit_ratePerTimeUnit.add(timedResourceRatePerTimeUnit_ratePerTimeUnit);
	}
	
	public void z_internalRemoveFromMyBusiness(MyBusiness myBusiness) {
		if ( getMyBusiness()!=null && myBusiness!=null && myBusiness.equals(getMyBusiness()) ) {
			this.myBusiness=null;
			this.myBusiness=null;
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