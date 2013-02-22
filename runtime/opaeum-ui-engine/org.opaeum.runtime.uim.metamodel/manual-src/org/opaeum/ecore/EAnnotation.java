package org.opaeum.ecore;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

public interface EAnnotation extends EObject{
	List<EObject> getContents();
	List<EObject> getReferences();
	List<EStringToStringMapEntry> getDetails();
	void setEModelElement(EModelElement eModelElement);
	EModelElement getEModelElement();


	String getUid();
	
	void setUid(String uid);
	
	void setSource(String src);
	
	String getSource();
	void populateReferencesFromXml(Element currentPropertyValueNode,Map<String,Object> map);
}