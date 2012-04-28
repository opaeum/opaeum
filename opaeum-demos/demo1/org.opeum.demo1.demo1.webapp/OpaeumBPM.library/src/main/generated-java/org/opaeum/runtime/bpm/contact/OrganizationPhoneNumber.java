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
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
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

@AuditMe
@NumlMetaInfo(uuid="252060@_Ca9wQEtoEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_phone_number",uniqueConstraints=
	@UniqueConstraint(columnNames={"organization_id","type","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QueryOrganizationPhoneNumberWithTypeForOrganization",query="from OrganizationPhoneNumber a where a.organization = :organization and a.type = :type"))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="OrganizationPhoneNumber")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class OrganizationPhoneNumber implements IPersistentObject, IEventGenerator, IConstrained, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Length(groups={},max=15,message="Phone number must consist of between  9 and 15 characters",min=8,payload={})
	@Digits(fraction=0,groups={},integer=15,message="",payload={})
	@Column(name="hpone_number")
	private String hponeNumber;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<OrganizationPhoneNumber> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Index(columnNames="organization_id",name="idx_organization_phone_number_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	private OrganizationNode organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6058915114543502092l;
	@Type(type="org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberTypeResolver")
	@Column(name="type",nullable=true)
	private OrganizationPhoneNumberType type;
	private String uid;
	@Column(name="key_in_pho_num_on_org_nod")
	private String z_keyOfPhoneNumberOnOrganizationNode;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param type 
	 */
	public OrganizationPhoneNumber(OrganizationNode owningObject, OrganizationPhoneNumberType type) {
		setType(type);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for OrganizationPhoneNumber
	 */
	public OrganizationPhoneNumber() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getOrganization().z_internalAddToPhoneNumber(this.getType(),(OrganizationPhoneNumber)this);
	}
	
	static public Set<? extends OrganizationPhoneNumber> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("hponeNumber").length()>0 ) {
			setHponeNumber(OpaeumLibraryForBPMFormatter.getInstance().parsePhoneNumber(xml.getAttribute("hponeNumber")));
		}
		if ( xml.getAttribute("type").length()>0 ) {
			setType(OrganizationPhoneNumberType.valueOf(xml.getAttribute("type")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(OrganizationPhoneNumber from, OrganizationPhoneNumber to) {
		to.setHponeNumber(from.getHponeNumber());
		to.setType(from.getType());
	}
	
	public void copyState(OrganizationPhoneNumber from, OrganizationPhoneNumber to) {
		to.setHponeNumber(from.getHponeNumber());
		to.setType(from.getType());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OrganizationPhoneNumber ) {
			return other==this || ((OrganizationPhoneNumber)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Set<String> getFailedInvariants() {
		Set<String> failedInvariants = new HashSet<String>();
		if ( !isUniqueInOrganization() ) {
			failedInvariants.add("org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber.uniqueInOrganization");
		}
		return failedInvariants;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7229033838421414396l,strategyFactory=SimpleTypeRuntimeStrategyFactory.class,uuid="252060@_ls8YAHr7EeGX8L_MMRBizg")
	@NumlMetaInfo(uuid="252060@_ls8YAHr7EeGX8L_MMRBizg")
	public String getHponeNumber() {
		String result = this.hponeNumber;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "OrganizationPhoneNumber["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7964194700080601190l,opposite="phoneNumber",uuid="252060@_HGK7IUtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_HGK7IUtoEeGd4cpyhpib9Q")
	public OrganizationNode getOrganization() {
		OrganizationNode result = this.organization;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getOrganization();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3953173395695543848l,opposite="organizationPhoneNumber",uuid="252060@_1i74dEtoEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_1i74dEtoEeGd4cpyhpib9Q")
	public OrganizationPhoneNumberType getType() {
		OrganizationPhoneNumberType result = this.type;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfPhoneNumberOnOrganizationNode() {
		return this.z_keyOfPhoneNumberOnOrganizationNode;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToOrganization((OrganizationNode)owner);
		createComponents();
	}
	
	public boolean isUniqueInOrganization() {
		boolean result = forAll1();
		
		return result;
	}
	
	public OrganizationPhoneNumber makeCopy() {
		OrganizationPhoneNumber result = new OrganizationPhoneNumber();
		copyState((OrganizationPhoneNumber)this,result);
		return result;
	}
	
	public OrganizationPhoneNumber makeShallowCopy() {
		OrganizationPhoneNumber result = new OrganizationPhoneNumber();
		copyShallowState((OrganizationPhoneNumber)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<OrganizationPhoneNumber> newMocks) {
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
	
	public void setHponeNumber(String hponeNumber) {
		propertyChangeSupport.firePropertyChange("hponeNumber",getHponeNumber(),hponeNumber);
		this.z_internalAddToHponeNumber(hponeNumber);
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOrganization(OrganizationNode organization) {
		propertyChangeSupport.firePropertyChange("organization",getOrganization(),organization);
		if ( this.getOrganization()!=null ) {
			this.getOrganization().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		if ( organization!=null ) {
			organization.z_internalAddToPhoneNumber(this.getType(),this);
			this.z_internalAddToOrganization(organization);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setType(OrganizationPhoneNumberType type) {
		propertyChangeSupport.firePropertyChange("type",getType(),type);
		if ( getOrganization()!=null && getType()!=null ) {
			getOrganization().z_internalRemoveFromPhoneNumber(this.getType(),this);
		}
		this.z_internalAddToType(type);
		if ( getOrganization()!=null && getType()!=null ) {
			getOrganization().z_internalAddToPhoneNumber(this.getType(),this);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setZ_keyOfPhoneNumberOnOrganizationNode(String z_keyOfPhoneNumberOnOrganizationNode) {
		this.z_keyOfPhoneNumberOnOrganizationNode=z_keyOfPhoneNumberOnOrganizationNode;
	}
	
	public String toXmlReferenceString() {
		return "<OrganizationPhoneNumber uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<OrganizationPhoneNumber ");
		sb.append("classUuid=\"252060@_Ca9wQEtoEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getHponeNumber()!=null ) {
			sb.append("hponeNumber=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatPhoneNumber(getHponeNumber())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</OrganizationPhoneNumber>");
		return sb.toString();
	}
	
	public void z_internalAddToHponeNumber(String val) {
		this.hponeNumber=val;
	}
	
	public void z_internalAddToOrganization(OrganizationNode val) {
		this.organization=val;
	}
	
	public void z_internalAddToType(OrganizationPhoneNumberType val) {
		this.type=val;
	}
	
	public void z_internalRemoveFromHponeNumber(String val) {
		if ( getHponeNumber()!=null && val!=null && val.equals(getHponeNumber()) ) {
			this.hponeNumber=null;
			this.hponeNumber=null;
		}
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode val) {
		if ( getOrganization()!=null && val!=null && val.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}
	
	public void z_internalRemoveFromType(OrganizationPhoneNumberType val) {
		if ( getType()!=null && val!=null && val.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}
	
	/** Implements ->forAll( p : OrganizationPhoneNumber | (p.type = self.type) implies p = self )
	 */
	private boolean forAll1() {
		for ( OrganizationPhoneNumber p : this.getOrganization().getPhoneNumber() ) {
			if ( !((p.getType().equals( this.getType())) ? p.equals(this) : true) ) {
				return false;
			}
		}
		return true;
	}

}