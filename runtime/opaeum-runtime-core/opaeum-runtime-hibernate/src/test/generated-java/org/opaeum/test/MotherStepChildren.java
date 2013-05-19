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

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="mother_step_children",uniqueConstraints=
	@UniqueConstraint(columnNames={"step_child","step_child_type","deleted_on"}))
@Entity(name="MotherStepChildren")
public class MotherStepChildren implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("stepChild",StepChild.class);
	}
	
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="mother_step_children",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends MotherStepChildren> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 7831556271646916577l;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="step_child"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="step_child_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue stepChild;
	@Index(columnNames="step_mother_id",name="idx_mother_step_children_step_mother_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="step_mother_id",nullable=true)
	protected Mother stepMother;
	private String uid;

	/** Constructor for MotherStepChildren
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public MotherStepChildren(Mother end1, StepChild end2) {
		this.z_internalAddToStepMother(end1);
		this.z_internalAddToStepChild(end2);
	}
	
	/** Constructor for MotherStepChildren
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public MotherStepChildren(StepChild end2, Mother end1) {
		this.z_internalAddToStepMother(end1);
		this.z_internalAddToStepChild(end2);
	}
	
	/** Default constructor for MotherStepChildren
	 */
	public MotherStepChildren() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends MotherStepChildren> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.MotherStepChildren.class));
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
		this.z_internalRemoveFromStepChild(getStepChild());
		this.z_internalRemoveFromStepMother(getStepMother());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof MotherStepChildren ) {
			return other==this || ((MotherStepChildren)other).getUid().equals(this.getUid());
		}
		return false;
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
		return "MotherStepChildren["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getStepChild();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6473770983472549223l,opposite="motherStepChildren_stepMother",uuid="Structures.uml@_ncZyUI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw@Structures.uml@_ncZyUI1OEeKgGLBcRSZFfw")
	public StepChild getStepChild() {
		StepChild result = null;
		if ( this.stepChild==null ) {
			this.stepChild=new UiidBasedInterfaceValue();
		}
		result=(StepChild)this.stepChild.getValue(persistence);
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6136886502272554435l,opposite="motherStepChildren_stepChild",uuid="Structures.uml@_ncdcsY1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw@Structures.uml@_ncdcsY1OEeKgGLBcRSZFfw")
	public Mother getStepMother() {
		Mother result = this.stepMother;
		
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
		this.z_internalAddToStepChild((StepChild)owner);
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
		return "<MotherStepChildren uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<MotherStepChildren ");
		sb.append("classUuid=\"Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw\" ");
		sb.append("className=\"org.opaeum.test.MotherStepChildren\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</MotherStepChildren>");
		return sb.toString();
	}
	
	public void z_internalAddToStepChild(StepChild stepChild) {
		if ( stepChild.equals(getStepChild()) ) {
			return;
		}
		if ( this.stepChild==null ) {
			this.stepChild=new UiidBasedInterfaceValue();
		}
		this.stepChild.setValue(stepChild);
	}
	
	public void z_internalAddToStepMother(Mother stepMother) {
		if ( stepMother.equals(getStepMother()) ) {
			return;
		}
		this.stepMother=stepMother;
	}
	
	public void z_internalRemoveFromStepChild(StepChild stepChild) {
		if ( getStepChild()!=null && stepChild!=null && stepChild.equals(getStepChild()) ) {
			this.stepChild.setValue(null);
		}
	}
	
	public void z_internalRemoveFromStepMother(Mother stepMother) {
		if ( getStepMother()!=null && stepMother!=null && stepMother.equals(getStepMother()) ) {
			this.stepMother=null;
			this.stepMother=null;
		}
	}

}