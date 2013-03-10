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
import org.opaeum.runtime.strategy.DateStrategyFactory;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA")
public interface Relation extends CompositionNode, Serializable {
	public void addToFamilyMember(FamilyMember familyMember);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearFamilyMember();
	
	public FamilyMemberHasRelation createFamilyMemberHasRelation_familyMember();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=984124978325014811l,opposite="godParent",uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_I7GooYhrEeK4s7QGypAJBA")
	public Child getChild();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4576801132852053498l,strategyFactory=DateStrategyFactory.class,uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_wFrE4IjSEeKq68owPnlvHg")
	public Date getDateOfBirth();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=1729227443467658447l,opposite="relation",uuid="Structures.uml@_wPOkxIhqEeK4s7QGypAJBA")
	public Set<FamilyMember> getFamilyMember();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=3903606770219243395l,opposite="relation",uuid="Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	@NumlMetaInfo(uuid="Structures.uml@_bVPeIIhqEeK4s7QGypAJBA@Structures.uml@_wPOkwIhqEeK4s7QGypAJBA")
	public Set<FamilyMemberHasRelation> getFamilyMemberHasRelation_familyMember();
	
	public FamilyMemberHasRelation getFamilyMemberHasRelation_familyMemberFor(FamilyMember match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4472055897408457748l,uuid="Structures.uml@_ojWi8IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_ojWi8IjSEeKq68owPnlvHg")
	public String getFirstName();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=7182092940400916328l,uuid="Structures.uml@_rPn78IjSEeKq68owPnlvHg")
	@NumlMetaInfo(uuid="Structures.uml@_rPn78IjSEeKq68owPnlvHg")
	public String getSurname();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromFamilyMember(FamilyMember familyMember);
	
	public void setChild(Child child);
	
	public void setDateOfBirth(Date dateOfBirth);
	
	public void setFamilyMember(Set<FamilyMember> familyMember);
	
	public void setFirstName(String firstName);
	
	public void setSurname(String surname);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToChild(Child child);
	
	public void z_internalAddToDateOfBirth(Date dateOfBirth);
	
	public void z_internalAddToFamilyMemberHasRelation_familyMember(FamilyMemberHasRelation familyMemberHasRelation_familyMember);
	
	public void z_internalAddToFirstName(String firstName);
	
	public void z_internalAddToSurname(String surname);
	
	public void z_internalRemoveFromChild(Child child);
	
	public void z_internalRemoveFromDateOfBirth(Date dateOfBirth);
	
	public void z_internalRemoveFromFamilyMemberHasRelation_familyMember(FamilyMemberHasRelation familyMemberHasRelation_familyMember);
	
	public void z_internalRemoveFromFirstName(String firstName);
	
	public void z_internalRemoveFromSurname(String surname);

}