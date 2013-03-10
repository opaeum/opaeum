package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_edqnoIhqEeK4s7QGypAJBA")
public class Aunt implements Relation, IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Map<String, ChildHasRelation> childHasRelation_child = new HashMap<String,ChildHasRelation>();
	protected Date dateOfBirth;
	protected Set<FamilyMemberHasRelation> familyMemberHasRelation_familyMember = new HashSet<FamilyMemberHasRelation>();
	protected String firstName;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 5674612196249131985l;
	protected String surname;
	private String uid;

	/** Default constructor for Aunt
	 */
	public Aunt() {
	}

	public void addAllToChild(Set<Child> child) {
		for ( Child o : child ) {
			addToChild(o.getName(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToFamilyMember(Set<FamilyMember> familyMember) {
		for ( FamilyMember o : familyMember ) {
			addToFamilyMember(o);
		}
	}
	
	public void addToChild(String name, Date dateOfBirth, Child child) {
		if ( child!=null && !this.getChild().contains(child) ) {
			ChildHasRelation newLink = new ChildHasRelation((Relation)this,(Child)child);
			if ( getFirstName()==null ) {
				throw new IllegalStateException("The qualifying property 'firstName' must be set before adding a value to 'child'");
			}
			if ( getSurname()==null ) {
				throw new IllegalStateException("The qualifying property 'surname' must be set before adding a value to 'child'");
			}
			if ( getDateOfBirth()==null ) {
				throw new IllegalStateException("The qualifying property 'dateOfBirth' must be set before adding a value to 'child'");
			}
			this.z_internalAddToChildHasRelation_child(name,dateOfBirth,newLink);
			child.z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),newLink);
		}
	}
	
	public void addToFamilyMember(FamilyMember familyMember) {
		if ( familyMember!=null && !this.getFamilyMember().contains(familyMember) ) {
			FamilyMemberHasRelation newLink = new FamilyMemberHasRelation((Relation)this,(FamilyMember)familyMember);
			if ( getFirstName()==null ) {
				throw new IllegalStateException("The qualifying property 'firstName' must be set before adding a value to 'familyMember'");
			}
			if ( getSurname()==null ) {
				throw new IllegalStateException("The qualifying property 'surname' must be set before adding a value to 'familyMember'");
			}
			if ( getDateOfBirth()==null ) {
				throw new IllegalStateException("The qualifying property 'dateOfBirth' must be set before adding a value to 'familyMember'");
			}
			this.z_internalAddToFamilyMemberHasRelation_familyMember(newLink);
			familyMember.z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),newLink);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("firstName").length()>0 ) {
			setFirstName(ModelFormatter.getInstance().parseString(xml.getAttribute("firstName")));
		}
		if ( xml.getAttribute("surname").length()>0 ) {
			setSurname(ModelFormatter.getInstance().parseString(xml.getAttribute("surname")));
		}
		if ( xml.getAttribute("dateOfBirth").length()>0 ) {
			setDateOfBirth(ModelFormatter.getInstance().parseDate(xml.getAttribute("dateOfBirth")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("childHasRelation_child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1488854094798314249")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ChildHasRelation curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToChildHasRelation_child(curVal.getChild().getName(),curVal.getChild().getDateOfBirth(),curVal);
						curVal.z_internalAddToGodParent(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_familyMember") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3903606770219243395")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyMemberHasRelation curVal;
						try {
							curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute("className"));
						} catch (Exception e) {
							curVal=org.opaeum.test.util.ModelJavaMetaInfoMap.INSTANCE.newInstance(((Element)currentPropertyValueNode).getAttribute("classUuid"));
						}
						curVal.buildTreeFromXml((Element)currentPropertyValueNode,map);
						this.z_internalAddToFamilyMemberHasRelation_familyMember(curVal);
						curVal.z_internalAddToRelation(this);
						map.put(curVal.getUid(), curVal);
					}
				}
			}
		}
	}
	
	public void clearChild() {
		Set<Child> tmp = new HashSet<Child>(getChild());
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearFamilyMember() {
		Set<FamilyMember> tmp = new HashSet<FamilyMember>(getFamilyMember());
		for ( FamilyMember o : tmp ) {
			removeFromFamilyMember(o);
		}
	}
	
	public void copyShallowState(Aunt from, Aunt to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void copyState(Aunt from, Aunt to) {
		to.setFirstName(from.getFirstName());
		to.setSurname(from.getSurname());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Aunt ) {
			return other==this || ((Aunt)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=984124978325014811l,opposite="godParent",uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	public Set<Child> getChild() {
		Set result = new HashSet<Child>();
		for ( ChildHasRelation cur : this.getChildHasRelation_child() ) {
			result.add(cur.getChild());
		}
		return result;
	}
	
	public ChildHasRelation getChildHasRelation_child(String name, Date dateOfBirth) {
		ChildHasRelation result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		result=this.childHasRelation_child.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=1488854094798314249l,opposite="godParent",uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA@Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	public Set<ChildHasRelation> getChildHasRelation_child() {
		Set result = new HashSet<ChildHasRelation>(this.childHasRelation_child.values());
		
		return result;
	}
	
	public ChildHasRelation getChildHasRelation_childFor(Child match) {
		for ( ChildHasRelation var : getChildHasRelation_child() ) {
			if ( var.getChild().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4576801132852053498l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	public Date getDateOfBirth() {
		Date result = this.dateOfBirth;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1729227443467658447l,opposite="relation",uuid="Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
	public Set<FamilyMember> getFamilyMember() {
		Set result = new HashSet<FamilyMember>();
		for ( FamilyMemberHasRelation cur : this.getFamilyMemberHasRelation_familyMember() ) {
			result.add(cur.getFamilyMember());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3903606770219243395l,opposite="relation",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_familyMember() {
		Set result = this.familyMemberHasRelation_familyMember;
		
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_familyMemberFor(FamilyMember match) {
		for ( FamilyMemberHasRelation var : getFamilyMemberHasRelation_familyMember() ) {
			if ( var.getFamilyMember().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4472055897408457748l,uuid="Structures.uml@_ojWi8IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_ojWi8IjSEeKq68owPnlvHg")
	public String getFirstName() {
		String result = this.firstName;
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		return this.outgoingEvents;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7182092940400916328l,uuid="Structures.uml@_rPn78IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_rPn78IjSEeKq68owPnlvHg")
	public String getSurname() {
		String result = this.surname;
		
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
	
	public Aunt makeCopy() {
		Aunt result = new Aunt();
		copyState((Aunt)this,result);
		return result;
	}
	
	public Aunt makeShallowCopy() {
		Aunt result = new Aunt();
		copyShallowState((Aunt)this,result);
		return result;
	}
	
	public void markDeleted() {
		removeAllFromChild(getChild());
		removeAllFromFamilyMember(getFamilyMember());
		for ( ChildHasRelation child : new ArrayList<ChildHasRelation>(getChildHasRelation_child()) ) {
			child.markDeleted();
		}
		for ( FamilyMemberHasRelation child : new ArrayList<FamilyMemberHasRelation>(getFamilyMemberHasRelation_familyMember()) ) {
			child.markDeleted();
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("childHasRelation_child") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("1488854094798314249")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((ChildHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_familyMember") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("3903606770219243395")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						((FamilyMemberHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"))).populateReferencesFromXml((Element)currentPropertyValueNode, map);
					}
				}
			}
		}
	}
	
	public void removeAllFromChild(Set<Child> child) {
		Set<Child> tmp = new HashSet<Child>(child);
		for ( Child o : tmp ) {
			removeFromChild(o.getName(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromFamilyMember(Set<FamilyMember> familyMember) {
		Set<FamilyMember> tmp = new HashSet<FamilyMember>(familyMember);
		for ( FamilyMember o : tmp ) {
			removeFromFamilyMember(o);
		}
	}
	
	public void removeFromChild(String name, Date dateOfBirth, Child child) {
		if ( child!=null ) {
			ChildHasRelation oldLink = getChildHasRelation_childFor(child);
			if ( oldLink!=null ) {
				child.z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromChildHasRelation_child(child.getName(),child.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void removeFromFamilyMember(FamilyMember familyMember) {
		if ( familyMember!=null ) {
			FamilyMemberHasRelation oldLink = getFamilyMemberHasRelation_familyMemberFor(familyMember);
			if ( oldLink!=null ) {
				familyMember.z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyMemberHasRelation_familyMember(oldLink);
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setChild(Set<Child> child) {
		this.clearChild();
		this.addAllToChild(child);
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( dateOfBirth == null ) {
			this.z_internalRemoveFromDateOfBirth(getDateOfBirth());
		} else {
			this.z_internalAddToDateOfBirth(dateOfBirth);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setFamilyMember(Set<FamilyMember> familyMember) {
		this.clearFamilyMember();
		this.addAllToFamilyMember(familyMember);
	}
	
	public void setFirstName(String firstName) {
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( firstName == null ) {
			this.z_internalRemoveFromFirstName(getFirstName());
		} else {
			this.z_internalAddToFirstName(firstName);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setSurname(String surname) {
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalRemoveFromChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalRemoveFromFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		if ( surname == null ) {
			this.z_internalRemoveFromSurname(getSurname());
		} else {
			this.z_internalAddToSurname(surname);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_child() ) {
			curVal.getChild().z_internalAddToChildHasRelation_godParent(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
		for ( FamilyMemberHasRelation curVal : getFamilyMemberHasRelation_familyMember() ) {
			curVal.getFamilyMember().z_internalAddToFamilyMemberHasRelation_relation(this.getFirstName(),this.getSurname(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public String toXmlReferenceString() {
		return "<Aunt uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Aunt ");
		sb.append("classUuid=\"Structures.uml@_edqnoIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Aunt\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getFirstName()!=null ) {
			sb.append("firstName=\""+ ModelFormatter.getInstance().formatString(getFirstName())+"\" ");
		}
		if ( getSurname()!=null ) {
			sb.append("surname=\""+ ModelFormatter.getInstance().formatString(getSurname())+"\" ");
		}
		if ( getDateOfBirth()!=null ) {
			sb.append("dateOfBirth=\""+ ModelFormatter.getInstance().formatDate(getDateOfBirth())+"\" ");
		}
		sb.append(">");
		sb.append("\n<childHasRelation_child propertyId=\"1488854094798314249\">");
		for ( ChildHasRelation childHasRelation_child : getChildHasRelation_child() ) {
			sb.append("\n" + childHasRelation_child.toXmlString());
		}
		sb.append("\n</childHasRelation_child>");
		sb.append("\n<familyMemberHasRelation_familyMember propertyId=\"3903606770219243395\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_familyMember : getFamilyMemberHasRelation_familyMember() ) {
			sb.append("\n" + familyMemberHasRelation_familyMember.toXmlString());
		}
		sb.append("\n</familyMemberHasRelation_familyMember>");
		sb.append("\n</Aunt>");
		return sb.toString();
	}
	
	public void z_internalAddToChildHasRelation_child(String name, Date dateOfBirth, ChildHasRelation childHasRelation_child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		if ( getChildHasRelation_child().contains(childHasRelation_child) ) {
			return;
		}
		childHasRelation_child.getChild().z_internalAddToName(name);
		childHasRelation_child.getChild().z_internalAddToDateOfBirth(dateOfBirth);
		this.childHasRelation_child.put(key.toString(),childHasRelation_child);
		childHasRelation_child.setZ_keyOfChildOnRelation(key.toString());
	}
	
	public void z_internalAddToDateOfBirth(Date dateOfBirth) {
		if ( dateOfBirth.equals(getDateOfBirth()) ) {
			return;
		}
		this.dateOfBirth=dateOfBirth;
	}
	
	public void z_internalAddToFamilyMemberHasRelation_familyMember(FamilyMemberHasRelation familyMemberHasRelation_familyMember) {
		if ( getFamilyMemberHasRelation_familyMember().contains(familyMemberHasRelation_familyMember) ) {
			return;
		}
		this.familyMemberHasRelation_familyMember.add(familyMemberHasRelation_familyMember);
	}
	
	public void z_internalAddToFirstName(String firstName) {
		if ( firstName.equals(getFirstName()) ) {
			return;
		}
		this.firstName=firstName;
	}
	
	public void z_internalAddToSurname(String surname) {
		if ( surname.equals(getSurname()) ) {
			return;
		}
		this.surname=surname;
	}
	
	public void z_internalRemoveFromChildHasRelation_child(String name, Date dateOfBirth, ChildHasRelation childHasRelation_child) {
		String key = ModelFormatter.getInstance().formatStringQualifier(name)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		this.childHasRelation_child.remove(key.toString());
	}
	
	public void z_internalRemoveFromDateOfBirth(Date dateOfBirth) {
		if ( getDateOfBirth()!=null && dateOfBirth!=null && dateOfBirth.equals(getDateOfBirth()) ) {
			this.dateOfBirth=null;
			this.dateOfBirth=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMemberHasRelation_familyMember(FamilyMemberHasRelation familyMemberHasRelation_familyMember) {
		this.familyMemberHasRelation_familyMember.remove(familyMemberHasRelation_familyMember);
	}
	
	public void z_internalRemoveFromFirstName(String firstName) {
		if ( getFirstName()!=null && firstName!=null && firstName.equals(getFirstName()) ) {
			this.firstName=null;
			this.firstName=null;
		}
	}
	
	public void z_internalRemoveFromSurname(String surname) {
		if ( getSurname()!=null && surname!=null && surname.equals(getSurname()) ) {
			this.surname=null;
			this.surname=null;
		}
	}

}