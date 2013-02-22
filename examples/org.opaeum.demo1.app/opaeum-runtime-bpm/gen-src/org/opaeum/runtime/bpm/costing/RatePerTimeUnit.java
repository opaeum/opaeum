package org.opaeum.runtime.bpm.costing;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.costing.CurrencyMismatchException;
import org.opaeum.runtime.costing.IRatePerTimeUnit;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.MoneyInDefaultCurrencyStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_zQphYPjuEeGEN6Fz86uBYA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="rate_per_time_unit",schema="bpm")
@Entity(name="RatePerTimeUnit")
public class RatePerTimeUnit implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IRatePerTimeUnit, Serializable {
	@Column(name="additional_cost_to_company")
	protected Double additionalCostToCompany;
	@Column(name="additional_cost_to_company_currency")
	@Basic
	private String additionalCostToCompanyCurrency;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="effective_from")
	protected Date effectiveFrom;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="rate_per_time_unit",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends RatePerTimeUnit> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Column(name="rate_paid_by_company")
	protected Double ratePaidByCompany;
	@Column(name="rate_paid_by_company_currency")
	@Basic
	private String ratePaidByCompanyCurrency;
	@Column(name="rate_paid_by_customer")
	protected Double ratePaidByCustomer;
	@Column(name="rate_paid_by_customer_currency")
	@Basic
	private String ratePaidByCustomerCurrency;
	static final private long serialVersionUID = 885923489080980585l;
	@Type(type="org.opaeum.runtime.domain.BusinessTimeUnitResolver")
	@Column(name="time_unit",nullable=true)
	protected BusinessTimeUnit timeUnit;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="ratePerTimeUnit")
	protected TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_timedResource;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public RatePerTimeUnit(ITimedResource owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for RatePerTimeUnit
	 */
	public RatePerTimeUnit() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getTimedResource().z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(getTimedResourceRatePerTimeUnit_timedResource());
	}
	
	static public Set<? extends RatePerTimeUnit> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.costing.RatePerTimeUnit.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("additionalCostToCompany").length()>0 ) {
			setAdditionalCostToCompany(OpaeumLibraryForBPMFormatter.getInstance().parseMoneyInDefaultCurrency(xml.getAttribute("additionalCostToCompany")));
		}
		if ( xml.getAttribute("ratePaidByCustomer").length()>0 ) {
			setRatePaidByCustomer(OpaeumLibraryForBPMFormatter.getInstance().parseMoneyInDefaultCurrency(xml.getAttribute("ratePaidByCustomer")));
		}
		if ( xml.getAttribute("effectiveFrom").length()>0 ) {
			setEffectiveFrom(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("effectiveFrom")));
		}
		if ( xml.getAttribute("ratePaidByCompany").length()>0 ) {
			setRatePaidByCompany(OpaeumLibraryForBPMFormatter.getInstance().parseMoneyInDefaultCurrency(xml.getAttribute("ratePaidByCompany")));
		}
		if ( xml.getAttribute("timeUnit").length()>0 ) {
			setTimeUnit(BusinessTimeUnit.valueOf(xml.getAttribute("timeUnit")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(RatePerTimeUnit from, RatePerTimeUnit to) {
		to.setAdditionalCostToCompany(from.getAdditionalCostToCompany());
		to.setRatePaidByCustomer(from.getRatePaidByCustomer());
		to.setEffectiveFrom(from.getEffectiveFrom());
		to.setRatePaidByCompany(from.getRatePaidByCompany());
		to.setTimeUnit(from.getTimeUnit());
	}
	
	public void copyState(RatePerTimeUnit from, RatePerTimeUnit to) {
		to.setAdditionalCostToCompany(from.getAdditionalCostToCompany());
		to.setRatePaidByCustomer(from.getRatePaidByCustomer());
		to.setEffectiveFrom(from.getEffectiveFrom());
		to.setRatePaidByCompany(from.getRatePaidByCompany());
		to.setTimeUnit(from.getTimeUnit());
	}
	
	public void createComponents() {
	}
	
	public TimedResourceRatePerTimeUnit createTimedResourceRatePerTimeUnit_timedResource() {
		TimedResourceRatePerTimeUnit newInstance= new TimedResourceRatePerTimeUnit();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof RatePerTimeUnit ) {
			return other==this || ((RatePerTimeUnit)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4706116605044973827l,strategyFactory=MoneyInDefaultCurrencyStrategyFactory.class,uuid="252060@_7PmU8PjuEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_7PmU8PjuEeGEN6Fz86uBYA")
	public Double getAdditionalCostToCompany() {
		Double result = this.additionalCostToCompany;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1483228793240051475l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_a_NhUPjvEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_a_NhUPjvEeGEN6Fz86uBYA")
	public Date getEffectiveFrom() {
		Date result = this.effectiveFrom;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "RatePerTimeUnit["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getTimedResource();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5675322823320976606l,strategyFactory=MoneyInDefaultCurrencyStrategyFactory.class,uuid="252060@_rjhC4Pl4EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_rjhC4Pl4EeGYn9aDKdPmKA")
	public Double getRatePaidByCompany() {
		Double result = this.ratePaidByCompany;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8302913504782687443l,strategyFactory=MoneyInDefaultCurrencyStrategyFactory.class,uuid="252060@_5KnlcPjuEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_5KnlcPjuEeGEN6Fz86uBYA")
	public Double getRatePaidByCustomer() {
		Double result = this.ratePaidByCustomer;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=891734682457543536l,uuid="252060@_s4gDYPl5EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_s4gDYPl5EeGYn9aDKdPmKA")
	public BusinessTimeUnit getTimeUnit() {
		BusinessTimeUnit result = this.timeUnit;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8451496134474259219l,opposite="ratePerTimeUnit",uuid="252060@_dIQKEfjyEeGEN6Fz86uBYA")
	public ITimedResource getTimedResource() {
		ITimedResource result = null;
		if ( this.timedResourceRatePerTimeUnit_timedResource!=null ) {
			result = this.timedResourceRatePerTimeUnit_timedResource.getTimedResource();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7675589196265913918l,opposite="ratePerTimeUnit",uuid="252060@_dIQKEPjyEeGEN6Fz86uBYA")
	@NumlMetaInfo(uuid="252060@_zQphYPjuEeGEN6Fz86uBYA@252060@_dIQKEPjyEeGEN6Fz86uBYA")
	public TimedResourceRatePerTimeUnit getTimedResourceRatePerTimeUnit_timedResource() {
		TimedResourceRatePerTimeUnit result = this.timedResourceRatePerTimeUnit_timedResource;
		
		return result;
	}
	
	public TimedResourceRatePerTimeUnit getTimedResourceRatePerTimeUnit_timedResourceFor(ITimedResource match) {
		if ( timedResourceRatePerTimeUnit_timedResource.getTimedResource().equals(match) ) {
			return timedResourceRatePerTimeUnit_timedResource;
		} else {
			return null;
		}
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
		this.z_internalAddToTimedResource((ITimedResource)owner);
		createComponents();
	}
	
	public RatePerTimeUnit makeCopy() {
		RatePerTimeUnit result = new RatePerTimeUnit();
		copyState((RatePerTimeUnit)this,result);
		return result;
	}
	
	public RatePerTimeUnit makeShallowCopy() {
		RatePerTimeUnit result = new RatePerTimeUnit();
		copyShallowState((RatePerTimeUnit)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getTimedResource()!=null ) {
			getTimedResource().z_internalRemoveFromRatePerTimeUnit(this);
		}
		if ( getTimedResourceRatePerTimeUnit_timedResource()!=null ) {
			getTimedResourceRatePerTimeUnit_timedResource().markDeleted();
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
		
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setAdditionalCostToCompany(Double additionalCostToCompany) {
		propertyChangeSupport.firePropertyChange("additionalCostToCompany",getAdditionalCostToCompany(),additionalCostToCompany);
		this.z_internalAddToAdditionalCostToCompany(additionalCostToCompany);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setEffectiveFrom(Date effectiveFrom) {
		propertyChangeSupport.firePropertyChange("effectiveFrom",getEffectiveFrom(),effectiveFrom);
		this.z_internalAddToEffectiveFrom(effectiveFrom);
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
	
	public void setRatePaidByCompany(Double ratePaidByCompany) {
		propertyChangeSupport.firePropertyChange("ratePaidByCompany",getRatePaidByCompany(),ratePaidByCompany);
		this.z_internalAddToRatePaidByCompany(ratePaidByCompany);
	}
	
	public void setRatePaidByCustomer(Double ratePaidByCustomer) {
		propertyChangeSupport.firePropertyChange("ratePaidByCustomer",getRatePaidByCustomer(),ratePaidByCustomer);
		this.z_internalAddToRatePaidByCustomer(ratePaidByCustomer);
	}
	
	public void setTimeUnit(BusinessTimeUnit timeUnit) {
		propertyChangeSupport.firePropertyChange("timeUnit",getTimeUnit(),timeUnit);
		this.z_internalAddToTimeUnit(timeUnit);
	}
	
	public void setTimedResource(ITimedResource timedResource) {
		propertyChangeSupport.firePropertyChange("timedResource",getTimedResource(),timedResource);
		if ( this.getTimedResource()!=null ) {
			this.getTimedResource().z_internalRemoveFromRatePerTimeUnit(this);
		}
		if ( timedResource!=null ) {
			this.z_internalAddToTimedResource(timedResource);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setTimedResourceRatePerTimeUnit_timedResource(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_timedResource) {
		TimedResourceRatePerTimeUnit oldValue = this.getTimedResourceRatePerTimeUnit_timedResource();
		propertyChangeSupport.firePropertyChange("timedResourceRatePerTimeUnit_timedResource",getTimedResourceRatePerTimeUnit_timedResource(),timedResourceRatePerTimeUnit_timedResource);
		if ( oldValue==null ) {
			if ( timedResourceRatePerTimeUnit_timedResource!=null ) {
				RatePerTimeUnit oldOther = (RatePerTimeUnit)timedResourceRatePerTimeUnit_timedResource.getRatePerTimeUnit();
				timedResourceRatePerTimeUnit_timedResource.z_internalRemoveFromRatePerTimeUnit(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromTimedResourceRatePerTimeUnit_timedResource(timedResourceRatePerTimeUnit_timedResource);
				}
				timedResourceRatePerTimeUnit_timedResource.z_internalAddToRatePerTimeUnit((RatePerTimeUnit)this);
			}
			this.z_internalAddToTimedResourceRatePerTimeUnit_timedResource(timedResourceRatePerTimeUnit_timedResource);
		} else {
			if ( !oldValue.equals(timedResourceRatePerTimeUnit_timedResource) ) {
				oldValue.z_internalRemoveFromRatePerTimeUnit(this);
				z_internalRemoveFromTimedResourceRatePerTimeUnit_timedResource(oldValue);
				if ( timedResourceRatePerTimeUnit_timedResource!=null ) {
					RatePerTimeUnit oldOther = (RatePerTimeUnit)timedResourceRatePerTimeUnit_timedResource.getRatePerTimeUnit();
					timedResourceRatePerTimeUnit_timedResource.z_internalRemoveFromRatePerTimeUnit(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromTimedResourceRatePerTimeUnit_timedResource(timedResourceRatePerTimeUnit_timedResource);
					}
					timedResourceRatePerTimeUnit_timedResource.z_internalAddToRatePerTimeUnit((RatePerTimeUnit)this);
				}
				this.z_internalAddToTimedResourceRatePerTimeUnit_timedResource(timedResourceRatePerTimeUnit_timedResource);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<RatePerTimeUnit uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<RatePerTimeUnit ");
		sb.append("classUuid=\"252060@_zQphYPjuEeGEN6Fz86uBYA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.costing.RatePerTimeUnit\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAdditionalCostToCompany()!=null ) {
			sb.append("additionalCostToCompany=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMoneyInDefaultCurrency(getAdditionalCostToCompany())+"\" ");
		}
		if ( getRatePaidByCustomer()!=null ) {
			sb.append("ratePaidByCustomer=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMoneyInDefaultCurrency(getRatePaidByCustomer())+"\" ");
		}
		if ( getEffectiveFrom()!=null ) {
			sb.append("effectiveFrom=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getEffectiveFrom())+"\" ");
		}
		if ( getRatePaidByCompany()!=null ) {
			sb.append("ratePaidByCompany=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMoneyInDefaultCurrency(getRatePaidByCompany())+"\" ");
		}
		if ( getTimeUnit()!=null ) {
			sb.append("timeUnit=\""+ getTimeUnit().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</RatePerTimeUnit>");
		return sb.toString();
	}
	
	public void z_internalAddToAdditionalCostToCompany(Double additionalCostToCompany) {
		if ( additionalCostToCompanyCurrency!=null && !additionalCostToCompanyCurrency.equals(org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency()) ) {
			throw new CurrencyMismatchException(additionalCostToCompanyCurrency,org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency());
		}
		this.additionalCostToCompany=additionalCostToCompany;
	}
	
	public void z_internalAddToEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom=effectiveFrom;
	}
	
	public void z_internalAddToRatePaidByCompany(Double ratePaidByCompany) {
		if ( ratePaidByCompanyCurrency!=null && !ratePaidByCompanyCurrency.equals(org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency()) ) {
			throw new CurrencyMismatchException(ratePaidByCompanyCurrency,org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency());
		}
		this.ratePaidByCompany=ratePaidByCompany;
	}
	
	public void z_internalAddToRatePaidByCustomer(Double ratePaidByCustomer) {
		if ( ratePaidByCustomerCurrency!=null && !ratePaidByCustomerCurrency.equals(org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency()) ) {
			throw new CurrencyMismatchException(ratePaidByCustomerCurrency,org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency());
		}
		this.ratePaidByCustomer=ratePaidByCustomer;
	}
	
	public void z_internalAddToTimeUnit(BusinessTimeUnit timeUnit) {
		this.timeUnit=timeUnit;
	}
	
	public void z_internalAddToTimedResource(ITimedResource timedResource) {
		TimedResourceRatePerTimeUnit newOne = new TimedResourceRatePerTimeUnit(this,timedResource);
		this.z_internalAddToTimedResourceRatePerTimeUnit_timedResource(newOne);
		newOne.getTimedResource().z_internalAddToTimedResourceRatePerTimeUnit_ratePerTimeUnit(newOne);
	}
	
	public void z_internalAddToTimedResourceRatePerTimeUnit_timedResource(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_timedResource) {
		this.timedResourceRatePerTimeUnit_timedResource=timedResourceRatePerTimeUnit_timedResource;
	}
	
	public void z_internalRemoveFromAdditionalCostToCompany(Double additionalCostToCompany) {
		if ( getAdditionalCostToCompany()!=null && additionalCostToCompany!=null && additionalCostToCompany.equals(getAdditionalCostToCompany()) ) {
			this.additionalCostToCompany=null;
			this.additionalCostToCompany=null;
		}
	}
	
	public void z_internalRemoveFromEffectiveFrom(Date effectiveFrom) {
		if ( getEffectiveFrom()!=null && effectiveFrom!=null && effectiveFrom.equals(getEffectiveFrom()) ) {
			this.effectiveFrom=null;
			this.effectiveFrom=null;
		}
	}
	
	public void z_internalRemoveFromRatePaidByCompany(Double ratePaidByCompany) {
		if ( getRatePaidByCompany()!=null && ratePaidByCompany!=null && ratePaidByCompany.equals(getRatePaidByCompany()) ) {
			this.ratePaidByCompany=null;
			this.ratePaidByCompany=null;
		}
	}
	
	public void z_internalRemoveFromRatePaidByCustomer(Double ratePaidByCustomer) {
		if ( getRatePaidByCustomer()!=null && ratePaidByCustomer!=null && ratePaidByCustomer.equals(getRatePaidByCustomer()) ) {
			this.ratePaidByCustomer=null;
			this.ratePaidByCustomer=null;
		}
	}
	
	public void z_internalRemoveFromTimeUnit(BusinessTimeUnit timeUnit) {
		if ( getTimeUnit()!=null && timeUnit!=null && timeUnit.equals(getTimeUnit()) ) {
			this.timeUnit=null;
			this.timeUnit=null;
		}
	}
	
	public void z_internalRemoveFromTimedResource(ITimedResource timedResource) {
		if ( this.timedResourceRatePerTimeUnit_timedResource!=null ) {
			this.timedResourceRatePerTimeUnit_timedResource.clear();
		}
	}
	
	public void z_internalRemoveFromTimedResourceRatePerTimeUnit_timedResource(TimedResourceRatePerTimeUnit timedResourceRatePerTimeUnit_timedResource) {
		if ( getTimedResourceRatePerTimeUnit_timedResource()!=null && timedResourceRatePerTimeUnit_timedResource!=null && timedResourceRatePerTimeUnit_timedResource.equals(getTimedResourceRatePerTimeUnit_timedResource()) ) {
			this.timedResourceRatePerTimeUnit_timedResource=null;
			this.timedResourceRatePerTimeUnit_timedResource=null;
		}
	}

}