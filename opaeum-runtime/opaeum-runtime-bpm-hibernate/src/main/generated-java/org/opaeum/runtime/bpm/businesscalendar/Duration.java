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
import javax.persistence.Enumerated;
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
@Entity(name="Duration")
@DiscriminatorColumn(name="type_descriminator",discriminatorType=javax.persistence.DiscriminatorType.STRING)
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Table(schema="opaeum_bpm",name="duration")
@NumlMetaInfo(uuid="252060@_mkwNcASREeGb9qsDxKJdSA")
@AccessType("field")
public class Duration implements HibernateEntity, Serializable, IPersistentObject {
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
	@Column(name="quantity")
	private Double _quantity;
	@Enumerated(javax.persistence.EnumType.STRING)
	@Column(name="time_unit",nullable=true)
	private BusinessTimeUnit _timeUnit;
	static final private long serialVersionUID = 632;
	static private Set<Duration> mockedAllInstances;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date _fromDate;

	/** Default constructor for Duration
	 */
	public Duration() {
	}

	static public Set<? extends Duration> allInstances() {
		if ( mockedAllInstances==null ) {
			Session session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(Session.class);
			return new HashSet(session.createQuery("from Duration").list());
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("_timeUnit").length()>0 ) {
			setTimeUnit(BusinessTimeUnit.valueOf(xml.getAttribute("_timeUnit")));
		}
		if ( xml.getAttribute("_quantity").length()>0 ) {
			setQuantity(OpaeumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("_quantity")));
		}
		if ( xml.getAttribute("_fromDate").length()>0 ) {
			setFromDate(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("_fromDate")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Duration from, Duration to) {
		to.setTimeUnit(from.getTimeUnit());
		to.setQuantity(from.getQuantity());
		to.setFromDate(from.getFromDate());
	}
	
	public void copyState(Duration from, Duration to) {
		to.setTimeUnit(from.getTimeUnit());
		to.setQuantity(from.getQuantity());
		to.setFromDate(from.getFromDate());
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@NumlMetaInfo(uuid="252060@_f6z9oASUEeGb9qsDxKJdSA")
	public Date getFromDate() {
		Date result = this._fromDate;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Duration["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@NumlMetaInfo(uuid="252060@_twyWUASREeGb9qsDxKJdSA")
	public Double getQuantity() {
		Double result = this._quantity;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_sa4TUASREeGb9qsDxKJdSA")
	public BusinessTimeUnit getTimeUnit() {
		BusinessTimeUnit result = this._timeUnit;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public Duration makeCopy() {
		Duration result = new Duration();
		copyState((Duration)this,result);
		return result;
	}
	
	public Duration makeShallowCopy() {
		Duration result = new Duration();
		copyShallowState((Duration)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
	}
	
	static public void mockAllInstances(Set<Duration> newMocks) {
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
	
	public void setFromDate(Date _fromDate) {
		this.z_internalAddToFromDate(_fromDate);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setQuantity(Double _quantity) {
		this.z_internalAddToQuantity(_quantity);
	}
	
	public void setTimeUnit(BusinessTimeUnit _timeUnit) {
		this.z_internalAddToTimeUnit(_timeUnit);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Duration uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Duration ");
		sb.append("classUuid=\"252060@_mkwNcASREeGb9qsDxKJdSA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.businesscalendar.Duration\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getTimeUnit()!=null ) {
			sb.append("_timeUnit=\""+ getTimeUnit().name() + "\" ");
		}
		if ( getQuantity()!=null ) {
			sb.append("_quantity=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatReal(getQuantity())+"\" ");
		}
		if ( getFromDate()!=null ) {
			sb.append("_fromDate=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getFromDate())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Duration>");
		return sb.toString();
	}
	
	public void z_internalAddToFromDate(Date val) {
		this._fromDate=val;
	}
	
	public void z_internalAddToQuantity(Double val) {
		this._quantity=val;
	}
	
	public void z_internalAddToTimeUnit(BusinessTimeUnit val) {
		this._timeUnit=val;
	}
	
	public void z_internalRemoveFromFromDate(Date val) {
		if ( getFromDate()!=null && val!=null && val.equals(getFromDate()) ) {
			this._fromDate=null;
		}
	}
	
	public void z_internalRemoveFromQuantity(Double val) {
		if ( getQuantity()!=null && val!=null && val.equals(getQuantity()) ) {
			this._quantity=null;
		}
	}
	
	public void z_internalRemoveFromTimeUnit(BusinessTimeUnit val) {
		if ( getTimeUnit()!=null && val!=null && val.equals(getTimeUnit()) ) {
			this._timeUnit=null;
		}
	}

}