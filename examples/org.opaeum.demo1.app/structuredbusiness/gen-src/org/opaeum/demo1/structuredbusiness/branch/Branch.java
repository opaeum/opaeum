package org.opaeum.demo1.structuredbusiness.branch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.ApplianceDoctor;
import org.opaeum.demo1.structuredbusiness.appliance.ProductAnnouncement;
import org.opaeum.demo1.structuredbusiness.appliance.ProductAnnouncementHandler;
import org.opaeum.demo1.structuredbusiness.appliance.ProductAnnouncementReceiver;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.StandaloneSingleScreenTask1;
import org.opaeum.demo1.structuredbusiness.jobs.Activity;
import org.opaeum.demo1.structuredbusiness.jobs.Job;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusiness;
import org.opaeum.runtime.bpm.organization.IBusinessComponent;
import org.opaeum.runtime.bpm.organization.OrganizationAsBusinessComponent;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.Participation;
import org.opaeum.runtime.bpm.request.ParticipationInRequest;
import org.opaeum.runtime.bpm.request.ParticipationInTask;
import org.opaeum.runtime.bpm.request.ParticipationParticipant;
import org.opaeum.runtime.bpm.request.RequestParticipationKind;
import org.opaeum.runtime.bpm.request.TaskRequest;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.FailedConstraint;
import org.opaeum.runtime.domain.FailedConstraintsException;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_0l-NAJJNEeGW4L5IejZxpA")
@BusinessComponent(businessRoles={CustomerAssistant.class,Technician.class})
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="branch",schema="structuredbusiness")
@Entity(name="Branch")
public class Branch implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, ProductAnnouncementReceiver, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Type(type="org.opaeum.demo1.structuredbusiness.branch.CityResolver")
	@Column(name="city",nullable=true)
	protected City city;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="branch",targetEntity=CustomerAssistant.class)
	protected Set<CustomerAssistant> customerAssistant = new HashSet<CustomerAssistant>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="dishwashers_inc_id",name="idx_branch_dishwashers_inc_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="dishwashers_inc_id",nullable=true)
	protected ApplianceDoctor dishwashersInc;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="branch",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="branch",targetEntity=Job.class)
	protected Set<Job> job = new HashSet<Job>();
	static private Set<? extends Branch> mockedAllInstances;
	@Column(name="name")
	@Basic
	protected String name;
	@Column(name="num")
	@Basic
	protected Double numberOfOpenPositions;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_as_business_component_represented_organization_id",nullable=true)
	protected OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_0l-NAJJNEeGW4L5IejZxpA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participant",nullable=true)
	protected Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private InternalHibernatePersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@IndexColumn(name="idx_in_pre_quo_on_branch")
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=PrepareQuote.class)
	@JoinColumn(name="context_object_id",nullable=true)
	protected List<PrepareQuote> prepareQuote = new ArrayList<PrepareQuote>();
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 128937784137359716l;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@IndexColumn(name="idx_in_s_s_s_t_on_branch")
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=StandaloneSingleScreenTask1.class)
	@JoinColumn(name="context_object_id",nullable=true)
	protected List<StandaloneSingleScreenTask1> standaloneSingleScreenTask1 = new ArrayList<StandaloneSingleScreenTask1>();
	@Column(name="surname")
	@Basic
	protected String surname;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="branch",targetEntity=Technician.class)
	protected Set<Technician> technician = new HashSet<Technician>();
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
	public ApplianceDoctor Operation1(@ParameterMetaInfo(name="parameter1ll",opaeumId=7842728324345019779l,uuid="914890@_bNhwQBRxEeKYhv9CKiNDbg") IBusiness parameter1ll) throws FailedConstraintsException {
		ApplianceDoctor result = null;
		Set<FailedConstraint> failedConstraints = new HashSet<FailedConstraint>();
		if ( !collectionLiteral12(parameter1ll).isEmpty() ) {
			String message = org.opaeum.demo1.util.Demo1Environment.INSTANCE.getMessage("structuredbusiness::branch::Branch::Operation1::newConstraint4" );
			failedConstraints.add(new FailedConstraint(null, message));
		}
		if ( !(parameter1ll == null) ) {
			String message = org.opaeum.demo1.util.Demo1Environment.INSTANCE.getMessage("structuredbusiness::branch::Branch::Operation1::newConstraint" );
			failedConstraints.add(new FailedConstraint("parameter1ll" ,message));
		}
		if ( failedConstraints.size()>0 ) {
			throw new FailedConstraintsException(true,failedConstraints);
		}
		if ( !((sum15(parameter1ll, failedConstraints)) > (Double)2.3) ) {
			String message = org.opaeum.demo1.util.Demo1Environment.INSTANCE.getMessage("structuredbusiness::branch::Branch::Operation1::newConstraint1" );
			failedConstraints.add(new FailedConstraint("sdfgdkkfsdfs" ,message));
		}
		if ( failedConstraints.size()>0 ) {
			throw new FailedConstraintsException(false,failedConstraints);
		}
		return result;
	}
	
	@NumlMetaInfo(uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
	public PrepareQuote PrepareQuote(IToken returnInfo, @ParameterMetaInfo(name="parameter1",opaeumId=1724672243889101143l,uuid="914890@__oyQoBYXEeKsDbmQL25eBw") Integer parameter1) {
		PrepareQuote result = new PrepareQuote(this);
		result.setParameter1(parameter1);
		result.setReturnInfo(returnInfo);
		return result;
	}
	
	@NumlMetaInfo(uuid="914890@_ylMisBYQEeKIFJAOfPz88A")
	public StandaloneSingleScreenTask1 StandaloneSingleScreenTask1(IToken returnInfo) {
		StandaloneSingleScreenTask1 result = new StandaloneSingleScreenTask1(this);
		result.setRequest(new TaskRequest());
		((TaskRequest)result.getRequest()).setPotentialOwners(Arrays.asList(this));
		result.setReturnInfo(returnInfo);
		return result;
	}
	
	static public StandaloneSingleScreenTask1 StandaloneSingleScreenTask1WithMultiplePotentialOwners(IToken returnInfo) {
		StandaloneSingleScreenTask1 result = new StandaloneSingleScreenTask1();
		((TaskRequest)result.getRequest()).setPotentialOwners(org.opaeum.demo1.util.Demo1Environment.INSTANCE.getCurrentPersistence().readAll(Branch.class));
		result.setRequest(new TaskRequest());
		org.opaeum.demo1.util.Demo1Environment.INSTANCE.getCurrentPersistence().persist(result);
		return result;
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
	
	public void addAllToPrepareQuote(List<PrepareQuote> prepareQuote) {
		for ( PrepareQuote o : prepareQuote ) {
			addToPrepareQuote(o);
		}
	}
	
	public void addAllToStandaloneSingleScreenTask1(List<StandaloneSingleScreenTask1> standaloneSingleScreenTask1) {
		for ( StandaloneSingleScreenTask1 o : standaloneSingleScreenTask1 ) {
			addToStandaloneSingleScreenTask1(o);
		}
	}
	
	public void addAllToTechnician(Set<Technician> technician) {
		for ( Technician o : technician ) {
			addToTechnician(o);
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
	
	public void addToPrepareQuote(PrepareQuote prepareQuote) {
		if ( prepareQuote!=null ) {
			prepareQuote.z_internalRemoveFromContextObject(prepareQuote.getContextObject());
			prepareQuote.z_internalAddToContextObject(this);
			z_internalAddToPrepareQuote(prepareQuote);
		}
	}
	
	public void addToStandaloneSingleScreenTask1(StandaloneSingleScreenTask1 standaloneSingleScreenTask1) {
		if ( standaloneSingleScreenTask1!=null ) {
			standaloneSingleScreenTask1.z_internalRemoveFromContextObject(standaloneSingleScreenTask1.getContextObject());
			standaloneSingleScreenTask1.z_internalAddToContextObject(this);
			z_internalAddToStandaloneSingleScreenTask1(standaloneSingleScreenTask1);
		}
	}
	
	public void addToTechnician(Technician technician) {
		if ( technician!=null ) {
			technician.z_internalRemoveFromBranch(technician.getBranch());
			technician.z_internalAddToBranch(this);
			z_internalAddToTechnician(technician);
		}
	}
	
	static public Set<? extends Branch> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.branch.Branch.class));
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
		if ( xml.getAttribute("surname").length()>0 ) {
			setSurname(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("surname")));
		}
		if ( xml.getAttribute("numberOfOpenPositions").length()>0 ) {
			setNumberOfOpenPositions(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("numberOfOpenPositions")));
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
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToTechnician(curVal);
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
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToJob(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("standaloneSingleScreenTask1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("163026363185791958")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						StandaloneSingleScreenTask1 curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToStandaloneSingleScreenTask1(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("prepareQuote") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4021326984444374137")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PrepareQuote curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToPrepareQuote(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationAsBusinessComponent_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3245714109628633948")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OrganizationAsBusinessComponent curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setOrganizationAsBusinessComponent_representedOrganization(curVal);
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
	
	public void clearCustomerAssistant() {
		Set<CustomerAssistant> tmp = new HashSet<CustomerAssistant>(getCustomerAssistant());
		for ( CustomerAssistant o : tmp ) {
			removeFromCustomerAssistant(o);
		}
	}
	
	public void clearJob() {
		Set<Job> tmp = new HashSet<Job>(getJob());
		for ( Job o : tmp ) {
			removeFromJob(o);
		}
	}
	
	public void clearParticipation() {
		Set<Participation> tmp = new HashSet<Participation>(getParticipation());
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void clearParticipationParticipant_participation() {
		Set<ParticipationParticipant> tmp = new HashSet<ParticipationParticipant>(getParticipationParticipant_participation());
		for ( ParticipationParticipant o : tmp ) {
			removeFromParticipationParticipant_participation(o);
		}
	}
	
	public void clearPrepareQuote() {
		List<PrepareQuote> tmp = new ArrayList<PrepareQuote>(getPrepareQuote());
		for ( PrepareQuote o : tmp ) {
			removeFromPrepareQuote(o);
		}
	}
	
	public void clearStandaloneSingleScreenTask1() {
		List<StandaloneSingleScreenTask1> tmp = new ArrayList<StandaloneSingleScreenTask1>(getStandaloneSingleScreenTask1());
		for ( StandaloneSingleScreenTask1 o : tmp ) {
			removeFromStandaloneSingleScreenTask1(o);
		}
	}
	
	public void clearTechnician() {
		Set<Technician> tmp = new HashSet<Technician>(getTechnician());
		for ( Technician o : tmp ) {
			removeFromTechnician(o);
		}
	}
	
	public boolean consumeProductAnnouncementEvent(ProductAnnouncement signal) {
		boolean result = false;
		if ( !result ) {
			for ( PrepareQuote behavior : getPrepareQuote() ) {
				result=behavior.consumeProductAnnouncementEvent(signal);
				if ( result ) {
					break;
				}
			}
		}
		return result;
	}
	
	public void copyShallowState(Branch from, Branch to) {
		to.setCity(from.getCity());
		to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setNumberOfOpenPositions(from.getNumberOfOpenPositions());
	}
	
	public void copyState(Branch from, Branch to) {
		for ( CustomerAssistant child : from.getCustomerAssistant() ) {
			to.addToCustomerAssistant(child.makeCopy());
		}
		to.setCity(from.getCity());
		for ( Technician child : from.getTechnician() ) {
			to.addToTechnician(child.makeCopy());
		}
		for ( Job child : from.getJob() ) {
			to.addToJob(child.makeCopy());
		}
		to.setName(from.getName());
		to.setSurname(from.getSurname());
		to.setNumberOfOpenPositions(from.getNumberOfOpenPositions());
		for ( StandaloneSingleScreenTask1 child : from.getStandaloneSingleScreenTask1() ) {
			to.addToStandaloneSingleScreenTask1(child.makeCopy());
		}
		for ( PrepareQuote child : from.getPrepareQuote() ) {
			to.addToPrepareQuote(child.makeCopy());
		}
	}
	
	public void createComponents() {
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
	
	public OrganizationAsBusinessComponent createOrganizationAsBusinessComponent_representedOrganization() {
		OrganizationAsBusinessComponent newInstance= new OrganizationAsBusinessComponent();
		newInstance.init(this);
		return newInstance;
	}
	
	public ParticipationParticipant createParticipationParticipant_participation() {
		ParticipationParticipant newInstance= new ParticipationParticipant();
		newInstance.init(this);
		return newInstance;
	}
	
	public PrepareQuote createPrepareQuote() {
		PrepareQuote newInstance= new PrepareQuote();
		newInstance.init(this);
		return newInstance;
	}
	
	public StandaloneSingleScreenTask1 createStandaloneSingleScreenTask1() {
		StandaloneSingleScreenTask1 newInstance= new StandaloneSingleScreenTask1();
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
	
	public void generateProductAnnouncementEvent(ProductAnnouncement signal) {
		this.getOutgoingEvents().add(new OutgoingEvent(this, new ProductAnnouncementHandler(signal,true)));
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
		Set result = this.customerAssistant;
		
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
		Collection result = new ArrayList<AbstractRequest>(collect11());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5635486542671558270l,uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_7MraII6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getInterestingRequests() {
		Collection result = new ArrayList<AbstractRequest>(collect2());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=9026526080661167087l,opposite="branch",uuid="914890@_QcN0UJLAEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_QcN0UJLAEeGnpuq6_ber_Q")
	public Set<Job> getJob() {
		Set result = this.job;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5447021495172291044l,uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	@NumlMetaInfo(uuid="252060@_jSstQI6lEeCFsPOcAnk69Q")
	public Collection<AbstractRequest> getManagedRequests() {
		Collection result = new ArrayList<AbstractRequest>(collect9());
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8433604321489463449l,uuid="914890@_ea2-cJT6EeG_oLv6jKpgkw")
	@NumlMetaInfo(uuid="914890@_ea2-cJT6EeG_oLv6jKpgkw")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4004087228002481042l,uuid="914890@_P2OvAHgVEeKYp4vzVCSoPA")
	@NumlMetaInfo(uuid="914890@_P2OvAHgVEeKYp4vzVCSoPA")
	public Double getNumberOfOpenPositions() {
		Double result = this.numberOfOpenPositions;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3245714109628633948l,opposite="businessComponent",uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_uVek8IoVEeCLqpffVZYAlw@252060@_vf4noFYuEeGj5_I7bIwNoA")
	public OrganizationAsBusinessComponent getOrganizationAsBusinessComponent_representedOrganization() {
		OrganizationAsBusinessComponent result = this.organizationAsBusinessComponent_representedOrganization;
		
		return result;
	}
	
	public OrganizationAsBusinessComponent getOrganizationAsBusinessComponent_representedOrganizationFor(OrganizationNode match) {
		if ( organizationAsBusinessComponent_representedOrganization.getRepresentedOrganization().equals(match) ) {
			return organizationAsBusinessComponent_representedOrganization;
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
		return getDishwashersInc();
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4021326984444374137l,opposite="contextObject",uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
	public List<PrepareQuote> getPrepareQuote() {
		List result = this.prepareQuote;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8314504260854280851l,opposite="businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	public OrganizationNode getRepresentedOrganization() {
		OrganizationNode result = null;
		if ( this.organizationAsBusinessComponent_representedOrganization!=null ) {
			result = this.organizationAsBusinessComponent_representedOrganization.getRepresentedOrganization();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=163026363185791958l,opposite="contextObject",uuid="914890@_ylMisBYQEeKIFJAOfPz88A")
	public List<StandaloneSingleScreenTask1> getStandaloneSingleScreenTask1() {
		List result = this.standaloneSingleScreenTask1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4331372641373749241l,uuid="914890@_AgMuwBUxEeKjqLZFOgkVYQ")
	@NumlMetaInfo(uuid="914890@_AgMuwBUxEeKjqLZFOgkVYQ")
	public String getSurname() {
		String result = this.surname;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6994431291756453463l,opposite="branch",shortDescription="",uuid="914890@_JaIUoJKfEeGiJMBDeZRymA")
	@NumlMetaInfo(uuid="914890@_JaIUoJKfEeGiJMBDeZRymA")
	public Set<Technician> getTechnician() {
		Set result = this.technician;
		
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		if ( getDishwashersInc()!=null ) {
			getDishwashersInc().z_internalRemoveFromBranch(this);
		}
		for ( CustomerAssistant child : new ArrayList<CustomerAssistant>(getCustomerAssistant()) ) {
			child.markDeleted();
		}
		for ( Technician child : new ArrayList<Technician>(getTechnician()) ) {
			child.markDeleted();
		}
		for ( Job child : new ArrayList<Job>(getJob()) ) {
			child.markDeleted();
		}
		for ( StandaloneSingleScreenTask1 child : new ArrayList<StandaloneSingleScreenTask1>(getStandaloneSingleScreenTask1()) ) {
			child.markDeleted();
		}
		for ( PrepareQuote child : new ArrayList<PrepareQuote>(getPrepareQuote()) ) {
			child.markDeleted();
		}
		if ( getOrganizationAsBusinessComponent_representedOrganization()!=null ) {
			getOrganizationAsBusinessComponent_representedOrganization().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("standaloneSingleScreenTask1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("163026363185791958")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((StandaloneSingleScreenTask1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("prepareQuote") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4021326984444374137")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PrepareQuote)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organizationAsBusinessComponent_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3245714109628633948")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OrganizationAsBusinessComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void receiveProductAnnouncement(ProductAnnouncement signal) {
		generateProductAnnouncementEvent(signal);
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
	
	public void removeAllFromPrepareQuote(List<PrepareQuote> prepareQuote) {
		List<PrepareQuote> tmp = new ArrayList<PrepareQuote>(prepareQuote);
		for ( PrepareQuote o : tmp ) {
			removeFromPrepareQuote(o);
		}
	}
	
	public void removeAllFromStandaloneSingleScreenTask1(List<StandaloneSingleScreenTask1> standaloneSingleScreenTask1) {
		List<StandaloneSingleScreenTask1> tmp = new ArrayList<StandaloneSingleScreenTask1>(standaloneSingleScreenTask1);
		for ( StandaloneSingleScreenTask1 o : tmp ) {
			removeFromStandaloneSingleScreenTask1(o);
		}
	}
	
	public void removeAllFromTechnician(Set<Technician> technician) {
		Set<Technician> tmp = new HashSet<Technician>(technician);
		for ( Technician o : tmp ) {
			removeFromTechnician(o);
		}
	}
	
	public void removeFromCustomerAssistant(CustomerAssistant customerAssistant) {
		if ( customerAssistant!=null ) {
			customerAssistant.z_internalRemoveFromBranch(this);
			z_internalRemoveFromCustomerAssistant(customerAssistant);
			customerAssistant.markDeleted();
		}
	}
	
	public void removeFromJob(Job job) {
		if ( job!=null ) {
			job.z_internalRemoveFromBranch(this);
			z_internalRemoveFromJob(job);
			job.markDeleted();
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
	
	public void removeFromPrepareQuote(PrepareQuote prepareQuote) {
		if ( prepareQuote!=null ) {
			prepareQuote.z_internalRemoveFromContextObject(this);
			z_internalRemoveFromPrepareQuote(prepareQuote);
			prepareQuote.markDeleted();
		}
	}
	
	public void removeFromStandaloneSingleScreenTask1(StandaloneSingleScreenTask1 standaloneSingleScreenTask1) {
		if ( standaloneSingleScreenTask1!=null ) {
			standaloneSingleScreenTask1.z_internalRemoveFromContextObject(this);
			z_internalRemoveFromStandaloneSingleScreenTask1(standaloneSingleScreenTask1);
			standaloneSingleScreenTask1.markDeleted();
		}
	}
	
	public void removeFromTechnician(Technician technician) {
		if ( technician!=null ) {
			technician.z_internalRemoveFromBranch(this);
			z_internalRemoveFromTechnician(technician);
			technician.markDeleted();
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
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
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
	
	public void setNumberOfOpenPositions(Double numberOfOpenPositions) {
		propertyChangeSupport.firePropertyChange("numberOfOpenPositions",getNumberOfOpenPositions(),numberOfOpenPositions);
		this.z_internalAddToNumberOfOpenPositions(numberOfOpenPositions);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganizationAsBusinessComponent_representedOrganization(OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization) {
		OrganizationAsBusinessComponent oldValue = this.getOrganizationAsBusinessComponent_representedOrganization();
		propertyChangeSupport.firePropertyChange("organizationAsBusinessComponent_representedOrganization",getOrganizationAsBusinessComponent_representedOrganization(),organizationAsBusinessComponent_representedOrganization);
		if ( oldValue==null ) {
			if ( organizationAsBusinessComponent_representedOrganization!=null ) {
				Branch oldOther = (Branch)organizationAsBusinessComponent_representedOrganization.getBusinessComponent();
				organizationAsBusinessComponent_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
				}
				organizationAsBusinessComponent_representedOrganization.z_internalAddToBusinessComponent((Branch)this);
			}
			this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
		} else {
			if ( !oldValue.equals(organizationAsBusinessComponent_representedOrganization) ) {
				oldValue.z_internalRemoveFromBusinessComponent(this);
				z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(oldValue);
				if ( organizationAsBusinessComponent_representedOrganization!=null ) {
					Branch oldOther = (Branch)organizationAsBusinessComponent_representedOrganization.getBusinessComponent();
					organizationAsBusinessComponent_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
					}
					organizationAsBusinessComponent_representedOrganization.z_internalAddToBusinessComponent((Branch)this);
				}
				this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
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
	
	public void setPrepareQuote(List<PrepareQuote> prepareQuote) {
		propertyChangeSupport.firePropertyChange("prepareQuote",getPrepareQuote(),prepareQuote);
		this.clearPrepareQuote();
		this.addAllToPrepareQuote(prepareQuote);
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
	
	public void setStandaloneSingleScreenTask1(List<StandaloneSingleScreenTask1> standaloneSingleScreenTask1) {
		propertyChangeSupport.firePropertyChange("standaloneSingleScreenTask1",getStandaloneSingleScreenTask1(),standaloneSingleScreenTask1);
		this.clearStandaloneSingleScreenTask1();
		this.addAllToStandaloneSingleScreenTask1(standaloneSingleScreenTask1);
	}
	
	public void setTechnician(Set<Technician> technician) {
		propertyChangeSupport.firePropertyChange("technician",getTechnician(),technician);
		this.clearTechnician();
		this.addAllToTechnician(technician);
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
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.branch.Branch\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getCity()!=null ) {
			sb.append("city=\""+ getCity().name() + "\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getSurname()!=null ) {
			sb.append("surname=\""+ StructuredbusinessFormatter.getInstance().formatString(getSurname())+"\" ");
		}
		if ( getNumberOfOpenPositions()!=null ) {
			sb.append("numberOfOpenPositions=\""+ StructuredbusinessFormatter.getInstance().formatReal(getNumberOfOpenPositions())+"\" ");
		}
		sb.append(">");
		sb.append("\n<customerAssistant propertyId=\"758991831684440668\">");
		for ( CustomerAssistant customerAssistant : getCustomerAssistant() ) {
			sb.append("\n" + customerAssistant.toXmlString());
		}
		sb.append("\n</customerAssistant>");
		sb.append("\n<technician propertyId=\"6994431291756453463\">");
		for ( Technician technician : getTechnician() ) {
			sb.append("\n" + technician.toXmlString());
		}
		sb.append("\n</technician>");
		sb.append("\n<job propertyId=\"9026526080661167087\">");
		for ( Job job : getJob() ) {
			sb.append("\n" + job.toXmlString());
		}
		sb.append("\n</job>");
		sb.append("\n<standaloneSingleScreenTask1 propertyId=\"163026363185791958\">");
		for ( StandaloneSingleScreenTask1 standaloneSingleScreenTask1 : getStandaloneSingleScreenTask1() ) {
			sb.append("\n" + standaloneSingleScreenTask1.toXmlString());
		}
		sb.append("\n</standaloneSingleScreenTask1>");
		sb.append("\n<prepareQuote propertyId=\"4021326984444374137\">");
		for ( PrepareQuote prepareQuote : getPrepareQuote() ) {
			sb.append("\n" + prepareQuote.toXmlString());
		}
		sb.append("\n</prepareQuote>");
		if ( getOrganizationAsBusinessComponent_representedOrganization()==null ) {
			sb.append("\n<organizationAsBusinessComponent_representedOrganization/>");
		} else {
			sb.append("\n<organizationAsBusinessComponent_representedOrganization propertyId=\"3245714109628633948\">");
			sb.append("\n" + getOrganizationAsBusinessComponent_representedOrganization().toXmlString());
			sb.append("\n</organizationAsBusinessComponent_representedOrganization>");
		}
		sb.append("\n<participationParticipant_participation propertyId=\"5579540379306504838\">");
		for ( ParticipationParticipant participationParticipant_participation : getParticipationParticipant_participation() ) {
			sb.append("\n" + participationParticipant_participation.toXmlString());
		}
		sb.append("\n</participationParticipant_participation>");
		sb.append("\n</Branch>");
		return sb.toString();
	}
	
	public void z_internalAddToCity(City city) {
		this.city=city;
	}
	
	public void z_internalAddToCustomerAssistant(CustomerAssistant customerAssistant) {
		this.customerAssistant.add(customerAssistant);
	}
	
	public void z_internalAddToDishwashersInc(ApplianceDoctor dishwashersInc) {
		this.dishwashersInc=dishwashersInc;
	}
	
	public void z_internalAddToJob(Job job) {
		this.job.add(job);
	}
	
	public void z_internalAddToName(String name) {
		this.name=name;
	}
	
	public void z_internalAddToNumberOfOpenPositions(Double numberOfOpenPositions) {
		this.numberOfOpenPositions=numberOfOpenPositions;
	}
	
	public void z_internalAddToOrganizationAsBusinessComponent_representedOrganization(OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization) {
		this.organizationAsBusinessComponent_representedOrganization=organizationAsBusinessComponent_representedOrganization;
	}
	
	public void z_internalAddToParticipation(Participation participation) {
		ParticipationParticipant newOne = new ParticipationParticipant(this,participation);
		this.z_internalAddToParticipationParticipant_participation(newOne);
		newOne.getParticipation().z_internalAddToParticipationParticipant_participant(newOne);
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		this.participationParticipant_participation.add(participationParticipant_participation);
	}
	
	public void z_internalAddToPrepareQuote(PrepareQuote prepareQuote) {
		this.prepareQuote.add(prepareQuote);
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode representedOrganization) {
		OrganizationAsBusinessComponent newOne = new OrganizationAsBusinessComponent(this,representedOrganization);
		this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(newOne);
		newOne.getRepresentedOrganization().z_internalAddToOrganizationAsBusinessComponent_businessComponent(newOne);
	}
	
	public void z_internalAddToStandaloneSingleScreenTask1(StandaloneSingleScreenTask1 standaloneSingleScreenTask1) {
		this.standaloneSingleScreenTask1.add(standaloneSingleScreenTask1);
	}
	
	public void z_internalAddToSurname(String surname) {
		this.surname=surname;
	}
	
	public void z_internalAddToTechnician(Technician technician) {
		this.technician.add(technician);
	}
	
	public void z_internalRemoveFromCity(City city) {
		if ( getCity()!=null && city!=null && city.equals(getCity()) ) {
			this.city=null;
			this.city=null;
		}
	}
	
	public void z_internalRemoveFromCustomerAssistant(CustomerAssistant customerAssistant) {
		this.customerAssistant.remove(customerAssistant);
	}
	
	public void z_internalRemoveFromDishwashersInc(ApplianceDoctor dishwashersInc) {
		if ( getDishwashersInc()!=null && dishwashersInc!=null && dishwashersInc.equals(getDishwashersInc()) ) {
			this.dishwashersInc=null;
			this.dishwashersInc=null;
		}
	}
	
	public void z_internalRemoveFromJob(Job job) {
		this.job.remove(job);
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromNumberOfOpenPositions(Double numberOfOpenPositions) {
		if ( getNumberOfOpenPositions()!=null && numberOfOpenPositions!=null && numberOfOpenPositions.equals(getNumberOfOpenPositions()) ) {
			this.numberOfOpenPositions=null;
			this.numberOfOpenPositions=null;
		}
	}
	
	public void z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization) {
		if ( getOrganizationAsBusinessComponent_representedOrganization()!=null && organizationAsBusinessComponent_representedOrganization!=null && organizationAsBusinessComponent_representedOrganization.equals(getOrganizationAsBusinessComponent_representedOrganization()) ) {
			this.organizationAsBusinessComponent_representedOrganization=null;
			this.organizationAsBusinessComponent_representedOrganization=null;
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
	
	public void z_internalRemoveFromParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		this.participationParticipant_participation.remove(participationParticipant_participation);
	}
	
	public void z_internalRemoveFromPrepareQuote(PrepareQuote prepareQuote) {
		this.prepareQuote.remove(prepareQuote);
	}
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.organizationAsBusinessComponent_representedOrganization!=null ) {
			this.organizationAsBusinessComponent_representedOrganization.clear();
		}
	}
	
	public void z_internalRemoveFromStandaloneSingleScreenTask1(StandaloneSingleScreenTask1 standaloneSingleScreenTask1) {
		this.standaloneSingleScreenTask1.remove(standaloneSingleScreenTask1);
	}
	
	public void z_internalRemoveFromTechnician(Technician technician) {
		this.technician.remove(technician);
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
	
	/** Implements self.job->collect(temp1 : Job | temp1.activity)
	 * 
	 * @param parameter1ll 
	 * @param failedConstraints 
	 */
	private Collection<Activity> collect13(@ParameterMetaInfo(name="parameter1ll",opaeumId=7842728324345019779l,uuid="914890@_bNhwQBRxEeKYhv9CKiNDbg") IBusiness parameter1ll, Set<FailedConstraint> failedConstraints) {
		Collection<Activity> result = new ArrayList<Activity>();
		for ( Job temp1 : this.getJob() ) {
			Set<Activity> bodyExpResult = temp1.getActivity();
			result.addAll( bodyExpResult );
		}
		return result;
	}
	
	/** Implements self.job->collect(temp1 : Job | temp1.activity)->collect(temp2 : Activity | temp2.costToCompany)
	 * 
	 * @param parameter1ll 
	 * @param failedConstraints 
	 */
	private Collection<Double> collect14(@ParameterMetaInfo(name="parameter1ll",opaeumId=7842728324345019779l,uuid="914890@_bNhwQBRxEeKYhv9CKiNDbg") IBusiness parameter1ll, Set<FailedConstraint> failedConstraints) {
		Collection<Double> result = new ArrayList<Double>();
		for ( Activity temp2 : (collect13(parameter1ll, failedConstraints)) ) {
			Double bodyExpResult = temp2.getCostToCompany();
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
	
	/** Implements Set {self.city}
	 * 
	 * @param parameter1ll 
	 */
	private Set<City> collectionLiteral12(@ParameterMetaInfo(name="parameter1ll",opaeumId=7842728324345019779l,uuid="914890@_bNhwQBRxEeKYhv9CKiNDbg") IBusiness parameter1ll) {
		Set<City> myList = new HashSet<City>();
		if ( this.getCity() != null ) {
			myList.add( this.getCity() );
		}
		return myList;
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
	
	private void setSurname(String surname) {
		propertyChangeSupport.firePropertyChange("surname",getSurname(),surname);
		this.z_internalAddToSurname(surname);
	}
	
	/** Implements self.job->collect(temp1 : Job | temp1.activity)->collect(temp2 : Activity | temp2.costToCompany)->sum() on self.job->collect(temp1 : Job | temp1.activity)->collect(temp2 : Activity | temp2.costToCompany)
	 * 
	 * @param parameter1ll 
	 * @param failedConstraints 
	 */
	private Double sum15(@ParameterMetaInfo(name="parameter1ll",opaeumId=7842728324345019779l,uuid="914890@_bNhwQBRxEeKYhv9CKiNDbg") IBusiness parameter1ll, Set<FailedConstraint> failedConstraints) {
		Double result = 0d;
		for ( Double elem : (collect14(parameter1ll, failedConstraints)) ) {
			result = result + elem;
		}
		return result;
	}

}