package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
public class FamilyMemberHasRelation implements CompositionNode, Serializable {
	protected FamilyMember familyMember;
	protected Relation relation;
	static final private long serialVersionUID = 6261560761254585007l;
	private String uid;
	private String z_keyOfRelationOnFamilyMember;

	/** Constructor for FamilyMemberHasRelation
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public FamilyMemberHasRelation(FamilyMember end1, Relation end2) {
		this.z_internalAddToFamilyMember(end1);
		this.z_internalAddToRelation(end2);
	}
	
	/** Constructor for FamilyMemberHasRelation
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public FamilyMemberHasRelation(Relation end2, FamilyMember end1) {
		this.z_internalAddToFamilyMember(end1);
		this.z_internalAddToRelation(end2);
	}
	
	/** Default constructor for FamilyMemberHasRelation
	 */
	public FamilyMemberHasRelation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
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
		this.z_internalRemoveFromRelation(getRelation());
		this.z_internalRemoveFromFamilyMember(getFamilyMember());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof FamilyMemberHasRelation ) {
			return other==this || ((FamilyMemberHasRelation)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6572647679031263107l,opposite="familyMemberHasRelation_relation",uuid="Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA@Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
	public FamilyMember getFamilyMember() {
		FamilyMember result = this.familyMember;
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getRelation();
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=9194987951453404123l,opposite="familyMemberHasRelation_familyMember",uuid="Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	public Relation getRelation() {
		Relation result = this.relation;
		
		return result;
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfRelationOnFamilyMember() {
		return this.z_keyOfRelationOnFamilyMember;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToRelation((Relation)owner);
	}
	
	public void markDeleted() {
	}
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map) {
		NodeList propertyNodes = xml.getChildNodes();
		int i = 0;
		while ( i<propertyNodes.getLength() ) {
			Node currentPropertyNode = propertyNodes.item(i++);
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("familyMember") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("6572647679031263107")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						FamilyMember familyMember = (FamilyMember)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( familyMember!=null ) {
							z_internalAddToFamilyMember(familyMember);
							familyMember.z_internalAddToFamilyMemberHasRelation_relation(this);
						}
					}
				}
			}
			if ( currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals("relation") || ((Element)currentPropertyNode).getAttribute("propertyId").equals("9194987951453404123")) ) {
				NodeList propertyValueNodes = currentPropertyNode.getChildNodes();
				int j = 0;
				while ( j<propertyValueNodes.getLength() ) {
					Node currentPropertyValueNode = propertyValueNodes.item(j++);
					if ( currentPropertyValueNode instanceof Element ) {
						Relation relation = (Relation)map.get(((Element)currentPropertyValueNode).getAttribute("uid"));
						if ( relation!=null ) {
							z_internalAddToRelation(relation);
							relation.z_internalAddToFamilyMemberHasRelation_familyMember(this);
						}
					}
				}
			}
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setZ_keyOfRelationOnFamilyMember(String z_keyOfRelationOnFamilyMember) {
		this.z_keyOfRelationOnFamilyMember=z_keyOfRelationOnFamilyMember;
	}
	
	public String toXmlReferenceString() {
		return "<FamilyMemberHasRelation uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<FamilyMemberHasRelation ");
		sb.append("classUuid=\"Structures.uml@_wPOkwIhqEeK4s7QGypAJBA\" ");
		sb.append("className=\"model.FamilyMemberHasRelation\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		if ( getFamilyMember()==null ) {
			sb.append("\n<familyMember/>");
		} else {
			sb.append("\n<familyMember propertyId=\"6572647679031263107\">");
			sb.append("\n" + getFamilyMember().toXmlReferenceString());
			sb.append("\n</familyMember>");
		}
		if ( getRelation()==null ) {
			sb.append("\n<relation/>");
		} else {
			sb.append("\n<relation propertyId=\"9194987951453404123\">");
			sb.append("\n" + getRelation().toXmlReferenceString());
			sb.append("\n</relation>");
		}
		sb.append("\n</FamilyMemberHasRelation>");
		return sb.toString();
	}
	
	public void z_internalAddToFamilyMember(FamilyMember familyMember) {
		if ( familyMember.equals(getFamilyMember()) ) {
			return;
		}
		this.familyMember=familyMember;
	}
	
	public void z_internalAddToRelation(Relation relation) {
		if ( relation.equals(getRelation()) ) {
			return;
		}
		this.relation=relation;
	}
	
	public void z_internalRemoveFromFamilyMember(FamilyMember familyMember) {
		if ( getFamilyMember()!=null && familyMember!=null && familyMember.equals(getFamilyMember()) ) {
			this.familyMember=null;
			this.familyMember=null;
		}
	}
	
	public void z_internalRemoveFromRelation(Relation relation) {
		if ( getRelation()!=null && relation!=null && relation.equals(getRelation()) ) {
			this.relation=null;
			this.relation=null;
		}
	}

}