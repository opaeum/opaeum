package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IEventGenerator;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_V2hysIhqEeK4s7QGypAJBA")
public class Child implements IEventGenerator, CompositionNode, FamilyMember, Serializable {
	transient private Set<CancelledEvent> cancelledEvents = new HashSet<CancelledEvent>();
	protected Family family;
	protected Map<String, FamilyMemberHasRelation> familyMemberHasRelation_relation = new HashMap<String,FamilyMemberHasRelation>();
	protected Relation godParent;
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

	public void addAllToRelation(Set<Relation> relation) {
		for ( Relation o : relation ) {
			addToRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
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
			this.z_internalAddToFamilyMemberHasRelation_relation(relation.getFirstName(),relation.getSurname(),relation.getDateOfBirth(),newLink);
			relation.z_internalAddToFamilyMemberHasRelation_familyMember(newLink);
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
	}
	
	public void copyState(Child from, Child to) {
		to.setName(from.getName());
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
	@NumlMetaInfo(uuid="Structures.uml@_I7BwIIhrEeK4s7QGypAJBA")
	public Relation getGodParent() {
		Relation result = this.godParent;
		
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
		if ( getGodParent()!=null ) {
			getGodParent().z_internalRemoveFromChild(this);
		}
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
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMemberHasRelation_relation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("5718737559910777343")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyMemberHasRelation familyMemberHasRelation_relation = (FamilyMemberHasRelation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( familyMemberHasRelation_relation!=null ) {
							z_internalAddToFamilyMemberHasRelation_relation(familyMemberHasRelation_relation);
							familyMemberHasRelation_relation.z_internalAddToFamilyMember(this);
						}
					}
				}
			}
		}
	}
	
	public void removeAllFromRelation(Set<Relation> relation) {
		Set<Relation> tmp = new HashSet<Relation>(relation);
		for ( Relation o : tmp ) {
			removeFromRelation(o.getFirstName(),o.getSurname(),o.getDateOfBirth(),o);
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
	
	public void setFamily(Family family) {
		if ( this.getFamily()!=null ) {
			this.getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( family == null ) {
			this.z_internalRemoveFromFamily(this.getFamily());
		} else {
			this.z_internalAddToFamily(family);
		}
		if ( family!=null ) {
			family.z_internalAddToChild(this.getName(),this);
		}
	}
	
	public void setGodParent(Relation godParent) {
		Relation oldValue = this.getGodParent();
		if ( oldValue==null ) {
			if ( godParent!=null ) {
				Child oldOther = (Child)godParent.getChild();
				godParent.z_internalRemoveFromChild(oldOther);
				if ( oldOther != null ) {
					oldOther.z_internalRemoveFromGodParent(godParent);
				}
				godParent.z_internalAddToChild((Child)this);
			}
			this.z_internalAddToGodParent(godParent);
		} else {
			if ( !oldValue.equals(godParent) ) {
				oldValue.z_internalRemoveFromChild(this);
				z_internalRemoveFromGodParent(oldValue);
				if ( godParent!=null ) {
					Child oldOther = (Child)godParent.getChild();
					godParent.z_internalRemoveFromChild(oldOther);
					if ( oldOther != null ) {
						oldOther.z_internalRemoveFromGodParent(godParent);
					}
					godParent.z_internalAddToChild((Child)this);
				}
				this.z_internalAddToGodParent(godParent);
			}
		}
	}
	
	public void setName(String name) {
		if ( getFamily()!=null && getName()!=null ) {
			getFamily().z_internalRemoveFromChild(this.getName(),this);
		}
		if ( name == null ) {
			this.z_internalRemoveFromName(getName());
		} else {
			this.z_internalAddToName(name);
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
		sb.append("className=\"model.Child\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		if ( getName()!=null ) {
			sb.append("name=\""+ ModelFormatter.getInstance().formatString(getName())+"\" ");
		}
		sb.append(">");
		sb.append("\n<familyMemberHasRelation_relation propertyId=\"5718737559910777343\">");
		for ( FamilyMemberHasRelation familyMemberHasRelation_relation : getFamilyMemberHasRelation_relation() ) {
			sb.append("\n" + familyMemberHasRelation_relation.toXmlReferenceString());
		}
		sb.append("\n</familyMemberHasRelation_relation>");
		sb.append("\n</Child>");
		return sb.toString();
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
	
	public void z_internalAddToGodParent(Relation godParent) {
		if ( godParent.equals(getGodParent()) ) {
			return;
		}
		this.godParent=godParent;
	}
	
	public void z_internalAddToName(String name) {
		if ( name.equals(getName()) ) {
			return;
		}
		this.name=name;
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
	
	public void z_internalRemoveFromGodParent(Relation godParent) {
		if ( getGodParent()!=null && godParent!=null && godParent.equals(getGodParent()) ) {
			this.godParent=null;
			this.godParent=null;
		}
	}
	
	public void z_internalRemoveFromName(String name) {
		if ( getName()!=null && name!=null && name.equals(getName()) ) {
			this.name=null;
			this.name=null;
		}
	}

}