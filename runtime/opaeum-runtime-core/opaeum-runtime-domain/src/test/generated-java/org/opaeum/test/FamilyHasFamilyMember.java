package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_N7WfII08EeKHBNiW4NWnIg")
public class FamilyHasFamilyMember implements CompositionNode, Serializable {
	protected Family family;
	protected FamilyMember familyMember;
	static final private long serialVersionUID = 3933237201390277163l;
	private String uid;
	private String z_keyOfFamilyMemberOnFamily;

	/** Constructor for FamilyHasFamilyMember
	 * 
	 * @param end1 
	 * @param end2 
	 */
	public FamilyHasFamilyMember(Family end1, FamilyMember end2) {
		this.z_internalAddToFamily(end1);
		this.z_internalAddToFamilyMember(end2);
	}
	
	/** Constructor for FamilyHasFamilyMember
	 * 
	 * @param end2 
	 * @param end1 
	 */
	public FamilyHasFamilyMember(FamilyMember end2, Family end1) {
		this.z_internalAddToFamily(end1);
		this.z_internalAddToFamilyMember(end2);
	}
	
	/** Default constructor for FamilyHasFamilyMember
	 */
	public FamilyHasFamilyMember() {
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
		this.z_internalRemoveFromFamilyMember(getFamilyMember());
		this.z_internalRemoveFromFamily(getFamily());
	}
	
	public void createComponents() {
	}
	
	public boolean equals(Object other) {
		if ( other instanceof FamilyHasFamilyMember ) {
			return other==this || ((FamilyHasFamilyMember)other).getUid().equals(this.getUid());
		}
		return false;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5122450861613748053l,opposite="familyHasFamilyMember_familyMember",uuid="Structures.uml@_N7WfIY08EeKHBNiW4NWnIg")
	@NumlMetaInfo(uuid="Structures.uml@_N7WfII08EeKHBNiW4NWnIg@Structures.uml@_N7WfIY08EeKHBNiW4NWnIg")
	public Family getFamily() {
		Family result = this.family;
		
		return result;
	}
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4571259680665147515l,opposite="familyHasFamilyMember_family",uuid="Structures.uml@_N7V4EI08EeKHBNiW4NWnIg")
	@NumlMetaInfo(uuid="Structures.uml@_N7WfII08EeKHBNiW4NWnIg@Structures.uml@_N7V4EI08EeKHBNiW4NWnIg")
	public FamilyMember getFamilyMember() {
		FamilyMember result = this.familyMember;
		
		return result;
	}
	
	public CompositionNode getOwningObject() {
		return getFamilyMember();
	}
	
	public String getUid() {
		if ( this.uid==null || this.uid.trim().length()==0 ) {
			uid=UUID.randomUUID().toString();
		}
		return this.uid;
	}
	
	public String getZ_keyOfFamilyMemberOnFamily() {
		return this.z_keyOfFamilyMemberOnFamily;
	}
	
	public int hashCode() {
		return getUid().hashCode();
	}
	
	public void init(CompositionNode owner) {
		this.z_internalAddToFamilyMember((FamilyMember)owner);
	}
	
	public void markDeleted() {
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
	
	public void setUid(String newUid) {
		this.uid=newUid;
	}
	
	public void setZ_keyOfFamilyMemberOnFamily(String z_keyOfFamilyMemberOnFamily) {
		this.z_keyOfFamilyMemberOnFamily=z_keyOfFamilyMemberOnFamily;
	}
	
	public String toXmlReferenceString() {
		return "<FamilyHasFamilyMember uid=\""+getUid() + "\"/>";
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<FamilyHasFamilyMember ");
		sb.append("classUuid=\"Structures.uml@_N7WfII08EeKHBNiW4NWnIg\" ");
		sb.append("className=\"org.opaeum.test.FamilyHasFamilyMember\" ");
		sb.append("uid=\"" + this.getUid() + "\" ");
		sb.append(">");
		sb.append("\n</FamilyHasFamilyMember>");
		return sb.toString();
	}
	
	public void z_internalAddToFamily(Family family) {
		if ( family.equals(getFamily()) ) {
			return;
		}
		this.family=family;
	}
	
	public void z_internalAddToFamilyMember(FamilyMember familyMember) {
		if ( familyMember.equals(getFamilyMember()) ) {
			return;
		}
		this.familyMember=familyMember;
	}
	
	public void z_internalRemoveFromFamily(Family family) {
		if ( getFamily()!=null && family!=null && family.equals(getFamily()) ) {
			this.family=null;
			this.family=null;
		}
	}
	
	public void z_internalRemoveFromFamilyMember(FamilyMember familyMember) {
		if ( getFamilyMember()!=null && familyMember!=null && familyMember.equals(getFamilyMember()) ) {
			this.familyMember=null;
			this.familyMember=null;
		}
	}

}