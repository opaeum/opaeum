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
@NumlMetaInfo(uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_fullfills_actor_role",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_actor","business_actor_type","deleted_on"}))
@Inheritance(strategy=javax.persistence.InheritanceType.JOINED)
@Entity(name="PersonFullfillsActorRole")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING,name="type_descriminator")
public class PersonFullfillsActorRole implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
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
	static private Set<PersonFullfillsActorRole> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private AbstractPersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="represented_person_id",name="idx_person_fullfills_actor_role_represented_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="represented_person_id",nullable=true)
	private PersonNode representedPerson;
	static final private long serialVersionUID = 2277354138337111468l;
	private String uid;

	/** Constructor for PersonFullfillsActorRole
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public PersonFullfillsActorRole(IBusinessActor end2, PersonNode end1) {
		this.z_internalAddToRepresentedPerson(end1);
		this.z_internalAddToBusinessActor(end2);
	}
	
	/** Constructor for PersonFullfillsActorRole
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public PersonFullfillsActorRole(PersonNode end1, IBusinessActor end2) {
		this.z_internalAddToRepresentedPerson(end1);
		this.z_internalAddToBusinessActor(end2);
	}
	
	/** Default constructor for PersonFullfillsActorRole
	 */
	public PersonFullfillsActorRole() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends PersonFullfillsActorRole> allInstances() {
		if ( mockedAllInstances==null ) {
			CmtPersistence session =org.opaeum.runtime.environment.Environment.getInstance().getComponent(CmtPersistence.class);
			return new HashSet(session.readAll(org.opaeum.runtime.bpm.organization.PersonFullfillsActorRole.class));
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
		getRepresentedPerson().z_internalRemoveFromPersonFullfillsActorRole_businessActor(this);
		this.z_internalRemoveFromRepresentedPerson(getRepresentedPerson());
		getBusinessActor().z_internalRemoveFromPersonFullfillsActorRole_representedPerson(this);
		this.z_internalRemoveFromBusinessActor(getBusinessActor());
		markDeleted();
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PersonFullfillsActorRole ) {
			return other==this || ((PersonFullfillsActorRole)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3187089794534318837l,opposite="personFullfillsActorRole_representedPerson",uuid="252060@_AN3QcEtxEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_X4-lcEtyEeGElKTCe2jfDw252060@_X4_Mg0tyEeGElKTCe2jfDw")
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
		return "PersonFullfillsActorRole["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getRepresentedPerson();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6815035603503332095l,opposite="personFullfillsActorRole_businessActor",uuid="252060@_k23OoEtmEeGd4cpyhpib9Q")
	@NumlMetaInfo(uuid="252060@_X4-lcEtyEeGElKTCe2jfDw252060@_X4_MgEtyEeGElKTCe2jfDw")
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
		if ( getBusinessActor()!=null ) {
			getBusinessActor().z_internalRemoveFromPersonFullfillsActorRole_representedPerson(this);
		}
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromPersonFullfillsActorRole_businessActor(this);
		}
		setDeletedOn(new Date());
	}
	
	static public void mockAllInstances(Set<PersonFullfillsActorRole> newMocks) {
		mockedAllInstances=newMocks;
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3187089794534318837")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessActor((IBusinessActor)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6815035603503332095")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setRepresentedPerson((PersonNode)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
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
				PersonFullfillsActorRole oldOther = (PersonFullfillsActorRole)businessActor.getPersonFullfillsActorRole_representedPerson();
				businessActor.z_internalRemoveFromPersonFullfillsActorRole_representedPerson(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessActor(businessActor);
				}
				businessActor.z_internalAddToPersonFullfillsActorRole_representedPerson((PersonFullfillsActorRole)this);
			}
			this.z_internalAddToBusinessActor(businessActor);
		} else {
			if ( !oldValue.equals(businessActor) ) {
				oldValue.z_internalRemoveFromPersonFullfillsActorRole_representedPerson(this);
				z_internalRemoveFromBusinessActor(oldValue);
				if ( businessActor!=null ) {
					PersonFullfillsActorRole oldOther = (PersonFullfillsActorRole)businessActor.getPersonFullfillsActorRole_representedPerson();
					businessActor.z_internalRemoveFromPersonFullfillsActorRole_representedPerson(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessActor(businessActor);
					}
					businessActor.z_internalAddToPersonFullfillsActorRole_representedPerson((PersonFullfillsActorRole)this);
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
	
	public void setRepresentedPerson(PersonNode representedPerson) {
		propertyChangeSupport.firePropertyChange("representedPerson",getRepresentedPerson(),representedPerson);
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromPersonFullfillsActorRole_businessActor(this);
		}
		if ( representedPerson!=null ) {
			representedPerson.z_internalAddToPersonFullfillsActorRole_businessActor(this);
			this.z_internalAddToRepresentedPerson(representedPerson);
			setDeletedOn(Stdlib.FUTURE);
		} else {
			markDeleted();
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<PersonFullfillsActorRole uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PersonFullfillsActorRole ");
		sb.append("classUuid=\"252060@_X4-lcEtyEeGElKTCe2jfDw\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.PersonFullfillsActorRole\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getBusinessActor()==null ) {
			sb.append("\n<businessActor/>");
		} else {
			sb.append("\n<businessActor propertyId=\"3187089794534318837\">");
			sb.append("\n" + getBusinessActor().toXmlReferenceString());
			sb.append("\n</businessActor>");
		}
		if ( getRepresentedPerson()==null ) {
			sb.append("\n<representedPerson/>");
		} else {
			sb.append("\n<representedPerson propertyId=\"6815035603503332095\">");
			sb.append("\n" + getRepresentedPerson().toXmlReferenceString());
			sb.append("\n</representedPerson>");
		}
		sb.append("\n</PersonFullfillsActorRole>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor val) {
		this.businessActor.setValue(val);
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode val) {
		this.representedPerson=val;
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor val) {
		if ( getBusinessActor()!=null && val!=null && val.equals(getBusinessActor()) ) {
			this.businessActor.setValue(null);
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode val) {
		if ( getRepresentedPerson()!=null && val!=null && val.equals(getRepresentedPerson()) ) {
			this.representedPerson=null;
			this.representedPerson=null;
		}
	}

}