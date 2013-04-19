package org.opaeum.uim.component;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface MasterComponent extends EObject {
	public void buildTreeFromXml(Element xml);
	
	public List<DetailComponent> getDetailComponents();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setDetailComponents(List<DetailComponent> detailComponents);
	
	public void setUid(String uid);

}