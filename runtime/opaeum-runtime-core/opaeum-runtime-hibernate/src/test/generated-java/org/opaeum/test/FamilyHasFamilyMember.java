package org.opaeum.test;

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
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.InterfaceValueOwner;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_N7WfII08EeKHBNiW4NWnIg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="family_has_family_member",uniqueConstraints=
	@UniqueConstraint(columnNames={"family_member","family_member_type","deleted_on"}))
@Entity(name="FamilyHasFamilyMember")
public class FamilyHasFamilyMember implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("familyMember",FamilyMember.class);
	}
	
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="family_id",name="idx_family_has_family_member_family_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="family_id",nullable=true)
	protected Family family;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="family_member"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="family_member_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue familyMember;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="family_has_family_member",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends FamilyHasFamilyMember> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 3933237201390277163l;
	private String uid;
	private String z_keyOfFamilyMemberOnFamily;

	/** Constructor for FamilyHasFamilyMember
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public FamilyHasFamilyMember(Family end1, FamilyMember end2) {
		this.z_internalAddToFamily(end1);
		this.z_internalAddToFamilyMember(end2);
	}
	
	/** Constructor for FamilyHasFamilyMember
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public FamilyHasFamilyMember(FamilyMember end2, Family end1) {
		this.z_internalAddToFamily(end1);
		this.z_internalAddToFamilyMember(end2);
	}
	
	/** Default constructor for FamilyHasFamilyMember
	 */
	public FamilyHasFamilyMember() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends FamilyHasFamilyMember> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.FamilyHasFamilyMember.class));
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
		this.z_internalRemoveFromFamilyMember(getFamilyMember());
		this.z_internalRemoveFromFamily(getFamily());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof FamilyHasFamilyMember ) {
			return other==this || ((FamilyHasFamilyMember)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5122450861613748053l,opposite="familyHasFamilyMember_familyMember",uuid="Structures.uml@_N7WfIY08EeKHBNiW4NWnIg")
	@NumlMetaInfo(uuid="Structures.uml@_N7WfII08EeKHBNiW4NWnIg@Structures.uml@_N7WfIY08EeKHBNiW4NWnIg")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4571259680665147515l,opposite="familyHasFamilyMember_family",uuid="Structures.uml@_N7V4EI08EeKHBNiW4NWnIg")
	@NumlMetaInfo(uuid="Structures.uml@_N7WfII08EeKHBNiW4NWnIg@Structures.uml@_N7V4EI08EeKHBNiW4NWnIg")
	public FamilyMember getFamilyMember() {
		FamilyMember result = null;
		if ( this.familyMember==null ) {
			this.familyMember=new UiidBasedInterfaceValue();
		}
		result=(FamilyMember)this.familyMember.getValue(persistence);
		return result;
	}
	
	public Class getFieldType(String fieldName) {
		Class result = INTERFACE_FIELDS.get(fieldName);
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "FamilyHasFamilyMember["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getFamilyMember();
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfFamilyMemberOnFamily() {
		return this.z_keyOfFamilyMemberOnFamily;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToFamilyMember((FamilyMember)owner);
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
	
	public void setZ_keyOfFamilyMemberOnFamily(String z_keyOfFamilyMemberOnFamily) {
		this.z_keyOfFamilyMemberOnFamily=z_keyOfFamilyMemberOnFamily;
	}
	
	public String toXmlReferenceString() {
		return "<FamilyHasFamilyMember uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<FamilyHasFamilyMember ");
		sb.append("classUuid=\"Structures.uml@_N7WfII08EeKHBNiW4NWnIg\" ");
		sb.append("className=\"org.opaeum.test.FamilyHasFamilyMember\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</FamilyHasFamilyMember>");
		return sb.toString();
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToFamilyMember(FamilyMember familyMember) {
		if ( familyMember.equals(getFamilyMember()) ) {
			return;
		}
		if ( this.familyMember==null ) {
			this.familyMember=new UiidBasedInterfaceValue();
		}
		this.familyMember.setValue(familyMember);
	}
	
	public void z_internalRemoveFromFamily(Family family) {
		if ( getFamily()!=null && family!=null && family.equals(getFamily()) ) {
			this.family=null;
			this.family=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMember(FamilyMember familyMember) {
		if ( getFamilyMember()!=null && familyMember!=null && familyMember.equals(getFamilyMember()) ) {
			this.familyMember.setValue(null);
		}
	}

}