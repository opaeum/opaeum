package org.nakeduml.runtime.bpm.businesscalendar.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.Session;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.BusinessComponent;
import org.nakeduml.runtime.bpm.businesscalendar.BusinessTimeUnit;
import org.nakeduml.runtime.bpm.businesscalendar.OnceOffHoliday;
import org.nakeduml.runtime.bpm.businesscalendar.RecurringHoliday;
import org.nakeduml.runtime.bpm.businesscalendar.WorkDay;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CancelledEvent;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.domain.OutgoingEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@MappedSuperclass
public class AbstractBusinessCalendar implements IEventGenerator, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	static final private long serialVersionUID = 638;
	@Column(name="business_hours_per_week")
	private Double businessHoursPerWeek;
	@Column(name="business_days_per_month")
	private Integer businessDaysPerMonth;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="businessCalendar",targetEntity=OnceOffHoliday.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<OnceOffHoliday> onceOffHoliday = new HashSet<OnceOffHoliday>();
	@Column(name="business_hours_per_day")
	private Double businessHoursPerDay;
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="businessCalendar",targetEntity=RecurringHoliday.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<RecurringHoliday> recurringHoliday = new HashSet<RecurringHoliday>();
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL,mappedBy="businessCalendar",targetEntity=WorkDay.class)
	@LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	private Set<WorkDay> workDay = new HashSet<WorkDay>();
	@Index(name="idx_business_calendar_business_component",columnNames="business_component")
	@Any(metaDef="BusinessComponent",metaColumn=@Column(name="business_component_type"))
	@JoinColumn(name="business_component",nullable=true)
	private BusinessComponent businessComponent;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static private Set<BusinessCalendar> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;

	/** Default constructor for BusinessCalendar
	 */
	public AbstractBusinessCalendar() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public AbstractBusinessCalendar(BusinessComponent owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToOnceOffHoliday(Set<OnceOffHoliday> onceOffHoliday) {
		for ( OnceOffHoliday o : onceOffHoliday ) {
			addToOnceOffHoliday(o);
		}
	}
	
	public void addAllToRecurringHoliday(Set<RecurringHoliday> recurringHoliday) {
		for ( RecurringHoliday o : recurringHoliday ) {
			addToRecurringHoliday(o);
		}
	}
	
	public void addAllToWorkDay(Set<WorkDay> workDay) {
		for ( WorkDay o : workDay ) {
			addToWorkDay(o);
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.add_time_to",uuid="OpiumBPM.library.uml@_NTccANcEEeCJ0dmaHEVVnw")
	public void addTimeTo(Date fromDateTime, BusinessTimeUnit timeUnit, Double numberOfUnits, Date result) {
		generateAddTimeToEvent(fromDateTime,timeUnit,numberOfUnits,result);
	}
	
	public void addToOnceOffHoliday(OnceOffHoliday onceOffHoliday) {
		if ( onceOffHoliday!=null ) {
			onceOffHoliday.z_internalRemoveFromBusinessCalendar(onceOffHoliday.getBusinessCalendar());
			onceOffHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToOnceOffHoliday(onceOffHoliday);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessComponent().z_internalAddToBusinessCalendar((BusinessCalendar)this);
	}
	
	public void addToRecurringHoliday(RecurringHoliday recurringHoliday) {
		if ( recurringHoliday!=null ) {
			recurringHoliday.z_internalRemoveFromBusinessCalendar(recurringHoliday.getBusinessCalendar());
			recurringHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToRecurringHoliday(recurringHoliday);
		}
	}
	
	public void addToWorkDay(WorkDay workDay) {
		if ( workDay!=null ) {
			workDay.z_internalRemoveFromBusinessCalendar(workDay.getBusinessCalendar());
			workDay.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToWorkDay(workDay);
		}
	}
	
	static public Set<? extends BusinessCalendar> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from BusinessCalendar").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("businessHoursPerWeek")!=null ) {
			setBusinessHoursPerWeek(OpiumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("businessHoursPerWeek")));
		}
		if ( xml.getAttribute("businessDaysPerMonth")!=null ) {
			setBusinessDaysPerMonth(OpiumLibraryForBPMFormatter.getInstance().parseInteger(xml.getAttribute("businessDaysPerMonth")));
		}
		if ( xml.getAttribute("businessHoursPerDay")!=null ) {
			setBusinessHoursPerDay(OpiumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("businessHoursPerDay")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("onceOffHoliday") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OnceOffHoliday curVal = (OnceOffHoliday)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.addToOnceOffHoliday(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("recurringHoliday") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						RecurringHoliday curVal = (RecurringHoliday)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.addToRecurringHoliday(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("workDay") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						WorkDay curVal = (WorkDay)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.addToWorkDay(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("businessComponent") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessComponent curVal = (BusinessComponent)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.setBusinessComponent(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.calculate_time_between",uuid="OpiumBPM.library.uml@_mOhZgNcEEeCJ0dmaHEVVnw")
	public void calculateTimeBetween(Date fromDateTIme, Date toDateTime, BusinessTimeUnit businessTimeUnit, Double result) {
		generateCalculateTimeBetweenEvent(fromDateTIme,toDateTime,businessTimeUnit,result);
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
	
	public boolean consumeAddTimeToOccurrence(Date fromDateTime, BusinessTimeUnit timeUnit, Double numberOfUnits, Date result) {
		boolean consumed = false;
		return consumed;
	}
	
	public boolean consumeCalculateTimeBetweenOccurrence(Date fromDateTIme, Date toDateTime, BusinessTimeUnit businessTimeUnit, Double result) {
		boolean consumed = false;
		return consumed;
	}
	
	public void copyShallowState(BusinessCalendar from, BusinessCalendar to) {
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
	}
	
	public void copyState(BusinessCalendar from, BusinessCalendar to) {
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		for ( OnceOffHoliday child : from.getOnceOffHoliday() ) {
			to.addToOnceOffHoliday(child.makeCopy());
		}
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
		for ( RecurringHoliday child : from.getRecurringHoliday() ) {
			to.addToRecurringHoliday(child.makeCopy());
		}
		for ( WorkDay child : from.getWorkDay() ) {
			to.addToWorkDay(child.makeCopy());
		}
	}
	
	public void createComponents() {
		if ( getWorkDay().isEmpty() ) {
			WorkDay newworkDay;
			newworkDay= new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setKind(org.nakeduml.runtime.bpm.businesscalendar.WorkDayKind.WEEKDAY);
			newworkDay= new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setKind(org.nakeduml.runtime.bpm.businesscalendar.WorkDayKind.SATURDAY);
			newworkDay= new WorkDay();
			addToWorkDay(newworkDay);
			newworkDay.setKind(org.nakeduml.runtime.bpm.businesscalendar.WorkDayKind.SUNDAY);
		}
	}
	
	public OnceOffHoliday createOnceOffHoliday() {
		OnceOffHoliday newInstance= new OnceOffHoliday();
		newInstance.init(this);
		return newInstance;
	}
	
	public RecurringHoliday createRecurringHoliday() {
		RecurringHoliday newInstance= new RecurringHoliday();
		newInstance.init(this);
		return newInstance;
	}
	
	public WorkDay createWorkDay() {
		WorkDay newInstance= new WorkDay();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessCalendar ) {
			return other==this || ((BusinessCalendar)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public void generateAddTimeToEvent(Date fromDateTime, BusinessTimeUnit timeUnit, Double numberOfUnits, Date result) {
	}
	
	public void generateCalculateTimeBetweenEvent(Date fromDateTIme, Date toDateTime, BusinessTimeUnit businessTimeUnit, Double result) {
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.business_component",uuid="OpiumBPM.library.uml@_ynCgQdcCEeCJ0dmaHEVVnw")
	public BusinessComponent getBusinessComponent() {
		return businessComponent;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.business_days_per_month",uuid="OpiumBPM.library.uml@_szTxoNcDEeCJ0dmaHEVVnw")
	public Integer getBusinessDaysPerMonth() {
		return businessDaysPerMonth;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.business_hours_per_day",uuid="OpiumBPM.library.uml@_1Hz-cNcDEeCJ0dmaHEVVnw")
	public Double getBusinessHoursPerDay() {
		return businessHoursPerDay;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.business_hours_per_week",uuid="OpiumBPM.library.uml@_QLpNoNcDEeCJ0dmaHEVVnw")
	public Double getBusinessHoursPerWeek() {
		return businessHoursPerWeek;
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
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.once_off_holiday_id",uuid="OpiumBPM.library.uml@_7UFI4NcCEeCJ0dmaHEVVnw")
	public Set<OnceOffHoliday> getOnceOffHoliday() {
		return onceOffHoliday;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessComponent();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.recurring_holiday_id",uuid="OpiumBPM.library.uml@_xucEUNcCEeCJ0dmaHEVVnw")
	public Set<RecurringHoliday> getRecurringHoliday() {
		return recurringHoliday;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="business_calendar.work_day_id",uuid="OpiumBPM.library.uml@_K_mY0Nb-EeCJ0dmaHEVVnw")
	public Set<WorkDay> getWorkDay() {
		return workDay;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToBusinessComponent((BusinessComponent)owner);
		createComponents();
		for ( WorkDay c : getWorkDay() ) {
			c.init(this);
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
		if ( getBusinessComponent()!=null ) {
			getBusinessComponent().z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
		}
		for ( OnceOffHoliday child : new ArrayList<OnceOffHoliday>(getOnceOffHoliday()) ) {
			child.markDeleted();
		}
		for ( RecurringHoliday child : new ArrayList<RecurringHoliday>(getRecurringHoliday()) ) {
			child.markDeleted();
		}
		for ( WorkDay child : new ArrayList<WorkDay>(getWorkDay()) ) {
			child.markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<BusinessCalendar> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("onceOffHoliday") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OnceOffHoliday)map.get(((Element)xml).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("recurringHoliday") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((RecurringHoliday)map.get(((Element)xml).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("workDay") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((WorkDay)map.get(((Element)xml).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromOnceOffHoliday(Set<OnceOffHoliday> onceOffHoliday) {
		Set<OnceOffHoliday> tmp = new HashSet<OnceOffHoliday>(onceOffHoliday);
		for ( OnceOffHoliday o : tmp ) {
			removeFromOnceOffHoliday(o);
		}
	}
	
	public void removeAllFromRecurringHoliday(Set<RecurringHoliday> recurringHoliday) {
		Set<RecurringHoliday> tmp = new HashSet<RecurringHoliday>(recurringHoliday);
		for ( RecurringHoliday o : tmp ) {
			removeFromRecurringHoliday(o);
		}
	}
	
	public void removeAllFromWorkDay(Set<WorkDay> workDay) {
		Set<WorkDay> tmp = new HashSet<WorkDay>(workDay);
		for ( WorkDay o : tmp ) {
			removeFromWorkDay(o);
		}
	}
	
	public void removeFromOnceOffHoliday(OnceOffHoliday onceOffHoliday) {
		if ( onceOffHoliday!=null ) {
			onceOffHoliday.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromOnceOffHoliday(onceOffHoliday);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromRecurringHoliday(RecurringHoliday recurringHoliday) {
		if ( recurringHoliday!=null ) {
			recurringHoliday.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromRecurringHoliday(recurringHoliday);
		}
	}
	
	public void removeFromWorkDay(WorkDay workDay) {
		if ( workDay!=null ) {
			workDay.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromWorkDay(workDay);
		}
	}
	
	public void setBusinessComponent(BusinessComponent businessComponent) {
		BusinessComponent oldValue = this.getBusinessComponent();
		if ( oldValue==null ) {
			this.z_internalAddToBusinessComponent(businessComponent);
			if ( businessComponent!=null ) {
				businessComponent.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			}
		} else {
			if ( !oldValue.equals(businessComponent) ) {
				this.z_internalAddToBusinessComponent(businessComponent);
				oldValue.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
				if ( businessComponent!=null ) {
					businessComponent.z_internalAddToBusinessCalendar((BusinessCalendar)this);
				}
			}
		}
	}
	
	public void setBusinessDaysPerMonth(Integer businessDaysPerMonth) {
		this.z_internalAddToBusinessDaysPerMonth(businessDaysPerMonth);
	}
	
	public void setBusinessHoursPerDay(Double businessHoursPerDay) {
		this.z_internalAddToBusinessHoursPerDay(businessHoursPerDay);
	}
	
	public void setBusinessHoursPerWeek(Double businessHoursPerWeek) {
		this.z_internalAddToBusinessHoursPerWeek(businessHoursPerWeek);
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
	
	public void setOnceOffHoliday(Set<OnceOffHoliday> onceOffHoliday) {
		this.clearOnceOffHoliday();
		this.addAllToOnceOffHoliday(onceOffHoliday);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setRecurringHoliday(Set<RecurringHoliday> recurringHoliday) {
		this.clearRecurringHoliday();
		this.addAllToRecurringHoliday(recurringHoliday);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setWorkDay(Set<WorkDay> workDay) {
		this.clearWorkDay();
		this.addAllToWorkDay(workDay);
	}
	
	public String toXmlReferenceString() {
		return "<businessCalendar uid=\""+getUid() + "\">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<businessCalendar");
		sb.append(" className=\"org.nakeduml.runtime.bpm.businesscalendar.BusinessCalendar\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getBusinessHoursPerWeek()!=null ) {
			sb.append("businessHoursPerWeek=\""+ OpiumLibraryForBPMFormatter.getInstance().formatReal(getBusinessHoursPerWeek())+"\" ");
		}
		if ( getBusinessDaysPerMonth()!=null ) {
			sb.append("businessDaysPerMonth=\""+ OpiumLibraryForBPMFormatter.getInstance().formatInteger(getBusinessDaysPerMonth())+"\" ");
		}
		if ( getBusinessHoursPerDay()!=null ) {
			sb.append("businessHoursPerDay=\""+ OpiumLibraryForBPMFormatter.getInstance().formatReal(getBusinessHoursPerDay())+"\" ");
		}
		sb.append(">\n");
		sb.append("</businessCalendar>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessComponent(BusinessComponent val) {
		this.businessComponent=val;
	}
	
	public void z_internalAddToBusinessDaysPerMonth(Integer val) {
		this.businessDaysPerMonth=val;
	}
	
	public void z_internalAddToBusinessHoursPerDay(Double val) {
		this.businessHoursPerDay=val;
	}
	
	public void z_internalAddToBusinessHoursPerWeek(Double val) {
		this.businessHoursPerWeek=val;
	}
	
	public void z_internalAddToOnceOffHoliday(OnceOffHoliday val) {
		this.onceOffHoliday.add(val);
	}
	
	public void z_internalAddToRecurringHoliday(RecurringHoliday val) {
		this.recurringHoliday.add(val);
	}
	
	public void z_internalAddToWorkDay(WorkDay val) {
		this.workDay.add(val);
	}
	
	public void z_internalRemoveFromBusinessComponent(BusinessComponent val) {
		if ( getBusinessComponent()!=null && val!=null && val.equals(getBusinessComponent()) ) {
			this.businessComponent=null;
		}
	}
	
	public void z_internalRemoveFromBusinessDaysPerMonth(Integer val) {
		if ( getBusinessDaysPerMonth()!=null && val!=null && val.equals(getBusinessDaysPerMonth()) ) {
			this.businessDaysPerMonth=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerDay(Double val) {
		if ( getBusinessHoursPerDay()!=null && val!=null && val.equals(getBusinessHoursPerDay()) ) {
			this.businessHoursPerDay=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerWeek(Double val) {
		if ( getBusinessHoursPerWeek()!=null && val!=null && val.equals(getBusinessHoursPerWeek()) ) {
			this.businessHoursPerWeek=null;
		}
	}
	
	public void z_internalRemoveFromOnceOffHoliday(OnceOffHoliday val) {
		this.onceOffHoliday.remove(val);
	}
	
	public void z_internalRemoveFromRecurringHoliday(RecurringHoliday val) {
		this.recurringHoliday.remove(val);
	}
	
	public void z_internalRemoveFromWorkDay(WorkDay val) {
		this.workDay.remove(val);
	}

}