package org.opaeum.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.Duration;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.opaeum.runtime.strategy.DurationStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_x9fmQNb9EeCJ0dmaHEVVnw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@MappedSuperclass
public class BusinessCalendarGenerated implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Column(name="business_days_per_month")
	@Basic
	protected Integer businessDaysPerMonth;
	@Column(name="business_hours_per_day")
	@Basic
	protected Double businessHoursPerDay;
	@Column(name="business_hours_per_week")
	@Basic
	protected Double businessHoursPerWeek;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_calendar",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static protected BusinessCalendar instance;
	static private Set<? extends BusinessCalendar> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCalendar",targetEntity=OnceOffHoliday.class)
	protected Set<OnceOffHoliday> onceOffHoliday = new HashSet<OnceOffHoliday>();
	@Index(columnNames="organization_id",name="idx_business_calendar_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	protected OrganizationNode organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCalendar",targetEntity=RecurringHoliday.class)
	protected Set<RecurringHoliday> recurringHoliday = new HashSet<RecurringHoliday>();
	static final private long serialVersionUID = 4208594208280739637l;
	private String uid;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCalendar",targetEntity=WorkDay.class)
	@MapKey(name="z_keyOfWorkDayOnBusinessCalendar")
	protected Map<String, WorkDay> workDay = new HashMap<String,WorkDay>();

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public BusinessCalendarGenerated(OrganizationNode owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for BusinessCalendar
	 */
	public BusinessCalendarGenerated() {
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
			addToWorkDay(o.getKind(),o);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_NTccANcEEeCJ0dmaHEVVnw")
	public Date addTimeTo(@ParameterMetaInfo(name="fromDateTime",opaeumId=5908544747543073361l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_OJSe4NcEEeCJ0dmaHEVVnw") Date fromDateTime, @ParameterMetaInfo(name="timeUnit",opaeumId=2654263369633975777l,uuid="252060@_QPwcINcEEeCJ0dmaHEVVnw") BusinessTimeUnit timeUnit, @ParameterMetaInfo(name="numberOfUnits",opaeumId=446023254321750925l,uuid="252060@_WfbDYNcEEeCJ0dmaHEVVnw") Double numberOfUnits) {
		Date result = null;
		
		return result;
	}
	
	public void addToOnceOffHoliday(OnceOffHoliday onceOffHoliday) {
		if ( onceOffHoliday!=null ) {
			if ( onceOffHoliday.getBusinessCalendar()!=null ) {
				onceOffHoliday.getBusinessCalendar().removeFromOnceOffHoliday(onceOffHoliday);
			}
			onceOffHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
		}
		z_internalAddToOnceOffHoliday(onceOffHoliday);
	}
	
	/** Call (BusinessCalendar)this method when you want to attach (BusinessCalendar)this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getOrganization().z_internalAddToBusinessCalendar((BusinessCalendar)this);
	}
	
	public void addToRecurringHoliday(RecurringHoliday recurringHoliday) {
		if ( recurringHoliday!=null ) {
			if ( recurringHoliday.getBusinessCalendar()!=null ) {
				recurringHoliday.getBusinessCalendar().removeFromRecurringHoliday(recurringHoliday);
			}
			recurringHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
		}
		z_internalAddToRecurringHoliday(recurringHoliday);
	}
	
	public void addToWorkDay(WorkDayKind kind, WorkDay workDay) {
		if ( workDay!=null ) {
			if ( workDay.getBusinessCalendar()!=null ) {
				workDay.getBusinessCalendar().removeFromWorkDay(workDay.getKind(),workDay);
			}
			workDay.z_internalAddToBusinessCalendar((BusinessCalendar)this);
		}
		z_internalAddToWorkDay(kind,workDay);
	}
	
	static public Set<? extends BusinessCalendar> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("businessHoursPerWeek").length()>0 ) {
			setBusinessHoursPerWeek(OpaeumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("businessHoursPerWeek")));
		}
		if ( xml.getAttribute("businessDaysPerMonth").length()>0 ) {
			setBusinessDaysPerMonth(OpaeumLibraryForBPMFormatter.getInstance().parseInteger(xml.getAttribute("businessDaysPerMonth")));
		}
		if ( xml.getAttribute("businessHoursPerDay").length()>0 ) {
			setBusinessHoursPerDay(OpaeumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("businessHoursPerDay")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("workDay") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2874459130083887215")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						WorkDay curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToWorkDay(curVal.getKind(),curVal);
						curVal.z_internalAddToBusinessCalendar((BusinessCalendar)this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("recurringHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5865908630178342957")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						RecurringHoliday curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToRecurringHoliday(curVal);
						curVal.z_internalAddToBusinessCalendar((BusinessCalendar)this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("onceOffHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5435749030548125197")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						OnceOffHoliday curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToOnceOffHoliday(curVal);
						curVal.z_internalAddToBusinessCalendar((BusinessCalendar)this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(uuid="252060@_mOhZgNcEEeCJ0dmaHEVVnw")
	public Duration calculateTimeBetween(@ParameterMetaInfo(name="fromDateTIme",opaeumId=7202429714693706761l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_nlX3sNcEEeCJ0dmaHEVVnw") Date fromDateTIme, @ParameterMetaInfo(name="toDateTime",opaeumId=250162200899249375l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_nm_BQNcEEeCJ0dmaHEVVnw") Date toDateTime, @ParameterMetaInfo(name="businessTimeUnit",opaeumId=5406692947055461229l,uuid="252060@_nogrQNcEEeCJ0dmaHEVVnw") BusinessTimeUnit businessTimeUnit) {
		Duration result = null;
		
		return result;
	}
	
	public void clearOnceOffHoliday() {
		Set<OnceOffHoliday> tmp = new HashSet<OnceOffHoliday>(getOnceOffHoliday());
		for ( OnceOffHoliday o : tmp ) {
			removeFromOnceOffHoliday(o);
		}
	}
	
	public void clearRecurringHoliday() {
		Set<RecurringHoliday> tmp = new HashSet<RecurringHoliday>(getRecurringHoliday());
		for ( RecurringHoliday o : tmp ) {
			removeFromRecurringHoliday(o);
		}
	}
	
	public void clearWorkDay() {
		Set<WorkDay> tmp = new HashSet<WorkDay>(getWorkDay());
		for ( WorkDay o : tmp ) {
			removeFromWorkDay(o.getKind(),o);
		}
	}
	
	@NumlMetaInfo(uuid="252060@_dXLYsASTEeGb9qsDxKJdSA")
	public Duration convertDuration(@ParameterMetaInfo(name="from",opaeumId=2105497212461834893l,strategyFactory=DurationStrategyFactory.class,uuid="252060@_e8ABcASTEeGb9qsDxKJdSA") Duration from, @ParameterMetaInfo(name="toTimeUnit",opaeumId=8309674443127600973l,uuid="252060@_ixMxYASTEeGb9qsDxKJdSA") BusinessTimeUnit toTimeUnit) {
		Duration result = null;
		
		return result;
	}
	
	public void copyShallowState(BusinessCalendar from, BusinessCalendar to) {
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
	}
	
	public void copyState(BusinessCalendar from, BusinessCalendar to) {
		for ( WorkDay child : from.getWorkDay() ) {
			to.addToWorkDay(child.getKind(),child.makeCopy());
		}
		for ( RecurringHoliday child : from.getRecurringHoliday() ) {
			to.addToRecurringHoliday(child.makeCopy());
		}
		for ( OnceOffHoliday child : from.getOnceOffHoliday() ) {
			to.addToOnceOffHoliday(child.makeCopy());
		}
		to.setBusinessHoursPerWeek(from.getBusinessHoursPerWeek());
		to.setBusinessDaysPerMonth(from.getBusinessDaysPerMonth());
		to.setBusinessHoursPerDay(from.getBusinessHoursPerDay());
	}
	
	public void createComponents() {
		if ( getWorkDay().isEmpty() ) {
			WorkDay newworkDay;
			newworkDay= new WorkDay();
			addToWorkDay(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.WEEKDAY,newworkDay);
			newworkDay= new WorkDay();
			addToWorkDay(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.SATURDAY,newworkDay);
			newworkDay= new WorkDay();
			addToWorkDay(org.opaeum.runtime.bpm.businesscalendar.WorkDayKind.SUNDAY,newworkDay);
		}
	}
	
	public OnceOffHoliday createOnceOffHoliday() {
		OnceOffHoliday newInstance= new OnceOffHoliday();
		newInstance.init((BusinessCalendar)this);
		return newInstance;
	}
	
	public RecurringHoliday createRecurringHoliday() {
		RecurringHoliday newInstance= new RecurringHoliday();
		newInstance.init((BusinessCalendar)this);
		return newInstance;
	}
	
	public WorkDay createWorkDay(WorkDayKind kind) {
		WorkDay newInstance= new WorkDay();
		newInstance.setKind(kind);
		newInstance.init((BusinessCalendar)this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessCalendar ) {
			return other==(BusinessCalendar)this || ((BusinessCalendar)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4593486266796409453l,uuid="252060@_szTxoNcDEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_szTxoNcDEeCJ0dmaHEVVnw")
	public Integer getBusinessDaysPerMonth() {
		Integer result = this.businessDaysPerMonth;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=782414597865687081l,uuid="252060@_1Hz-cNcDEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_1Hz-cNcDEeCJ0dmaHEVVnw")
	public Double getBusinessHoursPerDay() {
		Double result = this.businessHoursPerDay;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3032867667035280017l,uuid="252060@_QLpNoNcDEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_QLpNoNcDEeCJ0dmaHEVVnw")
	public Double getBusinessHoursPerWeek() {
		Double result = this.businessHoursPerWeek;
		
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4667313354449058195l,uuid="252060@_zSLd8OxiEeG5LLWI6G0Dyg")
	@NumlMetaInfo(uuid="252060@_zSLd8OxiEeG5LLWI6G0Dyg")
	static public BusinessCalendar getInstance() {
		BusinessCalendar result = BusinessCalendar.instance;
		
		return result;
	}
	
	public String getName() {
		return "BusinessCalendar["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5435749030548125197l,opposite="businessCalendar",uuid="252060@_7UFI4NcCEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_7UFI4NcCEeCJ0dmaHEVVnw")
	public Set<OnceOffHoliday> getOnceOffHoliday() {
		Set result = this.onceOffHoliday;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5488709261826613047l,opposite="businessCalendar",uuid="252060@_8YuD0VZFEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_8YuD0VZFEeGj5_I7bIwNoA")
	public OrganizationNode getOrganization() {
		OrganizationNode result = this.organization;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getOrganization();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5865908630178342957l,opposite="businessCalendar",uuid="252060@_xucEUNcCEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_xucEUNcCEeCJ0dmaHEVVnw")
	public Set<RecurringHoliday> getRecurringHoliday() {
		Set result = this.recurringHoliday;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public WorkDay getWorkDay(WorkDayKind kind) {
		WorkDay result = null;
		String key = kind.getUid();
		result=this.workDay.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2874459130083887215l,opposite="businessCalendar",uuid="252060@_K_mY0Nb-EeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_K_mY0Nb-EeCJ0dmaHEVVnw")
	public Set<WorkDay> getWorkDay() {
		Set result = new HashSet<WorkDay>(this.workDay.values());
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToOrganization((OrganizationNode)owner);
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
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
		}
		for ( WorkDay child : new ArrayList<WorkDay>(getWorkDay()) ) {
			child.markDeleted();
		}
		for ( RecurringHoliday child : new ArrayList<RecurringHoliday>(getRecurringHoliday()) ) {
			child.markDeleted();
		}
		for ( OnceOffHoliday child : new ArrayList<OnceOffHoliday>(getOnceOffHoliday()) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("workDay") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2874459130083887215")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((WorkDay)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("recurringHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5865908630178342957")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((RecurringHoliday)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("onceOffHoliday") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5435749030548125197")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((OnceOffHoliday)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("instance") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4667313354449058195")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						BusinessCalendar instance = (BusinessCalendar)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( instance!=null ) {
							z_internalAddToInstance(instance);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromOnceOffHoliday(Set<? extends OnceOffHoliday> onceOffHoliday) {
		Set<OnceOffHoliday> tmp = new HashSet<OnceOffHoliday>(onceOffHoliday);
		for ( OnceOffHoliday o : tmp ) {
			removeFromOnceOffHoliday(o);
		}
	}
	
	public void removeAllFromRecurringHoliday(Set<? extends RecurringHoliday> recurringHoliday) {
		Set<RecurringHoliday> tmp = new HashSet<RecurringHoliday>(recurringHoliday);
		for ( RecurringHoliday o : tmp ) {
			removeFromRecurringHoliday(o);
		}
	}
	
	public void removeAllFromWorkDay(Set<? extends WorkDay> workDay) {
		Set<WorkDay> tmp = new HashSet<WorkDay>(workDay);
		for ( WorkDay o : tmp ) {
			removeFromWorkDay(o.getKind(),o);
		}
	}
	
	public void removeFromOnceOffHoliday(OnceOffHoliday onceOffHoliday) {
		if ( onceOffHoliday!=null ) {
			onceOffHoliday.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromOnceOffHoliday(onceOffHoliday);
			onceOffHoliday.markDeleted();
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromRecurringHoliday(RecurringHoliday recurringHoliday) {
		if ( recurringHoliday!=null ) {
			recurringHoliday.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromRecurringHoliday(recurringHoliday);
			recurringHoliday.markDeleted();
		}
	}
	
	public void removeFromWorkDay(WorkDayKind kind, WorkDay workDay) {
		if ( workDay!=null ) {
			workDay.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromWorkDay(workDay.getKind(),workDay);
		}
	}
	
	public void setBusinessDaysPerMonth(Integer businessDaysPerMonth) {
		if ( businessDaysPerMonth == null ) {
			this.z_internalRemoveFromBusinessDaysPerMonth(getBusinessDaysPerMonth());
		} else {
			this.z_internalAddToBusinessDaysPerMonth(businessDaysPerMonth);
		}
	}
	
	public void setBusinessHoursPerDay(Double businessHoursPerDay) {
		if ( businessHoursPerDay == null ) {
			this.z_internalRemoveFromBusinessHoursPerDay(getBusinessHoursPerDay());
		} else {
			this.z_internalAddToBusinessHoursPerDay(businessHoursPerDay);
		}
	}
	
	public void setBusinessHoursPerWeek(Double businessHoursPerWeek) {
		if ( businessHoursPerWeek == null ) {
			this.z_internalRemoveFromBusinessHoursPerWeek(getBusinessHoursPerWeek());
		} else {
			this.z_internalAddToBusinessHoursPerWeek(businessHoursPerWeek);
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
	
	public void setOnceOffHoliday(Set<OnceOffHoliday> onceOffHoliday) {
		this.clearOnceOffHoliday();
		this.addAllToOnceOffHoliday(onceOffHoliday);
	}
	
	public void setOrganization(OrganizationNode organization) {
		OrganizationNode oldValue = this.getOrganization();
		if ( oldValue==null ) {
			if ( organization!=null ) {
				BusinessCalendar oldOther = (BusinessCalendar)organization.getBusinessCalendar();
				organization.z_internalRemoveFromBusinessCalendar(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromOrganization(organization);
				}
				organization.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			}
			this.z_internalAddToOrganization(organization);
		} else {
			if ( !oldValue.equals(organization) ) {
				oldValue.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
				z_internalRemoveFromOrganization(oldValue);
				if ( organization!=null ) {
					BusinessCalendar oldOther = (BusinessCalendar)organization.getBusinessCalendar();
					organization.z_internalRemoveFromBusinessCalendar(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromOrganization(organization);
					}
					organization.z_internalAddToBusinessCalendar((BusinessCalendar)this);
				}
				this.z_internalAddToOrganization(organization);
			}
		}
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
		return "<BusinessCalendar uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessCalendar ");
		sb.append("classUuid=\"252060@_x9fmQNb9EeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getBusinessHoursPerWeek()!=null ) {
			sb.append("businessHoursPerWeek=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatReal(getBusinessHoursPerWeek())+"\" ");
		}
		if ( getBusinessDaysPerMonth()!=null ) {
			sb.append("businessDaysPerMonth=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatInteger(getBusinessDaysPerMonth())+"\" ");
		}
		if ( getBusinessHoursPerDay()!=null ) {
			sb.append("businessHoursPerDay=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatReal(getBusinessHoursPerDay())+"\" ");
		}
		sb.append(">");
		sb.append("\n<workDay propertyId=\"2874459130083887215\">");
		for ( WorkDay workDay : getWorkDay() ) {
			sb.append("\n" + workDay.toXmlString());
		}
		sb.append("\n</workDay>");
		sb.append("\n<recurringHoliday propertyId=\"5865908630178342957\">");
		for ( RecurringHoliday recurringHoliday : getRecurringHoliday() ) {
			sb.append("\n" + recurringHoliday.toXmlString());
		}
		sb.append("\n</recurringHoliday>");
		sb.append("\n<onceOffHoliday propertyId=\"5435749030548125197\">");
		for ( OnceOffHoliday onceOffHoliday : getOnceOffHoliday() ) {
			sb.append("\n" + onceOffHoliday.toXmlString());
		}
		sb.append("\n</onceOffHoliday>");
		if ( getInstance()==null ) {
			sb.append("\n<instance/>");
		} else {
			sb.append("\n<instance propertyId=\"4667313354449058195\">");
			sb.append("\n" + getInstance().toXmlReferenceString());
			sb.append("\n</instance>");
		}
		sb.append("\n</BusinessCalendar>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessDaysPerMonth(Integer businessDaysPerMonth) {
		if ( businessDaysPerMonth.equals(this.businessDaysPerMonth) ) {
			return;
		}
		this.businessDaysPerMonth=businessDaysPerMonth;
	}
	
	public void z_internalAddToBusinessHoursPerDay(Double businessHoursPerDay) {
		if ( businessHoursPerDay.equals(this.businessHoursPerDay) ) {
			return;
		}
		this.businessHoursPerDay=businessHoursPerDay;
	}
	
	public void z_internalAddToBusinessHoursPerWeek(Double businessHoursPerWeek) {
		if ( businessHoursPerWeek.equals(this.businessHoursPerWeek) ) {
			return;
		}
		this.businessHoursPerWeek=businessHoursPerWeek;
	}
	
	static public void z_internalAddToInstance(BusinessCalendar instance) {
		if ( instance.equals(BusinessCalendar.instance) ) {
			return;
		}
		BusinessCalendar.instance=instance;
	}
	
	public void z_internalAddToOnceOffHoliday(OnceOffHoliday onceOffHoliday) {
		if ( this.onceOffHoliday.contains(onceOffHoliday) ) {
			return;
		}
		this.onceOffHoliday.add(onceOffHoliday);
	}
	
	public void z_internalAddToOrganization(OrganizationNode organization) {
		if ( organization.equals(this.organization) ) {
			return;
		}
		this.organization=organization;
	}
	
	public void z_internalAddToRecurringHoliday(RecurringHoliday recurringHoliday) {
		if ( this.recurringHoliday.contains(recurringHoliday) ) {
			return;
		}
		this.recurringHoliday.add(recurringHoliday);
	}
	
	public void z_internalAddToWorkDay(WorkDayKind kind, WorkDay workDay) {
		String key = kind.getUid();
		if ( this.workDay.containsValue(workDay) ) {
			return;
		}
		workDay.z_internalAddToKind(kind);
		this.workDay.put(key.toString(),workDay);
		workDay.setZ_keyOfWorkDayOnBusinessCalendar(key.toString());
	}
	
	public void z_internalRemoveFromBusinessDaysPerMonth(Integer businessDaysPerMonth) {
		if ( getBusinessDaysPerMonth()!=null && businessDaysPerMonth!=null && businessDaysPerMonth.equals(getBusinessDaysPerMonth()) ) {
			this.businessDaysPerMonth=null;
			this.businessDaysPerMonth=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerDay(Double businessHoursPerDay) {
		if ( getBusinessHoursPerDay()!=null && businessHoursPerDay!=null && businessHoursPerDay.equals(getBusinessHoursPerDay()) ) {
			this.businessHoursPerDay=null;
			this.businessHoursPerDay=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerWeek(Double businessHoursPerWeek) {
		if ( getBusinessHoursPerWeek()!=null && businessHoursPerWeek!=null && businessHoursPerWeek.equals(getBusinessHoursPerWeek()) ) {
			this.businessHoursPerWeek=null;
			this.businessHoursPerWeek=null;
		}
	}
	
	public void z_internalRemoveFromOnceOffHoliday(OnceOffHoliday onceOffHoliday) {
		this.onceOffHoliday.remove(onceOffHoliday);
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode organization) {
		if ( getOrganization()!=null && organization!=null && organization.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}
	
	public void z_internalRemoveFromRecurringHoliday(RecurringHoliday recurringHoliday) {
		this.recurringHoliday.remove(recurringHoliday);
	}
	
	public void z_internalRemoveFromWorkDay(WorkDayKind kind, WorkDay workDay) {
		String key = kind.getUid();
		this.workDay.remove(key.toString());
	}
	
	static private void setInstance(BusinessCalendar instance) {
		BusinessCalendar.z_internalAddToInstance(instance);
	}

}