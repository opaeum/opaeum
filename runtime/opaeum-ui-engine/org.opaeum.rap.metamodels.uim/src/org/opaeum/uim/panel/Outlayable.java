package org.opaeum.uim.panel;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface Outlayable extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public Boolean getFillHorizontally();
	
	public Boolean getFillVertically();
	
	public Integer getPreferredHeight();
	
	public Integer getPreferredWidth();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setFillHorizontally(Boolean fillHorizontally);
	
	public void setFillVertically(Boolean fillVertically);
	
	public void setPreferredHeight(Integer preferredHeight);
	
	public void setPreferredWidth(Integer preferredWidth);
	
	public void setUid(String uid);

}