package org.opaeum.uim.binding;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.component.UimDataTable;
import org.w3c.dom.Element;

public interface TableBinding extends EObject, UimBinding {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UimDataTable getTable();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setTable(UimDataTable table);
	
	public void setUid(String uid);

}