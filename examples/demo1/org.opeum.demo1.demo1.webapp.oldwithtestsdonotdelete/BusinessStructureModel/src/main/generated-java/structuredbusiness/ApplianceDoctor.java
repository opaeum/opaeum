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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_CQTWAGOeEeGwMNo027LgxA")
@BusinessComponent(businessRoles={Manager.class,Manager.class})
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="appliance_doctor")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="ApplianceDoctor")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class ApplianceDoctor implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="applianceDoctor",targetEntity=ApplianceModel.class)
	private Set<ApplianceModel> applianceModel = new HashSet<ApplianceModel>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Branch.class)
	private Set<Branch> branch = new HashSet<Branch>();
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Temporal(	javax.persistence.TemporalType.DATE)
	@Column(name="initiation_date")
	private Date initiationDate;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="dishwashersInc",targetEntity=Manager.class)
	private Set<Manager> manager = new HashSet<Manager>();
	static private Set<ApplianceDoctor> mockedAllInstances;
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
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participation_participant_participation_id",nullable=true)
	private Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8415961198448241003l;
	@Email(groups={},message="Invalid e-mail address format",payload={})
	@Column(name="support_e_mail_address")
	private String supportEMailAddress;
	@Length(groups={},max=15,message="Phone number must consist of between  9 and 15 characters",min=8,payload={})
	@Digits(fraction=0,groups={},integer=15,message="",payload={})
	@Column(name="support_number")
	private String supportNumber;
	private String uid;
	@Column(name="vat_number")
	private String vatNumber;

	/** Default constructor for ApplianceDoctor
	 */
	public ApplianceDoctor() {
	}

	@NumlMetaInfo(uuid="914890@_FGOJ8H4bEeGW5bASaRr7SQ")
	public void addAccountant(@ParameterMetaInfo(name="name",opaeumId=341190338248797855l,uuid="914890@_HmRE0H4bEeGW5bASaRr7SQ") String name, @ParameterMetaInfo(name="isChartered",opaeumId=9099761849766142693l,uuid="914890@_MWWvsH4bEeGW5bASaRr7SQ") String isChartered, @ParameterMetaInfo(name="manager",opaeumId=4684052632804621483l,uuid="914890@_RA5zQH4bEeGW5bASaRr7SQ") Manager manager) {
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
			return new HashSet(persistence.readAll(structuredbusiness.ApplianceDoctor.class));
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
			setVatNumber(StructuredbusinessFormatter.getInstance().parseString(xml.getAttribute("vatNumber")));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToApplianceModel(curVal);
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToApplianceModel(curVal);
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
	
	public void clearApplianceModel() {
		removeAllFromApplianceModel(getApplianceModel());
	}
	
	public void clearBranch() {
		removeAllFromBranch(getBranch());
	}
	
	public void clearManager() {
		removeAllFromManager(getManager());
	}
	
	public void clearParticipation() {
		removeAllFromParticipation(getParticipation());
	}
	
	public void clearParticipationParticipant_participation() {
		removeAllFromParticipationParticipant_participation(getParticipationParticipant_participation());
	}
	
	public boolean consumeAddAccountantOccurrence(@ParameterMetaInfo(name="name",opaeumId=341190338248797855l,uuid="914890@_HmRE0H4bEeGW5bASaRr7SQ") String name, @ParameterMetaInfo(name="isChartered",opaeumId=9099761849766142693l,uuid="914890@_MWWvsH4bEeGW5bASaRr7SQ") String isChartered, @ParameterMetaInfo(name="manager",opaeumId=4684052632804621483l,uuid="914890@_RA5zQH4bEeGW5bASaRr7SQ") Manager manager) {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(ApplianceDoctor from, ApplianceDoctor to) {
		to.setName(from.getName());
		to.setSupportNumber(from.getSupportNumber());
		to.setSupportEMailAddress(from.getSupportEMailAddress());
		to.setInitiationDate(from.getInitiationDate());
		to.setVatNumber(from.getVatNumber());
	}
	
	public void copyState(ApplianceDoctor from, ApplianceDoctor to) {
		for ( Manager child : from.getManager() ) {
			to.addToManager(child.makeCopy());
		}
		to.setName(from.getName());
		to.setSupportNumber(from.getSupportNumber());
		to.setSupportEMailAddress(from.getSupportEMailAddress());
		to.setInitiationDate(from.getInitiationDate());
		to.setVatNumber(from.getVatNumber());
		for ( Branch child : from.getBranch() ) {
			to.addToBranch(child.makeCopy());
		}
		for ( ApplianceModel child : from.getApplianceModel() ) {
			to.addToApplianceModel(child.makeCopy());
		}
		for ( Manager child : from.getManager() ) {
			to.addToManager(child.makeCopy());
		}
		for ( Branch child : from.getBranch() ) {
			to.addToBranch(child.makeCopy());
		}
		for ( ApplianceModel child : from.getApplianceModel() ) {
			to.addToApplianceModel(child.makeCopy());
		}
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
	
	public boolean equals(Object other) {
		if ( other instanceof ApplianceDoctor ) {
			return other==this || ((ApplianceDoctor)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateAddAccountantEvent(@ParameterMetaInfo(name="name",opaeumId=341190338248797855l,uuid="914890@_HmRE0H4bEeGW5bASaRr7SQ") String name, @ParameterMetaInfo(name="isChartered",opaeumId=9099761849766142693l,uuid="914890@_MWWvsH4bEeGW5bASaRr7SQ") String isChartered, @ParameterMetaInfo(name="manager",opaeumId=4684052632804621483l,uuid="914890@_RA5zQH4bEeGW5bASaRr7SQ") Manager manager) {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5635067770444539801l,opposite="applianceDoctor",uuid="914890@_wtFJEJK_EeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_wtFJEJK_EeGnpuq6_ber_Q")
	public Set<ApplianceModel> getApplianceModel() {
		Set<ApplianceModel> result = this.applianceModel;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5891138470786181946l,opposite="dishwashersInc",uuid="914890@_-IPNcJJNEeGW4L5IejZxpA")
	@NumlMetaInfo(uuid="914890@_-IPNcJJNEeGW4L5IejZxpA")
	public Set<Branch> getBranch() {
		Set<Branch> result = this.branch;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2129484770117698232l,strategyFactory=DateStrategyFactory.class,uuid="914890@_rZMyYHsKEeGBGZr9IpIa3A")
	@NumlMetaInfo(uuid="914890@_rZMyYHsKEeGBGZr9IpIa3A")
	public Date getInitiationDate() {
		Date result = this.initiationDate;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6644597149462340021l,opposite="dishwashersInc",uuid="914890@_0XGTgHHgEeGus4aKic9sIg")
	@NumlMetaInfo(uuid="914890@_0XGTgHHgEeGus4aKic9sIg")
	public Set<Manager> getManager() {
		Set<Manager> result = this.manager;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2403097927264790462l,uuid="914890@_8-HO8HorEeGBZ7vhZCNgsg")
	@NumlMetaInfo(uuid="914890@_8-HO8HorEeGBZ7vhZCNgsg")
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
		return null;
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
		for ( Manager child : new ArrayList<Manager>(getManager()) ) {
			child.markDeleted();
		}
		for ( Branch child : new ArrayList<Branch>(getBranch()) ) {
			child.markDeleted();
		}
		for ( ApplianceModel child : new ArrayList<ApplianceModel>(getApplianceModel()) ) {
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
	
	static public void mockAllInstances(Set<ApplianceDoctor> newMocks) {
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
		}
	}
	
	public void removeFromBranch(Branch branch) {
		if ( branch!=null ) {
			branch.z_internalRemoveFromDishwashersInc(this);
			z_internalRemoveFromBranch(branch);
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
	
	public void setApplianceModel(Set<ApplianceModel> applianceModel) {
		propertyChangeSupport.firePropertyChange("applianceModel",getApplianceModel(),applianceModel);
		this.clearApplianceModel();
		this.addAllToApplianceModel(applianceModel);
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
	
	public void setInitiationDate(Date initiationDate) {
		propertyChangeSupport.firePropertyChange("initiationDate",getInitiationDate(),initiationDate);
		this.z_internalAddToInitiationDate(initiationDate);
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
	
	public void setOrganization_iBusinessComponent_1_representedOrganization(Organization_iBusinessComponent_1 organization_iBusinessComponent_1_representedOrganization) {
		Organization_iBusinessComponent_1 oldValue = this.getOrganization_iBusinessComponent_1_representedOrganization();
		propertyChangeSupport.firePropertyChange("organization_iBusinessComponent_1_representedOrganization",getOrganization_iBusinessComponent_1_representedOrganization(),organization_iBusinessComponent_1_representedOrganization);
		if ( oldValue==null ) {
			if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
				ApplianceDoctor oldOther = (ApplianceDoctor)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
				organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
				}
				organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((ApplianceDoctor)this);
			}
			this.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
		} else {
			if ( !oldValue.equals(organization_iBusinessComponent_1_representedOrganization) ) {
				oldValue.z_internalRemoveFromBusinessComponent(this);
				z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(oldValue);
				if ( organization_iBusinessComponent_1_representedOrganization!=null ) {
					ApplianceDoctor oldOther = (ApplianceDoctor)organization_iBusinessComponent_1_representedOrganization.getBusinessComponent();
					organization_iBusinessComponent_1_representedOrganization.z_internalRemoveFromBusinessComponent(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(organization_iBusinessComponent_1_representedOrganization);
					}
					organization_iBusinessComponent_1_representedOrganization.z_internalAddToBusinessComponent((ApplianceDoctor)this);
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
		sb.append("className=\"structuredbusiness.ApplianceDoctor\" ");
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
			sb.append("vatNumber=\""+ StructuredbusinessFormatter.getInstance().formatString(getVatNumber())+"\" ");
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
		sb.append("\n</ApplianceDoctor>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceModel(ApplianceModel val) {
		this.applianceModel.add(val);
	}
	
	public void z_internalAddToBranch(Branch val) {
		this.branch.add(val);
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
	
	public void z_internalAddToSupportEMailAddress(String val) {
		this.supportEMailAddress=val;
	}
	
	public void z_internalAddToSupportNumber(String val) {
		this.supportNumber=val;
	}
	
	public void z_internalAddToVatNumber(String val) {
		this.vatNumber=val;
	}
	
	public void z_internalRemoveFromApplianceModel(ApplianceModel val) {
		this.applianceModel.remove(val);
	}
	
	public void z_internalRemoveFromBranch(Branch val) {
		this.branch.remove(val);
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
	
	public void z_internalRemoveFromVatNumber(String val) {
		if ( getVatNumber()!=null && val!=null && val.equals(getVatNumber()) ) {
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