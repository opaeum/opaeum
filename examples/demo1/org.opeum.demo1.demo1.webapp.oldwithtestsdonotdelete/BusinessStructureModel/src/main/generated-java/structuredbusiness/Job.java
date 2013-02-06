package structuredbusiness;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import structuredbusiness.util.Stdlib;
import structuredbusiness.util.StructuredbusinessFormatter;

@AuditMe
@NumlMetaInfo(uuid="914890@_PgzU8JLAEeGnpuq6_ber_Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="job")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Job")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Job implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="job",targetEntity=Activity.class)
	private Set<Activity> activity = new HashSet<Activity>();
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="job",targetEntity=ApplianceComponentSale.class)
	private Set<ApplianceComponentSale> applianceComponentSale = new HashSet<ApplianceComponentSale>();
	@Index(columnNames="branch_id",name="idx_job_branch_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="branch_id",nullable=true)
	private Branch branch;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="cost_to_company")
	private Double costToCompany;
	@Index(columnNames="customer_assistant_id",name="idx_job_customer_assistant_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="customer_assistant_id",nullable=true)
	private CustomerAssistant customerAssistant;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="foreman_id",nullable=true)
	private Technician foreman;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Job> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 2516037382701304091l;
	@Column(name="time_in_hours")
	private Double timeInHours;
	@Column(name="total_cost")
	private Double totalCost;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Job(Branch owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Job
	 */
	public Job() {
	}

	public void addAllToActivity(Set<Activity> activity) {
		for ( Activity o : activity ) {
			addToActivity(o);
		}
	}
	
	public void addAllToApplianceComponentSale(Set<ApplianceComponentSale> applianceComponentSale) {
		for ( ApplianceComponentSale o : applianceComponentSale ) {
			addToApplianceComponentSale(o);
		}
	}
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	public void addToActivity(Activity activity) {
		if ( activity!=null ) {
			activity.z_internalRemoveFromJob(activity.getJob());
			activity.z_internalAddToJob(this);
			z_internalAddToActivity(activity);
		}
	}
	
	public void addToApplianceComponentSale(ApplianceComponentSale applianceComponentSale) {
		if ( applianceComponentSale!=null ) {
			applianceComponentSale.z_internalRemoveFromJob(applianceComponentSale.getJob());
			applianceComponentSale.z_internalAddToJob(this);
			z_internalAddToApplianceComponentSale(applianceComponentSale);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBranch().z_internalAddToJob((Job)this);
	}
	
	static public Set<? extends Job> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(structuredbusiness.Job.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("totalCost").length()>0 ) {
			setTotalCost(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("totalCost")));
		}
		if ( xml.getAttribute("costToCompany").length()>0 ) {
			setCostToCompany(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("costToCompany")));
		}
		if ( xml.getAttribute("timeInHours").length()>0 ) {
			setTimeInHours(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("timeInHours")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceComponentSale") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4717846436910248785")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ApplianceComponentSale curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToApplianceComponentSale(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("activity") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("259316119968393399")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Activity curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToActivity(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceComponentSale") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4717846436910248785")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ApplianceComponentSale curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToApplianceComponentSale(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("activity") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("259316119968393399")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Activity curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToActivity(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearActivity() {
		removeAllFromActivity(getActivity());
	}
	
	public void clearApplianceComponentSale() {
		removeAllFromApplianceComponentSale(getApplianceComponentSale());
	}
	
	public void copyShallowState(Job from, Job to) {
		to.setTotalCost(from.getTotalCost());
		to.setCostToCompany(from.getCostToCompany());
		to.setTimeInHours(from.getTimeInHours());
	}
	
	public void copyState(Job from, Job to) {
		for ( ApplianceComponentSale child : from.getApplianceComponentSale() ) {
			to.addToApplianceComponentSale(child.makeCopy());
		}
		for ( Activity child : from.getActivity() ) {
			to.addToActivity(child.makeCopy());
		}
		to.setTotalCost(from.getTotalCost());
		to.setCostToCompany(from.getCostToCompany());
		to.setTimeInHours(from.getTimeInHours());
		for ( ApplianceComponentSale child : from.getApplianceComponentSale() ) {
			to.addToApplianceComponentSale(child.makeCopy());
		}
		for ( Activity child : from.getActivity() ) {
			to.addToActivity(child.makeCopy());
		}
	}
	
	public Activity createActivity() {
		Activity newInstance= new Activity();
		newInstance.init(this);
		return newInstance;
	}
	
	public ApplianceComponentSale createApplianceComponentSale() {
		ApplianceComponentSale newInstance= new ApplianceComponentSale();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Job ) {
			return other==this || ((Job)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=259316119968393399l,opposite="job",uuid="914890@_F6QmkJLCEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_F6QmkJLCEeGnpuq6_ber_Q")
	public Set<Activity> getActivity() {
		Set<Activity> result = this.activity;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4717846436910248785l,opposite="job",uuid="914890@_w1NfcJLBEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_w1NfcJLBEeGnpuq6_ber_Q")
	public Set<ApplianceComponentSale> getApplianceComponentSale() {
		Set<ApplianceComponentSale> result = this.applianceComponentSale;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2362331321287149689l,opposite="job",uuid="914890@_QcPpgZLAEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_QcPpgZLAEeGnpuq6_ber_Q")
	public Branch getBranch() {
		Branch result = this.branch;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=253605700129942225l,uuid="914890@_oF8YAJQbEeGJg4MkDnRIeA")
	@NumlMetaInfo(uuid="914890@_oF8YAJQbEeGJg4MkDnRIeA")
	public Double getCostToCompany() {
		Double result = this.costToCompany;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2334130007322557831l,opposite="job",uuid="914890@_YMgg0JLAEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_YMgg0JLAEeGnpuq6_ber_Q")
	public CustomerAssistant getCustomerAssistant() {
		CustomerAssistant result = this.customerAssistant;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8444045662644766029l,opposite="job",uuid="914890@_eQfxoJLCEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_eQfxoJLCEeGnpuq6_ber_Q")
	public Technician getForeman() {
		Technician result = this.foreman;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Job["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBranch();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2445396184547786077l,uuid="914890@_dbubkJQcEeGJg4MkDnRIeA")
	@NumlMetaInfo(uuid="914890@_dbubkJQcEeGJg4MkDnRIeA")
	public Double getTimeInHours() {
		Double result = this.timeInHours;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1905004093031379355l,uuid="914890@_sd0zsJQaEeGTXL33HwWNwQ")
	@NumlMetaInfo(uuid="914890@_sd0zsJQaEeGTXL33HwWNwQ")
	public Double getTotalCost() {
		Double result = this.totalCost;
		
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
		this.z_internalAddToBranch((Branch)owner);
		createComponents();
	}
	
	public Job makeCopy() {
		Job result = new Job();
		copyState((Job)this,result);
		return result;
	}
	
	public Job makeShallowCopy() {
		Job result = new Job();
		copyShallowState((Job)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getCustomerAssistant()!=null ) {
			getCustomerAssistant().z_internalRemoveFromJob(this);
		}
		if ( getBranch()!=null ) {
			getBranch().z_internalRemoveFromJob(this);
		}
		if ( getCustomerAssistant()!=null ) {
			getCustomerAssistant().z_internalRemoveFromJob(this);
		}
		for ( ApplianceComponentSale child : new ArrayList<ApplianceComponentSale>(getApplianceComponentSale()) ) {
			child.markDeleted();
		}
		for ( Activity child : new ArrayList<Activity>(getActivity()) ) {
			child.markDeleted();
		}
		for ( ApplianceComponentSale child : new ArrayList<ApplianceComponentSale>(getApplianceComponentSale()) ) {
			child.markDeleted();
		}
		for ( Activity child : new ArrayList<Activity>(getActivity()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Job> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customerAssistant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2334130007322557831")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setCustomerAssistant((CustomerAssistant)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceComponentSale") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4717846436910248785")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceComponentSale)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("activity") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("259316119968393399")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Activity)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("foreman") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8444045662644766029")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setForeman((Technician)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("customerAssistant") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2334130007322557831")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setCustomerAssistant((CustomerAssistant)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceComponentSale") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4717846436910248785")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ApplianceComponentSale)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("activity") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("259316119968393399")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Activity)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("foreman") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8444045662644766029")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setForeman((Technician)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeAllFromActivity(Set<Activity> activity) {
		Set<Activity> tmp = new HashSet<Activity>(activity);
		for ( Activity o : tmp ) {
			removeFromActivity(o);
		}
	}
	
	public void removeAllFromApplianceComponentSale(Set<ApplianceComponentSale> applianceComponentSale) {
		Set<ApplianceComponentSale> tmp = new HashSet<ApplianceComponentSale>(applianceComponentSale);
		for ( ApplianceComponentSale o : tmp ) {
			removeFromApplianceComponentSale(o);
		}
	}
	
	public void removeFromActivity(Activity activity) {
		if ( activity!=null ) {
			activity.z_internalRemoveFromJob(this);
			z_internalRemoveFromActivity(activity);
		}
	}
	
	public void removeFromApplianceComponentSale(ApplianceComponentSale applianceComponentSale) {
		if ( applianceComponentSale!=null ) {
			applianceComponentSale.z_internalRemoveFromJob(this);
			z_internalRemoveFromApplianceComponentSale(applianceComponentSale);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setActivity(Set<Activity> activity) {
		propertyChangeSupport.firePropertyChange("activity",getActivity(),activity);
		this.clearActivity();
		this.addAllToActivity(activity);
	}
	
	public void setApplianceComponentSale(Set<ApplianceComponentSale> applianceComponentSale) {
		propertyChangeSupport.firePropertyChange("applianceComponentSale",getApplianceComponentSale(),applianceComponentSale);
		this.clearApplianceComponentSale();
		this.addAllToApplianceComponentSale(applianceComponentSale);
	}
	
	public void setBranch(Branch branch) {
		propertyChangeSupport.firePropertyChange("branch",getBranch(),branch);
		if ( this.getBranch()!=null ) {
			this.getBranch().z_internalRemoveFromJob(this);
		}
		if ( branch!=null ) {
			branch.z_internalAddToJob(this);
			this.z_internalAddToBranch(branch);
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCostToCompany(Double costToCompany) {
		propertyChangeSupport.firePropertyChange("costToCompany",getCostToCompany(),costToCompany);
		this.z_internalAddToCostToCompany(costToCompany);
	}
	
	public void setCustomerAssistant(CustomerAssistant customerAssistant) {
		propertyChangeSupport.firePropertyChange("customerAssistant",getCustomerAssistant(),customerAssistant);
		if ( this.getCustomerAssistant()!=null ) {
			this.getCustomerAssistant().z_internalRemoveFromJob(this);
		}
		if ( customerAssistant!=null ) {
			customerAssistant.z_internalAddToJob(this);
			this.z_internalAddToCustomerAssistant(customerAssistant);
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setForeman(Technician foreman) {
		propertyChangeSupport.firePropertyChange("foreman",getForeman(),foreman);
		this.z_internalAddToForeman(foreman);
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
	
	public void setTimeInHours(Double timeInHours) {
		propertyChangeSupport.firePropertyChange("timeInHours",getTimeInHours(),timeInHours);
		this.z_internalAddToTimeInHours(timeInHours);
	}
	
	public void setTotalCost(Double totalCost) {
		propertyChangeSupport.firePropertyChange("totalCost",getTotalCost(),totalCost);
		this.z_internalAddToTotalCost(totalCost);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Job uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Job ");
		sb.append("classUuid=\"914890@_PgzU8JLAEeGnpuq6_ber_Q\" ");
		sb.append("className=\"structuredbusiness.Job\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getTotalCost()!=null ) {
			sb.append("totalCost=\""+ StructuredbusinessFormatter.getInstance().formatReal(getTotalCost())+"\" ");
		}
		if ( getCostToCompany()!=null ) {
			sb.append("costToCompany=\""+ StructuredbusinessFormatter.getInstance().formatReal(getCostToCompany())+"\" ");
		}
		if ( getTimeInHours()!=null ) {
			sb.append("timeInHours=\""+ StructuredbusinessFormatter.getInstance().formatReal(getTimeInHours())+"\" ");
		}
		sb.append(">");
		if ( getCustomerAssistant()==null ) {
			sb.append("\n<customerAssistant/>");
		} else {
			sb.append("\n<customerAssistant propertyId=\"2334130007322557831\">");
			sb.append("\n" + getCustomerAssistant().toXmlReferenceString());
			sb.append("\n</customerAssistant>");
		}
		sb.append("\n<applianceComponentSale propertyId=\"4717846436910248785\">");
		for ( ApplianceComponentSale applianceComponentSale : getApplianceComponentSale() ) {
			sb.append("\n" + applianceComponentSale.toXmlString());
		}
		sb.append("\n</applianceComponentSale>");
		sb.append("\n<activity propertyId=\"259316119968393399\">");
		for ( Activity activity : getActivity() ) {
			sb.append("\n" + activity.toXmlString());
		}
		sb.append("\n</activity>");
		if ( getForeman()==null ) {
			sb.append("\n<foreman/>");
		} else {
			sb.append("\n<foreman propertyId=\"8444045662644766029\">");
			sb.append("\n" + getForeman().toXmlReferenceString());
			sb.append("\n</foreman>");
		}
		if ( getCustomerAssistant()==null ) {
			sb.append("\n<customerAssistant/>");
		} else {
			sb.append("\n<customerAssistant propertyId=\"2334130007322557831\">");
			sb.append("\n" + getCustomerAssistant().toXmlReferenceString());
			sb.append("\n</customerAssistant>");
		}
		sb.append("\n<applianceComponentSale propertyId=\"4717846436910248785\">");
		for ( ApplianceComponentSale applianceComponentSale : getApplianceComponentSale() ) {
			sb.append("\n" + applianceComponentSale.toXmlString());
		}
		sb.append("\n</applianceComponentSale>");
		sb.append("\n<activity propertyId=\"259316119968393399\">");
		for ( Activity activity : getActivity() ) {
			sb.append("\n" + activity.toXmlString());
		}
		sb.append("\n</activity>");
		if ( getForeman()==null ) {
			sb.append("\n<foreman/>");
		} else {
			sb.append("\n<foreman propertyId=\"8444045662644766029\">");
			sb.append("\n" + getForeman().toXmlReferenceString());
			sb.append("\n</foreman>");
		}
		sb.append("\n</Job>");
		return sb.toString();
	}
	
	public void z_internalAddToActivity(Activity val) {
		this.activity.add(val);
	}
	
	public void z_internalAddToApplianceComponentSale(ApplianceComponentSale val) {
		this.applianceComponentSale.add(val);
	}
	
	public void z_internalAddToBranch(Branch val) {
		this.branch=val;
	}
	
	public void z_internalAddToCostToCompany(Double val) {
		this.costToCompany=val;
	}
	
	public void z_internalAddToCustomerAssistant(CustomerAssistant val) {
		this.customerAssistant=val;
	}
	
	public void z_internalAddToForeman(Technician val) {
		this.foreman=val;
	}
	
	public void z_internalAddToTimeInHours(Double val) {
		this.timeInHours=val;
	}
	
	public void z_internalAddToTotalCost(Double val) {
		this.totalCost=val;
	}
	
	public void z_internalRemoveFromActivity(Activity val) {
		this.activity.remove(val);
	}
	
	public void z_internalRemoveFromApplianceComponentSale(ApplianceComponentSale val) {
		this.applianceComponentSale.remove(val);
	}
	
	public void z_internalRemoveFromBranch(Branch val) {
		if ( getBranch()!=null && val!=null && val.equals(getBranch()) ) {
			this.branch=null;
			this.branch=null;
		}
	}
	
	public void z_internalRemoveFromCostToCompany(Double val) {
		if ( getCostToCompany()!=null && val!=null && val.equals(getCostToCompany()) ) {
			this.costToCompany=null;
			this.costToCompany=null;
		}
	}
	
	public void z_internalRemoveFromCustomerAssistant(CustomerAssistant val) {
		if ( getCustomerAssistant()!=null && val!=null && val.equals(getCustomerAssistant()) ) {
			this.customerAssistant=null;
			this.customerAssistant=null;
		}
	}
	
	public void z_internalRemoveFromForeman(Technician val) {
		if ( getForeman()!=null && val!=null && val.equals(getForeman()) ) {
			this.foreman=null;
			this.foreman=null;
		}
	}
	
	public void z_internalRemoveFromTimeInHours(Double val) {
		if ( getTimeInHours()!=null && val!=null && val.equals(getTimeInHours()) ) {
			this.timeInHours=null;
			this.timeInHours=null;
		}
	}
	
	public void z_internalRemoveFromTotalCost(Double val) {
		if ( getTotalCost()!=null && val!=null && val.equals(getTotalCost()) ) {
			this.totalCost=null;
			this.totalCost=null;
		}
	}

}