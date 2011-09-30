package org.opeum.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.Date;
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
import org.opeum.annotation.NumlMetaInfo;
import org.opeum.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.opeum.runtime.bpm.util.Stdlib;
import org.opeum.runtime.domain.CancelledEvent;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.domain.IEventGenerator;
import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.domain.OutgoingEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="RecurringHoliday")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opium_bpm",name="recurring_holiday")
@NumlMetaInfo(uuid="252060@_TFKVQNb_EeCJ0dmaHEVVnw")
@AccessType("field")
public class RecurringHoliday implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	private String uid;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Column(name="name")
	private String name;
	@Index(name="idx_recurring_holiday_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="month",nullable=true)
	private Month month;
	static final private long serialVersionUID = 33;
	static private Set<RecurringHoliday> mockedAllInstances;
	@Column(name="day")
	@Min(message="",value=1,payload={},groups={})
	@Max(message="",value=31,payload={},groups={})
	private Integer day;

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
			Session session =org.opeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from RecurringHoliday").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(OpiumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("month").length()>0 ) {
			setMonth(Month.valueOf(xml.getAttribute("month")));
		}
		if ( xml.getAttribute("day").length()>0 ) {
			setDay(OpiumLibraryForBPMFormatter.getInstance().parseDayOfMonth(xml.getAttribute("day")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(RecurringHoliday from, RecurringHoliday to) {
		to.setName(from.getName());
		to.setMonth(from.getMonth());
		to.setDay(from.getDay());
	}
	
	public void copyState(RecurringHoliday from, RecurringHoliday to) {
		to.setName(from.getName());
		to.setMonth(from.getMonth());
		to.setDay(from.getDay());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof RecurringHoliday ) {
			return other==this || ((RecurringHoliday)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_xu4wQdcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this.businessCalendar;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@NumlMetaInfo(uuid="252060@_DtECgNcCEeCJ0dmaHEVVnw")
	public Integer getDay() {
		Integer result = this.day;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_EgnmYNcCEeCJ0dmaHEVVnw")
	public Month getMonth() {
		Month result = this.month;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_8kV24NcCEeCJ0dmaHEVVnw")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
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
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<RecurringHoliday uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<RecurringHoliday ");
		sb.append("classUuid=\"252060@_TFKVQNb_EeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.opeum.runtime.bpm.businesscalendar.RecurringHoliday\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ OpiumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getMonth()!=null ) {
			sb.append("month=\""+ getMonth().name() + "\" ");
		}
		if ( getDay()!=null ) {
			sb.append("day=\""+ OpiumLibraryForBPMFormatter.getInstance().formatDayOfMonth(getDay())+"\" ");
		}
		sb.append(">");
		sb.append("\n</RecurringHoliday>");
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