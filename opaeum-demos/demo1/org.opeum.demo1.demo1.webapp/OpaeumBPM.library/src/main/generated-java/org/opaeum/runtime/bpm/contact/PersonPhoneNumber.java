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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.organization.PersonNode;
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
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_3E_9kEtnEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_phone_number")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="PersonPhoneNumber")
@DiscriminatorValue(	"person_phone_number")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class PersonPhoneNumber extends PhoneNumber implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IPersonPhoneNumber, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<PersonPhoneNumber> mockedAllInstances;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="person_id",name="idx_person_phone_number_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	private PersonNode person;
	static final private long serialVersionUID = 11888058762954742l;
	@Type(type="org.opaeum.runtime.bpm.contact.PersonPhoneNumberTypeResolver")
	@Column(name="type",nullable=true)
	private PersonPhoneNumberType type;
	@Column(name="key_in_pho_num_on_per_nod")
	private String z_keyOfPhoneNumberOnPersonNode;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param type 
	 */
	public PersonPhoneNumber(PersonNode owningObject, PersonPhoneNumberType type) {
		init(owningObject);
		addToOwningObject();
		setType(type);
	}
	
	/** Default constructor for PersonPhoneNumber
	 */
	public PersonPhoneNumber() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getPerson().z_internalAddToPhoneNumber(this.getType(),(PersonPhoneNumber)this);
	}
	
	static public Set<? extends PersonPhoneNumber> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.contact.PersonPhoneNumber.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("number").length()>0 ) {
			setNumber(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("number")));
		}
		if ( xml.getAttribute("type").length()>0 ) {
			setType(PersonPhoneNumberType.valueOf(xml.getAttribute("type")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(PersonPhoneNumber from, PersonPhoneNumber to) {
		to.setNumber(from.getNumber());
		to.setType(from.getType());
	}
	
	public void copyState(PersonPhoneNumber from, PersonPhoneNumber to) {
		to.setNumber(from.getNumber());
		to.setType(from.getType());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PersonPhoneNumber ) {
			return other==this || ((PersonPhoneNumber)other).getUid().equals(this.getUid());
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
		return "PersonPhoneNumber["+getId()+"]";
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getPerson();
	}
	
	@NumlMetaInfo(uuid="252060@_Gjz08EtoEeGd4cpyhpib9Q")
	public PersonNode getPerson() {
		PersonNode result = this.person;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_TR9ilEtoEeGd4cpyhpib9Q")
	public PersonPhoneNumberType getType() {
		PersonPhoneNumberType result = this.type;
		
		return result;
	}
	
	public String getZ_keyOfPhoneNumberOnPersonNode() {
		return this.z_keyOfPhoneNumberOnPersonNode;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToPerson((PersonNode)owner);
		createComponents();
	}
	
	public PersonPhoneNumber makeCopy() {
		PersonPhoneNumber result = new PersonPhoneNumber();
		copyState((PersonPhoneNumber)this,result);
		return result;
	}
	
	public PersonPhoneNumber makeShallowCopy() {
		PersonPhoneNumber result = new PersonPhoneNumber();
		copyShallowState((PersonPhoneNumber)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getPerson()!=null ) {
			getPerson().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<PersonPhoneNumber> newMocks) {
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
			this.getPerson().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		if ( person!=null ) {
			person.z_internalAddToPhoneNumber(this.getType(),this);
			this.z_internalAddToPerson(person);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setType(PersonPhoneNumberType type) {
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		this.z_internalAddToType(type);
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalAddToPhoneNumber(this.getType(),this);
		}
	}
	
	public void setZ_keyOfPhoneNumberOnPersonNode(String z_keyOfPhoneNumberOnPersonNode) {
		this.z_keyOfPhoneNumberOnPersonNode=z_keyOfPhoneNumberOnPersonNode;
	}
	
	public String toXmlReferenceString() {
		return "<PersonPhoneNumber uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PersonPhoneNumber ");
		sb.append("classUuid=\"252060@_3E_9kEtnEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.contact.PersonPhoneNumber\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getNumber()!=null ) {
			sb.append("number=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getNumber())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</PersonPhoneNumber>");
		return sb.toString();
	}
	
	public void z_internalAddToPerson(PersonNode val) {
		this.person=val;
	}
	
	public void z_internalAddToType(PersonPhoneNumberType val) {
		this.type=val;
	}
	
	public void z_internalRemoveFromPerson(PersonNode val) {
		if ( getPerson()!=null && val!=null && val.equals(getPerson()) ) {
			this.person=null;
			this.person=null;
		}
	}
	
	public void z_internalRemoveFromType(PersonPhoneNumberType val) {
		if ( getType()!=null && val!=null && val.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}

}