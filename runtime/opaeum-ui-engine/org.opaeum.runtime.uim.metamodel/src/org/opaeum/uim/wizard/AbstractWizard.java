package org.opaeum.uim.wizard;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.UserInterfaceRoot;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface AbstractWizard extends EObject, UserInterfaceRoot {
	public void buildTreeFromXml(Element xml);
	
	public List<WizardPage> getPages();
	
	public String getUid();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setPages(List<WizardPage> pages);
	
	public void setUid(String uid);

}