package org.opaeum.uim.wizard;

import org.opaeum.ecore.EObject;
import org.opaeum.runtime.domain.EcoreDataTypeParser;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.uim.Page;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface WizardPage extends EObject, Page {
	public void buildTreeFromXml(Element xml);
	
	public String getUid();
	
	public AbstractWizard getWizard();
	
	public void populateReferencesFromXml(Element xml);
	
	public void setUid(String uid);
	
	public void setWizard(AbstractWizard wizard);

}