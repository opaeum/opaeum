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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedCascadingInterfaceValue;
import org.opaeum.hibernate.domain.UiidBasedInterfaceValue;
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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_collaboration__business",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business","business_type","deleted_on"}))
@Entity(name="BusinessCollaboration_Business")
public class BusinessCollaboration_Business implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_type"),name="classIdentifier")})
	protected UiidBasedCascadingInterfaceValue business;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_collaboration"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_collaboration_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue businessCollaboration;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_collaboration__business",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends BusinessCollaboration_Business> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 290134081061645991l;
	private String uid;

	/** Constructor for BusinessCollaboration_Business
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public BusinessCollaboration_Business(IBusiness end2, IBusinessCollaboration end1) {
		this.z_internalAddToBusinessCollaboration(end1);
		this.z_internalAddToBusiness(end2);
	}
	
	/** Constructor for BusinessCollaboration_Business
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public BusinessCollaboration_Business(IBusinessCollaboration end1, IBusiness end2) {
		this.z_internalAddToBusinessCollaboration(end1);
		this.z_internalAddToBusiness(end2);
	}
	
	/** Default constructor for BusinessCollaboration_Business
	 */
	public BusinessCollaboration_Business() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends BusinessCollaboration_Business> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.BusinessCollaboration_Business.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("business") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5453873030391455984")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						IBusiness curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setBusiness(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clear() {
		getBusiness().z_internalRemoveFromBusinessCollaboration_Business_businessCollaboration(this);
		this.z_internalRemoveFromBusiness(getBusiness());
		getBusinessCollaboration().z_internalRemoveFromBusinessCollaboration_Business_business(this);
		this.z_internalRemoveFromBusinessCollaboration(getBusinessCollaboration());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessCollaboration_Business ) {
			return other==this || ((BusinessCollaboration_Business)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5453873030391455984l,opposite="businessCollaboration_Business_businessCollaboration",uuid="252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg@252060@_Rj0oEFYkEeGJUqEGX7bKSg")
	public IBusiness getBusiness() {
		IBusiness result = null;
		if ( this.business==null ) {
			this.business=new UiidBasedCascadingInterfaceValue();
		}
		result=(IBusiness)this.business.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1825927221422442726l,opposite="businessCollaboration_Business_business",uuid="252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_Rj0BAFYkEeGJUqEGX7bKSg@252060@_Rj0oE1YkEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		if ( this.businessCollaboration==null ) {
			this.businessCollaboration=new UiidBasedInterfaceValue();
		}
		result=(IBusinessCollaboration)this.businessCollaboration.getValue(persistence);
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "BusinessCollaboration_Business["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusiness();
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
		this.z_internalAddToBusiness((IBusiness)owner);
		createComponents();
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessCollaboration()!=null ) {
			getBusinessCollaboration().z_internalRemoveFromBusinessCollaboration_Business_business(this);
		}
		if ( getBusiness()!=null ) {
			getBusiness().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1825927221422442726")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessCollaboration((IBusinessCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("business") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5453873030391455984")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((IBusiness)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
	
	public void setBusiness(IBusiness business) {
		IBusiness oldValue = this.getBusiness();
		propertyChangeSupport.firePropertyChange("business",getBusiness(),business);
		if ( oldValue==null ) {
			if ( business!=null ) {
				BusinessCollaboration_Business oldOther = (BusinessCollaboration_Business)business.getBusinessCollaboration_Business_businessCollaboration();
				business.z_internalRemoveFromBusinessCollaboration_Business_businessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusiness(business);
				}
				business.z_internalAddToBusinessCollaboration_Business_businessCollaboration((BusinessCollaboration_Business)this);
			}
			this.z_internalAddToBusiness(business);
		} else {
			if ( !oldValue.equals(business) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration_Business_businessCollaboration(this);
				z_internalRemoveFromBusiness(oldValue);
				if ( business!=null ) {
					BusinessCollaboration_Business oldOther = (BusinessCollaboration_Business)business.getBusinessCollaboration_Business_businessCollaboration();
					business.z_internalRemoveFromBusinessCollaboration_Business_businessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusiness(business);
					}
					business.z_internalAddToBusinessCollaboration_Business_businessCollaboration((BusinessCollaboration_Business)this);
				}
				this.z_internalAddToBusiness(business);
			}
		}
	}
	
	public void setBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		propertyChangeSupport.firePropertyChange("businessCollaboration",getBusinessCollaboration(),businessCollaboration);
		if ( this.getBusinessCollaboration()!=null ) {
			this.getBusinessCollaboration().z_internalRemoveFromBusinessCollaboration_Business_business(this);
		}
		if ( businessCollaboration!=null ) {
			businessCollaboration.z_internalAddToBusinessCollaboration_Business_business(this);
			this.z_internalAddToBusinessCollaboration(businessCollaboration);
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
		return "<BusinessCollaboration_Business uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessCollaboration_Business ");
		sb.append("classUuid=\"252060@_Rj0BAFYkEeGJUqEGX7bKSg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.BusinessCollaboration_Business\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessCollaboration()==null ) {
			sb.append("\n<businessCollaboration/>");
		} else {
			sb.append("\n<businessCollaboration propertyId=\"1825927221422442726\">");
			sb.append("\n" + getBusinessCollaboration().toXmlReferenceString());
			sb.append("\n</businessCollaboration>");
		}
		if ( getBusiness()==null ) {
			sb.append("\n<business/>");
		} else {
			sb.append("\n<business propertyId=\"5453873030391455984\">");
			sb.append("\n" + getBusiness().toXmlString());
			sb.append("\n</business>");
		}
		sb.append("\n</BusinessCollaboration_Business>");
		return sb.toString();
	}
	
	public void z_internalAddToBusiness(IBusiness business) {
		if ( this.business==null ) {
			this.business=new UiidBasedCascadingInterfaceValue();
		}
		this.business.setValue(business);
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( this.businessCollaboration==null ) {
			this.businessCollaboration=new UiidBasedInterfaceValue();
		}
		this.businessCollaboration.setValue(businessCollaboration);
	}
	
	public void z_internalRemoveFromBusiness(IBusiness business) {
		if ( getBusiness()!=null && business!=null && business.equals(getBusiness()) ) {
			this.business.setValue(null);
		}
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( getBusinessCollaboration()!=null && businessCollaboration!=null && businessCollaboration.equals(getBusinessCollaboration()) ) {
			this.businessCollaboration.setValue(null);
		}
	}

}