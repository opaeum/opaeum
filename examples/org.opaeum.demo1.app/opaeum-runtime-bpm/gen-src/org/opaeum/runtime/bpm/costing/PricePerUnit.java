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
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.costing.CurrencyMismatchException;
import org.opaeum.runtime.costing.IPricePerUnit;
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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_p2yl4Pl5EeGYn9aDKdPmKA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="price_per_unit",schema="bpm")
@Entity(name="PricePerUnit")
public class PricePerUnit implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IPricePerUnit, Serializable {
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
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="price_per_unit",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends PricePerUnit> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Column(name="price_paid_by_company")
	protected Double pricePaidByCompany;
	@Column(name="price_paid_by_company_currency")
	@Basic
	private String pricePaidByCompanyCurrency;
	@Column(name="price_paid_by_customer")
	protected Double pricePaidByCustomer;
	@Column(name="price_paid_by_customer_currency")
	@Basic
	private String pricePaidByCustomerCurrency;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="pricePerUnit")
	protected QuantifiedResourcePricePerUnit quantifiedResourcePricePerUnit_quantifiedResource;
	static final private long serialVersionUID = 1981280689266683064l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PricePerUnit(IQuantifiedResource owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PricePerUnit
	 */
	public PricePerUnit() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getQuantifiedResource().z_internalAddToQuantifiedResourcePricePerUnit_pricePerUnit(getQuantifiedResourcePricePerUnit_quantifiedResource());
	}
	
	static public Set<? extends PricePerUnit> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.costing.PricePerUnit.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("additionalCostToCompany").length()>0 ) {
			setAdditionalCostToCompany(OpaeumLibraryForBPMFormatter.getInstance().parseMoneyInDefaultCurrency(xml.getAttribute("additionalCostToCompany")));
		}
		if ( xml.getAttribute("pricePaidByCustomer").length()>0 ) {
			setPricePaidByCustomer(OpaeumLibraryForBPMFormatter.getInstance().parseMoneyInDefaultCurrency(xml.getAttribute("pricePaidByCustomer")));
		}
		if ( xml.getAttribute("pricePaidByCompany").length()>0 ) {
			setPricePaidByCompany(OpaeumLibraryForBPMFormatter.getInstance().parseMoneyInDefaultCurrency(xml.getAttribute("pricePaidByCompany")));
		}
		if ( xml.getAttribute("effectiveFrom").length()>0 ) {
			setEffectiveFrom(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("effectiveFrom")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(PricePerUnit from, PricePerUnit to) {
		to.setAdditionalCostToCompany(from.getAdditionalCostToCompany());
		to.setPricePaidByCustomer(from.getPricePaidByCustomer());
		to.setPricePaidByCompany(from.getPricePaidByCompany());
		to.setEffectiveFrom(from.getEffectiveFrom());
	}
	
	public void copyState(PricePerUnit from, PricePerUnit to) {
		to.setAdditionalCostToCompany(from.getAdditionalCostToCompany());
		to.setPricePaidByCustomer(from.getPricePaidByCustomer());
		to.setPricePaidByCompany(from.getPricePaidByCompany());
		to.setEffectiveFrom(from.getEffectiveFrom());
	}
	
	public void createComponents() {
	}
	
	public QuantifiedResourcePricePerUnit createQuantifiedResourcePricePerUnit_quantifiedResource() {
		QuantifiedResourcePricePerUnit newInstance= new QuantifiedResourcePricePerUnit();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PricePerUnit ) {
			return other==this || ((PricePerUnit)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=560988961507753114l,strategyFactory=MoneyInDefaultCurrencyStrategyFactory.class,uuid="252060@_Gm8XgPl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_Gm8XgPl6EeGYn9aDKdPmKA")
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7566527400618681622l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_bQkG4Pl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_bQkG4Pl6EeGYn9aDKdPmKA")
	public Date getEffectiveFrom() {
		Date result = this.effectiveFrom;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "PricePerUnit["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getQuantifiedResource();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3325912029485802836l,strategyFactory=MoneyInDefaultCurrencyStrategyFactory.class,uuid="252060@_R4yfMPl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_R4yfMPl6EeGYn9aDKdPmKA")
	public Double getPricePaidByCompany() {
		Double result = this.pricePaidByCompany;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5069195214693720544l,strategyFactory=MoneyInDefaultCurrencyStrategyFactory.class,uuid="252060@_PCPI0Pl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_PCPI0Pl6EeGYn9aDKdPmKA")
	public Double getPricePaidByCustomer() {
		Double result = this.pricePaidByCustomer;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8964988229868479048l,opposite="pricePerUnit",uuid="252060@_gDOkofl6EeGYn9aDKdPmKA")
	public IQuantifiedResource getQuantifiedResource() {
		IQuantifiedResource result = null;
		if ( this.quantifiedResourcePricePerUnit_quantifiedResource!=null ) {
			result = this.quantifiedResourcePricePerUnit_quantifiedResource.getQuantifiedResource();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=6206988957384598229l,opposite="pricePerUnit",uuid="252060@_gDOkoPl6EeGYn9aDKdPmKA")
	@NumlMetaInfo(uuid="252060@_p2yl4Pl5EeGYn9aDKdPmKA@252060@_gDOkoPl6EeGYn9aDKdPmKA")
	public QuantifiedResourcePricePerUnit getQuantifiedResourcePricePerUnit_quantifiedResource() {
		QuantifiedResourcePricePerUnit result = this.quantifiedResourcePricePerUnit_quantifiedResource;
		
		return result;
	}
	
	public QuantifiedResourcePricePerUnit getQuantifiedResourcePricePerUnit_quantifiedResourceFor(IQuantifiedResource match) {
		if ( quantifiedResourcePricePerUnit_quantifiedResource.getQuantifiedResource().equals(match) ) {
			return quantifiedResourcePricePerUnit_quantifiedResource;
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
		this.z_internalAddToQuantifiedResource((IQuantifiedResource)owner);
		createComponents();
	}
	
	public PricePerUnit makeCopy() {
		PricePerUnit result = new PricePerUnit();
		copyState((PricePerUnit)this,result);
		return result;
	}
	
	public PricePerUnit makeShallowCopy() {
		PricePerUnit result = new PricePerUnit();
		copyShallowState((PricePerUnit)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getQuantifiedResource()!=null ) {
			getQuantifiedResource().z_internalRemoveFromPricePerUnit(this);
		}
		if ( getQuantifiedResourcePricePerUnit_quantifiedResource()!=null ) {
			getQuantifiedResourcePricePerUnit_quantifiedResource().markDeleted();
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
	
	public void setPricePaidByCompany(Double pricePaidByCompany) {
		propertyChangeSupport.firePropertyChange("pricePaidByCompany",getPricePaidByCompany(),pricePaidByCompany);
		this.z_internalAddToPricePaidByCompany(pricePaidByCompany);
	}
	
	public void setPricePaidByCustomer(Double pricePaidByCustomer) {
		propertyChangeSupport.firePropertyChange("pricePaidByCustomer",getPricePaidByCustomer(),pricePaidByCustomer);
		this.z_internalAddToPricePaidByCustomer(pricePaidByCustomer);
	}
	
	public void setQuantifiedResource(IQuantifiedResource quantifiedResource) {
		propertyChangeSupport.firePropertyChange("quantifiedResource",getQuantifiedResource(),quantifiedResource);
		if ( this.getQuantifiedResource()!=null ) {
			this.getQuantifiedResource().z_internalRemoveFromPricePerUnit(this);
		}
		if ( quantifiedResource!=null ) {
			this.z_internalAddToQuantifiedResource(quantifiedResource);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setQuantifiedResourcePricePerUnit_quantifiedResource(QuantifiedResourcePricePerUnit quantifiedResourcePricePerUnit_quantifiedResource) {
		QuantifiedResourcePricePerUnit oldValue = this.getQuantifiedResourcePricePerUnit_quantifiedResource();
		propertyChangeSupport.firePropertyChange("quantifiedResourcePricePerUnit_quantifiedResource",getQuantifiedResourcePricePerUnit_quantifiedResource(),quantifiedResourcePricePerUnit_quantifiedResource);
		if ( oldValue==null ) {
			if ( quantifiedResourcePricePerUnit_quantifiedResource!=null ) {
				PricePerUnit oldOther = (PricePerUnit)quantifiedResourcePricePerUnit_quantifiedResource.getPricePerUnit();
				quantifiedResourcePricePerUnit_quantifiedResource.z_internalRemoveFromPricePerUnit(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(quantifiedResourcePricePerUnit_quantifiedResource);
				}
				quantifiedResourcePricePerUnit_quantifiedResource.z_internalAddToPricePerUnit((PricePerUnit)this);
			}
			this.z_internalAddToQuantifiedResourcePricePerUnit_quantifiedResource(quantifiedResourcePricePerUnit_quantifiedResource);
		} else {
			if ( !oldValue.equals(quantifiedResourcePricePerUnit_quantifiedResource) ) {
				oldValue.z_internalRemoveFromPricePerUnit(this);
				z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(oldValue);
				if ( quantifiedResourcePricePerUnit_quantifiedResource!=null ) {
					PricePerUnit oldOther = (PricePerUnit)quantifiedResourcePricePerUnit_quantifiedResource.getPricePerUnit();
					quantifiedResourcePricePerUnit_quantifiedResource.z_internalRemoveFromPricePerUnit(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(quantifiedResourcePricePerUnit_quantifiedResource);
					}
					quantifiedResourcePricePerUnit_quantifiedResource.z_internalAddToPricePerUnit((PricePerUnit)this);
				}
				this.z_internalAddToQuantifiedResourcePricePerUnit_quantifiedResource(quantifiedResourcePricePerUnit_quantifiedResource);
			}
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<PricePerUnit uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PricePerUnit ");
		sb.append("classUuid=\"252060@_p2yl4Pl5EeGYn9aDKdPmKA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.costing.PricePerUnit\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAdditionalCostToCompany()!=null ) {
			sb.append("additionalCostToCompany=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMoneyInDefaultCurrency(getAdditionalCostToCompany())+"\" ");
		}
		if ( getPricePaidByCustomer()!=null ) {
			sb.append("pricePaidByCustomer=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMoneyInDefaultCurrency(getPricePaidByCustomer())+"\" ");
		}
		if ( getPricePaidByCompany()!=null ) {
			sb.append("pricePaidByCompany=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMoneyInDefaultCurrency(getPricePaidByCompany())+"\" ");
		}
		if ( getEffectiveFrom()!=null ) {
			sb.append("effectiveFrom=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getEffectiveFrom())+"\" ");
		}
		sb.append(">");
		sb.append("\n</PricePerUnit>");
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
	
	public void z_internalAddToPricePaidByCompany(Double pricePaidByCompany) {
		if ( pricePaidByCompanyCurrency!=null && !pricePaidByCompanyCurrency.equals(org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency()) ) {
			throw new CurrencyMismatchException(pricePaidByCompanyCurrency,org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency());
		}
		this.pricePaidByCompany=pricePaidByCompany;
	}
	
	public void z_internalAddToPricePaidByCustomer(Double pricePaidByCustomer) {
		if ( pricePaidByCustomerCurrency!=null && !pricePaidByCustomerCurrency.equals(org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency()) ) {
			throw new CurrencyMismatchException(pricePaidByCustomerCurrency,org.opeum.demo1.util.Demo1Environment.INSTANCE.getDefaultCurrency());
		}
		this.pricePaidByCustomer=pricePaidByCustomer;
	}
	
	public void z_internalAddToQuantifiedResource(IQuantifiedResource quantifiedResource) {
		QuantifiedResourcePricePerUnit newOne = new QuantifiedResourcePricePerUnit(this,quantifiedResource);
		this.z_internalAddToQuantifiedResourcePricePerUnit_quantifiedResource(newOne);
		newOne.getQuantifiedResource().z_internalAddToQuantifiedResourcePricePerUnit_pricePerUnit(newOne);
	}
	
	public void z_internalAddToQuantifiedResourcePricePerUnit_quantifiedResource(QuantifiedResourcePricePerUnit quantifiedResourcePricePerUnit_quantifiedResource) {
		this.quantifiedResourcePricePerUnit_quantifiedResource=quantifiedResourcePricePerUnit_quantifiedResource;
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
	
	public void z_internalRemoveFromPricePaidByCompany(Double pricePaidByCompany) {
		if ( getPricePaidByCompany()!=null && pricePaidByCompany!=null && pricePaidByCompany.equals(getPricePaidByCompany()) ) {
			this.pricePaidByCompany=null;
			this.pricePaidByCompany=null;
		}
	}
	
	public void z_internalRemoveFromPricePaidByCustomer(Double pricePaidByCustomer) {
		if ( getPricePaidByCustomer()!=null && pricePaidByCustomer!=null && pricePaidByCustomer.equals(getPricePaidByCustomer()) ) {
			this.pricePaidByCustomer=null;
			this.pricePaidByCustomer=null;
		}
	}
	
	public void z_internalRemoveFromQuantifiedResource(IQuantifiedResource quantifiedResource) {
		if ( this.quantifiedResourcePricePerUnit_quantifiedResource!=null ) {
			this.quantifiedResourcePricePerUnit_quantifiedResource.clear();
		}
	}
	
	public void z_internalRemoveFromQuantifiedResourcePricePerUnit_quantifiedResource(QuantifiedResourcePricePerUnit quantifiedResourcePricePerUnit_quantifiedResource) {
		if ( getQuantifiedResourcePricePerUnit_quantifiedResource()!=null && quantifiedResourcePricePerUnit_quantifiedResource!=null && quantifiedResourcePricePerUnit_quantifiedResource.equals(getQuantifiedResourcePricePerUnit_quantifiedResource()) ) {
			this.quantifiedResourcePricePerUnit_quantifiedResource=null;
			this.quantifiedResourcePricePerUnit_quantifiedResource=null;
		}
	}

}