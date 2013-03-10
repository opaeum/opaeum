package org.opaeum.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.annotation.PropertyMetaInfo;
import org.opaeum.runtime.domain.CompositionNode;
import org.opaeum.test.util.ModelFormatter;
import org.w3c.dom.Element;

@NumlMetaInfo(applicationIdentifier="structuretests",uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg")
public interface StepChild extends CompositionNode, Serializable {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=5616902939022633687l,opposite="stepChild",uuid="Structures.uml@_0vhRgYlZEeKhILqZBrW9Hg")
	public Family getFamily();
	
	@PropertyMetaInfo(constraints={},isComposite=true,opaeumId=7517388397186460345l,opposite="stepChild",uuid="Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_o7BvwIlZEeKhILqZBrW9Hg@Structures.uml@_0vhRgIlZEeKhILqZBrW9Hg")
	public FamilyStepChild getFamilyStepChild_family();
	
	public FamilyStepChild getFamilyStepChild_familyFor(Family match);
	
	@PropertyMetaInfo(constraints={},isComposite=false,opaeumId=6393952343144368733l,uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	@NumlMetaInfo(uuid="Structures.uml@_vm81MIlZEeKhILqZBrW9Hg")
	public String getName();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setFamily(Family family);
	
	public void setName(String name);
	
	public String toXmlReferenceString();
	
	public String toXmlString();
	
	public void z_internalAddToFamilyStepChild_family(FamilyStepChild familyStepChild_family);
	
	public void z_internalAddToName(String name);
	
	public void z_internalRemoveFromFamilyStepChild_family(FamilyStepChild familyStepChild_family);
	
	public void z_internalRemoveFromName(String name);

}