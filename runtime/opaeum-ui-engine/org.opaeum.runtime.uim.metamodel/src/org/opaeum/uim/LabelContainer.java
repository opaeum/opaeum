package org.opaeum.uim;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface LabelContainer extends EObject {
	public void buildTreeFromXml(Element xml);
	
	public Labels getLabelOverride();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setLabelOverride(Labels labelOverride);
	
	public void setUid(String uid);

}