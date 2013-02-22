package org.opaeum.uim.wizard;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.model.ResponsibilityUserInteractionModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ResponsibilityInvocationWizard extends EObject, InvocationWizard {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ResponsibilityUserInteractionModel getModel();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setModel(ResponsibilityUserInteractionModel model);
	
	public void setUid(String uid);

}