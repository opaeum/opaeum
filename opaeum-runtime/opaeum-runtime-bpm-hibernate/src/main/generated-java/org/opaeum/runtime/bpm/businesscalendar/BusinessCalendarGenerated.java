package org.opaeum.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.BusinessComponent;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@MappedSuperclass
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",uniqueConstraints=@UniqueConstraint(columnNames={"business_component_id","deleted_on"}),name="business_calendar")
@NumlMetaInfo(uuid="252060@_x9fmQNb9EeCJ0dmaHEVVnw")
@AccessType("field")
public class BusinessCalendarGenerated implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	private String uid;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Column(name="business_hours_per_day")
	private Double _businessHoursPerDay;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Index(name="idx_business_calendar_business_component_id",columnNames="business_component_id")
	@Any(metaDef="BusinessComponent",metaColumn=@Column(name="business_component_id_type"))
	@JoinColumn(name="business_component_id",nullable=true)
	private BusinessComponent _businessComponent;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="_businessCalendar",targetEntity=OnceOffHoliday.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<OnceOffHoliday> _onceOffHoliday = new HashSet<OnceOffHoliday>();
	@Column(name="business_hours_per_week")
	private Double _businessHoursPerWeek;
	@Column(name="business_days_per_month")
	private Integer _businessDaysPerMonth;
	static final private long serialVersionUID = 62;
	static private Set<BusinessCalendar> mockedAllInstances;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="_businessCalendar",targetEntity=RecurringHoliday.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<RecurringHoliday> _recurringHoliday = new HashSet<RecurringHoliday>();
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="_businessCalendar",targetEntity=WorkDay.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<WorkDay> _workDay = new HashSet<WorkDay>();

	/** Default constructor for BusinessCalendar
	 */
	public BusinessCalendarGenerated() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessCalendarGenerated(BusinessComponent owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToOnceOffHoliday(Set<OnceOffHoliday> _onceOffHoliday) {
		for ( OnceOffHoliday o : _onceOffHoliday ) {
			addToOnceOffHoliday(o);
		}
	}
	
	public void addAllToRecurringHoliday(Set<RecurringHoliday> _recurringHoliday) {
		for ( RecurringHoliday o : _recurringHoliday ) {
			addToRecurringHoliday(o);
		}
	}
	
	public void addAllToWorkDay(Set<WorkDay> _workDay) {
		for ( WorkDay o : _workDay ) {
			addToWorkDay(o);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_NTccANcEEeCJ0dmaHEVVnw")
	public Date addTimeTo(Date _fromDateTime, BusinessTimeUnit _timeUnit, Double _numberOfUnits) {
		Date result = null;
		
		return result;
	}
	
	public void addToOnceOffHoliday(OnceOffHoliday _onceOffHoliday) {
		if ( _onceOffHoliday!=null ) {
			_onceOffHoliday.z_internalRemoveFromBusinessCalendar(_onceOffHoliday.getBusinessCalendar());
			_onceOffHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToOnceOffHoliday(_onceOffHoliday);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessComponent().z_internalAddToBusinessCalendar((BusinessCalendar)this);
	}
	
	public void addToRecurringHoliday(RecurringHoliday _recurringHoliday) {
		if ( _recurringHoliday!=null ) {
			_recurringHoliday.z_internalRemoveFromBusinessCalendar(_recurringHoliday.getBusinessCalendar());
			_recurringHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToRecurringHoliday(_recurringHoliday);
		}
	}
	
	public void addToWorkDay(WorkDay _workDay) {
		if ( _workDay!=null ) {
			_workDay.z_internalRemoveFromBusinessCalendar(_workDay.getBusinessCalendar());
			_workDay.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToWorkDay(_workDay);
		}
	}
	
	static public Set<? extends BusinessCalendar> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from BusinessCalendar").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_businessDaysPerMonth").length()>0 ) {
			setBusinessDaysPerMonth(OpaeumLibraryForBPMFormatter.getInstance().parseInteger(xml.getAttribute("_businessDaysPerMonth")));
		}
		if ( xml.getAttribute("_businessHoursPerWeek").length()>0 ) {
			setBusinessHoursPerWeek(OpaeumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("_businessHoursPerWeek")));
		}
		if ( xml.getAttribute("_businessHoursPerDay").length()>0 ) {
			setBusinessHoursPerDay(OpaeumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("_businessHoursPerDay")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_onceOffHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("298")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OnceOffHoliday curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToOnceOffHoliday(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_workDay") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("295")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						WorkDay curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToWorkDay(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_recurringHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("289")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						RecurringHoliday curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToRecurringHoliday(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(uuid="252060@_mOhZgNcEEeCJ0dmaHEVVnw")
	public Duration calculateTimeBetween(Date _fromDateTIme, Date _toDateTime, BusinessTimeUnit _businessTimeUnit) {
		Duration result = null;
		
		return result;
	}
	
	public void clearOnceOffHoliday() {
		removeAllFromOnceOffHoliday(getOnceOffHoliday());
	}
	
	public void clearRecurringHoliday() {
		removeAllFromRecurringHoliday(getRecurringHoliday());
	}
	
	public void clearWorkDay() {
		removeAllFromWorkDay(getWorkDay());
	}
	
	@NumlMetaInfo(uuid="252060@_dXLYsASTEeGb9qsDxKJdSA")
	public Duration convertDuration(Duration _from, BusinessTimeUnit _toTimeUnit) {
		Duration result = null;
		
		return result;
	}
	
	public void copyShallowState(BusinessCalendar from, BusinessCalendar to) {
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
	}
	
	public void copyState(BusinessCalendar from, BusinessCalendar to) {
		for ( OnceOffHoliday child : from.getOnceOffHoliday() ) {
			to.addToOnceOffHoliday(child.makeCopy());
		}
		for ( WorkDay child : from.getWorkDay() ) {
			to.addToWorkDay(child.makeCopy());
		}
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
		for ( RecurringHoliday child : from.getRecurringHoliday() ) {
			to.addToRecurringHoliday(child.makeCopy());
		}
	}
	
	public void createComponents() {
		if ( getWorkDay().isEmpty() ) {
			WorkDay newworkDay;
			newworkDay= new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setKind(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.WEEKDAY);
			newworkDay= new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setKind(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.SATURDAY);
			newworkDay= new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setKind(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.SUNDAY);
		}
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessCalendar ) {
			return other==this || ((BusinessCalendar)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_ynCgQdcCEeCJ0dmaHEVVnw")
	public BusinessComponent getBusinessComponent() {
		BusinessComponent result = this._businessComponent;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_szTxoNcDEeCJ0dmaHEVVnw")
	public Integer getBusinessDaysPerMonth() {
		Integer result = this._businessDaysPerMonth;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_1Hz-cNcDEeCJ0dmaHEVVnw")
	public Double getBusinessHoursPerDay() {
		Double result = this._businessHoursPerDay;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_QLpNoNcDEeCJ0dmaHEVVnw")
	public Double getBusinessHoursPerWeek() {
		Double result = this._businessHoursPerWeek;
		
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
	
	public String getName() {
		return "BusinessCalendar["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@NumlMetaInfo(uuid="252060@_7UFI4NcCEeCJ0dmaHEVVnw")
	public Set<OnceOffHoliday> getOnceOffHoliday() {
		Set<OnceOffHoliday> result = this._onceOffHoliday;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessComponent();
	}
	
	@NumlMetaInfo(uuid="252060@_xucEUNcCEeCJ0dmaHEVVnw")
	public Set<RecurringHoliday> getRecurringHoliday() {
		Set<RecurringHoliday> result = this._recurringHoliday;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@NumlMetaInfo(uuid="252060@_K_mY0Nb-EeCJ0dmaHEVVnw")
	public Set<WorkDay> getWorkDay() {
		Set<WorkDay> result = this._workDay;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToBusinessComponent((BusinessComponent)owner);
		createComponents();
		for ( WorkDay c : getWorkDay() ) {
			c.init((BusinessCalendar)this);
		}
	}
	
	public BusinessCalendar makeCopy() {
		BusinessCalendar result = new BusinessCalendar();
		copyState((BusinessCalendar)this,result);
		return result;
	}
	
	public BusinessCalendar makeShallowCopy() {
		BusinessCalendar result = new BusinessCalendar();
		copyShallowState((BusinessCalendar)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessComponent()!=null ) {
			getBusinessComponent().z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
		}
		for ( OnceOffHoliday child : new ArrayList<OnceOffHoliday>(getOnceOffHoliday()) ) {
			child.markDeleted();
		}
		for ( WorkDay child : new ArrayList<WorkDay>(getWorkDay()) ) {
			child.markDeleted();
		}
		for ( RecurringHoliday child : new ArrayList<RecurringHoliday>(getRecurringHoliday()) ) {
			child.markDeleted();
		}
	}
	
	static public void mockAllInstances(Set<BusinessCalendar> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_onceOffHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("298")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OnceOffHoliday)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_workDay") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("295")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((WorkDay)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_recurringHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("289")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((RecurringHoliday)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromOnceOffHoliday(Set<OnceOffHoliday> _onceOffHoliday) {
		Set<OnceOffHoliday> tmp = new HashSet<OnceOffHoliday>(_onceOffHoliday);
		for ( OnceOffHoliday o : tmp ) {
			removeFromOnceOffHoliday(o);
		}
	}
	
	public void removeAllFromRecurringHoliday(Set<RecurringHoliday> _recurringHoliday) {
		Set<RecurringHoliday> tmp = new HashSet<RecurringHoliday>(_recurringHoliday);
		for ( RecurringHoliday o : tmp ) {
			removeFromRecurringHoliday(o);
		}
	}
	
	public void removeAllFromWorkDay(Set<WorkDay> _workDay) {
		Set<WorkDay> tmp = new HashSet<WorkDay>(_workDay);
		for ( WorkDay o : tmp ) {
			removeFromWorkDay(o);
		}
	}
	
	public void removeFromOnceOffHoliday(OnceOffHoliday _onceOffHoliday) {
		if ( _onceOffHoliday!=null ) {
			_onceOffHoliday.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromOnceOffHoliday(_onceOffHoliday);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromRecurringHoliday(RecurringHoliday _recurringHoliday) {
		if ( _recurringHoliday!=null ) {
			_recurringHoliday.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromRecurringHoliday(_recurringHoliday);
		}
	}
	
	public void removeFromWorkDay(WorkDay _workDay) {
		if ( _workDay!=null ) {
			_workDay.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromWorkDay(_workDay);
		}
	}
	
	public void setBusinessComponent(BusinessComponent _businessComponent) {
		BusinessComponent oldValue = this.getBusinessComponent();
		if ( oldValue==null ) {
			this.z_internalAddToBusinessComponent(_businessComponent);
			if ( _businessComponent!=null ) {
				_businessComponent.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			}
		} else {
			if ( !oldValue.equals(_businessComponent) ) {
				this.z_internalAddToBusinessComponent(_businessComponent);
				oldValue.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
				if ( _businessComponent!=null ) {
					_businessComponent.z_internalAddToBusinessCalendar((BusinessCalendar)this);
				}
			}
		}
	}
	
	public void setBusinessDaysPerMonth(Integer _businessDaysPerMonth) {
		this.z_internalAddToBusinessDaysPerMonth(_businessDaysPerMonth);
	}
	
	public void setBusinessHoursPerDay(Double _businessHoursPerDay) {
		this.z_internalAddToBusinessHoursPerDay(_businessHoursPerDay);
	}
	
	public void setBusinessHoursPerWeek(Double _businessHoursPerWeek) {
		this.z_internalAddToBusinessHoursPerWeek(_businessHoursPerWeek);
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
	
	public void setOnceOffHoliday(Set<OnceOffHoliday> _onceOffHoliday) {
		this.clearOnceOffHoliday();
		this.addAllToOnceOffHoliday(_onceOffHoliday);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setRecurringHoliday(Set<RecurringHoliday> _recurringHoliday) {
		this.clearRecurringHoliday();
		this.addAllToRecurringHoliday(_recurringHoliday);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setWorkDay(Set<WorkDay> _workDay) {
		this.clearWorkDay();
		this.addAllToWorkDay(_workDay);
	}
	
	public String toXmlReferenceString() {
		return "<BusinessCalendar uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessCalendar ");
		sb.append("classUuid=\"252060@_x9fmQNb9EeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getBusinessDaysPerMonth()!=null ) {
			sb.append("_businessDaysPerMonth=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatInteger(getBusinessDaysPerMonth())+"\" ");
		}
		if ( getBusinessHoursPerWeek()!=null ) {
			sb.append("_businessHoursPerWeek=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatReal(getBusinessHoursPerWeek())+"\" ");
		}
		if ( getBusinessHoursPerDay()!=null ) {
			sb.append("_businessHoursPerDay=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatReal(getBusinessHoursPerDay())+"\" ");
		}
		sb.append(">");
		sb.append("\n<_onceOffHoliday propertyId=\"298\">");
		for ( OnceOffHoliday _onceOffHoliday : getOnceOffHoliday() ) {
			sb.append("\n" + _onceOffHoliday.toXmlString());
		}
		sb.append("\n</_onceOffHoliday>");
		sb.append("\n<_workDay propertyId=\"295\">");
		for ( WorkDay _workDay : getWorkDay() ) {
			sb.append("\n" + _workDay.toXmlString());
		}
		sb.append("\n</_workDay>");
		sb.append("\n<_recurringHoliday propertyId=\"289\">");
		for ( RecurringHoliday _recurringHoliday : getRecurringHoliday() ) {
			sb.append("\n" + _recurringHoliday.toXmlString());
		}
		sb.append("\n</_recurringHoliday>");
		sb.append("\n</BusinessCalendar>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessComponent(BusinessComponent val) {
		this._businessComponent=val;
	}
	
	public void z_internalAddToBusinessDaysPerMonth(Integer val) {
		this._businessDaysPerMonth=val;
	}
	
	public void z_internalAddToBusinessHoursPerDay(Double val) {
		this._businessHoursPerDay=val;
	}
	
	public void z_internalAddToBusinessHoursPerWeek(Double val) {
		this._businessHoursPerWeek=val;
	}
	
	public void z_internalAddToOnceOffHoliday(OnceOffHoliday val) {
		this._onceOffHoliday.add(val);
	}
	
	public void z_internalAddToRecurringHoliday(RecurringHoliday val) {
		this._recurringHoliday.add(val);
	}
	
	public void z_internalAddToWorkDay(WorkDay val) {
		this._workDay.add(val);
	}
	
	public void z_internalRemoveFromBusinessComponent(BusinessComponent val) {
		if ( getBusinessComponent()!=null && val!=null && val.equals(getBusinessComponent()) ) {
			this._businessComponent=null;
		}
	}
	
	public void z_internalRemoveFromBusinessDaysPerMonth(Integer val) {
		if ( getBusinessDaysPerMonth()!=null && val!=null && val.equals(getBusinessDaysPerMonth()) ) {
			this._businessDaysPerMonth=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerDay(Double val) {
		if ( getBusinessHoursPerDay()!=null && val!=null && val.equals(getBusinessHoursPerDay()) ) {
			this._businessHoursPerDay=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerWeek(Double val) {
		if ( getBusinessHoursPerWeek()!=null && val!=null && val.equals(getBusinessHoursPerWeek()) ) {
			this._businessHoursPerWeek=null;
		}
	}
	
	public void z_internalRemoveFromOnceOffHoliday(OnceOffHoliday val) {
		this._onceOffHoliday.remove(val);
	}
	
	public void z_internalRemoveFromRecurringHoliday(RecurringHoliday val) {
		this._recurringHoliday.remove(val);
	}
	
	public void z_internalRemoveFromWorkDay(WorkDay val) {
		this._workDay.remove(val);
	}

}