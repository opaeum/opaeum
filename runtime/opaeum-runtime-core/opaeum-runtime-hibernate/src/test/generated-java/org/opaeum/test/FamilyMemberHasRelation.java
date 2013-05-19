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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
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

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="family_member_has_relation",uniqueConstraints=
	@UniqueConstraint(columnNames={"family_member","family_member_type","relation","relation_type","deleted_on"}))
@Entity(name="FamilyMemberHasRelation")
public class FamilyMemberHasRelation implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("relation",Relation.class);
	INTERFACE_FIELDS.put("familyMember",FamilyMember.class);
	}
	
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="family_member"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="family_member_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue familyMember;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="family_member_has_relation",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends FamilyMemberHasRelation> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="relation"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="relation_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue relation;
	static final private long serialVersionUID = 6261560761254585007l;
	private String uid;
	private String z_keyOfFamilyMemberOnRelation;
	private String z_keyOfRelationOnFamilyMember;

	/** Constructor for FamilyMemberHasRelation
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public FamilyMemberHasRelation(FamilyMember end1, Relation end2) {
		this.z_internalAddToFamilyMember(end1);
		this.z_internalAddToRelation(end2);
	}
	
	/** Constructor for FamilyMemberHasRelation
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public FamilyMemberHasRelation(Relation end2, FamilyMember end1) {
		this.z_internalAddToFamilyMember(end1);
		this.z_internalAddToRelation(end2);
	}
	
	/** Default constructor for FamilyMemberHasRelation
	 */
	public FamilyMemberHasRelation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends FamilyMemberHasRelation> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.FamilyMemberHasRelation.class));
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
		this.z_internalRemoveFromRelation(getRelation());
		this.z_internalRemoveFromFamilyMember(getFamilyMember());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof FamilyMemberHasRelation ) {
			return other==this || ((FamilyMemberHasRelation)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6572647679031263107l,opposite="familyMemberHasRelation_relation",uuid="Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA@Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
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
		return "FamilyMemberHasRelation["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getRelation();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9194987951453404123l,opposite="familyMemberHasRelation_familyMember",uuid="Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	public Relation getRelation() {
		Relation result = null;
		if ( this.relation==null ) {
			this.relation=new UiidBasedInterfaceValue();
		}
		result=(Relation)this.relation.getValue(persistence);
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfFamilyMemberOnRelation() {
		return this.z_keyOfFamilyMemberOnRelation;
	}
	
	public String getZ_keyOfRelationOnFamilyMember() {
		return this.z_keyOfRelationOnFamilyMember;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToRelation((Relation)owner);
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
	
	public void setZ_keyOfFamilyMemberOnRelation(String z_keyOfFamilyMemberOnRelation) {
		this.z_keyOfFamilyMemberOnRelation=z_keyOfFamilyMemberOnRelation;
	}
	
	public void setZ_keyOfRelationOnFamilyMember(String z_keyOfRelationOnFamilyMember) {
		this.z_keyOfRelationOnFamilyMember=z_keyOfRelationOnFamilyMember;
	}
	
	public String toXmlReferenceString() {
		return "<FamilyMemberHasRelation uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<FamilyMemberHasRelation ");
		sb.append("classUuid=\"Structures.uml@_wPOkwIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.FamilyMemberHasRelation\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</FamilyMemberHasRelation>");
		return sb.toString();
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
	
	public void z_internalAddToRelation(Relation relation) {
		if ( relation.equals(getRelation()) ) {
			return;
		}
		if ( this.relation==null ) {
			this.relation=new UiidBasedInterfaceValue();
		}
		this.relation.setValue(relation);
	}
	
	public void z_internalRemoveFromFamilyMember(FamilyMember familyMember) {
		if ( getFamilyMember()!=null && familyMember!=null && familyMember.equals(getFamilyMember()) ) {
			this.familyMember.setValue(null);
		}
	}
	
	public void z_internalRemoveFromRelation(Relation relation) {
		if ( getRelation()!=null && relation!=null && relation.equals(getRelation()) ) {
			this.relation.setValue(null);
		}
	}

}