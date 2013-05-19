package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.InterfaceValueOwner;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_fullfills_actor_role",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_actor","business_actor_type","deleted_on"}))
@Entity(name="OrganizationFullfillsActorRole")
public class OrganizationFullfillsActorRole implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("businessActor",IBusinessActor.class);
	}
	
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_actor"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_actor_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue businessActor;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="organization_fullfills_actor_role",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends OrganizationFullfillsActorRole> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Index(columnNames="organization_id",name="idx_organization_fullfills_actor_role_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	protected OrganizationNode organization;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 2252066632228028224l;
	private String uid;

	/** Constructor for OrganizationFullfillsActorRole
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public OrganizationFullfillsActorRole(IBusinessActor end1, OrganizationNode end2) {
		this.z_internalAddToBusinessActor(end1);
		this.z_internalAddToOrganization(end2);
	}
	
	/** Constructor for OrganizationFullfillsActorRole
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public OrganizationFullfillsActorRole(OrganizationNode end2, IBusinessActor end1) {
		this.z_internalAddToBusinessActor(end1);
		this.z_internalAddToOrganization(end2);
	}
	
	/** Default constructor for OrganizationFullfillsActorRole
	 */
	public OrganizationFullfillsActorRole() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends OrganizationFullfillsActorRole> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.OrganizationFullfillsActorRole.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromOrganization(getOrganization());
		this.z_internalRemoveFromBusinessActor(getBusinessActor());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OrganizationFullfillsActorRole ) {
			return other==this || ((OrganizationFullfillsActorRole)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4920750905025140827l,opposite="organizationFullfillsActorRole_organization",uuid="252060@_WjvQ0UtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw@252060@_WjvQ0UtyEeGElKTCe2jfDw")
	public IBusinessActor getBusinessActor() {
		IBusinessActor result = null;
		if ( this.businessActor==null ) {
			this.businessActor=new UiidBasedInterfaceValue();
		}
		result=(IBusinessActor)this.businessActor.getValue(persistence);
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Class getFieldType(String fieldName) {
		Class result = INTERFACE_FIELDS.get(fieldName);
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "OrganizationFullfillsActorRole["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7599859348250025213l,opposite="organizationFullfillsActorRole_businessActor",uuid="252060@_WjvQ1EtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw@252060@_WjvQ1EtyEeGElKTCe2jfDw")
	public OrganizationNode getOrganization() {
		OrganizationNode result = this.organization;
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getOrganization();
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
		this.z_internalAddToOrganization((OrganizationNode)owner);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
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
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<OrganizationFullfillsActorRole uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<OrganizationFullfillsActorRole ");
		sb.append("classUuid=\"252060@_WjvQ0EtyEeGElKTCe2jfDw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.OrganizationFullfillsActorRole\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</OrganizationFullfillsActorRole>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor businessActor) {
		if ( businessActor.equals(getBusinessActor()) ) {
			return;
		}
		if ( this.businessActor==null ) {
			this.businessActor=new UiidBasedInterfaceValue();
		}
		this.businessActor.setValue(businessActor);
	}
	
	public void z_internalAddToOrganization(OrganizationNode organization) {
		if ( organization.equals(getOrganization()) ) {
			return;
		}
		this.organization=organization;
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor businessActor) {
		if ( getBusinessActor()!=null && businessActor!=null && businessActor.equals(getBusinessActor()) ) {
			this.businessActor.setValue(null);
		}
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode organization) {
		if ( getOrganization()!=null && organization!=null && organization.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}

}