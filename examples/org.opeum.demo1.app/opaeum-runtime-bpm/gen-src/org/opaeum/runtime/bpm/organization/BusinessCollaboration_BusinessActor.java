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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_collaboration__business_actor",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_actor","business_actor_type","deleted_on"}))
@Entity(name="BusinessCollaboration_BusinessActor")
public class BusinessCollaboration_BusinessActor implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_actor"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_actor_type"),name="classIdentifier")})
	protected UiidBasedCascadingInterfaceValue businessActor;
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
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_collaboration__business_actor",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends BusinessCollaboration_BusinessActor> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8115205646489818195l;
	private String uid;

	/** Constructor for BusinessCollaboration_BusinessActor
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public BusinessCollaboration_BusinessActor(IBusinessActor end2, IBusinessCollaboration end1) {
		this.z_internalAddToBusinessCollaboration(end1);
		this.z_internalAddToBusinessActor(end2);
	}
	
	/** Constructor for BusinessCollaboration_BusinessActor
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public BusinessCollaboration_BusinessActor(IBusinessCollaboration end1, IBusinessActor end2) {
		this.z_internalAddToBusinessCollaboration(end1);
		this.z_internalAddToBusinessActor(end2);
	}
	
	/** Default constructor for BusinessCollaboration_BusinessActor
	 */
	public BusinessCollaboration_BusinessActor() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends BusinessCollaboration_BusinessActor> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.BusinessCollaboration_BusinessActor.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7010262207974877226")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						IBusinessActor curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.setBusinessActor(curVal);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clear() {
		getBusinessActor().z_internalRemoveFromBusinessCollaboration_BusinessActor_businessCollaboration(this);
		this.z_internalRemoveFromBusinessActor(getBusinessActor());
		getBusinessCollaboration().z_internalRemoveFromBusinessCollaboration_BusinessActor_businessActor(this);
		this.z_internalRemoveFromBusinessCollaboration(getBusinessCollaboration());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessCollaboration_BusinessActor ) {
			return other==this || ((BusinessCollaboration_BusinessActor)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7010262207974877226l,opposite="businessCollaboration_BusinessActor_businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA@252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public IBusinessActor getBusinessActor() {
		IBusinessActor result = null;
		if ( this.businessActor==null ) {
			this.businessActor=new UiidBasedCascadingInterfaceValue();
		}
		result=(IBusinessActor)this.businessActor.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4331153764749992840l,opposite="businessCollaboration_BusinessActor_businessActor",uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA@252060@_pP5QRFYuEeGj5_I7bIwNoA")
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
		return "BusinessCollaboration_BusinessActor["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
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
		createComponents();
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessCollaboration()!=null ) {
			getBusinessCollaboration().z_internalRemoveFromBusinessCollaboration_BusinessActor_businessActor(this);
		}
		if ( getBusinessActor()!=null ) {
			getBusinessActor().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4331153764749992840")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessCollaboration((IBusinessCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7010262207974877226")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((IBusinessActor)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
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
				BusinessCollaboration_BusinessActor oldOther = (BusinessCollaboration_BusinessActor)businessActor.getBusinessCollaboration_BusinessActor_businessCollaboration();
				businessActor.z_internalRemoveFromBusinessCollaboration_BusinessActor_businessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessActor(businessActor);
				}
				businessActor.z_internalAddToBusinessCollaboration_BusinessActor_businessCollaboration((BusinessCollaboration_BusinessActor)this);
			}
			this.z_internalAddToBusinessActor(businessActor);
		} else {
			if ( !oldValue.equals(businessActor) ) {
				oldValue.z_internalRemoveFromBusinessCollaboration_BusinessActor_businessCollaboration(this);
				z_internalRemoveFromBusinessActor(oldValue);
				if ( businessActor!=null ) {
					BusinessCollaboration_BusinessActor oldOther = (BusinessCollaboration_BusinessActor)businessActor.getBusinessCollaboration_BusinessActor_businessCollaboration();
					businessActor.z_internalRemoveFromBusinessCollaboration_BusinessActor_businessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessActor(businessActor);
					}
					businessActor.z_internalAddToBusinessCollaboration_BusinessActor_businessCollaboration((BusinessCollaboration_BusinessActor)this);
				}
				this.z_internalAddToBusinessActor(businessActor);
			}
		}
	}
	
	public void setBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		propertyChangeSupport.firePropertyChange("businessCollaboration",getBusinessCollaboration(),businessCollaboration);
		if ( this.getBusinessCollaboration()!=null ) {
			this.getBusinessCollaboration().z_internalRemoveFromBusinessCollaboration_BusinessActor_businessActor(this);
		}
		if ( businessCollaboration!=null ) {
			businessCollaboration.z_internalAddToBusinessCollaboration_BusinessActor_businessActor(this);
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
		return "<BusinessCollaboration_BusinessActor uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessCollaboration_BusinessActor ");
		sb.append("classUuid=\"252060@_pP5QQFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.BusinessCollaboration_BusinessActor\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessCollaboration()==null ) {
			sb.append("\n<businessCollaboration/>");
		} else {
			sb.append("\n<businessCollaboration propertyId=\"4331153764749992840\">");
			sb.append("\n" + getBusinessCollaboration().toXmlReferenceString());
			sb.append("\n</businessCollaboration>");
		}
		if ( getBusinessActor()==null ) {
			sb.append("\n<businessActor/>");
		} else {
			sb.append("\n<businessActor propertyId=\"7010262207974877226\">");
			sb.append("\n" + getBusinessActor().toXmlString());
			sb.append("\n</businessActor>");
		}
		sb.append("\n</BusinessCollaboration_BusinessActor>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor businessActor) {
		if ( this.businessActor==null ) {
			this.businessActor=new UiidBasedCascadingInterfaceValue();
		}
		this.businessActor.setValue(businessActor);
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( this.businessCollaboration==null ) {
			this.businessCollaboration=new UiidBasedInterfaceValue();
		}
		this.businessCollaboration.setValue(businessCollaboration);
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor businessActor) {
		if ( getBusinessActor()!=null && businessActor!=null && businessActor.equals(getBusinessActor()) ) {
			this.businessActor.setValue(null);
		}
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( getBusinessCollaboration()!=null && businessCollaboration!=null && businessCollaboration.equals(getBusinessCollaboration()) ) {
			this.businessCollaboration.setValue(null);
		}
	}

}