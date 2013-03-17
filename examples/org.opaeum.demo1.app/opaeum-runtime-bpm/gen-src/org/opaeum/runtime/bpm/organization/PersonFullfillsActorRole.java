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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_X4-lcEtyEeGElKTCe2jfDw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_fullfills_actor_role",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_actor","business_actor_type","deleted_on"}))
@Entity(name="PersonFullfillsActorRole")
public class PersonFullfillsActorRole implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_actor"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_actor_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue businessActor;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="person_fullfills_actor_role",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends PersonFullfillsActorRole> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="represented_person_id",name="idx_person_fullfills_actor_role_represented_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="represented_person_id",nullable=true)
	protected PersonNode representedPerson;
	static final private long serialVersionUID = 2277354138337111468l;
	private String uid;

	/** Constructor for PersonFullfillsActorRole
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public PersonFullfillsActorRole(IBusinessActor end1, PersonNode end2) {
		this.z_internalAddToBusinessActor(end1);
		this.z_internalAddToRepresentedPerson(end2);
	}
	
	/** Constructor for PersonFullfillsActorRole
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public PersonFullfillsActorRole(PersonNode end2, IBusinessActor end1) {
		this.z_internalAddToBusinessActor(end1);
		this.z_internalAddToRepresentedPerson(end2);
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
	
	static public Set<? extends PersonFullfillsActorRole> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.PersonFullfillsActorRole.class));
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
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PersonFullfillsActorRole ) {
			return other==this || ((PersonFullfillsActorRole)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6197890195961667755l,opposite="personFullfillsActorRole_representedPerson",uuid="252060@_X4_MgEtyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_X4-lcEtyEeGElKTCe2jfDw@252060@_X4_MgEtyEeGElKTCe2jfDw")
	public IBusinessActor getBusinessActor() {
		IBusinessActor result = null;
		if ( this.businessActor==null ) {
			this.businessActor=new UiidBasedInterfaceValue();
		}
		result=(IBusinessActor)this.businessActor.getValue(persistence);
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8620908068778870603l,opposite="personFullfillsActorRole_businessActor",uuid="252060@_X4_Mg0tyEeGElKTCe2jfDw")
	@NumlMetaInfo(uuid="252060@_X4-lcEtyEeGElKTCe2jfDw@252060@_X4_Mg0tyEeGElKTCe2jfDw")
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
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
		this.z_internalAddToRepresentedPerson((PersonNode)owner);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getBusinessActor()!=null ) {
			getBusinessActor().z_internalRemoveFromPersonFullfillsActorRole_representedPerson(this);
		}
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromPersonFullfillsActorRole_businessActor(this);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("businessActor") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6197890195961667755")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						setBusinessActor((IBusinessActor)map.get(((Element)currentPropertyValueNode).getAttribute("uid")));
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("representedPerson") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8620908068778870603")) ) {
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
		if ( representedPerson == null ) {
			this.z_internalRemoveFromRepresentedPerson(this.getRepresentedPerson());
		} else {
			this.z_internalAddToRepresentedPerson(representedPerson);
		}
		if ( representedPerson!=null ) {
			representedPerson.z_internalAddToPersonFullfillsActorRole_businessActor(this);
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
			sb.append("\n<businessActor propertyId=\"6197890195961667755\">");
			sb.append("\n" + getBusinessActor().toXmlReferenceString());
			sb.append("\n</businessActor>");
		}
		if ( getRepresentedPerson()==null ) {
			sb.append("\n<representedPerson/>");
		} else {
			sb.append("\n<representedPerson propertyId=\"8620908068778870603\">");
			sb.append("\n" + getRepresentedPerson().toXmlReferenceString());
			sb.append("\n</representedPerson>");
		}
		sb.append("\n</PersonFullfillsActorRole>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessActor(IBusinessActor businessActor) {
		if ( businessActor.equals(getBusinessActor()) ) {
			return;
		}
		if ( this.businessActor==null ) {
			this.businessActor=new UiidBasedInterfaceValue();
		}
		this.businessActor.setValueInternal(businessActor);
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode representedPerson) {
		if ( representedPerson.equals(getRepresentedPerson()) ) {
			return;
		}
		this.representedPerson=representedPerson;
	}
	
	public void z_internalRemoveFromBusinessActor(IBusinessActor businessActor) {
		if ( getBusinessActor()!=null && businessActor!=null && businessActor.equals(getBusinessActor()) ) {
			this.businessActor.setValueInternal(null);
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode representedPerson) {
		if ( getRepresentedPerson()!=null && representedPerson!=null && representedPerson.equals(getRepresentedPerson()) ) {
			this.representedPerson=null;
			this.representedPerson=null;
		}
	}

}