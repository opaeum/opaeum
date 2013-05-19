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
import org.opaeum.annotation.BusinessComponent;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.IBusinessComponent;
import org.opaeum.runtime.bpm.organization.IParticipant;
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
import org.opaeum.runtime.domain.IToken;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.organization.IOrganizationNode;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bpmmodel.mybusiness.PrepareQuote;
import bpmmodel.mybusiness.PrepareQuoteImpl;
import bpmmodel.util.Stdlib;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="bpm.uml@_b9ZSMI_cEeKWQLWOG6WHmA")
@BusinessComponent(businessRoles={SalesPerson.class,BackOfficeWorker.class},isRoot=true)
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="my_business",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_collaboration1_id","deleted_on"}))
@Entity(name="MyBusiness")
public class MyBusiness implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IBusinessComponent, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="myBusiness",targetEntity=BackOfficeWorker.class)
	protected Set<BackOfficeWorker> backOfficeWorker = new HashSet<BackOfficeWorker>();
	@Index(columnNames="business_collaboration1_id",name="idx_my_business_business_collaboration1_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_collaboration1_id",nullable=true)
	protected BusinessCollaboration1 businessCollaboration1;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="my_business",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends MyBusiness> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_as_business_component_represented_organization_id",nullable=true)
	protected OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Where(clause="participant_type='bpm.uml@_b9ZSMI_cEeKWQLWOG6WHmA'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=ParticipationParticipant.class)
	@JoinColumn(name="participant",nullable=true)
	protected Set<ParticipationParticipant> participationParticipant_participation = new HashSet<ParticipationParticipant>();
	@Transient
	private InternalHibernatePersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@IndexColumn(name="idx_in_pre_quo_on_my_bus")
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=PrepareQuote.class)
	@JoinColumn(name="context_object_id",nullable=true)
	protected List<PrepareQuote> prepareQuote = new ArrayList<PrepareQuote>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@IndexColumn(name="idx_in_p_q_i_on_my_bus")
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=PrepareQuoteImpl.class)
	@JoinColumn(name="context_object_id",nullable=true)
	protected List<PrepareQuoteImpl> prepareQuoteImpl = new ArrayList<PrepareQuoteImpl>();
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="myBusiness")
	protected SalesPerson salesPerson;
	static final private long serialVersionUID = 8621215441141393629l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public MyBusiness(BusinessCollaboration1 owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for MyBusiness
	 */
	public MyBusiness() {
	}

	@NumlMetaInfo(uuid="bpm.uml@_fa3IgI_lEeK7E8hdca0nfw")
	public void Method1() {
	}
	
	public void addAllToBackOfficeWorker(Set<BackOfficeWorker> backOfficeWorker) {
		for ( BackOfficeWorker o : backOfficeWorker ) {
			addToBackOfficeWorker(o);
		}
	}
	
	public void addAllToParticipation(Set<Participation> participation) {
		for ( Participation o : participation ) {
			addToParticipation(o);
		}
	}
	
	public void addAllToPrepareQuote(List<PrepareQuote> prepareQuote) {
		for ( PrepareQuote o : prepareQuote ) {
			addToPrepareQuote(o);
		}
	}
	
	public void addAllToPrepareQuoteImpl(List<PrepareQuoteImpl> prepareQuoteImpl) {
		for ( PrepareQuoteImpl o : prepareQuoteImpl ) {
			addToPrepareQuoteImpl(o);
		}
	}
	
	public void addToBackOfficeWorker(BackOfficeWorker backOfficeWorker) {
		if ( backOfficeWorker!=null ) {
			if ( backOfficeWorker.getMyBusiness()!=null ) {
				backOfficeWorker.getMyBusiness().removeFromBackOfficeWorker(backOfficeWorker);
			}
			backOfficeWorker.z_internalAddToMyBusiness(this);
		}
		z_internalAddToBackOfficeWorker(backOfficeWorker);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessCollaboration1().z_internalAddToMyBusiness((MyBusiness)this);
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
	
	public void addToPrepareQuote(PrepareQuote prepareQuote) {
		if ( prepareQuote!=null ) {
			if ( prepareQuote.getContextObject()!=null ) {
				prepareQuote.getContextObject().removeFromPrepareQuote(prepareQuote);
			}
			prepareQuote.z_internalAddToContextObject(this);
		}
		z_internalAddToPrepareQuote(prepareQuote);
	}
	
	public void addToPrepareQuoteImpl(PrepareQuoteImpl prepareQuoteImpl) {
		if ( prepareQuoteImpl!=null ) {
			if ( prepareQuoteImpl.getContextObject()!=null ) {
				prepareQuoteImpl.getContextObject().removeFromPrepareQuoteImpl(prepareQuoteImpl);
			}
			prepareQuoteImpl.z_internalAddToContextObject(this);
		}
		z_internalAddToPrepareQuoteImpl(prepareQuoteImpl);
	}
	
	static public Set<? extends MyBusiness> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(bpmmodel.MyBusiness.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("salesPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7930574956849624622")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SalesPerson curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToSalesPerson(curVal);
						curVal.z_internalAddToMyBusiness(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("backOfficeWorker") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8656650025522290088")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BackOfficeWorker curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToBackOfficeWorker(curVal);
						curVal.z_internalAddToMyBusiness(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("prepareQuote") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("773142861041519426")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PrepareQuote curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToPrepareQuote(curVal);
						curVal.z_internalAddToContextObject(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("prepareQuoteImpl") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("679997709189448942")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						PrepareQuoteImpl curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=bpmmodel.util.BpmmodelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToPrepareQuoteImpl(curVal);
						curVal.z_internalAddToContextObject(this);
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
						this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(curVal);
						curVal.z_internalAddToBusinessComponent(this);
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
		}
	}
	
	public void clearBackOfficeWorker() {
		Set<BackOfficeWorker> tmp = new HashSet<BackOfficeWorker>(getBackOfficeWorker());
		for ( BackOfficeWorker o : tmp ) {
			removeFromBackOfficeWorker(o);
		}
	}
	
	public void clearParticipation() {
		Set<Participation> tmp = new HashSet<Participation>(getParticipation());
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void clearPrepareQuote() {
		List<PrepareQuote> tmp = new ArrayList<PrepareQuote>(getPrepareQuote());
		for ( PrepareQuote o : tmp ) {
			removeFromPrepareQuote(o);
		}
	}
	
	public void clearPrepareQuoteImpl() {
		List<PrepareQuoteImpl> tmp = new ArrayList<PrepareQuoteImpl>(getPrepareQuoteImpl());
		for ( PrepareQuoteImpl o : tmp ) {
			removeFromPrepareQuoteImpl(o);
		}
	}
	
	public boolean consumePrepareQuoteOccurrence(@ParameterMetaInfo(name="product",opaeumId=1045825153413114698l,uuid="bpm.uml@_Kcyv8I_hEeK855GX2Z3x4Q") Product product, @ParameterMetaInfo(name="quantity",opaeumId=7459204235176453452l,uuid="bpm.uml@_40ws0I_hEeK855GX2Z3x4Q") Integer quantity, @ParameterMetaInfo(name="requiredByDate",opaeumId=2288080870635458250l,strategyFactory=DateStrategyFactory.class,uuid="bpm.uml@_6S-XQI_hEeK855GX2Z3x4Q") Date requiredByDate) {
		boolean result = false;
		
		return result;
	}
	
	public void copyShallowState(MyBusiness from, MyBusiness to) {
		if ( from.getSalesPerson()!=null ) {
			to.z_internalAddToSalesPerson(from.getSalesPerson().makeShallowCopy());
		}
	}
	
	public void copyState(MyBusiness from, MyBusiness to) {
		if ( from.getSalesPerson()!=null ) {
			to.z_internalAddToSalesPerson(from.getSalesPerson().makeCopy());
		}
		for ( BackOfficeWorker child : from.getBackOfficeWorker() ) {
			to.addToBackOfficeWorker(child.makeCopy());
		}
		for ( PrepareQuoteImpl child : from.getPrepareQuoteImpl() ) {
			to.addToPrepareQuoteImpl(child.makeCopy());
		}
	}
	
	public BackOfficeWorker createBackOfficeWorker() {
		BackOfficeWorker newInstance= new BackOfficeWorker();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		if ( getSalesPerson()==null ) {
			setSalesPerson(new SalesPerson());
		}
	}
	
	public PrepareQuote createPrepareQuote() {
		PrepareQuote newInstance= new PrepareQuote();
		newInstance.init(this);
		return newInstance;
	}
	
	public PrepareQuoteImpl createPrepareQuoteImpl() {
		PrepareQuoteImpl newInstance= new PrepareQuoteImpl();
		newInstance.init(this);
		return newInstance;
	}
	
	public SalesPerson createSalesPerson() {
		SalesPerson newInstance= new SalesPerson();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof MyBusiness ) {
			return other==this || ((MyBusiness)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generatePrepareQuoteEvent(@ParameterMetaInfo(name="product",opaeumId=1045825153413114698l,uuid="bpm.uml@_Kcyv8I_hEeK855GX2Z3x4Q") Product product, @ParameterMetaInfo(name="quantity",opaeumId=7459204235176453452l,uuid="bpm.uml@_40ws0I_hEeK855GX2Z3x4Q") Integer quantity, @ParameterMetaInfo(name="requiredByDate",opaeumId=2288080870635458250l,strategyFactory=DateStrategyFactory.class,uuid="bpm.uml@_6S-XQI_hEeK855GX2Z3x4Q") Date requiredByDate) {
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=8656650025522290088l,opposite="myBusiness",uuid="bpm.uml@_yhvsEI_gEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_yhvsEI_gEeK855GX2Z3x4Q")
	public Set<BackOfficeWorker> getBackOfficeWorker() {
		Set result = this.backOfficeWorker;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8752796604079556707l,opposite="myBusiness",uuid="bpm.uml@_neO4IY_cEeKWQLWOG6WHmA")
	@NumlMetaInfo(uuid="bpm.uml@_neO4IY_cEeKWQLWOG6WHmA")
	public BusinessCollaboration1 getBusinessCollaboration1() {
		BusinessCollaboration1 result = this.businessCollaboration1;
		
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
	
	public String getName() {
		return "MyBusiness["+getId()+"]";
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
		return getBusinessCollaboration1();
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=773142861041519426l,opposite="contextObject",uuid="bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q@MT")
	public List<PrepareQuote> getPrepareQuote() {
		List result = this.prepareQuote;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=679997709189448942l,opposite="contextObject",uuid="bpm.uml@_Gld5EI_hEeK855GX2Z3x4Q")
	public List<PrepareQuoteImpl> getPrepareQuoteImpl() {
		List result = this.prepareQuoteImpl;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7930574956849624622l,opposite="myBusiness",uuid="bpm.uml@_yTM5wI_gEeK855GX2Z3x4Q")
	@NumlMetaInfo(uuid="bpm.uml@_yTM5wI_gEeK855GX2Z3x4Q")
	public SalesPerson getSalesPerson() {
		SalesPerson result = this.salesPerson;
		
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
		this.z_internalAddToBusinessCollaboration1((BusinessCollaboration1)owner);
	}
	
	public MyBusiness makeCopy() {
		MyBusiness result = new MyBusiness();
		copyState((MyBusiness)this,result);
		return result;
	}
	
	public MyBusiness makeShallowCopy() {
		MyBusiness result = new MyBusiness();
		copyShallowState((MyBusiness)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		for ( ParticipationParticipant link : new HashSet<ParticipationParticipant>(getParticipationParticipant_participation()) ) {
			link.getParticipation().z_internalRemoveFromParticipationParticipant_participant(link);
		}
		if ( getRepresentedOrganization()!=null ) {
			OrganizationAsBusinessComponent link = getOrganizationAsBusinessComponent_representedOrganization();
			link.getRepresentedOrganization().z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(link);
			link.markDeleted();
		}
		if ( getBusinessCollaboration1()!=null ) {
			getBusinessCollaboration1().z_internalRemoveFromMyBusiness(this);
		}
		if ( getSalesPerson()!=null ) {
			getSalesPerson().markDeleted();
		}
		for ( BackOfficeWorker child : new ArrayList<BackOfficeWorker>(getBackOfficeWorker()) ) {
			child.markDeleted();
		}
		for ( PrepareQuote child : new ArrayList<PrepareQuote>(getPrepareQuote()) ) {
			child.markDeleted();
		}
		for ( PrepareQuoteImpl child : new ArrayList<PrepareQuoteImpl>(getPrepareQuoteImpl()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("salesPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7930574956849624622")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((SalesPerson)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("backOfficeWorker") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8656650025522290088")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((BackOfficeWorker)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("prepareQuote") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("773142861041519426")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PrepareQuote)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("prepareQuoteImpl") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("679997709189448942")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((PrepareQuoteImpl)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	@NumlMetaInfo(uuid="bpm.uml@_D4xCQI_hEeK855GX2Z3x4Q")
	public PrepareQuoteImpl prepareQuote(IToken returnInfo, @ParameterMetaInfo(name="product",opaeumId=1045825153413114698l,uuid="bpm.uml@_Kcyv8I_hEeK855GX2Z3x4Q") Product product, @ParameterMetaInfo(name="quantity",opaeumId=7459204235176453452l,uuid="bpm.uml@_40ws0I_hEeK855GX2Z3x4Q") Integer quantity, @ParameterMetaInfo(name="requiredByDate",opaeumId=2288080870635458250l,strategyFactory=DateStrategyFactory.class,uuid="bpm.uml@_6S-XQI_hEeK855GX2Z3x4Q") Date requiredByDate) {
		PrepareQuoteImpl result = new PrepareQuoteImpl(this);
		result.setProduct(product);
		result.setQuantity(quantity);
		result.setRequiredByDate(requiredByDate);
		result.setReturnInfo(returnInfo);
		generatePrepareQuoteEvent(product,quantity,requiredByDate);
		return result;
	}
	
	public void removeAllFromBackOfficeWorker(Set<? extends BackOfficeWorker> backOfficeWorker) {
		Set<BackOfficeWorker> tmp = new HashSet<BackOfficeWorker>(backOfficeWorker);
		for ( BackOfficeWorker o : tmp ) {
			removeFromBackOfficeWorker(o);
		}
	}
	
	public void removeAllFromParticipation(Set<? extends Participation> participation) {
		Set<Participation> tmp = new HashSet<Participation>(participation);
		for ( Participation o : tmp ) {
			removeFromParticipation(o);
		}
	}
	
	public void removeAllFromPrepareQuote(List<? extends PrepareQuote> prepareQuote) {
		List<PrepareQuote> tmp = new ArrayList<PrepareQuote>(prepareQuote);
		for ( PrepareQuote o : tmp ) {
			removeFromPrepareQuote(o);
		}
	}
	
	public void removeAllFromPrepareQuoteImpl(List<? extends PrepareQuoteImpl> prepareQuoteImpl) {
		List<PrepareQuoteImpl> tmp = new ArrayList<PrepareQuoteImpl>(prepareQuoteImpl);
		for ( PrepareQuoteImpl o : tmp ) {
			removeFromPrepareQuoteImpl(o);
		}
	}
	
	public void removeFromBackOfficeWorker(BackOfficeWorker backOfficeWorker) {
		if ( backOfficeWorker!=null ) {
			backOfficeWorker.z_internalRemoveFromMyBusiness(this);
			z_internalRemoveFromBackOfficeWorker(backOfficeWorker);
			backOfficeWorker.markDeleted();
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
	
	public void removeFromPrepareQuote(PrepareQuote prepareQuote) {
		if ( prepareQuote!=null ) {
			prepareQuote.z_internalRemoveFromContextObject(this);
			z_internalRemoveFromPrepareQuote(prepareQuote);
			prepareQuote.markDeleted();
		}
	}
	
	public void removeFromPrepareQuoteImpl(PrepareQuoteImpl prepareQuoteImpl) {
		if ( prepareQuoteImpl!=null ) {
			prepareQuoteImpl.z_internalRemoveFromContextObject(this);
			z_internalRemoveFromPrepareQuoteImpl(prepareQuoteImpl);
			prepareQuoteImpl.markDeleted();
		}
	}
	
	public void setBackOfficeWorker(Set<BackOfficeWorker> backOfficeWorker) {
		this.clearBackOfficeWorker();
		this.addAllToBackOfficeWorker(backOfficeWorker);
	}
	
	public void setBusinessCollaboration1(BusinessCollaboration1 businessCollaboration1) {
		BusinessCollaboration1 oldValue = this.getBusinessCollaboration1();
		if ( oldValue==null ) {
			if ( businessCollaboration1!=null ) {
				MyBusiness oldOther = (MyBusiness)businessCollaboration1.getMyBusiness();
				businessCollaboration1.z_internalRemoveFromMyBusiness(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessCollaboration1(businessCollaboration1);
				}
				businessCollaboration1.z_internalAddToMyBusiness((MyBusiness)this);
			}
			this.z_internalAddToBusinessCollaboration1(businessCollaboration1);
		} else {
			if ( !oldValue.equals(businessCollaboration1) ) {
				oldValue.z_internalRemoveFromMyBusiness(this);
				z_internalRemoveFromBusinessCollaboration1(oldValue);
				if ( businessCollaboration1!=null ) {
					MyBusiness oldOther = (MyBusiness)businessCollaboration1.getMyBusiness();
					businessCollaboration1.z_internalRemoveFromMyBusiness(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessCollaboration1(businessCollaboration1);
					}
					businessCollaboration1.z_internalAddToMyBusiness((MyBusiness)this);
				}
				this.z_internalAddToBusinessCollaboration1(businessCollaboration1);
			}
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
	
	public void setPrepareQuote(List<PrepareQuote> prepareQuote) {
		this.clearPrepareQuote();
		this.addAllToPrepareQuote(prepareQuote);
	}
	
	public void setPrepareQuoteImpl(List<PrepareQuoteImpl> prepareQuoteImpl) {
		this.clearPrepareQuoteImpl();
		this.addAllToPrepareQuoteImpl(prepareQuoteImpl);
	}
	
	public void setRepresentedOrganization(IOrganizationNode p) {
		setRepresentedOrganization((OrganizationNode)p);
	}
	
	public void setRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( this.getRepresentedOrganization()!=null ) {
			this.getRepresentedOrganization().z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(getOrganizationAsBusinessComponent_representedOrganization());
		}
		if ( representedOrganization == null ) {
			this.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(this.getOrganizationAsBusinessComponent_representedOrganization());
		} else {
			OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization = new OrganizationAsBusinessComponent((IBusinessComponent)this,(OrganizationNode)representedOrganization);
			this.z_internalAddToOrganizationAsBusinessComponent_representedOrganization(organizationAsBusinessComponent_representedOrganization);
			representedOrganization.z_internalAddToOrganizationAsBusinessComponent_businessComponent(organizationAsBusinessComponent_representedOrganization);
		}
	}
	
	public void setSalesPerson(SalesPerson salesPerson) {
		SalesPerson oldValue = this.getSalesPerson();
		if ( oldValue==null ) {
			if ( salesPerson!=null ) {
				MyBusiness oldOther = (MyBusiness)salesPerson.getMyBusiness();
				salesPerson.z_internalRemoveFromMyBusiness(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromSalesPerson(salesPerson);
				}
				salesPerson.z_internalAddToMyBusiness((MyBusiness)this);
			}
			this.z_internalAddToSalesPerson(salesPerson);
		} else {
			if ( !oldValue.equals(salesPerson) ) {
				oldValue.z_internalRemoveFromMyBusiness(this);
				z_internalRemoveFromSalesPerson(oldValue);
				if ( salesPerson!=null ) {
					MyBusiness oldOther = (MyBusiness)salesPerson.getMyBusiness();
					salesPerson.z_internalRemoveFromMyBusiness(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromSalesPerson(salesPerson);
					}
					salesPerson.z_internalAddToMyBusiness((MyBusiness)this);
				}
				this.z_internalAddToSalesPerson(salesPerson);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<MyBusiness uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<MyBusiness ");
		sb.append("classUuid=\"bpm.uml@_b9ZSMI_cEeKWQLWOG6WHmA\" ");
		sb.append("className=\"bpmmodel.MyBusiness\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getSalesPerson()==null ) {
			sb.append("\n<salesPerson/>");
		} else {
			sb.append("\n<salesPerson propertyId=\"7930574956849624622\">");
			sb.append("\n" + getSalesPerson().toXmlString());
			sb.append("\n</salesPerson>");
		}
		sb.append("\n<backOfficeWorker propertyId=\"8656650025522290088\">");
		for ( BackOfficeWorker backOfficeWorker : getBackOfficeWorker() ) {
			sb.append("\n" + backOfficeWorker.toXmlString());
		}
		sb.append("\n</backOfficeWorker>");
		sb.append("\n<prepareQuote propertyId=\"773142861041519426\">");
		for ( PrepareQuote prepareQuote : getPrepareQuote() ) {
			sb.append("\n" + prepareQuote.toXmlString());
		}
		sb.append("\n</prepareQuote>");
		sb.append("\n<prepareQuoteImpl propertyId=\"679997709189448942\">");
		for ( PrepareQuoteImpl prepareQuoteImpl : getPrepareQuoteImpl() ) {
			sb.append("\n" + prepareQuoteImpl.toXmlString());
		}
		sb.append("\n</prepareQuoteImpl>");
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
		sb.append("\n</MyBusiness>");
		return sb.toString();
	}
	
	public void z_internalAddToBackOfficeWorker(BackOfficeWorker backOfficeWorker) {
		if ( this.backOfficeWorker.contains(backOfficeWorker) ) {
			return;
		}
		this.backOfficeWorker.add(backOfficeWorker);
	}
	
	public void z_internalAddToBusinessCollaboration1(BusinessCollaboration1 businessCollaboration1) {
		if ( businessCollaboration1.equals(this.businessCollaboration1) ) {
			return;
		}
		this.businessCollaboration1=businessCollaboration1;
	}
	
	public void z_internalAddToOrganizationAsBusinessComponent_representedOrganization(OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization) {
		if ( organizationAsBusinessComponent_representedOrganization.equals(getOrganizationAsBusinessComponent_representedOrganization()) ) {
			return;
		}
		this.organizationAsBusinessComponent_representedOrganization=organizationAsBusinessComponent_representedOrganization;
	}
	
	public void z_internalAddToParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		if ( getParticipationParticipant_participation().contains(participationParticipant_participation) ) {
			return;
		}
		this.participationParticipant_participation.add(participationParticipant_participation);
	}
	
	public void z_internalAddToPrepareQuote(PrepareQuote prepareQuote) {
		if ( this.prepareQuote.contains(prepareQuote) ) {
			return;
		}
		this.prepareQuote.add(prepareQuote);
	}
	
	public void z_internalAddToPrepareQuoteImpl(PrepareQuoteImpl prepareQuoteImpl) {
		if ( this.prepareQuoteImpl.contains(prepareQuoteImpl) ) {
			return;
		}
		this.prepareQuoteImpl.add(prepareQuoteImpl);
	}
	
	public void z_internalAddToSalesPerson(SalesPerson salesPerson) {
		if ( salesPerson.equals(this.salesPerson) ) {
			return;
		}
		this.salesPerson=salesPerson;
	}
	
	public void z_internalRemoveFromBackOfficeWorker(BackOfficeWorker backOfficeWorker) {
		this.backOfficeWorker.remove(backOfficeWorker);
	}
	
	public void z_internalRemoveFromBusinessCollaboration1(BusinessCollaboration1 businessCollaboration1) {
		if ( getBusinessCollaboration1()!=null && businessCollaboration1!=null && businessCollaboration1.equals(getBusinessCollaboration1()) ) {
			this.businessCollaboration1=null;
			this.businessCollaboration1=null;
		}
	}
	
	public void z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(OrganizationAsBusinessComponent organizationAsBusinessComponent_representedOrganization) {
		if ( getOrganizationAsBusinessComponent_representedOrganization()!=null && organizationAsBusinessComponent_representedOrganization!=null && organizationAsBusinessComponent_representedOrganization.equals(getOrganizationAsBusinessComponent_representedOrganization()) ) {
			this.organizationAsBusinessComponent_representedOrganization=null;
			this.organizationAsBusinessComponent_representedOrganization=null;
		}
	}
	
	public void z_internalRemoveFromParticipationParticipant_participation(ParticipationParticipant participationParticipant_participation) {
		this.participationParticipant_participation.remove(participationParticipant_participation);
	}
	
	public void z_internalRemoveFromPrepareQuote(PrepareQuote prepareQuote) {
		this.prepareQuote.remove(prepareQuote);
	}
	
	public void z_internalRemoveFromPrepareQuoteImpl(PrepareQuoteImpl prepareQuoteImpl) {
		this.prepareQuoteImpl.remove(prepareQuoteImpl);
	}
	
	public void z_internalRemoveFromSalesPerson(SalesPerson salesPerson) {
		if ( getSalesPerson()!=null && salesPerson!=null && salesPerson.equals(getSalesPerson()) ) {
			this.salesPerson=null;
			this.salesPerson=null;
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