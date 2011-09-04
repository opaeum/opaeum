package org.nakeduml.runtime.bpm.businesscalendar;

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
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.runtime.event.IEventHandler;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="RecurringHoliday")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="recurring_holiday")
@NumlMetaInfo(qualifiedPersistentName="businesscalendar.recurring_holiday",uuid="51c97f43_9068_4645_8673_20cc7aa9bec3")
@AccessType("field")
public class RecurringHoliday implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 448;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="month",nullable=true)
	private Month month;
	@Column(name="day")
	@Min(message="",value=1,payload={},groups={})
	@Max(message="",value=31,payload={},groups={})
	private Integer day;
	@Column(name="name")
	private String name;
	@Index(name="idx_recurring_holiday_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	static private Set<RecurringHoliday> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;
	@Transient
	private Map<Object, String> cancelledEvents = new HashMap<Object,String>();
	@Transient
	private Map<Object, IEventHandler> outgoingEvents = new HashMap<Object,IEventHandler>();

	/** Default constructor for RecurringHoliday
	 */
	public RecurringHoliday() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public RecurringHoliday(BusinessCalendar owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessCalendar().z_internalAddToRecurringHoliday((RecurringHoliday)this);
	}
	
	static public Set<? extends RecurringHoliday> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from RecurringHoliday").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("month")!=null ) {
			setMonth(Month.valueOf(xml.getAttribute("month")));
		}
		if ( xml.getAttribute("day")!=null ) {
			setDay(OpiumLibraryForBPMFormatter.getInstance().parseDayOfMonth(xml.getAttribute("day")));
		}
		if ( xml.getAttribute("name")!=null ) {
			setName(OpiumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(RecurringHoliday from, RecurringHoliday to) {
		to.setMonth(from.getMonth());
		to.setDay(from.getDay());
		to.setName(from.getName());
	}
	
	public void copyState(RecurringHoliday from, RecurringHoliday to) {
		to.setMonth(from.getMonth());
		to.setDay(from.getDay());
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof RecurringHoliday ) {
			return other==this || ((RecurringHoliday)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="recurring_holiday.business_calendar_id",uuid="9f20062e_7ab8_464f_b1d2_bc45bd947415")
	public BusinessCalendar getBusinessCalendar() {
		return businessCalendar;
	}
	
	public Map<Object, String> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="recurring_holiday.day",uuid="fdb68f8e_220b_4129_9257_5669e18b60e3")
	public Integer getDay() {
		return day;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="recurring_holiday.month",uuid="6d4c5519_e124_4f17_ad60_5b991df6dbaf")
	public Month getMonth() {
		return month;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="recurring_holiday.name",uuid="210197bb_91ba_4ac7_b09f_6fe3372fa372")
	public String getName() {
		return name;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Map<Object, IEventHandler> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessCalendar();
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
		this.z_internalAddToBusinessCalendar((BusinessCalendar)owner);
		createComponents();
	}
	
	public RecurringHoliday makeCopy() {
		RecurringHoliday result = new RecurringHoliday();
		copyState((RecurringHoliday)this,result);
		return result;
	}
	
	public RecurringHoliday makeShallowCopy() {
		RecurringHoliday result = new RecurringHoliday();
		copyShallowState((RecurringHoliday)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().z_internalRemoveFromRecurringHoliday((RecurringHoliday)this);
		}
	}
	
	static public void mockAllInstances(Set<RecurringHoliday> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBusinessCalendar(BusinessCalendar businessCalendar) {
		if ( this.getBusinessCalendar()!=null ) {
			this.getBusinessCalendar().z_internalRemoveFromRecurringHoliday((RecurringHoliday)this);
		}
		if ( businessCalendar!=null ) {
			businessCalendar.z_internalAddToRecurringHoliday((RecurringHoliday)this);
			this.z_internalAddToBusinessCalendar(businessCalendar);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			setDeletedOn(new Date());
		}
	}
	
	public void setCancelledEvents(Map<Object, String> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDay(Integer day) {
		this.z_internalAddToDay(day);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMonth(Month month) {
		this.z_internalAddToMonth(month);
	}
	
	public void setName(String name) {
		this.z_internalAddToName(name);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Map<Object, IEventHandler> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<recurringHoliday uid=\""+getUid() + "\">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<recurringHoliday");
		sb.append(" className=\"org.nakeduml.runtime.bpm.businesscalendar.RecurringHoliday\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getMonth()!=null ) {
			sb.append("month=\""+ getMonth() + "\" ");
		}
		if ( getDay()!=null ) {
			sb.append("day=\""+ OpiumLibraryForBPMFormatter.getInstance().formatDayOfMonth(getDay())+"\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ OpiumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">\n");
		sb.append("</recurringHoliday>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val) {
		this.businessCalendar=val;
	}
	
	public void z_internalAddToDay(Integer val) {
		this.day=val;
	}
	
	public void z_internalAddToMonth(Month val) {
		this.month=val;
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val) {
		if ( getBusinessCalendar()!=null && val!=null && val.equals(getBusinessCalendar()) ) {
			this.businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromDay(Integer val) {
		if ( getDay()!=null && val!=null && val.equals(getDay()) ) {
			this.day=null;
		}
	}
	
	public void z_internalRemoveFromMonth(Month val) {
		if ( getMonth()!=null && val!=null && val.equals(getMonth()) ) {
			this.month=null;
		}
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
		}
	}

}