package org.nakeduml.runtime.bpm.businesscalendar;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.runtime.bpm.util.OpiumLibraryForBPMFormatter;
import org.nakeduml.runtime.bpm.util.Stdlib;
import org.nakeduml.runtime.domain.HibernateEntity;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="TimeOfDay")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(name="time_of_day")
@NumlMetaInfo(qualifiedPersistentName="businesscalendar.time_of_day",uuid="9ddfdee2_11ec_44a6_8e51_fe542c94ead1")
@AccessType("field")
public class TimeOfDay implements HibernateEntity, Serializable, IPersistentObject {
	static final private long serialVersionUID = 446;
	@Column(name="minutes")
	@Min(message="",value=0,payload={},groups={})
	@Max(message="",value=59,payload={},groups={})
	private Integer minutes;
	@Column(name="hours")
	@DecimalMin(message="",value="",payload={},groups={})
	@DecimalMax(message="",value="",payload={},groups={})
	private Integer hours;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	static private Set<TimeOfDay> mockedAllInstances;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	private String uid;

	/** Default constructor for TimeOfDay
	 */
	public TimeOfDay() {
		this.setMinutes( 0 );
		this.setHours( 0 );
	}

	static public Set<? extends TimeOfDay> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.nakeduml.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from TimeOfDay").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, IPersistentObject> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("minutes")!=null ) {
			setMinutes(OpiumLibraryForBPMFormatter.getInstance().parseMinuteOfHour(xml.getAttribute("minutes")));
		}
		if ( xml.getAttribute("hours")!=null ) {
			setHours(OpiumLibraryForBPMFormatter.getInstance().parseHourOfDay(xml.getAttribute("hours")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(TimeOfDay from, TimeOfDay to) {
		to.setMinutes(from.getMinutes());
		to.setHours(from.getHours());
	}
	
	public void copyState(TimeOfDay from, TimeOfDay to) {
		to.setMinutes(from.getMinutes());
		to.setHours(from.getHours());
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="time_of_day.hours",uuid="c8961f89_0558_4442_a5b0_061dd2db8bfb")
	public Integer getHours() {
		return hours;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="time_of_day.minute_of_day",uuid="506f8f6e_2d1c_484e_9df0_fdfec1c9c859")
	public Integer getMinuteOfDay() {
		Integer minuteOfDay = (this.getHours() * 60) + this.getMinutes();
		return minuteOfDay;
	}
	
	@NumlMetaInfo(qualifiedPersistentName="time_of_day.minutes",uuid="fb3f6ee1_0eaa_49a0_8005_5fa70aec7fa7")
	public Integer getMinutes() {
		return minutes;
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
	}
	
	static public void mockAllInstances(Set<TimeOfDay> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, IPersistentObject> map) {
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
		this.z_internalAddToHours(hours);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMinutes(Integer minutes) {
		this.z_internalAddToMinutes(minutes);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<timeOfDay uid=\""+getUid() + "\">";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<timeOfDay");
		sb.append(" className=\"org.nakeduml.runtime.bpm.businesscalendar.TimeOfDay\" ") ;
		sb.append("uid=\"" + this.getUid() + "\"") ;
		if ( getMinutes()!=null ) {
			sb.append("minutes=\""+ OpiumLibraryForBPMFormatter.getInstance().formatMinuteOfHour(getMinutes())+"\" ");
		}
		if ( getHours()!=null ) {
			sb.append("hours=\""+ OpiumLibraryForBPMFormatter.getInstance().formatHourOfDay(getHours())+"\" ");
		}
		sb.append(">\n");
		sb.append("</timeOfDay>");
		return sb.toString();
	}
	
	public void z_internalAddToHours(Integer val) {
		this.hours=val;
	}
	
	public void z_internalAddToMinutes(Integer val) {
		this.minutes=val;
	}
	
	public void z_internalRemoveFromHours(Integer val) {
		if ( getHours()!=null && val!=null && val.equals(getHours()) ) {
			this.hours=null;
		}
	}
	
	public void z_internalRemoveFromMinutes(Integer val) {
		if ( getMinutes()!=null && val!=null && val.equals(getMinutes()) ) {
			this.minutes=null;
		}
	}

}