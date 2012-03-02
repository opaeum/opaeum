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
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.Property;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_mkwNcASREeGb9qsDxKJdSA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="duration")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Duration")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Duration implements IPersistentObject, HibernateEntity, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Duration> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Column(name="quantity")
	private Double quantity;
	static final private long serialVersionUID = 6916267881564579221l;
	@Type(type="org.opaeum.runtime.domain.BusinessTimeUnitResolver")
	@Column(name="time_unit",nullable=true)
	private BusinessTimeUnit timeUnit;
	private String uid;

	/** Default constructor for Duration
	 */
	public Duration() {
	}

	static public Set<? extends Duration> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.businesscalendar.Duration.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("timeUnit").length()>0 ) {
			setTimeUnit(BusinessTimeUnit.valueOf(xml.getAttribute("timeUnit")));
		}
		if ( xml.getAttribute("quantity").length()>0 ) {
			setQuantity(OpaeumLibraryForBPMFormatter.getInstance().parseReal(xml.getAttribute("quantity")));
		}
		if ( xml.getAttribute("fromDate").length()>0 ) {
			setFromDate(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("fromDate")));
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
	
	@Property(isComposite=false)
	@NumlMetaInfo(uuid="252060@_f6z9oASUEeGb9qsDxKJdSA")
	public Date getFromDate() {
		Date result = this.fromDate;
		
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
	
	@Property(isComposite=false)
	@NumlMetaInfo(uuid="252060@_twyWUASREeGb9qsDxKJdSA")
	public Double getQuantity() {
		Double result = this.quantity;
		
		return result;
	}
	
	@Property(isComposite=false)
	@NumlMetaInfo(uuid="252060@_sa4TUASREeGb9qsDxKJdSA")
	public BusinessTimeUnit getTimeUnit() {
		BusinessTimeUnit result = this.timeUnit;
		
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
		setDeletedOn(new Date());
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
	
	public void setFromDate(Date fromDate) {
		this.z_internalAddToFromDate(fromDate);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setQuantity(Double quantity) {
		this.z_internalAddToQuantity(quantity);
	}
	
	public void setTimeUnit(BusinessTimeUnit timeUnit) {
		this.z_internalAddToTimeUnit(timeUnit);
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
			sb.append("timeUnit=\""+ getTimeUnit().name() + "\" ");
		}
		if ( getQuantity()!=null ) {
			sb.append("quantity=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatReal(getQuantity())+"\" ");
		}
		if ( getFromDate()!=null ) {
			sb.append("fromDate=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getFromDate())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Duration>");
		return sb.toString();
	}
	
	public void z_internalAddToFromDate(Date val) {
		this.fromDate=val;
	}
	
	public void z_internalAddToQuantity(Double val) {
		this.quantity=val;
	}
	
	public void z_internalAddToTimeUnit(BusinessTimeUnit val) {
		this.timeUnit=val;
	}
	
	public void z_internalRemoveFromFromDate(Date val) {
		if ( getFromDate()!=null && val!=null && val.equals(getFromDate()) ) {
			this.fromDate=null;
			this.fromDate=null;
		}
	}
	
	public void z_internalRemoveFromQuantity(Double val) {
		if ( getQuantity()!=null && val!=null && val.equals(getQuantity()) ) {
			this.quantity=null;
			this.quantity=null;
		}
	}
	
	public void z_internalRemoveFromTimeUnit(BusinessTimeUnit val) {
		if ( getTimeUnit()!=null && val!=null && val.equals(getTimeUnit()) ) {
			this.timeUnit=null;
			this.timeUnit=null;
		}
	}

}