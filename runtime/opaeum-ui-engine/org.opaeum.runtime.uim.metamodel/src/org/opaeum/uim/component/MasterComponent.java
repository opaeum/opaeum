package org.opaeum.uim.component;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.w3c.dom.Element;

public interface MasterComponent extends EObject {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<DetailComponent> getDetailComponents();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setDetailComponents(List<DetailComponent> detailComponents);
	
	public void setUid(String uid);

}