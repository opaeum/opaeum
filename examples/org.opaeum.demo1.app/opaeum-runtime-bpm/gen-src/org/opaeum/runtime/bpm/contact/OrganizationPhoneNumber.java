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
import javax.validation.constraints.Digits;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.Length;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_Ca9wQEtoEeGd4cpyhpib9Q")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_phone_number",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"organization_id","type","deleted_on"}))
@NamedQueries(value=
	@NamedQuery(name="QueryOrganizationPhoneNumberWithTypeForOrganization",query="from OrganizationPhoneNumber a where a.organization = :organization and a.type = :type"))
@Entity(name="OrganizationPhoneNumber")
public class OrganizationPhoneNumber implements IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Length(max=15,message="Phone number must consist of between  9 and 15 characters",min=8)
	@Digits(fraction=0,integer=15,message="")
	@Column(name="hpone_number")
	@Basic
	protected String hponeNumber;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="organization_phone_number",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends OrganizationPhoneNumber> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Index(columnNames="organization_id",name="idx_organization_phone_number_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	protected OrganizationNode organization;
	@Transient
	private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 6058915114543502092l;
	@Type(type="org.opaeum.runtime.bpm.contact.OrganizationPhoneNumberTypeResolver")
	@Column(name="type",nullable=true)
	protected OrganizationPhoneNumberType type;
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
	
	static public Set<? extends OrganizationPhoneNumber> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.contact.OrganizationPhoneNumber.class));
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7229033838421414396l,uuid="252060@_ls8YAHr7EeGX8L_MMRBizg")
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
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromPhoneNumber(this.getType(),this);
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
	
	public void z_internalAddToHponeNumber(String hponeNumber) {
		this.hponeNumber=hponeNumber;
	}
	
	public void z_internalAddToOrganization(OrganizationNode organization) {
		this.organization=organization;
	}
	
	public void z_internalAddToType(OrganizationPhoneNumberType type) {
		this.type=type;
	}
	
	public void z_internalRemoveFromHponeNumber(String hponeNumber) {
		if ( getHponeNumber()!=null && hponeNumber!=null && hponeNumber.equals(getHponeNumber()) ) {
			this.hponeNumber=null;
			this.hponeNumber=null;
		}
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode organization) {
		if ( getOrganization()!=null && organization!=null && organization.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}
	
	public void z_internalRemoveFromType(OrganizationPhoneNumberType type) {
		if ( getType()!=null && type!=null && type.equals(getType()) ) {
			this.type=null;
			this.type=null;
		}
	}

}