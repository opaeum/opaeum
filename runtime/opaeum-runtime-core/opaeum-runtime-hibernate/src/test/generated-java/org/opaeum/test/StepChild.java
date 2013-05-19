package org.opaeum.test;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.runtime.domain.HibernateEntity;
import org.opaeum.runtime.domain.IPersistentObject;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="opaeum_hibernate_tests",uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg")
public interface StepChild extends HibernateEntity, CompositionNode, Serializable, IPersistentObject {
	public void addToStepSibling(Child stepSibling);
	
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public void clearStepSibling();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5616902939022633687l,opposite="stepChild",uuid="Structures.uml@_0vhRgYlZEeKhILqZBrW9Hg")
	public Family getFamily();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7517388397186460345l,opposite="stepChild",uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	public FamilyStepChild getFamilyStepChild_family();
	
	public FamilyStepChild getFamilyStepChild_familyFor(Family match);
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=2474112361200246321l,opposite="stepChild",uuid="Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_ncdcsI1OEeKgGLBcRSZFfw")
	public MotherStepChildren getMotherStepChildren_stepMother();
	
	public MotherStepChildren getMotherStepChildren_stepMotherFor(Mother match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6393952343144368733l,uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	public String getName();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=844736339179565915l,opposite="stepSibling1",uuid="Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_1X1ycI1OEeKgGLBcRSZFfw")
	public Set<SiblingStepSibling> getSiblingStepSibling_stepSibling();
	
	public SiblingStepSibling getSiblingStepSibling_stepSiblingFor(Child match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=2838340376300211263l,opposite="stepChild",uuid="Structures.uml@_ncdcsY1OEeKgGLBcRSZFfw")
	public Mother getStepMother();
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=4467716398320891669l,opposite="stepSibling",uuid="Structures.uml@_1X1ycY1OEeKgGLBcRSZFfw")
	public Set<Child> getStepSibling();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void removeFromStepSibling(Child stepSibling);
	
	public void setFamily(Family family);
	
	public void setName(String name);
	
	public void setStepMother(Mother stepMother);
	
	public void setStepSibling(Set<Child> stepSibling);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToFamilyStepChild_family(FamilyStepChild familyStepChild_family);
	
	public void z_internalAddToMotherStepChildren_stepMother(MotherStepChildren motherStepChildren_stepMother);
	
	public void z_internalAddToName(String name);
	
	public void z_internalAddToSiblingStepSibling_stepSibling(SiblingStepSibling siblingStepSibling_stepSibling);
	
	public void z_internalRemoveFromFamilyStepChild_family(FamilyStepChild familyStepChild_family);
	
	public void z_internalRemoveFromMotherStepChildren_stepMother(MotherStepChildren motherStepChildren_stepMother);
	
	public void z_internalRemoveFromName(String name);
	
	public void z_internalRemoveFromSiblingStepSibling_stepSibling(SiblingStepSibling siblingStepSibling_stepSibling);

}