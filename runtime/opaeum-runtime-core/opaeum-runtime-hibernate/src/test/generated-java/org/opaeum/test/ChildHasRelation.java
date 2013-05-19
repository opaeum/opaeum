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

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="child_has_relation",uniqueConstraints=
	@UniqueConstraint(columnNames={"child_id","god_parent","god_parent_type","deleted_on"}))
@Entity(name="ChildHasRelation")
public class ChildHasRelation implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("godParent",Relation.class);
	}
	
	@Index(columnNames="child_id",name="idx_child_has_relation_child_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="child_id",nullable=true)
	protected Child child;
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="god_parent"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="god_parent_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue godParent;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="child_has_relation",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends ChildHasRelation> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 6792722447437408965l;
	private String uid;
	private String z_keyOfChildOnRelation;
	private String z_keyOfGodParentOnChild;

	/** Constructor for ChildHasRelation
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public ChildHasRelation(Child end1, Relation end2) {
		this.z_internalAddToChild(end1);
		this.z_internalAddToGodParent(end2);
	}
	
	/** Constructor for ChildHasRelation
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public ChildHasRelation(Relation end2, Child end1) {
		this.z_internalAddToChild(end1);
		this.z_internalAddToGodParent(end2);
	}
	
	/** Default constructor for ChildHasRelation
	 */
	public ChildHasRelation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends ChildHasRelation> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.ChildHasRelation.class));
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
		this.z_internalRemoveFromGodParent(getGodParent());
		this.z_internalRemoveFromChild(getChild());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof ChildHasRelation ) {
			return other==this || ((ChildHasRelation)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8317084472673424687l,opposite="childHasRelation_godParent",uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA@Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	public Child getChild() {
		Child result = this.child;
		
		return result;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	public Class getFieldType(String fieldName) {
		Class result = INTERFACE_FIELDS.get(fieldName);
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7442837881736799797l,opposite="childHasRelation_child",uuid="Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA@Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	public Relation getGodParent() {
		Relation result = null;
		if ( this.godParent==null ) {
			this.godParent=new UiidBasedInterfaceValue();
		}
		result=(Relation)this.godParent.getValue(persistence);
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "ChildHasRelation["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getGodParent();
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfChildOnRelation() {
		return this.z_keyOfChildOnRelation;
	}
	
	public String getZ_keyOfGodParentOnChild() {
		return this.z_keyOfGodParentOnChild;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToGodParent((Relation)owner);
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
	
	public void setZ_keyOfChildOnRelation(String z_keyOfChildOnRelation) {
		this.z_keyOfChildOnRelation=z_keyOfChildOnRelation;
	}
	
	public void setZ_keyOfGodParentOnChild(String z_keyOfGodParentOnChild) {
		this.z_keyOfGodParentOnChild=z_keyOfGodParentOnChild;
	}
	
	public String toXmlReferenceString() {
		return "<ChildHasRelation uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ChildHasRelation ");
		sb.append("classUuid=\"Structures.uml@_I7GooIhrEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.ChildHasRelation\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</ChildHasRelation>");
		return sb.toString();
	}
	
	public void z_internalAddToChild(Child child) {
		if ( child.equals(getChild()) ) {
			return;
		}
		this.child=child;
	}
	
	public void z_internalAddToGodParent(Relation godParent) {
		if ( godParent.equals(getGodParent()) ) {
			return;
		}
		if ( this.godParent==null ) {
			this.godParent=new UiidBasedInterfaceValue();
		}
		this.godParent.setValue(godParent);
	}
	
	public void z_internalRemoveFromChild(Child child) {
		if ( getChild()!=null && child!=null && child.equals(getChild()) ) {
			this.child=null;
			this.child=null;
		}
	}
	
	public void z_internalRemoveFromGodParent(Relation godParent) {
		if ( getGodParent()!=null && godParent!=null && godParent.equals(getGodParent()) ) {
			this.godParent.setValue(null);
		}
	}

}