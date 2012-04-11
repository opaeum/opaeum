package org.opaeum.runtime.bpm.contact;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
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
import org.opaeum.runtime.domain.IConstrained;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory;
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
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class PersonEMailAddress implements IPersistentObject, IEventGenerator, IConstrained, HibernateEntity, CompositionNode, IPersonEMailAddress, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Email(groups={},message="Invalid e-mail address format",payload={})
	@Column(name="email_address")
	private String emailAddress;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<PersonEMailAddress> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="person_id",name="idx_person_e_mail_address_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="person_id",nullable=true)
	private PersonNode person;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 2460027813273694992l;
	@Type(type="org.opaeum.runtime.contact.PersonEMailAddressTypeResolver")
	@Column(name="type",nullable=true)
	private PersonEMailAddressType type;
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4200000522195976260l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_XkOw4Hr7EeGX8L_MMRBizg")
	@NumlMetaInfo(uuid="252060@_XkOw4Hr7EeGX8L_MMRBizg")
	public String getEmailAddress() {
		String result = this.emailAddress;
		
		return result;
	}
	
	public Set<String> getFailedInvariants() {
		Set<String> failedInvariants = new HashSet<String>();
		if ( !isUniqueInPerson() ) {
			failedInvariants.add("org.opaeum.runtime.bpm.contact.PersonEMailAddress.person");
		}
		return failedInvariants;
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
	
	public boolean isUniqueInPerson() {
		boolean result = forAll1();
		
		return result;
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
	
	public void z_internalAddToEmailAddress(String val) {
		this.emailAddress=val;
	}
	
	public void z_internalAddToPerson(PersonNode val) {
		this.person=val;
	}
	
	public void z_internalAddToType(PersonEMailAddressType val) {
		this.type=val;
	}
	
	public void z_internalRemoveFromEmailAddress(String val) {
		if ( getEmailAddress()!=null && val!=null && val.equals(getEmailAddress()) ) {
			this.emailAddress=null;
			this.emailAddress=null;
		}
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
	
	/** Implements ->forAll( p : PersonEMailAddress | (p.type = self.type) implies p = self )
	 */
	private boolean forAll1() {
		for ( PersonEMailAddress p : this.getPerson().getEMailAddress() ) {
			if ( !((p.getType().equals( this.getType())) ? p.equals(this) : true) ) {
				return false;
			}
		}
		return true;
	}

}