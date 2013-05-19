package org.opaeum.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_UjTHMNb_EeCJ0dmaHEVVnw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="time_of_day",schema="bpm")
@Entity(name="TimeOfDay")
public class TimeOfDay implements IPersistentObject, HibernateEntity, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Min(message="",value=0)
	@Max(message="",value=23)
	@Column(name="hours")
	@Basic
	protected Integer hours;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="time_of_day",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Min(message="",value=0)
	@Max(message="",value=59)
	@Column(name="minutes")
	@Basic
	protected Integer minutes;
	static private Set<? extends TimeOfDay> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 2904073007558910507l;
	private String uid;
	@Index(columnNames="work_day_id",name="idx_time_of_day_work_day_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="work_day_id",nullable=true)
	protected WorkDay workDay;

	/** Default constructor for TimeOfDay
	 */
	public TimeOfDay() {
	}

	static public Set<? extends TimeOfDay> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.businesscalendar.TimeOfDay.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("hours").length()>0 ) {
			setHours(OpaeumLibraryForBPMFormatter.getInstance().parseHourOfDay(xml.getAttribute("hours")));
		}
		if ( xml.getAttribute("minutes").length()>0 ) {
			setMinutes(OpaeumLibraryForBPMFormatter.getInstance().parseMinuteOfHour(xml.getAttribute("minutes")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(TimeOfDay from, TimeOfDay to) {
		to.setHours(from.getHours());
		to.setMinutes(from.getMinutes());
	}
	
	public void copyState(TimeOfDay from, TimeOfDay to) {
		to.setHours(from.getHours());
		to.setMinutes(from.getMinutes());
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6786898535260920075l,uuid="252060@_WB_50Nb_EeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_WB_50Nb_EeCJ0dmaHEVVnw")
	public Integer getHours() {
		Integer result = this.hours;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3808798176605930566l,uuid="252060@_MUFl4NcGEeCOrPzFUqsJFw")
	@NumlMetaInfo(uuid="252060@_MUFl4NcGEeCOrPzFUqsJFw")
	public Integer getMinuteOfDay() {
		Integer result = (this.getHours() * 60) + this.getMinutes();
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5836556839916014923l,uuid="252060@_XW53QNb_EeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_XW53QNb_EeCJ0dmaHEVVnw")
	public Integer getMinutes() {
		Integer result = this.minutes;
		
		return result;
	}
	
	public String getName() {
		return "TimeOfDay["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7617892952965707261l,opposite="endTime",uuid="252060@_5yG1QdcBEeCJ0dmaHEVVnw")
	@NumlMetaInfo(uuid="252060@_5yG1QdcBEeCJ0dmaHEVVnw")
	public WorkDay getWorkDay() {
		WorkDay result = this.workDay;
		
		return result;
	}
	
	public TimeOfDay makeCopy() {
		TimeOfDay result = new TimeOfDay();
		copyState((TimeOfDay)this,result);
		return result;
	}
	
	public TimeOfDay makeShallowCopy() {
		TimeOfDay result = new TimeOfDay();
		copyShallowState((TimeOfDay)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getWorkDay()!=null ) {
			getWorkDay().z_internalRemoveFromEndTime(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setHours(Integer hours) {
		if ( hours == null ) {
			this.z_internalRemoveFromHours(getHours());
		} else {
			this.z_internalAddToHours(hours);
		}
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMinutes(Integer minutes) {
		if ( minutes == null ) {
			this.z_internalRemoveFromMinutes(getMinutes());
		} else {
			this.z_internalAddToMinutes(minutes);
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setWorkDay(WorkDay workDay) {
		WorkDay oldValue = this.getWorkDay();
		if ( oldValue==null ) {
			if ( workDay!=null ) {
				TimeOfDay oldOther = (TimeOfDay)workDay.getEndTime();
				workDay.z_internalRemoveFromEndTime(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromWorkDay(workDay);
				}
				workDay.z_internalAddToEndTime((TimeOfDay)this);
			}
			this.z_internalAddToWorkDay(workDay);
		} else {
			if ( !oldValue.equals(workDay) ) {
				oldValue.z_internalRemoveFromEndTime(this);
				z_internalRemoveFromWorkDay(oldValue);
				if ( workDay!=null ) {
					TimeOfDay oldOther = (TimeOfDay)workDay.getEndTime();
					workDay.z_internalRemoveFromEndTime(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromWorkDay(workDay);
					}
					workDay.z_internalAddToEndTime((TimeOfDay)this);
				}
				this.z_internalAddToWorkDay(workDay);
			}
		}
	}
	
	public String toXmlReferenceString() {
		return "<TimeOfDay uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<TimeOfDay ");
		sb.append("classUuid=\"252060@_UjTHMNb_EeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.TimeOfDay\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getHours()!=null ) {
			sb.append("hours=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatHourOfDay(getHours())+"\" ");
		}
		if ( getMinutes()!=null ) {
			sb.append("minutes=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatMinuteOfHour(getMinutes())+"\" ");
		}
		sb.append(">");
		sb.append("\n</TimeOfDay>");
		return sb.toString();
	}
	
	public void z_internalAddToHours(Integer hours) {
		if ( hours.equals(this.hours) ) {
			return;
		}
		this.hours=hours;
	}
	
	public void z_internalAddToMinutes(Integer minutes) {
		if ( minutes.equals(this.minutes) ) {
			return;
		}
		this.minutes=minutes;
	}
	
	public void z_internalAddToWorkDay(WorkDay workDay) {
		if ( workDay.equals(this.workDay) ) {
			return;
		}
		this.workDay=workDay;
	}
	
	public void z_internalRemoveFromHours(Integer hours) {
		if ( getHours()!=null && hours!=null && hours.equals(getHours()) ) {
			this.hours=null;
			this.hours=null;
		}
	}
	
	public void z_internalRemoveFromMinutes(Integer minutes) {
		if ( getMinutes()!=null && minutes!=null && minutes.equals(getMinutes()) ) {
			this.minutes=null;
			this.minutes=null;
		}
	}
	
	public void z_internalRemoveFromWorkDay(WorkDay workDay) {
		if ( getWorkDay()!=null && workDay!=null && workDay.equals(getWorkDay()) ) {
			this.workDay=null;
			this.workDay=null;
		}
	}

}