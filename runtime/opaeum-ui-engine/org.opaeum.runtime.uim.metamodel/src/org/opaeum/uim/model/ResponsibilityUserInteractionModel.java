package org.opaeum.uim.model;

import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.editor.ResponsibilityViewer;
import org.opaeum.uim.wizard.ResponsibilityInvocationWizard;
import org.w3c.dom.Element;

public interface ResponsibilityUserInteractionModel extends EObject, AbstractUserInteractionModel {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public ResponsibilityInvocationWizard getInvocationWizard();
	
	public String getUid();
	
	public ResponsibilityViewer getViewer();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setInvocationWizard(ResponsibilityInvocationWizard invocationWizard);
	
	public void setUid(String uid);
	
	public void setViewer(ResponsibilityViewer viewer);

}