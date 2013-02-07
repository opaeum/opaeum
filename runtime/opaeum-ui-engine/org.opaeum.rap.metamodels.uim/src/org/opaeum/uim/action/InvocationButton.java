package org.opaeum.uim.action;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface InvocationButton extends EObject, AbstractActionButton, LabeledElement {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public UserInterfaceRoot getPopup();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setPopup(UserInterfaceRoot popup);
	
	public void setUid(String uid);

}