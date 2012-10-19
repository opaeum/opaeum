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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Filter;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.audit.AuditMe;
import org.opaeum.hibernate.domain.CascadingInterfaceValue;
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
@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="i_business_collaboration_i_business_actor_1",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_actor","business_actor_type","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="IBusinessCollaboration_iBusinessActor_1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class IBusinessCollaboration_iBusinessActor_1 implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_actor"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_actor_type"),name="classIdentifier")})
	private CascadingInterfaceValue businessActor = new CascadingInterfaceValue();
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_collaboration"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_collaboration_type"),name="classIdentifier")})
	private InterfaceValue businessCollaboration = new InterfaceValue();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<IBusinessCollaboration_iBusinessActor_1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	static final private long serialVersionUID = 8115205646489818195l;
	private String uid;

	/** Constructor for IBusinessCollaboration_iBusinessActor_1
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public IBusinessCollaboration_iBusinessActor_1(IBusinessActor end2, IBusinessCollaboration end1) {
		this.z_internalAddToBusinessCollaboration(end1);
		this.z_internalAddToBusinessActor(end2);
	}
	
	/** Constructor for IBusinessCollaboration_iBusinessActor_1
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public IBusinessCollaboration_iBusinessActor_1(IBusinessCollaboration end1, IBusinessActor end2) {
		this.z_internalAddToBusinessCollaboration(end1);
		this.z_internalAddToBusinessActor(end2);
	}
	
	/** Default constructor for IBusinessCollaboration_iBusinessActor_1
	 */
	public IBusinessCollaboration_iBusinessActor_1() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends IBusinessCollaboration_iBusinessActor_1> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessActor_1.class));
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
							curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
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
		getBusinessActor().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(this);
		this.z_internalRemoveFromBusinessActor(getBusinessActor());
		getBusinessCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(this);
		this.z_internalRemoveFromBusinessCollaboration(getBusinessCollaboration());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof IBusinessCollaboration_iBusinessActor_1 ) {
			return other==this || ((IBusinessCollaboration_iBusinessActor_1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7010262207974877226l,opposite="iBusinessCollaboration_iBusinessActor_1_businessCollaboration",uuid="252060@_pP5QQVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA@252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public IBusinessActor getBusinessActor() {
		IBusinessActor result = (IBusinessActor)this.businessActor.getValue(persistence);
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4331153764749992840l,opposite="iBusinessCollaboration_iBusinessActor_1_businessActor",uuid="252060@_pP5QRFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA@252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = (IBusinessCollaboration)this.businessCollaboration.getValue(persistence);
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "iBusinessCollaboration_iBusinessActor_1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessCollaboration();
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
		this.z_internalAddToBusinessCollaboration((IBusinessCollaboration)owner);
	}
	
	public void markDeleted() {
		if ( getBusinessCollaboration()!=null ) {
			getBusinessCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(this);
		}
		if ( getBusinessActor()!=null ) {
			getBusinessActor().markDeleted();
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<IBusinessCollaboration_iBusinessActor_1> newMocks) {
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
				IBusinessCollaboration_iBusinessActor_1 oldOther = (IBusinessCollaboration_iBusinessActor_1)businessActor.getIBusinessCollaboration_iBusinessActor_1_businessCollaboration();
				businessActor.z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessActor(businessActor);
				}
				businessActor.z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessCollaboration((IBusinessCollaboration_iBusinessActor_1)this);
			}
			this.z_internalAddToBusinessActor(businessActor);
		} else {
			if ( !oldValue.equals(businessActor) ) {
				oldValue.z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(this);
				z_internalRemoveFromBusinessActor(oldValue);
				if ( businessActor!=null ) {
					IBusinessCollaboration_iBusinessActor_1 oldOther = (IBusinessCollaboration_iBusinessActor_1)businessActor.getIBusinessCollaboration_iBusinessActor_1_businessCollaboration();
					businessActor.z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessCollaboration(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessActor(businessActor);
					}
					businessActor.z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessCollaboration((IBusinessCollaboration_iBusinessActor_1)this);
				}
				this.z_internalAddToBusinessActor(businessActor);
			}
		}
	}
	
	public void setBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		propertyChangeSupport.firePropertyChange("businessCollaboration",getBusinessCollaboration(),businessCollaboration);
		if ( this.getBusinessCollaboration()!=null ) {
			this.getBusinessCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(this);
		}
		if ( businessCollaboration!=null ) {
			businessCollaboration.z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessActor(this);
			this.z_internalAddToBusinessCollaboration(businessCollaboration);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
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
		return "<iBusinessCollaboration_iBusinessActor_1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<iBusinessCollaboration_iBusinessActor_1 ");
		sb.append("classUuid=\"252060@_pP5QQFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessActor_1\" ");
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
		sb.append("\n</iBusinessCollaboration_iBusinessActor_1>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor val) {
		this.businessActor.setValue(val);
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration val) {
		this.businessCollaboration.setValue(val);
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor val) {
		if ( getBusinessActor()!=null && val!=null && val.equals(getBusinessActor()) ) {
			this.businessActor.setValue(null);
		}
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration val) {
		if ( getBusinessCollaboration()!=null && val!=null && val.equals(getBusinessCollaboration()) ) {
			this.businessCollaboration.setValue(null);
		}
	}

}