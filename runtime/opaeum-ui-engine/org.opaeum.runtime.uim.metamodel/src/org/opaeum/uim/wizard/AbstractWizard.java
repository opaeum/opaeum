package org.opaeum.uim.wizard;

import java.util.List;
import java.util.Map;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;

public interface AbstractWizard extends EObject, UserInterfaceRoot {
	public void buildTreeFromXml(Element xml, Map<String, Object> map);
	
	public List<WizardPage> getPages();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml, Map<String, Object> map);
	
	public void setPages(List<WizardPage> pages);
	
	public void setUid(String uid);

}