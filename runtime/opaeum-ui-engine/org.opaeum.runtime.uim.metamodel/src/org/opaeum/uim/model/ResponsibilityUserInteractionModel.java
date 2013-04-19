package org.opaeum.uim.model;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface ResponsibilityUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public void buildTreeFromXml(Element xml);
	
	public ResponsibilityInvocationWizard getInvocationWizard();
	
	public String getUid();
	
	public ResponsibilityViewer getViewer();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setInvocationWizard(ResponsibilityInvocationWizard invocationWizard);
	
	public void setUid(String uid);
	
	public void setViewer(ResponsibilityViewer viewer);

}