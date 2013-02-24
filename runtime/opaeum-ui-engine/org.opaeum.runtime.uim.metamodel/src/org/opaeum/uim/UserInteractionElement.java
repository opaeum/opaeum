package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface UserInteractionElement extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getName();
	
	public String getUid();
	
	public boolean isUnderUserControl();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setName(String name);
	
	public void setUid(String uid);
	
	public void setUnderUserControl(boolean underUserControl);

}