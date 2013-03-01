package org.opaeum.uim;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface PageOrdering extends EObject, LabeledElement {
	public void buildTreeFromXml(Element xml);
	
	public Page getPage();
	
	public int getPosition();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setPage(Page page);
	
	public void setPosition(int position);
	
	public void setUid(String uid);

}