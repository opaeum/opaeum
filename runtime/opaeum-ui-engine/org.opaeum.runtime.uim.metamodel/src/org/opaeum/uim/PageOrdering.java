package org.opaeum.uim;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface PageOrdering extends EObject, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public Page getPage();
	
	public int getPosition();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setPage(Page page);
	
	public void setPosition(int position);
	
	public void setUid(String uid);

}