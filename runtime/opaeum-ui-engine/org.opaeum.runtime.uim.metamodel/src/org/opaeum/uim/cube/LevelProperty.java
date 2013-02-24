package org.opaeum.uim.cube;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UmlReference;
import org.w3c.dom.Element;

public interface LevelProperty extends EObject, UmlReference, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);

}