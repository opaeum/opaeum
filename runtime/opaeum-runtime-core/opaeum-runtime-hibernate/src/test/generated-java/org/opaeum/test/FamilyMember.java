package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_uAFMoIhqEeK4s7QGypAJBA")
public interface FamilyMember extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToRelation(String firstName, String surname, Date dateOfBirth, Relation relation);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearRelation();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6736659446556850677l,opposite="familyMember",uuid="Structures.uml@_N7WfIY08EeKHBNiW4NWnIg")
	public Family getFamily();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5718737559910777343l,opposite="familyMember",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_uAFMoIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_relation();
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_relationFor(Relation match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2192193425227496123l,uuid="Structures.uml@_mEtEwI08EeKHBNiW4NWnIg")
	@NumlMetaInfo(uuid="Structures.uml@_mEtEwI08EeKHBNiW4NWnIg")
	public String getName();
	
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