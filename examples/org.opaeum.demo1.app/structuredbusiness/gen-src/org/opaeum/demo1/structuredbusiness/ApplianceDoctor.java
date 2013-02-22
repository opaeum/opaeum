package org.opaeum.demo1.structuredbusiness;

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

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.appliance.ApplianceModel;
import org.opaeum.demo1.structuredbusiness.branch.Branch;
import org.opaeum.demo1.structuredbusiness.branch.Manager;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
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
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_CQTWAGOeEeGwMNo027LgxA")
@BusinessComponent(businessRoles=Manager.class)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="appliance_doctor",schema="structuredbusiness")
@Entity(name="ApplianceDoctor")
public class ApplianceDoctor implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="applianceDoctor",targetEntity=ApplianceModel.class)
	protected Set<ApplianceModel> applianceModel = new HashSet<ApplianceModel>();
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="attribute1")
	protected Date attribute1;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Branch.class)
	protected Set<Branch> branch = new HashSet<Branch>();
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="appliance_doctor",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="initiation_datell")
	protected Date initiationDatell;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Manager.class)
	protected Set<Manager> manager = new HashSet<Manager>();
	static private Set<? extends ApplianceDoctor> mockedAllInstances;
	@Lob
	@Column(name="name")
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_as_business_component_represented_organization_id",nullable=true)
	protected OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='914890@_CQTWAGOeEeGwMNo027LgxA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participant",nullable=true)
	protected Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="property1")
	protected Date property1;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8415961198448241003l;
	@Email(message="Invalid e-mail address format")
	@Column(name="support_e_mail_address")
	@Basic
	protected String supportEMailAddress;
	@Length(max=15,message="Phone number must consist of between  9 and 15 characters",min=8)
	@Digits(fraction=0,integer=15,message="")
	@Column(name="support_number")
	@Basic
	protected String supportNumber;
	private String uid;
	@Column(name="vat_number")
	@Basic
	protected String vatNumber;

	/** Default constructor for ApplianceDoctor
	 */
	public ApplianceDoctor() {
	}

	@NumlMetaInfo(uuid="914890@_FGOJ8H4bEeGW5bASaRr7SQ")
	public void addAccountant(@ParameterMetaInfo(name="name",opaeumId=341190338248797855l,uuid="914890@_HmRE0H4bEeGW5bASaRr7SQ") String name, @ParameterMetaInfo(name="isChartered",opaeumId=9099761849766142693l,uuid="914890@_MWWvsH4bEeGW5bASaRr7SQ") Boolean isChartered, @ParameterMetaInfo(name="manager",opaeumId=4684052632804621483l,uuid="914890@_RA5zQH4bEeGW5bASaRr7SQ") Manager manager) {
		generateAddAccountantEvent(name,isChartered,manager);
	}
	
	public void addAllToApplianceModel(Set<ApplianceModel> applianceModel) {
		for ( ApplianceModel o : applianceModel ) {
			addToApplianceModel(o);
		}
	}
	
	public void addAllToBranch(Set<Branch> branch) {
		for ( Branch o : branch ) {
			addToBranch(o);
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
	
	public void addAllToParticipationParticipant_participation(Set<ParticipationParticipant> participationParticipant_participation) {
		for ( ParticipationParticipant o : participationParticipant_participation ) {
			addToParticipationParticipant_participation(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToApplianceModel(ApplianceModel applianceModel) {
		if ( applianceModel!=null ) {
			applianceModel.z_internalRemoveFromApplianceDoctor(applianceModel.getApplianceDoctor());
			applianceModel.z_internalAddToApplianceDoctor(this);
			z_internalAddToApplianceModel(applianceModel);
		}
	}
	
	public void addToBranch(Branch branch) {
		if ( branch!=null ) {
			branch.z_internalRemoveFromDishwashersInc(branch.getDishwashersInc());
			branch.z_internalAddToDishwashersInc(this);
			z_internalAddToBranch(branch);
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
	
	static public Set<? extends ApplianceDoctor> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.ApplianceDoctor.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("supportNumber").length()>0 ) {
			setSupportNumber(StructuredbusinessFormatter.getInstance().parsePhoneNumber(xml.getAttribute("supportNumber")));
		}
		if ( xml.getAttribute("supportEMailAddress").length()>0 ) {
			setSupportEMailAddress(StructuredbusinessFormatter.getInstance().parseEMailAddress(xml.getAttribute("supportEMailAddress")));
		}
		if ( xml.getAttribute("initiationDatell").length()>0 ) {
			setInitiationDatell(StructuredbusinessFormatter.getInstance().parseDate(xml.getAttribute("initiationDatell")));
		}
		if ( xml.getAttribute("vatNumber").length()>0 ) {
			setVatNumber(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("vatNumber")));
		}
		if ( xml.getAttribute("name").length()>0 ) {
			setName(StructuredbusinessFormatter.getInstance().parseText(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("property1").length()>0 ) {
			setProperty1(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("property1")));
		}
		if ( xml.getAttribute("attribute1").length()>0 ) {
			setAttribute1(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("attribute1")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
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
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToManager(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("branch") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5891138470786181946")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Branch curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToBranch(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceModel") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5635067770444539801")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ApplianceModel curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.demo1.structuredbusiness.util.StructuredbusinessJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToApplianceModel(curVal);
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
	
	public void clearApplianceModel() {
		Set<ApplianceModel> tmp = new HashSet<ApplianceModel>(getApplianceModel());
		for ( ApplianceModel o : tmp ) {
			removeFromApplianceModel(o);
		}
	}
	
	public void clearBranch() {
		Set<Branch> tmp = new HashSet<Branch>(getBranch());
		for ( Branch o : tmp ) {
			removeFromBranch(o);
		}
	}
	
	public void clearManager() {
		Set<Manager> tmp = new HashSet<Manager>(getManager());
		for ( Manager o : tmp ) {
			removeFromManager(o);
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
	
	public boolean consumeAddAccountantOccurrence(@ParameterMetaInfo(name="name",opaeumId=341190338248797855l,uuid="914890@_HmRE0H4bEeGW5bASaRr7SQ") String name, @ParameterMetaInfo(name="isChartered",opaeumId=9099761849766142693l,uuid="914890@_MWWvsH4bEeGW5bASaRr7SQ") Boolean isChartered, @ParameterMetaInfo(name="manager",opaeumId=4684052632804621483l,uuid="914890@_RA5zQH4bEeGW5bASaRr7SQ") Manager manager) {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(ApplianceDoctor from, ApplianceDoctor to) {
		to.setSupportNumber(from.getSupportNumber());
		to.setSupportEMailAddress(from.getSupportEMailAddress());
		to.setInitiationDatell(from.getInitiationDatell());
		to.setVatNumber(from.getVatNumber());
		to.setName(from.getName());
		to.setProperty1(from.getProperty1());
		to.setAttribute1(from.getAttribute1());
	}
	
	public void copyState(ApplianceDoctor from, ApplianceDoctor to) {
		for ( Manager child : from.getManager() ) {
			to.addToManager(child.makeCopy());
		}
		to.setSupportNumber(from.getSupportNumber());
		to.setSupportEMailAddress(from.getSupportEMailAddress());
		to.setInitiationDatell(from.getInitiationDatell());
		to.setVatNumber(from.getVatNumber());
		for ( Branch child : from.getBranch() ) {
			to.addToBranch(child.makeCopy());
		}
		for ( ApplianceModel child : from.getApplianceModel() ) {
			to.addToApplianceModel(child.makeCopy());
		}
		to.setName(from.getName());
		to.setProperty1(from.getProperty1());
		to.setAttribute1(from.getAttribute1());
	}
	
	public ApplianceModel createApplianceModel() {
		ApplianceModel newInstance= new ApplianceModel();
		newInstance.init(this);
		return newInstance;
	}
	
	public Branch createBranch() {
		Branch newInstance= new Branch();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public Manager createManager() {
		Manager newInstance= new Manager();
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
	
	public boolean equals(Object other) {
		if ( other instanceof ApplianceDoctor ) {
			return other==this || ((ApplianceDoctor)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateAddAccountantEvent(@ParameterMetaInfo(name="name",opaeumId=341190338248797855l,uuid="914890@_HmRE0H4bEeGW5bASaRr7SQ") String name, @ParameterMetaInfo(name="isChartered",opaeumId=9099761849766142693l,uuid="914890@_MWWvsH4bEeGW5bASaRr7SQ") Boolean isChartered, @ParameterMetaInfo(name="manager",opaeumId=4684052632804621483l,uuid="914890@_RA5zQH4bEeGW5bASaRr7SQ") Manager manager) {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5635067770444539801l,opposite="applianceDoctor",uuid="914890@_wtFJEJK_EeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_wtFJEJK_EeGnpuq6_ber_Q")
	public Set<ApplianceModel> getApplianceModel() {
		Set result = this.applianceModel;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1341626369504680748l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_nyJ_kBIuEeKet5Gm_LksyQ")
	@NumlMetaInfo(uuid="914890@_nyJ_kBIuEeKet5Gm_LksyQ")
	public Date getAttribute1() {
		Date result = this.attribute1;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5891138470786181946l,opposite="dishwashersInc",uuid="914890@_-IPNcJJNEeGW4L5IejZxpA")
	@NumlMetaInfo(uuid="914890@_-IPNcJJNEeGW4L5IejZxpA")
	public Set<Branch> getBranch() {
		Set result = this.branch;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2129484770117698232l,strategyFactory=DateStrategyFactory.class,uuid="914890@_rZMyYHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_rZMyYHsKEeGBGZr9IpIa3A")
	public Date getInitiationDatell() {
		Date result = this.initiationDatell;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6644597149462340021l,opposite="dishwashersInc",uuid="914890@_0XGTgHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0XGTgHHgEeGus4aKic9sIg")
	public Set<Manager> getManager() {
		Set result = this.manager;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4482302546737265492l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="914890@_h6e00BIeEeKr5f-zTsG0Vg")
	@NumlMetaInfo(uuid="914890@_h6e00BIeEeKr5f-zTsG0Vg")
	public String getName() {
		String result = this.name;
		
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
		return null;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9040234418452979532l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_gfQZsBIuEeKet5Gm_LksyQ")
	@NumlMetaInfo(uuid="914890@_gfQZsBIuEeKet5Gm_LksyQ")
	public Date getProperty1() {
		Date result = this.property1;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=656426330587139118l,uuid="914890@_okhEQHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_okhEQHsKEeGBGZr9IpIa3A")
	public String getSupportEMailAddress() {
		String result = this.supportEMailAddress;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=77118842450650400l,uuid="914890@_kin8IHsKEeGBGZr9IpIa3A")
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8454956352695908190l,uuid="914890@_VSJmQHsLEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_VSJmQHsLEeGBGZr9IpIa3A")
	public String getVatNumber() {
		String result = this.vatNumber;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		createComponents();
	}
	
	public ApplianceDoctor makeCopy() {
		ApplianceDoctor result = new ApplianceDoctor();
		copyState((ApplianceDoctor)this,result);
		return result;
	}
	
	public ApplianceDoctor makeShallowCopy() {
		ApplianceDoctor result = new ApplianceDoctor();
		copyShallowState((ApplianceDoctor)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromBusinessComponent(this);
		}
		for ( Manager child : new ArrayList<Manager>(getManager()) ) {
			child.markDeleted();
		}
		for ( Branch child : new ArrayList<Branch>(getBranch()) ) {
			child.markDeleted();
		}
		for ( ApplianceModel child : new ArrayList<ApplianceModel>(getApplianceModel()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("branch") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5891138470786181946")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Branch)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceModel") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5635067770444539801")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceModel)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void removeAllFromApplianceModel(Set<ApplianceModel> applianceModel) {
		Set<ApplianceModel> tmp = new HashSet<ApplianceModel>(applianceModel);
		for ( ApplianceModel o : tmp ) {
			removeFromApplianceModel(o);
		}
	}
	
	public void removeAllFromBranch(Set<Branch> branch) {
		Set<Branch> tmp = new HashSet<Branch>(branch);
		for ( Branch o : tmp ) {
			removeFromBranch(o);
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
	
	public void removeAllFromParticipationParticipant_participation(Set<ParticipationParticipant> participationParticipant_participation) {
		Set<ParticipationParticipant> tmp = new HashSet<ParticipationParticipant>(participationParticipant_participation);
		for ( ParticipationParticipant o : tmp ) {
			removeFromParticipationParticipant_participation(o);
		}
	}
	
	public void removeFromApplianceModel(ApplianceModel applianceModel) {
		if ( applianceModel!=null ) {
			applianceModel.z_internalRemoveFromApplianceDoctor(this);
			z_internalRemoveFromApplianceModel(applianceModel);
			applianceModel.markDeleted();
		}
	}
	
	public void removeFromBranch(Branch branch) {
		if ( branch!=null ) {
			branch.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromBranch(branch);
			branch.markDeleted();
		}
	}
	
	public void removeFromManager(Manager manager) {
		if ( manager!=null ) {
			manager.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromManager(manager);
			manager.markDeleted();
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
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setApplianceModel(Set<ApplianceModel> applianceModel) {
		propertyChangeSupport.firePropertyChange("applianceModel",getApplianceModel(),applianceModel);
		this.clearApplianceModel();
		this.addAllToApplianceModel(applianceModel);
	}
	
	public void setAttribute1(Date attribute1) {
		propertyChangeSupport.firePropertyChange("Attribute1",getAttribute1(),attribute1);
		this.z_internalAddToAttribute1(attribute1);
	}
	
	public void setBranch(Set<Branch> branch) {
		propertyChangeSupport.firePropertyChange("branch",getBranch(),branch);
		this.clearBranch();
		this.addAllToBranch(branch);
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
	
	public void setInitiationDatell(Date initiationDatell) {
		propertyChangeSupport.firePropertyChange("initiationDatell",getInitiationDatell(),initiationDatell);
		this.z_internalAddToInitiationDatell(initiationDatell);
	}
	
	public void setManager(Set<Manager> manager) {
		propertyChangeSupport.firePropertyChange("manager",getManager(),manager);
		this.clearManager();
		this.addAllToManager(manager);
	}
	
	public void setName(String name) {
		propertyChangeSupport.firePropertyChange("name",getName(),name);
		this.z_internalAddToName(name);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganizationAsBusinessComponent_representedOrganization(OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization) {
		OrganizationAsBusinessComponent oldValue = this.getOrganizationAsBusinessComponent_representedOrganization();
		propertyChangeSupport.firePropertyChange("organizationAsBusinessComponent_representedOrganization",getOrganizationAsBusinessComponent_representedOrganization(),organizationAsBusinessComponent_representedOrganization);
		if ( oldValue==null ) {
			if ( organizationAsBusinessComponent_representedOrganization!=null ) {
				ApplianceDoctor oldOther = (ApplianceDoctor)organizationAsBusinessComponent_representedOrganization.getBusinessComponent();
				organizationAsBusinessComponent_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
				}
				organizationAsBusinessComponent_representedOrganization.z_internalAddToBusinessComponent((ApplianceDoctor)this);
			}
			this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
		} else {
			if ( !oldValue.equals(organizationAsBusinessComponent_representedOrganization) ) {
				oldValue.z_internalRemoveFromBusinessComponent(this);
				z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(oldValue);
				if ( organizationAsBusinessComponent_representedOrganization!=null ) {
					ApplianceDoctor oldOther = (ApplianceDoctor)organizationAsBusinessComponent_representedOrganization.getBusinessComponent();
					organizationAsBusinessComponent_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
					}
					organizationAsBusinessComponent_representedOrganization.z_internalAddToBusinessComponent((ApplianceDoctor)this);
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
	
	public void setProperty1(Date property1) {
		propertyChangeSupport.firePropertyChange("Property1",getProperty1(),property1);
		this.z_internalAddToProperty1(property1);
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
	
	public void setSupportEMailAddress(String supportEMailAddress) {
		propertyChangeSupport.firePropertyChange("supportEMailAddress",getSupportEMailAddress(),supportEMailAddress);
		this.z_internalAddToSupportEMailAddress(supportEMailAddress);
	}
	
	public void setSupportNumber(String supportNumber) {
		propertyChangeSupport.firePropertyChange("supportNumber",getSupportNumber(),supportNumber);
		this.z_internalAddToSupportNumber(supportNumber);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setVatNumber(String vatNumber) {
		propertyChangeSupport.firePropertyChange("vatNumber",getVatNumber(),vatNumber);
		this.z_internalAddToVatNumber(vatNumber);
	}
	
	public String toXmlReferenceString() {
		return "<ApplianceDoctor uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ApplianceDoctor ");
		sb.append("classUuid=\"914890@_CQTWAGOeEeGwMNo027LgxA\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.ApplianceDoctor\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getSupportNumber()!=null ) {
			sb.append("supportNumber=\""+ StructuredbusinessFormatter.getInstance().formatPhoneNumber(getSupportNumber())+"\" ");
		}
		if ( getSupportEMailAddress()!=null ) {
			sb.append("supportEMailAddress=\""+ StructuredbusinessFormatter.getInstance().formatEMailAddress(getSupportEMailAddress())+"\" ");
		}
		if ( getInitiationDatell()!=null ) {
			sb.append("initiationDatell=\""+ StructuredbusinessFormatter.getInstance().formatDate(getInitiationDatell())+"\" ");
		}
		if ( getVatNumber()!=null ) {
			sb.append("vatNumber=\""+ StructuredbusinessFormatter.getInstance().formatString(getVatNumber())+"\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ StructuredbusinessFormatter.getInstance().formatText(getName())+"\" ");
		}
		if ( getProperty1()!=null ) {
			sb.append("property1=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(getProperty1())+"\" ");
		}
		if ( getAttribute1()!=null ) {
			sb.append("attribute1=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(getAttribute1())+"\" ");
		}
		sb.append(">");
		sb.append("\n<manager propertyId=\"6644597149462340021\">");
		for ( Manager manager : getManager() ) {
			sb.append("\n" + manager.toXmlString());
		}
		sb.append("\n</manager>");
		sb.append("\n<branch propertyId=\"5891138470786181946\">");
		for ( Branch branch : getBranch() ) {
			sb.append("\n" + branch.toXmlString());
		}
		sb.append("\n</branch>");
		sb.append("\n<applianceModel propertyId=\"5635067770444539801\">");
		for ( ApplianceModel applianceModel : getApplianceModel() ) {
			sb.append("\n" + applianceModel.toXmlString());
		}
		sb.append("\n</applianceModel>");
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
		sb.append("\n</ApplianceDoctor>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceModel(ApplianceModel applianceModel) {
		this.applianceModel.add(applianceModel);
	}
	
	public void z_internalAddToAttribute1(Date attribute1) {
		this.attribute1=attribute1;
	}
	
	public void z_internalAddToBranch(Branch branch) {
		this.branch.add(branch);
	}
	
	public void z_internalAddToInitiationDatell(Date initiationDatell) {
		this.initiationDatell=initiationDatell;
	}
	
	public void z_internalAddToManager(Manager manager) {
		this.manager.add(manager);
	}
	
	public void z_internalAddToName(String name) {
		this.name=name;
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
	
	public void z_internalAddToProperty1(Date property1) {
		this.property1=property1;
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode representedOrganization) {
		OrganizationAsBusinessComponent newOne = new OrganizationAsBusinessComponent(this,representedOrganization);
		this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(newOne);
		newOne.getRepresentedOrganization().z_internalAddToOrganizationAsBusinessComponent_businessComponent(newOne);
	}
	
	public void z_internalAddToSupportEMailAddress(String supportEMailAddress) {
		this.supportEMailAddress=supportEMailAddress;
	}
	
	public void z_internalAddToSupportNumber(String supportNumber) {
		this.supportNumber=supportNumber;
	}
	
	public void z_internalAddToVatNumber(String vatNumber) {
		this.vatNumber=vatNumber;
	}
	
	public void z_internalRemoveFromApplianceModel(ApplianceModel applianceModel) {
		this.applianceModel.remove(applianceModel);
	}
	
	public void z_internalRemoveFromAttribute1(Date attribute1) {
		if ( getAttribute1()!=null && attribute1!=null && attribute1.equals(getAttribute1()) ) {
			this.attribute1=null;
			this.attribute1=null;
		}
	}
	
	public void z_internalRemoveFromBranch(Branch branch) {
		this.branch.remove(branch);
	}
	
	public void z_internalRemoveFromInitiationDatell(Date initiationDatell) {
		if ( getInitiationDatell()!=null && initiationDatell!=null && initiationDatell.equals(getInitiationDatell()) ) {
			this.initiationDatell=null;
			this.initiationDatell=null;
		}
	}
	
	public void z_internalRemoveFromManager(Manager manager) {
		this.manager.remove(manager);
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
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
	
	public void z_internalRemoveFromProperty1(Date property1) {
		if ( getProperty1()!=null && property1!=null && property1.equals(getProperty1()) ) {
			this.property1=null;
			this.property1=null;
		}
	}
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.organizationAsBusinessComponent_representedOrganization!=null ) {
			this.organizationAsBusinessComponent_representedOrganization.clear();
		}
	}
	
	public void z_internalRemoveFromSupportEMailAddress(String supportEMailAddress) {
		if ( getSupportEMailAddress()!=null && supportEMailAddress!=null && supportEMailAddress.equals(getSupportEMailAddress()) ) {
			this.supportEMailAddress=null;
			this.supportEMailAddress=null;
		}
	}
	
	public void z_internalRemoveFromSupportNumber(String supportNumber) {
		if ( getSupportNumber()!=null && supportNumber!=null && supportNumber.equals(getSupportNumber()) ) {
			this.supportNumber=null;
			this.supportNumber=null;
		}
	}
	
	public void z_internalRemoveFromVatNumber(String vatNumber) {
		if ( getVatNumber()!=null && vatNumber!=null && vatNumber.equals(getVatNumber()) ) {
			this.vatNumber=null;
			this.vatNumber=null;
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