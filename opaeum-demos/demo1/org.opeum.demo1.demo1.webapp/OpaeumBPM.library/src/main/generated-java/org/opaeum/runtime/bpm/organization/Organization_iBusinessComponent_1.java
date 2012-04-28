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
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@AuditMe
@NumlMetaInfo(uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_i_business_component_1",uniqueConstraints=
	@UniqueConstraint(columnNames={"represented_organization_id","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="Organization_iBusinessComponent_1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class Organization_iBusinessComponent_1 implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_component"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_component_type"),name="classIdentifier")})
	private InterfaceValue businessComponent = new InterfaceValue();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Organization_iBusinessComponent_1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="represented_organization_id",name="idx_organization_i_business_component_1_represented_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="represented_organization_id",nullable=true)
	private OrganizationNode representedOrganization;
	static final private long serialVersionUID = 537656835091857075l;
	private String uid;

	/** Constructor for Organization_iBusinessComponent_1
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public Organization_iBusinessComponent_1(IBusinessComponent end1, OrganizationNode end2) {
		this.z_internalAddToBusinessComponent(end1);
		this.z_internalAddToRepresentedOrganization(end2);
	}
	
	/** Constructor for Organization_iBusinessComponent_1
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public Organization_iBusinessComponent_1(OrganizationNode end2, IBusinessComponent end1) {
		this.z_internalAddToBusinessComponent(end1);
		this.z_internalAddToRepresentedOrganization(end2);
	}
	
	/** Default constructor for Organization_iBusinessComponent_1
	 */
	public Organization_iBusinessComponent_1() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Organization_iBusinessComponent_1> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.Organization_iBusinessComponent_1.class));
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
		getBusinessComponent().z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(this);
		this.z_internalRemoveFromBusinessComponent(getBusinessComponent());
		getRepresentedOrganization().z_internalRemoveFromOrganization_iBusinessComponent_1_businessComponent(this);
		this.z_internalRemoveFromRepresentedOrganization(getRepresentedOrganization());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Organization_iBusinessComponent_1 ) {
			return other==this || ((Organization_iBusinessComponent_1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=736417380271461952l,opposite="organization_iBusinessComponent_1_representedOrganization",uuid="252060@_uVek8IoVEeCLqpffVZYAlw")
	@NumlMetaInfo(uuid="252060@_vf4noFYuEeGj5_I7bIwNoA252060@_vf4noVYuEeGj5_I7bIwNoA")
	public IBusinessComponent getBusinessComponent() {
		IBusinessComponent result = (IBusinessComponent)this.businessComponent.getValue(persistence);
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "Organization_iBusinessComponent_1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessComponent();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5468800036735903732l,opposite="organization_iBusinessComponent_1_businessComponent",uuid="252060@_pZdQEEtmEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_vf4noFYuEeGj5_I7bIwNoA252060@_vf2LYFYuEeGj5_I7bIwNoA")
	public OrganizationNode getRepresentedOrganization() {
		OrganizationNode result = this.representedOrganization;
		
		return result;
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
		this.z_internalAddToBusinessComponent((IBusinessComponent)owner);
	}
	
	public void markDeleted() {
		if ( getBusinessComponent()!=null ) {
			getBusinessComponent().z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(this);
		}
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromOrganization_iBusinessComponent_1_businessComponent(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Organization_iBusinessComponent_1> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("736417380271461952")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessComponent((IBusinessComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5468800036735903732")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setRepresentedOrganization((OrganizationNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
	
	public void setBusinessComponent(IBusinessComponent businessComponent) {
		IBusinessComponent oldValue = this.getBusinessComponent();
		propertyChangeSupport.firePropertyChange("businessComponent",getBusinessComponent(),businessComponent);
		if ( oldValue==null ) {
			if ( businessComponent!=null ) {
				Organization_iBusinessComponent_1 oldOther = (Organization_iBusinessComponent_1)businessComponent.getOrganization_iBusinessComponent_1_representedOrganization();
				businessComponent.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessComponent(businessComponent);
				}
				businessComponent.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization((Organization_iBusinessComponent_1)this);
			}
			this.z_internalAddToBusinessComponent(businessComponent);
		} else {
			if ( !oldValue.equals(businessComponent) ) {
				oldValue.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(this);
				z_internalRemoveFromBusinessComponent(oldValue);
				if ( businessComponent!=null ) {
					Organization_iBusinessComponent_1 oldOther = (Organization_iBusinessComponent_1)businessComponent.getOrganization_iBusinessComponent_1_representedOrganization();
					businessComponent.z_internalRemoveFromOrganization_iBusinessComponent_1_representedOrganization(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessComponent(businessComponent);
					}
					businessComponent.z_internalAddToOrganization_iBusinessComponent_1_representedOrganization((Organization_iBusinessComponent_1)this);
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
	
	public void setRepresentedOrganization(OrganizationNode representedOrganization) {
		propertyChangeSupport.firePropertyChange("representedOrganization",getRepresentedOrganization(),representedOrganization);
		if ( this.getRepresentedOrganization()!=null ) {
			this.getRepresentedOrganization().z_internalRemoveFromOrganization_iBusinessComponent_1_businessComponent(this);
		}
		if ( representedOrganization!=null ) {
			representedOrganization.z_internalAddToOrganization_iBusinessComponent_1_businessComponent(this);
			this.z_internalAddToRepresentedOrganization(representedOrganization);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Organization_iBusinessComponent_1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Organization_iBusinessComponent_1 ");
		sb.append("classUuid=\"252060@_vf4noFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.Organization_iBusinessComponent_1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessComponent()==null ) {
			sb.append("\n<businessComponent/>");
		} else {
			sb.append("\n<businessComponent propertyId=\"736417380271461952\">");
			sb.append("\n" + getBusinessComponent().toXmlReferenceString());
			sb.append("\n</businessComponent>");
		}
		if ( getRepresentedOrganization()==null ) {
			sb.append("\n<representedOrganization/>");
		} else {
			sb.append("\n<representedOrganization propertyId=\"5468800036735903732\">");
			sb.append("\n" + getRepresentedOrganization().toXmlReferenceString());
			sb.append("\n</representedOrganization>");
		}
		sb.append("\n</Organization_iBusinessComponent_1>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessComponent(IBusinessComponent val) {
		this.businessComponent.setValue(val);
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode val) {
		this.representedOrganization=val;
	}
	
	public void z_internalRemoveFromBusinessComponent(IBusinessComponent val) {
		if ( getBusinessComponent()!=null && val!=null && val.equals(getBusinessComponent()) ) {
			this.businessComponent.setValue(null);
		}
	}
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode val) {
		if ( getRepresentedOrganization()!=null && val!=null && val.equals(getRepresentedOrganization()) ) {
			this.representedOrganization=null;
			this.representedOrganization=null;
		}
	}

}