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
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.organization.OrganizationalNode;
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
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_Ca9wQEtoEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_phone_number",schema="opaeum_bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="OrganizationPhoneNumber")
@DiscriminatorValue(	"organization_phone_number")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class OrganizationPhoneNumber extends PhoneNumber implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	static private Set<OrganizationPhoneNumber> mockedAllInstances;
	@Index(columnNames="organization_id",name="idx_organization_phone_number_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	private OrganizationalNode organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 6058915114543502092l;
	@Enumerated(	javax.persistence.EnumType.STRING)
	@Column(name="type",nullable=true)
	private OrganizationPhoneNumberType type;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public OrganizationPhoneNumber(OrganizationalNode owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for OrganizationPhoneNumber
	 */
	public OrganizationPhoneNumber() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getOrganization().z_internalAddToPhoneNumber((OrganizationPhoneNumber)this);
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
		if ( xml.getAttribute("number").length()>0 ) {
			setNumber(OpaeumLibraryForBPMFormatter.getInstance().parseString(xml.getAttribute("number")));
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
		to.setNumber(from.getNumber());
		to.setType(from.getType());
	}
	
	public void copyState(OrganizationPhoneNumber from, OrganizationPhoneNumber to) {
		to.setNumber(from.getNumber());
		to.setType(from.getType());
	}
	
	public void createComponents() {
		super.createComponents();
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
	
	public String getName() {
		return "OrganizationPhoneNumber["+getId()+"]";
	}
	
	@NumlMetaInfo(uuid="252060@_HGK7IUtoEeGd4cpyhpib9Q")
	public OrganizationalNode getOrganization() {
		OrganizationalNode result = this.organization;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getOrganization();
	}
	
	@NumlMetaInfo(uuid="252060@_1i74dEtoEeGd4cpyhpib9Q")
	public OrganizationPhoneNumberType getType() {
		OrganizationPhoneNumberType result = this.type;
		
		return result;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		this.z_internalAddToOrganization((OrganizationalNode)owner);
		createComponents();
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
		super.markDeleted();
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromPhoneNumber(this);
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
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
		super.setDeletedOn(deletedOn);
	}
	
	public void setOrganization(OrganizationalNode organization) {
		if ( this.getOrganization()!=null ) {
			this.getOrganization().z_internalRemoveFromPhoneNumber(this);
		}
		if ( organization!=null ) {
			organization.z_internalAddToPhoneNumber(this);
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
		this.z_internalAddToType(type);
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
		if ( getNumber()!=null ) {
			sb.append("number=\""+ OpaeumLibraryForBPMFormatter.getInstance().formatString(getNumber())+"\" ");
		}
		if ( getType()!=null ) {
			sb.append("type=\""+ getType().name() + "\" ");
		}
		sb.append(">");
		sb.append("\n</OrganizationPhoneNumber>");
		return sb.toString();
	}
	
	public void z_internalAddToOrganization(OrganizationalNode val) {
		this.organization=val;
	}
	
	public void z_internalAddToType(OrganizationPhoneNumberType val) {
		this.type=val;
	}
	
	public void z_internalRemoveFromOrganization(OrganizationalNode val) {
		if ( getOrganization()!=null && val!=null && val.equals(getOrganization()) ) {
			this.organization=null;
		}
	}
	
	public void z_internalRemoveFromType(OrganizationPhoneNumberType val) {
		if ( getType()!=null && val!=null && val.equals(getType()) ) {
			this.type=null;
		}
	}

}