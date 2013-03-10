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

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_V2hysIhqEeK4s7QGypAJBA")
public class Child implements FamilyMember, IEventGenerator, CompositionNode, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Map<String, ChildHasRelation> childHasRelation_godParent = new HashMap<String,ChildHasRelation>();
	protected Date dateOfBirth;
	protected Family family;
	protected Map<String, FamilyMemberHasRelation> familyMemberHasRelation_relation = new HashMap<String,FamilyMemberHasRelation>();
	protected String name;
	transient private Set<OutgoingEvent> outgoingEvents = new HashSet<OutgoingEvent>();
	static final private long serialVersionUID = 1446520852367386199l;
	private String uid;
	private String z_keyOfChildOnFamily;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 * @param name 
	 */
	public Child(Family owningObject, String name) {
		setName(name);
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for Child
	 */
	public Child() {
	}

	public void addAllToGodParent(Set<Relation> godParent) {
		for ( Relation o : godParent ) {
			addToGodParent(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void addAllToRelation(Set<Relation> relation) {
		for ( Relation o : relation ) {
			addToRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void addToGodParent(String firstName, String surname, Date dateOfBirth, Relation godParent) {
		if ( godParent!=null && !this.getGodParent().contains(godParent) ) {
			ChildHasRelation newLink = new ChildHasRelation((Child)this,(Relation)godParent);
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'godParent'");
			}
			if ( getDateOfBirth()==null ) {
				throw new IllegalStateException("The qualifying property 'dateOfBirth' must be set before adding a value to 'godParent'");
			}
			this.z_internalAddToChildHasRelation_godParent(firstName,surname,dateOfBirth,newLink);
			godParent.z_internalAddToChildHasRelation_child(this.getName(),this.getDateOfBirth(),newLink);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFamily().z_internalAddToChild(this.getName(),(Child)this);
	}
	
	public void addToRelation(String firstName, String surname, Date dateOfBirth, Relation relation) {
		if ( relation!=null && !this.getRelation().contains(relation) ) {
			FamilyMemberHasRelation newLink = new FamilyMemberHasRelation((FamilyMember)this,(Relation)relation);
			this.z_internalAddToFamilyMemberHasRelation_relation(firstName,surname,dateOfBirth,newLink);
			relation.z_internalAddToFamilyMemberHasRelation_familyMember(newLink);
		}
	}
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map) {
		setUid(xml.getAttribute("uid"));
		if ( xml.getAttribute("name").length()>0 ) {
			setName(ModelFormatter.getInstance().parseString(xml.getAttribute("name")));
		}
		if ( xml.getAttribute("dateOfBirth").length()>0 ) {
			setDateOfBirth(ModelFormatter.getInstance().parseDate(xml.getAttribute("dateOfBirth")));
		}
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
		
		}
	}
	
	public void clearGodParent() {
		Set<Relation> tmp = new HashSet<Relation>(getGodParent());
		for ( Relation o : tmp ) {
			removeFromGodParent(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void clearRelation() {
		Set<Relation> tmp = new HashSet<Relation>(getRelation());
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void copyShallowState(Child from, Child to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void copyState(Child from, Child to) {
		to.setName(from.getName());
		to.setDateOfBirth(from.getDateOfBirth());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof Child ) {
			return other==this || ((Child)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		return this.cancelledEvents;
	}
	
	public ChildHasRelation getChildHasRelation_godParent(String firstName, String surname, Date dateOfBirth) {
		ChildHasRelation result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		result=this.childHasRelation_godParent.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=8434948450232903107l,opposite="child",uuid="Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_V2hysIhqEeK4s7QGypAJBA@Structures.uml@_I7GooIhrEeK4s7QGypAJBA")
	public Set<ChildHasRelation> getChildHasRelation_godParent() {
		Set result = new HashSet<ChildHasRelation>(this.childHasRelation_godParent.values());
		
		return result;
	}
	
	public ChildHasRelation getChildHasRelation_godParentFor(Relation match) {
		for ( ChildHasRelation var : getChildHasRelation_godParent() ) {
			if ( var.getGodParent().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=3866952438253764885l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_9QxBMIlWEeK8Z-Y1T93HUw")
	@NumlMetaInfo(uuid="Structures.uml@_9QxBMIlWEeK8Z-Y1T93HUw")
	public Date getDateOfBirth() {
		Date result = this.dateOfBirth;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6981150938619650761l,opposite="child",uuid="Structures.uml@_lw6LwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_lw6LwIhqEeK4s7QGypAJBA")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth) {
		FamilyMemberHasRelation result = null;
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		result=this.familyMemberHasRelation_relation.get(key.toString());
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5718737559910777343l,opposite="familyMember",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_uAFMoIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_relation() {
		Set result = new HashSet<FamilyMemberHasRelation>(this.familyMemberHasRelation_relation.values());
		
		return result;
	}
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_relationFor(Relation match) {
		for ( FamilyMemberHasRelation var : getFamilyMemberHasRelation_relation() ) {
			if ( var.getRelation().equals(match) ) {
				return var;
			}
		}
		return null;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1858371569261639701l,opposite="child",uuid="Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	public Set<Relation> getGodParent() {
		Set result = new HashSet<Relation>();
		for ( ChildHasRelation cur : this.getChildHasRelation_godParent() ) {
			result.add(cur.getGodParent());
		}
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6583079073938083054l,uuid="Structures.uml@_3GSEsIiBEeKSGOXl2qggew")
	@NumlMetaInfo(uuid="Structures.uml@_3GSEsIiBEeKSGOXl2qggew")
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
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4408335886692542833l,opposite="familyMember",uuid="Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	public Set<Relation> getRelation() {
		Set result = new HashSet<Relation>();
		for ( FamilyMemberHasRelation cur : this.getFamilyMemberHasRelation_relation() ) {
			result.add(cur.getRelation());
		}
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfChildOnFamily() {
		return this.z_keyOfChildOnFamily;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToFamily((Family)owner);
	}
	
	public Child makeCopy() {
		Child result = new Child();
		copyState((Child)this,result);
		return result;
	}
	
	public Child makeShallowCopy() {
		Child result = new Child();
		copyShallowState((Child)this,result);
		return result;
	}
	
	public void markDeleted() {
		removeAllFromGodParent(getGodParent());
		removeAllFromRelation(getRelation());
		if ( getFamily()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("childHasRelation_godParent") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("8434948450232903107")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						ChildHasRelation childHasRelation_godParent = (ChildHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( childHasRelation_godParent!=null ) {
							z_internalAddToChildHasRelation_godParent(childHasRelation_godParent.getGodParent().getFirstName(),childHasRelation_godParent.getGodParent().getSurname(),childHasRelation_godParent.getGodParent().getDateOfBirth(),childHasRelation_godParent);
							childHasRelation_godParent.z_internalAddToChild(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_relation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5718737559910777343")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyMemberHasRelation familyMemberHasRelation_relation = (FamilyMemberHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( familyMemberHasRelation_relation!=null ) {
							z_internalAddToFamilyMemberHasRelation_relation(familyMemberHasRelation_relation.getRelation().getFirstName(),familyMemberHasRelation_relation.getRelation().getSurname(),familyMemberHasRelation_relation.getRelation().getDateOfBirth(),familyMemberHasRelation_relation);
							familyMemberHasRelation_relation.z_internalAddToFamilyMember(this);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromGodParent(Set<Relation> godParent) {
		Set<Relation> tmp = new HashSet<Relation>(godParent);
		for ( Relation o : tmp ) {
			removeFromGodParent(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeAllFromRelation(Set<Relation> relation) {
		Set<Relation> tmp = new HashSet<Relation>(relation);
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
		}
	}
	
	public void removeFromGodParent(String firstName, String surname, Date dateOfBirth, Relation godParent) {
		if ( godParent!=null ) {
			ChildHasRelation oldLink = getChildHasRelation_godParentFor(godParent);
			if ( oldLink!=null ) {
				godParent.z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),oldLink);
				oldLink.clear();
				z_internalRemoveFromChildHasRelation_godParent(godParent.getFirstName(),godParent.getSurname(),godParent.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromRelation(String firstName, String surname, Date dateOfBirth, Relation relation) {
		if ( relation!=null ) {
			FamilyMemberHasRelation oldLink = getFamilyMemberHasRelation_relationFor(relation);
			if ( oldLink!=null ) {
				relation.z_internalRemoveFromFamilyMemberHasRelation_familyMember(oldLink);
				oldLink.clear();
				z_internalRemoveFromFamilyMemberHasRelation_relation(relation.getFirstName(),relation.getSurname(),relation.getDateOfBirth(),oldLink);
			}
		}
	}
	
	public void setCancelledEvents(Set<CancelledEvent> cancelledEvents) {
		this.cancelledEvents=cancelledEvents;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
		if ( dateOfBirth == null ) {
			this.z_internalRemoveFromDateOfBirth(getDateOfBirth());
		} else {
			this.z_internalAddToDateOfBirth(dateOfBirth);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalAddToChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
	}
	
	public void setFamily(Family family) {
		if ( this.getFamily()!=null ) {
			this.getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( family == null ) {
			this.z_internalRemoveFromFamily(this.getFamily());
		} else {
			if ( getName()==null ) {
				throw new IllegalStateException("The qualifying property 'name' must be set before adding a value to 'family'");
			}
			this.z_internalAddToFamily(family);
		}
		if ( family!=null ) {
			family.z_internalAddToChild(this.getName(),this);
		}
	}
	
	public void setGodParent(Set<Relation> godParent) {
		this.clearGodParent();
		this.addAllToGodParent(godParent);
	}
	
	public void setName(String name) {
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalRemoveFromChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
		}
		for ( ChildHasRelation curVal : getChildHasRelation_godParent() ) {
			curVal.getGodParent().z_internalAddToChildHasRelation_child(this.getName(),this.getDateOfBirth(),curVal);
		}
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalAddToChild(this.getName(),this);
		}
	}
	
	public void setOutgoingEvents(Set<OutgoingEvent> outgoingEvents) {
		this.outgoingEvents=outgoingEvents;
	}
	
	public void setRelation(Set<Relation> relation) {
		this.clearRelation();
		this.addAllToRelation(relation);
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setZ_keyOfChildOnFamily(String z_keyOfChildOnFamily) {
		this.z_keyOfChildOnFamily=z_keyOfChildOnFamily;
	}
	
	public String toXmlReferenceString() {
		return "<Child uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Child ");
		sb.append("classUuid=\"Structures.uml@_V2hysIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"org.opaeum.test.Child\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		if ( getDateOfBirth()!=null ) {
			sb.append("dateOfBirth=\""+ ModelFormatter.getInstance().formatDate(getDateOfBirth())+"\" ");
		}
		sb.append(">");
		sb.append("\n<childHasRelation_godParent propertyId=\"8434948450232903107\">");
		for ( ChildHasRelation childHasRelation_godParent : getChildHasRelation_godParent() ) {
			sb.append("\n" + childHasRelation_godParent.toXmlReferenceString());
		}
		sb.append("\n</childHasRelation_godParent>");
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n</Child>");
		return sb.toString();
	}
	
	public void z_internalAddToChildHasRelation_godParent(String firstName, String surname, Date dateOfBirth, ChildHasRelation childHasRelation_godParent) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		if ( getChildHasRelation_godParent().contains(childHasRelation_godParent) ) {
			return;
		}
		childHasRelation_godParent.getGodParent().z_internalAddToFirstName(firstName);
		childHasRelation_godParent.getGodParent().z_internalAddToSurname(surname);
		childHasRelation_godParent.getGodParent().z_internalAddToDateOfBirth(dateOfBirth);
		this.childHasRelation_godParent.put(key.toString(),childHasRelation_godParent);
		childHasRelation_godParent.setZ_keyOfGodParentOnChild(key.toString());
	}
	
	public void z_internalAddToDateOfBirth(Date dateOfBirth) {
		if ( dateOfBirth.equals(getDateOfBirth()) ) {
			return;
		}
		this.dateOfBirth=dateOfBirth;
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth, FamilyMemberHasRelation familyMemberHasRelation_relation) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		if ( getFamilyMemberHasRelation_relation().contains(familyMemberHasRelation_relation) ) {
			return;
		}
		familyMemberHasRelation_relation.getRelation().z_internalAddToFirstName(firstName);
		familyMemberHasRelation_relation.getRelation().z_internalAddToSurname(surname);
		familyMemberHasRelation_relation.getRelation().z_internalAddToDateOfBirth(dateOfBirth);
		this.familyMemberHasRelation_relation.put(key.toString(),familyMemberHasRelation_relation);
		familyMemberHasRelation_relation.setZ_keyOfRelationOnFamilyMember(key.toString());
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(getName()) ) {
			return;
		}
		this.name=name;
	}
	
	public void z_internalRemoveFromChildHasRelation_godParent(String firstName, String surname, Date dateOfBirth, ChildHasRelation childHasRelation_godParent) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		this.childHasRelation_godParent.remove(key.toString());
	}
	
	public void z_internalRemoveFromDateOfBirth(Date dateOfBirth) {
		if ( getDateOfBirth()!=null && dateOfBirth!=null && dateOfBirth.equals(getDateOfBirth()) ) {
			this.dateOfBirth=null;
			this.dateOfBirth=null;
		}
	}
	
	public void z_internalRemoveFromFamily(Family family) {
		if ( getFamily()!=null && family!=null && family.equals(getFamily()) ) {
			this.family=null;
			this.family=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth, FamilyMemberHasRelation familyMemberHasRelation_relation) {
		String key = ModelFormatter.getInstance().formatStringQualifier(firstName)+ModelFormatter.getInstance().formatStringQualifier(surname)+ModelFormatter.getInstance().formatDateQualifier(dateOfBirth);
		this.familyMemberHasRelation_relation.remove(key.toString());
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}

}