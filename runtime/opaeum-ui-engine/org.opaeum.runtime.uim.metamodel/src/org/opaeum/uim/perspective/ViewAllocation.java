package org.opaeum.uim.perspective;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInteractionElement;
import org.w3c.dom.Element;

public interface ViewAllocation extends EObject, UserInteractionElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public Integer getHeight();
	
	public PositionInPerspective getPosition();
	
	public String getUid();
	
	public Integer getWidth();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setHeight(Integer height);
	
	public void setPosition(PositionInPerspective position);
	
	public void setUid(String uid);
	
	public void setWidth(Integer width);

}