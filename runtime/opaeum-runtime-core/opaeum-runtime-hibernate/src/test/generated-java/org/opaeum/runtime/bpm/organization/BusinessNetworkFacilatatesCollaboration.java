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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedCascadingInterfaceValue;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.InterfaceValueOwner;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="business_network_facilatates_collaboration",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_collaboration","business_collaboration_type","deleted_on"}))
@Entity(name="BusinessNetworkFacilatatesCollaboration")
public class BusinessNetworkFacilatatesCollaboration implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("businessCollaboration",IBusinessCollaboration.class);
	}
	
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_collaboration"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_collaboration_type"),name="classIdentifier")})
	protected UiidBasedCascadingInterfaceValue businessCollaboration;
	@Index(columnNames="business_network_id",name="idx_business_network_facilatates_collaboration_business_network_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="business_network_id",nullable=true)
	protected BusinessNetwork businessNetwork;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="business_network_facilatates_collaboration",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends BusinessNetworkFacilatatesCollaboration> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 2393652207927689043l;
	private String uid;

	/** Constructor for BusinessNetworkFacilatatesCollaboration
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public BusinessNetworkFacilatatesCollaboration(BusinessNetwork end1, IBusinessCollaboration end2) {
		this.z_internalAddToBusinessNetwork(end1);
		this.z_internalAddToBusinessCollaboration(end2);
	}
	
	/** Constructor for BusinessNetworkFacilatatesCollaboration
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public BusinessNetworkFacilatatesCollaboration(IBusinessCollaboration end2, BusinessNetwork end1) {
		this.z_internalAddToBusinessNetwork(end1);
		this.z_internalAddToBusinessCollaboration(end2);
	}
	
	/** Default constructor for BusinessNetworkFacilatatesCollaboration
	 */
	public BusinessNetworkFacilatatesCollaboration() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends BusinessNetworkFacilatatesCollaboration> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.BusinessNetworkFacilatatesCollaboration.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2754791200820683043")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						IBusinessCollaboration curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToBusinessCollaboration(curVal);
						curVal.z_internalAddToBusinessNetworkFacilatatesCollaboration_businessNetwork(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromBusinessCollaboration(getBusinessCollaboration());
		this.z_internalRemoveFromBusinessNetwork(getBusinessNetwork());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof BusinessNetworkFacilatatesCollaboration ) {
			return other==this || ((BusinessNetworkFacilatatesCollaboration)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2754791200820683043l,opposite="businessNetworkFacilatatesCollaboration_businessNetwork",uuid="252060@_YJETMFYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg@252060@_YJETMFYjEeGJUqEGX7bKSg")
	public IBusinessCollaboration getBusinessCollaboration() {
		IBusinessCollaboration result = null;
		if ( this.businessCollaboration==null ) {
			this.businessCollaboration=new UiidBasedCascadingInterfaceValue();
		}
		result=(IBusinessCollaboration)this.businessCollaboration.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1977591455643758737l,opposite="businessNetworkFacilatatesCollaboration_businessCollaboration",uuid="252060@_YJGvcVYjEeGJUqEGX7bKSg")
	@NumlMetaInfo(uuid="252060@_YJGvcFYjEeGJUqEGX7bKSg@252060@_YJGvcVYjEeGJUqEGX7bKSg")
	public BusinessNetwork getBusinessNetwork() {
		BusinessNetwork result = this.businessNetwork;
		
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
		return "BusinessNetworkFacilatatesCollaboration["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessNetwork();
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
		this.z_internalAddToBusinessNetwork((BusinessNetwork)owner);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessCollaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2754791200820683043")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((IBusinessCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
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
		return "<BusinessNetworkFacilatatesCollaboration uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<BusinessNetworkFacilatatesCollaboration ");
		sb.append("classUuid=\"252060@_YJGvcFYjEeGJUqEGX7bKSg\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.BusinessNetworkFacilatatesCollaboration\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessCollaboration()==null ) {
			sb.append("\n<businessCollaboration/>");
		} else {
			sb.append("\n<businessCollaboration propertyId=\"2754791200820683043\">");
			sb.append("\n" + getBusinessCollaboration().toXmlString());
			sb.append("\n</businessCollaboration>");
		}
		sb.append("\n</BusinessNetworkFacilatatesCollaboration>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( businessCollaboration.equals(getBusinessCollaboration()) ) {
			return;
		}
		if ( this.businessCollaboration==null ) {
			this.businessCollaboration=new UiidBasedCascadingInterfaceValue();
		}
		this.businessCollaboration.setValue(businessCollaboration);
	}
	
	public void z_internalAddToBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( businessNetwork.equals(getBusinessNetwork()) ) {
			return;
		}
		this.businessNetwork=businessNetwork;
	}
	
	public void z_internalRemoveFromBusinessCollaboration(IBusinessCollaboration businessCollaboration) {
		if ( getBusinessCollaboration()!=null && businessCollaboration!=null && businessCollaboration.equals(getBusinessCollaboration()) ) {
			this.businessCollaboration.setValue(null);
		}
	}
	
	public void z_internalRemoveFromBusinessNetwork(BusinessNetwork businessNetwork) {
		if ( getBusinessNetwork()!=null && businessNetwork!=null && businessNetwork.equals(getBusinessNetwork()) ) {
			this.businessNetwork=null;
			this.businessNetwork=null;
		}
	}

}