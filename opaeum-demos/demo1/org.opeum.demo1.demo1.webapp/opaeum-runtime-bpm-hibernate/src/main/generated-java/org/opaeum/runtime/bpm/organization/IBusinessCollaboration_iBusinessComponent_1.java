package org.opaeum.runtime.bpm.organization;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.hibernate.domain.InterfaceValue;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="i_business_collaboration_i_business_component_1",schema="opaeum_bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="IBusinessCollaboration_iBusinessComponent_1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class IBusinessCollaboration_iBusinessComponent_1 implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_collaboration"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_collaboration_type"),name="classIdentifier")})
	private InterfaceValue businessCollaboration;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_component"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_component_type"),name="classIdentifier")})
	private InterfaceValue businessComponent;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	private Long id;
	static private Set<IBusinessCollaboration_iBusinessComponent_1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	static final private long serialVersionUID = 290134081061645991l;
	private String uid;

	/** Constructor for IBusinessCollaboration_iBusinessComponent_1
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public IBusinessCollaboration_iBusinessComponent_1(IBusiness end1, IBusinessCollaboration end2) {
		this.z_internalAddToBusinessComponent(end1);
		this.z_internalAddToBusinessCollaboration(end2);
	}
	
	/** Constructor for IBusinessCollaboration_iBusinessComponent_1
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public IBusinessCollaboration_iBusinessComponent_1(IBusinessCollaboration end2, IBusiness end1) {
		this.z_internalAddToBusinessComponent(end1);
		this.z_internalAddToBusinessCollaboration(end2);
	}
	
	/** Default constructor for IBusinessCollaboration_iBusinessComponent_1
	 */
	public IBusinessCollaboration_iBusinessComponent_1() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends IBusinessCollaboration_iBusinessComponent_1> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessComponent_1.class));
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
		getBusinessComponent().z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(this);
		this.z_internalRemoveFromBusinessComponent(getBusinessComponent());
		getBusinessCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessComponent(this);
		this.z_internalRemoveFromBusinessCollaboration(getBusinessCollaboration());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof IBusinessCollaboration_iBusinessComponent_1 ) {
			return other==this || ((IBusinessCollaboration_iBusinessComponent_1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = (IBusinessCollaboration)this.businessCollaboration.getValue(persistence);
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusiness getBusinessComponent() {
		IBusiness result = (IBusiness)this.businessComponent.getValue(persistence);
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "IBusinessCollaboration_iBusinessComponent_1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessComponent();
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
		this.z_internalAddToBusinessComponent((IBusiness)owner);
	}
	
	public void markDeleted() {
		if ( getBusinessCollaboration()!=null ) {
			getBusinessCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessComponent(this);
		}
		if ( getBusinessComponent()!=null ) {
			getBusinessComponent().z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<IBusinessCollaboration_iBusinessComponent_1> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("951622303080239230")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessCollaboration((IBusinessCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4579568112049252488")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessComponent((IBusiness)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( this.getBusinessCollaboration()!=null ) {
			this.getBusinessCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessComponent(this);
		}
		if ( businessCollaboration!=null ) {
			businessCollaboration.z_internalAddToIBusinessCollaboration_iBusinessComponent_1_businessComponent(this);
			this.z_internalAddToBusinessCollaboration(businessCollaboration);
		}
	}
	
	public void setBusinessComponent(IBusiness businessComponent) {
		IBusiness oldValue = this.getBusinessComponent();
		if ( oldValue==null ) {
			if ( businessComponent!=null ) {
				IBusinessCollaboration_iBusinessComponent_1 oldOther = (IBusinessCollaboration_iBusinessComponent_1)businessComponent.getIBusinessCollaboration_iBusinessComponent_1_businessCollaboration();
				businessComponent.z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessComponent(businessComponent);
				}
				businessComponent.z_internalAddToIBusinessCollaboration_iBusinessComponent_1_businessCollaboration((IBusinessCollaboration_iBusinessComponent_1)this);
			}
			this.z_internalAddToBusinessComponent(businessComponent);
		} else {
			if ( !oldValue.equals(businessComponent) ) {
				oldValue.z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(this);
				z_internalRemoveFromBusinessComponent(oldValue);
				if ( businessComponent!=null ) {
					IBusinessCollaboration_iBusinessComponent_1 oldOther = (IBusinessCollaboration_iBusinessComponent_1)businessComponent.getIBusinessCollaboration_iBusinessComponent_1_businessCollaboration();
					businessComponent.z_internalRemoveFromIBusinessCollaboration_iBusinessComponent_1_businessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessComponent(businessComponent);
					}
					businessComponent.z_internalAddToIBusinessCollaboration_iBusinessComponent_1_businessCollaboration((IBusinessCollaboration_iBusinessComponent_1)this);
				}
				this.z_internalAddToBusinessComponent(businessComponent);
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
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<IBusinessCollaboration_iBusinessComponent_1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<IBusinessCollaboration_iBusinessComponent_1 ");
		sb.append("classUuid=\"252060@_Rj0BAFYkEeGJUqEGX7bKSg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessComponent_1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessCollaboration()==null ) {
			sb.append("\n<businessCollaboration/>");
		} else {
			sb.append("\n<businessCollaboration propertyId=\"951622303080239230\">");
			sb.append("\n" + getBusinessCollaboration().toXmlReferenceString());
			sb.append("\n</businessCollaboration>");
		}
		if ( getBusinessComponent()==null ) {
			sb.append("\n<businessComponent/>");
		} else {
			sb.append("\n<businessComponent propertyId=\"4579568112049252488\">");
			sb.append("\n" + getBusinessComponent().toXmlReferenceString());
			sb.append("\n</businessComponent>");
		}
		sb.append("\n</IBusinessCollaboration_iBusinessComponent_1>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration val) {
		this.businessCollaboration.setValue(val);
	}
	
	public void z_internalAddToBusinessComponent(IBusiness val) {
		this.businessComponent.setValue(val);
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration val) {
		if ( getBusinessCollaboration()!=null && val!=null && val.equals(getBusinessCollaboration()) ) {
			this.businessCollaboration.setValue(null);
		}
	}
	
	public void z_internalRemoveFromBusinessComponent(IBusiness val) {
		if ( getBusinessComponent()!=null && val!=null && val.equals(getBusinessComponent()) ) {
			this.businessComponent.setValue(null);
		}
	}

}