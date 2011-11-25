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

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
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
@Entity(name="OnceOffHoliday")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",name="once_off_holiday")
@NumlMetaInfo(uuid="252060@_5rW3kNcCEeCJ0dmaHEVVnw")
@AccessType("field")
public class OnceOffHoliday implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	private String uid;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Column(name="name")
	private String _name;
	@Column(name="date")
	private Date _date;
	static final private long serialVersionUID = 53;
	static private Set<OnceOffHoliday> mockedAllInstances;
	@Index(name="idx_once_off_holiday_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar _businessCalendar;

	/** Default constructor for OnceOffHoliday
	 */
	public OnceOffHoliday() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public OnceOffHoliday(BusinessCalendar owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessCalendar().z_internalAddToOnceOffHoliday((OnceOffHoliday)this);
	}
	
	static public Set<? extends OnceOffHoliday> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from OnceOffHoliday").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_date").length()>0 ) {
			setDate(OpaeumLibraryForBPMFormatter.getInstance().parseDate(xml.getAttribute("_date")));
		}
		if ( xml.getAttribute("_name").length()>0 ) {
			setName(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("_name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(OnceOffHoliday from, OnceOffHoliday to) {
		to.setDate(from.getDate());
		to.setName(from.getName());
	}
	
	public void copyState(OnceOffHoliday from, OnceOffHoliday to) {
		to.setDate(from.getDate());
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OnceOffHoliday ) {
			return other==this || ((OnceOffHoliday)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_7Uk4IdcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this._businessCalendar;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@NumlMetaInfo(uuid="252060@__KuDQNcCEeCJ0dmaHEVVnw")
	public Date getDate() {
		Date result = this._date;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_94Gk0NcCEeCJ0dmaHEVVnw")
	public String getName() {
		String result = this._name;
		
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
	
	public OnceOffHoliday makeCopy() {
		OnceOffHoliday result = new OnceOffHoliday();
		copyState((OnceOffHoliday)this,result);
		return result;
	}
	
	public OnceOffHoliday makeShallowCopy() {
		OnceOffHoliday result = new OnceOffHoliday();
		copyShallowState((OnceOffHoliday)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().z_internalRemoveFromOnceOffHoliday((OnceOffHoliday)this);
		}
	}
	
	static public void mockAllInstances(Set<OnceOffHoliday> newMocks) {
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
	
	public void setBusinessCalendar(BusinessCalendar _businessCalendar) {
		if ( this.getBusinessCalendar()!=null ) {
			this.getBusinessCalendar().z_internalRemoveFromOnceOffHoliday((OnceOffHoliday)this);
		}
		if ( _businessCalendar!=null ) {
			_businessCalendar.z_internalAddToOnceOffHoliday((OnceOffHoliday)this);
			this.z_internalAddToBusinessCalendar(_businessCalendar);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			setDeletedOn(new Date());
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDate(Date _date) {
		this.z_internalAddToDate(_date);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setName(String _name) {
		this.z_internalAddToName(_name);
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
		return "<OnceOffHoliday uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<OnceOffHoliday ");
		sb.append("classUuid=\"252060@_5rW3kNcCEeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.OnceOffHoliday\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getDate()!=null ) {
			sb.append("_date=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDate(getDate())+"\" ");
		}
		if ( getName()!=null ) {
			sb.append("_name=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n</OnceOffHoliday>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val) {
		this._businessCalendar=val;
	}
	
	public void z_internalAddToDate(Date val) {
		this._date=val;
	}
	
	public void z_internalAddToName(String val) {
		this._name=val;
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val) {
		if ( getBusinessCalendar()!=null && val!=null && val.equals(getBusinessCalendar()) ) {
			this._businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromDate(Date val) {
		if ( getDate()!=null && val!=null && val.equals(getDate()) ) {
			this._date=null;
		}
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this._name=null;
		}
	}

}