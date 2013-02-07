package org.opaeum.uim.wizard;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Page;
import org.opaeum.uim.model.OperationInvocationWizard;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface OperationResultPage extends EObject, Page {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public String getUid();
	
	public OperationInvocationWizard getWizard();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setUid(String uid);
	
	public void setWizard(OperationInvocationWizard wizard);

}