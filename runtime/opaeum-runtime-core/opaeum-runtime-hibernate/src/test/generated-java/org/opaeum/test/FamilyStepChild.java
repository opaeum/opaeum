package org.opaeum.test;

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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Index;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.hibernate.domain.UiidBasedCascadingInterfaceValue;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="family_step_child",uniqueConstraints=
	@UniqueConstraint(columnNames={"step_child","step_child_type","deleted_on"}))
@Entity(name="FamilyStepChild")
public class FamilyStepChild implements IPersistentObject, HibernateEntity, CompositionNode, Serializable {
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@Index(columnNames="family_id",name="idx_family_step_child_family_id")
	@ManyToOne(fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="family_id",nullable=true)
	protected Family family;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="family_step_child",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends FamilyStepChild> mockedAllInstances;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 2159944486739790089l;
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@Embedded
	@AttributeOverrides(	{
		@AttributeOverride(column=
			@Column(name="step_child"),name="identifier"),
		@AttributeOverride(column=
			@Column(name="step_child_type"),name="classIdentifier")})
	protected UiidBasedCascadingInterfaceValue stepChild;
	private String uid;
	private String z_keyOfStepChildOnFamily;

	/** Constructor for FamilyStepChild
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public FamilyStepChild(Family end1, StepChild end2) {
		this.z_internalAddToFamily(end1);
		this.z_internalAddToStepChild(end2);
	}
	
	/** Constructor for FamilyStepChild
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public FamilyStepChild(StepChild end2, Family end1) {
		this.z_internalAddToFamily(end1);
		this.z_internalAddToStepChild(end2);
	}
	
	/** Default constructor for FamilyStepChild
	 */
	public FamilyStepChild() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	static public Set<? extends FamilyStepChild> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.FamilyStepChild.class));
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("stepChild") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3026107929571144029")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						StepChild curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToStepChild(curVal);
						curVal.z_internalAddToFamilyStepChild_family(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clear() {
		this.z_internalRemoveFromStepChild(getStepChild());
		this.z_internalRemoveFromFamily(getFamily());
		markDeleted();
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof FamilyStepChild ) {
			return other==this || ((FamilyStepChild)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8765669249047247797l,opposite="familyStepChild_stepChild",uuid="Structures.uml@_0vhRgYlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg@Structures.uml@_0vhRgYlZEeKhILqZBrW9Hg")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return "FamilyStepChild["+getId()+"]";
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3026107929571144029l,opposite="familyStepChild_family",uuid="Structures.uml@_0vgqcIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg@Structures.uml@_0vgqcIlZEeKhILqZBrW9Hg")
	public StepChild getStepChild() {
		StepChild result = null;
		if ( this.stepChild==null ) {
			this.stepChild=new UiidBasedCascadingInterfaceValue();
		}
		result=(StepChild)this.stepChild.getValue(persistence);
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfStepChildOnFamily() {
		return this.z_keyOfStepChildOnFamily;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToFamily((Family)owner);
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("stepChild") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3026107929571144029")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((StepChild)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
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
	
	public void setZ_keyOfStepChildOnFamily(String z_keyOfStepChildOnFamily) {
		this.z_keyOfStepChildOnFamily=z_keyOfStepChildOnFamily;
	}
	
	public String toXmlReferenceString() {
		return "<FamilyStepChild uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<FamilyStepChild ");
		sb.append("classUuid=\"Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg\" ");
		sb.append("className=\"org.opaeum.test.FamilyStepChild\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getStepChild()==null ) {
			sb.append("\n<stepChild/>");
		} else {
			sb.append("\n<stepChild propertyId=\"3026107929571144029\">");
			sb.append("\n" + getStepChild().toXmlString());
			sb.append("\n</stepChild>");
		}
		sb.append("\n</FamilyStepChild>");
		return sb.toString();
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToStepChild(StepChild stepChild) {
		if ( stepChild.equals(getStepChild()) ) {
			return;
		}
		if ( this.stepChild==null ) {
			this.stepChild=new UiidBasedCascadingInterfaceValue();
		}
		this.stepChild.setValue(stepChild);
	}
	
	public void z_internalRemoveFromFamily(Family family) {
		if ( getFamily()!=null && family!=null && family.equals(getFamily()) ) {
			this.family=null;
			this.family=null;
		}
	}
	
	public void z_internalRemoveFromStepChild(StepChild stepChild) {
		if ( getStepChild()!=null && stepChild!=null && stepChild.equals(getStepChild()) ) {
			this.stepChild.setValue(null);
		}
	}

}