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
import org.opeum.annotation.NumlMetaInfo;
import org.opeum.runtime.bpm.util.Stdlib;
import org.opeum.runtime.domain.CancelledEvent;
import org.opeum.runtime.domain.CompositionNode;
import org.opeum.runtime.domain.HibernateEntity;
import org.opeum.runtime.domain.IEventGenerator;
import org.opeum.runtime.domain.IPersistentObject;
import org.opeum.runtime.domain.IntrospectionUtil;
import org.opeum.runtime.domain.OutgoingEvent;
import org.opeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="WorkDay")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@NamedQueries(value=@NamedQuery(query="from WorkDay a where a.businessCalendar = :businessCalendar and a.kind = :kind",name="QueryWorkDayWithKindForBusinessCalendar"))
@Table(schema="opium_bpm",uniqueConstraints={@UniqueConstraint(columnNames={"end_time_id","deleted_on"}),@UniqueConstraint(columnNames={"start_time_id","deleted_on"}),@UniqueConstraint(columnNames={"business_calendar_id","kind","deleted_on"})},name="work_day")
@NumlMetaInfo(uuid="252060@_Jn9QcNb-EeCJ0dmaHEVVnw")
@AccessType("field")
public class WorkDay implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
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
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="start_time_id",nullable=true)
	private TimeOfDay startTime;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Index(name="idx_work_day_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 22;
	static private Set<WorkDay> mockedAllInstances;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="end_time_id",nullable=true)
	private TimeOfDay endTime;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private WorkDayKind kind;

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
			Session session =org.opeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from WorkDay").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("kind").length()>0 ) {
			setKind(WorkDayKind.valueOf(xml.getAttribute("kind")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("endTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("130")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TimeOfDay curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setEndTime(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("startTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("128")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						TimeOfDay curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setStartTime(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void copyShallowState(WorkDay from, WorkDay to) {
		to.setKind(from.getKind());
		if ( from.getEndTime()!=null ) {
			to.setEndTime(from.getEndTime().makeShallowCopy());
		}
		if ( from.getStartTime()!=null ) {
			to.setStartTime(from.getStartTime().makeShallowCopy());
		}
	}
	
	public void copyState(WorkDay from, WorkDay to) {
		to.setKind(from.getKind());
		if ( from.getEndTime()!=null ) {
			to.setEndTime(from.getEndTime().makeCopy());
		}
		if ( from.getStartTime()!=null ) {
			to.setStartTime(from.getStartTime().makeCopy());
		}
	}
	
	public void createComponents() {
		if ( getEndTime()==null ) {
			setEndTime(new TimeOfDay());
		}
		if ( getStartTime()==null ) {
			setStartTime(new TimeOfDay());
		}
	}
	
	public boolean equals(Object other) {
		if ( other instanceof WorkDay ) {
			return other==this || ((WorkDay)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_LAOD4db-EeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this.businessCalendar;
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(uuid="252060@_5xvo4NcBEeCJ0dmaHEVVnw")
	public TimeOfDay getEndTime() {
		TimeOfDay result = this.endTime;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_LrAGRNb-EeCJ0dmaHEVVnw")
	public WorkDayKind getKind() {
		WorkDayKind result = this.kind;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_vEgCENcMEeCnccVVb6bGDQ")
	public Integer getMinutesPerDay() {
		Integer result = (this.getEndTime().getMinuteOfDay() - this.getStartTime().getMinuteOfDay());
		
		return result;
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
	
	@NumlMetaInfo(uuid="252060@_xyUUMNcBEeCJ0dmaHEVVnw")
	public TimeOfDay getStartTime() {
		TimeOfDay result = this.startTime;
		
		return result;
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessCalendar()!=null ) {
			getBusinessCalendar().z_internalRemoveFromWorkDay((WorkDay)this);
		}
		if ( getEndTime()!=null ) {
			getEndTime().markDeleted();
		}
		if ( getStartTime()!=null ) {
			getStartTime().markDeleted();
		}
	}
	
	static public void mockAllInstances(Set<WorkDay> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("endTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("130")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((TimeOfDay)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("startTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("128")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((TimeOfDay)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
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
			setDeletedOn(new Date());
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
		return "<WorkDay uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<WorkDay ");
		sb.append("classUuid=\"252060@_Jn9QcNb-EeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.opeum.runtime.bpm.businesscalendar.WorkDay\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getKind()!=null ) {
			sb.append("kind=\""+ getKind().name() + "\" ");
		}
		sb.append(">");
		if ( getEndTime()==null ) {
			sb.append("\n<endTime/>");
		} else {
			sb.append("\n<endTime propertyId=\"130\">");
			sb.append("\n" + getEndTime().toXmlString());
			sb.append("\n</endTime>");
		}
		if ( getStartTime()==null ) {
			sb.append("\n<startTime/>");
		} else {
			sb.append("\n<startTime propertyId=\"128\">");
			sb.append("\n" + getStartTime().toXmlString());
			sb.append("\n</startTime>");
		}
		sb.append("\n</WorkDay>");
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