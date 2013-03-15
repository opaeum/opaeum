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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_TL7NoIhqEeK4s7QGypAJBA")
public class Family implements IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Map<String, Child> child = new HashMap<String,Child>();
	protected Map<String, FamilyStepChild> familyStepChild_stepChild = new HashMap<String,FamilyStepChild>();
	protected Father father;
	protected Mother mother;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 1554804427498750475l;
	private String uid;

	/** Default constructor for Family
	 */
	public Family() {
	}

	public void addAllToChild(Set<Child> child) {
		for ( Child o : child ) {
			addToChild(o.getName(),o);
		}
	}
	
	public void addAllToStepChild(Set<StepChild> stepChild) {
		for ( StepChild o : stepChild ) {
			addToStepChild(o.getName(),o);
		}
	}
	
	public void addToChild(String name, Child child) {
		if ( child!=null ) {
			if ( child.getFamily()!=null ) {
				child.getFamily().removeFromChild(child.getName(),child);
			}
			child.z_internalAddToFamily(this);
		}
		z_internalAddToChild(name,child);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void addToStepChild(String name, StepChild stepChild) {
		if ( stepChild!=null && !this.getStepChild().contains(stepChild) ) {
			FamilyStepChild newLink = new FamilyStepChild((Family)this,(StepChild)stepChild);
			if ( stepChild.getFamily()!=null ) {
				stepChild.getFamily().removeFromStepChild(stepChild.getName(),stepChild);
			}
			this.z_internalAddToFamilyStepChild_stepChild(name,newLink);
			stepChild.z_internalAddToFamilyStepChild_family(newLink);
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("mother") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4168537961309924917")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Mother curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToMother(curVal);
						curVal.z_internalAddToFamily(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1669943078079397905")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Child curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToChild(curVal.getName(),curVal);
						curVal.z_internalAddToFamily(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("father") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2295318488590672432")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Father curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToFather(curVal);
						curVal.z_internalAddToFamily(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyStepChild_stepChild") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5583823813129990661")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyStepChild curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToFamilyStepChild_stepChild(curVal.getStepChild().getName(),curVal);
						curVal.z_internalAddToFamily(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearChild() {
		Set<Child> tmp = new HashSet<Child>(getChild());
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o);
		}
	}
	
	public void clearStepChild() {
		Set<StepChild> tmp = new HashSet<StepChild>(getStepChild());
		for ( StepChild o : tmp ) {
			removeFromStepChild(o.getName(),o);
		}
	}
	
	public void copyShallowState(Family from, Family to) {
		if ( from.getMother()!=null ) {
			to.z_internalAddToMother(from.getMother().makeShallowCopy());
		}
		if ( from.getFather()!=null ) {
			to.z_internalAddToFather(from.getFather().makeShallowCopy());
		}
	}
	
	public void copyState(Family from, Family to) {
		if ( from.getMother()!=null ) {
			to.z_internalAddToMother(from.getMother().makeCopy());
		}
		for ( Child child : from.getChild() ) {
			to.addToChild(child.getName(),child.makeCopy());
		}
		if ( from.getFather()!=null ) {
			to.z_internalAddToFather(from.getFather().makeCopy());
		}
	}
	
	public Child createChild(String name) {
		Child newInstance= new Child();
		newInstance.setName(name);
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		if ( getMother()==null ) {
			setMother(new Mother());
		}
		if ( getFather()==null ) {
			setFather(new Father());
		}
	}
	
	public Father createFather() {
		Father newInstance= new Father();
		newInstance.init(this);
		return newInstance;
	}
	
	public Mother createMother() {
		Mother newInstance= new Mother();
		newInstance.init(this);
		return newInstance;
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Family ) {
			return other==this || ((Family)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public Child getChild(String name) {
		Child result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.child.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1669943078079397905l,opposite="family",uuid="Structures.uml@_lw1TQIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_lw1TQIhqEeK4s7QGypAJBA")
	public Set<? extends Child> getChild() {
		Set result = new HashSet<Child>(this.child.values());
		
		return result;
	}
	
	public FamilyMember getFamilyMember(String name) {
		FamilyMember result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		for ( FamilyMember elem : getFamilyMember() ) {
			if ( elem.getName()!=null && elem.getName().equals(name)  ) {
				result=elem;
				break;
			}
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6185468265608250139l,opposite="family",uuid="Structures.uml@_N7V4EI08EeKHBNiW4NWnIg")
	public Set<? extends FamilyMember> getFamilyMember() {
		Set result = new HashSet<FamilyMember>();
		result.addAll(this.getChild());
		return result;
	}
	
	public FamilyStepChild getFamilyStepChild_stepChild(String name) {
		FamilyStepChild result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		result=this.familyStepChild_stepChild.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=5583823813129990661l,opposite="family",uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_TL7NoIhqEeK4s7QGypAJBA@Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	public Set<FamilyStepChild> getFamilyStepChild_stepChild() {
		Set result = new HashSet<FamilyStepChild>(this.familyStepChild_stepChild.values());
		
		return result;
	}
	
	public FamilyStepChild getFamilyStepChild_stepChildFor(StepChild match) {
		for ( FamilyStepChild var : getFamilyStepChild_stepChild() ) {
			if ( var.getStepChild().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2295318488590672432l,opposite="family",uuid="Structures.uml@_u4dQMIiYEeKb2pFQKBBPKw")
	@NumlMetaInfo(uuid="Structures.uml@_u4dQMIiYEeKb2pFQKBBPKw")
	public Father getFather() {
		Father result = this.father;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=4168537961309924917l,opposite="family",uuid="Structures.uml@_lidgEIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_lidgEIhqEeK4s7QGypAJBA")
	public Mother getMother() {
		Mother result = this.mother;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public StepChild getStepChild(String name) {
		StepChild result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		FamilyStepChild link = this.familyStepChild_stepChild.get(key.toString());
		result= link==null || link.getStepChild()==null?null:link.getStepChild();
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7090279815210814161l,opposite="family",uuid="Structures.uml@_0vgqcIlZEeKhILqZBrW9Hg")
	public Set<StepChild> getStepChild() {
		Set result = new HashSet<StepChild>();
		for ( FamilyStepChild cur : this.getFamilyStepChild_stepChild() ) {
			result.add(cur.getStepChild());
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
	}
	
	public Family makeCopy() {
		Family result = new Family();
		copyState((Family)this,result);
		return result;
	}
	
	public Family makeShallowCopy() {
		Family result = new Family();
		copyShallowState((Family)this,result);
		return result;
	}
	
	public void markDeleted() {
		if ( getMother()!=null ) {
			getMother().markDeleted();
		}
		for ( Child child : new ArrayList<Child>(getChild()) ) {
			child.markDeleted();
		}
		if ( getFather()!=null ) {
			getFather().markDeleted();
		}
		for ( StepChild child : new ArrayList<StepChild>(getStepChild()) ) {
			child.markDeleted();
		}
		for ( FamilyStepChild child : new ArrayList<FamilyStepChild>(getFamilyStepChild_stepChild()) ) {
			child.markDeleted();
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("mother") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("4168537961309924917")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Mother)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1669943078079397905")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Child)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("father") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("2295318488590672432")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((Father)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyStepChild_stepChild") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5583823813129990661")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyStepChild familyStepChild_stepChild = (FamilyStepChild)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( familyStepChild_stepChild!=null ) {
							z_internalAddToFamilyStepChild_stepChild(familyStepChild_stepChild.getStepChild().getName(),familyStepChild_stepChild);
							familyStepChild_stepChild.z_internalAddToFamily(this);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromChild(Set<Child> child) {
		Set<Child> tmp = new HashSet<Child>(child);
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o);
		}
	}
	
	public void removeAllFromStepChild(Set<StepChild> stepChild) {
		Set<StepChild> tmp = new HashSet<StepChild>(stepChild);
		for ( StepChild o : tmp ) {
			removeFromStepChild(o.getName(),o);
		}
	}
	
	public void removeFromChild(String name, Child child) {
		if ( child!=null ) {
			child.z_internalRemoveFromFamily(this);
			z_internalRemoveFromChild(child.getName(),child);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromStepChild(String name, StepChild stepChild) {
		if ( stepChild!=null ) {
			FamilyStepChild oldLink = getFamilyStepChild_stepChildFor(stepChild);
			if ( oldLink!=null ) {
				stepChild.z_internalRemoveFromFamilyStepChild_family(oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyStepChild_stepChild(stepChild.getName(),oldLink);
			}
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setChild(Set<Child> child) {
		this.clearChild();
		this.addAllToChild(child);
	}
	
	public void setFather(Father father) {
		Father oldValue = this.getFather();
		if ( oldValue==null ) {
			if ( father!=null ) {
				Family oldOther = (Family)father.getFamily();
				father.z_internalRemoveFromFamily(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromFather(father);
				}
				father.z_internalAddToFamily((Family)this);
			}
			this.z_internalAddToFather(father);
		} else {
			if ( !oldValue.equals(father) ) {
				oldValue.z_internalRemoveFromFamily(this);
				z_internalRemoveFromFather(oldValue);
				if ( father!=null ) {
					Family oldOther = (Family)father.getFamily();
					father.z_internalRemoveFromFamily(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromFather(father);
					}
					father.z_internalAddToFamily((Family)this);
				}
				this.z_internalAddToFather(father);
			}
		}
	}
	
	public void setMother(Mother mother) {
		Mother oldValue = this.getMother();
		if ( oldValue==null ) {
			if ( mother!=null ) {
				Family oldOther = (Family)mother.getFamily();
				mother.z_internalRemoveFromFamily(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromMother(mother);
				}
				mother.z_internalAddToFamily((Family)this);
			}
			this.z_internalAddToMother(mother);
		} else {
			if ( !oldValue.equals(mother) ) {
				oldValue.z_internalRemoveFromFamily(this);
				z_internalRemoveFromMother(oldValue);
				if ( mother!=null ) {
					Family oldOther = (Family)mother.getFamily();
					mother.z_internalRemoveFromFamily(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromMother(mother);
					}
					mother.z_internalAddToFamily((Family)this);
				}
				this.z_internalAddToMother(mother);
			}
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setStepChild(Set<StepChild> stepChild) {
		this.clearStepChild();
		this.addAllToStepChild(stepChild);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Family uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Family ");
		sb.append("classUuid=\"Structures.uml@_TL7NoIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Family\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getMother()==null ) {
			sb.append("\n<mother/>");
		} else {
			sb.append("\n<mother propertyId=\"4168537961309924917\">");
			sb.append("\n" + getMother().toXmlString());
			sb.append("\n</mother>");
		}
		sb.append("\n<child propertyId=\"1669943078079397905\">");
		for ( Child child : getChild() ) {
			sb.append("\n" + child.toXmlString());
		}
		sb.append("\n</child>");
		if ( getFather()==null ) {
			sb.append("\n<father/>");
		} else {
			sb.append("\n<father propertyId=\"2295318488590672432\">");
			sb.append("\n" + getFather().toXmlString());
			sb.append("\n</father>");
		}
		sb.append("\n<familyStepChild_stepChild propertyId=\"5583823813129990661\">");
		for ( FamilyStepChild familyStepChild_stepChild : getFamilyStepChild_stepChild() ) {
			sb.append("\n" + familyStepChild_stepChild.toXmlString());
		}
		sb.append("\n</familyStepChild_stepChild>");
		sb.append("\n</Family>");
		return sb.toString();
	}
	
	public void z_internalAddToChild(String name, Child child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getChild().contains(child) ) {
			return;
		}
		child.z_internalAddToName(name);
		this.child.put(key.toString(),child);
		child.setZ_keyOfChildOnFamily(key.toString());
	}
	
	public void z_internalAddToFamilyStepChild_stepChild(String name, FamilyStepChild familyStepChild_stepChild) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		if ( getFamilyStepChild_stepChild().contains(familyStepChild_stepChild) ) {
			return;
		}
		familyStepChild_stepChild.getStepChild().z_internalAddToName(name);
		this.familyStepChild_stepChild.put(key.toString(),familyStepChild_stepChild);
		familyStepChild_stepChild.setZ_keyOfStepChildOnFamily(key.toString());
	}
	
	public void z_internalAddToFather(Father father) {
		if ( father.equals(getFather()) ) {
			return;
		}
		this.father=father;
	}
	
	public void z_internalAddToMother(Mother mother) {
		if ( mother.equals(getMother()) ) {
			return;
		}
		this.mother=mother;
	}
	
	public void z_internalRemoveFromChild(String name, Child child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.child.remove(key.toString());
	}
	
	public void z_internalRemoveFromFamilyStepChild_stepChild(String name, FamilyStepChild familyStepChild_stepChild) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name);
		this.familyStepChild_stepChild.remove(key.toString());
	}
	
	public void z_internalRemoveFromFather(Father father) {
		if ( getFather()!=null && father!=null && father.equals(getFather()) ) {
			this.father=null;
			this.father=null;
		}
	}
	
	public void z_internalRemoveFromMother(Mother mother) {
		if ( getMother()!=null && mother!=null && mother.equals(getMother()) ) {
			this.mother=null;
			this.mother=null;
		}
	}

}