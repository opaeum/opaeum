package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="sibling_step_sibling",uniqueConstraints=
	@UniqueConstraint(columnNames={"step_sibling_id","step_sibling1","step_sibling1_type","deleted_on"}))
@Entity(name="SiblingStepSibling")
public class SiblingStepSibling implements InterfaceValueOwner, IPersistentObject, HibernateEntity, CompositionNode, Serializable {
	static private Map<String, Class> INTERFACE_FIELDS = new HashMap<String,Class>();
	static{
	INTERFACE_FIELDS.put("stepSibling1",StepChild.class);
	}
	
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="sibling_step_sibling",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends SiblingStepSibling> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 6202180249626236171l;
	@Index(columnNames="step_sibling_id",name="idx_sibling_step_sibling_step_sibling_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="step_sibling_id",nullable=true)
	protected Child stepSibling;
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="step_sibling1"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="step_sibling1_type"),name="classIdentifier")})
	protected UiidBasedInterfaceValue stepSibling1;
	private String uid;

	/** Constructor for SiblingStepSibling
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public SiblingStepSibling(Child end1, StepChild end2) {
		this.z_internalAddToStepSibling(end1);
		this.z_internalAddToStepSibling1(end2);
	}
	
	/** Constructor for SiblingStepSibling
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public SiblingStepSibling(StepChild end2, Child end1) {
		this.z_internalAddToStepSibling(end1);
		this.z_internalAddToStepSibling1(end2);
	}
	
	/** Default constructor for SiblingStepSibling
	 */
	public SiblingStepSibling() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends SiblingStepSibling> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.SiblingStepSibling.class));
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
		this.z_internalRemoveFromStepSibling1(getStepSibling1());
		this.z_internalRemoveFromStepSibling(getStepSibling());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof SiblingStepSibling ) {
			return other==this || ((SiblingStepSibling)other).getUid().equals(this.getUid());
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
		return "SiblingStepSibling["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getStepSibling1();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1462536871377234328l,opposite="siblingStepSibling_stepSibling",uuid="Structures.uml@_1X1ycY1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw@Structures.uml@_1X1ycY1OEeKgGLBcRSZFfw")
	public Child getStepSibling() {
		Child result = this.stepSibling;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=192384936427444854l,opposite="siblingStepSibling_stepSibling",uuid="Structures.uml@_1X0kUI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw@Structures.uml@_1X0kUI1OEeKgGLBcRSZFfw")
	public StepChild getStepSibling1() {
		StepChild result = null;
		if ( this.stepSibling1==null ) {
			this.stepSibling1=new UiidBasedInterfaceValue();
		}
		result=(StepChild)this.stepSibling1.getValue(persistence);
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
		this.z_internalAddToStepSibling1((StepChild)owner);
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
		return "<SiblingStepSibling uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<SiblingStepSibling ");
		sb.append("classUuid=\"Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw\" ");
		sb.append("className=\"org.opaeum.test.SiblingStepSibling\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</SiblingStepSibling>");
		return sb.toString();
	}
	
	public void z_internalAddToStepSibling(Child stepSibling) {
		if ( stepSibling.equals(getStepSibling()) ) {
			return;
		}
		this.stepSibling=stepSibling;
	}
	
	public void z_internalAddToStepSibling1(StepChild stepSibling1) {
		if ( stepSibling1.equals(getStepSibling1()) ) {
			return;
		}
		if ( this.stepSibling1==null ) {
			this.stepSibling1=new UiidBasedInterfaceValue();
		}
		this.stepSibling1.setValue(stepSibling1);
	}
	
	public void z_internalRemoveFromStepSibling(Child stepSibling) {
		if ( getStepSibling()!=null && stepSibling!=null && stepSibling.equals(getStepSibling()) ) {
			this.stepSibling=null;
			this.stepSibling=null;
		}
	}
	
	public void z_internalRemoveFromStepSibling1(StepChild stepSibling1) {
		if ( getStepSibling1()!=null && stepSibling1!=null && stepSibling1.equals(getStepSibling1()) ) {
			this.stepSibling1.setValue(null);
		}
	}

}