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
import org.opaeum.runtime.domain.InterfaceValueOwner;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="252060@_3lcZgFYuEeGj5_I7bIwNoA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="person_in_business_role",schema="bpm",uniqueConstraints=
	@UniqueConstraint(columnNames={"business_role","business_role_type","deleted_on"}))
@Entity(name="PersonInBusinessRole")
public class PersonInBusinessRole implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("businessRole",IBusinessRole.class);
	}
	
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
		this.z_internalRemoveFromBusinessRole(getBusinessRole());
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
	
	public Class getFieldType(String fieldName) {
		Class result = INTERFACE_FIELDS.get(fieldName);
		
		return result;
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
		this.z_internalAddToBusinessRole((IBusinessRole)owner);
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
		return "<PersonInBusinessRole uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<PersonInBusinessRole ");
		sb.append("classUuid=\"252060@_3lcZgFYuEeGj5_I7bIwNoA\" ");
		sb.append("className=\"org.opaeum.runtime.bpm.organization.PersonInBusinessRole\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
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