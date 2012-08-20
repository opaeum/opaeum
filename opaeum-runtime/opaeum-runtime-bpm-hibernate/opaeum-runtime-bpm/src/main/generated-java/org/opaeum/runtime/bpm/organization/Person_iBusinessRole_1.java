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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

@NumlMetaInfo(uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_i_business_role_1",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_role","business_role_type","deleted_on"}))
@Entity(name="Person_iBusinessRole_1")
public class Person_iBusinessRole_1 implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_role"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_role_type"),name="classIdentifier")})
	private IBusinessRole businessRole;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Id
	@GeneratedValue(strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<Person_iBusinessRole_1> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Index(columnNames="represented_person_id",name="idx_person_i_business_role_1_represented_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="represented_person_id",nullable=true)
	private PersonNode representedPerson;
	static final private long serialVersionUID = 1746310635847270999l;
	private String uid;

	/** Constructor for Person_iBusinessRole_1
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public Person_iBusinessRole_1(IBusinessRole end2, PersonNode end1) {
		this.z_internalAddToRepresentedPerson(end1);
		this.z_internalAddToBusinessRole(end2);
	}
	
	/** Constructor for Person_iBusinessRole_1
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public Person_iBusinessRole_1(PersonNode end1, IBusinessRole end2) {
		this.z_internalAddToRepresentedPerson(end1);
		this.z_internalAddToBusinessRole(end2);
	}
	
	/** Default constructor for Person_iBusinessRole_1
	 */
	public Person_iBusinessRole_1() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends Person_iBusinessRole_1> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.Person_iBusinessRole_1.class));
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
		getBusinessRole().z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(this);
		this.z_internalRemoveFromBusinessRole(getBusinessRole());
		getRepresentedPerson().z_internalRemoveFromPerson_iBusinessRole_1_businessRole(this);
		this.z_internalRemoveFromRepresentedPerson(getRepresentedPerson());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Person_iBusinessRole_1 ) {
			return other==this || ((Person_iBusinessRole_1)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=126801632371403666l,opposite="person_iBusinessRole_1_representedPerson",uuid="252060@_3lakUFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA@252060@_3lakUFYuEeGj5_I7bIwNoA")
	public IBusinessRole getBusinessRole() {
		IBusinessRole result = this.businessRole;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "person_iBusinessRole_1["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getRepresentedPerson();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6073685631049409880l,opposite="person_iBusinessRole_1_businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA@252060@_3lcZgVYuEeGj5_I7bIwNoA")
	public PersonNode getRepresentedPerson() {
		PersonNode result = this.representedPerson;
		
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
		this.z_internalAddToRepresentedPerson((PersonNode)owner);
	}
	
	public void markDeleted() {
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromPerson_iBusinessRole_1_businessRole(this);
		}
		if ( getBusinessRole()!=null ) {
			getBusinessRole().z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<Person_iBusinessRole_1> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6073685631049409880")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setRepresentedPerson((PersonNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessRole") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("126801632371403666")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessRole((IBusinessRole)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setBusinessRole(IBusinessRole businessRole) {
		IBusinessRole oldValue = this.getBusinessRole();
		if ( oldValue==null ) {
			if ( businessRole!=null ) {
				Person_iBusinessRole_1 oldOther = (Person_iBusinessRole_1)businessRole.getPerson_iBusinessRole_1_representedPerson();
				businessRole.z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessRole(businessRole);
				}
				businessRole.z_internalAddToPerson_iBusinessRole_1_representedPerson((Person_iBusinessRole_1)this);
			}
			this.z_internalAddToBusinessRole(businessRole);
		} else {
			if ( !oldValue.equals(businessRole) ) {
				oldValue.z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(this);
				z_internalRemoveFromBusinessRole(oldValue);
				if ( businessRole!=null ) {
					Person_iBusinessRole_1 oldOther = (Person_iBusinessRole_1)businessRole.getPerson_iBusinessRole_1_representedPerson();
					businessRole.z_internalRemoveFromPerson_iBusinessRole_1_representedPerson(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessRole(businessRole);
					}
					businessRole.z_internalAddToPerson_iBusinessRole_1_representedPerson((Person_iBusinessRole_1)this);
				}
				this.z_internalAddToBusinessRole(businessRole);
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
	
	public void setRepresentedPerson(PersonNode representedPerson) {
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromPerson_iBusinessRole_1_businessRole(this);
		}
		if ( representedPerson!=null ) {
			representedPerson.z_internalAddToPerson_iBusinessRole_1_businessRole(this);
			this.z_internalAddToRepresentedPerson(representedPerson);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<person_iBusinessRole_1 uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<person_iBusinessRole_1 ");
		sb.append("classUuid=\"252060@_3lcZgFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.Person_iBusinessRole_1\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getRepresentedPerson()==null ) {
			sb.append("\n<representedPerson/>");
		} else {
			sb.append("\n<representedPerson propertyId=\"6073685631049409880\">");
			sb.append("\n" + getRepresentedPerson().toXmlReferenceString());
			sb.append("\n</representedPerson>");
		}
		if ( getBusinessRole()==null ) {
			sb.append("\n<businessRole/>");
		} else {
			sb.append("\n<businessRole propertyId=\"126801632371403666\">");
			sb.append("\n" + getBusinessRole().toXmlReferenceString());
			sb.append("\n</businessRole>");
		}
		sb.append("\n</person_iBusinessRole_1>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessRole(IBusinessRole val) {
		this.businessRole=val;
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode val) {
		this.representedPerson=val;
	}
	
	public void z_internalRemoveFromBusinessRole(IBusinessRole val) {
		if ( getBusinessRole()!=null && val!=null && val.equals(getBusinessRole()) ) {
			this.businessRole=null;
			this.businessRole=null;
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode val) {
		if ( getRepresentedPerson()!=null && val!=null && val.equals(getRepresentedPerson()) ) {
			this.representedPerson=null;
			this.representedPerson=null;
		}
	}

}