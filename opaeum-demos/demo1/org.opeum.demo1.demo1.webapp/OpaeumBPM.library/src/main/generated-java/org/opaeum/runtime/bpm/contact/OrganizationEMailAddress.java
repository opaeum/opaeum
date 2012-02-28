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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.organization.OrganizationNode;
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

@NumlMetaInfo(uuid="252060@_GfviYEtqEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_e_mail_address",uniqueConstraints=
	@UniqueConstraint(columnNames={"type","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="OrganizationEMailAddress")
@DiscriminatorValue(	"organization_e_mail_address")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class OrganizationEMailAddress extends EMailAddress implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<OrganizationEMailAddress> mockedAllInstances;
	@Index(columnNames="organization_id",name="idx_organization_e_mail_address_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	private OrganizationNode organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private AbstractPersistence persistence;
	static final private long serialVersionUID = 1041028322921096628l;
	@Type(type="org.opaeum.runtime.bpm.contact.OrganizationEMailAddressTypeResolver")
	@Column(name="type",nullable=true)
	private OrganizationEMailAddressType type;
	@Column(name="key_in_e_m_a_on_org_nod")
	private String z_keyOfEMailAddressOnOrganizationNode;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param type 
	 */
	public OrganizationEMailAddress(OrganizationNode owningObject, OrganizationEMailAddressType type) {
		init(owningObject);
		addToOwningObject();
		setType(type);
	}
	
	/** Default constructor for OrganizationEMailAddress
	 */
	public OrganizationEMailAddress() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getOrganization().z_internalAddToEMailAddress(this.getType(),(OrganizationEMailAddress)this);
	}
	
	static public Set<? extends OrganizationEMailAddress> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.contact.OrganizationEMailAddress.class));
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
			setType(OrganizationEMailAddressType.valueOf(xml.getAttribute("type")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void copyShallowState(OrganizationEMailAddress from, OrganizationEMailAddress to) {
		to.setAddress(from.getAddress());
		to.setType(from.getType());
	}
	
	public void copyState(OrganizationEMailAddress from, OrganizationEMailAddress to) {
		to.setAddress(from.getAddress());
		to.setType(from.getType());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OrganizationEMailAddress ) {
			return other==this || ((OrganizationEMailAddress)other).getUid().equals(this.getUid());
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
		return "OrganizationEMailAddress["+getId()+"]";
	}
	
	@NumlMetaInfo(uuid="252060@_JGNOUUtqEeGd4cpyhpib9Q")
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
	
	@NumlMetaInfo(uuid="252060@_Ju-ehEtqEeGd4cpyhpib9Q")
	public OrganizationEMailAddressType getType() {
		OrganizationEMailAddressType result = this.type;
		
		return result;
	}
	
	public String getZ_keyOfEMailAddressOnOrganizationNode() {
		return this.z_keyOfEMailAddressOnOrganizationNode;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToOrganization((OrganizationNode)owner);
		createComponents();
	}
	
	public OrganizationEMailAddress makeCopy() {
		OrganizationEMailAddress result = new OrganizationEMailAddress();
		copyState((OrganizationEMailAddress)this,result);
		return result;
	}
	
	public OrganizationEMailAddress makeShallowCopy() {
		OrganizationEMailAddress result = new OrganizationEMailAddress();
		copyShallowState((OrganizationEMailAddress)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<OrganizationEMailAddress> newMocks) {
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
	
	public void setOrganization(OrganizationNode organization) {
		if ( this.getOrganization()!=null ) {
			this.getOrganization().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		if ( organization!=null ) {
			organization.z_internalAddToEMailAddress(this.getType(),this);
			this.z_internalAddToOrganization(organization);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setType(OrganizationEMailAddressType type) {
		if ( getOrganization()!=null && getType()!=null ) {
			getOrganization().z_internalRemoveFromEMailAddress(this.getType(),this);
		}
		this.z_internalAddToType(type);
		if ( getOrganization()!=null && getType()!=null ) {
			getOrganization().z_internalAddToEMailAddress(this.getType(),this);
		}
	}
	
	public void setZ_keyOfEMailAddressOnOrganizationNode(String z_keyOfEMailAddressOnOrganizationNode) {
		this.z_keyOfEMailAddressOnOrganizationNode=z_keyOfEMailAddressOnOrganizationNode;
	}
	
	public String toXmlReferenceString() {
		return "<OrganizationEMailAddress uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<OrganizationEMailAddress ");
		sb.append("classUuid=\"252060@_GfviYEtqEeGd4cpyhpib9Q\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.contact.OrganizationEMailAddress\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getAddress()!=null ) {
			sb.append("address=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getAddress())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</OrganizationEMailAddress>");
		return sb.toString();
	}
	
	public void z_internalAddToOrganization(OrganizationNode val) {
		this.organization=val;
	}
	
	public void z_internalAddToType(OrganizationEMailAddressType val) {
		this.type=val;
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode val) {
		if ( getOrganization()!=null && val!=null && val.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}
	
	public void z_internalRemoveFromType(OrganizationEMailAddressType val) {
		if ( getType()!=null && val!=null && val.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}

}