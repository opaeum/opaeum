package org.opaeum.runtime.bpm.businesscalendar;

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

import org.hibernate.Session;
import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Filter(name="noDeletedObjects")
@Entity(name="CronExpression")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",name="cron_expression")
@NumlMetaInfo(uuid="252060@_hqpgYASQEeGb9qsDxKJdSA")
@AccessType("field")
public class CronExpression implements HibernateEntity, Serializable, IPersistentObject {
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
	@Column(name="hours")
	private String _hours;
	@Column(name="day_of_month")
	private String _dayOfMonth;
	@Column(name="day_of_week")
	private String _dayOfWeek;
	@Column(name="month")
	private String _month;
	static final private long serialVersionUID = 633;
	static private Set<CronExpression> mockedAllInstances;
	@Column(name="minutes")
	private String _minutes;

	/** Default constructor for CronExpression
	 */
	public CronExpression() {
	}

	static public Set<? extends CronExpression> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from CronExpression").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_minutes").length()>0 ) {
			setMinutes(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("_minutes")));
		}
		if ( xml.getAttribute("_hours").length()>0 ) {
			setHours(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("_hours")));
		}
		if ( xml.getAttribute("_dayOfMonth").length()>0 ) {
			setDayOfMonth(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("_dayOfMonth")));
		}
		if ( xml.getAttribute("_month").length()>0 ) {
			setMonth(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("_month")));
		}
		if ( xml.getAttribute("_dayOfWeek").length()>0 ) {
			setDayOfWeek(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("_dayOfWeek")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(CronExpression from, CronExpression to) {
		to.setMinutes(from.getMinutes());
		to.setHours(from.getHours());
		to.setDayOfMonth(from.getDayOfMonth());
		to.setMonth(from.getMonth());
		to.setDayOfWeek(from.getDayOfWeek());
	}
	
	public void copyState(CronExpression from, CronExpression to) {
		to.setMinutes(from.getMinutes());
		to.setHours(from.getHours());
		to.setDayOfMonth(from.getDayOfMonth());
		to.setMonth(from.getMonth());
		to.setDayOfWeek(from.getDayOfWeek());
	}
	
	@NumlMetaInfo(uuid="252060@_pCvIQASQEeGb9qsDxKJdSA")
	public String getDayOfMonth() {
		String result = this._dayOfMonth;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_sJLUwASQEeGb9qsDxKJdSA")
	public String getDayOfWeek() {
		String result = this._dayOfWeek;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(uuid="252060@_nh_HMASQEeGb9qsDxKJdSA")
	public String getHours() {
		String result = this._hours;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@NumlMetaInfo(uuid="252060@_m6UqQASQEeGb9qsDxKJdSA")
	public String getMinutes() {
		String result = this._minutes;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_qpRBMASQEeGb9qsDxKJdSA")
	public String getMonth() {
		String result = this._month;
		
		return result;
	}
	
	public String getName() {
		return "CronExpression["+getId()+"]";
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
	
	public CronExpression makeCopy() {
		CronExpression result = new CronExpression();
		copyState((CronExpression)this,result);
		return result;
	}
	
	public CronExpression makeShallowCopy() {
		CronExpression result = new CronExpression();
		copyShallowState((CronExpression)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
	}
	
	static public void mockAllInstances(Set<CronExpression> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void setDayOfMonth(String _dayOfMonth) {
		this.z_internalAddToDayOfMonth(_dayOfMonth);
	}
	
	public void setDayOfWeek(String _dayOfWeek) {
		this.z_internalAddToDayOfWeek(_dayOfWeek);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setHours(String _hours) {
		this.z_internalAddToHours(_hours);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMinutes(String _minutes) {
		this.z_internalAddToMinutes(_minutes);
	}
	
	public void setMonth(String _month) {
		this.z_internalAddToMonth(_month);
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<CronExpression uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<CronExpression ");
		sb.append("classUuid=\"252060@_hqpgYASQEeGb9qsDxKJdSA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.CronExpression\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getMinutes()!=null ) {
			sb.append("_minutes=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getMinutes())+"\" ");
		}
		if ( getHours()!=null ) {
			sb.append("_hours=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getHours())+"\" ");
		}
		if ( getDayOfMonth()!=null ) {
			sb.append("_dayOfMonth=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getDayOfMonth())+"\" ");
		}
		if ( getMonth()!=null ) {
			sb.append("_month=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getMonth())+"\" ");
		}
		if ( getDayOfWeek()!=null ) {
			sb.append("_dayOfWeek=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getDayOfWeek())+"\" ");
		}
		sb.append(">");
		sb.append("\n</CronExpression>");
		return sb.toString();
	}
	
	public void z_internalAddToDayOfMonth(String val) {
		this._dayOfMonth=val;
	}
	
	public void z_internalAddToDayOfWeek(String val) {
		this._dayOfWeek=val;
	}
	
	public void z_internalAddToHours(String val) {
		this._hours=val;
	}
	
	public void z_internalAddToMinutes(String val) {
		this._minutes=val;
	}
	
	public void z_internalAddToMonth(String val) {
		this._month=val;
	}
	
	public void z_internalRemoveFromDayOfMonth(String val) {
		if ( getDayOfMonth()!=null && val!=null && val.equals(getDayOfMonth()) ) {
			this._dayOfMonth=null;
		}
	}
	
	public void z_internalRemoveFromDayOfWeek(String val) {
		if ( getDayOfWeek()!=null && val!=null && val.equals(getDayOfWeek()) ) {
			this._dayOfWeek=null;
		}
	}
	
	public void z_internalRemoveFromHours(String val) {
		if ( getHours()!=null && val!=null && val.equals(getHours()) ) {
			this._hours=null;
		}
	}
	
	public void z_internalRemoveFromMinutes(String val) {
		if ( getMinutes()!=null && val!=null && val.equals(getMinutes()) ) {
			this._minutes=null;
		}
	}
	
	public void z_internalRemoveFromMonth(String val) {
		if ( getMonth()!=null && val!=null && val.equals(getMonth()) ) {
			this._month=null;
		}
	}

}