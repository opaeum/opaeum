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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_UjTHMNb_EeCJ0dmaHEVVnw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="time_of_day")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="TimeOfDay")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class TimeOfDay implements IPersistentObject, HibernateEntity, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Min(groups={},message="",payload={},value=0)
	@Max(groups={},message="",payload={},value=23)
	@Column(name="hours")
	private Integer hours;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Min(groups={},message="",payload={},value=0)
	@Max(groups={},message="",payload={},value=59)
	@Column(name="minutes")
	private Integer minutes;
	static private Set<TimeOfDay> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 2904073007558910507l;
	private String uid;

	/** Default constructor for TimeOfDay
	 */
	public TimeOfDay() {
		this.setMinutes( 0 );
		this.setHours( 0 );
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	static public Set<? extends TimeOfDay> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.businesscalendar.TimeOfDay.class));
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
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMinutes(Integer minutes) {
		propertyChangeSupport.firePropertyChange("minutes",getMinutes(),minutes);
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

}