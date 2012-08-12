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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.organization.IBusinessComponent;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.Organization_iBusinessComponent_1;
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
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_0l-NAJJNEeGW4L5IejZxpA")
@BusinessComponent(businessRoles={CustomerAssistant.class,Technician.class,CustomerAssistant.class,Technician.class})
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="branch")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Branch")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Branch implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Type(type="structuredbusiness.CityResolver")
	@Column(name="city",nullable=true)
	private City city;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="branch",targetEntity=CustomerAssistant.class)
	private Set<CustomerAssistant> customerAssistant = new HashSet<CustomerAssistant>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="dishwashers_inc_id",name="idx_branch_dishwashers_inc_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dishwashers_inc_id",nullable=true)
	private ApplianceDoctor dishwashersInc;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="branch",targetEntity=Job.class)
	private Set<Job> job = new HashSet<Job>();
	static private Set<Branch> mockedAllInstances;
	@Column(name="name")
	private String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_i_business_component_1_represented_organization_id",nullable=true)
	private Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_0l-NAJJNEeGW4L5IejZxpA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participation_participant_participation_id",nullable=true)
	private Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 128937784137359716l;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="branch")
	private Technician technician;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Branch(ApplianceDoctor owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Branch
	 */
	public Branch() {
	}

	@NumlMetaInfo(uuid="914890@_dOodgMzBEeGKe7Qm4dvydQ")
	public void Operation1(@ParameterMetaInfo(name="sdfgd",opaeumId=4983800839352232442l,uuid="914890@_k_VZsMzBEeGKe7Qm4dvydQ") String sdfgd) {
		generateOperation1Event(sdfgd);
	}
	
	public void addAllToCustomerAssistant(Set<CustomerAssistant> customerAssistant) {
		for ( CustomerAssistant o : customerAssistant ) {
			addToCustomerAssistant(o);
		}
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
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToCustomerAssistant(CustomerAssistant customerAssistant) {
		if ( customerAssistant!=null ) {
			customerAssistant.z_internalRemoveFromBranch(customerAssistant.getBranch());
			customerAssistant.z_internalAddToBranch(this);
			z_internalAddToCustomerAssistant(customerAssistant);
		}
	}
	
	public void addToJob(Job job) {
		if ( job!=null ) {
			job.z_internalRemoveFromBranch(job.getBranch());
			job.z_internalAddToBranch(this);
			z_internalAddToJob(job);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getDishwashersInc().z_internalAddToBranch((Branch)this);
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
	
	static public Set<? extends Branch> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(structuredbusiness.Branch.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("city").length()>0 ) {
			setCity(City.valueOf(xml.getAttribute("city")));
		}
		if ( xml.getAttribute("name").length()>0 ) {
			setName(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("city").length()>0 ) {
			setCity(City.valueOf(xml.getAttribute("city")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customerAssistant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("758991831684440668")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						CustomerAssistant curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToCustomerAssistant(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("technician") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6994431291756453463")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Technician curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setTechnician(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("job") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9026526080661167087")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Job curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToJob(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customerAssistant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("758991831684440668")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						CustomerAssistant curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToCustomerAssistant(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("technician") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6994431291756453463")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Technician curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setTechnician(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("job") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9026526080661167087")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Job curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToJob(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3245714109628633948")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Organization_iBusinessComponent_1 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setOrganization_iBusinessComponent_1_representedOrganization(curVal);
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
	
	public void clearCustomerAssistant() {
		removeAllFromCustomerAssistant(getCustomerAssistant());
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
	
	public boolean consumeOperation1Occurrence(@ParameterMetaInfo(name="sdfgd",opaeumId=4983800839352232442l,uuid="914890@_k_VZsMzBEeGKe7Qm4dvydQ") String sdfgd) {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(Branch from, Branch to) {
		to.setCity(from.getCity());
		if ( from.getTechnician()!=null ) {
			to.setTechnician(from.getTechnician().makeShallowCopy());
		}
		to.setName(from.getName());
		to.setCity(from.getCity());
		if ( from.getTechnician()!=null ) {
			to.setTechnician(from.getTechnician().makeShallowCopy());
		}
	}
	
	public void copyState(Branch from, Branch to) {
		for ( CustomerAssistant child : from.getCustomerAssistant() ) {
			to.addToCustomerAssistant(child.makeCopy());
		}
		to.setCity(from.getCity());
		if ( from.getTechnician()!=null ) {
			to.setTechnician(from.getTechnician().makeCopy());
		}
		for ( Job child : from.getJob() ) {
			to.addToJob(child.makeCopy());
		}
		to.setName(from.getName());
		for ( CustomerAssistant child : from.getCustomerAssistant() ) {
			to.addToCustomerAssistant(child.makeCopy());
		}
		to.setCity(from.getCity());
		if ( from.getTechnician()!=null ) {
			to.setTechnician(from.getTechnician().makeCopy());
		}
		for ( Job child : from.getJob() ) {
			to.addToJob(child.makeCopy());
		}
	}
	
	public void createComponents() {
		if ( getTechnician()==null ) {
			setTechnician(new Technician());
		}
	}
	
	public CustomerAssistant createCustomerAssistant() {
		CustomerAssistant newInstance= new CustomerAssistant();
		newInstance.init(this);
		return newInstance;
	}
	
	public Job createJob() {
		Job newInstance= new Job();
		newInstance.init(this);
		return newInstance;
	}
	
	public Organization_iBusinessComponent_1 createOrganization_iBusinessComponent_1_representedOrganization() {
		Organization_iBusinessComponent_1 newInstance= new Organization_iBusinessComponent_1();
		newInstance.init(this);
		return newInstance;
	}
	
	public ParticipationParticipant createParticipationParticipant_participation() {
		ParticipationParticipant newInstance= new ParticipationParticipant();
		newInstance.init(this);
		return newInstance;
	}
	
	public Technician createTechnician() {
		Technician newInstance= new Technician();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Branch ) {
			return other==this || ((Branch)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateOperation1Event(@ParameterMetaInfo(name="sdfgd",opaeumId=4983800839352232442l,uuid="914890@_k_VZsMzBEeGKe7Qm4dvydQ") String sdfgd) {
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4663136941357255578l,opposite="branch",uuid="914890@_59AMMJKGEeGFkOm2e1MJNQ")
	@NumlMetaInfo(uuid="914890@_59AMMJKGEeGFkOm2e1MJNQ")
	public City getCity() {
		City result = this.city;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=758991831684440668l,opposite="branch",uuid="914890@_pNEJIJJPEeGW4L5IejZxpA")
	@NumlMetaInfo(uuid="914890@_pNEJIJJPEeGW4L5IejZxpA")
	public Set<CustomerAssistant> getCustomerAssistant() {
		Set<CustomerAssistant> result = this.customerAssistant;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=813509692033034648l,opposite="branch",uuid="914890@_-IRCoZJNEeGW4L5IejZxpA")
	@NumlMetaInfo(uuid="914890@_-IRCoZJNEeGW4L5IejZxpA")
	public ApplianceDoctor getDishwashersInc() {
		ApplianceDoctor result = this.dishwashersInc;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=9026526080661167087l,opposite="branch",uuid="914890@_QcN0UJLAEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_QcN0UJLAEeGnpuq6_ber_Q")
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8433604321489463449l,uuid="914890@_ea2-cJT6EeG_oLv6jKpgkw")
	@NumlMetaInfo(uuid="914890@_ea2-cJT6EeG_oLv6jKpgkw")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3245714109628633948l,opposite="businessComponent",uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_uVek8IoVEeCLqpffVZYAlw@252060@_vf4noFYuEeGj5_I7bIwNoA")
	public Organization_iBusinessComponent_1 getOrganization_iBusinessComponent_1_representedOrganization() {
		Organization_iBusinessComponent_1 result = this.organization_iBusinessComponent_1_representedOrganization;
		
		return result;
	}
	
	public Organization_iBusinessComponent_1 getOrganization_iBusinessComponent_1_representedOrganizationFor(OrganizationNode match) {
		if ( organization_iBusinessComponent_1_representedOrganization.getRepresentedOrganization().equals(match) ) {
			return organization_iBusinessComponent_1_representedOrganization;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8314504260854280851l,opposite="businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	public OrganizationNode getRepresentedOrganization() {
		OrganizationNode result = null;
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			result = this.organization_iBusinessComponent_1_representedOrganization.getRepresentedOrganization();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6994431291756453463l,opposite="branch",uuid="914890@_JaIUoJKfEeGiJMBDeZRymA")
	@NumlMetaInfo(uuid="914890@_JaIUoJKfEeGiJMBDeZRymA")
	public Technician getTechnician() {
		Technician result = this.technician;
		
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
		this.z_internalAddToDishwashersInc((ApplianceDoctor)owner);
		createComponents();
		getTechnician().init(this);
	}
	
	public Branch makeCopy() {
		Branch result = new Branch();
		copyState((Branch)this,result);
		return result;
	}
	
	public Branch makeShallowCopy() {
		Branch result = new Branch();
		copyShallowState((Branch)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getDishwashersInc()!=null ) {
			getDishwashersInc().z_internalRemoveFromBranch(this);
		}
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		for ( CustomerAssistant child : new ArrayList<CustomerAssistant>(getCustomerAssistant()) ) {
			child.markDeleted();
		}
		if ( getTechnician()!=null ) {
			getTechnician().markDeleted();
		}
		for ( Job child : new ArrayList<Job>(getJob()) ) {
			child.markDeleted();
		}
		for ( CustomerAssistant child : new ArrayList<CustomerAssistant>(getCustomerAssistant()) ) {
			child.markDeleted();
		}
		if ( getTechnician()!=null ) {
			getTechnician().markDeleted();
		}
		for ( Job child : new ArrayList<Job>(getJob()) ) {
			child.markDeleted();
		}
		if ( getOrganization_iBusinessComponent_1_representedOrganization()!=null ) {
			getOrganization_iBusinessComponent_1_representedOrganization().markDeleted();
		}
		for ( ParticipationParticipant child : new ArrayList<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Branch> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customerAssistant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("758991831684440668")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((CustomerAssistant)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("technician") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6994431291756453463")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Technician)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("job") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9026526080661167087")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Job)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customerAssistant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("758991831684440668")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((CustomerAssistant)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("technician") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6994431291756453463")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Technician)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("job") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9026526080661167087")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Job)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3245714109628633948")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Organization_iBusinessComponent_1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void removeAllFromCustomerAssistant(Set<CustomerAssistant> customerAssistant) {
		Set<CustomerAssistant> tmp = new HashSet<CustomerAssistant>(customerAssistant);
		for ( CustomerAssistant o : tmp ) {
			removeFromCustomerAssistant(o);
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
	
	public void removeFromCustomerAssistant(CustomerAssistant customerAssistant) {
		if ( customerAssistant!=null ) {
			customerAssistant.z_internalRemoveFromBranch(this);
			z_internalRemoveFromCustomerAssistant(customerAssistant);
		}
	}
	
	public void removeFromJob(Job job) {
		if ( job!=null ) {
			job.z_internalRemoveFromBranch(this);
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
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCity(City city) {
		propertyChangeSupport.firePropertyChange("city",getCity(),city);
		this.z_internalAddToCity(city);
	}
	
	public void setCustomerAssistant(Set<CustomerAssistant> customerAssistant) {
		propertyChangeSupport.firePropertyChange("customerAssistant",getCustomerAssistant(),customerAssistant);
		this.clearCustomerAssistant();
		this.addAllToCustomerAssistant(customerAssistant);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishwashersInc(ApplianceDoctor dishwashersInc) {
		propertyChangeSupport.firePropertyChange("dishwashersInc",getDishwashersInc(),dishwashersInc);
		if ( this.getDishwashersInc()!=null ) {
			this.getDishwashersInc().z_internalRemoveFromBranch(this);
		}
		if ( dishwashersInc!=null ) {
			dishwashersInc.z_internalAddToBranch(this);
			this.z_internalAddToDishwashersInc(dishwashersInc);
		}
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setJob(Set<Job> job) {
		propertyChangeSupport.firePropertyChange("job",getJob(),job);
		this.clearJob();
		this.addAllToJob(job);
	}
	
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name",getName(),name);
		this.z_internalAddToName(name);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization) {
		Organization_iBusinessComponent_1 oldValue = this.getOrganization_iBusinessComponent_1_representedOrganization();
		propertyChangeSupport.firePropertyChange("organization_iBusinessComponent_1_representedOrganization",getOrganization_iBusinessComponent_1_representedOrganization(),organization_iBusinessComponent_1_representedOrganization);
		if ( oldValue==null ) {
			if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
				Branch oldOther = (Branch)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
				organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
				}
				organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((Branch)this);
			}
			this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
		} else {
			if ( !oldValue.equals(organization_iBusinessComponent_1_representedOrganization) ) {
				oldValue.z_internalRemoveFromBusinessComponent(this);
				z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(oldValue);
				if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
					Branch oldOther = (Branch)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
					organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
					}
					organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((Branch)this);
				}
				this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
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
	
	public void setRepresentedOrganization(IOrganizationNode p) {
		setRepresentedOrganization((OrganizationNode)p);
	}
	
	public void setRepresentedOrganization(OrganizationNode representedOrganization) {
		propertyChangeSupport.firePropertyChange("representedOrganization",getRepresentedOrganization(),representedOrganization);
		if ( this.getRepresentedOrganization()!=null ) {
			this.getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		if ( representedOrganization!=null ) {
			this.z_internalAddToRepresentedOrganization(representedOrganization);
		}
	}
	
	public void setTechnician(Technician technician) {
		Technician oldValue = this.getTechnician();
		propertyChangeSupport.firePropertyChange("technician",getTechnician(),technician);
		if ( oldValue==null ) {
			if ( technician!=null ) {
				Branch oldOther = (Branch)technician.getBranch();
				technician.z_internalRemoveFromBranch(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromTechnician(technician);
				}
				technician.z_internalAddToBranch((Branch)this);
			}
			this.z_internalAddToTechnician(technician);
		} else {
			if ( !oldValue.equals(technician) ) {
				oldValue.z_internalRemoveFromBranch(this);
				z_internalRemoveFromTechnician(oldValue);
				if ( technician!=null ) {
					Branch oldOther = (Branch)technician.getBranch();
					technician.z_internalRemoveFromBranch(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromTechnician(technician);
					}
					technician.z_internalAddToBranch((Branch)this);
				}
				this.z_internalAddToTechnician(technician);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Branch uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Branch ");
		sb.append("classUuid=\"914890@_0l-NAJJNEeGW4L5IejZxpA\" ");
		sb.append("className=\"structuredbusiness.Branch\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getCity()!=null ) {
			sb.append("city=\""+ getCity().name() + "\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getCity()!=null ) {
			sb.append("city=\""+ getCity().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n<customerAssistant propertyId=\"758991831684440668\">");
		for ( CustomerAssistant customerAssistant : getCustomerAssistant() ) {
			sb.append("\n" + customerAssistant.toXmlString());
		}
		sb.append("\n</customerAssistant>");
		if ( getTechnician()==null ) {
			sb.append("\n<technician/>");
		} else {
			sb.append("\n<technician propertyId=\"6994431291756453463\">");
			sb.append("\n" + getTechnician().toXmlString());
			sb.append("\n</technician>");
		}
		sb.append("\n<job propertyId=\"9026526080661167087\">");
		for ( Job job : getJob() ) {
			sb.append("\n" + job.toXmlString());
		}
		sb.append("\n</job>");
		sb.append("\n<customerAssistant propertyId=\"758991831684440668\">");
		for ( CustomerAssistant customerAssistant : getCustomerAssistant() ) {
			sb.append("\n" + customerAssistant.toXmlString());
		}
		sb.append("\n</customerAssistant>");
		if ( getTechnician()==null ) {
			sb.append("\n<technician/>");
		} else {
			sb.append("\n<technician propertyId=\"6994431291756453463\">");
			sb.append("\n" + getTechnician().toXmlString());
			sb.append("\n</technician>");
		}
		sb.append("\n<job propertyId=\"9026526080661167087\">");
		for ( Job job : getJob() ) {
			sb.append("\n" + job.toXmlString());
		}
		sb.append("\n</job>");
		if ( getOrganization_iBusinessComponent_1_representedOrganization()==null ) {
			sb.append("\n<organization_iBusinessComponent_1_representedOrganization/>");
		} else {
			sb.append("\n<organization_iBusinessComponent_1_representedOrganization propertyId=\"3245714109628633948\">");
			sb.append("\n" + getOrganization_iBusinessComponent_1_representedOrganization().toXmlString());
			sb.append("\n</organization_iBusinessComponent_1_representedOrganization>");
		}
		sb.append("\n<participationParticipant_participation propertyId=\"5579540379306504838\">");
		for ( ParticipationParticipant participationParticipant_participation : getParticipationParticipant_participation() ) {
			sb.append("\n" + participationParticipant_participation.toXmlString());
		}
		sb.append("\n</participationParticipant_participation>");
		sb.append("\n</Branch>");
		return sb.toString();
	}
	
	public void z_internalAddToCity(City val) {
		this.city=val;
	}
	
	public void z_internalAddToCustomerAssistant(CustomerAssistant val) {
		this.customerAssistant.add(val);
	}
	
	public void z_internalAddToDishwashersInc(ApplianceDoctor val) {
		this.dishwashersInc=val;
	}
	
	public void z_internalAddToJob(Job val) {
		this.job.add(val);
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
	}
	
	public void z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val) {
		this.organization_iBusinessComponent_1_representedOrganization=val;
	}
	
	public void z_internalAddToParticipation(Participation participation) {
		ParticipationParticipant newOne = new ParticipationParticipant(this,participation);
		this.z_internalAddToParticipationParticipant_participation(newOne);
		newOne.getParticipation().z_internalAddToParticipationParticipant_participant(newOne);
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant val) {
		this.participationParticipant_participation.add(val);
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode representedOrganization) {
		Organization_iBusinessComponent_1 newOne = new Organization_iBusinessComponent_1(this,representedOrganization);
		this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(newOne);
		newOne.getRepresentedOrganization().z_internalAddToOrganization_iBusinessComponent_1_businessComponent(newOne);
	}
	
	public void z_internalAddToTechnician(Technician val) {
		this.technician=val;
	}
	
	public void z_internalRemoveFromCity(City val) {
		if ( getCity()!=null && val!=null && val.equals(getCity()) ) {
			this.city=null;
			this.city=null;
		}
	}
	
	public void z_internalRemoveFromCustomerAssistant(CustomerAssistant val) {
		this.customerAssistant.remove(val);
	}
	
	public void z_internalRemoveFromDishwashersInc(ApplianceDoctor val) {
		if ( getDishwashersInc()!=null && val!=null && val.equals(getDishwashersInc()) ) {
			this.dishwashersInc=null;
			this.dishwashersInc=null;
		}
	}
	
	public void z_internalRemoveFromJob(Job val) {
		this.job.remove(val);
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val) {
		if ( getOrganization_iBusinessComponent_1_representedOrganization()!=null && val!=null && val.equals(getOrganization_iBusinessComponent_1_representedOrganization()) ) {
			this.organization_iBusinessComponent_1_representedOrganization=null;
			this.organization_iBusinessComponent_1_representedOrganization=null;
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
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			this.organization_iBusinessComponent_1_representedOrganization.clear();
		}
	}
	
	public void z_internalRemoveFromTechnician(Technician val) {
		if ( getTechnician()!=null && val!=null && val.equals(getTechnician()) ) {
			this.technician=null;
			this.technician=null;
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