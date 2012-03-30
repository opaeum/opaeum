package org.opaeum.runtime.bpm.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.contact.IPersonEMailAddress;
import org.opaeum.runtime.contact.PersonEMailAddressType;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_LSLzIEtpEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_e_mail_address",uniqueConstraints=
	@UniqueConstraint(columnNames={"person_id","type","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QueryPersonEMailAddressWithTypeForPerson",query="from PersonEMailAddress a where a.person = :person and a.type = :type"))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="PersonEMailAddress")
@DiscriminatorValue(	"person_e_mail_address")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class PersonEMailAddress extends EMailAddress implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IPersonEMailAddress, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<PersonEMailAddress> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="person_id",name="idx_person_e_mail_address_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	private PersonNode person;
	static final private long serialVersionUID = 2460027813273694992l;
	@Type(type="org.opaeum.runtime.contact.PersonEMailAddressTypeResolver")
	@Column(name="type",nullable=true)
	private PersonEMailAddressType type;
	@Column(name="key_in_e_m_a_on_per_nod")
	private String z_keyOfEMailAddressOnPersonNode;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param type 
	 */
	public PersonEMailAddress(PersonNode owningObject, PersonEMailAddressType type) {
		setType(type);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PersonEMailAddress
	 */
	public PersonEMailAddress() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getPerson().z_internalAddToEMailAddress(this.getType(),(PersonEMailAddress)this);
	}
	
	static public Set<? extends PersonEMailAddress> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.contact.PersonEMailAddress.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("address").length()>0 ) {
			setAddress(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("address")));
		}
		if ( xml.getAttribute("type").length()>0 ) {
			setType(PersonEMailAddressType.valueOf(xml.getAttribute("type")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(PersonEMailAddress from, PersonEMailAddress to) {
		to.setAddress(from.getAddress());
		to.setType(from.getType());
	}
	
	public void copyState(PersonEMailAddress from, PersonEMailAddress to) {
		to.setAddress(from.getAddress());
		to.setType(from.getType());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PersonEMailAddress ) {
			return other==this || ((PersonEMailAddress)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public String getName() {
		return "PersonEMailAddress["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getPerson();
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=7889195384454107184l,opposite="eMailAddress",uuid="252060@_fNvioUtpEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_fNvioUtpEeGd4cpyhpib9Q")
	public PersonNode getPerson() {
		PersonNode result = this.person;
		
		return result;
	}
	
	@PropertyMetaInfo(isComposite=false,opaeumId=2816184780881689238l,opposite="personEMailAddress",uuid="252060@_NrUNtEtpEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_NrUNtEtpEeGd4cpyhpib9Q")
	public PersonEMailAddressType getType() {
		PersonEMailAddressType result = this.type;
		
		return result;
	}
	
	public String getZ_keyOfEMailAddressOnPersonNode() {
		return this.z_keyOfEMailAddressOnPersonNode;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToPerson((PersonNode)owner);
		createComponents();
	}
	
	public PersonEMailAddress makeCopy() {
		PersonEMailAddress result = new PersonEMailAddress();
		copyState((PersonEMailAddress)this,result);
		return result;
	}
	
	public PersonEMailAddress makeShallowCopy() {
		PersonEMailAddress result = new PersonEMailAddress();
		copyShallowState((PersonEMailAddress)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getPerson()!=null ) {
			getPerson().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<PersonEMailAddress> newMocks) {
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
		super.setDeletedOn(deletedOn);
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setPerson(PersonNode person) {
		if ( this.getPerson()!=null ) {
			this.getPerson().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		if ( person!=null ) {
			person.z_internalAddToEMailAddress(this.getType(),this);
			this.z_internalAddToPerson(person);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setType(PersonEMailAddressType type) {
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		this.z_internalAddToType(type);
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalAddToEMailAddress(this.getType(),this);
		}
	}
	
	public void setZ_keyOfEMailAddressOnPersonNode(String z_keyOfEMailAddressOnPersonNode) {
		this.z_keyOfEMailAddressOnPersonNode=z_keyOfEMailAddressOnPersonNode;
	}
	
	public String toXmlReferenceString() {
		return "<PersonEMailAddress uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PersonEMailAddress ");
		sb.append("classUuid=\"252060@_LSLzIEtpEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.contact.PersonEMailAddress\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAddress()!=null ) {
			sb.append("address=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getAddress())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</PersonEMailAddress>");
		return sb.toString();
	}
	
	public void z_internalAddToPerson(PersonNode val) {
		this.person=val;
	}
	
	public void z_internalAddToType(PersonEMailAddressType val) {
		this.type=val;
	}
	
	public void z_internalRemoveFromPerson(PersonNode val) {
		if ( getPerson()!=null && val!=null && val.equals(getPerson()) ) {
			this.person=null;
			this.person=null;
		}
	}
	
	public void z_internalRemoveFromType(PersonEMailAddressType val) {
		if ( getType()!=null && val!=null && val.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}

}