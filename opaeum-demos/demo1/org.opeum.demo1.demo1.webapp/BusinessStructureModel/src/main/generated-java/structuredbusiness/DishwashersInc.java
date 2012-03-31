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
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
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
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Accountant.class)
	private Set<Accountant> accountant = new HashSet<Accountant>();
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=DishWasherModel.class)
	private Set<DishWasherModel> dishWasher = new HashSet<DishWasherModel>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=DocumentVerifier.class)
	private Set<DocumentVerifier> documentVerifier = new HashSet<DocumentVerifier>();
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=IdBook.class)
	private Set<IdBook> idBook = new HashSet<IdBook>();
	@Column(name="initiation_date")
	private Date initiationDate;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Manager.class)
	private Set<Manager> manager = new HashSet<Manager>();
	static private Set<DishwashersInc> mockedAllInstances;
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
	@Email(groups={},message="",payload={})
	@Column(name="support_e_mail_address")
	private String supportEMailAddress;
	@Length(groups={},max=15,message="Phone number must consist of between  9 and 15 characters",min=8,payload={})
	@Digits(fraction=0,groups={},integer=15,message="",payload={})
	@Column(name="support_number")
	private String supportNumber;
	private String uid;
	@Column(name="vat_number")
	private Integer vatNumber;

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
	
	public void addAllToDishWasher(Set<DishWasherModel> dishWasher) {
		for ( DishWasherModel o : dishWasher ) {
			addToDishWasher(o);
		}
	}
	
	public void addAllToDocumentVerifier(Set<DocumentVerifier> documentVerifier) {
		for ( DocumentVerifier o : documentVerifier ) {
			addToDocumentVerifier(o);
		}
	}
	
	public void addAllToIdBook(Set<IdBook> idBook) {
		for ( IdBook o : idBook ) {
			addToIdBook(o);
		}
	}
	
	public void addAllToManager(Set<Manager> manager) {
		for ( Manager o : manager ) {
			addToManager(o);
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
	
	public void addToDishWasher(DishWasherModel dishWasher) {
		if ( dishWasher!=null ) {
			dishWasher.z_internalRemoveFromDishwashersInc(dishWasher.getDishwashersInc());
			dishWasher.z_internalAddToDishwashersInc(this);
			z_internalAddToDishWasher(dishWasher);
		}
	}
	
	public void addToDocumentVerifier(DocumentVerifier documentVerifier) {
		if ( documentVerifier!=null ) {
			documentVerifier.z_internalRemoveFromDishwashersInc(documentVerifier.getDishwashersInc());
			documentVerifier.z_internalAddToDishwashersInc(this);
			z_internalAddToDocumentVerifier(documentVerifier);
		}
	}
	
	public void addToIdBook(IdBook idBook) {
		if ( idBook!=null ) {
			idBook.z_internalRemoveFromDishwashersInc(idBook.getDishwashersInc());
			idBook.z_internalAddToDishwashersInc(this);
			z_internalAddToIdBook(idBook);
		}
	}
	
	public void addToManager(Manager manager) {
		if ( manager!=null ) {
			manager.z_internalRemoveFromDishwashersInc(manager.getDishwashersInc());
			manager.z_internalAddToDishwashersInc(this);
			z_internalAddToManager(manager);
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
		if ( xml.getAttribute("name").length()>0 ) {
			setName(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("supportNumber").length()>0 ) {
			setSupportNumber(StructuredbusinessFormatter.getInstance().parsePhoneNumber(xml.getAttribute("supportNumber")));
		}
		if ( xml.getAttribute("supportEMailAddress").length()>0 ) {
			setSupportEMailAddress(StructuredbusinessFormatter.getInstance().parseEMailAddress(xml.getAttribute("supportEMailAddress")));
		}
		if ( xml.getAttribute("initiationDate").length()>0 ) {
			setInitiationDate(StructuredbusinessFormatter.getInstance().parseDate(xml.getAttribute("initiationDate")));
		}
		if ( xml.getAttribute("vatNumber").length()>0 ) {
			setVatNumber(StructuredbusinessFormatter.getInstance().parseInteger(xml.getAttribute("vatNumber")));
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
						DishWasherModel curVal;
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
						this.addToIdBook(curVal);
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
						this.addToManager(curVal);
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
						this.addToAccountant(curVal);
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
						this.addToDocumentVerifier(curVal);
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
	
	public void clearIdBook() {
		removeAllFromIdBook(getIdBook());
	}
	
	public void clearManager() {
		removeAllFromManager(getManager());
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void copyShallowState(DishwashersInc from, DishwashersInc to) {
		to.setName(from.getName());
		to.setSupportNumber(from.getSupportNumber());
		to.setSupportEMailAddress(from.getSupportEMailAddress());
		to.setInitiationDate(from.getInitiationDate());
		to.setVatNumber(from.getVatNumber());
	}
	
	public void copyState(DishwashersInc from, DishwashersInc to) {
		for ( DishWasherModel child : from.getDishWasher() ) {
			to.addToDishWasher(child.makeCopy());
		}
		for ( IdBook child : from.getIdBook() ) {
			to.addToIdBook(child.makeCopy());
		}
		for ( Manager child : from.getManager() ) {
			to.addToManager(child.makeCopy());
		}
		for ( Accountant child : from.getAccountant() ) {
			to.addToAccountant(child.makeCopy());
		}
		for ( DocumentVerifier child : from.getDocumentVerifier() ) {
			to.addToDocumentVerifier(child.makeCopy());
		}
		to.setName(from.getName());
		to.setSupportNumber(from.getSupportNumber());
		to.setSupportEMailAddress(from.getSupportEMailAddress());
		to.setInitiationDate(from.getInitiationDate());
		to.setVatNumber(from.getVatNumber());
	}
	
	public Accountant createAccountant() {
		Accountant newInstance= new Accountant();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public DishWasherModel createDishWasher() {
		DishWasherModel newInstance= new DishWasherModel();
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
	
	@PropertyMetaInfo(isComposite=true,opaeumId=7823994331136448473l,opposite="dishwashersInc",uuid="914890@_0mn9QHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0mn9QHHgEeGus4aKic9sIg")
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
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=5940555815826406889l,opposite="dishwashersInc",uuid="914890@_z0LMoHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_z0LMoHHgEeGus4aKic9sIg")
	public Set<DishWasherModel> getDishWasher() {
		Set<DishWasherModel> result = this.dishWasher;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=3418722451639770409l,opposite="dishwashersInc",uuid="914890@_03oNsHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_03oNsHHgEeGus4aKic9sIg")
	public Set<DocumentVerifier> getDocumentVerifier() {
		Set<DocumentVerifier> result = this.documentVerifier;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=3308359593929749339l,opposite="dishwashersInc",uuid="914890@_0EdEUHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0EdEUHHgEeGus4aKic9sIg")
	public Set<IdBook> getIdBook() {
		Set<IdBook> result = this.idBook;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_rz7zsI6TEeCne5ArYLDbiA")
	public Collection<AbstractRequest> getInitiatedRequests() {
		Collection<AbstractRequest> result = collect11();
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=2129484770117698232l,uuid="914890@_rZMyYHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_rZMyYHsKEeGBGZr9IpIa3A")
	public Date getInitiationDate() {
		Date result = this.initiationDate;
		
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
	
	@PropertyMetaInfo(isComposite=true,opaeumId=6644597149462340021l,opposite="dishwashersInc",uuid="914890@_0XGTgHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0XGTgHHgEeGus4aKic9sIg")
	public Set<Manager> getManager() {
		Set<Manager> result = this.manager;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=2403097927264790462l,uuid="914890@_8-HO8HorEeGBZ7vhZCNgsg")
	@NumlMetaInfo(uuid="914890@_8-HO8HorEeGBZ7vhZCNgsg")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(isComposite=true,opaeumId=5756915452752219728l,opposite="businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA252060@_vf4noFYuEeGj5_I7bIwNoA")
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=4480510548106225415l,opposite="participant",uuid="252060@_3YyGkYoXEeCPduia_-NbFw")
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=7737100568581358598l,opposite="dishwashersInc",uuid="914890@_CQTWAGOeEeGwMNo027LgxA914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	@NumlMetaInfo(uuid="914890@_-VLbkE8VEeGA3PFuQY5w7QNakedBusinessCollaborationNakedBusinessCollaboration")
	public Structuredbusiness getRoot() {
		Structuredbusiness result = this.root;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=656426330587139118l,uuid="914890@_okhEQHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_okhEQHsKEeGBGZr9IpIa3A")
	public String getSupportEMailAddress() {
		String result = this.supportEMailAddress;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=77118842450650400l,uuid="914890@_kin8IHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_kin8IHsKEeGBGZr9IpIa3A")
	public String getSupportNumber() {
		String result = this.supportNumber;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=8454956352695908190l,uuid="914890@_VSJmQHsLEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_VSJmQHsLEeGBGZr9IpIa3A")
	public Integer getVatNumber() {
		Integer result = this.vatNumber;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToRoot((Structuredbusiness)owner);
		createComponents();
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
		for ( DishWasherModel child : new ArrayList<DishWasherModel>(getDishWasher()) ) {
			child.markDeleted();
		}
		for ( IdBook child : new ArrayList<IdBook>(getIdBook()) ) {
			child.markDeleted();
		}
		for ( Manager child : new ArrayList<Manager>(getManager()) ) {
			child.markDeleted();
		}
		for ( Accountant child : new ArrayList<Accountant>(getAccountant()) ) {
			child.markDeleted();
		}
		for ( DocumentVerifier child : new ArrayList<DocumentVerifier>(getDocumentVerifier()) ) {
			child.markDeleted();
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
						((DishWasherModel)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void removeAllFromAccountant(Set<Accountant> accountant) {
		Set<Accountant> tmp = new HashSet<Accountant>(accountant);
		for ( Accountant o : tmp ) {
			removeFromAccountant(o);
		}
	}
	
	public void removeAllFromDishWasher(Set<DishWasherModel> dishWasher) {
		Set<DishWasherModel> tmp = new HashSet<DishWasherModel>(dishWasher);
		for ( DishWasherModel o : tmp ) {
			removeFromDishWasher(o);
		}
	}
	
	public void removeAllFromDocumentVerifier(Set<DocumentVerifier> documentVerifier) {
		Set<DocumentVerifier> tmp = new HashSet<DocumentVerifier>(documentVerifier);
		for ( DocumentVerifier o : tmp ) {
			removeFromDocumentVerifier(o);
		}
	}
	
	public void removeAllFromIdBook(Set<IdBook> idBook) {
		Set<IdBook> tmp = new HashSet<IdBook>(idBook);
		for ( IdBook o : tmp ) {
			removeFromIdBook(o);
		}
	}
	
	public void removeAllFromManager(Set<Manager> manager) {
		Set<Manager> tmp = new HashSet<Manager>(manager);
		for ( Manager o : tmp ) {
			removeFromManager(o);
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
	
	public void removeFromDishWasher(DishWasherModel dishWasher) {
		if ( dishWasher!=null ) {
			dishWasher.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromDishWasher(dishWasher);
		}
	}
	
	public void removeFromDocumentVerifier(DocumentVerifier documentVerifier) {
		if ( documentVerifier!=null ) {
			documentVerifier.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromDocumentVerifier(documentVerifier);
		}
	}
	
	public void removeFromIdBook(IdBook idBook) {
		if ( idBook!=null ) {
			idBook.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromIdBook(idBook);
		}
	}
	
	public void removeFromManager(Manager manager) {
		if ( manager!=null ) {
			manager.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromManager(manager);
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setDishWasher(Set<DishWasherModel> dishWasher) {
		this.clearDishWasher();
		this.addAllToDishWasher(dishWasher);
	}
	
	public void setDocumentVerifier(Set<DocumentVerifier> documentVerifier) {
		this.clearDocumentVerifier();
		this.addAllToDocumentVerifier(documentVerifier);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setIdBook(Set<IdBook> idBook) {
		this.clearIdBook();
		this.addAllToIdBook(idBook);
	}
	
	public void setInitiationDate(Date initiationDate) {
		this.z_internalAddToInitiationDate(initiationDate);
	}
	
	public void setManager(Set<Manager> manager) {
		this.clearManager();
		this.addAllToManager(manager);
	}
	
	public void setName(String name) {
		this.z_internalAddToName(name);
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
	
	public void setSupportEMailAddress(String supportEMailAddress) {
		this.z_internalAddToSupportEMailAddress(supportEMailAddress);
	}
	
	public void setSupportNumber(String supportNumber) {
		this.z_internalAddToSupportNumber(supportNumber);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setVatNumber(Integer vatNumber) {
		this.z_internalAddToVatNumber(vatNumber);
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
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getSupportNumber()!=null ) {
			sb.append("supportNumber=\""+ StructuredbusinessFormatter.getInstance().formatPhoneNumber(getSupportNumber())+"\" ");
		}
		if ( getSupportEMailAddress()!=null ) {
			sb.append("supportEMailAddress=\""+ StructuredbusinessFormatter.getInstance().formatEMailAddress(getSupportEMailAddress())+"\" ");
		}
		if ( getInitiationDate()!=null ) {
			sb.append("initiationDate=\""+ StructuredbusinessFormatter.getInstance().formatDate(getInitiationDate())+"\" ");
		}
		if ( getVatNumber()!=null ) {
			sb.append("vatNumber=\""+ StructuredbusinessFormatter.getInstance().formatInteger(getVatNumber())+"\" ");
		}
		sb.append(">");
		sb.append("\n<dishWasher propertyId=\"5940555815826406889\">");
		for ( DishWasherModel dishWasher : getDishWasher() ) {
			sb.append("\n" + dishWasher.toXmlString());
		}
		sb.append("\n</dishWasher>");
		sb.append("\n<idBook propertyId=\"3308359593929749339\">");
		for ( IdBook idBook : getIdBook() ) {
			sb.append("\n" + idBook.toXmlString());
		}
		sb.append("\n</idBook>");
		sb.append("\n<manager propertyId=\"6644597149462340021\">");
		for ( Manager manager : getManager() ) {
			sb.append("\n" + manager.toXmlString());
		}
		sb.append("\n</manager>");
		sb.append("\n<accountant propertyId=\"7823994331136448473\">");
		for ( Accountant accountant : getAccountant() ) {
			sb.append("\n" + accountant.toXmlString());
		}
		sb.append("\n</accountant>");
		sb.append("\n<documentVerifier propertyId=\"3418722451639770409\">");
		for ( DocumentVerifier documentVerifier : getDocumentVerifier() ) {
			sb.append("\n" + documentVerifier.toXmlString());
		}
		sb.append("\n</documentVerifier>");
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
	
	public void z_internalAddToDishWasher(DishWasherModel val) {
		this.dishWasher.add(val);
	}
	
	public void z_internalAddToDocumentVerifier(DocumentVerifier val) {
		this.documentVerifier.add(val);
	}
	
	public void z_internalAddToIdBook(IdBook val) {
		this.idBook.add(val);
	}
	
	public void z_internalAddToInitiationDate(Date val) {
		this.initiationDate=val;
	}
	
	public void z_internalAddToManager(Manager val) {
		this.manager.add(val);
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
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
	
	public void z_internalAddToSupportEMailAddress(String val) {
		this.supportEMailAddress=val;
	}
	
	public void z_internalAddToSupportNumber(String val) {
		this.supportNumber=val;
	}
	
	public void z_internalAddToVatNumber(Integer val) {
		this.vatNumber=val;
	}
	
	public void z_internalRemoveFromAccountant(Accountant val) {
		this.accountant.remove(val);
	}
	
	public void z_internalRemoveFromDishWasher(DishWasherModel val) {
		this.dishWasher.remove(val);
	}
	
	public void z_internalRemoveFromDocumentVerifier(DocumentVerifier val) {
		this.documentVerifier.remove(val);
	}
	
	public void z_internalRemoveFromIdBook(IdBook val) {
		this.idBook.remove(val);
	}
	
	public void z_internalRemoveFromInitiationDate(Date val) {
		if ( getInitiationDate()!=null && val!=null && val.equals(getInitiationDate()) ) {
			this.initiationDate=null;
			this.initiationDate=null;
		}
	}
	
	public void z_internalRemoveFromManager(Manager val) {
		this.manager.remove(val);
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
	
	public void z_internalRemoveFromSupportEMailAddress(String val) {
		if ( getSupportEMailAddress()!=null && val!=null && val.equals(getSupportEMailAddress()) ) {
			this.supportEMailAddress=null;
			this.supportEMailAddress=null;
		}
	}
	
	public void z_internalRemoveFromSupportNumber(String val) {
		if ( getSupportNumber()!=null && val!=null && val.equals(getSupportNumber()) ) {
			this.supportNumber=null;
			this.supportNumber=null;
		}
	}
	
	public void z_internalRemoveFromVatNumber(Integer val) {
		if ( getVatNumber()!=null && val!=null && val.equals(getVatNumber()) ) {
			this.vatNumber=null;
			this.vatNumber=null;
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