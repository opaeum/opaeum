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
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.businesscalendar.impl.BusinessCalendar;
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

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="WorkDay")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@NamedQueries(value=@NamedQuery(query="from WorkDay a where a.businessCalendar = :businessCalendar and a.kind = :kind",name="QueryWorkDayWithKindForBusinessCalendar"))
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"end_time_id","deleted_on"}),@UniqueConstraint(columnNames={"start_time_id","deleted_on"}),@UniqueConstraint(columnNames={"business_calendar_id","kind","deleted_on"})},name="work_day")
@NumlMetaInfo(qualifiedPersistentName="businesscalendar.work_day",uuid="OpiumBPM.library.uml@_Jn9QcNb-EeCJ0dmaHEVVnw")
@AccessType("field")
public class WorkDay implements IEventGenerator, HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	static final private long serialVersionUID = 642;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="end_time_id",nullable=true)
	private TimeOfDay endTime;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="start_time_id",nullable=true)
	private TimeOfDay startTime;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private WorkDayKind kind;
	@Index(name="idx_work_day_business_calendar_id",columnNames="business_calendar_id")
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
	static private Set<WorkDay> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;

	/** Default constructor for WorkDay
	 */
	public WorkDay() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public WorkDay(BusinessCalendar owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getBusinessCalendar().z_internalAddToWorkDay((WorkDay)this);
	}
	
	static public Set<? extends WorkDay> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from WorkDay").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("kind")!=null ) {
			setKind(WorkDayKind.valueOf(xml.getAttribute("kind")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("endTime") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TimeOfDay curVal = (TimeOfDay)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.setEndTime(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals("startTime") ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TimeOfDay curVal = (TimeOfDay)IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute("className")));
						this.setStartTime(curVal);
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void copyShallowState(WorkDay from, WorkDay to) {
		if ( from.getEndTime()!=null ) {
			to.setEndTime(from.getEndTime().makeShallowCopy());
		}
		if ( from.getStartTime()!=null ) {
			to.setStartTime(from.getStartTime().makeShallowCopy());
		}
		to.setKind(from.getKind());
	}
	
	public void copyState(WorkDay from, WorkDay to) {
		if ( from.getEndTime()!=null ) {
			to.setEndTime(from.getEndTime().makeCopy());
		}
		if ( from.getStartTime()!=null ) {
			to.setStartTime(from.getStartTime().makeCopy());
		}
		to.setKind(from.getKind());
	}
	
	public void createComponents() {
		if ( getEndTime()==null ) {
			setEndTime(new TimeOfDay());
		}
		if ( getStartTime()==null ) {
			setStartTime(new TimeOfDay());
		}
	}
	
	public TimeOfDay createEndTime() {
		TimeOfDay newInstance= new TimeOfDay();
		return newInstance;
	}
	
	public TimeOfDay createStartTime() {
		TimeOfDay newInstance= new TimeOfDay();
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof WorkDay ) {
			return other==this || ((WorkDay)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.business_calendar_id",uuid="OpiumBPM.library.uml@_LAOD4db-EeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		return businessCalendar;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.end_time_id",uuid="OpiumBPM.library.uml@_5xvo4NcBEeCJ0dmaHEVVnw")
	public TimeOfDay getEndTime() {
		return endTime;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.kind",uuid="OpiumBPM.library.uml@_LrAGRNb-EeCJ0dmaHEVVnw")
	public WorkDayKind getKind() {
		return kind;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.minutes_per_day",uuid="OpiumBPM.library.uml@_vEgCENcMEeCnccVVb6bGDQ")
	public Integer getMinutesPerDay() {
		Integer minutesPerDay = (this.getEndTime().getMinuteOfDay() - this.getStartTime().getMinuteOfDay());
		return minutesPerDay;
	}
	
	public String getName() {
		return "WorkDay["+getId()+"]";
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
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.start_time_id",uuid="OpiumBPM.library.uml@_xyUUMNcBEeCJ0dmaHEVVnw")
	public TimeOfDay getStartTime() {
		return startTime;
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
	
	public WorkDay makeCopy() {
		WorkDay result = new WorkDay();
		copyState((WorkDay)this,result);
		return result;
	}
	
	public WorkDay makeShallowCopy() {
		WorkDay result = new WorkDay();
		copyShallowState((WorkDay)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().z_internalRemoveFromWorkDay((WorkDay)this);
		}
		if ( getEndTime()!=null ) {
			getEndTime().markDeleted();
		}
		if ( getStartTime()!=null ) {
			getStartTime().markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<WorkDay> newMocks) {
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
			this.getBusinessCalendar().z_internalRemoveFromWorkDay((WorkDay)this);
		}
		if ( businessCalendar!=null ) {
			businessCalendar.z_internalAddToWorkDay((WorkDay)this);
			this.z_internalAddToBusinessCalendar(businessCalendar);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setEndTime(TimeOfDay endTime) {
		this.z_internalAddToEndTime(endTime);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setKind(WorkDayKind kind) {
		this.z_internalAddToKind(kind);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setStartTime(TimeOfDay startTime) {
		this.z_internalAddToStartTime(startTime);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<workDay uid=\""+getUid() + "\">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<workDay");
		sb.append(" className=\"org.nakeduml.runtime.bpm.businesscalendar.WorkDay\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getKind()!=null ) {
			sb.append("kind=\""+ getKind() + "\" ");
		}
		sb.append(">\n");
		if ( getEndTime()==null ) {
			sb.append("<endTime/>");
		} else {
			sb.append("<endTime>");
			sb.append(getEndTime().toXmlString());
			sb.append("</endTime>");
			sb.append("\n");
		}
		if ( getStartTime()==null ) {
			sb.append("<startTime/>");
		} else {
			sb.append("<startTime>");
			sb.append(getStartTime().toXmlString());
			sb.append("</startTime>");
			sb.append("\n");
		}
		sb.append("</workDay>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val) {
		this.businessCalendar=val;
	}
	
	public void z_internalAddToEndTime(TimeOfDay val) {
		this.endTime=val;
	}
	
	public void z_internalAddToKind(WorkDayKind val) {
		this.kind=val;
	}
	
	public void z_internalAddToStartTime(TimeOfDay val) {
		this.startTime=val;
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val) {
		if ( getBusinessCalendar()!=null && val!=null && val.equals(getBusinessCalendar()) ) {
			this.businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromEndTime(TimeOfDay val) {
		if ( getEndTime()!=null && val!=null && val.equals(getEndTime()) ) {
			this.endTime=null;
		}
	}
	
	public void z_internalRemoveFromKind(WorkDayKind val) {
		if ( getKind()!=null && val!=null && val.equals(getKind()) ) {
			this.kind=null;
		}
	}
	
	public void z_internalRemoveFromStartTime(TimeOfDay val) {
		if ( getStartTime()!=null && val!=null && val.equals(getStartTime()) ) {
			this.startTime=null;
		}
	}

}