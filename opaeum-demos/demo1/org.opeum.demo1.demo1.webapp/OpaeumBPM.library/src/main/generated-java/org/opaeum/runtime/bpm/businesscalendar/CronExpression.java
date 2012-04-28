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

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="252060@_hqpgYASQEeGb9qsDxKJdSA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="cron_expression")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="CronExpression")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class CronExpression implements IPersistentObject, HibernateEntity, Serializable {
	@Column(name="day_of_month")
	private String dayOfMonth;
	@Column(name="day_of_week")
	private String dayOfWeek;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Column(name="hours")
	private String hours;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	@Column(name="minutes")
	private String minutes;
	static private Set<CronExpression> mockedAllInstances;
	@Column(name="month")
	private String month;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 5460935804071936083l;
	private String uid;

	/** Default constructor for CronExpression
	 */
	public CronExpression() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	static public Set<? extends CronExpression> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.businesscalendar.CronExpression.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("minutes").length()>0 ) {
			setMinutes(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("minutes")));
		}
		if ( xml.getAttribute("hours").length()>0 ) {
			setHours(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("hours")));
		}
		if ( xml.getAttribute("dayOfMonth").length()>0 ) {
			setDayOfMonth(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("dayOfMonth")));
		}
		if ( xml.getAttribute("month").length()>0 ) {
			setMonth(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("month")));
		}
		if ( xml.getAttribute("dayOfWeek").length()>0 ) {
			setDayOfWeek(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("dayOfWeek")));
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9055417578278384593l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_pCvIQASQEeGb9qsDxKJdSA")
	@NumlMetaInfo(uuid="252060@_pCvIQASQEeGb9qsDxKJdSA")
	public String getDayOfMonth() {
		String result = this.dayOfMonth;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=708173574190646295l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_sJLUwASQEeGb9qsDxKJdSA")
	@NumlMetaInfo(uuid="252060@_sJLUwASQEeGb9qsDxKJdSA")
	public String getDayOfWeek() {
		String result = this.dayOfWeek;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=220906512785518639l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_nh_HMASQEeGb9qsDxKJdSA")
	@NumlMetaInfo(uuid="252060@_nh_HMASQEeGb9qsDxKJdSA")
	public String getHours() {
		String result = this.hours;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5291683613794914355l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_m6UqQASQEeGb9qsDxKJdSA")
	@NumlMetaInfo(uuid="252060@_m6UqQASQEeGb9qsDxKJdSA")
	public String getMinutes() {
		String result = this.minutes;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7632703191246202423l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_qpRBMASQEeGb9qsDxKJdSA")
	@NumlMetaInfo(uuid="252060@_qpRBMASQEeGb9qsDxKJdSA")
	public String getMonth() {
		String result = this.month;
		
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
		setDeletedOn(new Date());
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
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setDayOfMonth(String dayOfMonth) {
		propertyChangeSupport.firePropertyChange("dayOfMonth",getDayOfMonth(),dayOfMonth);
		this.z_internalAddToDayOfMonth(dayOfMonth);
	}
	
	public void setDayOfWeek(String dayOfWeek) {
		propertyChangeSupport.firePropertyChange("dayOfWeek",getDayOfWeek(),dayOfWeek);
		this.z_internalAddToDayOfWeek(dayOfWeek);
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setHours(String hours) {
		propertyChangeSupport.firePropertyChange("hours",getHours(),hours);
		this.z_internalAddToHours(hours);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setMinutes(String minutes) {
		propertyChangeSupport.firePropertyChange("minutes",getMinutes(),minutes);
		this.z_internalAddToMinutes(minutes);
	}
	
	public void setMonth(String month) {
		propertyChangeSupport.firePropertyChange("month",getMonth(),month);
		this.z_internalAddToMonth(month);
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
			sb.append("minutes=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getMinutes())+"\" ");
		}
		if ( getHours()!=null ) {
			sb.append("hours=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getHours())+"\" ");
		}
		if ( getDayOfMonth()!=null ) {
			sb.append("dayOfMonth=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getDayOfMonth())+"\" ");
		}
		if ( getMonth()!=null ) {
			sb.append("month=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getMonth())+"\" ");
		}
		if ( getDayOfWeek()!=null ) {
			sb.append("dayOfWeek=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getDayOfWeek())+"\" ");
		}
		sb.append(">");
		sb.append("\n</CronExpression>");
		return sb.toString();
	}
	
	public void z_internalAddToDayOfMonth(String val) {
		this.dayOfMonth=val;
	}
	
	public void z_internalAddToDayOfWeek(String val) {
		this.dayOfWeek=val;
	}
	
	public void z_internalAddToHours(String val) {
		this.hours=val;
	}
	
	public void z_internalAddToMinutes(String val) {
		this.minutes=val;
	}
	
	public void z_internalAddToMonth(String val) {
		this.month=val;
	}
	
	public void z_internalRemoveFromDayOfMonth(String val) {
		if ( getDayOfMonth()!=null && val!=null && val.equals(getDayOfMonth()) ) {
			this.dayOfMonth=null;
			this.dayOfMonth=null;
		}
	}
	
	public void z_internalRemoveFromDayOfWeek(String val) {
		if ( getDayOfWeek()!=null && val!=null && val.equals(getDayOfWeek()) ) {
			this.dayOfWeek=null;
			this.dayOfWeek=null;
		}
	}
	
	public void z_internalRemoveFromHours(String val) {
		if ( getHours()!=null && val!=null && val.equals(getHours()) ) {
			this.hours=null;
			this.hours=null;
		}
	}
	
	public void z_internalRemoveFromMinutes(String val) {
		if ( getMinutes()!=null && val!=null && val.equals(getMinutes()) ) {
			this.minutes=null;
			this.minutes=null;
		}
	}
	
	public void z_internalRemoveFromMonth(String val) {
		if ( getMonth()!=null && val!=null && val.equals(getMonth()) ) {
			this.month=null;
			this.month=null;
		}
	}

}