package org.opaeum.runtime.bpm.businesscalendar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.ParameterMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_x9fmQNb9EeCJ0dmaHEVVnw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_calendar",uniqueConstraints=
	@UniqueConstraint(columnNames={"organization_id","deleted_on"}))
@MappedSuperclass
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class BusinessCalendarGenerated implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Column(name="business_days_per_month")
	private Integer businessDaysPerMonth;
	@Column(name="business_hours_per_day")
	private Double businessHoursPerDay;
	@Column(name="business_hours_per_week")
	private Double businessHoursPerWeek;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<BusinessCalendar> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCalendar",targetEntity=OnceOffHoliday.class)
	private Set<OnceOffHoliday> onceOffHoliday = new HashSet<OnceOffHoliday>();
	@Index(columnNames="organization_id",name="idx_business_calendar_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	private OrganizationNode organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport((BusinessCalendar)this);
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCalendar",targetEntity=RecurringHoliday.class)
	private Set<RecurringHoliday> recurringHoliday = new HashSet<RecurringHoliday>();
	static final private long serialVersionUID = 4208594208280739637l;
	private String uid;
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy="businessCalendar",targetEntity=WorkDay.class)
	@MapKey(name="z_keyOfWorkDayOnBusinessCalendar")
	private Map<String, WorkDay> workDay = new HashMap<String,WorkDay>();

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
	
	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	@NumlMetaInfo(uuid="252060@_NTccANcEEeCJ0dmaHEVVnw")
	public Date addTimeTo(@ParameterMetaInfo(name="fromDateTime",opaeumId=5908544747543073361l,uuid="252060@_OJSe4NcEEeCJ0dmaHEVVnw") Date fromDateTime, @ParameterMetaInfo(name="timeUnit",opaeumId=2654263369633975777l,uuid="252060@_QPwcINcEEeCJ0dmaHEVVnw") BusinessTimeUnit timeUnit, @ParameterMetaInfo(name="numberOfUnits",opaeumId=446023254321750925l,uuid="252060@_WfbDYNcEEeCJ0dmaHEVVnw") Double numberOfUnits) {
		Date result = null;
		
		return result;
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
		getOrganization().z_internalAddToBusinessCalendar((BusinessCalendar)this);
	}
	
	public void addToRecurringHoliday(RecurringHoliday recurringHoliday) {
		if ( recurringHoliday!=null ) {
			recurringHoliday.z_internalRemoveFromBusinessCalendar(recurringHoliday.getBusinessCalendar());
			recurringHoliday.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToRecurringHoliday(recurringHoliday);
		}
	}
	
	public void addToWorkDay(WorkDayKind kind, WorkDay workDay) {
		if ( workDay!=null ) {
			workDay.z_internalRemoveFromBusinessCalendar(workDay.getBusinessCalendar());
			workDay.z_internalAddToBusinessCalendar((BusinessCalendar)this);
			z_internalAddToWorkDay(kind,workDay);
		}
	}
	
	static public Set<? extends BusinessCalendar> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.businesscalendar.BusinessCalendar.class));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToWorkDay(curVal.getKind(),curVal);
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToRecurringHoliday(curVal);
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.addToOnceOffHoliday(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	@NumlMetaInfo(uuid="252060@_mOhZgNcEEeCJ0dmaHEVVnw")
	public Duration calculateTimeBetween(@ParameterMetaInfo(name="fromDateTIme",opaeumId=7202429714693706761l,uuid="252060@_nlX3sNcEEeCJ0dmaHEVVnw") Date fromDateTIme, @ParameterMetaInfo(name="toDateTime",opaeumId=250162200899249375l,uuid="252060@_nm_BQNcEEeCJ0dmaHEVVnw") Date toDateTime, @ParameterMetaInfo(name="businessTimeUnit",opaeumId=5406692947055461229l,uuid="252060@_nogrQNcEEeCJ0dmaHEVVnw") BusinessTimeUnit businessTimeUnit) {
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
		Set<WorkDay> tmp = new HashSet<WorkDay>(getWorkDay());
		for ( WorkDay o : tmp ) {
			removeFromWorkDay(o.getKind(),o);
		}
		workDay.clear();
	}
	
	@NumlMetaInfo(uuid="252060@_dXLYsASTEeGb9qsDxKJdSA")
	public Duration convertDuration(@ParameterMetaInfo(name="from",opaeumId=2105497212461834893l,uuid="252060@_e8ABcASTEeGb9qsDxKJdSA") Duration from, @ParameterMetaInfo(name="toTimeUnit",opaeumId=8309674443127600973l,uuid="252060@_ixMxYASTEeGb9qsDxKJdSA") BusinessTimeUnit toTimeUnit) {
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
			return other==this || ((BusinessCalendar)other).getUid().equals(this.getUid());
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
	
	public String getName() {
		return "BusinessCalendar["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5435749030548125197l,opposite="businessCalendar",uuid="252060@_7UFI4NcCEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_7UFI4NcCEeCJ0dmaHEVVnw")
	public Set<OnceOffHoliday> getOnceOffHoliday() {
		Set<OnceOffHoliday> result = this.onceOffHoliday;
		
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
		Set<RecurringHoliday> result = this.recurringHoliday;
		
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
		StringBuilder key = new StringBuilder();
		key.append(kind.getUid());
		result=this.workDay.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2874459130083887215l,opposite="businessCalendar",uuid="252060@_K_mY0Nb-EeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_K_mY0Nb-EeCJ0dmaHEVVnw")
	public Set<WorkDay> getWorkDay() {
		Set<WorkDay> result = new HashSet<WorkDay>(this.workDay.values());
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToOrganization((OrganizationNode)owner);
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
	
	static public void mockAllInstances(Set<BusinessCalendar> newMocks) {
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
	
	public void removeFromWorkDay(WorkDayKind kind, WorkDay workDay) {
		if ( workDay!=null ) {
			workDay.z_internalRemoveFromBusinessCalendar((BusinessCalendar)this);
			z_internalRemoveFromWorkDay(kind,workDay);
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setBusinessDaysPerMonth(Integer businessDaysPerMonth) {
		propertyChangeSupport.firePropertyChange("businessDaysPerMonth",getBusinessDaysPerMonth(),businessDaysPerMonth);
		this.z_internalAddToBusinessDaysPerMonth(businessDaysPerMonth);
	}
	
	public void setBusinessHoursPerDay(Double businessHoursPerDay) {
		propertyChangeSupport.firePropertyChange("businessHoursPerDay",getBusinessHoursPerDay(),businessHoursPerDay);
		this.z_internalAddToBusinessHoursPerDay(businessHoursPerDay);
	}
	
	public void setBusinessHoursPerWeek(Double businessHoursPerWeek) {
		propertyChangeSupport.firePropertyChange("businessHoursPerWeek",getBusinessHoursPerWeek(),businessHoursPerWeek);
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
		propertyChangeSupport.firePropertyChange("onceOffHoliday",getOnceOffHoliday(),onceOffHoliday);
		this.clearOnceOffHoliday();
		this.addAllToOnceOffHoliday(onceOffHoliday);
	}
	
	public void setOrganization(OrganizationNode organization) {
		OrganizationNode oldValue = this.getOrganization();
		propertyChangeSupport.firePropertyChange("organization",getOrganization(),organization);
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
		propertyChangeSupport.firePropertyChange("recurringHoliday",getRecurringHoliday(),recurringHoliday);
		this.clearRecurringHoliday();
		this.addAllToRecurringHoliday(recurringHoliday);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
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
		sb.append("\n</BusinessCalendar>");
		return sb.toString();
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
	
	public void z_internalAddToOrganization(OrganizationNode val) {
		this.organization=val;
	}
	
	public void z_internalAddToRecurringHoliday(RecurringHoliday val) {
		this.recurringHoliday.add(val);
	}
	
	public void z_internalAddToWorkDay(WorkDayKind kind, WorkDay val) {
		StringBuilder key = new StringBuilder();
		key.append(kind.getUid());
		val.z_internalAddToKind(kind);
		this.workDay.put(key.toString(),val);
		val.setZ_keyOfWorkDayOnBusinessCalendar(key.toString());
	}
	
	public void z_internalRemoveFromBusinessDaysPerMonth(Integer val) {
		if ( getBusinessDaysPerMonth()!=null && val!=null && val.equals(getBusinessDaysPerMonth()) ) {
			this.businessDaysPerMonth=null;
			this.businessDaysPerMonth=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerDay(Double val) {
		if ( getBusinessHoursPerDay()!=null && val!=null && val.equals(getBusinessHoursPerDay()) ) {
			this.businessHoursPerDay=null;
			this.businessHoursPerDay=null;
		}
	}
	
	public void z_internalRemoveFromBusinessHoursPerWeek(Double val) {
		if ( getBusinessHoursPerWeek()!=null && val!=null && val.equals(getBusinessHoursPerWeek()) ) {
			this.businessHoursPerWeek=null;
			this.businessHoursPerWeek=null;
		}
	}
	
	public void z_internalRemoveFromOnceOffHoliday(OnceOffHoliday val) {
		this.onceOffHoliday.remove(val);
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode val) {
		if ( getOrganization()!=null && val!=null && val.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}
	
	public void z_internalRemoveFromRecurringHoliday(RecurringHoliday val) {
		this.recurringHoliday.remove(val);
	}
	
	public void z_internalRemoveFromWorkDay(WorkDayKind kind, WorkDay val) {
		StringBuilder key = new StringBuilder();
		key.append(kind.getUid());
		this.workDay.remove(key.toString());
	}

}