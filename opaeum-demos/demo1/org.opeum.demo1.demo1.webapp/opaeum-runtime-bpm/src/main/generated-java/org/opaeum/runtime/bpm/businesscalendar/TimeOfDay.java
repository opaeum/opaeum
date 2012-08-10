package org.opaeum.runtime.bpm.businesscalendar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="252060@_UjTHMNb_EeCJ0dmaHEVVnw")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
public class TimeOfDay implements IPersistentObject, HibernateEntity, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Min(groups={},message="",payload={},value=0)
	@Max(groups={},message="",payload={},value=23)
	private Integer hours;
	@Min(groups={},message="",payload={},value=0)
	@Max(groups={},message="",payload={},value=59)
	private Integer minutes;
	static private Set<TimeOfDay> mockedAllInstances;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 2904073007558910507l;
	private String uid;
	@Index(columnNames="work_day_id",name="idx_time_of_day_work_day_id")
	private WorkDay workDay;

	/** Default constructor for TimeOfDay
	 */
	public TimeOfDay() {
		this.setHours( 0 );
		this.setMinutes( 0 );
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
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
		if ( getWorkDay()!=null ) {
			getWorkDay().z_internalRemoveFromEndTime(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<TimeOfDay> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setHours(Integer hours) {
		propertyChangeSupport.firePropertyChange("hours",getHours(),hours);
		this.z_internalAddToHours(hours);
	}
	
	public void setMinutes(Integer minutes) {
		propertyChangeSupport.firePropertyChange("minutes",getMinutes(),minutes);
		this.z_internalAddToMinutes(minutes);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setWorkDay(WorkDay workDay) {
		WorkDay oldValue = this.getWorkDay();
		propertyChangeSupport.firePropertyChange("workDay",getWorkDay(),workDay);
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
	
	public void z_internalAddToHours(Integer val) {
		this.hours=val;
	}
	
	public void z_internalAddToMinutes(Integer val) {
		this.minutes=val;
	}
	
	public void z_internalAddToWorkDay(WorkDay val) {
		this.workDay=val;
	}
	
	public void z_internalRemoveFromHours(Integer val) {
		if ( getHours()!=null && val!=null && val.equals(getHours()) ) {
			this.hours=null;
			this.hours=null;
		}
	}
	
	public void z_internalRemoveFromMinutes(Integer val) {
		if ( getMinutes()!=null && val!=null && val.equals(getMinutes()) ) {
			this.minutes=null;
			this.minutes=null;
		}
	}
	
	public void z_internalRemoveFromWorkDay(WorkDay val) {
		if ( getWorkDay()!=null && val!=null && val.equals(getWorkDay()) ) {
			this.workDay=null;
			this.workDay=null;
		}
	}

}