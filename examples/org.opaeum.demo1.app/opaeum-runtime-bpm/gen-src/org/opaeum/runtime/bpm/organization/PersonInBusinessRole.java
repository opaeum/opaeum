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

@NumlMetaInfo(applicationIdentifier="demo1",uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_in_business_role",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_role","business_role_type","deleted_on"}))
@Entity(name="PersonInBusinessRole")
public class PersonInBusinessRole implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="business_role"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="business_role_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue businessRole;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="person_in_business_role",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends PersonInBusinessRole> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	@Index(columnNames="represented_person_id",name="idx_person_in_business_role_represented_person_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="represented_person_id",nullable=true)
	protected PersonNode representedPerson;
	static final private long serialVersionUID = 1746310635847270999l;
	private String uid;

	/** Constructor for PersonInBusinessRole
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public PersonInBusinessRole(IBusinessRole end2, PersonNode end1) {
		this.z_internalAddToRepresentedPerson(end1);
		this.z_internalAddToBusinessRole(end2);
	}
	
	/** Constructor for PersonInBusinessRole
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public PersonInBusinessRole(PersonNode end1, IBusinessRole end2) {
		this.z_internalAddToRepresentedPerson(end1);
		this.z_internalAddToBusinessRole(end2);
	}
	
	/** Default constructor for PersonInBusinessRole
	 */
	public PersonInBusinessRole() {
	}

	public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(property,listener);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends PersonInBusinessRole> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.runtime.bpm.organization.PersonInBusinessRole.class));
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
		getBusinessRole().z_internalRemoveFromPersonInBusinessRole_representedPerson(this);
		this.z_internalRemoveFromBusinessRole(getBusinessRole());
		getRepresentedPerson().z_internalRemoveFromPersonInBusinessRole_businessRole(this);
		this.z_internalRemoveFromRepresentedPerson(getRepresentedPerson());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof PersonInBusinessRole ) {
			return other==this || ((PersonInBusinessRole)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=126801632371403666l,opposite="personInBusinessRole_representedPerson",uuid="252060@_3lakUFYuEeGj5_I7bIwNoA")
	@NumlMetaInfo(uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA@252060@_3lakUFYuEeGj5_I7bIwNoA")
	public IBusinessRole getBusinessRole() {
		IBusinessRole result = null;
		if ( this.businessRole==null ) {
			this.businessRole=new UiidBasedInterfaceValue();
		}
		result=(IBusinessRole)this.businessRole.getValue(persistence);
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "PersonInBusinessRole["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getBusinessRole();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6073685631049409880l,opposite="personInBusinessRole_businessRole",uuid="252060@_3lcZgVYuEeGj5_I7bIwNoA")
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
		if ( getOwningObject()!=null && !getOwningObject().equals(owner) ) {
			System.out.println("Reparenting "+getClass().getSimpleName() +getId());
		}
		this.z_internalAddToBusinessRole((IBusinessRole)owner);
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getRepresentedPerson()!=null ) {
			getRepresentedPerson().z_internalRemoveFromPersonInBusinessRole_businessRole(this);
		}
		if ( getBusinessRole()!=null ) {
			getBusinessRole().z_internalRemoveFromPersonInBusinessRole_representedPerson(this);
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
	
	public void removePropertyChangeListener(String property, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(property,listener);
	}
	
	public void setBusinessRole(IBusinessRole businessRole) {
		IBusinessRole oldValue = this.getBusinessRole();
		propertyChangeSupport.firePropertyChange("businessRole",getBusinessRole(),businessRole);
		if ( oldValue==null ) {
			if ( businessRole!=null ) {
				PersonInBusinessRole oldOther = (PersonInBusinessRole)businessRole.getPersonInBusinessRole_representedPerson();
				businessRole.z_internalRemoveFromPersonInBusinessRole_representedPerson(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromBusinessRole(businessRole);
				}
				businessRole.z_internalAddToPersonInBusinessRole_representedPerson((PersonInBusinessRole)this);
			}
			this.z_internalAddToBusinessRole(businessRole);
		} else {
			if ( !oldValue.equals(businessRole) ) {
				oldValue.z_internalRemoveFromPersonInBusinessRole_representedPerson(this);
				z_internalRemoveFromBusinessRole(oldValue);
				if ( businessRole!=null ) {
					PersonInBusinessRole oldOther = (PersonInBusinessRole)businessRole.getPersonInBusinessRole_representedPerson();
					businessRole.z_internalRemoveFromPersonInBusinessRole_representedPerson(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromBusinessRole(businessRole);
					}
					businessRole.z_internalAddToPersonInBusinessRole_representedPerson((PersonInBusinessRole)this);
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
		propertyChangeSupport.firePropertyChange("representedPerson",getRepresentedPerson(),representedPerson);
		if ( this.getRepresentedPerson()!=null ) {
			this.getRepresentedPerson().z_internalRemoveFromPersonInBusinessRole_businessRole(this);
		}
		if ( representedPerson == null ) {
			this.z_internalRemoveFromRepresentedPerson(this.getRepresentedPerson());
		} else {
			this.z_internalAddToRepresentedPerson(representedPerson);
		}
		if ( representedPerson!=null ) {
			representedPerson.z_internalAddToPersonInBusinessRole_businessRole(this);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<PersonInBusinessRole uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PersonInBusinessRole ");
		sb.append("classUuid=\"252060@_3lcZgFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.PersonInBusinessRole\" ");
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
		sb.append("\n</PersonInBusinessRole>");
		return sb.toString();
	}
	
	public void z_internalAddToBusinessRole(IBusinessRole businessRole) {
		if ( businessRole.equals(getBusinessRole()) ) {
			return;
		}
		if ( this.businessRole==null ) {
			this.businessRole=new UiidBasedInterfaceValue();
		}
		this.businessRole.setValue(businessRole);
	}
	
	public void z_internalAddToRepresentedPerson(PersonNode representedPerson) {
		if ( representedPerson.equals(getRepresentedPerson()) ) {
			return;
		}
		this.representedPerson=representedPerson;
	}
	
	public void z_internalRemoveFromBusinessRole(IBusinessRole businessRole) {
		if ( getBusinessRole()!=null && businessRole!=null && businessRole.equals(getBusinessRole()) ) {
			this.businessRole.setValue(null);
		}
	}
	
	public void z_internalRemoveFromRepresentedPerson(PersonNode representedPerson) {
		if ( getRepresentedPerson()!=null && representedPerson!=null && representedPerson.equals(getRepresentedPerson()) ) {
			this.representedPerson=null;
			this.representedPerson=null;
		}
	}

}