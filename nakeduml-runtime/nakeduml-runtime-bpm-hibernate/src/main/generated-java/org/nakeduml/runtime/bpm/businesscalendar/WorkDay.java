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
@Entity(name="WorkDay")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@NamedQueries(value=@NamedQuery(query="from WorkDay a where a.businessCalendar = :businessCalendar and a.kind = :kind",name="QueryWorkDayWithKindForBusinessCalendar"))
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"business_calendar_id","kind","deleted_on"}),@UniqueConstraint(columnNames={"end_time_id","deleted_on"}),@UniqueConstraint(columnNames={"start_time_id","deleted_on"})},name="work_day")
@NumlMetaInfo(qualifiedPersistentName="businesscalendar.work_day",uuid="372722e6_244a_4e21_a5fd_3fd037cb2339")
@AccessType("field")
public class WorkDay implements IEventGenerator, CompositionNode, HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 435;
	@Index(name="idx_work_day_business_calendar_id",columnNames="business_calendar_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_calendar_id",nullable=true)
	private BusinessCalendar businessCalendar;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="end_time_id",nullable=true)
	private TimeOfDay endTime;
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="start_time_id",nullable=true)
	private TimeOfDay startTime;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="kind",nullable=true)
	private WorkDayKind kind;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	static private Set<WorkDay> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;
	@Transient
	private Map<Object, String> cancelledEvents = new HashMap<Object,String>();
	@Transient
	private Map<Object, IEventHandler> outgoingEvents = new HashMap<Object,IEventHandler>();

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
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.business_calendar_id",uuid="e97ea856_2de9_40c1_bf87_7e51f7f7a5d8")
	public BusinessCalendar getBusinessCalendar() {
		return businessCalendar;
	}
	
	public Map<Object, String> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.end_time_id",uuid="65471208_f5ac_4805_bad2_e0e0076290dd")
	public TimeOfDay getEndTime() {
		return endTime;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.kind",uuid="3c5448f5_c22b_41f3_8aaf_762d66613609")
	public WorkDayKind getKind() {
		return kind;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.minutes_per_day",uuid="c1729b0c_2eac_4a68_b6de_a4dd8bf1e327")
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
	
	public Map<Object, IEventHandler> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessCalendar();
	}
	
	@NumlMetaInfo(qualifiedPersistentName="work_day.start_time_id",uuid="ec085b5d_c68d_4075_ba77_a397f6716dc6")
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
			setDeletedOn(new Date());
		}
	}
	
	public void setCancelledEvents(Map<Object, String> cancelledEvents) {
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
	
	public void setOutgoingEvents(Map<Object, IEventHandler> outgoingEvents) {
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