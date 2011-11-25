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
@Entity(name="WorkDay")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@NamedQueries(value=@NamedQuery(query="from WorkDay a where a._businessCalendar = :_businessCalendar and a._kind = :_kind",name="QueryWorkDayWith_kindFor_businessCalendar"))
@Table(schema="opaeum_bpm",uniqueConstraints={@UniqueConstraint(columnNames={"business_calendar_id","kind","deleted_on"}),@UniqueConstraint(columnNames={"end_time_id","deleted_on"}),@UniqueConstraint(columnNames={"start_time_id","deleted_on"})},name="work_day")
@NumlMetaInfo(uuid="252060@_Jn9QcNb-EeCJ0dmaHEVVnw")
@AccessType("field")
public class WorkDay implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
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
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private WorkDayKind _kind;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="end_time_id",nullable=true)
	private TimeOfDay _endTime;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="start_time_id",nullable=true)
	private TimeOfDay _startTime;
	static final private long serialVersionUID = 65;
	static private Set<WorkDay> mockedAllInstances;
	@Index(name="idx_work_day_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar _businessCalendar;

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
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from WorkDay").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_kind").length()>0 ) {
			setKind(WorkDayKind.valueOf(xml.getAttribute("_kind")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_endTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("308")) ) {
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_startTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("310")) ) {
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
	
	public boolean equals(Object other) {
		if ( other instanceof WorkDay ) {
			return other==this || ((WorkDay)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_LAOD4db-EeCJ0dmaHEVVnw")
	public BusinessCalendar getBusinessCalendar() {
		BusinessCalendar result = this._businessCalendar;
		
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
		TimeOfDay result = this._endTime;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_LrAGRNb-EeCJ0dmaHEVVnw")
	public WorkDayKind getKind() {
		WorkDayKind result = this._kind;
		
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
		TimeOfDay result = this._startTime;
		
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_endTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("308")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((TimeOfDay)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("_startTime") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("310")) ) {
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
	
	public void setBusinessCalendar(BusinessCalendar _businessCalendar) {
		if ( this.getBusinessCalendar()!=null ) {
			this.getBusinessCalendar().z_internalRemoveFromWorkDay((WorkDay)this);
		}
		if ( _businessCalendar!=null ) {
			_businessCalendar.z_internalAddToWorkDay((WorkDay)this);
			this.z_internalAddToBusinessCalendar(_businessCalendar);
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
	
	public void setEndTime(TimeOfDay _endTime) {
		this.z_internalAddToEndTime(_endTime);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setKind(WorkDayKind _kind) {
		this.z_internalAddToKind(_kind);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setStartTime(TimeOfDay _startTime) {
		this.z_internalAddToStartTime(_startTime);
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
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.WorkDay\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getKind()!=null ) {
			sb.append("_kind=\""+ getKind().name() + "\" ");
		}
		sb.append(">");
		if ( getEndTime()==null ) {
			sb.append("\n<_endTime/>");
		} else {
			sb.append("\n<_endTime propertyId=\"308\">");
			sb.append("\n" + getEndTime().toXmlString());
			sb.append("\n</_endTime>");
		}
		if ( getStartTime()==null ) {
			sb.append("\n<_startTime/>");
		} else {
			sb.append("\n<_startTime propertyId=\"310\">");
			sb.append("\n" + getStartTime().toXmlString());
			sb.append("\n</_startTime>");
		}
		sb.append("\n</WorkDay>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCalendar(BusinessCalendar val) {
		this._businessCalendar=val;
	}
	
	public void z_internalAddToEndTime(TimeOfDay val) {
		this._endTime=val;
	}
	
	public void z_internalAddToKind(WorkDayKind val) {
		this._kind=val;
	}
	
	public void z_internalAddToStartTime(TimeOfDay val) {
		this._startTime=val;
	}
	
	public void z_internalRemoveFromBusinessCalendar(BusinessCalendar val) {
		if ( getBusinessCalendar()!=null && val!=null && val.equals(getBusinessCalendar()) ) {
			this._businessCalendar=null;
		}
	}
	
	public void z_internalRemoveFromEndTime(TimeOfDay val) {
		if ( getEndTime()!=null && val!=null && val.equals(getEndTime()) ) {
			this._endTime=null;
		}
	}
	
	public void z_internalRemoveFromKind(WorkDayKind val) {
		if ( getKind()!=null && val!=null && val.equals(getKind()) ) {
			this._kind=null;
		}
	}
	
	public void z_internalRemoveFromStartTime(TimeOfDay val) {
		if ( getStartTime()!=null && val!=null && val.equals(getStartTime()) ) {
			this._startTime=null;
		}
	}

}