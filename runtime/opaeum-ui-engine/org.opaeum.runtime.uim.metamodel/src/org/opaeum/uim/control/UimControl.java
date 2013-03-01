package org.opaeum.uim.control;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.component.UimField;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface UimControl extends EObject {
	public void buildTreeFromXml(Element xml);
	
	public UimField getField();
	
	public String getMimumWidth();
	
	public Integer getMinimumHeight();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setField(UimField field);
	
	public void setMimumWidth(String mimumWidth);
	
	public void setMinimumHeight(Integer minimumHeight);
	
	public void setUid(String uid);

}