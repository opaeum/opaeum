package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.Date;
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
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.CancelledEvent;
import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IEventGenerator;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.OutgoingEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="OnceOffHoliday")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="once_off_holiday")
@NumlMetaInfo(qualifiedPersistentName="businesscalendar.once_off_holiday",uuid="OpiumBPM.library.uml@_5rW3kNcCEeCJ0dmaHEVVnw")
@AccessType("field")
public class OnceOffHoliday implements IEventGenerator, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	static final private long serialVersionUID = 645;
	@Column(name="date")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
	@Column(name="name")
	private String name;
	@Index(name="idx_once_off_holiday_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
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
	static private Set<OnceOffHoliday> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;

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
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from OnceOffHoliday").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("date")!=null ) {
			setDate(OpiumLibraryForBPMFormatter.getInstance().parseDate(xml.getAttribute("date")));
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
	
	@NumlMetaInfo(qualifiedPersistentName="once_off_holiday.business_calendar_id",uuid="OpiumBPM.library.uml@_7Uk4IdcCEeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		return businessCalendar;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="once_off_holiday.date",uuid="OpiumBPM.library.uml@__KuDQNcCEeCJ0dmaHEVVnw")
	public Date getDate() {
		return date;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="once_off_holiday.name",uuid="OpiumBPM.library.uml@_94Gk0NcCEeCJ0dmaHEVVnw")
	public String getName() {
		return name;
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
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().z_internalRemoveFromOnceOffHoliday((OnceOffHoliday)this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<OnceOffHoliday> newMocks) {
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
			this.getBusinessCalendar().z_internalRemoveFromOnceOffHoliday((OnceOffHoliday)this);
		}
		if ( businessCalendar!=null ) {
			businessCalendar.z_internalAddToOnceOffHoliday((OnceOffHoliday)this);
			this.z_internalAddToBusinessCalendar(businessCalendar);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDate(Date date) {
		this.z_internalAddToDate(date);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
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
		return "<onceOffHoliday uid=\""+getUid() + "\">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<onceOffHoliday");
		sb.append(" className=\"org.nakeduml.runtime.bpm.businesscalendar.OnceOffHoliday\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getDate()!=null ) {
			sb.append("date=\""+ OpiumLibraryForBPMFormatter.getInstance().formatDate(getDate())+"\" ");
		}
		if ( getName()!=null ) {
			sb.append("name=\""+ OpiumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">\n");
		sb.append("</onceOffHoliday>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val) {
		this.businessCalendar=val;
	}
	
	public void z_internalAddToDate(Date val) {
		this.date=val;
	}
	
	public void z_internalAddToName(String val) {
		this.name=val;
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val) {
		if ( getBusinessCalendar()!=null && val!=null && val.equals(getBusinessCalendar()) ) {
			this.businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromDate(Date val) {
		if ( getDate()!=null && val!=null && val.equals(getDate()) ) {
			this.date=null;
		}
	}
	
	public void z_internalRemoveFromName(String val) {
		if ( getName()!=null && val!=null && val.equals(getName()) ) {
			this.name=null;
		}
	}

}