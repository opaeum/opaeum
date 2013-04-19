package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.AccessType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.Where;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.hibernate.domain.InternalHibernatePersistence;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.AbstractPersistence;
import org.opaeum.test.util.ModelFormatter;
import org.opaeum.test.util.Stdlib;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_qIwuMIlZEeKhILqZBrW9Hg")
@Filter(name="noDeletedObjects")
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@AccessType(	"field")
@Table(name="step_brother")
@Entity(name="StepBrother")
public class StepBrother implements StepChild, IPersistentObject, IEventGenerator, HibernateEntity, CompositionNode, Serializable {
	@Transient
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
		// Initialise to 1000 from 1970
	@Temporal(	javax.persistence.TemporalType.TIMESTAMP)
	@Column(name="deleted_on")
	private Date deletedOn = Stdlib.FUTURE;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="family_step_child_family_id",nullable=true)
	protected FamilyStepChild familyStepChild_family;
	@TableGenerator(allocationSize=20,name="id_generator",pkColumnName="type",pkColumnValue="step_brother",table="hi_value")
	@Id
	@GeneratedValue(generator="id_generator",strategy=javax.persistence.GenerationType.TABLE)
	private Long id;
	static private Set<? extends StepBrother> mockedAllInstances;
	@OneToOne(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY)
	@JoinColumn(name="mother_step_children_step_mother_id",nullable=true)
	protected MotherStepChildren motherStepChildren_stepMother;
	@Column(name="name")
	@Basic
	protected String name;
	@Version
	@Column(name="object_version")
	private int objectVersion;
	@Transient
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	@Transient
	private InternalHibernatePersistence persistence;
	static final private long serialVersionUID = 3737954068926204439l;
	@Where(clause="step_sibling1_type='Structures.uml@_qIwuMIlZEeKhILqZBrW9Hg'")
	@LazyCollection(	org.hibernate.annotations.LazyCollectionOption.TRUE)
	@Filter(condition="deleted_on > current_timestamp",name="noDeletedObjects")
	@OneToMany(cascade=javax.persistence.CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,targetEntity=SiblingStepSibling.class)
	@JoinColumn(name="step_sibling1",nullable=true)
	protected Set<SiblingStepSibling> siblingStepSibling_stepSibling = new HashSet<SiblingStepSibling>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public StepBrother(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for StepBrother
	 */
	public StepBrother() {
	}

	public void addAllToStepSibling(Set<Child> stepSibling) {
		for ( Child o : stepSibling ) {
			addToStepSibling(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		if ( getName()==null ) {
			throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
		}
		getFamily().z_internalAddToFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
	}
	
	public void addToStepSibling(Child stepSibling) {
		if ( stepSibling!=null && !this.getStepSibling().contains(stepSibling) ) {
			SiblingStepSibling newLink = new SiblingStepSibling((StepChild)this,(Child)stepSibling);
			this.z_internalAddToSiblingStepSibling_stepSibling(newLink);
			stepSibling.z_internalAddToSiblingStepSibling_stepSibling(newLink);
		}
	}
	
	static public Set<? extends StepBrother> allInstances(AbstractPersistence persistence) {
		if ( mockedAllInstances==null ) {
			return new HashSet(persistence.readAll(org.opaeum.test.StepBrother.class));
		} else {
			return mockedAllInstances;
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(ModelFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("siblingStepSibling_stepSibling") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("844736339179565915")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						SiblingStepSibling curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToSiblingStepSibling_stepSibling(curVal);
						curVal.z_internalAddToStepSibling1(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("motherStepChildren_stepMother") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2474112361200246321")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						MotherStepChildren curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToMotherStepChildren_stepMother(curVal);
						curVal.z_internalAddToStepChild(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearStepSibling() {
		Set<Child> tmp = new HashSet<Child>(getStepSibling());
		for ( Child o : tmp ) {
			removeFromStepSibling(o);
		}
	}
	
	public void copyShallowState(StepBrother from, StepBrother to) {
		to.setName(from.getName());
	}
	
	public void copyState(StepBrother from, StepBrother to) {
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof StepBrother ) {
			return other==this || ((StepBrother)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5616902939022633687l,opposite="stepChild",uuid="Structures.uml@_0vhRgYlZEeKhILqZBrW9Hg")
	public Family getFamily() {
		Family result = null;
		if ( this.familyStepChild_family!=null ) {
			result = this.familyStepChild_family.getFamily();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7517388397186460345l,opposite="stepChild",uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	public FamilyStepChild getFamilyStepChild_family() {
		FamilyStepChild result = this.familyStepChild_family;
		
		return result;
	}
	
	public FamilyStepChild getFamilyStepChild_familyFor(Family match) {
		if ( familyStepChild_family.getFamily().equals(match) ) {
			return familyStepChild_family;
		} else {
			return null;
		}
	}
	
	public Long getId() {
		return this.id;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2474112361200246321l,opposite="stepChild",uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
	public MotherStepChildren getMotherStepChildren_stepMother() {
		MotherStepChildren result = this.motherStepChildren_stepMother;
		
		return result;
	}
	
	public MotherStepChildren getMotherStepChildren_stepMotherFor(Mother match) {
		if ( motherStepChildren_stepMother.getStepMother().equals(match) ) {
			return motherStepChildren_stepMother;
		} else {
			return null;
		}
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6393952343144368733l,uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	public String getName() {
		String result = this.name;
		
		return result;
	}
	
	public int getObjectVersion() {
		return this.objectVersion;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return getFamily();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=844736339179565915l,opposite="stepSibling1",uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
	public Set<SiblingStepSibling> getSiblingStepSibling_stepSibling() {
		Set result = this.siblingStepSibling_stepSibling;
		
		return result;
	}
	
	public SiblingStepSibling getSiblingStepSibling_stepSiblingFor(Child match) {
		for ( SiblingStepSibling var : getSiblingStepSibling_stepSibling() ) {
			if ( var.getStepSibling().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2838340376300211263l,opposite="stepChild",uuid="Structures.uml@_ncdcsY1OEeKgGLBcRSZFfw")
	public Mother getStepMother() {
		Mother result = null;
		if ( this.motherStepChildren_stepMother!=null ) {
			result = this.motherStepChildren_stepMother.getStepMother();
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4467716398320891669l,opposite="stepSibling",uuid="Structures.uml@_1X1ycY1OEeKgGLBcRSZFfw")
	public Set<Child> getStepSibling() {
		Set result = new HashSet<Child>();
		for ( SiblingStepSibling cur : this.getSiblingStepSibling_stepSibling() ) {
			result.add(cur.getStepSibling());
		}
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
		this.z_internalAddToFamilyStepChild_family(new FamilyStepChild((StepChild)this,(Family)owner));
	}
	
	public StepBrother makeCopy() {
		StepBrother result = new StepBrother();
		copyState((StepBrother)this,result);
		return result;
	}
	
	public StepBrother makeShallowCopy() {
		StepBrother result = new StepBrother();
		copyShallowState((StepBrother)this,result);
		result.setId(this.getId());
		return result;
	}
	
	public void markDeleted() {
		setDeletedOn(new Date(System.currentTimeMillis()));
		if ( getFamily()!=null ) {
			FamilyStepChild link = getFamilyStepChild_family();
			link.getFamily().z_internalRemoveFromFamilyStepChild_stepChild(this.getName(),link);
			link.markDeleted();
		}
		for ( SiblingStepSibling link : new HashSet<SiblingStepSibling>(getSiblingStepSibling_stepSibling()) ) {
			link.getStepSibling().z_internalRemoveFromSiblingStepSibling_stepSibling(link);
		}
		if ( getStepMother()!=null ) {
			MotherStepChildren link = getMotherStepChildren_stepMother();
			link.getStepMother().z_internalRemoveFromMotherStepChildren_stepChild(link);
			link.markDeleted();
		}
		if ( getFamilyStepChild_family()!=null ) {
			getFamilyStepChild_family().markDeleted();
		}
		for ( SiblingStepSibling child : new ArrayList<SiblingStepSibling>(getSiblingStepSibling_stepSibling()) ) {
			child.markDeleted();
		}
		if ( getMotherStepChildren_stepMother()!=null ) {
			getMotherStepChildren_stepMother().markDeleted();
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("siblingStepSibling_stepSibling") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("844736339179565915")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((SiblingStepSibling)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("motherStepChildren_stepMother") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2474112361200246321")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((MotherStepChildren)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromStepSibling(Set<Child> stepSibling) {
		Set<Child> tmp = new HashSet<Child>(stepSibling);
		for ( Child o : tmp ) {
			removeFromStepSibling(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromStepSibling(Child stepSibling) {
		if ( stepSibling!=null ) {
			SiblingStepSibling oldLink = getSiblingStepSibling_stepSiblingFor(stepSibling);
			if ( oldLink!=null ) {
				stepSibling.z_internalRemoveFromSiblingStepSibling_stepSibling(oldLink);
				oldLink.clear();
				z_internalRemoveFromSiblingStepSibling_stepSibling(oldLink);
			}
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn=deletedOn;
	}
	
	public void setFamily(Family family) {
		if ( this.getFamily()!=null ) {
			this.getFamily().z_internalRemoveFromFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
		}
		if ( family == null ) {
			this.z_internalRemoveFromFamilyStepChild_family(this.getFamilyStepChild_family());
		} else {
			FamilyStepChild familyStepChild_family = new FamilyStepChild((StepChild)this,(Family)family);
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
			}
			this.z_internalAddToFamilyStepChild_family(familyStepChild_family);
			family.z_internalAddToFamilyStepChild_stepChild(this.getName(),familyStepChild_family);
		}
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public void setName(String name) {
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalRemoveFromFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalAddToFamilyStepChild_stepChild(this.getName(),getFamilyStepChild_family());
		}
	}
	
	public void setObjectVersion(int objectVersion) {
		this.objectVersion=objectVersion;
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setStepMother(Mother stepMother) {
		if ( this.getStepMother()!=null ) {
			this.getStepMother().z_internalRemoveFromMotherStepChildren_stepChild(getMotherStepChildren_stepMother());
		}
		if ( stepMother == null ) {
			this.z_internalRemoveFromMotherStepChildren_stepMother(this.getMotherStepChildren_stepMother());
		} else {
			MotherStepChildren motherStepChildren_stepMother = new MotherStepChildren((StepChild)this,(Mother)stepMother);
			this.z_internalAddToMotherStepChildren_stepMother(motherStepChildren_stepMother);
			stepMother.z_internalAddToMotherStepChildren_stepChild(motherStepChildren_stepMother);
		}
	}
	
	public void setStepSibling(Set<Child> stepSibling) {
		this.clearStepSibling();
		this.addAllToStepSibling(stepSibling);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<StepBrother uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<StepBrother ");
		sb.append("classUuid=\"Structures.uml@_qIwuMIlZEeKhILqZBrW9Hg\" ");
		sb.append("className=\"org.opaeum.test.StepBrother\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<siblingStepSibling_stepSibling propertyId=\"844736339179565915\">");
		for ( SiblingStepSibling siblingStepSibling_stepSibling : getSiblingStepSibling_stepSibling() ) {
			sb.append("\n" + siblingStepSibling_stepSibling.toXmlString());
		}
		sb.append("\n</siblingStepSibling_stepSibling>");
		if ( getMotherStepChildren_stepMother()==null ) {
			sb.append("\n<motherStepChildren_stepMother/>");
		} else {
			sb.append("\n<motherStepChildren_stepMother propertyId=\"2474112361200246321\">");
			sb.append("\n" + getMotherStepChildren_stepMother().toXmlString());
			sb.append("\n</motherStepChildren_stepMother>");
		}
		sb.append("\n</StepBrother>");
		return sb.toString();
	}
	
	public void z_internalAddToFamilyStepChild_family(FamilyStepChild familyStepChild_family) {
		if ( familyStepChild_family.equals(getFamilyStepChild_family()) ) {
			return;
		}
		this.familyStepChild_family=familyStepChild_family;
	}
	
	public void z_internalAddToMotherStepChildren_stepMother(MotherStepChildren motherStepChildren_stepMother) {
		if ( motherStepChildren_stepMother.equals(getMotherStepChildren_stepMother()) ) {
			return;
		}
		this.motherStepChildren_stepMother=motherStepChildren_stepMother;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(getName()) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalAddToSiblingStepSibling_stepSibling(SiblingStepSibling siblingStepSibling_stepSibling) {
		if ( getSiblingStepSibling_stepSibling().contains(siblingStepSibling_stepSibling) ) {
			return;
		}
		this.siblingStepSibling_stepSibling.add(siblingStepSibling_stepSibling);
	}
	
	public void z_internalRemoveFromFamilyStepChild_family(FamilyStepChild familyStepChild_family) {
		if ( getFamilyStepChild_family()!=null && familyStepChild_family!=null && familyStepChild_family.equals(getFamilyStepChild_family()) ) {
			this.familyStepChild_family=null;
			this.familyStepChild_family=null;
		}
	}
	
	public void z_internalRemoveFromMotherStepChildren_stepMother(MotherStepChildren motherStepChildren_stepMother) {
		if ( getMotherStepChildren_stepMother()!=null && motherStepChildren_stepMother!=null && motherStepChildren_stepMother.equals(getMotherStepChildren_stepMother()) ) {
			this.motherStepChildren_stepMother=null;
			this.motherStepChildren_stepMother=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}
	
	public void z_internalRemoveFromSiblingStepSibling_stepSibling(SiblingStepSibling siblingStepSibling_stepSibling) {
		this.siblingStepSibling_stepSibling.remove(siblingStepSibling_stepSibling);
	}

}