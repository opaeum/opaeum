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
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.Property;
import org.opaeum.runtime.bpm.organization.IBusiness;
import org.opaeum.runtime.bpm.organization.IBusinessCollaboration;
import org.opaeum.runtime.bpm.organization.IBusinessComponent;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.organization.Organization_iBusinessComponent_1;
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
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@NumlMetaInfo(uuid="914890@_CQTWAGOeEeGwMNo027LgxA")
@BusinessComponent(businessRoles={Accountant.class,DocumentVerifier.class,Manager.class,LulusRole.class},isRoot=true)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="dishwashers_inc")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="DishwashersInc")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class DishwashersInc implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, IBusiness, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Accountant.class)
	private Set<Accountant> accountant = new HashSet<Accountant>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	private LulusRole businessRole1;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=DishWasher.class)
	private Set<DishWasher> dishWasher = new HashSet<DishWasher>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	private DocumentVerifier documentVerifier;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	private Manager manager;
	static private Set<DishwashersInc> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_i_business_component_1_represented_organization_id",nullable=true)
	private Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_CQTWAGOeEeGwMNo027LgxA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,targetEntity=Participation.class)
	@JoinColumn(name="participation_id",nullable=true)
	private Set<Participation> participation = new HashSet<Participation>();
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="root_id",name="idx_dishwashers_inc_root_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="root_id",nullable=true)
	private Structuredbusiness root;
	static final private long serialVersionUID = 8415961198448241003l;
	@Column(name="service_and_repair_person")
	private String serviceAndRepairPerson;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DishwashersInc(Structuredbusiness owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for DishwashersInc
	 */
	public DishwashersInc() {
	}

	public void addAllToAccountant(Set<Accountant> accountant) {
		for ( Accountant o : accountant ) {
			addToAccountant(o);
		}
	}
	
	public void addAllToDishWasher(Set<DishWasher> dishWasher) {
		for ( DishWasher o : dishWasher ) {
			addToDishWasher(o);
		}
	}
	
	public void addAllToParticipation(Set<Participation> participation) {
		for ( Participation o : participation ) {
			addToParticipation(o);
		}
	}
	
	public void addToAccountant(Accountant accountant) {
		if ( accountant!=null ) {
			accountant.z_internalRemoveFromDishwashersInc(accountant.getDishwashersInc());
			accountant.z_internalAddToDishwashersInc(this);
			z_internalAddToAccountant(accountant);
		}
	}
	
	public void addToDishWasher(DishWasher dishWasher) {
		if ( dishWasher!=null ) {
			dishWasher.z_internalRemoveFromDishwashersInc(dishWasher.getDishwashersInc());
			dishWasher.z_internalAddToDishwashersInc(this);
			z_internalAddToDishWasher(dishWasher);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getRoot().z_internalAddToDishwashersInc((DishwashersInc)this);
	}
	
	public void addToParticipation(Participation participation) {
		if ( participation!=null ) {
			participation.z_internalRemoveFromParticipant(participation.getParticipant());
			participation.z_internalAddToParticipant(this);
			z_internalAddToParticipation(participation);
		}
	}
	
	static public Set<? extends DishwashersInc> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(structuredbusiness.DishwashersInc.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("serviceAndRepairPerson").length()>0 ) {
			setServiceAndRepairPerson(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("serviceAndRepairPerson")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("dishWasher") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4003812926412214934")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						DishWasher curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToDishWasher(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("accountant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6804950105273954598")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Accountant curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToAccountant(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("documentVerifier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7700287307730271111")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						DocumentVerifier curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setDocumentVerifier(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("manager") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7657659257541105263")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Manager curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setManager(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessRole1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2298578172604671943")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						LulusRole curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setBusinessRole1(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5756915452752219728")) ) {
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
		}
	}
	
	public void clearAccountant() {
		removeAllFromAccountant(getAccountant());
	}
	
	public void clearDishWasher() {
		removeAllFromDishWasher(getDishWasher());
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void copyShallowState(DishwashersInc from, DishwashersInc to) {
		if ( from.getDocumentVerifier()!=null ) {
			to.setDocumentVerifier(from.getDocumentVerifier().makeShallowCopy());
		}
		if ( from.getManager()!=null ) {
			to.setManager(from.getManager().makeShallowCopy());
		}
		to.setServiceAndRepairPerson(from.getServiceAndRepairPerson());
		if ( from.getBusinessRole1()!=null ) {
			to.setBusinessRole1(from.getBusinessRole1().makeShallowCopy());
		}
	}
	
	public void copyState(DishwashersInc from, DishwashersInc to) {
		for ( DishWasher child : from.getDishWasher() ) {
			to.addToDishWasher(child.makeCopy());
		}
		for ( Accountant child : from.getAccountant() ) {
			to.addToAccountant(child.makeCopy());
		}
		if ( from.getDocumentVerifier()!=null ) {
			to.setDocumentVerifier(from.getDocumentVerifier().makeCopy());
		}
		if ( from.getManager()!=null ) {
			to.setManager(from.getManager().makeCopy());
		}
		to.setServiceAndRepairPerson(from.getServiceAndRepairPerson());
		if ( from.getBusinessRole1()!=null ) {
			to.setBusinessRole1(from.getBusinessRole1().makeCopy());
		}
	}
	
	public Accountant createAccountant() {
		Accountant newInstance= new Accountant();
		newInstance.init(this);
		return newInstance;
	}
	
	public LulusRole createBusinessRole1() {
		LulusRole newInstance= new LulusRole();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		if ( getDocumentVerifier()==null ) {
			setDocumentVerifier(new DocumentVerifier());
		}
		if ( getManager()==null ) {
			setManager(new Manager());
		}
		if ( getBusinessRole1()==null ) {
			setBusinessRole1(new LulusRole());
		}
	}
	
	public DishWasher createDishWasher() {
		DishWasher newInstance= new DishWasher();
		newInstance.init(this);
		return newInstance;
	}
	
	public DocumentVerifier createDocumentVerifier() {
		DocumentVerifier newInstance= new DocumentVerifier();
		newInstance.init(this);
		return newInstance;
	}
	
	public Manager createManager() {
		Manager newInstance= new Manager();
		newInstance.init(this);
		return newInstance;
	}
	
	public Organization_iBusinessComponent_1 createOrganization_iBusinessComponent_1_representedOrganization() {
		Organization_iBusinessComponent_1 newInstance= new Organization_iBusinessComponent_1();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof DishwashersInc ) {
			return other==this || ((DishwashersInc)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@Property(isComposite=true,opposite="dishwashersInc")
	@NumlMetaInfo(uuid="914890@_nItCUGO4EeGodsKwWzy5aw")
	public Set<Accountant> getAccountant() {
		Set<Accountant> result = this.accountant;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		if ( this.getRoot()!=null ) {
			result=this.getRoot();
		}
		return result;
	}
	
	@Property(isComposite=true,opposite="dishwashersInc")
	@NumlMetaInfo(uuid="914890@_vjgnMGSDEeG8Es66O6-kpg")
	public LulusRole getBusinessRole1() {
		LulusRole result = this.businessRole1;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@Property(isComposite=true,opposite="dishwashersInc")
	@NumlMetaInfo(uuid="914890@_m2nMwGO4EeGodsKwWzy5aw")
	public Set<DishWasher> getDishWasher() {
		Set<DishWasher> result = this.dishWasher;
		
		return result;
	}
	
	@Property(isComposite=true,opposite="dishwashersInc")
	@NumlMetaInfo(uuid="914890@_dBlRoGQWEeGbL9nlXe9lTQ")
	public DocumentVerifier getDocumentVerifier() {
		DocumentVerifier result = this.documentVerifier;
		
		return result;
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
	
	@Property(isComposite=true,opposite="dishwashersInc")
	@NumlMetaInfo(uuid="914890@_wkyykGQWEeGbL9nlXe9lTQ")
	public Manager getManager() {
		Manager result = this.manager;
		
		return result;
	}
	
	public String getName() {
		return "DishwashersInc["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@Property(isComposite=true,opposite="businessComponent")
	@NumlMetaInfo(uuid="252060@_vf4noVYuEeGj5_I7bIwNoA252060@_vf4noFYuEeGj5_I7bIwNoA")
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
	
	@NumlMetaInfo(uuid="252060@_NYHP0I6mEeCFsPOcAnk69Q")
	public Collection<TaskRequest> getOwnedTaskRequests() {
		Collection<TaskRequest> result = collect3();
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getRoot();
	}
	
	@Property(isComposite=false,opposite="participant")
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
	
	public OrganizationNode getRepresentedOrganization() {
		OrganizationNode result = null;
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			result = this.organization_iBusinessComponent_1_representedOrganization.getRepresentedOrganization();
		}
		return result;
	}
	
	@Property(isComposite=false,opposite="dishwashersInc")
	@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	public Structuredbusiness getRoot() {
		Structuredbusiness result = this.root;
		
		return result;
	}
	
	@Property(isComposite=true)
	@NumlMetaInfo(uuid="914890@_csN_0GR9EeGos671Ig7N3Q")
	public String getServiceAndRepairPerson() {
		String result = this.serviceAndRepairPerson;
		
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
		createComponents();
		getDocumentVerifier().init(this);
		getManager().init(this);
		getBusinessRole1().init(this);
	}
	
	public DishwashersInc makeCopy() {
		DishwashersInc result = new DishwashersInc();
		copyState((DishwashersInc)this,result);
		return result;
	}
	
	public DishwashersInc makeShallowCopy() {
		DishwashersInc result = new DishwashersInc();
		copyShallowState((DishwashersInc)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		if ( getRoot()!=null ) {
			getRoot().z_internalRemoveFromDishwashersInc(this);
		}
		for ( DishWasher child : new ArrayList<DishWasher>(getDishWasher()) ) {
			child.markDeleted();
		}
		for ( Accountant child : new ArrayList<Accountant>(getAccountant()) ) {
			child.markDeleted();
		}
		if ( getDocumentVerifier()!=null ) {
			getDocumentVerifier().markDeleted();
		}
		if ( getManager()!=null ) {
			getManager().markDeleted();
		}
		if ( getBusinessRole1()!=null ) {
			getBusinessRole1().markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<DishwashersInc> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("dishWasher") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4003812926412214934")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((DishWasher)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("accountant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6804950105273954598")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Accountant)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("documentVerifier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7700287307730271111")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((DocumentVerifier)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("manager") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7657659257541105263")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Manager)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessRole1") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2298578172604671943")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((LulusRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization_iBusinessComponent_1_representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5756915452752219728")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Organization_iBusinessComponent_1)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromAccountant(Set<Accountant> accountant) {
		Set<Accountant> tmp = new HashSet<Accountant>(accountant);
		for ( Accountant o : tmp ) {
			removeFromAccountant(o);
		}
	}
	
	public void removeAllFromDishWasher(Set<DishWasher> dishWasher) {
		Set<DishWasher> tmp = new HashSet<DishWasher>(dishWasher);
		for ( DishWasher o : tmp ) {
			removeFromDishWasher(o);
		}
	}
	
	public void removeAllFromParticipation(Set<Participation> participation) {
		Set<Participation> tmp = new HashSet<Participation>(participation);
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void removeFromAccountant(Accountant accountant) {
		if ( accountant!=null ) {
			accountant.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromAccountant(accountant);
		}
	}
	
	public void removeFromDishWasher(DishWasher dishWasher) {
		if ( dishWasher!=null ) {
			dishWasher.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromDishWasher(dishWasher);
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
	
	public void setAccountant(Set<Accountant> accountant) {
		this.clearAccountant();
		this.addAllToAccountant(accountant);
	}
	
	public void setBusinessRole1(LulusRole businessRole1) {
		LulusRole oldValue = this.getBusinessRole1();
		if ( oldValue==null ) {
			if ( businessRole1!=null ) {
				DishwashersInc oldOther = (DishwashersInc)businessRole1.getDishwashersInc();
				businessRole1.z_internalRemoveFromDishwashersInc(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessRole1(businessRole1);
				}
				businessRole1.z_internalAddToDishwashersInc((DishwashersInc)this);
			}
			this.z_internalAddToBusinessRole1(businessRole1);
		} else {
			if ( !oldValue.equals(businessRole1) ) {
				oldValue.z_internalRemoveFromDishwashersInc(this);
				z_internalRemoveFromBusinessRole1(oldValue);
				if ( businessRole1!=null ) {
					DishwashersInc oldOther = (DishwashersInc)businessRole1.getDishwashersInc();
					businessRole1.z_internalRemoveFromDishwashersInc(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessRole1(businessRole1);
					}
					businessRole1.z_internalAddToDishwashersInc((DishwashersInc)this);
				}
				this.z_internalAddToBusinessRole1(businessRole1);
			}
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishWasher(Set<DishWasher> dishWasher) {
		this.clearDishWasher();
		this.addAllToDishWasher(dishWasher);
	}
	
	public void setDocumentVerifier(DocumentVerifier documentVerifier) {
		DocumentVerifier oldValue = this.getDocumentVerifier();
		if ( oldValue==null ) {
			if ( documentVerifier!=null ) {
				DishwashersInc oldOther = (DishwashersInc)documentVerifier.getDishwashersInc();
				documentVerifier.z_internalRemoveFromDishwashersInc(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromDocumentVerifier(documentVerifier);
				}
				documentVerifier.z_internalAddToDishwashersInc((DishwashersInc)this);
			}
			this.z_internalAddToDocumentVerifier(documentVerifier);
		} else {
			if ( !oldValue.equals(documentVerifier) ) {
				oldValue.z_internalRemoveFromDishwashersInc(this);
				z_internalRemoveFromDocumentVerifier(oldValue);
				if ( documentVerifier!=null ) {
					DishwashersInc oldOther = (DishwashersInc)documentVerifier.getDishwashersInc();
					documentVerifier.z_internalRemoveFromDishwashersInc(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromDocumentVerifier(documentVerifier);
					}
					documentVerifier.z_internalAddToDishwashersInc((DishwashersInc)this);
				}
				this.z_internalAddToDocumentVerifier(documentVerifier);
			}
		}
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setManager(Manager manager) {
		Manager oldValue = this.getManager();
		if ( oldValue==null ) {
			if ( manager!=null ) {
				DishwashersInc oldOther = (DishwashersInc)manager.getDishwashersInc();
				manager.z_internalRemoveFromDishwashersInc(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromManager(manager);
				}
				manager.z_internalAddToDishwashersInc((DishwashersInc)this);
			}
			this.z_internalAddToManager(manager);
		} else {
			if ( !oldValue.equals(manager) ) {
				oldValue.z_internalRemoveFromDishwashersInc(this);
				z_internalRemoveFromManager(oldValue);
				if ( manager!=null ) {
					DishwashersInc oldOther = (DishwashersInc)manager.getDishwashersInc();
					manager.z_internalRemoveFromDishwashersInc(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromManager(manager);
					}
					manager.z_internalAddToDishwashersInc((DishwashersInc)this);
				}
				this.z_internalAddToManager(manager);
			}
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization) {
		Organization_iBusinessComponent_1 oldValue = this.getOrganization_iBusinessComponent_1_representedOrganization();
		if ( oldValue==null ) {
			if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
				DishwashersInc oldOther = (DishwashersInc)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
				organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
				}
				organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((DishwashersInc)this);
			}
			this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
		} else {
			if ( !oldValue.equals(organization_iBusinessComponent_1_representedOrganization) ) {
				oldValue.z_internalRemoveFromBusinessComponent(this);
				z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(oldValue);
				if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
					DishwashersInc oldOther = (DishwashersInc)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
					organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
					}
					organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((DishwashersInc)this);
				}
				this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
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
	
	public void setRepresentedOrganization(IOrganizationNode p) {
		setRepresentedOrganization((OrganizationNode)p);
	}
	
	public void setRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.getRepresentedOrganization()!=null ) {
			this.getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		if ( representedOrganization!=null ) {
			representedOrganization.z_internalAddToBusinessComponent(this);
			this.z_internalAddToRepresentedOrganization(representedOrganization);
		}
	}
	
	public void setRoot(Structuredbusiness root) {
		if ( this.getRoot()!=null ) {
			this.getRoot().z_internalRemoveFromDishwashersInc(this);
		}
		if ( root!=null ) {
			root.z_internalAddToDishwashersInc(this);
			this.z_internalAddToRoot(root);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setServiceAndRepairPerson(String serviceAndRepairPerson) {
		this.z_internalAddToServiceAndRepairPerson(serviceAndRepairPerson);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<DishwashersInc uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<DishwashersInc ");
		sb.append("classUuid=\"914890@_CQTWAGOeEeGwMNo027LgxA\" ");
		sb.append("className=\"structuredbusiness.DishwashersInc\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getServiceAndRepairPerson()!=null ) {
			sb.append("serviceAndRepairPerson=\""+ StructuredbusinessFormatter.getInstance().formatString(getServiceAndRepairPerson())+"\" ");
		}
		sb.append(">");
		sb.append("\n<dishWasher propertyId=\"4003812926412214934\">");
		for ( DishWasher dishWasher : getDishWasher() ) {
			sb.append("\n" + dishWasher.toXmlString());
		}
		sb.append("\n</dishWasher>");
		sb.append("\n<accountant propertyId=\"6804950105273954598\">");
		for ( Accountant accountant : getAccountant() ) {
			sb.append("\n" + accountant.toXmlString());
		}
		sb.append("\n</accountant>");
		if ( getDocumentVerifier()==null ) {
			sb.append("\n<documentVerifier/>");
		} else {
			sb.append("\n<documentVerifier propertyId=\"7700287307730271111\">");
			sb.append("\n" + getDocumentVerifier().toXmlString());
			sb.append("\n</documentVerifier>");
		}
		if ( getManager()==null ) {
			sb.append("\n<manager/>");
		} else {
			sb.append("\n<manager propertyId=\"7657659257541105263\">");
			sb.append("\n" + getManager().toXmlString());
			sb.append("\n</manager>");
		}
		if ( getBusinessRole1()==null ) {
			sb.append("\n<businessRole1/>");
		} else {
			sb.append("\n<businessRole1 propertyId=\"2298578172604671943\">");
			sb.append("\n" + getBusinessRole1().toXmlString());
			sb.append("\n</businessRole1>");
		}
		if ( getOrganization_iBusinessComponent_1_representedOrganization()==null ) {
			sb.append("\n<organization_iBusinessComponent_1_representedOrganization/>");
		} else {
			sb.append("\n<organization_iBusinessComponent_1_representedOrganization propertyId=\"5756915452752219728\">");
			sb.append("\n" + getOrganization_iBusinessComponent_1_representedOrganization().toXmlString());
			sb.append("\n</organization_iBusinessComponent_1_representedOrganization>");
		}
		sb.append("\n</DishwashersInc>");
		return sb.toString();
	}
	
	public void z_internalAddToAccountant(Accountant val) {
		this.accountant.add(val);
	}
	
	public void z_internalAddToBusinessRole1(LulusRole val) {
		this.businessRole1=val;
	}
	
	public void z_internalAddToDishWasher(DishWasher val) {
		this.dishWasher.add(val);
	}
	
	public void z_internalAddToDocumentVerifier(DocumentVerifier val) {
		this.documentVerifier=val;
	}
	
	public void z_internalAddToManager(Manager val) {
		this.manager=val;
	}
	
	public void z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val) {
		this.organization_iBusinessComponent_1_representedOrganization=val;
	}
	
	public void z_internalAddToParticipation(Participation val) {
		this.participation.add(val);
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode representedOrganization) {
		Organization_iBusinessComponent_1 newOne = new Organization_iBusinessComponent_1(this,representedOrganization);
		this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(newOne);
		newOne.getRepresentedOrganization().z_internalAddToOrganization_iBusinessComponent_1_businessComponent(newOne);
	}
	
	public void z_internalAddToRoot(Structuredbusiness val) {
		this.root=val;
	}
	
	public void z_internalAddToServiceAndRepairPerson(String val) {
		this.serviceAndRepairPerson=val;
	}
	
	public void z_internalRemoveFromAccountant(Accountant val) {
		this.accountant.remove(val);
	}
	
	public void z_internalRemoveFromBusinessRole1(LulusRole val) {
		if ( getBusinessRole1()!=null && val!=null && val.equals(getBusinessRole1()) ) {
			this.businessRole1=null;
			this.businessRole1=null;
		}
	}
	
	public void z_internalRemoveFromDishWasher(DishWasher val) {
		this.dishWasher.remove(val);
	}
	
	public void z_internalRemoveFromDocumentVerifier(DocumentVerifier val) {
		if ( getDocumentVerifier()!=null && val!=null && val.equals(getDocumentVerifier()) ) {
			this.documentVerifier=null;
			this.documentVerifier=null;
		}
	}
	
	public void z_internalRemoveFromManager(Manager val) {
		if ( getManager()!=null && val!=null && val.equals(getManager()) ) {
			this.manager=null;
			this.manager=null;
		}
	}
	
	public void z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 val) {
		if ( getOrganization_iBusinessComponent_1_representedOrganization()!=null && val!=null && val.equals(getOrganization_iBusinessComponent_1_representedOrganization()) ) {
			this.organization_iBusinessComponent_1_representedOrganization=null;
			this.organization_iBusinessComponent_1_representedOrganization=null;
		}
	}
	
	public void z_internalRemoveFromParticipation(Participation val) {
		this.participation.remove(val);
	}
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			this.organization_iBusinessComponent_1_representedOrganization.clear();
		}
	}
	
	public void z_internalRemoveFromRoot(Structuredbusiness val) {
		if ( getRoot()!=null && val!=null && val.equals(getRoot()) ) {
			this.root=null;
			this.root=null;
		}
	}
	
	public void z_internalRemoveFromServiceAndRepairPerson(String val) {
		if ( getServiceAndRepairPerson()!=null && val!=null && val.equals(getServiceAndRepairPerson()) ) {
			this.serviceAndRepairPerson=null;
			this.serviceAndRepairPerson=null;
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