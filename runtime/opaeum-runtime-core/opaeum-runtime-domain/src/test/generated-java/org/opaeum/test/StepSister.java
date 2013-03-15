package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_r-BmsIlZEeKhILqZBrW9Hg")
public class StepSister implements StepChild, IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected FamilyStepChild familyStepChild_family;
	protected MotherStepChildren motherStepChildren_stepMother;
	protected String name;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 6013251805696522269l;
	protected Set<SiblingStepSibling> siblingStepSibling_stepSibling = new HashSet<SiblingStepSibling>();
	private String uid;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public StepSister(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for StepSister
	 */
	public StepSister() {
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
	
	public void copyShallowState(StepSister from, StepSister to) {
		to.setName(from.getName());
	}
	
	public void copyState(StepSister from, StepSister to) {
		to.setName(from.getName());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof StepSister ) {
			return other==this || ((StepSister)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
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
	
	public StepSister makeCopy() {
		StepSister result = new StepSister();
		copyState((StepSister)this,result);
		return result;
	}
	
	public StepSister makeShallowCopy() {
		StepSister result = new StepSister();
		copyShallowState((StepSister)this,result);
		return result;
	}
	
	public void markDeleted() {
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
		return "<StepSister uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<StepSister ");
		sb.append("classUuid=\"Structures.uml@_r-BmsIlZEeKhILqZBrW9Hg\" ");
		sb.append("className=\"org.opaeum.test.StepSister\" ");
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
		sb.append("\n</StepSister>");
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