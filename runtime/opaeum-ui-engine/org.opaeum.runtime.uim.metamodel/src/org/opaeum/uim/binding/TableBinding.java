package org.opaeum.uim.binding;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.component.UimDataTable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface TableBinding extends EObject, UimBinding {
	public void buildTreeFromXml(Element xml);
	
	public UimDataTable getTable();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setTable(UimDataTable table);
	
	public void setUid(String uid);

}