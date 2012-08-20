package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.strategy.DateTimeStrategyFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_Pybi0Et3EeGElKTCe2jfDw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="leave")
@Entity(name="Leave")
public class Leave implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
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
	static private Set<Leave> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="person_id",name="idx_leave_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	private PersonNode person;
	static final private long serialVersionUID = 5351150154132902312l;
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Leave(PersonNode owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Leave
	 */
	public Leave() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getPerson().z_internalAddToLeave((Leave)this);
	}
	
	static public Set<? extends Leave> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.Leave.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("fromDate").length()>0 ) {
			setFromDate(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("fromDate")));
		}
		if ( xml.getAttribute("toDate").length()>0 ) {
			setToDate(OpaeumLibraryForBPMFormatter.getInstance().parseDateTime(xml.getAttribute("toDate")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(Leave from, Leave to) {
		to.setFromDate(from.getFromDate());
		to.setToDate(from.getToDate());
	}
	
	public void copyState(Leave from, Leave to) {
		to.setFromDate(from.getFromDate());
		to.setToDate(from.getToDate());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Leave ) {
			return other==this || ((Leave)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3694564450390573092l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_RZHMwEt3EeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_RZHMwEt3EeGElKTCe2jfDw")
	public Date getFromDate() {
		Date result = this.fromDate;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Leave["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getPerson();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2100864140378992430l,opposite="leave",uuid="252060@_UvR3UUt3EeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_UvR3UUt3EeGElKTCe2jfDw")
	public PersonNode getPerson() {
		PersonNode result = this.person;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7751222380771627172l,strategyFactory=DateTimeStrategyFactory.class,uuid="252060@_TCe-UEt3EeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_TCe-UEt3EeGElKTCe2jfDw")
	public Date getToDate() {
		Date result = this.toDate;
		
		return result;
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
		this.z_internalAddToPerson((PersonNode)owner);
		createComponents();
	}
	
	public Leave makeCopy() {
		Leave result = new Leave();
		copyState((Leave)this,result);
		return result;
	}
	
	public Leave makeShallowCopy() {
		Leave result = new Leave();
		copyShallowState((Leave)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getPerson()!=null ) {
			getPerson().z_internalRemoveFromLeave(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Leave> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
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
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPerson(PersonNode person) {
		if ( this.getPerson()!=null ) {
			this.getPerson().z_internalRemoveFromLeave(this);
		}
		if ( person!=null ) {
			person.z_internalAddToLeave(this);
			this.z_internalAddToPerson(person);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setToDate(Date toDate) {
		this.z_internalAddToToDate(toDate);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Leave uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Leave ");
		sb.append("classUuid=\"252060@_Pybi0Et3EeGElKTCe2jfDw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.Leave\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getFromDate()!=null ) {
			sb.append("fromDate=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getFromDate())+"\" ");
		}
		if ( getToDate()!=null ) {
			sb.append("toDate=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatDateTime(getToDate())+"\" ");
		}
		sb.append(">");
		sb.append("\n</Leave>");
		return sb.toString();
	}
	
	public void z_internalAddToFromDate(Date val) {
		this.fromDate=val;
	}
	
	public void z_internalAddToPerson(PersonNode val) {
		this.person=val;
	}
	
	public void z_internalAddToToDate(Date val) {
		this.toDate=val;
	}
	
	public void z_internalRemoveFromFromDate(Date val) {
		if ( getFromDate()!=null && val!=null && val.equals(getFromDate()) ) {
			this.fromDate=null;
			this.fromDate=null;
		}
	}
	
	public void z_internalRemoveFromPerson(PersonNode val) {
		if ( getPerson()!=null && val!=null && val.equals(getPerson()) ) {
			this.person=null;
			this.person=null;
		}
	}
	
	public void z_internalRemoveFromToDate(Date val) {
		if ( getToDate()!=null && val!=null && val.equals(getToDate()) ) {
			this.toDate=null;
			this.toDate=null;
		}
	}

}