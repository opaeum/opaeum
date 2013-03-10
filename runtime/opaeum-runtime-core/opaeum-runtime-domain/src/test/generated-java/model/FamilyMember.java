package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import model.util.ModelFormatter;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_uAFMoIhqEeK4s7QGypAJBA")
public interface FamilyMember extends CompositionNode, Serializable {
	public void addToRelation(String firstName, String surname, Date dateOfBirth, Relation relation);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearRelation();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5718737559910777343l,opposite="familyMember",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_uAFMoIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_relation();
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_relationFor(Relation match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4408335886692542833l,opposite="familyMember",uuid="Structures.uml@_wPOkwYhqEeK4s7QGypAJBA")
	public Set<Relation> getRelation();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromRelation(String firstName, String surname, Date dateOfBirth, Relation relation);
	
	public void setRelation(Set<Relation> relation);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth, FamilyMemberHasRelation familyMemberHasRelation_relation);
	
	public void z_internalRemoveFromFamilyMemberHasRelation_relation(String firstName, String surname, Date dateOfBirth, FamilyMemberHasRelation familyMemberHasRelation_relation);

}