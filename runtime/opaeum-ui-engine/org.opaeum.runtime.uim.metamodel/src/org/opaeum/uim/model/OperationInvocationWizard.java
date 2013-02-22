package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.wizard.InvocationWizard;
import org.opaeum.uim.wizard.OperationResultPage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface OperationInvocationWizard extends EObject, AbstractUserInteractionModel, InvocationWizard {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public OperationResultPage getResultPage();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setResultPage(OperationResultPage resultPage);
	
	public void setUid(String uid);

}