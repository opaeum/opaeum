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
@Entity(name="OnceOffHoliday")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="once_off_holiday")
@NumlMetaInfo(qualifiedPersistentName="businesscalendar.once_off_holiday",uuid="354e353a_7b7c_4e54_8d8e_122d936f997a")
@AccessType("field")
public class OnceOffHoliday implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 447;
	@Index(name="idx_once_off_holiday_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
	@Column(name="name")
	private String name;
	@Column(name="date")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date date;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	static private Set<OnceOffHoliday> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;
	@Transient
	private Map<Object, String> cancelledEvents = new HashMap<Object,String>();
	@Transient
	private Map<Object, IEventHandler> outgoingEvents = new HashMap<Object,IEventHandler>();

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
		if ( xml.getAttribute("name")!=null ) {
			setName(OpiumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("date")!=null ) {
			setDate(OpiumLibraryForBPMFormatter.getInstance().parseDate(xml.getAttribute("date")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(OnceOffHoliday from, OnceOffHoliday to) {
		to.setName(from.getName());
		to.setDate(from.getDate());
	}
	
	public void copyState(OnceOffHoliday from, OnceOffHoliday to) {
		to.setName(from.getName());
		to.setDate(from.getDate());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OnceOffHoliday ) {
			return other==this || ((OnceOffHoliday)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="once_off_holiday.business_calendar_id",uuid="575e9546_710e_48a5_a8c3_88e4fa347802")
	public BusinessCalendar getBusinessCalendar() {
		return businessCalendar;
	}
	
	public Map<Object, String> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="once_off_holiday.date",uuid="56623ac2_82cd_4020_b81c_f255f863c67d")
	public Date getDate() {
		return date;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="once_off_holiday.name",uuid="26b0d069_5ed5_417e_9f8a_3b0bbde6714c")
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
			setDeletedOn(new Date());
		}
	}
	
	public void setCancelledEvents(Map<Object, String> cancelledEvents) {
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
	
	public void setOutgoingEvents(Map<Object, IEventHandler> outgoingEvents) {
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
		if ( getName()!=null ) {
			sb.append("name=\""+ OpiumLibraryForBPMFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getDate()!=null ) {
			sb.append("date=\""+ OpiumLibraryForBPMFormatter.getInstance().formatDate(getDate())+"\" ");
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