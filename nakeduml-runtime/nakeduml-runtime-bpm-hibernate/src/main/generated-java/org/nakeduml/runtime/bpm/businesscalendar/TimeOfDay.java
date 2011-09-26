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
import org.nakeduml.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="TimeOfDay")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opium_bpm",name="time_of_day")
@NumlMetaInfo(uuid="252060@_UjTHMNb_EeCJ0dmaHEVVnw")
@AccessType("field")
public class TimeOfDay implements HibernateEntity, Serializable, IPersistentObject {
	private String uid;
		// Initialise to 1000 from 1970
	@Column(name="deleted_on")
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date deletedOn = Stdlib.FUTURE;
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	@Id
	private Long id;
	@Column(name="object_version")
	@Version
	private int objectVersion;
	@Column(name="minutes")
	@Min(message="",value=0,payload={},groups={})
	@Max(message="",value=59,payload={},groups={})
	private Integer minutes;
	@Column(name="hours")
	@DecimalMin(message="",value="",payload={},groups={})
	@DecimalMax(message="",value="",payload={},groups={})
	private Integer hours;
	static final private long serialVersionUID = 31;
	static private Set<TimeOfDay> mockedAllInstances;

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
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("hours").length()>0 ) {
			setHours(OpiumLibraryForBPMFormatter.getInstance().parseHourOfDay(xml.getAttribute("hours")));
		}
		if ( xml.getAttribute("minutes").length()>0 ) {
			setMinutes(OpiumLibraryForBPMFormatter.getInstance().parseMinuteOfHour(xml.getAttribute("minutes")));
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
	
	@NumlMetaInfo(uuid="252060@_WB_50Nb_EeCJ0dmaHEVVnw")
	public Integer getHours() {
		Integer result = this.hours;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_MUFl4NcGEeCOrPzFUqsJFw")
	public Integer getMinuteOfDay() {
		Integer result = (this.getHours() * 60) + this.getMinutes();
		
		return result;
	}
	
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
		return "<TimeOfDay uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<TimeOfDay ");
		sb.append("classUuid=\"252060@_UjTHMNb_EeCJ0dmaHEVVnw\" ");
		sb.append("className=\"org.nakeduml.runtime.bpm.businesscalendar.TimeOfDay\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getHours()!=null ) {
			sb.append("hours=\""+ OpiumLibraryForBPMFormatter.getInstance().formatHourOfDay(getHours())+"\" ");
		}
		if ( getMinutes()!=null ) {
			sb.append("minutes=\""+ OpiumLibraryForBPMFormatter.getInstance().formatMinuteOfHour(getMinutes())+"\" ");
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