package org.opaeum.runtime.bpm.organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.runtime.bpm.util.OpaeumLibraryForBPMFormatter;
import org.opaeum.runtime.bpm.util.Stdlib;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="i_business_collaboration_i_business_actor_1",schema="opaeum_bpm")
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="IBusinessCollaboration_iBusinessActor_1")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class IBusinessCollaboration_iBusinessActor_1 implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Any(metaColumn=
		@Column(name="business_actor_type"),metaDef="IBusinessActor")
	@JoinColumn(name="business_actor",nullable=true)
	private IBusinessActor businessActor;
	@Index(columnNames="collaboration",name="idx_i_business_collaboration_i_business_actor_1_collaboration")
	@Any(metaColumn=
		@Column(name="collaboration_type"),metaDef="IBusinessCollaboration")
	@JoinColumn(name="collaboration",nullable=true)
	private IBusinessCollaboration collaboration;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.AUTO)
	private Long id;
	static private Set<IBusinessCollaboration_iBusinessActor_1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	static final private long serialVersionUID = 8115205646489818195l;
	private String uid;

	/** Constructor for IBusinessCollaboration_iBusinessActor_1
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public IBusinessCollaboration_iBusinessActor_1(IBusinessActor end1, IBusinessCollaboration end2) {
		this.z_internalAddToBusinessActor(end1);
		this.z_internalAddToCollaboration(end2);
	}
	
	/** Constructor for IBusinessCollaboration_iBusinessActor_1
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public IBusinessCollaboration_iBusinessActor_1(IBusinessCollaboration end2, IBusinessActor end1) {
		this.z_internalAddToBusinessActor(end1);
		this.z_internalAddToCollaboration(end2);
	}
	
	/** Default constructor for IBusinessCollaboration_iBusinessActor_1
	 */
	public IBusinessCollaboration_iBusinessActor_1() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends IBusinessCollaboration_iBusinessActor_1> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessActor_1.class));
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
		getCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(this);
		this.z_internalRemoveFromCollaboration(getCollaboration());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof IBusinessCollaboration_iBusinessActor_1 ) {
			return other==this || ((IBusinessCollaboration_iBusinessActor_1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA252060@_pP5QRFYuEeGj5_I7bIwNoA")
	public IBusinessActor getBusinessActor() {
		IBusinessActor result = this.businessActor;
		
		return result;
	}
	
	@NumlMetaInfo(uuid="252060@_pP5QQFYuEeGj5_I7bIwNoA252060@_pP5QQVYuEeGj5_I7bIwNoA")
	public IBusinessCollaboration getCollaboration() {
		IBusinessCollaboration result = this.collaboration;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "IBusinessCollaboration_iBusinessActor_1["+getId()+"]";
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
	}
	
	public void markDeleted() {
		if ( getCollaboration()!=null ) {
			getCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4783256010118172302")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessActor((IBusinessActor)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("collaboration") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("7462364453343056688")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setCollaboration((IBusinessCollaboration)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBusinessActor(IBusinessActor businessActor) {
		this.z_internalAddToBusinessActor(businessActor);
	}
	
	public void setCollaboration(IBusinessCollaboration collaboration) {
		if ( this.getCollaboration()!=null ) {
			this.getCollaboration().z_internalRemoveFromIBusinessCollaboration_iBusinessActor_1_businessActor(this);
		}
		if ( collaboration!=null ) {
			collaboration.z_internalAddToIBusinessCollaboration_iBusinessActor_1_businessActor(this);
			this.z_internalAddToCollaboration(collaboration);
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
		return "<IBusinessCollaboration_iBusinessActor_1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<IBusinessCollaboration_iBusinessActor_1 ");
		sb.append("classUuid=\"252060@_pP5QQFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.IBusinessCollaboration_iBusinessActor_1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessActor()==null ) {
			sb.append("\n<businessActor/>");
		} else {
			sb.append("\n<businessActor propertyId=\"4783256010118172302\">");
			sb.append("\n" + getBusinessActor().toXmlReferenceString());
			sb.append("\n</businessActor>");
		}
		if ( getCollaboration()==null ) {
			sb.append("\n<collaboration/>");
		} else {
			sb.append("\n<collaboration propertyId=\"7462364453343056688\">");
			sb.append("\n" + getCollaboration().toXmlReferenceString());
			sb.append("\n</collaboration>");
		}
		sb.append("\n</IBusinessCollaboration_iBusinessActor_1>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor val) {
		this.businessActor=val;
	}
	
	public void z_internalAddToCollaboration(IBusinessCollaboration val) {
		this.collaboration=val;
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor val) {
		if ( getBusinessActor()!=null && val!=null && val.equals(getBusinessActor()) ) {
			this.businessActor=null;
		}
	}
	
	public void z_internalRemoveFromCollaboration(IBusinessCollaboration val) {
		if ( getCollaboration()!=null && val!=null && val.equals(getCollaboration()) ) {
			this.collaboration=null;
		}
	}

}