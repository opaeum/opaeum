package org.opaeum.runtime.bpm.organization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="252060@_WjvQ0EtyEeGElKTCe2jfDw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_fullfills_actor_role",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_actor","business_actor_type","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="OrganizationFullfillsActorRole")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class OrganizationFullfillsActorRole implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_actor"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_actor_type"),name="classIdentifier")})
	private InterfaceValue businessActor = new InterfaceValue();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<OrganizationFullfillsActorRole> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Index(columnNames="organization_id",name="idx_organization_fullfills_actor_role_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="organization_id",nullable=true)
	private OrganizationNode organization;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
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

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
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
		getOrganization().z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(this);
		this.z_internalRemoveFromOrganization(getOrganization());
		getBusinessActor().z_internalRemoveFromOrganizationFullfillsActorRole_organization(this);
		this.z_internalRemoveFromBusinessActor(getBusinessActor());
		markDeleted();
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
		IBusinessActor result = (IBusinessActor)this.businessActor.getValue(persistence);
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
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
		return getBusinessActor();
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
		this.z_internalAddToBusinessActor((IBusinessActor)owner);
	}
	
	public void markDeleted() {
		if ( getBusinessActor()!=null ) {
			getBusinessActor().z_internalRemoveFromOrganizationFullfillsActorRole_organization(this);
		}
		if ( getOrganization()!=null ) {
			getOrganization().z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<OrganizationFullfillsActorRole> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4920750905025140827")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessActor((IBusinessActor)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("organization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7599859348250025213")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setOrganization((OrganizationNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setBusinessActor(IBusinessActor businessActor) {
		IBusinessActor oldValue = this.getBusinessActor();
		propertyChangeSupport.firePropertyChange("businessActor",getBusinessActor(),businessActor);
		if ( oldValue==null ) {
			if ( businessActor!=null ) {
				OrganizationFullfillsActorRole oldOther = (OrganizationFullfillsActorRole)businessActor.getOrganizationFullfillsActorRole_organization();
				businessActor.z_internalRemoveFromOrganizationFullfillsActorRole_organization(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessActor(businessActor);
				}
				businessActor.z_internalAddToOrganizationFullfillsActorRole_organization((OrganizationFullfillsActorRole)this);
			}
			this.z_internalAddToBusinessActor(businessActor);
		} else {
			if ( !oldValue.equals(businessActor) ) {
				oldValue.z_internalRemoveFromOrganizationFullfillsActorRole_organization(this);
				z_internalRemoveFromBusinessActor(oldValue);
				if ( businessActor!=null ) {
					OrganizationFullfillsActorRole oldOther = (OrganizationFullfillsActorRole)businessActor.getOrganizationFullfillsActorRole_organization();
					businessActor.z_internalRemoveFromOrganizationFullfillsActorRole_organization(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessActor(businessActor);
					}
					businessActor.z_internalAddToOrganizationFullfillsActorRole_organization((OrganizationFullfillsActorRole)this);
				}
				this.z_internalAddToBusinessActor(businessActor);
			}
		}
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
	
	public void setOrganization(OrganizationNode organization) {
		propertyChangeSupport.firePropertyChange("organization",getOrganization(),organization);
		if ( this.getOrganization()!=null ) {
			this.getOrganization().z_internalRemoveFromOrganizationFullfillsActorRole_businessActor(this);
		}
		if ( organization!=null ) {
			organization.z_internalAddToOrganizationFullfillsActorRole_businessActor(this);
			this.z_internalAddToOrganization(organization);
		}
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
		if ( getBusinessActor()==null ) {
			sb.append("\n<businessActor/>");
		} else {
			sb.append("\n<businessActor propertyId=\"4920750905025140827\">");
			sb.append("\n" + getBusinessActor().toXmlReferenceString());
			sb.append("\n</businessActor>");
		}
		if ( getOrganization()==null ) {
			sb.append("\n<organization/>");
		} else {
			sb.append("\n<organization propertyId=\"7599859348250025213\">");
			sb.append("\n" + getOrganization().toXmlReferenceString());
			sb.append("\n</organization>");
		}
		sb.append("\n</OrganizationFullfillsActorRole>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor val) {
		this.businessActor.setValue(val);
	}
	
	public void z_internalAddToOrganization(OrganizationNode val) {
		this.organization=val;
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor val) {
		if ( getBusinessActor()!=null && val!=null && val.equals(getBusinessActor()) ) {
			this.businessActor.setValue(null);
		}
	}
	
	public void z_internalRemoveFromOrganization(OrganizationNode val) {
		if ( getOrganization()!=null && val!=null && val.equals(getOrganization()) ) {
			this.organization=null;
			this.organization=null;
		}
	}

}