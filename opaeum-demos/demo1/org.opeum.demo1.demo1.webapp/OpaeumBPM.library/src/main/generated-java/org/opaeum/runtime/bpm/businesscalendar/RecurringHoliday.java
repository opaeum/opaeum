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
import javax.persistence.Entity;
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

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
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
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_TFKVQNb_EeCJ0dmaHEVVnw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="recurring_holiday")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="RecurringHoliday")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class RecurringHoliday implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Index(columnNames="business_calendar_id",name="idx_recurring_holiday_business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Min(groups={},message="",payload={},value=1)
	@Max(groups={},message="",payload={},value=31)
	@Column(name="day")
	private Integer day;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<RecurringHoliday> mockedAllInstances;
	@Type(type="org.opaeum.runtime.bpm.businesscalendar.MonthResolver")
	@Column(name="month",nullable=true)
	private Month month;
	@Column(name="name")
	private String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	static final private long serialVersionUID = 6208240707029980235l;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public RecurringHoliday(BusinessCalendar owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for RecurringHoliday
	 */
	public RecurringHoliday() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessCalendar().z_internalAddToRecurringHoliday((RecurringHoliday)this);
	}
	
	static public Set<? extends RecurringHoliday> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.businesscalendar.RecurringHoliday.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("day").length()>0 ) {
			setDay(OpaeumLibraryForBPMFormatter.getInstance().parseDayOfMonth(xml.getAttribute("day")));
		}
		if ( xml.getAttribute("month").length()>0 ) {
			setMonth(Month.valueOf(xml.getAttribute("month")));
		}
		if ( xml.getAttribute("name").length()>0 ) {
			setName(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(RecurringHoliday from, RecurringHoliday to) {
		to.setDay(from.getDay());
		to.setMonth(from.getMonth());
		to.setName(from.getName());
	}
	
	public void copyState(RecurringHoliday from, RecurringHoliday to) {
		to.setDay(from.getDay());
		to.setMonth(from.getMonth());
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=7899854084172381059,opposite="recurringHoliday",uuid="252060@_xu4wQdcCEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_xu4wQdcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this.businessCalendar;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=2528290867262960345,uuid="252060@_DtECgNcCEeCJ0dmaHEVVnw")
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
	
	@PropertyMetaInfo(isComposite=false,opaeumId=660142128285799895,uuid="252060@_EgnmYNcCEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_EgnmYNcCEeCJ0dmaHEVVnw")
	public Month getMonth() {
		Month result = this.month;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=6018749174316171601,uuid="252060@_8kV24NcCEeCJ0dmaHEVVnw")
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
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().z_internalRemoveFromRecurringHoliday(this);
		}
		setDeletedOn(new Date());
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
			this.getBusinessCalendar().z_internalRemoveFromRecurringHoliday(this);
		}
		if ( businessCalendar!=null ) {
			businessCalendar.z_internalAddToRecurringHoliday(this);
			this.z_internalAddToBusinessCalendar(businessCalendar);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
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
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.RecurringHoliday\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDay()!=null ) {
			sb.append("day=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDayOfMonth(getDay())+"\" ");
		}
		if ( getMonth()!=null ) {
			sb.append("month=\""+ getMonth().name() + "\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
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
			this.businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromDay(Integer val) {
		if ( getDay()!=null && val!=null && val.equals(getDay()) ) {
			this.day=null;
			this.day=null;
		}
	}
	
	public void z_internalRemoveFromMonth(Month val) {
		if ( getMonth()!=null && val!=null && val.equals(getMonth()) ) {
			this.month=null;
			this.month=null;
		}
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}

}