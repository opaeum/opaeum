package org.opaeum.demo1.structuredbusiness.jobs;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.demo1.structuredbusiness.appliance.ApplianceComponent;
import org.opaeum.demo1.structuredbusiness.util.Stdlib;
import org.opaeum.demo1.structuredbusiness.util.StructuredbusinessFormatter;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="914890@_tGMQAJLBEeGnpuq6_ber_Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="appliance_component_sale",schema="structuredbusiness")
@Entity(name="ApplianceComponentSale")
public class ApplianceComponentSale implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="appliance_component_id",nullable=true)
	protected ApplianceComponent applianceComponent;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="cost_price_of_component")
	@Basic
	protected Double costPriceOfComponent;
	@Column(name="cost_to_customer")
	@Basic
	protected Double costToCustomer;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="date_of_sale")
	protected Date dateOfSale;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="appliance_component_sale",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Index(columnNames="job_id",name="idx_appliance_component_sale_job_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="job_id",nullable=true)
	protected Job job;
	static private Set<? extends ApplianceComponentSale> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6796854228468393857l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ApplianceComponentSale(Job owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for ApplianceComponentSale
	 */
	public ApplianceComponentSale() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getJob().z_internalAddToApplianceComponentSale((ApplianceComponentSale)this);
	}
	
	static public Set<? extends ApplianceComponentSale> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.demo1.structuredbusiness.jobs.ApplianceComponentSale.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("dateOfSale").length()>0 ) {
			setDateOfSale(StructuredbusinessFormatter.getInstance().parseDateTime(xml.getAttribute("dateOfSale")));
		}
		if ( xml.getAttribute("costToCustomer").length()>0 ) {
			setCostToCustomer(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("costToCustomer")));
		}
		if ( xml.getAttribute("costPriceOfComponent").length()>0 ) {
			setCostPriceOfComponent(StructuredbusinessFormatter.getInstance().parseReal(xml.getAttribute("costPriceOfComponent")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(ApplianceComponentSale from, ApplianceComponentSale to) {
		to.setDateOfSale(from.getDateOfSale());
		to.setCostToCustomer(from.getCostToCustomer());
		to.setCostPriceOfComponent(from.getCostPriceOfComponent());
	}
	
	public void copyState(ApplianceComponentSale from, ApplianceComponentSale to) {
		to.setDateOfSale(from.getDateOfSale());
		to.setCostToCustomer(from.getCostToCustomer());
		to.setCostPriceOfComponent(from.getCostPriceOfComponent());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ApplianceComponentSale ) {
			return other==this || ((ApplianceComponentSale)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6098139414879603393l,opposite="applianceComponentSale",uuid="914890@_xHDdYJLBEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_xHDdYJLBEeGnpuq6_ber_Q")
	public ApplianceComponent getApplianceComponent() {
		ApplianceComponent result = this.applianceComponent;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8688700823983782316l,uuid="914890@_0sbXoJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_0sbXoJN8EeGzUIOTq-3iUg")
	public Double getCostPriceOfComponent() {
		Double result = this.costPriceOfComponent;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6210166080156242980l,uuid="914890@_nD2CEJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_nD2CEJN8EeGzUIOTq-3iUg")
	public Double getCostToCustomer() {
		Double result = this.costToCustomer;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7427103407087121466l,strategyFactory=DateTimeStrategyFactory.class,uuid="914890@_kXRuIJN8EeGzUIOTq-3iUg")
	@NumlMetaInfo(uuid="914890@_kXRuIJN8EeGzUIOTq-3iUg")
	public Date getDateOfSale() {
		Date result = this.dateOfSale;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7603546578951991557l,opposite="applianceComponentSale",uuid="914890@_w1OtkZLBEeGnpuq6_ber_Q")
	@NumlMetaInfo(uuid="914890@_w1OtkZLBEeGnpuq6_ber_Q")
	public Job getJob() {
		Job result = this.job;
		
		return result;
	}
	
	public String getName() {
		return "ApplianceComponentSale["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getJob();
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
		this.z_internalAddToJob((Job)owner);
		createComponents();
	}
	
	public ApplianceComponentSale makeCopy() {
		ApplianceComponentSale result = new ApplianceComponentSale();
		copyState((ApplianceComponentSale)this,result);
		return result;
	}
	
	public ApplianceComponentSale makeShallowCopy() {
		ApplianceComponentSale result = new ApplianceComponentSale();
		copyShallowState((ApplianceComponentSale)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getJob()!=null ) {
			getJob().z_internalRemoveFromApplianceComponentSale(this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("applianceComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6098139414879603393")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setApplianceComponent((ApplianceComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setApplianceComponent(ApplianceComponent applianceComponent) {
		propertyChangeSupport.firePropertyChange("applianceComponent",getApplianceComponent(),applianceComponent);
		this.z_internalAddToApplianceComponent(applianceComponent);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setCostPriceOfComponent(Double costPriceOfComponent) {
		propertyChangeSupport.firePropertyChange("costPriceOfComponent",getCostPriceOfComponent(),costPriceOfComponent);
		this.z_internalAddToCostPriceOfComponent(costPriceOfComponent);
	}
	
	public void setCostToCustomer(Double costToCustomer) {
		propertyChangeSupport.firePropertyChange("costToCustomer",getCostToCustomer(),costToCustomer);
		this.z_internalAddToCostToCustomer(costToCustomer);
	}
	
	public void setDateOfSale(Date dateOfSale) {
		propertyChangeSupport.firePropertyChange("dateOfSale",getDateOfSale(),dateOfSale);
		this.z_internalAddToDateOfSale(dateOfSale);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setJob(Job job) {
		propertyChangeSupport.firePropertyChange("job",getJob(),job);
		if ( this.getJob()!=null ) {
			this.getJob().z_internalRemoveFromApplianceComponentSale(this);
		}
		if ( job!=null ) {
			job.z_internalAddToApplianceComponentSale(this);
			this.z_internalAddToJob(job);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<ApplianceComponentSale uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ApplianceComponentSale ");
		sb.append("classUuid=\"914890@_tGMQAJLBEeGnpuq6_ber_Q\" ");
		sb.append("className=\"org.opaeum.demo1.structuredbusiness.jobs.ApplianceComponentSale\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDateOfSale()!=null ) {
			sb.append("dateOfSale=\""+ StructuredbusinessFormatter.getInstance().formatDateTime(getDateOfSale())+"\" ");
		}
		if ( getCostToCustomer()!=null ) {
			sb.append("costToCustomer=\""+ StructuredbusinessFormatter.getInstance().formatReal(getCostToCustomer())+"\" ");
		}
		if ( getCostPriceOfComponent()!=null ) {
			sb.append("costPriceOfComponent=\""+ StructuredbusinessFormatter.getInstance().formatReal(getCostPriceOfComponent())+"\" ");
		}
		sb.append(">");
		if ( getApplianceComponent()==null ) {
			sb.append("\n<applianceComponent/>");
		} else {
			sb.append("\n<applianceComponent propertyId=\"6098139414879603393\">");
			sb.append("\n" + getApplianceComponent().toXmlReferenceString());
			sb.append("\n</applianceComponent>");
		}
		sb.append("\n</ApplianceComponentSale>");
		return sb.toString();
	}
	
	public void z_internalAddToApplianceComponent(ApplianceComponent applianceComponent) {
		this.applianceComponent=applianceComponent;
	}
	
	public void z_internalAddToCostPriceOfComponent(Double costPriceOfComponent) {
		this.costPriceOfComponent=costPriceOfComponent;
	}
	
	public void z_internalAddToCostToCustomer(Double costToCustomer) {
		this.costToCustomer=costToCustomer;
	}
	
	public void z_internalAddToDateOfSale(Date dateOfSale) {
		this.dateOfSale=dateOfSale;
	}
	
	public void z_internalAddToJob(Job job) {
		this.job=job;
	}
	
	public void z_internalRemoveFromApplianceComponent(ApplianceComponent applianceComponent) {
		if ( getApplianceComponent()!=null && applianceComponent!=null && applianceComponent.equals(getApplianceComponent()) ) {
			this.applianceComponent=null;
			this.applianceComponent=null;
		}
	}
	
	public void z_internalRemoveFromCostPriceOfComponent(Double costPriceOfComponent) {
		if ( getCostPriceOfComponent()!=null && costPriceOfComponent!=null && costPriceOfComponent.equals(getCostPriceOfComponent()) ) {
			this.costPriceOfComponent=null;
			this.costPriceOfComponent=null;
		}
	}
	
	public void z_internalRemoveFromCostToCustomer(Double costToCustomer) {
		if ( getCostToCustomer()!=null && costToCustomer!=null && costToCustomer.equals(getCostToCustomer()) ) {
			this.costToCustomer=null;
			this.costToCustomer=null;
		}
	}
	
	public void z_internalRemoveFromDateOfSale(Date dateOfSale) {
		if ( getDateOfSale()!=null && dateOfSale!=null && dateOfSale.equals(getDateOfSale()) ) {
			this.dateOfSale=null;
			this.dateOfSale=null;
		}
	}
	
	public void z_internalRemoveFromJob(Job job) {
		if ( getJob()!=null && job!=null && job.equals(getJob()) ) {
			this.job=null;
			this.job=null;
		}
	}

}