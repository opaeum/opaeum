package org.opaeum.runtime.bpm.organization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_vf4noFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="organization_as_business_component",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_component","business_component_type","deleted_on"}))
@Entity(name="OrganizationAsBusinessComponent")
public class OrganizationAsBusinessComponent implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_component"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_component_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue businessComponent;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="organization_as_business_component",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends OrganizationAsBusinessComponent> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="represented_organization_id",name="idx_organization_as_business_component_represented_organization_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="represented_organization_id",nullable=true)
	protected OrganizationNode representedOrganization;
	static final private long serialVersionUID = 537656835091857075l;
	private String uid;

	/** Constructor for OrganizationAsBusinessComponent
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public OrganizationAsBusinessComponent(IBusinessComponent end2, OrganizationNode end1) {
		this.z_internalAddToRepresentedOrganization(end1);
		this.z_internalAddToBusinessComponent(end2);
	}
	
	/** Constructor for OrganizationAsBusinessComponent
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public OrganizationAsBusinessComponent(OrganizationNode end1, IBusinessComponent end2) {
		this.z_internalAddToRepresentedOrganization(end1);
		this.z_internalAddToBusinessComponent(end2);
	}
	
	/** Default constructor for OrganizationAsBusinessComponent
	 */
	public OrganizationAsBusinessComponent() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends OrganizationAsBusinessComponent> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.OrganizationAsBusinessComponent.class));
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
		getBusinessComponent().z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(this);
		this.z_internalRemoveFromBusinessComponent(getBusinessComponent());
		getRepresentedOrganization().z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(this);
		this.z_internalRemoveFromRepresentedOrganization(getRepresentedOrganization());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof OrganizationAsBusinessComponent ) {
			return other==this || ((OrganizationAsBusinessComponent)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=404524283969866022l,opposite="organizationAsBusinessComponent_representedOrganization",uuid="252060@_vf2LYFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_vf4noFYuEeGj5_I7bIwNoA@252060@_vf2LYFYuEeGj5_I7bIwNoA")
	public IBusinessComponent getBusinessComponent() {
		IBusinessComponent result = null;
		if ( this.businessComponent==null ) {
			this.businessComponent=new UiidBasedInterfaceValue();
		}
		result=(IBusinessComponent)this.businessComponent.getValue(persistence);
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "OrganizationAsBusinessComponent["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessComponent();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5136906940434307802l,opposite="organizationAsBusinessComponent_businessComponent",uuid="252060@_vf4noVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_vf4noFYuEeGj5_I7bIwNoA@252060@_vf4noVYuEeGj5_I7bIwNoA")
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
		createComponents();
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getRepresentedOrganization()!=null ) {
			getRepresentedOrganization().z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(this);
		}
		if ( getBusinessComponent()!=null ) {
			getBusinessComponent().z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("representedOrganization") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5136906940434307802")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setRepresentedOrganization((OrganizationNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessComponent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("404524283969866022")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessComponent((IBusinessComponent)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
				OrganizationAsBusinessComponent oldOther = (OrganizationAsBusinessComponent)businessComponent.getOrganizationAsBusinessComponent_representedOrganization();
				businessComponent.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessComponent(businessComponent);
				}
				businessComponent.z_internalAddToOrganizationAsBusinessComponent_representedOrganization((OrganizationAsBusinessComponent)this);
			}
			this.z_internalAddToBusinessComponent(businessComponent);
		} else {
			if ( !oldValue.equals(businessComponent) ) {
				oldValue.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(this);
				z_internalRemoveFromBusinessComponent(oldValue);
				if ( businessComponent!=null ) {
					OrganizationAsBusinessComponent oldOther = (OrganizationAsBusinessComponent)businessComponent.getOrganizationAsBusinessComponent_representedOrganization();
					businessComponent.z_internalRemoveFromOrganizationAsBusinessComponent_representedOrganization(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessComponent(businessComponent);
					}
					businessComponent.z_internalAddToOrganizationAsBusinessComponent_representedOrganization((OrganizationAsBusinessComponent)this);
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
			this.getRepresentedOrganization().z_internalRemoveFromOrganizationAsBusinessComponent_businessComponent(this);
		}
		if ( representedOrganization!=null ) {
			representedOrganization.z_internalAddToOrganizationAsBusinessComponent_businessComponent(this);
			this.z_internalAddToRepresentedOrganization(representedOrganization);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<OrganizationAsBusinessComponent uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<OrganizationAsBusinessComponent ");
		sb.append("classUuid=\"252060@_vf4noFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.OrganizationAsBusinessComponent\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getRepresentedOrganization()==null ) {
			sb.append("\n<representedOrganization/>");
		} else {
			sb.append("\n<representedOrganization propertyId=\"5136906940434307802\">");
			sb.append("\n" + getRepresentedOrganization().toXmlReferenceString());
			sb.append("\n</representedOrganization>");
		}
		if ( getBusinessComponent()==null ) {
			sb.append("\n<businessComponent/>");
		} else {
			sb.append("\n<businessComponent propertyId=\"404524283969866022\">");
			sb.append("\n" + getBusinessComponent().toXmlReferenceString());
			sb.append("\n</businessComponent>");
		}
		sb.append("\n</OrganizationAsBusinessComponent>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessComponent(IBusinessComponent businessComponent) {
		if ( this.businessComponent==null ) {
			this.businessComponent=new UiidBasedInterfaceValue();
		}
		this.businessComponent.setValue(businessComponent);
	}
	
	public void z_internalAddToRepresentedOrganization(OrganizationNode representedOrganization) {
		this.representedOrganization=representedOrganization;
	}
	
	public void z_internalRemoveFromBusinessComponent(IBusinessComponent businessComponent) {
		if ( getBusinessComponent()!=null && businessComponent!=null && businessComponent.equals(getBusinessComponent()) ) {
			this.businessComponent.setValue(null);
		}
	}
	
	public void z_internalRemoveFromRepresentedOrganization(OrganizationNode representedOrganization) {
		if ( getRepresentedOrganization()!=null && representedOrganization!=null && representedOrganization.equals(getRepresentedOrganization()) ) {
			this.representedOrganization=null;
			this.representedOrganization=null;
		}
	}

}