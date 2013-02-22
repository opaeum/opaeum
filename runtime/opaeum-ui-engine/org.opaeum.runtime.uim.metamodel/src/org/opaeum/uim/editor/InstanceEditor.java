package org.opaeum.uim.editor;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface InstanceEditor extends EObject, AbstractEditor {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public MenuConfiguration getMenuConfiguration();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setMenuConfiguration(MenuConfiguration menuConfiguration);
	
	public void setUid(String uid);

}