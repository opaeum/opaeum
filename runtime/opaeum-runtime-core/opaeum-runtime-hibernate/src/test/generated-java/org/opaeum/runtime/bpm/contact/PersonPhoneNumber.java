package org.opaeum.runtime.bpm.contact;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.bpm.organization.PersonNode;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.contact.IPersonPhoneNumber;
import org.opaeum.runtime.contact.PersonPhoneNumberType;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_3E_9kEtnEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_phone_number",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"person_id","type","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QueryPersonPhoneNumberWithTypeForPerson",query="from PersonPhoneNumber a where a.person = :person and a.type = :type"))
@Entity(name="PersonPhoneNumber")
public class PersonPhoneNumber implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IPersonPhoneNumber, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="person_phone_number",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends PersonPhoneNumber> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="person_id",name="idx_person_phone_number_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	protected PersonNode person;
	@Length(max=15,message="Phone number must consist of between  9 and 15 characters",min=8)
	@Digits(fraction=0,integer=15,message="")
	@Column(name="phone_number")
	@Basic
	protected String phoneNumber;
	static final private long serialVersionUID = 11888058762954742l;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="type",nullable=true)
	protected PersonPhoneNumberType type;
	private String uid;
	@Column(name="key_in_pho_num_on_per_nod")
	private String z_keyOfPhoneNumberOnPersonNode;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param type 
	 */
	public PersonPhoneNumber(PersonNode owningObject, PersonPhoneNumberType type) {
		setType(type);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for PersonPhoneNumber
	 */
	public PersonPhoneNumber() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		if ( getType()==null ) {
			throw new IllegalStateException("The qualifying property 'type' must be set before adding a value to 'person'");
		}
		getPerson().z_internalAddToPhoneNumber(this.getType(),(PersonPhoneNumber)this);
	}
	
	static public Set<? extends PersonPhoneNumber> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.contact.PersonPhoneNumber.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("phoneNumber").length()>0 ) {
			setPhoneNumber(OpaeumLibraryForBPMFormatter.getInstance().parsePhoneNumber(xml.getAttribute("phoneNumber")));
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
		to.setPhoneNumber(from.getPhoneNumber());
		to.setType(from.getType());
	}
	
	public void copyState(PersonPhoneNumber from, PersonPhoneNumber to) {
		to.setPhoneNumber(from.getPhoneNumber());
		to.setType(from.getType());
	}
	
	public void createComponents() {
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
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "PersonPhoneNumber["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4171052596734632680l,opposite="phoneNumber",uuid="252060@_Gjz08EtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_Gjz08EtoEeGd4cpyhpib9Q")
	public PersonNode getPerson() {
		PersonNode result = this.person;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2490948071546069620l,uuid="252060@_fjrTsHr7EeGX8L_MMRBizg")
	@NumlMetaInfo(uuid="252060@_fjrTsHr7EeGX8L_MMRBizg")
	public String getPhoneNumber() {
		String result = this.phoneNumber;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1377242588430375366l,opposite="personPhoneNumber",uuid="252060@_TR9ilEtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_TR9ilEtoEeGd4cpyhpib9Q")
	public PersonPhoneNumberType getType() {
		PersonPhoneNumberType result = this.type;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfPhoneNumberOnPersonNode() {
		return this.z_keyOfPhoneNumberOnPersonNode;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToPerson((PersonNode)owner);
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getPerson()!=null ) {
			getPerson().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set newMocks) {
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
			this.getPerson().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		if ( person == null ) {
			this.z_internalRemoveFromPerson(this.getPerson());
		} else {
			if ( getType()==null ) {
				throw new IllegalStateException("The qualifying property 'type' must be set before adding a value to 'person'");
			}
			this.z_internalAddToPerson(person);
		}
		if ( person!=null ) {
			person.z_internalAddToPhoneNumber(this.getType(),this);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setPhoneNumber(String phoneNumber) {
		if ( phoneNumber == null ) {
			this.z_internalRemoveFromPhoneNumber(getPhoneNumber());
		} else {
			this.z_internalAddToPhoneNumber(phoneNumber);
		}
	}
	
	public void setType(PersonPhoneNumberType type) {
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		if ( type == null ) {
			this.z_internalRemoveFromType(getType());
		} else {
			this.z_internalAddToType(type);
		}
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalAddToPhoneNumber(this.getType(),this);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
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
		if ( getPhoneNumber()!=null ) {
			sb.append("phoneNumber=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatPhoneNumber(getPhoneNumber())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</PersonPhoneNumber>");
		return sb.toString();
	}
	
	public void z_internalAddToPerson(PersonNode person) {
		if ( person.equals(this.person) ) {
			return;
		}
		this.person=person;
	}
	
	public void z_internalAddToPhoneNumber(String phoneNumber) {
		if ( phoneNumber.equals(this.phoneNumber) ) {
			return;
		}
		this.phoneNumber=phoneNumber;
	}
	
	public void z_internalAddToType(PersonPhoneNumberType type) {
		if ( type.equals(this.type) ) {
			return;
		}
		this.type=type;
	}
	
	public void z_internalRemoveFromPerson(PersonNode person) {
		if ( getPerson()!=null && person!=null && person.equals(getPerson()) ) {
			this.person=null;
			this.person=null;
		}
	}
	
	public void z_internalRemoveFromPhoneNumber(String phoneNumber) {
		if ( getPhoneNumber()!=null && phoneNumber!=null && phoneNumber.equals(getPhoneNumber()) ) {
			this.phoneNumber=null;
			this.phoneNumber=null;
		}
	}
	
	public void z_internalRemoveFromType(PersonPhoneNumberType type) {
		if ( getType()!=null && type!=null && type.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}

}