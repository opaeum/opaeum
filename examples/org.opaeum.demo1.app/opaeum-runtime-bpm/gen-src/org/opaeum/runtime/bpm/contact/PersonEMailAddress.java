package org.opaeum.runtime.bpm.contact;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.Email;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
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
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_LSLzIEtpEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_e_mail_address",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"person_id","type","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QueryPersonEMailAddressWithTypeForPerson",query="from PersonEMailAddress a where a.person = :person and a.type = :type"))
@Entity(name="PersonEMailAddress")
public class PersonEMailAddress implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, IPersonEMailAddress, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Email(message="Invalid e-mail address format")
	@Column(name="email_address")
	@Basic
	protected String emailAddress;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="person_e_mail_address",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends PersonEMailAddress> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Index(columnNames="person_id",name="idx_person_e_mail_address_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	protected PersonNode person;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 2460027813273694992l;
	@Type(type="org.opaeum.runtime.contact.PersonEMailAddressTypeResolver")
	@Column(name="type",nullable=true)
	protected PersonEMailAddressType type;
	private String uid;
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

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getPerson().z_internalAddToEMailAddress(this.getType(),(PersonEMailAddress)this);
	}
	
	static public Set<? extends PersonEMailAddress> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.contact.PersonEMailAddress.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("emailAddress").length()>0 ) {
			setEmailAddress(OpaeumLibraryForBPMFormatter.getInstance().parseEMailAddress(xml.getAttribute("emailAddress")));
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
		to.setEmailAddress(from.getEmailAddress());
		to.setType(from.getType());
	}
	
	public void copyState(PersonEMailAddress from, PersonEMailAddress to) {
		to.setEmailAddress(from.getEmailAddress());
		to.setType(from.getType());
	}
	
	public void createComponents() {
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4200000522195976260l,uuid="252060@_XkOw4Hr7EeGX8L_MMRBizg")
	@NumlMetaInfo(uuid="252060@_XkOw4Hr7EeGX8L_MMRBizg")
	public String getEmailAddress() {
		String result = this.emailAddress;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "PersonEMailAddress["+getId()+"]";
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7889195384454107184l,opposite="eMailAddress",uuid="252060@_fNvioUtpEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_fNvioUtpEeGd4cpyhpib9Q")
	public PersonNode getPerson() {
		PersonNode result = this.person;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2816184780881689238l,opposite="personEMailAddress",uuid="252060@_NrUNtEtpEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_NrUNtEtpEeGd4cpyhpib9Q")
	public PersonEMailAddressType getType() {
		PersonEMailAddressType result = this.type;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfEMailAddressOnPersonNode() {
		return this.z_keyOfEMailAddressOnPersonNode;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getPerson()!=null ) {
			getPerson().z_internalRemoveFromEMailAddress(this.getType(),this);
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
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setEmailAddress(String emailAddress) {
		propertyChangeSupport.firePropertyChange("emailAddress",getEmailAddress(),emailAddress);
		this.z_internalAddToEmailAddress(emailAddress);
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
		propertyChangeSupport.firePropertyChange("person",getPerson(),person);
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
		propertyChangeSupport.firePropertyChange("type",getType(),type);
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		this.z_internalAddToType(type);
		if ( getPerson()!=null && getType()!=null ) {
			getPerson().z_internalAddToEMailAddress(this.getType(),this);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
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
		if ( getEmailAddress()!=null ) {
			sb.append("emailAddress=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatEMailAddress(getEmailAddress())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</PersonEMailAddress>");
		return sb.toString();
	}
	
	public void z_internalAddToEmailAddress(String emailAddress) {
		this.emailAddress=emailAddress;
	}
	
	public void z_internalAddToPerson(PersonNode person) {
		this.person=person;
	}
	
	public void z_internalAddToType(PersonEMailAddressType type) {
		this.type=type;
	}
	
	public void z_internalRemoveFromEmailAddress(String emailAddress) {
		if ( getEmailAddress()!=null && emailAddress!=null && emailAddress.equals(getEmailAddress()) ) {
			this.emailAddress=null;
			this.emailAddress=null;
		}
	}
	
	public void z_internalRemoveFromPerson(PersonNode person) {
		if ( getPerson()!=null && person!=null && person.equals(getPerson()) ) {
			this.person=null;
			this.person=null;
		}
	}
	
	public void z_internalRemoveFromType(PersonEMailAddressType type) {
		if ( getType()!=null && type!=null && type.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}

}