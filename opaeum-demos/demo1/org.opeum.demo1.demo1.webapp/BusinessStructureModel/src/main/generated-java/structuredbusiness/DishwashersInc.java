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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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
@BusinessComponent(businessRoles={Manager.class,Accountant.class,DocumentVerifier.class},isRoot=true)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="dishwashers_inc")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="DishwashersInc")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class DishwashersInc implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, IBusiness, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@CollectionOfElements(fetch=javax.persistence.FetchType.LAZY,targetElement=String.class)
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	@JoinTable(joinColumns=
		@JoinColumn(name="dishwashers_inc_id",nullable=false,unique=false),name="dishwashers_inc_accountant")
	private Accountant accountant;
	@Column(name="business_role1")
	private String businessRole1;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@CollectionOfElements(fetch=javax.persistence.FetchType.LAZY,targetElement=String.class)
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	@JoinTable(joinColumns=
		@JoinColumn(name="dishwashers_inc_id",nullable=false,unique=false),name="dishwashers_inc_dish_washer")
	private DishWasher dishWasher;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@CollectionOfElements(fetch=javax.persistence.FetchType.LAZY,targetElement=String.class)
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	@JoinTable(joinColumns=
		@JoinColumn(name="dishwashers_inc_id",nullable=false,unique=false),name="dishwashers_inc_document_verifier")
	private DocumentVerifier documentVerifier;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	private IdBook idBook;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc")
	@Column(name="manager")
	private String manager;
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

	public void addAllToAccountant(Set<String> accountant) {
		for ( String o : accountant ) {
			addToAccountant(o);
		}
	}
	
	public void addAllToDishWasher(Set<String> dishWasher) {
		for ( String o : dishWasher ) {
			addToDishWasher(o);
		}
	}
	
	public void addAllToDocumentVerifier(Set<String> documentVerifier) {
		for ( String o : documentVerifier ) {
			addToDocumentVerifier(o);
		}
	}
	
	public void addAllToParticipation(Set<Participation> participation) {
		for ( Participation o : participation ) {
			addToParticipation(o);
		}
	}
	
	public void addToAccountant(String accountant) {
		z_internalAddToAccountant(accountant);
	}
	
	public void addToDishWasher(String dishWasher) {
		z_internalAddToDishWasher(dishWasher);
	}
	
	public void addToDocumentVerifier(String documentVerifier) {
		z_internalAddToDocumentVerifier(documentVerifier);
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
		if ( xml.getAttribute("dishWasher").length()>0 ) {
			for ( String val : xml.getAttribute("dishWasher").split(";") ) {
				addToDishWasher(StructuredbusinessFormatter.getInstance().parseString(val));
			}
		}
		if ( xml.getAttribute("accountant").length()>0 ) {
			for ( String val : xml.getAttribute("accountant").split(";") ) {
				addToAccountant(StructuredbusinessFormatter.getInstance().parseString(val));
			}
		}
		if ( xml.getAttribute("documentVerifier").length()>0 ) {
			for ( String val : xml.getAttribute("documentVerifier").split(";") ) {
				addToDocumentVerifier(StructuredbusinessFormatter.getInstance().parseString(val));
			}
		}
		if ( xml.getAttribute("manager").length()>0 ) {
			setManager(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("manager")));
		}
		if ( xml.getAttribute("serviceAndRepairPerson").length()>0 ) {
			setServiceAndRepairPerson(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("serviceAndRepairPerson")));
		}
		if ( xml.getAttribute("businessRole1").length()>0 ) {
			setBusinessRole1(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("businessRole1")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("dishWasher") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5940555815826406889")) ) {
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
						this.setDishWasher(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("idBook") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3308359593929749339")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						IdBook curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setIdBook(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("manager") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6644597149462340021")) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("accountant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7823994331136448473")) ) {
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
						this.setAccountant(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("documentVerifier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3418722451639770409")) ) {
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
	
	public void clearDocumentVerifier() {
		removeAllFromDocumentVerifier(getDocumentVerifier());
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void copyShallowState(DishwashersInc from, DishwashersInc to) {
		to.getDishWasher().addAll(from.getDishWasher());
		to.getAccountant().addAll(from.getAccountant());
		to.getDocumentVerifier().addAll(from.getDocumentVerifier());
		to.setManager(from.getManager());
		to.setServiceAndRepairPerson(from.getServiceAndRepairPerson());
		to.setBusinessRole1(from.getBusinessRole1());
		if ( from.getDishWasher()!=null ) {
			to.setDishWasher(from.getDishWasher().makeShallowCopy());
		}
		if ( from.getIdBook()!=null ) {
			to.setIdBook(from.getIdBook().makeShallowCopy());
		}
		if ( from.getManager()!=null ) {
			to.setManager(from.getManager().makeShallowCopy());
		}
		if ( from.getAccountant()!=null ) {
			to.setAccountant(from.getAccountant().makeShallowCopy());
		}
		if ( from.getDocumentVerifier()!=null ) {
			to.setDocumentVerifier(from.getDocumentVerifier().makeShallowCopy());
		}
	}
	
	public void copyState(DishwashersInc from, DishwashersInc to) {
		to.getDishWasher().addAll(from.getDishWasher());
		to.getAccountant().addAll(from.getAccountant());
		to.getDocumentVerifier().addAll(from.getDocumentVerifier());
		to.setManager(from.getManager());
		to.setServiceAndRepairPerson(from.getServiceAndRepairPerson());
		to.setBusinessRole1(from.getBusinessRole1());
		if ( from.getDishWasher()!=null ) {
			to.setDishWasher(from.getDishWasher().makeCopy());
		}
		if ( from.getIdBook()!=null ) {
			to.setIdBook(from.getIdBook().makeCopy());
		}
		if ( from.getManager()!=null ) {
			to.setManager(from.getManager().makeCopy());
		}
		if ( from.getAccountant()!=null ) {
			to.setAccountant(from.getAccountant().makeCopy());
		}
		if ( from.getDocumentVerifier()!=null ) {
			to.setDocumentVerifier(from.getDocumentVerifier().makeCopy());
		}
	}
	
	public Accountant createAccountant() {
		Accountant newInstance= new Accountant();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		if ( getDishWasher()==null ) {
			setDishWasher(new DishWasher());
		}
		if ( getIdBook()==null ) {
			setIdBook(new IdBook());
		}
		if ( getManager()==null ) {
			setManager(new Manager());
		}
		if ( getAccountant()==null ) {
			setAccountant(new Accountant());
		}
		if ( getDocumentVerifier()==null ) {
			setDocumentVerifier(new DocumentVerifier());
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
	
	public IdBook createIdBook() {
		IdBook newInstance= new IdBook();
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
	
	@PropertyMetaInfo(isComposite=true,opaeumId=7823994331136448473,opposite="dishwashersInc",uuid="914890@_0mn9QHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0mn9QHHgEeGus4aKic9sIg")
	public Accountant getAccountant() {
		Accountant result = this.accountant;
		
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
	
	@PropertyMetaInfo(isComposite=true,opaeumId=2298578172604671943,uuid="914890@_vjgnMGSDEeG8Es66O6-kpg")
	@NumlMetaInfo(uuid="914890@_vjgnMGSDEeG8Es66O6-kpg")
	public String getBusinessRole1() {
		String result = this.businessRole1;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=5940555815826406889,opposite="dishwashersInc",uuid="914890@_z0LMoHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_z0LMoHHgEeGus4aKic9sIg")
	public DishWasher getDishWasher() {
		DishWasher result = this.dishWasher;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=3418722451639770409,opposite="dishwashersInc",uuid="914890@_03oNsHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_03oNsHHgEeGus4aKic9sIg")
	public DocumentVerifier getDocumentVerifier() {
		DocumentVerifier result = this.documentVerifier;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=3308359593929749339,opposite="dishwashersInc",uuid="914890@_0EdEUHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0EdEUHHgEeGus4aKic9sIg")
	public IdBook getIdBook() {
		IdBook result = this.idBook;
		
		return result;
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
	
	@PropertyMetaInfo(isComposite=true,opaeumId=7657659257541105263,uuid="914890@_wkyykGQWEeGbL9nlXe9lTQ")
	@NumlMetaInfo(uuid="914890@_wkyykGQWEeGbL9nlXe9lTQ")
	public String getManager() {
		String result = this.manager;
		
		return result;
	}
	
	public String getName() {
		return "DishwashersInc["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=5756915452752219728,opposite="businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA252060@_vf4noFYuEeGj5_I7bIwNoA")
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
	
	public OrganizationNode getRepresentedOrganization() {
		OrganizationNode result = null;
		if ( this.organization_iBusinessComponent_1_representedOrganization!=null ) {
			result = this.organization_iBusinessComponent_1_representedOrganization.getRepresentedOrganization();
		}
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=7737100568581358598,opposite="dishwashersInc",uuid="914890@_CQTWAGOeEeGwMNo027LgxA914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	public Structuredbusiness getRoot() {
		Structuredbusiness result = this.root;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=7737305102789101846,uuid="914890@_csN_0GR9EeGos671Ig7N3Q")
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
		getDishWasher().init(this);
		getIdBook().init(this);
		getManager().init(this);
		getAccountant().init(this);
		getDocumentVerifier().init(this);
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
		if ( getDishWasher()!=null ) {
			getDishWasher().markDeleted();
		}
		if ( getIdBook()!=null ) {
			getIdBook().markDeleted();
		}
		if ( getManager()!=null ) {
			getManager().markDeleted();
		}
		if ( getAccountant()!=null ) {
			getAccountant().markDeleted();
		}
		if ( getDocumentVerifier()!=null ) {
			getDocumentVerifier().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("dishWasher") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5940555815826406889")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((DishWasher)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("idBook") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3308359593929749339")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((IdBook)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("manager") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6644597149462340021")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Manager)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("accountant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7823994331136448473")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Accountant)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("documentVerifier") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3418722451639770409")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((DocumentVerifier)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void removeAllFromAccountant(Set<String> accountant) {
		Set<String> tmp = new HashSet<String>(accountant);
		for ( String o : tmp ) {
			removeFromAccountant(o);
		}
	}
	
	public void removeAllFromDishWasher(Set<String> dishWasher) {
		Set<String> tmp = new HashSet<String>(dishWasher);
		for ( String o : tmp ) {
			removeFromDishWasher(o);
		}
	}
	
	public void removeAllFromDocumentVerifier(Set<String> documentVerifier) {
		Set<String> tmp = new HashSet<String>(documentVerifier);
		for ( String o : tmp ) {
			removeFromDocumentVerifier(o);
		}
	}
	
	public void removeAllFromParticipation(Set<Participation> participation) {
		Set<Participation> tmp = new HashSet<Participation>(participation);
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void removeFromAccountant(String accountant) {
	}
	
	public void removeFromDishWasher(String dishWasher) {
	}
	
	public void removeFromDocumentVerifier(String documentVerifier) {
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
	
	public void setAccountant(Accountant accountant) {
		Accountant oldValue = this.getAccountant();
		if ( oldValue==null ) {
			if ( accountant!=null ) {
				DishwashersInc oldOther = (DishwashersInc)accountant.getDishwashersInc();
				accountant.z_internalRemoveFromDishwashersInc(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromAccountant(accountant);
				}
				accountant.z_internalAddToDishwashersInc((DishwashersInc)this);
			}
			this.z_internalAddToAccountant(accountant);
		} else {
			if ( !oldValue.equals(accountant) ) {
				oldValue.z_internalRemoveFromDishwashersInc(this);
				z_internalRemoveFromAccountant(oldValue);
				if ( accountant!=null ) {
					DishwashersInc oldOther = (DishwashersInc)accountant.getDishwashersInc();
					accountant.z_internalRemoveFromDishwashersInc(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromAccountant(accountant);
					}
					accountant.z_internalAddToDishwashersInc((DishwashersInc)this);
				}
				this.z_internalAddToAccountant(accountant);
			}
		}
	}
	
	public void setAccountant(Set<String> accountant) {
		this.clearAccountant();
		this.addAllToAccountant(accountant);
	}
	
	public void setBusinessRole1(String businessRole1) {
		this.z_internalAddToBusinessRole1(businessRole1);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishWasher(DishWasher dishWasher) {
		DishWasher oldValue = this.getDishWasher();
		if ( oldValue==null ) {
			if ( dishWasher!=null ) {
				DishwashersInc oldOther = (DishwashersInc)dishWasher.getDishwashersInc();
				dishWasher.z_internalRemoveFromDishwashersInc(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromDishWasher(dishWasher);
				}
				dishWasher.z_internalAddToDishwashersInc((DishwashersInc)this);
			}
			this.z_internalAddToDishWasher(dishWasher);
		} else {
			if ( !oldValue.equals(dishWasher) ) {
				oldValue.z_internalRemoveFromDishwashersInc(this);
				z_internalRemoveFromDishWasher(oldValue);
				if ( dishWasher!=null ) {
					DishwashersInc oldOther = (DishwashersInc)dishWasher.getDishwashersInc();
					dishWasher.z_internalRemoveFromDishwashersInc(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromDishWasher(dishWasher);
					}
					dishWasher.z_internalAddToDishwashersInc((DishwashersInc)this);
				}
				this.z_internalAddToDishWasher(dishWasher);
			}
		}
	}
	
	public void setDishWasher(Set<String> dishWasher) {
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
	
	public void setDocumentVerifier(Set<String> documentVerifier) {
		this.clearDocumentVerifier();
		this.addAllToDocumentVerifier(documentVerifier);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setIdBook(IdBook idBook) {
		IdBook oldValue = this.getIdBook();
		if ( oldValue==null ) {
			if ( idBook!=null ) {
				DishwashersInc oldOther = (DishwashersInc)idBook.getDishwashersInc();
				idBook.z_internalRemoveFromDishwashersInc(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromIdBook(idBook);
				}
				idBook.z_internalAddToDishwashersInc((DishwashersInc)this);
			}
			this.z_internalAddToIdBook(idBook);
		} else {
			if ( !oldValue.equals(idBook) ) {
				oldValue.z_internalRemoveFromDishwashersInc(this);
				z_internalRemoveFromIdBook(oldValue);
				if ( idBook!=null ) {
					DishwashersInc oldOther = (DishwashersInc)idBook.getDishwashersInc();
					idBook.z_internalRemoveFromDishwashersInc(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromIdBook(idBook);
					}
					idBook.z_internalAddToDishwashersInc((DishwashersInc)this);
				}
				this.z_internalAddToIdBook(idBook);
			}
		}
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
	
	public void setManager(String manager) {
		this.z_internalAddToManager(manager);
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
		sb.append("dishWasher=\"");
		for ( String val : getDishWasher() ) {
			sb.append(StructuredbusinessFormatter.getInstance().formatString(val) + ";");
		}
		sb.append("\" ");
		sb.append("accountant=\"");
		for ( String val : getAccountant() ) {
			sb.append(StructuredbusinessFormatter.getInstance().formatString(val) + ";");
		}
		sb.append("\" ");
		sb.append("documentVerifier=\"");
		for ( String val : getDocumentVerifier() ) {
			sb.append(StructuredbusinessFormatter.getInstance().formatString(val) + ";");
		}
		sb.append("\" ");
		if ( getManager()!=null ) {
			sb.append("manager=\""+ StructuredbusinessFormatter.getInstance().formatString(getManager())+"\" ");
		}
		if ( getServiceAndRepairPerson()!=null ) {
			sb.append("serviceAndRepairPerson=\""+ StructuredbusinessFormatter.getInstance().formatString(getServiceAndRepairPerson())+"\" ");
		}
		if ( getBusinessRole1()!=null ) {
			sb.append("businessRole1=\""+ StructuredbusinessFormatter.getInstance().formatString(getBusinessRole1())+"\" ");
		}
		sb.append(">");
		if ( getDishWasher()==null ) {
			sb.append("\n<dishWasher/>");
		} else {
			sb.append("\n<dishWasher propertyId=\"5940555815826406889\">");
			sb.append("\n" + getDishWasher().toXmlString());
			sb.append("\n</dishWasher>");
		}
		if ( getIdBook()==null ) {
			sb.append("\n<idBook/>");
		} else {
			sb.append("\n<idBook propertyId=\"3308359593929749339\">");
			sb.append("\n" + getIdBook().toXmlString());
			sb.append("\n</idBook>");
		}
		if ( getManager()==null ) {
			sb.append("\n<manager/>");
		} else {
			sb.append("\n<manager propertyId=\"6644597149462340021\">");
			sb.append("\n" + getManager().toXmlString());
			sb.append("\n</manager>");
		}
		if ( getAccountant()==null ) {
			sb.append("\n<accountant/>");
		} else {
			sb.append("\n<accountant propertyId=\"7823994331136448473\">");
			sb.append("\n" + getAccountant().toXmlString());
			sb.append("\n</accountant>");
		}
		if ( getDocumentVerifier()==null ) {
			sb.append("\n<documentVerifier/>");
		} else {
			sb.append("\n<documentVerifier propertyId=\"3418722451639770409\">");
			sb.append("\n" + getDocumentVerifier().toXmlString());
			sb.append("\n</documentVerifier>");
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
		this.accountant=val;
	}
	
	public void z_internalAddToAccountant(String val) {
		this.accountant.add(val);
	}
	
	public void z_internalAddToBusinessRole1(String val) {
		this.businessRole1=val;
	}
	
	public void z_internalAddToDishWasher(DishWasher val) {
		this.dishWasher=val;
	}
	
	public void z_internalAddToDishWasher(String val) {
		this.dishWasher.add(val);
	}
	
	public void z_internalAddToDocumentVerifier(DocumentVerifier val) {
		this.documentVerifier=val;
	}
	
	public void z_internalAddToDocumentVerifier(String val) {
		this.documentVerifier.add(val);
	}
	
	public void z_internalAddToIdBook(IdBook val) {
		this.idBook=val;
	}
	
	public void z_internalAddToManager(Manager val) {
		this.manager=val;
	}
	
	public void z_internalAddToManager(String val) {
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
		if ( getAccountant()!=null && val!=null && val.equals(getAccountant()) ) {
			this.accountant=null;
			this.accountant=null;
		}
	}
	
	public void z_internalRemoveFromAccountant(String val) {
		this.accountant.remove(val);
	}
	
	public void z_internalRemoveFromBusinessRole1(String val) {
		if ( getBusinessRole1()!=null && val!=null && val.equals(getBusinessRole1()) ) {
			this.businessRole1=null;
			this.businessRole1=null;
		}
	}
	
	public void z_internalRemoveFromDishWasher(DishWasher val) {
		if ( getDishWasher()!=null && val!=null && val.equals(getDishWasher()) ) {
			this.dishWasher=null;
			this.dishWasher=null;
		}
	}
	
	public void z_internalRemoveFromDishWasher(String val) {
		this.dishWasher.remove(val);
	}
	
	public void z_internalRemoveFromDocumentVerifier(DocumentVerifier val) {
		if ( getDocumentVerifier()!=null && val!=null && val.equals(getDocumentVerifier()) ) {
			this.documentVerifier=null;
			this.documentVerifier=null;
		}
	}
	
	public void z_internalRemoveFromDocumentVerifier(String val) {
		this.documentVerifier.remove(val);
	}
	
	public void z_internalRemoveFromIdBook(IdBook val) {
		if ( getIdBook()!=null && val!=null && val.equals(getIdBook()) ) {
			this.idBook=null;
			this.idBook=null;
		}
	}
	
	public void z_internalRemoveFromManager(Manager val) {
		if ( getManager()!=null && val!=null && val.equals(getManager()) ) {
			this.manager=null;
			this.manager=null;
		}
	}
	
	public void z_internalRemoveFromManager(String val) {
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