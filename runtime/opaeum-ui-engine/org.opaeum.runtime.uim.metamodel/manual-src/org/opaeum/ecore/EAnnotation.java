package org.opaeum.ecore;
import java.util.List;

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
}